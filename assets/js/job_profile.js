var collect_status = 0;//0 未收藏   1 已收藏
$(document).ready(function () {
    //点击星星收藏
    $('#collect_job').click(function () {
        if (collect_status === 0){
            setAlert('成功关注');
            collect_status = 1;
            $(this).attr('src','./assets/images/collected_star.png')
        } else if (collect_status === 1) {
            setAlert('已取消关注');
            collect_status = 0;
            $(this).attr('src','./assets/images/collect_star.png')
        }

    });
});