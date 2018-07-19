$(document).ready(function () {
    var res = $.parseURL(location.href);
    if(res && res['id']){
        var id = res['id'];
        var option = getBASEGETAJAX();
        option.url = './json/news.json';
        option.data = {'id':id};
        option.success = function (data) {
            if(data.code !== 200){
                alert(data.msg);
                return;
            }else{
                $('.news-title h2').html(data.data.title);
                $('.news-content-body').html(data.data.details);
            }
        }
        option.error = function (res) {
            console.log(res)
        }
        $.ajax(option);
    }
});