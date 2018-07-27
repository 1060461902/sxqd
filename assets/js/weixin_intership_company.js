$(document).ready(function () {
    var screen_height = document.documentElement.clientHeight;
    var top_height = document.getElementById("company-top").offsetHeight;
    $('.bottom-info').css({
        'height': (screen_height - top_height),
    });

    $('.company-switch li').click(function () {
        var id_name = $(this).attr('id');
        switch (id_name) {
            case 'index-btn':
                $('#index-btn').addClass('company-switch-selected');
                $('#post-btn').removeClass('company-switch-selected');
                $('.company-post').css({
                    'display': 'none'
                });
                $('.company-index').css({
                    'display': 'block'
                });
                break;
            case 'post-btn':
                $('#post-btn').addClass('company-switch-selected');
                $('#index-btn').removeClass('company-switch-selected');
                $('.company-index').css({
                    'display': 'none'
                });
                $('.company-post').css({
                    'display': 'block'
                });
                break;
        }
    });
})