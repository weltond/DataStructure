package com.weltond.historyServlet;

import com.weltond.entity.Book;
import com.weltond.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author weltond
 * @project WebBrowserHistory
 * @date 5/18/2019
 */
//@WebServlet(name = "ShowAllBooksServlet", urlPatterns = "/servlet/showAllBook")
public class ShowAllBooksServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.write("This website has the following books: <br/>");
        Map<String, Book> books = DBUtil.findAllBooks();

        for (Map.Entry<String, Book> book : books.entrySet()) {
            out.write("<a href='" + request.getContextPath() + "/servlet/showBookDetail?id=" + book.getKey() + "' target='_blank'>" + book.getValue().getName() + "</a><br/>");
        }

        // ========= History : Cookie ==========
        out.write("<hr/>You recently viewed books: <br/>");

        Cookie[] cookies = request.getCookies();
        if (cookies == null) return;
        for (Cookie cookie : cookies) {
            if ("historyBookId".equals(cookie.getName())) {
                String value = cookie.getValue();
                String[] ids = value.split("-");

                for (String id : ids) {
                    Book book = DBUtil.findBookById(id);
                    out.print(book + "<br/>");
                }
            }
        }
    }
}
