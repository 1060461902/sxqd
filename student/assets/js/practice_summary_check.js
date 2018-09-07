$(document).ready(function () {
    var report_id = $.parseURL(location.href)['id'];
    /**
     * 请求页面数据
     */
    var option = getBASEGETAJAX();
    option.url = "../student/summaries/details";
    option.data = {
        id: report_id
    }
    option.success = function (data) {
        if (data.code === 200) {
            $('.info-content').handlebars($('#info-content-model'),data.data);
            $('#teacher-read-v').handlebars($('#teacher-read-model'),data.data);
            // $('#company-read-v').handlebars($('#company-read-model'),data.data);
        } else {
            console.log(data.msg);
            setAlert("系统繁忙，请稍后再试");
        }
    }
    option.error = function (res) {
        setAlert("系统繁忙，请稍后再试");
        console.log(res);
    }
    $.ajax(option);
});