//package com.asm.interceptor;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.asm.entity.Users;
//import com.asm.services.SessionService;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@Component
//public class AuthorizationInterceptor implements HandlerInterceptor {
//
//	@Autowired
//	private SessionService sessionService;
//
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		Users loggedInUser = (Users) sessionService.get("loggedInUser");
//
//		if (loggedInUser == null) {
//			response.sendRedirect(request.getContextPath() + "/app/user/login");
//			return false;
//		}
//		System.err.println("Đăng nhập thành công " + loggedInUser);
//		// Kiểm tra vai trò người dùng và điều hướng tới trang không có quyền truy cập
//		// nếu cần
//		String role = loggedInUser.getUserRole().getRoleName();
//		String requestURI = request.getRequestURI();
//		System.err.println("requestURI" + requestURI);
//		if (requestURI.equals("/app/admin")) {
//			if (loggedInUser.getUserRole().getRoleId() == 3) {
//				return true;
//			} else {
//				response.sendRedirect(request.getContextPath() + "/app/user/login");
//				return false;
//			}
//		}
//
//		return true;
//	}
//
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		// Bạn có thể thực hiện các hành động sau khi xử lý request ở đây nếu cần
//	}
//
//	@Override
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//		// Bạn có thể thực hiện các hành động sau khi hoàn thành xử lý request ở đây nếu
//		// cần
//	}
//}
package com.asm.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.asm.entity.Users;
import com.asm.services.SessionService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Autowired
    private SessionService sessionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Users loggedInUser = (Users) sessionService.get("loggedInUser");

        if (loggedInUser == null) {
            response.sendRedirect(request.getContextPath() + "/app/user/login");
            return false;
        }
        System.err.println("Đăng nhập thành công " + loggedInUser);

        // Kiểm tra vai trò người dùng và điều hướng tới trang không có quyền truy cập nếu cần
        int roleId = loggedInUser.getUserRole().getRoleId();
        String requestURI = request.getRequestURI();
        System.err.println("requestURI: " + requestURI);

        // Chỉ cho phép người dùng có vai trò roleId là 3 truy cập vào các trang admin và checkout
        if (requestURI.startsWith("/app/admin")) {
            if (roleId == 3 || roleId == 2) {
                return true;
            } else {
                response.sendRedirect(request.getContextPath() + "/app/user/home/index");
                return false;
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // Bạn có thể thực hiện các hành động sau khi xử lý request ở đây nếu cần
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // Bạn có thể thực hiện các hành động sau khi hoàn thành xử lý request ở đây nếu cần
    }
}
