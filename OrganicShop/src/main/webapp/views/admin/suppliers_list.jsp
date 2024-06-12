<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Management</title>
    <%@ include file="/common/admin/head.jsp" %>
</head>
<body id="page-top">
    <div id="wrapper">
        <%@ include file="/common/admin/sidebar.jsp" %>
        <div id="content-wrapper" class="d-flex flex-column">
            <div id="content">
                <%@ include file="/common/admin/topbar.jsp" %>
                <div class="container-fluid">
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800 font-weight-bold text-uppercase">Suppliers Management</h1>
                        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
                            <i class="fas fa-download fa-sm text-white-50"></i> Generate Report
                        </a>
                    </div>
                    <div class="px-4 py-3 mb-8 bg-white rounded-lg shadow-md mt-3">
                    	<form:form action="/app/admin/categories_list" modelAttribute="item">
							<form:input type="hidden" path="supplierId" placeholder="supplierId?"/>
							<form:input path="name" class="form-control mt-2" placeholder="Supplier Name?"/>
							<form:input path="contactName" class="form-control mt-2" placeholder="Supplier Name Contact?"/>
							<form:input path="contactEmail" class="form-control mt-2" placeholder="Supplier Email Contact?"/>
							<form:input path="contactPhone" class="form-control mt-2" placeholder="Supplier Phone Contact?"/>
							<form:input path="address" class="form-control mt-2" placeholder="Supplier Address?"/>
							<hr>
							 
							<div class="form-group">
						        <div class="col-sm-offset-2 col-sm-10">
						            <button type="submit" class="btn btn-primary" formaction="/app/admin/suppliers/create">Create</button>
						            <button type="submit" class="btn btn-info" formaction="/app/admin/suppliers/update">Update</button>
						            <a href="/app/admin/suppliers/delete/${item.supplierId}" class="btn btn-danger">Delete</a>
						            <a href="/app/admin/suppliers_list" class="btn btn-secondary">Reset</a>
						        </div>
						    </div>
						</form:form>
                        <%-- <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Name</th>
                                            <th>Name Contact</th>
                                            <th>Email</th>
                                            <th>Phone Number</th>
                                            <th>Address</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="supplier" items="${items}">
                                            <tr>
                                                <td>${supplier.supplierId}</td>
                                                <td>${supplier.name}</td>
                                                <td>${supplier.contactName}</td>
                                                <td>${supplier.contactEmail}</td>
                                                <td>${supplier.contactPhone}</td>
                                                <td>${supplier.address}</td>
                                                <td>
                                                    <a href="/suppliers/edit/${supplier.supplierId}" class="btn btn-primary btn-sm mb-2">Edit</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div> --%>
                        
	                        <div class="card shadow mb-4 mt-5">
	                        <div class="card">
	                            <div class="card-header py-3">
	                                <div class="d-flex align-items-center justify-content-between">
	                                    <h6 class="m-0 font-weight-bold text-primary text-uppercase">Suppliers List</h6>
	                                    <form class="form-inline">
	                                        <div class="input-group">
	                                            <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..."
	                                                aria-label="Search" aria-describedby="basic-addon2">
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
	                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
	                                    <thead>
	                                        <tr>
	                                            <th>ID</th>
	                                            <th>Name</th>
	                                            <th>Name Contact</th>
	                                            <th>Email</th>
	                                            <th>Phone Number</th>
	                                            <th>Address</th>
	                                            <th>Action</th>
	                                        </tr>
	                                    </thead>
	                                    <tbody>
	                                        <c:forEach var="supplier" items="${items}">
	                                            <tr>
	                                                <td>${supplier.supplierId}</td>
	                                                <td>${supplier.name}</td>
	                                                <td>${supplier.contactName}</td>
	                                                <td>${supplier.contactEmail}</td>
	                                                <td>${supplier.contactPhone}</td>
	                                                <td>${supplier.address}</td>
	                                                <td>
	                                                    <a href="/app/admin/suppliers/edit/${supplier.supplierId}" class="btn btn-primary btn-sm mb-2">Edit</a>
	                                                </td>
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
    </div>
</body>
</html>
