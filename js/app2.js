/**
 * Created by user-xiaofan on 2016/4/21.
 */

var $jq = $.noConflict();

var app = angular.module('myapp',['ui.router']);

// app.config(function($routeProvider) {
//     $routeProvider
//
//     // route for the home page
//         .when('/', {
//             templateUrl : 'pages/afterLogin.html',
//             controller  : 'mainController'
//         })
//         // route for the about page
//         .when('/member/:page_name', {
//             templateUrl : 'pages/member.html',
//             controller  : 'memController'
//         })
//
//         // route for the contact page
//         .when('/show', {
//             templateUrl : 'pages/show.html',
//             controller  : 'showController'
//         });
// });

app.config(function ($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/main');

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
        .state('member',{
            url:"/member",
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


});
app.controller('mainController',function ($scope) {

});
app.controller('memController',function ($scope,$rootScope,$location) {
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
// app.controller('showController',function ($scope) {
//
// });

app.controller('updateController',function ($scope) {

});
app.controller('hotController',function ($scope) {

});
app.controller('showController',function ($scope) {

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