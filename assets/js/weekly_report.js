$(document).ready(function () {
    weeklyReportView(0,6);
    weeklyReportView(1,5,3);
    weeklyReportView(2,4,5);
    weeklyReportView(3,3);
    weeklyReportView(4,2);
    weeklyReportView(5,1);
});

function weeklyReportView(status,data_id,day) {
    switch (status){
        case 0: //未到填写时段
            $('[data-id="'+data_id+'"] .report-show-entity-left').html("未到可填写时间");
            break;
        case 1: //即将开始
            $('[data-id="'+data_id+'"] .report-show-entity-left').html("还有");
            $('[data-id="'+data_id+'"] .report-show-entity-day').html(day);
            $('[data-id="'+data_id+'"] .report-show-entity-right').html("天开始");
            break;
        case 2: //已开始，未填写
            $('[data-id="'+data_id+'"] .report-show-entity-left').html("还有");
            $('[data-id="'+data_id+'"] .report-show-entity-day').html(day);
            $('[data-id="'+data_id+'"] .report-show-entity-right').html("天截止");
            $('[data-id="'+data_id+'"] .report-show-edit').css({'display':'block'});
            break;
        case 3: //已开始，已填写
            $('[data-id="'+data_id+'"] .report-show-already').css({'display':'block'});
            $('[data-id="'+data_id+'"] .report-show-edit').css({'display':'block'});
            $('[data-id="'+data_id+'"] .report-show-entity p').css({'display':'none'});
            break;
        case 4: //已填写，已过时
            $('[data-id="'+data_id+'"] .report-show-already').css({'display':'block'});
            $('[data-id="'+data_id+'"] .report-show-entity p').css({'display':'none'});
            break;
        case 5: //错过
            $('[data-id="'+data_id+'"] .report-show-entity-left').html("未按时填写");
            break;
    }
}