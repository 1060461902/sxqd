      // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('all'));

        // 指定图表的配置项和数据
option = {
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'cross',
            crossStyle: {
                color: '#999'
            }
        }
    },
    toolbox: {
        feature: {
            dataView: {show: true, readOnly: false},
            magicType: {show: true, type: ['line']},
            restore: {show: true},
            saveAsImage: {show: true}
        }
    },
    legend: {
        data:['企业注册数目','在招岗位数目','招聘人数']
    },
    xAxis: [
        {
            type: 'category',
            data: ['17年8月','17年9月','17年10月','17年11月','17年12月','18年1月','18年2月'],
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
            data:[80, 79, 70, 72, 76, 26, 35]
        },
        {
            name:'企业注册数目',
            type:'line',
            yAxisIndex: 1,
            data:[20,16, 19, 19, 20, 8, 5 ]
        },
        {
            name:'在招岗位数目',
            type:'line',
            yAxisIndex: 1,
            data:[12, 12, 13, 14, 16, 10, 10]
        }
    ]
};

        // 使用刚指定的配置项和数据显示图表。    
myChart.setOption(option,window.onresize = myChart.resize);
window.onresize = myChart.resize;