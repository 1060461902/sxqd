$(document).ready(function () {
    /**
     * 访问页面请求第一页
     */
    var option = getBASEGETAJAX();
    option.url = '../student/roads/road';
    option.data = {pageNum:1};
    option.success = function (data) {
        if (data.code !== 200) {
            console.log(data.code+":"+data.msg);
            setAlert("无法获取求职之路列表");
            return;
        } else {
            var roads = data.data.data;
            if (roads.length > 0) {
                $('.list-group').handlebars($('#employ-road-model'), roads, [{
                    name: 'statusImage',
                    callback: function (actionName) {
                        switch (actionName) {
                            case '投递简历':
                                return './assets/images/history_send.png';
                            case '简历通过':
                                return './assets/images/history_pass.png';
                            case '成功录用':
                                return './assets/images/history_success.png';
                        }
                    }
                },image_tool]);
            }

            /**
             * 分页
             */
            pageLimit(1, data.data.totalPage, 5, function (page) {
                var option = getBASEGETAJAX();
                option.url = '../student/roads/road';
                option.data = {pageNum:page};
                option.success = function (data) {
                    if (data.code !== 200) {
                        setAlert("无法获取求职之路列表");
                        return;
                    } else {
                        $('.list-group').handlebars($('#employ-road-model'), data.data.data, [{
                            name: 'statusImage',
                            callback: function (actionName) {
                                switch (actionName) {
                                    case '投递简历':
                                        return './assets/images/history_send.png';
                                    case '简历通过':
                                        return './assets/images/history_pass.png';
                                    case '成功录用':
                                        return './assets/images/history_success.png';
                                }
                            }
                        },image_tool]);
                    }
                }
                option.error = function (res) {
                    console.log(res)
                }
                $.ajax(option);
            });
        }
    }
    option.error = function (res) {
        console.log(res)
    }
    $.ajax(option);
});