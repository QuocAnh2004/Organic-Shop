

<style>
	.fixed-sidebar {
    position: fixed;
    top: 0;
    left: 0;
    height: 100%;
    overflow-y: auto; /* Để đảm bảo nội dung sidebar có thể cuộn nếu vượt quá chiều cao màn hình */
}
</style>
 <ul class="navbar-nav bg-gradient-success sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center">
                <div class="sidebar-brand-icon rotate-n-15">
                    <i class="fas fa-laugh-wink"></i>
                </div>
                <div class="sidebar-brand-text mx-3">OrganicShop Admin</div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->
            <li class="nav-item active">
                <a class="nav-link" href="/app/admin/home">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>Overview</span>
                </a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                Shop
            </div>
            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item active">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
                    aria-expanded="true" aria-controls="collapseTwo">
                    <i class="fas fa-fw fa-cog"></i>
                    <span>Manage</span>
                </a>
                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
				    <div class="bg-light py-2 collapse-inner rounded">
				        <!-- Products Menu -->
				        <a class="collapse-item" href="#" data-toggle="collapse" data-target="#productsSubmenu" aria-expanded="true" aria-controls="productsSubmenu">Products</a>
				        
				        <div id="productsSubmenu" class="collapse">
				            <a class="collapse-item" href="/app/admin/productForm">Add Product</a>
				        </div>
				
				        <!-- Employee Menu -->
				        <a class="collapse-item" href="#" data-toggle="collapse" data-target="#employeeSubmenu" aria-expanded="true" aria-controls="employeeSubmenu">Employee</a>
				        <div id="employeeSubmenu" class="collapse">
				            <a class="collapse-item" href="/app/admin/employeeForm">Add Employee</a>
<!-- 				            <a class="collapse-item" href="/app/admin/manage_employee/list">List Employees</a>
 -->				        </div>
				
				        <!-- Customers Menu -->
				        <!-- <a class="collapse-item" href="#" data-toggle="collapse" data-target="#customersSubmenu" aria-expanded="true" aria-controls="customersSubmenu">Customers</a>
				        <div id="customersSubmenu" class="collapse">
				            <a class="collapse-item" href="/app/admin/manage_customer/add">Add Customer</a>
				            <a class="collapse-item" href="/app/admin/manage_customer/list">List Customers</a>
				        </div> -->
				        
				        <!-- <a class="collapse-item" href="#" data-toggle="collapse" data-target="#suppliersSubmenu" aria-expanded="true" aria-controls="suppliersSubmenu">Suppliers</a>
				        
				        <div id="suppliersSubmenu" class="collapse">
				            <a class="collapse-item" href="/app/admin/suppliersForm">Add Suppliers</a>
				            <a class="collapse-item" href="/app/admin/list_suppliers">List Suppliers</a>
				        </div> -->
				        
				    </div>
				</div>
            </li>
			
			<div class="sidebar-heading">
                Orders
            </div>
            
            <li class="nav-item active">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapse3"
                    aria-expanded="true" aria-controls="collapse3">
                    <i class="fas fa-fw fa-cog"></i>
                    <span>Orders</span>
                </a>
                <div id="collapse3" class="collapse" aria-labelledby="heading3" data-parent="#accordionSidebar">
				    <div class="bg-light py-2 collapse-inner rounded">
				        <!-- Products Menu -->
				        <a class="collapse-item" href="#" data-toggle="collapse" data-target="#orderSubmenu" aria-expanded="true" aria-controls="orderSubmenu">List of Orders</a>
				        
				        <div id="orderSubmenu" class="collapse">
				            <a class="collapse-item" href="/app/admin/order_list">List</a>
				        </div>
				
				        <!-- Employee Menu -->
				        <a class="collapse-item" href="#" data-toggle="collapse" data-target="#orderstatusSubmenu" aria-expanded="true" aria-controls="orderstatusSubmenu">Orders Status</a>
				        <div id="orderstatusSubmenu" class="collapse">
				            <a class="collapse-item" href="/app/admin/order_processing">Pending</a>
				            <a class="collapse-item" href="/app/admin/order_delivering">Delivering</a>
				            <a class="collapse-item" href="/app/admin/order_cancelled">Cancelled</a>
				            <a class="collapse-item" href="/app/admin/order_success">Success</a>
				            <a class="collapse-item" href="/app/admin/order_returned">Returned</a>
				        </div>
				    </div>
				</div>
            </li>
            <!-- Divider -->
            <hr class="sidebar-divider d-none d-md-block">

            <!-- Sidebar Toggler (Sidebar) -->
            <div class=" my-3 mx-3" >
                <a class="ms-3 text-light "style="text-decoration: none;"  href="/app/user/home/index">
                    <i class="bi bi-house-fill"></i>
                    <span>Back to shop</span>
                </a>
            </div>

        </ul>