<%--
  Created by IntelliJ IDEA.
  User: hs
  Date: 8/11/19
  Time: 10:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.curLang}"/>
<fmt:setBundle basename="lang" var="lang"/>
<jsp:useBean id="getParams" class="java.lang.String"/>
<jsp:useBean id="busService" class="model.service.BusService"/>
<jsp:useBean id="usrService" class="model.service.UserService"/>


<c:if test="${sessionScope.count == null}">${sessionScope.count = 1}</c:if>
<c:set var="butch_bus" value="${busService.getButchDirections(5).get(sessionScope.count - 1)}"/>
<c:set var="max_pages" value="${busService.maxRows / 5 + 1}"/>

<html>
<head>
    <title><fmt:message key="header.buses" bundle="${lang}"/></title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/grid.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container wrap">
    <div class="admin-buses">
        <c:forEach var="bus" items="${butch_bus}">
            <form method="post">
                <div class="admin-body-form-marg">
                    <div class="bus-form">
                        <div class="bus-model">
                            <fmt:message key="body.bus.model" bundle="${lang}"/> ${bus.name}
                        </div>
                        <div class="bus-number">
                            <fmt:message key="body.bus.number" bundle="${lang}"/> ${bus.number}
                        </div>
                        <div class="bus-num-places">
                            <fmt:message key="body.bus.num.places" bundle="${lang}"/> ${bus.numPlaces}
                        </div>
                        <div class="free">
                            <c:if test="${bus.free == true}">
                                <fmt:message key="body.is.free" bundle="${lang}"/>
                            </c:if>
                            <c:if test="${bus.free == false}">
                                <fmt:message key="body.not.free" bundle="${lang}"/>
                            </c:if>
                        </div>
                    </div>
                    <c:if test="${bus.free == true}">
                        <div class="chs-marsh input-group mb-3">
                            <select class="custom-select" id="driver" name="driver">
                                <option selected>Choose driver...</option>
                                <c:forEach var="driver" items="${usrService.drivers}">
                                    <c:if test="${driver.free == true}">
                                        <option value="${driver.id}">${driver.name}, ${driver.age}
                                            <fmt:message key="body.driver.age" bundle="${lang}"/>
                                        </option>
                                    </c:if>
                                </c:forEach>
                            </select>
                            <div class="input-group-append" style="height: 80%">
                                <button class="btn btn-outline-secondary" type="submit"
                                        name="bus" value="${bus.id}"
                                        formaction="${pageContext.request.contextPath}/page/buses/choose_driver?curLang=${sessionScope.curLang}">
                                    <fmt:message key="register.submit" bundle="${lang}"/>
                                </button>
                            </div>
                        </div>
                    </c:if>
                </div>
                <c:if test="${bus.free == false}">
                    <div class="bus-driver">
                        <div>
                            <h5>
                                <fmt:message key="bus.driver" bundle="${lang}"/>
                                    ${usrService.readById(bus.userId).name}, ${usrService.readById(bus.userId).age}
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
                           href="${pageContext.request.contextPath}/page/buses/load_bs?count=${sessionScope.count - 1}">
                            <fmt:message key="pagin.prev" bundle="${lang}"/>
                        </a>
                    </c:if>
                </li>

                <c:forEach var="page_num" begin="${1}" end="${max_pages}">
                    <c:if test="${page_num != sessionScope.count}">
                        <li class="page-item">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/page/buses/load_bs?count=${page_num}">
                                    ${page_num}
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${page_num == sessionScope.count}">
                        <li class="page-item active">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/page/buses/load_bs?count=${page_num}">
                                    ${page_num}
                                <span class="sr-only">(current)</span>
                            </a>
                        </li>
                    </c:if>
                </c:forEach>

                <li class="page-item">
                    <c:if test="${sessionScope.count + 1 < max_pages}">
                        <a class="page-link"
                           href="${pageContext.request.contextPath}/page/buses/load_bs?count=${sessionScope.count + 1}">
                            <fmt:message key="pagin.next" bundle="${lang}"/>
                        </a>
                    </c:if>
                </li>
            </ul>
        </nav>
    </div>
</div>

</body>
</html>
