/**
 * Created by user-xiaofan on 2016/4/15.
 */
'use strict';
var $jq = $.noConflict();
var app = angular.module('myapp',[]);

app.controller('imgController',function ($scope) {
    $scope.url = 'http://127.0.0.1:8080/MyProject/';
    $scope.imgs=[];
    $jq.ajax({
        type:"GET",
        url: "http://127.0.0.1:8080/MyProject/JsonTest",async:false,
        dataType:"json",
        success:function(data){
            $scope.imgs = data;
        }
    });
});

$jq(document).ready(function(){
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
app.controller('regController',function ($scope){


});