package com.example.web;

import com.example.model.BeerExpert;
import sun.misc.Request;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author weltond
 * @project Chapter3
 * @date 5/6/2019
 */
public class BeerSelect extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");   // this method comes from the ServeletResponse interface
        PrintWriter out = resp.getWriter();

        out.println("Beer Selection Advice<br>");
        // notice that the argument matches the value of the "name" attribute in HTML's <select> tag
        String c = req.getParameter("color");
        //out.println("<br>Got beer color " + c); // we're not giving back advice here, just displaying test information
        List<String> brands = new BeerExpert().getBrands(c);
        /*Now that JSP is going to produce the output, we should remove the test output from the servlet. */
//        out.println("<br> Here is our advice for color - " + c);
//        for (String brand : brands) {
//            out.println("<br>" + brand);
//        }

        /*Add an attribute to the request object for the JSP to use. Notice the JSP is looking for "styles"*/
        req.setAttribute("styles", brands);

        RequestDispatcher view = req.getRequestDispatcher("result.jsp");// Instantiate a request dispatcher for JSP.

        /*Use the request dispatcher to ask the Container to crank up the JSP, sending it the request and response*/
        view.forward(req, resp);
    }
}
