Here’s the breakdown for the remaining problems, following the same structure as above. Each problem includes **class structure, method signatures, expected input/output**, and guidance for **local execution**.

---

## **8. Next Greater Element**
### Class Structure & Method Signatures
```java
import java.util.Stack;

class NextGreaterElement {
    public int[] nextGreaterElements(int[] nums) {
        int[] result = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i]);
        }
        return result;
    }
}
```

### Expected Input & Output
- **Input**: `[4, 5, 2, 25]`
- **Output**: `[5, 25, 25, -1]`

---

## **9. Stock Span Problem**
### Class Structure & Method Signatures
```java
import java.util.Stack;

class StockSpan {
    public int[] calculateSpan(int[] prices) {
        int[] span = new int[prices.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < prices.length; i++) {
            while (!stack.isEmpty() && prices[stack.peek()] <= prices[i]) {
                stack.pop();
            }
            span[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }
        return span;
    }
}
```

### Expected Input & Output
- **Input**: `[100, 80, 60, 70, 60, 75, 85]`
- **Output**: `[1, 1, 1, 2, 1, 4, 6]`

---

## **10. Largest Rectangle in Histogram**
### Class Structure & Method Signatures
```java
import java.util.Stack;

class LargestRectangle {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i <= heights.length; i++) {
            int h = (i == heights.length) ? 0 : heights[i];
            while (!stack.isEmpty() && heights[stack.peek()] > h) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        return maxArea;
    }
}
```

### Expected Input & Output
- **Input**: `[2, 1, 5, 6, 2, 3]`
- **Output**: `10`

---

## **11. Sliding Window Maximum**
### Class Structure & Method Signatures
```java
import java.util.Deque;
import java.util.LinkedList;

class SlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) return new int[0];
        int[] result = new int[nums.length - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && deque.peek() < i - k + 1) {
                deque.poll();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.offer(i);
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peek()];
            }
        }
        return result;
    }
}
```

### Expected Input & Output
- **Input**: `[1, 3, -1, -3, 5, 3, 6, 7]`, `k = 3`
- **Output**: `[3, 3, 5, 5, 6, 7]`

---

## **12. Circular Queue Implementation**
### Class Structure & Method Signatures
```java
class CircularQueue {
    private int[] queue;
    private int front, rear, size, capacity;

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new int[capacity];
        this.front = this.size = 0;
        this.rear = capacity - 1;
    }

    public void enqueue(int element) {
        if (isFull()) throw new IllegalStateException("Queue is full");
        rear = (rear + 1) % capacity;
        queue[rear] = element;
        size++;
    }

    public int dequeue() {
        if (isEmpty()) throw new IllegalStateException("Queue is empty");
        int element = queue[front];
        front = (front + 1) % capacity;
        size--;
        return element;
    }

    public int peek() {
        if (isEmpty()) throw new IllegalStateException("Queue is empty");
        return queue[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }
}
```

### Expected Input & Output
- **Input**: Enqueue `[10, 20, 30]`, then dequeue twice.
- **Output**: Dequeued elements `[10, 20]`.

---

## **13. Evaluate Reverse Polish Notation**
### Class Structure & Method Signatures
```java
import java.util.Stack;

class ReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (token.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (token.equals("-")) {
                int b = stack.pop(), a = stack.pop();
                stack.push(a - b);
            } else if (token.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (token.equals("/")) {
                int b = stack.pop(), a = stack.pop();
                stack.push(a / b);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
}
```

### Expected Input & Output
- **Input**: `["2", "1", "+", "3", "*"]`
- **Output**: `9`

---

## **14. Decode String**
### Class Structure & Method Signatures
```java
import java.util.Stack;

class DecodeString {
    public String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<String> stringStack = new Stack<>();
        String currentString = "";
        int currentCount = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                currentCount = currentCount * 10 + (c - '0');
            } else if (c == '[') {
                countStack.push(currentCount);
                stringStack.push(currentString);
                currentCount = 0;
                currentString = "";
            } else if (c == ']') {
                int count = countStack.pop();
                String prevString = stringStack.pop();
                currentString = prevString + currentString.repeat(count);
            } else {
                currentString += c;
            }
        }
        return currentString;
    }
}
```

### Expected Input & Output
- **Input**: `"3[a2[c]]"`
- **Output**: `"accaccacc"`

---

## **15. Design a Max Stack**
### Class Structure & Method Signatures
```java
import java.util.Stack;

class MaxStack {
    private Stack<Integer> stack;
    private Stack<Integer> maxStack;

    public MaxStack() {
        stack = new Stack<>();
        maxStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (maxStack.isEmpty() || x >= maxStack.peek()) {
            maxStack.push(x);
        }
    }

    public int pop() {
        int x = stack.pop();
        if (x == maxStack.peek()) {
            maxStack.pop();
        }
        return x;
    }

    public int top() {
        return stack.peek();
    }

    public int peekMax() {
        return maxStack.peek();
    }

    public int popMax() {
        int max = maxStack.pop();
        Stack<Integer> temp = new Stack<>();
        while (stack.peek() != max) {
            temp.push(stack.pop());
        }
        stack.pop();
        while (!temp.isEmpty()) {
            push(temp.pop());
        }
        return max;
    }
}
```

### Expected Input & Output
- **Input**: Push `[5, 1, 5]`, then `popMax()`.
- **Output**: `5`

---

This pattern continues for the remaining problems. Let me know if you'd like the rest broken down in the same way!