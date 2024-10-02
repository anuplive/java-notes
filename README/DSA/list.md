# List
=================
## Table of contents

<!--ts-->
| Category                                                                  | Problems |    |    |    |
|:--------------------------------------------------------------------------|:---------|:---|:---|:---|
| [Basic](#basic)                                                           |          |    |    |    |
| [Remove nth Node from End of List](#remove-nth-node-from-end-of-list)     |          |    |    |    |
| [Linked List Cycle](#linked-list-cycle)                                   |          |    |    |    |
|                                                                           |          |    |    |    |
| [AlgoMap](#algomap)                                                       |          |    |    |    |
| [Remove Duplicates from Sorted List](#remove-duplicates-from-sorted-list) |          |    |    |    |
| [Reverse Linked List](#reverse-linked-list)                               |          |    |    |    |
| [Merge Two Sorted Lists](#merge-two-sorted-lists)                         |          |    |    |    |
| [Linked List Cycle](#linked-list-cycle)                                   |          |    |    |    |
| [Middle of the Linked List](#middle-of-the-linked-list)                   |          |    |    |    |
| [Remove nth Node from End of List](#remove-nth-node-from-end-of-list)     |          |    |    |    |
| [Copy List with Random Pointer](#copy-list-with-random-pointer)           |          |    |    |    |

#### Basic
[Back to Top](#table-of-contents)
***



#### Remove nth Node from End of List
##### Pattern: Two Pointer
[Back to Top](#table-of-contents)
##### Description:
- Input: head = [1, 2, 3, 4, 5], n = 2
- Output: [1, 2, 3, 5]
- Explanation: The second node from the end is the node with value 4, which is removed.

```java
// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Create a dummy node that points to the head of the list
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        // Initialize two pointers - first and second
        ListNode first = dummy;
        ListNode second = dummy;
        
        // Move first pointer n+1 steps ahead so there's a gap of n between first and second
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }
        
        // Move both pointers until first reaches the end of the list
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        
        // Remove the nth node from the end
        second.next = second.next.next;
        
        // Return the head of the modified list
        return dummy.next;
    }
}
```
##### Time Complexity:
- The time complexity is O(L), where L is the length of the linked list, as we traverse the list twice (once to advance the first pointer and once to move both pointers together).

##### Space Complexity:
- The space complexity is O(1) since we are using only a constant amount of extra space.
***

#### Linked List Cycle
##### Pattern: Two Pointer
[Back to Top](#table-of-contents)
##### Description:
- Input: `head = [3, 2, 0, -4]` (with a cycle at node with value `2`)
- Output: `true`
- Explanation: The linked list has a cycle as the node with value `-4` points back to the node with value `2`.

```java
// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class Solution {
    public boolean hasCycle(ListNode head) {
        // Use two pointers, slow and fast.
        ListNode slow = head;
        ListNode fast = head;
        
        // Traverse the list with slow moving one step at a time and fast moving two steps at a time.
        while (fast != null && fast.next != null) {
            slow = slow.next;         // Move slow pointer one step.
            fast = fast.next.next;    // Move fast pointer two steps.
            
            // If slow and fast pointers meet, there is a cycle.
            if (slow == fast) {
                return true;
            }
        }
        
        // If we reach here, there is no cycle.
        return false;
    }
}
```
##### Time Complexity:
- O(n): In the worst case, the slow pointer will traverse the entire list, and the fast pointer will cover at most twice the distance of the slow pointer.

##### Space Complexity:
- O(1): Only two additional pointers (slow and fast) are used, which requires constant space.
***

#### AlgoMap
[Back to Top](#Table-of-contents)
***


#### Remove Duplicates from Sorted List
##### Pattern: Linked List
[Back to Top](#table-of-contents)

##### Description:
- **Input:** A sorted linked list with possible duplicate values.  
  Example: `1 -> 1 -> 2 -> 3 -> 3`
- **Output:** A linked list with duplicates removed.  
  Example: `1 -> 2 -> 3`
- **Explanation:** The input list contains duplicates, and the output should only retain distinct values in the list.

```java
// Node class for the linked list
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class RemoveDuplicates {
    
    // Method to remove duplicates from a sorted linked list
    public ListNode deleteDuplicates(ListNode head) {
        // Initialize current node to the head of the list
        ListNode current = head;

        // Traverse the list until we reach the end
        while (current != null && current.next != null) {
            // Compare current node with the next node
            if (current.val == current.next.val) {
                // If duplicate, skip the next node
                current.next = current.next.next;
            } else {
                // Move to the next node if no duplicate
                current = current.next;
            }
        }

        // Return the head of the modified list
        return head;
    }
}
```

##### Algorithm Explanation:
- Start from the head of the linked list.
- Compare each node's value with the next node.
- If they are equal, skip the next node (removing the duplicate).
- If not, move to the next node.

##### Time Complexity:
- **O(n)** — We traverse the list once, where `n` is the number of nodes in the list.

##### Space Complexity:
- **O(1)** — No additional space is used apart from pointers.
***

***


#### Reverse Linked List
##### Pattern: Linked List
[Back to Top](#table-of-contents)
##### Description:
- Input: 1 -> 2 -> 3 -> 4 -> 5 -> null
- Output: 5 -> 4 -> 3 -> 2 -> 1 -> null
- Explanation: The linked list is reversed by changing the direction of the pointers for each node.

```java
// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class ReverseLinkedList {

    // Function to reverse the linked list
    public ListNode reverseList(ListNode head) {
        // Initialize the previous pointer to null
        ListNode prev = null;
        // Start with the head of the list
        ListNode curr = head;
        
        // Iterate through the list until we reach the end
        while (curr != null) {
            // Save the next node
            ListNode nextTemp = curr.next;
            // Reverse the current node's pointer
            curr.next = prev;
            // Move the previous pointer to the current node
            prev = curr;
            // Move the current pointer to the next node in the original list
            curr = nextTemp;
        }
        // At the end, prev will point to the new head of the reversed list
        return prev;
    }
}
```
##### Time Complexity:
- **O(n)**: We traverse the entire linked list once, where `n` is the number of nodes.

##### Space Complexity:
- **O(1)**: Only a constant amount of extra space is used (pointers for `prev`, `curr`, and `nextTemp`).

##### Algorithm Explanation:
- **Pointers Setup**:
    1. **prev** starts at `null`, **curr** starts at the head.
    2. For each node, store the next node in a temporary pointer.
    3. Reverse the link by pointing **curr** to **prev**.
    4. Update **prev** and **curr** for the next iteration.
    5. Return **prev** as the new head after all nodes are reversed.

***

#### Merge Two Sorted Lists
##### Pattern: Linked List
[Back to Top](#table-of-contents)

##### Description:
- **Input**: Two sorted linked lists:
  1. `1 -> 2 -> 4`
  2. `1 -> 3 -> 4`
- **Output**: A merged sorted linked list: `1 -> 1 -> 2 -> 3 -> 4 -> 4`
- **Explanation**: Merge the two sorted linked lists into a single sorted list.

```java
// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class MergeTwoSortedLists {
    // Function to merge two sorted lists
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // Initialize a dummy node to build the new list
        ListNode dummy = new ListNode(0);
        // 'current' is a pointer to the last node in the merged list
        ListNode current = dummy;

        // Traverse both lists while they are not null
        while (l1 != null && l2 != null) {
            // Compare the values in l1 and l2
            if (l1.val < l2.val) {
                // If l1 is smaller, add it to the result and move l1
                current.next = l1;
                l1 = l1.next;
            } else {
                // If l2 is smaller or equal, add it to the result and move l2
                current.next = l2;
                l2 = l2.next;
            }
            // Move the current pointer forward
            current = current.next;
        }

        // If either list still has nodes, append them to the result
        if (l1 != null) {
            current.next = l1;
        } else {
            current.next = l2;
        }

        // Return the merged list, starting from the next node of the dummy
        return dummy.next;
    }
}
```

##### Time Complexity:
- **O(n + m)** where `n` and `m` are the lengths of the two lists. We traverse each node once.

##### Space Complexity:
- **O(1)** excluding the space for the output list. Only a few pointers are used for merging.

***

- **Algorithm:**
  - Initialize a dummy node and a pointer (`current`) to track the merged list.
  - Traverse both lists:
    - Compare current nodes of `l1` and `l2`.
    - Append the smaller node to the merged list.
    - Move the respective pointer (`l1` or `l2`) forward.
  - If one list is exhausted, append the remainder of the other list.
***


#### Linked List Cycle
##### Pattern: Fast & Slow Pointers
[Back to Top](#table-of-contents)
##### Description:
- Input: A linked list with nodes connected in a cycle, e.g., `1 -> 2 -> 3 -> 4 -> 2` (the node with value `2` connects back to itself).
- Output: `true` (indicating the presence of a cycle) or `false` (indicating no cycle).
- Explanation: The function detects if there is a cycle in the linked list by using two pointers moving at different speeds.

```java
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        // Initialize two pointers, slow and fast
        ListNode slow = head;
        ListNode fast = head;

        // Traverse the list with the two pointers
        while (fast != null && fast.next != null) {
            slow = slow.next;            // Move slow pointer by 1
            fast = fast.next.next;      // Move fast pointer by 2
            
            // Check if both pointers meet
            if (slow == fast) {
                return true;            // Cycle detected
            }
        }
        return false;                   // No cycle found
    }
}
```
##### Time Complexity:
- O(n): The algorithm traverses the linked list at most twice, where n is the number of nodes in the list.

##### Space Complexity:
- O(1): Only a constant amount of space is used for the pointers.
***


#### Middle of the Linked List
##### Pattern: Fast and Slow Pointers
[Back to Top](#table-of-contents)
##### Description:
- Input: LinkedList: 1 → 2 → 3 → 4 → 5
- Output: 3
- Explanation: The middle node of the linked list is 3. For an even number of nodes, the second middle node is returned.

```java
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }
}

public class MiddleOfLinkedList {
    public ListNode middleNode(ListNode head) {
        // Initialize two pointers: slow and fast
        ListNode slow = head;
        ListNode fast = head;

        // Move fast pointer two steps and slow pointer one step
        while (fast != null && fast.next != null) {
            slow = slow.next;           // Move slow pointer one step
            fast = fast.next.next;      // Move fast pointer two steps
        }
        // When fast reaches the end, slow will be at the middle
        return slow; // Return the middle node
    }
}
```
##### Time Complexity:
- O(n): The algorithm traverses the linked list once, where n is the number of nodes.

##### Space Complexity:
- O(1): The algorithm uses a constant amount of space regardless of the input size.

***

#### Copy List with Random Pointer
##### Pattern: Linked List Manipulation
[Back to Top](#table-of-contents)

##### Description:
- Input: A linked list where each node contains a `next` pointer and a `random` pointer (which could point to any node or `null`).
- Output: A deep copy of the original list, where both `next` and `random` pointers are correctly replicated.
- Explanation: Given a complex linked list with `next` and `random` pointers, we need to create a completely independent copy of the list.

```java
// Node class for the linked list
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

public class Solution {
    // Function to copy the list
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        // Step 1: Create new nodes and insert them after original nodes
        Node current = head;
        while (current != null) {
            Node newNode = new Node(current.val); // Create copy
            newNode.next = current.next;          // Link copy to next node
            current.next = newNode;               // Place copy next to original node
            current = newNode.next;               // Move to next original node
        }

        // Step 2: Copy the random pointers
        current = head;
        while (current != null) {
            if (current.random != null) {
                current.next.random = current.random.next; // Point to copied random node
            }
            current = current.next.next;                   // Move to next original node
        }

        // Step 3: Separate the original and copied lists
        current = head;
        Node copiedHead = head.next;
        Node copyCurrent = copiedHead;

        while (current != null) {
            current.next = current.next.next;             // Restore original list
            if (copyCurrent.next != null) {
                copyCurrent.next = copyCurrent.next.next; // Connect copied nodes
            }
            current = current.next;
            copyCurrent = copyCurrent.next;
        }

        return copiedHead;  // Return the head of the copied list
    }
}
```

##### Time Complexity:
- **O(n)**: Each of the 3 steps (insertion, updating `random` pointers, and separation) runs through the list once.

##### Space Complexity:
- **O(1)**: Only constant space is used apart from the output list, as we modified the original list in-place before separating it.

[Back to Top](#table-of-contents)
***


