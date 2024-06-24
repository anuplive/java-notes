# Arrays

=================
## Table of contents

<!--ts-->
| Category                                        | Problems                                                      |                                                   |                                                         |                                                       |
|:------------------------------------------------|:--------------------------------------------------------------|:--------------------------------------------------|:--------------------------------------------------------|:------------------------------------------------------|
| [Two Pointer](#two-pointer)                     | [Valid Palindrome](#valid-palindrome)                         | [Three Sum Problem](#three-sum-problem)           | [Container with most Water](#container-with-most-water) | [Product except itself](#product-except-itself)       |
| [Linked List](#linked-list)                     | [Reverse Linked List](#reverse-linked-list)                   | [Merge Two Sorted Lists](#merge-two-sorted-lists) | [Linked List Cycle](#linked-list-cycle)                 | [Remove Nth Node from End](#remove-nth-node-from-end) |
| [Triplet Sum](#triplet-sum)                     |                                                               |                                                   |                                                         |                                                       |
| [Sliding Window](#sliding-window)               | [Longest Substring No Repeats](#longest-substring-no-repeats) |                                                   |                                                         |                                                       |
| [Merging Intervals](#merging-intervals)         |                                                               |                                                   |                                                         |                                                       |
| [HashMap](#hashmap)                             | [Two-Sum](#two-sum)                                           |                                                   |                                                         |                                                       |
| [Max and Minimum Value](#max-and-minimum-value) | [Best time to Buy Stock](#best-time-to-buy-stock)             | [Max Sum Subarray](#max-sum-subarray)             |                                                         |                                                       |
| [Array Rotation](#array-rotation)               | [Rotate an Array](#rotate-an-array)                           |                                                   |                                                         |                                                       |
| [Cycle Detection](#cycle-detection)             | [Find Duplicate Number](#find-duplicate-number)               |                                                   |                                                         |                                                       |
| [Strings](#strings)                             | [Grouped Anargams](#grouped-anagrams)                         |                                                   |                                                         |                                                       |
| [Tree DFS](#tree-dfs)                           | [All Traversals](#all-traversals)                             |                                                   |                                                         |                                                       |
|                                                 |                                                               |                                                   |                                                         |                                                       |
|                                                 |                                                               |                                                   |                                                         |                                                       |


<!--te-->
***
[Back to Top](#Table-of-contents)
### Two Pointer

[Back to Top](#Table-of-contents)
#### Valid Palindrome

```java
/**
 Statement: Write a function that takes a string, s, as an input and determines whether or not it is a palindrome.
 */

import java.util.*;

public class Main{
    public static boolean isPalindrome(String s) {

        if(s == null || s.length() == 0)
            return false; 

        int start = 0; 
        int end = s.length() - 1; 

        while (end > start){
            if (s.charAt(end) != s.charAt(start))
                return false;
            start ++; 
            end --;    
        }

        return true;      
    }
}
/**
 Time complexity
 The time complexity is O(n), where n is the number of characters in the string. 
 However, our algorithm will only run O(n/2) times, since two pointers are traversing toward each other.

 Space complexity
 The space complexity is O(1), since we use constant space to store two indexes.
 */
```

#### Three Sum Problem

***
[Back to Top](#Table-of-contents)

### Linked List
[Back to Top](#Table-of-contents)

#### Reverse Linked List
- Pattern
  This problem belongs to the "Two Pointers" pattern.
- Example Inputs and Outputs
  Input: 1 -> 2 -> 3 -> 4 -> 5 -> null
  Output: 5 -> 4 -> 3 -> 2 -> 1 -> null
```java
// Definition for singly-linked list.
class ListNode {
  int val;
  ListNode next;
  ListNode(int x) { val = x; }
}

public class Solution {
  public ListNode reverseList(ListNode head) {
    // Initialize three pointers: previous, current, and next
    ListNode prev = null;
    ListNode curr = head;

    // Traverse the list
    while (curr != null) {
      // Store the next node
      ListNode nextTemp = curr.next;
      // Reverse the current node's pointer
      curr.next = prev;
      // Move pointers one position ahead
      prev = curr;
      curr = nextTemp;
    }
    // Return the new head of the reversed list
    return prev;
  }
}

```

###### Time Complexity:
- O(n), where n is the number of nodes in the linked list. We traverse each node exactly once.
###### Space Complexity:
O(1). We use a constant amount of extra space for the pointers (prev, curr, nextTemp).
***
#### Merge Two Sorted Lists
[Back to Top](#Table-of-contents)
##### Problem
- Merge two sorted linked lists and return it as a new sorted list. The new list should be made by splicing together the nodes of the first two lists.
- Input:
  List1: 1 -> 2 -> 4
  List2: 1 -> 3 -> 4
  Output: 1 -> 1 -> 2 -> 3 -> 4 -> 4
```java
// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class MergeTwoSortedLists {
    
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // Create a dummy node to act as the start of the new merged list
        ListNode dummy = new ListNode(0);
        // Tail points to the last node in the merged list
        ListNode tail = dummy;
        
        // Traverse both lists until we reach the end of either list
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                // If the current node in l1 is smaller or equal, add it to the merged list
                tail.next = l1;
                l1 = l1.next;
            } else {
                // If the current node in l2 is smaller, add it to the merged list
                tail.next = l2;
                l2 = l2.next;
            }
            // Move the tail to the last node
            tail = tail.next;
        }
        
        // If there are remaining nodes in l1 or l2, add them to the merged list
        if (l1 != null) {
            tail.next = l1;
        } else {
            tail.next = l2;
        }
        
        // The merged list starts at dummy.next
        return dummy.next;
    }
    
    // Helper method to print the list (for testing purposes)
    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}

```
##### Time Complexity: O(n + m)
- Where n is the number of nodes in the first list and m is the number of nodes in the second list.
  Each node is processed exactly once.
##### Space Complexity: O(1)
- We are only using a constant amount of extra space (for the dummy and tail nodes),
- not including the space required for the output list which is allocated dynamically.
***
#### Linked List Cycle
[Back to Top](#Table-of-contents)
##### Problem:
Given a linked list, determine if it has a cycle in it.
###### Example
- Input: 3 -> 2 -> 0 -> -4 -> 2 (cycle starts at node with value 2)
  Output: true

- Input: 1 -> 2 -> 1 (cycle starts at node with value 1)
  Output: true
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
    /**
     * Detects if a cycle exists in the given linked list.
     * Uses Floyd's Tortoise and Hare algorithm to detect the cycle.
     *
     * @param head the head of the linked list
     * @return true if there is a cycle, false otherwise
     */
    public boolean hasCycle(ListNode head) {
        // Edge case: if the list is empty or has only one node, no cycle can exist
        if (head == null || head.next == null) {
            return false;
        }

        // Initialize two pointers, slow (tortoise) and fast (hare)
        ListNode slow = head;
        ListNode fast = head;

        // Traverse the list
        while (fast != null && fast.next != null) {
            // Move slow pointer by one step
            slow = slow.next;
            // Move fast pointer by two steps
            fast = fast.next.next;

            // If slow and fast pointers meet, a cycle exists
            if (slow == fast) {
                return true;
            }
        }

        // If we reach here, there is no cycle
        return false;
    }
}
```
###### Time Complexity:
- O(n): In the worst case, we might need to traverse the entire list once to detect a cycle.
###### Space Complexity:
- O(1): We use a constant amount of extra space regardless of the input size.
***
#### Remove Nth Node from End
[Back to Top](#Table-of-contents)
##### Problem: Given a linked list, remove the nth node from the end of the list and return its head.
- Input: 1 -> 2 -> 3 -> 4 -> 5, n = 2
  Output: 1 -> 2 -> 3 -> 5
  Explanation: After removing the second node from the end, the linked list becomes 1 -> 2 -> 3 -> 5.
```java
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Create a dummy node to handle edge cases and ease of deletion
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        // Initialize two pointers: fast and slow
        ListNode fast = dummy;
        ListNode slow = dummy;
        
        // Move fast pointer n steps ahead
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        
        // Move both fast and slow pointers until fast reaches the end
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        
        // Now slow is at the node just before the one to be deleted
        slow.next = slow.next.next;
        
        // Return the head of the updated list
        return dummy.next;
    }
}

```
##### Time Complexity:
- O(L), where L is the length of the linked list.
  We need to iterate over the list once with the fast pointer and once with the slow pointer.
##### Space Complexity:
- We only use a constant amount of extra space regardless of the input size.
*


### Triplet Sum
- Statement
  Given an array of integers, nums, and an integer target.
  target, that is, nums[i] + nums[j] + nums[k] == target.

Determine if there are any three integers in nums whose sum equals target.
Return TRUE if such integers exist.
Return FALSE otherwise.

```java
import java.util.*; // Import the utility package which contains the Arrays class

public class SumOfThree {

    // Method to find if there are three numbers in the array that sum up to the target value
    public static boolean findSumOfThree(int[] nums, int target) {
        // Check if the input array is null or has less than 3 elements
        if (nums == null || nums.length < 3)
            return false;

        // Sort the array to enable the two-pointer approach
        Arrays.sort(nums);

        // Iterate through the array up to the third last element
        for (int i = 0 ; i < nums.length - 2 ; i ++) {
            int left = i + 1; // Initialize the left pointer to the element after the current one
            int right = nums.length - 1; // Initialize the right pointer to the last element of the array

            // While there are elements between the left and right pointers
            while (right > left) {
                // Check if the sum of the elements at the left and right pointers plus the current element equals the target
                if ((nums[left] + nums[right]) == (target - nums[i])) {
                    return true; // If found, return true
                } else if ((nums[left] + nums[right]) > (target - nums[i])) {
                    right--; // If the sum is greater than the target, move the right pointer to the left
                } else {
                    left++; // If the sum is less than the target, move the left pointer to the right
                }
            }
        }
        // If no such triplet is found, return false
        return false;
    }
}
```
##### Time Complexity
- Sorting the array: O(nlog(n))
- Nested loop: O(n^2)
- Total time complexity: O(nlog(n) + n^2) => O(n^2)

##### Space Complexity
- Using Arrays.sort(): O(log(n))

***
[Back to Top](#Table-of-contents)

#### Container with most Water
##### Statement
- Given an integer array `height` of length `n`, and `n` vertical lines drawn such that the two endpoints of the `i-th` line are `(i, 0)` and `(i, height[i])`.
- Find two lines from the input array that, together with the x-axis, form a container that holds as much water as possible.
- Return the maximum amount of water a container can store.

```java
import java.util.*;

public class Solution {
    // Method to find the container with most water
    public static int containerWithMostWater(int[] height) {
        // Check if the height array is valid
        if (height == null || height.length < 2)
            return -1; // Not enough elements to form a container

        int start = 0; // Start index of the container
        int end = height.length - 1; // End index of the container
        int maxArea = Integer.MIN_VALUE; // Variable to store the maximum area

        // Loop until the two pointers meet
        while (end > start) {
            int width = end - start; // Calculate the width of the container
            int tempHeight = Math.min(height[end], height[start]); // Calculate the height of the container
            maxArea = Math.max(maxArea, width * tempHeight); // Update the maximum area

            // Move the pointer which has the smaller height
            if (height[end] < height[start]) {
                end--; // Move the end pointer inward
            } else {
                start++; // Move the start pointer inward
            }
        }

        return maxArea; // Return the maximum area found
    }
}

```
##### Time Complexity:
O(n) - where n is the length of the height array. This code makes a single pass through the array with two pointers.

##### Space Complexity:
O(1) - Constant extra space is used, regardless of the size of the input.

***

[Back to Top](#Table-of-contents)
#### Product Except Itself
##### Problem Statement:
Given an integer array `arr`, return an array `res` where `res[i]` is the product of all elements of `arr` except `arr[i]`.

##### Constraints:
1. The algorithm must run in O(n) time.
2. Division operation is not allowed.

```java
public class Solution {

    // Method to calculate the product of array elements except self
    public static int[] productExceptSelf(int[] arr) {
        
        // Check for null or empty input array
        if (arr == null || arr.length < 1)
            return null; 
        
        // Initialize accumulators for left and right scans
        int leftAcc = 1, rightAcc = 1;  
        
        // Result array to store the final products
        int[] result = new int[arr.length]; 

        // Left Scan: Compute product of elements to the left of each index
        for (int i = 0; i < arr.length; i++) {
            result[i] = leftAcc; // Store the left accumulated product
            leftAcc *= arr[i];   // Update the left accumulator
        }

        // Right Scan: Compute product of elements to the right of each index
        for (int j = arr.length - 1; j >= 0; j--) {
            result[j] *= rightAcc; // Multiply with the right accumulated product
            rightAcc *= arr[j];    // Update the right accumulator
        } 

        // Return the result array
        return result; 
    }
}
```
*Time Complexity*
- Initialization: O(1) , Left Scan: O(n), Right Scan: O(n)
- Total Time Complexity: O(n)

*Space Complexity*
- Auxiliary Space: O(1) (ignoring the input and output arrays)
- Output Array: O(n)
- Total Space Complexity: O(n)




***
[Back to Top](#Table-of-contents)

### Sliding Window
#### Sliding Window Fruit Basket Problem

##### Problem: Sliding Window Fruit Basket Problem
##### Pattern: Sliding Window
[Back to Top](#Table-of-contents)
##### Description:
- Input: `[1, 2, 1, 2, 3, 3, 1]`
- Output: `4`
- Explanation: The longest subarray with at most 2 different types of fruits is `[1, 2, 1, 2]`.

```java
import java.util.HashMap;
import java.util.Map;

public class FruitBasket {
    public static int totalFruit(int[] fruits) {
        // Map to store the count of each type of fruit
        Map<Integer, Integer> count = new HashMap<>();
        int maxFruits = 0; // Variable to keep track of the maximum number of fruits
        int start = 0; // Start index of the sliding window

        for (int end = 0; end < fruits.length; end++) {
            // Add the fruit at the end of the window to the count map
            count.put(fruits[end], count.getOrDefault(fruits[end], 0) + 1);

            // If we have more than 2 types of fruits, shrink the window from the start
            while (count.size() > 2) {
                count.put(fruits[start], count.get(fruits[start]) - 1);
                if (count.get(fruits[start]) == 0) {
                    count.remove(fruits[start]);
                }
                start++;
            }

            // Update the maximum number of fruits found
            maxFruits = Math.max(maxFruits, end - start + 1);
        }

        return maxFruits;
    }

    public static void main(String[] args) {
        int[] fruits1 = {1, 2, 1, 2, 3, 3, 1};
        System.out.println(totalFruit(fruits1)); // Output: 5
        
        int[] fruits2 = {0, 1, 2, 2};
        System.out.println(totalFruit(fruits2)); // Output: 3

        int[] fruits3 = {1, 2, 3, 2, 2};
        System.out.println(totalFruit(fruits3)); // Output: 4

        int[] fruits4 = {3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4};
        System.out.println(totalFruit(fruits4)); // Output: 5
    }
}
```
##### Time Complexity:
- The time complexity is `O(n)` where `n` is the number of fruits in the array, as we traverse each fruit once.

##### Space Complexity:
- The space complexity is `O(1)`, as the hashmap will store at most 3 types of fruits at any time.
***

#### Longest Substring No Repeats
- Problem Description: Given a string, find the length of the longest substring without repeating characters.

###### Example:
- Input: "abcabcbb"
- Output: 3 ("abc" is the longest substring without repeating characters)

- Input: "bbbbb"
- Output: 1 ("b" is the longest substring without repeating characters)

- Input: "pwwkew"
- Output: 3 ("wke" is the longest substring without repeating characters)

```java
import java.util.*;

public class LongestSubstringWithoutRepeatingCharacters {
  public int lengthOfLongestSubstring(String s) {
    int n = s.length();
    Set<Character> set = new HashSet<>(); // Set to track characters in the current window
    int longest = 0; // Length of the longest substring found
    int left = 0; // Left pointer of the sliding window
    int right = 0; // Right pointer of the sliding window

    while (right < n) {
      // Try to extend the window [left, right]
      if (!set.contains(s.charAt(right))) {
        // If the character at 'right' is not in the set, add it to the set and move 'right' pointer
        set.add(s.charAt(right));
        right++;
        // Update 'longest' with the maximum length found so far
        longest = Math.max(longest, right - left);
      } else {
        // If the character at 'right' is already in the set, remove character at 'left' from the set
        // and move 'left' pointer until the character at 'right' is no longer in the set
        set.remove(s.charAt(left));
        left++;
      }
    }
    return longest;
  }
}
```
###### Time Complexity:
- O(n), where n is the length of the input string.
- Each character is visited at most twice, once by the right pointer and once by the left pointer.
###### Space Complexity: O(min(m, n)),
- where m is the size of the character set (in this case, the set of characters in the input string)
- and n is the length of the input string.


***

#### Find Maximum in Sliding Window
##### Pattern: Sliding Window - TODO
[Back to Top](#Table-of-contents)
##### Description:
- Input: `nums = [1,3,-1,-3,5,3,6,7], k = 3`
- Output: `[3,3,5,5,6,7]`
- Explanation: The maximum values in each sliding window of size `k` are `[3, 3, 5, 5, 6, 7]`.

```java
import java.util.*;

public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];
        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            // Remove elements out of this window
            if (!deque.isEmpty() && deque.peekFirst() == i - k) {
                deque.pollFirst();
            }

            // Remove elements smaller than the current element from the deque
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            // Add the current element at the back of the deque
            deque.offerLast(i);

            // The front of the deque is the largest element in the current window
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        SlidingWindowMaximum solver = new SlidingWindowMaximum();
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println(Arrays.toString(solver.maxSlidingWindow(nums, k)));
    }
}
```
##### Time Complexity:
- O(N), where N is the length of the array. Each element is processed at most twice (once added and once removed from the deque).

##### Space Complexity:
- O(K), where K is the size of the sliding window. This is the maximum size of the deque.
***

#### Minimum Window Subsequence
##### Pattern: Sliding Window
[Back to Top](#Table-of-contents)
##### Description:
- **Input:**
    - S = "abcdebdde", T = "bde"
- **Output:**
    - "bcde"
- **Explanation:**
    - The minimum window in S that contains all the characters in T (in order) is "bcde". The substring "bdde" is also a valid window but not the minimum.

```java

import java.util.*;

public class Main {
    public static String minWindow(String s, String t) {
        // Edge cases: if input strings are null or s is shorter than t
        if (s == null || t == null || s.length() < t.length())
            return "";

        int sLen = s.length();
        int tLen = t.length();
        int minLength = Integer.MAX_VALUE;
        String result = "";

        // Iterate over each character in s
        for (int i = 0; i < sLen; i++) {
            // Check if current character in s matches the first character in t
            if (s.charAt(i) == t.charAt(0)) {
                int tempStart = i;
                int j = 0;
                int end = i;

                // Try to find the entire t in s starting from i
                while (end < sLen && j < tLen) {
                    if (s.charAt(end) == t.charAt(j)) {
                        j++;
                    }
                    end++;
                }

                // If a valid subsequence is found
                if (j == tLen) {
                    // Calculate the length of the current valid subsequence
                    int subSeqLength = end - tempStart;

                    // Update the result if this subsequence is shorter than the previously found one
                    if (minLength > subSeqLength) {
                        minLength = subSeqLength;
                        result = s.substring(tempStart, end);
                    }
                }
            }
        }

        return result;
    }
}

```
##### Time Complexity:
- The time complexity is O(S * T), where S is the length of the string S and T is the length of the string T. This is because for each character in S, we potentially traverse the entire string T.

##### Space Complexity:
- The space complexity is O(1) as we are using a constant amount of extra space.

***


#### Longest Repeating Character Replacement
##### Pattern: Sliding Window TODO
[Back to Top](#Table-of-contents)
##### Description:
- Input: `"AABABBA", k = 1`
- Output: `4`
- Explanation: Replace one 'B' with 'A' to get the longest repeating substring "AAAA".

```java
public class LongestRepeatingCharacterReplacement {
    public static int characterReplacement(String s, int k) {
        // Array to count the frequency of characters in the current window
        int[] count = new int[26];
        int maxCount = 0; // Maximum count of a single character in the current window
        int maxLength = 0; // Result: the length of the longest substring
        int start = 0; // Left pointer of the window

        for (int end = 0; end < s.length(); end++) {
            // Increment the count of the current character
            maxCount = Math.max(maxCount, ++count[s.charAt(end) - 'A']);

            // If the current window size minus the count of the most frequent character
            // is greater than k, shrink the window from the left
            while (end - start + 1 - maxCount > k) {
                count[s.charAt(start) - 'A']--;
                start++;
            }

            // Update the maxLength
            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;
    }

    // Main method for testing
    public static void main(String[] args) {
        System.out.println(characterReplacement("AABABBA", 1)); // Output: 4
        System.out.println(characterReplacement("ABAB", 2)); // Output: 4
    }
}
```
##### Time Complexity:
- The time complexity is O(n), where n is the length of the string. We process each character at most twice (once when expanding the window and once when shrinking it).

##### Space Complexity:
- The space complexity is O(1), since the size of the count array is fixed (26 for English alphabets).
***




### Merging Intervals


***

### HashMap
[Back to Top](#Table-of-contents)
#### Two-Sum
```java
import java.util.HashMap;
import java.util.Map;
public class TwoSum {
  public int[] twoSum(int[] nums, int target) {
    // Create a map to store the value and its index
    Map<Integer, Integer> map = new HashMap<>();

    // Iterate over the array
    for (int i = 0; i < nums.length; i++) {
      // Calculate the complement of the current number
      int complement = target - nums[i];

      // Check if the complement is already in the map
      if (map.containsKey(complement)) {
        // If found, return the indices of the complement and the current number
        return new int[] { map.get(complement), i };
      }

      // If not found, add the current number and its index to the map
      map.put(nums[i], i);
    }

    // If no solution is found, throw an exception
    throw new IllegalArgumentException("No two sum solution");
  }
}
 
```
##### Time Complexity
O(n): The algorithm iterates through the array only once.
- Each lookup and insertion in the HashMap is O(1) on average.
- Therefore, the overall time complexity is linear, i.e., O(n), where n is the number of elements in the array.
##### Space Complexity
O(n): In the worst case, all the elements might be stored in the HashMap.
- Hence, the space complexity is linear, i.e., O(n), where n is the number of elements in the array
***

### Dynamic Programming
[Back to Top](#Table-of-contents)

#### Best time to Buy Stock
```java
 public class BestTimeToBuyAndSellStock {

  public int maxProfit(int[] prices) {
    if (prices == null || prices.length == 0) {
      return 0;
    }

    int minPrice = Integer.MAX_VALUE;
    int maxProfit = 0;

    for (int price : prices) {
      // Update the minimum price seen so far
      if (price < minPrice) {
        minPrice = price;
      }
      // Calculate the potential profit and update maxProfit if it's higher than the current maxProfit
      else if (price - minPrice > maxProfit) {
        maxProfit = price - minPrice;
      }
    }

    return maxProfit;
  } 
}
```
###### Time and Space Complexity:
- Time Complexity: O(n)
- We only pass through the array once with a single for-loop, where n is the length of the prices array.
###### Space Complexity: O(1)
- We use a constant amount of extra space (for variables minPrice and maxProfit).

***
#### Max Sum Subarray
[Back to Top](#Table-of-contents)
###### Kanane's Agorithm

```java
public class MaximumSubarray {
    public static int maxSubArray(int[] nums) {
        // Initialize variables to store the maximum sum
        int maxCurrent = nums[0];
        int maxGlobal = nums[0];

        // Loop through the array starting from the second element
        for (int i = 1; i < nums.length; i++) {
            // Update the maximum sum ending at the current position
            maxCurrent = Math.max(nums[i], maxCurrent + nums[i]);
            
            // Update the global maximum sum if the current max is greater
            if (maxCurrent > maxGlobal) {
                maxGlobal = maxCurrent;
            }
        }
        return maxGlobal;
    }
}
```
###### Time and Space Complexity:
- Time Complexity: O(n), where n is the length of the input array. We traverse the array once.
###### Space Complexity: O(1),
- we use a constant amount of extra space for the variables maxCurrent and maxGlobal.
***


### Array Rotation
[Back to Top](#Table-of-contents)

##### Rotate an Array
###### Problem Description
- Given an array, rotate the array to the right by k steps, where k is non-negative.
- Input :
  int[] nums = {1, 2, 3, 4, 5, 6, 7};
  int k = 3;
- Output:
  {5, 6, 7, 1, 2, 3, 4}
```java
public class RotateArray {
  public void rotate(int[] nums, int k) {
    int n = nums.length;
    k = k % n; // in case k is larger than the array length

    // Reverse the whole array
    reverse(nums, 0, n - 1);

    // Reverse the first k elements
    reverse(nums, 0, k - 1);

    // Reverse the rest of the elements
    reverse(nums, k, n - 1);
  }
  private void reverse(int[] nums, int start, int end) {
    while (start < end) {
      int temp = nums[start];
      nums[start] = nums[end];
      nums[end] = temp;
      start++;
      end--;
    }
  }
}
```
###### Time Complexity:
- O(n), where n is the number of elements in the array.
###### Space Complexity:
- O(1), because we do not use any extra space.
***

### Cycle Detection
[Back to Top](#Table-of-contents)
##### Find Duplicate Number
###### Problem :
- Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n]
 inclusive, there is exactly one repeated number in the array. Return the duplicate number.

- Example Inputs and Expected Outputs
Input: [1,3,4,2,2]
Output: 2

Input: [3,1,3,4,2]
Output: 3

##### Equation
At meeting point.
- Distance (Slow) = F + a
- Distance (Fast) = F + a + C
- distancy(Fast) = 2 * distance(slow)
- *F = C - a*

```java
public class FindDuplicate {
  public static int findDuplicate(int[] nums) {
    // Phase 1: Finding the intersection point of the two runners.
    int slow = nums[0];
    int fast = nums[0];

    // Move slow by 1 step and fast by 2 steps
    do {
      slow = nums[slow];
      fast = nums[nums[fast]];
    } while (slow != fast);

    // Phase 2: Finding the entrance to the cycle
    slow = nums[0];
    while (slow != fast) {
      slow = nums[slow];
      fast = nums[fast];
    }

    return slow; // The duplicate number
  }
}
```
###### Time Complexity
- O(n): Each pointer travels at most twice through the list.
###### Space Complexity
- O(1): We use only a constant amount of extra space.
***

### Strings
[Back to Top](#Table-of-contents)
#### Grouped Anagrams
##### Problem
- Given an array of strings, group the anagrams together.
- An anagram is a word formed by rearranging the letters of another, such as "eat" and "tea".

- Input
  ["eat", "tea", "tan", "ate", "nat", "bat"]
- Output
  [  ["eat", "tea", "ate"],
  ["tan", "nat"],
  ["bat"]  ]

```java
import java.util.*;

public class GroupAnagrams {

    public static List<List<String>> groupAnagrams(String[] strs) {
        // Initialize a HashMap to store the grouped anagrams
        Map<String, List<String>> map = new HashMap<>();

        // Iterate through each string in the input array
        for (String str : strs) {
            // Convert the string to a char array
            char[] charArray = str.toCharArray();
            // Sort the char array
            Arrays.sort(charArray);
            // Convert the sorted char array back to a string
            String sortedStr = new String(charArray);

            // If the sorted string is not in the map, add it with a new list
            if (!map.containsKey(sortedStr)) {
                map.put(sortedStr, new ArrayList<>());
            }
            // Add the original string to the list of its sorted version
            map.get(sortedStr).add(str);
        }
        // Return all the values (groups of anagrams) from the map
        return new ArrayList<>(map.values());
    }
}

```
##### Time Complexity:
- O(N⋅K log K), where N is the number of strings and K is the maximum length of a string.
- Sorting each string takes O(K log K) and we do this for each of the N strings.

##### Space Complexity:
O(N⋅K), where N is the number of strings and K is the maximum length of a string.
We store all strings in the hash map
***

### Tree DFS
- Full BT: Every node has 0 or 2 children.
- Complete BT: All levels are fully filled except possibly the last level.
- Perfect BT: All internal nodes have two children and all leaves are at the same level.
- Balanced BT: Heights of subtrees differ by no more than 1.
- Skewed BT: All nodes have only one child, creating a linear tree structure.

[Back to Top](#Table-of-contents)
#### All Traversals
##### Problem: Iterative and Recursive code for  DFS
- In-ORDER, Pre-ORDER and Post-ORDER Traversals

##### Pre-ORDER  ( N - left - right)
```java
// In-order Recursive
class AllTraversals{
  // ################### DFS PREORDER RECURSIVE ################################
  
  public List<Integer> preOrderRecursive(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    dfsPreorder(root, result);
    return result;
  }
  // Helper function to recursively traverse the tree
    private void dfsPreorder(TreeNode node, List<Integer> result) {
        if (node == null) {
        return; // Base case: if the node is null, return
        }

        result.add(node.val); // Visit the root node
        dfsPreorder(node.left, result); // Traverse the left subtree
        dfsPreorder(node.right, result); // Traverse the right subtree
        }
        
  // ################### DFS PREORDER ITERATIVE ################################
  
  public List<Integer> preOredrIterative(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) {
      return result; // If the tree is empty, return an empty list
    }

    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);

    while (!stack.isEmpty()) {
      TreeNode node = stack.pop();
      result.add(node.val); // Visit the root node

      // Push right child first so that the left child is processed first
      if (node.right != null) {
        stack.push(node.right);
      }
      if (node.left != null) {
        stack.push(node.left);
      }
    }

    return result;
  }
}
```
[Back to Top](#Table-of-contents)
##### IN-ORDER TRAVERSAL [left - Node - right]
- Input
        Tree:  
          1
        /   \
       2     3
      / \
     4   5
- Output: [4, 2, 5, 1, 3]

```java
// In-order Recursive
class AllTraversals{
  // ################### DFS IN-ORDER  RECURSIVE ################################
  
  public List<Integer> inOrderRecursive(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    dfsPreorder(root, result);
    return result;
  }
  // Helper function to recursively traverse the tree
    private void dfsIneorder(TreeNode node, List<Integer> result) {
        if (node == null) {
        return; // Base case: if the node is null, return
        }

      dfsIneorder(node.left, result); // Traverse the left subtree
      result.add(node.val); // Visit the root node
      dfsIneorder(node.right, result); // Traverse the right subtree
        }
        
  // ################### DFS InORDER ITERATIVE ################################
  
  public List<Integer> preOredrIterative(TreeNode root) {
    if (root == null) {
      return result; // If the tree is empty, return an empty list
    }
    List<Integer> result = new ArrayList<>();
    Stack<TreeNode> stack = new Stack<>();
    TreeNode current = root;

    // Traverse the tree
    while (current != null || !stack.isEmpty()) {
      // Reach the left most Node of the current Node
      while (current != null) {
        stack.push(current);
        current = current.left;
      }
      // Current must be null at this point
      current = stack.pop();
      result.add(current.val); // Add the node value to result
      current = current.right; // Visit the right subtree
    }

    return result;

  }
}

```
[Back to Top](#Table-of-contents)
##### POST-ORDER (left - right - Node)
- Input Tree:
        1
      /   \
     2     3
    / \
   4   5

- Output
  [4, 5, 2, 3, 1]
```java
// Post-Order Recursive
class AllTraversals {
  // ################### DFS POST ORDER RECURSIVE ################################

  public List<Integer> postOrderRecursive(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    dfsPostOrder(root, result);
    return result;
  }

  // Helper function to recursively traverse the tree
  private void dfsPostorder(TreeNode node, List<Integer> result) {
    if (node == null) {
      return; // Base case: if the node is null, return
    }

    dfsPostOrder(node.left, result); // Traverse the left subtree
    dfsPostOrder(node.right, result); // Traverse the right subtree
    result.add(node.val); // Visit the root node
  }

  // ################### DFS POST-ORDER ITERATIVE ################################
  
  public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) return result;

    Stack<TreeNode> stack = new Stack<>();
    TreeNode current = root;
    TreeNode lastVisited = null;

    while (!stack.isEmpty() || current != null) {
      // Reach the left most Node of the current Node
      while (current != null) {
        stack.push(current);
        current = current.left;
      }

      // Peek the node from the stack
      current = stack.peek();

      // If the right node is null or already visited, process the current node
      if (current.right == null || current.right == lastVisited) {
        result.add(current.val);
        stack.pop();
        lastVisited = current;
        current = null; // Reset the current node
      } else {
        // Otherwise, visit the right subtree
        current = current.right;
      }
    }

    return result;
  }
  
}

```
##### Time Complexity:
- O(n), where n is the number of nodes in the tree. This is because each node is visited exactly once.
##### Space Complexity:
- O(n), where n is the number of nodes in the tree.
- The space is used by the stack in the worst case (when the tree is completely unbalanced).
