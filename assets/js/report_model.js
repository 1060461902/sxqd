$(document).ready(function () {
    $('.read-title li').click(function () {
        var id = $(this).attr('id');
        $('.read-title li').css({
            'color':'black',
            'border-bottom':'none',
        });
        $(this).css({
            'color': '#2DBEEA',
            'border-bottom': '3px solid #2DBEEA',
        });
        if (id=="teacher-read") {

        }else if(id=="company-read"){

        }
    });
});