package com.weltond.web.servlet;

import com.weltond.domain.User;
import com.weltond.domain.UserForm;
import com.weltond.service.UserService;
import com.weltond.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

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

        /*Verify form data*/
        UserForm uf = new UserForm();
        try {
            BeanUtils.populate(uf, request.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        if (!uf.validate()) {   // form not valid
            request.setAttribute("uf", uf);
            request.getRequestDispatcher("/reg.jsp").forward(request, response);
            return;
        }

        User user = new User();
        setUser(request, user);

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

    private void setUser(HttpServletRequest request, User user) {
        try {
            /* Method 1 (Not working though) */
            // add a date converter
//            ConvertUtils.register(new Converter() {
//                @Override
//                public Object convert(Class type, Object value) {
//                    Date date = null;
//                    if (value instanceof String) {
//                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                        try {
//                            date = sdf.parse((String)value);
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    return date;
//                }
//            }, Date.class);
            // ConvertUtils.register(new DateLocaleConverter(), Date.class); // not working
//            user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthday")));
//            BeanUtils.populate(user,request.getParameterMap() );    // need collections version 3

            /*Method 2*/
            Map<String, String[]> map = request.getParameterMap();

            for (Map.Entry<String, String[]> m : map.entrySet()) {
                String name = m.getKey();
                //String[] value = m.getValue();

                if (name.equals("repassword")) continue;

                // create property descriptor
                PropertyDescriptor pd = new PropertyDescriptor(name, User.class);
                // get property "setter"
                Method setter = pd.getWriteMethod();

                if (name.equals("birthday")) {
                    Date date = null;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        date = sdf.parse(m.getValue()[0]);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (date != null) {
                        setter.invoke(user, date);
                    }
                } else {
                    setter.invoke(user, (Object)m.getValue()[0]);
                }


//                if (value.length == 1) {
//                    setter.invoke(user, value[0]);
//                } else {
//                    setter.invoke(user, (Object)value);
//                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
