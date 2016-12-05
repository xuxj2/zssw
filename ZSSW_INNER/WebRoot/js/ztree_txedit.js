/**
 * 条线编辑js函数库
 */

var ids;
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
		beforeClick : beforeClick,
		onCheck : onCheck
	}
};

function beforeClick(treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("txtree");
	zTree.checkNode(treeNode, !treeNode.checked, null, true);
	return false;
}

function onCheck(e, treeId, treeNode) {

	var zTree = $.fn.zTree.getZTreeObj("txtree");
	nodes = zTree.getCheckedNodes(true);
	ids = "";
	for (var i = 0, l = nodes.length; i < l; i++) {
		if (nodes[i].children == null)
			ids+= nodes[i].id+",";
	}
	var cityObj = $("#ids");
	cityObj.attr("value", ids);
}

$(document).ready(function() {
	$.fn.zTree.init($("#txtree"), setting, zNodes);
	var zTree = $.fn.zTree.getZTreeObj("txtree");
	nodes = zTree.getCheckedNodes(true);
	ids = "";
	for (var i = 0, l = nodes.length; i < l; i++) {
		if (nodes[i].children == null)
			ids+= nodes[i].id+",";
	}
	var cityObj = $("#ids");
	cityObj.attr("value", ids);
});