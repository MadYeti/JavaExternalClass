<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored ="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>

<html>
    <body>
        <fmt:bundle basename="messages">
            <c:set var="success" scope="request" value="${requestScope.success}" />
            <c:set var="errorCreditCardNumber" scope="request" value="${requestScope.errorCreditCardNumber}" />
            <c:set var="errorCreditCardExpirationDate" scope="request" value="${requestScope.errorCreditCardExpirationDate}" />
            <c:set var="errorCreditCardCVVCode" scope="request" value="${requestScope.errorCreditCardCVVCode}" />
            <c:set var="id" value="${param.pay}" />

            <div>
                <a href="/BidsDetailsServlet"><button><fmt:message key="msg.viewBidsDetail.button"/></button></a>
            </div>

            <c:if test = "${success eq null}">
                <form action="BuyingControllerServlet" method="post">
                    <div class="field">
                        <label><fmt:message key="msg.bidNumber.label"/></label>
                        <div class="input"><input type="text" name="bidNumber" value="${id}" id="bidNumber" readonly /></div>
                    </div>

                    <div class="field">
                        <label><fmt:message key="msg.creditCard.number.label"/></label>
                        <div class="input"><input type="text" name="creditCardNumber" value="" id="creditCardNumber" /></div>
                    </div>

                    <div class="field">
                        <label><fmt:message key="msg.creditCard.expirationDate.label"/></label>
                        <div class="input"><input type="text" name="creditCardDate" value="" id="creditCardDate" /></div>
                    </div>

                    <div class="field">
                        <label><fmt:message key="msg.creditCard.cvvCode.label"/></label>
                        <div class="input"><input type="password" name="cvv" value="" id="cvv" /></div>
                    </div>
                    <button type="submit"><fmt:message key="msg.payBid.button"/></button>
                </form>
            </c:if>
            <div>
                <c:if test = "${success eq true}">
                    <fmt:message key="msg.bidPaid.success"/>
                </c:if>
            </div>
            <div>
                <c:if test = "${success eq false}">
                    <fmt:message key="msg.bidPaid.fail"/>
                </c:if>
            </div>
            <div>
                <c:if test = "${errorCreditCardNumber ne null}">
                    <fmt:message key="msg.creditCard.number.input.error"/>
                </c:if>
            </div>
            <div>
                <c:if test = "${errorCreditCardExpirationDate ne null}">
                    <fmt:message key="msg.creditCard.expirationDate.input.error"/>
                </c:if>
            </div>
            <div>
                <c:if test = "${errorCreditCardCVVCode ne null}">
                    <fmt:message key="msg.creditCard.cvvCode.input.error"/>
                </c:if>
            </div>
        </fmt:bundle>
    </body>
</html>