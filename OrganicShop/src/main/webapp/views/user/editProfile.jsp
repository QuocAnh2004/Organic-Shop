<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Profile</title>
    <%@ include file="/common/head.jsp" %>
</head>
<body>
    <%@ include file="/common/header.jsp" %>
    <div class="container-fluid py-5">
        <div class="container py-5">
            <div class="row justify-content-center">
                <div class="col-lg-6">
                    <h1 class="mt-5">Edit profile</h1>
                    <!-- Display error message if any -->
                    <% if (request.getAttribute("error") != null) { %>
                        <div class="alert alert-danger" role="alert">
                            <%= request.getAttribute("error") %>
                        </div>
                    <% } %>
                    <form:form modelAttribute="customer" action="/app/user/editProfile" method="post">
                        <div class="mb-3">
                            <label for="firstName" class="form-label">First name</label>
                            <form:input type="text" path="firstName" class="form-control" id="firstName" />
                            <form:errors path="firstName" cssClass="text-danger"/>
                        </div>
                        <div class="mb-3">
                            <label for="lastName" class="form-label">Last name</label>
                            <form:input type="text" path="lastName" class="form-control" id="lastName" />
                            <form:errors path="lastName" cssClass="text-danger"/>
                        </div>
                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <form:input type="text" path="email" class="form-control" id="email" />
                            <form:errors path="email" cssClass="text-danger"/>
                        </div>
                        <div class="mb-3">
                            <label for="address" class="form-label">Address</label>
                            <form:input type="text" path="address" class="form-control" id="address" />
                            <form:errors path="address" cssClass="text-danger"/>
                        </div>
                        <div class="mb-3">
                            <label for="phoneNumber" class="form-label">Phone number</label>
                            <form:input type="text" path="phoneNumber" class="form-control" id="phoneNumber" />
                            <form:errors path="phoneNumber" cssClass="text-danger"/>
                        </div>
                        <div class="mb-3">
                            <label for="dateOfBirth" class="form-label">Date of birth</label>
                            <form:input type="text" path="dateOfBirth" class="form-control" id="dateOfBirth" placeholder ="yyyy-MM-dd" />
                            <form:errors path="dateOfBirth" cssClass="text-danger"/>
                        </div>
                        <div class="mb-3">
                            <label for="gender" class="form-label">Gender</label>
                            <form:select path="gender" class="form-control" id="gender">
                                <form:option value="true">Male</form:option>
                                <form:option value="false">Female</form:option>
                            </form:select>
                            <form:errors path="gender" cssClass="text-danger"/>
                        </div>
                        <button type="submit" class="btn btn-primary">Save</button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="/common/footer.jsp" %>
</body>
</html>
