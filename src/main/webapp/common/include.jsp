<%--
  Created by IntelliJ IDEA.
  User: demon
  Date: 2019/4/11
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
    request.setAttribute("path",basePath);
%>
<script type="application/javascript" language="JavaScript">
    var path="${path}";
</script>

