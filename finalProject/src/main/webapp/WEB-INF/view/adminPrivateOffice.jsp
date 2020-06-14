<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored ="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:requestEncoding value="UTF-8" />

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
    <body class="bg-light">
        <fmt:bundle basename="messages">
            <nav class="site-header sticky-top py-1" style="background-color: #000">
                <div class="container d-flex flex-column flex-md-row justify-content-between">
                    <a class="py-2" href="/MainPage" aria-label="Logic">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="d-block mx-auto" role="img" viewBox="0 0 24 24" focusable="false"><title>Product</title><circle cx="12" cy="12" r="10"></circle><path d="M14.31 8l5.74 9.94M9.69 8h11.48M7.38 12l5.74-9.94M9.69 16L3.95 6.06M14.31 16H2.83m13.79-4l-5.74 9.94"></path></svg>
                    </a>
                    <a class="py-2 d-none d-md-inline-block" href="/MainPage"><fmt:message key="msg.mainPage.button"/></a>
                    <a class="py-2 d-none d-md-inline-block" href="/BidsDetailsServlet"><fmt:message key="msg.viewBidsDetail.button"/></a>
                    <a class="py-2 d-none d-md-inline-block" href="/admin"><fmt:message key="msg.privateOffice.button"/></a>
                    <a class="py-2 d-none d-md-inline-block" href="#"><fmt:message key="msg.support.button"/></a>
                    <a class="py-2 d-none d-md-inline-block" href="#"><fmt:message key="msg.pricing.button"/></a>
                    <a class="py-2 d-none d-md-inline-block" href="/LogoutServlet"><fmt:message key="msg.logOut.button"/></a>
                </div>
            </nav>

            <div class="container">
                <c:set var="bid" scope="request" value="${requestScope.bid}" />
                <c:set var="errorInput" scope="request" value="${requestScope.errorInput}" />

                <div class="py-5 text-center">
                    <h4 class="mb-3"><fmt:message key="msg.adminAccess.privateOffice"/></h4>
                </div>

                <div class="row">
                    <div class="col-md-4 order-md-2 mb-4">
                        <c:if test = "${bid ne null}">
                            <div>
                                <table border="1px">
                                    <tr>
                                        <td><fmt:message key="msg.bidNumber.label"/></td>
                                        <td>${bid.id}</td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="msg.weight.label"/></td>
                                        <td>${bid.weight}</td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="msg.volume.label"/></td>
                                        <td>${bid.volume}</td>
                                    </tr>
                                    <tr>
                                        <c:choose>
                                            <c:when test = "${lang eq null}">
                                                <c:set var="cargoTypeValue" value="cargoTypeEN" />
                                            </c:when>
                                            <c:when test = "${lang ne null}">
                                                <c:set var="langValue" value="${fn:substring(lang, 3, 5)}" />
                                                <c:set var="cargoTypeValue" value="cargoType${langValue}" />
                                            </c:when>
                                        </c:choose>
                                        <td><fmt:message key="msg.cargoType.label"/></td>
                                        <td>${bid.cargoType[cargoTypeValue]}</td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="msg.cargoCost.label"/></td>
                                        <td>${bid.cargoCost}</td>
                                    </tr>
                                    <tr>
                                        <c:choose>
                                            <c:when test = "${lang eq null}">
                                                <c:set var="sendingPointValue" value="sendingPointEN" />
                                            </c:when>
                                            <c:when test = "${lang ne null}">
                                                <c:set var="langValue" value="${fn:substring(lang, 3, 5)}" />
                                                <c:set var="sendingPointValue" value="sendingPoint${langValue}" />
                                            </c:when>
                                        </c:choose>
                                        <td><fmt:message key="msg.sendingPoint.label"/></td>
                                        <td>${bid.sendingPoint[sendingPointValue]}</td>
                                    </tr>
                                    <tr>
                                        <c:choose>
                                            <c:when test = "${lang eq null}">
                                                <c:set var="destinationPointValue" value="destinationPointEN" />
                                            </c:when>
                                            <c:when test = "${lang ne null}">
                                                <c:set var="langValue" value="${fn:substring(lang, 3, 5)}" />
                                                <c:set var="destinationPointValue" value="destinationPoint${langValue}" />
                                            </c:when>
                                        </c:choose>
                                        <td><fmt:message key="msg.destinationPoint.label"/></td>
                                        <td>${bid.destinationPoint[destinationPointValue]}</td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="msg.arrivalDate.label"/></td>
                                        <td>${bid.arrivalDate}</td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="msg.notes.label"/></td>
                                        <td>${bid.notes}</td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="msg.bidPrice.label"/></td>
                                        <td>${bid.price}</td>
                                    </tr>
                                    <tr>
                                        <c:choose>
                                            <c:when test = "${lang eq null}">
                                                <c:set var="bidStatusValue" value="bidStatusEN" />
                                            </c:when>
                                            <c:when test = "${lang ne null}">
                                                <c:set var="langValue" value="${fn:substring(lang, 3, 5)}" />
                                                <c:set var="bidStatusValue" value="bidStatus${langValue}" />
                                            </c:when>
                                        </c:choose>
                                        <td><fmt:message key="msg.bidStatus.label"/></td>
                                        <td>${bid.bidStatus[bidStatusValue]}</td>
                                    </tr>
                                    <tr>
                                        <c:choose>
                                            <c:when test = "${lang eq null}">
                                                <c:set var="paymentStatusValue" value="paymentStatusEN" />
                                            </c:when>
                                            <c:when test = "${lang ne null}">
                                                <c:set var="langValue" value="${fn:substring(lang, 3, 5)}" />
                                                <c:set var="paymentStatusValue" value="paymentStatus${langValue}" />
                                            </c:when>
                                        </c:choose>
                                        <td><fmt:message key="msg.paymentStatus.label"/></td>
                                        <td>${bid.paymentStatus[paymentStatusValue]}</td>
                                    </tr>
                                </table>
                            </div>
                        </c:if>
                    </div>
                    <div class="col-md-8 order-md-1">
                        <form class="needs-validation" action="AdminBidControllerServlet" method="get">
                            <div class="mb-3" style="padding-left: 0px;">
                                <label><fmt:message key="msg.bidId.label"/></label>
                                <input class="form-control" type="text" name="bidId" value="" id="bidId" />
                            </div>
                            <div class="mb-3" style="padding-left: 0px;">
                                <input type="radio" checked="true" id="read" name="operation" value="read">
                                <label for="read"><fmt:message key="msg.admin.operation.read"/></label>
                                <input type="radio" id="update" name="operation" value="update">
                                <label for="update"><fmt:message key="msg.admin.operation.update"/></label>
                                <input type="radio" id="delete" name="operation" value="delete">
                                <label for="delete"><fmt:message key="msg.admin.operation.delete"/></label>
                            </div>

                            <div>
                                <div style="color: #dc3545;">
                                    <c:if test = "${errorInput eq true}">
                                        <fmt:message key="msg.admin.operation.error"/>
                                    </c:if>
                                </div>
                                <div style="color: #28a745;">
                                    <c:if test = "${errorInput eq false}">
                                        <fmt:message key="msg.admin.operation.success"/>
                                    </c:if>
                                </div>
                            </div>

                            <hr class="mb-4">

                            <div>
                                <button class="btn btn-primary btn-lg btn-block" type="submit"><fmt:message key="msg.submit.button"/></button>
                            </div>

                        </form>
                    </div>
                </div>
                <footer class="my-5 pt-5 text-muted text-center text-small">
                    <p class="mb-1">© 2020 Logic</p>
                    <ul class="list-inline">
                        <li class="list-inline-item"><a href="#"><fmt:message key="msg.privacy.label"/></a></li>
                        <li class="list-inline-item"><a href="#"><fmt:message key="msg.terms.label"/></a></li>
                        <li class="list-inline-item"><a href="#"><fmt:message key="msg.support.button"/></a></li>
                    </ul>
                </footer>
            </div>
        </fmt:bundle>
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </body>
</html>