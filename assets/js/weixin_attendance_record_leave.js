$(document).ready(function () {
    $.ajax({
        url:'',
        type:'',
        data:{
            id:id
        },
        success:function (d) {
            $('#info-container').handlebars($('#info-model'),d.data);
        },
        error:function (res) {

        }
    });
});