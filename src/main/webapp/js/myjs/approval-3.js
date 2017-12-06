$(document).ready(function(){
    $.getJSON("js/json/approval-3.json", function(data) {
       var tbody = document.getElementsByTagName ('tbody')[0];
       var len = data.dynamicNewApplyList.length;
       var nums=0;
       for ( var i = 0; i < len; i++)
       {
          var obj = data.dynamicNewApplyList[i];
            var tr = tbody.insertRow(tbody.rows.length);
            var j=i+1;
            $("tr:eq("+j+")").val(obj.id);//对当前行赋值
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<input type="checkbox">';
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.title;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<div class="pic-frame"><img src="'+obj.imageUrl+'" class="img-responsive"></img></div>';
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.startTime;
            if(obj.checked!=="false"){
             nums+=1;
            } 
            var m=i+1;
              $('tr:eq('+m+') td:eq(2)').addClass('flex-center');
              // var td = tr.insertCell (tr.cells.length);
              // td.innerHTML = '<a href="./approval-news.html?id='+$("tr:eq("+j+")").val()+'">查看</a>';
         } //for
         //未读消息
         if(nums!==0){
          $("span#sydtsp").html(nums);
         }
         else if(nums==0)
         {
          $("span#sydtsp").css("display","none");
         }
// //搜索企业名称（需要表格重新导入）
//   $("button").click(function(){
//     var type = $("input").val();
//   });
//--------------点击查看------------------------
// $("[href]#checkcompany").click(function(){
//   var id = $(this).parent("td").parent('tr').val();
//   location.href='./approval_company.html?id='+id;
// });
//--------------------------全选checkbox--------------------------
var m=0;
$('th>input:checkbox').click(function() {
      m+=1;
      if(m%2==1){
        $('input:checkbox').each(function() {
        $(this).attr('checked', true);
        $(".operations").css("display","block");
       });
      }
      else if(m%2==0){
        $('input:checkbox').each(function () {
        $(this).attr('checked',false);
        $(".operations").css("display","none");
});
      }
});
//--------------部分选择通过checkbox--------------------------------
   $("td>input:checkbox").click(function(){
    var mm=0;
    $('input:checkbox').each(function() {
        if ($(this).attr('checked') =='checked') {
          mm++;     
        }
        if(mm>0)
        {
          $(".operations").css("display","block");
        }
        else
        {
          $(".operations").css("display","none");
        }
    });
   });
//-----------点击通过（需要表格重新导入）-----------------
  $(".operations").click(function(){
    var id = new Array();
    var a=0;
    $('td>input:checkbox').each(function() {
        if ($(this).attr('checked') =='checked') {
          id[a]=$(this).parent('td').parent('tr').val();
          a++;
        }
    });
    var info = new Object();
    info.id=id;
    info.passFlag='1';
    info.msg = null;
    $('th>input:checkbox').attr('checked',false);
    location.reload();
   });
      var docrTable = $('#table-3').dataTable({
                "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示   
                "bFilter" : true, //是否启动过滤、搜索功能
                "info": false,
                 "pageLength": 3,
                "lengthChange" : false, 
                  "oLanguage": { //国际化配置
                    "sProcessing" : "正在获取数据，请稍后...",
                    "sLengthMenu" : "显示 _MENU_ 条",
                    "sZeroRecords" : "没有您要搜索的内容",
                    "sInfo" : "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",
                    "sInfoEmpty" : "记录数为0",
                    "sInfoFiltered" : "(全部记录数 _MAX_ 条)",
                    "sInfoPostFix" : "",
                    "sSearch" : "搜索",
                    "sUrl" : "",
                    "oPaginate": {
                        "sFirst" : "第一页",
                        "sPrevious" : "上一页",
                        "sNext" : "下一页",
                        "sLast" : "最后一页"
                    }  
                },
              });
});//getjson
//------------------->
});//document