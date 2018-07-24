var collect_status = 0; //0 未收藏   1 已收藏
var send_status = 0; //0 未投递   1 已投递

$(document).ready(function () {

    /**
     * 获得岗位ID
     */
    var post_id = $.parseURL(location.href)['id'];

    /**
     * 请求页面数据
     */
    var option = getBASEGETAJAX();
    option.url = './json/job_profile.json';
    option.data = {
        id: post_id
    }
    option.success = function (data) {
        if (data.code === 200) {
            $(".post-info").handlebars($('#recruitment-model'), data.data.recruitment, {
                name: 'skills',
                callback: function (skill_request) {
                    var content = '';
                    var skills = skill_request.split(',');
                    var len = skills.length;
                    for (var i = 0; i < len; i++) {
                        content += '<span class="label label-info">' + skills[i] + '</span>';
                    }
                    return content;
                }
            });
            $(".company-info").handlebars($('#company-model'), data.data.company);
            $(".ask-list").handlebars($('#answer-item-model'), data.data.commits);
            $(".job-discription").html(data.data.recruitment.postInfo);
            if (send_status === 1) {
                var sub_btn = $('.send-btn');
                sub_btn.html('已投递');
                sub_btn.removeClass('send-btn');
                sub_btn.addClass('sent-btn');
            }
        } else {
            setAlert('系统繁忙，请稍后再试');
            console.log(msg);
        }
    }
    option.error = function (res) {
        console.log(res);
        setAlert("系统繁忙，请稍后再试");
    }
    $.ajax(option);

    /**
     * 点击星星关注或取消关注
     */
    $('body').on('click', '#collect_job', function () {
        if (collect_status === 0) {
            // var option = getBASEPUTAJAX();
            var option = getBASEGETAJAX(); //暂时
            option.url = './json/focus_job.json';
            option.data = {
                'id': post_id
            };
            option.success = function (data) {
                if (data.code !== 200) {
                    console.log(data.msg);
                    setAlert("系统繁忙，请稍后再试");
                } else {
                    collect_status = 1;
                    $('#collect_job').attr('src', './assets/images/collected_star.png');
                    setAlert("关注成功");
                }
            }
            option.error = function (res) {
                setAlert("系统繁忙，请稍后再试");
                console.log(res);
            }
            $.ajax(option);
        } else if (collect_status === 1) {
            // var option = getBASEDELETEAJAX();
            var option = getBASEGETAJAX(); //暂时
            option.url = './json/unfoucs_job.json';
            option.data = {
                'id': post_id
            };
            option.success = function (data) {
                if (data.code !== 200) {
                    console.log(data.msg);
                    setAlert("系统繁忙，请稍后再试");
                } else {
                    collect_status = 0;
                    $('#collect_job').attr('src', './assets/images/collect_star.png');
                    setAlert("已取消关注");
                }
            }
            option.error = function (res) {
                setAlert("系统繁忙，请稍后再试");
                console.log(res)
            }
            $.ajax(option);
        }
    });

    /**
     * 
     */
    $('.send-btn').click(function () {
        // var option = getBASEPOSTAJAX();
        var option = getBASEGETAJAX(); //暂时
        option.url = './json/send_resume.json';
        option.data = {
            'recruitmentId': post_id
        };
        option.success = function (data) {
            if (data.code !== 200) {
                console.log(data.msg);
                setAlert("系统繁忙，请稍后再试");
            } else {
                var sub_btn = $('.send-btn');
                sub_btn.html('已投递');
                sub_btn.removeClass('send-btn');
                sub_btn.addClass('sent-btn');
                send_status = 1;
                setAlert("简历已投递");
            }
        }
        option.error = function (res) {
            setAlert("系统繁忙，请稍后再试");
            console.log(res)
        }
        $.ajax(option);
    });
});