# Prompt
Design a Token Bucket Rate Limiter
Problem Statement: Create a rate limiter that allows N requests per second per user/client.
Key Concepts:
Token refill strategy (e.g., fixed window, sliding log).
Thread-safe bucket updates (Atomic operations/ReentrantLock).

Demonstrates all SOLID principles clearly,
Uses Java Collections Framework effectively (e.g., List, Map, Streams),
Includes class diagrams, method signatures, and step-by-step explanations with comments,
Provides example usage and SOLID principle annotations.


# LRU Cache Implementation in Java

Here's a complete implementation of a fixed-size Least Recently Used (LRU) cache that demonstrates SOLID principles and uses efficient data structures (HashMap + Doubly Linked List) for O(1) operations.

## Class Diagram (Text Representation)

```
<<interface>>
Cache<K,V>
+ get(K key): V
+ put(K key, V value): void
+ size(): int

LRUCache<K,V>
- capacity: int
- cacheMap: Map<K, Node<K,V>>
- head: Node<K,V>
- tail: Node<K,V>
+ LRUCache(int capacity)
+ get(K key): V
+ put(K key, V value): void
+ size(): int
- addToFront(Node<K,V> node)
- removeNode(Node<K,V> node)
- evict()

Node<K,V>
- key: K
- value: V
- prev: Node<K,V>
- next: Node<K,V>
+ Node(K key, V value)
```

## Complete Implementation

```java
import java.util.HashMap;
import java.util.Map;

/**
 * Interface defining cache operations
 * Demonstrates: Dependency Inversion Principle (DIP)
 */
public interface Cache<K, V> {
    V get(K key);
    void put(K key, V value);
    int size();
}

/**
 * Node class for doubly linked list
 */
class Node<K, V> {
    K key;
    V value;
    Node<K, V> prev;
    Node<K, V> next;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

/**
 * LRU Cache implementation using HashMap and Doubly Linked List
 * Demonstrates SOLID principles:
 * - Single Responsibility: Manages cache operations only
 * - Open/Closed: Extensible through Cache interface
 * - Liskov Substitution: Can be used wherever Cache is expected
 * - Interface Segregation: Small, focused interface
 * - Dependency Inversion: Depends on Cache abstraction
 */
public class LRUCache<K, V> implements Cache<K, V> {
    private final int capacity;
    private final Map<K, Node<K, V>> cacheMap;
    private Node<K, V> head; // Most recently used
    private Node<K, V> tail; // Least recently used

    /**
     * Initialize LRU cache with positive capacity
     * @param capacity Maximum number of items in cache
     * @throws IllegalArgumentException if capacity <= 0
     */
    public LRUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.capacity = capacity;
        this.cacheMap = new HashMap<>();
        this.head = null;
        this.tail = null;
    }

    /**
     * Get value by key and update access order
     * @param key Key to look up
     * @return Value if key exists, null otherwise
     * Time complexity: O(1)
     */
    @Override
    public V get(K key) {
        if (!cacheMap.containsKey(key)) {
            return null;
        }
        
        Node<K, V> node = cacheMap.get(key);
        removeNode(node);     // Remove from current position
        addToFront(node);     // Move to head (most recently used)
        
        return node.value;
    }

    /**
     * Add or update key-value pair
     * @param key Key to add/update
     * @param value Value to associate with key
     * Time complexity: O(1)
     */
    @Override
    public void put(K key, V value) {
        if (cacheMap.containsKey(key)) {
            // Update existing value
            Node<K, V> node = cacheMap.get(key);
            node.value = value;
            removeNode(node);
            addToFront(node);
        } else {
            // Add new entry
            if (cacheMap.size() >= capacity) {
                evict(); // Remove least recently used if at capacity
            }
            
            Node<K, V> newNode = new Node<>(key, value);
            cacheMap.put(key, newNode);
            addToFront(newNode);
        }
    }

    /**
     * @return Current number of items in cache
     */
    @Override
    public int size() {
        return cacheMap.size();
    }

    // Helper methods

    /**
     * Add node to the front (most recently used) of the list
     * @param node Node to add
     */
    private void addToFront(Node<K, V> node) {
        node.next = head;
        node.prev = null;
        
        if (head != null) {
            head.prev = node;
        }
        head = node;
        
        if (tail == null) {
            tail = node;
        }
    }

    /**
     * Remove node from the linked list
     * @param node Node to remove
     */
    private void removeNode(Node<K, V> node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }
        
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }
    }

    /**
     * Evict least recently used item (tail of list)
     */
    private void evict() {
        if (tail == null) return;
        
        cacheMap.remove(tail.key);
        removeNode(tail);
    }
}
```

## SOLID Principles Demonstration

1. **Single Responsibility Principle (SRP)**
   - `LRUCache` only handles cache operations
   - `Node` class only manages node data and links
   - Eviction policy is clearly separated in `evict()` method

2. **Open/Closed Principle (OCP)**
   - New cache implementations can be created by implementing `Cache` interface
   - Eviction strategies could be made pluggable through strategy pattern

3. **Liskov Substitution Principle (LSP)**
   - `LRUCache` can be used anywhere `Cache` is expected
   - All methods adhere to interface contract

4. **Interface Segregation Principle (ISP)**
   - `Cache` interface is small and focused
   - Doesn't force implementations to include unnecessary methods

5. **Dependency Inversion Principle (DIP)**
   - High-level modules could depend on `Cache` interface
   - `LRUCache` depends on abstractions (Map interface)

## Example Usage

```java
public class LRUCacheExample {
    public static void main(String[] args) {
        // Create cache with capacity of 3
        Cache<String, Integer> cache = new LRUCache<>(3);

        // Add some entries
        cache.put("one", 1);
        cache.put("two", 2);
        cache.put("three", 3);
        
        System.out.println(cache.get("one")); // 1 (now most recently used)
        
        // This will evict "two" (least recently used)
        cache.put("four", 4);
        
        System.out.println(cache.get("two")); // null (was evicted)
        System.out.println(cache.get("four")); // 4
    }
}
```

## Thread Safety Consideration

For multi-threaded environments, we could extend this implementation:

```java
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadSafeLRUCache<K, V> implements Cache<K, V> {
    private final LRUCache<K, V> cache;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public ThreadSafeLRUCache(int capacity) {
        this.cache = new LRUCache<>(capacity);
    }

    @Override
    public V get(K key) {
        lock.readLock().lock();
        try {
            return cache.get(key);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void put(K key, V value) {
        lock.writeLock().lock();
        try {
            cache.put(key, value);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public int size() {
        lock.readLock().lock();
        try {
            return cache.size();
        } finally {
            lock.readLock().unlock();
        }
    }
}
```

## Performance Analysis

- **Time Complexity**:
  - `get()`: O(1) - HashMap lookup + constant time linked list operations
  - `put()`: O(1) - HashMap insertion + constant time linked list operations
  - `size()`: O(1) - HashMap size operation

- **Space Complexity**: O(n) where n is the cache capacity

---

# Token Bucket Rate Limiter Implementation in Java

Here's a complete implementation of a Token Bucket rate limiter that demonstrates SOLID principles and thread-safe operations.

## Class Diagram (Text Representation)

```
<<interface>>
RateLimiter
+ allowRequest(String clientId): boolean

TokenBucketRateLimiter
- capacity: int
- refillRate: int
- clientBuckets: Map<String, ClientBucket>
- lock: ReentrantLock
+ TokenBucketRateLimiter(int capacity, int refillRate)
+ allowRequest(String clientId): boolean
- refillBucket(ClientBucket bucket, long currentTimeNanos)

ClientBucket
- tokens: AtomicInteger
- lastRefillNanos: AtomicLong
+ ClientBucket(int tokens, long lastRefillNanos)
+ tryConsume(): boolean
+ getLastRefillNanos(): long
+ setLastRefillNanos(long time)
```

## Complete Implementation

```java
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Interface defining rate limiting operations
 * Demonstrates: Dependency Inversion Principle (DIP)
 */
public interface RateLimiter {
    /**
     * Check if a request is allowed
     * @param clientId Client identifier
     * @return true if request is allowed, false if rate limited
     */
    boolean allowRequest(String clientId);
}

/**
 * Token bucket implementation for a single client
 * Demonstrates: Single Responsibility Principle (SRP)
 */
class ClientBucket {
    private final AtomicInteger tokens;
    private final AtomicLong lastRefillNanos;
    private final int capacity;

    public ClientBucket(int capacity, long currentTimeNanos) {
        this.capacity = capacity;
        this.tokens = new AtomicInteger(capacity);
        this.lastRefillNanos = new AtomicLong(currentTimeNanos);
    }

    /**
     * Attempt to consume a token
     * @return true if token was consumed, false if no tokens available
     */
    public boolean tryConsume() {
        while (true) {
            int current = tokens.get();
            if (current <= 0) {
                return false;
            }
            if (tokens.compareAndSet(current, current - 1)) {
                return true;
            }
        }
    }

    public long getLastRefillNanos() {
        return lastRefillNanos.get();
    }

    public void setLastRefillNanos(long time) {
        lastRefillNanos.set(time);
    }

    public void refill(int newTokens) {
        tokens.set(newTokens);
    }

    public int getCapacity() {
        return capacity;
    }
}

/**
 * Token Bucket Rate Limiter implementation
 * Demonstrates SOLID principles:
 * - Single Responsibility: Handles rate limiting only
 * - Open/Closed: Extensible through RateLimiter interface
 * - Liskov Substitution: Can be used wherever RateLimiter is expected
 * - Interface Segregation: Small, focused interface
 * - Dependency Inversion: Depends on RateLimiter abstraction
 */
public class TokenBucketRateLimiter implements RateLimiter {
    private final int capacity;
    private final int refillRate; // tokens per second
    private final Map<String, ClientBucket> clientBuckets;
    private final ReentrantLock lock;

    /**
     * Create a new rate limiter
     * @param capacity Maximum tokens per client
     * @param refillRate Tokens added per second
     */
    public TokenBucketRateLimiter(int capacity, int refillRate) {
        if (capacity <= 0 || refillRate <= 0) {
            throw new IllegalArgumentException("Capacity and refill rate must be positive");
        }
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.clientBuckets = new ConcurrentHashMap<>();
        this.lock = new ReentrantLock();
    }

    @Override
    public boolean allowRequest(String clientId) {
        // Get or create bucket for client
        ClientBucket bucket = clientBuckets.computeIfAbsent(clientId, 
            k -> new ClientBucket(capacity, System.nanoTime()));

        // Refill tokens if needed
        refillBucket(bucket, System.nanoTime());

        // Try to consume a token
        return bucket.tryConsume();
    }

    /**
     * Refill the bucket based on elapsed time
     * @param bucket Client's token bucket
     * @param currentTimeNanos Current time in nanoseconds
     */
    private void refillBucket(ClientBucket bucket, long currentTimeNanos) {
        long lastRefillNanos = bucket.getLastRefillNanos();
        long elapsedNanos = currentTimeNanos - lastRefillNanos;
        
        // Calculate how many tokens to add
        double elapsedSeconds = (double) elapsedNanos / 1_000_000_000;
        int tokensToAdd = (int) (elapsedSeconds * refillRate);
        
        if (tokensToAdd > 0) {
            lock.lock();
            try {
                // Check again in case another thread already updated it
                long lastRefillNanosCheck = bucket.getLastRefillNanos();
                if (lastRefillNanosCheck == lastRefillNanos) {
                    // Refill tokens and update last refill time
                    bucket.refill(Math.min(bucket.getCapacity(), bucket.getCapacity() + tokensToAdd));
                    bucket.setLastRefillNanos(currentTimeNanos);
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
```

## SOLID Principles Demonstration

1. **Single Responsibility Principle (SRP)**
   - `TokenBucketRateLimiter` only handles rate limiting
   - `ClientBucket` only manages token state for a single client
   - Refill logic is cleanly separated in `refillBucket()`

2. **Open/Closed Principle (OCP)**
   - New rate limiter implementations can be created by implementing `RateLimiter`
   - Refill strategies could be made pluggable through strategy pattern

3. **Liskov Substitution Principle (LSP)**
   - `TokenBucketRateLimiter` can be used anywhere `RateLimiter` is expected
   - All methods adhere to interface contract

4. **Interface Segregation Principle (ISP)**
   - `RateLimiter` interface is small and focused
   - Doesn't force implementations to include unnecessary methods

5. **Dependency Inversion Principle (DIP)**
   - High-level modules could depend on `RateLimiter` interface
   - `TokenBucketRateLimiter` depends on abstractions (Map interface)

## Thread Safety Implementation

The implementation uses several thread-safety mechanisms:
1. `ConcurrentHashMap` for thread-safe client bucket storage
2. `AtomicInteger` and `AtomicLong` for lock-free token counting
3. `ReentrantLock` for coordinated refill operations to prevent race conditions

## Example Usage

```java
public class RateLimiterExample {
    public static void main(String[] args) throws InterruptedException {
        // Create rate limiter with 5 requests per second capacity
        RateLimiter limiter = new TokenBucketRateLimiter(5, 1);
        
        String clientId = "user123";
        
        // Simulate burst of requests
        for (int i = 1; i <= 10; i++) {
            boolean allowed = limiter.allowRequest(clientId);
            System.out.println("Request " + i + ": " + (allowed ? "Allowed" : "Rate limited"));
            Thread.sleep(100); // 100ms between requests
        }
        
        // Wait for bucket to refill
        Thread.sleep(1000);
        
        System.out.println("\nAfter refill:");
        for (int i = 1; i <= 5; i++) {
            boolean allowed = limiter.allowRequest(clientId);
            System.out.println("Request " + i + ": " + (allowed ? "Allowed" : "Rate limited"));
        }
    }
}
```

### Example Output:
```
Request 1: Allowed
Request 2: Allowed
Request 3: Allowed
Request 4: Allowed
Request 5: Allowed
Request 6: Rate limited
Request 7: Rate limited
Request 8: Rate limited
Request 9: Rate limited
Request 10: Rate limited

After refill:
Request 1: Allowed
Request 2: Allowed
Request 3: Allowed
Request 4: Allowed
Request 5: Allowed
```

## Performance Considerations

1. **Time Complexity**:
   - `allowRequest()`: O(1) average case for ConcurrentHashMap operations
   - `refillBucket()`: O(1) for calculations, with brief lock contention

2. **Space Complexity**: O(n) where n is number of distinct clients

3. **Optimizations**:
   - Atomic operations for most frequent operations (token consumption)
   - Fine-grained locking only for refill operations
   - Lazy refill - tokens are only added when needed

## Alternative Refill Strategies

The current implementation uses a "lazy refill" strategy where tokens are only added when a request is made. Alternative approaches could include:

1. **Scheduled Refill**:
```java
ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
scheduler.scheduleAtFixedRate(() -> {
    long now = System.nanoTime();
    clientBuckets.forEach((id, bucket) -> refillBucket(bucket, now));
}, 1, 1, TimeUnit.SECONDS);
```

2. **Leaky Bucket**:
   - Instead of adding tokens in bursts, calculate exact token count based on elapsed time for each request

The lazy approach is generally most efficient for low to moderate request rates, while scheduled refill can be better for very high traffic systems.


# Thread-Safe Blocking Queue Implementation in Java

Here's a complete implementation of a blocking queue that demonstrates SOLID principles and thread-safe operations using `ReentrantLock` and `Condition`.

## Class Diagram (Text Representation)

```
<<interface>>
BlockingQueue<T>
+ put(T item): void
+ get(): T
+ size(): int

SimpleBlockingQueue<T>
- capacity: int
- queue: Queue<T>
- lock: ReentrantLock
- notFull: Condition
- notEmpty: Condition
+ SimpleBlockingQueue(int capacity)
+ put(T item): void
+ get(): T
+ size(): int
```

## Complete Implementation

```java
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Interface defining blocking queue operations
 * Demonstrates: Dependency Inversion Principle (DIP)
 */
public interface BlockingQueue<T> {
    /**
     * Put an item into the queue, blocks if queue is full
     * @param item the item to add
     * @throws InterruptedException if interrupted while waiting
     */
    void put(T item) throws InterruptedException;

    /**
     * Get an item from the queue, blocks if queue is empty
     * @return the item from the head of the queue
     * @throws InterruptedException if interrupted while waiting
     */
    T get() throws InterruptedException;

    /**
     * @return current number of items in the queue
     */
    int size();
}

/**
 * Thread-safe blocking queue implementation
 * Demonstrates SOLID principles:
 * - Single Responsibility: Manages queue operations only
 * - Open/Closed: Extensible through BlockingQueue interface
 * - Liskov Substitution: Can be used wherever BlockingQueue is expected
 * - Interface Segregation: Small, focused interface
 * - Dependency Inversion: Depends on BlockingQueue abstraction
 */
public class SimpleBlockingQueue<T> implements BlockingQueue<T> {
    private final int capacity;
    private final Queue<T> queue;
    private final ReentrantLock lock;
    private final Condition notFull;
    private final Condition notEmpty;

    /**
     * Create a blocking queue with specified capacity
     * @param capacity maximum number of items the queue can hold
     * @throws IllegalArgumentException if capacity <= 0
     */
    public SimpleBlockingQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.capacity = capacity;
        this.queue = new LinkedList<>();
        this.lock = new ReentrantLock();
        this.notFull = lock.newCondition();
        this.notEmpty = lock.newCondition();
    }

    @Override
    public void put(T item) throws InterruptedException {
        lock.lock();
        try {
            // Wait while queue is full
            while (queue.size() == capacity) {
                notFull.await();
            }
            
            // Add item and signal that queue is not empty
            queue.add(item);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public T get() throws InterruptedException {
        lock.lock();
        try {
            // Wait while queue is empty
            while (queue.isEmpty()) {
                notEmpty.await();
            }
            
            // Remove item and signal that queue is not full
            T item = queue.remove();
            notFull.signal();
            return item;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int size() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }
}
```

## SOLID Principles Demonstration

1. **Single Responsibility Principle (SRP)**
   - `SimpleBlockingQueue` only handles queue operations
   - Locking and condition management is contained within the class
   - No mixing of queue logic with other concerns

2. **Open/Closed Principle (OCP)**
   - New queue implementations can be created by implementing `BlockingQueue`
   - Different backing stores or synchronization strategies could be added without modifying existing code

3. **Liskov Substitution Principle (LSP)**
   - `SimpleBlockingQueue` can be used anywhere `BlockingQueue` is expected
   - All methods adhere to interface contract

4. **Interface Segregation Principle (ISP)**
   - `BlockingQueue` interface is small and focused
   - Doesn't force implementations to include unnecessary methods

5. **Dependency Inversion Principle (DIP)**
   - High-level modules could depend on `BlockingQueue` interface
   - `SimpleBlockingQueue` depends on abstractions (Queue interface)

## Thread Safety Implementation

The implementation uses several thread-safety mechanisms:
1. `ReentrantLock` for mutual exclusion
2. Two `Condition` variables (`notFull` and `notEmpty`) for precise thread signaling
3. Proper lock acquisition/release in try-finally blocks
4. Correct use of await() in while loops to handle spurious wakeups

## Example Usage (Producer-Consumer Pattern)

```java
public class BlockingQueueExample {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new SimpleBlockingQueue<>(5);
        
        // Producer thread
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    String item = "Item " + i;
                    queue.put(item);
                    System.out.println("Produced: " + item);
                    Thread.sleep(100); // Simulate work
                }
                queue.put("DONE"); // Signal completion
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        
        // Consumer thread
        Thread consumer = new Thread(() -> {
            try {
                while (true) {
                    String item = queue.get();
                    if ("DONE".equals(item)) {
                        break;
                    }
                    System.out.println("Consumed: " + item);
                    Thread.sleep(200); // Simulate work
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        
        producer.start();
        consumer.start();
        
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
```

### Example Output:
```
Produced: Item 1
Consumed: Item 1
Produced: Item 2
Produced: Item 3
Consumed: Item 2
Produced: Item 4
Produced: Item 5
Produced: Item 6
Consumed: Item 3
Produced: Item 7
Consumed: Item 4
Produced: Item 8
Consumed: Item 5
Produced: Item 9
Consumed: Item 6
Produced: Item 10
Consumed: Item 7
Consumed: Item 8
Consumed: Item 9
Consumed: Item 10
```

## Performance Considerations

1. **Throughput**: The `ReentrantLock` implementation typically provides better throughput than synchronized methods under contention
2. **Fairness**: The current implementation uses an unfair lock (default) which favors throughput over fairness
3. **Blocking**: Proper use of `Condition` variables ensures efficient thread signaling

## Alternative Implementations

1. **Using synchronized/wait/notify**:
```java
public class SynchronizedBlockingQueue<T> implements BlockingQueue<T> {
    private final int capacity;
    private final Queue<T> queue;
    
    public SynchronizedBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
    }
    
    public synchronized void put(T item) throws InterruptedException {
        while (queue.size() == capacity) {
            wait();
        }
        queue.add(item);
        notifyAll();
    }
    
    public synchronized T get() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        T item = queue.remove();
        notifyAll();
        return item;
    }
    
    public synchronized int size() {
        return queue.size();
    }
}
```

2. **Using Java's built-in `LinkedBlockingQueue`**:
```java
// For comparison - Java's standard implementation
BlockingQueue<String> standardQueue = new LinkedBlockingQueue<>(5);
```

## Key Design Decisions

1. **Choice of Locking Mechanism**:
   - Used `ReentrantLock` instead of `synchronized` for more flexible locking
   - Allows separate condition variables for full/empty states
   - Provides better diagnostics through lock monitoring

2. **Queue Backing Store**:
   - Used `LinkedList` for O(1) enqueue/dequeue operations
   - Could be replaced with `ArrayDeque` for potentially better memory locality

3. **Interruption Handling**:
   - Properly propagates `InterruptedException` to respect thread interruption
   - Follows Java's concurrency best practices

4. **Condition Variable Usage**:
   - Separate conditions for "not full" and "not empty" states
   - More efficient than using `notifyAll()` for all cases
   - Correctly uses while loops to handle spurious wakeups

This implementation provides a solid foundation that can be extended with additional features like timed waits, bulk operations, or statistics collection while maintaining thread safety and good performance characteristics.

# All problems
Here’s a list of **5 classic Low-Level Design (LLD) problems** frequently asked in technical interviews (especially at companies like Amazon, Google, and Microsoft). These test OOP, concurrency, and system design fundamentals:

### **1. Implement an LRU (Least Recently Used) Cache**
   - **Problem Statement**: Design a fixed-size cache that evicts the least recently used item when full.
   - **Key Concepts**:
     - HashMap + Doubly Linked List for O(1) access/insertion.
     - Thread safety (if extended to multi-threaded environments).

### **2. Design a Token Bucket Rate Limiter**
   - **Problem Statement**: Create a rate limiter that allows `N` requests per second per user/client.
   - **Key Concepts**:
     - Token refill strategy (e.g., fixed window, sliding log).
     - Thread-safe bucket updates (Atomic operations/ReentrantLock).

### **3. Design a Parking Lot System**
   - **Problem Statement**: Model a parking lot with features like spot assignment, payment, and vehicle tracking.
   - **Key Concepts**:
     - OOP (Classes: `ParkingLot`, `Spot`, `Ticket`, `Vehicle`).
     - Strategies for spot allocation (e.g., nearest to entrance).

### **4. Implement a Thread-Safe Blocking Queue**
   - **Problem Statement**: Build a blocking queue with `put()` and `get()` methods that block when full/empty.
   - **Key Concepts**:
     - Producer-consumer problem (wait/notify or `ReentrantLock` + `Condition`).
     - Bounded buffer handling.

### **5. Design a Deck of Cards for Blackjack**
   - **Problem Statement**: Model a deck of cards with operations like shuffle, deal, and game rules.
   - **Key Concepts**:
     - Enums for suits/ranks.
     - Shuffling algorithms (Fisher-Yates).
     - Extensibility (e.g., adding new games like Poker).

### **Why These Problems?**
- **Cover Core Skills**: OOP, data structures, concurrency, and scalability.
- **Real-World Relevance**: Caches, rate limiting, and resource management are common in backend systems.
- **Follow-Up Questions**:
  - *"How would you make the LRU cache distributed?"*
  - *"Extend the rate limiter to support dynamic limits per API key."*

Need variations (e.g., simpler/harder) or solutions for any of these?