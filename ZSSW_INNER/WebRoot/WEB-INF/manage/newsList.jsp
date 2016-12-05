<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<style>
 .leftv {
 	text-align: left;
 	width: 75px;
 }
 .rightv {
 	text-align: right;
 	width: 75px;
 }
</style>

<script type="text/javascript">
	$(document).ready(function() {
	
		var columnId=$("#columnId").val();
	
		//切换到另一页
		$(".pagination a").click(function() {
			loadList("#tab1",$("#columnId").val(),$(this).attr("title"));
		});
		
		//删除一条
		$(".forth button").click(function (){
		var uri="http://localhost:8080/shuixin/manage/news_delete.action";
			if(window.confirm("confirm to delete this?")){				
				var id=$(this).parent().parent().attr("id");
				$.post(uri,
						{"columnId":columnId,"news.id":id},
						function(data){	
							if(data){								
								$('#'+id).remove();
							}	
						},
						"json");
			}			
		});
		
		//修改信息状态
		$(".fivth button").click(function (){
			if(columnId=="zsk1000"||columnId=="fgk2000"){
				return;
			}
			var id=$(this).parent().parent().attr("id");
			var uri="http://localhost:8080/shuixin/manage/news_status.action";
			var msg="修改为不显示后，客户端将查询不到该信息。确定修改吗？";
			var ele=$(this);
			var status=ele.attr("name");
			if(status==0){				
				msg="修改为显示后，客户端能够查询到该信息。确定修改吗？";					
			}else{
				status=0;
			}
			if(window.confirm(msg,"修改信息状态？")){				
				$.post(uri,{"columnId":columnId,"news.id":id,"news.visibility":status},
						function(data){	
							if(data){								
								if(status){
									ele.text("显示");
									ele.removeClass("rightv");									
									ele.addClass("leftv");
									ele.attr("name","1");
								}else{
									ele.text("不显示");
									ele.remove("leftv").addClass("rightv");
									ele.attr("name","0");
								}
							}	
						},
						"json");			
			}
		});
		
		
		
		
		
	});
</script>

<input id="columnId" type="hidden" value="${columnId}" />
<table class="table table-bordered xx-fix">
	<thead>
		<tr>
			<th>
				标题
			</th>
			<th>
				内容
			</th>
			<th>
				修改
			</th>
			<th>
				删除
			</th>
			<th>
				状态
			</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="page.list" status="status">
			<tr id="${id}" class="row${status.index%2} }">
				<td class="first">
					${title}
				</td>
				<td class="second">
					${textContent}......
				</td>
				<td class="third">
					<button class="btn btn-primary btn-mini">
						<i class="icon-edit icon-white"></i> 编辑修改
					</button>
				</td>
				<td class="forth">
					<button class="btn btn-primary btn-mini">
						<i class="icon-remove icon-white"></i> 删除该条
					</button>
				</td>
				<td class="fivth">
						<s:if test="visibility==0" >
							<button class="btn btn-primary btn-mini rightv" name="${visibility}" >
							不显示</button>
						</s:if>
						<s:else >
							<button class="btn btn-primary btn-mini leftv" name="${visibility}" >
							显示</button>
						</s:else>	
				</td>
			</tr>
		</s:iterator>
	</tbody>
</table>
<div class="pagination alternate">
	<ul>
		<li >
			<a href="javascript:;" title="<s:property value="page.topPageNo"/>">首页</a>
		</li>
		<li >
			<a href="javascript:;" title="<s:property value="page.previousPageNo"/>">前一页</a>
		</li>
		<s:iterator begin="1" end="%{page.bottomPageNo>9?9:page.bottomPageNo}"
			var="num">
			<s:if test="#num==page.pageNo">
				<li class="active">
					<a href="javascript:;">${num}</a>
				</li>
			</s:if>
			<s:else>
				<li>
					<a href="javascript:;" title="${num}">${num}</a>
				</li>
			</s:else>
		</s:iterator>
		<li>
			<a href="javascript:;" title="<s:property value="page.nextPageNo"/>">下一页</a>
		</li>
		<li>
			<a href="javascript:;" title="<s:property value="page.bottomPageNo"/>">尾页</a>
		</li>
	</ul>
</div>



