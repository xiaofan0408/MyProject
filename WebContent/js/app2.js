/**
 * Created by user-xiaofan on 2016/4/21.
 */

var $jq = $.noConflict();



var app = angular.module('myapp',['ui.router']);

app.config(function ($stateProvider, $urlRouterProvider) {
    

    $urlRouterProvider.otherwise('/main');
    $urlRouterProvider.when('/search','search');

    $stateProvider
        .state('main',{
            url:"/main",
            templateUrl:'pages/afterLogin.html',
            controller  : 'mainController'
        })
        .state('hot',{
            url:"/hot",
            templateUrl:'pages/hot.html',
            controller  : 'hotController'
        })
        .state('show',{
            url:"/show/:illust_id",
            templateUrl : 'pages/show.html',
            controller  : 'showController'
        })
        .state('update',{
            url:"/update",
            templateUrl : 'pages/update.html',
            controller  : 'updateController'
        })
        .state('search',{
            url:"/search/:keyword",
            templateUrl : 'pages/search.html',
            controller  : 'searchController'
        })
        .state('member',{
            url:"/member/:user_id",
            templateUrl : 'pages/member.html',
            controller  : 'memController'
        })
        .state('member.index',{
            url:"/index",
            templateUrl : 'pages/member/uIndex.html',
            controller  : 'indexController'	
        })
        .state('member.illust',{
            url:"/illust",
            templateUrl : 'pages/member/uIllust.html',
            controller  : 'illustController'
        })
        .state('member.collect',{
            url:"/collect",
            templateUrl : 'pages/member/uCollect.html',
            controller  : 'collectController'
        })
        .state('member.follow',{
            url:"/follow",
            templateUrl : 'pages/member/uFollow.html',
            controller  : 'followController'
        })
        .state('member.fans',{
            url:"/fans",
            templateUrl : 'pages/member/uFans.html',
            controller  : 'fansController'
        })


});
app.controller('mainController',function ($scope,$rootScope,$state,$http) {

    $scope.keyword="";
    var empty=null;
    $scope.testKey= function ($event) {
        if ($event.keyCode == 13){
            $state.go('search',{'keyword':$scope.keyword});
        }
    };
    $scope.Myitems=null;
    $scope.hots =null;
    $scope. HotUser=null;
    $rootScope.mainuserid=null;
    $http.get("api/afterlogin").success(function (data) {
        $scope.Myitems = data['illusts'];
        $scope.hots =data['hot'];
        $scope. HotUser=data['HotUser']
        $rootScope.mainuserid=data['mainuserid'];
        empty = data['empty'];
    });
    if(empty == 'true'){
     	$scope.isEmpty = true;
     }
     else{
     	$scope.isEmpty = false;
     }
    

});
app.controller('memController',function ($scope,$rootScope,$location,$state,$stateParams,$http) {

    $scope.nCursor = {
        'left': '16px',
        'width': '84px',
        'height': '3px'
    };
    var path = $location.absUrl();
    var paths = path.split('/');
    if (paths[paths.length-1] == 'index'){
        $scope.nCursor['left'] = '16px';
    }else if(paths[paths.length-1] == 'illust'){
        $scope.nCursor['left'] = '150px';
    }else if (paths[paths.length-1] == 'collect'){
        $scope.nCursor['left'] = '290px';
    }
    
    $scope.tempCursor = $scope.nCursor;

    $scope.pagechange = function (mCursor) {
        $scope.tempCursor = mCursor;
        $scope.nCursor = $scope.tempCursor;
    };
    
    $scope.users=null;
    $scope.illusts=null;
    $scope.collects =null;
    $http.get('api/member',{
    	params:{
    		userid:$stateParams.user_id
    	}
    }).success(function(data){
    	$scope.users= data['user'];
        $scope.illusts=data['illust'];
        $scope.collects =data['collect'];
    });
    $scope.follow = function () {
		$jq.ajax({
			url:'api/follow',
			data:{
                userid:$rootScope.mainuserid,
                followid:$stateParams.user_id
            },
              type:'post',
              cache:false,
              success:function(){
            	  
            	  $state.reload();
              }
		});
	}
    $scope.unfollow = function () {
		$jq.ajax({
			url:'api/follow',
			data:{
                userid:$rootScope.mainuserid,
                followid:$stateParams.user_id,
                method:'delete'
            },
              type:'post',
              cache:false,
              success:function(){
            	  $state.reload();
              }
		});
	}
    $scope.ismy = false;
    $scope.isfollow = false;
    $scope.a = null;
    if($rootScope.mainuserid == $stateParams.user_id){
    	$scope.ismy = true;
    	$scope.isfollow = false;
    }else{
    	$scope.ismy = false;
    	$jq.ajax({
			url:'api/fans',
			data:{
                userid:$rootScope.mainuserid,
                followid:$stateParams.user_id
            },
            async:false,
              type:'post',
              cache:false,
            success:function(data){
            	 $scope.a = data;
            }  
		});
    	if($scope.a.isfollow == 'true'){
    		$scope.isfollow = true;
    	}
    	else{
    		$scope.isfollow = false;
    	}
    }

});
app.controller('updateController',function ($scope) {

});
app.controller('hotController',function ($scope,$http) {

    $scope.items =null;
    $scope.flag=false;
    $scope.isEmpty = false;
    $scope.num =1;
    var pagenums;
    var total;
    $http.get("api/hot",{
        params:{
            num:$scope.num
        }
    }).success(function (data) {
        $scope.items = data['current'];
        total = data['total'];
        if (total >16){
            flag = true;
        }
        var temp = total%15;

        if (temp>0){
            pagenums = total/15+1;
        }else {
            pagenums = total/15;
        }
        if($scope.items.length==0){
        	$scope.isEmpty = true;
        }
        else{
        	$scope.isEmpty = false;
        }
    });

    $scope.getpre = function () {
        $scope.num = $scope.num -1;
        if ($scope.num <1){
            alert("已经是第一页");
            $scope.num = $scope.num +1;
        }else {
            $http.get("api/hot",{
                params:{
                    num:$scope.num
                }
            }).success(function (data) {
                $scope.items = data['current'];
                total = data['total'];
                if (total >16){
                    flag = true;
                }
                var temp = total%15;

                if (temp>0){
                    pagenums = total/15+1;
                }else {
                    pagenums = total/15;
                }
            });
        }

    };
    $scope.getnext = function () {
        $scope.num = $scope.num +1;
        if ($scope.num >pagenums){
            alert("已经是最后一页");
            $scope.num = $scope.num -1;
        }
        else {
            $http.get("api/hot",{
                params:{
                    num:$scope.num
                }
            }).success(function (data) {
                $scope.items = data['current'];
                total = data['total'];
                if (total >16){
                    flag = true;
                }
                var temp = total%15;

                if (temp>0){
                    pagenums = total/15+1;
                }else {
                    pagenums = total/15;
                }
            });
        }
    }

});
app.controller('showController',function ($scope,$http,$stateParams,$rootScope,$timeout,$state) {
    
    $scope.hascomment = 0;
    $scope.commentList=null;
    $scope.CommContent="";
    $scope.illustid = $stateParams.illust_id;
    $scope.isfollow = false;
    $scope.a = null;
    $scope.addComment = function () {
        if( !$scope.CommContent){
            return false;
        }
        var newComment ={
            content:$scope.CommContent,
            userid:$rootScope.mainuserid,
            illustid:$scope.illustid,
        };
        if ($scope.commentList == null){
            $jq.ajax({
               url: "api/comment",
                data:{
                    content:$scope.CommContent,
                    userid:$rootScope.mainuserid,
                    illustid:$scope.illustid
                },
                  type:'post',
                  cache:false,
                  success:function (data) {
                      $scope.commentList = data['comments'];
                      $scope.hascomment = data['hascomment'];
                      $scope.commentList.reverse();
                  }
            });
        }else {
            $jq.ajax({
                url: "api/comment",
                data:{
                    content:$scope.CommContent,
                    userid:$rootScope.mainuserid,
                    illustid:$scope.illustid
                },
                type:'post',
                cache:false,
                success:function (data) {
                    $scope.commentList = data['comments'];
                    $scope.hascomment = data['hascomment'];
                    $scope.commentList.reverse();
                }
            });
        }
        
    };
    $scope.jsondata =null;
    $http.get("api/show",
    {
        params:
        {
            Illust_id:$scope.illustid
        }
    }).success(function (data) {
        $scope.jsondata = data;
    })

    $http.get("api/comment",
        {
            params: {
                illustid:$scope.illustid
            }
        }).success(function (data) {
        $scope.commentList = data['comments'];
        $scope.hascomment = data['hascomment'];
        $scope.commentList.reverse();
    });
    $scope.tags=[];
    $scope.tag_input="";
     $scope.flag= false;
    $scope.addtag =function () {

        $scope.flag = !$scope.flag;
        if( $scope.tag_input!=""){
            var a = $scope.tags.indexOf($scope.tag_input);
            if(a != -1){
                alert("tag已经存在");
            }else{
                $scope.tags.push( $scope.tag_input);
            }
            $scope.tag_input="";
        }

    };
    $scope.follow = function () {
		$jq.ajax({
			url:'api/follow',
			data:{
                userid:$rootScope.mainuserid,
                followid:$scope.jsondata[1].authorid
            },
              type:'post',
              cache:false,
              success:function(){
            	  
            	  $state.reload();
              }
		});
	}
    $scope.unfollow = function () {
		$jq.ajax({
			url:'api/follow',
			data:{
                userid:$rootScope.mainuserid,
                followid:$scope.jsondata[1].authorid,
                method:'delete'
            },
              type:'post',
              cache:false,
              success:function(){
            	  $state.reload();
              }
		});
	}
    $scope.Collect = function () {
		$jq.ajax({
			url:'api/collect',
			data:{
                userid:$rootScope.mainuserid,
                illustid:$stateParams.illust_id
            },
              type:'post',
              cache:false,
              success:function(){
            	  
            	  $state.reload();
              }
		});
	}
    $scope.unCollect = function () {
		$jq.ajax({
			url:'api/collect',
			data:{
                userid:$rootScope.mainuserid,
                illustid:$stateParams.illust_id,
                method:'delete'
            },
            async:false,
              type:'post',
              cache:false,
              success:function(){
            	  $state.reload();
              }
		});
	}
    $scope.iscollect = false;
    $jq.ajax({
		url:'api/collect',
		data:{
            userid:$rootScope.mainuserid,
            illustid:$stateParams.illust_id,
            method:'find'
        },
        async:false,
          type:'post',
          cache:false,
          success:function(data){
        	  $scope.b = data;
          }
	});
    if($scope.b.iscollect == 'false'){
    	$scope.iscollect = false;
    }else{
    	$scope.iscollect = true;
    }

});

app.controller('searchController',function ($scope,$stateParams,$http) {

     $scope.b =$stateParams.keyword;
     
     $scope.items =null;
     $scope.flag=false;
     $scope.isEmpty = false;
     $scope.num =1;
     var pagenums;
     var total;
     $http.get("api/search",{
         params:{
             keyword:$scope.b
         }
     }).success(function (data) {
         $scope.items = data['illust'];
         total = data['total'];
         if (total >16){
             flag = true;
         }
         var temp = total%15;

         if (temp>0){
             pagenums = total/15+1;
         }else {
             pagenums = total/15;
         }
         if($scope.items.length==0){
         	$scope.isEmpty = true;
         }
         else{
         	$scope.isEmpty = false;
         }
     });
    
});

$jq(document).ready(function () {
    var btnBack=$jq('.back-to-top');

    btnBack.on('click',function () {
        $jq('html,body').animate({
            scrollTop:0
        },800);
    });
    $jq(window).on('scroll',function () {
        if($jq(window).scrollTop()>$jq(window).height()){
            btnBack.fadeIn();
        }else {
            btnBack.fadeOut();
        }
    })
});

app.controller('indexController',function ($scope) {
	
	

});
app.controller('illustController',function ($scope,$http,$stateParams,$timeout) {
	
	$scope.illusts=null;
	$scope.isEmpty=true;
	var empty=null;
	$http.get('api/illust',{
    	params:{
    		userid:$stateParams.user_id
    	}
    }).success(function(data){
    	$scope.illusts = data['illust'];
    	empty = data['empty'];
    }).then(function(){
    	$timeout(function(){
    		if(empty == 'true'){
    			$scope.isEmpty=true;
    		}else{
    			$scope.isEmpty=false;
    		}
    	},500);
    });
	
	
  
});
app.controller('collectController',function ($scope,$http,$stateParams,$timeout) {
	
	$scope.collects=null;
	$scope.isEmpty=true;
	var empty=null;
	$http.get('api/collect',{
    	params:{
    		userid:$stateParams.user_id
    	}
    }).success(function(data){
    	$scope.collects = data['collects'];
    }).then(function(){
    	$timeout(function(){
    		if(empty == 'true'){
    			$scope.isEmpty=true;
    		}else{
    			$scope.isEmpty=false;
    		}
    	},500);
    });

});
app.controller('followController',function ($scope,$http,$stateParams,$timeout) {
	
	$scope.follows=null;
	$scope.isEmpty=true;
	var empty=null;
	$http.get('api/follow',{
    	params:{
    		userid:$stateParams.user_id
    	}
    }).success(function(data){
    	$scope.follows = data['follow'];
    	empty = data['empty'];
    }).then(function(){
    	$timeout(function(){
    		if(empty == 'true'){
    			$scope.isEmpty=true;
    		}else{
    			$scope.isEmpty=false;
    		}
    	},500);
    });
	


});
app.controller('fansController',function ($scope,$http,$stateParams,$timeout) {
	
	$scope.fans=null;
	$scope.isEmpty=true;
	var empty=null;
	$http.get('api/fans',{
    	params:{
    		userid:$stateParams.user_id
    	}
    }).success(function(data){
    	$scope.fans = data['fans'];
    	empty = data['empty'];
    }).then(function(){
    	$timeout(function(){
    		if(empty == 'true'){
    			$scope.isEmpty=true;
    		}else{
    			$scope.isEmpty=false;
    		}
    	},500);
    });
	

});