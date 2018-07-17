$(document).ready(function () {
    $('.remind-swipe').fadeIn(1500);
    $('.remind-swipe').fadeOut(1500);

    /**
     * 右滑
     */
    $("body").on("swiperight",function () {
        $('.side-nav-bg').fadeIn();
        $('.side-nav').removeClass('nav-out');
        $('.side-nav').addClass('nav-show');
    });

    /**
     * 左滑
     */
    $("body").on("swipeleft",function () {
        $('.side-nav-bg').fadeOut();
        $('.side-nav').removeClass('nav-show');
        $('.side-nav').addClass('nav-out');
    });

    /**
     * 点击侧边导航栏
     */
    $('.nav-item').on('click',function () {
        $('.nav-item').removeClass('nav-on');
        $(this).addClass('nav-on');
    })
});