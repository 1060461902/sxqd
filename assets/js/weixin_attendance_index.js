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

$(document).ready(function () {
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
                    },
                    cancel:function (rs) {
                        $.alert("您拒绝提供位置信息");
                    }
                })
            });
        }
    });

    /**
     * 请求当日记录
     */
    getTodayRecord(false,true);

    /**
     * 点击打卡按钮
     */
    $('#work-status-change-btn').click(function () {
        var posForm = new FormData();
        posForm.append("latitude",latitude);
        posForm.append("longitude",longitude);
        if (isGoToWork){
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
});

function getTodayRecord(isAsync,isNeedName) {
    $.ajax({
        url:'/internshipmgn/wx/attendances/record',
        type:'GET',
        async:isAsync,
        data:{},
        success:function (d) {
            if (d.code === 200){
                if (!isNeedName){
                    $('#first-username').html(d.data.username);
                }
                $('.first-dk-body').handlebars($('#dk-model'),d.data.attendances);
                if(d.data.flag == 0){
                    isGoToWork = true;
                    $('#show-status').html('上班打卡');
                }else {
                    $('#show-status').html('下班打卡');
                }
                lastID = d.data.attendances.pop().id;
            }else {
                isGoToWork = false;
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