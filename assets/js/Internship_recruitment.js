var order = $('#sort-type').val();
var type = $('#company-type').val();

$(document).ready(function () {
    /**
     * 请求第一页
     */
    requestURL();

    /**
     * 点击顺序列表
     */
    $('#sort-type').on('change', function () {
        order = $(this).val();
        requestURL();
    });

    /**
     * 点击企业类型列表
     */
    $('#company-type').on('change', function () {
        type = $(this).val();
        requestURL();
    });
});

function requestURL() {
    var option = getBASEGETAJAX();
    option.url = '../student/recruitments/recruitment';
    option.data = {
        order: order,
        type: type,
        pageNum: 1
    };
    option.success = function (data) {
        if (data.code !== 200) {
            setAlert('暂时无法获取职位列表');
            console.log(data.code+":"+data.msg);
            return;
        } else {
            if (data.data.data.length>0){
                $('.list-group').handlebars($('#post-item-model'), data.data.data);
            } else {
                $('.list-group').html('<div class="list-empty-info">' +
                    '<p>暂无内容</p>' +
                    '</div>');
            }
            /**
             * 分页
             */
            pageLimit(1, data.data.totalPage, 5, function (page) {
                var option = getBASEGETAJAX();
                option.url = '../student/recruitments/recruitment';
                option.data = {
                    pageNum: page
                };
                option.success = function (data) {
                    if (data.code !== 200) {
                        setAlert('暂时无法获取职位列表');
                        console.log(data.code+":"+data.msg);
                        return;
                    } else {
                        $('.list-group').handlebars($('#post-item-model'), data.data.data);
                    }
                }
                option.error = function (res) {
                    setAlert('系统繁忙，请稍候再试');
                    console.log(res)
                }
                $.ajax(option);
            });
        }
    }
    option.error = function (res) {
        setAlert('系统繁忙，请稍候再试');
        console.log(res)
    }
    $.ajax(option);
};