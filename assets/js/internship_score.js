$(document).ready(function () {
    var option = getBASEGETAJAX();
    option.url = './json/internship_score.json';
    option.success = function (data) {
        if(data.code === 200){
            var score = data.data.score;
            var weight = data.data.weight;
            $('.main-score-level').html(score.score);
            $('#t-week-report').html(score.tWeekReport);
            $('#t-summary').html(score.tSummary);
            $('#addtional-score').html(score.addtionalScore);
            $('#t-final-report').html(score.tFinalReport);
            $('#t-score').html(score.tScore);
            $('#t-multi-score').html(score.tMultiScore);
            $('#c-week-report').html(score.cWeekReport);
            $('#c-attendance').html(score.cAttendance);
            $('#c-score').html(score.cScore);
            $('#tw-teacherWeight').html(weight.teacherWeight*100);
            $('#tw-tWeekReport').html(weight.tWeekReport*100);
            $('#tw-tSummary').html(weight.tSummary*100);
            $('#tw-tFinalReport').html(weight.tFinalReport*100);
            $('#cw-companyWeight').html(weight.companyWeight*100);
            $('#cw-cWeekReport').html(weight.cWeekReport*100);
            $('#cw-cAttendance').html(weight.cAttendance*100);
        }
    }
    option.error = function (res) {
        console.log(res);
    }
    $.ajax(option);
});