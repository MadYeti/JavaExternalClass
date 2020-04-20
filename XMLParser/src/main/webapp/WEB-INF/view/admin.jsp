<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>

<html>
    <body>
        <h1>Hello ADMIN!</h1>
        <form action="XMLProcessingAdminServlet" method="get">

        	<div>
        		<label>Enter id:</label>
        		<div class="input"><input type="text" name="fileid" value="" id="fileid" /></div>
        	</div>

            <div>
                <input type="radio" checked="true" id="create" name="operation" value="create">
                <label for="create">Create</label>
                <input type="radio" id="read" name="operation" value="read">
                <label for="read">Read</label>
                <input type="radio" id="update" name="operation" value="update">
                <label for="update">Update</label>
                <input type="radio" id="delete" name="operation" value="delete">
                <label for="delete">Delete</label>
            </div>

            <div>
                <input type="radio" checked="true" id="dom" name="parser" value="DOM">
                <label for="dom">DOM</label>
                <input type="radio" id="sax" name="parser" value="SAX">
                <label for="sax">SAX</label>
                <input type="radio" id="stax" name="parser" value="StAX">
                <label for="stax">StAX</label>
            </div>

        	<div>
        		<button type="submit">Submit</button>
        	</div>

        </form>

        <c:set var="result" scope="request" value="${requestScope.result}" />

        <c:if test = "${result ne null}">
            <c:if test = "${result == true}">
                <c:out value = "Operation complete successfully" />
            </c:if>
            <c:if test = "${result == false}">
                <c:out value = "Operation denied" />
            </c:if>
        </c:if>

        <form action="LogoutServlet" method="get">
            <div>
                <button type="submit">Logout</button>
            </div>
        </form>
    </body>
</html>
