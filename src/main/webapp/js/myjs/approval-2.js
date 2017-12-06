$(document).ready(function(){
  var type = "企业类型";//第一次导入时默认全部企业类型
  var info = new Object();
  info.companyName = type;
    $.getJSON("js/json/approval-2-companyname.json", function(data) {
        var namelength = data.compamyNameList.length;
        for ( var i = 0; i < namelength; i++){
        $('select').append('<option>'+data.compamyNameList[i].companyName+'</option>');
       }
  });
    $.getJSON("js/json/approval-2.json", function(data) {
       var tbody = document.getElementsByTagName ('tbody')[0];
       var len = data.recruitmentApplyList.length;
       var nums=0;
       for ( var i = 0; i < len; i++)
       {
          var obj = data.recruitmentApplyList[i];
            var tr = tbody.insertRow(tbody.rows.length);
            var j=i+1;
            $("tr:eq("+j+")").val(obj.id);//对当前行赋值
            //alert($("tr:eq("+j+")").val());
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<input type="checkbox">';
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<a href="./approval-occupation.html?id='+$("tr:eq("+j+")").val()+'">'+obj.post+'</a>'
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.companyName;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.totalNumber;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.nickName;
            if(obj.checked!=="false"){
             nums+=1;
            }   
              // var td = tr.insertCell (tr.cells.length);
              // td.innerHTML = '<a href="./approval-occupation.html?id='+$("tr:eq("+j+")").val()+'">'++'</a>';
         } //for
         //未读消息
         if(nums!==0){
          $("span#sxfbsp").html(nums);
         }
         else if(nums==0)
         {
          $("span#sxfbsp").css("display","none");
         }
//筛选（需要表格重新导入）
  $("option").click(function(){
    var type = $(this).text();
    var info = new Object();
    info.companyName = type;
  });

////搜索企业名称（需要表格重新导入）
  // $("button").click(function(){
  //   var type = $("input").val();
  // });
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
      var docrTable = $('#table-2').dataTable({
                "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示   
                "bFilter" : true, //是否启动过滤、搜索功能
                "info": false,
                 "pageLength": 8,
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