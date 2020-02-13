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
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
<link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="lib/bootstrap-table/bootstrap-table.min.css" />
<link rel="stylesheet" type="text/css" href="lib/font-awesome-4.7.0/css/font-awesome.min.css">
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>订单列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 订单管理 <span class="c-gray en">&gt;</span> 订单列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
<span class="l" id="toolbar">
	</span>
	</div>
  	<table id="orderTable" class="table table-border table-bordered table-bg"></table>
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
<script type="text/javascript" src="lib/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript">
	$(function(){
		$('#orderTable').bootstrapTable({
			url:'business/list.do',
			method:'post',
				/*
				ajax请求以后回调函数的处理
				后台使用返回的PageInfo对象中的 结果 级的key是list，总条数是total
				而前台bootstrapTable插件需要的数据的key叫做rows ，总条数也是叫做total
				那么出现一个问题 : 总条数的key能对上，结果集对不上，就需要在ajax请求完成回调
				responseHandler 这个函数方法处理一下
				并且在自定义一个 json,rows做为key，返回json的 list作为值
					total：还是total
				这样才能满足 bootstrapTable插件数据的需要
			*/
			responseHandler:function(res){
				var data = {rows:res.list,total:res.total};
				return data;
			},
		pagination:true,//是否分页
		toolbar:"#toolbar",//顶部显示能添加删除的工具条
		contentType:"application/x-www-form-urlencoded",//条件搜索的时候ajax请求给后台数据的数据类型
		search:true,//是否显示搜索框
		pageNumber:1,//默认第一页
		pageSize:7,//默认每页十条
		pageList:[10,25,50,100],
		sidePagination:"server",//是否服务器分页
		paginationHAlign:'right',//底部分页条
		showToggle:true,//是否显示详细试图和列表的切换按钮
		cardView:false,//是否显示详细视图
		showColumn:true,//是否显示所有的列
		showRefresh:true,//是否刷新按钮
		detailView:true,// 父子表
		columns:[
			//表格显示数据对应的表头设置
			{
				checkbox:true//是否显示前台的复选框
			},
			//每列表头的设置
			{field: 'orderId',title: '编号'}, 
			{field: 'shippingName',title: '发货人'}, 
			{field: 'shippingPhone',title: '发货电话'},
			{field: 'shippingAddress',title: '发货地址'},
			{field: 'takeName',title: '取件人'}, 
			{field: 'takePhone',title: '取件人电话'},
			{field: 'takeAddress',title: '取件地址'},
			{field: 'orderRemark',title: '订单描述'},
			{field: 'realname',title: '业务员'},
			{field: 'customerName',title: '所属客户'},
			
			//操作列的设置(删除修改)
			{
				field:'orderId',
				title:'操作',
				align:'center',
				formatter:operationFormatter
			}
		],
		/*
		发送请求的参数
		params：bootstraptable的插件内部参数对象包含如下参数
		limit,offset,search,sort
		limit:每页条数
		offset:每页的结束位置
		search;搜索框对应的值
		sort:排序
		*/
		queryParams:function(params){
			//此方法在用户分页或者搜索的时候会自动发送ajax
			return {
				pageNum:params.offset / params.limit+1,
				pageSize:params.limit,
				keyword:params.search
			};
		},
		/* 当点击父表的加号时会展开
			index行号
			row站开行对应的json数据
			$detail创建子表的一个对象
		*/
		onExpandRow: function(index,row,$detail){
			//获取当前展开行对应的订单id
			var orderId = row.orderId;
			//创建一个表格，用户点击+号时候马上创建一个表格
			var cur_table = $detail.html('<table></table>').find('table');
			//把子表编程bootstrap table
			$(cur_table).bootstrapTable({
				url:'business/detail.do',
				method:'get',
				contentType:'application/json;charset=UTF-8',
				dataType:'json',
				queryParams:{orderId:orderId},
				clickToSelect:true,
				columns:[{
					field:'orderDetailId',
					title:'订单明细编号'
				},{
					field:'goodsName',
					title:'货品名称'
				},{
					field:'goodsNumber',
					title:'货品数量'
				},{
					field:'goodsTotal',
					title:'总价'
				},{
					field:'goodsRemark',
					title:'货品描述'
				}]
			})
		}
		})
	});
	/* 
	*
	* value :当前行 field的值
	* index ：索引 从0开始
	* row ：每一行的对象
	*/
	function operationFormatter(value,row,index){
		//console.log(value,row,index);
		
		var html = "<span onclick='order_edit("+value+")'style='cursor: pointer;' class='glyphicon glyphicon-shopping-cart'></span>&nbsp;&nbsp;&nbsp;";
		html += "<span onclick='order_info("+value+")' style='cursor: pointer;' class='glyphicon glyphicon-usd'></span>";
		
		return html;
	}	
</script>

<script type="text/javascript">
/*
	参数解释：
	title	标题
	url		请求的url
	id		需要操作的数据id
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/
/*订单-增加*/
function order_add(){
	layer.open({
		type:2,
		title:"添加订单",
		shadeClose:true,
		shade:false,
		maxmin:true,
		area:['100%','100%'],
		content:"business/edit.do"
	});
}
	/* function order_del(userId){
		//layer的弹框使用
		layer.confirm('确认要删除吗？',function(index){
			//如果是点击的确认就会执行函数ajax请求
			$.post("order/delete.do",{"userId":userId},function(data){
				//弹出一个提示消息
				layer.msg(data.msg, {time: 1000, icon:6});
				
				//如果是1说明删除成功，刷新表格 ，调用 bootstrap插件的刷新一下表格
				if(data.code == 1){
					refreshTable();
				}
				
			});
		});
	} */
function refreshTable(){
	$("#orderTable").bootstrapTable("refresh")
}
function menuFormatter(value,row,index){
	if("menu"==value){
		return "<span style='color:red'>菜单订单</span>";
	}
	return "普通订单";
}	
/*订单-编辑*/
function order_edit(orderId){
	layer.open({
		type:2,
		title:"核对入库",
		shadeClose:true,
		shade:false,
		maxmin:true,
		area:['100%','100%'],
		content:"business/edit.do?orderId="+orderId
	});
}
function order_info(orderId){
	layer.open({
		type:2,
		title:"财务审核",
		shadeClose:true,
		shade:false,
		maxmin:true,
		area:['100%','100%'],
		content:"business/info.do?orderId="+orderId
	});
}

/*订单-停用*/
function order_stop(obj,id){
	layer.confirm('确认要停用吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		
		$(obj).parents("tr").find(".td-manage").prepend('<a onClick="order_start(this,id)" href="javascript:;" title="启用" style="text-decoration:none"><i class="Hui-iconfont">&#xe615;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">已禁用</span>');
		$(obj).remove();
		layer.msg('已停用!',{icon: 5,time:1000});
	});
}

/*订单-启用*/
function order_start(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		
		
		$(obj).parents("tr").find(".td-manage").prepend('<a onClick="order_stop(this,id)" href="javascript:;" title="停用" style="text-decoration:none"><i class="Hui-iconfont">&#xe631;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
		$(obj).remove();
		layer.msg('已启用!', {icon: 6,time:1000});
	});
}
</script>
</body>
</html>