$(document).ready(function () {

    $('#project-info-group,#corporation-info-group,#honor-info-group').hide();

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