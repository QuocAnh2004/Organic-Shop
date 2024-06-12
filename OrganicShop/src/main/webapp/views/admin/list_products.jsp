<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product List</title>
<%@ include file="/common/admin/head.jsp" %>
</head>
<body id="page-top">
    <!-- Page Wrapper -->
    <div id="wrapper">
        <!-- Sidebar -->
        <%@ include file="/common/admin/sidebar.jsp" %>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">
            <!-- Main Content -->
            <div id="content">
                <!-- Topbar -->
                <%@ include file="/common/admin/topbar.jsp" %>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800 font-weight-bold">Product List</h1>
                    <p class="mb-4">Here is a list of all products.</p>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">Products</h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Name</th>
                                            <th>Description</th>
                                            <th>Price</th>
                                            <th>Sale Price</th>
                                            <th>Unit</th>
                                            <th>Image</th>
                                            <th>Import Date</th>
                                            <th>Quantity</th>
                                            <th>Exp Date</th>
                                            <th>Category</th>
                                            <th>Supplier</th>
                                            <th>Active</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="product" items="${products}">
                                            <tr>
                                                <td>${product.productId}</td>
                                                <td>${product.productName}</td>
                                                <td>${product.description}</td>
                                                <td>${product.price}</td>
                                                <td>${product.salePrice}</td>
                                                <td>${product.unit.name}</td>
                                                <td><img src="/template/user/picture/${product.imageUrl}" alt="Product Image" style="width: 50px; height: 50px;"></td>
                                                <td>${product.importDate}</td>
                                                <td>${product.quantity}</td>
                                                <td>${product.expDate}</td>
                                                <td>${product.category.categoryName}</td>
                                                <td>${product.supplier.name}</td>
                                                <td>${product.isActive ? "Yes" : "No"}</td>
                                                <td>
                                                    <a href="/app/admin/product/edit/${product.productId}" class="btn btn-primary btn-sm mb-2">Edit</a>
                                                    <a href="/app/admin/deleteProduct?id=${product.productId}" class="btn btn-danger btn-sm">Delete</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End of Page Content -->
            </div>
            <!-- End of Main Content -->
        </div>
        <!-- End of Content Wrapper -->
    </div>
    <!-- End of Page Wrapper -->
</body>
</html>
