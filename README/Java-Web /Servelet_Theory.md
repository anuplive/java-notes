## 1. **Introduction to Servlets**

### What is a Servlet?
A **Servlet** is a Java program that runs on a web server and handles client requests (usually HTTP). It dynamically generates responses, typically in **HTML**, **JSON**, or other formats.

### Servlet vs. CGI
| Feature | Servlet | CGI |
|---------|---------|-----|
| Performance | High (runs in JVM, lightweight threads) | Low (creates a new process per request) |
| Scalability | Better (thread-based model) | Poor (process-heavy) |
| Security | More control via Java security model | Limited security features |
| Language Support | Java-based | Multiple languages (Perl, Python, etc.) |

### Advantages of Servlets
- **Efficient**: Uses threads instead of separate processes.
- **Portable**: Java-based, runs on any servlet container (Tomcat, Jetty, etc.).
- **Secure**: Leverages Java security features.
- **Scalable**: Supports session management, filters, and listeners for enhanced functionality.

---

## 2. **Servlet Lifecycle**

A servlet lifecycle has **three main methods**:

### 1️⃣ `init()`
- Called **once** when the servlet is created.
- Used for **initialization tasks** like loading configuration settings.

```java
@Override
public void init() throws ServletException {
    System.out.println("Servlet Initialized");
}
```

### 2️⃣ `service(HttpServletRequest, HttpServletResponse)`
- Called **for every request**.
- Determines the HTTP method (`doGet()`, `doPost()`, etc.).

```java
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.getWriter().println("Hello from Servlet");
}
```

### 3️⃣ `destroy()`
- Called when the servlet is **unloaded**.
- Used for **cleanup tasks** like closing database connections.

```java
@Override
public void destroy() {
    System.out.println("Servlet Destroyed");
}
```

---

## 3. **Servlet API**

### Key Interfaces and Classes
| Component | Description |
|-----------|-------------|
| `Servlet` | Core interface for servlets. |
| `GenericServlet` | Implements `Servlet`, protocol-independent. |
| `HttpServlet` | Extends `GenericServlet`, specialized for HTTP. |

### `ServletConfig` vs. `ServletContext`
| Feature | `ServletConfig` | `ServletContext` |
|---------|---------------|-----------------|
| Scope | Per servlet | Per application |
| Access | Inside `init()` | Accessible everywhere |
| Usage | Read servlet-specific config | Read global app settings |

Example:
```java
String value = getServletContext().getInitParameter("appName");
```

### `HttpServletRequest` & `HttpServletResponse`
- `HttpServletRequest`: Represents client request, allows parameter retrieval.
- `HttpServletResponse`: Represents server response, allows setting content type, headers.

Example:
```java
String name = request.getParameter("username");
response.setContentType("text/html");
response.getWriter().println("Hello, " + name);
```

---

## 4. **Handling Requests and Responses**

### GET vs. POST
| Method | Use Case | Characteristics |
|--------|---------|----------------|
| `GET` | Fetch data | Parameters in URL, cacheable |
| `POST` | Submit data | Parameters in body, more secure |

### Reading Request Parameters
```java
String username = request.getParameter("username");
String[] hobbies = request.getParameterValues("hobbies");
```

### Setting Response Headers
```java
response.setContentType("application/json");
response.setStatus(HttpServletResponse.SC_OK);
```

---

## 5. **Session Management**

### Session Tracking Techniques
1. **Cookies**
```java
Cookie cookie = new Cookie("user", "JohnDoe");
cookie.setMaxAge(3600);
response.addCookie(cookie);
```

2. **URL Rewriting** (when cookies are disabled)
```java
String encodedURL = response.encodeURL("dashboard.jsp");
```

3. **HttpSession API**
```java
HttpSession session = request.getSession();
session.setAttribute("username", "JohnDoe");
```

---

## 6. **Servlet Filters & Listeners**

### Filters (`Filter` Interface)
Used for **logging, authentication, input validation**.
```java
public class AuthFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        System.out.println("Filter applied before processing request.");
        chain.doFilter(req, res);
    }
}
```

### Listeners
| Listener | Purpose |
|----------|---------|
| `ServletContextListener` | Tracks application start/stop. |
| `HttpSessionListener` | Tracks session creation/destruction. |

Example:
```java
public class MyListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("App Started");
    }
}
```

---

## 7. **Servlet Deployment & Web.xml Configuration**

### Web Application Structure
```
webapps/
 ├── MyApp/
 │   ├── WEB-INF/
 │   │   ├── web.xml
 │   │   ├── classes/
 │   │   ├── lib/
 │   ├── index.jsp
 │   ├── MyServlet.java
```

### `web.xml` Example
```xml
<servlet>
    <servlet-name>MyServlet</servlet-name>
    <servlet-class>com.example.MyServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>MyServlet</servlet-name>
    <url-pattern>/hello</url-pattern>
</servlet-mapping>
```

### Annotations
```java
@WebServlet("/hello")
public class MyServlet extends HttpServlet { }
```

---

## 8. **Asynchronous Processing in Servlets**
For long-running requests.

```java
@WebServlet(urlPatterns = "/async", asyncSupported = true)
public class AsyncServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        AsyncContext asyncContext = request.startAsync();
        asyncContext.start(() -> {
            try {
                Thread.sleep(3000);
                response.getWriter().write("Async response");
                asyncContext.complete();
            } catch (Exception e) { e.printStackTrace(); }
        });
    }
}
```

---

## 9. **Servlets & JSP Integration**

### `RequestDispatcher` (`forward()` vs. `include()`)
```java
RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
rd.forward(request, response);
```

### Servlet as Controller in MVC
1. **Servlet** (Controller) processes logic.
2. **JSP** (View) displays data.

Example:
```java
request.setAttribute("message", "Hello, User!");
request.getRequestDispatcher("home.jsp").forward(request, response);
```

**JSP side:**
```jsp
<p>${message}</p>
```

---

## 10. **Real-world Applications & Best Practices**

✅ **Security Considerations**
- Validate input to prevent **XSS, SQL Injection**.
- Use `HttpOnly` and `Secure` flags for cookies.
- Disable directory listing in deployment.

✅ **Performance Optimizations**
- Use connection pooling for database access.
- Implement caching for frequently requested data.

✅ **Common Pitfalls**  
❌ Not closing resources (DB connections, streams).  
❌ Using instance variables (servlets are shared between requests).

