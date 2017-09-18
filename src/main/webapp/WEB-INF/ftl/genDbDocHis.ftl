<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>生成历史</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.6 -->
<link rel="stylesheet" href="${rc.contextPath}/bootstrap/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
<!-- Ionicons -->
<!-- <link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css"> -->
<!-- Theme style -->
<link rel="stylesheet" href="${rc.contextPath}/dist/css/AdminLTE.min.css">
<!-- AdminLTE Skins. We have chosen the skin-blue for this starter
        page. However, you can choose any other skin. Make sure you
        apply the skin class to the body tag so the changes take effect.
  -->
<link rel="stylesheet" href="${rc.contextPath}/dist/css/skins/skin-blue.min.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
<!-- daterange picker -->
<link rel="stylesheet"
	href="${rc.contextPath}/plugins/daterangepicker/daterangepicker.css">
<!-- bootstrap datepicker -->
<link rel="stylesheet" href="${rc.contextPath}/plugins/datepicker/datepicker3.css">
<!-- bootstrap datetimepicker -->
<link rel="stylesheet" href="${rc.contextPath}/plugins/datetimepicker/bootstrap-datetimepicker.css">

<!--自动换行-->
<style>
td {
	word-wrap:break-word;
	word-break:break-all;
}
</style>

<script src="${rc.contextPath}/js/base.js"></script>

<script type="text/javascript">
	var contextPath = "${rc.contextPath}/";
	var monitorArray = new Array();//监控信息集合
	var pageSize = 10;//每页大小
	var activePage = 1;//当前页数
	var totalPage = 0;//总页数
	var totalCount = 0;//总数
	var isRequesting = false;//是否正在与服务器端交互
	
	//生成db文档历史查询
	function queryHis(){
		if(isRequesting){
			showAttention("正在与服务器端交互，请稍等！");
			return;
		}
		isRequesting = true;
		$("#his_table").css("display", "none");
		$.ajax({
			type: 'POST',
			url: '${rc.contextPath}/gendoc/query.htm',
			data: 'tableSchema=' + $("#sys").val() + '&pageSize=' + pageSize + '&activePage=' + activePage,
			success: function(data) {
				isRequesting = false;
				var obj = data;
				monitorArray = obj.list;
				totalPage = obj.totalPage;
				totalCount = obj.totalCount;
				if(totalPage == 0){
					totalPage = 1;
					activePage = 1;
				}
				showHis();
				showSuccess("查询历史成功！");
			}
		});
	}
	//生成db文档历史信息
	function showHis(){
		var content = "<div class='box-header with-border'> <h3 class='box-title'>查询结果</h3>"
						+"<div class='box-tools pull-right'>"
			                +"<button type='button' class='btn btn-box-tool' data-widget='collapse'>"
			                	+"<i class='fa fa-minus'></i>"
			                +"</button>"
			                +"<button type='button' class='btn btn-box-tool' data-widget='remove'><i class='fa fa-times'></i></button>"
		              	+"</div></div>"
		+"<div class='box-body table-responsive no-padding'>"
			+"<table class='table table-bordered table-hover' style='width: 1000px;'>"
				+"<tbody>"
					+"<tr>"
						+"<th class='text-center'>主键</th>"
						+"<th class='text-center'>系统日期</th>"
						+"<th class='text-center'>系统时间</th>"
						+"<th class='text-center'>操作用户</th>"
						+"<th class='text-center'>ip</th>"
						+"<th class='text-center'>数据库</th>"
						+"<th class='text-center'>db文档</th>"
					+"</tr>";
		if(monitorArray.length == 0){
			content += "<tr><td class='text-center' colspan='7'>无相关数据！</td></tr>";
		} else {
			for(var i=0;i<monitorArray.length;i++){
				var obj = monitorArray[i];
				content += "<tr id='tr_" + obj["id"] + "'>"
						+"<td style='width: 50px; overflow:auto; text-align:center;'>" + obj["id"] + "</td>"
						+"<td style='width: 80px; overflow:auto; text-align:center;'>" + obj["sysDate"] + "</td>"
						+"<td style='width: 80px; overflow:auto; text-align:center;'>" + obj["sysTime"] + "</td>"
						+"<td style='width: 80px; overflow:auto; text-align:center;'>" + obj["nickName"] + "</td>"
						+"<td style='width: 150px; overflow:auto; text-align:center;'>" + obj["ip"] + "</td>"
						+"<td style='width: 150px; overflow:auto; text-align:center;'>" + obj["databaseName"] + "</td>"
						+"<td style='width: 80px; overflow:auto; text-align:center;'>"
							+"<a href='" + obj["fileUrl"] + "' target='_blank'>下载</a>"
						+"</td>"
					+"</tr>";
			}
		}
		
		content += "</tbody>"
			+"</table>"
		+"</div>";

		content += getTfoot();

		$("#his_table").html(content);
		$("#his_table").css("display", "block");
	}
	function getTfoot(){
		var content = "<div class='box-footer clearfix'>" + 
		"<ul class='pagination pagination-sm no-margin pull-right'>" + 
		"<li>" + "<a>总数:[" + totalCount + "] 总页数:[" + totalPage + "]</a>" + "</li>" +
		"<li><a href=\"javascript: jump2page(1)\" title=\"首页\">&laquo; 首页</a></li>";   
	    if(activePage > 1){
			content += "<li><a href=\"javascript: jump2page(" + (activePage-1) + ")\" title=\"上一页\">&laquo; 上一页</a></li>";
	    }
		//显示前2页，本页，后2页
		for(var i=activePage-2;i<activePage+3;i++){
			if(i >= 1 && i <= totalPage){
				content += "<li" + (i==activePage?" class='active'":"") + "><a href=\"javascript: jump2page(" + i + ")\" class=\"number" + ((i==activePage)?" current":"") + "\" title=\"" + i + "\">" + i + "</a></li>";
			}
		}
	    if(activePage < totalPage){
			content += "<li><a href=\"javascript: jump2page(" + (activePage+1) + ")\" title=\"下一页\">下一页 &raquo;</a></li>";
		}
		content += "<li><a href=\"javascript: jump2page(" + totalPage + ")\" title=\"尾页\">尾页 &raquo;</a></li>" + 
	                "</ul>" + 
	                "</div>";
	    return content;
	}
	function jump2page(index){
		activePage = index;
		queryMonitor();
	}
</script>

<style type="text/css">
div.alert {
	padding-top: 5px;
	padding-bottom: 5px
}
</style>

</head>
<!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-blue                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<!-- Main Header -->
		<header class="main-header">

			<!-- Logo -->
			<a href="index.htm" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
				<span class="logo-mini"><b>GT</b></span> <!-- logo for regular state and mobile devices -->
				<span class="logo-lg"><b>Gxx Tools</b></span>
			</a>

			<!-- Header Navbar -->
			<nav class="navbar navbar-static-top" role="navigation">
				<!-- Sidebar toggle button-->
				<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
					role="button"> <span class="sr-only">Toggle navigation</span>
				</a>
				<!-- Navbar Right Menu -->
				<div class="navbar-custom-menu">
					<ul class="nav navbar-nav">
						<!-- User Account Menu -->
						<li class="dropdown user user-menu">
							<!-- Menu Toggle Button --> <a href="#" class="dropdown-toggle"
							data-toggle="dropdown"> <!-- The user image in the navbar-->
								<img src="${rc.contextPath}/dist/img/user2-160x160.jpg" class="user-image"
								alt="User Image"> <!-- hidden-xs hides the username on small devices so only the image appears. -->
								<span class="hidden-xs">guest</span>
						</a>
							<ul class="dropdown-menu">
								<!-- The user image in the menu -->
								<li class="user-header"><img
									src="${rc.contextPath}/dist/img/user2-160x160.jpg" class="img-circle"
									alt="User Image">

									<p>
										guest <small>游荡中...</small>
									</p></li>
								<!-- Menu Footer -->
								<!-- <li class="user-footer">
									<div class="pull-right">
										<a href="${rc.contextPath}/logout.htm" class="btn btn-default btn-flat">退出</a>
									</div>
								</li> -->
							</ul>
						</li>
					</ul>
				</div>
			</nav>
		</header>
		<!-- Left side column. contains the logo and sidebar -->
		<aside class="main-sidebar">

			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">

				<!-- Sidebar user panel (optional) -->
				<div class="user-panel">
					<div class="pull-left image">
						<img src="${rc.contextPath}/dist/img/user2-160x160.jpg" class="img-circle"
							alt="User Image">
					</div>
					<div class="pull-left info">
						<p>guest</p>
						<!-- Status -->
						<a href="#"><i class="glyphicon glyphicon-record text-success"></i> 游荡中</a>
					</div>
				</div>

				<!-- Sidebar Menu -->
				<ul class="sidebar-menu">
					<li class="header">db文档生成器</li>
					<!-- Optionally, you can add icons to the links -->
					<li>
						<a href="${rc.contextPath}/tools/index.htm">
							<i class="glyphicon glyphicon-pencil"></i>
							<span>生成db文档</span>
						</a>
					</li>
					<li class="active">
						<a href="${rc.contextPath}/tools/genDbDocHis.htm">
							<i class="glyphicon glyphicon-search"></i>
							<span>生成历史</span>
						</a>
					</li>
					<!-- <li class="header">问题跟踪模块</li> -->
					<!-- Optionally, you can add icons to the links -->
					<!-- <li>
						<a href="${rc.contextPath}/monitor/query.htm">
							<i class="glyphicon glyphicon-search"></i>
							<span>查看问题</span>
						</a>
					</li>
					<li>
						<a href="${rc.contextPath}/monitor/realtime.htm">
							<i class="glyphicon glyphicon-search"></i>
							<span>新增问题</span>
						</a>
					</li> -->
				</ul>
				<!-- /.sidebar-menu -->
			</section>
			<!-- /.sidebar -->
		</aside>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Main content -->
			<section class="content">

			<div id="message_id" style="display: none;"></div>

				<div class="box box-primary">
					<div class="box-header with-border">
						<h3 class="box-title">生成历史</h3>
						<div class="box-tools pull-right">
			                <button type="button" class="btn btn-box-tool" data-widget="collapse">
			                	<i class="fa fa-minus"></i>
			                </button>
			                <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
		              	</div>
					</div>
					<!-- /.box-header -->
					<div class="box-body">

						<div class="row">
							<div class="col-xs-12 col-sm-6 col-md-4">
								<select class="form-control" id="sys">
									<option value="">全部系统</option>
									<#if Session.sys_list?exists>
										<#list Session.sys_list as node>
											<option value="${node}">${node}</option> 
										</#list>
									</#if>
								</select>
							</div>
							<div class="col-xs-12 col-sm-6 col-md-4">
								<button type="button" class="btn btn-block btn-primary"
									onclick="activePage=1;queryHis();">查询</button>
							</div>
						</div>
					</div>
				</div>

				<div id="his_table" class="box box-primary"
					style="display: none;">
					<div class="box-header with-border">
						<h3 class="box-title">查询结果</h3>
					</div>
					<!-- /.box-header -->
					<div class="box-body table-responsive no-padding">
						<table class="table table-hover">
							<tbody>
								<tr>
									<th style="width: 10px">ID</th>
									<th>系统日期</th>
									<th>系统时间</th>
									<th>昵称</th>
									<th>ip</th>
									<th>数据库</th>
									<th>文件</th>
								</tr>
							</tbody>
						</table>
					</div>
					<!-- /.box-body -->
					<div class="box-footer clearfix">
						<ul class="pagination pagination-sm no-margin pull-right">
							<li><a href="#">«</a></li>
							<li><a href="#">1</a></li>
							<li><a href="#">2</a></li>
							<li><a href="#">3</a></li>
							<li><a href="#">»</a></li>
						</ul>
					</div>
				</div>
				<!-- /.box -->
				
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<!-- Main Footer -->
		<footer class="main-footer">
			<!-- To the right -->
			<div class="pull-right hidden-xs">Come on baby~</div>
			<!-- Default to the left -->
			<strong>Copyright &copy; 2017 by <a href="http://www.recorddrip.com/" target="_blank">Gxx</a>.
			</strong> All rights reserved.
		</footer>

		<!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
		<div class="control-sidebar-bg"></div>
	</div>
	<!-- ./wrapper -->

	<!-- REQUIRED JS SCRIPTS -->

	<!-- jQuery 2.2.3 -->
	<script src="${rc.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
	<!-- Bootstrap 3.3.6 -->
	<script src="${rc.contextPath}/bootstrap/js/bootstrap.min.js"></script>
	<!-- AdminLTE App -->
	<script src="${rc.contextPath}/dist/js/app.min.js"></script>
	<!-- bootstrap datepicker -->
	<script src="${rc.contextPath}/plugins/datetimepicker/bootstrap-datetimepicker.js"></script>

	<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. Slimscroll is required when using the
     fixed layout. -->
</body>
</html>
