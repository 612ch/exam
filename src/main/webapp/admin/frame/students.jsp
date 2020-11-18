<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../../common/include.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>学生信息管理</title>

	<link href="${path }/css/bootstrap/bootstrap.min.css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${path }/js/zeroModal/zeroModal.css" />
	<link rel="stylesheet" href="${path}/js/layui/css/layui.css"  media="all">
</head>
<body>

<div style="text-align: center;">
	<table class="table table-striped table-hover table-condensed">
		<thead>
		<tr>
			<th>学生编号</th>
			<th>学生姓名</th>
			<th>学生账户</th>
			<th>登录密码</th>
			<th>就读班级</th>
			<th>就读年级</th>
			<%--<c:if test="${sessionScope.adminPower == 1 }">--%>
			<th>操作</th>
			<%--</c:if>--%>
		</tr>
		</thead>
		<tbody>
		<c:choose>
			<c:when test="${not empty students }">
				<c:forEach items="${students }" var="student">
					<tr>
						<td>${student.studentId }</td>
						<td>${student.studentName }</td>
						<td>${student.studentAccount }</td>
						<td>
							<span id="hidePwd">******</span>
							<span id="showPwd" style="display: none">${student.studentPwd }</span>
							<button type="button" class="btn btn-info btn-xs viewPwd">查看</button>
						</td>
						<td>${student.classInfo.className }</td>
						<td>${student.grade.gradeName }</td>
						<td>
							<div class="btn-group">
								<button type="button" class="btn btn-info btn-sm" onclick="_iframe(1,'/student/selectByPrimaryKey.action?studentId=${student.studentId}')">修改</button>
								<button type="button" class="btn btn-danger btn-sm" onclick="del(${student.studentId},'/student/deleteByPrimaryKey.action')">删除</button>
							</div>
						</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
		</tbody>
	</table>
	<form action="student" method="post">
		<input type="hidden" value="DELETE" name="_method" />
	</form>
	<div id="fenye"></div>
</div>

<!-- js引入 -->
<script src="${path}/js/jquery.js"></script>
<script src="${path}/js/bootstrap/bootstrap.min.js"></script>
<script src="${path}/js/handle.js"></script>
<script src="${path}/js/zeroModal/zeroModal.min.js"></script>
<script src="${path}/js/add-update.js"></script>
<script src="${path}/js/layer/layer.js"></script>
<script src="${path}/js/layui/layui.js"></script>
<script type="text/javascript">
	$(function() {
		$(".viewPwd").click(function() {
			var pwd0 = $(this).siblings("#hidePwd").text();
			if (pwd0.indexOf("*") != -1) {
				var pwd = $(this).siblings("#showPwd").text();
				$(this).siblings("#hidePwd").text(pwd);
				return;
			} else {
				$(this).siblings("#hidePwd").text("******");
			}
		});
	});

	layui.use(['laypage', 'layer'], function() {
		var laypage = layui.laypage;
		var layer = layui.layer;
		laypage.render({
			elem: 'fenye'
			,count: ${totalCount}
			,limit: 11
			,curr:${pageNow}
			,theme: '#1E9FFF'
			,jump: function (obj,first) {
				if(!first){
					location.href=path+'/student/studentList.action?pageNow='+obj.curr;
				}
			}
		});
	});
</script>
</body>
</html>