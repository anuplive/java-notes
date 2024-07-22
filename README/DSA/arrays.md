# Arrays
[HOME.md](HOME.md)
=================
## Table of contents

<!--ts-->
| Category                                        | Problems                                                      |                                                                           |                                                         |                                                               |
|:------------------------------------------------|:--------------------------------------------------------------|:--------------------------------------------------------------------------|:--------------------------------------------------------|:--------------------------------------------------------------|
| [Two Pointer](#two-pointer)                     | [Valid Palindrome](#valid-palindrome)                         | [Three Sum Problem](#three-sum-problem)                                   | [Container with most Water](#container-with-most-water) | [Product except itself](#product-except-itself)               |
| [Linked List](#linked-list)                     | [Reverse Linked List](#reverse-linked-list)                   | [Merge Two Sorted Lists](#merge-two-sorted-lists)                         | [Linked List Cycle](#linked-list-cycle)                 | [Remove Nth Node from End](#remove-nth-node-from-end)         |
| [Searching](#searching)                         | [Binary Search](#binary-search)                               | [Binary Search Rotated Sorted Array](#binary-search-rotated-sorted-array) | [Binary Search 2D Matrix](#binary-search-2d-matrix)     | [Binary Search Order-Agnostic](#binary-search-order-agnostic) |
| [Triplet Sum](#triplet-sum)                     |                                                               |                                                                           |                                                         |                                                               |
| [Sliding Window](#sliding-window)               | [Longest Substring No Repeats](#longest-substring-no-repeats) |                                                                           |                                                         |                                                               |
| [Merging Intervals](#merging-intervals)         |                                                               |                                                                           |                                                         |                                                               |
| [HashMap](#hashmap)                             | [Two-Sum](#two-sum)                                           |                                                                           |                                                         |                                                               |
| [Max and Minimum Value](#max-and-minimum-value) | [Best time to Buy Stock](#best-time-to-buy-stock)             | [Max Sum Subarray](#max-sum-subarray)                                     |                                                         |                                                               |
| [Array Rotation](#array-rotation)               | [Rotate an Array](#rotate-an-array)                           |                                                                           |                                                         |                                                               |
| [Cycle Detection](#cycle-detection)             | [Find Duplicate Number](#find-duplicate-number)               |                                                                           |                                                         |                                                               |
| [Strings](#strings)                             | [Grouped Anargams](#grouped-anagrams)                         |                                                                           |                                                         |                                                               |
| [Tree DFS](#tree-dfs)                           | [All Traversals](#all-traversals)                             |                                                                           |                                                         |                                                               |
| [Matrix](#matrix)                               |                                                               |                                                                           |                                                         |                                                               |
|                                                 |                                                               |                                                                           |                                                         |                                                               |

[HOME.md](HOME.md)
<!--te-->

***
[Back to Top](#table-of-contents)
### Two Pointer

###### Remove Duplicates, White Spaces etc using two Pointers

- Remove Duplicates from Sorted Array
- Remove Element
- Move Zeroes
- Remove Duplicates from Sorted Array II
- Valid Palindrome
- Reverse String
- String to Integer (atoi)

[Back to Top](#table-of-contents)

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

***

### Three Sum Problem
#### Three Sum Target
##### Pattern: Two Pointers
[Back to Top](#Table-of-contents)
##### Description:
- **Input:** nums = [1, 2, -1, 0, -2, 1], target = 0
- **Output:** [[-2, 1, 1], [-1, 0, 1]]
- **Explanation:** The triplets [-2, 1, 1] and [-1, 0, 1] sum up to the target 0.

```java
import java.util.*;

public class ThreeSumTarget {
    public List<List<Integer>> threeSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // Sort the array to make two-pointer technique possible
        Arrays.sort(nums);
        
        // Iterate through the array
        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicate elements
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            // Two pointers
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == target) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // Skip duplicate elements
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }
}
```
##### Time Complexity:
- The time complexity is \(O(n^2)\) due to the nested loops: one for iterating through the array and one for the two-pointer traversal.

##### Space Complexity:
- The space complexity is \(O(m)\) where \(m\) is the number of triplets found because of the space required to store the output list.
***





### Searching
#### Binary Search

[Back to Top](#table-of-contents)
###### Description:
- Input:
  - Array: [1, 3, 5, 7, 9, 11, 13]
  - Target: 7
- Output:
  - Index: 3
- Explanation:
  - In the given sorted array, the value 7 is found at index 3.
```java
public class BinarySearch{
public int binarySearch(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        if (nums[mid] == target) {
            return mid; // Found the target
        } else if (nums[mid] < target) {
            left = mid + 1; // Target is in the right half
        } else {
            right = mid - 1; // Target is in the left half
        }
    }
    
    return -1; // Target not found
}}
```
##### Time Complexity:
- Binary search has a time complexity of O(log n), where n is the number of elements in the array.
##### Space Complexity:
- Binary search has a space complexity of O(1) because it uses a constant amount of extra space.
***


#### Binary Search Rotated Sorted Array
###### Pattern: Binary Search
[Back to Top](#table-of-contents)
##### Description:
- Input: nums = [4, 5, 6, 7, 0, 1, 2], target = 0
- Output: 4
- Explanation: In the rotated sorted array nums, the index of target 0 is 4.
```java
public class Solution {
    public int search(int[] nums, int target) {
        // Implementation of binary search on rotated sorted array
        int left = 0, right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target)
                return mid;
            
            if (nums[left] <= nums[mid]) { // left half is sorted
                if (nums[left] <= target && target < nums[mid])
                    right = mid - 1;
                else
                    left = mid + 1;
            } else { // right half is sorted
                if (nums[mid] < target && target <= nums[right])
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }
        return -1;
    }
}
```
##### Time Complexity:
- O(log n) - Binary search
##### Space Complexity:
- O(1) - Constant space complexity
***

#### Binary Search 2D Matrix
##### Pattern: Binary Search
[Back to Top](#table-of-contents)
##### Description:
- Input: A 2D matrix `matrix` and an integer `target`.
 int[][] matrix = {
      {1, 3, 5, 7},
      {10, 11, 16, 20},
      {23, 30, 34, 60}
  };
  int target = 3;
- Output: `true` if `target` is found in the matrix, `false` otherwise.
- Explanation: The matrix is sorted in a way that each row is sorted and the first integer of each row is greater than the last integer of the previous row. Perform binary search to find the target.

```java
public class Search2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        // Check for empty matrix
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        // Binary search in the whole matrix treated as a 1D array
        int left = 0, right = rows * cols - 1;
        
        while (left <= right) {
            
            int mid = left + (right - left) / 2;
            // Find the mid element
            int midElement = matrix[mid / cols][mid % cols];
            
            if (midElement == target) {
                return true;
            } else if (midElement < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return false; // Target not found
    }
}
```
##### Time Complexity:
- O(log(m * n)), where m is the number of rows and n is the number of columns. This is because we are performing binary search on a 1D representation of the matrix.
##### Space Complexity:
- O(1), as we are using a constant amount of extra space.
***


#### Binary Search Order-Agnostic
##### Pattern: Binary Search
[Back to Top](#table-of-contents)
##### Description:
   Input: `arr = [50, 40, 30, 20, 10]`, `target = 30`
   Output: `2`
   Explanation: The target value 30 is located at index 2 in the descending sorted array.

```java
public class OrderAgnosticBinarySearch {
    public static int binarySearch(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;

        // Check if array is sorted in ascending or descending order
        boolean isAscending = arr[start] < arr[end];

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (arr[mid] == target) {
                return mid; // Target found at mid
            }

            if (isAscending) {
                if (target < arr[mid]) {
                    end = mid - 1; // Move the end to mid - 1 if target is less than mid element
                } else {
                    start = mid + 1; // Move the start to mid + 1 if target is greater than mid element
                }
            } else {
                if (target > arr[mid]) {
                    end = mid - 1; // Move the end to mid - 1 if target is greater than mid element (descending order)
                } else {
                    start = mid + 1; // Move the start to mid + 1 if target is less than mid element (descending order)
                }
            }
        }

        return -1; // Target not found
    }
}
```
##### Time Complexity:
- The time complexity of this algorithm is O(log n), where n is the number of elements in the array. This is because we are dividing the search range by half in each step of the algorithm.

##### Space Complexity:
- The space complexity is O(1) as we are using a constant amount of extra space regardless of the size of the input array.
***


#### Infinite Array Binary Search
##### Pattern: Binary Search
[Back to Top](#table-of-contents)
##### Description:
Example:
- Input: `arr[] = {1, 3, 5, 7, 9, 10, 15, ...}`, `x = 10`
- Output: `5`
- Explanation: The target value `10` is located at index `5` in the array.

```java
public class InfiniteArrayBinarySearch {
    
    // Function to find the position of an element in an infinite sorted array
    public static int findPosition(int[] arr, int x) {
        int left = 0;
        int right = 1;
        
        // First, find the range for binary search
        while (arr[right] < x) {
            left = right;
            right = 2 * right; // Exponentially increase the range
        }
        
        // Perform binary search within the found range
        return binarySearch(arr, left, right, x);
    }
   
    // Helper function to perform binary search
    public static int binarySearch(int[] arr, int left, int right, int x) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            // Check if x is present at mid
            if (arr[mid] == x) {
                return mid;
            }
            
            // If x greater, ignore left half
            if (arr[mid] < x) {
                left = mid + 1;
            } else {
                // If x is smaller, ignore right half
                right = mid - 1;
            }
        }
        
        // If we reach here, the element was not present
        return -1;
    }
 
}
```
##### Time Complexity:
- **O(log n)**: The binary search within the found range takes logarithmic time relative to the size of the range. Finding the range also takes logarithmic time since the range is increased exponentially.

##### Space Complexity:
- **O(1)**: The space complexity is constant as no extra space is used apart from a few variables.
***

#### Find Minimum in Rotated Sorted Array
###### Pattern: Binary Search
[Back to Top](#table-of-contents)
###### Description:

- Input: `[4, 5, 6, 7, 0, 1, 2]`
- Output: `0`
- Explanation: The array `[4, 5, 6, 7, 0, 1, 2]` is a rotated version of the sorted array `[0, 1, 2, 4, 5, 6, 7]`. The minimum value in this array is `0`.

```java
public class FindMinInRotatedSortedArray {
    public int findMin(int[] nums) {
        // Initialize pointers
        int left = 0, right = nums.length - 1;

        // Binary search loop
        while (left < right) {
            // Calculate middle index
            int mid = left + (left - right) / 2;

            // Check if the middle element is greater than the rightmost element
            if (nums[mid] > nums[right]) {
                // Minimum must be to the right of mid
                left = mid + 1;
            } else {
                // Minimum is to the left of mid, including mid
                right = mid;
            }
        }

        // The left pointer will be at the minimum element
        return nums[left];
    }
}
```
###### Time Complexity:
- The time complexity of this algorithm is `O(log n)` because we are performing binary search, halving the search space in each step.

###### Space Complexity:
- The space complexity is `O(1)` as we are using a constant amount of extra space for the pointers.
***

##########################################


##########################################

##########################################

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

##########################################


##########################################



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
##########################################


##########################################

### Sliding Window

#### MaxSum SubArray Size K
##### Pattern: Sliding Window
[Back to Top](#table-of-contents)
##### Description:
- Input: `int[] arr = {2, 1, 5, 1, 3, 2}`, `int k = 3`
- Output: `9`
- Explanation: The subarray with the maximum sum of size `k` is `{5, 1, 3}` which sums to `9`.

```java
public class MaxSumSubArraySizeK {
    public static int findMaxSumSubArray(int k, int[] arr) {
        int maxSum = 0, windowSum = 0;
        int windowStart = 0;

        // Loop through the array to calculate the sum of subarrays of size k
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            // Add the next element to the window
            windowSum += arr[windowEnd];

            // Slide the window, we don't need to slide if we've not hit the window size of 'k'
            if (windowEnd >= k - 1) {
                // Update the maximum sum if the current window sum is larger
                maxSum = Math.max(maxSum, windowSum);
                // Subtract the element going out of the window
                windowSum -= arr[windowStart];
                // Slide the window ahead
                windowStart++;
            }
        }

        return maxSum;
    }
}
```
##### Time Complexity:
- The time complexity of this solution is `O(N)`, where `N` is the number of elements in the input array. This is because we are going through the array only once.

##### Space Complexity:
- The space complexity is `O(1)` as we are using a fixed amount of extra space.
***



#### Longest Substring with K Distinct Characters
##### Pattern: Sliding Window
[Back to Top](#table-of-contents)
##### Description:
- Input: `String s, int k`
  - Example: `s = "eceba", k = 2`
- Output: `int`
  - Example: `3`
```java
public class LongestSubstringKDistinct {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // Base case: if k is 0, or the string is empty, the result is 0
        if (k == 0 || s == null || s.length() == 0) return 0;

        // Use a hashmap to count characters in the current window
        Map<Character, Integer> charCountMap = new HashMap<>();
        int left = 0, right = 0; // Sliding window pointers
        int maxLength = 0;

        // Expand the window
        while (right < s.length()) {
            char currentChar = s.charAt(right);
            charCountMap.put(currentChar, charCountMap.getOrDefault(currentChar, 0) + 1);
            right++;

            // If the window has more than k distinct characters, shrink it
            while (charCountMap.size() > k) {
                char leftChar = s.charAt(left);
                charCountMap.put(leftChar, charCountMap.get(leftChar) - 1);
                if (charCountMap.get(leftChar) == 0) {
                    charCountMap.remove(leftChar);
                }
                left++;
            }

            // Update the maximum length of substring found
            maxLength = Math.max(maxLength, right - left);
        }

        return maxLength;
    }
}
```
##### Time Complexity:
- The time complexity is `O(n)` because each character is processed at most twice (once when added to the window and once when removed).

##### Space Complexity:
- The space complexity is `O(k)` because the size of the hashmap is bounded by the number of distinct characters `k`.

***
##########################################

#### Sliding Window Maximum
##### Pattern: Sliding Window
[Back to Top](#table-of-contents)
##### Description:
- **Input:** `nums = [1,3,-1,-3,5,3,6,7]`, `k = 3`
- **Output:** `[3, 3, 5, 5, 6, 7]`
- **Explanation:** For each sliding window of size `k`, the maximum value is taken. The windows are `[1, 3, -1]`, `[3, -1, -3]`, `[-1, -3, 5]`, `[-3, 5, 3]`, `[5, 3, 6]`, `[3, 6, 7]` with maximums `3, 3, 5, 5, 6, 7` respectively.

```java
import java.util.*;

public class SlidingWindowMax {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        
        int n = nums.length;
        int[] result = new int[n - k + 1];
        // Priority Queue (Max Heap) to store pairs of (num, index)
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        
        // Initialize the heap with the first window
        for (int i = 0; i < k; i++) {
            maxHeap.offer(new int[]{nums[i], i});
        }
        result[0] = maxHeap.peek()[0];
        
        // Iterate over the array, starting from the second window
        for (int i = k; i < n; i++) {
            // Add the new element to the heap
            maxHeap.offer(new int[]{nums[i], i});
            // Remove elements from the heap that are out of the current window
            while (maxHeap.peek()[1] <= i - k) {
                maxHeap.poll();
            }
            // The top element is the maximum of the current window
            result[i - k + 1] = maxHeap.peek()[0];
        }
        
        return result;
    }
}
```
##### Time Complexity:
- The time complexity is `O(n log k)`, where `n` is the number of elements in the input array and `k` is the size of the sliding window.
- This is because each insertion and removal operation in the heap takes `O(log k)` time,
- and there are `n` such operations.

##### Space Complexity:
- The space complexity is `O(k)` because the size of the heap is at most `k` at any point in time.
***



#### Problem: Sub arrays Product Less than Target

##### Pattern: Sliding Window
[Back to Top](#table-of-contents)

##### Description:
- Input: nums = [10, 5, 2, 6], target = 100
- Output: 8
- Explanation: The subarrays that have product less than 100 are [10], [5], [10, 5], [2], [5, 2], [6], [2, 6], and [5, 2, 6].

```java
public class SlidingWindowSubarryProduct{ 
    
public int numSubarrayProductLessThanK(int[] nums, int target) {
    // If target is less than or equal to 1, no valid subarrays can have product less than it.
    if (target <= 1) return 0;
    
    int product = 1; // Current product of elements in the window
    int count = 0;   // Count of valid subarrays
    int left = 0;    // Left pointer of the sliding window
    
    // Traverse the array with the right pointer
    for (int right = 0; right < nums.length; right++) {
        // Expand the window by including nums[right] in the product
        product *= nums[right];
        
        // If product is greater than or equal to target, shrink the window from the left
        while (product >= target) {
            product /= nums[left++]; // Remove nums[left] from the product and move left pointer
        }
        
        // Every valid subarray ending at nums[right] is counted
        count += right - left + 1;
    }
    
    return count;
}}

```

##### Time Complexity:
- The time complexity is O(n), where n is the length of the input array nums.
- This is because each element is processed at most twice (once by the right pointer and once by the left pointer).

##### Space Complexity:
- The space complexity is O(1) since only a constant amount of extra space is used for variables regardless of the input size.
***



#### Problem: Longest Substring No Repeats
- Problem Description: Given a string, find the length of the longest substring without repeating characters.
##### Pattern: Sliding Window
[Back to Top](#table-of-contents)

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



#### Minimum Window Subsequence
###### Pattern: Sliding Window
[Back to Top](#table-of-contents)

###### Description:
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
                // Start match the bigger String with the smaller string 
                
                int stempStart = i;
                int stempEnd = i;
                int tPointer = 0;
                

                // Try to find the entire t in s starting from i
                while (temstempEndpEnd < sLen && tStringPointer < tLen) {
                    if (s.charAt(tempEnd) == t.charAt(tPointer)) {
                        tPointer++;
                    }
                    stempEnd++;
                }

                // Walid subsequence is found when tPointer = tLength 
                if (tPointer == tLen) {
                    // Calculate the length of the current valid subsequence
                    int subSeqLength = tempEnd - tempStart;

                    // Update the result if this subsequence is shorter than the previously found one
                    if (minLength > subSeqLength) {
                        minLength = subSeqLength;
                        result = s.substring(tempStart, tempEnd);
                    }
                }
            }
        }

        return result;
    }
}

```

###### Time Complexity:
- The time complexity is O(S * T), where S is the length of the string S and T is the length of the string T.
- This is because for each character in S, we potentially traverse the entire string T.
###### Space Complexity:
- The space complexity is O(1) as we are using a constant amount of extra space.
***

#### Longest Repeating Character Replacement
##### Pattern: Sliding Window
- **TODO**
[Back to Top](#table-of-contents)
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





##########################################

##########################################


##########################################

### Merging Intervals
#### Merge Intervals
[Back to Top](#table-of-contents)
##### Description:
```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {
    
    public static int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }

        // Sort intervals by the start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // Use a list to hold the merged intervals
        List<int[]> mergedIntervals = new ArrayList<>();

        // Start with the first interval
        int[] currentInterval = intervals[0];
        mergedIntervals.add(currentInterval);

        for (int[] interval : intervals) {
            int currentEnd = currentInterval[1];
            int nextBegin = interval[0];
            int nextEnd = interval[1];

            // Check if intervals overlap
            if (currentEnd >= nextBegin) {
                // Merge the intervals
                currentInterval[1] = Math.max(currentEnd, nextEnd);
            } else {
                // Add the new interval to the list
                currentInterval = interval;
                mergedIntervals.add(currentInterval);
            }
        }

        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals = { {1, 3}, {2, 6}, {8, 10}, {15, 18} };
        int[][] merged = merge(intervals);

        System.out.println("Merged Intervals:");
        for (int[] interval : merged) {
            System.out.println(Arrays.toString(interval));
        }
    }
}
```
##### Time Complexity:
- The time complexity is `O(n log n)` due to the sorting step, where `n` is the number of intervals.
##### Space Complexity:
- The space complexity is `O(n)` for storing the merged intervals.
***


#### Insert Intervals
[Back to Top](#table-of-contents)
##### Description:
- Input: `intervals = [[1,3],[6,9]], newInterval = [2,5]`
- Output: `[[1,5],[6,9]]`
```java
import java.util.ArrayList;
import java.util.List;

public class InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        // Add all intervals before newInterval
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // Merge newInterval with overlapping intervals
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);

        // Add all intervals after newInterval
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        // Convert list to array
        return result.toArray(new int[result.size()][]);
    }
}
```
##### Time Complexity:
- O(n), where n is the number of intervals. We iterate through the list of intervals once.

##### Space Complexity:
- O(n), for the output list which stores the merged intervals.
***

#### Interval List Intersections
[Back to Top](#table-of-contents)
##### Description:
- Input:
  - `firstList` = [[0,2],[5,10],[13,23],[24,25]]
  - `secondList` = [[1,5],[8,12],[15,24],[25,26]]
- Output:
  - [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]

```java
import java.util.ArrayList;
import java.util.List;

public class IntervalListIntersections {
    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        // Initialize result list
        List<int[]> result = new ArrayList<>();
        
        // Initialize two pointers for both lists
        int i = 0, j = 0;
        
        // Iterate over both lists
        while (i < firstList.length && j < secondList.length) {
            // Find the start and end of the intersection
            int start = Math.max(firstList[i][0], secondList[j][0]);
            int end = Math.min(firstList[i][1], secondList[j][1]);
            
            // Check if there is an intersection
            if (start <= end) {
                result.add(new int[]{start, end});
            }
            
            // Move the pointer which has the smallest end time
            if (firstList[i][1] < secondList[j][1]) {
                i++;
            } else {
                j++;
            }
        }
        
        // Convert result list to array
        return result.toArray(new int[result.size()][]);
    }
 
}
```
##### Time Complexity:
- The time complexity is O(m + n), where m and n are the lengths of the two input lists. This is because we are iterating through both lists once.

##### Space Complexity:
- The space complexity is O(min(m, n)), where m and n are the lengths of the two input lists. This is because the result list will store the intersections, which can be at most the size of the smaller input list.
***


#### Employee Free Time

[Back to Top](#table-of-contents)
##### Description:
- Input: `[[[1, 3], [6, 7]],
            [[2, 4]],
            [[2, 5], [9, 12]]]`

- Output: `[[5, 6],
            [7, 9]]`

- Explanation: The input represents the schedules of three employees. Their busy times are `[1, 3], [6, 7]`, `[2, 4]`, and `[2, 5], [9, 12]` respectively.
  The intervals where all employees are free are `[5, 6]` and `[7, 9]`.

```java
import java.util.*;

public class EmployeeFreeTime {
    public static List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        // Flatten the list of schedules into one list of intervals
        List<Interval> allIntervals = new ArrayList<>();
        for (List<Interval> employee : schedule) {
            allIntervals.addAll(employee);
        }
        
        // Sort intervals by their start time
        allIntervals.sort((a, b) -> Integer.compare(a.start, b.start));
        
        /**
        ######### OR ###########
        Collections.sort(allIntervals, (a, b) -> Integer.compare(a.start, b.start));
        ######### OR ###########
        allIntervals.sort(new Comparator<Interval>() {
        @Override
        public int compare(Interval a, Interval b) {
        return Integer.compare(a.start, b.start);
        }});
        ###########################
        */

        // Merge intervals and find gaps
        List<Interval> mergedIntervals = new ArrayList<>();
        Interval prev = allIntervals.get(0);
        for (Interval curr : allIntervals) {
            if (curr.start <= prev.end) {
                // If current interval overlaps with the previous, merge them
                prev.end = Math.max(prev.end, curr.end);
            } else {
                // Otherwise, add the previous interval to merged list
                mergedIntervals.add(prev);
                prev = curr;
            }
        }
        mergedIntervals.add(prev);
        
        // Find the gaps between merged intervals
        List<Interval> freeTime = new ArrayList<>();
        for (int i = 1; i < mergedIntervals.size(); i++) {
            Interval first = mergedIntervals.get(i - 1);
            Interval second = mergedIntervals.get(i);
            if (first.end < second.start) {
                freeTime.add(new Interval(first.end, second.start));
            }
        }
        
        return freeTime;
    }

    // Helper class to represent an interval
    public static class Interval {
        int start;
        int end;

        Interval() {}

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
```
##### Time Complexity:
- The time complexity is O(N log N) due to the sorting step, where N is the total number of intervals.

##### Space Complexity:
- The space complexity is O(N) for storing the flattened list of intervals and the merged intervals.
***

#### Problem: Task Scheduler
##### Pattern: Greedy Algorithm
[Back to Top](#table-of-contents)
##### Description:
- Input: tasks = ["A","A","A","B","B","B"], n = 2
- Output: 8
- Explanation: We need to execute tasks in such a way that the same tasks are at least n units apart. One possible solution is:
```java

class TaskScheduler{
public int leastInterval(char[] tasks, int n) {
    // Array to store frequencies of each task (A-Z)
    int[] frequencies = new int[26];
    
    // Calculate frequency of each task
    for (char task : tasks) {
        frequencies[task - 'A']++;
    }
    
    // Sort frequencies to prioritize the most frequent tasks
    Arrays.sort(frequencies);
    
    // Get the maximum frequency (the most frequent task)
    int maxFrequency = frequencies[25] - 1;
    
    // Calculate the total idle slots needed
    int idleSlots = maxFrequency * n;
    
    // Distribute idle slots among the other tasks
    for (int i = 24; i >= 0 && frequencies[i] > 0; i--) {
        idleSlots -= Math.min(frequencies[i], maxFrequency);
    }
    
    // Calculate the minimum number of intervals required
    idleSlots = Math.max(0, idleSlots);
    
    // Total number of intervals required
    return tasks.length + idleSlots;
}}
```
##### Time Complexity:
- Sorting the frequencies array takes O(26 log 26) which is O(1).
- Calculating idle slots takes O(26), which is O(1).
- Overall time complexity is O(n), where n is the number of tasks.

##### Space Complexity:
- The space complexity is O(1) because we use a fixed-size array (26) to store task frequencies.
***

#### Problem: Meeting Rooms II
##### Pattern: Merge Intervals
[Back to Top](#table-of-contents)

##### Description:
- Input: [[0, 30], [5, 10], [15, 20]]
- Output: 2
- Explanation: The minimum number of meeting rooms required for these meetings is 2.

```java
import java.util.*;

class Solution {
    
    public int minMeetingRooms(int[][] intervals) {
        // Check for invalid input
        if (intervals == null || intervals.length == 0) return 0;

        // Separate out start times and end times
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];

        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }

        // Sort the start and end times
        Arrays.sort(starts);
        Arrays.sort(ends);

        // Variables to track minimum rooms required and current number of rooms in use
        int minRooms = 0;
        int currentRooms = 0;

        // Iterate over start and end times with two pointers
        int i = 0;
        int j = 0;

        while (i < intervals.length) {
            if (starts[i] < ends[j]) {
                currentRooms++;
                minRooms = Math.max(minRooms, currentRooms);
                i++;
            } else {
                currentRooms--;
                j++;
            }
        }

        return minRooms;
    }
}
```
##### Time Complexity:
- Sorting the intervals: O(n log n)
- Iterating over the intervals: O(n)
- Total: O(n log n)
##### Space Complexity:
- O(n) for storing start and end times.
***

##########################################
### HashMap
#### Two-Sum
[Back to Top](#table-of-contents)
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

##########################################
### Dynamic Programming
#### Best time to Buy Stock
[Back to Top](#table-of-contents)
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
[Back to Top](#table-of-contents)
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


##########################################
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

##########################################
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

##########################################
### Strings
#### Grouped Anagrams
[Back to Top](#Table-of-contents)
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


##########################################
### Tree DFS
- Full BT: Every node has 0 or 2 children.
- Complete BT: All levels are fully filled except possibly the last level.
- Perfect BT: All internal nodes have two children and all leaves are at the same level.
- Balanced BT: Heights of subtrees differ by no more than 1.
- Skewed BT: All nodes have only one child, creating a linear tree structure.

[Back to Top](#table-of-contents)
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

***

### Matrix
#### Rotate a Matrix
##### Pattern: Matrix Manipulation
[Back to Top](#table-of-contents)
##### Description:
- Input: A 2D array (matrix).
  ```java
  [
    [1, 2, 3],
    [4, 5, 6],
    [7, 8, 9]
  ]
  ```
- Output: The matrix rotated 90 degrees clockwise.
  ```java
  [
    [7, 4, 1],
    [8, 5, 2],
    [9, 6, 3]
  ]
  ```
- Explanation: The matrix is rotated 90 degrees clockwise, shifting each element to its new position.

```java
public class RotateMatrix {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        
        // Step 1: Transpose the matrix
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        
        // Step 2: Reverse each row
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        }
    }
}
```

##### Time Complexity:
- The time complexity is O(n^2), where n is the number of rows (or columns) of the matrix, because we traverse the entire matrix twice: once for transposing and once for reversing each row.

##### Space Complexity:
- The space complexity is O(1) because we are performing the rotation in place without using any extra space.

***

#### Set Matrix Zeros
##### Pattern: Matrix Manipulation
[Back to Top](#Table-of-contents)
##### Description:
- Input:
  ```
  [
    [1, 1, 1],
    [1, 0, 1],
    [1, 1, 1]
  ]
  ```
- Output:
  ```
  [
    [1, 0, 1],
    [0, 0, 0],
    [1, 0, 1]
  ]
  ```
- Explanation: If an element is 0, set its entire row and column to 0. The example above shows the matrix before and after setting the required rows and columns to 0.

```java
public class SetMatrixZeros {
    public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        boolean[] rowZero = new boolean[rows]; // To track which rows need to be set to 0
        boolean[] colZero = new boolean[cols]; // To track which columns need to be set to 0
        
        // First pass: determine which rows and columns need to be zeroed
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    rowZero[i] = true;
                    colZero[j] = true;
                }
            }
        }
        
        // Second pass: set the rows and columns to zero
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (rowZero[i] || colZero[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
```
##### Time Complexity:
- The time complexity is O(m * n), where m is the number of rows and n is the number of columns. This is because we iterate through the entire matrix twice.

##### Space Complexity:
- The space complexity is O(m + n) due to the additional arrays used to track which rows and columns need to be zeroed.
***

***
#### Spiral Matrix
##### Pattern: Matrix Traversal
[Back to Top](#Table-of-contents)
##### Description:
- Input: `matrix = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]`
- Output: `[1, 2, 3, 6, 9, 8, 7, 4, 5]`
- Explanation: Starting at the top left, the traversal moves right, down, left, and up, repeating until all elements are covered.

```java
import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return result;
        }

        // Define boundaries
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {
            // Traverse from left to right along the top boundary
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            top++;

            // Traverse from top to bottom along the right boundary
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--;

            // Ensure we are within valid boundaries before proceeding
            if (top <= bottom) {
                // Traverse from right to left along the bottom boundary
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--;
            }

            // Ensure we are within valid boundaries before proceeding
            if (left <= right) {
                // Traverse from bottom to top along the left boundary
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++;
            }
        }

        return result;
    }

    // Example usage
    public static void main(String[] args) {
        SpiralMatrix sm = new SpiralMatrix();
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println(sm.spiralOrder(matrix)); // Output: [1, 2, 3, 6, 9, 8, 7, 4, 5]
    }
}
```
##### Time Complexity:
- The time complexity is O(m * n), where m is the number of rows and n is the number of columns in the matrix. This is because we visit each element exactly once.

##### Space Complexity:
- The space complexity is O(1) if we ignore the space used for the output list. Otherwise, it is O(m * n) to store the result.
***
