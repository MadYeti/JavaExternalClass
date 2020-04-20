<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>

<html>
<head>
    <title>USER</title>

</head>
<body>

<h1>Hello USER!</h1>
    <form action="XMLProcessingUserServlet" method="get">
        <div>
            <label>Enter id:</label>
            <div class="input"><input type="text" name="fileid" value="" id="fileid" /></div>
        </div>
        <div>
            <input type="radio" checked="true" id="readElement" name="operation" value="readElement">
            <label for="readElement">Read element</label>
            <input type="radio" id="readList" name="operation" value="readList">
            <label for="readList">Read list</label>
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
            Operation complete successfully
        </c:if>
        <c:if test = "${result == false}">
            Operation denied
        </c:if>
    </c:if>

    <form action="LogoutServlet" method="get">
        <div>
            <button type="submit">Logout</button>
        </div>
    </form>
</body>
</html>