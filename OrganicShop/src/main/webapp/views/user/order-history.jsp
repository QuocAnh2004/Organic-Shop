<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order History</title>
<%@ include file="/common/head.jsp" %>
</head>
<body>
    <%@ include file="/common/header.jsp" %>
    <!-- Order History Start -->
    <div class="container-fluid py-5 mt-5">
        <div class="container py-5 mt-5">
            <h1 class="mb-4 text-center">Order History</h1>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th scope="col">Order Date</th>
                        <th scope="col">Total Amount</th>
                        <th scope="col">Status</th>
                        <th scope="col">Details</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="order" items="${orders}">
                        <tr>
                            <td>
                                <form:form modelAttribute="orderForm" action="/app/user/order-details/${order.orderId}" method="get">
                                    <input type="hidden" name="orderId" value="${order.orderId}" />
                                    <fmt:formatDate value="${order.orderDate}" pattern="dd-MM-yyyy || HH:mm:ss"/>
                                </form:form>
                            </td>
                            <td>${order.totalAmount} $</td>
                            <td>${order.orderStatus}</td>
                            <td>
                                <form:form modelAttribute="orderForm" action="/app/user/order-details/${order.orderId}" method="get">
                                    <input type="hidden" name="orderId" value="${order.orderId}" />
                                    <button type="submit" class="btn btn-primary">View Details</button>
                                </form:form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <%@ include file="/common/footer.jsp" %>
    <!-- Order History End -->
</body>
</html>
