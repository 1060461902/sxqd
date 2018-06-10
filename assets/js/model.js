/*
* 通用文件
* */
$(document).ready(function () {
    $('.confirm-cancel').click(function() {
        $('.confirm-modal,.mask').fadeOut();
    });
});

/**
 * 分页条
 * @author xujuncong
 * */
function pageLimit(currentPage,totalPages,numberOfPages) {
    $('#pageLimit').bootstrapPaginator({
        currentPage: currentPage, //当前的请求页面
        totalPages: totalPages, //一共多少页
        size: "small", //大小
        bootstrapMajorVersion: 3, //bootstrap的版本要求
        alignment: "right",
        numberOfPages: numberOfPages, //一页列出多少数据
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
        }
    });
}

/**
 *简化handlebars的操作
 * @author xujuncong
 * @param {object} obj [需要包含以下属性]
 * @param {} origin [模板对象]
 * @param {[object]} data [需要插入的数据]
 * @param {} goal [可缺省，缺省为origin，模板作用的对象]
 * @return
 * */
function HandelBarsHelper(obj) {
    if (typeof obj.origin != 'undefined'&&typeof obj.data != 'undefined'){
        if(typeof obj.goal == 'undefined'){
            obj.goal = obj.origin;
        }
        var origin = obj.origin;
        var goal = obj.goal;
        var data = obj.data;
        var template = Handlebars.compile(origin.html());
        var html = template(data);
        goal.html(html);
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
 * 根据状态在报告界面显示不同的画面
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