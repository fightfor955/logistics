<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath(); 
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	// http://localhost:8080/logistics/
%>
<!DOCTYPE HTML>
<html>
<head>
<!-- 设置页面的 基本路径，页面所有资源引入和页面的跳转全部基于 base路径 -->
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,role-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
<link rel="stylesheet" type="text/css" href="lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" />
</head>
<body>
<article class="page-container">
	<form  class="form form-horizontal" action="${empty role ?'role/insert.do' : 'role/update.do'}" id="roleForm">
	<!-- 修改用户隐藏域 id -->
	<input type="hidden" name="roleId" value="${role.roleId}">
	<!-- 隐藏域，权限的id数据  -->
	<input id="permissionIds" type="hidden" name="permissionIds">
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色名称：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text"  value="${role.rolename}" placeholder="请输入角色名称" id="rolename" name="rolename">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3">备注：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<textarea name="remark" cols="" rows="" class="textarea" placeholder="请输入角色描述">${role.remark}</textarea>
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3">系统权限：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<!-- zTree生成树的列表 -->
			<ul id="permissionTree" class="ztree"></ul>
		</div>
	</div>
	
	
	<div class="row cl">
		<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
			<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
		</div>
	</div>
	
	
	
	</form>
</article>
<!--_footer 作为公共模版分离出去--> 
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="lib/jquery.validation/1.14.0/messages_zh.js"></script> 
<script type="text/javascript" src="lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript">


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
	
	//当前角色的权限
	var permissionIds = "${role.permissionIds}";
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
	console.log(permissionIdsArr);
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



$(function(){
	
	/* 
		使用jquery.validate 进行表单校验
	*/
	$("#roleForm").validate({
		rules:{
			rolename:{
				required:true,
			}
		},
		messages:{
			rolename:{
				required:"角色名称不能为空",
			}
		},
		submitHandler:function(form){
			
			/* 
				提交表单需要将  zTree的数据获取出来一并提交
				思路
				
				1，先将zTree的选中的数据获取出来，并组装成一个字符串（1,2,4,8,9）
				
				2，在表中添加一个隐藏域
				
				3，将zTree获取的数据设置到隐藏域中
				
				所有操作封装到		getCheckedData()函数中	
				
			*/
			getCheckedData();
	
			
			var jqForm = $(form);
			
			//使用jquery表单异步提交
			jqForm.ajaxSubmit(function(data){
				
				layer.msg(data.msg,{icon:data.code,time:2000},function(){
					//让父层页面重新刷新一下（重新加载一下）
					window.parent.refreshTable();
					//关闭当前弹出层
					parent.layer.closeAll();
				});
			});
			
		}
		
	});

});

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

	//获取隐藏域，并设置 （权限id）
	$("#permissionIds").val(permissionIds);

}

</script> 
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>