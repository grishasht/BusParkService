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
<jsp:useBean id="drctService" class="model.service.DirectionService"/>
<jsp:useBean id="busService" class="model.service.BusService"/>
<jsp:useBean id="userService" class="model.service.UserService"/>

<c:set var="max_pages" value="${drctService.maxRows / 5 + 1}"/>
<div style="display: none">
    <c:if test="${sessionScope.count == null || sessionScope.count > max_pages}">${sessionScope.count = 1}</c:if>
</div>
<c:set var="butch_drct" value="${drctService.getButchDirections(5).get(sessionScope.count - 1)}"/>

<div class="container wrap">
    <div class="admin-directions">
        <h2><fmt:message key="admin.body.routes" bundle="${lang}"/></h2>
        <c:forEach var="direction" items="${butch_drct}">
            <form method="post">
                <div class="admin-body-form-marg">
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
                            <c:if test="${direction.isFree == true}">
                                <fmt:message key="body.is.free" bundle="${lang}"/>
                            </c:if>
                            <c:if test="${direction.isFree == false}">
                                <fmt:message key="body.not.free" bundle="${lang}"/>
                            </c:if>
                        </div>
                    </div>
                    <c:if test="${direction.free == true}">
                        <div class="chs-marsh input-group mb-3">
                            <select class="custom-select" id="bus" name="bus">
                                <option selected>Choose bus...</option>
                                <c:forEach var="bus" items="${busService.buses}">
                                    <c:if test="${bus.free == false}">
                                        <option value="${bus.id}">${bus.name}, ${bus.number}, ${bus.numPlaces}
                                            <fmt:message key="body.places" bundle="${lang}"/></option>
                                    </c:if>
                                </c:forEach>
                            </select>
                            <div class="input-group-append" style="height: 80%">
                                <button class="btn btn-outline-secondary" type="submit"
                                        name="route" value="${direction.id}"
                                        formaction="${pageContext.request.contextPath}/page/routes/choose_bus?curLang=${sessionScope.curLang}">
                                    <fmt:message key="register.submit" bundle="${lang}"/>
                                </button>
                            </div>
                        </div>
                    </c:if>
                </div>
                <c:if test="${direction.free == false}">
                    <div class="bus-driver">
                        <div>
                            <h5>
                                <fmt:message key="body.form.bus" bundle="${lang}"/>
                                    ${busService.readById(direction.busId).name},
                                    ${busService.readById(direction.busId).number},
                                    ${busService.readById(direction.busId).numPlaces}
                                <fmt:message key="body.places" bundle="${lang}"/>
                            </h5>
                        </div>
                    </div>
                </c:if>
            </form>
        </c:forEach>
    </div>
    <div class="pagin-form">
        <nav>
            <ul class="pagination">
                <li class="page-item">
                    <c:if test="${sessionScope.count != 1}">
                        <a class="page-link"
                           href="${pageContext.request.contextPath}/page/load_drct?count=${sessionScope.count - 1}">
                            <fmt:message key="pagin.prev" bundle="${lang}"/>
                        </a>
                    </c:if>
                </li>
                <c:forEach var="page_num" begin="${1}" end="${max_pages}">
                    <c:if test="${page_num != sessionScope.count}">
                        <li class="page-item">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/page/load_drct?count=${page_num}">
                                    ${page_num}
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${page_num == sessionScope.count}">
                        <li class="page-item active">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/page/load_drct?count=${page_num}">
                                    ${page_num}
                                <span class="sr-only">(current)</span>
                            </a>
                        </li>
                    </c:if>
                </c:forEach>
                <li class="page-item">
                    <c:if test="${sessionScope.count + 1 < max_pages}">
                        <a class="page-link"
                           href="${pageContext.request.contextPath}/page/load_drct?count=${sessionScope.count + 1}">
                            <fmt:message key="pagin.next" bundle="${lang}"/>
                        </a>
                    </c:if>
                </li>
            </ul>
        </nav>
    </div>

    <c:if test="${sessionScope.create_req == true}">
        <div class="admin-new-req guest-body-login" style="width: 60%">
            <form method="post">
                <div>
                    <c:set var="route" value="${sessionScope.direction_entity}"/>
                    <c:set var="bus" value="${sessionScope.bus_entity}"/>
                    <c:set var="driver" value="${sessionScope.driver_entity}"/>
                    <h4><fmt:message key="admin.new.req" bundle="${lang}"/></h4>
                    <div class="start">
                        <h5><fmt:message key="body.form.route" bundle="${lang}"/></h5>
                        <fmt:message key="body.start.point" bundle="${lang}"/> ${route.start}<br>
                        <fmt:message key="body.end.point" bundle="${lang}"/> ${route.end}<br>
                        <fmt:message key="body.distance" bundle="${lang}"/> ${route.distance}<br>
                        <h5><fmt:message key="body.form.bus" bundle="${lang}"/></h5>
                        <fmt:message key="body.bus.model" bundle="${lang}"/> ${bus.name}<br>
                        <fmt:message key="body.bus.number" bundle="${lang}"/> ${bus.number}<br>
                        <h5><fmt:message key="bus.driver" bundle="${lang}"/></h5> ${driver.name}, ${driver.age}
                        <fmt:message key="body.driver.age" bundle="${lang}"/><br>
                    </div>
                </div>
                <div class="input-group-append" style="height: 80%">
                    <button class="btn btn-primary form-elem" type="submit"
                            name="route" value="${direction.id}"
                            formaction="${pageContext.request.contextPath}/page/create_req?curLang=${sessionScope.curLang}">
                        <fmt:message key="admin.form.accept" bundle="${lang}"/>
                    </button>
                </div>
            </form>
        </div>
    </c:if>
    <c:if test="${sessionScope.create_req == false}">
        <div class="admin-new-req">
            <h2><fmt:message key="success.success" bundle="${lang}"/></h2>
        </div>
    </c:if>
</div>
