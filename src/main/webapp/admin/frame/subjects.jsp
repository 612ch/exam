<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="../../common/include.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>试题信息</title>
    <link href="${path }/css/bootstrap/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${path }/js/zeroModal/zeroModal.css"/>
    <link rel="stylesheet" href="${path}/js/layui/css/layui.css" media="all">
</head>
<body><!-- 试卷编号(针对手动添加试题到试卷) --><span style="display: none;" id="examPaperId">${examPaperId }</span>
<div style="text-align: center;">
    <table class="table table-striped table-hover table-condensed">
        <thead>
        <tr><%--<c:if test="${handAdd != null }">--%><%--<th>--%><%--已选:--%><%--<span id="choosed" style="color: red;">${choosed }</span>--%><%--</th>--%><%--</c:if>--%>
            <th>试题编号</th>
            <th>题目</th>
            <th>选项A</th>
            <th>选项B</th>
            <th>选项C</th>
            <th>选项D</th>
            <th>正确答案</th>
            <th>分值</th>
            <th>试题类型</th>
            <th>难易程度</th>
            <th>所属科目</th>
            <th>所属年级</th>
            <%--<c:if test="${handAdd == null }">--%>
            <th>操作&emsp;<button type="button" class="btn btn-xs btn-info"
                                onclick="subject_iframe(2,'/subject/getCoursesAndGrades.action')">添加
            </button>
            </th>
            <%--</c:if>--%></tr>
        </thead>
        <tbody><c:choose><c:when test="${not empty subjects }"><c:forEach items="${subjects }" var="subject">
            <tr id="tr-${subject.subjectId }"><c:if test="${handAdd != null }">
                <td><input type="checkbox" name="chooseSubject" id="${subject.subjectId }"/></td>
            </c:if>
                <td>${subject.subjectId }</td>
                <td><c:if test="${fn:length(subject.subjectName) > 8 }">${fn:substring(subject.subjectName, 0, 8) }<a
                        href="#"
                        onclick="subject_iframe(1,'/subject/preSubjectUpdate.action?subjectId=${subject.subjectId }')">[...]</a></c:if><c:if
                        test="${fn:length(subject.subjectName) <= 8 }">${subject.subjectName }</c:if></td>
                <td><c:if test="${fn:length(subject.optionA) > 5 }"> ${fn:substring(subject.optionA, 0, 5) } <sub><a
                        href="#"
                        onclick="subject_iframe(1,'/subject/preSubjectUpdate.action?subjectId=${subject.subjectId }')">[...]</a></sub> </c:if><c:if
                        test="${fn:length(subject.optionA) <= 5 }"> ${subject.optionA }</c:if></td>
                <td><c:if test="${fn:length(subject.optionB) > 5 }"> ${fn:substring(subject.optionB, 0, 5) } <sub><a
                        href="#"
                        onclick="subject_iframe(1,'/subject/preSubjectUpdate.action?subjectId=${subject.subjectId }')">[...]</a></sub> </c:if><c:if
                        test="${fn:length(subject.optionB) <= 5 }"> ${subject.optionB }</c:if></td>
                <td><c:if test="${fn:length(subject.optionC) > 5 }"> ${fn:substring(subject.optionC, 0, 5) } <sub><a
                        href="#"
                        onclick="subject_iframe(1,'/subject/preSubjectUpdate.action?subjectId=${subject.subjectId }')">[...]</a></sub> </c:if><c:if
                        test="${fn:length(subject.optionC) <= 5 }"> ${subject.optionC }</c:if></td>
                <td><c:if test="${fn:length(subject.optionD) > 5 }"> ${fn:substring(subject.optionD, 0, 5) } <sub><a
                        href="#"
                        onclick="subject_iframe(1,'/subject/preSubjectUpdate.action?subjectId=${subject.subjectId }')">[...]</a></sub> </c:if><c:if
                        test="${fn:length(subject.optionD) <= 5 }"> ${subject.optionD }</c:if></td>
                <td>${subject.rightResult }</td>
                <td id="subjectScore">${subject.subjectScore }</td>
                <td><c:if test="${subject.subjectType == 0 }">单选</c:if><c:if
                        test="${subject.subjectType == 1 }">多选</c:if><c:if
                        test="${subject.subjectType == 2 }">简答</c:if></td>
                <td><c:if test="${subject.subjectEasy == 0 }">简单</c:if><c:if
                        test="${subject.subjectEasy == 1 }">普通</c:if><c:if
                        test="${subject.subjectEasy == 2 }">困难</c:if></td>
                <td>${subject.course.courseName }</td>
                <td>${subject.grade.gradeName }</td>
                <td><%--<c:if test="${handAdd == null }">--%>
                    <div class="btn-group">
                        <button type="button" class="btn btn-info btn-sm"
                                onclick="subject_iframe(1,'/subject/preSubjectUpdate.action?subjectId=${subject.subjectId }')">
                            修改
                        </button>
                        <button type="button" class="btn btn-danger btn-sm delSubject"
                                onclick="del(${subject.subjectId },'/subject/delSubject.action')">删除
                        </button>
                    </div>
                        <%--</c:if>--%></td>
            </tr>
        </c:forEach></c:when></c:choose></tbody>
    </table>
    <form action="class" method="post"><input type="hidden" value="DELETE" name="_method"/></form>
    <div id="fenye"></div>
</div><!-- js引入 -->
<script src="${path }/js/jquery.js"></script>
<script src="${path }/js/kindeditor/kindeditor-min.js"></script>
<script src="${path }/js/bootstrap/bootstrap.min.js"></script>
<script src="${path }/js/zeroModal/zeroModal.min.js"></script>
<script src="${path }/js/add-update.js"></script>
<script src="${path }/js/handle.js"></script>
<script src="${path}/js/layer/layer.js"></script>
<script src="${path}/js/layui/layui.js"></script>
<script type="text/javascript"> layui.use(['laypage', 'layer'], function () {
    var laypage = layui.laypage;
    var layer = layui.layer;
    laypage.render({
        elem: 'fenye',
        count: ${totalCount},
        limit: 11,
        curr:${pageNow},
        theme: '#1E9FFF',
        jump: function (obj, first) {
            if (!first) {
                location.href = path + '/subject/subjectList.action?pageNow=' + obj.curr;
            }
        }
    });
});</script>
</body>
</html>