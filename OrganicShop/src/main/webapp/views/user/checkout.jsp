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
	<!-- Checkout Page Start -->
	<!-- Single Page Header start -->
	<div class="container-fluid page-header py-5">
		<h1 class="text-center text-white display-6">Checkout</h1>
		<ol class="breadcrumb justify-content-center mb-0">
			<li class="breadcrumb-item"><a href="#">Home</a></li>
			<li class="breadcrumb-item"><a href="#">Pages</a></li>
			<li class="breadcrumb-item active text-white">Checkout</li>
		</ol>
	</div>
	<!-- Single Page Header End -->

	<!-- Checkout Page Start -->
	<div class="container-fluid py-5">
		<div class="container py-5">
			<tr>
				<div class="mt-5 text-end">
					<form action="/app/user/checkout/apply-voucher" method="post">
						<input type="text" name="coupon"
							class="border-0 border-bottom rounded me-5 py-3 mb-4"
							placeholder="Coupon Code">
						<button
							class="btn border-secondary rounded-pill px-4 py-3 text-primary"
							type="submit">Apply Coupon</button>
						<p class="text-success h6 text-end">${message}</p>
					</form>
				</div>
			</tr>
			<h1 class="mb-4">Billing details</h1>
			<form action="/app/user/checkout" method="post">
				<div class="row g-5">
					<div class="col-md-12 col-lg-6 col-xl-7">
						<div class="row">
							<div class="col-md-12 col-lg-6">
								<div class="form-item w-100">
									<label class="form-label my-3">First Name<sup>*</sup></label> <input
										type="text" class="form-control" value="${customer.firstName}"
										readonly>
								</div>
							</div>
							<div class="col-md-12 col-lg-6">
								<div class="form-item w-100">
									<label class="form-label my-3">Last Name<sup>*</sup></label> <input
										type="text" class="form-control" value="${customer.lastName}"
										readonly>
								</div>
							</div>
						</div>
						<div class="form-item">
							<label class="form-label my-3">Address <sup>*</sup></label> <input
								type="text" class="form-control" value="${customer.address}"
								readonly>
						</div>
						<div class="form-item">
							<label class="form-label my-3">Mobile<sup>*</sup></label> <input
								type="tel" class="form-control" value="${customer.phoneNumber}"
								readonly>
						</div>
						<div class="form-item">
							<label class="form-label my-3">Email Address<sup>*</sup></label>
							<input type="email" class="form-control"
								value="${customer.email}" readonly>
						</div>
					</div>
					<div class="col-md-12 col-lg-6 col-xl-5">
						<div class="table-responsive">
							<table class="table">
								<thead>
									<tr>
										<th scope="col">Products</th>
										<th scope="col">Name</th>
										<th scope="col">Price</th>
										<th scope="col">Quantity</th>
										<th scope="col">Total</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="product" items="${cartItems}">
										<tr>
											<th scope="row">
												<div class="d-flex align-items-center mt-2">
													<c:url value="/template/user/picture/${product.imageUrl}"
														var="imageUrl" />
													<img src="${imageUrl}" class="img-fluid rounded-circle"
														style="width: 90px; height: 90px;" alt="">
												</div>
											</th>
											<td class="py-5">${product.productName}</td>
											<td class="py-5">${product.price}</td>
											<td class="py-5">${product.quantity}</td>
											<td class="py-5">${product.quantity * product.price}</td>
										</tr>
									</c:forEach>
									<tr>
										<th scope="row"></th>
										<td class="py-5"></td>
										<td class="py-5"></td>
										<td class="py-5">
											<p class="mb-0 text-dark py-3">Subtotal</p>
										</td>
										<td class="py-5">
											<div class="py-3 border-bottom border-top">
												<p class="mb-0 text-dark">${subtotal}</p>
											</div>
										</td>
									</tr>
									<%-- <tr>
										<div class="mt-5">
											<form action="/app/user/checkout/apply-voucher" method="post">
												<input type="text" name="coupon"
													class="border-0 border-bottom rounded me-5 py-3 mb-4"
													placeholder="Coupon Code">
												<button
													class="btn border-secondary rounded-pill px-4 py-3 text-primary"
													type="submit">Apply Coupon</button>
												<p class="text-success h6">${message}</p>
											</form>
										</div>
									</tr> --%>
									<tr>
										<th scope="row"></th>
										<td class="py-5">
											<p class="mb-0 text-dark text-uppercase py-3">Shipping to</p>
										</td>
										<td class="py-5"></td>
										<td class="py-5"></td>
										<td class="py-5">
											<div class="py-3 border-bottom border-top">
												<p class="mb-0 text-dark">${sessionScope.loggedInCustomer.address}</p>
											</div>
										</td>
									</tr>
									<tr>
										<th scope="row"></th>
										<td class="py-5">
											<p class="mb-0 text-dark text-uppercase py-3">Discount</p>
										</td>
										<td class="py-5"></td>
										<td class="py-5"></td>
										<td class="py-5"><c:if test="${not empty discountValue}">
												<div class="py-3 border-bottom border-top">
													<p class="mb-0 text-dark h6">${discountValue}%</p>
												</div>
											</c:if> <c:if test="${empty discountValue}">
												<div class="py-3 border-bottom border-top">
													<p class="mb-0 text-dark h6">Not yet</p>
												</div>
											</c:if></td>
									</tr>
									<tr>
										<th scope="row"></th>
										<td class="py-5">
											<p class="mb-0 text-dark text-uppercase py-3">Shipping fee</p>
										</td>
										<td class="py-5"></td>
										<td class="py-5"></td>
										<td class="py-5">
											<div class="py-3 border-bottom border-top">
												<p class="mb-0 text-dark">15.0 $</p>
											</div>
										</td>
									</tr>
									<tr>
										<th scope="row"></th>
										<td class="py-5">
											<p class="mb-0 text-dark text-uppercase py-3">TOTAL</p>
										</td>
										<td class="py-5"></td>
										<td class="py-5"></td>
										<td class="py-5">
											<div class="py-3 border-bottom border-top">
												<p class="mb-0 text-dark">${subtotal-(subtotal *discountValue/100) +15} $</p>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>											
						<button type="submit" class="btn btn-primary w-100 py-3">Place
							Order
							</button>
					</div>
				</div>
			</form>
		</div>

	</div>

	<!-- Checkout Page End -->
	<%@ include file="/common/footer.jsp"%>
</body>
</html>
