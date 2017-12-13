$(document).ready(function(){    
    // $.getJSON("js/json/data_analysis.json", function(data) {
    //      var obj = data.DataList;
    //      $("span#a").text(obj.a);
    //      $("span#b").text(obj.b);
    //      $("h1#total-num").text(obj.c);
    //      $("span#d").text(obj.d);
    //      $("span#e").text(obj.e);
    //      $("span#f").text(obj.f);
    //      $("span#g").text(obj.g);
    //      $("span#h").text(obj.h);
    // });
     $.ajax({
       type: 'get',
       url: '/fieldManagement/admin/index',
       async: true,
       contentType: "application/json",
       dateType: "json",
       success: function(data){
         $("span#a").text(obj.clockInNum);
         $("span#b").text(obj.clockOutNum);
         $("h1#total-num").text(obj.studentNum);
         $("span#d").text(obj.employeeNum);
         $("span#e").text(obj.lastDayAttendNum);
         $("span#f").text(obj.companyNum);
         $("span#g").text(obj.postNum);
         $("span#h").text(obj.headcounts);
          // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('all'));

        // 指定图表的配置项和数据
option = {
    tooltip: {//提示框
        trigger: 'axis',
        axisPointer: {
            type: 'cross',
            crossStyle: {
                color: '#999'
            }
        }
    },
    toolbox: {//工具栏
        feature: {
            dataView: {show: true, readOnly: false},
            magicType: {show: true, type: ['line']},
            restore: {show: true},
            saveAsImage: {show: true}
        }
    },
    legend: {//图例
        data:['企业注册数目','在招岗位数目','招聘人数']
    },
    xAxis: [//x轴
        {
            type: 'category',
            data: [],
            axisPointer: {
                type: 'shadow'
            }
        }
    ],
    yAxis: [
        {
            type: 'value',
            name: '招聘人数',
            min: 0,
            max: 150,
            interval: 50,
            axisLabel: {
                formatter: '{value} 人'
            }
        },
        {
            type: 'value',
            name: '企业注册数目/在招岗位数目',
            min: 0,
            max: 25,
            interval: 5,
            axisLabel: {
                formatter: '{value} 人'
            }
        }
    ],
    series: [
        {
            name:'招聘人数',
            type:'line',
            data:[]
        },
        {
            name:'企业注册数目',
            type:'line',
            yAxisIndex: 1,
            data:[]
        },
        {
            name:'在招岗位数目',
            type:'line',
            yAxisIndex: 1,
            data:[]
        }
    ]
};//option

     myChart.showLoading();
         var qyzc=[];
         var zzgw=[];
         var zprs=[];
         var dates=[];

     //$.getJSON("js/json/table-all.json", function(data) {
            if (data.table != null && data.table.length > 0) {
                   var obj = data.table;
                    for(var i=0;i<obj.length;i++){
                       qyzc.push(obj[i].companyNum);        //挨个取出温度、湿度、压强等值并填入前面声明的温度、湿度、压强等数组
                       zzgw.push(obj[i].currentRecruitNum);
                       zprs.push(obj[i].currentStudentNum);
                       dates.push(obj[i].times);
                    }
                    myChart.hideLoading();    //隐藏加载动画
                    
                    myChart.setOption({        //载入数据
                        xAxis: {
                            data: dates    //填入X轴数据
                        },
                        series: [    //填入系列（内容）数据
                                      {
                                    // 根据名字对应到相应的系列
                                    name: '企业注册数目',
                                    data: qyzc
                                },
                                      {
                                    name: '在招岗位数目',
                                    data: zzgw
                                },
                                    {
                                    name: '招聘人数',
                                    data: zprs
                                }
                       ]
                    });
             }
             else {
                 //返回的数据为空时显示提示信息
                 alert("图表请求数据为空，可能服务器暂未录入数据，您可以稍后再试！");
                   myChart.hideLoading();
             }

 //    });
        // 使用刚指定的配置项和数据显示图表。    
myChart.setOption(option,window.onresize = myChart.resize);
window.onresize = myChart.resize;
     },
       error: function(){
        alert('服务端异常');
        }
    });
//           // 基于准备好的dom，初始化echarts实例
//         var myChart = echarts.init(document.getElementById('all'));

//         // 指定图表的配置项和数据
// option = {
//     tooltip: {//提示框
//         trigger: 'axis',
//         axisPointer: {
//             type: 'cross',
//             crossStyle: {
//                 color: '#999'
//             }
//         }
//     },
//     toolbox: {//工具栏
//         feature: {
//             dataView: {show: true, readOnly: false},
//             magicType: {show: true, type: ['line']},
//             restore: {show: true},
//             saveAsImage: {show: true}
//         }
//     },
//     legend: {//图例
//         data:['企业注册数目','在招岗位数目','招聘人数']
//     },
//     xAxis: [//x轴
//         {
//             type: 'category',
//             data: [],
//             axisPointer: {
//                 type: 'shadow'
//             }
//         }
//     ],
//     yAxis: [
//         {
//             type: 'value',
//             name: '招聘人数',
//             min: 0,
//             max: 150,
//             interval: 50,
//             axisLabel: {
//                 formatter: '{value} 人'
//             }
//         },
//         {
//             type: 'value',
//             name: '企业注册数目/在招岗位数目',
//             min: 0,
//             max: 25,
//             interval: 5,
//             axisLabel: {
//                 formatter: '{value} 人'
//             }
//         }
//     ],
//     series: [
//         {
//             name:'招聘人数',
//             type:'line',
//             data:[]
//         },
//         {
//             name:'企业注册数目',
//             type:'line',
//             yAxisIndex: 1,
//             data:[]
//         },
//         {
//             name:'在招岗位数目',
//             type:'line',
//             yAxisIndex: 1,
//             data:[]
//         }
//     ]
// };//option

//      myChart.showLoading();
//          var qyzc=[];
//          var zzgw=[];
//          var zprs=[];
//          var dates=[];

//      $.getJSON("js/json/table-all.json", function(data) {
//             if (data.table != null && data.table.length > 0) {
//                    var obj = data.table;
//                     for(var i=0;i<obj.length;i++){
//                        qyzc.push(obj[i].companyNum);        //挨个取出温度、湿度、压强等值并填入前面声明的温度、湿度、压强等数组
//                        zzgw.push(obj[i].currentRecruitNum);
//                        zprs.push(obj[i].currentStudentNum);
//                        dates.push(obj[i].times);
//                     }
//                     myChart.hideLoading();    //隐藏加载动画
                    
//                     myChart.setOption({        //载入数据
//                         xAxis: {
//                             data: dates    //填入X轴数据
//                         },
//                         series: [    //填入系列（内容）数据
//                                       {
//                                     // 根据名字对应到相应的系列
//                                     name: '企业注册数目',
//                                     data: qyzc
//                                 },
//                                       {
//                                     name: '在招岗位数目',
//                                     data: zzgw
//                                 },
//                                     {
//                                     name: '招聘人数',
//                                     data: zprs
//                                 }
//                        ]
//                     });
//              }
//              else {
//                  //返回的数据为空时显示提示信息
//                  alert("图表请求数据为空，可能服务器暂未录入数据，您可以稍后再试！");
//                    myChart.hideLoading();
//              }

//      });
//         // 使用刚指定的配置项和数据显示图表。    
// myChart.setOption(option,window.onresize = myChart.resize);
// window.onresize = myChart.resize;
});