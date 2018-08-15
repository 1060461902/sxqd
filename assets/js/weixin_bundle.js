/*$(document).ready(function () {
    $.ajax({
        type: 'GET',
        url: '../auth/bind',
        data: {},
        success: function (d) {
            if (d.code === 500) {
                if(d.msg == '502') {
                    $.toptip('该微信已绑定账号', 'warning');
                }else {
                    $.toptip('系统繁忙', 'error');
                }
            }
        },
        error: function (res) {
            console.log(res);
            $.toptip('系统繁忙', 'error');
        }
    });
});*/

function dobind() {
    var userid = $('#student-id-input').val();
    var password = $('#student-pwd-input').val();
    if (userid == null || userid == '') {
        $.toptip('请输入学号', 'error');
    } else if (password == null || password == '') {
        $.toptip('请输入密码', 'error');
    } else {
        $.ajax({
            type: 'POST',
            url: '../bind',
            data: {
                'username': userid,
                'password': password
            },
            success: function (d) {
                if (d.code === 200) {
                    $.toptip('绑定成功', 'success');
                } else {
                    if (d.msg == '501'){
                        $.toptip('请检查账号密码', 'warning');
                    }else {
                        $.toptip('系统繁忙', 'error');
                    }
                }
            },
            error: function (res) {
                console.log(res);
                $.toptip('系统繁忙', 'error');
            }
        });
    }
}

function dounbind() {
    $.confirm('确定要解绑吗？（功能暂未实现）', function () {
        $.toptip('目前无法解绑', 'success')
    }, function () {
        $.toptip('已取消', 'warning')
    })
}