<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!-- Spinner Start -->
<div id="spinner"
	class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
	<div class="spinner-grow text-primary" role="status"></div>
</div>
<!-- Spinner End -->

<!-- Navbar Start -->
<div class="container-fluid fixed-top">
	<div class="container topbar bg-primary d-none d-lg-block">
		<div class="d-flex justify-content-between">
			<div class="top-info ps-2">
				<small class="me-3"><i class="fas fa-map-marker-alt me-2 text-secondary"></i> <a href="#" class="text-white"><s:message code="nav.address"/></a></small> 
				<small class="me-3"><i class="fas fa-envelope me-2 text-secondary"></i><a href="#" class="text-white"><s:message code="nav.email"/></a></small>
			</div>
			<div class="top-link pe-2">
				<a href="#" class="text-white"><small class="text-white mx-2"><s:message code="nav.policy"/></small>/</a> 
				<a href="#" class="text-white"><small class="text-white mx-2"><s:message code="nav.term"/></small>/</a> 
				<a href="#" class="text-white"><small class="text-white ms-2"><s:message code="nav.sale"/></small></a>
			</div>
		</div>
	</div>
	<div class="container px-0">
		<nav class="navbar navbar-light bg-white navbar-expand-xl">
			<a href="/app/user/index" class="navbar-brand"><h1 class="text-primary display-6">Fruitables</h1></a>
			<button class="navbar-toggler py-2 px-3" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
				<span class="fa fa-bars text-primary"></span>
			</button>
			<div class="collapse navbar-collapse bg-white" id="navbarCollapse">
				<div class="navbar-nav mx-auto">
					<a href="/app/user/home/index" class="nav-item nav-link"><s:message code="nav.home"/></a>
					<a href="/app/user/product/index" class="nav-item nav-link "><s:message code="nav.shop"/></a>
					<a href="/app/user/contact" class="nav-item nav-link"><s:message code="nav.contact"/></a>
				</div>
				<div class="d-flex m-3 me-0">			
					<a href="/app/user/product/cart" class="position-relative me-4 my-auto">
						<i class="fa fa-shopping-bag fa-2x"></i> 
						<span class="position-absolute bg-secondary rounded-circle d-flex align-items-center justify-content-center text-dark px-1" style="top: -5px; left: 15px; height: 20px; min-width: 20px;">
							${cartCount}
						</span>
					</a>
					<div class="nav-item dropdown">
	                    	<a href="#" class="nav-link dropdown-toggle my-auto" data-bs-toggle="dropdown">
	                        	<i class="fas fa-user fa-2x">
	                            	<span style="font-size: 20px;">${sessionScope.loggedInUser.username}</span>
	                            </i>
	                        </a>	                            
	                        <div class="dropdown-menu m-0 bg-secondary rounded-0">
	                        	<c:choose>
						            <c:when test="${sessionScope.loggedInUser ne null}">
						                <a href="/app/user/editProfile" class="dropdown-item"><s:message code="nav.edit"/></a>
						                <a href="/app/user/changePass" class="dropdown-item"><s:message code="nav.change"/></a>
						                <a href="/app/user/order-history" class="dropdown-item"><s:message code="nav.history"/></a>
						                <c:if test="${sessionScope.roleUserLogin eq 3}">
  								 			<a href="/app/admin/home" class="dropdown-item"><s:message code="nav.admin"/></a>
										</c:if>
						                <a href="/app/user/logout" class="dropdown-item"><s:message code="nav.logout"/></a>
						            </c:when>
						            <c:otherwise>
						                <a href="/app/user/login" class="dropdown-item"><s:message code="nav.login"/></a>
						                <a href="/app/user/account/register" class="dropdown-item"><s:message code="nav.register"/></a>
						                <a href="/app/user/forgotPass" class="dropdown-item"><s:message code="nav.forgot"/></a>
						            </c:otherwise>
						        </c:choose>
	                        </div>
                    	</div>
                    	<ul class="navbar-nav ml-auto">
	                        <li class="nav-item"><a href="?lang=vi" class="nav-link"><img src="/template/user/picture/coVietNam.png" width="55" height="35"></a></li>
	                        <li class="nav-item"><a href="?lang=en" class="nav-link"><img src="/template/user/picture/coNuocAnh.png" width="55" height="35"></a></li>
	                        <li class="nav-item"><a href="?lang=ch" class="nav-link"><img src="/template/user/picture/images.png" width="55" height="35"></a></li>
	                    </ul>
				</div>
			</div>
		</nav>
	</div>
</div>
<!-- Navbar End -->
