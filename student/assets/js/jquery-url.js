/**
 * @author xujuncong
 */
(function ($) {
    /**
     * jquery解析url中的param，参数格式错误不影响解析
     * 
     * @param [string] url [需要解析的url字符串]
     * 
     * @return [dict] [返回字典] 
     */
    $.parseURL = function (url) {
        url = url.split('?')[1] || '';
        var params = url.split('&');
        var res = {};
        params.forEach(param => {
            var arr = param.split('=');
            var key = arr[0] || '';
            var value = arr[1] || '';
            res[key] = value;
        });
        return res;
    }
})(jQuery)