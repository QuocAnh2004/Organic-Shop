
package com.asm.controller.admin;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.asm.entity.Categories;
import com.asm.entity.Employees;
import com.asm.entity.Products;
import com.asm.entity.Suppliers;
import com.asm.entity.Units;
import com.asm.entity.UserRoles;
import com.asm.entity.Users;
import com.asm.entity.dao.EmployeesDAO;
import com.asm.entity.dao.UsersDAO;
import com.asm.services.EmployeeService;
import com.asm.services.UserRoleService;
import com.asm.services.UserService;
import com.asm.utils.UtilsXoaDau;

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

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
@RequestMapping("app/admin")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	UserRoleService userRoleService;

	@Autowired
	UsersDAO usersDAO;
	@Autowired
	UserService userService;

	@Autowired
	EmployeesDAO employeesDAO;

	@Value("${file.uploadEmployee.dir}") // Sử dụng @Value để inject giá trị từ application.properties
	private String uploadDir;

	@GetMapping("/employeeForm")
	public String showEmployee(Model model) {
		List<Employees> employees = employeeService.getAllEmployee();
		List<UserRoles> userRoles = userRoleService.getAllRole();
		model.addAttribute("userRoles", userRoles);
		model.addAttribute("employees", employees); // chứa dữ liệu của tất cả nhân viên hiện tại
		model.addAttribute("employees_form", new Employees()); // Thêm thuộc tính item vào model để liên kết với form
		return "admin/manage_employee";
	}

	@PostMapping("/employee/create")
	public String createEmployee(@Valid @ModelAttribute("employees_form") Employees employee, BindingResult result,
			Model model, @RequestParam("file") MultipartFile file) {
		if (result.hasErrors()) {
			List<Employees> employees = employeeService.getAllEmployee();
			model.addAttribute("employees", employees);
			List<UserRoles> userRoles = userRoleService.getAllRole();
			model.addAttribute("userRoles", userRoles);
			return "admin/manage_employee";
		} else {
			  if (file.isEmpty()) {
		            // Xử lý khi không có tệp được chọn
					return "redirect:/app/admin/employeeForm";
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
				Users user = new Users();
				// ID
				String userID = "user" + (usersDAO.count() + 1); // Tạo username theo yêu cầu
				user.setUserId(userID);

				// User name
				String rawUsername = employee.getLastName() + employee.getFirstName();
				String userName = UtilsXoaDau.XoaDauVaKhoangTrang(rawUsername.toLowerCase());
				user.setUsername(userName);
				// pass
				String rawPassword = "pass_" + employee.getFirstName(); // Tạo mật khẩu theo yêu cầu
				String passwordUser = UtilsXoaDau.XoaDauVaKhoangTrang(rawPassword.toLowerCase());
				user.setPasswordUser(passwordUser);

				// role
				int userRole = employee.getUser().getUserRole().getRoleId();
				UserRoles role = new UserRoles();
				role.setRoleId(userRole);
				user.setUserRole(role);

				usersDAO.save(user);

				employee.setUser(user); // Set the saved user to the employee
				employee.setPicture(fileName);
				employeeService.save(employee);

			} catch (IOException e) {
				e.printStackTrace(); // Xử lý lỗi nếu có
				// có thể trả về một trang lỗi hoặc thông báo lỗi khác
			}
		}
		return "redirect:/app/admin/employeeForm";
	}

	@RequestMapping("/employee/edit/{id}")
	public String edit(Model model, @PathVariable("id") int id) {
		Employees employees_form = employeesDAO.findById(id).get();
		model.addAttribute("employees_form", employees_form);
		List<Employees> employees = employeeService.getAllEmployee();
		List<UserRoles> userRoles = userRoleService.getAllRole();
		model.addAttribute("userRoles", userRoles);
		model.addAttribute("employees", employees);
		System.err.println("employees "+employees);
		
		return "admin/manage_employee";

	}



//	@PostMapping("/employee/update")
//	public String updateEmployee(@ModelAttribute("employees_form") Employees employee,
//	                             Model model, @RequestParam("file") MultipartFile file) {
//	    Employees existingEmployee = employeesDAO.findById(employee.getEmployeeId()).orElse(null);
//	    System.err.println("existingEmployee " + existingEmployee);
//	    
//	    if (existingEmployee == null) {
//	        // Nếu không tìm thấy employee hiện tại, trả về trang quản lý nhân viên với thông báo lỗi
//	        model.addAttribute("errorMessage", "Employee not found");
//	        List<Employees> employees = employeeService.getAllEmployee();
//	        model.addAttribute("employees", employees);
//	        List<UserRoles> userRoles = userRoleService.getAllRole();
//	        model.addAttribute("userRoles", userRoles);
//	        return "admin/manage_employee";
//	    }
//
//	    try {
//	        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//	        
//	        if (!file.isEmpty()) {
//	            Path uploadPath = Paths.get(uploadDir);
//	            if (!Files.exists(uploadPath)) {
//	                Files.createDirectories(uploadPath);
//	            }
//	            try (InputStream inputStream = file.getInputStream()) {
//	                Path filePath = uploadPath.resolve(fileName);
//	                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
//	                employee.setPicture(fileName);
//	                System.err.println("fileName " + fileName);
//	            }
//	        } else {
//	            // Nếu không có tệp mới, giữ nguyên hình ảnh cũ
//	            employee.setPicture(existingEmployee.getPicture());
//	        }
//	        
//	        // Cập nhật các trường khác
//	        existingEmployee.setFirstName(employee.getFirstName());
//	        existingEmployee.setLastName(employee.getLastName());
//	        existingEmployee.setEmail(employee.getEmail());
//	        existingEmployee.setPhoneNumber(employee.getPhoneNumber());
//	        existingEmployee.setAddress(employee.getAddress());
//	        existingEmployee.setPicture(employee.getPicture());
//	        existingEmployee.setDateOfBirth(employee.getDateOfBirth());
//	        existingEmployee.setGender(employee.isGender());
//
//	        // Cập nhật vai trò của bảng nhân viên
//	        Users user = employee.getUser();
//	        System.err.println("user " + user);
//	        if (user != null && user.getUserId() != null) {
//	            Users existingUser = usersDAO.findById(user.getUserId()).orElse(null);
//	            System.err.println("existingUser " + existingUser);
//	            if (existingUser != null) {
//	                UserRoles newRole = userRoleService.getRoleById(user.getUserRole().getRoleId());
//	                System.err.println("newRole " + newRole);
//	                if (newRole != null) {
//	                    existingUser.setUserRole(newRole);
//	                }
//	                usersDAO.save(existingUser);
//	            }
//	            existingEmployee.setUser(existingUser);
//	        }
//
//	        // Save updated employee
//	        employeesDAO.save(existingEmployee);
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	    }
//
//	    return "redirect:/admin/employee/edit/"+employee.getEmployeeId();
//	}
	@PostMapping("/employee/update")
	public String updateEmployee(@ModelAttribute("employees_form") Employees employee,
	                             Model model, @RequestParam("file") MultipartFile file) {
	    Employees existingEmployee = employeesDAO.findById(employee.getEmployeeId()).orElse(null);
	    if (existingEmployee == null) {
	        model.addAttribute("errorMessage", "Employee not found");
	        List<Employees> employees = employeeService.getAllEmployee();
	        model.addAttribute("employees", employees);
	        List<UserRoles> userRoles = userRoleService.getAllRole();
	        model.addAttribute("userRoles", userRoles);
	        return "admin/manage_employee";
	    }

	    try {
	        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

	        if (!file.isEmpty()) {
	            Path uploadPath = Paths.get(uploadDir);
	            if (!Files.exists(uploadPath)) {
	                Files.createDirectories(uploadPath);
	            }
	            try (InputStream inputStream = file.getInputStream()) {
	                Path filePath = uploadPath.resolve(fileName);
	                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
	                employee.setPicture(fileName);
	            }
	        } else {
	            employee.setPicture(existingEmployee.getPicture());
	        }

	        // Cập nhật các trường khác
	        existingEmployee.setFirstName(employee.getFirstName());
	        existingEmployee.setLastName(employee.getLastName());
	        existingEmployee.setEmail(employee.getEmail());
	        existingEmployee.setPhoneNumber(employee.getPhoneNumber());
	        existingEmployee.setAddress(employee.getAddress());
	        existingEmployee.setPicture(employee.getPicture());
	        existingEmployee.setDateOfBirth(employee.getDateOfBirth());
	        existingEmployee.setGender(employee.isGender());
	        existingEmployee.setisActive(employee.getisActive());

	        Users user = existingEmployee.getUser();
	        if (user != null && user.getUserId() != null) {
	            UserRoles newRole = userRoleService.getRoleById(employee.getUser().getUserRole().getRoleId());
	            if (newRole != null) {
	                user.setUserRole(newRole);
	            }
	            usersDAO.save(user);
	        }

	        employeesDAO.save(existingEmployee);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return "redirect:/app/admin/employee/edit/" + employee.getEmployeeId();
	}

	
	  @GetMapping("/employee/delete/{id}")
	    public String deleteEmployee(@PathVariable("id") int id, Model model) {
	        Employees employee = employeeService.getEmployeeById(id);
	        if (employee != null) {
	            Users user = employee.getUser();
	            if (user != null) {
	                // Xóa tệp hình ảnh nếu có
	                String picture = employee.getPicture();
	                if (picture != null && !picture.isEmpty()) {
	                    try {
	                        employeeService.deleteEmployeeFile(uploadDir,picture);
	                        System.err.println("xóa ảnh thành công");
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                        model.addAttribute("errorMessage", "Error deleting employee picture");
	                        return "admin/manage_employee";
	                    }
	                }

	                // Xóa nhân viên và người dùng liên kết
	                employeeService.deleteEmployeeById(id);
	                userService.deleteUserById(user.getUserId());
	                System.err.println("Delete employee thành công");
	            }
	        } else {
	            model.addAttribute("errorMessage", "Employee not found");
	        }
	        return "redirect:/app/admin/employeeForm";
	    }

	  @GetMapping("/employee/export")
	    public void exportEmployeesToExcel(HttpServletResponse response) throws IOException {
	        response.setContentType("application/vnd.ms-excel");
	        response.setHeader("Content-Disposition", "attachment;filename=All_Employees.xlsx");

	        List<Employees> employeesList = employeeService.getAllEmployee();

	        Workbook workbook = new XSSFWorkbook();
	        Sheet sheet = workbook.createSheet("Employees");

	        Row headerRow = sheet.createRow(0);
	        String[] columns = {"ID", "First Name", "Last Name", "Email", "Phone Number", "Address", "Picture", "Date of Birth", "Gender", "Active", "Username", "Role"};
	        // set màu vàng
	        CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
	        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        for (int i = 0; i < columns.length; i++) {
	            Cell cell = headerRow.createCell(i);
	            cell.setCellValue(columns[i]);
	            cell.setCellStyle(headerCellStyle);

	        }

	        int rowIdx = 1;
	        for (Employees employee : employeesList) {
	            Row row = sheet.createRow(rowIdx++);
	            row.createCell(0).setCellValue(employee.getEmployeeId());
	            row.createCell(1).setCellValue(employee.getFirstName());
	            row.createCell(2).setCellValue(employee.getLastName());
	            row.createCell(3).setCellValue(employee.getEmail());
	            row.createCell(4).setCellValue(employee.getPhoneNumber());
	            row.createCell(5).setCellValue(employee.getAddress());
	            row.createCell(6).setCellValue(employee.getPicture());
	            row.createCell(7).setCellValue(employee.getDateOfBirth().toString());
	            row.createCell(8).setCellValue(employee.getisGender() ? "Male" : "Female");
	            row.createCell(9).setCellValue(employee.getisActive() ? "Active" : "Inactive");
	            row.createCell(10).setCellValue(employee.getUser().getUsername());
	            row.createCell(11).setCellValue(employee.getUser().getUserRole().getRoleName());
	        }

	        workbook.write(response.getOutputStream());
	        workbook.close();
	    }
}
