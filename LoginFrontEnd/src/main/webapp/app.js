
var app = angular.module('myApp', ['ngRoute', 'ngCookies']);

app.config(function($routeProvider)
{
	$routeProvider	

	.when('/', {
		templateUrl: 'v_home/welcome.html',
		controller: 'HomeController'
	})
	.when('/userprofile', {
		templateUrl: 'v_user/frmUserProfile.html',
		controller: 'UserProfileController'
	})
	.when('/login', {
		templateUrl: 'v_user/frmLogin.html',
		controller: 'UserProfileController'
	})
	.when('/allusers', {
		templateUrl: 'v_user/AllUserAdmin.html',
		controller: 'UserProfileController'
	})
	.otherwise({redirectTo: '/'});
});

app.run( function($rootScope,$location,$cookieStore,$http){
	
	$rootScope.currentUser=$cookieStore.get('currentUser')||{};
    if($rootScope.currentUser){
        $http.defaults.headers.common['Authorization']= 'Basic' + $rootScope.currentUser;
    }
   
    });
    