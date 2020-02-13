<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>    
    
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + 	request.getServerPort() + request.getContextPath() + "/";
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
<style type="text/css">
	.order_detail{
		background-color: durkgray;
		border: 1px solid black;
		border-right: 0; 
		height: 40px;
		line-height: 40px;
	}
	.order_detail input{
		height: 30px;
		text-align: center;
	}
	 .order_add{
		border: 1px solid black;
	} 
	#order_detail{
		text-align: center;
	}

</style>
<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
<link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.min.css" />
</head>
<body>
<article class="page-container">
	<form class="form form-horizontal" method="post" action="${empty order ? 'order/insert.do' : 'order/update.do'}" id="orderForm">
	<!-- 隐藏域 ，订单状态-->
	<input type="hidden" name="orderStatus" value="1">
	<input type="hidden" name="orderId" value="${order.orderId}">
	<div class="row cl">
		<%-- <div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">业务员：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<select name="userId">
					<option value="0">=请选择=</option>
					<c:forEach items="${users}" var="obj">
						<option ${order.userId eq obj.userId ? 'selected':''} value="${obj.userId}">${obj.realname}</option>
					</c:forEach>
				</select>
			</div>
		</div> --%>
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">订单编号：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" value="${order.orderId}"   placeholder="" id="orderId" name="orderId">
			</div>
		</div>
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">业务员：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<c:forEach items="${users}" var="obj">
						<c:if test="${order.userId eq obj.userId}">
							<input type="text" class="input-text" value="${obj.realname}"   placeholder="" id="userId" name="userId">
							<input type="hidden" name="userId" value="${obj.userId}">
						</c:if>
				</c:forEach>
			</div>
		</div>
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">客户：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<c:forEach items="${customers}" var="obj">
						<c:if test="${order.customerId eq obj.customerId}">
							<input type="text" class="input-text" value="${obj.customerName}"   placeholder="" id="customerId" name="customerId">
							<input type="hidden" name="customerId" value="${obj.customerId}">
						</c:if>
				</c:forEach>
			</div>
		</div>
	</div>
	<div class="row cl">
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">到达区域：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<c:forEach items="${intervals}" var="obj">
						<c:if test="${order.intervalId eq obj.baseId}">
							<input type="text" class="input-text" value="${obj.baseName}"   placeholder="">
							<input type="hidden" id="intervalId" name="intervalId" value="${obj.baseId}">
						</c:if>
				</c:forEach>
			</div>
		</div>
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">取件地址：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" value="${order.takeAddress}"   placeholder="" id="takeAddress" name="takeAddress">
			</div>
		</div>
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">收货人：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" value="${order.takeName}"   placeholder="" id="takeName" name="takeName">
			</div>
		</div>
	</div>
	<div class="row cl">
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">联系电话：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" value="${order.takePhone}"   placeholder="" id="takePhone" name="takePhone">
			</div>
		</div>
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">付款方式：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<select name="paymentMethodId">
					<c:forEach items="${payments}" var="obj">
						<option ${order.paymentMethodId eq obj.baseId ? 'selected' :''} value="${obj.baseId}">${obj.baseName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">货运方式：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<c:forEach items="${freights}" var="obj">
						<c:if test="${order.freightMethodId eq obj.baseId}">
							<input type="text" class="input-text" value="${obj.baseName}"   placeholder="">
							<input type="hidden" id="freightMethodId" name="freightMethodId" value="${obj.baseId}">
						</c:if>
				</c:forEach>
			</div>
		</div>
	</div>
	<div class="row cl">
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">取件方式：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<select name="takeMethodId">
					<c:forEach items="${fetchTypes}" var="obj">
						<option ${order.takeMethodId eq obj.baseId ? 'selected' :''} value="${obj.baseId}" >${obj.baseName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">仓库选择：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<select name="takeMethodId">
					<c:forEach items="${warHouse}" var="obj">
						<option  value="${obj.baseId}" >${obj.baseName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<%-- <div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">取件费用：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" value="${orderDetailList[0].goodDeliveryPrice}"  disabled="disabled"  placeholder="" id="deliveryPrice2" name="orderDetails[][goodDeliveryPrice]">
			</div>
		</div> --%>
		<!-- <div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">物流单号：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" value="243242343" disabled="disabled"   placeholder="" id="customerName" name="customerName">
			</div>
		</div> -->
	</div>
	<div class="row cl">
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">体积收费：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" value=""   placeholder="" id="volumeCost">
			</div>
		</div>
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">总体积：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" value=""   placeholder="" id="volume">
			</div>
		</div>
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">体积率费：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" value="${volumePrice}"   placeholder="" id="volumePrice" name="volumePrice">
			</div>
		</div>
	</div>
	<div class="row cl">
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">重量收费：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" value=""   placeholder="" id="heavyCost" name="heavyCost">
			</div>
		</div>
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">总重量：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" value=""   placeholder="" id="heavy">
			</div>
		</div>
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">重量率费：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" value="${heavyPrice}"   placeholder="" id="heavyPrice" name="heavyPrice">
			</div>
		</div>
	</div>	
	
	<div class="row cl">
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">取件地址：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" value="${order.takeAddress}"   placeholder="" id="takeAddress" name="takeAddress">
			</div>
		</div>
	</div>
	<div class="row cl">
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">总费用(含运费)：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" value="${orderDetailList[0].goodDeliveryPrice}"   placeholder="" id="deliveryPrice" name="orderDetails[][goodDeliveryPrice]">
			</div>
		</div>
	</div>
	<div  id="order_detail" class="row cl">
		<div>
			<div  class="col-xs-2 col-sm-2 order_detail">货物名称</div>
			<div  class="col-xs-2 col-sm-1 order_detail">数量</div>
			<div  class="col-xs-2 col-sm-1 order_detail">单位</div>
			<div  class="col-xs-2 col-sm-1 order_detail">长</div>
			<div  class="col-xs-2 col-sm-1 order_detail">宽</div>
			<div  class="col-xs-2 col-sm-1 order_detail">高</div>
			<div  class="col-xs-2 col-sm-1 order_detail">核算体积(立方)</div>
			<div  class="col-xs-2 col-sm-2 order_detail">核算重量(千克)</div>
			<div  class="col-xs-2 col-sm-2 order_detail">总价值(元)</div>
		</div>
		<c:if test="${!empty order}">
		<c:forEach items="${orderDetailList}" var="orderDetail">
		<div id="goods_detail">
			<div  class="col-xs-2 col-sm-2 order_detail">
				<input type="text" name="orderDetails[][goodsName]" value="${orderDetail.goodsName}">
			</div>
			<div  class="col-xs-2 col-sm-1 order_detail">
				<input type="text" size="3" onkeyup="getTotal(this)" name="orderDetails[][goodsNumber]"value="${orderDetail.goodsNumber}">
			</div>
			<div  class="col-xs-2 col-sm-1 order_detail">
				<select name="orderDetails[][goodsUnit]">
					<c:forEach items="${units}" var="obj">
						<option ${orderDetail.goodsUnit eq obj.baseId ? 'selected':''} value="${obj.baseId}">${obj.baseName}</option>
					</c:forEach>
				</select>
			</div>
			<div  class="col-xs-2 col-sm-1 order_detail">
				<input type="text"  required="true"  size="3" onkeyup="getTotal(this)"name="orderDetails[][goodLength]" value="${orderDetail.goodLength}">
			</div>
			<div  class="col-xs-2 col-sm-1 order_detail">
				<input type="text" required="true" size="3" onkeyup="getTotal(this)"name="orderDetails[][goodsWidth]" value="${orderDetail.goodsWidth}">
			</div>
			<div  class="col-xs-2 col-sm-1 order_detail">
				<input type="text" required="true" size="3" onkeyup="getTotal(this)"name="orderDetails[][goodHeight]" value="${orderDetail.goodHeight}">
			</div>
			<div  class="col-xs-2 col-sm-1 order_detail">
				<input type="text" size="3"  required="true" name="orderDetails[][goodsVolume]" value="${orderDetail.goodsVolume}">
			</div>
			<div  class="col-xs-2 col-sm-2 order_detail">
				<input type="text" required="true" name="orderDetails[][goodsHeavy]" value="${orderDetail.goodsHeavy}">
			</div>
			<div  class="col-xs-2 col-sm-2 order_detail">
				<input type="text" required="true" name="orderDetails[][goodsTotal]" value="${orderDetail.goodsTotal}">
			</div>
			<input type="hidden" name="orderDetails[][orderDetailId]" value="${orderDetail.orderDetailId}">
			<input type="hidden" name="orderId" value="${order.orderId}">
			<input type="hidden" name="orderDetails[][goodDeliveryPrice]" value="${orderDetail.goodDeliveryPrice}"   id="deliveryPrice">
		</div>
		</c:forEach>
		</c:if>
		<%--  <c:if test="${!empty order}">
		<c:forEach items="${orderDetailList}" var="orderDetail">
		<div id="goods_detail">
			<div  class="col-xs-2 col-sm-2 order_detail">
				<input type="text" name="orderDetails[][goodsName]" value="${orderDetail.goodsName}">
			</div>
			<div  class="col-xs-2 col-sm-1 order_detail">
				<input type="text" size="3" onkeyup="getTotal(this)" name="orderDetails[][goodsNumber]"value="${orderDetail.goodsNumber}">
			</div>
			<div  class="col-xs-2 col-sm-1 order_detail">
				<select name="orderDetails[][goodsUnit]">
					<c:forEach items="${units}" var="obj">
						<option ${orderDetail.goodsUnit eq obj.baseId ? 'selected':''} value="${obj.baseId}">${obj.baseName}</option>
					</c:forEach>
				</select>
			</div>
			 <div  class="col-xs-2 col-sm-2 order_detail">
				<input type="text" onkeyup="getTotal(this)"name="orderDetails[][goodsUnitPrice]" value="${orderDetail.goodsUnitPrice}">
			</div> 
			<div  class="col-xs-2 col-sm-2 order_detail">
				<input type="text" onkeyup="getTotal(this)" name="orderDetails[][goodLength]" value="${orderDetail.goodLength}">
			</div>   
			<div  class="col-xs-2 col-sm-1 order_detail">
				<input type="text" name="orderDetails[][goodsTotal]" value="${orderDetail.goodsTotal}">
			</div>
			<div  class="col-xs-2 col-sm-2 order_detail">
				<input type="text" name="orderDetails[][goodsRemark]" value="${orderDetail.goodsRemark}">
			</div>
			<div  class="col-xs-2 col-sm-2 order_detail order_add">
				<span style="font: 30px;cursor: pointer;color: red" 
				class="glyphicon glyphicon-remove"
				onclick="remove_goods_detail(this);"
				></span>
			</div>
		</div>
		</c:forEach>
		</c:if>  --%>
	</div>
	<%-- <div id="order_detail" class="row cl">
		<div>
			<div  class="col-xs-2 col-sm-2 order_detail">货物名称</div>
			<div  class="col-xs-2 col-sm-1 order_detail">数量</div>
			<div  class="col-xs-2 col-sm-1 order_detail">单位</div>
			<div  class="col-xs-2 col-sm-2 order_detail">长</div>
			<div  class="col-xs-2 col-sm-2 order_detail">宽</div>
			<div  class="col-xs-2 col-sm-3 order_detail">高</div>
			<div  class="col-xs-2 col-sm-3 order_detail">核算体积(立方)</div>
			<div  class="col-xs-2 col-sm-3 order_detail">核算重量(千克)</div>
		</div>
		<c:if test="${!empty order}">
		<c:forEach items="${orderDetailList}" var="orderDetail">
		<div id="goods_detail">
			<div  class="col-xs-2 col-sm-2 order_detail">
				<input type="text" name="orderDetails[][goodsName]" value="${orderDetail.goodsName}">
			</div>
			<div  class="col-xs-2 col-sm-1 order_detail">
				<input type="text" size="3" onkeyup="getTotal(this)" name="orderDetails[][goodsNumber]"value="${orderDetail.goodsNumber}">
			</div>
			<div  class="col-xs-2 col-sm-1 order_detail">
				<select name="orderDetails[][goodsUnit]">
					<c:forEach items="${units}" var="obj">
						<option ${orderDetail.goodsUnit eq obj.baseId ? 'selected':''} value="${obj.baseId}">${obj.baseName}</option>
					</c:forEach>
				</select>
			</div>
			<div  class="col-xs-2 col-sm-1 order_detail">
				<input type="text" onkeyup="getTotal(this)"name="orderDetails[][goodsLength]" value="${orderDetail.goodsLength}">
			</div>
			<div  class="col-xs-2 col-sm-1 order_detail">
				<input type="text" onkeyup="getTotal(this)"name="orderDetails[][goodsWidth]" value="${orderDetail.goodsWidth}">
			</div>
			<div  class="col-xs-2 col-sm-1 order_detail">
				<input type="text" onkeyup="getTotal(this)"name="orderDetails[][goodsHeight]" value="${orderDetail.goodsHeight}">
			</div>
			<div  class="col-xs-2 col-sm-2 order_detail">
				<input type="text" name="orderDetails[][goodsVolume]" value="${orderDetail.goodsVolume}">
			</div>
			<div  class="col-xs-2 col-sm-2 order_detail">
				<input type="text" name="orderDetails[][goodsHeavy]" value="${orderDetail.goodsHeavy}">
			</div>
		</div>
		</c:forEach>
		</c:if>
	</div> --%>
	
	
	<div class="row cl">
		<div class="col-xs-12 col-sm-12 col-xs-offset-4 col-sm-offset-3">
			<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			<input class="btn btn-primary radius" type="button" value="&nbsp;&nbsp;返回&nbsp;&nbsp;">
		</div>
	</div>
	</form>
</article>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.11.3/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="lib/bootstrap-table/bootstrap-table.min.js"></script>
<script type="text/javascript" src="lib/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="lib/jquery.validation/1.14.0/messages_zh.js"></script> 
<script type="text/javascript" src="lib/jquery/jquery.serializejson.min.js"></script>
<script type="text/javascript">
//计算所有
function getTotal(obj) {
	var allDiv = $(obj).parent().parent();
	var inputs = allDiv.find("input");
	console.log(inputs);
	var goodNumber = inputs[1].value;
	var goodLength = inputs[2].value;
	var goodsWidth = inputs[3].value;
	var goodHeight = inputs[4].value;
	if(goodNumber!=null&&goodLength!=null&&goodsWidth!=null&&goodHeight!=null){
		inputs[5].value = goodNumber*goodLength*goodsWidth*goodHeight;
		var heavyPrice = $("#heavyPrice").val();
		var volumePrice = $("#volumePrice").val();
		var heavy = inputs[7].value;
		$("#deliveryPrice2").val(inputs[5].value*volumePrice/10000+(heavyPrice*heavy));
		$("#deliveryPrice").val(inputs[5].value*volumePrice/10000+(heavyPrice*heavy));
	}
}
function fuck(){
	var allVolumePrice = 0;
	var allHeavyPrice = 0;
	var allVolume = 0;
	var allHeavy = 0;
	var heavyPrice = $("#heavyPrice").val();
	var volumePrice = $("#volumePrice").val();
	for (var i = 0; i < ${arrNum}; i++) {
		var volumes = $("input[name='orderDetails[][goodsVolume]']")[i].value;
		var heavys = $("input[name='orderDetails[][goodsHeavy]']")[i].value;
		allVolumePrice += volumes*volumePrice;
		allHeavyPrice +=heavys*heavyPrice;
		allVolume += parseFloat(volumes);
		allHeavy +=parseFloat(heavys);
	}
	var AllMoney = allHeavyPrice+allVolumePrice
	$("#volumeCost").val(allVolumePrice);
	$("#volume").val(allVolume);
	$("#heavyCost").val(allHeavyPrice);
	$("#heavy").val(allHeavy);
	$("#deliveryPrice2").val(AllMoney);
	$("#deliveryPrice").val(AllMoney);
}
//添加商品详情
function add_goods_detail(){
	//克隆商品信息
	var goodsDetail = $("#goods_detail").clone();
	//清楚表单数据
	goodsDetail.find("input").val("");
	//找到order_detail框
	var order_detail = $("#order_detail");
	//添加
	order_detail.append(goodsDetail);
}
//删除商品详情
function remove_goods_detail(obj){
	
	$(obj).parent().parent().remove();
}
//客户改变的时候改变地区
function customerChangeUpdateInterval(){
	//获取当前客户下拉框的区间值
	var selectedOption = $("#customer option:selected");
	var intervalId = selectedOption.data("base_id");
	var intervalName = selectedOption.data("base_name");
	var intervalOptions = $("#interval option");
	console.log(intervalOptions);
	for(var i =0;i<intervalOptions.length;i++){
		//获取的是原生dom对象
		var intervalOption = intervalOptions[i];
		//获取区间ID
		var baseId = $(intervalOption).val();
		//先清除选中的效果
		$(intervalOption).attr("selected",false);
		//判断客户的区间id值和baseId是否相等
		if(intervalId == baseId){
			$(intervalOption).attr("selected",true);
		}
	}
}
//为客户绑定一个change函树

$(function(){
		   
	//页面加载触发 根据客户显示选择对应的区域
	customerChangeUpdateInterval();
	fuck()
	//为客户的下拉框绑定change函数
	
	$("#customer").change(function(){
		customerChangeUpdateInterval();
	});
	
	$("#orderForm").validate({
		rules:{
			shippingAddress:{
				required:true
			},shippingName:{
				required:true
			},shippingPhone:{
				required:true
			},/* orderDetails[][goodLength]:{
				required:true
			},orderDetails[][goodsWidth]:{
				required:true
			},orderDetails[][goodHeight]:{
				required:true
			},orderDetails[][goodsHeavy]:{
				required:true
			}, */
		},
		messages:{
			shippingAddress:{
				required:"地址不能为空",
			},shippingName:{
				required:"收货人不能为空"
			},shippingPhone:{
				required:"收货人电话不能为空"
			},/* orderDetails[][goodLength]:{
				required:"长度不能为空"
			},orderDetails[][goodsWidth]:{
				required:"宽度不能为空"
			},orderDetails[][goodHeight]:{
				required:"高度不能为空"
			},orderDetails[][goodsHeavy]:{
				required:"重量不能为空"
			}, */
		},
		 submitHandler:function(form){
			//将表单使用jquery序列化成json对象
			var serializeJSON = $(form).serializeJSON();
			//将json对象转化成json字符串
			var jsonData = JSON.stringify(serializeJSON);
			console.log(jsonData);
			<c:if test="${!empty order}">
			 $.ajax({
				type:"POST",
				url:"business/update.do",
				data:jsonData,
				contentType:"application/json;charset=utf-8",
				success:function(data){
					layer.msg(data.msg,{icon:data.code,time:2000},function (){
						var index = parent.layer.getFrameIndex(window.name) //获取窗口索引
						console.log("订单"+index)
						window.parent.refreshTable()
						parent.layer.close(index)
					});
				}
			})
			</c:if>
			 <c:if test="${empty order}">
			 $.ajax({
				type:"POST",
				url:"business/insert.do",
				data:jsonData,
				contentType:"application/json;charset=utf-8",
				success:function(data){
					layer.msg(data.msg,{icon:data.code,time:2000},function (){
						var index = parent.layer.getFrameIndex(window.name) //获取窗口索引
						console.log("订单"+index)
						window.parent.refreshTable()
						parent.layer.close(index)
					});
				}
			})
			</c:if>
			/* var jqForm = $(form);
			jqForm.ajaxSubmit(function(jsonData){
				layer.msg(data.msg,{icon:data.code,time:2000},function(){
					window.parent.refreshTable();
					parent.layer.closeAll();
				})
			}) */
		} 
		
	})
	
})





</script> 
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>