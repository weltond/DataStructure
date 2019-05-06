Head First Servlet and JSP notes
==========================
* [Chapter 2 Web App Architechture: High-level Overview](#chapter-2-web-app-architechture)
* [Chapter 3 Mini MVC Tutorial: hands-on MVC](#chapter-3-mini-mvc-tutorial)
* [Chapter 4 Being a Servlet: request AND response](#chapter-4-being-a-servlet)

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

