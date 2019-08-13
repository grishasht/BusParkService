<%--
  Created by IntelliJ IDEA.
  User: hs
  Date: 6/7/19
  Time: 12:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.curLang}"/>
<fmt:setBundle basename="lang" var="lang"/>
<jsp:useBean id="getParams" class="java.lang.String"/>
<jsp:useBean id="req" class="model.service.RequestService"/>
<%--<c:set var="jReq" value="${req.getRequests(sessionScope.sessionUser)}"/>--%>


<div class="container driver-req">
    <c:if test="${sessionScope.sessionUser.id != null}">
        <c:forEach var="iReq" items="${req.getRequests(sessionScope.sessionUser)}">
            <form method="post">
                <div class="driver-form wrap">
                    <div class="driver-route">
                        <h4 style="padding-left: 30%">
                            <fmt:message key="driver.form.route" bundle="${lang}"/>
                        </h4>
                        <div class="route-start">
                            <fmt:message key="body.start.point" bundle="${lang}"/>
                                ${iReq.start}
                        </div>
                        <div class="route-end">
                            <fmt:message key="body.end.point" bundle="${lang}"/>
                                ${iReq.end}
                        </div>
                        <div class="route-dist">
                            <fmt:message key="body.distance" bundle="${lang}"/>
                                ${iReq.distance}
                        </div>
                    </div>
                    <div class="driver-bus">
                        <h4 style="padding-left: 30%">
                            <fmt:message key="driver.form.bus" bundle="${lang}"/>
                        </h4>
                        <div class="bus-model">
                            <fmt:message key="body.bus.model" bundle="${lang}"/>
                                ${iReq.name}
                        </div>
                        <div class="bus-number">
                            <fmt:message key="body.bus.number" bundle="${lang}"/>
                                ${iReq.number}
                        </div>
                        <div class="bus-num-places">
                            <fmt:message key="body.bus.num.places" bundle="${lang}"/>
                                ${iReq.numPlaces}
                            <fmt:message key="body.places" bundle="${lang}"/>
                        </div>
                    </div>
                </div>
                <c:if test="${iReq.isAccept != true}">
                    <div class="input-group-append">
                        <button class="btn btn-primary form-elem" type="submit"
                                name="reqId" value="${iReq.id}"
                                formaction="${pageContext.request.contextPath}/page/accept_req?curLang=${sessionScope.curLang}">
                            <fmt:message key="admin.form.accept" bundle="${lang}"/>
                        </button>
                    </div>
                </c:if>
                <c:if test="${iReq.isAccept == true}">
                    <div style="margin-top: 3%">
                        <h2><fmt:message key="driver.req.accept" bundle="${lang}"/></h2>
                    </div>
                </c:if>
            </form>
        </c:forEach>
    </c:if>
</div>

