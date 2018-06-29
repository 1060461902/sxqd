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