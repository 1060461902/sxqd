$(document).ready(function () {
    var report_id = $.parseURL(location.href)['id'];
    /**
     * 获取已保存的内容
     */
    var option = getBASEGETAJAX();
    option.url = "../student/summaries/read";
    option.data = {
        id: report_id
    }
    option.success = function (data) {
        if (data.code === 200) {
            $('.note-editable').html(data.data.content);
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
     * 点击保存
     */
    $('#save-timeplate').click(function () {
        var edited_content = $('.note-editable').html();
        if (edited_content == '' || edited_content == null) {
            setAlert("请输入内容");
        } else {
            var option = getBASEPOSTAJAX();
            option.url = "../student/summaries/save";

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
                    setAlert('保存成功');
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
    /**
     * 点击发表
     */
    $('#sub-report').click(function () {
        var edited_content = $('.note-editable').html();
        if (edited_content == '' || edited_content == null) {
            setAlert("请输入内容");
        } else {
            var option = getBASEPOSTAJAX();
            option.url = "../student/summaries/summary";

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
});