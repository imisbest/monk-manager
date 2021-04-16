<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="${pageContext.request.contextPath}/boot/js/jquery-2.2.1.min.js"></script>
    <script charset="UTF-8" src="${pageContext.request.contextPath}/echarts/echarts.min.js"></script>
    <script type="text/javascript" src="http://cdn.goeasy.io/goeasy-1.0.3.js"></script>
    <title>echarts</title>
    <script>
        var goEasy = new GoEasy({
            host: 'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
            appkey: "BS-c1e433c322534eceb85a0f7e72efd310", //替换为您的应用appkey
        });
        goEasy.subscribe({

            channel: "csw", //替换为您自己的channel
            onMessage: function (message) {
                // 手动将 字符串类型转换为 Json类型
                var data = JSON.parse(message.content);
                myChart.setOption({
                    series: [
                        {
                            name: '男',
                            type: 'bar',
                            data: data.man,
                        }, {
                            name: '女',
                            type: 'bar',
                            data: data.women,
                        }
                    ]
                })
                console.log("2");
                //alert("触发一次");
            }
        });
    </script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: 'ECharts 入门示例'
        },
        tooltip: {},
        legend: {
            data: ['男', '女']
        },
        xAxis: {
            data: ["1天", "7天", "30天", "1年"]
        },
        yAxis: {},
        series: [],
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    // Ajax异步数据回显
    $.get("${pageContext.request.contextPath}/user/usershowAll", function (data) {

        myChart.setOption({
            series: [
                {
                    name: '男',
                    type: 'bar',
                    data: data.man,
                }, {
                    name: '女',
                    type: 'bar',
                    data: data.women,
                }
            ]
        })
    }, "json")
</script>
</body>
</html>