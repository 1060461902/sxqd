var USE_ORIGIN_NATIVE_PLACE = 0; //使用原来的地址
var USE_CHANGED_NATIVE_PLACE = 1; //使用用户修改的地址
var nativePlaveUseStatus = USE_ORIGIN_NATIVE_PLACE; //默认使用原来的地址

$(document).ready(function () {

    $(document).context

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
        setConfirm("保存后可预览，确认保存？",function () {
            window.location.href = "./student_set_view.html";
        })
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
    $('#origin-native-place').html("浙江省杭州市江干区");

    /**
     * 籍贯选择相关,定义默认选项
     * */
    $('#native-place').distpicker('destroy');
    $('#native-place').distpicker('distpick');

    /**
     * 籍贯的修改按钮
     * */
    $('#change-place-btn').click(function () {
        $('#origin-native-place').css({'display':'none'});
        $('#change-place-btn').css({'display':'none'});
        $('#native-place-select').css({'display':'inline'});
        nativePlaveUseStatus = USE_CHANGED_NATIVE_PLACE;
    });

    /**
     * 基本信息编辑按钮
     * */
    $('#edit-basic-info').click(function () {
        $('.mask').fadeIn();
        $('.edit-position').fadeIn();
    });

    /**
     * 基本信息编辑确认按钮
     * */
    $('.basic-info-confirm').click(function () {
        var native_place = "";
        if (nativePlaveUseStatus==USE_ORIGIN_NATIVE_PLACE){
            native_place = $('#origin-native-place').html();
        }else{
            $('#native-place-select select option:selected').each(function () {
                native_place += $(this).val();
            })
        }
        $('.mask').fadeOut();
        $('.edit-position').fadeOut();
        nativePlaveUseStatus = USE_ORIGIN_NATIVE_PLACE;
        $('#origin-native-place').html(native_place);
        $('#origin-native-place').css({'display':'inline'});
        $('#change-place-btn').css({'display':'inline'});
        $('#native-place-select').css({'display':'none'});
        setAlert("编辑成功");
    });

    $('#project-info-group,#corporation-info-group,#honor-info-group').hide();

/*    $('.updown-list li').bind('contextmenu',function () {
        return false
    });*/

    /**
     * 右键弹出菜单
     * */
    $('.updown-list li').bind('contextmenu',function(e){
        e.preventDefault();
        var x = e.clientX;
        var y = e.clientY;
        $(".popup-menu").css({
            'left':x+'px',
            'top':y+'px'
        }).show()
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
     * 点击项目经历添加按钮
     * */
    $('.add-project').click(function () {
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
        if (len>max){
            $('.project-info-confirm').attr("disabled",true);
            $('.project-info-confirm').css({'background':'#7f7f7f'})
        }else {
            $('.project-info-confirm').attr("disabled",false);
            $('.project-info-confirm').css({'background':'#2f84f6'})
        }
        $('.project-limit').html(200-len);
    });

    /**
     * 项目经历编辑确认按钮
     * */
    $('.project-info-confirm').click(function () {
        $('.mask').fadeOut();
        $('.edit-project').fadeOut();
        setAlert("编辑成功");
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
        if (len>max){
            $('.corporation-info-confirm').attr("disabled",true);
            $('.corporation-info-confirm').css({'background':'#7f7f7f'})
        }else {
            $('.corporation-info-confirm').attr("disabled",false);
            $('.corporation-info-confirm').css({'background':'#2f84f6'})
        }
        $('.corporation-limit').html(200-len);
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

    /**
     * 荣誉说明描述字数限制
     * */
    $('#edit-honor-description textarea').keyup(function () {
        var max = 200;
        var str = $(this).val();
        var len = str.length;
        if (len>max){
            $('.honor-info-confirm').attr("disabled",true);
            $('.honor-info-confirm').css({'background':'#7f7f7f'})
        }else {
            $('.honor-info-confirm').attr("disabled",false);
            $('.honor-info-confirm').css({'background':'#2f84f6'})
        }
        $('.honor-limit').html(200-len);
    });

    /**
     * 所获荣誉编辑确认按钮
     * */
    $('.honor-info-confirm').click(function () {
        $('.mask').fadeOut();
        $('.edit-honor').fadeOut();
        setAlert("编辑成功");
    });
});

$(document).scroll(function (e) {
    //侧边导航栏
    if ($(document).scrollTop()>550){
        $(".guide-bar").css({"position":"fixed","top":"0"});
    }else {
        $(".guide-bar").css({"position":"static"});
    }
});

$(document).click(function () {
   $(".popup-menu").hide();
});