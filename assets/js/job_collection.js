$(document).ready(function () {
    /**
     * 访问页面请求第一页
     */
    var option = getBASEGETAJAX();
    option.url = '../student/collections/collection';
    option.data = {
        pageNum: 1
    };
    option.success = function (data) {
        if (data.code !== 200) {
            if (data.msg != '0'){
                console(data.code+':'+data.msg);
                setAlert('无法获取收藏列表');
                return;
            }
        } else {
            $('.list-group').handlebars($('#job-collection-model'), data.data.data,{
                name:'if_bool',
                callback:function (flag,options) {
                    if(flag === 1){
                        return options.fn(this);
                    }else if(flag === 0){
                        return options.inverse(this);
                    }
                }
            });
            /**
             * 分页
             */
            pageLimit(1, data.data.totalPage, 5, function (page) {
                var option = getBASEGETAJAX();
                option.url = '../student/collections/collection';
                option.data = {
                    pageNum: page
                };
                option.success = function (data) {
                    if (data.code !== 200) {
                        console(data.code+':'+data.msg);
                        setAlert('无法获取收藏列表');
                        return;
                    } else {
                        $('.list-group').handlebars($('#job-collection-model'), data.data.data,{
                            name:'if_bool',
                            callback:function (flag,options) {
                                if(flag === 1){
                                    return options.fn(this);
                                }else if(flag === 0){
                                    return options.inverse(this);
                                }
                            }
                        });
                    }
                };
                option.error = function (res) {
                    setAlert("系统繁忙，请稍后再试");
                    console.log(res)
                };
                $.ajax(option);
            });
        }
    };
    option.error = function (res) {
        setAlert("系统繁忙，请稍后再试");
        console.log(res)
    };
    $.ajax(option);

    /**
     * 点击取消关注
     */
    $('.list-group').on('click', '.unfocus-btn', function () {
        var id = $(this).data('id');
        setConfirm("是否取消对该岗位的关注？",function () {
            var option = getBASEDELETEAJAX();
            option.url = '../student/collections/collection?id='+id;
            option.success = function (data) {
                if (data.code !== 200) {
                    console.log(data.msg);
                    setAlert("无法取消关注，请稍候再试");
                } else {
                    $('.list-group-item[data-id="'+id+'"]').remove();
                    setAlert("已取消关注");
                }
            };
            option.error = function (res) {
                setAlert("系统繁忙，请稍后再试");
                console.log(res)
            };
            $.ajax(option);
        });
    });

    /**
     * 点击投简历
     */
    $('.list-group').on('click', '.send-btn', function () {
        var id = $(this).data('id');
        var option = getBASEPOSTAJAX();
        option.url = '../student/recruitments/resume';
        option.data = {'recruitmentId':id};
        option.success = function (data) {
            if (data.code !== 200) {
                console.log(data.msg);
                setAlert("无法投递简历");
                return;
            } else {
                var tag = $('.send-btn[data-id="'+id+'"]');
                tag.html('已投递');
                tag.removeClass('send-btn');
                tag.addClass('sent-btn');
                setAlert("简历已投递");
            }
        };
        option.error = function (res) {
            setAlert("系统繁忙，请稍后再试");
            console.log(res)
        };
        $.ajax(option);
    });
});