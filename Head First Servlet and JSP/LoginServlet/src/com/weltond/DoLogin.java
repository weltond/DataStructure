package com.weltond;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Weltond Ning
 * @Project LoginServlet
 * @Date 5/18/2019
 */
@WebServlet(name = "DoLogin")
public class DoLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form
        String userName = request.getParameter("userName");
        String pwd = request.getParameter("pwd");
        System.out.println(userName + ", " + pwd);
        // business logic
        if ("weltond".equals(userName) && "123".equals(pwd)) {
            request.setAttribute("el", "el msg");
            request.getSession().setAttribute("name", userName);
            // dispatch
            request.getRequestDispatcher("/success.jsp").forward(request, response);
        } else {
            // this will not save userName because its a new request
            // response.sendRedirect(request.getContextPath() + "/success.jsp");
            System.out.println("wrong");
            request.setAttribute("msg", "User name or password is WRONG!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
