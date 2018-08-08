// var USE_ORIGIN_NATIVE_PLACE = 0; //使用原来的地址
// var USE_CHANGED_NATIVE_PLACE = 1; //使用用户修改的地址
// var nativePlaveUseStatus = USE_ORIGIN_NATIVE_PLACE; //默认使用原来的地址
var isProjectAdd = true;

$(document).ready(function () {
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

                /**
                 * 信息读取完成后计算百分比
                 */
                persent();
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
     * 阻止editable-lable点击事件的派发
     */
    $('.editable-lable').click(function (e) {
        e.stopPropagation();
    });

    /**
     * 点击skill-info-tab空白处
     */
    $('body').click(function () {
        if ($('.editable-lable>input').val() !== '' && $('.editable-lable>input').val() !== null) {
            var skill = $('.editable-lable>input').val();
            $('.edited-lables').append('<div class="edited-lable">' +
                '<span>' + skill + '</span>' +
                '<a>X</a>' +
                '</div>');
            $('.editable-lable>input').val('')
            if ($('.edited-lables').children('.edited-lable').length >= 10) {
                $('.editable-lable').css({
                    'display': 'none'
                });
            }
        }
    });

    /**
     * 点击edited-lable的关闭按钮
     */
    $('.edited-lables').on('click', '.edited-lable>a', function () {
        $(this).parent().remove();
        if ($('.edited-lables').children('.edited-lable').length < 10) {
            $('.editable-lable').css({
                'display': 'inline-block'
            });
        }
    });

    /**
     * 点击X按钮清空技能输入框
     */
    $('.editable-lable>a').click(function () {
        $('.editable-lable>input').val('')
    });

    /**
     * 点击上传建立附件
     * */
    $('.upload-btn').click(function () {
        $('#attachment-upload').trigger("click");
    });

    /**
     * 预览按钮
     * */
    $('#preview-btn').click(function () {
        window.location.href = "./student_set_view.html";
    });

    /**
     * editor中的取消按钮
     * */
    $('.cancel-button').click(function () {
        $('.mask').fadeOut();
        $('.editor').fadeOut();
    });

    /**
     * 数据库中存储的籍贯
     * */
    /*
    $('#origin-native-place').html("浙江省杭州市江干区");
    */

    /**
     * 籍贯选择相关,定义默认选项
     * */
    /*
    $('#native-place').distpicker('destroy');
    $('#native-place').distpicker('distpick');
    */

    /**
     * 籍贯的修改按钮
     * */
    /*$('#change-place-btn').click(function () {
        $('#origin-native-place').css({
            'display': 'none'
        });
        $('#change-place-btn').css({
            'display': 'none'
        });
        $('#native-place-select').css({
            'display': 'inline'
        });
        nativePlaveUseStatus = USE_CHANGED_NATIVE_PLACE;
    });*/

    /**
     * 基本信息编辑按钮
     * */
    $('body').on("click", "#edit-basic-info", function () {
        $("#phone-number").val($('.info-phone-number').html());
        $("#student-email").val($('.info-email').html());
        var head_img_load = $('.head-img-block img').attr('src');
        if (head_img_load != null && head_img_load != '') {
            $('#head-upload-btn').css({
                'display': 'none'
            });
            $('#head-img-prov').attr('src', head_img_load);
            $('#head-img-prov').css({
                'display': 'inline-block'
            });
        }
        $('.mask').fadeIn();
        $('.edit-position').fadeIn();
    });

    /**
     * 头像上传按钮
     */
    $('#head-img-prov,#head-upload-btn').click(function () {
        $('#head-upload').click();
    });

    /**
     * 头像预览
     */
    $('#head-upload').change(function () {
        proviewImg($('#head-upload')[0].files[0], $('#head-img-prov'));
    });

    /**
     * 基本信息编辑确认按钮
     * */
    $('.basic-info-confirm').click(function () {
        // var native_place = "";
        // if (nativePlaveUseStatus == USE_ORIGIN_NATIVE_PLACE) {
        //     native_place = $('#origin-native-place').html();
        // } else {
        //     $('#native-place-select select option:selected').each(function () {
        //         native_place += $(this).val();
        //     })
        // }
        var phone_edit = $('#phone-number').val();
        var email_edit = $('#student-email').val();
        var head_img = $('#head-upload')[0].files[0];
        var form = new FormData();
        if (head_img != null && head_img != '') {
            form.append('logo', head_img);
        }
        form.append('phone', phone_edit);
        form.append('email', email_edit);

        // var option = getBASEPOSTAJAX();
        // option.url = "../student/studentset/set";
        var option = getBASEGETAJAX();
        option.url = './json/set/set.json';
        option.data = form;
        option.processData = false;
        option.contentType = false;
        option.success = function (d) {
            d = d.return; //暂时
            if (d.code === 200) {
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
                             * 信息读取完成后计算百分比
                             */
                            persent();
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
                setAlert("修改成功");
            } else {
                console.log(d.code + ":" + d.msg);
                setAlert("系统繁忙，请稍后再试");
            }
        };
        option.error = function (res) {
            setAlert("系统繁忙，请稍后再试");
            console.log(res);
        }
        $.ajax(option);
        $('.mask').fadeOut();
        $('.edit-position').fadeOut();
        // nativePlaveUseStatus = USE_ORIGIN_NATIVE_PLACE;
        // $('#origin-native-place').html(native_place);
        // $('#origin-native-place').css({
        //     'display': 'inline'
        // });
        // $('#change-place-btn').css({
        //     'display': 'inline'
        // });
        // $('#native-place-select').css({
        //     'display': 'none'
        // });
        setAlert("编辑成功");
    });

    $('#project-info-group,#corporation-info-group,#honor-info-group').hide();

    /**
     * 右键弹出菜单
     * */
    /*$('.updown-list').on('contextmenu', 'li', function (e) {
        e.preventDefault();
        var x = e.clientX;
        var y = e.clientY;
        $(".popup-menu").css({
            'left': x + 'px',
            'top': y + 'px'
        }).show()
    });*/

    /**
     * 项目经历下拉按钮
     * */
    $('#project-info-updown').click(function () {
        var role = $(this).data('role');
        if (role === "down") {
            $(this).val('▼');
            $(this).data('role', 'up');
            $('#project-info-group').slideUp();
        } else if (role === "up") {
            $(this).val('▲');
            $(this).data('role', 'down');
            $('#project-info-group').slideDown();
        }
    });

    /**
     * 社团经历下拉按钮
     * */
    $('#corporation-info-updown').click(function () {
        var role = $(this).data('role');
        if (role === "down") {
            $(this).val('▼');
            $(this).data('role', 'up');
            $('#corporation-info-group').slideUp();
        } else if (role === "up") {
            $(this).val('▲');
            $(this).data('role', 'down');
            $('#corporation-info-group').slideDown();
        }
    });

    /**
     * 点击项目经历添加按钮
     * */
    $('.add-project').click(function () {
        $('#edit-project-name input').val('');
        $('#edit-project-role input').val('');
        $('#edit-project-start input').val('');
        $('#edit-project-end input').val('');
        $('#edit-project-description textarea').val('');
        var id = $('.edit-project').attr('data-id', '');
        var studentId = $('.edit-project').attr('data-studentid', '');
        isProjectAdd = true;
        $('.mask').fadeIn();
        $('.edit-project').fadeIn();
    });

    /**
     * 点击项目修改按钮
     */
    $('.project-info-list').on('click', '.project-edit', function () {
        var parent = $(this).parent().parent();
        var id = $(this).data('id');
        var studentId = $(this).data('studentid');
        $('.edit-project').attr('data-id', id);
        $('.edit-project').attr('data-studentid', studentId);
        $('#edit-project-name input').val(parent.find('.project-info-name').html());
        $('#edit-project-role input').val(parent.find('.project-info-role').html());
        $('#edit-project-start input').val(parent.find('.project-info-start').html());
        $('#edit-project-end input').val(parent.find('.project-info-end').html());
        $('#edit-project-description textarea').val(parent.find('.project-info-introduction').html());
        isProjectAdd = false;
        $('.mask').fadeIn();
        $('.edit-project').fadeIn();
    })

    /**
     * 项目经历描述字数限制
     * */
    $('#edit-project-description textarea').keyup(function () {
        var max = 200;
        var str = $(this).val();
        var len = str.length;
        if (len > max) {
            $('.project-info-confirm').attr("disabled", true);
            $('.project-info-confirm').css({
                'background': '#7f7f7f'
            })
        } else {
            $('.project-info-confirm').attr("disabled", false);
            $('.project-info-confirm').css({
                'background': '#2f84f6'
            })
        }
        $('.project-limit').html(200 - len);
    });

    /**
     * 项目经历编辑确认按钮
     * */
    $('.project-info-confirm').click(function () {
        var proname = $('#edit-project-name input').val();
        var proindentity = $('#edit-project-role input').val();
        var prostart = $('#edit-project-start input').val();
        var proend = $('#edit-project-end input').val();
        var prointro = $('#edit-project-description textarea').val();
        if (proname == '' || proname == null) {
            setAlert('请填写项目名称');
        } else if (proindentity == '' || proindentity == null) {
            setAlert('请填写参与身份');
        } else if (prostart == '' || prostart == null) {
            setAlert('请填写开始时间');
        } else if (proend == '' || proend == null) {
            setAlert('请填写结束时间');
        } else if (prointro == '' || prointro == null) {
            setAlert('请填写项目介绍');
        } else {
            // var option = getBASEPOSTAJAX();
            var option = getBASEGETAJAX(); //暂时
            option.url = "./json/set/add_project.json";
            if (isProjectAdd) {
                option.data = {
                    "studentProject": {
                        "projectName": proname,
                        "identity": proindentity,
                        "startTime": prostart,
                        "endTime": proend,
                        "instruction": prointro,
                        "deleteTag": false
                    }
                }
            } else {
                var id = $('.edit-project').data('id');
                var studentId = $('.edit-project').data('studentid');
                option.data = {
                    "studentProject": {
                        "id": id,
                        "projectName": proname,
                        "identity": proindentity,
                        "startTime": prostart,
                        "endTime": proend,
                        "instruction": prointro,
                        "studentId": studentId,
                        "deleteTag": false
                    }
                }
                var id = $('.edit-project').attr('data-id', '');
                var studentId = $('.edit-project').attr('data-studentid', '');
            }
            option.success = function (d) {
                d = d.return; //暂时
                if (d.code === 200) {
                    $.ajax({
                        type: "GET",
                        url: "./json/set/get.json",
                        data: {},
                        success: function (d) {
                            d = d.return; //暂时
                            if (d.code === 200) {
                                /**
                                 * 项目经历
                                 */
                                var project = d.data.project;
                                $('.project-info-list').handlebars($('#project-info-model'), project, {
                                    name: "timehelper",
                                    callback: function (time) {
                                        return getdate(time);
                                    }
                                });
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
                    $('.mask').fadeOut();
                    $('.edit-project').fadeOut();
                    setAlert("编辑成功");
                } else {
                    console.log(d.code + ":" + d.msg);
                    setAlert("系统繁忙，请稍后再试");
                }
            }
            option.error = function (res) {
                setAlert("系统繁忙，请稍后再试");
                console.log(res);
            }
            $.ajax(option);
        }

    });

    /**
     * 点击社团经历添加按钮
     * */
    $('.add-corporation').click(function () {
        $('.mask').fadeIn();
        $('.edit-corporation').fadeIn();
    });

    /**
     * 项目经历描述字数限制
     * */
    $('#edit-corporation-description textarea').keyup(function () {
        var max = 200;
        var str = $(this).val();
        var len = str.length;
        if (len > max) {
            $('.corporation-info-confirm').attr("disabled", true);
            $('.corporation-info-confirm').css({
                'background': '#7f7f7f'
            })
        } else {
            $('.corporation-info-confirm').attr("disabled", false);
            $('.corporation-info-confirm').css({
                'background': '#2f84f6'
            })
        }
        $('.corporation-limit').html(200 - len);
    });

    /**
     * 社团经历编辑确认按钮
     * */
    $('.corporation-info-confirm').click(function () {
        $('.mask').fadeOut();
        $('.edit-corporation').fadeOut();
        setAlert("编辑成功");
    });

    /**
     * 所获荣誉添加按钮
     * */
    $('.add-honor').click(function () {
        $('.mask').fadeIn();
        $('.edit-honor').fadeIn();
    });

    /**
     * 所获荣誉下拉按钮
     * */
    $('#honor-info-updown').click(function () {
        var role = $(this).data('role');
        if (role === "down") {
            $(this).val('▼');
            $(this).data('role', 'up');
            $('#honor-info-group').slideUp();
        } else if (role === "up") {
            $(this).val('▲');
            $(this).data('role', 'down');
            $('#honor-info-group').slideDown();
        }
    });

    /**
     * 点击上传荣誉证书按钮
     * */
    $('#honor-upload-btn').click(function () {
        $('#honor-upload').trigger("click");
    });

    /**
     * 荣誉说明描述字数限制
     * */
    $('#edit-honor-description textarea').keyup(function () {
        var max = 200;
        var str = $(this).val();
        var len = str.length;
        if (len > max) {
            $('.honor-info-confirm').attr("disabled", true);
            $('.honor-info-confirm').css({
                'background': '#7f7f7f'
            })
        } else {
            $('.honor-info-confirm').attr("disabled", false);
            $('.honor-info-confirm').css({
                'background': '#2f84f6'
            })
        }
        $('.honor-limit').html(200 - len);
    });

    /**
     * 所获荣誉编辑确认按钮
     * */
    $('.honor-info-confirm').click(function () {
        $('.mask').fadeOut();
        $('.edit-honor').fadeOut();
        setAlert("编辑成功");
    });

    /**
     * 计算简历完成度
     */
    var edited_lables = document.querySelector(".edited-lables");
    var updown_list = document.querySelector(".updown-list");
    edited_lables.addEventListener('DOMSubtreeModified', persent, false);
    updown_list.addEventListener('DOMSubtreeModified', persent, false);
    $('body').on('change', '.info-phone-number,.info-email', persent);
});

$(document).scroll(function (e) {
    //侧边导航栏
    if ($(document).scrollTop() > 450) {
        $(".guide-bar").css({
            "position": "fixed",
            "top": "50px"
        });
    } else {
        $(".guide-bar").css({
            "position": "static"
        });
    }
});

/*$(document).click(function () {
    $(".popup-menu").hide();
});*/

/**
 * 计算完成度百分比
 */
function persent() {
    var persent = 0;
    if ($('.edited-lables').children().length > 0) {
        persent += 20;
    }
    if ($('.honor-info-list').children().length > 0) {
        persent += 20;
    }
    if ($('.corporation-info-list').children().length > 0) {
        persent += 20;
    }
    if ($('.project-info-list').children().length > 0) {
        persent += 20;
    }
    if ($('.info-phone-number').html() != "" && $('.info-phone-number').html() != null) {
        persent += 10;
    }
    if ($('.info-email').html() != "" && $('.info-email').html() != null) {
        persent += 10;
    }
    $('.resume-complete-percent').html(persent + '%');
    $('.progress-bar-info').attr('style', "width: " + persent + "%");
    $('.progress-bar-info').attr('aria-valuenow', persent + "");
}

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

/**
 * 图片预览
 */
function proviewImg(file, container) {
    var fileType = file.type.split("/")[0];
    if (fileType != "image") {
        setAlert("请上传图片")
        return;
    }
    var fileSize = Math.round(file.size / 1024 / 1024);
    if (fileSize >= 3) {
        setAlert("请上传小于少于3M的图片");
        return;
    }
    var reader = new FileReader();
    reader.readAsBinaryString(file);
    reader.onload = function (f) {
        var src = "data:" + file.type + ";base64," + window.btoa(this.result);
        container.attr('src', src);
    }
}