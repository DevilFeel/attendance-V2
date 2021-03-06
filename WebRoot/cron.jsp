<%@ page contentType="text/html; charset=utf-8" %>
<%@ page language="java" import="java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<!--
		Charisma v1.0.0

		Copyright 2012 Muhammad Usman
		Licensed under the Apache License v2.0
		http://www.apache.org/licenses/LICENSE-2.0

		http://usman.it
		http://twitter.com/halalit_usman
	-->
	<meta charset="utf-8">
	<title>欢迎使用</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
	<meta name="author" content="Muhammad Usman">
	<script src="js/jquery-1.7.2.min.js" type="text/javascript"></script>

	<!-- The styles -->
	<link id="bs-css" href="css/bootstrap-cerulean.css" rel="stylesheet">
	<style type="text/css">
	  body {
		padding-bottom: 40px;
	  }
	  .sidebar-nav {
		padding: 9px 0;
	  }
	</style>
	<link href="css/bootstrap-responsive.css" rel="stylesheet">
	<link href="css/charisma-app.css" rel="stylesheet">
	<link href="css/jquery-ui-1.8.21.custom.css" rel="stylesheet">
	<link href='css/fullcalendar.css' rel='stylesheet'>
	<link href='css/fullcalendar.print.css' rel='stylesheet'  media='print'>
	<link href='css/chosen.css' rel='stylesheet'>
	<link href='css/uniform.default.css' rel='stylesheet'>
	<link href='css/colorbox.css' rel='stylesheet'>
	<link href='css/jquery.cleditor.css' rel='stylesheet'>
	<link href='css/jquery.noty.css' rel='stylesheet'>
	<link href='css/noty_theme_default.css' rel='stylesheet'>
	<link href='css/elfinder.min.css' rel='stylesheet'>
	<link href='css/elfinder.theme.css' rel='stylesheet'>
	<link href='css/jquery.iphone.toggle.css' rel='stylesheet'>
	<link href='css/opa-icons.css' rel='stylesheet'>
	<link href='css/uploadify.css' rel='stylesheet'>

	<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
	  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->

	<!-- The fav icon -->
	<link rel="shortcut icon" href="img/favicon.ico">
		
</head>

<body>
		<!-- topbar starts -->
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</a>
				<a class="brand" href="index.jsp"> <img alt="Charisma Logo" src="img/logo20.png" /> <span>Manage</span></a>
				
				<!-- theme selector starts -->
				<div class="btn-group pull-right theme-container" >
					<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
						<i class="icon-tint"></i><span class="hidden-phone"> Change Theme / Skin</span>
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu" id="themes">
						<li><a data-value="classic" href="#"><i class="icon-blank"></i> Classic</a></li>
						<li><a data-value="cerulean" href="#"><i class="icon-blank"></i> Cerulean</a></li>
						<li><a data-value="cyborg" href="#"><i class="icon-blank"></i> Cyborg</a></li>
						<li><a data-value="redy" href="#"><i class="icon-blank"></i> Redy</a></li>
						<li><a data-value="journal" href="#"><i class="icon-blank"></i> Journal</a></li>
						<li><a data-value="simplex" href="#"><i class="icon-blank"></i> Simplex</a></li>
						<li><a data-value="slate" href="#"><i class="icon-blank"></i> Slate</a></li>
						<li><a data-value="spacelab" href="#"><i class="icon-blank"></i> Spacelab</a></li>
						<li><a data-value="united" href="#"><i class="icon-blank"></i> United</a></li>
					</ul>
				</div>
				<!-- theme selector ends -->
				
				<!-- user dropdown starts -->
				<div class="btn-group pull-right" >
					<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
						<i class="icon-user"></i><span class="hidden-phone"> 欢迎 <%=session.getAttribute("username") %></span>
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li><a href="#">Profile</a></li>
						<li class="divider"></li>
						<li><a href="login.html">登出</a></li>
					</ul>
				</div>
				<!-- user dropdown ends -->
				
				<div class="top-nav nav-collapse">
					<ul class="nav">
						<li><a href="#">Visit Site</a></li>
						<li>
							<form class="navbar-search pull-left">
								<input placeholder="Search" class="search-query span2" name="query" type="text">
							</form>
						</li>
					</ul>
				</div><!--/.nav-collapse -->
			</div>
		</div>
	</div>
	<!-- topbar ends -->
		<div class="container-fluid">
		<div class="row-fluid">
				
			<!-- left menu starts -->
			<div class="span2 main-menu-span">
				<div class="well nav-collapse sidebar-nav">
					<ul class="nav nav-tabs nav-stacked main-menu">
						<li class="nav-header hidden-tablet">主菜单</li>
						<li><a class="ajax-link" href="subject.jsp"><i class="icon-home"></i><span class="hidden-tablet"> 创建课程</span></a></li>
						<li><a class="ajax-link" href="class.jsp"><i class="icon-eye-open"></i><span class="hidden-tablet"> 创建班级</span></a></li>
						<li><a class="ajax-link" href="cron.jsp"><i class="icon-edit"></i><span class="hidden-tablet"> 创建任务</span></a></li>
						<li><a class="ajax-link" href="query.jsp"><i class="icon-list-alt"></i><span class="hidden-tablet"> 查看考勤</span></a></li>
						<li class="nav-header hidden-tablet">未开发</li>
						<li><a class="ajax-link" href="index.jsp"><i class="icon-font"></i><span class="hidden-tablet"> 功能待定</span></a></li>
						<li><a class="ajax-link" href="index.jsp"><i class="icon-picture"></i><span class="hidden-tablet"> 功能待定</span></a></li>
						
					</ul>
					<label id="for-is-ajax" class="hidden-tablet" for="is-ajax"><input id="is-ajax" type="checkbox"> Ajax on menu</label>
				</div><!--/.well -->
			</div><!--/span-->
			<!-- left menu ends -->
			
			<noscript>
				<div class="alert alert-blo ck span10">
					<h4 class="alert-heading">Warning!</h4>
					<p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a> enabled to use this site.</p>
				</div>
			</noscript>
			
			<div id="content" class="span10">
			<!-- content starts -->
       			<form action="createCron" method="post">
       				<label>选择学年：</label><select id="selectYear" name="year"></select>
       				<label>选择学期：</label>
       				<select id="selectSemester" name="semester" onchange="getSubject('selectYear', 'selectSemester', 'selectSubject')">
       					<option value="void">请选择</option>
       					<option value="1">1</option>
       					<option value="2">2</option>
       				</select>
       				<label>选择课程：</label>
       				<select id="selectSubject" name="subject" onchange="getClass('selectSubject','selectClass')"></select>
       				<label>选择班级：</label>
       				<select id="selectClass" name="class"></select>
       				<div>
	       				<div id="add">
	       					上课时间：
	       					<select name="week" style="WIDTH:90px">
	       						<option value="1">星期一</option>
	       						<option value="2">星期二</option>
	       						<option value="3">星期三</option>
	       						<option value="4">星期四</option>
	       						<option value="5">星期五</option>
	       						<option value="6">星期六</option>
	       						<option value="7">星期七</option>
	       					</select>
	       					单双周：
	       					<select name="evenAndOld" style="WIDTH:70px">
	       						<option value="0">全部</option>
	       						<option value="1">单周</option>
	       						<option value="2">双周</option>
	       					</select>
	       					上课地点：
	       					<input type="text" name="classroom" style="WIDTH:80px">
	       					第
	       					<select name="start" style="WIDTH:50px">
	       						<option value="1">1</option>
	       						<option value="2">2</option>
	       						<option value="3">3</option>
	       						<option value="4">4</option>
	       						<option value="5">5</option>
	       						<option value="6">6</option>
	       						<option value="7">7</option>
	       						<option value="8">8</option>
	       						<option value="9">9</option>
	       						<option value="10">10</option>
	       						<option value="11">11</option>
	       					</select>
	       					节~第
	       					<select name="end" style="WIDTH:50px">
	       						<option value="1">1</option>
	       						<option value="2">2</option>
	       						<option value="3">3</option>
	       						<option value="4">4</option>
	       						<option value="5">5</option>
	       						<option value="6">6</option>
	       						<option value="7">7</option>
	       						<option value="8">8</option>
	       						<option value="9">9</option>
	       						<option value="10">10</option>
	       						<option value="11">11</option>
	       					</select>
	       					节
	       					<input id="addButton" type="button" value="添加课时" onclick="addLesson(this)">
	       					<input type="button" value="删除课时" onclick="delLesson(this)">
	       				</div>
       				</div>
       				<br>
       				<input type="submit" value="提交">
       				<br>
       				<%
   						String msg = (String)request.getAttribute("msg");
   						if(msg!=null){
   							out.print(msg);
   						}
   					%>
       			</form>
       			<script type="text/javascript">
	       			//初始化学期信息
	   				$("selectYear").ready(function(){
	   					var select = document.getElementById("selectYear");
	   					$.post("queryYear",{},function(data, textStatus){
	   						var dataArray = JSON.parse(data);
	   						select.options.add(new Option("请选择", "void"));
	   						for(var i=0;i<dataArray.length;i++){
	   							select.options.add(new Option(dataArray[i], dataArray[i]));
	   						}
	   					});
	   				});
	   				//Ajax刷新课程信息
       				function getSubject(frist_type_id, second_type_id, next_sel_id) {
			            var sel = document.getElementById(frist_type_id);
			            var frist_type = sel.options[sel.selectedIndex].value;
			            var sel_second = document.getElementById(second_type_id);
			            var second_type = sel_second.options[sel_second.selectedIndex].value;
			            var next_sel = document.getElementById(next_sel_id);
			            next_sel.options.length = 0;
			            if (frist_type != "void") {
			                $.post('querySubject', { 'year':frist_type, 'semester':second_type }, function (data, textStatus) {
			                    var dataArray = JSON.parse(data);
			                    next_sel.options.length = 0;
			                    next_sel.options.add(new Option("请选择", "void"));
			                    for (var i = 0; i < dataArray.length; i++) {
			                        next_sel.options.add(new Option(dataArray[i].name, dataArray[i].id));
			                    }
			                });
			            }
			        };
			        //Ajax查询上课班级
			        function getClass(frist_type_id,next_sel_id){
			        	var sel = document.getElementById(frist_type_id);
			            var frist_type = sel.options[sel.selectedIndex].value;
			            var next_sel = document.getElementById(next_sel_id);
			            next_sel.options.length = 0;
			            if (frist_type != "void") {
			                $.post('queryClass2', { 'subject':frist_type }, function (data, textStatus) {
			                    var dataArray = JSON.parse(data);
			                    next_sel.options.length = 0;
			                    next_sel.options.add(new Option("请选择", "void"));
			                    for (var i = 0; i < dataArray.length; i++) {
			                        next_sel.options.add(new Option(dataArray[i].name, dataArray[i].id));
			                    }
			                });
			            }
			        }
			        //添加课时
			        function addLesson(object){
			        	$("#add").parent().append($(object).parent().clone());
			        	//$(object).remove();
			        }
			        //删除课时
			        function delLesson(object){
			        	$(object).parent().remove();
			        }
       			</script>
			<!-- content ends -->
			</div><!--/#content.span10-->
				</div><!--/fluid-row-->
				
		<hr>

		<div class="modal hide fade" id="myModal">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">Ã</button>
				<h3>Settings</h3>
			</div>
			<div class="modal-body">
				<p>Here settings can be configured...</p>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn" data-dismiss="modal">Close</a>
				<a href="#" class="btn btn-primary">Save changes</a>
			</div>
		</div>

		<footer>
			<p class="pull-left">&copy; <a href="http://usman.it" target="_blank">Muhammad Usman</a> 2012</p>
			<p class="pull-right">Powered by: <a href="http://usman.it/free-responsive-admin-template">Charisma</a></p>
		</footer>
		
	</div><!--/.fluid-container-->

	<!-- external javascript
	================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->

	<!-- jQuery -->
	<script src="js/jquery-1.7.2.min.js"></script>
	<!-- jQuery UI -->
	<script src="js/jquery-ui-1.8.21.custom.min.js"></script>
	<!-- transition / effect library -->
	<script src="js/bootstrap-transition.js"></script>
	<!-- alert enhancer library -->
	<script src="js/bootstrap-alert.js"></script>
	<!-- modal / dialog library -->
	<script src="js/bootstrap-modal.js"></script>
	<!-- custom dropdown library -->
	<script src="js/bootstrap-dropdown.js"></script>
	<!-- scrolspy library -->
	<script src="js/bootstrap-scrollspy.js"></script>
	<!-- library for creating tabs -->
	<script src="js/bootstrap-tab.js"></script>
	<!-- library for advanced tooltip -->
	<script src="js/bootstrap-tooltip.js"></script>
	<!-- popover effect library -->
	<script src="js/bootstrap-popover.js"></script>
	<!-- button enhancer library -->
	<script src="js/bootstrap-button.js"></script>
	<!-- accordion library (optional, not used in demo) -->
	<script src="js/bootstrap-collapse.js"></script>
	<!-- carousel slideshow library (optional, not used in demo) -->
	<script src="js/bootstrap-carousel.js"></script>
	<!-- autocomplete library -->
	<script src="js/bootstrap-typeahead.js"></script>
	<!-- tour library -->
	<script src="js/bootstrap-tour.js"></script>
	<!-- library for cookie management -->
	<script src="js/jquery.cookie.js"></script>
	<!-- calander plugin -->
	<script src='js/fullcalendar.min.js'></script>
	<!-- data table plugin -->
	<script src='js/jquery.dataTables.min.js'></script>

	<!-- chart libraries start -->
	<script src="js/excanvas.js"></script>
	<script src="js/jquery.flot.min.js"></script>
	<script src="js/jquery.flot.pie.min.js"></script>
	<script src="js/jquery.flot.stack.js"></script>
	<script src="js/jquery.flot.resize.min.js"></script>
	<!-- chart libraries end -->

	<!-- select or dropdown enhancer -->
	<script src="js/jquery.chosen.min.js"></script>
	<!-- checkbox, radio, and file input styler -->
	<script src="js/jquery.uniform.min.js"></script>
	<!-- plugin for gallery image view -->
	<script src="js/jquery.colorbox.min.js"></script>
	<!-- rich text editor library -->
	<script src="js/jquery.cleditor.min.js"></script>
	<!-- notification plugin -->
	<script src="js/jquery.noty.js"></script>
	<!-- file manager library -->
	<script src="js/jquery.elfinder.min.js"></script>
	<!-- star rating plugin -->
	<script src="js/jquery.raty.min.js"></script>
	<!-- for iOS style toggle switch -->
	<script src="js/jquery.iphone.toggle.js"></script>
	<!-- autogrowing textarea plugin -->
	<script src="js/jquery.autogrow-textarea.js"></script>
	<!-- multiple file upload plugin -->
	<script src="js/jquery.uploadify-3.1.min.js"></script>
	<!-- history.js for cross-browser state change on ajax -->
	<script src="js/jquery.history.js"></script>
	<!-- application script for Charisma demo -->
	<script src="js/charisma.js"></script>
	
		
</body>
</html>
