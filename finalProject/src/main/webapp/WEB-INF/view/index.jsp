<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                    <c:if test = "${client eq null}">
                        <a class="py-2 d-none d-md-inline-block" href="/sighIn"><fmt:message key="msg.sighIn.button"/></a>
                        <a class="py-2 d-none d-md-inline-block" href="/sighUp"><fmt:message key="msg.sighUp.button"/></a>
                    </c:if>
                    <c:if test = "${client ne null}">
                        <a class="py-2 d-none d-md-inline-block" href="/${client.getRole()}"><fmt:message key="msg.privateOffice.button"/></a>
                    </c:if>
                    <a class="py-2 d-none d-md-inline-block" href="#"><fmt:message key="msg.support.button"/></a>
                    <a class="py-2 d-none d-md-inline-block" href="#"><fmt:message key="msg.pricing.button"/></a>
                    <a class="py-2 d-none d-md-inline-block" href="/MainPage?lang=en_EN"><img src="/images/britainFlag.png" style="wight: 20px; height: 20px" alt="EN"></a>
                    <a class="py-2 d-none d-md-inline-block" href="/MainPage?lang=uk_UA"><img src="/images/ukraineFlag.png" style="wight: 20px; height: 20px" alt="UA"></a>
                    <c:if test = "${client ne null}">
                        <a class="py-2 d-none d-md-inline-block" href="/LogoutController"><fmt:message key="msg.logOut.button"/></a>
                    </c:if>
                </div>
            </nav>
            <div class="container">
                <c:set var="client" scope="session" value="${sessionScope.client}" />
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

                <div class="py-5 text-center">
                    <img class="d-block mx-auto mb-4" src="/images/truck.svg" alt="" width="72" height="72">
                    <h2><fmt:message key="msg.logicCompany.label"/></h2>
                    <p class="lead"><fmt:message key="msg.intro.label"/></p>
                </div>

                <div class="d-flex justify-content-center">
                    <div class="col-md-8 order-md-1">

                        <h4 class="mb-3"><fmt:message key="msg.serviceCalculator.label"/></h4>

                        <div class="my-3 p-3 bg-white rounded shadow-sm">
                            <form class="needs-validation" action="BidOrderPriceController" method="get">
                                <div class="mb-3" style="padding-left: 0px;">
                                    <label><fmt:message key="msg.weight.label"/></label>
                                    <input type="text" class="form-control" name="weight" value="${weightValue}" id="weight" />
                                    <div style="color: #dc3545; font-size: 75%;">
                                        <c:if test = "${weightInputError eq true}">
                                            <fmt:message key="msg.weight.input.error"/>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="mb-3" style="padding-left: 0px;">
                                    <label><fmt:message key="msg.volume.label"/></label>
                                    <input type="text" class="form-control" name="volume" value="${volumeValue}" id="volume" />
                                    <div style="color: #dc3545; font-size: 75%;">
                                        <c:if test = "${volumeInputError eq true}">
                                            <fmt:message key="msg.volume.input.error"/>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="mb-3" style="padding-left: 0px;">
                                    <label><fmt:message key="msg.cargoType.label"/></label>
                                    <div>
                                        <c:choose>
                                            <c:when test = "${cargoTypeValue eq null}">
                                                <fmt:message key="msg.cargoType.field" var="cargoTypeField"/>
                                                <c:set var="cargoTypeValue" value="${cargoTypeField}" />
                                            </c:when>
                                            <c:when test = "${cargoTypeValue ne null}">
                                                <c:set var="cargoTypeValue" value="${cargoTypeValue}" />
                                            </c:when>
                                        </c:choose>
                                        <select class="custom-select d-block w-100" size="1" name="type" id="type">
                                            <option hidden="">${cargoTypeValue}</option>
                                            <option><fmt:message key="msg.cargoType.field.option.healthAndCare"/></option>
                                            <option><fmt:message key="msg.cargoType.field.option.cloth"/></option>
                                            <option><fmt:message key="msg.cargoType.field.option.fmcg"/></option>
                                            <option><fmt:message key="msg.cargoType.field.option.buildingMaterials"/></option>
                                            <option><fmt:message key="msg.cargoType.field.option.carParts"/></option>
                                            <option><fmt:message key="msg.cargoType.field.option.kidsGoods"/></option>
                                            <option><fmt:message key="msg.cargoType.field.option.other"/></option>
                                        </select>
                                        <div style="color: #dc3545; font-size: 75%;">
                                            <c:if test = "${typeInputError eq true}">
                                                <fmt:message key="msg.cargoType.input.error"/>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                                <div class="mb-3" style="padding-left: 0px;">
                                    <label><fmt:message key="msg.cargoCost.label"/></label>
                                    <input type="text" class="form-control" name="cost" value="${cargoCostValue}" id="cost" />
                                    <div style="color: #dc3545; font-size: 75%;">
                                        <c:if test = "${costInputError eq true}">
                                            <fmt:message key="msg.cargoCost.input.error"/>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="mb-3" style="padding-left: 0px;">
                                    <label><fmt:message key="msg.sendingPoint.label"/></label>
                                    <div>
                                        <c:choose>
                                            <c:when test = "${sendingPointValue eq null}">
                                                <fmt:message key="msg.sendingPoint.field" var="sendingPointField"/>
                                                <c:set var="sendingPointValue" value="${sendingPointField}" />
                                            </c:when>
                                            <c:when test = "${sendingPointValue ne null}">
                                                <c:set var="sendingPointValue" value="${sendingPointValue}" />
                                            </c:when>
                                        </c:choose>
                                        <select class="custom-select d-block w-100" size="1" name="sendingPoint" id="sendingPoint">
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
                                        <div style="color: #dc3545; font-size: 75%;">
                                            <c:if test = "${sendingPointInputError eq true}">
                                                <fmt:message key="msg.sendingPoint.input.error"/>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                                <div class="mb-3" style="padding-left: 0px;">
                                    <label><fmt:message key="msg.destinationPoint.label"/></label>
                                    <div>
                                        <c:choose>
                                            <c:when test = "${destinationPointValue eq null}">
                                                <fmt:message key="msg.destinationPoint.field" var="destinationPointField"/>
                                                <c:set var="destinationPointValue" value="${destinationPointField}" />
                                            </c:when>
                                            <c:when test = "${destinationPointValue ne null}">
                                                <c:set var="destinationPointValue" value="${destinationPointValue}" />
                                            </c:when>
                                        </c:choose>
                                        <select class="custom-select d-block w-100" size="1" name="destinationPoint" id="destinationPoint">
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
                                        <div style="color: #dc3545; font-size: 75%;">
                                            <c:if test = "${destinationPointInputError eq true}">
                                                <fmt:message key="msg.destinationPoint.input.error"/>
                                            </c:if>
                                        </div>
                                        <div style="color: #dc3545; font-size: 75%;">
                                            <c:if test = "${isSendingDestinationPointSame eq true}">
                                                <fmt:message key="msg.sendingDestinationPointSame.input.error"/>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>

                                <hr class="mb-4">

                                <c:if test = "${totalPriceValue eq null}">
                                    <c:set var="totalPriceValue" value="0" />
                                </c:if>
                                <div class="mb-3">
                                    <fmt:message key="msg.getPrice.label"/><span id="totalPrice">${totalPriceValue}</span>$
                                </div>

                                <div>
                                    <button class="btn btn-primary btn-lg btn-block" type="submit"><fmt:message key="msg.getPrice.button"/></button>
                                </div>
                            </form>
                        </div>
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