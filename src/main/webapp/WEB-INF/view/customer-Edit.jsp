<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>添加管理员 - 管理员管理 - H-ui.admin v3.1</title>
<meta name="keywords" content="H-ui.admin v3.1,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description" content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
<article class="page-container">
	<form class="form form-horizontal" id="customerForm" action="${empty customer ? 'customer/insert.do' : 'customer/update.do'}">
	<input type="hidden" name="customerId" value="${customer.customerId}">
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>客户名称：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" ${customer == null ? '' :'disabled'} value="${customer.customerName}"  placeholder="请输入客户名称账号" id="customerName" name="customerName">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>客户电话：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" value="${customer.phone}" placeholder="" id="phone" name="phone">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3">客户邮箱：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" value="${customer.email}" placeholder="" id="email" name="email">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3">客户地址：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" value="${customer.address}" placeholder="" id="address" name="address">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>客户性别：</label>
		<div class="formControls col-xs-8 col-sm-9 skin-minimal">
			<div class="radio-box">
				<input type="radio" ${customer.gender eq 1 ? 'checked':''} value="1" name="gender">
				<label for="sex-1">男</label>
			</div>
			<div class="radio-box">
				<input type="radio" ${customer.gender eq 2 ? 'checked':''} value="2" name="gender">
				<label for="sex-2">女</label>
			</div>
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>备注：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<textarea name="remark" cols="" rows="" class="textarea" placeholder="请输入备注">${customer.remark}</textarea>
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3">身份证：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" value="${customer.idCard}" placeholder="" id="idCard" name="idCard">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3">业务员</label>
		<div class="formControls col-xs-8 col-sm-9"> <span class="select-box" style="width:150px;">
			<select class="select" name="userId" size="1">
				<option value="">请选择</option>
				<c:forEach items="${users}" var="u">
					<option  ${u.userId eq customer.userId ? 'selected':''} value="${u.userId}">${u.realname}</option>
				</c:forEach>
			</select>
			</span> </div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3">客户区间</label>
		<div class="formControls col-xs-8 col-sm-9"> <span class="select-box" style="width:150px;">
			<select class="select" name="baseId" size="1">
				<option value="">请选择</option>
				<c:forEach items="${baseDatas}" var="b">
					<option  ${b.baseId eq customer.baseId ? 'selected':''} value="${b.baseId}">${b.baseName}</option>
				</c:forEach>
			</select>
			</span> </div>
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
<script type="text/javascript">
$(function(){
	$("#customerForm").validate({
		rules:{
			name:{
				required:true,
			},
			expression:{
				required:true,
			},
			type:{
				required:true,
			},
		},
		messages:{
			name:{
				required:"客户名称不能为空",
				
			},
			expression:{
				requied:"客户表达式不能为空",
				
			},
			type:{
				requied:"客户类型不能为空",
			},
		},
		submitHandler:function(form){
			//form是原生js dom对象
			//原生js转jquery $(obj)
			//jq对象转js  obj[0]
			var jqForm = $(form);
			jqForm.ajaxSubmit(function(data){
				layer.msg(data.msg,{icon:data.code,time:2000},function(){
					//让父层页面刷新
					window.parent.refreshTable();
					//关闭当前页面
					parent.layer.closeAll();
				})
			})
		}
	});
});

</script> 
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>