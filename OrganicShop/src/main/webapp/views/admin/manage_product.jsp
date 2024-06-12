
 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Management</title>
<%@ include file="/common/admin/head.jsp"%>
</head>
<body id="page-top">
	<div id="wrapper">
		<%@ include file="/common/admin/sidebar.jsp"%>
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<%@ include file="/common/admin/topbar.jsp"%>
				<div class="container-fluid">
					<!-- <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        
                    </div> -->
					<div class="row">
						<div class="col-md-9">
							<h1 class="h3 mb-3 text-gray-800 font-weight-bold text-uppercase">Product
								Management</h1>
						</div>
						<div class="col-md-3">
							<a href="/app/admin/categories_list"
								class="btn btn-sm btn-primary shadow-sm"> <i
								class="bi bi-plus-circle-fill"></i> Add Category
							</a> <a href="/app/admin/suppliers_list"
								class="btn btn-sm btn-primary shadow-sm"> <i
								class="bi bi-plus-circle-fill"></i> Add Suppliers
							</a>
						</div>
					</div>
					<div class="px-4 py-3 mb-8 bg-white rounded-lg shadow-md">
						<!-- Product form -->
						<form:form action="/app/admin/product/create" method="post"
							modelAttribute="item" enctype="multipart/form-data">
							<!-- Input fields for product information -->
							<div class="form-row">
								<div class="form-group col-md-6">
									<form:input type="hidden" path="productId"
										placeholder="Product Id" />
									<label for="productName" class="text-gray-700">Product
										Name</label>
									<form:input path="productName" id="productName"
										class="form-control" placeholder="Product Name"
										required="required" />
								</div>
								<div class="form-group col-md-6">
									<label for="supplier.supplierId" class="text-gray-700">Supplier</label>
									<form:select path="supplier.supplierId" id="supplierId"
										class="form-control">
										<form:options items="${suppliers}" itemValue="supplierId"
											itemLabel="name" />
									</form:select>
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label for="price" class="text-gray-700">Price</label>
									<form:input path="price" id="price" class="form-control"
										placeholder="VNĐ" required="required" />
								</div>
								<div class="form-group col-md-6">
									<label for="salePrice" class="text-gray-700">Sale Price</label>
									<form:input path="salePrice" id="salePrice"
										class="form-control" placeholder="VNĐ" />
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label for="quantity" class="text-gray-700">Quantity</label>
<form:input path="quantity" id="quantity" class="form-control"
										placeholder="Quantity" required="required" />
								</div>
								<div class="form-group col-md-6">
									<label for="unit.unitId" class="text-gray-700">Unit</label>
									<form:select path="unit.unitId" id="unitId"
										class="form-control">
										<form:options items="${units}" itemValue="unitId"
											itemLabel="name" />
									</form:select>
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label for="category.categoryId" class="text-gray-700">Category</label>
									<form:select path="category.categoryId" id="categoryId"
										class="form-control">
										<form:options items="${categories}" itemValue="categoryId"
											itemLabel="categoryName" />
									</form:select>
								</div>

								<div class="form-group col-md-6">
									<label for="importDate" class="text-gray-700">Import
										Date</label>
									<form:input type="date"  path="importDate" id="importDate"
										class="form-control" value="${dateTime}" pattern="\d{4}-\d{2}-\d{2}" />
								</div>

							</div>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label for="description" class="text-gray-700">Description</label>
									<form:textarea path="description" id="description"
										class="form-control" rows="10"
										placeholder="Product Description"></form:textarea>
								</div>
								<div class="form-group col-md-6">
									<label for="expDate" class="text-gray-700">Expiry Date</label>
									<form:input type="date" path="expDate" id="expDate"
										class="form-control"  pattern="\d{4}-\d{2}-\d{2}" />

									<div class="form-group col-md-6">
										<label for="imageUrl" class="text-gray-700">Image</label> <input
											type="file" name="file" id="fileInput"
											class="form-control-file" onchange="previewImages(event)"
											accept="image/*" />
										<div id="previewContainer" class="row">
											<c:if test="${not empty item.imageUrl}">
											<img src="/template/user/picture/${item.imageUrl}"
												class="img-thumbnail mt-2" width="100" alt=" "
												id="currentImage" />
										</c:if>
										</div>
										<!-- Container to display image previews -->
									</div>

								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-6 mt-3">
									<label class="text-gray-700">Status</label><br>
									<div class="form-check form-check-inline">
										<form:radiobutton path="isActive" id="active" value="true"
											checked="checked" class="form-check-input" />
										<label class="form-check-label" for="active">Active</label>
									</div>
									<div class="form-check form-check-inline">
										<form:radiobutton path="isActive" id="inactive" value="false"
											class="form-check-input" />
										<label class="form-check-label" for="inactive">Inactive</label>
									</div>
								</div>
							</div>
							<%-- <div class="mt-4">
						        <button type="submit" class="btn btn-success">Add New</button>
<form:form action="/product/update" method="post" modelAttribute="item">
							        <button type="submit" class="btn btn-primary">Update</button>
						    	</form:form>
						    	<form:form action="/productForm" method="post" modelAttribute="item">
							        <button type="submit" class="btn btn-secondary">Reset</button>
						    	</form:form>
						    </div> --%>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button type="submit" class="btn btn-primary"
										formaction="/app/admin/product/create">Create</button>
									<button type="submit" class="btn btn-info"
										formaction="/app/admin/product/update">Update</button>
									<a href="/app/admin/product/delete/${item.productId}"
										class="btn btn-danger text-end">Delete</a> <a
										href="/app/admin/productForm" class="btn btn-default">Reset</a>
								</div>
							</div>

						</form:form>
					</div>

					<div class="card shadow mb-4 mt-5">
						<div class="card">
							<div class="card-header py-3">
								<div class="d-flex align-items-center justify-content-between">
									<h6 class="m-0 font-weight-bold text-primary text-uppercase">Product
										List</h6>
									<form action="/app/admin/productForm" method="get" class="form-inline">
										<div class="dropdown mx-2">
										
										   
											<button  class="btn btn-secondary "><a class="text-light" href="/app/admin/productForm?field=productId">Newest</a></button>
											<button  class="btn btn-danger "><a class="text-light" href="/app/admin/productForm?field=productId&direction=asc">Oldest</a></button>
											<button  class="btn btn-warning "><a class="text-light" href="/app/admin/productForm?field=price&direction=asc">Lowest Price</a></button>
											<button  class="btn btn-info "><a class="text-light" href="/app/admin/productForm?field=price&direction=desc">Highest Price</a></button>
											<button  class="btn btn-success "><a class="text-light" href="${pageContext.request.contextPath}/app/admin/products/export">Export Produtcs to Excel</a></button>
										</div>
										
										<div class="input-group">
											<input type="search" class="form-control bg-light border-0 small"
												placeholder="Search for..." aria-label="Search"
												aria-describedby="basic-addon2" name="keywords" value="${keyword}">
											<div class="input-group-append">
												<button class="btn btn-primary" type="submit">
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
								<table class="table table-hover table-bordered">
									<thead class="thead-light">
										<tr>
											<th><a href="/app/admin/productForm?field=productId">Id</a></th>
											<th scope="col">Name</th>
											<th scope="col">Supplier</th>
											<th scope="col">Price</th>
											<th scope="col">Sale Price</th>
<th scope="col">Quantity</th>
											<th scope="col">Unit</th>
											<th scope="col">Category</th>
											<th scope="col">Import Date</th>
											<th scope="col">Image</th>
											<th scope="col">Actions</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="product" items="${items_products.content}">
											<tr>
												<th scope="row">${product.productId}</th>
												<td>${product.productName}</td>
												<td>${product.supplier.name}</td>
												<td>${product.price} $</td>
												<td>${product.salePrice} $</td>
												<td>${product.quantity}</td>
												<td>${product.unit.name}</td>
												<td>${product.category.categoryName}</td>
												<td> 
													<fmt:formatDate value="${product.importDate}" pattern="dd-MM-yyyy || HH:mm:ss"/>
											   </td>
												<td>
													<c:if test="${not empty product.imageUrl}">
														<img src="/template/user/picture/${product.imageUrl}"
															class="img-thumbnail" width="100"
															alt="${product.productId}" />
													</c:if>
												</td>
												<td><a
													href="${pageContext.request.contextPath}/app/admin/product/edit/${product.productId}"
													class="btn btn-sm btn-primary">Edit</a> <a
													href="${pageContext.request.contextPath}/app/admin/product/delete/${product.productId}"
													class="btn btn-sm btn-danger"
													onclick="return confirm('Are you sure you want to delete this product?');">Delete</a>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<div class="col-12">
								    <div class="pagination d-flex justify-content-center mt-5">
								        <ul class="pagination">
								            <c:if test="${items_products.hasPrevious()}">
								                <li class="page-item">
								                    <a href="?page=0" class="page-link">First</a>
								                </li>
								                <li class="page-item">
								                    <a href="?page=${items_products.number - 1}" class="page-link">&laquo;</a>
								                </li>
								            </c:if>
								            <c:forEach var="i" begin="1" end="${items_products.totalPages}">
								                <li class="page-item ${i == items_products.number + 1 ? 'active' : ''}">
								                    <a class="page-link" href="?page=${i - 1}">${i}</a>
								                </li>
								            </c:forEach>
								            <c:if test="${items_products.hasNext()}">
								                <li class="page-item">
								                    <a href="?page=${items_products.number + 1}" class="page-link">&raquo;</a>
								                </li>
								                <li class="page-item">
								                    <a href="?page=${items_products.totalPages - 1}" class="page-link">Last</a>
								                </li>
								            </c:if>
								        </ul>
								    </div>
								</div>
							</div>
						</div>
					</div>
<!-- <h1 class="h3 mb-0 text-gray-800 font-weight-bold text-uppercase mt-5">Product List</h1> -->
					<%-- <div class="px-4 py-3 mb-8 bg-white rounded-lg shadow-md mt-3">
                    	<form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search text-end">

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
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Name</th>
                                            <th>Description</th>
                                            <th>Price</th>
                                            <th>Sale Price</th>
                                            <th>Unit</th>
                                            <th>Image</th>
                                            <th>Import Date</th>
                                            <th>Quantity</th>
                                            <th>Exp Date</th>
                                            <th>Category</th>
                                            <th>Supplier</th>
                                            <th>Active</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="product" items="${products}">
                                            <tr>
                                                <td>${product.productId}</td>
                                                <td>${product.productName}</td>
                                                <td>${product.description}</td>
                                                <td>${product.price}</td>
                                                <td>${product.salePrice}</td>
                                                <td>${product.unit.name}</td>
                                                <td><img src="/template/user/picture/${product.imageUrl}" alt="Product Image" style="width: 50px; height: 50px;"></td>
<td>${product.importDate}</td>
                                                <td>${product.quantity}</td>
                                                <td>${product.expDate}</td>
                                                <td>${product.category.categoryName}</td>
                                                <td>${product.supplier.name}</td>
                                                <td>${product.isActive ? "Yes" : "No"}</td>
                                                <td>
                                                    <a href="/product/edit/${product.productId}" class="btn btn-primary btn-md mb-2">Edit</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div> --%>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
    function previewImages(event) {
        const input = event.target;
        const previewContainer = document.getElementById('previewContainer');
        previewContainer.innerHTML = ''; // Clear previous previews

        if (input.files) {
            const filesArray = Array.from(input.files);
            filesArray.forEach(file => {
                const reader = new FileReader();
                reader.onload = function(e) {
                    const col = document.createElement('div');
                    col.className = 'col-md-4'; // Adjust to display more images
                    const img = document.createElement('img');
                    img.src = e.target.result;
                    img.className = 'img-fluid img-thumbnail mb-2';
                    img.style.maxHeight = '200px';
                    col.appendChild(img);
                    previewContainer.appendChild(col);
                }
                reader.readAsDataURL(file);
            });
        }
    }
</script>
</html>