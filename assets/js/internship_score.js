$(document).ready(function () {
    var option = getBASEGETAJAX();
    option.url = '../student/scores/score';
    option.success = function (data) {
        if(data.code === 200){
            $('.score-body').handlebars($('#score-model'),data.data,{
                name:'weight_tool',
                callback:function (weight) {
                    return weight * 100;
                }
            });
        }
    }
    option.error = function (res) {
        console.log(res);
    }
    $.ajax(option);
});