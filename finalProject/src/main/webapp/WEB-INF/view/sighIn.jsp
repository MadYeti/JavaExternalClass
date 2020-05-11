<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored ="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
    <body class="bg-light">
        <fmt:bundle basename="messages">
            <nav class="site-header sticky-top py-1" style="background-color: #000">
                <div class="container d-flex flex-column flex-md-row justify-content-between">
                    <a class="py-2" href="/MainPage" aria-label="Logic">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="d-block mx-auto" role="img" viewBox="0 0 24 24" focusable="false"><title>Product</title><circle cx="12" cy="12" r="10"></circle><path d="M14.31 8l5.74 9.94M9.69 8h11.48M7.38 12l5.74-9.94M9.69 16L3.95 6.06M14.31 16H2.83m13.79-4l-5.74 9.94"></path></svg>
                    </a>
                    <a class="py-2 d-none d-md-inline-block" href="/MainPage"><fmt:message key="msg.mainPage.button"/></a>
                    <a class="py-2 d-none d-md-inline-block" href="/sighUp"><fmt:message key="msg.sighUp.button"/></a>
                    <a class="py-2 d-none d-md-inline-block" href="#"><fmt:message key="msg.support.button"/></a>
                    <a class="py-2 d-none d-md-inline-block" href="#"><fmt:message key="msg.pricing.button"/></a>
                </div>
            </nav>

            <div class="container">

                <div class="py-5 text-center">
                    <h4 class="mb-3"><fmt:message key="msg.sighIn.button"/></h4>
                </div>

                <c:set var="client" scope="request" value="${requestScope.client}" />

                <div class="d-flex justify-content-center">
                    <div class="col-md-8 order-md-1">
                        <div class="my-3 p-3 bg-white rounded shadow-sm">
                            <form class="needs-validation" id="sighInForm" action="AuthorizationServlet" method="post">

                                <div class="mb-3">
                                    <label><fmt:message key="msg.email.input.label"/></label>
                                    <input class="form-control" type="text" name="email" value="" id="email" />
                                </div>

                                <div class="mb-3">
                                    <label><fmt:message key="msg.password.input.label"/></label>
                                    <input class="form-control" type="password" name="password" value="" id="password" />
                                    <div style="color: #dc3545; font-size: 75%;">
                                        <c:if test = "${client eq 'Warning'}">
                                            <fmt:message key="msg.sighIn.input.error.option1"/>
                                        </c:if>
                                        <c:if test = "${client eq 'IncorrectInput'}">
                                            <fmt:message key="msg.sighIn.input.error.option2"/>
                                        </c:if>
                                    </div>
                                    <a href="/passwordRecovery" id="passwordRecovery"><fmt:message key="msg.forgotPassword.label"/></a>
                                </div>
                                <label id="remember"><input name="rememberMe" type="checkbox" value="Remember me" /><fmt:message key="msg.rememberme.checkbox"/></label>
                                <hr class="mb-4">
                                <div class="mb-3">
                                    <div>
                                        <button class="btn btn-primary btn-lg btn-block" type="submit"><fmt:message key="msg.submit.button"/></button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <footer class="my-5 pt-5 text-muted text-center text-small">
                    <p class="mb-1">© 2020 Logic</p>
                    <ul class="list-inline">
                        <li class="list-inline-item"><a href="#"><fmt:message key="msg.privacy.label"/></a></li>
                        <li class="list-inline-item"><a href="#"><fmt:message key="msg.terms.label"/></a></li>
                        <li class="list-inline-item"><a href="#"><fmt:message key="msg.support.button"/></a></li>
                    </ul>
                </footer>
            </div>
        </fmt:bundle>
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </body>
</html>