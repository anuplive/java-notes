

***
### Two-Pointer

#### Remove nth Node from End of List
##### Pattern: Two Pointer
[Back to Top](#Table-of-contents)
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
[Back to Top](#Table-of-contents)
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