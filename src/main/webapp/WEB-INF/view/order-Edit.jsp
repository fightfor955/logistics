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
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">业务员：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<select name="userId">
					<option value="0">=请选择=</option>
					<c:forEach items="${users}" var="obj">
						<option ${order.userId eq obj.userId ? 'selected':''} value="${obj.userId}">${obj.realname}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">客户：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<select name="customerId" id="customer">
					<c:forEach items="${customers}" var="obj">
						<!--data-xxx
							html5自定义属性名
						  -->
						<option ${order.customerId eq obj.customerId ? 'selected' : ''} data-base_name="${obj.baseName}" data-base_id="${obj.baseId}" value="${obj.customerId}">${obj.customerName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">到达区域：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<select id="interval" name="intervalId">
					<c:forEach items="${intervals}" var="obj">
						<option value="${obj.baseId}">${obj.baseName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</div>
	<div class="row cl">
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">发货地址：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" value="${order.shippingAddress}"   placeholder="" id="shippingAddress" name="shippingAddress">
			</div>
		</div>
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">发货人：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" value="${order.shippingName}"    placeholder="" id="shippingName" name="shippingName">
			</div>
		</div>
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">联系电话：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" value="${order.shippingPhone}" placeholder="" id="shippingPhone" name="shippingPhone">
			</div>
		</div>
	</div>
	<div class="row cl">
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
				<select name="freightMethodId">
					<c:forEach items="${freights}" var="obj">
						<option ${order.freightMethodId eq obj.baseId ? 'selected' :''} value="${obj.baseId}">${obj.baseName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</div>
	<div class="row cl">
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">取件方式：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<select name="takeMethodId">
					<c:forEach items="${fetchTypes}" var="obj">
						<option ${order.takeMethodId eq obj.baseId ? 'selected' :''} value="${obj.baseId}">${obj.baseName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">物流公司：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" value="顺丰物流公司"  disabled="disabled"  placeholder="" id="customerName" name="customerName">
			</div>
		</div>
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">物流单号：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" value="243242343" disabled="disabled"   placeholder="" id="customerName" name="customerName">
			</div>
		</div>
	</div>
	<div class="row cl">
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">取件地址：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" value="${order.takeAddress}"   placeholder="" id="customerName" name="takeAddress">
			</div>
		</div>
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">联系电话：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" value="${order.takePhone}"   placeholder="" id="takePhone" name="takePhone">
			</div>
		</div>
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">取件联系人：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" value="${order.takeName}"   placeholder="" id="takeName" name="takeName">
			</div>
		</div>
	</div>
	<div class="row cl">
		<div class="col-xs-4 col-sm-16">
			<label class="form-label col-xs-4 col-sm-4">订单备注：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<textarea id="orderRemark"  name="orderRemark" class="textarea"  placeholder="" >${order.orderRemark}</textarea>
			</div>
		</div>
	</div>
	
	
	
	<div  id="order_detail" class="row cl">
		<div>
			<div  class="col-xs-2 col-sm-2 order_detail">货物名称</div>
			<div  class="col-xs-2 col-sm-1 order_detail">数量</div>
			<div  class="col-xs-2 col-sm-1 order_detail">单位</div>
			<div  class="col-xs-2 col-sm-2 order_detail">单价</div>
			<div  class="col-xs-2 col-sm-2 order_detail">总价值</div>
			<div  class="col-xs-2 col-sm-3 order_detail">备注</div>
			<div  class="col-xs-2 col-sm-1 order_detail order_add">
				<span style="font: 30px;cursor: pointer;color: green" 
					class="glyphicon glyphicon-plus"
					onclick="add_goods_detail();"
					></span>
				</div>
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
			<div  class="col-xs-2 col-sm-2 order_detail">
				<input type="text" onkeyup="getTotal(this)"name="orderDetails[][goodsUnitPrice]" value="${orderDetail.goodsUnitPrice}">
			</div>
			<div  class="col-xs-2 col-sm-2 order_detail">
				<input type="text" name="orderDetails[][goodsTotal]" value="${orderDetail.goodsTotal}">
			</div>
			<div  class="col-xs-2 col-sm-3 order_detail">
				<input type="text" name="orderDetails[][goodsRemark]" value="${orderDetail.goodsRemark}">
			</div>
			<div  class="col-xs-2 col-sm-1 order_detail order_add">
				<span style="font: 30px;cursor: pointer;color: red" 
				class="glyphicon glyphicon-remove"
				onclick="remove_goods_detail(this);"
				></span>
			</div>
		</div>
		</c:forEach>
		</c:if>
		<c:if test="${empty order}">
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
				<input type="text" name="orderDetails[][goodsTotal]" value="${orderDetail.goodsTotal}">
			</div>
			<div  class="col-xs-2 col-sm-3 order_detail">
				<input type="text" name="orderDetails[][goodsRemark]" value="${orderDetail.goodsRemark}">
			</div>
			<div  class="col-xs-2 col-sm-1 order_detail order_add">
				<span style="font: 30px;cursor: pointer;color: red" 
				class="glyphicon glyphicon-remove"
				onclick="remove_goods_detail(this);"
				></span>
			</div>
		</div>
		</c:if>
	</div>
	
	
	<div class="row cl">
		<div class="col-xs-12 col-sm-12 col-xs-offset-4 col-sm-offset-3">
			<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
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
//计算所有的商品价格
function getTotal(obj) {
	var allDiv = $(obj).parent().parent();
	var inputs = allDiv.find("input");
	console.log(inputs);
	var goodNumber = inputs[1].value;
	var goodPrice = inputs[2].value;
	console.log(goodNumber);
	console.log(goodPrice);
	if(goodNumber!=null&&goodPrice!=null){
		inputs[3].value = goodNumber*goodPrice;
	}
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
			},userId:{
				min:1
			},
		},
		messages:{
			shippingAddress:{
				required:"地址不能为空",
			},shippingName:{
				required:"收货人不能为空"
			},shippingPhone:{
				required:"收货人电话不能为空"
			},userId:{
				min:"请选择你的业务员"
			},
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
				url:"order/update.do",
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
				url:"order/insert.do",
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