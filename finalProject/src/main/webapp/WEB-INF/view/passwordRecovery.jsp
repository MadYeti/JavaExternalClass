<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>

<html>
    <body>
        <c:set var="emailSend" scope="request" value="${requestScope.emailSend}" />
        <c:if test = "${emailSend eq null || emailSend eq false}">
            <form action="PasswordRecoveryServlet" method="post">
                <div class="field">
                    <label>Enter your email:</label>
                    <div class="input"><input type="text" name="email" value="" id="email" /></div>
                </div>
                <div>
                    <button type="submit">Submit</button>
                </div>
            </form>
        </c:if>
        <div>
            <c:if test = "${emailSend eq true}">
                Password recovery link has been sent to your email.
            </c:if>
        </div>
        <div>
            <c:if test = "${emailSend eq false}">
                Wrong email.
            </c:if>
        </div>
    </body>
</html>