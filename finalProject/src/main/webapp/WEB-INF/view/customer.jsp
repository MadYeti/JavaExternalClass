<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>

<html>
    <body>
        <c:set var="weightInputError" scope="request" value="${requestScope.weightInputError}" />
        <c:set var="volumeInputError" scope="request" value="${requestScope.volumeInputError}" />
        <c:set var="typeInputError" scope="request" value="${requestScope.typeInputError}" />
        <c:set var="costInputError" scope="request" value="${requestScope.costInputError}" />
        <c:set var="sendingPointInputError" scope="request" value="${requestScope.sendingPointInputError}" />
        <c:set var="destinationPointInputError" scope="request" value="${requestScope.destinationPointInputError}" />
        <c:set var="isSendingDestinationPointSame" scope="request" value="${requestScope.isSendingDestinationPointSame}" />
        <c:set var="weightValue" scope="request" value="${requestScope.weightValue}" />
        <c:set var="volumeValue" scope="request" value="${requestScope.volumeValue}" />
        <c:set var="cargoTypeValue" scope="request" value="${requestScope.cargoTypeValue}" />
        <c:set var="cargoCostValue" scope="request" value="${requestScope.cargoCostValue}" />
        <c:set var="sendingPointValue" scope="request" value="${requestScope.sendingPointValue}" />
        <c:set var="destinationPointValue" scope="request" value="${requestScope.destinationPointValue}" />
        <c:set var="totalPriceValue" scope="request" value="${requestScope.totalPriceValue}" />
        <c:set var="success" scope="request" value="${requestScope.success}" />
        <H2>Hello customer!</H2>
        <a href="/"><button>Main page</button></a>
        <form id="logoutForm" action="LogoutServlet" method="post">
            <div class="submit">
                <button type="submit">Log out</button>
            </div>
        </form>
        <form id="viewBidDetailsFrom" action="BidsDetailsServlet" method="get">
            <div>
                <button type="submit">View bids details</button>
            </div>
        </form>
        <form id="calculationForm" action="BidControllerServlet" method="get">
            <div class="field">
                <label>Weight (kg):</label>
                <div class="input"><input type="text" name="weight" value="${weightValue}" id="weight" /></div>
            </div>
            <div class="field">
                <label>Volume (m3):</label>
                <div class="input"><input type="text" name="volume" value="${volumeValue}" id="volume" /></div>
            </div>
            <div class="field">
                <label>Cargo type:</label>
                <div class="select">
                    <c:choose>
                        <c:when test = "${cargoTypeValue eq null}">
                            <c:set var="cargoTypeValue" value="Choose cargo type" />
                        </c:when>
                        <c:when test = "${cargoTypeValue ne null}">
                            <c:set var="cargoTypeValue" value="${cargoTypeValue}" />
                        </c:when>
                    </c:choose>
                    <select size="1" name="type" id="type">
                        <option hidden="">${cargoTypeValue}</option>
                        <option>Health & care</option>
                        <option>Cloth</option>
                        <option>FMCG</option>
                        <option>Building materials</option>
                        <option>Car parts</option>
                        <option>Kids goods</option>
                        <option>Other</option>
                    </select>
                </div>
            </div>
            <div class="field">
                <label>Cargo cost ($):</label>
                <div class="input"><input type="text" name="cost" value="${cargoCostValue}" id="cost" /></div>
            </div>
            <div class="field">
                <label>Sending point:</label>
                <div class="select">
                    <c:choose>
                        <c:when test = "${sendingPointValue eq null}">
                            <c:set var="sendingPointValue" value="Choose sending point" />
                        </c:when>
                        <c:when test = "${sendingPointValue ne null}">
                            <c:set var="sendingPointValue" value="${sendingPointValue}" />
                        </c:when>
                    </c:choose>
                    <select size="1" name="sendingPoint" id="sendingPoint">
                        <option hidden="">${sendingPointValue}</option>
                        <option>Kiev</option>
                        <option>London</option>
                        <option>Paris</option>
                        <option>Berlin</option>
                        <option>Rome</option>
                        <option>Moscow</option>
                        <option>Warsaw</option>
                        <option>Oslo</option>
                        <option>Helsinki</option>
                        <option>Beijing</option>
                        <option>Madrid</option>
                        <option>Amsterdam</option>
                    </select>
                </div>
            </div>
            <div class="field">
                <label>Destination point:</label>
                <div class="select">
                    <c:choose>
                        <c:when test = "${destinationPointValue eq null}">
                            <c:set var="destinationPointValue" value="Choose destination point" />
                        </c:when>
                        <c:when test = "${destinationPointValue ne null}">
                            <c:set var="destinationPointValue" value="${destinationPointValue}" />
                        </c:when>
                    </c:choose>
                    <select size="1" name="destinationPoint" id="destinationPoint">
                        <option hidden="">${destinationPointValue}</option>
                        <option>Kiev</option>
                        <option>London</option>
                        <option>Paris</option>
                        <option>Berlin</option>
                        <option>Rome</option>
                        <option>Moscow</option>
                        <option>Warsaw</option>
                        <option>Oslo</option>
                        <option>Helsinki</option>
                        <option>Beijing</option>
                        <option>Madrid</option>
                        <option>Amsterdam</option>
                    </select>
                </div>
            </div>
            <div class="field">
                <label>Notes:</label>
                <div class="input"><textarea rows="10" cols="45" type="text" name="notes" value="" id="notes"></textarea>
                </div>
            </div>
            <div class="submit">
                <button type="submit" name="submit" value="false">Get price</button>
                <button type="submit" name="submit" value="true">Format bid</button>
            </div>

            <c:if test = "${totalPriceValue eq null}">
                <c:set var="totalPriceValue" value="0" />
            </c:if>
            <div>
                Price: <span id="totalPrice">${totalPriceValue}</span>$
            </div>
            </form>
            <div>
                <c:if test = "${success eq true}">
                    <c:out value = "Bid has been successfully formalized" />
                </c:if>
            </div>
            <div>
                <c:if test = "${weightInputError eq true}">
                    <c:out value = "Wrong weight input" />
                </c:if>
            </div>
            <div>
                <c:if test = "${volumeInputError eq true}">
                    <c:out value = "Wrong volume input" />
                </c:if>
            </div>
            <div>
                <c:if test = "${typeInputError eq true}">
                    <c:out value = "Wrong cargo type input" />
                </c:if>
            </div>
            <div>
                <c:if test = "${costInputError eq true}">
                    <c:out value = "Wrong cost input" />
                </c:if>
            </div>
            <div>
                <c:if test = "${sendingPointInputError eq true}">
                    <c:out value = "Wrong sending point input" />
                </c:if>
            </div>
            <div>
                <c:if test = "${destinationPointInputError eq true}">
                    <c:out value = "Wrong destination point input" />
                </c:if>
            </div>
            <div>
                <c:if test = "${isSendingDestinationPointSame eq true}">
                    <c:out value = "Sending and destination points are the same" />
                </c:if>
            </div>
    </body>
</html>