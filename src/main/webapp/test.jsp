<%--
  Created by IntelliJ IDEA.
  User: demon
  Date: 2019/5/7
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="common/include.jsp"/>
<html>
<head>
    <title>班级信息全部显示</title>
    <link rel="stylesheet" type="text/css" href="${path}/css/table.css">
</head>
<body>
<table class="table">
    <thead><th>classId</th><th>className</th><th>gradeId</th><th>teacherId</th></thead>
    <tbody>
    <c:forEach items="${classlist}" var="class">
        <tr><td>${class.classid}</td><td>${class.classname}</td><td>${class.gradeid}</td><td>${class.teacherid}</td></tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
