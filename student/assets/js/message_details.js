$(document).ready(function () {
    /*$('#summernote').summernote({
        height:250,
        maxHeight:250,
        minHeight:250,
        lang:"zh-CN",
        focus:true
    });*/

    /**
     * 获取消息ID
     */
    var message_id = $.parseURL(location.href)['id'];
    /**
     * 请求接口获取页面信息
     */
    var option = getBASEGETAJAX();
    option.url = '../student/messages/details';
    option.data = {
        "id": message_id
    }
    option.success = function (data) {
        if (data.code === 200) {
            $("#teacher-name").html(data.data.teacherName);
            $("#message-content").html(data.data.msgcontent);
            $("#receiver-v").handlebars($("#receiver-model"), data.data.students);
            $(".answer-item-v").handlebars($("#answer-item-model"), data.data.replies);
        } else {
            setAlert('无法获取页面信息');
        }
    }
    option.error = function (res) {
        setAlert("系统繁忙，请稍候再试");
        console.log(res)
    }
    $.ajax(option);
    /**
     * 点击回复按钮
     */
    $('.answer-button-bar button').click(function () {
        var answer_content = $('#answer-content').val();
        if (answer_content != null && answer_content != '') {
            var option = getBASEPOSTAJAX();
            option.url = '../student/messages/reply';
            option.data = {
                "id": message_id,
                "content": answer_content
            }
            option.success = function (data) {
                if (data.code === 200) {
                    setAlert('回复成功');
                    $('#answer-content').val('');
                    /**
                     * 请求接口获取回复列表
                     */
                    var option = getBASEGETAJAX();
                    option.url = '../student/messages/details';
                    option.data = {
                        "id": message_id
                    }
                    option.success = function (data) {
                        if (data.code === 200) {
                            $(".answer-item-v").handlebars($("#answer-item-model"), data.data.replies);
                        } else {
                            setAlert('无法获取回复列表');
                        }
                    }
                    option.error = function (res) {
                        setAlert("系统繁忙，请稍候再试");
                        console.log(res)
                    }
                    $.ajax(option);
                } else {
                    console.log(data.code+':'+data.msg);
                    setAlert("回复失败");
                }
            }
            option.error = function (res) {
                setAlert("系统繁忙，请稍候再试");
                console.log(res)
            }
            $.ajax(option);
        } else {
            setAlert("请输入回复内容！");
        }
    });
});