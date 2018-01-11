$(document).ready(function(){
     $.ajax({
       type: 'get',
       url: '/fieldManagement/admin/showDynamicNewsList',
       async: true,
       contentType: "application/json",
       dateType: "json",
       success: function(data){
       var tbody = document.getElementsByTagName ('tbody')[0];
       var len = data.dynamicApproves.length;
       var nums=0;
       for ( var i = 0; i < len; i++)
       {
          var obj = data.dynamicApproves[i];
            var tr = tbody.insertRow(tbody.rows.length);
            var j=i+1;
            $("tr:eq("+j+")").val(obj.id);//对当前行赋值
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<input type="checkbox">';
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.title;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<div class="pic-frame"><img src="'+obj.imageUrl+'" class="img-responsive"></img></div>';
            var m=i+1;
              $('tr:eq('+m+') td:eq(2)').addClass('flex-center');
              var td = tr.insertCell (tr.cells.length);
            if(obj.showStatus==true)
            td.innerHTML = '展示中';
            else if(obj.showStatus==false) 
            td.innerHTML = '已撤下';
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<a href="./news_table_details.html?id='+$("tr:eq("+j+")").val()+'">查看</a>';
       } //for
//--------------部分选择删除checkbox--------------------------------
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
     },
       error: function(){
        alert('服务端异常');
        }
    });
//     $.getJSON("js/json/news_table.json", function(data) {
//         var tbody = document.getElementsByTagName ('tbody')[0];
//        var len = data.dynamicApproves.length;
//        var nums=0;
//        for ( var i = 0; i < len; i++)
//        {
//           var obj = data.dynamicApproves[i];
//             var tr = tbody.insertRow(tbody.rows.length);
//             var j=i+1;
//             $("tr:eq("+j+")").val(obj.id);//对当前行赋值
//             var td = tr.insertCell (tr.cells.length);
//             td.innerHTML = '<input type="checkbox">';
//             var td = tr.insertCell (tr.cells.length);
//             td.innerHTML = obj.title;
//             var td = tr.insertCell (tr.cells.length);
//             td.innerHTML = '<div class="pic-frame"><img src="'+obj.imageUrl+'" class="img-responsive"></img></div>';
//             var m=i+1;
//               $('tr:eq('+m+') td:eq(2)').addClass('flex-center');
//               var td = tr.insertCell (tr.cells.length);
//             if(obj.showStatus==true)
//             td.innerHTML = '展示中';
//             else if(obj.showStatus==false) 
//             td.innerHTML = '已撤下';
//             var td = tr.insertCell (tr.cells.length);
//             td.innerHTML = '<a href="./news_table_details.html?id='+$("tr:eq("+j+")").val()+'">查看</a>';
//        } //for
// //--------------部分选择删除checkbox--------------------------------
//    $("td>input:checkbox").click(function(){
//     var mm=0;
//     $('input:checkbox').each(function() {
//         if ($(this).attr('checked') =='checked') {
//           mm++;
//         }
//         if(mm>0)
//         {
//           $(".operations").css("display","block");
//         }
//         else
//         {
//           $(".operations").css("display","none");
//         }
//     });
//    });
//       var docrTable = $('#table-3').dataTable({
//                 "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示   
//                 "bFilter" : true, //是否启动过滤、搜索功能
//                 "info": false,
//                  "pageLength": 3,
//                 "lengthChange" : false, 
//                   "oLanguage": { //国际化配置
//                     "sProcessing" : "正在获取数据，请稍后...",
//                     "sLengthMenu" : "显示 _MENU_ 条",
//                     "sZeroRecords" : "没有您要搜索的内容",
//                     "sInfo" : "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",
//                     "sInfoEmpty" : "记录数为0",
//                     "sInfoFiltered" : "(全部记录数 _MAX_ 条)",
//                     "sInfoPostFix" : "",
//                     "sSearch" : "搜索",
//                     "sUrl" : "",
//                     "oPaginate": {
//                         "sFirst" : "第一页",
//                         "sPrevious" : "上一页",
//                         "sNext" : "下一页",
//                         "sLast" : "最后一页"
//                     }
//                 },
//               });     
// });//getjson
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
//-----------点击删除（需要表格重新导入）-----------------
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
     $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/deleteshow',
       data: JSON.stringify(info),
       async: true,
       contentType: "application/json",
       dateType: "json",
       success: function(data){
        alert("删除成功");
        $('th>input:checkbox').attr('checked',false);
        location.reload();
     },
       error: function(){
        alert('服务端异常');
        }
    });
   });
//------------------->
});//document