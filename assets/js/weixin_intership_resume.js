$(document).ready(function () {
    $('.remind-swipe').fadeIn(1500);
    $('.remind-swipe').fadeOut(1500);
    /**
     * 点击返回键
     */
    $('#title-back').click(function () {
        $.confirm({
            text: '如确认保存，点击确定退出页面',
            onOK: function () {
                window.location.href = './weixin_intership_index.html?tab=3';
            }
        });
    });

    /**
     * 右滑
     */
    $("body").on("swiperight", function () {
        $('.side-nav-bg').fadeIn();
        $('.side-nav').removeClass('nav-out');
        $('.side-nav').addClass('nav-show');
    });

    /**
     * 左滑
     */
    $("body").on("swipeleft", function () {
        $('.side-nav-bg').fadeOut();
        $('.side-nav').removeClass('nav-show');
        $('.side-nav').addClass('nav-out');
    });

    /**
     * 点击侧边导航栏
     */
    $('.nav-item').on('click', function () {
        $('.nav-item').removeClass('nav-on');
        $(this).addClass('nav-on');
        var id = $(this).data('id');
        id = parseInt(id);
        $('.main-body>div').removeClass('tab-active');
        switch (id) {
            case 0:
                $('#title-name').html('简历管理·基本信息');
                $('.basic-info-tab').addClass('tab-active');
                break;
            case 1:
                $('#title-name').html('简历管理·项目经历');
                $('.project-info-tab').addClass('tab-active');
                break;
            case 2:
                $('#title-name').html('简历管理·社团经历');
                $('.corporation-info-tab').addClass('tab-active');
                break;
            case 3:
                $('#title-name').html('简历管理·所获荣誉');
                $('.honor-info-tab').addClass('tab-active');
                break;
            case 4:
                $('#title-name').html('简历管理·技能证书');
                $('.skill-info-tab').addClass('tab-active');
                break;
        }
    })

    /**
     * 点击保存按钮
     */
    $('#save-btn').on('click', function () {
        $.confirm({
            text: '确定编辑好了所有资料?',
            onOK: function () {

            }
        });
    });

    /**
     * 点击头像
     */
    $('#person-head-img').on('click', function () {
        $('#upload-head-img').click();
    });

    /**
     * 点击地址选择
     */
    $('#location-picker').cityPicker({
        title: "请选择籍贯地址"
    });

    /**
     * 点击名族选择
     */
    $('#nation-picker').picker({
        title: "请选择民族",
        cols: [{
            textAlign: 'center',
            values: [
                '汉族',
                '壮族',
                '满族',
                '回族',
                '苗族',
                '维吾尔族',
                '土家族',
                '彝族',
                '蒙古族',
                '藏族',
                '布依族',
                '侗族',
                '瑶族',
                '朝鲜族',
                '白族',
                '哈尼族',
                '哈萨克族',
                '黎族',
                '傣族',
                '畲族',
                '傈傈族',
                '仡佬族',
                '东乡族',
                '高山族',
                '拉枯族',
                '水族',
                '佤族',
                '纳西族',
                '羌族',
                '土族',
                '仫佬族',
                '锡伯族',
                '柯尔克牧族',
                '达斡尔族',
                '景颇族',
                '毛南族',
                '撒拉族',
                '塔吉克族',
                '阿昌族',
                '普米族',
                '鄂温克族',
                '怒族',
                '京族',
                '基诺族',
                '德昂族',
                '保安族',
                '俄罗斯族',
                '裕固族',
                '乌孜别克族',
                '门巴族',
                '鄂伦春族',
                '独龙族',
                '塔塔尔族',
                '赫哲族',
                '珞巴族',
                '布朗族',
                '来自国外'
            ]
        }]
    });

    /**
     * 日期选择
     */
    $('#birthday-picker,.project-item .project-start,.project-item .project-end,.corporation-item .corporation-start,.corporation-item .corporation-end,.honor-item .honor-date').datetimePicker({
        times: function () {}
    });

    /**
     * 设置老条目不可编辑
     */
    $('.project-item-old input,.project-item-old textarea,.corporation-item-old input,.corporation-item-old textarea,.honor-item-old input,.honor-item-old textarea').attr("disabled", true);

    /**
     * 点击老项目条目修改按钮询问是否编辑
     */
    $('.project-item-old').on('click', '.project-edit', function () {
        var id = $(this).parent().parent().data('id');
        $.confirm({
            text: '确定要修改该条目？',
            onOK: function () {
                $('.project-item[data-id="' + id + '"]').removeClass('project-item-old');
                $('.project-item[data-id="' + id + '"]').addClass('project-item-new');
                $('.project-item[data-id="' + id + '"] input,.project-item[data-id="' + id + '"] textarea').attr("disabled", false);
            }
        })
    });

    /**
     * 点击老社团经历条目修改按钮询问是否编辑
     */
    $('.corporation-item-old').on('click', '.corporation-edit', function () {
        var id = $(this).parent().parent().data('id');
        $.confirm({
            text: '确定要修改该条目？',
            onOK: function () {
                $('.corporation-item[data-id="' + id + '"]').removeClass('corporation-item-old');
                $('.corporation-item[data-id="' + id + '"]').addClass('corporation-item-new');
                $('.corporation-item[data-id="' + id + '"] input,.corporation-item[data-id="' + id + '"] textarea').attr("disabled", false);
            }
        })
    });

    /**
     * 点击老荣誉条目修改按钮询问是否编辑
     */
    $('.honor-item-old').on('click', '.honor-edit', function () {
        var id = $(this).parent().parent().data('id');
        $.confirm({
            text: '确定要修改该条目？',
            onOK: function () {
                $('.honor-item[data-id="' + id + '"]').removeClass('honor-item-old');
                $('.honor-item[data-id="' + id + '"]').addClass('honor-item-new');
                $('.honor-item[data-id="' + id + '"] input,.honor-item[data-id="' + id + '"] textarea').attr("disabled", false);
            }
        })
    });

    /**
     * 点击荣誉图片上传按钮
     */
    $('.honor-item').on('click', '.honor-img-btn', function () {
        $(this).parent().children('input').eq(0).click();
    });

    /**
     * 点击添加项目
     */
    $('#add-project-btn').click(function () {
        var date = new Date(); //利用date拼接虚拟id，到时候删除
        $('.project-items').append('<div class="project-item project-item-new" data-id="' + date.getMinutes() + date.getSeconds() + '">' +
            '<div class="project-info-bar">' +
            '<div class="project-info-title">' +
            '<p>项目名称</p>' +
            '</div>' +
            '<div class="project-info-entity">' +
            '<input id="project-name" class="weui-input" type="text" placeholder="请填写">' +
            '</div>' +
            '</div>' +
            '<div class="project-info-bar">' +
            '<div class="project-info-title">' +
            '<p>参与身份</p>' +
            '</div>' +
            '<div class="project-info-entity">' +
            '<input id="project-role" class="weui-input" type="text" placeholder="请填写">' +
            '</div>' +
            '</div>' +
            '<div class="project-info-bar">' +
            '<div class="project-info-title">' +
            '<p>开始时间</p>' +
            '</div>' +
            '<div class="project-info-entity">' +
            '<input class="weui-input project-start" type="text" placeholder="请选择">' +
            '</div>' +
            '</div>' +
            '<div class="project-info-bar">' +
            '<div class="project-info-title">' +
            '<p>结束时间</p>' +
            '</div>' +
            '<div class="project-info-entity">' +
            '<input class="weui-input project-end" type="text" placeholder="请选择">' +
            '</div>' +
            '</div>' +
            '<div class="project-describe">' +
            '<p>项目说明</p>' +
            '<textarea placeholder="请填写"></textarea>' +
            '</div>' +
            '<div class="project-info-bar">' +
            '<a class="project-delete">删除</a>' +
            '</div>' +
            '</div>');
        $('.project-item .project-start,.project-item .project-end').datetimePicker({
            times: function () {}
        });
    });

    /**
     * 点击添加社团经历
     */
    $('#add-corporation-btn').click(function () {
        var date = new Date(); //利用date拼接虚拟id，到时候删除
        $('.corporation-items').append('<div class="corporation-item corporation-item-new" data-id="' + date.getMinutes() + date.getSeconds() + '">' +
            '<div class="corporation-info-bar">' +
            '<div class="corporation-info-title">' +
            '<p>组织名称</p>' +
            '</div>' +
            '<div class="corporation-info-entity">' +
            '<input id="corporation-name" class="weui-input" type="text" placeholder="请填写">' +
            '</div>' +
            '</div>' +
            '<div class="corporation-info-bar">' +
            '<div class="corporation-info-title">' +
            '<p>职位身份</p>' +
            '</div>' +
            '<div class="corporation-info-entity">' +
            '<input id="corporation-role" class="weui-input" type="text" placeholder="请填写">' +
            '</div>' +
            '</div>' +
            '<div class="corporation-info-bar">' +
            '<div class="corporation-info-title">' +
            '<p>开始时间</p>' +
            '</div>' +
            '<div class="corporation-info-entity">' +
            '<input class="weui-input corporation-start" type="text" placeholder="请选择">' +
            '</div>' +
            '</div>' +
            '<div class="corporation-info-bar">' +
            '<div class="corporation-info-title">' +
            '<p>结束时间</p>' +
            '</div>' +
            '<div class="corporation-info-entity">' +
            '<input class="weui-input corporation-end" type="text" placeholder="请选择">' +
            '</div>' +
            '</div>' +
            '<div class="corporation-describe">' +
            '<p>内容描述</p>' +
            '<textarea placeholder="请填写"></textarea>' +
            '</div>' +
            '<div class="corporation-info-bar">' +
            '<a class="corporation-delete">删除</a>' +
            '</div>' +
            '</div>');
        $('.corporation-item .corporation-start,.corporation-item .corporation-end').datetimePicker({
            times: function () {}
        });
    });

    /**
     * 点击添加所获荣誉
     */
    $('#add-honor-btn').click(function () {
        var date = new Date(); //利用date拼接虚拟id，到时候删除
        $('.honor-items').append('<div class="honor-item honor-item-new" data-id="' + date.getMinutes() + date.getSeconds() + '">' +
            '<div class="honor-info-bar">' +
            '<div class="honor-info-title">' +
            '<p>荣誉名称</p>' +
            '</div>' +
            '<div class="honor-info-entity">' +
            '<input id="honor-name" class="weui-input" type="text" value="" placeholder="请填写">' +
            '</div>' +
            '</div>' +
            '<div class="honor-info-bar">' +
            '<div class="honor-info-title">' +
            '<p>获奖时间</p>' +
            '</div>' +
            '<div class="honor-info-entity">' +
            '<input class="weui-input honor-date" type="text" value="" placeholder="选择">' +
            '</div>' +
            '</div>' +
            '<div class="honor-describe">' +
            '<p>获奖说明</p>' +
            '<textarea placeholder="请填写"></textarea>' +
            '</div>' +
            '<div class="honor-img">' +
            '<p>获奖证书</p>' +
            '<div class="honor-img-upload">' +
            '<input type="file" data-id="2" hidden>' +
            '<a class="honor-img-btn" data-id="2">' +
            '<p>' +
            '<span class="honor-img-add">' +
            '+' +
            '</span>' +
            '</p>' +
            '<p>点击上传证书</p>' +
            '</a>' +
            '</div>' +
            '<div class="honor-img-describe">' +
            '<p>①支持格式：.jpg，.png，.jpeg，.gif，.bmp；②图片格式要求：高/宽100-1400像素；图片大小不能超过5M。</p>' +
            '</div>' +
            '<div></div>' +
            '</div>' +
            '<div class="honor-info-bar">' +
            '<a class="honor-delete">删除</a>' +
            '<a class="honor-edit">修改</a>' +
            '</div>' +
            '</div>');
        $('.honor-item .honor-date').datetimePicker({
            times: function () {}
        });
    });

    /**
     * 删除新增的项目条目
     */
    $('.project-items').on('click', '.project-item-new .project-delete', function () {
        $.confirm({
            text: "确定删除该条目?",
            onOK: () => {
                $(this).parent().parent().remove();
            }
        })
    })

    /**
     * 删除新增的社团条目
     */
    $('.corporation-items').on('click', '.corporation-item-new .corporation-delete', function () {
        $.confirm({
            text: "确定删除该条目?",
            onOK: () => {
                $(this).parent().parent().remove();
            }
        })
    })

    /**
     * 删除新增的荣誉条目
     */
    $('.honor-items').on('click', '.honor-item-new .honor-delete', function () {
        $.confirm({
            text: "确定删除该条目?",
            onOK: () => {
                $(this).parent().parent().remove();
            }
        })
    })
});