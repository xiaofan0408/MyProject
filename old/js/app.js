/**
 * Created by user-xiaofan on 2016/4/15.
 */
'use strict';
var $jq = $.noConflict();
var app = angular.module('myapp',['ui.router']);


app.config(function ($stateProvider, $urlRouterProvider) {



    $urlRouterProvider.otherwise('/index');

    $stateProvider
        .state('index',{
            url:"/index",
            templateUrl:'pages/prelogin/prelogin.html',
            controller  : 'imgController'
        })
        .state('reg', {
            url: "/register",
            templateUrl: 'pages/prelogin/register.html',
            controller: 'regController'
        });


});

app.controller('imgController',function ($scope,$http,$timeout,$state) {
    $scope.url = 'http://127.0.0.1:8080/MyProject/';
    $scope.imgs=[];
    // $jq.ajax({
    //     type:"GET",
    //     url: "http://127.0.0.1:8080/MyProject/JsonTest",async:false,
    //     dataType:"json",
    //     success:function(data){
    //         $scope.imgs = data;
    //     }
    // });
    $scope.password="";
    $scope.txt_password="";
    $scope.cypto = function () {
        var value = hex_md5($scope.password);
        $scope.txt_password = value;
    };
    $http.get( "http://127.0.0.1:8080/MyProject/api/prelogin").success(function (data) {
        $scope.imgs = data;
    }).then(function () {
        $timeout(function () {
                $jq(function() {
                    var $container = $jq('.masonry');
                    $container.imagesLoaded(function() {
                        $container.masonry({
                            columnWidth :'.grid-sizer',
                            gutter: '.gutter-sizer',
                            itemSelector: '.box',
                            isAnimated: true
                        });
                    });
                });
            },300);
        });
});

$jq(document).ready(function () {
    $jq(function() {
        var $container = $jq('.masonry');
        $container.imagesLoaded(function() {
            $container.masonry({
                columnWidth :'.grid-sizer',
                gutter: '.gutter-sizer',
                itemSelector: '.box',
                isAnimated: true
            });
        });
    });
});


app.controller('regController',function ($scope) {
    
    $scope.password="";
    $scope.txt_password="";
    $scope.username="";
    $scope.Email="";
    $scope.cypto = function () {
        var value = hex_md5($scope.password);
        $scope.txt_password = value;
    };
    $scope.register = function () {
        $jq.ajax({
            url:"http://127.0.0.1:8080/MyProject/api/register",
            data:{
                username:$scope.username,
                txt_password: $scope.txt_password,
                Email:$scope.Email
            },
            type:'post',
            cache:false,
            success:function (data) {
                alert(data);
            }
        });
    }
    
});