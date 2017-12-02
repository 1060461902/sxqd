$(document).ready(function(){ 
    if ($(".badge").text()==0) {
      $(".badge").css("display","none")
    };  
    var docrTable = $('#table-1').dataTable({  
                "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示   
                "bFilter" : true, //是否启动过滤、搜索功能
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
    var docrTable = $('#table-2').dataTable({  
                "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示   
                "bFilter" : true, //是否启动过滤、搜索功能
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
        var docrTable = $('#table-3').dataTable({  
                "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示   
                "bFilter" : true, //是否启动过滤、搜索功能
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
});
function admit()
{
var r=confirm("是否允许该公司注册");
if (r==true)
  {
  alert("注册成功");
  }
}
function submit(){
	alert("您的操作已提交！");
}
$(document).ready(function(){    
    if ($("#qysq").text()==0) {
      $("#qysq").css("display","none")
    };
    if ($("#sxfbsp").text()==0) {
      $("#sxfbsp").css("display","none")
    };
    if ($("#sydtsp").text()==0) {
      $("#sydtsp").css("display","none")
    };
});