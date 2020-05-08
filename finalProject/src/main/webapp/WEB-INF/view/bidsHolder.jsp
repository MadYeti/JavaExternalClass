<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored ="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>

<html>
    <body>
        <fmt:bundle basename="messages">
            <fmt:message key="msg.bidList.label"/>
            <c:set var="client" scope="session" value="${sessionScope.client}" />
            <div>
                <a href="/${client.getRole()}"><button><fmt:message key="msg.privateOffice.button"/></button></a>
            </div>
            <c:forEach items="${bidsHolder.getBidsHolder()}" var="bids">
                <div>
                    <table border="1px">
                        <tr>
                            <td><fmt:message key="msg.bidNumber.label"/></td>
                            <td>${bids.id}</td>
                        </tr>
                        <tr>
                            <td><fmt:message key="msg.weight.label"/></td>
                            <td>${bids.weight}</td>
                        </tr>
                        <tr>
                            <td><fmt:message key="msg.volume.label"/></td>
                            <td>${bids.volume}</td>
                        </tr>
                        <tr>
                            <td><fmt:message key="msg.cargoType.label"/></td>
                            <td>${bids.cargoType}</td>
                        </tr>
                        <tr>
                            <td><fmt:message key="msg.cargoCost.label"/></td>
                            <td>${bids.cargoCost}</td>
                        </tr>
                        <tr>
                            <td><fmt:message key="msg.sendingPoint.label"/></td>
                            <td>${bids.sendingPoint}</td>
                        </tr>
                        <tr>
                            <td><fmt:message key="msg.destinationPoint.label"/></td>
                            <td>${bids.destinationPoint}</td>
                        </tr>
                        <tr>
                            <td><fmt:message key="msg.arrivalDate.label"/></td>
                            <td>${bids.arrivalDate}</td>
                        </tr>
                        <tr>
                            <td><fmt:message key="msg.notes.label"/></td>
                            <td>${bids.notes}</td>
                        </tr>
                        <tr>
                            <td><fmt:message key="msg.bidPrice.label"/></td>
                            <td>${bids.price}</td>
                        </tr>
                        <tr>
                            <td><fmt:message key="msg.bidStatus.label"/></td>
                            <td>${bids.bidStatus}</td>
                        </tr>
                        <tr>
                            <td><fmt:message key="msg.paymentStatus.label"/></td>
                            <td>${bids.paymentStatus}</td>
                        </tr>
                    </table>
                    <div>
                        <c:if test = "${bids.paymentStatus eq 'not paid'}">
                            <a href="/buyingPage?pay=${bids.id}"><button type="submit" name="pay" value="${bids.id}"><fmt:message key="msg.payBid.button"/></button></a>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </fmt:bundle>
    </body>
</html>