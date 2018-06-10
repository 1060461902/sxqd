$(document).ready(function () {
   $(".log-input ul li").on("click",function (e) {
       $(".log-input ul li").css({
           'border-bottom': 'none',
           'color': 'black',
       });
       $(e.toElement).css({
           'border-bottom': '3px solid rgb(0, 140, 255)',
           'color': 'rgb(0, 140, 255)',
       });
       if (e.toElement.id=='company-select'){
           $('.company-logUp-bar').css({
               'display':"block"
           });
           $('.remember-bar').css({
               'display':"none"
           });
       }else {
           $('.remember-bar').css({
               'display':"block"
           });
           $('.company-logUp-bar').css({
               'display':"none"
           });
       }
   }) 
});