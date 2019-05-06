import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * @author weltond
 * @project HeadFirstServlet
 * @date 5/2/2019
 */
public class Ch1Servlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        PrintWriter out = response.getWriter();
        Date today = new Date();
        // System.out.println(this.getServletConfig());
        out.println("<html> " +
                "<body>" +
                "<h1 align=center>HF\'s Chapter 1 Servlet<h1/>" +
                "<br>" + today + "</body>" + "</html>");
    }
}
