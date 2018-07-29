$(document).ready(function () {
    var report_id = $.parseURL(location.href)['id'];
    /**
     * 
     */
    var option = getBASEGETAJAX();
    option.url = "../student/practicereports/details";
    option.data = {
        id: report_id
    }
    option.success = function (data) {
        if (data.code === 200) {
            $('.note-editable').html(data.data.practiceReport.content);
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
    /**
     * 
     */
    $('#save-timeplate').click(function () {
        setAlert('保存成功');
    });
    /**
     * 
     */
    $('#sub-report').click(function () {
        var edited_content = $('.note-editable').html();
        if (edited_content == '' || edited_content == null) {
            setAlert("请输入内容");
        } else {
            var option = getBASEPOSTAJAX();
            option.url = "../student/practicereports/report";
            option.data = {
                id: report_id,
                content: edited_content
            }
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
});