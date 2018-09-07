$(document).ready(function () {
    $('#start-date-picker').calendar();
    $('#end-date-picker').calendar();

    /**
     * 请求负责人
     */
    $.ajax({
        url:'../wx/leave/responser',
        type:'GET',
        data:{},
        success:function (d) {
            if (d.code === 200){
                $('#approver-container').handlebars($('#approver-person-model'),d.data,image_tool);
            }else {
                console.log(d.code+' '+d.msg);
                $.toptip('无法获取负责人列表','warning')
            }
        },
        error:function (res) {
            console.log(res);
            $.toptip('系统繁忙，请稍候再试','error');
        }
    });

    /**
     * 点击提交按钮
     */
    $(".submit-bar button").click(function () {
        var starttime = $('#start-date-picker').val();
        var endtime = $('#end-date-picker').val();
        var days = $('#duration-day-num').val();
        var reason = $('#reason-enter').val();
        if (starttime == null || starttime == ''){
            $.alert('请输入开始时间');
        } else if (endtime == null || endtime == '') {
            $.alert('请输入结束时间');
        } else if (days == null || days == '') {
            $.alert('请输入请假天数');
        } else if (reason == null || reason == ''){
            $.alert('请输入请假理由');
        } else {
            $.ajax({
                url:'../wx/leave/proposal',
                type:'POST',
                data:{
                    starttime:starttime.split('/').join('-'),
                    endtime:endtime.split('/').join('-'),
                    days:days,
                    reason:reason
                },
                success:function (d) {
                    if (d.code === 200){
                        $.alert('申请成功',function () {
                            history.back();
                        });
                    }else {
                        console.log(d.code+' '+d.msg);
                        $.toptip('无法申请，请重试','warning')
                    }
                },
                error:function (res) {
                    console.log(res);
                    $.toptip('系统繁忙，请稍候再试','error');
                }
            });
        }
    });
});