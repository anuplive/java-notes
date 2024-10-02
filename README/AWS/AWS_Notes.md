***
### S3 (Simple Storage Service)
[Back to Top](#Table-of-contents)

- **Definition:** Amazon S3 (Simple Storage Service) is an **object storage service** that provides scalability, data availability, security, and performance.
- **Data Model:** S3 stores data as objects in **buckets**. Each object consists of data, metadata, and a unique identifier (key).
- **Use Cases:** Backup & restore, disaster recovery, data archiving, big data analytics, and content distribution.

***

### S3 Storage Classes
[Back to Top](#Table-of-contents)

- **S3 Standard:** Default storage class for frequently accessed data. Offers **low latency** and **high throughput**.
- **S3 Intelligent-Tiering:** Automatically moves data between two access tiers when access patterns change, optimizing **costs**.
- **S3 Standard-IA (Infrequent Access):** For data that is less frequently accessed but requires **fast retrieval**. **Lower cost** but **higher retrieval fee**.
- **S3 One Zone-IA:** Similar to Standard-IA but stored in a **single Availability Zone**, at a lower cost.
- **S3 Glacier:** For long-term data archiving with **retrieval times ranging from minutes to hours**.
- **S3 Glacier Deep Archive:** Lowest-cost storage option, used for **data retrieval that can wait up to 12 hours**.

***

### Securing S3 Buckets
[Back to Top](#Table-of-contents)

- **Bucket Policies:** Define **who can access** the S3 bucket and under what conditions. It is a resource-based policy.
- **Bucket Encryption:** Enable **server-side encryption (SSE)** or **client-side encryption** to protect data at rest.
  - **SSE-S3:** Managed by S3.
  - **SSE-KMS:** Uses AWS Key Management Service (KMS) for managing encryption keys.
  - **SSE-C:** Allows the customer to manage their own encryption keys.
- **Access Control Lists (ACLs):** Provide **granular control** over who can read/write to individual S3 objects.
- **MFA Delete:** Adds an additional layer of security by requiring **multi-factor authentication (MFA)** to delete S3 objects.
- **IAM Policies:** Attach **Identity and Access Management (IAM)** policies to control which AWS users can interact with the S3 bucket.
- **Block Public Access:** A global setting to **block public access** to S3 buckets and objects, preventing accidental exposure.

***

### S3 Bucket Policies
[Back to Top](#Table-of-contents)

- **Purpose:** Bucket policies are **JSON-based access control policies** that define permissions for the entire bucket or specific objects.
- **Key Elements:**
  - **Effect:** Set to **Allow** or **Deny** to specify permission behavior.
  - **Principal:** Defines **who** the policy applies to (e.g., specific AWS users, services, or accounts).
  - **Action:** Specifies **what actions** are allowed (e.g., `s3:GetObject`, `s3:PutObject`).
  - **Resource:** Identifies the **bucket or objects** to which the policy applies.
  - **Condition:** (Optional) Defines **conditional requirements** for the policy (e.g., IP address restrictions or specific VPC access).

- **Example Policy:**
    ```json
    {
      "Version": "2012-10-17",
      "Statement": [
        {
          "Effect": "Allow",
          "Principal": "*",
          "Action": "s3:GetObject",
          "Resource": "arn:aws:s3:::example-bucket/*"
        }
      ]
    }
    ```

***
