# Stacks and Queue
=================
## Table of contents

<!--ts-->
| Category        | Problems                                |    |    |    |
|:----------------|:----------------------------------------|:---|:---|:---|
| [Basic](#basic) | [Implement a Stack](#implement-a-stack) |    |    |    |
|                 |                                         |    |    |    |


<!--te-->
***
### Basic
```java
class Intro{
    public static void intro(){
        
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(10); stack.pop(); stack.peek();
        stack.isEmpty(); stack.size();
        
        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(10);  // q.add() is also acceptable
        q.poll(); q.peek();
        q.isEmpty(); q.size();
        
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();                            // minimum Heap by default
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());  // change to maximum Heap
        pq.add(10); 
        pq.poll(); pq.peek();
        pq.isEmpty(); pq.size();
        
        class Node implements Comparable<Node>{
            int x; int y;
            public Node(int x, int y){
                this.x = x;
                this.y = y;
            }
            @Override
            public int compareTo(Node that){
                return this.x - that.x;                            // ascending order / minimum Heap
                // return that.x - this.x;			               // descending order / maximum Heap
            }
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();

        
        
        java.util.Deque<Integer> dq = new LinkedList<Integer>();    // Deque is usually used to implement monotone queue
        dq.addFirst();  //  dq.offerFirst();
        dq.addLast();   //  dq.offerLast();
        dq.peekFirst(); // 
        dq.peekLast();
        dq.pollFirst(); //  dq.removeFirst();
        dq.pollLast();  //  dq.removeLast();
    }
}
    
```
***
#### Implement a Stack
##### Pattern: Stack
[Back to Top](#table-of-contents)
##### Description: Implement a basic stack using an array with standard stack operations.

- Input / Output:
    - `push(1)` => Stack: [1]
    - `push(2)` => Stack: [1, 2]
    - `pop()` => 2, Stack: [1]
    - `top()` => 1
- Explanation:
    - After pushing 1 and 2, the stack has [1, 2].
    - Popping the stack returns 2 and leaves [1].
    - Calling top() returns the current top element, which is 1.

```java
public class Stack {
    private int[] stack;
    private int top;
    private int capacity;

    // Constructor to initialize the stack with a given capacity
    public Stack(int capacity) {
        this.capacity = capacity;
        stack = new int[capacity];
        top = -1;
    }

    // Method to push an element onto the stack
    public void push(int value) {
        if (top == capacity - 1) {
            throw new IllegalStateException("Stack is full");
        }
        stack[++top] = value;
    }

    // Method to pop the top element off the stack
    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack[top--];
    }

    // Method to get the top element of the stack without removing it
    public int top() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack[top];
    }

    // Method to check if the stack is empty
    public boolean isEmpty() {
        return top == -1;
    }

    // Method to get the current size of the stack
    public int size() {
        return top + 1;
    }

    // Main method to test the stack implementation
    public static void main(String[] args) {
        Stack stack = new Stack(5); // Create a stack with capacity 5
        stack.push(1); // Stack: [1]
        System.out.println(stack.top()); // Output: 1
        stack.push(2); // Stack: [1, 2]
        System.out.println(stack.pop()); // Output: 2, Stack: [1]
        System.out.println(stack.top()); // Output: 1
        System.out.println(stack.size()); // Output: 1
        System.out.println(stack.isEmpty()); // Output: false
    }
}
```
##### Time Complexity:
- Push: O(1)
- Pop: O(1)
- Top: O(1)
- IsEmpty: O(1)
- Size: O(1)

##### Space Complexity:
- O(n) where n is the capacity of the stack
***

#### Implement a Queue Using Array
##### Pattern: Queue
[Back to Top](#table-of-contents)
##### Description:
Implement a queue using an array. The queue should support the following operations:
- `offer`: Add an element to the end of the queue.
- `poll`: Remove an element from the front of the queue.
- `isEmpty`: Check if the queue is empty.
- `isFull`: Check if the queue is full.
- `peek`: Get the front element without removing it.

- Input: / Output:

```java
public class Queue {
    private int[] arr; // Array to store queue elements
    private int front; // Front points to front element in the queue
    private int rear;  // Rear points to last element in the queue
    private int capacity; // Maximum capacity of the queue
    private int count; // Current size of the queue

    // Constructor to initialize queue
    Queue(int size) {
        arr = new int[size];
        capacity = size;
        front = 0;
        rear = -1;
        count = 0;
    }

    // Utility function to remove front element from the queue
    public void poll() {
        if (isEmpty()) {
            System.out.println("Underflow\nProgram Terminated");
            System.exit(1);
        }

        front = (front + 1) % capacity;
        count--;
    }

    // Utility function to add an item to the queue
    public void offer(int item) {
        if (isFull()) {
            System.out.println("Overflow\nProgram Terminated");
            System.exit(1);
        }

        rear = (rear + 1) % capacity;
        arr[rear] = item;
        count++;
    }

    // Utility function to return front element of the queue
    public int peek() {
        if (isEmpty()) {
            System.out.println("Underflow\nProgram Terminated");
            System.exit(1);
        }
        return arr[front];
    }

    // Utility function to return the size of the queue
    public int size() {
        return count;
    }

    // Utility function to check if the queue is empty or not
    public boolean isEmpty() {
        return (size() == 0);
    }

    // Utility function to check if the queue is full or not
    public boolean isFull() {
        return (size() == capacity);
    }
}

```
##### Time Complexity:
- `offer`: O(1)
- `poll`: O(1)
- `peek`: O(1)
- `isEmpty`: O(1)
- `isFull`: O(1)

##### Space Complexity:
- O(n), where n is the capacity of the queue (the size of the array used).

***

#### Generate Binary Numbers From 1 to n Using a Queue
##### Pattern: Breadth-First Search (BFS)
[Back to Top](#table-of-contents)
##### Description: Given a positive integer `n`, generate binary numbers from `1` to `n` using a queue.

- Input: n = 5
  Output: ["1", "10", "11", "100", "101"]
  Explanation: The binary numbers from 1 to 5 are 1, 10, 11, 100, and 101.

- Input: n = 10
  Output: ["1", "10", "11", "100", "101", "110", "111", "1000", "1001", "1010"]
  Explanation: The binary numbers from 1 to 10 are 1, 10, 11, 100, 101, 110, 111, 1000, 1001, and 1010.

```java
import java.util.LinkedList;
import java.util.Queue;

public class GenerateBinaryNumbers {
    
    // Method to generate binary numbers from 1 to n using a queue
    public static String[] generateBinaryNumbers(int n) {
        // Array to store the binary numbers
        String[] result = new String[n];
        
        // Queue to store the intermediate binary numbers
        Queue<String> queue = new LinkedList<>();
        
        // Enqueue the first binary number
        queue.add("1");
        
        for (int i = 0; i < n; i++) {
            // Dequeue the front of the queue
            String current = queue.remove();
            
            // Store the current binary number in the result array
            result[i] = current;
            
            // Generate the next two binary numbers and enqueue them
            queue.add(current + "0");
            queue.add(current + "1");
        }
        
        return result;
    }
}
```
##### Time Complexity:
- The time complexity is O(n) because we process each number exactly once.

##### Space Complexity:
- The space complexity is O(n) because we store `n` binary numbers in the result array and the queue.

***

#### Implement Two Stacks Using One Array
##### Pattern: Two Pointer
[Back to Top](#table-of-contents)
##### Description: Implement two stacks using a single array.

- Input:
    - Push operations: push1(10), push2(20), push1(30), push2(40)
    - Pop operations: pop1(), pop2(), pop1(), pop2()

  Output:
    - After push operations: stack1 = [10, 30], stack2 = [20, 40]
    - After pop operations: 30, 40, 10, 20

- Explanation: Two stacks are maintained in a single array. `push1` and `pop1` operate
on the first stack, while `push2` and `pop2` operate on the second stack. The first stack grows from the
start to the middle of the array, and the second stack grows from the end to the middle.

```java
public class TwoStacks {
    int[] arr;
    int size;
    int top1, top2;

    // Constructor to initialize the array and the pointers
    public TwoStacks(int n) {
        size = n;
        arr = new int[n];
        top1 = -1;
        top2 = n;
    }

    // Push an element x to stack1
    public void push1(int x) {
        // Check for overflow
        if (top1 < top2 - 1) {
            top1++;
            arr[top1] = x;
        } else {
            System.out.println("Stack Overflow");
        }
    }

    // Push an element x to stack2
    public void push2(int x) {
        // Check for overflow
        if (top1 < top2 - 1) {
            top2--;
            arr[top2] = x;
        } else {
            System.out.println("Stack Overflow");
        }
    }

    // Pop an element from stack1
    public int pop1() {
        // Check for underflow
        if (top1 >= 0) {
            int x = arr[top1];
            top1--;
            return x;
        } else {
            System.out.println("Stack Underflow");
            return -1;
        }
    }

    // Pop an element from stack2
    public int pop2() {
        // Check for underflow
        if (top2 < size) {
            int x = arr[top2];
            top2++;
            return x;
        } else {
            System.out.println("Stack Underflow");
            return -1;
        }
    }
}
```
##### Time Complexity:
- Each push and pop operation is O(1) as they involve a single operation of updating the index and the array element.

##### Space Complexity:
- O(n), where n is the size of the array used to store the elements of both stacks.
***

#### Reverse First k Elements of Queue
##### Pattern: Queue Manipulation
[Back to Top](#table-of-contents)
##### Description: Reverse the first `k` elements of a given queue (represented using an array). The rest of the queue should remain in the same order.

- Input: `queue = [10, 20, 30, 40, 50], k = 3`
  Output: `[30, 20, 10, 40, 50]`
  Explanation: The first three elements (10, 20, 30) are reversed to become (30, 20, 10). The rest of the queue (40, 50) remains unchanged.

```java
import java.util.*;

public class ReverseFirstKElements {
    // Function to reverse the first k elements of the queue
    
 public static MyQueue<Integer> reverseK(MyQueue<Integer> queue, int k) {
    
    if (queue.isEmpty() || k > queue.size() || k < 0) {
        return queue;
    }
    MyStack<Integer> stack = new MyStack<>();

    // Dequeue the first k elements from the queue and push them onto the stack.
    for (int i = 0; i < k; ++i) {
        stack.push(queue.dequeue());
    }

    // Pop elements from the stack and enqueue them back to the queue.
    // This reverses the order of the first k elements.
    while (!stack.isEmpty()) {
        queue.enqueue(stack.pop());
    }

    // Calculate the size of the remaining elements in the queue.
    int size = queue.size();

    // Move the remaining elements (size - k) to the back of the queue to maintain their order.
    for (int i = 0; i < size - k; ++i) {
        queue.enqueue(queue.dequeue());
    }

    // Return the modified queue with the first k elements reversed.
    return queue;
}}
```
##### Time Complexity:
- The time complexity is O(k) since we push the first k elements onto the stack and then pop them back into the queue. This is linear with respect to k.

##### Space Complexity:
- The space complexity is O(k) due to the additional stack used to store the first k elements.
***

#### Implement Queue Using Stacks
##### Pattern: Stack and Queue
[Back to Top](#table-of-contents)
##### Description:
- Implement a queue using two stacks.
The queue should support
- `enqueue` (adding to the end of the queue)
- `dequeue` (removing from the front of the queue) operations.

```java
import java.util.Stack;

public class MyQueue {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    // Initialize your data structure here.
    public MyQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    // Push element x to the back of the queue.
    public void enqueue(int x) {
        stack1.push(x);
    }

    // Removes the element from in front of the queue and returns that element.
    public int dequeue() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    // Get the front element.
    public int peek() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.peek();
    }

    // Returns whether the queue is empty.
    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}
```
##### Time Complexity:
- `enqueue`: O(1) - Each element is pushed onto `stack1` directly.
- `dequeue`: Amortized O(1) - Each element is moved at most once from `stack1` to `stack2` and popped from `stack2`.
- `peek`: Amortized O(1) - Similar to `dequeue`, each element is moved at most once from `stack1` to `stack2` for peeking.
- `empty`: O(1) - Directly checks if both stacks are empty.

##### Space Complexity:
- O(n) - Space is used to store the elements in the two stacks.
***

#### Sort Values in a Stack
##### Pattern: Sorting
[Back to Top](#table-of-contents)
##### Description:
- Given a stack, sort it in ascending order using an auxiliary stack.
- The original stack should be modified to contain the sorted elements.

- Input: `stack = [34, 3, 31, 98, 92, 23]`
  Output: `stack = [3, 23, 31, 34, 92, 98]`
  Explanation: The elements in the stack are sorted in ascending order.

```java
import java.util.Stack;

public class SortStack {
    public static void sortStack(Stack<Integer> stack) {
        Stack<Integer> auxiliaryStack = new Stack<>();
        
        // While the original stack is not empty
        while (!stack.isEmpty()) {
            // Pop an element from the original stack
            int temp = stack.pop();
            
            // While the auxiliary stack is not empty and the top of auxiliary stack
            // is greater than the popped element from the original stack
            while (!auxiliaryStack.isEmpty() && auxiliaryStack.peek() > temp) {
                // Pop from auxiliary stack and push it to the original stack
                stack.push(auxiliaryStack.pop());
            }
            
            // Push the temp value into the auxiliary stack
            auxiliaryStack.push(temp);
        }
        
        // Transfer the sorted elements back to the original stack
        while (!auxiliaryStack.isEmpty()) {
            stack.push(auxiliaryStack.pop());
        }
    }
}
```
##### Time Complexity:
- The time complexity of this algorithm is O(n^2), where n is the number of elements in the stack. This is because each element may need to be moved multiple times between the two stacks.

##### Space Complexity:
- The space complexity is O(n), where n is the number of elements in the stack, due to the additional auxiliary stack used for sorting.
***

### Evaluate Postfix Expression Using a Stack
##### Pattern: Stack
[Back to Top](#table-of-contents)
##### Description:
Evaluate a postfix expression (also known as Reverse Polish Notation) using a stack.
The expression is given as an array of strings, where each string is either an operand or an operator.
The function should return the evaluated result of the expression.

- Input: `["2", "1", "+", "3", "*"]`
  Output: `9`
  Explanation:
    - 2 and 1 are pushed onto the stack.
    - "+" pops 2 and 1, adds them (2 + 1 = 3), and pushes the result (3) onto the stack.
    - 3 is pushed onto the stack.
    - "*" pops 3 and 3, multiplies them (3 * 3 = 9), and pushes the result (9) onto the stack.
    - The final result is 9.

```java
import java.util.Stack;

public class PostfixEvaluator {

    // Method to evaluate postfix expression
    public static int evaluatePostfix(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            // If the token is a number, push it onto the stack
            if (isNumber(token)) {
                stack.push(Integer.parseInt(token));
            } else {
                // Token is an operator, pop two operands from stack
                int operand2 = stack.pop();
                int operand1 = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(operand1 + operand2);
                        break;
                    case "-":
                        stack.push(operand1 - operand2);
                        break;
                    case "*":
                        stack.push(operand1 * operand2);
                        break;
                    case "/":
                        stack.push(operand1 / operand2);
                        break;
                }
            }
        }
        // The result is the last remaining element on the stack
        return stack.pop();
    }

    // Helper method to determine if a string is a number
    private static boolean isNumber(String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

  
}
```
##### Time Complexity:
- The time complexity is O(n), where n is the number of tokens in the expression. Each token is processed exactly once.

##### Space Complexity:
- The space complexity is O(n), where n is the number of tokens in the expression. In the worst case, the stack could hold all the operands at once.
***