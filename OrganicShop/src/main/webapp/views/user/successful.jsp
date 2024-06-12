<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Fruitables - Vegetable Website Template</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">
    <%@ include file="/common/head.jsp" %>
</head>
<body>
<%@ include file="/common/header.jsp" %>
<!-- Success Message Start -->
<div class="container-fluid py-5 ">
    <div class="container py-5 text-center mt-5">
        <div class="row justify-content-center">
            <div class="col-lg-6">
                <i class="bi bi-check-circle display-1 text-success"></i>
                <h1 class="display-1">Success</h1>
                <h1 class="mb-4">Order Placed Successfully</h1>
                <p class="mb-4">Thank you for your order! Your order has been successfully placed.</p>
                <a class="btn border-success rounded-pill py-3 px-5" href="/app/user/home/index">Go Back To Home</a>
            </div>
        </div>
    </div>
</div>
<!-- Success Message End -->

<%@ include file="/common/footer.jsp" %>
<!-- Copyright Start -->
<div class="container-fluid copyright bg-dark py-4">
    <div class="container">
        <div class="row">
            <div class="col-md-6 text-center text-md-start mb-3 mb-md-0">
                <span class="text-light"><a href="#"><i class="fas fa-copyright text-light me-2"></i>Your Site Name</a>, All right reserved.</span>
            </div>
            <div class="col-md-6 my-auto text-center text-md-end text-white">
                <!--/*** This template is free as long as you keep the below author’s credit link/attribution link/backlink. ***/-->
                <!--/*** If you'd like to use the template without the below author’s credit link/attribution link/backlink, ***/-->
                <!--/*** you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". ***/-->
                Designed By <a class="border-bottom" href="https://htmlcodex.com">HTML Codex</a> Distributed By <a class="border-bottom" href="https://themewagon.com">ThemeWagon</a>
            </div>
        </div>
    </div>
</div>
<!-- Copyright End -->
<!-- Back to Top -->
<a href="#" class="btn btn-primary border-3 border-primary rounded-circle back-to-top"><i class="fa fa-arrow-up"></i></a>   

</body>
</html>
