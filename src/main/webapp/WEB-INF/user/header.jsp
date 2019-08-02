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
<fmt:setBundle basename="lang"/>
<jsp:useBean id="getParams" scope="page" class="java.lang.String"/>

<script src="${pageContext.request.contextPath}/js/signAjax.js"></script>
<script src="${pageContext.request.contextPath}/js/post.js"></script>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item active" style="width: 250px">
                <a class="nav-link" href="/page/"><h3><fmt:message key="header.yourdelivery"/></h3>
                    <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/page/service?curLang=${sessionScope.curLang}"><fmt:message key="header.service"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link"
                   href="${pageContext.request.contextPath}/page/my_requests?curLang=${sessionScope.curLang}"><fmt:message
                        key="header.my.requests"/></a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <fmt:message key="header.language"/>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" onclick="{return renewPage(setGetParam('curLang', 'en'))}"
                       href="#"><fmt:message key="locale.english"/></a>
                    <a class="dropdown-item" onclick="{return renewPage(setGetParam('curLang', 'ru'))}"
                       href="#"><fmt:message key="locale.russian"/></a>
                </div>
            </li>
            <li class="nav-item mybutton">
                <a class="btn nav-link rounded"
                   href="${pageContext.request.contextPath}/page/logout?curLang=${sessionScope.curLang}"><fmt:message
                        key="header.form.logout"/></a>
            </li>
            <li class="nav-item mybutton">
                <a class="btn nav-link rounded singup"
                   href="${pageContext.request.contextPath}/page/reg_fwd?curLang=${sessionScope.curLang}"><fmt:message
                        key="header.form.signup"/></a>
            </li>
        </ul>
    </div>
</nav>