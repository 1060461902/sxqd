$(document).ready(function(){
  var info = new Object();
  info.grade = null;
  info.teacher = null;
  info.status = null
  info.clss = null;
  info.company = null;
  // ajax
  //---------获取筛选内容
  $.getJSON("js/json/internship_table.json", function(data) {
        var namelength = data.majorNameList.length;
        for ( var i = 0; i < namelength; i++){
        $('select').append('<option>'+data.majorNameList[i].majorName+'</option>');
       }
  });
    $.getJSON("js/json/internship_table.json", function(data) {
       var tbody = document.getElementsByTagName ('tbody')[0];
       var len = data.internship.length;
       for ( var i = 0; i < len; i++)
      {
          var obj = data.internship[i];
            var tr = tbody.insertRow(tbody.rows.length);
            var j=i+1;
            $("tr:eq("+j+")").val(obj.id);//对当前行赋值
            $('tr').addClass('pointer');
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<input type="checkbox">';
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<a href="student_table_details.html?id="'+obj.id+'">'+obj.nickName+'</a>';
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.clss;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<a href="company_table_details.html?id="'+obj.companyId+'>'+obj.company+'</a>';
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.post;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.status;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.stages;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<a href="teacher_table_details.html?id="'+obj.companyId+'>'+obj.teacherName+'</a>';
      } //for
//--------筛选-----------
$('option').click(function(){
   var info = new Object();
   info.major= $(this).text();
//ajax
});
//-------------点击---------
$('tr').click(function(){
  var href ="./student_table_clock.html?id="+$(this).val();
  location.href=href;
});
//--------------------------全选checkbox--------------------------
var m=0;
$('th>input:checkbox').click(function() {
      m+=1;
      if(m%2==1){
        $('input:checkbox').each(function() {
        $(this).attr('checked', true);
       });
        if($('select.status').val()=='待实习'){
            $('button#gg').css("display","none");
            $('button#gd').css("display","none");
            $('button#fp').css("display","block");
          }
          else if($('select.status').val()=='实习中'){
            $('button#fp').css("display","none");
            $('button#gd').css("display","none");
            $('button#gg').css("display","block");
          }
          else if($('select.status').val()=='已结业'){
            $('button#fp').css("display","none");
            $('button#gg').css("display","none");
            $('button#gd').css("display","block");
          }
      }
      else if(m%2==0){
        $('input:checkbox').each(function () {
        $(this).attr('checked',false);
        $('button#gg').css("display","none");
        $('button#gd').css("display","none");
        $('button#fp').css("display","none");
});
      }
});
//--------------部分选择操作checkbox--------------------------------
   $("td>input:checkbox").click(function(){
    var mm=0;
    $('input:checkbox').each(function() {
        if ($(this).attr('checked') =='checked') {
          mm++;
        }
    });
        if(mm>0)
        {
          if($('select.status').val()=='待实习'){
            $('button#gg').css("display","none");
            $('button#gd').css("display","none");
            $('button#fp').css("display","block");
          }
          else if($('select.status').val()=='实习中'){
            $('button#fp').css("display","none");
            $('button#gd').css("display","none");
            $('button#gg').css("display","block");
          }
          else if($('select.status').val()=='已结业'){
            $('button#fp').css("display","none");
            $('button#gg').css("display","none");
            $('button#gd').css("display","block");
          }
        }
        else
        {
        $('button#gg').css("display","none");
        $('button#gd').css("display","none");
        $('button#fp').css("display","none");
        }
   });
//-----------点击归档（需要表格重新导入）-----------------
  $("button#gd").click(function(){
    var id = new Array();
    var a=0;
    $('td>input:checkbox').each(function() {
        if ($(this).attr('checked') =='checked') {
          id[a]=$(this).parent('td').parent('tr').val();  alert(id[a]);
          a++;
        }
    });
    var info = new Object();
    info.id=id;
    //ajax
    $('th>input:checkbox').attr('checked',false);
    location.reload();
   // alert(info.id);
    //<---------------------------------------表格重新导入
   });
      var docrTable = $('#table').dataTable({
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