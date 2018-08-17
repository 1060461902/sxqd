$(document).ready(function () {
    $.ajax({
        url:'../wx/records/record',
        type:'GET',
        data:{},
        success:function (d) {
            if(d.code === 200){
                $('.record-items').handlebars($('#record-model'),d.data.records,{
                    name:'if_bool',
                    callback:function (flag, options) {
                        if (flag === '1' || flag === true) {
                            return options.fn(this);
                        } else {
                            return options.inverse(this);
                        }
                    }
                });
            }else {
                console.log(d.code+' '+d.msg);
                $.toptip('无法获取记录，请重试','warning')
            }
        },
        error:function (res) {
            console.log(res);
            $.toptip('系统繁忙，请稍候再试','error');
        }
    });
});