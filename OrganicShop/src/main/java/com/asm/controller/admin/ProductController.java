//package com.asm.controller.admin;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.FileInputStream;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
//import java.time.LocalDate;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.asm.entity.Categories;
//import com.asm.entity.Products;
//import com.asm.entity.Suppliers;
//import com.asm.entity.Units;
//import com.asm.entity.dao.ProductsDAO;
//import com.asm.services.CategoryService;
//import com.asm.services.ProductService;
//import com.asm.services.SuppliersService;
//import com.asm.services.UnitService;
//
//@Controller
//public class ProductController {
//    @Autowired
//    private UnitService unitService;
//
//    @Autowired
//    private CategoryService categoryService;
//
//    @Autowired
//    private SuppliersService supplierService;
//    
//    @Autowired
//    private ProductService productService;
//    
//    @Autowired
//    ProductsDAO productDAO;
//    
//    @Value("${file.upload.dir}") // Sử dụng @Value để inject giá trị từ application.properties
//    private String uploadDir;
////    private static final String UPLOAD_DIR = "src/main/webapp/template/user/picture/";
//    
//    @RequestMapping("/productForm")
//    public String showProductForm(Model model) {
//        // Lấy danh sách các đơn vị, danh mục và nhà cung cấp từ service
//    	List<Products> product = productService.getAllProducts();
//        List<Units> units = unitService.getAllUnits();
//        List<Categories> categories = categoryService.getAllCategory();
//        List<Suppliers> suppliers = supplierService.getAllSuppliers();
//        Products item = new Products();
//        model.addAttribute("dateTime", LocalDate.now());
//        // Thêm danh sách các đơn vị, danh mục và nhà cung cấp vào model để hiển thị trong form JSP
//        model.addAttribute("item", item);
//        model.addAttribute("units", units);
//        model.addAttribute("categories", categories);
//        model.addAttribute("suppliers", suppliers);
//        model.addAttribute("products", product);
//        
//        return "admin/manage_product";
//    }
//    
//    @RequestMapping("/product/edit/{id}")
//    public String edit(Model model, @PathVariable("id") int id) {
//        Products item = productDAO.findById(id).get();
//        model.addAttribute("item", item);
//        List<Products> items = productDAO.findAll();
//        List<Units> units = unitService.getAllUnits();
//        List<Categories> categories = categoryService.getAllCategory();
//        List<Suppliers> suppliers = supplierService.getAllSuppliers();
//        model.addAttribute("products", items);
//        model.addAttribute("units", units);
//        model.addAttribute("categories", categories);
//        model.addAttribute("suppliers", suppliers);
//        return "/admin/manage_product";
//    }
//    
////    @RequestMapping("/product/create")
////    public String create(Products product) {
////        productDAO.save(product);
////        return "redirect:/productForm";
////    }
////    
////    @RequestMapping("/product/update")
////    public String update(Products item) {
////        productDAO.save(item);
////        return "redirect:/product/edit/" + item.getProductId();
////    }
//    @PostMapping("/product/create")
//    public String create(@ModelAttribute("item") Products product, @RequestParam("file") MultipartFile file) {
//        // Kiểm tra xem tệp đã được chọn chưa
//        if (file.isEmpty()) {
//            // Xử lý khi không có tệp được chọn
//            return "redirect:/productForm"; // hoặc trả về một thông báo lỗi
//        }
//
//        try {
//            // Lấy tên tệp
//            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//
//            // Copy tệp vào thư mục được chỉ định
//            Path uploadPath = Paths.get(uploadDir);
//            if (!Files.exists(uploadPath)) {
//                Files.createDirectories(uploadPath);
//            }
//            try (InputStream inputStream = file.getInputStream()) {
//                Path filePath = uploadPath.resolve(fileName);
//                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
//            }
//
//            // Lưu tên tệp vào đối tượng sản phẩm
//            product.setImageUrl(fileName);
//          product.setImportDate(new Date());
//
//            // Lưu đối tượng sản phẩm vào cơ sở dữ liệu
//            productDAO.save(product);
//        } catch (IOException e) {
//            e.printStackTrace(); // Xử lý lỗi nếu có
//            // có thể trả về một trang lỗi hoặc thông báo lỗi khác
//        }
//
//        return "redirect:/productForm"; // hoặc trả về một trang khác sau khi lưu sản phẩm thành công
//    }
//    @PostMapping("/product/update")
//    public String update(@ModelAttribute("item") Products product, @RequestParam("file") MultipartFile file) {
//        // Kiểm tra xem tệp đã được chọn chưa
//        if (file.isEmpty()) {
//            // Xử lý khi không có tệp được chọn
//            return "redirect:/productForm"; // hoặc trả về một thông báo lỗi
//        }
//
//        try {
//            // Lấy tên tệp
//            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//
//            // Copy tệp vào thư mục được chỉ định
//            Path uploadPath = Paths.get(uploadDir);
//            if (!Files.exists(uploadPath)) {
//                Files.createDirectories(uploadPath);
//            }
//            try (InputStream inputStream = file.getInputStream()) {
//                Path filePath = uploadPath.resolve(fileName);
//                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
//            }
//
//            // Lưu tên tệp vào đối tượng sản phẩm
//            product.setImageUrl(fileName);
//
//            // Lưu đối tượng sản phẩm vào cơ sở dữ liệu
//            productDAO.save(product);
//        } catch (IOException e) {
//            e.printStackTrace(); // Xử lý lỗi nếu có
//            // có thể trả về một trang lỗi hoặc thông báo lỗi khác
//        }
//
//        return "redirect:/product/edit/" + product.getProductId(); // hoặc trả về một trang khác sau khi lưu sản phẩm thành công
//    }
//    
//    @RequestMapping("/product/delete/{id}")
//    public String delete(@PathVariable("id") int id) {
//        productDAO.deleteById(id);
//        return "redirect:/productForm";
//    }
//    
//
//}
package com.asm.controller.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.asm.entity.Categories;
import com.asm.entity.Products;
import com.asm.entity.Suppliers;
import com.asm.entity.Units;
import com.asm.entity.dao.ProductsDAO;
import com.asm.services.CategoryService;
import com.asm.services.ProductService;
import com.asm.services.SessionService;
import com.asm.services.SuppliersService;
import com.asm.services.UnitService;

import jakarta.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Controller
@RequestMapping("app/admin")
public class ProductController {
    @Autowired
    private UnitService unitService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SuppliersService supplierService;
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    ProductsDAO productDAO;
    
    @Autowired
	SessionService sessionService;
    
    @Value("${file.upload.dir}") // Sử dụng @Value để inject giá trị từ application.properties
    private String uploadDir;
//    private static final String UPLOAD_DIR = "src/main/webapp/template/user/picture/";
    
    @RequestMapping("/productForm")
    public String showProductForm(Model model,
    				@RequestParam("keywords") Optional<String> kw, 
    				@RequestParam("page")Optional<Integer> page,
    				@RequestParam("field") Optional<String> field,
    				@RequestParam("direction") Optional<String> direction) {
    	// Sort
        String sortField = field.orElse("productId");
        String sortDirection = direction.orElse("desc"); // Mặc định là sắp xếp giảm dần
        Sort sort = Sort.by(sortDirection.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortField);
        
        //find by name
    	String kwords = kw.orElse("");
//		sessionService.set("keywords", kwords); // lưu sesion để duy trì list chứa từ khóa đang tìm kiếm
//		sessionService.setWithExpiry("keywords", kwords, 10, TimeUnit.SECONDS);
		Pageable pageable = PageRequest.of(page.orElse(0), 10,sort); // truy xuất trang thứ 0 có 2 sản phẩm
		Page<Products> items_products = productDAO.findAllByProductNameLike("%"+kwords+"%", pageable);
		if(items_products.isEmpty())
		{
			model.addAttribute("message", kwords);
			return "admin/404";
		}else {
			 model.addAttribute("message", kwords);
			model.addAttribute("items_products", items_products);
		}
		
//    	List<Products> product = productService.getAllProducts();
        List<Units> units = unitService.getAllUnits();
        List<Categories> categories = categoryService.getAllCategory();
        List<Suppliers> suppliers = supplierService.getAllSuppliers();
        Products item = new Products();
        model.addAttribute("dateTime", LocalDate.now());
        // Thêm danh sách các đơn vị, danh mục và nhà cung cấp vào model để hiển thị trong form JSP
        model.addAttribute("item", item);
        model.addAttribute("units", units);
        model.addAttribute("categories", categories);
        model.addAttribute("suppliers", suppliers);
        model.addAttribute("sortField", sortField);
//        model.addAttribute("products", product);
        
        return "admin/manage_product";
    }
    
    @RequestMapping("/product/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id,
	    		@RequestParam("keywords") Optional<String> kw, 
				@RequestParam("page")Optional<Integer> page,
				@RequestParam("field") Optional<String> field,
				@RequestParam("direction") Optional<String> direction) {
        Products item = productDAO.findById(id).get();
        model.addAttribute("item", item);
        
        // Sort
        String sortField = field.orElse("productId");
        String sortDirection = direction.orElse("desc"); // Mặc định là sắp xếp giảm dần
        Sort sort = Sort.by(sortDirection.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortField);
        
        //find by name
    	String kwords = kw.orElse("");
//		sessionService.set("keywords", kwords); // lưu sesion để duy trì list chứa từ khóa đang tìm kiếm
//		sessionService.setWithExpiry("keywords", kwords, 10, TimeUnit.SECONDS);
		Pageable pageable = PageRequest.of(page.orElse(0), 10,sort); // truy xuất trang thứ 0 có 2 sản phẩm
		Page<Products> items_products = productDAO.findAllByProductNameLike("%"+kwords+"%", pageable);
		if(items_products.isEmpty())
		{
			model.addAttribute("message", kwords);
			return "admin/404";
		}else {
			 model.addAttribute("message", kwords);
			model.addAttribute("items_products", items_products);
		}
//        List<Products> items = productDAO.findAll();
        List<Units> units = unitService.getAllUnits();
        List<Categories> categories = categoryService.getAllCategory();
        List<Suppliers> suppliers = supplierService.getAllSuppliers();
        model.addAttribute("items_products", items_products);
        model.addAttribute("units", units);
        model.addAttribute("categories", categories);
        model.addAttribute("suppliers", suppliers);
        return "/admin/manage_product";
    }
    
//    @RequestMapping("/product/create")
//    public String create(Products product) {
//        productDAO.save(product);
//        return "redirect:/productForm";
//    }
//    
//    @RequestMapping("/product/update")
//    public String update(Products item) {
//        productDAO.save(item);
//        return "redirect:/product/edit/" + item.getProductId();
//    }
    @PostMapping("/product/create")
    public String create(@ModelAttribute("item") Products product, @RequestParam("file") MultipartFile file) {
        // Kiểm tra xem tệp đã được chọn chưa
        if (file.isEmpty()) {
            // Xử lý khi không có tệp được chọn
            return "redirect:/app/admin/productForm"; // hoặc trả về một thông báo lỗi
        }

        try {
            // Lấy tên tệp
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            // Copy tệp vào thư mục được chỉ định
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            }

            // Lưu tên tệp vào đối tượng sản phẩm
            product.setImageUrl(fileName);
          product.setImportDate(new Date());

            // Lưu đối tượng sản phẩm vào cơ sở dữ liệu
            productDAO.save(product);
        } catch (IOException e) {
            e.printStackTrace(); // Xử lý lỗi nếu có
            // có thể trả về một trang lỗi hoặc thông báo lỗi khác
        }

        return "redirect:/app/admin/productForm"; // hoặc trả về một trang khác sau khi lưu sản phẩm thành công
    }
    @PostMapping("/product/update")
    public String update(@ModelAttribute("item") Products product, @RequestParam("file") MultipartFile file) {
        // Kiểm tra xem tệp có trống không
        if (!file.isEmpty()) {
            try {
                // Lấy tên tệp
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());

                // Copy tệp vào thư mục được chỉ định
                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                try (InputStream inputStream = file.getInputStream()) {
                    Path filePath = uploadPath.resolve(fileName);
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                }

                // Lưu tên tệp vào đối tượng sản phẩm
                product.setImageUrl(fileName);

            } catch (IOException e) {
                e.printStackTrace(); // Xử lý lỗi nếu có
                // có thể trả về một trang lỗi hoặc thông báo lỗi khác
            }
        } else {
            // Không có tệp được chọn, giữ nguyên URL hình ảnh hiện tại
            Products existingProduct = productDAO.findById(product.getProductId()).orElse(null);
            if (existingProduct != null) {
                product.setImageUrl(existingProduct.getImageUrl());
            }
        }

        // Lưu đối tượng sản phẩm vào cơ sở dữ liệu
        productDAO.save(product);

        return "redirect:/app/admin/product/edit/" + product.getProductId(); // hoặc trả về một trang khác sau khi lưu sản phẩm thành công
    }
    
    @RequestMapping("/product/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        productDAO.deleteById(id);
        return "redirect:/app/admin/productForm";
    }
    
//    @RequestMapping("/search")
//	public String searchAndPage(Model model,@RequestParam("keywords") Optional<String> kw) {
//		String kwords = kw.orElse(sessionService.get("keywords", ""));
//		sessionService.set("keywords", kwords); // lưu sesion để duy trì list chứa từ khóa đang tìm kiếm
//		Page<Products> items_products = productDAO.findAllByProductNameLike("%"+kwords+"%"); // truy vấn tất cả
//		
//		if(items_products.isEmpty())
//		{
//			model.addAttribute("message", sessionService.get("keywords"));
//			return "user/404";
//		}else {
//			model.addAttribute("keyword", sessionService.get("keywords"));
//			model.addAttribute("items_products", items_products);
//		}
//		
//		return "user/shop";
//	}
    @GetMapping("/products/export")
    public void exportProductsToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=All_Products.xlsx");

        List<Products> productsList = productService.getAllProducts();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Products");

        Row headerRow = sheet.createRow(0);
        String[] columns = {"ID", "Name", "Description", "Price", "Sale Price", "Unit", "Image URL", "Import Date", "Quantity", "Exp Date", "Category", "Supplier", "Active"};

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        int rowIdx = 1;
        for (Products product : productsList) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(product.getProductId());
            row.createCell(1).setCellValue(product.getProductName());
            row.createCell(2).setCellValue(product.getDescription());
            row.createCell(3).setCellValue(product.getPrice());
            row.createCell(4).setCellValue(product.getSalePrice());
            row.createCell(5).setCellValue(product.getUnit().getName());
            row.createCell(6).setCellValue(product.getImageUrl());
            row.createCell(7).setCellValue(product.getImportDate() != null ? product.getImportDate().toString() : "");
            row.createCell(8).setCellValue(product.getQuantity());
            row.createCell(9).setCellValue(product.getExpDate() != null ? product.getExpDate().toString() : "");
            row.createCell(10).setCellValue(product.getCategory().getCategoryName());
            row.createCell(11).setCellValue(product.getSupplier().getName());
            row.createCell(12).setCellValue(product.getisActive() ? "Active" : "Inactive");
        }

        workbook.write(response.getOutputStream());
        workbook.close();
    }
}

