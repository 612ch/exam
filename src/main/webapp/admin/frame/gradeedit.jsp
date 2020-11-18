<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Insert title here</title>
    <jsp:include page="../../common/include.jsp"/>
 	<link href="${path }/css/bootstrap/bootstrap.min.css" rel="stylesheet" />
 	<link href="${path }/css/form-public.css" rel="stylesheet" />
</head>
<body>

<div style="text-align: center;">
	<%--<form:form  method="POST" modelAttribute="grade" id="gradeAction">--%>
		<%--<c:if test="${grade.gradeId != null }">--%>
			<%--年级编号：<form:input path="gradeId" readonly="readonly" unselectable='on' onfocus='this.blur()' class="ipt" />--%>
			<%--<input type="hidden" value="PUT" name="_method" class="ipt" />--%>
		<%--</c:if>--%>
		<%--<br /><br />--%>
		<%--年级名称：<form:input path="gradeName" class="ipt" />--%>
		<%--<br /><br />--%>
		<%--<c:if test="${grade.gradeId != null }">--%>
			<%--<input type="button" onclick="onSubmit(1,'/grade/updateGradeInfo.action','#gradeAction')" value="提交" class="sub" />--%>
		<%--</c:if>--%>
		<%--<input type="button" onclick="onSubmit(2,'/grade/add.action','#gradeAction')" value="提交" class="sub" />--%>
	<%--</form:form>--%>
	<form id="gradeAction" method="POST">
		<c:if test="${grade.gradeId != null }">
			年级编号：<input type="text" id="gradeId" name="gradeId" value="${grade.gradeId}" readonly="readonly"  class="ipt" />
		</c:if>
		<br /><br />
		年级名称：<input type="text" id="gradeName" name="gradeName" value="${grade.gradeName}" class="ipt" />
		<br /><br />
		<c:if test="${grade.gradeId != null }">
			<input type="button" onclick="onSubmit(1,'/grade/updateGradeInfo.action','#gradeAction')" value="提交" class="sub" />
		</c:if>
		<c:if test="${grade.gradeId == null }">
			<input type="button" onclick="onSubmit(2,'/grade/add.action','#gradeAction')" value="提交" class="sub" />
		</c:if>
	</form>
</div>


	<!-- js引入 -->
    <script src="${path }/js/jquery.js"></script>
    <script src="${path }/js/bootstrap/bootstrap.min.js"></script>
    <script src="${path}/js/layer/layer.js"></script>
    <script src="${path }/js/form-public.js"></script>
    <script src="${path }/js/add-update.js"></script>

</body>
</html>