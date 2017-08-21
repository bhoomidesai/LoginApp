'use strict';

app.controller('UserProfileController', ['$scope', '$http', '$cookieStore', 'UserProfileService', 
                         '$location', '$rootScope',
             function($scope, $http,  $cookieStore, UserProfileService, $location, $rootScope, $routeParams) 
             {
				console.log("user profile controller");

				var self = this;
				self.user=
				{
						currentrole:'',
						fstname:'',
						password:'',
						useremail:''
				};
			self.users=[];
 
    self.fetchAllUsers = function(){
        UserProfileService.fetchAllUsers().then
        (function(d)
        		{
        		self.users = d;
            	console.log("Fetch all users sucess")
        	},
        	function(errResponse)
        	{
        	console.error('Error while fetching Users'+ errResponse);
        	}
        );
    };
    
    self.createUser = function(user)
    {
        UserProfileService.createUser(user)
        	.then(
        			self.reset,
        			function(errResponse)
        			{
        				console.error('Error while creating User');
        			}
        		);
    };

    self.authenticate = function(user){
         UserProfileService.authenticate(user)
             .then(
            		 function(d) {
                         self.user = d;
                         if(self.user.useremail!=="undefined")
                         {
                        	 
                        	 $rootScope.currentUser = {
     	    							useremail: self.user.useremail,
     	    							username: self.user.fstname,
     	    							currentrole: self.user.currentrole
     	    					};
                        	 if($rootScope.currentUser.useremail!=null)
                        		 {
     	    					$http.defaults.headers.common['Authorization'] = 'Basic' + $rootScope.currentUser;
            					$cookieStore.put('currentUser',$rootScope.currentUser1);
            					console.log("Valid credentials, navigate to home page");
     	    				
     	    					alert('Welcome...'+$rootScope.currentUser.username);
     	    					$location.path('/');
                         		}else
                         			{
                         			 alert("Invalid user credentials, try again!!!");
                                	 $location.path('/login');
                                	 self.user.password = "";
                         			
                         			}
                         }
                         else
                         {
                        	 alert("Invalid user credentials, try again!!!");
                        	 $location.path('/login');
                        	 self.user.useremail = "";
                        	 self.user.password = "";
                         }
                     },
                     function(errResponse)
                     {
                    	 console.error('Error while authenticate User');
                     }
             );
    };
     
    self.fetchAllUsers();

    self.loginclick = function(){
   		self.authenticate(self.user);
    };
   
    self.logout=function()
    {
    	$rootScope.currentUser={};
    	$cookieStore.remove('currentUser');
    	UserProfileService.logout()
    	alert("Thanks for visiting website,\n Visit again....");
    };

    self.logoutuser=function()
    {
    	self.logout();
    };

    self.submit = function() 
    {
        console.log('Saving New User', self.user);
        self.createUser(self.user);
        self.reset();
    };

    self.reset = function()
    {
        self.user=
        {
        		currentrole:'',
            	fstname:'',
            	password:'',
            	useremail:''};
        $scope.myForm.$setPristine(); //reset Form
    }
}]);