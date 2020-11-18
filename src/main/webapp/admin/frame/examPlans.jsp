<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>考试安排信息</title>
	<jsp:include page="../../common/include.jsp"/>
 	<link href="${path }/css/bootstrap/bootstrap.min.css" rel="stylesheet" />
 	<link rel="stylesheet" type="text/css" href="${path }/js/zeroModal/zeroModal.css" />
</head>
<body>

	<div>
		<table class="table table-striped table-hover table-condensed">
			<thead>
				<tr>
					<th>编号</th>
					<th>考试班级</th>
					<th>考试科目</th>
					<th>试卷名称</th>
					<th>题目数量</th>
					<th>开考时间</th>
					<th>操作
						&emsp;
						<a type="button" onclick="_iframe(2,'/examPlanInfo/toAdd.action')" class="btn btn-xs btn-info" target="right">添加</a>
					</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${not empty examPlans }">
						<c:forEach items="${examPlans }" var="plan">
							<tr>
								<%--<td>${plan.examPaper.examPaperId }</td>--%>
								<td>${plan.examPlanId }</td>
								<td>${plan.clazz.className }</td>
								<td>${plan.course.courseName }</td>
								<td>${plan.examPaper.examPaperName }</td>
								<td>${plan.examPaper.subjectNum }</td>
								<td>${plan.beginTime }</td>
								<td>
									<div class="btn-group">
										<button type="button" class="btn btn-info btn-sm" onclick="_iframe(1,'/examPlanInfo/getExamPlanByExamPlanId.action?examPlanId=${plan.examPlanId}')">修改</button>
										<%--<button type="button" class="btn btn-info btn-sm" onclick="_iframe(1,'/examPlanInfo/getExamPlanByExamPlanId.action?examPlanId=${plan.examPlanId}')">修改</button>--%>

										<button type="button" class="btn btn-danger btn-sm" onclick="del(${plan.examPlanId},'/examPlanInfo/delExamPlanInfo.action')">移除</button>

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
    <script src="${path }/js/jquery.js"></script>
    <script src="${path }/js/zeroModal/zeroModal.min.js"></script>
    <script src="${path }/js/bootstrap/bootstrap.min.js"></script>

	<script src="${path }/js/layer/layer.js"></script>
	<script src="${path }/js/examplan.js"></script>
	<script src="${path }/js/delexamplan.js"></script>
</body>
</html>