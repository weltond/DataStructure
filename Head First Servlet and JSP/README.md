# Head First Servlet and JSP notes

* [Chapter 2](chapter-2)

## Chapter 2 
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

| Task | Web Server | Container | Servlet 
| :--------: | :-------: | :---------: | :------:
| Creates the request & response objects | | Just before starting the thread | |
| Calls the **service()** method | | Then **service()** method calls **doGet()** or **doPost()** | |
