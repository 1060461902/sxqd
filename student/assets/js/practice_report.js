$(document).ready(function () {
    /*html2canvas(win.document.body).then((canvas) => {
        canvas.style = "width: 490px; height: 400px;";
        $('.report-show-entity').append(canvas);
    });*/
    var option = getBASEGETAJAX();
    option.url = '../student/practicereports/report';
    option.success = function (data) {
        if (data.code === 200) {
            if (data.data != "0") {
                $('.report-show-body').handlebars($('#practice-report-model'), data.data, [{
                    name: 'show_info',
                    callback: function (status_code, id, write_flag, days) {
                        switch (status_code) {
                            case '1': //未到填写时段
                                return '<p><span class="report-show-entity-left">未到可填写时间</span></p>';
                            case '3': //已开始
                                if (write_flag === 1) {
                                    return '<a class="report-show-already" href="./practice_report_check.html?id=' + id + '">' +
                                        '<img src="./assets/images/done.png">' +
                                        '</a>'
                                } else {
                                    return '<p><span class="report-show-entity-left">可填写</span></p>';
                                }
                            case '5': //已结束
                                if (write_flag === 1) {
                                    return '<a class="report-show-already" href="./practice_report_check.html?id=' + id + '">' +
                                        '<img src="./assets/images/done.png">' +
                                        '</a>'
                                } else {
                                    return '<p><span class="report-show-entity-left">未按时填写</span></p>';
                                }
                            case '2': //即将开始
                                return '<p>' +
                                    '<span class="report-show-entity-left">还有</span>' +
                                    '<span class="report-show-entity-day">' + days + '</span>' +
                                    '<span class="report-show-entity-right">天开始</span>' +
                                    '</p>'
                            case '4': //即将截止
                                if (write_flag === 1) {
                                    return '<a class="report-show-already" href="./practice_report_check.html?id=' + id + '">' +
                                        '<img src="./assets/images/done.png">' +
                                        '</a>'
                                } else {
                                    return '<p>' +
                                        '<span class="report-show-entity-left">还有</span>' +
                                        '<span class="report-show-entity-day">' + days + '</span>' +
                                        '<span class="report-show-entity-right">天截止</span>' +
                                        '</p>'
                                }
                        }

                    }
                }, {
                    name: 'editable',
                    callback: function (status_code, id) {
                        switch (status_code) {
                            case '3':
                            case '4':
                                return '<a class="report-show-edit" href="./practice_report_edit.html?id=' + id + '">' +
                                    '<img src="assets/images/edit_pen_nover.png">' +
                                    '</a>';
                        }
                    }
                }]);
            }
        } else {
            console.log(data.msg);
            setAlert('系统繁忙，请稍后再试');
        }
    }
    option.error = function (res) {
        console.log(res);
        setAlert('系统繁忙，请稍后再试');
    }
    $.ajax(option);
});