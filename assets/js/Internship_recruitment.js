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
            alert(data.msg);
            return;
        } else {
            $('.list-group').handlebars($('#post-item-model'), data.data.data);
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
                        alert(data.msg);
                        return;
                    } else {
                        $('.list-group').handlebars($('#post-item-model'), data.data.data);
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
};