$(document).ready(function () {

    //第二导航栏
    $('.guide-bar li').click(function () {
        var id = $(this).attr('id');
        if (id == 'info1') {
            $('#info1').css({
                'border-bottom': '3px solid rgb(0, 140, 255)',
                'color': 'rgb(0, 140, 255)',
            });
            $('#info2').css({
                'border-bottom': 'none',
                'color': 'black',
            });
            $('.recruit-info-body').css({'display':'none'});
            $('.company-info-body').css({'display':'block'});
        } else if (id == 'info2') {
            $('#info2').css({
                'border-bottom': '3px solid rgb(0, 140, 255)',
                'color': 'rgb(0, 140, 255)',
            });
            $('#info1').css({
                'border-bottom': 'none',
                'color': 'black',
            });
            $('.company-info-body').css({'display':'none'});
            $('.recruit-info-body').css({'display':'block'});
        }
    });

    //点击关注
    $('.post-focus').click(function () {
        console.log($(this).html());
        if ($(this).html()=='关注'){
            $(this).html('已收藏');
            $(this).css({
                'border':'none',
                'background':'rgb(0, 140, 255)',
                'color':'white',
            })
        }else {
            $(this).html('关注');
            $(this).css({
                'border':'0.1vw dashed #7F7F7F',
                'background':'none',
                'color':'#2DBEEA',
            })
        }
    });

    //点击投简历
    $('.hand-in-resume').click(function () {
        console.log($(this).html());
        if ($(this).html()=='投简历'){
            $(this).html('投递成功');
            $(this).css({
                'border':'none',
                'background':'rgb(0, 140, 255)',
                'color':'white',
            })
        }else {
            $(this).html('投简历');
            $(this).css({
                'border':'0.1vw dashed #7F7F7F',
                'background':'none',
                'color':'#2DBEEA',
            })
        }
    })

    //分页
    pageLimit(1,20,5);
});