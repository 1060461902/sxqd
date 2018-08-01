var loading = false; //状态标记
var startY, isToTop;
var pageNum = 1;
$(document).ready(function () {

    $("body").on("touchstart", function (e) {
        startY = e.originalEvent.changedTouches[0].pageY;
    });

    $("body").on("touchmove", function (e) {
        touchMoveFunc(e);
    });

    var screen_height = document.documentElement.clientHeight;
    $('body').css({
        'min-height': screen_height
    });
    /**
     * 获取关注列表
     */
    askPage(1, function (data) {
        $('.post-cells').handlebars($('#collect-item-model'), data.data.data, {
            name: 'if_bool',
            callback: function (flag, options) {
                if (flag === 1) {
                    return options.fn(this);
                } else if (flag === 0) {
                    return options.inverse(this);
                }
            }
        });
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

/**
 * 
 */
function askPage(pageNum, callback) {
    var option = getBASEGETAJAX();
    option.data = {
        pageNum: pageNum
    }
    // option.url = '../student/collections/collection';
    option.url = './json/job_collection.json';
    option.success = function (data) {
        if (data.code === 200) {
            callback(data);
        } else {
            console.log(data.msg);
            $.toptip("系统繁忙，请稍后再试", 'error');
        }
    }
    option.error = function (res) {
        $.toptip("系统繁忙，请稍后再试", 'error');
        console.log(res);
    }
    $.ajax(option);
}

function touchMoveFunc(e) {
    var moveEndY = e.originalEvent.changedTouches[0].pageY;
    var Y = moveEndY - startY;
    if (Y < 0) {
        var scrollT = document.documentElement.scrollTop || document.body.scrollTop; //滚动条的垂直偏移
        var scrollH = document.documentElement.scrollHeight || document.body.scrollHeight; //元素的整体高度
        var clientH = document.documentElement.clientHeight || document.body.clientHeight; //元素的可见高度
        if (scrollT == scrollH - clientH) {
            e.preventDefault();
            if (loading) {
                return
            } else {
                pageNum++;
                loading = true;
                $('.weui-loadmore').fadeIn();
                askPage(pageNum, function (data) {
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
                    $("body").on("touchmove", function (e) {
                        touchMoveFunc(e);
                    });
                });
                $("body").off("touchmove");
                $('.weui-loadmore').fadeOut();
                loading = false;
            }
        }
    }
}