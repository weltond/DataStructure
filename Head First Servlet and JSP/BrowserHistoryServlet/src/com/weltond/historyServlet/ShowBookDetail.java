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
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author weltond
 * @project WebBrowserHistory
 * @date 5/18/2019
 */
//@WebServlet(name = "ShowBookDetail", urlPatterns = "/servlet/showBookDetail")
public class ShowBookDetail extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // display detail book

        // get id ("?id=")
        String id = request.getParameter("id");
        Book book = DBUtil.findBookById(id);

        //out.write(book.toString());
        out.print(book);

        // add current book's to client browser
        String historyBookId = organizeId(id, request);

        Cookie cookie = new Cookie("historyBookId", historyBookId);
        cookie.setPath("/");
        cookie.setMaxAge(Integer.MAX_VALUE);    // set cookie's reserved time

        response.addCookie(cookie);
    }

    /**
     *          Client Browser                                  showAllBooks                    showBookDetail
     * 1.          No Cookie                                          1                        historyBookId = 1
     * 2.     Has Cookie but no historyBookId                         1                        historyBookId = 1
     * 3.     Has historyBookId = 1 Cookie                            2                        hitoryBookId = 2-1
     * 4.     Has historyBookId = 1-2 Cookie                          2                        historyBookId = 2-1
     * 5.     Has historyBookId = 1-2-3 Cookie                        2                        historyBookId = 2-1-3
     * 6.     Has historyBookId = 1-2-3 Cookie                        4                        historyBookId = 4-1-2
     * @param id
     * @param request
     * @return
     */
    private String organizeId(String id, HttpServletRequest request) {
        // get client cookie
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return id;
        }

        // search for the cookie name that is "historyBook"
        Cookie historyBook = null;
        for (Cookie cookie : cookies) {
            if ("historyBookId".equals(cookie.getName())) {
                historyBook = cookie;
            }
        }

        // if no cookie that matches "historyBook"
        if (historyBook == null) {
            return id;
        }

        String value = historyBook.getValue();  // 2-1-3

        String[] values = value.split("-");
        LinkedList<String> list = new LinkedList<>(Arrays.asList(values));

        if (list.size() < 3) {
            list.remove(id);
        } else {    // has max num of history = 3.
            // if list contains id, remove current one.
            // if list not contains id, remove the last one.
            if (!list.remove(id)) {
                list.removeLast();
            }
        }

        list.addFirst(id);  // put current id to the front of the list.

        StringBuffer sb = new StringBuffer();
        for (int i = 0, len = list.size(); i < len; i++) {
            if (i > 0) {
                sb.append("-");
            }
            sb.append(list.get(i));
        }

        return sb.toString();
    }
}
