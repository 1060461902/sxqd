var info_id;

$(document).ready(function () {
    /*$('.delete-btn').click(function () {
        var id = $(this).parent().parent().data('id');
        setConfirm("确认删除此条消息？",function () {
            $("tr[data-id="+id+"]").remove();
            setAlert("删除成功");
        })
    });*/
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