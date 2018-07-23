var collect_status = 0; //0 未收藏   1 已收藏

$(document).ready(function () {

    /**
     * 
     */
    var post_id = $.parseURL(location.href)['id'];
    
    /**
     * 
     */
    var option = getBASEGETAJAX();
    option.url = './json/job_profile.json';
    option.data = {
        id : post_id
    }
    option.success = function (data) {
        if(data.code === 200){
            $("#info-v").handlebars($('#info-model'),data.data,{
                name:'skills',
                callback:function (skill_request) {
                    var content = '';
                    var skills = skill_request.split(',');
                    var len = skills.length;
                    for(var i=0;i<len;i++){
                        content += '<span class="label label-info">'+skills[i]+'</span>';
                    }
                    return content;
                }
            });
            $(".ask-list").handlebars($('#answer-item-model'),data.data.commits)
        }else{
            setAlert('系统繁忙，请稍后再试');
            console.log(msg);
        }
    }
    option.error = function (res) {
        console.log(res);
    }
    $.ajax(option);

    /**
     * 点击星星收藏
     */
    $('body').on('click','#collect_job',function () {
        if (collect_status === 0) {
            setAlert('成功关注');
            collect_status = 1;
            $(this).attr('src', './assets/images/collected_star.png')
        } else if (collect_status === 1) {
            setAlert('已取消关注');
            collect_status = 0;
            $(this).attr('src', './assets/images/collect_star.png')
        }
    });
});