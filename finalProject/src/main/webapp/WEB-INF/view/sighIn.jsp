<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored ="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>

<html>
    <body>
        <fmt:bundle basename="messages">
            <a href="/"><button><fmt:message key="msg.mainPage.button"/></button></a>
            <a href="/sighUp"><button><fmt:message key="msg.sighUp.button"/></button></a>
    
            <c:set var="client" scope="request" value="${requestScope.client}" />

            <form id="sighInForm" action="AuthorizationServlet" method="post">

                <div class="field">
                    <label><fmt:message key="msg.email.input.label"/></label>
                    <div class="input"><input type="text" name="email" value="" id="email" /></div>
                </div>

                <div class="field">
                    <label><fmt:message key="msg.password.input.label"/></label>
                    <div class="input"><input type="password" name="password" value="" id="password" /></div>
                    <a href="/passwordRecovery" id="passwordRecovery"><fmt:message key="msg.forgotPassword.label"/></a>
                </div>

                <c:if test = "${client eq 'Warning'}">
                    <fmt:message key="msg.sighIn.input.error.option1"/>
                </c:if>
                <c:if test = "${client eq 'IncorrectInput'}">
                    <fmt:message key="msg.sighIn.input.error.option2"/>
                </c:if>

                <div class="submit">
                    <label id="remember"><input name="rememberMe" type="checkbox" value="Remember me" /><fmt:message key="msg.rememberme.checkbox"/></label>
                    <div>
                        <button type="submit"><fmt:message key="msg.submit.button"/></button>
                    </div>
                </div>
            </form>
        </fmt:bundle>
    </body>
</html>