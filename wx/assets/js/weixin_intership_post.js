$(document).ready(function () {
    /**
     * 请求页面信息
     */
    var post_id = $.parseURL(location.href)['id'];
    var option = getBASEGETAJAX();
    option.url = '../student/recruitments/profile';
    option.data = {
        id:post_id
    }
    option.success = function (d) {
        if (d.code === 200){
            $('.post-info').handlebars($('#post-info-model'),d.data, {
                name: 'skills',
                callback: function (skill_request) {
                    var content = '';
                    var skills = skill_request.split(',');
                    var len = skills.length;
                    for (var i = 0; i < len; i++) {
                        content += '<span class="post-skill-lable">' + skills[i] + '</span>';
                    }
                    return content;
                }
            });
            $('.post-info-other').handlebars($('#post-info-other-model'),d.data.company);
            $('.post-describe p').html(d.data.recruitment.postInfo);
            $('.ask-items').handlebars($('#ask-item-model'),d.data.commits,image_tool);
            $('.post-do-bar').handlebars($('#do-model'),d.data,{
                name:'if_bool',
                callback:function (flag, options) {
                    if (flag === 1) {
                        return options.fn(this);
                    } else if (flag === 0) {
                        return options.inverse(this);
                    }
                }
            });
        }else {
            console.log(d.code+':'+d.msg);
            $.toptip("系统繁忙，请稍后再试", 'error');
        }
    }
    option.error = function (res) {
        console.log(res);
        $.toptip("系统繁忙，请稍后再试", 'error');
    };
    $.ajax(option);

    /**
     * 点击关注按钮关注
     */
    $('.post-do-bar').on('click','.collect-btn',function () {
        var option = getBASEPUTAJAX();
        option.url = '../student/collections/collection';
        option.data = {
            'id': post_id
        };
        option.success = function (data) {
            if (data.code !== 200) {
                console.log(data.msg);
                $.toptip("系统繁忙，请稍后再试","error");
            } else {
                var btn = $('.collect-btn');
                btn.removeClass('collect-btn');
                btn.addClass('collected-btn');
                $('.collect-text').html('已关注');
                $('.collect-star').attr('src', './assets/images/collected_star.png');
                $.toptip("关注成功","success");
            }
        }
        option.error = function (res) {
            $.toptip("系统繁忙，请稍后再试","error");
            console.log(res);
        }
        $.ajax(option);
    });

    /**
     * 点击已关注按钮取消关注
     */
    $('.post-do-bar').on('click','.collected-btn',function () {
        var option = getBASEDELETEAJAX();
        option.url = '../student/collections/collection?id='+post_id;
        option.success = function (data) {
            if (data.code !== 200) {
                console.log(data.msg);
                $.toptip("系统繁忙，请稍后再试","error");
            } else {
                var btn = $('.collected-btn');
                btn.removeClass('collected-btn');
                btn.addClass('collect-btn');
                $('.collect-text').html('关注岗位');
                $('.collect-star').attr('src', './assets/images/collect_star.png');
                $.toptip("已取消关注","success");
            }
        }
        option.error = function (res) {
            $.toptip("系统繁忙，请稍后再试","error");
            console.log(res);
        }
        $.ajax(option);
    });

    /**
     * 投简历
     */
    $('.post-do-bar').on('click','.send-btn',function () {
        var option = getBASEGETAJAX();
        option.url = '../student/recruitments/resume';
        option.data = {
            'recruitmentId': post_id
        };
        option.success = function (data) {
            if (data.code !== 200) {
                console.log(data.msg);
                $.toptip("系统繁忙，请稍后再试","error");
            } else {
                var sub_btn = $('.send-btn');
                sub_btn.html('已投递');
                sub_btn.removeClass('send-btn');
                sub_btn.addClass('sent-btn');
                $.toptip("简历投递成功","success");
            }
        }
        option.error = function (res) {
            $.toptip("系统繁忙，请稍后再试","error");
            console.log(res)
        }
        $.ajax(option);
    });

    /**
     * 点击回复按钮
     */
    var reply_who;
    $('.ask-items').on('click','.reply-btn a',function () {
        reply_who = $(this).data('id');
        var reply_name = $(this).data('name');
        $.prompt({
            title: '请输入内容',
            text: '回复 '+reply_name+' :',
            empty: false, // 是否允许为空
            onOK: function (input) {
                var option = getBASEPOSTAJAX();
                option.url = '../student/recruitments/reply';
                option.data = {
                    "commitId": reply_who,
                    "content": input
                }
                option.success = function (data) {
                    if(data.code === 200){
                        $.toptip('回复成功','success');
                        reflashAskList(post_id);
                    }else{
                        console.log("回复发生错误:"+data.msg);
                        $.toptip('回复失败','error');
                    }
                }
                option.error = function (res) {
                    console.log(res)
                }
                $.ajax(option);
            }
        });
    });

    /**
     * 点击咨询按钮
     */
    $('body').on('click','#ask-btn',function () {
        $.prompt({
            title: '请输入内容',
            empty: false, // 是否允许为空
            onOK: function (input) {
                var option = getBASEPOSTAJAX();
                option.url = '../student/recruitments/consult';
                option.data = {
                    "id":post_id,
                    "content": input
                }
                option.success = function (data) {
                    if(data.code === 200){
                        $.toptip('回复成功','success');
                        reflashAskList(post_id);
                    }else{
                        console.log("回复发生错误:"+data.msg);
                        $.toptip('回复失败','warning');
                    }
                }
                option.error = function (res) {
                    $.toptip('系统繁忙，请稍候再试','error');
                    console.log(res)
                }
                $.ajax(option);
            }
        });
    });
});

/**
 * 刷新回复列表
 */
function reflashAskList(post_id) {
    var option = getBASEGETAJAX();
    option.url = '../student/recruitments/profile';
    option.data = {
        id: post_id
    };
    option.success = function (d) {
        if (d.code === 200) {
            $('.ask-items').handlebars($('#ask-item-model'), d.data.commits, image_tool);
        } else {
            $.toptip('刷新咨询板列表出错','error');
            console.log(d.code+":"+d.msg);
        }
    }
    option.error = function (res) {
        console.log(res);
        $.toptip("系统繁忙，请稍后再试",'error');
    }
    $.ajax(option);
}