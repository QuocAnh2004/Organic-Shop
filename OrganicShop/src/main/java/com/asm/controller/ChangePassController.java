package com.asm.controller;

import com.asm.entity.Users;
import com.asm.entity.dao.UsersDAO;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/app")
public class ChangePassController {
    
    @Autowired
    private UsersDAO usersDAO;

    @GetMapping("/user/changePass")
    public String showChangePasswordForm(Model model, HttpSession session) {
    	Users loggedInUsername = (Users) session.getAttribute("loggedInUser"); // Giả sử bạn lưu tên người dùng đăng nhập trong session
        Users user = new Users();
        user.setUsername(loggedInUsername.getUsername());
        model.addAttribute("user", user);
        return "user/changePass";
    }


    @PostMapping("/user/changePass")
    public String processChangePassword(@Valid @ModelAttribute("user") Users user, BindingResult result,@RequestParam("newPassword") String newPassword,@RequestParam("confirmPassword") String confirmPassword,@RequestParam("oldPassword") String oldPassword, Model model) {
        // Ghi log tên người dùng để xác minh nó được nhận đúng cách
        System.out.println("Username: " + user.getUsername());
        System.out.println("old: " + oldPassword);
        System.out.println("new: " +newPassword);
        System.out.println("xac nhan: " + confirmPassword);
        
        // Validate form
//        if (result.hasErrors()) {
//            System.err.println("loix roi con cho");
//            return "user/changePass";
//        }

        // Kiểm tra mật khẩu cũ
        Users existingUser = usersDAO.findByUsername(user.getUsername());
        if (existingUser == null) {
            model.addAttribute("error", "User not found");
            return "user/changePass";
        }

//        if (!existingUser.getPassword().equals(user.getOldPassword())) 
        if (!existingUser.getPasswordUser().equals(oldPassword)) 
        {
        	if(newPassword != confirmPassword)
        	{
				model.addAttribute("message", "Incorrect old password");
				System.err.println("loix roi");
				return "user/changePass";
        	}
           
        }

        // Cập nhật mật khẩu
        existingUser.setPasswordUser(newPassword);
        usersDAO.save(existingUser);

        model.addAttribute("message", "Password updated successfully");
        return "redirect:/app/user/home/index"; // Redirect về trang chủ hoặc bất kỳ trang nào khác
    }
}
