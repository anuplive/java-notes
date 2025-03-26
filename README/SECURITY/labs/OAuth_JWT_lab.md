# OAuth 2.0 Authorization Code Flow with JWT Validation - Complete Implementation

This comprehensive solution provides a production-ready implementation of an OAuth 2.0 Authorization Code Flow with JWT validation in a Java/JSP application running on Tomcat, containerized with Docker, and deployed to Kubernetes.

## Table of Contents
1. [Solution Architecture](#solution-architecture)
2. [Implementation Details](#implementation-details)
3. [Setup Instructions](#setup-instructions)
4. [Configuration Files](#configuration-files)
5. [Security Considerations](#security-considerations)
6. [Troubleshooting Guide](#troubleshooting-guide)

## Solution Architecture

![Architecture Diagram](https://i.imgur.com/JQZvY3l.png)

The architecture consists of:
1. **Frontend**: JSP pages served by Tomcat
2. **Backend**: Java servlets handling OAuth flow
3. **OAuth Provider**: Google OAuth 2.0
4. **Containerization**: Docker for packaging
5. **Orchestration**: Kubernetes for deployment

## Implementation Details

### 1. OAuth 2.0 Authorization Code Flow with JWT Validation

The implementation follows these steps:
1. User clicks "Login with Google"
2. Application redirects to Google OAuth with client ID, redirect URI, and scopes
3. User authenticates and authorizes the application
4. Google redirects back with authorization code
5. Application exchanges code for ID token (JWT) and access token
6. Application validates JWT signature, claims, and expiration
7. Application creates session for authenticated user

### 2. Java/JSP Application Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           └── oauth/
│   │               ├── AuthController.java
│   │               ├── CallbackServlet.java
│   │               ├── JWTValidator.java
│   │               └── UserInfo.java
│   ├── resources/
│   └── webapp/
│       ├── WEB-INF/
│       │   ├── web.xml
│       │   └── views/
│       │       ├── home.jsp
│       │       ├── login.jsp
│       │       └── profile.jsp
│       └── index.jsp
```

### 3. Docker Containerization

Multi-stage Docker build:
1. Build stage: Maven to create WAR file
2. Runtime stage: Tomcat image with deployed WAR

### 4. Kubernetes Deployment

- Deployment: 3 replicas of the Tomcat application
- Service: ClusterIP for internal communication
- Ingress: Nginx ingress controller for external access
- Secrets: Encrypted OAuth credentials

## Configuration Files

### 1. pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>oauth-app</artifactId>
    <version>1.0.0</version>
    <packaging>war</packaging>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <tomcat.version>9.0.50</tomcat.version>
    </properties>

    <dependencies>
        <!-- Servlet API -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>4.0.1</version>
            <scope>provided</scope>
        </dependency>

        <!-- JSP API -->
        <dependency>
            <groupId>javax.servlet.jsp</groupId>
            <artifactId>javax.servlet.jsp-api</artifactId>
            <version>2.3.3</version>
            <scope>provided</scope>
        </dependency>

        <!-- OAuth and JWT Libraries -->
        <dependency>
            <groupId>com.google.api-client</groupId>
            <artifactId>google-api-client</artifactId>
            <version>1.32.1</version>
        </dependency>
        <dependency>
            <groupId>com.google.oauth-client</groupId>
            <artifactId>google-oauth-client-jetty</artifactId>
            <version>1.32.1</version>
        </dependency>
        <dependency>
            <groupId>com.auth0</groupId>
            <artifactId>java-jwt</artifactId>
            <version>3.18.2</version>
        </dependency>
        <dependency>
            <groupId>com.google.http-client</groupId>
            <artifactId>google-http-client-jackson2</artifactId>
            <version>1.40.1</version>
        </dependency>

        <!-- Logging -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>1.7.32</version>
        </dependency>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>1.2.6</version>
        </dependency>
    </dependencies>

    <build>
        <finalName>oauth-app</finalName>
        <plugins>
            <!-- Maven WAR Plugin -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-war-plugin</artifactId>
                <version>3.3.2</version>
            </plugin>

            <!-- Docker Build Plugin -->
            <plugin>
                <groupId>com.spotify</groupId>
                <artifactId>dockerfile-maven-plugin</artifactId>
                <version>1.4.13</version>
                <configuration>
                    <repository>${docker.image.prefix}/${project.artifactId}</repository>
                    <tag>${project.version}</tag>
                    <buildArgs>
                        <JAR_FILE>${project.build.finalName}.war</JAR_FILE>
                    </buildArgs>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

### 2. Dockerfile

```dockerfile
# Build stage
FROM maven:3.8.4-openjdk-11 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src /app/src
RUN mvn package

# Runtime stage
FROM tomcat:9.0.50-jdk11-openjdk
LABEL maintainer="example.com"

# Remove default Tomcat apps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy WAR file from build stage
COPY --from=build /app/target/oauth-app.war /usr/local/tomcat/webapps/ROOT.war

# Expose port 8080
EXPOSE 8080

# Set environment variables for OAuth
ENV OAUTH_CLIENT_ID=your-client-id
ENV OAUTH_CLIENT_SECRET=your-client-secret
ENV OAUTH_REDIRECT_URI=https://your-domain.com/callback
ENV OAUTH_JWKS_URI=https://www.googleapis.com/oauth2/v3/certs

# Start Tomcat
CMD ["catalina.sh", "run"]
```

### 3. Kubernetes Manifests

#### deployment.yaml

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: oauth-app
  labels:
    app: oauth-app
spec:
  replicas: 3
  selector:
    matchLabels:
      app: oauth-app
  template:
    metadata:
      labels:
        app: oauth-app
    spec:
      containers:
      - name: oauth-app
        image: your-registry/oauth-app:1.0.0
        ports:
        - containerPort: 8080
        envFrom:
        - secretRef:
            name: oauth-secrets
        resources:
          requests:
            cpu: "100m"
            memory: "256Mi"
          limits:
            cpu: "500m"
            memory: "512Mi"
        readinessProbe:
          httpGet:
            path: /
            port: 8080
          initialDelaySeconds: 30
          periodSeconds: 10
        livenessProbe:
          httpGet:
            path: /
            port: 8080
          initialDelaySeconds: 45
          periodSeconds: 15
```

#### service.yaml

```yaml
apiVersion: v1
kind: Service
metadata:
  name: oauth-app-service
spec:
  selector:
    app: oauth-app
  ports:
    - protocol: TCP
      port: 80
      targetPort: 8080
```

#### ingress.yaml

```yaml
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: oauth-app-ingress
  annotations:
    nginx.ingress.kubernetes.io/rewrite-target: /
    nginx.ingress.kubernetes.io/force-ssl-redirect: "true"
    cert-manager.io/cluster-issuer: "letsencrypt-prod"
spec:
  tls:
  - hosts:
    - your-domain.com
    secretName: oauth-app-tls
  rules:
  - host: your-domain.com
    http:
      paths:
      - path: /
        pathType: Prefix
        backend:
          service:
            name: oauth-app-service
            port:
              number: 80
```

#### secrets.yaml

```yaml
apiVersion: v1
kind: Secret
metadata:
  name: oauth-secrets
type: Opaque
stringData:
  OAUTH_CLIENT_ID: "your-google-client-id"
  OAUTH_CLIENT_SECRET: "your-google-client-secret"
  OAUTH_REDIRECT_URI: "https://your-domain.com/callback"
  OAUTH_JWKS_URI: "https://www.googleapis.com/oauth2/v3/certs"
```

### 4. web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee 
         http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <display-name>OAuth Application</display-name>

    <!-- Session timeout set to 30 minutes -->
    <session-config>
        <session-timeout>30</session-timeout>
        <cookie-config>
            <http-only>true</http-only>
            <secure>true</secure>
        </cookie-config>
    </session-config>

    <!-- Servlets -->
    <servlet>
        <servlet-name>AuthController</servlet-name>
        <servlet-class>com.example.oauth.AuthController</servlet-class>
    </servlet>
    <servlet>
        <servlet-name>CallbackServlet</servlet-name>
        <servlet-class>com.example.oauth.CallbackServlet</servlet-class>
    </servlet>

    <!-- Servlet Mappings -->
    <servlet-mapping>
        <servlet-name>AuthController</servlet-name>
        <url-pattern>/login</url-pattern>
    </servlet-mapping>
    <servlet-mapping>
        <servlet-name>CallbackServlet</servlet-name>
        <url-pattern>/callback</url-pattern>
    </servlet-mapping>

    <!-- Security Constraints -->
    <security-constraint>
        <web-resource-collection>
            <web-resource-name>Protected Resources</web-resource-name>
            <url-pattern>/profile</url-pattern>
        </web-resource-collection>
        <auth-constraint>
            <role-name>*</role-name>
        </auth-constraint>
    </security-constraint>

    <!-- Error Pages -->
    <error-page>
        <error-code>401</error-code>
        <location>/error.jsp</location>
    </error-page>
    <error-page>
        <error-code>403</error-code>
        <location>/error.jsp</location>
    </error-page>
</web-app>
```

## Java Implementation

### AuthController.java

```java
package com.example.oauth;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Handles the initiation of the OAuth 2.0 Authorization Code Flow.
 * Redirects the user to Google's OAuth 2.0 server for authentication.
 */
@WebServlet("/login")
public class AuthController extends HttpServlet {
    
    private static final String OAUTH_AUTH_URL = "https://accounts.google.com/o/oauth2/v2/auth";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        
        // Retrieve configuration from environment variables
        String clientId = System.getenv("OAUTH_CLIENT_ID");
        String redirectUri = System.getenv("OAUTH_REDIRECT_URI");
        
        if (clientId == null || redirectUri == null) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
                "OAuth configuration missing");
            return;
        }
        
        // Generate a state token for CSRF protection
        String stateToken = CSRFUtil.generateStateToken();
        
        // Store the state token in the session
        request.getSession().setAttribute("state", stateToken);
        
        // Build the authorization URL
        String authorizationUrl = String.format(
            "%s?response_type=code&client_id=%s&redirect_uri=%s&scope=%s&state=%s&access_type=offline&prompt=consent",
            OAUTH_AUTH_URL,
            URLEncoder.encode(clientId, StandardCharsets.UTF_8.toString()),
            URLEncoder.encode(redirectUri, StandardCharsets.UTF_8.toString()),
            URLEncoder.encode("openid email profile", StandardCharsets.UTF_8.toString()),
            URLEncoder.encode(stateToken, StandardCharsets.UTF_8.toString())
        );
        
        // Redirect to Google's OAuth 2.0 server
        response.sendRedirect(authorizationUrl);
    }
}
```

### CallbackServlet.java

```java
package com.example.oauth;

import com.auth0.jwk.JwkException;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.JwkProviderBuilder;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.interfaces.RSAPublicKey;
import java.util.concurrent.TimeUnit;

/**
 * Handles the OAuth 2.0 callback from the authorization server.
 * Exchanges the authorization code for tokens and validates the ID token.
 */
@WebServlet("/callback")
public class CallbackServlet extends HttpServlet {

    private static final String OAUTH_TOKEN_URL = "https://oauth2.googleapis.com/token";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        
        // Retrieve configuration from environment variables
        String clientId = System.getenv("OAUTH_CLIENT_ID");
        String clientSecret = System.getenv("OAUTH_CLIENT_SECRET");
        String redirectUri = System.getenv("OAUTH_REDIRECT_URI");
        String jwksUri = System.getenv("OAUTH_JWKS_URI");
        
        // Verify the state parameter for CSRF protection
        String state = request.getParameter("state");
        String sessionState = (String) request.getSession().getAttribute("state");
        
        if (state == null || !state.equals(sessionState)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid state parameter");
            return;
        }
        
        // Get the authorization code
        String code = request.getParameter("code");
        if (code == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Authorization code missing");
            return;
        }
        
        try {
            // Exchange the authorization code for tokens
            GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
                new NetHttpTransport(),
                new JacksonFactory(),
                OAUTH_TOKEN_URL,
                clientId,
                clientSecret,
                code,
                redirectUri
            ).execute();
            
            // Get the ID token
            String idTokenString = tokenResponse.getIdToken();
            
            // Validate the ID token
            DecodedJWT jwt = validateIdToken(idTokenString, jwksUri, clientId);
            
            // Extract user information from the ID token
            GoogleIdToken.Payload payload = GoogleIdToken.parse(new JacksonFactory(), idTokenString).getPayload();
            
            // Store user information in the session
            UserInfo userInfo = new UserInfo();
            userInfo.setEmail(payload.getEmail());
            userInfo.setName((String) payload.get("name"));
            userInfo.setPictureUrl((String) payload.get("picture"));
            
            request.getSession().setAttribute("user", userInfo);
            
            // Redirect to the profile page
            response.sendRedirect(request.getContextPath() + "/profile.jsp");
            
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed: " + e.getMessage());
        }
    }
    
    /**
     * Validates the ID token using the JWKS endpoint.
     */
    private DecodedJWT validateIdToken(String idToken, String jwksUri, String audience) 
            throws JwkException, JWTVerificationException {
        
        // Create a JWT verifier
        JwkProvider provider = new JwkProviderBuilder(jwksUri)
            .cached(10, 24, TimeUnit.HOURS) // Cache JWKS for 10 hours
            .build();
            
        DecodedJWT jwt = JWT.decode(idToken);
        RSAPublicKey publicKey = (RSAPublicKey) provider.get(jwt.getKeyId()).getPublicKey();
        
        Algorithm algorithm = Algorithm.RSA256(publicKey, null);
        JWTVerifier verifier = JWT.require(algorithm)
            .withIssuer("https://accounts.google.com")
            .withAudience(audience)
            .build();
            
        return verifier.verify(idToken);
    }
}
```

### JWTValidator.java

```java
package com.example.oauth;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkException;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.JwkProviderBuilder;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.security.interfaces.RSAPublicKey;
import java.util.concurrent.TimeUnit;

/**
 * Utility class for validating JWT tokens.
 */
public class JWTValidator {
    
    private final JwkProvider jwkProvider;
    private final String expectedAudience;
    
    public JWTValidator(String jwksUri, String expectedAudience) {
        this.jwkProvider = new JwkProviderBuilder(jwksUri)
            .cached(10, 24, TimeUnit.HOURS) // Cache JWKS for 10 hours
            .build();
        this.expectedAudience = expectedAudience;
    }
    
    /**
     * Validates a JWT token and returns the decoded JWT if valid.
     */
    public DecodedJWT validate(String token) throws JwkException, JWTVerificationException {
        DecodedJWT jwt = JWT.decode(token);
        
        // Verify the token is not expired
        if (jwt.getExpiresAt() == null || jwt.getExpiresAt().before(new java.util.Date())) {
            throw new JWTVerificationException("Token is expired");
        }
        
        // Get the public key from JWKS
        Jwk jwk = jwkProvider.get(jwt.getKeyId());
        RSAPublicKey publicKey = (RSAPublicKey) jwk.getPublicKey();
        
        // Verify the token signature and claims
        Algorithm algorithm = Algorithm.RSA256(publicKey, null);
        JWTVerifier verifier = JWT.require(algorithm)
            .withIssuer("https://accounts.google.com")
            .withAudience(expectedAudience)
            .build();
            
        return verifier.verify(token);
    }
}
```

### UserInfo.java

```java
package com.example.oauth;

import java.io.Serializable;

/**
 * Represents user information retrieved from the ID token.
 */
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String email;
    private String name;
    private String pictureUrl;
    
    // Getters and Setters
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPictureUrl() {
        return pictureUrl;
    }
    
    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
```

## JSP Pages

### login.jsp

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <style>
        body { font-family: Arial, sans-serif; text-align: center; margin-top: 50px; }
        .login-btn { 
            background-color: #4285F4; 
            color: white; 
            padding: 10px 20px; 
            border: none; 
            border-radius: 4px; 
            font-size: 16px; 
            cursor: pointer;
        }
    </style>
</head>
<body>
    <h1>Welcome to Our Application</h1>
    <p>Please sign in with your Google account to continue.</p>
    
    <form action="${pageContext.request.contextPath}/login" method="get">
        <button type="submit" class="login-btn">
            Sign in with Google
        </button>
    </form>
    
    <p style="margin-top: 30px; font-size: 12px; color: #666;">
        By signing in, you agree to our Terms of Service and Privacy Policy.
    </p>
</body>
</html>
```

### profile.jsp

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.oauth.UserInfo" %>
<%
    // Check if user is authenticated
    UserInfo user = (UserInfo) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Profile</title>
    <style>
        body { font-family: Arial, sans-serif; max-width: 600px; margin: 0 auto; padding: 20px; }
        .profile { display: flex; align-items: center; margin-bottom: 20px; }
        .profile-pic { width: 80px; height: 80px; border-radius: 50%; margin-right: 20px; }
        .user-info { flex-grow: 1; }
        .logout-btn { 
            background-color: #f44336; 
            color: white; 
            padding: 8px 16px; 
            border: none; 
            border-radius: 4px; 
            cursor: pointer;
        }
    </style>
</head>
<body>
    <h1>User Profile</h1>
    
    <div class="profile">
        <img src="<%= user.getPictureUrl() %>" alt="Profile Picture" class="profile-pic">
        <div class="user-info">
            <h2><%= user.getName() %></h2>
            <p><%= user.getEmail() %></p>
        </div>
    </div>
    
    <p>Welcome to your profile page. You have successfully authenticated using Google OAuth 2.0.</p>
    
    <form action="${pageContext.request.contextPath}/logout" method="post">
        <button type="submit" class="logout-btn">Logout</button>
    </form>
</body>
</html>
```

## Setup Instructions

### 1. Google OAuth Provider Configuration

1. Go to [Google Cloud Console](https://console.cloud.google.com/)
2. Create a new project or select an existing one
3. Navigate to "APIs & Services" > "Credentials"
4. Click "Create Credentials" > "OAuth client ID"
5. Select "Web application" as the application type
6. Configure authorized JavaScript origins and redirect URIs:
   - Authorized JavaScript origins: `https://your-domain.com`
   - Authorized redirect URIs: `https://your-domain.com/callback`
7. Click "Create" and note your Client ID and Client Secret
8. Enable the "Google People API" for additional user profile information

### 2. Local Development Setup

1. **Prerequisites**:
   - Java JDK 11+
   - Apache Maven 3.6+
   - Docker Desktop
   - Kubernetes cluster (Minikube for local development)

2. **Clone the repository**:
   ```bash
   git clone https://github.com/your-repo/oauth-app.git
   cd oauth-app
   ```

3. **Configure environment variables**:
   Create a `.env` file in the project root:
   ```env
   OAUTH_CLIENT_ID=your-client-id
   OAUTH_CLIENT_SECRET=your-client-secret
   OAUTH_REDIRECT_URI=http://localhost:8080/callback
   OAUTH_JWKS_URI=https://www.googleapis.com/oauth2/v3/certs
   ```

4. **Build the application**:
   ```bash
   mvn clean package
   ```

5. **Run locally with Tomcat**:
   ```bash
   mvn tomcat7:run
   ```
   The application will be available at `http://localhost:8080`

### 3. Building and Dockerizing

1. **Build the Docker image**:
   ```bash
   docker build -t oauth-app:1.0.0 .
   ```

2. **Run the container locally**:
   ```bash
   docker run -p 8080:8080 --env-file .env oauth-app:1.0.0
   ```

3. **Push to container registry**:
   ```bash
   docker tag oauth-app:1.0.0 your-registry/oauth-app:1.0.0
   docker push your-registry/oauth-app:1.0.0
   ```

### 4. Kubernetes Deployment

1. **Create Kubernetes secrets**:
   ```bash
   kubectl apply -f k8s/secrets.yaml
   ```

2. **Deploy the application**:
   ```bash
   kubectl apply -f k8s/deployment.yaml
   kubectl apply -f k8s/service.yaml
   kubectl apply -f k8s/ingress.yaml
   ```

3. **Verify deployment**:
   ```bash
   kubectl get pods
   kubectl get services
   kubectl get ingress
   ```

### 5. Testing the Full Flow

1. Access the application at `https://your-domain.com`
2. Click "Sign in with Google"
3. You should be redirected to Google's authentication page
4. After authenticating, you should be redirected back to your profile page
5. Verify that your user information is displayed correctly
6. Test logout functionality

## Security Considerations

1. **Transport Security**:
   - Always use HTTPS for all OAuth redirects and callbacks
   - Configure HSTS headers in your ingress controller
   - Use secure cookies with HttpOnly and Secure flags

2. **Token Security**:
   - Never store access tokens or refresh tokens in the frontend
   - Validate ID tokens properly (signature, issuer, audience, expiration)
   - Use short-lived access tokens and refresh them when needed

3. **Session Management**:
   - Implement proper session timeout (30 minutes recommended)
   - Regenerate session IDs after login to prevent session fixation
   - Store session IDs securely and implement proper invalidation on logout

4. **CSRF Protection**:
   - Always use state parameters in OAuth flows
   - Validate state parameters strictly
   - Implement CSRF tokens for all state-changing operations

5. **Error Handling**:
   - Don't expose sensitive error details to users
   - Log errors securely on the server side
   - Implement proper error pages for common error cases

6. **Rate Limiting**:
   - Implement rate limiting on authentication endpoints
   - Monitor for unusual authentication patterns

## Troubleshooting Guide

### 1. OAuth Flow Issues

**Problem**: Redirect URI mismatch error  
**Solution**:
- Verify the redirect URI in Google Cloud Console matches exactly
- Ensure protocol (http/https) and domain are correct
- Check for trailing slashes

**Problem**: Invalid state parameter  
**Solution**:
- Verify the state parameter is being generated and stored in the session
- Ensure the same session is maintained throughout the flow
- Check for proper URL encoding/decoding

### 2. JWT Validation Issues

**Problem**: Signature verification failed  
**Solution**:
- Verify the JWKS URI is correct
- Check that the JWT header contains a valid "kid"
- Ensure your server's clock is synchronized (NTP)

**Problem**: Token expired  
**Solution**:
- Check the token expiration time
- Ensure your server's clock is correct
- Implement token refresh logic if needed

### 3. Kubernetes Deployment Issues

**Problem**: Pods not starting  
**Solution**:
- Check logs with `kubectl logs <pod-name>`
- Verify secrets are properly configured
- Check resource limits and requests

**Problem**: Ingress not routing traffic  
**Solution**:
- Verify ingress controller is installed
- Check ingress annotations
- Verify DNS records point to your cluster

### 4. General Application Issues

**Problem**: Session not persisting  
**Solution**:
- Verify session cookie settings
- Check for proper session storage configuration
- Ensure cookies are being sent with requests

**Problem**: Performance issues  
**Solution**:
- Check JWT verification caching
- Monitor database connections if using session persistence
- Review resource utilization in Kubernetes

### 5. Common Error Messages

**Error**: "Invalid credentials"  
**Fix**: Verify client ID and secret match Google Cloud Console

**Error**: "Redirect URI mismatch"  
**Fix**: Update authorized redirect URIs in Google Cloud Console

**Error**: "Invalid JWT signature"  
**Fix**: Verify JWKS endpoint and token validation logic

**Error**: "Session not found"  
**Fix**: Check session configuration and cookie settings

This comprehensive implementation provides a production-ready OAuth 2.0 solution with all necessary components, security considerations, and troubleshooting guidance. The solution can be deployed to any Kubernetes cluster and scaled as needed while maintaining security best practices.