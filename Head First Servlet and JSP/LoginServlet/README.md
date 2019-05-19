# JSP Best practice

- `login.jsp` is the main page. Submit to the **LoginServlet**.

  - If success, dispatch to `success.jsp`
    - can also **redirect** to `home.jsp`. Notice here we should use **session.getAttribute**, not **request.getParameter**
  - Else, dispatch to 'login.jsp`
