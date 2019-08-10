<%--
  Created by IntelliJ IDEA.
  User: hs
  Date: 6/7/19
  Time: 12:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.curLang}"/>
<fmt:setBundle basename="lang" var="lang"/>
<jsp:useBean id="getParams" scope="page" class="java.lang.String"/>
<jsp:useBean id="drctService" scope="page" class="model.service.DirectionService"/>
<jsp:useBean id="busService" scope="page" class="model.service.BusService"/>

<div class="container wrap">
    <div class="admin-directions">
        <c:forEach var="direction" items="${drctService.directions}">
            <div>
                <div class="dist-form">
                    <div class="start-point">
                        <fmt:message key="body.start.point" bundle="${lang}"/> ${direction.start}
                    </div>
                    <div class="end-point">
                        <fmt:message key="body.end.point" bundle="${lang}"/> ${direction.end}
                    </div>
                    <div class="distance">
                        <fmt:message key="body.distance" bundle="${lang}"/> ${direction.distance}
                    </div>
                    <div class="free">
                        <fmt:message key="body.is.free" bundle="${lang}"/>
                        <c:if test="${direction.isFree == true}">
                            <fmt:message key="body.yes" bundle="${lang}"/>
                        </c:if>
                        <c:if test="${direction.isFree == false}">
                            <fmt:message key="body.no" bundle="${lang}"/>
                        </c:if>
                    </div>
                </div>
                <div class="chs-marsh input-group mb-3">
                        <select class="custom-select" id="bus" name="bus">
                            <option selected>Choose bus...</option>
                            <c:forEach var="bus" items="${busService.buses}">
                                <c:if test="${bus.free == true}">
                                    <option value="${bus.id}">${bus.name}, ${bus.number}, ${bus.numPlaces}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    <div class="input-group-append" style="height: 80%">
                        <button class="btn btn-outline-secondary" type="submit"
                                name="curLang" value="${sessionScope.curLang}"
                                formaction="${pageContext.request.contextPath}/page/service/choose_city">
<%--                            Change here!!!!!!!!!!!--%>
                            <fmt:message key="register.submit" bundle="${lang}"/>
                        </button>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
