$(document).ready(function () {
    //获取公司id
    var company_id = $.parseURL(location.href)['id'];

    //第二导航栏
    $('.guide-bar li').click(function () {
        var id = $(this).attr('id');
        if (id == 'info1') {
            $('#info1').css({
                'border-bottom': '3px solid rgb(0, 140, 255)',
                'color': 'rgb(0, 140, 255)',
            });
            $('#info2').css({
                'border-bottom': 'none',
                'color': 'black',
            });
            $('.recruit-info-body').css({
                'display': 'none'
            });
            $('.company-info-body').css({
                'display': 'block'
            });
        } else if (id == 'info2') {
            $('#info2').css({
                'border-bottom': '3px solid rgb(0, 140, 255)',
                'color': 'rgb(0, 140, 255)',
            });
            $('#info1').css({
                'border-bottom': 'none',
                'color': 'black',
            });
            $('.company-info-body').css({
                'display': 'none'
            });
            $('.recruit-info-body').css({
                'display': 'block'
            });
        }
    });

    /**
     * 请求企业信息页面数据
     */
    var option = getBASEGETAJAX();
    option.url = '../student/companies/company';
    option.data = {
        id: company_id
    }
    option.success = function (data) {
        if (data.code === 200) {
            $('#company-intro-v').handlebars($('#company-intro-model'), data.data.general);
            $('.photo-album>p').handlebars($('#photo-album-model'), data.data.images);
            $('.company-discription').html(data.data.info);
            $('.label-body').handlebars($('#label-model'), data.data.marks);
            var location_point = data.data.location.lnglat.split('#');
            /**
             * 腾讯地图
             */
            var center = new qq.maps.LatLng(location_point[0], location_point[1]);
            var map = new qq.maps.Map(document.getElementById("map-container"), {
                center: center,
                zoom: 16
            });
            var marker = new qq.maps.Marker({
                position: center,
                map: map
            });
            marker.setVisible(true);
            var anchor = new qq.maps.Point(5, 20),
                size = new qq.maps.Size(12, 19),
                origin = new qq.maps.Point(0, 0),
                icon = new qq.maps.MarkerImage(
                    "../assets/images/location_point.png",
                    size,
                    origin,
                    anchor
                );
            marker.setIcon(icon);
            var info = new qq.maps.InfoWindow({
                map: map
            });
            info.open();
            info.setContent('<div style="text-align:center;' +
                'margin:10px;width:100px">' + data.data.location.address + '</div>');
            info.setPosition(marker.getPosition());
            qq.maps.event.addListener(marker, 'click', function () {
                info.open();
                info.setContent('<div style="text-align:center;' +
                    'margin:10px;width:100px">' + data.data.location.address + '</div>');
                info.setPosition(marker.getPosition());
            });
        } else {
            console.log(data.code + ":" + data.msg);
            setAlert("系统繁忙，请稍后再试");
        }
    }
    option.error = function (res) {
        console.log(res);
        setAlert("系统繁忙，请稍后再试");
    }
    $.ajax(option);

    /**
     * 请求企业招聘岗位页面数据
     */
    getPostData(company_id, 1, pageLimit);

    /**
     * 点击已关注按钮取消关注
     */
    $('.post-list').on('click', '.post-focused', function () {
        var id = $(this).data('id');
        var option = getBASEDELETEAJAX();
        option.url = '../student/collections/collection?id=' + id;
        option.success = function (data) {
            if (data.code !== 200) {
                alert(data.msg);
                return;
            } else {
                var focus_btn = $('.post-focused[data-id="' + id + '"]');
                focus_btn.html('关注');
                focus_btn.removeClass('post-focused');
                focus_btn.addClass('post-focus');
                setAlert("已取消关注");
            }
        }
        option.error = function (res) {
            setAlert("系统繁忙，请稍后再试");
            console.log(res)
        }
        $.ajax(option);
    });

    /**
     * 点击关注按钮关注
     */
    $('.post-list').on('click', '.post-focus', function () {
        var id = $(this).data('id');
        var option = getBASEPUTAJAX();
        option.url = '../student/collections/collection';
        option.data = {
            'id': id
        };
        option.success = function (data) {
            if (data.code !== 200) {
                alert(data.msg);
                return;
            } else {
                var focus_btn = $('.post-focus[data-id="' + id + '"]');
                focus_btn.html('已关注');
                focus_btn.removeClass('post-focus');
                focus_btn.addClass('post-focused');
                setAlert("关注成功");
            }
        }
        option.error = function (res) {
            setAlert("系统繁忙，请稍后再试");
            console.log(res)
        }
        $.ajax(option);
    });

    /**
     * 点击投递简历按钮投简历
     */
    $('.post-list').on('click', '.hand-in-resume', function () {
        var id = $(this).data('id');
        // var option = getBASEPOSTAJAX();
        var option = getBASEGETAJAX(); //暂时
        option.url = '../student/recruitments/resume';
        option.data = {
            'recruitmentId': id
        };
        option.success = function (data) {
            if (data.code !== 200) {
                console.log(data.msg);
                setAlert("系统繁忙，请稍后再试");
                return;
            } else {
                var tag = $('.hand-in-resume[data-id="' + id + '"]');
                tag.html('已投递');
                tag.removeClass('hand-in-resume');
                tag.addClass('handed-in-resume');
                setAlert("简历已投递");
            }
        }
        option.error = function (res) {
            setAlert("系统繁忙，请稍后再试");
            console.log(res)
        }
        $.ajax(option);
    });
});

function getPostData(company_id, pageNum, pageLimit) {
    console.log(pageNum);
    var option = getBASEGETAJAX();
    option.url = '../student/companies/recruitment';
    option.data = {
        'id': company_id,
        'pageNum': pageNum
    }
    option.success = function (data) {
        if (data.code === 200) {
            $('.post-list').handlebars($('#post-item-model'), data.data.recruitments, {
                name: "if_status",
                callback: function (status, options) {
                    if (status === 1) {
                        return options.fn(this);
                    } else if (status === 0) {
                        return options.inverse(this);
                    }
                }
            });

            if (pageLimit) {
                pageLimit(pageNum, data.data.totalPage, 5, function (page) {
                    getPostData(company_id, page);
                });
            }
        } else {
            console.log(data.code + ":" + data.msg);
            setAlert("系统繁忙，请稍后再试");
        }
    }
    option.error = function (res) {
        console.log(res);
        setAlert("系统繁忙，请稍后再试");
    }
    $.ajax(option);
}