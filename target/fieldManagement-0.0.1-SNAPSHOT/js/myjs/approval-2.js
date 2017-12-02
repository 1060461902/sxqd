$(document).ready(function(){
  var type = "企业类型";//第一次导入时默认全部企业类型
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
            var n=0;
            for(var p in obj){
              if(n==4){
                break;
              }
              var td = tr.insertCell (tr.cells.length);
              td.innerHTML = obj[p];
              n++;
            }

            if(obj.checked!=="false"){
             nums+=1;
            }   
              var td = tr.insertCell (tr.cells.length);
              td.innerHTML = '<a href="./approval-occupation.html?id='+$("tr:eq("+j+")").val()+'">查看</a>';
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
  });

//搜索企业名称（需要表格重新导入）
  $("button").click(function(){
    var type = $("input").val();
  });
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
        $("span.operations").css("display","block");
       });
      }
      else if(m%2==0){
        $('input:checkbox').each(function () {
        $(this).attr('checked',false);
        $("span.operations").css("display","none");
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
          $("span.operations").css("display","block");
        }
        else
        {
          $("span.operations").css("display","none");
        }
    });
   });
//-----------点击通过（需要表格重新导入）-----------------
  $("span.operations").click(function(){
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
    info.passFlag='1';//要传输的数据
    //<---------------------------------------表格重新导入
    $.getJSON("js/json/approval-occupation.json", function(data) {
      $("tbody").empty();
      $('th>input:checkbox').attr("checked",false);
      $("span.operations").css("display","none");
      var tbody = document.getElementsByTagName ('tbody')[0];
       var len = data.recruitmentApplyList.length;
       var nums=0;
       for ( var i = 0; i < len; i++)
       {
          var obj = data.recruitmentApplyList[i];
            var tr = tbody.insertRow(tbody.rows.length);
            var j=i+1;
            $("tr:eq("+j+")").val(obj.id);//对当前行赋值
            //alert($("tr").val());
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<input type="checkbox">';
            var n=0;
            for(var p in obj){
              if(n==4){
                break;
              }
              var td = tr.insertCell (tr.cells.length);
              td.innerHTML = obj[p];
              n++;
            }
              var td = tr.insertCell (tr.cells.length);
              td.innerHTML = '<a href="./approval-occupation.html?id='+$("tr:eq("+j+")").val()+'">查看</a>';
            if(obj.checked!=="false"){
             nums+=1;
            }  
         } //for
        if(nums!==0){
          $("span#sxfbsp").html(nums);
         }
         else if(nums==0)
         {
          $("span#sxfbsp").css("display","none");
         }
        $("td>input:checkbox").click(function(){
        var mm=0;
       $('input:checkbox').each(function() {
        if ($(this).attr('checked') =='checked') {
          mm++;     
        }
        if(mm>0)
        {
          $("span.operations").css("display","block");
        }
        else
        {
          $("span.operations").css("display","none");
        }
    });
   });
        });//geijson 
   });
});//getjson
//------------------->
});//document