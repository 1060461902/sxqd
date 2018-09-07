$(document).ready(function () {
    var date = $.parseURL(location.href)['date'];
    $('#apply-date').html(date+' ');

    $('#time-date').val('00:00');
    var calendar = new datePicker();
    calendar.init({
        'trigger': '#time-date', /*按钮选择器，用于触发弹出插件*/
        'type': 'time',/*模式：date日期；datetime日期时间；time时间；ym年月；*/
        'minDate':'1900-1-1',/*最小日期*/
        'maxDate':'2100-12-31',/*最大日期*/
        'onSubmit':function(){/*确认时触发事件*/
            var theSelectData=calendar.value;
            $('#time-date').val(theSelectData);
        },
        'onClose':function(){/*取消时触发事件*/
        }
    });

    /**
     * 请求负责人
     */
    $.ajax({
        url:'../wx/leave/responser',
        type:'GET',
        data:{},
        success:function (d) {
            if (d.code === 200){
                $('#approver-container').handlebars($('#approver-person-model'), d.data, image_tool);
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
    
    $('.submit-bar button').click(function () {
        var time = $('#apply-date').html() + $('#time-date').val();
        var reason = $('#reason-enter').val();
        if (reason == null || reason == ''){
            $.alert('请输入补卡理由');
        }else {
            $.ajax({
                url:'../wx/supplementary/proposal',
                type:'POST',
                data:{
                    time:time,
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