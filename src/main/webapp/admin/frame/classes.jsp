<%@ page import="com.qhit.pojo.TeacherInfo" %>
<%@ page import="org.apache.shiro.SecurityUtils" %>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<jsp:include page="../../common/include.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>班级信息</title>
	<link rel="stylesheet" href="${path}js/layer/theme/default/layer.css" media="all">
 	<link href="${path}/css/bootstrap/bootstrap.min.css" rel="stylesheet" />
	<link rel="stylesheet" href="${path}/js/layui/css/layui.css"  media="all">
</head>
<body>

	<div style="text-align: center">
		<table class="table table-striped table-hover table-condensed">
			<thead>
				<tr>
					<th>班级编号</th>
					<th>班级名称</th>
					<th>所属年级</th>
					<th>班主任</th>
					<c:if test="${user.adminPower == 1}">
						<%--<th>操作
							<a href="javascript:_iframe(0,'/class/toAddClass.action');" class="btn btn-xs btn-info">添加</a>
						</th>--%>
						<th>操作
							<a href="${path}/class/toAddClass.action" class="btn btn-xs btn-info">添加</a>
						</th>
					</c:if>
				</tr>
			</thead>
			<tbody>
						<c:forEach items="${classes.result }" var="clazz">
							<tr>
								<td>${clazz.classId }</td>
								<td>${clazz.className }</td>
								<td>${clazz.grade.gradeName }</td>
								<td>
									<c:if test="${clazz.teacher.teacherName == null }">
										待定
									</c:if>
									<c:if test="${clazz.teacher.teacherName != null }">
										${clazz.teacher.teacherName }
									</c:if>
								</td>
								<td>
                                    <c:if test="${user.adminPower == 1}">
										<div class="btn-group">
											<a class="btn btn-info btn-sm" href="/class/toUpdateClass.action?cid=${clazz.classId}">修改</a>
											<button type="button" class="btn btn-danger btn-sm" onclick="delClass(${clazz.classId})">删除</button>
										</div>
									</c:if>
								</td>
							</tr>
						</c:forEach>
			</tbody>
		</table>
<%--		<div style="height: 114px">
			<ul class="pagination">
				<c:if test="${classes.pageCount > 1 }">
					<ul class="pagination">
						<li><a href="${path}/class/classList.action?page=1">首页</a></li>
						<c:if test="${classes.pageNow-1 > 0 }">
							<li><a href="${path}/class/classList.action?page=${classes.pageNow-1 }">上一页</a></li>
						</c:if>
						<c:forEach begin="${classes.pageNow }" end="${classes.pageNow+4 }" var="subPage">
							<c:if test="${subPage-5 > 0 }">
								<li><a href="${path}/class/classList.action?page=${subPage-5 }">${subPage-5 }</a></li>
							</c:if>
						</c:forEach>
						<c:forEach begin="${classes.pageNow }" end="${classes.pageNow+5 }" step="1" var="pageNo">
							<c:if test="${pageNo <= classes.pageCount }">
								<c:if test="${classes.pageNow == pageNo }">
									<li class="active"><a href="${path}/class/classList.action?page=${pageNo }">${pageNo }</a></li>
								</c:if>
								<c:if test="${classes.pageNow != pageNo }">
									<li><a href="${path}/class/classList.action?page=${pageNo }" class="pageLink">${pageNo }</a></li>
								</c:if>
							</c:if>
						</c:forEach>
						<c:if test="${classes.pageNow+1 <= classes.pageCount }">
							<li><a href="${path}/class/classList.action?page=${classes.pageNow+1 }">下一页</a></li>
						</c:if>
						<li><a href="${path}/class/classList.action?page=${classes.pageCount }">尾页</a></li>
						<li>
							<a>${classes.pageNow }/${classes.pageCount }</a>
						</li>
						<li>
							<div style="width:-1%; height:100%;float:right;">
								<form action="${path}/class/classList.action" id="scannerPageForm">
									<input id="scannerPage" type="text" name="page" style="width: 40px; height: 30px; border: 1px solid gray; border-radius: 4px;" />
									<input class="btn btn-default goPage" type="submit" value="Go" style="margin-left: -4px; height: 30px;" />
								</form>
							</div>
						</li>
					</ul>
				</c:if>
			</ul>
		</div>--%>
		<div id="pageContainer"></div>

	</div>


	<!-- js引入 -->
    <script src="${path}/js/jquery.js"></script>
    <script src="${path}/js/class.js"></script>
    <script src="${path}/js/bootstrap/bootstrap.min.js"></script>
    <script src="${path}/js/handle.js"></script>
	<script src="${path}/js/add-update.js"></script>
	<script src="${path}/js/layer/layer.js"></script>
	<script src="${path}/js/layui/layui.js"></script>

	<script>

        layui.use(['layer', 'laypage'], function(){
            var layer = layui.layer
                ,laypage= layui.laypage;
            laypage.render({
                elem: 'pageContainer'
                /*,count: 100*/
              /*  ,theme: '#1E9FFF'*/
                ,count: ${classes.classCount}
                ,limit: 11
                ,curr:${classes.pageNow}
                ,theme: '#009688'
                ,layout: ['count', 'prev', 'page', 'next', 'refresh', 'skip']
                ,jump: function (obj,first) {
                    if(!first){
                        location.href=path+'/class/classList.action?page='+obj.curr;
                    }
                }
            });
        });
	</script>
</body>
</html>