/**
 * @author xujuncong
 * 通用文件
 * */
var BASEGETAJAX = {
    dataType:"json",
    type:"GET",
    data:{}
}

var BASEPOSTAJAX = {
    dataType:"json",
    type:"POST",
    traditional:true,
    data:{}
}

var BASEDELETEAJAX = {
    dataType:"json",
    type:"DELETE",
    traditional:true,
    data:{}
}

var BASEPUTAJAX = {
    dataType:"json",
    type:"PUT",
    traditional:true,
    data:{}
}

/**
 * 图片路径帮助对象
 * @type {{}}
 */
var image_tool = {
    name:'image_tool',
    callback:function (url) {
        return '/static/'+url;
    }
}

/**
 * 获得基础GET method ajax对象
 * @return object [基础GET method ajax对象]
 */
function getBASEGETAJAX(){
    var newObject = $.extend(true,{},BASEGETAJAX);
    return newObject;
}

/**
 * 获得基础POST method ajax对象
 * @return object [基础POST method ajax对象]
 */
function getBASEPOSTAJAX(){
    var newObject = $.extend(true,{},BASEPOSTAJAX);
    return newObject;
}

/**
 * 获得基础DELETE method ajax对象
 * @return object [基础DELETE method ajax对象]
 */
function getBASEDELETEAJAX(){
    var newObject = $.extend(true,{},BASEDELETEAJAX);
    return newObject;
}

/**
 * 获得基础PUT method ajax对象
 * @return object [基础PUT method ajax对象]
 */
function getBASEPUTAJAX(){
    var newObject = $.extend(true,{},BASEPUTAJAX);
    return newObject;
}

$(document).ready(function () {
    /**
     * 获取cookie中的用户名
     */
    $('#user-name').html(localStorage.getItem('js-name'));

    /**
     * 点击确认框的取消按钮
     * */
    $('.confirm-cancel').click(function() {
        $('.confirm-modal,.mask').fadeOut();
    });

    /**
     * 点击退出按钮退出登录
     */
    $('#exit_button').click(function () {
        var option = getBASEGETAJAX();
        option.url = '../logout';
        option.success = function (data) {
            if (data.code === 200){
                window.location.href = './log_in.html'
            }else {
                console.log(data.code+":"+data.msg);
                setAlert('系统繁忙，请稍候再试');
            }
        }
        option.error = function (res) {
            console.log(res);
            setAlert('系统繁忙，请稍候再试');
        }
        $.ajax(option);
    });
});

/**
 * 分页条
 * @param {int} currentPage [当前所需要请求的页面]
 * @param {int} totalPages [一共有多少页]
 * @param {int} numberOfPages [页码按钮个数]
 * 
 * @return
 * */
function pageLimit(currentPage,totalPages,numberOfPages,callback) {
    $('#pageLimit').bootstrapPaginator({
        currentPage: currentPage,
        totalPages: totalPages,
        size: "small", //大小
        bootstrapMajorVersion: 3, //bootstrap的版本要求
        alignment: "right",
        numberOfPages: numberOfPages,
        itemTexts: function (type, page, current) {
            switch (type) {
                case "first":
                    return "首页";
                case "prev":
                    return "上一页";
                case "next":
                    return "下一页";
                case "last":
                    return "末页";
                case "page":
                    return page;
            }
        },
        //点击事件
        onPageClicked:function (event, originalEvent, type, page) {
            if (callback){
                callback(page);
            }
        }
    });
}

/**
 * 简化handlebars的操作，此方法可脱离jquery使用(已编写jquery插件替代，操作更加简便)
 * @param {object} obj [需要包含以下属性]
 *
 * @param {element} obj.origin [模板对象]
 * @param {object} obj.data [需要插入的数据]
 * @param {element} obj.goal [可缺省，缺省为origin，模板作用的对象]
 * @param {object} obj.helper [可缺省，registerHelper相关对象]
 *
 * @param {string} obj.helper.name [需要registerHelper的模块名]
 * @param {function} obj.helper.callback [registerHelper回调函数]
 * @return
 * */
function HandelBarsHelper(obj) {
    if (typeof obj.origin != 'undefined'&&typeof obj.data != 'undefined'){
        if(typeof obj.goal == 'undefined'){
            obj.goal = obj.origin;
        }
        if(typeof obj.helper != 'undefined'){
            Handlebars.registerHelper(obj.helper.name,obj.helper.callback);
        }
        var origin = obj.origin;
        var goal = obj.goal;
        var data = obj.data;
        var template = Handlebars.compile(origin.innerHTML);
        var html = template(data);
        goal.innerHTML = html;
    }
}

/**
 * 自订弹出框
 * @param {string} str [弹出框内容]
 * @param {int} timeAlert [显示时间,缺省值为1.5秒]
 * */
function setAlert(str,timeAlert) {
    if (typeof timeAlert == "undefined"){
        timeAlert = 1500;
    }
    $('.alert-model').html(str);
    $('.alert-model').fadeIn();
    setTimeout(function () {
        $('.alert-model').fadeOut();
    },timeAlert);
}

/**
 * 自订确认框
 * @param {string} string [确认框中的文本内容]
 * @param {function} callback [点击确认后执行的回调函数]
 * */
function setConfirm(string,callback) {
    $('.confirm-text').html(string);
    $('.confirm-modal').fadeIn();
    $('.mask').fadeIn();
    $('.confirm-btn').click(function () {
        callback();
        $('.confirm-modal,.mask').fadeOut();
    });
}

/**
 * @deprecated
 * 根据状态在报告界面显示不同的画面
 * @param {int} status [状态码]
 * @param {int} day [天数]
 * */
function reportView(status,day) {
    switch (status){
        case 0: //未到填写时段
            $('.report-show-entity-left').html("未到可填写时间");
            break;
        case 1: //即将开始
            $('.report-show-entity-left').html("还有");
            $('.report-show-entity-day').html(day);
            $('.report-show-entity-right').html("天开始");
            break;
        case 2: //已开始，未填写
            $('.report-show-entity-left').html("还有");
            $('.report-show-entity-day').html(day);
            $('.report-show-entity-right').html("天截止");
            $('.report-show-edit').css({'display':'block'});
            break;
        case 3: //已开始，已填写
            $('.report-show-already').css({'display':'block'});
            $('.report-show-edit').css({'display':'block'});
            $('.report-show-entity p').css({'display':'none'});
            break;
        case 4: //已填写，已过时
            $('.report-show-already').css({'display':'block'});
            $('.report-show-entity p').css({'display':'none'});
            break;
        case 5: //错过
            $('.report-show-entity-left').html("未按时填写");
            break;
    }
}

/**
 * 顶部导航栏滑动固定
 */
/*$(document).scroll(function (e) {
    if ($(document).scrollTop()>300){
        $(".nav-up").addClass('nav-fix');
    }else {
        $(".nav-up").removeClass('nav-fix');
    }
});*/

/**
 * 处理错误
 */
document.addEventListener("error", function (e) {
    var elem = e.target;
    if (elem.tagName.toLowerCase() === 'img') {
        var notFoundImgSrt = $(elem).attr("notFoundSrc");
        if (notFoundImgSrt) {
            $.get(notFoundImgSrt, null, function (response, status, xhr) {
                if (xhr.status === 200)
                    elem.src = notFoundImgSrt;
            });
        }
    }
}, true);

/**
 * 设置cookie
 * @param name
 * @param value
 * @param days
 */
function setCookie(name, value, days) {
    var Days = 1;
    if (days) {
        var Days = days;
    }
    var exp = new Date();
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = name + "="+ value + ";expires=" + exp.toGMTString();
}

/**
 * 获取cookie的值
 * @param cname
 * @returns {string}
 */
function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i].trim();
        if (c.indexOf(name)==0) return c.substring(name.length,c.length);
    }
    return "";
}