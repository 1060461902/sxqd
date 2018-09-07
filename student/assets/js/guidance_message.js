var info_id;

$(document).ready(function () {
    /*$('.delete-btn').click(function () {
        var id = $(this).parent().parent().data('id');
        setConfirm("确认删除此条消息？",function () {
            $("tr[data-id="+id+"]").remove();
            setAlert("删除成功");
        })
    });*/
    /**
     * 访问页面请求第一页
     */
    var option = getBASEGETAJAX();
    option.url = '../student/messages/message';
    option.data = {
        pageNum: 1
    };
    option.success = function (data) {
        if (data.code !== 200) {
            console.log(data.msg);
            setAlert('获取指导消息失败');
            return;
        } else {
            $('.table-v').handlebars($('#guidance-message-model'), data.data.message,{
                name:'content_tool',
                callback:function (content) {
                    if (content.length <= 10){
                        return content;
                    }else {
                        return content.substring(0,10) + '...'
                    }
                }
            });
            /**
             * 分页
             */
            pageLimit(1, data.data.totalPage, 5, function (page) {
                var option = getBASEGETAJAX();
                option.url = '../student/messages/message';
                option.data = {
                    pageNum: page
                };
                option.success = function (data) {
                    if (data.code !== 200) {
                        console.log(data.msg);
                        setAlert('获取指导消息失败');
                        return;
                    } else {
                        $('.table-v').handlebars($('#guidance-message-model'), data.data.message, {
                            name:'content_tool',
                            callback:function (content) {
                                if (content.length <= 8){
                                    return content;
                                }else {
                                    return content.substring(0,10) + '...'
                                }
                            }
                        });
                    }
                }
                option.error = function (res) {
                    console.log(res)
                }
                $.ajax(option);
            });
        }
    }
    option.error = function (res) {
        console.log(res)
    }
    $.ajax(option);
});

function answer_window(data) {
    info_id = $(data).parent().parent().data('id');
    console.log(info_id);
    $('.answer-window').css({
        'display':'block',
    });
}

function close_window() {
    $('.answer-window').css({
        'display':'none',
    });
    $('#answer-content').val('');
}

function answer_it() {
    var answer_content = $('#answer-content').val();
    if(answer_content !=null && answer_content != ''){
        var option = getBASEPOSTAJAX();
        option.url = '../student/messages/reply';
        option.data = {
            "id": info_id,
            "content": answer_content
        }
        option.success = function (data) {
            if(data.code === 200){
                setAlert('回复成功');
                // $('tr[data-id="'+info_id+'"] .is-answered').html("已回复");
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