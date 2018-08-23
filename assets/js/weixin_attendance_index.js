/**
 * 经纬度
 */
var latitude,longitude;
/**
 * 上班打卡还是下班打卡
 */
var isGoToWork = true;
/**
 * 最后一条记录的ID
 */
var lastID;
/**
 * 当前日期
 */
var YMDate = getCHDate();

$(document).ready(function () {
    /**
     * 询问是否已绑定，后端自动跳转到提示页面
     */
    var option = getBASEGETAJAX();
    option.url = '/internshipmgn/wx/auth/attendance';
    option.error = function (res) {
        $.toptip("系统繁忙，请稍后再试", 'error');
        console.log(res);
    }
    $.ajax(option);

    /**
     * 获取url中的参数并判断切换到哪一页面
     */
    var res = $.parseURL(location.href);
    if (res) {
        switch (res['tab']) {
            case '2':
                $('#sq').click();
                break;
            case '3':
                $('#tj').click();
                break;
            default:
                $('#dk').click();
                break;
        }
    }

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
        if (hours < 10) {
            hours = "0" + hours;
        }
        if (minutes < 10) {
            minutes = "0" + minutes;
        }
        if (seconds < 10) {
            seconds = "0" + seconds;
        }
        var newDate = month + "月" + date + "日" + "&nbsp" + hours + ":" + minutes + ":" + seconds;
        $('#show-time').html(newDate);
    }, 1000);
    //picker-calendar-day

    /**
     * 微信定位
     */
    $.ajax({
        url:"/internshipmgn/wx/config",
        type:"GET",
        async:false,
        data:{url:location.href},
        dataType:"json",
        success:function (data) {
            console.log(data.data);
            //微信验证
            wx.config({
                appId:'wxb4a99366ee403d22',
                timestamp:data.data.timestamp,
                nonceStr:data.data.noncestr,
                signature:data.data.signature,
                jsApiList:[
                    "checkJsApi",
                    "getLocation"
                ]
            });
            wx.checkJsApi({
                jsApiList: [
                    'getLocation'
                ],
                success: function (res) {
                    if (res.checkResult.getLocation == false) {
                        $.alert('你的微信版本太低，不支持微信JS接口，请升级到最新的微信版本！');
                        return;
                    }
                }
            });
            //微信验证完成调用
            wx.ready(function () {
                wx.getLocation({
                    success:function (rs) {
                        latitude = rs.latitude; //维度
                        longitude = rs.longitude; //经度
                        /**
                         * 点击打卡按钮
                         */
                        $('#work-status-change-btn').click(function () {
                            var posForm = new FormData();
                            posForm.append("latitude",latitude);
                            posForm.append("longitude",longitude);
                            if (!isGoToWork){
                                posForm.append("id",lastID);
                            }
                            $.ajax({
                                url:"/internshipmgn/wx/attendances/default",
                                type:"POST",
                                data:posForm,
                                processData:false,
                                contentType:false,
                                success:function (d) {
                                    if (d.code === 200){
                                        getTodayRecord(true,false);
                                        $.toptip('打卡成功','success');
                                    }else {
                                        if (d.msg == "501"){
                                            $.confirm({
                                                text: '不在考勤范围，确定要打外勤卡吗？',
                                                onOK:function () {
                                                    $.ajax({
                                                        url: "/internshipmgn/wx/attendances/outside",
                                                        type: "POST",
                                                        data: posForm,
                                                        processData: false,
                                                        contentType: false,
                                                        success:function (d) {
                                                            if (d.code === 200){
                                                                getTodayRecord(true,false);
                                                                $.toptip('打卡成功','success');
                                                            }else {
                                                                console.log(d.code + ":" + d.msg);
                                                                $.toptip("打卡失败", "warning");
                                                            }
                                                        },
                                                        error:function (res) {
                                                            console.log(res);
                                                            $.toptip("系统繁忙,请稍后再试",'error');
                                                        }
                                                    });
                                                }
                                            })
                                        }else {
                                            console.log(d.code + ":" + d.msg);
                                            $.toptip("打卡失败", "warning");
                                        }
                                    }
                                },
                                error:function (res) {
                                    console.log(res);
                                    $.toptip("系统繁忙,请稍后再试",'error');
                                }
                            });
                        });
                    },
                    cancel:function (rs) {
                        $.alert("您拒绝提供位置信息");
                    }
                })
            });
        }
    });

    /**
     * 请求当日打卡记录
     */
    getTodayRecord(false,true);

    /**
     * 设置考勤统计的年月
     */
    $('#tj-top-time').val(YMDate);
    var calendar = new datePicker();
    calendar.init({
        'trigger': '#tj-top-time', /*按钮选择器，用于触发弹出插件*/
        'type': 'ym',/*模式：date日期；datetime日期时间；time时间；ym年月；*/
        'minDate':'1900-1-1',/*最小日期*/
        'maxDate':'2100-12-31',/*最大日期*/
        'onSubmit':function(){/*确认时触发事件*/
            var theSelectData=calendar.value;
            getThisMouthStatistic(getCHDate(theSelectData));
        },
        'onClose':function(){/*取消时触发事件*/
        }
    });

    /**
     * 获取考勤统计(每次点击统计请求一次)
     */
    $('#tj').click(function () {
        getThisMouthStatistic(getCHDate(YMDate));
    });
});

function getTodayRecord(isAsync,isNeedName) {
    $.ajax({
        url:'/internshipmgn/wx/attendances/record',
        type:'GET',
        async:isAsync,
        data:{},
        success:function (d) {
            if (d.code === 200){
                var attendances = d.data.attendances;
                if (isNeedName){
                    $('#first-username,#second-username').html(d.data.username);
                    $('#first-userimg,#second-userimg').attr("src", d.data.logo);

                }
                $('.first-dk-body').handlebars($('#dk-model'), attendances);
                var scrollTop = $(".first-dk-body")[0].scrollHeight;
                $(".first-dk-body").scrollTop(scrollTop);
                if(d.data.flag == 0){
                    isGoToWork = true;
                    $('#show-status').html('上班打卡');
                }else {
                    isGoToWork = false;
                    $('#show-status').html('下班打卡');
                }
                if(attendances.length > 0){
                    lastID = attendances.pop().id;
                }
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
}

/**
 * 获取考勤统计
 */
function getThisMouthStatistic(mouth) {
    $.ajax({
        url:'/internshipmgn/wx/statistics/statistic',
        type:'GET',
        data:{pattern: mouth},
        success:function (d) {
            if (d.code === 200){
                $('.tj-list').handlebars($('#tj-model'),d.data);
            }else {
                console.log(d.code + ":" + d.msg);
                $.toptip('无法获取本月考勤数据','warning');
            }
        },
        error:function () {
            console.log(res);
            setAlert("系统繁忙,请稍后再试");
        }
    });
}

function getCHDate(str) {
    if (str){
        var date = str.split('-');
        return date[0]+'年'+date[1]+'月';
    }else {
        var dateObj = new Date();
        var year = dateObj.getFullYear();
        var month = dateObj.getMonth() + 1;
        if (month < 10) {
            month = "0" + month;
        }
        var newDate = year + "-" + month;
        return newDate;
    }
}