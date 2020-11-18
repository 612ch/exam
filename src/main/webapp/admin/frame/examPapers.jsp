<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../../common/include.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>试卷信息管理</title>
 	<link href="${path}/css/bootstrap/bootstrap.min.css" rel="stylesheet" />
 	<link rel="stylesheet" type="text/css" href="${path}/js/zeroModal/zeroModal.css" />
	<link rel="stylesheet" href="${path}/js/layui/css/layui.css"  media="all">

</head>
<body>

	<div style="text-align: center">
		<table class="table table-striped table-hover table-condensed">
			<thead>
				<tr>
					<th>试卷编号</th>
					<th>试卷名称</th>
					<th>题目数量</th>
					<th>总分</th>
					<th>考试时长</th>
					<th>分科情况</th>
					<th>难易程度</th>
					<th>所属年级</th>
					<th>操作
						<button type="button" class="btn btn-xs btn-info" onclick="exam_iframe(0, '/examPaper/toAdd.action')">添加</button>
					</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${not empty exmapapers.result }">
						<c:forEach items="${exmapapers.result }" var="ep">
							<tr class="subject_info">
								<td>${ep.examPaperId }</td>
								<td>${ep.examPaperName }</td>
								<td id="examPaper-sn${ep.examPaperId }">${ep.subjectNum }</td>
								<td id="examPaper-score${ep.examPaperId }">${ep.examPaperScore }</td>
								<td>${ep.examPaperTime }</td>
								<td>
									<c:if test="${ep.division == 0 }">
										未分科
									</c:if>
									<c:if test="${ep.division == 1 }">
										文科
									</c:if>
									<c:if test="${ep.division == 2 }">
										理科
									</c:if>
								</td>
								<td>
									<c:if test="${ep.examPaperEasy == 0 }">
										简单
									</c:if>
									<c:if test="${ep.examPaperEasy == 1 }">
										普通
									</c:if>
									<c:if test="${ep.examPaperEasy == 2 }">
										困难
									</c:if>
								</td>
								<td>${ep.grade.gradeName }</td>
								<td>
									<div class="btn-group">
										<button type="button" class="btn btn-info btn-sm" onclick="exam_iframe(1, '/examPaper/toUpdate.action?examPaperId=${ep.examPaperId }', 'examPapers')">修改</button>
										<button type="button" class="btn btn-danger btn-sm" onclick="del('${ep.examPaperId}','/examPaper/delete.action')">删除</button>
										<button type="button" id="${ep.examPaperId }" class="btn btn-info btn-sm examPaper-subjects">查看试题</button>
										<button type="button" onclick="examPaperAddSubjects(${ep.examPaperId })" class="btn btn-info btn-sm">添加试题</button>
										<button type="button" onclick="autoAddSubjects(${ep.examPaperId })" class="btn btn-info btn-sm">生成试题</button>
									</div>
								</td>
							</tr>
						</c:forEach>
					</c:when>
				</c:choose>
			</tbody>
		</table>
		<%--<form action="student" method="post">
			<input type="hidden" value="DELETE" name="_method" />
		</form>
		<c:if test="${exmapapers.pageCount>1 }">
			<div>
				<ul class="pagination">
					<c:if test="${exmapapers.pageNow-1 > 0 }">
						<li><a href="${path}/examPaper/getAll.action${exmapapers.pageNow-1 }">&laquo;</a></li>
					</c:if>
					<c:forEach begin="1" end="${pageTotal }" step="1" var="pageNo">
						<c:if test="${exmapapers.pageNow == pageNo }">
							<li class="active"><a href="examPapers?startPage=${pageNo }">${pageNo }</a></li>
						</c:if>
						<c:if test="${exmapapers.pageNow != pageNo }">
							<li><a href="${path}/examPaper/getAll.action${pageNo }" class="pageLink">${pageNo }</a></li>
						</c:if>
					</c:forEach>
					<c:if test="${exmapapers.pageNow+1 <= pageTotal }">
						<li><a href="${path}/examPaper/getAll.action${exmapapers.pageNow+1 }">&raquo;</a></li>
					</c:if>
				</ul>
			</div>
		</c:if>--%>
		<%--<div style="height: 114px">
			<ul class="pagination">
				&lt;%&ndash;<c:if test="${exmapapers.pageCount > 1 }">&ndash;%&gt;
				&lt;%&ndash;	<ul class="pagination">
						<li><a href="${path}/examPaper/getAll.action?page=1">首页</a></li>
						<c:if test="${exmapapers.pageNow-1 > 0 }">
							<li><a href="${path}/examPaper/getAll.action?page=${exmapapers.pageNow-1 }">上一页</a></li>
						</c:if>
						<c:forEach begin="${exmapapers.pageNow }" end="${exmapapers.pageNow+4 }" var="subPage">
							<c:if test="${subPage-5 > 0 }">
								<li><a href="${path}/examPaper/getAll.action?page=${subPage-5 }">${subPage-5 }</a></li>
							</c:if>
						</c:forEach>
						<c:forEach begin="${exmapapers.pageNow }" end="${exmapapers.pageNow+5 }" step="1" var="pageNo">
							<c:if test="${pageNo <= exmapapers.pageCount }">
								<c:if test="${exmapapers.pageNow == pageNo }">
									<li class="active"><a href="${path}/examPaper/getAll.action?page=${pageNo }">${pageNo }</a></li>
								</c:if>
								<c:if test="${exmapapers.pageNow != pageNo }">
									<li><a href="${path}/examPaper/getAll.action?page=${pageNo }" class="pageLink">${pageNo }</a></li>
								</c:if>
							</c:if>
						</c:forEach>
						<c:if test="${exmapapers.pageNow+1 <= exmapapers.pageCount }">
							<li><a href="${path}/examPaper/getAll.action?page=${exmapapers.pageNow+1 }">下一页</a></li>
						</c:if>
						<li><a href="${path}/examPaper/getAll.action?page=${exmapapers.pageCount }">尾页</a></li>
						<li>
							<a>${exmapapers.pageNow }/${exmapapers.pageCount }</a>
						</li>
						<li>
							<div style="width:-1%; height:100%;float:right;">
								<form action="${path}/examPaper/getAll.action" id="scannerPageForm">
									<input id="scannerPage" type="text" name="page" style="width: 40px; height: 30px; border: 1px solid gray; border-radius: 4px;" />
									<input class="btn btn-default goPage" type="submit" value="Go" style="margin-left: -4px; height: 30px;" />
								</form>
							</div>
						</li>
					</ul>
				&lt;%&ndash;</c:if>&ndash;%&gt;&ndash;%&gt;
			</ul>
		</div>--%>
		<div id="pageContainer"></div>
	</div>

	<!-- js引入 -->
    <script src="${path}/js/jquery.js"></script>
    <script src="${path}/js/bootstrap/bootstrap.min.js"></script>
    <script src="${path}/js/handle.js"></script>
    <script src="${path}/js/zeroModal/zeroModal.min.js"></script>
    <script src="${path}/js/add-update.js"></script>
   	<script src="${path}/js/view-link.js"></script>
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
                ,count: ${exmapapers.classCount}
                ,limit: 12
                ,curr:${exmapapers.pageNow}
                ,theme: '#009688'
                ,layout: ['count', 'prev', 'page', 'next', 'refresh', 'skip']
                ,jump: function (obj,first) {
                    if(!first){
                        location.href=path+'/examPaper/getAll.action?page='+obj.curr;
                    }
                }
            });
        });
	</script>
</body>
</html>