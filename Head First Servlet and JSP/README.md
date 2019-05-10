Head First Servlet and JSP notes
==========================
* [Chapter 2 Web App Architechture: High-level Overview](#chapter-2-web-app-architechture)
  * [MVC](#MVC)
  * [Web Server, Container and Servlet](#web-server-container-and-servlet)
  * [J2EE Application Server](#j2ee-application-server)
* [Chapter 3 Mini MVC Tutorial: hands-on MVC](#chapter-3-mini-mvc-tutorial)
  * [Architecture](#architecture)
* [Chapter 4 Being a Servlet: request AND response](#chapter-4-being-a-servlet)
  * [Servlets are controlled by Container](#servlets-are-controlled-by-container)
  * [Servlet Life](#servlet-life)
  * [Inherits](#inherits)
* [Chapter 5 Being a Web App](#chapter-5-being-a-web-app)
  * [Init Parameters](#init-parameters)
  * [ServletConfig and ServletContext](#servletconfig-and-servletcontext)
  
## Chapter 2 Web App Architechture
### MVC
MVC takes the businuess logic out of the servlet, and puts it in a **model** - a resuable plain old Java class. The *Model* is a combination
of the business data (like the state of a Shopping Cart) and the methods (rules) that operate on that data.
- **Controller** (Servlet)
  - Takes user input from the request and figures out what it means to the model.
  - Tells the **model** to update itself, and makes the new model state available for the **view** (JSP)
- **View** (JSP)
  - Responsible for the presentation. 
  - It gets the state of the model from the **Controller** (although not directly; the Controller puts the model data in a place where the
  **View** can find it).
  - It's also part that gets the user input that goes back to the **Controller**.
- **Model** (Plain Old Java)
  - Holds the real business logic and the state. In other words, it knows the rules for getting and updating the state.
  - A shopping cart's contents (and the rules for that to do with it) would be part of the **Model** in **MVC**.
  - It's the only part of the system that talks to the database (although it probably uses another object for the actual DB communication, but we'll save that pattern for later)

### Web Server, Container and Servlet
| Task | Web Server | Container | Servlet 
| :--------: | :-------: | :---------: | :------:
| Creates the request & response objects | | Just before starting the thread | |
| Calls the **service()** method | | Then **service()** method calls **doGet()** or **doPost()** | |
| Start a new thread to handle request | | Starts a servlet thread| |
| Converts a response object to an HTTP response | | Generates the HTTP response stream from the data in response object | 
| Knows HTTP | Uses it to talk to the client browser | | |
| Adds HTML to the response object | | | The dynamic content for the client |
| Has a reference to the response object | | Container gives it the servlet | Uses it to print a response |
| Finds URLs in the DD | | To find the correct servlet for the request | |
|Deletes the request and response objects | | Once the servlet is finished | 
| Coordinates making dynamic content | Knows how to forward to the **Container** | Knows who to call |
| Manages lifecycles | | Calls **service()** method (and others) | |

### J2EE Application Server
- A J2EE application server includes both a **web container** AND an **EJB Container**.
- Tomcat is a web container, but not a full J2EE application server.

## Chapter 3 Mini MVC Tutorial
### Architecture
1. The client makes a request for the `form.html` page.
2. The **Container** retreives the `form.html` page.
3. The **Container** returns the page to the browser, where the user anwers the questions on the form and ...
![Architecture 1](https://github.com/weltond/DataStructure/blob/master/Head%20First%20Servlet%20and%20JSP/Chapter3/architecture_1.PNG)

4. The browser sends the request data to the container.
5. The **Container** finds the correct **servlet** based on the URL, and passes the request to the **servlet**.
6. The **servlet** calls the `BeerExpert class` for help.
7. The `expert` class returns an answer, which the **servlet** adds to the reqeuest object.
8. The **servlet** forwards the request to the **JSP**.
9. The **JSP** gets the answer from the request object.
10. The **JSP** generates a page for the **Container**.
11. The **Container** returns the page to the happy user.

![Architecture 2](https://github.com/weltond/DataStructure/blob/master/Head%20First%20Servlet%20and%20JSP/Chapter3/architecture_2.PNG)

## Chapter 4 Being a Servlet
### Servlets are controlled by **Container**
1. User clicks a link that has a URL to a **servlet**.
2. The **Container** "sees" that the request is for a **servlet**, so the contianer creates two objects:
  - `HttpServletResponse`
  - `HttpServletRequest`
3. The **Container** finds the correct **servlet** based on the URL in the request, creates or allocates a thread for that request, 
and calls the servlet's `service()` method, passing the request and response objects as arguments.
4. The `service()` method figures out which servlet method to call based on the HTTP method (`GET`, `POST`, ect) sent by the client.
The client sent an HTTP `GET` request, so the `service()` method calls the **servlet's** `doGet()` method, passing the request and response objects as arguments.
5. The **servlet** uses the response object to write out the response to the client. The response goes back through the **Container**.
6. The `service()` method completes, so the **thread** either dies or returns to a **Container-managed thread pool**. The request and response object references fall out of scope, so these objects are toast (ready for garbage collection).
The client gets the response.
### Servlet Life
![Life Cycle](https://github.com/weltond/DataStructure/blob/master/Head%20First%20Servlet%20and%20JSP/Chapter%204/lifecycle.PNG)
### Inherits
#### Servlet Interface (javax.servlet.Servlet)

  The Servlet interface says that all servlets have these five methods (the first three are lifecycle methods)
  - `service(ServletReuqest, ServletResponse)`
  - `init(ServletConfig)`
  - `destroy()`
  - `getServletConfig()`
  - `getServletInfo()`
#### GenericServlet class (javax.servlet.GenericServlet)

  **GenericServlet** is an **abstract** class that impements most of the basic servlet methods you'll need, including those from the **Servlet interface**. You will probably NEVER extend this class yourself. Most of your servlet's "servlet behavior" comes from this class.
#### HttpServlet class (javax.servlet.http.HttpServlet)

  **HttpServlet** (also an **abstract** class) implements the `service()` method to reflect the HTTPness of the servlet -- the `service()` method doesn't take just ANY old servlet request and response, but an HTTP-specific request and response.
  
#### MyServlet class

  Most of your servletness is handled by superclass methods. All you do is override the HTTP methods you need.

### Servlet config and context
- ServletConfig object
  - One `ServletConfig` object per servlet.
  - Use it to pass deploy-time information to the servlet (a database or enterprise bean lookup name, for example) that you don’t want to hard-code into the servlet (servlet init parameters).
  - Use it to access the ServletContext.
  - Parameters are confi gured in the Deployment Descriptor.
- ServletContext
  - One ServletContext per web app.
  - Use it to access web app parameters (also confi gured in the Deployment Descriptor).
  - Use it as a kind of application bulletin-board, where you can put up messages (called attributes) that other parts of the application can access.
  - Use it to get server info, including the name and version of the Container, and the version of the API that’s supported.
### Redirect
- `response.sendRedirect("...URL...");
- Relative URLs
  - Original typed in: `http://www.wickedlysmart.com/myApp/cool/bar.do`
    - `sendRedirect("foo/stuff.html")`Container will build: `http://www.wickedlysmart.com/myApp/cool/foo/stuff.html`
    - `sendRedirect("/foo/stuff.html")` Container will build: `http://www.wickedlysmart.com/foo/stuff.html`

## Chapter 5 Being a Web App
### Init Parameters
- In the **DD** (web.xml) file:
```xml
<servlet>
  <servlet-name>BeerParamTests</servlet-name>
  <servlet-class>TestInitParams</servlet-class>
  <init-param>
    <param-name>adminEmail</param-name>
    <param-value>likewecare@wickedlysmart.com</param-value>
  </init-param>
</servlet>
```
- In the **servlet** class code:
```java
out.println(getServletConfig().getInitParameter("adminEmail"));
```
- You CAN'T use servlet init parameters until the servlet is **initialized**, which means you cannot call it from your constructor!
 - When the container initializes a servlet, it makes a unique **ServletConfig** for the servlet.
 - The Container "reads" the servlet init parameters from the DD and gives them to the **ServletConfig**, then passes the **ServletConfig** to the servlet's `init()` method.
 
### ServletConfig and ServletContext
ServletConfig is **ONE PER Servlet**. ServletContext is **ONE PER Web App**.

If the app is distributed, there's **ONE ServletContext per JVM**.

- Servlet Context
  - DD
    - Within the `<web-app>` element but NOT within a specific `<servlet>` element
  ```xml
  <web-app ...>
     <context-param>
        <param-name>foo</param-name>
        <param-value>bar</param-value>
     </context-param>
  </web-app>
  ```
  - Servlet Code
  ```java
  getServletContext().getInitParameter("foo");
  ```
  - Availability
    - To any servlets and JSPs that are part of this web app.
- Servlet Config
  - DD
    - Within the `<servlet>` element for each specific servlet.
  ```xml
  <servlet>
    <servlet-name>
      BeerParamTests
    </servlet-name>
    <servlet-class>
      TestInitParams
    </servlet-class>
    <init-param>
      <param-name>foo</param-name>
      <param-value>bar</param-value>
    </init-param>
    <!-- other stuff -->
  </servlet>
  ```
  - Servlet code
  ```java
  getServletConfig().getInitParameter("foo");
  ```
  - Availaibility
    - To only the servlet for which the `<init-param>` was configured. (Although the servlet can choose to make it more widely available by storing it in an attribute.)  
