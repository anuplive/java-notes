### **Java Collection Framework: Comprehensive Lab Assignment**

---

### **Main Topic:** [Java Collection Framework](w)
### **Should Include:** [Collection](w), [Set](w), [List](w), [Queue](w), [Map](w), [PriorityQueue](w), [SortedSet](w), [SortedMap](w), [BlockingQueue](w), [HashSet](w), [TreeSet](w), [ConcurrentHashMap](w), [ArrayList](w), [LinkedList](w), [HashMap](w), [PriorityQueue](w), [Comparable](w), [Comparator](w), [hashCode](w), [equals](w)

---

### **Objective:**

In this lab, you will implement and use various Java Collection Framework classes to create a **Library System**. The assignment will guide you through the process of building a practical system using **List**, **Set**, **Queue**, **Map**, **PriorityQueue**, **SortedSet**, **BlockingQueue**, **ConcurrentMap**, and implementing concepts like **hashCode()**, **equals()**, **Comparable**, and **Comparator**.

### **Project Overview:**

You will progressively build a **Library Management System**, a **Bookstore Inventory System**, and a **Reservation System** that incorporates the following features:
- Adding/removing books and patrons
- Handling book reservations with **PriorityQueue**
- Managing bookstore inventory with **HashMap**, **ConcurrentHashMap**, and **BlockingQueue**
- Categorizing books with **SortedSet** and **SortedMap**

Each stage will increase in complexity and will involve using different collections to solve real-world problems.

---

### **Lab Stages Overview:**

---

### **Stage 1: Building a Library System (Basic Collections)**

**Objective:** Build a simple **Library System** where books and patrons can be added, removed, and listed. Use **List** to store books and **Set** for patrons.

#### **Classes to Implement:**
1. **Book Class**
   - **Fields:**
     - `String title`
     - `String author`
     - `String isbn`
   - **Methods:**
     - `String getTitle()`
     - `String getAuthor()`
     - `String getIsbn()`
     - `boolean equals(Object obj)` (Override `equals` for Book comparison)
     - `int hashCode()` (Override `hashCode` based on ISBN)

2. **Patron Class**
   - **Fields:**
     - `String name`
     - `String patronId`
   - **Methods:**
     - `String getName()`
     - `String getPatronId()`
     - `boolean equals(Object obj)` (Override `equals` for Patron comparison)
     - `int hashCode()` (Override `hashCode` based on patronId)

3. **LibrarySystem Class**
   - **Fields:**
     - `List<Book> books = new ArrayList<>();`
     - `Set<Patron> patrons = new HashSet<>();`
   - **Methods:**
     - `void addBook(Book book)`
     - `void removeBook(Book book)`
     - `void listBooks()`
     - `void addPatron(Patron patron)`
     - `void removePatron(Patron patron)`
     - `void listPatrons()`

---

### **Stage 2: Managing Book Reservations (Queues and Sorting)**

**Objective:** Implement a reservation system where patrons can reserve books, and the system processes reservations in the order they are made using **PriorityQueue**.

#### **Classes to Implement:**
1. **BookReservation Class**
   - **Fields:**
     - `Patron patron`
     - `Book book`
     - `LocalDateTime reservationTime`
   - **Methods:**
     - `Patron getPatron()`
     - `Book getBook()`
     - `LocalDateTime getReservationTime()`

2. **LibraryReservationSystem Class**
   - **Fields:**
     - `Queue<BookReservation> reservationQueue = new PriorityQueue<>(new ReservationTimeComparator());`
   - **Methods:**
     - `void reserveBook(Book book, Patron patron)`
     - `void processReservation()`
     - `void listReservations()`

**Comparator for PriorityQueue (based on reservation time):**
```java
class ReservationTimeComparator implements Comparator<BookReservation> {
    @Override
    public int compare(BookReservation r1, BookReservation r2) {
        return r1.getReservationTime().compareTo(r2.getReservationTime());
    }
}
```

---

### **Stage 3: Managing a Bookstore Inventory (Maps and Concurrency)**

**Objective:** Implement a **Bookstore Inventory System** where books are stored and retrieved using **HashMap**. Also, simulate thread-safe operations using **ConcurrentMap**.

#### **Classes to Implement:**
1. **Inventory Class**
   - **Fields:**
     - `Map<String, Book> bookInventory = new HashMap<>();`
   - **Methods:**
     - `void addBook(Book book)`
     - `void removeBook(Book book)`
     - `Book getBookByIsbn(String isbn)`
     - `void listBooks()`

2. **ConcurrentInventory Class**
   - **Fields:**
     - `ConcurrentMap<String, Book> concurrentInventory = new ConcurrentHashMap<>();`
   - **Methods:**
     - `void addBook(String isbn, Book book)`
     - `void removeBook(String isbn)`
     - `boolean isBookAvailable(String isbn)`

---

### **Stage 4: Advanced Bookstore System (Advanced Collections and Concurrency)**

**Objective:** Build a **Bookstore System** with advanced features such as **book categories** using **SortedSet**, manage restocking using **BlockingQueue**, and handle concurrent access to the inventory using **ConcurrentMap**.

#### **Classes to Implement:**
1. **BookCategory Class**
   - **Fields:**
     - `String categoryName`
     - `SortedSet<Book> booksInCategory = new TreeSet<>(new BookTitleComparator());`
   - **Methods:**
     - `void addBook(Book book)`
     - `void removeBook(Book book)`
     - `void listBooks()`

2. **RestockQueue Class**
   - **Fields:**
     - `BlockingQueue<Book> restockQueue = new ArrayBlockingQueue<>(100);`
   - **Methods:**
     - `void restockBook(Book book)`
     - `Book takeRestock()`
     - `int getQueueSize()`

3. **BookTitleComparator Class**
   - **Comparator for Sorting Books by Title:**
   ```java
   class BookTitleComparator implements Comparator<Book> {
       @Override
       public int compare(Book b1, Book b2) {
           return b1.getTitle().compareTo(b2.getTitle());
       }
   }
   ```

---

### **Expected Method Signatures:**

```java
class Book {
    String title;
    String author;
    String isbn;

    public String getTitle() {...}
    public String getAuthor() {...}
    public String getIsbn() {...}

    @Override
    public boolean equals(Object obj) {...}

    @Override
    public int hashCode() {...}
}

class Patron {
    String name;
    String patronId;

    public String getName() {...}
    public String getPatronId() {...}

    @Override
    public boolean equals(Object obj) {...}

    @Override
    public int hashCode() {...}
}

class LibrarySystem {
    List<Book> books = new ArrayList<>();
    Set<Patron> patrons = new HashSet<>();

    public void addBook(Book book) {...}
    public void removeBook(Book book) {...}
    public void listBooks() {...}

    public void addPatron(Patron patron) {...}
    public void removePatron(Patron patron) {...}
    public void listPatrons() {...}
}

class BookReservation {
    Patron patron;
    Book book;
    LocalDateTime reservationTime;

    public Patron getPatron() {...}
    public Book getBook() {...}
    public LocalDateTime getReservationTime() {...}
}

class LibraryReservationSystem {
    Queue<BookReservation> reservationQueue = new PriorityQueue<>(new ReservationTimeComparator());

    public void reserveBook(Book book, Patron patron) {...}
    public void processReservation() {...}
    public void listReservations() {...}
}

class ReservationTimeComparator implements Comparator<BookReservation> {
    @Override
    public int compare(BookReservation r1, BookReservation r2) {
        return r1.getReservationTime().compareTo(r2.getReservationTime());
    }
}

class Inventory {
    Map<String, Book> bookInventory = new HashMap<>();

    public void addBook(Book book) {...}
    public void removeBook(Book book) {...}
    public Book getBookByIsbn(String isbn) {...}
    public void listBooks() {...}
}

class ConcurrentInventory {
    ConcurrentMap<String, Book> concurrentInventory = new ConcurrentHashMap<>();

    public void addBook(String isbn, Book book) {...}
    public void removeBook(String isbn) {...}
    public boolean isBookAvailable(String isbn) {...}
}

class BookCategory {
    String categoryName;
    SortedSet<Book> booksInCategory = new TreeSet<>(new BookTitleComparator());

    public void addBook(Book book) {...}
    public void removeBook(Book book) {...}
    public void listBooks() {...}
}

class RestockQueue {
    BlockingQueue<Book> restockQueue = new ArrayBlockingQueue<>(100);

    public void restockBook(Book book) {...}
    public Book takeRestock() {...}
    public int getQueueSize() {...}
}

class BookTitleComparator implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        return b1.getTitle().compareTo(b2.getTitle());
    }
}
```

---

### **Evaluation Criteria:**
- Correct usage of **Collection Framework** classes: [Set](w), [List](w), [Queue](w), [Map](w), [SortedSet](w), [SortedMap](w), [ConcurrentMap](w), [BlockingQueue](w)
- Accurate implementation of **hashCode()**, **equals()**, **Comparable**, and **Comparator** where necessary
- Appropriate use of **concurrent collections** to handle thread-safe operations
- Ability to handle **priority-based operations** with [




com.library
│── 📂 model                # Data model classes (POJOs)
│   ├── Book.java
│   ├── Patron.java
│   ├── BookReservation.java
│── 📂 service              # Business logic & core functionality
│   ├── LibraryService.java
│   ├── ReservationService.java
│   ├── InventoryService.java
│   ├── BookstoreService.java
│── 📂 util                 # Utility classes (Comparators, Validators, etc.)
│   ├── ReservationTimeComparator.java
│   ├── BookTitleComparator.java
│── 📂 exception            # Custom exceptions
│   ├── BookNotFoundException.java
│   ├── PatronNotFoundException.java
│── 📂 repository           # Data persistence and storage
│   ├── BookRepository.java
│   ├── PatronRepository.java
│── 📂 concurrency          # Thread-safe operations and concurrent collections
│   ├── ConcurrentInventory.java
│   ├── RestockQueue.java
│── 📂 main                 # Main application entry point
│   ├── LibraryApplication.java
