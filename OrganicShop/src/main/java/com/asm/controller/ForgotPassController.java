package com.asm.controller;

import com.asm.entity.Customers;
import com.asm.entity.Users;
import com.asm.entity.dao.CustomersDAO;
import com.asm.entity.dao.UsersDAO;
import com.asm.services.MailService;

import jakarta.mail.MessagingException;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app/user")
public class ForgotPassController {
    
    @Autowired
    private MailService mailService;
    
    @Autowired
    private CustomersDAO customersDAO;
    
    @Autowired
    private UsersDAO usersDAO;

    @GetMapping("/forgotPass")
    public String showForgotPasswordForm() {
        return "user/forgotPass";
    }

    @PostMapping("/forgotPass")
    public String processForgotPassword(String email, Model model) {
        Customers customers = customersDAO.findByEmail(email);
        System.err.println("customers "+customers);
        if (customers == null) {
            model.addAttribute("error", "No account found with that email.");
            return "user/forgotPass";
        }

        // Generate a new random password
        String newPassword = generateRandomPassword();

        // Update customer's password (assuming it's in Customers entity)
        // customers.setPassword(newPassword); // This line is not present in your code

        // Send the new password to the user's email
        String body = String.format("""
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
		                    <h2>PASS WAS CHANGED ORGANIC STORE</h2>
		                </div>
		                <div class="email-content">
		                    <p>Your new password, <strong>%s</strong></p>
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
""", newPassword);
        
//        mailService.sendSimpleMessage(email, "New Password", "Your new password is: " + newPassword);
//        mailService.send(email, "New Password", body);
        try {
			mailService.send(email, "New Pass", body);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // Update password in Users table
        Optional<Users> userOpt = usersDAO.findById(customers.getUser().getUserId());
        Users user = userOpt.get();

        System.err.println("user "+userOpt);
        System.err.println("user "+user);
        if (userOpt != null) {
            user.setPasswordUser(newPassword);
            usersDAO.save(user);
        }

        model.addAttribute("message", "Your new password has been sent to your email.");
        return "user/forgotPass";
    }

    // Method to generate a random password
    private String generateRandomPassword() {
        String uuid = UUID.randomUUID().toString();
        // Take first 8 characters as the random password
        return uuid.substring(0, 8);
    }
}
