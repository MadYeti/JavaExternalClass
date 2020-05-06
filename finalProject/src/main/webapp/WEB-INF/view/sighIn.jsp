<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>

<html>
    <body>
        <a href="/"><button>Main page</button></a>
        <a href="/sighUp"><button>Sigh Up</button></a>

        <c:set var="client" scope="request" value="${requestScope.client}" />

        <form id="sighInForm" action="AuthorizationServlet" method="post">

        	<div class="field">
        		<label>Enter your email:</label>
        		<div class="input"><input type="text" name="email" value="" id="email" /></div>
        	</div>

        	<div class="field">
        		<label>Enter your password:</label>
        		<div class="input"><input type="password" name="password" value="" id="password" /></div>
        		<a href="/passwordRecovery" id="passwordRecovery">Forgot your password?</a>
        	</div>

        	<c:if test = "${client eq 'Warning'}">
        		<c:out value = "Wrong credentials!" />
        	</c:if>
        	<c:if test = "${client eq 'IncorrectInput'}">
                <c:out value = "Incorrect data input!" />
            </c:if>

        	<div class="submit">
        		<label id="remember"><input name="rememberMe" type="checkbox" value="Remember me" />Remember me</label>
        		<div>
        		    <button type="submit">Submit</button>
        		</div>
        	</div>

        </form>
    </body>
</html>