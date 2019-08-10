<%--
  Created by IntelliJ IDEA.
  User: hs
  Date: 5/26/19
  Time: 4:54 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.curLang}"/>
<fmt:setBundle basename="lang" var="lang"/>
<jsp:useBean id="getParams" scope="page" class="java.lang.String"/>

<script src="${pageContext.request.contextPath}/js/post.js"></script>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <div class="navbar-nav" style="width: 90%">
            <div class="nav-item active" style="width: 250px">
                <a class="nav-link" href="${pageContext.request.contextPath}/page/"><h3>
                    <fmt:message key="header.bus.park" bundle='${lang}'/></h3>
                    <span class="sr-only">(current)</span></a>
            </div>
            <div class="nav-item">
                <a class="nav-link"
                   href="${pageContext.request.contextPath}/page/service?curLang=${sessionScope.curLang}">
                    <fmt:message key="header.service" bundle='${lang}'/></a>
            </div>
            <div class="nav-item">
                <a class="nav-link"
                   href="${pageContext.request.contextPath}/page/my_requests?curLang=${sessionScope.curLang}">
                    <fmt:message key="header.my.requests" bundle='${lang}'/></a>
            </div>
            <div class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <fmt:message key="header.language" bundle='${lang}'/>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" onclick="{return renewPage(setGetParam('curLang', 'en'))}"
                       href="#"><fmt:message key="locale.english" bundle='${lang}'/></a>
                    <a class="dropdown-item" onclick="{return renewPage(setGetParam('curLang', 'ru'))}"
                       href="#"><fmt:message key="locale.russian" bundle='${lang}'/></a>
                </div>
            </div>
            <div class="nav-item mybutton logout-right">
                <a class="btn nav-link rounded"
                   href="${pageContext.request.contextPath}/page/logout?curLang=${sessionScope.curLang}">
                    <fmt:message key="header.form.logout" bundle='${lang}'/></a>
            </div>
            <div class="nav-item mybutton sign-up-right">
                <a class="btn nav-link rounded singup"
                   href="${pageContext.request.contextPath}/page/reg_fwd?curLang=${sessionScope.curLang}">
                    <fmt:message key="header.form.signup" bundle='${lang}'/></a>
            </div>
        </div>
    </div>
</nav>