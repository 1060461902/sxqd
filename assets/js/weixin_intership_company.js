var page = 1;
var status = true;

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
        'height': (screen_height - top_height),
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
                if (status) {
                    status = false;
                    /**
                     * 请求企业招聘岗位页面数据
                     */
                    $('.company-post-loading').dropload({
                        scrollArea: $('.bottom-info'),
                        loadDownFn: function (me) {
                            $.ajax({
                                type: 'GET',
                                url: './json/company_profile_2.json',
                                data: {
                                    pageNum: page
                                },
                                success: function (data) {
                                    if (data.code === 200) {
                                        var template = Handlebars.compile($('#company-post-model').html());
                                        /*Handlebars.registerHelper('if_bool', function (flag, options) {
                                            if (flag === 1) {
                                                return options.fn(this);
                                            } else if (flag === 0) {
                                                return options.inverse(this);
                                            }
                                        });*/
                                        var html = template(data.data.data);
                                        setTimeout(function () {
                                            $('.company-post-list').append(html);
                                            page++;
                                            me.resetload();
                                        }, 1000)
                                        // $('.company-post-list').append(html);
                                        // page++;
                                        // me.resetload();
                                    } else {
                                        console.log(data.msg);
                                        $.toptip("系统繁忙，请稍后再试", 'error');
                                        me.resetload();
                                    }
                                },
                                error: function (res) {
                                    $.toptip("系统繁忙，请稍后再试", 'error');
                                    console.log(res);
                                    me.resetload();
                                }
                            });
                        }
                    });
                }
                break;
        }
    });

    /**
     * 请求企业信息页面数据
     */
    var option = getBASEGETAJAX();
    option.url = './json/company_profile_1.json';
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
             * 百度地图
             */
            var map = new BMap.Map("map-container");
            var point = new BMap.Point(location_point[0], location_point[1]);
            map.centerAndZoom(point, 15);
            var marker = new BMap.Marker(point);
            map.addOverlay(marker);
            map.addControl(new BMap.NavigationControl());
            map.addControl(new BMap.ScaleControl());
            map.addControl(new BMap.OverviewMapControl());
            var opts = {
                width: 20,
                height: 10,
                title: "企业地址"
            }
            var infoWindow = new BMap.InfoWindow(data.data.location.address, opts);
            map.openInfoWindow(infoWindow, map.getCenter());
            marker.addEventListener('click', function () {
                map.openInfoWindow(infoWindow, marker.getPosition());
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