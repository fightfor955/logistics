<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">

</head>
<body>
<script type="text/javascript" src="lib/jquery/1.11.3/jquery.min.js"></script> 
<script type="text/javascript" src="lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript">

//获取选中复选框的数据
function getCheckedData(){
	
	//获取树对象
	var treeObj = $.fn.zTree.getZTreeObj("permissionTree");
	//获取选中的节点
	var nodes = treeObj.getCheckedNodes(true);

	//声明一个数据，添加节点id（权限的id）
	var permissionIdsArr = [];
	//循环节点集合，获取每个节点的id，并添加到数组中
	for(var i = 0;i<nodes.length ; i++){
		//获取每个节点
		var node = nodes[i];
		
		//获取每个节点的id
		var permissionId = node.id;
		//console.log(permissionId);
		
		//将id添加到数组
		permissionIdsArr.push(permissionId);
	}
	console.log(permissionIdsArr);
	//将数组转换成字符串
	//var permissionIds = permissionIdsArr.toString();//默认使用逗号隔开 1,2,3,4
	//指定分隔符将数组转换成字符串
	var permissionIds = permissionIdsArr.join(",");
	console.log(permissionIds);
}




//标准json数据格式的zTree

/* 创建zTree的设置对象 （json） */
var setting = {
		check: {
			enable: true
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		async: {
			enable: true,
			url:"permission/getAllPermission.do",
		},
		/* 在zTree异步加载数据完毕以后回调函数 */
		callback: {
			onAsyncSuccess: zTreeOnAsyncSuccess
		}
};

//zTree异步加载数据完毕以后回调函数
function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
	
	//当前角色用于的权限
	//var permissionIds = "${role.permissionIds}";
	
	var permissionIds = "10,36,37,38,39,40,46,47,50,51,52,54,55";
  
	//获取树对象
	var treeObj = $.fn.zTree.getZTreeObj("permissionTree");
	
	
	/*
		回显复选框选中的思路
		
		1，将字符串转换成数组，得到每个对应权限的id
		
		2，zTree可以通过制定的属性的值获取对应的节点
		  上面一步获取的值，就是 zTree节点的 id属性的值
		  
		3，让其对应的节点选中
	
	*/
	var permissionIdsArr = permissionIds.split(",");
	
	for(var i = 0 ;i<permissionIdsArr.length;i++){
		//获取每个id
		var permissionId = permissionIdsArr[i];
		
		console.log(permissionId);
		
		//获取对应节点
		var node = treeObj.getNodeByParam("id", permissionId, null);
		console.log(node.children)
		
		//让其节点选中，（如果有子节点不能选中，否则会所有子节点全部选中）
		if(node.children == undefined){
			treeObj.checkNode(node, true, true);
		}
	}
};

$(function(){
	/* 初始化zTree */
	$.fn.zTree.init($("#permissionTree"), setting);
})
</script>
</head>
<body>
Ztree的demo<br>
<!-- ztree是基于列表的，所以必须有一个基本的ul，并且有id和class -->
<ul id="permissionTree" class="ztree"></ul>
<button type="button" onclick="getCheckedData()">获取数据</button>
</body>
</html>