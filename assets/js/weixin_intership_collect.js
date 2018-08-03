var page = 1;

$(document).ready(function () {

    $('.post-loading').dropload({
        scrollArea: window,
        loadDownFn: function (me) {
            $.ajax({
                type: 'GET',
                url: './json/job_collection1.json',
                // url:'../student/collections/collection',
                data: {
                    pageNum: page
                },
                success: function (data) {
                    if (data.code === 200) {
                        if (data.data.data.length === 0) {
                            // 锁定
                            me.lock();
                            // 无数据
                            me.noData();
                        } else {
                            var template = Handlebars.compile($('#collect-item-model').html());
                            Handlebars.registerHelper('if_bool', function (flag, options) {
                                if (flag === 1) {
                                    return options.fn(this);
                                } else if (flag === 0) {
                                    return options.inverse(this);
                                }
                            });
                            var html = template(data.data.data);
                            $('.post-cells').append(html);
                            page++;
                            me.resetload();
                        }
                    } else {
                        console.log(data.msg);
                        $.toptip("系统繁忙，请稍后再试", 'error');
                        me.resetload();
                        // 锁定
                        me.lock();
                        // 无数据
                        me.noData();
                    }
                },
                error: function (res) {
                    $.toptip("系统繁忙，请稍后再试", 'error');
                    console.log(res);
                    me.resetload();
                    // 锁定
                    me.lock();
                    // 无数据
                    me.noData();
                }
            });
        }
    });

    /**
     * 点击取消关注
     */
    $('.post-cells').on('click', '.post-unfocus', function () {
        var id = $(this).data('id');
        // var option = getBASEDELETEAJAX();
        // option.url = '../student/collections/collection?id='+id;
        var option = getBASEGETAJAX();
        option.url = './json/unfoucs_job.json';
        option.success = function (data) {
            if (data.code !== 200) {
                console.log(data.msg);
                $.toptip("系统繁忙，请稍后再试", 'error');
            } else {
                $('.weui-cell[data-id="' + id + '"]').remove();
                $.toptip('已取消关注', 'success');
            }
        };
        option.error = function (res) {
            $.toptip("系统繁忙，请稍后再试", 'error');
            console.log(res)
        };
        $.ajax(option);
    });

    /**
     * 点击投简历
     */
    $('.post-cells').on('click', '.post-send', function () {
        var id = $(this).data('id');
        // var option = getBASEPOSTAJAX();
        // option.url = '../student/recruitments/resume';
        var option = getBASEGETAJAX();
        option.url = './json/send_resume.json';
        option.data = {
            'recruitmentId': id
        };
        option.success = function (data) {
            if (data.code !== 200) {
                console.log(data.msg);
                $.toptip("系统繁忙，请稍后再试", 'error');
                return;
            } else {
                var tag = $('.post-send[data-id="' + id + '"]');
                tag.html('已投递');
                tag.removeClass('post-send');
                tag.addClass('post-sent');
                $.toptip('投递成功', 'success');
            }
        };
        option.error = function (res) {
            $.toptip("系统繁忙，请稍后再试", 'error');
            console.log(res)
        };
        $.ajax(option);
    });
});