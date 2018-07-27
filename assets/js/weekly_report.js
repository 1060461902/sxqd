$(document).ready(function () {
    getPageData(1,pageLimit);
});

/*function weeklyReportView(status, data_id, day) {
    switch (status) {
        case 1: //未到填写时段
            $('[data-id="' + data_id + '"] .report-show-entity-left').html("未到可填写时间");
            break;
        case 2: //即将开始
            $('[data-id="' + data_id + '"] .report-show-entity-left').html("还有");
            $('[data-id="' + data_id + '"] .report-show-entity-day').html(day);
            $('[data-id="' + data_id + '"] .report-show-entity-right').html("天开始");
            break;
        case 3: //已开始，未填写
            $('[data-id="' + data_id + '"] .report-show-entity-left').html("还有");
            $('[data-id="' + data_id + '"] .report-show-entity-day').html(day);
            $('[data-id="' + data_id + '"] .report-show-entity-right').html("天截止");
            $('[data-id="' + data_id + '"] .report-show-edit').css({
                'display': 'block'
            });
            break;
        case 4: //已开始，已填写
            $('[data-id="' + data_id + '"] .report-show-already').css({
                'display': 'block'
            });
            $('[data-id="' + data_id + '"] .report-show-edit').css({
                'display': 'block'
            });
            $('[data-id="' + data_id + '"] .report-show-entity p').css({
                'display': 'none'
            });
            break;
        case 5: //已填写，已过时
            $('[data-id="' + data_id + '"] .report-show-already').css({
                'display': 'block'
            });
            $('[data-id="' + data_id + '"] .report-show-entity p').css({
                'display': 'none'
            });
            break;
        case 6: //错过
            $('[data-id="' + data_id + '"] .report-show-entity-left').html("未按时填写");
            break;
    }
}*/

function getPageData(pageNum,pageLimit) {
    var option = getBASEGETAJAX();
    option.url = './json/weekly_report.json';
    option.data = {
        'pageNum':pageNum
    };
    option.success = function (data) {
        if (data.code === 200) {
            $('.weekly-report-list').handlebars($('#weekly-report-item-model'), data.data.data, [{
                name: 'show_info',
                callback: function (message,id) {
                    var infos = message.split('_');
                    if (infos.length === 1) {
                        switch (message) {
                            case '1': //未到填写时段
                                return '<p><span class="report-show-entity-left">未到可填写时间</span></p>';
                            case '4': //已开始，已填写
                            case '5': //已填写，已过时
                                return '<a class="report-show-already" href="./weekly_report_check.html?id='+id+'">' +
                                    '<img src="./assets/images/done.png">' +
                                    '</a>'
                            case '6': //错过
                                return '<p><span class="report-show-entity-left">未按时填写</span></p>';
                        }
                    } else {
                        switch (infos[0]) {
                            case '2': //即将开始
                                return '<p>' +
                                    '<span class="report-show-entity-left">还有</span>' +
                                    '<span class="report-show-entity-day">' + infos[1] + '</span>' +
                                    '<span class="report-show-entity-right">天开始</span>' +
                                    '</p>'
                            case '3': //已开始，未填写
                                return '<p>' +
                                    '<span class="report-show-entity-left">还有</span>' +
                                    '<span class="report-show-entity-day">' + infos[1] + '</span>' +
                                    '<span class="report-show-entity-right">天截止</span>' +
                                    '</p>'
                        }
                    }
                }
            }, {
                name: 'editable',
                callback: function (message,id) {
                    var infos = message.split('_');
                    switch (infos[0]) {
                        case '3':
                        case '4':
                            return '<a class="report-show-edit" href="./weekly_report_edit.html?id='+id+'">' +
                                '<img src="assets/images/edit_pen_nover.png">' +
                                '</a>';
                    }
                }
            }]);

            if(pageLimit){
                pageLimit(pageNum,data.data.totalPage,5,function (page) {
                    getPageData(page);
                })
            }
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
}