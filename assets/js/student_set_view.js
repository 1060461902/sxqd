$(document).ready(function () {

    $('#project-info-group,#corporation-info-group,#honor-info-group').hide();

    $.ajax({
        type: "GET",
        url: "./json/set/get.json",
        data: {},
        success: function (d) {
            d = d.return;
            if (d.code === 200) {
                /**
                 * 基础信息
                 */
                var info = d.data.info;
                $('.basic-info-entity').handlebars($('#basic-info-model'), info);
                $("#student-name").html(info.name);
                $("#student-no").html(info.studentNum);
                $("#student-edit-nation").html(info.nation);
                $("#student-place").html(info.place); //空缺
                $("#student-birthday").html(info.birthday);
                if (info.sex === '男') {
                    $('#male-btn').css({
                        'display': 'block'
                    })
                } else {
                    $('#female-btn').css({
                        'display': 'block'
                    })
                }
                /**
                 * 项目经历
                 */
                var project = d.data.project;
                $('.project-info-list').handlebars($('#project-info-model'), project, {
                    name: "timehelper",
                    callback: function (time) {
                        time = Number(time);
                        return getdate(time);
                    }
                });

                /**
                 * 社团经历 
                 */
                var club = d.data.club;
                $('.corporation-info-list').handlebars($('#corporation-info-model'), club, {
                    name: "timehelper",
                    callback: function (time) {
                        time = Number(time);
                        return getdate(time);
                    }
                });

                /**
                 * 所获荣誉
                 */
                var honor = d.data.honor;
                $('.honor-info-list').handlebars($('#honor-info-model'), honor, {
                    name: "timehelper",
                    callback: function (time) {
                        time = Number(time);
                        return getdate(time);
                    }
                });

                /**
                 * 技能水平
                 */
                var skill = d.data.skill;
                $('.edited-lables').handlebars($('#skill-info-model'), skill);
            } else {
                console.log(d.code + ":" + d.msg);
                setAlert("系统繁忙,请稍后再试");
            }
        },
        error: function (res) {
            console.log(res);
            setAlert("系统繁忙,请稍后再试");
        }
    });

    /**
     * 项目经历下拉按钮
     * */
    $('#project-info-updown').click(function () {
        var role = $(this).data('role');
        if (role === "down"){
            $(this).val('▼');
            $(this).data('role','up');
            $('#project-info-group').slideUp();
        }else if(role === "up"){
            $(this).val('▲');
            $(this).data('role','down');
            $('#project-info-group').slideDown();
        }
    });

    /**
     * 社团经历下拉按钮
     * */
    $('#corporation-info-updown').click(function () {
        var role = $(this).data('role');
        if (role === "down"){
            $(this).val('▼');
            $(this).data('role','up');
            $('#corporation-info-group').slideUp();
        }else if(role === "up"){
            $(this).val('▲');
            $(this).data('role','down');
            $('#corporation-info-group').slideDown();
        }
    });

    /**
     * 所获荣誉下拉按钮
     * */
    $('#honor-info-updown').click(function () {
        var role = $(this).data('role');
        if (role === "down"){
            $(this).val('▼');
            $(this).data('role','up');
            $('#honor-info-group').slideUp();
        }else if(role === "up"){
            $(this).val('▲');
            $(this).data('role','down');
            $('#honor-info-group').slideDown();
        }
    });
});

/**
 * 时间戳转换
 */
function getdate(time) {
    var date = new Date(time);
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    var d = date.getDate();
    return y + "-" + (m < 10 ? "0" + m : m) + "-" + (d < 10 ? "0" + d : d);
}