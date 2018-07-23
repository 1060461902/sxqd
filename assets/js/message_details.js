$(document).ready(function () {
    /*$('#summernote').summernote({
        height:250,
        maxHeight:250,
        minHeight:250,
        lang:"zh-CN",
        focus:true
    });*/
    var message_id = $.parseURL(location.href)['id'];
    /**
     * 请求接口获取页面信息
     */
    var option = getBASEGETAJAX();
    option.url = './json/message_details.json';
    option.data = {
        "id": message_id
    }
    option.success = function (data) {
        if (data.code === 200) {
            $("#teacher-name").html(data.data.teacherName);
            $("#message-content").html(data.data.msgcontent);
            $("#receiver-v").handlebars($("#receiver-model"),data.data.students);
            $(".answer-item-v").handlebars($("#answer-item-model"),data.data.replies);
        }else{
            alert(data.msg);
        }
    }
    option.error = function (res) {
        console.log(res)
    }
    $.ajax(option);
    //点击回复按钮
    $('.answer-button-bar button').click(function () {
        var answer_content = $('#answer-content').val();
        if (answer_content != null && answer_content != '') {
            var option = getBASEGETAJAX();
            option.url = './json/reply_info.json';
            option.data = {
                "id": message_id,
                "content": answer_content
            }
            option.success = function (data) {
                if (data.code === 200) {
                    setAlert('回复成功');
                } else {
                    alert("回复发生错误:" + data.msg);
                }
            }
            option.error = function (res) {
                console.log(res)
            }
            $.ajax(option);
        } else {
            setAlert("请输入回复内容！");
        }
    });
});