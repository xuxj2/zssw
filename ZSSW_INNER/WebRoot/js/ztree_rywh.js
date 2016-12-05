/**
 * 条线编辑js函数库
 */
var ids;
var names;
var setting = {
	check : {
		enable : true
	},
	view : {
		dblClickExpand : true
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		onClick: zTreeOnClick,
		onCheck : onCheck
	}
};
function zTreeOnClick(event, treeId, treeNode) {
	if(treeNode.children==null){
		
		var url="manage/rywh_selectSwry.action";
		$.post(url,{
			"swryDm":treeNode.id
				},function(data){
					$("#tabDetail").html(data);
					$("#tabDetail").css("display","block");
					$("#form-horizontal").css("display","none");
				},"text"
				);
	}
};


function onCheck(e, treeId, treeNode) {

	var zTree = $.fn.zTree.getZTreeObj("tree");
	nodes = zTree.getCheckedNodes(true);
	ids = "";
	names="";
	for ( var i = 0, l = nodes.length; i < l; i++) {
		if (nodes[i].children == null) {
			ids += nodes[i].id + ",";
			names += nodes[i].name + ",";
		}
	}
	$("#tabDetail").css("display","none");
	$("#form-horizontal").css("display","block");
	var gh=$("#goh");
	gh.text(ids);
	//gh.attr("value",ids);
	var obj=$("#name");
	//obj.attr("value",names);
	obj.text(names);
}

$(document).ready(function() {
	$.fn.zTree.init($("#tree"), setting, zNodes);
});