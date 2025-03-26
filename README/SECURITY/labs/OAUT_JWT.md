You're absolutely right! Let me enhance the assignment with detailed OAuth provider configuration steps and add comprehensive code comments. Here's the improved version:

## Enhanced Assignment: OAuth with JWT Validation

### New Step: Configure OAuth Provider (Google Example)

**Step 1: Set Up Google OAuth Credentials**

1. Go to [Google Cloud Console](https://console.cloud.google.com/)
2. Create a new project or select an existing one
3. Navigate to "APIs & Services" > "Credentials"
4. Click "Create Credentials" > "OAuth client ID"
5. Select "Web application" as the application type
6. Set the name (e.g., "My JSP OAuth App")
7. Under "Authorized JavaScript origins", add: `http://localhost:8080`
8. Under "Authorized redirect URIs", add: `http://localhost:8080/oauth-app/callback`
9. Click "Create" and note down your Client ID and Client Secret
10. Navigate to "OAuth consent screen" and configure your app name, support email, etc.

**Update OAuthConfig.java with your credentials:**

```java
package com.example.oauth;

/**
 * Configuration class for OAuth settings.
 * Replace placeholder values with your actual OAuth provider credentials.
 */
public class OAuthConfig {
    // Google OAuth 2.0 credentials
    public static final String CLIENT_ID = "YOUR_GOOGLE_CLIENT_ID.apps.googleusercontent.com";
    public static final String CLIENT_SECRET = "YOUR_GOOGLE_CLIENT_SECRET";
    
    // OAuth endpoints
    public static final String REDIRECT_URI = "http://localhost:8080/oauth-app/callback";
    public static final String AUTH_ENDPOINT = "https://accounts.google.com/o/oauth2/auth";
    public static final String TOKEN_ENDPOINT = "https://oauth2.googleapis.com/token";
    public static final String USERINFO_ENDPOINT = "https://openidconnect.googleapis.com/v1/userinfo";
    
    // Requested scopes (openid for JWT, email and profile for user info)
    public static final String SCOPES = "openid email profile";
    
    // JWT validation configuration
    public static final String JWKS_URI = "https://www.googleapis.com/oauth2/v3/certs";
    public static final String ISSUER = "https://accounts.google.com";
    
    // Session attributes
    public static final String SESSION_USER_EMAIL = "user_email";
    public static final String SESSION_USER_NAME = "user_name";
    public static final String SESSION_ID_TOKEN = "id_token";
    public static final String SESSION_ACCESS_TOKEN = "access_token";
}
```

### Enhanced Code with Comments

Here's the enhanced `CallbackServlet.java` with detailed comments:

```java
package com.example.oauth;

import io.jsonwebtoken.Claims;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the OAuth callback after user authentication.
 * Exchanges authorization code for tokens and validates the JWT ID token.
 */
@WebServlet("/callback")
public class CallbackServlet extends HttpServlet {
    
    /**
     * Processes the OAuth callback with authorization code
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. Get the authorization code from query parameters
        String code = request.getParameter("code");
        String error = request.getParameter("error");
        
        // 2. Check for errors from OAuth provider
        if (error != null) {
            handleOAuthError(error, request, response);
            return;
        }
        
        // 3. Verify we received an authorization code
        if (code == null || code.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, 
                "Authorization code missing");
            return;
        }
        
        // 4. Exchange authorization code for tokens
        try {
            TokenResponse tokenResponse = exchangeCodeForTokens(code);
            
            // 5. Validate the ID token (JWT)
            Claims claims = JwtUtil.validateJwt(tokenResponse.idToken);
            
            // 6. Verify token issuer matches expected value
            if (!OAuthConfig.ISSUER.equals(claims.getIssuer())) {
                throw new ServletException("Invalid token issuer");
            }
            
            // 7. Store user information in session
            storeUserSession(request, tokenResponse, claims);
            
            // 8. Redirect to home page
            response.sendRedirect("home");
            
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, 
                "Authentication failed: " + e.getMessage());
        }
    }
    
    /**
     * Exchanges authorization code for access token and ID token
     */
    private TokenResponse exchangeCodeForTokens(String code) throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // Prepare token request
            HttpPost tokenRequest = new HttpPost(OAuthConfig.TOKEN_ENDPOINT);
            
            // Add required OAuth parameters
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("code", code));
            params.add(new BasicNameValuePair("client_id", OAuthConfig.CLIENT_ID));
            params.add(new BasicNameValuePair("client_secret", OAuthConfig.CLIENT_SECRET));
            params.add(new BasicNameValuePair("redirect_uri", OAuthConfig.REDIRECT_URI));
            params.add(new BasicNameValuePair("grant_type", "authorization_code"));
            
            tokenRequest.setEntity(new UrlEncodedFormEntity(params));
            
            // Execute token request
            try (CloseableHttpResponse tokenResponse = httpClient.execute(tokenRequest)) {
                // Parse JSON response (in production, use a proper JSON library)
                String responseBody = EntityUtils.toString(tokenResponse.getEntity());
                return parseTokenResponse(responseBody);
            }
        }
    }
    
    /**
     * Simple JSON parser for token response (use Jackson/Gson in production)
     */
    private TokenResponse parseTokenResponse(String json) {
        // This is a simplified parser - use proper JSON parsing in production
        String idToken = extractJsonValue(json, "id_token");
        String accessToken = extractJsonValue(json, "access_token");
        
        if (idToken == null || accessToken == null) {
            throw new RuntimeException("Invalid token response");
        }
        
        return new TokenResponse(idToken, accessToken);
    }
    
    /**
     * Stores user information in the HTTP session
     */
    private void storeUserSession(HttpServletRequest request, 
            TokenResponse tokenResponse, Claims claims) {
        HttpSession session = request.getSession();
        session.setAttribute(OAuthConfig.SESSION_ID_TOKEN, tokenResponse.idToken);
        session.setAttribute(OAuthConfig.SESSION_ACCESS_TOKEN, tokenResponse.accessToken);
        session.setAttribute(OAuthConfig.SESSION_USER_EMAIL, claims.get("email", String.class));
        session.setAttribute(OAuthConfig.SESSION_USER_NAME, claims.get("name", String.class));
    }
    
    /**
     * Handles OAuth provider errors
     */
    private void handleOAuthError(String error, HttpServletRequest request, 
            HttpServletResponse response) throws IOException {
        String errorDescription = request.getParameter("error_description");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, 
            "OAuth error: " + error + " - " + errorDescription);
    }
    
    /**
     * Helper method to extract values from simple JSON
     */
    private String extractJsonValue(String json, String key) {
        if (json.contains("\"" + key + "\":")) {
            int start = json.indexOf("\"" + key + "\":") + key.length() + 3;
            int end = json.indexOf("\"", start);
            if (end > start) {
                return json.substring(start, end);
            }
        }
        return null;
    }
    
    /**
     * Simple container for token response data
     */
    private static class TokenResponse {
        final String idToken;
        final String accessToken;
        
        TokenResponse(String idToken, String accessToken) {
            this.idToken = idToken;
            this.accessToken = accessToken;
        }
    }
}
```

### Enhanced JWT Utility with Comments

```java
package com.example.oauth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SigningKeyResolver;
import io.jsonwebtoken.security.Keys;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.security.Key;
import java.util.Base64;

/**
 * Utility class for JWT validation and processing.
 * Implements JWT validation according to RFC 7519.
 */
public class JwtUtil {
    
    /**
     * Validates a JWT and returns its claims
     * @param jwt The JWT to validate
     * @return The validated claims
     * @throws Exception if validation fails
     */
    public static Claims validateJwt(String jwt) throws Exception {
        // In production, you would:
        // 1. Download the JWKS (JSON Web Key Set) from the provider
        // 2. Find the correct key based on the JWT's 'kid' header
        // 3. Use that key to validate the signature
        
        // For this example, we're using a simplified approach with the client secret
        // Note: This is NOT recommended for production with asymmetric signing
        
        Key signingKey = Keys.hmacShaKeyFor(OAuthConfig.CLIENT_SECRET.getBytes());
        
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                // In production, add these validations:
                // .requireIssuer(OAuthConfig.ISSUER)
                // .requireAudience(OAuthConfig.CLIENT_ID)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }
    
    /**
     * Fetches the JWKS (JSON Web Key Set) from the provider
     */
    private static String fetchJwks(String jwksUri) throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(jwksUri);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                if (response.getStatusLine().getStatusCode() != 200) {
                    throw new RuntimeException("Failed to fetch JWKS: " + 
                        response.getStatusLine());
                }
                return EntityUtils.toString(response.getEntity());
            }
        }
    }
    
    /**
     * Extracts a claim from an ID token without validation
     * WARNING: Only use this for non-security purposes as token isn't validated
     */
    public static String getUnverifiedClaim(String idToken, String claimName) {
        try {
            String[] parts = idToken.split("\\.");
            if (parts.length != 3) {
                throw new IllegalArgumentException("Invalid JWT format");
            }
            
            String payload = new String(Base64.getUrlDecoder().decode(parts[1]));
            // In production, use a proper JSON parser
            if (payload.contains("\"" + claimName + "\":")) {
                int start = payload.indexOf("\"" + claimName + "\":") + claimName.length() + 3;
                int end = payload.indexOf("\"", start);
                return payload.substring(start, end);
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse ID token", e);
        }
    }
}
```

### Additional Step: Add a Logout Servlet

Create `src/main/java/com/example/oauth/LogoutServlet.java`:

```java
package com.example.oauth;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Handles user logout by invalidating the session
 * and redirecting to the OAuth provider's logout endpoint
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Invalidate the current session
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        
        // For Google OAuth, you can redirect to their logout endpoint
        // Note: This will log the user out of Google entirely
        String logoutUrl = "https://accounts.google.com/Logout";
        
        // Alternatively, just redirect to your home page
        response.sendRedirect(request.getContextPath());
    }
}
```

### Updated Home Page with Token Details

Enhance `home.jsp` to show more token information:

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home</title>
    <style>
        .token { word-break: break-all; font-family: monospace; font-size: 0.8em; }
    </style>
</head>
<body>
    <h1>Welcome, <c:out value="${sessionScope.user_name}"/>!</h1>
    <p>Email: <c:out value="${sessionScope.user_email}"/></p>
    
    <h2>Token Information</h2>
    <h3>ID Token</h3>
    <div class="token"><c:out value="${sessionScope.id_token}"/></div>
    
    <h3>Access Token</h3>
    <div class="token"><c:out value="${sessionScope.access_token}"/></div>
    
    <p><a href="logout">Logout</a></p>
    
    <h3>Token Claims</h3>
    <pre>${token_claims}</pre>
</body>
</html>
```

### Updated HomeServlet to Show Claims

```java
package com.example.oauth;

import io.jsonwebtoken.Claims;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Check authentication
        if (request.getSession().getAttribute(OAuthConfig.SESSION_USER_EMAIL) == null) {
            response.sendRedirect("login");
            return;
        }
        
        // Add token claims to request attributes for display
        try {
            String idToken = (String) request.getSession().getAttribute(OAuthConfig.SESSION_ID_TOKEN);
            Claims claims = JwtUtil.validateJwt(idToken);
            request.setAttribute("token_claims", claims.toString());
        } catch (Exception e) {
            request.setAttribute("token_claims", "Failed to parse claims: " + e.getMessage());
        }
        
        // Forward to home page
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }
}
```

## Complete Step-by-Step Testing Guide

1. **Build and Run**:
   ```bash
   mvn clean package
   docker build -t oauth-jsp-app .
   docker run -p 8080:8080 oauth-jsp-app
   ```

2. **Access Application**:
   - Open `http://localhost:8080` in your browser
   - Click "Login with OAuth Provider"

3. **OAuth Flow**:
   - You'll be redirected to Google's login page
   - Authenticate with your Google account
   - Approve the requested permissions

4. **Verify Return**:
   - You should be redirected back to your app's home page
   - Verify your user info is displayed correctly
   - Check that the tokens are shown (for debugging)

5. **Test Logout**:
   - Click the logout link
   - Verify you're logged out and can log in again

## Security Considerations

1. **Never expose client secrets**:
   - In production, don't hardcode secrets in source code
   - Use environment variables or secure configuration

2. **Use proper JWT validation**:
   - The example uses a simplified approach
   - In production, properly implement JWKS validation

3. **Add CSRF protection**:
   - Implement state parameter in OAuth flow
   - Use anti-CSRF tokens for forms

4. **Secure session handling**:
   - Set appropriate session timeouts
   - Use secure and HttpOnly flags for cookies

This enhanced version provides:
1. Detailed OAuth provider configuration steps
2. Comprehensive code comments explaining each component
3. Better error handling
4. More complete token display for learning purposes
5. Security considerations at each step

---
To register an OAuth application with Google, follow these detailed steps:

---

### **Step 1: Go to Google Developer Console**
1. Open your web browser and navigate to the [Google Developer Console](https://console.developers.google.com/).
2. Sign in with your Google account. If you don’t have one, create a new Google account.

---

### **Step 2: Create a New Project**
1. In the Google Developer Console, click on the project dropdown menu at the top of the page (usually labeled "Select a project" or displays the current project name).
2. Click on the **"New Project"** button.
3. Enter a name for your project (e.g., "My OAuth Application").
4. Optionally, you can edit the project ID, which is auto-generated by Google.
5. Click **"Create"** to create the project.

---

### **Step 3: Enable OAuth API**
1. After creating the project, you will be redirected to the project dashboard.
2. In the left-hand sidebar, click on **"APIs & Services"** > **"Library"**.
3. Search for **"OAuth"** in the API Library search bar.
4. Select **"OAuth 2.0 API"** or any other API you want to use (e.g., Google Drive API, Gmail API, etc.).
5. Click **"Enable"** to enable the API for your project.

---

### **Step 4: Configure OAuth Consent Screen**
1. In the left-hand sidebar, click on **"APIs & Services"** > **"OAuth consent screen"**.
2. Select the **"External"** user type (if you want to allow users outside your organization to access the app) and click **"Create"**.
3. Fill in the required fields:
   - **App name**: Enter the name of your application (e.g., "My OAuth App").
   - **User support email**: Enter your email address or a support email for your application.
   - **Developer contact information**: Enter your email address.
4. Click **"Save and Continue"**.
5. On the **Scopes** page, click **"Add or Remove Scopes"**, select the required scopes (e.g., `email`, `profile`, or any API-specific scopes), and click **"Update"**.
6. Click **"Save and Continue"**.
7. On the **Test Users** page, add any email addresses of users who will test the application (optional for now).
8. Click **"Save and Continue"** and then **"Back to Dashboard"**.

---

### **Step 5: Register OAuth 2.0 Credentials**
1. In the left-hand sidebar, click on **"APIs & Services"** > **"Credentials"**.
2. Click on the **"Create Credentials"** button and select **"OAuth client ID"**.
3. Configure the OAuth client:
   - **Application type**: Select **"Web application"**.
   - **Name**: Enter a name for your OAuth client (e.g., "My OAuth Client").
   - **Authorized JavaScript origins**: Leave this blank for now.
   - **Authorized redirect URIs**: Enter `http://localhost:8080/oauth2callback` (this is where Google will redirect users after they grant permission).
4. Click **"Create"**.
5. Once the OAuth client is created, Google will display the **Client ID** and **Client Secret**. Note these down securely, as you will need them for your application.

---

### **Step 6: Use the Credentials in Your Application**
1. Use the **Client ID** and **Client Secret** in your application's OAuth flow.
2. Ensure your application is running on `http://localhost:8080` and can handle the redirect to `/oauth2callback`.

---

### **Step 7: Test Your OAuth Flow**
1. Run your application and initiate the OAuth flow.
2. Google will redirect the user to the consent screen, where they can grant permissions.
3. After granting permissions, Google will redirect the user to the specified `redirect_uri` (`http://localhost:8080/oauth2callback`) with an authorization code.
4. Your application should exchange the authorization code for an access token using the **Client ID** and **Client Secret**.


### **Additional Notes**
- If you need to use a different `redirect_uri` (e.g., for production), update it in the **Authorized redirect URIs** section of your OAuth client configuration.
- Keep your **Client Secret** secure and never expose it in client-side code.

___

Setting up Apache Tomcat 9 on a Mac with an M1 Pro Apple Silicon chip involves downloading, configuring, and integrating Tomcat into your development environment. Below are the detailed steps:

---

### **Step 1: Download Apache Tomcat 9**
1. Open your web browser and go to the [Apache Tomcat official website](https://tomcat.apache.org/).
2. Navigate to the **Tomcat 9** section under the "Download" menu.
3. Under the **Binary Distributions** section, download the **Core** `.tar.gz` file (e.g., `apache-tomcat-9.0.x.tar.gz`).

---

### **Step 2: Extract the Tomcat Folder**
1. Locate the downloaded `.tar.gz` file (usually in the `Downloads` folder).
2. Open the Terminal application on your Mac.
3. Navigate to the directory where the file is downloaded:
   ```bash
   cd ~/Downloads
   ```
4. Extract the `.tar.gz` file using the following command:
   ```bash
   tar -xzf apache-tomcat-9.0.x.tar.gz
   ```
   This will create a folder named `apache-tomcat-9.0.x`.

5. Move the extracted folder to a permanent location (e.g., `/Library/Tomcat`):
   ```bash
   sudo mv apache-tomcat-9.0.x /Library/Tomcat
   ```

---

### **Step 3: Set Permissions for Tomcat**
1. Change the ownership of the Tomcat folder to your user:
   ```bash
   sudo chown -R $(whoami) /Library/Tomcat
   ```
2. Make the Tomcat scripts executable:
   ```bash
   sudo chmod +x /Library/Tomcat/bin/*.sh
   ```

---

### **Step 4: Set Environment Variables**
1. Open the Terminal and edit your shell configuration file (e.g., `.zshrc` for Zsh or `.bash_profile` for Bash):
   ```bash
   nano ~/.zshrc
   ```
2. Add the following lines to set the `CATALINA_HOME` environment variable:
   ```bash
   export CATALINA_HOME=/Library/Tomcat
   export PATH=$CATALINA_HOME/bin:$PATH
   ```
3. Save the file and exit the editor (in Nano, press `CTRL + X`, then `Y`, and `Enter`).
4. Reload the shell configuration:
   ```bash
   source ~/.zshrc
   ```

---

### **Step 5: Verify Tomcat Installation**
1. Start Tomcat by running:
   ```bash
   $CATALINA_HOME/bin/startup.sh
   ```
2. Open your web browser and navigate to `http://localhost:8080`. You should see the Apache Tomcat welcome page.
3. To stop Tomcat, run:
   ```bash
   $CATALINA_HOME/bin/shutdown.sh
   ```

---

### **Step 6: Set Up Tomcat in IntelliJ IDEA**
1. Open IntelliJ IDEA.
2. Go to **File** > **Project Structure** > **Modules**.
3. Click the **+** button, select **Web**, and configure your web module.
4. Go to **Run** > **Edit Configurations**.
5. Click the **+** button and select **Tomcat Server** > **Local**.
6. Configure the Tomcat server:
   - **Application Server**: Click **Configure** and set the Tomcat home directory to `/Library/Tomcat`.
   - **Deployment**: Click **+**, select your web application artifact, and set the context path.
7. Click **Apply** and **OK**.

---

### **Step 7: Set Up Tomcat in Eclipse**
1. Open Eclipse.
2. Go to **Window** > **Preferences** > **Server** > **Runtime Environments**.
3. Click **Add**, select **Apache Tomcat v9.0**, and click **Next**.
4. Set the Tomcat installation directory to `/Library/Tomcat` and click **Finish**.
5. Create a new server by going to **File** > **New** > **Other** > **Server**.
6. Select **Tomcat v9.0 Server** and configure it with your project.

---

### **Step 8: Test Your Setup**
1. Start Tomcat from your IDE.
2. Deploy your web application.
3. Access your application via `http://localhost:8080/yourapp`.


### **Additional Notes**
- If you encounter issues related to Apple Silicon (M1 Pro), ensure you are using a compatible JDK (e.g., Azul Zulu or OpenJDK for ARM64).
- For better performance, use an IDE optimized for Apple Silicon (e.g., IntelliJ IDEA or Eclipse for ARM64).

--------------------------------------------------------------------------------------------------------------------------------------------

Here’s the **complete step-by-step guide** with all missing parts included (Maven WAR packaging, Dockerization, and Kubernetes deployment), optimized for **Mac M1 Pro (ARM64)**:

---

### **Step-by-Step Plan**
1. **Create a Maven WAR file** (for Java web apps)
2. **Dockerize Tomcat + WAR file** (ARM64-compatible)
3. **Deploy to Kubernetes** (with YAML files)

---

## **1. Create a Maven WAR File**
### **Prerequisite**: Maven installed (`brew install maven` on Mac).
#### **Steps**:
1. **Navigate to your Java project**:
   ```bash
   cd /path/to/your/java-project
   ```

2. **Ensure `pom.xml` has `<packaging>war</packaging>`**:
   ```xml
   <project>
     <packaging>war</packaging>
     <build>
       <finalName>myapp</finalName>  <!-- Output: myapp.war -->
     </build>
   </project>
   ```

3. **Build the WAR file**:
   ```bash
   mvn clean package
   ```
   - Output: `target/myapp.war` (or `target/{artifactId}.war`).

---

## **2. Dockerize Tomcat + WAR File (ARM64)**
### **Prerequisite**: Docker Desktop (Apple Silicon version).
#### **Steps**:
1. **Create `Dockerfile`**:
   ```dockerfile
   # ARM64-compatible base image (for M1/M2 Macs)
   FROM --platform=linux/arm64 tomcat:9.0-jdk17-temurin

   # Remove default Tomcat apps (optional)
   RUN rm -rf /usr/local/tomcat/webapps/*

   # Copy WAR file into Tomcat
   COPY target/myapp.war /usr/local/tomcat/webapps/ROOT.war

   # Expose port 8080
   EXPOSE 8080

   # Start Tomcat
   CMD ["catalina.sh", "run"]
   ```

2. **Build the Docker image**:
   ```bash
   docker build -t my-tomcat-app .
   ```

3. **Run locally**:
   ```bash
   docker run -d -p 8080:8080 --name tomcat-app my-tomcat-app
   ```
   - Verify: `http://localhost:8080`.

---

## **3. Deploy to Kubernetes**
### **Prerequisite**:
- Kubernetes cluster (e.g., Docker Desktop Kubernetes, Minikube, or cloud-based like EKS/GKE).
- `kubectl` installed (`brew install kubectl`).

#### **Steps**:
1. **Create a Kubernetes Deployment (`deployment.yaml`)**
   ```yaml
   apiVersion: apps/v1
   kind: Deployment
   metadata:
     name: tomcat-deployment
   spec:
     replicas: 1
     selector:
       matchLabels:
         app: tomcat
     template:
       metadata:
         labels:
           app: tomcat
       spec:
         containers:
         - name: tomcat
           image: my-tomcat-app  # Your Docker image
           ports:
           - containerPort: 8080
   ```

2. **Create a Service (`service.yaml`)**
   ```yaml
   apiVersion: v1
   kind: Service
   metadata:
     name: tomcat-service
   spec:
     selector:
       app: tomcat
     ports:
       - protocol: TCP
         port: 80
         targetPort: 8080
     type: LoadBalancer  # For external access (NodePort for local testing)
   ```

3. **Apply Kubernetes Configs**:
   ```bash
   kubectl apply -f deployment.yaml
   kubectl apply -f service.yaml
   ```

4. **Verify Deployment**:
   ```bash
   kubectl get pods           # Check if the pod is running
   kubectl get services       # Get the external IP (if LoadBalancer)
   ```
   - For Docker Desktop/Minikube:
     ```bash
     minikube service tomcat-service  # Opens Tomcat in browser
     ```

---

### **Troubleshooting**
#### **1. ARM64 Compatibility Issues**
- Ensure base images support ARM64 (e.g., `tomcat:9.0-jdk17-temurin`).
- Use `--platform=linux/arm64` in `Dockerfile`.

#### **2. Kubernetes Pod Fails to Start**
- Check logs:
  ```bash
  kubectl logs <pod-name>
  ```
- Ensure Docker image is pushed to a registry (or use `imagePullPolicy: Never` for local images).

#### **3. Persistent Storage (Optional)**
To persist Tomcat data (e.g., logs, uploaded files), add a `PersistentVolumeClaim` to `deployment.yaml`.


### **Final Notes**
✅ **Maven WAR**: `mvn clean package` → `target/myapp.war`.  
✅ **Docker**: ARM64-compatible image → `docker run -p 8080:8080`.  
✅ **Kubernetes**: `kubectl apply -f deployment.yaml,service.yaml`.

------------------------------------------------------------------


