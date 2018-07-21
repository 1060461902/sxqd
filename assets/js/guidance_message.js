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
    option.url = './json/guidance_message.json';
    option.data = {
        pageNum: 1
    };
    option.success = function (data) {
        if (data.code !== 200) {
            alert(data.msg);
            return;
        } else {
            $('.table-v').handlebars($('#guidance-message-model'), data.data.messages);
            /**
             * 分页
             */
            pageLimit(1, data.data.totalPage, 5, function (page) {
                var option = getBASEGETAJAX();
                option.url = './json/guidance_message.json';
                option.data = {
                    pageNum: page
                };
                option.success = function (data) {
                    if (data.code !== 200) {
                        alert(data.msg);
                        return;
                    } else {
                        $('.table-v').handlebars($('#guidance-message-model'), data.data.messages);
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
    })
}

function answer_it() {
    console.log(info_id);
    setAlert("回复成功");
    $('tr[data-id="'+info_id+'"] .is-answered').html("已回复")
}