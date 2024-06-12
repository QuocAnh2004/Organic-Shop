<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
                        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                                class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
                    </div>

                    <!-- Content Row -->
                    <div class="row">

                        <!-- Earnings (Monthly) Card Example -->
                       <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-primary shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                Earnings (Daily)</div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">${sumDailyAmount} $</div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-calendar fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Earnings (Monthly) Card Example -->
                        <div class="col-xl-3 col-md-6 mb-4">
						    <div class="card border-left-success shadow h-100 py-2">
						        <div class="card-body">
						            <div class="row no-gutters align-items-center">
						                <div class="col mr-2">
						                    <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
						                        Today's Total Amount</div>
						                    <div class="h5 mb-0 font-weight-bold text-gray-800">${sumTotalRevenue} $</div>
						                </div>
						                <div class="col-auto">
						                    <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
						                </div>
						            </div>
						        </div>
						    </div>
						</div>
                        <!-- Earnings (Monthly) Card Example -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-info shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-info text-uppercase mb-1">Tasks
                                            </div>
                                            <div class="row no-gutters align-items-center">
                                                <div class="col-auto">
                                                    <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">50%</div>
                                                </div>
                                                <div class="col">
                                                    <div class="progress progress-sm mr-2">
                                                        <div class="progress-bar bg-info" role="progressbar"
                                                            style="width: 50%" aria-valuenow="50" aria-valuemin="0"
                                                            aria-valuemax="100"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Pending Requests Card Example -->
		                <div class="col-xl-3 col-md-6 mb-4">
			                <div class="card border-left-warning shadow h-100 py-2">
			                    <div class="card-body">
			                        <div class="row no-gutters align-items-center">
			                            <div class="col mr-2">
			                                <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
			                                    Pending Requests</div>
			                                <div class="h5 mb-0 font-weight-bold text-gray-800">
			                                    ${pendingOrdersCount}
			                                </div>
			                            </div>
			                            <div class="col-auto">
			                                <i class="fas fa-comments fa-2x text-gray-300"></i>
			                            </div>
			                        </div>
			                    </div>
			                </div>
		            	</div>
                    </div>

                    <!-- Content Row -->

                    <!--Tables-->
                    <div class="card shadow mb-4">
                        <div class="card">
                            <div class="card-header py-3">
                                <div class="d-flex align-items-center justify-content-between">
                                    <h6 class="m-0 font-weight-bold text-primary">Waiting to Confirm</h6>
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
                                            <c:if test="${order.orderStatus eq 'Pending'}">
                                                <tr>
                                                    <td>${order.orderId}</td>
                                                    <td>${order.customer.firstName} ${order.customer.lastName}</td>
                                                    <td> 
                                                        <fmt:formatDate value="${order.orderDate}" pattern="dd-MM-yyyy || HH:mm:ss"/>
                                                   </td>
                                                    <td>${order.totalAmount} $</td>
                                                    <td>${order.shippingAddress}</td>
													<td><label class="badge badge-warning p-2" style="font-size: 12px;">${order.orderStatus}</label></td>	
                                                    <td>
													    <div class="btn-group" role="group" aria-label="Order Actions">
													        <a href="/app/admin/order-detail/${order.orderId}" class="btn btn-primary btn-sm">Detail</a>
													        <form action="/app/admin/setDelivering" method="POST" style="margin-left: 5px;">
													            <input type="hidden" name="orderId" value="${order.orderId}">
													            <button type="button" class="btn btn-success btn-sm" onclick="confirmAction(this)">Confirm</button>
													        </form>
													        <form action="/app/admin/setCancelled" method="POST" style="margin-left: 5px;">
													            <input type="hidden" name="orderId" value="${order.orderId}">
													            <button type="button" class="btn btn-danger btn-sm" onclick="confirmAction(this)">Delete</button>
													        </form>
													    </div>
													</td>
                                                </tr>
                                            </c:if>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <!--End Tables-->
                    <!--Tables-->
                    <div class="card shadow mb-4">
                        <div class="card">
                            <div class="card-header py-3">
                                <div class="d-flex align-items-center justify-content-between">
                                    <h6 class="m-0 font-weight-bold text-primary">Top best selling products</h6>
                                    <form class="form-inline">
                                        <a href="${pageContext.request.contextPath}/app/admin/top-products/export" class="btn btn-success mb-3 mt-3 mx-3 ">Export to Excel</a>
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

                                <div class="table-responsive">
									<table class="table table-hover table-bordered">
										<thead class="thead-light">
											<tr>
												<th><a href="/productForm?field=productId">Id</a></th>
												<th scope="col">Name</th>
												<th scope="col">Image</th>
												<th scope="col">Total Sold</th>
												<th scope="col">Total Amount</th>
												<th scope="col">Actions</th>
											</tr>
										</thead>
										<tbody>
									        <c:forEach var="product" items="${top10Products}">
									            <tr>
									                <th scope="row">${product.product_id}</th>
									                <td>${product.product_name}</td>
									                <td>
													<c:if test="${not empty product.image_url}">
														<img src="/template/user/picture/${product.image_url}"
															class="img-thumbnail" width="100"
															alt="${product.productId}" />
													</c:if>
													</td>									                
													<td>${product.total_quantity_sold}</td>
													<td>${product.total_revenue} $</td>
									                <td>
									                    <a href="${pageContext.request.contextPath}/app/admin/product/edit/${product.product_id}" class="btn btn-sm btn-primary">Edit</a>
									                  
									                </td>
									            </tr>
									        </c:forEach>
									    </tbody>
									</table>
									
								</div>
                            </div>
                        </div>
                    </div>
                    <!--End Tables-->
                </div>
            </div>
            <!-- End of Main Content -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->
</body>
<script>
    function confirmAction(button) {
        var action = button.textContent; // Lấy hành động từ nội dung của nút
        var confirmationMessage = "Are you sure you want to " + action.toLowerCase() + "?";
        
        // Hiển thị hộp thoại xác nhận
        if (confirm(confirmationMessage)) {
            // Nếu người dùng xác nhận, submit form tương ứng
            button.form.submit();
        } else {
            // Nếu người dùng hủy bỏ, không làm gì cả
        }
    }
</script>
</html>