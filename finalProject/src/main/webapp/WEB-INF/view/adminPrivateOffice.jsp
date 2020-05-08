<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored ="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>

<html>
    <body>
        <fmt:bundle basename="messages">
            <c:set var="bid" scope="request" value="${requestScope.bid}" />
            <c:set var="errorInput" scope="request" value="${requestScope.errorInput}" />
            <fmt:message key="msg.adminAccess.privateOffice"/>
            <div>
                <a href="/admin"><button><fmt:message key="msg.privateOffice.button"/></button></a>
            </div>
            <form action="AdminBidControllerServlet" method="get">
                <div>
                    <label><fmt:message key="msg.bidId.label"/></label>
                    <div class="input"><input type="text" name="bidId" value="" id="bidId" /></div>
                </div>
                <div>
                    <input type="radio" checked="true" id="read" name="operation" value="read">
                    <label for="read"><fmt:message key="msg.admin.operation.read"/></label>
                    <input type="radio" id="update" name="operation" value="update">
                    <label for="update"><fmt:message key="msg.admin.operation.update"/></label>
                    <input type="radio" id="delete" name="operation" value="delete">
                    <label for="delete"><fmt:message key="msg.admin.operation.delete"/></label>
                </div>
                <div>
                    <button type="submit"><fmt:message key="msg.submit.button"/></button>
                </div>
                <div>
                    <c:choose>
                        <c:when test = "${errorInput eq true}">
                            <fmt:message key="msg.admin.operation.error"/>
                        </c:when>
                        <c:when test = "${errorInput eq false}">
                            <fmt:message key="msg.admin.operation.success"/>
                        </c:when>
                    </c:choose>
                </div>
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
                                <td><fmt:message key="msg.cargoType.label"/></td>
                                <td>${bid.cargoType}</td>
                            </tr>
                            <tr>
                                <td><fmt:message key="msg.cargoCost.label"/></td>
                                <td>${bid.cargoCost}</td>
                            </tr>
                            <tr>
                                <td><fmt:message key="msg.sendingPoint.label"/></td>
                                <td>${bid.sendingPoint}</td>
                            </tr>
                            <tr>
                                <td><fmt:message key="msg.destinationPoint.label"/></td>
                                <td>${bid.destinationPoint}</td>
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
                                <td><fmt:message key="msg.bidStatus.label"/></td>
                                <td>${bid.bidStatus}</td>
                            </tr>
                            <tr>
                                <td><fmt:message key="msg.paymentStatus.label"/></td>
                                <td>${bid.paymentStatus}</td>
                            </tr>
                        </table>
                    </div>
                </c:if>
            </form>
        </fmt:bundle>
    </body>
</html>