var collect_status = 0; //0 未收藏   1 已收藏
var send_status = 0; //0 未投递   1 已投递
var post_id;

$(document).ready(function () {

    /**
     * 获得岗位ID
     */
    post_id = $.parseURL(location.href)['id'];

    /**
     * 请求页面数据
     */
    var option = getBASEGETAJAX();
    option.url = '../student/recruitments/profile';
    option.data = {
        id: post_id
    };
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
            if (data.data.sendflag === 1) {
                send_status = 1;
                var sub_btn = $('.send-btn');
                sub_btn.html('已投递');
                sub_btn.removeClass('send-btn');
                sub_btn.addClass('sent-btn');
            }
            if (data.data.collectflag === 1) {
                collect_status = 1;
                $('#collect_job').attr('src', './assets/images/collected_star.png');
            }
        } else {
            setAlert('系统繁忙，请稍后再试');
            console.log(data.code+":"+data.msg);
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
            var option = getBASEPUTAJAX();
            option.url = '../student/collections/collection';
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
            setConfirm('确定取消关注该岗位吗？',function () {
                var option = getBASEDELETEAJAX();
                option.url = '../student/collections/collection?id='+post_id;
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
            });
        }
    });

    /**
     * 点击投简历按钮
     */
    $('.send-btn').click(function () {
        if (send_status === 0) {
            setConfirm('确定投递简历？（一旦投递无法撤回）',function () {
                var option = getBASEGETAJAX();
                option.url = '../student/recruitments/resume';
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
                        setAlert("简历投递成功");
                    }
                }
                option.error = function (res) {
                    setAlert("系统繁忙，请稍后再试");
                    console.log(res)
                }
                $.ajax(option);
            });
        }else {
            setAlert("简历已投递");
        }
    });

    /**
     * 点击发送按钮发送咨询信息
     */
    $('.question-send-bar button').on('click',function () {
        askFunc();
    });

    /**
     * 点击评论条的回复按钮
     */
    $('.ask-list').on('click','.answer-person',function () {
        var id = $(this).data('id');
        var name = $(this).data('name');
        if (name == ''){
            name = '[未知用户]';
        }
        $('.mask').fadeIn();
        $('.answer-window').css({
            'display':'block'
        });
        $('#answer-content').attr('placeholder','回复 '+name);
        $('#answer-who').attr('data-id',id);
        $('.question-send-bar button').off('click');
    });

    /**
     * 点击弹出框的回复按钮
     */
    $('#answer-who').click(function () {
        answer_it();
        close_window();
    });
});

function askFunc() {
    var ask_content = $('#ask-input').val();
    if (ask_content == null || ask_content == ''){
        setAlert('请输入内容');
    }else {
        var option = getBASEPOSTAJAX();
        option.url = '../student/recruitments/consult';
        option.data = {
            id:post_id,
            content:ask_content
        };
        option.success = function (data) {
            if (data.code !== 200) {
                console.log(data.msg);
                setAlert("系统繁忙，请稍后再试");
            } else {
                reflashAskList();
                setAlert("消息已发送");
                $('#ask-input').val('');
            }
        };
        option.error = function (res) {
            setAlert("系统繁忙，请稍后再试");
            console.log(res)
        };
        $.ajax(option);
    }
}

function close_window() {
    $('.answer-window').css({
        'display':'none',
    });
    $('#answer-who').attr('data-id','');
    $('#answer-content').val('');
    $('#answer-content').attr('placeholder','');
    $('.mask').fadeOut();
}

function answer_it() {
    var answer_content = $('#answer-content').val();
    var id = $('#answer-who').data('id');
    if(answer_content !=null && answer_content != ''){
        var option = getBASEPOSTAJAX();
        option.url = '../student/recruitments/reply';
        option.data = {
            "commitId": id,
            "content": answer_content
        }
        option.success = function (data) {
            if(data.code === 200){
                setAlert('回复成功');
                reflashAskList();
            }else{
                console.log("回复发生错误:"+data.msg);
                setAlert('回复失败');
            }
        }
        option.error = function (res) {
            console.log(res)
        }
        $.ajax(option);
    }else{
        setAlert("请输入回复内容！");
    }
}

function reflashAskList() {
    var option = getBASEGETAJAX();
    option.url = '../student/recruitments/profile';
    option.data = {
        id: post_id
    };
    option.success = function (data) {
        if (data.code === 200) {
            $(".ask-list").handlebars($('#answer-item-model'), data.data.commits);
        } else {
            setAlert('刷新咨询板列表出错');
            console.log(data.code+":"+data.msg);
        }
    }
    option.error = function (res) {
        console.log(res);
        setAlert("系统繁忙，请稍后再试");
    }
    $.ajax(option);
}