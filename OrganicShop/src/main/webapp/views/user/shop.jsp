<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Shop</title>
    <%@ include file="/common/head.jsp" %>
    <style>
        .product-img {
            width: 100%;
            height: 200px;
            object-fit: cover;
        }
    </style>
</head>

<body>
    <%@ include file="/common/header.jsp" %>
    <!-- Single Page Header start -->
    <div class="container-fluid page-header py-5">
        <h1 class="text-center text-white display-6">Shop</h1>
        <ol class="breadcrumb justify-content-center mb-0">
            <li class="breadcrumb-item"><a href="#">Home</a></li>
            <li class="breadcrumb-item"><a href="#">Pages</a></li>
            <li class="breadcrumb-item active text-white">Shop</li>
        </ol>
    </div>
    <!-- Single Page Header End -->

    <!-- Fruits Shop Start-->
    <div class="container-fluid fruite py-5">
        <div class="container py-5">
            <h1 class="mb-4">Fresh fruits shop</h1>
            <div class="row g-4">
                <div class="col-lg-12">
                    <div class="row g-4">
                        <div class="col-xl-3">
                            <div class="input-group w-100 mx-auto d-flex">
                                <form action="/app/user/product/search-and-page" method="get" class="w-100 d-flex">
                                    <input type="search" name="keywords" value="${keyword}" class="form-control p-3"  aria-describedby="search-icon-1">
                                    <button type="submit" id="search-icon-1" class="input-group-text p-3">
                                        <i class="fa fa-search"></i>
                                    </button>
                                </form>
                            </div>
                        </div>
                        <div class="col-6"></div>
                        <div class="col-xl-3">
						    <div class="bg-light ps-3 py-3 rounded d-flex justify-content-between mb-4">
						        <label for="fruits">Default Sorting:</label>
						        <form id="sortingForm" action="/app/user/product/index" method="get">
						        <select id="fruits" name="sort" class="border-0 form-select-sm bg-light me-3" form="sortingForm">
						            <option value="asc" <c:if test="${sort == 'asc'}">selected</c:if>>Low to High</option>
						            <option value="desc" <c:if test="${sort == 'desc'}">selected</c:if>>High to Low</option>
						        </select>
						        </form>
						    </div>
						</div>
                    </div>
                    <div class="row g-4">
                        <div class="col-lg-3">
                            <div class="row g-4">
                                <div class="col-lg-12">
                                    <div class="mb-3">
                                        <h4>Categories</h4>
                                        <ul class="list-unstyled fruite-categorie">
                                        <c:forEach var="category" items="${categories}">
                                            <li>
                                                <div class="d-flex justify-content-between fruite-name">
                                                    <a href="/app/user/product/index?categoryId=${category.categoryId}"><i class="fas fa-apple-alt me-2"></i>${category.categoryName}</a>
                                                </div>
                                            </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="mb-3">
                                        <h4 class="mb-2">Price Range:</h4>
                                        	<form action="/app/user/product/index" method="get" class="w-100 d-flex">
                                        		<input type="number" name="minPrice" placeholder="Min Price" class="form-control me-2">
                                        		<input type="number" name="maxPrice" placeholder="Max Price" class="form-control me-2">
                                        		<button type="submit" class="btn btn-primary">Search</button>
                                        	</form>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-9">
                            <div class="row g-4">
                                <c:forEach var="product" items="${items_products.content}">
                                    <div class="col-md-6 col-lg-4">
                                        <c:url value="/app/user/product/detail/${product.productId}" var="productDetailUrl"/>
                                        <a href="${productDetailUrl}">
                                            <div class="rounded position-relative fruite-item">
                                                <div class="fruite-img">
                                                    <c:url value="/template/user/picture/${product.imageUrl}" var="imageUrl"/>
                                                    <img src="${imageUrl}" class="product-img img-fluid w-100 rounded-top" alt="${product.productName}">
                                                </div>
                                                <div class="text-white bg-secondary px-3 py-1 rounded position-absolute" style="top: 10px; left: 10px;">
                                                    ${product.category.categoryName}
                                                </div>
                                                <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                    <h4>${product.productName}</h4>
                                                    <p>${product.description}</p>
                                                    <div class="d-flex justify-content-between flex-lg-wrap">
														<p class="text-dark fs-6 fw-bold mb-0">${product.price} $/ ${product.unit.name}</p>
                                                        <form action="/app/user/product/add-to-cart/${product.productId}" method="post">
                                                            <button type="submit" class="btn border border-secondary rounded-pill px-3 text-primary">
                                                                <i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart
                                                            </button>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                </c:forEach>
                                <div class="col-12">
                                    <div class="pagination d-flex justify-content-center mt-5">
                                        <c:if test="${items_products.hasPrevious()}">
                                            <a href="?page=0&sort=${sort}" class="rounded">FIRST</a>
                                            <a href="?page=${items_products.number - 1}&sort=${sort}" class="rounded">&laquo;</a>
                                        </c:if>
                                        <c:if test="${items_products.hasNext()}">
                                            <a href="?page=${items_products.number + 1}&sort=${sort}" class="rounded">&raquo;</a>
                                            <a href="?page=${items_products.totalPages - 1}&sort=${sort}" class="rounded">LAST</a>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Fruits Shop End-->
    <%@ include file="/common/footer.jsp" %>
</body>
<script>
    // Add event listener to the select element
    document.getElementById('fruits').addEventListener('change', function() {
        // Submit the form when the select value changes
        document.getElementById('sortingForm').submit();
    });
</script>
</html>
