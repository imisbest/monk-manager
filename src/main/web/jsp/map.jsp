<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <!-- 引入 echarts.js -->
    <script src="${pageContext.request.contextPath}/echarts/echarts.min.js" charset="UTF-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/echarts/china.js" charset="UTF-8"></script>
    <script src="${pageContext.request.contextPath}/boot/js/jquery-2.2.1.min.js"></script>
    <script type="text/javascript" src="http://cdn.goeasy.io/goeasy-1.0.3.js"></script>
    <script>
        /* var goEasy = new GoEasy({
           host: 'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
           appkey: "BC-8c7f344f2ffc4974a88531acc725c2f9" //替换为您的应用appkey
       });
       goEasy.publish({
           channel: "csw", //替换为您自己的channel
           message: "AAA" //替换为您想要发送的消息内容
       });*/
        var goEasy = new GoEasy({
            host: 'singapore.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
            appkey: "BS-3d8b37ff41984c1abb689e5b68fec5da", //替换为您的应用appkey
        });
        goEasy.subscribe({

            channel: "cmfz", //替换为您自己的channel
            onMessage: function (message) {
                console.log("message]" + message);

                // 手动将 字符串类型转换为 Json类型
                var data = JSON.parse(message.content);
                console.log("data]" + data);
                myChart.setOption({
                    series: [
                        {
                            name: '男',
                            type: 'map',
                            mapType: 'china',
                            roam: false,
                            label: {
                                normal: {
                                    show: false
                                },
                                emphasis: {
                                    show: true
                                }
                            },
                            data:
                            data.man
                        },
                        {
                            name: '女',
                            type: 'map',
                            mapType: 'china',
                            roam: false,
                            label: {
                                normal: {
                                    show: false
                                },
                                emphasis: {
                                    show: true
                                }
                            },
                            data:
                            data.women
                        }]
                });
                console.log("2");
                //alert("触发一次");
            }
        });
    </script>

</head>

<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="userMap" style="width: 600px;height:400px;"></div>
<script type="text/javascript">

    // $(function () {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('userMap'));


    var option = {
        title: {
            text: '用户分布图',
            subtext: '纯属虚构',
            left: 'center'
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['男', '女']
        },

        visualMap: {
            left: 'left',
            top: 'bottom',
            text: ['高', '低'],           // 文本，默认为数值文本
            calculable: true
        },
        toolbox: {
            show: true,
            orient: 'vertical',
            left: 'right',
            top: 'center',
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        }
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

    $.get("${pageContext.request.contextPath}/user/showAllMap", function (data) {
        myChart.setOption({
            series: [
                {
                    name: '男',
                    type: 'map',
                    mapType: 'china',
                    roam: false,
                    label: {
                        normal: {
                            show: false
                        },
                        emphasis: {
                            show: true
                        }
                    },
                    data:
                    data.man
                },
                {
                    name: '女',
                    type: 'map',
                    mapType: 'china',
                    roam: false,
                    label: {
                        normal: {
                            show: false
                        },
                        emphasis: {
                            show: true
                        }
                    },
                    data:
                    data.women
                }]
        })
    }, "json");
    // });

</script>


</body>
</html>