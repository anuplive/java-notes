
## **Java Servlet Technology - Hands-on Coding Exercises**
---

## **📌 Beginner Level: Basics of Servlets**

### **Exercise 1: Create a Basic Servlet**
**Objective:** Implement a simple servlet that responds with `"Hello, Servlet!"` when accessed via a browser.

🔹 **Steps:**
1. Create a servlet class named `HelloServlet`.
2. Extend `HttpServlet`.
3. Override `doGet()` to send `"Hello, Servlet!"` as a response.
4. Deploy using `@WebServlet` annotation or `web.xml` configuration.

💡 **Code Skeleton:**
```java
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Hello, Servlet!</h1>");
    }
}
```

---

### **Exercise 2: Handling Request Parameters (GET and POST)**
**Objective:** Create a servlet that reads user input via **GET and POST** methods and displays it.

🔹 **Steps:**
1. Create a `UserInputServlet`.
2. Handle form submission using `doGet()` and `doPost()`.
3. Extract user input using `request.getParameter()`.
4. Display the received data in the response.

💡 **Code Skeleton:**
```java
@WebServlet("/user-input")
public class UserInputServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        response.getWriter().println("Hello, " + username);
    }
}
```
🔹 **Test:** Create an `HTML` form that submits data to `/user-input`.

---

## **📌 Intermediate Level: Session Management & Filters**

### **Exercise 3: Implement a Login System using Sessions**
**Objective:** Create a login servlet that authenticates users and maintains session data.

🔹 **Steps:**
1. Create a `LoginServlet`.
2. Accept `username` and `password` from a form (`doPost()`).
3. Validate credentials (`admin` / `password123`).
4. If valid, create a session and store `username`.
5. Redirect to a `dashboard.jsp` that displays "Welcome, [username]".

💡 **Code Skeleton:**
```java
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ("admin".equals(username) && "password123".equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", username);
            response.sendRedirect("dashboard.jsp");
        } else {
            response.getWriter().println("Invalid credentials!");
        }
    }
}
```

---

### **Exercise 4: Implement a Logout Servlet**
**Objective:** Allow users to log out by invalidating their session.

🔹 **Steps:**
1. Create a `LogoutServlet`.
2. In `doGet()`, retrieve and invalidate the session.
3. Redirect to `login.html`.

💡 **Code:**
```java
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("login.html");
    }
}
```

---

### **Exercise 5: Implement Authentication Filter**
**Objective:** Restrict access to certain pages unless the user is logged in.

🔹 **Steps:**
1. Create a `LoginFilter` implementing `Filter`.
2. Check if the session contains a valid user.
3. If not, redirect to `login.html`.

💡 **Code:**
```java
@WebFilter("/dashboard.jsp")
public class LoginFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.html");
        } else {
            chain.doFilter(req, res);
        }
    }
}
```

---

## **📌 Advanced Level: Performance, Asynchronous Processing & Security**

### **Exercise 6: Asynchronous Request Processing**
**Objective:** Implement an asynchronous servlet that simulates long-running processing (e.g., fetching data from a slow API).

🔹 **Steps:**
1. Create an `AsyncServlet`.
2. Enable async support.
3. Use `startAsync()` to execute a long-running task.

💡 **Code:**
```java
@WebServlet(urlPatterns = "/async-task", asyncSupported = true)
public class AsyncServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        AsyncContext asyncContext = request.startAsync();
        asyncContext.start(() -> {
            try {
                Thread.sleep(3000);
                response.getWriter().write("Async Task Completed!");
                asyncContext.complete();
            } catch (Exception e) { e.printStackTrace(); }
        });
    }
}
```

---

### **Exercise 7: Prevent SQL Injection & XSS Attacks**
**Objective:** Implement security best practices by sanitizing user input.

🔹 **Steps:**
1. Create a `SecureInputServlet`.
2. Use **prepared statements** to prevent SQL Injection.
3. Encode output using `StringEscapeUtils.escapeHtml4()` to prevent XSS.

💡 **Code:**
```java
@WebServlet("/secure-input")
public class SecureInputServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userInput = request.getParameter("input");
        String safeOutput = StringEscapeUtils.escapeHtml4(userInput);

        response.getWriter().println("Safe Output: " + safeOutput);
    }
}
```

---

### **Exercise 8: Implement File Upload in Servlets**
**Objective:** Allow users to upload files using a multipart form.

🔹 **Steps:**
1. Create a `FileUploadServlet`.
2. Configure it with `@MultipartConfig`.
3. Save the uploaded file to the server.

💡 **Code:**
```java
@WebServlet("/upload")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Part filePart = request.getPart("file");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        filePart.write("C:/uploads/" + fileName);
        response.getWriter().println("File uploaded successfully!");
    }
}
```

---

## **📌 Real-World Scenario: Implementing a Mini E-Commerce Cart**

### **Exercise 9: Shopping Cart using Servlets & Sessions**
**Objective:** Implement a simple shopping cart where users can **add, remove, and view items** in their session.

🔹 **Steps:**
1. Create `CartServlet`.
2. Store items in `HttpSession`.
3. Provide endpoints to **add**, **remove**, and **view** cart items.

💡 **Code Skeleton:**
```java
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        List<String> cart = (List<String>) session.getAttribute("cart");

        if (cart == null) cart = new ArrayList<>();
        cart.add(request.getParameter("item"));

        session.setAttribute("cart", cart);
        response.getWriter().println("Cart Items: " + cart);
    }
}
```
