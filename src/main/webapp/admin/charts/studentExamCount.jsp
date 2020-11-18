<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../../common/include.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>学生考试次数统计</title>
    <link href="${path}/css/bootstrap/bootstrap.min.css" rel="stylesheet" />
    <!-- js引入 -->
    <script src="${path}/js/jquery.js"></script>
    <script src="${path}/js/echarts/echarts.js"></script>
    <script src="${path}/js/echarts/shine.js"></script>
    <style type="text/css">
        li {list-style: none;float: left;margin-left: 9px;}
        a{text-decoration:none;}
        a:HOVER{text-decoration: none;}
        a:link{text-decoration:none; }
        a:visited{text-decoration:none; }
        a:active{text-decoration:none;}
        li a {cursor: pointer;}
    </style>
</head>
<body>
<div style="width: 100%; height: 500px;">
    <div style="width: 104px; height: 100%;float: left; margin-left: 10px;">
        <span style="display: none;" id="tid">${sessionScope.user.teacherId }</span>
        <strong>描述：</strong>
        <p style="font-size: 12px;">当前图表所描述了本班
            所有学生考试信息
        </p>
        <div>
            <strong>班级学生：</strong>
            <ul id="stusList">
                <li><a href="#" onclick="x()">全部</a></li>
                    <c:forEach items="${students}" var="student" >
                        <a href="#" onclick="e('${student.studentName}')"><li>${student.studentName}</li></a>
                    </c:forEach>
            </ul>
        </div>
    </div>
    <div id="stuExamInfo" style="margin-left: 100px; width:750px; height: 400px; float: left; border: 1px solid #E5E5E5;"></div>
</div>
<script type="text/javascript">
    window.onload=function(){
        x();
    }
    function x() {
        //点击全部的时候
        $.ajax(
            {
                type:"POST",
                url:path+'/student/Echars.action?teacherId='+${sessionScope.user.teacherId},
                dataType:'json',
                success:function (result) {

                    var names=[];
                    var values=[];
                    if (result) {
                        for (var i = 0; i < result.length; i++) {
                            names.push(result[i].name);    //挨个取出填入类别数组
                        }
                        for (var i = 0; i < result.length; i++) {
                            values.push(result[i].value);    //挨个取出填入销量数组
                        }
                    }
                    var myChart = echarts.init(document.getElementById('stuExamInfo'));
                    var option = {
                        title:{
                            text:'ECharts 班级考试信息统计'
                        },
                        tooltip:{},
                        legend:{
                            data:['学生考试次数统计']
                        },
                        /*试卷列表*/
                        xAxis:{
                            /*点击全部的时候，获取的是学生的姓名列表*/
                            /*点击单个，获取的是学生考试过的试卷名*/

                            data:names
                        },
                        yAxis:{
                        },
                        series:[{
                            name:'学生考试次数',
                            type:'bar',
                            /*点击全部是，考试次数*/
                            /*点击单个是，每个试卷所对应的分数*/
                            data:values,
                            markPoint: {
                                data: [
                                    {type: 'max', name: '最大值'},
                                    {type: 'min', name: '最小值'}
                                ]
                            },
                            markLine:{
                                data:[
                                    {type:'average',name:'平均值',itemStyle:{
                                            normal:{
                                                color:'blue'
                                            }
                                        }}
                                ]
                            }
                        }]
                    };
                    //使用制定的配置项和数据显示图表
                    myChart.setOption(option);
                }
            }
        )

    }




    function e(studentName) {
        $.ajax(
            {
                type:"POST",
                url:path+'/student/getExamPaperANDexamScore.action?studentName='+studentName,
                dataType:'json',
                success:function (result) {
                    var names=[];
                    var values=[];
                    if (result) {
                        for (var i = 0; i < result.length; i++) {
                            names.push(result[i].name);    //挨个取出填入类别数组
                        }
                        for (var i = 0; i < result.length; i++) {
                            values.push(result[i].value);    //挨个取出填入销量数组
                        }
                    }
                    var myChart = echarts.init(document.getElementById('stuExamInfo'));
                    var option = {
                        title:{
                            text:'ECharts 班级考试信息统计'
                        },
                        tooltip:{},
                        legend:{
                            data:['学生考试成绩统计']
                        },
                        /*试卷列表*/
                        xAxis:{
                            /*点击单个，获取的是学生考试过的试卷名*/
                            data:names
                        },
                        yAxis:{
                        },
                        series:[{
                            name:'学生考试成绩',
                            type:'line',

                            /*点击单个是，每个试卷所对应的分数*/
                            data:values,
                            markPoint: {
                                data: [
                                    {type: 'max', name: '最大值'},
                                    {type: 'min', name: '最小值'}
                                ]
                            },
                            markLine:{
                                data:[
                                    {type:'average',name:'平均值',itemStyle:{
                                            normal:{
                                                color:'blue'
                                            }
                                        }}
                                ]
                            }
                        }]
                    };
                    //使用制定的配置项和数据显示图表
                    myChart.setOption(option);
                }
            }
        )
    }
</script>

</body>
</html>