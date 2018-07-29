$(document).ready(function () {
    /*pageLimit(1,20,5,function (page) {
        console.log(page);
    });*/
    /**
     * 请求月份
     */
    var option = getBASEGETAJAX();
    option.url = '../student/attendances/pattern';
    option.success = function (data) {
        if (data.code === 200) {
            $("#pattern-v").handlebars($('#pattern-model'), data.data.patterns);
            var first_pattern_val = data.data.patterns[0];
            /**
             * 请求第一个月份的考勤情况
             */
            var option = getBASEGETAJAX();
            option.url = '../student/attendances/record';
            option.data = {
                pattern: first_pattern_val
            }
            option.success = function (data) {
                if (data.code === 200) {
                    $("#record-items").handlebars($('#record-model'), data.data.attendances);
                } else {
                    alert(data.msg);
                }
            }
            option.error = function (res) {
                console.log(res);
            };
            $.ajax(option);
        } else {
            alert(data.msg);
        }
    }
    option.error = function (res) {
        console.log(res);
    };
    $.ajax(option);

    $("#pattern-v").on('change', function () {
        var pattern_val = $('#pattern-v').val();
        var option = getBASEGETAJAX();
        option.url = '../student/attendances/record';
        option.data = {
            pattern: pattern_val
        }
        option.success = function (data) {
            if (data.code === 200) {
                $("#record-items").handlebars($('#record-model'), data.data.attendances);
            } else {
                alert(data.msg);
            }
        }
        option.error = function (res) {
            console.log(res);
        };
        $.ajax(option);
    });
});