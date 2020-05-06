<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>

<html>
    <body>
        <H2>Your bids:</H2>
        <c:set var="client" scope="session" value="${sessionScope.client}" />
        <a href="/${client.getRole()}"><button>Private office</button></a>
        <c:forEach items="${bidsHolder.getBidsHolder()}" var="bids">
            <div>
                <table border="1px">
                    <tr>
                        <td>Bid number: </td>
                        <td>${bids.id}</td>
                    </tr>
                    <tr>
                        <td>Cargo weight: </td>
                        <td>${bids.weight}</td>
                    </tr>
                    <tr>
                        <td>Cargo volume: </td>
                        <td>${bids.volume}</td>
                    </tr>
                    <tr>
                        <td>Cargo type: </td>
                        <td>${bids.cargoType}</td>
                    </tr>
                    <tr>
                        <td>Cargo cost: </td>
                        <td>${bids.cargoCost}</td>
                    </tr>
                    <tr>
                        <td>Sending point: </td>
                        <td>${bids.sendingPoint}</td>
                    </tr>
                    <tr>
                        <td>Destination point: </td>
                        <td>${bids.destinationPoint}</td>
                    </tr>
                    <tr>
                        <td>Arrival date: </td>
                        <td>${bids.arrivalDate}</td>
                    </tr>
                    <tr>
                        <td>Notes: </td>
                        <td>${bids.notes}</td>
                    </tr>
                    <tr>
                        <td>Bids price: </td>
                        <td>${bids.price}</td>
                    </tr>
                    <tr>
                        <td>Bids status: </td>
                        <td>${bids.bidStatus}</td>
                    </tr>
                    <tr>
                        <td>Payment status: </td>
                        <td>${bids.paymentStatus}</td>
                    </tr>
                </table>
                <div>
                    <c:if test = "${bids.paymentStatus eq 'not paid'}">
                        <a href="/buyingPage?pay=${bids.id}"><button type="submit" name="pay" value="${bids.id}">Pay bid</button></a>
                    </c:if>
                </div>
            </div>
        </c:forEach>
    </body>
</html>