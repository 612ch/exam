<%@ page import="org.apache.shiro.SecurityUtils" %>
<%@ page import="com.qhit.pojo.TeacherInfo" %>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<jsp:include page="../../common/include.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>年级信息</title>
 	<link href="${path}/css/bootstrap/bootstrap.min.css" rel="stylesheet" />
 	<link href="${path}/css/public.css" rel="stylesheet" />
</head>
<body>
	<c:set var="power" value="${sessionScope.adminPower }"></c:set>
	<div>
		<table class="table table-striped table-hover table-condensed">
			<thead>
				<tr>
					<th>年级编号</th>
					<th>年级名称</th>
					<%--<c:if test="${((SecurityUtils.getSubject().getPrincipal()).getAdminpower())==1}">--%>
						<th>操作
							&emsp;
							<a type="button" class="btn btn-xs btn-info" onclick="_iframe(2,'/admin/frame/gradeedit.jsp')">添加</a>
						</th>
					<%--</c:if>--%>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${not empty grades }">
						<c:forEach items="${grades }" var="grade">
							<tr>
								<td>${grade.gradeId }</td>
								<td>${grade.gradeName }</td>
								<td>
									<div class="btn-group">
											<button type="button" class="btn btn-info btn-sm" onclick="_iframe(1,'/grade/selectById.action?gradeId=${grade.gradeId }')">修改</button>
											<button type="button" class="btn btn-danger btn-sm" onclick="del(${grade.gradeId },'/grade/deleteGradeInfo.action')">删除</button>
										<button type="button" class="btn btn-primary btn-sm lookclass" id="c${grade.gradeId}" >班级</button>
									</div>
								</td>
							</tr>
						</c:forEach>
					</c:when>
				</c:choose>
			</tbody>
		</table>
		<form action="class" method="post">
			<input type="hidden" value="DELETE" name="_method" />
		</form>
	</div>


	<!-- js引入 -->
    <script src="${path}/js/jquery.js"></script>
    <script src="${path}/js/bootstrap/bootstrap.min.js"></script>
	<script src="${path}/js/add-update.js"></script>
	<script src="${path}/js/layer/layer.js"></script>
    <script src="${path}/js/handle.js"></script>
    <script src="${path}/js/view-link.js"></script>

</body>
</html>