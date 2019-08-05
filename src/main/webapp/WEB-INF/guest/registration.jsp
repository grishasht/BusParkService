<%--
  Created by IntelliJ IDEA.
  User: hs
  Date: 5/29/19
  Time: 12:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.curLang}"/>
<fmt:setBundle basename="lang"/>

<html>
<head>
    <title><fmt:message key="register.registration"/></title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <form style="width: 30%; margin: auto" method="post">
        <h2 style="margin-top: 10%; color: #005cbf; position: center"><fmt:message key="register.registration"/></h2>
        <div class="form-group">
            <label for="inputEmail1"><fmt:message key="register.email"/></label>
            <input name="email"
                   type="email" class="form-control mr-sm-2" id="inputEmail1" aria-describedby="emailHelp"
                   placeholder="<fmt:message key="register.enter.email"/>" required/>
        </div>
        <div class="form-group">
            <label for="inputUsername"><fmt:message key="register.name"/></label>
            <input name="username"
                   type="text" class="form-control mr-sm-2" id="inputUsername" aria-describedby="usernameHelp"
                   placeholder="<fmt:message key="register.enter.name"/>" required/>
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1"><fmt:message key="register.password"/></label>
            <input name="password"
                   type="password" class="form-control mr-sm-2" id="exampleInputPassword1"
                   placeholder="<fmt:message key="register.enter.password"/>" required/>
        </div>
        <div class="form-group">
            <label for="exampleInputConfirmPassword"><fmt:message key="register.confirm.password"/></label>
            <input name="confirmPassword" type="password" class="form-control mr-sm-2" id="exampleInputConfirmPassword"
                   placeholder="<fmt:message key="register.enter.password"/>" required/>
        </div>
        <button type="submit" class="btn btn-primary"
                name="curLang" value="${sessionScope.curLang}"
                formaction="${pageContext.request.contextPath}/page/register">
            <fmt:message key="register.submit"/>
        </button>
    </form>
</div>
</body>
</html>
