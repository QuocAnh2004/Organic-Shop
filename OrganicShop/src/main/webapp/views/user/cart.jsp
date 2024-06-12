<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Fruitables - Vegetable Website Template</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">
<%@ include file="/common/head.jsp"%>
</head>
<body>
	<%@ include file="/common/header.jsp"%>
	<!-- Cart Page Start -->
	<!-- Single Page Header start -->
	<div class="container-fluid page-header py-5">
		<h1 class="text-center text-white display-6">Cart</h1>
		<ol class="breadcrumb justify-content-center mb-0">
			<li class="breadcrumb-item"><a href="#">Home</a></li>
			<li class="breadcrumb-item"><a href="#">Pages</a></li>
			<li class="breadcrumb-item active text-white">Cart</li>
		</ol>
	</div>
	<!-- Single Page Header End -->
	<!-- Cart Page Start -->
	<div class="container-fluid py-5">
		<div class="container py-5">
			<div class="table-responsive">
				<table class="table">
					<thead>
						<tr>
							<th scope="col">Products</th>
							<th scope="col">Name</th>
							<th scope="col">Price</th>
							<th scope="col">Quantity</th>
							<th scope="col">Total</th>
							<th scope="col">Handle</th>
						</tr>
					</thead>
					<!-- Phần giỏ hàng -->
<tbody>
    <c:forEach var="product" items="${cart}">
        <tr>
            <th scope="row">
                <div class="d-flex align-items-center">
                    <c:url value="/template/user/picture/${product.imageUrl}" var="imageUrl"/>
                    <img src="${imageUrl}" class="img-fluid me-5 rounded-circle" style="width: 80px; height: 80px;" alt="${product.productName}">
                </div>
            </th>
            <td>
                <p class="mb-0 mt-4">${product.productName}</p>
            </td>
            <td>
                <p class="mb-0 mt-4">${product.price} $</p>
            </td>
            <td>
                <div class="input-group quantity mt-4" style="width: 100px;">
                    <div class="input-group-btn">
                        <form action="/app/user/product/cart/update/${product.productId}" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="minus"/>
                            <button type="submit" class="btn btn-sm btn-minus rounded-circle bg-light border">
                                <i class="fa fa-minus"></i>
                            </button>
                        </form>
                    </div>
                    <input type="text" class="form-control form-control-sm text-center border-0" value="${product.quantity}" readonly>
                    <div class="input-group-btn">
                        <form action="/app/user/product/cart/update/${product.productId}" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="plus"/>
                            <button type="submit" class="btn btn-sm btn-plus rounded-circle bg-light border">
                                <i class="fa fa-plus"></i>
                            </button>
                        </form>
                    </div>
                </div>
            </td>
            <td>
                <p class="mb-0 mt-4">${product.quantity * product.price} $</p> <!-- Tính tổng giá của sản phẩm -->
            </td>
            <td>
                <form action="/app/user/product/cart/remove/${product.productId}" method="post">
                    <button type="submit" class="btn btn-md rounded-circle bg-light border mt-4">
                        <i class="fa fa-times text-danger"></i>
                    </button>
                </form>
            </td>
        </tr>
    </c:forEach>
</tbody>
</table>
</div>
<div class="mt-5">
<!-- Form áp dụng mã giảm giá -->
</div>
<%-- <div class="mt-5">
    <form action="/product/cart/apply-voucher" method="post">
        <input type="text" name="coupon" class="border-0 border-bottom rounded me-5 py-3 mb-4" placeholder="Coupon Code">
        <button class="btn border-secondary rounded-pill px-4 py-3 text-primary" type="submit">Apply Coupon</button>
        <p class="text-success h6">${message}</p>
    </form>
</div> --%>
<div class="row g-4 justify-content-end">
    <div class="col-8"></div>
    <div class="col-sm-8 col-md-7 col-lg-6 col-xl-4">
        <div class="bg-light rounded">
            <div class="p-4">
                <h1 class="display-6 mb-4">
                    Cart <span class="fw-normal">Total</span>
                </h1>
                <div class="d-flex justify-content-between mb-4">
                    <h5 class="mb-0 me-4">Subtotal:</h5>
                    <p class="mb-0">${subtotal} $</p> <!-- Hiển thị Subtotal -->
                </div>
               <%--  <div class="d-flex justify-content-between">
                    <h5 class="mb-0 me-4">Discount:</h5>
                    <p class="mb-0">- ${discountValue}%</p> <!-- Hiển thị giảm giá -->
                </div> --%>
                <div class="d-flex justify-content-between">
                    <h5 class="mb-0 me-4">Shipping:</h5>
                    <div class="">
                        <c:if test="${empty cart}"><p class="mb-0">Not yet</p></c:if>
                        <c:if test="${not empty cart}"><p class="mb-0">15.0$</p></c:if>

                        <!-- <p class="mb-0">15.0$</p> -->
                    </div>
                </div>
                <p class="mb-0 text-end">Shipping to <span class="fw-bold text-dark fs-6">${sessionScope.loggedInCustomer.address}</span></p>
            </div>
            <div class="py-4 mb-4 border-top border-bottom d-flex justify-content-between">
                <h5 class="mb-0 ps-4 me-4">Total:</h5>
               <%--  <h5 class="mb-0 ps-4 me-4">Sub: ${subtotal}</h5>
                <h5 class="mb-0 ps-4 me-4">discountAmount: ${discountAmount}</h5> --%>
                <c:if test="${empty cart}"><p class="mb-0 pe-4">Not yet</p></c:if>
                <c:if test="${not empty cart}">
                <p class="mb-0 pe-4">${subtotal +15} $</p></c:if> <!-- Hiển thị Total sau khi giảm giá -->
            </div>
            <a href="/app/user/checkout" class="btn border-secondary rounded-pill px-4 py-3 text-primary text-uppercase mb-4 ms-4" type="button">Proceed Checkout</a>
        </div>
    </div>
</div>
</div>
</div>

	<!-- Cart Page End -->
	<%@ include file="/common/footer.jsp"%>
</body>
</html>