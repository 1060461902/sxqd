$(document).ready(function () {
    /**
     * 考勤按钮的时间显示
     */
    setInterval(function () {
        var dateObj = new Date();
        var month = dateObj.getMonth() + 1;
        var date = dateObj.getDate();
        var hours = dateObj.getHours();
        var minutes = dateObj.getMinutes();
        var seconds = dateObj.getSeconds();
        if(hours<10){
            hours = "0"+hours;
        }
        if(minutes<10){
            minutes = "0"+minutes;
        }
        if(seconds<10){
            seconds = "0"+seconds;
        }
        var newDate = month+"月"+date+"日"+"&nbsp"+hours+":"+minutes+":"+seconds;
        $('#show-time').html(newDate);
    }, 1000);
    //picker-calendar-day
});