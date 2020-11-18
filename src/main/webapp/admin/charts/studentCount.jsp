<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../../common/include.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>班级人数统计</title>
	<link href="${path}/css/bootstrap/bootstrap.min.css" rel="stylesheet" />
	<!-- js引入 -->
	<script src="${path}/js/jquery.js"></script>
	<script src="${path}/js/echarts/echarts.js"></script>
	<script src="${path}/js/echarts/shine.js"></script>
	<style type="text/css">
		li {list-style: none;}
		a{text-decoration:none;}
		a:HOVER{text-decoration: none;}
		a:link{text-decoration:none; }
		a:visited{text-decoration:none; }
		a:active{text-decoration:none;}
		li a {cursor: pointer;}
	</style>
</head>
<body>

<div style="width: 100%; height: 100%;">
	<div style="width: 100%; height: 10%;float: left;matgin-bottom:10px;">
		<c:forEach items="${grades }" var="grade">
			<button type="button" class="btn btn-info btn-sm" onclick="loadShopLine(${grade.gradeId })">${grade.gradeName }</button>
		</c:forEach>
		<button type="button" class="btn btn-info btn-sm" onclick="loadShopLine(-1)">全部</button>
	</div>
	<%--<div id="studentCount" style="width:1000px; height: 400px; float: left; border: 1px solid #E5E5E5;"></div>--%>
	<%--
            <div id="main" style="width: 100%;height:400px;"></div>--%>
	<div id="chart" style="width: 100%; height: 400px;"></div>
</div>
<script src="${path}/js/jquery.js"></script>
<script>
    $(document).ready(function(){
        var chart = document.getElementById('chart');
        var chartData = echarts.init(chart);

        chartData.setOption({
            title: {
                text: '各班级人数统计'
            },
            tooltip: {},
            legend: {
                data:['人数']
            },
            xAxis: {
                data: []
            },
            yAxis: {},
            series: [{
                name: '人数',
                type: 'bar',
                data: []
            }]
        });

        $.get('stuCount.action').done(function (data) {
            var calssName=[];
            var studentNum=[];
            for (var i = 0; i < data.length; i++) {
                calssName.push(data[i].className);
            }
            for (var i = 0; i < data.length; i++) {
                studentNum.push(data[i].studentNum);
            }
            chartData.setOption({
                xAxis: {
                    type: 'category',
                    /*boundaryGap: false,*/
                    data: calssName
                },
                series: [{
                    name: '人数',
                    /*type: 'line',
                    smooth: true,*/
                    data: studentNum
                }]
            });

        });


        chartData.on("click",eConsole);
    });

</script>
<%--<script type="text/javascript">
    var studentCount = echarts.init(document.getElementById("studentCount"), "shine");

    //加载数据
    function loadShopLine(flag) {
        studentCount.clear();

        // 指定图表的配置项和数据
        var option = {
            title: { //标题
                text: 'ECharts 入门示例'
            },
            tooltip: {}, //提示框
            legend: { //图例
                data:['销量']
            },
            xAxis: { //x轴坐标系
                data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
            },
            yAxis: {}, //y轴坐标系
            series: [{ //列表，确定图表类型、数据等
                name: '销量',
                type: 'bar',
                data: [5, 20, 36, 10, 10, 20]
            }]
        };
    }
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    /*
    //载入折线图
    loadShopLine(-1);*/
</script>--%>
<%--<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    $.get('stuCount.action').done(function (data) {
        var calssName=[];
        var studentNum=[];
        if (data) {
            for (var i = 0; i < result.length; i++) {
                names.push(result[i].calssName);
            }
            for (var i = 0; i < result.length; i++) {
                values.push(result[i].studentNum);
            }
        }
        // 指定图表的配置项和数据
        var option = {
            title: { //标题
                text: '班级学生数量统计'
            },
            tooltip: {}, //提示框
            legend: { //图例
                data: ['人数']
            },
            xAxis: { //x轴坐标系
                data: calssName
            },
            yAxis: {}, //y轴坐标系
            series: [{ //列表，确定图表类型、数据等
                name: '人数',
                type: 'bar',
                data: studentNum
            }]
        };
    }

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>--%>
</body>
</html>