var setting = {
  check: {
    enable: true,
  },
  view: {
    dblClickExpand: false
  },
  data: {
    simpleData: {
      enable: true
    }
  },
  callback: {
    beforeClick: beforeClick,
    onCheck: onCheck
  }
};

function beforeClick(treeId, treeNode) {
  var zTree = $.fn.zTree.getZTreeObj("treeDemo");
  zTree.checkNode(treeNode, !treeNode.checked, null, true);
  return false;
}

function onCheck(e, treeId, treeNode) {
  var zTree = $.fn.zTree.getZTreeObj("treeDemo");
  nodes = zTree.getCheckedNodes(true);
  v = "";
  for (var i = 0, l = nodes.length; i < l; i++) {
    v += nodes[i].name + "、";
  }
  if (v.length > 0) v = v.substring(0, v.length - 1);
  var cityObj = $("#citySel");
  cityObj.attr("value", v);
}

// function showMenu() {
//   var cityObj = $("#citySel");
//   var cityOffset = $("#citySel").offset();
//   $("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
//   $("#menuContent").slideDown("fast");
//   $("body").bind("mousedown", onBodyDown);
// }

// function hideMenu() {
//   $("#menuContent").fadeOut("fast");
//   $("body").unbind("mousedown", onBodyDown);
// }

// function onBodyDown(event) {
//   if (!(event.target.id == "menuBtn" || event.target.id == "citySel" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length > 0)) {
//     hideMenu();
//   }
// }

$(document).ready(function() {
  $.fn.zTree.init($("#treeDemo"), setting, zNodes);
});