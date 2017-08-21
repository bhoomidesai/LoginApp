<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Sergey Pozhilov (GetTemplate.com)">

<title>REgistration App</title>

<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/font-awesome.min.css">


<!-- Custom styles for our template -->
<link rel="stylesheet" href="resources/assets/css/main.css">
   <script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.js"></script>
<script src="resources/js/jquery.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<!--  <script src="resources/js/angular.js"></script>
 --><script src="resources/js/angular-route.min.js"></script>
<script src="resources/js/angular-cookies.min.js"></script>



</head>

</head>

<body ng-app="myApp" 
	ng-controller="UserProfileController as ctrl">
	<div ng-show="currentUser.useremail==undefined">
		<div ng-include="'v_common/common.html'"></div>
	</div>
	<div ng-show="currentUser.useremail!==undefined">
			<div ng-include="'v_common/userview.html'"></div>
	</div>
	<div class="row">
		<div class="col-md-2 col-sm-5 highlight">
			<div class="panel panel-primary">
				
			</div>
		</div>

<div class="col-md-7 col-sm-7 highlight">
	<div id="myContainer" style=" width: 100%;">

 		
		
			<div ng-view></div>
	
	</div>	
	<br/>	
		

		<!-- Left and right controls -->
		
	</div>

</div>
				
		
		
		
			<h3 class="widget-title">About NIIT</h3>
			<div class="widget-body">
				<p style="text-align: justify;">NIIT is a leading Skills and
					Talent Development Corporation that is building a manpower pool for
					global industry requirements. The company, which was set up in 1981
					to help the nascent IT industry overcome its human resource
					challenges, today ranks among the world’s leading training
					companies owing to its vast, yet comprehensive array of talent
					development programs. With a footprint across 40 nations, NIIT
					offers training and development solutions to Individuals,
					Enterprises and Institutions.</p>
			</div>

</div>


	<footer id="footer" class="top-space">

		<div class="footer2">

			<div class="row">
				<div class="col-md-6 widget">
					<div class="widget-body">
						<p class="simplenav">NIIT, Vadodara</p>
					</div>
				</div>

				<div class="col-md-6 widget">
					<div class="widget-body">
						<p class="text-right">
							Copyright &copy; 2017, by Bhoomi Desai <a href="#/home"
								rel="designer">(NIIT)</a>
						</p>
					</div>
				</div>
			</div>
		</div>

	</footer>
	<script src="resources/assets/js/headroom.min.js"></script>
	<script src="resources/assets/js/jQuery.headroom.min.js"></script>
	<script src="resources/assets/js/template.js"></script>

	<script src="app.js"></script>
	<script src="v_home/HomeController.js"></script>
	<script src="v_user/UserProfileController.js"></script>
	<script src="v_user/UserProfileService.js"></script>
</body>
</html>