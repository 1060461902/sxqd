$(document).ready(function () {
    var res = $.parseURL(location.href);
    if(res && res['id']){
        var id = res['id'];
        var option = getBASEGETAJAX();
        option.url = '../student/approves/details';
        option.data = {'id':id};
        option.success = function (data) {
            if(data.code !== 200){
                setAlert("无法获取新闻内容");
                console.log(data.code+':'+data.msg)
                return;
            }else{
                $('.news-title h2').html(data.data.title);
                $('.news-content-body').html(data.data.details);
                $('#company-name').html(data.data.companyName);
                $('#publish-time').html(data.data.publishTime);
            }
        }
        option.error = function (res) {
            setAlert("系统繁忙，请稍候再试");
            console.log(res)
        }
        $.ajax(option);
    }
});