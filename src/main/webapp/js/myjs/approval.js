$(document).ready(function(){
  var info = new Object();
  info.type = "企业类型";//第一次导入时默认全部企业类型
      $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/showCompanyRegisterApplyList',
       data: JSON.stringify(info),
       async: true,
       contentType: "application/json",
       dateType: "json",
       success: function(data){
      writein(data);
      checkbox();
      var docrTable = $('#table-1').dataTable({
                "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示   
                "bFilter" : true, //是否启动过滤、搜索功能
                "info": false,
                 "pageLength": 7,
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
//     $.getJSON("js/json/approval-1.json", function(data) {
// });//getjson
//--------------------筛选（需要表格重新导入）
  $("option").click(function(){
    var info = new Object();
    info.type = $(this).text();
     $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/showCompanyRegisterApplyList',
       data: JSON.stringify(info),
       async: true,
       contentType: "application/json",
       dateType: "json",
       success: function(data){
     $('#table-1').dataTable().fnClearTable(); //清除表格内
     $('#table-1').dataTable().fnDestroy();
      writein(data);
      checkbox();
            var docrTable = $('#table-1').dataTable({
                "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示   
                "bFilter" : true, //是否启动过滤、搜索功能
                "info": false,
                 "pageLength": 7,
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
      // $.getJSON("js/json/approval-1.json", function(data) {
      // });
  });
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
//-----------点击通过（需要表格重新导入）-----------------
  $(".operations").click(function(){
    var id = new Array();
    var a=0;
    $('td>input:checkbox').each(function() {
        if ($(this).attr('checked') =='checked') {
          id[a]=$(this).parent('td').parent('tr').val();alert(id[a]);
          a++;
        }
    });
    var info = new Object();
    info.id=id;
    info.passFlag='1';
    info.meg = null;
      $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/comfirmRegister',
       data: JSON.stringify(info),
       async: true,
       contentType: "application/json",
       dateType: "json",
       success: function(data){
         $('th>input:checkbox').attr('checked',false);
          location.reload();
     },
       error: function(){
        alert('服务端异常');
        }
    });
   });
//------------------->
function writein(data){
       var tbody = document.getElementsByTagName ('tbody')[0];
       var len = data.compamyViewList.length;
       var nums=0;
       for ( var i = 0; i < len; i++)
       {
          var obj = data.compamyViewList[i];
            var tr = tbody.insertRow(tbody.rows.length);
            var j=i+1;
            $("tr:eq("+j+")").val(obj.id);//对当前行赋值
            //alert($("tr").val());
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<input type="checkbox">';
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<a href="./approval_company.html?id='+$("tr:eq("+j+")").val()+'">'+obj.companyName+'</a>';
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.type;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.address;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.nickName;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.phone;
              // var td = tr.insertCell (tr.cells.length);
              // // alert($("tr:eq("+j+")").val());
              // td.innerHTML = '<a href="./approval_company.html?id='+$("tr:eq("+j+")").val()+'">'+obj.companyName+'</a>';
         } //for
}
function checkbox(){
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
}
});//document