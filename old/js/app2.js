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
            url:"/member?user_id&method",
            templateUrl : 'pages/member.html',
            controller  : 'memController'
        })
        .state('member.index',{
            url:"/index",
            templateUrl : 'pages/member/uIndex.html'
        })
        .state('member.illust',{
            url:"/illust",
            templateUrl : 'pages/member/uIllust.html'
        })
        .state('member.collect',{
            url:"/collect",
            templateUrl : 'pages/member/uCollect.html'
        })
        .state('member.follow',{
            url:"/follow",
            templateUrl : 'pages/member/uFollow.html'
        })
        .state('member.fans',{
            url:"/fans",
            templateUrl : 'pages/member/uFans.html'
        })


});
app.controller('mainController',function ($scope,$rootScope,$state,$http) {

    $scope.keyword="";

    $scope.testKey= function ($event) {
        if ($event.keyCode == 13){
            $state.go('search',{'keyword':$scope.keyword});
        }
    };
    $scope.Myitems=null;
    $scope.hots =null;
    $scope. HotUser=null;
    $http.get("http://127.0.0.1:8080/MyProject/api/afterlogin").success(function (data) {
        $scope.Myitems = data['illusts'];
        $scope.hots =data['hot'];
        $scope. HotUser=data['HotUser']
    });


});
app.controller('memController',function ($scope,$rootScope,$location,$state,$stateParams) {

    if ($stateParams.method == 'illust'){
        $state.go('member.illust');
    }else if($stateParams.method == 'follow'){
        $state.go('member.follow');
    }else if($stateParams.method == 'fans'){
        $state.go('member.fans');
    }




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
        $scope.nCursor['left'] = '129px';
    }else if (paths[paths.length-1] == 'collect'){
        $scope.nCursor['left'] = '242px';
    }
    
    $scope.tempCursor = $scope.nCursor;

    $scope.pagechange = function (mCursor) {
        $scope.tempCursor = mCursor;
        $scope.nCursor = $scope.tempCursor;
    };

});
app.controller('updateController',function ($scope) {

});
app.controller('hotController',function ($scope,$http) {

    $scope.items =null;
    $scope.flag=false;
    $scope.num =1;
    var pagenums;
    var total;
    $http.get("http://127.0.0.1:8080/MyProject/api/hot",{
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
        $scope.items.length
    });

    $scope.getpre = function () {
        $scope.num = $scope.num -1;
        if ($scope.num <1){
            alert("已经是第一页");
            $scope.num = $scope.num +1;
        }else {
            $http.get("http://127.0.0.1:8080/MyProject/api/hot",{
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
            $http.get("http://127.0.0.1:8080/MyProject/api/hot",{
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
app.controller('showController',function ($scope,$http,$stateParams) {
    
    $scope.hascomment = 0;
    $scope.commentList=null;
    $scope.CommContent="";
    $scope.illustid = $stateParams.illust_id;
    $scope.addComment = function () {
        if( !$scope.CommContent){
            return false;
        }
        var newComment ={
            content:$scope.CommContent,
            userid:1,
            illustid:$scope.illustid,
        };
        if ($scope.commentList == null){
            $jq.ajax({
               url: "http://127.0.0.1:8080/MyProject/api/comment",
                data:{
                    content:$scope.CommContent,
                    userid:'1',
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
                url: "http://127.0.0.1:8080/MyProject/api/comment",
                data:{
                    content:$scope.CommContent,
                    userid:'1',
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
        
        $scope.commentList.reverse();
    };
    $scope.jsondata =null;
    $http.get("http://127.0.0.1:8080/MyProject/api/show",
    {
        params:
        {
            Illust_id:$scope.illustid
        }
    }).success(function (data) {
        $scope.jsondata = data;
    });
    $http.get("http://127.0.0.1:8080/MyProject/api/comment",
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


});

app.controller('searchController',function ($scope,$stateParams) {

     $scope.b =$stateParams.keyword;
    
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