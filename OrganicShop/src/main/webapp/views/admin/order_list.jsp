<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order List</title>
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
                    <h1 class="h3 mb-2 text-gray-800 font-weight-bold">Order List</h1>
                    <p class="mb-4">Here is a list of all orders.</p>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">Orders</h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Customer Name</th>
                                            <th>Order Date</th>
                                            <th>Total Amount</th>
                                            <th>Shipping Address</th>
                                            <th>Order Status</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="order" items="${orders}">
                                             <tr>
                                                 <td>${order.orderId}</td>
                                                 <td>${order.customer.firstName} ${order.customer.lastName}</td>
                                                 <td> 
                                                    <fmt:formatDate value="${order.orderDate}" pattern="dd-MM-yyyy || HH:mm:ss"/>
                                               </td>
                                                 <td>${order.totalAmount} $</td>
                                                 <td>${order.shippingAddress}</td>
                                                 <td>
													<c:choose>
														<c:when test="${order.orderStatus eq 'Pending'}">
															<label class="badge badge-warning p-2" style="font-size: 12px;">Pending</label>
														</c:when>
														<c:when test="${order.orderStatus eq 'Delivering'}">
															<label class="badge badge-info p-2" style="font-size: 12px;">Delivering</label>
														</c:when>
														<c:when test="${order.orderStatus eq 'Cancelled'}">
															<label class="badge badge-danger p-2" style="font-size: 12px;">Cancelled</label>
														</c:when>
														<c:otherwise>
															<label class="badge badge-success p-2" style="font-size: 12px;">Success</label>
														</c:otherwise>
													</c:choose>	
	                                            </td>
                                                 <td>
												    <div class="btn-group" role="group" aria-label="Order Actions">
												        <a href="/app/admin/order-detail/${order.orderId}" class="btn btn-primary btn-sm">Detail</a>
												    </div>
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
