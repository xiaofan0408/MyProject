/**
 * Created by user-xiaofan on 2016/4/15.
 */
'use strict';

$(document).ready(function(){
    $(function() {
        var $container = $('.masonry');
        $container.imagesLoaded(function() {
            $container.masonry({

                columnWidth :'.grid-sizer',
                gutter: '.gutter-sizer',
                itemSelector: '.box',
                isAnimated: true,
            });
        });
    });
});
//
//$(document).ready(function(){
//    $(".mygallery").chromaGallery
//    ({
//        color:'#000',
//        gridMargin:15,
//        maxColumns:5,
//        dof:false,
//        screenOpacity:0.8,
//        onLoad: function () {
//            alert("1111");
//        }
//    });
//});