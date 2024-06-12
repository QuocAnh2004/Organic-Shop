package com.asm.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asm.entity.Customers;
import com.asm.entity.UserRoles;
import com.asm.entity.Users;
import com.asm.entity.dao.CustomersDAO;
import com.asm.entity.dao.UsersDAO;
import com.asm.services.MailService;
import com.asm.utils.UtilsXoaDau;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

@Controller
@RequestMapping("app/user")
public class AccountController {
	@Autowired
	CustomersDAO customersDAO;
	@Autowired
	UsersDAO usersDAO;
	@Autowired
	private MailService mailer;
	// @Autowired
	// private BCryptPasswordEncoder passwordEncoder;

	@GetMapping("/account/register")
	public String form(@ModelAttribute("customer") Customers cus) {
		Customers kh = new Customers();
		// model.addAttribute("message", "Chúc mừng, bạn đã đăng ký thành công");

		return "user/register";
	}

	@RequestMapping("/account/save")
	public String save(@Valid @ModelAttribute("customer") Customers cus, BindingResult errors,
			RedirectAttributes redirectAttributes) {
		if (errors.hasErrors()) {
			redirectAttributes.addFlashAttribute("message", "Vui lòng sửa các lỗi sau:");
			return "user/register";
		} else {

			// Tạo mới một User
			Users user = new Users();
			// ID
			String userID = "user" + (usersDAO.count() + 1); // Tạo username theo yêu cầu
			user.setUserId(userID);
			// User name
			String rawUsername = cus.getLastName() + cus.getFirstName();
			String userName = UtilsXoaDau.XoaDauVaKhoangTrang(rawUsername.toLowerCase());

			user.setUsername(userName);
			// pass

			String rawPassword = "pass_" + cus.getFirstName(); // Tạo mật khẩu theo yêu cầu
			String passwordUser = UtilsXoaDau.XoaDauVaKhoangTrang(rawPassword.toLowerCase());

			user.setPasswordUser(passwordUser);

			// Tạo một role mặc định
			UserRoles role = new UserRoles();
			role.setRoleId(1);
			user.setUserRole(role);
			// Lưu User vào cơ sở dữ liệu
			usersDAO.save(user);

			// Thiết lập userId cho Customer và lưu vào cơ sở dữ liệu

			cus.setUser(user);
			// Set ngày đăng ký
			cus.setDateSignUp(new Date());
			cus.setIsActive(true);
			customersDAO.save(cus);

			redirectAttributes.addFlashAttribute("message", "Chúc mừng, bạn đã đăng ký thành công");
			// String body = "cảm ơn vì bạn đã đăng ký xin chào "+cus.getLastName()+"
			// "+cus.getFirstName();
			String body = String.format(
					"""
											        <!DOCTYPE html>
											        <html lang="en">
											        <head>
							<meta charset="UTF-8">
											            <meta name="viewport" content="width=device-width, initial-scale=1.0">
											            <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
											            <title>Email Template</title>
											            <style>
											            body{
											                    background-color: #fefefe;
											            }

											                .email-container {
											                    max-width: 600px;
											                    margin: auto;
											                    padding: 20px;
											                    border: 1px solid #ddd;
											                    border-radius: 5px;
											                    font-family: Arial, sans-serif;
											                    background-color: #fff;
											                }
											                .email-header {
											                    text-align: center;
											                    padding-bottom: 5px;
											                }
											                .email-header img {
											                    max-width: 300px;
											                }
											                .email-content {
											                    text-align: left;
											                }
											                .email-content h2 {
											                    color: #333;
											                }
											                .email-footer {
											                    text-align: center;
											                    padding-top: 20px;
											                    font-size: 12px;
											                    color: #777;
											                }
											            </style>
											        </head>
											        <body>
											            <div class="email-container">
											                <div class="email-header">
											                    <img src="https://i.pinimg.com/236x/43/f1/09/43f1099f0b25fec574eb9948e8d8a8b1.jpg" alt="Logo">
											                    <h2>WELCOME TO ORGANIC STORE</h2>
											                </div>
											                <div class="email-content">
											                    <p>Thank you for registering, <strong>%s %s</strong>!</p>
											                    <p>We are excited to have you on board. Here’s what you can expect from our service:</p>
											                    <ul>
											                        <li>Exclusive offers and discounts.</li>
											                        <li>Regular updates on our latest products.</li>
											                        <li>Personalized recommendations.</li>
											                    </ul>
											                    <p>If you have any questions, feel free to contact our support team.</p>
											                    <p>Best regards,<br>The Organic Store Team</p>
											                </div>
											                <div class="email-footer">
											                    <p>123 Organic Street, Healthy City, Freshland 45678</p>
											                    <p>&copy; 2024 Organic Store. All rights reserved.</p>
											                </div>
											            </div>
											        </body>
											        </html>
											        """,
					cus.getLastName(), cus.getFirstName());
			String email = cus.getEmail();
			try {
				mailer.send(email, "WELCOME TO OGANIC STRORE", body);
				return "redirect:/app/user/account/register";
			} catch (MessagingException e) {
				redirectAttributes.addFlashAttribute("error", "Lỗi khi gửi email: " + e.getMessage());
				return "redirect:/account/register";
			}

		}
	}

}