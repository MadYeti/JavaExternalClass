<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>

<html>
    <body>
        <a href="/sighIn"><button>Sigh In</button></a>
        <a href="/"><button>Main page</button></a>

        <c:set var="errorEmail" scope="request" value="${requestScope.emailError}" />
        <c:set var="errorPassword" scope="request" value="${requestScope.passwordError}" />
        <c:set var="errorRetypedPassword" scope="request" value="${requestScope.retypedPasswordError}" />

        <form id="sighUpForm" action="RegistrationServlet" method="post">

        	<div class="field">
        		<label>Enter your email:</label>
        		<div class="input"><input type="text" name="email" value="" id="email" /></div>
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
        		<c:if test = "${errorEmail ne null}">
        			<c:out value = "${errorEmail}" />
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

        	<div class="submit">
        		<button type="submit">Submit</button>
        	</div>

        </form>

    </body>
</html>