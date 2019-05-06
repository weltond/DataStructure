Head First Servlet and JSP notes
==========================
* [Chapter 2: Web App Architechture: High-level Overview](#chapter-2)
* [Chapter 3 Mini MVC Tutorial: hands-on MVC](#chapter-3-mini-mvc-tutorial-:-hands-on-mvc)

## Chapter 2 Web App Architechture: High-level Overview
### MVC
MVC takes the businuess logic out of the servlet, and puts it in a **model** - a resuable plain old Java class. The *Model* is a combination
of the business data (like the state of a Shopping Cart) and the methods (rules) that operate on that data.
- Controller (Servlet)
  - Takes user input from the request and figures out what it means to the model.
  - Tells the **model** to update itself, and makes the new model state available for the **view** (JSP)
- View (JSP)
  - Responsible for the presentation. 
  - It gets the state of the model from the **Controller** (although not directly; the Controller puts the model data in a place where the
  **View** can find it).
  - It's also part that gets the user input that goes back to the **Controller**.
- Model (Plain Old Java)
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

## Chapter 3 Mini MVC Tutorial: hands-on MVC
