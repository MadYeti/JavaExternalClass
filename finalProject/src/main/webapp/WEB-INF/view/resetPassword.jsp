<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>

<html>
    <body>
        <c:set var="token" value="${param.token}" />
        <c:set var="resetPassword" scope="request" value="${requestScope.resetPassword}" />
        <c:set var="errorPassword" scope="request" value="${requestScope.passwordError}" />
        <c:set var="errorRetypedPassword" scope="request" value="${requestScope.retypedPasswordError}" />
        <div>
            <a href="/"><button>Main page</button></a>
        </div>
        <c:if test = "${resetPassword eq null || resetPassword eq false}">
            <form action="ResetPasswordServlet" method="post">
                <div class="field">
                    <div class="input"><input type="hidden" name="token" value="${token}" id="token" /></div>
                </div>
                <div class="field">
                    <label>Enter your password:</label>
                    <div class="input"><input type="password" name="password" value="" id="password" /></div>
                </div>
                <div class="field">
                    <label>Retype your password:</label>
                    <div class="input"><input type="password" name="retypedPassword" value="" id="retypedPassword" /></div>
                </div>
                <div>
                    <button type="submit">Submit</button>
                </div>
            </form>
        </c:if>
        <div>
            <c:if test = "${resetPassword eq true}">
                Password has been reset.
            </c:if>
        </div>
        <div>
            <c:if test = "${errorPassword ne null}">
                <c:out value = "${errorPassword}" />
            </c:if>
        </div>
        <div>
            <c:if test = "${errorRetypedPassword ne null}">
                <c:out value = "${errorRetypedPassword}" />
            </c:if>
        </div>
    </body>
</html>