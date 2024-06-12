<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Management</title>
<%@ include file="/common/admin/head.jsp"%>
</head>
<body id="page-top">
	<div id="wrapper">
		<%@ include file="/common/admin/sidebar.jsp"%>
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<%@ include file="/common/admin/topbar.jsp"%>
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-9">
							<h1 class="h3 mb-3 text-gray-800 font-weight-bold text-uppercase">Employee
								Management</h1>
							<p>${errorMessage}</p>
						</div>
					</div>
					<div class="px-4 py-3 mb-8 bg-white rounded-lg shadow-md">
						<!-- Employee form -->
						<form:form action="/app/admin/employee/create" method="post"
							modelAttribute="employees_form" enctype="multipart/form-data">
							<!-- Input fields for employee information -->
							<div class="form-row">
								<div class="form-group col-md-6">
									<form:input type="hidden" path="employeeId"
										placeholder="Employee Id" />
									<form:input type="hidden" path="user" placeholder="Employee Id" />
									<form:input type="hidden" path="user.userId"
										placeholder="Employee Id" />
									<label for="firstName" class="text-gray-700">First Name</label>
									<form:input path="firstName" id="firstName"
										class="form-control" placeholder="First Name" />
									<form:errors path="firstName" cssClass="text-danger" />
								</div>
								<div class="form-group col-md-6">
									<label for="lastName" class="text-gray-700">Last Name</label>
									<form:input path="lastName" id="lastName" class="form-control"
										placeholder="Last Name" />
									<form:errors path="lastName" cssClass="text-danger" />
								</div>
								<div  class="form-group col-md-6" style="display: none;">
									<label for="user.userRole.roleId" class="text-gray-700">Position</label>
									<form:select  path="user.userRole.roleId" id="roleId"
										class="form-control">
										<form:options items="${userRoles}" itemValue="roleId"
											itemLabel="roleName" />
									</form:select>
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label for="email" class="text-gray-700">Email</label>
									<form:input path="email" id="email" class="form-control"
										placeholder="Email" />
									<form:errors path="email" cssClass="text-danger" />
								</div>
								<div class="form-group col-md-6">
									<label for="phoneNumber" class="text-gray-700">Phone
										Number</label>
									<form:input path="phoneNumber" id="phoneNumber"
										class="form-control" placeholder="Phone Number" />
									<form:errors path="phoneNumber" cssClass="text-danger" />
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label for="address" class="text-gray-700">Address</label>
									<form:input path="address" id="address" class="form-control"
										placeholder="Address" />
									<!--hình ảnh
                                        <form:input path="picture" id="picture" class="form-control"
										placeholder="picture" />
                                        <img src="/template/admin/picture/${employees_form.picture}" class="img-thumbnail mt-2" width="100" alt="${employees_form.employeeId}" id="currentImage"/>
                                            -->
									<form:errors path="address" cssClass="text-danger" />
								</div>
								<div class="form-group col-md-6">
									<label for="dateOfBirth" class="text-gray-700">Date of
										Birth</label>
									<form:input type="date" path="dateOfBirth" id="dateOfBirth"
										class="form-control" pattern="\d{4}-\d{2}-\d{2}" />
									<form:errors path="dateOfBirth" cssClass="text-danger" />
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label for="gender" class="text-gray-700">Gender</label>
									<div class="form-check">
										<form:radiobutton path="gender" id="male" value="true"
											class="form-check-input" />
										<label class="form-check-label" for="male">Male</label>
									</div>
									<div class="form-check">
										<form:radiobutton path="gender" id="female" value="false"
											class="form-check-input" />
										<label class="form-check-label" for="female">Female</label>
									</div>
									<form:errors path="gender" cssClass="text-danger" />
								</div>
								<div class="form-group col-md-6">
									<label for="picture" class="text-gray-700">Picture</label> <input
										type="file" name="file" id="fileInput"
										class="form-control-file" onchange="previewImages(event)"
										accept="image/*" />
									<div id="previewContainer" class="row">
										<c:if test="${not empty employees_form.picture}">
											<img src="/template/admin/picture/${employees_form.picture}"
												class="img-thumbnail mt-2" width="100" alt=" "
												id="currentImage" />
										</c:if>
									</div>
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-6 mt-3">
									<label class="text-gray-700">Status</label><br>
									<div class="form-check form-check-inline">
										<form:radiobutton path="isActive" id="active" value="true"
											class="form-check-input" />
										<label class="form-check-label" for="active">Active</label>
									</div>
									<div class="form-check form-check-inline">
										<form:radiobutton path="isActive" id="inactive" value="false"
											class="form-check-input" />
										<label class="form-check-label" for="inactive">Inactive</label>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button type="submit" class="btn btn-primary"
										formaction="/app/admin/employee/create">Create</button>
									<button type="submit" class="btn btn-info"
										formaction="/app/admin/employee/update">Update</button>
									<a
										href="/app/admin/employee/delete/${employees_form.employeeId}"
										class="btn btn-danger text-end">Delete</a> <a
										href="/app/admin/employeeForm" class="btn btn-default">Reset</a>
								</div>
							</div>
						</form:form>


					</div>

					<div class="card shadow mb-4 mt-5">
						<div class="card">
							<div class="card-header py-3">
								<div class="d-flex align-items-center justify-content-between">
									<h6 class="m-0 font-weight-bold text-primary text-uppercase">Employee
										List</h6>
									<form class="form-inline">
										<a href="${pageContext.request.contextPath}/app/admin/employee/export" class="btn btn-success mb-3 mt-3 mx-3 ">Export Employees to Excel</a>

										<div class="input-group">
											<input type="text"
												class="form-control bg-light border-0 small"
												placeholder="Search for..." aria-label="Search"
												aria-describedby="basic-addon2">
											<div class="input-group-append">
												<button class="btn btn-primary" type="button">
													<i class="fas fa-search fa-sm"></i>
												</button>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-hover table-bordered">
									<thead class="thead-light">
										<tr>
											<th scope="col">#</th>
											<th scope="col">First Name</th>
											<th scope="col">Last Name</th>
											<th scope="col">Position</th>
											<th scope="col">Email</th>
											<th scope="col">Phone Number</th>
											<th scope="col">Address</th>
											<th scope="col">Date of Birth</th>
											<th scope="col">Gender</th>
											<th scope="col">Picture</th>
											<th scope="col">Status</th>
											<th scope="col">Actions</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="employee" items="${employees}">
											<tr>
												<th scope="row">${employee.employeeId}</th>
												<td>${employee.firstName}</td>
												<td>${employee.lastName}</td>
												<td>${employee.user.userRole.roleName}</td>
												<td>${employee.email}</td>
												<td>${employee.phoneNumber}</td>
												<td>${employee.address}</td>
												<td><fmt:formatDate value="${employee.dateOfBirth}"
														pattern="dd/MM/yyyy" /></td>
												<td>${employee.gender ? "Male" : "Female"}</td>
												<td><c:if test="${not empty employee.picture}">
														<img src="/template/admin/picture/${employee.picture}"
															class="img-thumbnail" width="100"
															alt="${employee.employeeId}" />
													</c:if></td>
												<td><c:if test="${employee.isActive}">
														<span class="badge bg-success text-light">Active</span>
													</c:if> <c:if test="${!employee.isActive}">
														<span class="badge bg-danger text-light">Inactive</span>
													</c:if></td>
												<td><a
													href="${pageContext.request.contextPath}/app/admin/employee/edit/${employee.employeeId}"
													class="btn btn-sm btn-primary">Edit</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
    function previewImages(event) {
        const input = event.target;
        const previewContainer = document.getElementById('previewContainer');
        previewContainer.innerHTML = ''; // Clear previous previews

        if (input.files) {
            const filesArray = Array.from(input.files);
            filesArray.forEach(file => {
                const reader = new FileReader();
                reader.onload = function(e) {
                    const col = document.createElement('div');
                    col.className = 'col-md-4'; // Adjust to display more images
                    const img = document.createElement('img');
                    img.src = e.target.result;
                    img.className = 'img-fluid img-thumbnail mb-2';
                    img.style.maxHeight = '200px';
                    col.appendChild(img);
                    previewContainer.appendChild(col);
                }
                reader.readAsDataURL(file);
            });
        }
    }
</script>
</html>
