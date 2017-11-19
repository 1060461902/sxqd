// 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('stu2'));

        // 指定图表的配置项和数据
option = {
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        x: 'left',
    },
    series: [
        {
            type:'pie',
            radius: ['50%', '70%'],
            avoidLabelOverlap: false,
            label: {
                normal: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    show: true,
                    textStyle: {
                        fontSize: '30',
                        fontWeight: 'bold'
                    }
                }
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data:[
                {value:23, name:'状态三'},
                {value:13, name:'状态四'},
            ]
        }
    ]
};

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);