<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
<%@ include file="/common/head.jsp" %>
</head>
<body>
	<%@ include file="/common/header.jsp" %>
	<div class="container-fluid py-5">
        <div class="container py-5">
            <div class="row justify-content-center">
                <div class="col-lg-6">
                    <h1 class="mt-5">Register</h1>
                    <form:form action="/app/user/account/save"  modelAttribute="customer">
                        <div class="mb-3">
                            <label for="registerFirstName" class="form-label">First Name</label>
                            <form:input path="firstName" id="registerFirstName" class="form-control"  />
                            <form:errors path="firstName" class="text-danger" />
                        </div>
                        <div class="mb-3">
                            <label for="registerLastName" class="form-label">Last Name</label>
                            <form:input path="lastName" id="registerLastName" class="form-control"   />
                            <form:errors path="lastName" class="text-danger" />
                        </div>
                        <div class="mb-3">
                            <label for="registerEmail" class="form-label">Email address</label>
                            <form:input path="email" id="registerEmail" class="form-control"   />
                            <form:errors path="email" class="text-danger" />
                        </div>
                        <div class="mb-3">
                            <label for="registerAddress" class="form-label">Address</label>
                            <form:input path="address" id="registerAddress" class="form-control"   />
                            <form:errors path="address" class="text-danger" />
                        </div>
                        <div class="mb-3">
                            <label for="registerPhoneNumber" class="form-label">Phone Number</label>
                            <form:input path="phoneNumber" id="registerPhoneNumber" class="form-control"   />
                            <form:errors path="phoneNumber" class="text-danger" />
                        </div>
                        <div class="mb-3">
                            <label for="registerDateOfBirth" class="form-label">Date of Birth</label>
                            <form:input path="dateOfBirth"  id="registerDateOfBirth" class="form-control" type="date"   />
                            <form:errors path="dateOfBirth" class="text-danger" />
                        </div>
                        <div class="mb-3">
                            <label for="registerGender" class="form-label">Gender</label>
                            <form:select path="gender" id="registerGender" class="form-select"  >
							<form:option value="true">Male</form:option>
                                <form:option value="false">Female</form:option>
                            </form:select>
                            <form:errors path="gender" cssClass="text-danger" />
                        </div>                        
                        <button type="submit" class="btn btn-primary">Register</button>
                    </form:form>
						<h1>${message}</h1>
                    <p class="mt-3">Already have an account? <a href="login.html">Login here</a></p>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="/common/footer.jsp" %>
</body>
</html>