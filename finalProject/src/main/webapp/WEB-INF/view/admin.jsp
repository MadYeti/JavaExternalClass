<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored ="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>

<html>
    <body>
        <fmt:bundle basename="messages">
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
            <fmt:message key="msg.greeting.admin"/>
            <div>
                <a href="/"><button><fmt:message key="msg.mainPage.button"/></button></a>
            </div>
            <form id="logoutForm" action="LogoutServlet" method="post">
                <div class="submit">
                    <button type="submit"><fmt:message key="msg.logOut.button"/></button>
                </div>
            </form>
            <form id="viewBidDetailsFrom" action="BidsDetailsServlet" method="get">
                <div>
                    <button type="submit"><fmt:message key="msg.viewBidsDetail.button"/></button>
                </div>
            </form>
            <div>
                <a href="/adminPrivateOffice"><button><fmt:message key="msg.admin.bidAccess.button"/></button></a>
            </div>
            <form id="calculationForm" action="BidControllerServlet" method="get">
                <div class="field">
                    <label><fmt:message key="msg.weight.label"/></label>
                    <div class="input"><input type="text" name="weight" value="${weightValue}" id="weight" /></div>
                </div>
                <div class="field">
                    <label><fmt:message key="msg.volume.label"/></label>
                    <div class="input"><input type="text" name="volume" value="${volumeValue}" id="volume" /></div>
                </div>
                <div class="field">
                    <label><fmt:message key="msg.cargoType.label"/></label>
                    <div class="select">
                        <c:choose>
                            <c:when test = "${cargoTypeValue eq null}">
                                <fmt:message key="msg.cargoType.field" var="cargoTypeField"/>
                                <c:set var="cargoTypeValue" value="${cargoTypeField}" />
                            </c:when>
                            <c:when test = "${cargoTypeValue ne null}">
                                <c:set var="cargoTypeValue" value="${cargoTypeValue}" />
                            </c:when>
                        </c:choose>
                        <select size="1" name="type" id="type">
                            <option hidden="">${cargoTypeValue}</option>
                            <option><fmt:message key="msg.cargoType.field.option.healthAndCare"/></option>
                            <option><fmt:message key="msg.cargoType.field.option.cloth"/></option>
                            <option><fmt:message key="msg.cargoType.field.option.fmcg"/></option>
                            <option><fmt:message key="msg.cargoType.field.option.buildingMaterials"/></option>
                            <option><fmt:message key="msg.cargoType.field.option.carParts"/></option>
                            <option><fmt:message key="msg.cargoType.field.option.kidsGoods"/></option>
                            <option><fmt:message key="msg.cargoType.field.option.other"/></option>
                        </select>
                    </div>
                </div>
                <div class="field">
                    <label><fmt:message key="msg.cargoCost.label"/></label>
                    <div class="input"><input type="text" name="cost" value="${cargoCostValue}" id="cost" /></div>
                </div>
                <div class="field">
                    <label><fmt:message key="msg.sendingPoint.label"/></label>
                    <div class="select">
                        <c:choose>
                            <c:when test = "${sendingPointValue eq null}">
                                <fmt:message key="msg.sendingPoint.field" var="sendingPointField"/>
                                <c:set var="sendingPointValue" value="${sendingPointField}" />
                            </c:when>
                            <c:when test = "${sendingPointValue ne null}">
                                <c:set var="sendingPointValue" value="${sendingPointValue}" />
                            </c:when>
                        </c:choose>
                        <select size="1" name="sendingPoint" id="sendingPoint">
                            <option hidden="">${sendingPointValue}</option>
                            <option><fmt:message key="msg.sendingPoint.field.option.kiev"/></option>
                            <option><fmt:message key="msg.sendingPoint.field.option.london"/></option>
                            <option><fmt:message key="msg.sendingPoint.field.option.paris"/></option>
                            <option><fmt:message key="msg.sendingPoint.field.option.berlin"/></option>
                            <option><fmt:message key="msg.sendingPoint.field.option.rome"/></option>
                            <option><fmt:message key="msg.sendingPoint.field.option.moscow"/></option>
                            <option><fmt:message key="msg.sendingPoint.field.option.warsaw"/></option>
                            <option><fmt:message key="msg.sendingPoint.field.option.oslo"/></option>
                            <option><fmt:message key="msg.sendingPoint.field.option.helsinki"/></option>
                            <option><fmt:message key="msg.sendingPoint.field.option.beijing"/></option>
                            <option><fmt:message key="msg.sendingPoint.field.option.madrid"/></option>
                            <option><fmt:message key="msg.sendingPoint.field.option.amsterdam"/></option>
                        </select>
                    </div>
                </div>
                <div class="field">
                    <label><fmt:message key="msg.destinationPoint.label"/></label>
                    <div class="select">
                        <c:choose>
                            <c:when test = "${destinationPointValue eq null}">
                                <fmt:message key="msg.destinationPoint.field" var="destinationPointField"/>
                                <c:set var="destinationPointValue" value="${destinationPointField}" />
                            </c:when>
                            <c:when test = "${destinationPointValue ne null}">
                                <c:set var="destinationPointValue" value="${destinationPointValue}" />
                            </c:when>
                        </c:choose>
                        <select size="1" name="destinationPoint" id="destinationPoint">
                            <option hidden="">${destinationPointValue}</option>
                            <option><fmt:message key="msg.destinationPoint.field.option.kiev"/></option>
                            <option><fmt:message key="msg.destinationPoint.field.option.london"/></option>
                            <option><fmt:message key="msg.destinationPoint.field.option.paris"/></option>
                            <option><fmt:message key="msg.destinationPoint.field.option.berlin"/></option>
                            <option><fmt:message key="msg.destinationPoint.field.option.rome"/></option>
                            <option><fmt:message key="msg.destinationPoint.field.option.moscow"/></option>
                            <option><fmt:message key="msg.destinationPoint.field.option.warsaw"/></option>
                            <option><fmt:message key="msg.destinationPoint.field.option.oslo"/></option>
                            <option><fmt:message key="msg.destinationPoint.field.option.helsinki"/></option>
                            <option><fmt:message key="msg.destinationPoint.field.option.beijing"/></option>
                            <option><fmt:message key="msg.destinationPoint.field.option.madrid"/></option>
                            <option><fmt:message key="msg.destinationPoint.field.option.amsterdam"/></option>
                        </select>
                    </div>
                </div>
                <div class="field">
                    <label><fmt:message key="msg.notes.label"/></label>
                    <div class="input"><textarea rows="10" cols="45" type="text" name="notes" value="" id="notes"></textarea>
                    </div>
                </div>
                <div class="submit">
                    <button type="submit" name="submit" value="false"><fmt:message key="msg.getPrice.button"/></button>
                    <button type="submit" name="submit" value="true"><fmt:message key="msg.orderBid.button"/></button>
                </div>
                <c:if test = "${totalPriceValue eq null}">
                    <c:set var="totalPriceValue" value="0" />
                </c:if>
                <div>
                    <fmt:message key="msg.getPrice.label"/><span id="totalPrice">${totalPriceValue}</span>$
                </div>
            </form>
            <div>
                <c:if test = "${success eq true}">
                    <fmt:message key="msg.bidOrder.success"/>
                </c:if>
            </div>
            <div>
                <c:if test = "${weightInputError eq true}">
                    <fmt:message key="msg.weight.input.error"/>
                </c:if>
            </div>
            <div>
                <c:if test = "${volumeInputError eq true}">
                    <fmt:message key="msg.volume.input.error"/>
                </c:if>
            </div>
            <div>
                <c:if test = "${typeInputError eq true}">
                    <fmt:message key="msg.cargoType.input.error"/>
                </c:if>
            </div>
            <div>
                <c:if test = "${costInputError eq true}">
                    <fmt:message key="msg.cargoCost.input.error"/>
                </c:if>
            </div>
            <div>
                <c:if test = "${sendingPointInputError eq true}">
                    <fmt:message key="msg.sendingPoint.input.error"/>
                </c:if>
            </div>
            <div>
                <c:if test = "${destinationPointInputError eq true}">
                    <fmt:message key="msg.destinationPoint.input.error"/>
                </c:if>
            </div>
            <div>
                <c:if test = "${isSendingDestinationPointSame eq true}">
                    <fmt:message key="msg.sendingDestinationPointSame.input.error"/>
                </c:if>
            </div>
        </fmt:bundle>
    </body>
</html>