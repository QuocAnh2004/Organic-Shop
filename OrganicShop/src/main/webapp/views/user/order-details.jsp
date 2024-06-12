<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Details</title>
<%@ include file="/common/head.jsp" %>
</head>
<body>
    <%@ include file="/common/header.jsp" %>
    <!-- Order Details Start -->
    <div class="container-fluid py-5">
        <div class="container py-5">
            <h1 class="mb-4">Order Details</h1>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th scope="col">Product Name</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Unit Price</th>
                        <th scope="col">Total Price</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${orderDetails}">
                        <tr>
                            <td>${item.product.productName}</td>
                            <td>${item.quantity}</td>
                            <td>${item.unitPrice} $</td>
                            <td>${item.totalPrice} $</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <%@ include file="/common/footer.jsp" %>
    <!-- Order Details End -->
</body>
</html>
