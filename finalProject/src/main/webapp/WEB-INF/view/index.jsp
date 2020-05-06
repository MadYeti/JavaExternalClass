<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>

<html>
    <head>
        <script type="text/javascrip" ></script>
        <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    </head>
    <body>
        <div class="body">
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

            <c:if test = "${client eq null}">
                <a href="/sighIn"><button>Sigh In</button></a>
                <a href="/sighUp"><button>Sigh Up</button></a>
            </c:if>

            <c:if test = "${client ne null}">
                <a href="/${client.getRole()}"><button>Private office</button></a>
                <form id="logoutForm" action="LogoutServlet" method="post">
                    <div class="submit">
                        <button type="submit">Log out</button>
                    </div>
                </form>
            </c:if>

            <form action="BidControllerServlet" method="get">
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
            <div class="submit">
                <button type="submit">Get price</button>
            </div>
            </form>

            <c:if test = "${totalPriceValue eq null}">
                <c:set var="totalPriceValue" value="0" />
            </c:if>
            <div>
                Price: <span id="totalPrice">${totalPriceValue}</span>$
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
        </div>
    </body>
</html>
<script>

    function getPrice(){
        var weight = $("#weight").val();
        var volume = $("#volume").val();
        var type = $("#type").val();
        var cost = $("#cost").val();
        var sendingPoint = $("#sendingPoint").val();
        var destinationPoint = $("#destinationPoint").val();
        $.ajax({
            url: "/BidControllerServlet",
        	data:{
        	    weight: weight,
        	    volume: volume,
        	    type: type,
        	    cost: cost,
        	    sendingPoint: sendingPoint,
        	    destinationPoint: destinationPoint,
        	},
        	type: "get",
        	success: function(response){
        		//$(".body").html();
                var jsonObject = '${jsResponseData}';
                var object = object != "" ? JSON.parse(jsonObject) : {};
                var finalPrice = document.getElementById("totalPrice");
                console.log(object.totalPrice);
                console.log("AAAAAAAAAAA");
                alert(object.totalPrice);
                finalPrice.value = object.totalPrice;
        	}
        });
    }

</script>
