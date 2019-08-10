<%--
  Created by IntelliJ IDEA.
  User: hs
  Date: 6/7/19
  Time: 12:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.curLang}"/>
<fmt:setBundle basename="lang"/>

<div class="container wrap">
    <div class="guest-body-instr">
        <h1><fmt:message key="guest.body.welcome"/></h1>
        <h3><fmt:message key="guest.body.instr"/></h3>
    </div>
    <div class="guest-body-login" style="width: 50%">
        <form class="form-inline" method="post">
            <div class="form-group">
                <label class="form-elem" for="inputLogin"><fmt:message key="header.form.login"/></label><br>
                <input name="login" type="text" class="form-elem form-control mr-sm-2" id="inputLogin"
                       placeholder="<fmt:message key="body.enter.login"/>" required/>
            </div>
            <div class="form-group">
                <label class="form-elem" for="inputPassword"><fmt:message key="header.form.password"/></label><br>
                <input name="password" type="password" class="form-elem form-control mr-sm-2" id="inputPassword"
                       placeholder="<fmt:message key="body.enter.pswd"/>" required/>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary form-elem" name="curLang"
                        value="${sessionScope.curLang}"
                        formaction="${pageContext.request.contextPath}/page/login?curLang=${sessionScope.curLang}">
                    <fmt:message key="header.form.signin"/>
                </button>
            </div>
        </form>
    </div>
</div>