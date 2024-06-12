<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/common/taglib.jsp" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<%@ include file="/common/head.jsp" %>
</head>
<body>
	<%@ include file="/common/header.jsp" %>
	<!-- Login Form Start -->
    <div class="container-fluid py-5">
        <div class="container py-5">
            <div class="row justify-content-center">
                <div class="col-lg-6">
                    <h1 class="mt-5">Login</h1>
                    <!-- Display error message if any -->
                    <% if (request.getAttribute("error") != null) { %>
                        <div class="alert alert-danger" role="alert">
                            <%= request.getAttribute("error") %>
                        </div>
                    <% } %>
                    <form:form modelAttribute="user" action="/app/user/login" method="post">
                        <div class="mb-3">
                            <label for="loginUserId" class="form-label">Username</label>
                            <form:input type="text" path="username" class="form-control" id="loginUserName" />
                            <form:errors path="username" cssClass="text-danger"/>
                        </div>
                        <div class="mb-3">
                            <label for="loginPassword" class="form-label">Password</label>
                            <form:input type="password" path="passwordUser" class="form-control" id="loginPassword" />
                            <form:errors path="passwordUser" cssClass="text-danger"/>
                        </div>
                        <button type="submit" class="btn btn-primary">Login</button>
                    </form:form>
                    <p class="mt-3">Don't have an account? <a href="/app/user/register">Register here</a></p>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="/common/footer.jsp" %>
    <!-- Login Form End -->
</body>
</html>