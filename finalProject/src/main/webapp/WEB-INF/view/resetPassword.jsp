<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored ="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>

<html>
    <body>
        <fmt:bundle basename="messages">
            <c:set var="token" value="${param.token}" />
            <c:set var="resetPassword" scope="request" value="${requestScope.resetPassword}" />
            <c:set var="errorPassword" scope="request" value="${requestScope.passwordError}" />
            <c:set var="errorRetypedPassword" scope="request" value="${requestScope.retypedPasswordError}" />
            <div>
                <a href="/"><button><fmt:message key="msg.mainPage.button"/></button></a>
            </div>
            <c:if test = "${resetPassword eq null || resetPassword eq false}">
                <form action="ResetPasswordServlet" method="post">
                    <div class="field">
                        <div class="input"><input type="hidden" name="token" value="${token}" id="token" /></div>
                    </div>
                    <div class="field">
                        <label><fmt:message key="msg.password.input.label"/></label>
                        <div class="input"><input type="password" name="password" value="" id="password" /></div>
                    </div>
                    <div class="field">
                        <label><fmt:message key="msg.retypedPassword.input.label"/></label>
                        <div class="input"><input type="password" name="retypedPassword" value="" id="retypedPassword" /></div>
                    </div>
                    <div>
                        <button type="submit"><fmt:message key="msg.submit.button"/></button>
                    </div>
                </form>
            </c:if>
            <div>
                <c:if test = "${resetPassword eq true}">
                    <fmt:message key="msg.password.input.success.label"/>
                </c:if>
            </div>
            <div>
                <c:if test = "${errorPassword ne null}">
                    <c:choose>
                        <c:when test = "${errorPassword eq 1}">
                            <fmt:message key="msg.password.input.error.option1" var="errorPasswordField"/>
                            <c:out value = "${errorPasswordField}" />
                        </c:when>
                        <c:when test = "${errorPassword eq 2}">
                            <fmt:message key="msg.password.input.error.option2" var="errorPasswordField"/>
                            <c:out value = "${errorPasswordField}" />
                        </c:when>
                        <c:when test = "${errorPassword eq 3}">
                            <fmt:message key="msg.password.input.error.option3" var="errorPasswordField"/>
                            <c:out value = "${errorPasswordField}" />
                        </c:when>
                        <c:when test = "${errorPassword eq 4}">
                            <fmt:message key="msg.password.input.error.option4" var="errorPasswordField"/>
                            <c:out value = "${errorPasswordField}" />
                        </c:when>
                    </c:choose>
                </c:if>
            </div>
            <div>
                <c:if test = "${errorRetypedPassword ne null}">
                    <c:choose>
                        <c:when test = "${errorRetypedPassword eq 1}">
                            <fmt:message key="msg.retypedPassword.input.error.option1" var="errorRetypedPasswordField"/>
                            <c:out value = "${errorRetypedPasswordField}" />
                        </c:when>
                        <c:when test = "${errorRetypedPassword eq 2}">
                            <fmt:message key="msg.retypedPassword.input.error.option2" var="errorRetypedPasswordField"/>
                            <c:out value = "${errorRetypedPasswordField}" />
                        </c:when>
                    </c:choose>
                </c:if>
            </div>
        </fmt:bundle>
    </body>
</html>