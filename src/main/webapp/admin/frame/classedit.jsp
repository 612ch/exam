<%--
  Created by IntelliJ IDEA.
  User: demon
  Date: 2019/5/14
  Time: 8:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../../common/include.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>班级编辑</title>
    <link rel="stylesheet" href="${path}/css/layui/css/layui.css" media="all">
</head>
<body>
<form method="post" class="layui-form"
<c:if test="${classInfo.classId != null }">
    action="/class/updateClass.action"
</c:if>
        <c:if test="${classInfo.classId == null }">
            action="/class/addClass.action"
        </c:if>
> <!-- 提示：如果你不想用form，你可以换成div等任何一个普通元素 -->
    <c:if test="${classInfo.classId != null }">
        <div class="layui-form-item">
            <label class="layui-form-label">班级编号</label>
            <div class="layui-input-block">
                <input type="text" readonly="readonly" name="classId" value="${classInfo.classId}" autocomplete="off" class="layui-input">
            </div>
        </div>
    </c:if>

    <div class="layui-form-item">
        <label class="layui-form-label">班级名称</label>
        <div class="layui-input-block">
            <c:if test="${classInfo.className != null }">
                <input type="text" name="className" required value="${classInfo.className}"  placeholder="${classInfo.className}" autocomplete="off" class="layui-input">
            </c:if>
            <c:if test="${classInfo.className == null }">
            <input type="text" name="className" required  placeholder="请输入班级名称" autocomplete="off" class="layui-input">
            </c:if>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">所属年级</label>
        <div class="layui-input-block">
            <select name="gradeId" lay-filter="aihao">
                <c:if test="${classInfo.grade.gradeId!=null}">
                    <option value="${classInfo.grade.gradeId}">${classInfo.grade.gradeName}</option>
                </c:if>
                <c:forEach var="grade" items="${gradeInfoList}">
                    <option value="${grade.gradeId}">${grade.gradeName}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">班主任</label>
        <div class="layui-input-block">
            <select name="teacherId" lay-filter="aihao">
                <c:if test="${classInfo.teacher.teacherId!=null}">
                    <option value="${classInfo.teacher.teacherId}">${classInfo.teacher.teacherName}</option>
                </c:if>
                <c:forEach var="teacher" items="${teacherInfoList}">
                    <option value="${teacher.teacherId}">${teacher.teacherName}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script src="${path}/css/layui/layui.js"></script>
<script>
    layui.use('form', function () {
        var form = layui.form;
        //监听提交
       /* form.on('submit(formDemo)', function(data){
            // layer.msg(JSON.stringify(data.field));
            data.field.shstate = 1;
            // return false;

        });*/

    });
</script>
</body>
</html>