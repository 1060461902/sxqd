var mescroll;

$(document).ready(function () {
    //获取公司id
    var company_id = $.parseURL(location.href)['id'];

    /**
     * 适应不同手机，自动调整上下窗格高度
     */
    var screen_height = document.documentElement.clientHeight;
    var top_height = document.getElementById("company-top").offsetHeight;
    $('.bottom-info').css({
        'overflow': 'scroll',
        'height': (screen_height - top_height)
    });
    $('#mescroll').height(screen_height - top_height - 50);
    $('#mescroll').css({
        'overflow': 'scroll'
    });

    /**
     * 切换企业主页和在招岗位
     */
    $('.company-switch li').click(function () {
        var id_name = $(this).attr('id');
        switch (id_name) {
            case 'index-btn':
                $('#index-btn').addClass('company-switch-selected');
                $('#post-btn').removeClass('company-switch-selected');
                $('.company-post').css({
                    'display': 'none'
                });
                $('.company-index').css({
                    'display': 'block'
                });
                $('.bottom-info').css({
                    'overflow': 'scroll'
                });
                mescroll.destroy();
                break;
            case 'post-btn':
                $('#post-btn').addClass('company-switch-selected');
                $('#index-btn').removeClass('company-switch-selected');
                $('.company-index').css({
                    'display': 'none'
                });
                $('.company-post').css({
                    'display': 'block'
                });
                $('.bottom-info').css({
                    'overflow': 'hidden'
                });
                mescroll = new MeScroll("mescroll", {
                    up: {
                        auto: true,
                        isBounce: false,
                        clearEmptyId: "dataList",
                        page: {
                            num: 0,
                            size:1
                        },
                        loadFull: {
                            use: true
                        },
                        callback: function (page) {
                            $.ajax({
                                type: 'GET',
                                url: '../student/companies/recruitment',
                                data: {
                                    pageNum: page.num,
                                    id:company_id
                                },
                                success: function (data) {
                                    if (data.code === 200) {
                                        mescroll.endSuccess(data.data.recruitments.length, page.num == data.data.totalPage ? false : true);
                                        var template = Handlebars.compile($('#company-post-model').html());
                                        /*Handlebars.registerHelper('if_bool', function (flag, options) {
                                            if (flag === 1) {
                                                return options.fn(this);
                                            } else if (flag === 0) {
                                                return options.inverse(this);
                                            }
                                        });*/
                                        var html = template(data.data.recruitments);
                                        $('.company-post-list').append(html);
                                    } else {
                                        mescroll.endErr();
                                        console.log(data.msg);
                                        $.toptip("系统繁忙，请稍后再试", 'error');
                                    }
                                },
                                error: function (res) {
                                    //联网失败的回调
                                    mescroll.endErr();
                                    $.toptip("系统繁忙，请稍后再试", 'error');
                                    console.log(res);
                                }
                            });

                        },
                        toTop: {
                            src: "./assets/images/mescroll-totop.png",
                            offset: 1000
                        }
                    }
                });
                break;
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
            $('.company-info-v').handlebars($('#company-info-model'), data.data.general);
            $('.company-album-list>p').handlebars($('#company-album-model'), data.data.images);
            $('.company-introduction-entity').html(data.data.info);
            $('.company-lable-list').handlebars($('#company-lable-model'), data.data.marks);
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


});