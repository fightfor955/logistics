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
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>权限列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 权限管理 <span class="c-gray en">&gt;</span> 权限列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
<span class="l" id="toolbar">
	<a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
	<a href="javascript:;" onclick="permission_add('添加权限','permission/edit.do','800','500')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加权限</a></div>
  	<table id="permissionTable" class="table table-border table-bordered table-bg"></table>
</div>
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
		$('#permissionTable').bootstrapTable({
			url:'permission/list.do',
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
		columns:[
			//表格显示数据对应的表头设置
			{
				checkbox:true//是否显示前台的复选框
			},
			//每列表头的设置
			{field:'id',title:'编号'}, 
			{field:'name',title:'权限名称'},
			{field:'url',title:'权限地址账号'},
			{field:'expression',title:'权限表达式'},
			{field:'type',title:'是否菜单权限',formatter:menuFormatter},
			{field:'parentName',title:'父权限'}, 
			//操作列的设置(删除修改)
			{
				field:'id',
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
		
		})
	});

	function operationFormatter(value,row,index){
		//console.log(value,row,index);
		
		var html = "<span onclick='permission_edit("+value+")'style='cursor: pointer;' class='glyphicon glyphicon-pencil'></span>&nbsp;&nbsp;&nbsp;";
		
		html += "<span onclick='permission_del(this,"+value+")' style='cursor: pointer;color:red' class='glyphicon glyphicon-trash'></span>"
		
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
/*权限-增加*/
function permission_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*权限-删除*/
function permission_del(obj,id){
	console.log(id);
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: 'permission/delete.do',
			data:{"permissionId":id},
			dataType: 'json',
			success: function(data){
				$(obj).parents("tr").remove();
				layer.msg('已删除!',{icon:1,time:2000});
				refreshTable();
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
}
	/* function permission_del(userId){
		//layer的弹框使用
		layer.confirm('确认要删除吗？',function(index){
			//如果是点击的确认就会执行函数ajax请求
			$.post("permission/delete.do",{"userId":userId},function(data){
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
	$("#permissionTable").bootstrapTable("refresh")
}
function menuFormatter(value,row,index){
	if("menu"==value){
		return "<span style='color:red'>菜单权限</span>";
	}
	return "普通权限";
}	
/*权限-编辑*/
function permission_edit(permissionId){
	layer.open({
		type:2,
		title:"编辑权限",
		shadeClose:true,
		shade:false,
		maxmin:true,
		area:['800px','500px'],
		content:"permission/edit.do?permissionId="+permissionId
	});
}
/*权限-停用*/
function permission_stop(obj,id){
	layer.confirm('确认要停用吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		
		$(obj).parents("tr").find(".td-manage").prepend('<a onClick="permission_start(this,id)" href="javascript:;" title="启用" style="text-decoration:none"><i class="Hui-iconfont">&#xe615;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">已禁用</span>');
		$(obj).remove();
		layer.msg('已停用!',{icon: 5,time:1000});
	});
}

/*权限-启用*/
function permission_start(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		
		
		$(obj).parents("tr").find(".td-manage").prepend('<a onClick="permission_stop(this,id)" href="javascript:;" title="停用" style="text-decoration:none"><i class="Hui-iconfont">&#xe631;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
		$(obj).remove();
		layer.msg('已启用!', {icon: 6,time:1000});
	});
}
</script>
</body>
</html>