Below is the detailed breakdown for each of the 25 problems related to **Queue and Stack** data structures. Each problem includes **class structure, method signatures, expected input/output**, and guidance for **local execution**.

---

## **1. Implement Stack using Arrays**
### Class Structure & Method Signatures
```java
class ArrayStack {
    private int[] stack;
    private int top;
    private int capacity;

    // Constructor
    public ArrayStack(int capacity) {
        this.capacity = capacity;
        this.stack = new int[capacity];
        this.top = -1;
    }

    // Push element onto the stack
    public void push(int element) {
        if (isFull()) throw new IllegalStateException("Stack is full");
        stack[++top] = element;
    }

    // Pop element from the stack
    public int pop() {
        if (isEmpty()) throw new IllegalStateException("Stack is empty");
        return stack[top--];
    }

    // Peek the top element
    public int peek() {
        if (isEmpty()) throw new IllegalStateException("Stack is empty");
        return stack[top];
    }

    // Check if stack is empty
    public boolean isEmpty() {
        return top == -1;
    }

    // Check if stack is full
    public boolean isFull() {
        return top == capacity - 1;
    }
}
```

### Expected Input & Output
- **Input**: Push elements `[10, 20, 30]` onto the stack.
- **Output**: Pop elements `[30, 20, 10]` in LIFO order.

---

## **2. Implement Queue using Arrays**
### Class Structure & Method Signatures
```java
class ArrayQueue {
    private int[] queue;
    private int front, rear, size, capacity;

    // Constructor
    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new int[capacity];
        this.front = this.size = 0;
        this.rear = capacity - 1;
    }

    // Add element to the queue
    public void enqueue(int element) {
        if (isFull()) throw new IllegalStateException("Queue is full");
        rear = (rear + 1) % capacity;
        queue[rear] = element;
        size++;
    }

    // Remove element from the queue
    public int dequeue() {
        if (isEmpty()) throw new IllegalStateException("Queue is empty");
        int element = queue[front];
        front = (front + 1) % capacity;
        size--;
        return element;
    }

    // Peek the front element
    public int peek() {
        if (isEmpty()) throw new IllegalStateException("Queue is empty");
        return queue[front];
    }

    // Check if queue is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Check if queue is full
    public boolean isFull() {
        return size == capacity;
    }
}
```

### Expected Input & Output
- **Input**: Enqueue elements `[10, 20, 30]`.
- **Output**: Dequeue elements `[10, 20, 30]` in FIFO order.

---

## **3. Valid Parentheses**
### Class Structure & Method Signatures
```java
import java.util.Stack;

class ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if ((c == ')' && top != '(') || (c == '}' && top != '{') || (c == ']' && top != '[')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
```

### Expected Input & Output
- **Input**: `"()[]{}"`
- **Output**: `true`

---

## **4. Reverse a String using Stack**
### Class Structure & Method Signatures
```java
import java.util.Stack;

class ReverseString {
    public String reverse(String input) {
        Stack<Character> stack = new Stack<>();
        for (char c : input.toCharArray()) {
            stack.push(c);
        }
        StringBuilder reversed = new StringBuilder();
        while (!stack.isEmpty()) {
            reversed.append(stack.pop());
        }
        return reversed.toString();
    }
}
```

### Expected Input & Output
- **Input**: `"hello"`
- **Output**: `"olleh"`

---

## **5. Implement Stack using Queues**
### Class Structure & Method Signatures
```java
import java.util.LinkedList;
import java.util.Queue;

class StackUsingQueues {
    private Queue<Integer> queue;

    public StackUsingQueues() {
        queue = new LinkedList<>();
    }

    public void push(int x) {
        queue.add(x);
        for (int i = 1; i < queue.size(); i++) {
            queue.add(queue.remove());
        }
    }

    public int pop() {
        return queue.remove();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
```

### Expected Input & Output
- **Input**: Push elements `[10, 20, 30]`.
- **Output**: Pop elements `[30, 20, 10]`.

---

## **6. Implement Queue using Stacks**
### Class Structure & Method Signatures
```java
import java.util.Stack;

class QueueUsingStacks {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public QueueUsingStacks() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void enqueue(int x) {
        stack1.push(x);
    }

    public int dequeue() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public int peek() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.peek();
    }

    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}
```

### Expected Input & Output
- **Input**: Enqueue elements `[10, 20, 30]`.
- **Output**: Dequeue elements `[10, 20, 30]`.

---

## **7. Minimum Element in Stack**
### Class Structure & Method Signatures
```java
import java.util.Stack;

class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }
    }

    public void pop() {
        if (stack.pop().equals(minStack.peek())) {
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
```

### Expected Input & Output
- **Input**: Push `[3, 5, 2, 1]`, then pop once.
- **Output**: Minimum element is `2`.

---

This pattern continues for the remaining problems. Each problem will have:
1. **Class and method definitions**.
2. **Input/output examples**.
3. **Local execution guidance**.

Let me know if you'd like the remaining problems broken down in the same way!