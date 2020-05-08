<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored ="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>

<html>
    <body>
        <fmt:bundle basename="messages">
            <a href="/sighIn"><button><fmt:message key="msg.sighIn.button"/></button></a>
            <a href="/"><button><fmt:message key="msg.mainPage.button"/></button></a>

            <c:set var="errorEmail" scope="request" value="${requestScope.emailError}" />
            <c:set var="errorPassword" scope="request" value="${requestScope.passwordError}" />
            <c:set var="errorRetypedPassword" scope="request" value="${requestScope.retypedPasswordError}" />

            <form id="sighUpForm" action="RegistrationServlet" method="post">

                <div class="field">
                    <label><fmt:message key="msg.email.input.label"/></label>
                    <div class="input"><input type="text" name="email" value="" id="email" /></div>
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
                    <c:if test = "${errorEmail ne null}">
                        <c:choose>
                            <c:when test = "${errorEmail eq 1}">
                                <fmt:message key="msg.email.input.error.option1" var="errorEmailField"/>
                                <c:out value = "${errorEmailField}" />
                            </c:when>
                            <c:when test = "${errorEmail eq 2}">
                                <fmt:message key="msg.email.input.error.option2" var="errorEmailField"/>
                                <c:out value = "${errorEmailField}" />
                            </c:when>
                            <c:when test = "${errorEmail eq 3}">
                                <fmt:message key="msg.email.input.error.option3" var="errorEmailField"/>
                                <c:out value = "${errorEmailField}" />
                            </c:when>
                        </c:choose>
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
                <div class="submit">
                    <button type="submit"><fmt:message key="msg.submit.button"/></button>
                </div>
            </form>
        </fmt:bundle>
    </body>
</html>