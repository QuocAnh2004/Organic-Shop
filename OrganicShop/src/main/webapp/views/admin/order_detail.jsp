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
                    <h1 class="h3 mb-2 text-gray-800 font-weight-bold">Order Detail</h1>
                    <p class="mb-4">Here is a list of all orders.</p>

                    <!-- DataTales Example -->
                    <div class="container-fluid">
					    <div class="card shadow mb-4">
					        <!-- Order Information -->
					        <div class="card mb-4">
					            <div class="card-header d-flex justify-content-between align-items-center py-3">
					                <h6 class="m-0 font-weight-bold text-primary">Order Information</h6>
					                <c:if test="${order.orderStatus eq 'Pending'}">
					                    <form action="/app/admin/setDelivering" method="POST" class="d-inline">
					                        <input type="hidden" name="orderId" value="${order.orderId}">
					                        <button type="button" class="btn btn-success btn-md" onclick="confirmAction(this)">Confirm</button>
					                    </form>
					                </c:if>
					            </div>
					            <div class="card-body">
					                <h5><span class="font-weight-bold">Order ID: #${order.orderId}</span></h5>
					                <p>Order Date: 
										<fmt:formatDate value="${order.orderDate}" pattern="dd-MM-yyyy || HH:mm:ss"/>
									 </p>
					                <p>Shipping Address: ${order.shippingAddress}</p>
					                <p>Phone Number: ${order.customer.phoneNumber}</p>
					                <p>Order Status: 
					                    <c:choose>
					                        <c:when test="${order.orderStatus eq 'Pending'}">
					                            <label class="badge badge-warning p-2" style="font-size: 12px;">Waiting Confirm</label>
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
					                </p>
					                <!-- Other order information... -->
					            </div>
					        </div>
					
					        <div class="card-body">
					            <table class="table table-bordered table-hover" id="dataTable" width="100%" cellspacing="0">
					                <thead class="thead-light">
					                    <tr>
					                        <th>#</th> <!-- Cột số thứ tự -->
					                        <th>Product Name</th>
					                        <th>Image</th>
					                        <th>Quantity</th>
					                        <th>Unit</th>
					                        <th>Unit Price</th>
					                        <th>Total Price</th>
					                    </tr>
					                </thead>
					                <tbody>
					                    <c:forEach var="orderDetail" items="${orderDetails}" varStatus="loop">
					                        <tr>
					                            <td>${loop.index + 1}</td> <!-- Số thứ tự được tính từ 1 -->
					                            <td>${orderDetail.product.productName}</td>
					                            <td><img src="/template/user/picture/${orderDetail.product.imageUrl}"
					                                     class="img-thumbnail" width="100"
					                                     alt="${orderDetail.product.productId}" />
					                            </td>
					                            <td>${orderDetail.quantity}</td>
					                            <td>${orderDetail.product.unit.name}</td>
					                            <td>${orderDetail.unitPrice} $</td>
					                            <td>${orderDetail.totalPrice} $</td>
					                        </tr>
					                    </c:forEach>
					                </tbody>
					                <tfoot>
					                    <tr>
					                        <td colspan="6" class="text-right font-weight-bold">Total Amount:</td>
					                        <td class="font-weight-bold">${order.totalAmount}$</td>
					                    </tr>
					                </tfoot>
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
