package com.asm.controller.admin;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.asm.entity.Orders;
import com.asm.entity.Products;
import com.asm.entity.dao.OrdersDAO;
import com.asm.entity.dao.ProductsDAO;
import com.asm.services.OrderService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("app/admin")
public class AdminController {

	@Autowired
	OrdersDAO ordersDAO;

	@Autowired
	ProductsDAO productDAO;
	@Autowired
	OrderService orderService;

	@RequestMapping("/home")
	public String home(Model model) {
		BigDecimal totalTodayAmount = ordersDAO.sumTotalAmountByDate();
		model.addAttribute("totalTodayAmount", totalTodayAmount != null ? totalTodayAmount : BigDecimal.ZERO);

		long pendingOrdersCount = ordersDAO.countByOrderStatus("Pending");
		model.addAttribute("pendingOrdersCount", pendingOrdersCount);

		List<Orders> orders = ordersDAO.findAll();
		model.addAttribute("orders", orders);

		List<Products> products = productDAO.findAll();
		model.addAttribute("products", products);

		BigDecimal sumTotalRevenue = ordersDAO.sumTotalRevenue();
		model.addAttribute("sumTotalRevenue", sumTotalRevenue);

		Double sumDailyAmount = orderService.getTotalRevenueForToday(); // Lấy tổng doanh thu của ngày hiện tại
		System.out.println(sumDailyAmount);
		if (sumDailyAmount == null) {
			sumDailyAmount = 0.0;
			return "admin/home";
		} else {
			model.addAttribute("sumDailyAmount", sumDailyAmount);
		}

		List<Map<String, Object>> top10Products = orderService.getTop10MostPurchasedProducts();
		model.addAttribute("top10Products", top10Products);
		return "admin/home";

	}

	@GetMapping("/top-products/export")
	public void exportTopProductsToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment;filename=Top_Products.xlsx");

		List<Map<String, Object>> top10Products = orderService.getTop10MostPurchasedProducts();

		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Top Products");
		// set màu vàng
		CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        
		Row headerRow = sheet.createRow(0);
		String[] columns = { "Id", "Name", "Description", "Price", "Sale Price", "Unit", "Quantity", "Category Id",
				"Supplier Id", "Total Sold", "Total Revenue" };

		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);

		}

		int rowIdx = 1;
		for (Map<String, Object> product : top10Products) {
			Row row = sheet.createRow(rowIdx++);

			row.createCell(0).setCellValue((Integer) product.get("product_id"));
			row.createCell(1).setCellValue((String) product.get("product_name"));
			row.createCell(2).setCellValue((String) product.get("description"));

			// Convert Float to Double
			Object priceProduct = product.get("price_product");
			if (priceProduct instanceof Float) {
				row.createCell(3).setCellValue(((Float) priceProduct).doubleValue());
			} else {
				row.createCell(3).setCellValue((Double) priceProduct);
			}

			Object salePrice = product.get("sale_price");
			if (salePrice instanceof Float) {
				row.createCell(4).setCellValue(((Float) salePrice).doubleValue());
			} else {
				row.createCell(4).setCellValue((Double) salePrice);
			}

			row.createCell(5).setCellValue((Integer) product.get("unit"));

			row.createCell(6).setCellValue((Integer) product.get("quantity"));

			row.createCell(7).setCellValue((Integer) product.get("category_id"));
			row.createCell(8).setCellValue((Integer) product.get("supplier_id"));

			// Convert Integer to Long
			Object totalQuantitySold = product.get("total_quantity_sold");
			if (totalQuantitySold instanceof Integer) {
				row.createCell(9).setCellValue(((Integer) totalQuantitySold).longValue());
			} else {
				row.createCell(9).setCellValue((Long) totalQuantitySold);
			}

			// Convert Float to Double
			Object totalRevenue = product.get("total_revenue");
			if (totalRevenue instanceof Float) {
				row.createCell(10).setCellValue(((Float) totalRevenue).doubleValue());
			} else {
				row.createCell(10).setCellValue((Double) totalRevenue);
			}
		}

		workbook.write(response.getOutputStream());
		workbook.close();
	}

}