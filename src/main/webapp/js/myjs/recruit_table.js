$(document).ready(function(){
    $.getJSON("js/json/recruit_table.json", function(data) {
       var tbody = document.getElementsByTagName ('tbody')[0];
       var len = data.RecruitmentList.length;
       for ( var i = 0; i < len; i++)
      {
          var obj = data.RecruitmentList[i];
            var tr = tbody.insertRow(tbody.rows.length);
            var j=i+1;
            $("tr:eq("+j+")").val(obj.id);//对当前行赋值
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<input type="checkbox">';
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<a href="recruit_table_details.html?id='+obj.id+'">'+obj.post;+'</a>';
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<a href="company_table_details.html?id='+obj.companyId+'">'+obj.companyName+'</a>';
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<a href="#">'+obj.totalNumber+'/'+obj.currentNumber+'<i class="fa fa-sort-desc fa-fw"></i></a>';
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.postTime;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.address;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML='<a href="#" title="禁用/解禁" class="forbidden" id="'+obj.id+'" value='+obj.forbidden+'><i class="fa fa-ban fa-2x"></i>';
            if(obj.forbidden==true){
              $('tr:eq('+j+') td:eq(6) a').css("color","red");
            }
      } //for
// -------------禁用--------------
$('.forbidden').click(function(){
// alert($(this).attr("id")+'+'+$(this).attr("value"));
 var info = new Object();
 info.id = $(this).attr("id");
 info.roleId = "5";
 if($(this).attr("value")=='true'){
   var r=confirm("是否解禁该用户");
      if (r==true)
      { 
        info.operationType ="4";
        alert("解禁成功");
        $(this).css("color","#337ab7");
        $(this).attr("value",'false');
      } 
    }
    else if($(this).attr("value")=='false'){
       var r=confirm("是否禁用该用户");
      if (r==true)
      { 
        info.operationType ="2";
        alert("禁用成功");
        $(this).css("color","red");
        $(this).attr("value",'true');
      }
    }
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
    info.operationType ="3";
    info.roleId = "5";
    alert("删除成功");
    $('th>input:checkbox').attr('checked',false);
    location.reload();
   // alert(info.id);
    //<---------------------------------------表格重新导入
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