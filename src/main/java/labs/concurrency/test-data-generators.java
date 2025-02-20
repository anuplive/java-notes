package labs.concurrency;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Module 1: Order Processing Test Data Generator
 */
class OrderTestDataGenerator {
    private static final String[] PRODUCT_IDS = {"P1", "P2", "P3", "P4", "P5"};
    private static final BigDecimal[] PRICES = {
        new BigDecimal("9.99"),
        new BigDecimal("19.99"),
        new BigDecimal("29.99"),
        new BigDecimal("39.99"),
        new BigDecimal("49.99")
    };

    public static Order generateOrder() {
        long orderId = ThreadLocalRandom.current().nextLong(1000, 9999);
        long customerId = ThreadLocalRandom.current().nextLong(100, 999);
        List<OrderItem> items = generateOrderItems(1 + ThreadLocalRandom.current().nextInt(5));
        
        return new Order(orderId, customerId, items, OrderStatus.NEW);
    }

    public static List<Order> generateOrders(int count) {
        return IntStream.range(0, count)
            .mapToObj(i -> generateOrder())
            .collect(Collectors.toList());
    }

    private static List<OrderItem> generateOrderItems(int count) {
        return IntStream.range(0, count)
            .mapToObj(i -> {
                int index = ThreadLocalRandom.current().nextInt(PRODUCT_IDS.length);
                return new OrderItem(
                    PRODUCT_IDS[index],
                    1 + ThreadLocalRandom.current().nextInt(5),
                    PRICES[index]
                );
            })
            .collect(Collectors.toList());
    }
}

/**
 * Module 2: Bank Transaction Test Data Generator
 */
class BankTestDataGenerator {
    private static final String[] ACCOUNT_IDS = {"ACC001", "ACC002", "ACC003", "ACC004", "ACC005"};
    
    public static Account generateAccount() {
        String accountId = ACCOUNT_IDS[ThreadLocalRandom.current().nextInt(ACCOUNT_IDS.length)];
        BigDecimal initialBalance = new BigDecimal(ThreadLocalRandom.current().nextInt(1000, 10000));
        return new Account(accountId, initialBalance);
    }

    public static Transaction generateTransaction(String accountId) {
        String transactionId = "TXN" + ThreadLocalRandom.current().nextInt(10000, 99999);
        TransactionType type = ThreadLocalRandom.current().nextBoolean() ? 
            TransactionType.DEPOSIT : TransactionType.WITHDRAWAL;
        BigDecimal amount = new BigDecimal(ThreadLocalRandom.current().nextInt(10, 1000));
        
        return new Transaction(transactionId, accountId, amount, type);
    }

    public static List<Transaction> generateTransactionBatch(int count) {
        return IntStream.range(0, count)
            .mapToObj(i -> generateTransaction(
                ACCOUNT_IDS[ThreadLocalRandom.current().nextInt(ACCOUNT_IDS.length)]))
            .collect(Collectors.toList());
    }
}

/**
 * Module 3: Product Data Generator
 */
class ProductTestDataGenerator {
    private static final String[] CATEGORIES = {"Electronics", "Books", "Clothing", "Food", "Toys"};
    private static final String[] PRODUCT_NAMES = {
        "Laptop", "Smartphone", "Headphones", "Book", "T-Shirt", 
        "Jeans", "Snacks", "Coffee", "Toy Car", "Board Game"
    };

    public static Product generateProduct() {
        String id = "PROD" + ThreadLocalRandom.current().nextInt(10000, 99999);
        String name = PRODUCT_NAMES[ThreadLocalRandom.current().nextInt(PRODUCT_NAMES.length)];
        String category = CATEGORIES[ThreadLocalRandom.current().nextInt(CATEGORIES.length)];
        BigDecimal price = new BigDecimal(ThreadLocalRandom.current().nextInt(1000, 100000))
            .divide(new BigDecimal("100"));
        
        return new Product(id, name, price, category);
    }

    public static List<Product> generateProducts(int count) {
        return IntStream.range(0, count)
            .mapToObj(i -> generateProduct())
            .collect(Collectors.toList());
    }
}

/**
 * Module 4: Asynchronous Order Processing Test Data
 */
class AsyncOrderTestDataGenerator {
    public static Map<String, Integer> generateInventoryData() {
        return ProductTestDataGenerator.generateProducts(10).stream()
            .collect(Collectors.toMap(
                Product::getId,
                p -> ThreadLocalRandom.current().nextInt(0, 100)
            ));
    }

    public static PaymentResult generatePaymentResult(Order order) {
        boolean success = ThreadLocalRandom.current().nextDouble() < 0.9; // 90% success rate
        String message = success ? "Payment processed successfully" : "Payment failed";
        return new PaymentResult(order.getOrderId(), success, message);
    }
}

/**
 * Module 5: Price Aggregation Test Data
 */
class PriceTestDataGenerator {
    private static final String[] SUPPLIER_IDS = {"SUP1", "SUP2", "SUP3", "SUP4", "SUP5"};

    public static Map<String, BigDecimal> generateSupplierPrices(String productId) {
        return Arrays.stream(SUPPLIER_IDS)
            .collect(Collectors.toMap(
                supplierId -> supplierId,
                supplierId -> new BigDecimal(ThreadLocalRandom.current().nextInt(1000, 2000))
                    .divide(new BigDecimal("100"))
            ));
    }

    public static List<PriceQuote> generatePriceQuotes(String productId) {
        return Arrays.stream(SUPPLIER_IDS)
            .map(supplierId -> new PriceQuote(
                supplierId,
                productId,
                new BigDecimal(ThreadLocalRandom.current().nextInt(1000, 2000))
                    .divide(new BigDecimal("100")),
                LocalDateTime.now()
            ))
            .collect(Collectors.toList());
    }
}

/**
 * Module 6: Session Management Test Data
 */
class SessionTestDataGenerator {
    private static final String[] USER_IDS = {"USER1", "USER2", "USER3", "USER4", "USER5"};
    
    public static UserContext generateUserContext() {
        String userId = USER_IDS[ThreadLocalRandom.current().nextInt(USER_IDS.length)];
        Map<String, String> attributes = new HashMap<>();
        attributes.put("role", ThreadLocalRandom.current().nextBoolean() ? "ADMIN" : "USER");
        attributes.put("region", "Region-" + ThreadLocalRandom.current().nextInt(1, 5));
        
        return new UserContext(userId, attributes, LocalDateTime.now());
    }

    public static TransactionContext generateTransactionContext() {
        String transactionId = "TRX" + ThreadLocalRandom.current().nextInt(10000, 99999);
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("source", "WEB");
        attributes.put("sessionId", UUID.randomUUID().toString());
        
        return new TransactionContext(transactionId, LocalDateTime.now(), attributes);
    }
}

/**
 * Module 7: Log Processing Test Data
 */
class LogTestDataGenerator {
    private static final String[] LOG_MESSAGES = {
        "User login attempt", "Database query executed", "Order processed",
        "Payment received", "Error occurred", "Cache miss", "API request received"
    };
    
    public static LogEntry generateLogEntry() {
        String id = UUID.randomUUID().toString();
        LogLevel level = LogLevel.values()[ThreadLocalRandom.current().nextInt(LogLevel.values().length)];
        String message = LOG_MESSAGES[ThreadLocalRandom.current().nextInt(LOG_MESSAGES.length)];
        
        Map<String, String> metadata = new HashMap<>();
        metadata.put("source", "TestGenerator");
        metadata.put("thread", Thread.currentThread().getName());
        
        return new LogEntry(id, LocalDateTime.now(), level, message, metadata);
    }

    public static List<LogEntry> generateLogBatch(int count) {
        return IntStream.range(0, count)
            .mapToObj(i -> generateLogEntry())
            .collect(Collectors.toList());
    }
}

/**
 * Module 8: Cache Test Data
 */
class CacheTestDataGenerator {
    public static <K> Map<K, CacheEntry<String>> generateCacheEntries(List<K> keys) {
        return keys.stream()
            .collect(Collectors.toMap(
                key -> key,
                key -> new CacheEntry<>(
                    "Value-" + ThreadLocalRandom.current().nextInt(1000),
                    LocalDateTime.now().minusMinutes(ThreadLocalRandom.current().nextInt(60)),
                    LocalDateTime.now(),
                    new AtomicLong(ThreadLocalRandom.current().nextInt(100))
                )
            ));
    }

    public static CacheStatistics generateCacheStatistics() {
        CacheStatistics stats = new CacheStatistics();
        IntStream.range(0, ThreadLocalRandom.current().nextInt(1000))
            .forEach(i -> stats.recordRead());
        IntStream.range(0, ThreadLocalRandom.current().nextInt(1000))
            .forEach(i -> stats.recordWrite());
        return stats;
    }
}

/**
 * Module 9: Queue Test Data
 */
class QueueTestDataGenerator {
    public static <T> List<T> generateQueueElements(int count, Supplier<T> elementSupplier) {
        return IntStream.range(0, count)
            .mapToObj(i -> elementSupplier.get())
            .collect(Collectors.toList());
    }

    public static QueueStatistics generateQueueStatistics() {
        QueueStatistics stats = new QueueStatistics();
        IntStream.range(0, ThreadLocalRandom.current().nextInt(1000))
            .forEach(i -> stats.recordEnqueue());
        IntStream.range(0, ThreadLocalRandom.current().nextInt(1000))
            .forEach(i -> stats.recordDequeue());
        return stats;
    }
}

/**
 * Module 10: E-commerce Integration Test Data
 */
class EcommerceTestDataGenerator {
    public static class TestDataSet {
        public final List<Order> orders;
        public final Map<String, Integer> inventory;
        public final List<UserContext> sessions;
        public final Map<String, Product> products;
        
        public TestDataSet(List<Order> orders, Map<String, Integer> inventory,
                          List<UserContext> sessions, Map<String, Product> products) {
            this.orders = orders;
            this.inventory = inventory;
            this.sessions = sessions;
            this.products = products;
        }
    }

    public static TestDataSet generateFullTestDataSet() {
        // Generate products first as they're needed for orders
        List<Product> products = ProductTestDataGenerator.generateProducts(20);
        Map<String, Product> productMap = products.stream()
            .collect(Collectors.toMap(Product::getId, p -> p));
        
        // Generate other data
        List<Order> orders = OrderTestDataGenerator.generateOrders(10);
        Map<String, Integer> inventory = products.stream()
            .collect(Collectors.toMap(
                Product::getId,
                p -> ThreadLocalRandom.current().nextInt(0, 100)
            ));
        List<UserContext> sessions = IntStream.range(0, 5)
            .mapToObj(i -> SessionTestDataGenerator.generateUserContext())
            .collect(Collectors.toList());
        
        return new TestDataSet(orders, inventory, sessions, productMap);
    }

    public static MetricsCollector generateMetricsCollector() {
        MetricsCollector collector = new MetricsCollector();
        String[] metricNames = {"orders_processed", "inventory_checks", "cache_hits", "active_sessions"};
        
        for (String metric : metricNames) {
            collector.recordMetric(metric, ThreadLocalRandom.current().nextLong(1000));
        }
        
        return collector;
    }
}
