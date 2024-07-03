
### Bean Scope
[Back to Top](#Table-of-contents)

- **Types of bean scopes:**
  - **singleton**
  - **prototype**
  - **request**
  - **session**
  - **application**
  - **websocket**

### Singleton scope
[Back to Top](#Table-of-contents)

- **Default Scope:** The default scope of a bean is **singleton**, where only one instance of the bean is created and cached in memory.
- **Purpose:** Minimizes the number of objects created.
- **Creation:** Beans are created when the context is loaded and cached in memory.
- **Scope:** In Spring, it means one bean per application context.

### Prototype scope
[Back to Top](#Table-of-contents)

- **Definition:** A prototype bean is not created until it is requested from Spring.
```java
//**Option 1:**
@Scope("prototype")

//**Option 2:**
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)

```
