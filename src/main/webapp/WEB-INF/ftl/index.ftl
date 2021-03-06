<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>生成db文档</title>
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

<!--自动换行-->
<style>
td {
	word-wrap:break-word;
	word-break:break-all;
}
</style>

<script src="${rc.contextPath}/js/base.js"></script>

<script type="text/javascript">

	var isRequesting = false;//是否正在与服务器端交互
	var nickName = EMPTY;//昵称
	
	/**
	 * 设置昵称
	 */
	function setNickName() {
		if(EMPTY != $("#nickName").val()) {
			nickName = $("#nickName").val();
			gendoc();
		}
	}
	
	/**
	 * 生成db文档
	 */
	function gendoc() {
		if(EMPTY == nickName) {
			$("#nickNameModalBtn").click();
			return;
		}
		if(isRequesting){
			showAttention("正在与服务器端交互，请稍等！");
			return;
		}
		if('' == $("#sys").val()) {
			showAttention("请先选择系统！");
			return;
		}
		isRequesting = true;
		closeMessage("message_id");
		$.ajax({
			type: 'POST',
			url: '${rc.contextPath}/gendoc/gendoc.htm',
			data: 'tableSchema=' + $("#sys").val() + "&nickName=" + nickName,
			success: function(data, textStatus) {
				isRequesting = false;
				//判修改是否成功
                if (true == data["isSuccess"]) {
                	showSuccess(data["message"]);
                } else {
                	showError(data["message"]);
                }
			},
	        error:function (data, textStatus) {
	            showAttention("服务器连接异常，请稍后再试！");
	        }
		});
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
					<li class="active">
						<a href="${rc.contextPath}/tools/index.htm">
							<i class="glyphicon glyphicon-pencil"></i>
							<span>生成db文档</span>
						</a>
					</li>
					<li>
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
						<h3 class="box-title">生成db文档</h3>
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
									<option value="">选择系统</option>
									<#if Session.sys_list?exists>
										<#list Session.sys_list as node>
											<option value="${node}">${node}</option> 
										</#list>
									</#if>
								</select>
							</div>
							<div class="col-xs-12 col-sm-6 col-md-4">
								<button type="button" class="btn btn-block btn-primary"
									onclick="gendoc();">生成db文档</button>
							</div>
						</div>
					</div>
				</div>

				<div id="config_table" class="box box-primary"
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
									<th>环境</th>
									<th>项目</th>
									<th>配置名</th>
									<th>配置值</th>
									<th>描述</th>
									<th style="width: 60px">操作</th>
								</tr>
								<tr>
									<td>1.</td>
									<td>Update software</td>
									<td>Update software</td>
									<td>Update software</td>
									<td>Update software</td>
									<td>Update software</td>
									<td>ware</td>
								</tr>
								<tr>
									<td>2.</td>
									<td>Update software</td>
									<td>Update software</td>
									<td>Update software</td>
									<td>Update software</td>
									<td>Update software</td>
									<td>ware</td>
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
				
				<!-- 模态框（Modal）对应按钮 -->
				<button id="nickNameModalBtn" type='button' class='btn btn-block btn-primary' data-toggle='modal' 
					data-target='#nickNameModal' style="display:none;"></button>
				
				<!-- 模态框（Modal） -->
				<div class="modal fade" id="nickNameModal" tabindex="-1" role="dialog" 
				   aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog">
				      <div class="modal-content">
				         <div class="modal-header">
				            <button type="button" class="close" 
				               data-dismiss="modal" aria-hidden="true">
				                  &times;
				            </button>
				            <h4 class="modal-title" id="myModalLabel">
				               请输入昵称
				            </h4>
				         </div>
				         <div class="modal-body">
				            比如：[关向辉]则输入[Gxx]
				         	<input type="text" class="form-control" placeholder="昵称..." id="nickName">
				         </div>
				         <div class="modal-footer">
				            <button type="button" class="btn btn-default" 
				               data-dismiss="modal">取消
				            </button>
				            <button type="button" class="btn btn-primary" onclick="setNickName()" data-dismiss="modal">
				               确定
				            </button>
				         </div>
				      </div><!-- /.modal-content -->
					</div><!-- /.modal -->
				</div>

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
	<script src="${rc.contextPath}/plugins/datepicker/bootstrap-datepicker.js"></script>

	<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. Slimscroll is required when using the
     fixed layout. -->
</body>
</html>
