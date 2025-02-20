I'll help you create a comprehensive solution for fetching files from SharePoint using the REST API in Java.



```java
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.microsoft.aad.msal4j.*;
import java.io.*;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

public class SharePointFileDownloader {
    private final String clientId;
    private final String clientSecret;
    private final String tenantId;
    private final String siteUrl;
    
    public SharePointFileDownloader(String clientId, String clientSecret, String tenantId, String siteUrl) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.tenantId = tenantId;
        this.siteUrl = siteUrl;
    }
    
    private String getAccessToken() throws Exception {
        ConfidentialClientApplication app = ConfidentialClientApplication.builder(
                clientId,
                ClientCredentialFactory.createFromSecret(clientSecret))
                .authority("https://login.microsoftonline.com/" + tenantId)
                .build();

        ClientCredentialParameters parameters = ClientCredentialParameters.builder(
                Collections.singleton("https://graph.microsoft.com/.default"))
                .build();

        CompletableFuture<IAuthenticationResult> future = app.acquireToken(parameters);
        IAuthenticationResult result = future.get();
        
        return result.accessToken();
    }
    
    public byte[] downloadFile(String libraryName, String filePath) throws Exception {
        String accessToken = getAccessToken();
        
        // Construct the file URL
        String fileUrl = String.format("%s/_api/web/GetFileByServerRelativeUrl('/sites/%s/%s/%s')/$value",
                siteUrl, 
                extractSiteName(siteUrl),
                libraryName,
                filePath);
        
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(fileUrl);
        
        // Add required headers
        request.addHeader("Authorization", "Bearer " + accessToken);
        request.addHeader("Accept", "application/json;odata=verbose");
        
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toByteArray(entity);
            }
            throw new IOException("No content received from SharePoint");
        }
    }
    
    private String extractSiteName(String siteUrl) {
        // Extract site name from URL (implement based on your URL structure)
        // Example: https://company.sharepoint.com/sites/MySite -> returns "MySite"
        String[] parts = siteUrl.split("/sites/");
        return parts[1].split("/")[0];
    }
    
    // Example usage
    public static void main(String[] args) {
        try {
            // Load configuration from properties file
            Properties props = new Properties();
            props.load(new FileInputStream("sharepoint-config.properties"));
            
            SharePointFileDownloader downloader = new SharePointFileDownloader(
                props.getProperty("client.id"),
                props.getProperty("client.secret"),
                props.getProperty("tenant.id"),
                props.getProperty("site.url")
            );
            
            // Download file
            byte[] fileContent = downloader.downloadFile(
                "Documents",                // Library name
                "folder/myfile.pdf"         // File path within library
            );
            
            // Save to local file
            try (FileOutputStream fos = new FileOutputStream("downloaded-file.pdf")) {
                fos.write(fileContent);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```

To use this code, you'll need:

1. Azure AD App Registration:
   - Register an app in Azure AD
   - Get the Client ID and Client Secret
   - Configure appropriate API permissions (Microsoft Graph -> Files.Read.All)
   - Get your Tenant ID from Azure AD

2. Dependencies in your pom.xml:
```xml
<dependencies>
    <dependency>
        <groupId>com.microsoft.azure</groupId>
        <artifactId>msal4j</artifactId>
        <version>1.13.8</version>
    </dependency>
    <dependency>
        <groupId>org.apache.httpcomponents</groupId>
        <artifactId>httpclient</artifactId>
        <version>4.5.13</version>
    </dependency>
</dependencies>
```

3. Create a sharepoint-config.properties file:
```properties
client.id=your_client_id
client.secret=your_client_secret
tenant.id=your_tenant_id
site.url=https://your-company.sharepoint.com/sites/your-site
```

Key features of the implementation:

1. Uses MSAL4J for Microsoft authentication
2. Supports downloading files from any SharePoint library
3. Handles authentication token management
4. Uses Apache HttpClient for REST API calls
5. Includes error handling and resource cleanup
6. Configurable through properties file

To fetch a file:
```java
SharePointFileDownloader downloader = new SharePointFileDownloader(clientId, clientSecret, tenantId, siteUrl);
byte[] fileContent = downloader.downloadFile("Documents", "path/to/your/file.pdf");
```

Would you like me to explain any part in more detail or help you modify the code for your specific needs?