$(document).ready(function () {
    /**
     * 日历
     */
    $('.date-select').calendar();
    /**
     * 设置当前日期
     */
    var now = new Date();
    $('#user-select-time').html(now.getFullYear() + '-' + (now.getMonth() + 1) + '-' + now.getDate());

    //模拟插点
    addpoint(1,'2018-6-1');
    addpoint(1,'2018-6-2');
});

/**
 * 点击日期，动态绑定
 */
$(document).on('click', '.picker-calendar-day', function () {
    var date_str = $(this).data('date');
    var dateArray = date_str.split('-');
    dateArray[1] = Number(dateArray[1]) + 1;
    $('#user-select-time').html(dateArray.join('-'));
})

/**
 * 
 */
function addpoint(status, str) {
    if (status != 0) { //默认状态.改
        var point_url = '';
        switch (status) {
            case 1:
                point_url = "./assets/images/point_normal.png";
                break;
        }
        $("[data-date='" + str + "']").append("<img class='info-point' src='"+point_url+"' alt=''/>");
    }
}