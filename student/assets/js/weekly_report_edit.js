$(document).ready(function () {

    var report_id = $.parseURL(location.href)['id'];

    $('#save-timeplate').click(function () {
        setAlert('保存成功');
    });
    $('#sub-report').click(function () {
        var edited_content = $('.note-editable').html();
        if (edited_content == '' || edited_content == null) {
            setAlert("请输入内容");
        } else {
            var option = getBASEPOSTAJAX();
            option.url = "../student/weeklyreports/report";

            /*option.data = {
                id: report_id,
                content: edited_content
            }*/
            var form = new FormData();
            var attachment = $('#select-file')[0].files[0];
            if (attachment != null && attachment != ''){
                form.append('attachment',attachment);
            }
            form.append('id', report_id);
            form.append('content', edited_content);

            option.data = form;
            option.processData = false;
            option.contentType = false;

            option.success = function (data) {
                if (data.code === 200) {
                    setAlert("发表成功");
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
            setAlert('发表成功');
        }
    });

    var option = getBASEGETAJAX();
    option.url = "../student/weeklyreports/details";
    option.data = {
        id: report_id
    }
    option.success = function (data) {
        if (data.code === 200) {
            $('.note-editable').html(data.data.report.content);
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