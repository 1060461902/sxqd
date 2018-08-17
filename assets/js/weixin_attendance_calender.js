$(document).ready(function () {
    /**
     * 日历
     */
    $('.date-select').calendar();
    /**
     * 设置当前日期
     */
    var now = new Date();
    var now_str = now.getFullYear() + '-' + ((now.getMonth() + 1)<10 ? '0'+(now.getMonth()+1) : (now.getMonth()+1)) + '-' + (now.getDate()<10 ? '0'+now.getDate() : now.getDate());
    var YMDate = now.getFullYear() + '年' + ((now.getMonth() + 1)<10 ? '0'+(now.getMonth()+1) : (now.getMonth()+1)) + '月';
    $('#user-select-time').html(now_str);

    /**
     * 请求月打卡状态
     */
    getThisMouth(YMDate);

    /**
     * 请求当日打卡记录
     */
    $.ajax({
        url:'../wx/attendances/record',
        type:'GET',
        data:{},
        success:function (d) {
            if (d.code === 200){
                var attendances = d.data.attendances;
                $('.dk-bar').handlebars($('#dk-model'), attendances);
            }else {
                console.log(d.code + ":" + d.msg);
                $.toptip('无法获取当天记录','warning');
            }
        },
        error:function (res) {
            console.log(res);
            setAlert("系统繁忙,请稍后再试");
        }
    });

    /**
     * 点击年月的选择按钮
     */
    $('body').on('click','.picker-calendar-year-picker a,.picker-calendar-month-picker a',function () {
        var year = $('.current-year-value').html();
        var CHMouth = $('.current-month-value').html();
        var mouth;
        switch (CHMouth){
            case '一月':
                mouth = '01';
                break;
            case '二月':
                mouth = '02';
                break;
            case '三月':
                mouth = '03';
                break;
            case '四月':
                mouth = '04';
                break;
            case '五月':
                mouth = '05';
                break;
            case '六月':
                mouth = '06';
                break;
            case '七月':
                mouth = '07';
                break;
            case '八月':
                mouth = '08';
                break;
            case '九月':
                mouth = '09';
                break;
            case '十月':
                mouth = '10';
                break;
            case '十一月':
                mouth = '11';
                break;
            case '十二月':
                mouth = '12';
                break;
        }
        var selectYMDate = year+'年'+mouth+'月';
        getThisMouth(selectYMDate);
    });
});

/**
 * 点击日期,请求本日打卡记录
 */
$(document).on('click', '.picker-calendar-day', function () {
    var date_str = $(this).data('date');
    var dateArray = date_str.split('-');
    dateArray[1] = Number(dateArray[1]) + 1;
    dateArray[1] = dateArray[1] < 10 ? '0' + dateArray[1] : dateArray[1];
    dateArray[2] = dateArray[2] < 10 ? '0' + dateArray[2] : dateArray[2];
    // var CHDate = dateArray[0]+'年'+dateArray[1]+'月'+dateArray[2]+'日';
    var ask_date = dateArray.join('-');
    $('#user-select-time').html(ask_date);
    $.ajax({
        url:'../wx/supplementary/attendance',
        type:'GET',
        data:{
            date:ask_date
        },
        success:function (d) {
            if (d.code === 200){
                $('.dk-bar').handlebars($('#dk-model'),d.data.attendances);
            }else {
                console.log(d.code+':'+d.msg);
                $.toptip('无法获取当日记录','warning');
            }
        }

    });
});

/**
 * 通用状态显示函数
 */
function addpoint(status, str) {
    if (status != 0) { //默认状态.改
        var point_url = '';
        switch (status) {
            case 1:
                point_url = "./assets/images/point_normal.png";
                break;
            case 2:
                point_url = "./assets/images/point_abnormal.png";
                break;
        }
        strArray = str.split('-');
        strArray[1] = parseInt(strArray[1],10) - 1;
        strArray[2] = parseInt(strArray[2],10);
        str = strArray.join('-');
        $("[data-date='" + str + "']").append("<img class='info-point' src='"+point_url+"' alt=''/>");
    }
}

/**
 * 获取某月状态
 */
function getThisMouth(CHYMDate) {
    $.ajax({
        url:'../wx/supplementary/record',
        type:'GET',
        data:{
            pattern: CHYMDate
        },
        success:function (d) {
            if (d.code === 200){
                var normal = d.data.normal;
                var abnormal = d.data.abnormal;
                normal.forEach(function (item, index) {
                    addpoint(1,item);
                });
                abnormal.forEach(function (item, index) {
                    addpoint(2,item);
                });
            }else {
                console.log(d.code + ":" + d.msg);
                $.toptip('无法获取当月状态','warning');
            }
        },
        error:function (res) {
            console.log(res);
            setAlert("系统繁忙,请稍后再试");
        }
    });
}