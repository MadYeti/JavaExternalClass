<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored ="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>

<html>
    <body>
        <fmt:bundle basename="messages">
            <c:set var="emailSend" scope="request" value="${requestScope.emailSend}" />

            <div>
                <a href="/"><button><fmt:message key="msg.mainPage.button"/></button></a>
            </div>

            <c:if test = "${emailSend eq null || emailSend eq false}">
                <form action="PasswordRecoveryServlet" method="post">
                    <div class="field">
                        <label><fmt:message key="msg.email.input.label"/></label>
                        <div class="input"><input type="text" name="email" value="" id="email" /></div>
                    </div>
                    <div>
                        <button type="submit"><fmt:message key="msg.submit.button"/></button>
                    </div>
                </form>
            </c:if>
            <div>
                <c:if test = "${emailSend eq true}">
                    <fmt:message key="msg.email.input.success.label"/>
                </c:if>
            </div>
            <div>
                <c:if test = "${emailSend eq false}">
                    <fmt:message key="msg.email.input.fail.label"/>
                </c:if>
            </div>
        </fmt:bundle>
    </body>
</html>