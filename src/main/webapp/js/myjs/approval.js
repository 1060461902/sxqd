$(document).ready(function(){
  var type = "企业类型";//第一次导入时默认全部企业类型
    $.getJSON("js/json/approval-1.json", function(data) {
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
            td.innerHTML = obj.companyName;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.type;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.address;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.nickName;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.phone;
            if(obj.checked!=="false"){
             nums+=1;
            }     
              var td = tr.insertCell (tr.cells.length);
              // alert($("tr:eq("+j+")").val());
              td.innerHTML = '<a href="./approval_company.html?id='+$("tr:eq("+j+")").val()+'">查看</a>';
         } //for
         //未读消息
         if(nums!==0){
          $("span#qysp").html(nums);
         }
         else if(nums==0)
         {
          $("span#qysp").css("display","none");
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
