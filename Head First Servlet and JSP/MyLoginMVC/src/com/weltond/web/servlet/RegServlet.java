package com.weltond.web.servlet;

import com.weltond.domain.User;
import com.weltond.service.UserService;
import com.weltond.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Weltond Ning
 * @Project MyLoginMVC
 * @Date 5/21/2019
 */
@WebServlet(name = "RegServlet", urlPatterns = "/servlet/regServlet")
public class RegServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        // Get form data
        User user = new User();
        try {
            BeanUtils.populate(user,request.getParameterMap() );
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        // Call logic
        UserService us = new UserServiceImpl();
        try {
            us.register(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Dispatch
        response.getWriter().write("Register Successful!\n Return to LogIn page in 3 seconds");
        response.setHeader("refresh", "3;url=" + request.getContextPath() + "/index.jsp");
    }
}
