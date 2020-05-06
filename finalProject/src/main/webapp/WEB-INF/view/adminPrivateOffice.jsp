<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>

<html>
    <body>
        <c:set var="bid" scope="request" value="${requestScope.bid}" />
        <c:set var="errorInput" scope="request" value="${requestScope.errorInput}" />
        <H2>Admin private office</H2>
        <form action="AdminBidControllerServlet" method="get">
            <div>
                <label>Enter bid id:</label>
                <div class="input"><input type="text" name="bidId" value="" id="bidId" /></div>
            </div>
            <div>
                <input type="radio" checked="true" id="read" name="operation" value="read">
                <label for="read">Read</label>
                <input type="radio" id="update" name="operation" value="update">
                <label for="update">Update</label>
                <input type="radio" id="delete" name="operation" value="delete">
                <label for="delete">Delete</label>
            </div>
            <div>
                <button type="submit">Submit</button>
            </div>
            <div>
                <c:choose>
                    <c:when test = "${errorInput eq true}">
                        <c:out value = "Operation denied" />
                    </c:when>
                    <c:when test = "${errorInput eq false}">
                        <c:out value = "Operation has been complete successfully" />
                    </c:when>
                </c:choose>
            </div>
            <c:if test = "${bid ne null}">
                <div>
                    <table border="1px">
                        <tr>
                            <td>Bid number: </td>
                            <td>${bid.id}</td>
                        </tr>
                        <tr>
                            <td>Cargo weight: </td>
                            <td>${bid.weight}</td>
                        </tr>
                        <tr>
                            <td>Cargo volume: </td>
                            <td>${bid.volume}</td>
                        </tr>
                        <tr>
                            <td>Cargo type: </td>
                            <td>${bid.cargoType}</td>
                        </tr>
                        <tr>
                            <td>Cargo cost: </td>
                            <td>${bid.cargoCost}</td>
                        </tr>
                        <tr>
                            <td>Sending point: </td>
                            <td>${bid.sendingPoint}</td>
                        </tr>
                        <tr>
                            <td>Destination point: </td>
                            <td>${bid.destinationPoint}</td>
                        </tr>
                        <tr>
                            <td>Arrival date: </td>
                            <td>${bid.arrivalDate}</td>
                        </tr>
                        <tr>
                            <td>Notes: </td>
                            <td>${bid.notes}</td>
                        </tr>
                        <tr>
                            <td>Bids price: </td>
                            <td>${bid.price}</td>
                        </tr>
                        <tr>
                            <td>Bids status: </td>
                            <td>${bid.bidStatus}</td>
                        </tr>
                        <tr>
                            <td>Payment status: </td>
                            <td>${bid.paymentStatus}</td>
                        </tr>
                    </table>
                </div>
            </c:if>
        </form>
    </body>
</html>