<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../common/include.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>猿来入此 后台管理系统</title>
	<link href='${path}/images/admin/admin_index.png' rel='shortcut icon' type='image/x-icon' />
 	<link href="${path}/css/bootstrap/bootstrap.min.css" rel="stylesheet" />
	<!-- js引入 -->
    <script src="${path}/js/jquery.js"></script>
    <script src="${path}/js/bootstrap/bootstrap.min.js"></script>
</head>
	<frameset rows="15%, *" frameborder="0">
    	<frame src="${path}/admin/common/head.jsp" name="head" noresize="noresize" />
    	<frameset cols="15%, *" frameborder="0">
    		<frame src="${path}/admin/common/left.jsp" name="left" noresize="noresize" />
    		<frameset rows="7%, *">
	    		<frame src="${path}/admin/common/nav.jsp" name="nav" noresize="noresize" />
	    		<frame src="${path}/admin/home.jsp" name="right" noresize="noresize" />
    		</frameset>
    	</frameset>
    </frameset>

</html>