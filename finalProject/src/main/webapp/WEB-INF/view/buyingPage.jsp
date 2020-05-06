<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>

<html>
    <body>
        <c:set var="success" scope="request" value="${requestScope.success}" />
        <c:set var="errorCreditCardNumber" scope="request" value="${requestScope.errorCreditCardNumber}" />
        <c:set var="errorCreditCardExpirationDate" scope="request" value="${requestScope.errorCreditCardExpirationDate}" />
        <c:set var="errorCreditCardCVVCode" scope="request" value="${requestScope.errorCreditCardCVVCode}" />
        <c:set var="id" value="${param.pay}" />

        <c:if test = "${success eq null}">
            <form action="BuyingControllerServlet" method="post">
                <div class="field">
                    <label>Bid number:</label>
                    <div class="input"><input type="text" name="bidNumber" value="${id}" id="bidNumber" readonly /></div>
                </div>

                <div class="field">
                    <label>Enter credit card number:</label>
                    <div class="input"><input type="text" name="creditCardNumber" value="" id="creditCardNumber" /></div>
                </div>

                <div class="field">
                    <label>Enter credit card expiration date (day/month i.e. 04/22):</label>
                    <div class="input"><input type="text" name="creditCardDate" value="" id="creditCardDate" /></div>
                </div>

                <div class="field">
                    <label>Enter CVV code:</label>
                    <div class="input"><input type="password" name="cvv" value="" id="cvv" /></div>
                </div>
                <button type="submit">Pay bid</button>
            </form>
        </c:if>
        <div>
            <c:if test = "${success eq true}">
                Bid has been paid successfully
            </c:if>
        </div>
        <div>
            <c:if test = "${success eq false}">
                Error occurred during payment transaction
            </c:if>
        </div>
        <div>
            <c:if test = "${errorCreditCardNumber ne null}">
                <c:out value = "${errorCreditCardNumber}" />
            </c:if>
        </div>
        <div>
            <c:if test = "${errorCreditCardExpirationDate ne null}">
                <c:out value = "${errorCreditCardExpirationDate}" />
            </c:if>
        </div>
        <div>
            <c:if test = "${errorCreditCardCVVCode ne null}">
                <c:out value = "${errorCreditCardCVVCode}" />
            </c:if>
        </div>
    </body>
</html>