$(document).ready(function () {
    /**
     * 经纬度
     */
    var latitude,longitude;

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
     * 点击打卡按钮
     */
    $('#work-status-change-btn').click(function () {
        var posForm = new FormData();
        posForm.append("latitude",latitude);
        posForm.append("longitude",longitude);
        $.ajax({
            url:"/internshipmgn/wx/attendances/default",
            type:"POST",
            data:posForm,
            processData:false,
            contentType:false,
            success:function (d) {
                if (d.code === 200){
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
                                            $.toptip('打卡成功','success');
                                        }else {
                                            console.log(d.code + ":" + d.msg);
                                            $.toptip("系统繁忙，请稍后再试", "error");
                                        }
                                    },
                                    error:function (res) {
                                        console.log(res);
                                        setAlert("系统繁忙,请稍后再试");
                                    }
                                });
                            }
                        })
                    }else {
                        console.log(d.code + ":" + d.msg);
                        $.toptip("系统繁忙，请稍后再试", "error");
                    }
                }
            },
            error:function (res) {
                console.log(res);
                setAlert("系统繁忙,请稍后再试");
            }
        });
    });
});