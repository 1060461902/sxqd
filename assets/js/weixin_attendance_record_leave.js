$(document).ready(function () {
    var id = $.parseURL(location.href)['id'];
    $.ajax({
        url:'../wx/records/leave',
        type:'GET',
        data:{
            id:id
        },
        success:function (d) {
            if (d.code == 200) {
                var if_bool =  {
                    name:'if_bool',
                    callback: function (flag, options) {
                        if (flag === true) {
                            return options.fn(this);
                        } else {
                            return options.inverse(this);
                        }
                    }
                };
                $('.info-container').handlebars($('#info-model'), d.data,if_bool);
                $('.approver-bar').handlebars($('#approver-item-model'), d.data,if_bool);
            }else {
                console.log(d.code+' '+d.msg);
                $.toptip('无法获取详情，请重试','warning')
            }
        },
        error:function (res) {
            console.log(res);
            $.toptip('系统繁忙，请稍候再试','error');
        }
    });
});