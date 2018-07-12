function dobind() {
    if(true){
        $.toptip('绑定成功', 'success');
    }else{
        $.toptip('绑定失败', 'error');
    }
}

function dounbind() {
    $.confirm('确定要解绑吗？',function () {$.toptip('已解绑','success')},function () {$.toptip('已取消','warning')})
}