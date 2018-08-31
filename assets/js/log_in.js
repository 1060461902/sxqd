var role = 0;

$(document).ready(function () {
    /**
     * 读取本地存储的用户名密码
     * @type {string | null}
     */
    var user_id = localStorage.getItem('x-keyName');
    var password = localStorage.getItem('x-keyPass');
    if(user_id){
        $('#user-id').val(user_id);
    }if(password){
        $('#check-remember').attr('checked',true);
        $('#user-password').val(password);
    }

   $(".log-input ul li").on("click",function (e) {
       $(".log-input ul li").css({
           'border-bottom': 'none',
           'color': 'black',
       });
       $(e.toElement).css({
           'border-bottom': '3px solid rgb(0, 140, 255)',
           'color': 'rgb(0, 140, 255)',
       });
       if (e.toElement.id=='company-select'){
           role = 1;
           $('.company-logUp-bar').css({
               'display':"block"
           });
           $('.remember-bar').css({
               'display':"none"
           });
       }else {
           $('.remember-bar').css({
               'display':"block"
           });
           $('.company-logUp-bar').css({
               'display':"none"
           });
           var element_id = e.toElement.id;
           if (element_id === 'teacher-select'){
               role = 2;
           }else if (element_id === 'student-select'){
               role = 0;
           }
       }
   }) ;

   /**
    * 点击登录按钮
    * */
   $('.logIn-btn button').click(function () {
       /*switch (role){
           case 0:
               break;
           case 1:
               break;
           case 2:
               break;
       }*/
       var user_id = $('#user-id').val();
       var password = $('#user-password').val();
       if (user_id == ''||user_id == null){
           setAlert("请检查用户名");
       }else if(password == ''||password == null){
           setAlert("请检查密码");
       }else{
           localStorage.setItem('x-keyName',user_id);
           if($('#check-remember').is(':checked')) {
               localStorage.setItem('x-keyPass', password);
           } else{
               localStorage.removeItem('x-keyPass');
           }
           var option = getBASEPOSTAJAX();
           option.url = '../login';
           option.contentType = "application/json";
           option.data = JSON.stringify({
               "userName":user_id,
               "password":password
           });
           option.success = function (data) {
               if (data.code === 200){
                   window.location.href = "./home_page.html";
               }else {
                   console.log(data.code+":"+data.msg);
                   setAlert("系统繁忙，请稍候再试");
               }
           };
           option.error = function (res) {
               console.log(res);
               setAlert("系统繁忙，请稍候再试");
           };
           $.ajax(option);
       }
   });
});