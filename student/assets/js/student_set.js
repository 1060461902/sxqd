// var USE_ORIGIN_NATIVE_PLACE = 0; //使用原来的地址
// var USE_CHANGED_NATIVE_PLACE = 1; //使用用户修改的地址
// var nativePlaveUseStatus = USE_ORIGIN_NATIVE_PLACE; //默认使用原来的地址
var isProjectAdd = true;
var isClubAdd = true;
var isHonorAdd = true;

$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "../student/studentsets/set",
        data: {},
        success: function (d) {
            if (d.code === 200) {
                /**
                 * 基础信息
                 */
                var info = d.data.info;
                $('.basic-info-entity').handlebars($('#basic-info-model'), info, image_tool);
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
                $('.project-info-list').handlebars($('#project-info-model'), project);

                /**
                 * 社团经历 
                 */
                var club = d.data.club;
                $('.corporation-info-list').handlebars($('#corporation-info-model'), club);

                /**
                 * 所获荣誉
                 */
                var honor = d.data.honor;
                $('.honor-info-list').handlebars($('#honor-info-model'), honor,image_tool);

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
            var skillForm = new FormData();
            skillForm.append('skill',skill);
            var option = getBASEPOSTAJAX();
            option.url = '../student/studentsets/skill';
            option.data = skillForm;
            option.processData = false;
            option.contentType = false;
            option.success = function (d) {
                if (d.code === 200){
                    reflashSkill(function () {
                        $('.editable-lable>input').val('');
                        if ($('.edited-lables').children('.edited-lable').length >= 10) {
                            $('.editable-lable').css({
                                'display': 'none'
                            });
                        }
                    });
                }else {
                    console.log(d.code + ":" + d.msg);
                    setAlert("系统繁忙,请稍后再试");
                }
            }
            option.error = function (res) {
                console.log(res);
                setAlert("系统繁忙,请稍后再试");
            }
            $.ajax(option);
        }
    });

    /**
     * 点击edited-lable的关闭按钮
     */
    $('.edited-lables').on('click', '.edited-lable>a', function () {
        var id = $(this).data('id');
        var option = getBASEDELETEAJAX();
        option.url = '../student/studentsets/skill?id='+id;
        option.success = function (d) {
            if (d.code === 200){
                reflashSkill(function () {
                    if ($('.edited-lables').children('.edited-lable').length < 10) {
                        $('.editable-lable').css({
                            'display': 'inline-block'
                        });
                    }
                });
            }else {
                console.log(d.code + ":" + d.msg);
                setAlert("系统繁忙,请稍后再试");
            }
        }
        option.error = function (res) {
            console.log(res);
            setAlert("系统繁忙,请稍后再试");
        }
        $.ajax(option);
    });

    /**
     * 点击X按钮清空技能输入框
     */
    $('.editable-lable>a').click(function () {
        $('.editable-lable>input').val('')
    });

    /**
     * 点击上传简历附件
     * */
    $('.upload-btn').click(function () {
        $('#attachment-upload').trigger("click");
    });
    $('#attachment-upload').change(function () {
        var form = new FormData();
        var attachment = $('#attachment-upload')[0].files[0];
        form.append('attachment',attachment);

        var option = getBASEPOSTAJAX();
        option.url = "../student/studentsets/attachment";
        option.data = form;
        option.processData = false;
        option.contentType = false;
        option.success = function (data) {
            if (data.code === 200) {
                setAlert("上传成功");
            } else {
                console.log(data.code+":"+data.msg);
                setAlert("上传失败，请稍后再试");
            }
        }
        option.error = function (res) {
            setAlert("系统繁忙，请稍后再试");
            console.log(res);
        }
        $.ajax(option);
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
        var reg = new RegExp("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$");

        if (phone_edit != ''&&phone_edit != null&&phone_edit.length != 11){
            setAlert('请检查您的手机号码');
        } else if (email_edit != '' && email_edit != null && !reg.test(email_edit)) {
            setAlert('请检查您的邮箱')
        } else {
            var option = getBASEPOSTAJAX();
            option.url = "../student/studentsets/set";
            option.data = form;
            option.processData = false;
            option.contentType = false;
            option.success = function (d) {
                if (d.code === 200) {
                    $.ajax({
                        type: "GET",
                        url: "../student/studentsets/set",
                        data: {},
                        success: function (d) {
                            if (d.code === 200) {
                                /**
                                 * 基础信息
                                 */
                                var info = d.data.info;
                                $('.basic-info-entity').handlebars($('#basic-info-model'), info, image_tool);
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
        }
    });

    // $('#project-info-group,#corporation-info-group,#honor-info-group').hide();

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
    });

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
            var option = getBASEPOSTAJAX();
            option.url = "../student/studentsets/project";
            var projectForm = new FormData();
            projectForm.append("projectName", proname);
            projectForm.append("identity", proindentity);
            projectForm.append("startTime", prostart);
            projectForm.append("endTime", proend);
            projectForm.append("instruction", prointro);
            if (!isProjectAdd) {
                var id = $('.edit-project').data('id');
                // var studentId = $('.edit-project').data('studentid');
                projectForm.append('id',id)
                $('.edit-project').attr('data-id', '');
                // $('.edit-project').attr('data-studentid', '');
            }
            option.data = projectForm;
            option.processData = false;
            option.contentType = false;
            option.success = function (d) {
                if (d.code === 200) {
                    reflashProjct();
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
     * 点击项目删除按钮
     */
    $('.project-info-list').on('click', '.project-delete', function () {
        var id = $(this).data('id');
        var option = getBASEDELETEAJAX();
        option.url = '../student/studentsets/project?id='+id;
        option.success = function (d) {
            if (d.code === 200) {
                reflashProjct();
                setAlert('删除成功');
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
    });

    /**
     * 社团经历描述字数限制
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
     * 点击社团经历添加按钮
     * */
    $('.add-corporation').click(function () {
        $('#edit-corporation-name input').val('');
        $('#edit-corporation-role input').val('');
        $('#edit-corporation-start input').val('');
        $('#edit-corporation-end input').val('');
        $('#edit-corporation-description textarea').val('');
        var id = $('.edit-corporation').attr('data-id', '');
        var studentId = $('.edit-corporation').attr('data-studentid', '');
        isClubAdd = true;
        $('.mask').fadeIn();
        $('.edit-corporation').fadeIn();
    });

    /**
     * 社团经历编辑确认按钮
     * */
    $('.corporation-info-confirm').click(function () {
        var clubname = $('#edit-corporation-name input').val();
        var clubindentity = $('#edit-corporation-role input').val();
        var clubstart = $('#edit-corporation-start input').val();
        var clubend = $('#edit-corporation-end input').val();
        var clubintro = $('#edit-corporation-description textarea').val();
        if (clubname == '' || clubname == null) {
            setAlert('请填写社团名称');
        } else if (clubindentity == '' || clubindentity == null) {
            setAlert('请填写担任职位');
        } else if (clubstart == '' || clubstart == null) {
            setAlert('请填写开始时间');
        } else if (clubend == '' || clubend == null) {
            setAlert('请填写结束时间');
        } else if (clubintro == '' || clubintro == null) {
            setAlert('请填写经历介绍');
        } else {
            var option = getBASEPOSTAJAX();
            option.url = "../student/studentsets/club";
            var clubForm = new FormData();
            clubForm.append("clubName", clubname);
            clubForm.append("indentity", clubindentity);
            clubForm.append("startTime", clubstart);
            clubForm.append("endTime", clubend);
            clubForm.append("instruction", clubintro);
            if (!isClubAdd) {
                var id = $('.edit-corporation').data('id');
                // var studentId = $('.edit-corporation').data('studentid');
                clubForm.append("id",id);
                $('.edit-corporation').attr('data-id', '');
            }
            option.data = clubForm;
            option.processData = false;
            option.contentType = false;
            option.success = function (d) {
                if (d.code === 200) {
                    reflashClub();
                    $('.mask').fadeOut();
                    $('.edit-corporation').fadeOut();
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
     * 点击社团修改按钮
     */
    $('.corporation-info-list').on('click', '.corporation-edit', function () {
        var parent = $(this).parent().parent();
        var id = $(this).data('id');
        // var studentId = $(this).data('studentid');
        $('.edit-corporation').attr('data-id', id);
        // $('.edit-project').attr('data-studentid', studentId);
        $('#edit-corporation-name input').val(parent.find('.corporation-info-name').html());
        $('#edit-corporation-role input').val(parent.find('.corporation-info-role').html());
        $('#edit-corporation-start input').val(parent.find('.corporation-info-start').html());
        $('#edit-corporation-end input').val(parent.find('.corporation-info-end').html());
        $('#edit-corporation-description textarea').val(parent.find('.corporation-info-introduction').html());
        isClubAdd = false;
        $('.mask').fadeIn();
        $('.edit-corporation').fadeIn();
    });

    /**
     * 点击经历删除按钮
     */
    $('.corporation-info-list').on('click', '.corporation-delete', function () {
        var id = $(this).data('id');
        var option = getBASEDELETEAJAX();
        option.url = '../student/studentsets/club?id='+id;
        option.success = function (d) {
            if (d.code === 200) {
                reflashClub();
                setAlert('删除成功');
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
    });

    /**
     * 所获荣誉添加按钮
     * */
    $('.add-honor').click(function () {
        $('#edit-honor-name input').val('');
        $('#edit-honor-time input').val('');
        $('#edit-honor-description textarea').val('');
        $('#edit-honor-img input').val('');
        var id = $('.edit-honor').attr('data-id', '');
        $('#honor-upload-btn').css({'display':'inline-block'});
        $('#honor-img-show').css({'display':'none'});
        $('#honor-img-show').attr('src','');
        // var studentId = $('.edit-honor').attr('data-studentid', '');
        isHonorAdd = true;
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
    $('#honor-upload-btn, #honor-img-show').click(function () {
        $('#honor-upload').trigger("click");
    });

    /**
     * 预览荣誉证书
     */
    $('#honor-upload').change(function () {
        $('#honor-upload-btn').css({'display':'none'});
        $('#honor-img-show').css({'display':'inline-block'});
        proviewImg($('#honor-upload')[0].files[0],$('#honor-img-show'));
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
        var honorname = $('#edit-honor-name input').val();
        var honortime = $('#edit-honor-time input').val();
        var honorintro = $('#edit-honor-description textarea').val();
        var honorimg = $('#honor-upload')[0].files[0];
        if (honorname == '' || honorname == null) {
            setAlert('请填写荣誉名称');
        } else if (honortime == '' || honortime == null) {
            setAlert('请填写获奖时间');
        } else if (honorintro == '' || honorintro == null) {
            setAlert('请填写获奖说明');
        } else {
            var option = getBASEPOSTAJAX();
            option.url = "../student/studentsets/honor";
            var honorForm = new FormData();
            honorForm.append("name", honorname);
            honorForm.append("time", honortime);
            honorForm.append("instruction", honorintro);
            if(honorimg != null && honorimg != '') {
                honorForm.append("image", honorimg);
            }
            if (!isHonorAdd) {
                var id = $('.edit-honor').data('id');
                // var studentId = $('.edit-project').data('studentid');
                honorForm.append('id',id)
                $('.edit-honor').attr('data-id', '');
                // $('.edit-project').attr('data-studentid', '');
            }
            option.data = honorForm;
            option.processData = false;
            option.contentType = false;
            option.success = function (d) {
                if (d.code === 200) {
                    reflashHonor();
                    $('.mask').fadeOut();
                    $('.edit-honor').fadeOut();
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
     * 点击荣誉修改按钮
     */
    $('.honor-info-list').on('click', '.honor-edit', function () {
        var parent = $(this).parent().parent();
        var id = $(this).data('id');
        // var studentId = $(this).data('studentid');
        $('.edit-honor').attr('data-id', id);
        // $('.edit-project').attr('data-studentid', studentId);
        $('#edit-honor-name input').val(parent.find('.honor-info-name').html());
        $('#edit-honor-time input').val(parent.find('.honor-info-time').html());
        $('#edit-honor-description textarea').val(parent.find('.honor-info-introduction').html());
        var img_url = parent.find('.honor-info-img').attr('src');
        if (img_url != './assets/images/not_found.jpg'){
            $('#honor-upload-btn').css({'display':'none'});
            $('#honor-img-show').css({'display':'inline-block'});
            $('#honor-img-show').attr('src',img_url);
        }else {
            $('#honor-upload-btn').css({'display':'inline-block'});
            $('#honor-img-show').css({'display':'none'});
        }
        isHonorAdd = false;
        $('.mask').fadeIn();
        $('.edit-honor').fadeIn();
    });

    /**
     * 点击荣誉删除按钮
     */
    $('.honor-info-list').on('click', '.honor-delete', function () {
        var id = $(this).data('id');
        var option = getBASEDELETEAJAX();
        option.url = '../student/studentsets/honor?id='+id;
        option.success = function (d) {
            if (d.code === 200) {
                reflashHonor();
                setAlert('删除成功');
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
    if ($(document).scrollTop() > 550) {
        $(".guide-bar").css({
            "position": "fixed",
            "top": 0
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

/**
 * 刷新项目列表
 */
function reflashProjct() {
    $.ajax({
        type: "GET",
        url: "../student/studentsets/set",
        data: {},
        success: function (d) {
            if (d.code === 200) {
                /**
                 * 项目经历
                 */
                var project = d.data.project;
                $('.project-info-list').handlebars($('#project-info-model'), project);
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
}

/**
 * 刷新社团经历列表
 */
function reflashClub() {
    $.ajax({
        type: "GET",
        url: "../student/studentsets/set",
        data: {},
        success: function (d) {
            if (d.code === 200) {
                /**
                 * 社团经历 
                 */
                var club = d.data.club;
                $('.corporation-info-list').handlebars($('#corporation-info-model'), club);
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
}

/**
 * 刷新所获荣誉列表
 */
function reflashHonor() {
    $.ajax({
        type: "GET",
        url: "../student/studentsets/set",
        data: {},
        success: function (d) {
            if (d.code === 200) {
                /**
                 * 所获荣誉
                 */
                var honor = d.data.honor;
                $('.honor-info-list').handlebars($('#honor-info-model'), honor, image_tool);
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
}

/**
 * 刷新技能列表
 */
function reflashSkill(callback) {
    $.ajax({
        type: "GET",
        url: "../student/studentsets/set",
        data: {},
        success: function (d) {
            if (d.code === 200) {
                /**
                 * 技能水平
                 */
                var skill = d.data.skill;
                $('.edited-lables').handlebars($('#skill-info-model'), skill);
                if (callback){
                    callback();
                }
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
}