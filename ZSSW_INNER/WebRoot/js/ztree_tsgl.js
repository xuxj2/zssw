/**
 * 通知管理js函数
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
		onCheck : onCheck,
		beforeCheck: zTreeBeforeCheck

	}
};

function beforeClick(treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("tree");
	zTree.checkNode(treeNode, !treeNode.checked, null, true);
	return false;
}
function zTreeBeforeCheck(treeId, treeNode) {
    return false;
};

function onCheck(e, treeId, treeNode) {

	var zTree = $.fn.zTree.getZTreeObj("tree");
	nodes = zTree.getCheckedNodes(true);
	ids = "";
	for (var i = 0, l = nodes.length; i < l; i++) {
		if (nodes[i].children == null)
			ids+= nodes[i].id+",";
	}	
	if (ids.length > 0)
		v = ids.substring(0, ids.length - 1);
	var cityObj = $("#ids");
	cityObj.attr("value", ids);
}


	