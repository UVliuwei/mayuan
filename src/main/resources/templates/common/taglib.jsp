<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<c:set var="imgctx" value="${pageContext.request.contextPath }" />
<c:set var="user" value="${sessionScope.user }" />
