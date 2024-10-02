# Heaps
[HOME.md](HOME.md)
=================
## Table of contents

<!--ts-->
|                                                                     |    |    |    |    |
|:--------------------------------------------------------------------|:---|:---|:---|:---|
| [Last Stone Weight](#last-stone-weight)                             |    |    |    |    |
| [Kth Largest Element in an Array](#kth-largest-element-in-an-array) |    |    |    |    |
| [Top K Frequent Elements](#top-k-frequent-elements)                 |    |    |    |    |
| [K Closest Points to Origin](#k-closest-points-to-origin)           |    |    |    |    |
| [Merge K Sorted Linked Lists](#merge-k-sorted-linked-lists)         |    |    |    |    |
|                                                                     |    |    |    |    |

***
#### Last Stone Weight
##### Pattern: Heaps (Priority Queue)
[Back to Top](#table-of-contents)
##### Description:
- Input: `[2,7,4,1,8,1]`
- Output: `1`
- Explanation: The two heaviest stones (8, 7) are smashed, leaving a stone of weight 1. Repeat the process until one or zero stones remain. The last stone weighs `1`.

```java
import java.util.PriorityQueue;

public class LastStoneWeight {
    public int lastStoneWeight(int[] stones) {
        // Create a max heap using a priority queue with reverse order
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        // Add all stones into the max heap
        for (int stone : stones) {
            maxHeap.add(stone);
        }

        // Simulate the stone-smashing process
        while (maxHeap.size() > 1) {
            // Take the two heaviest stones
            int stone1 = maxHeap.poll(); // Heaviest stone
            int stone2 = maxHeap.poll(); // Second heaviest stone

            // If they are not of equal weight, put the difference back into the heap
            if (stone1 != stone2) {
                maxHeap.add(stone1 - stone2);
            }
        }

        // Return the weight of the last stone (or 0 if no stones are left)
        return maxHeap.isEmpty() ? 0 : maxHeap.poll();
    }
    
    // Example usage
    public static void main(String[] args) {
        LastStoneWeight solution = new LastStoneWeight();
        int[] stones = {2, 7, 4, 1, 8, 1};
        System.out.println(solution.lastStoneWeight(stones));  // Output: 1
    }
}
```

##### Time Complexity:
- O(n log n), where `n` is the number of stones. Each insertion and removal from the heap takes O(log n).

##### Space Complexity:
- O(n), as we store all stones in the heap.

***

#### Kth Largest Element in an Array
##### Pattern: Heap / Priority Queue
[Back to Top](#table-of-contents)
##### Description:
- Input: `[3, 2, 1, 5, 6, 4], k = 2`
- Output: `5`
- Explanation: The 2nd largest element in the array `[3, 2, 1, 5, 6, 4]` is `5`.

```java
import java.util.PriorityQueue;

public class KthLargestElement {
    public static int findKthLargest(int[] nums, int k) {
        // Using a min-heap (PriorityQueue in Java) to maintain the top k largest elements
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        // Iterate over each element in the array
        for (int num : nums) {
            // Add the element to the heap
            minHeap.add(num);
            
            // If the heap size exceeds k, remove the smallest element
            if (minHeap.size() > k) {
                minHeap.poll();  // Removes the smallest element in the heap
            }
        }
        
        // The root of the heap is the kth largest element
        return minHeap.peek();
    }

    // Example usage
    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        System.out.println("The " + k + "th largest element is: " + findKthLargest(nums, k));  // Output: 5
    }
}
```
##### Time Complexity:
- The time complexity is **O(N log k)**, where **N** is the number of elements in the array. Inserting an element into the heap takes **O(log k)**, and we do this for **N** elements.

##### Space Complexity:
- The space complexity is **O(k)**, as we only store **k** elements in the heap at any given time.
***

#### Top K Frequent Elements
##### Pattern: Heap / Bucket Sort
[Back to Top](#Table-of-contents)
##### Description:
- Input: `nums = [1,1,1,2,2,3]`, `k = 2`
- Output: `[1, 2]`
- Explanation: The elements `1` and `2` are the most frequent, with frequencies of 3 and 2 respectively. As `k = 2`, we return the two most frequent elements.

```java
import java.util.*;

public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        // Step 1: Count the frequency of each element
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Create a max heap based on frequency
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = 
            new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        // Step 3: Add all entries of frequency map to the max heap
        maxHeap.addAll(frequencyMap.entrySet());

        // Step 4: Extract the top 'k' frequent elements
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll().getKey();
        }

        return result;
    }

    public static void main(String[] args) {
        TopKFrequentElements solution = new TopKFrequentElements();
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        int[] result = solution.topKFrequent(nums, k);
        System.out.println(Arrays.toString(result)); // Expected Output: [1, 2]
    }
}
```
##### Time Complexity:
- **O(N log k)**: We iterate over `nums` to build the frequency map in O(N) time. The heap operations (insertion and removal) are O(log k), and we do this for each of the `k` elements.

##### Space Complexity:
- **O(N + k)**: We use a frequency map that takes O(N) space and a heap that stores up to `k` elements, so the total space complexity is O(N + k).
***


#### K Closest Points to Origin
##### Pattern: Heap (Priority Queue)
[Back to Top](#table-of-contents)
##### Description:
- Input: `points = [[1,3],[-2,2],[5,8],[0,1]]`, `k = 2`
- Output: `[[1,3],[-2,2]]`
- Explanation: The two closest points to the origin (0,0) based on their Euclidean distance are (1,3) and (-2,2).

```java
import java.util.AbstractMap;
import java.util.Map;
import java.util.PriorityQueue;

public class KClosestPoints {
    public int[][] kClosest(int[][] points, int k) {
        // Handle null and invalid cases
        if (points == null || points.length < k || k <= 0) {
            return new int[][]{};  // Return an empty array if conditions are not met
        }

        // Using a max-heap (PriorityQueue) to store the closest points
        PriorityQueue<Map.Entry<Double, int[]>> queue = new PriorityQueue<>(
            (a, b) -> Double.compare(b.getKey(), a.getKey())  // Max-heap based on distance
        );

        // Use a HashMap to store distances and corresponding points
        for (int[] point : points) {
            // Calculate the squared distance to avoid floating-point precision issues
            double distance = point[0] * point[0] + point[1] * point[1];
            // Store the distance and point as an entry in the priority queue
            queue.offer(new AbstractMap.SimpleEntry<>(distance, point)); 

            // Maintain only the K closest points in the heap
            if (queue.size() > k) {
                queue.poll(); // Remove the farthest point from the max-heap
            }
        }

        // Extract the K closest points from the priority queue
        int[][] result = new int[k][];
        for (int i = 0; i < k; i++) {
            result[i] = queue.poll().getValue(); // Get the point associated with the closest distance
        }

        return result; // Return the K closest points
    }
}
```
##### Time Complexity:
- O(N log K), where N is the number of points. Each point is processed in O(log K) time due to the heap operations.

##### Space Complexity:
- O(K), for storing the K closest points in the priority queue.
***

#### Merge K Sorted Linked Lists
##### Pattern: Merging Intervals
[Back to Top](#table-of-contents)
##### Description:
- Input: `k = 3, lists = [[1,4,5],[1,3,4],[2,6]]`
- Output: `[1,1,2,3,4,4,5,6]`
- Explanation: Merging the three sorted linked lists results in a single sorted list.

```java
import java.util.PriorityQueue;

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        // Create a priority queue to hold the nodes
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(a.val, b.val) // Compare node values
        );

        // Add the head of each list to the priority queue
        for (ListNode list : lists) {
            if (list != null) {
                minHeap.add(list);
            }
        }

        // Create a dummy node to simplify the merging process
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // While there are nodes in the min-heap
        while (!minHeap.isEmpty()) {
            // Get the smallest node from the heap
            ListNode smallestNode = minHeap.poll();
            current.next = smallestNode; // Link it to the merged list
            current = current.next; // Move to the next position in the merged list

            // If the smallest node has a next node, add it to the heap
            if (smallestNode.next != null) {
                minHeap.add(smallestNode.next);
            }
        }

        return dummy.next; // Return the merged list, which is next to the dummy node
    }
}
```
##### Time Complexity:
- O(N log K) where N is the total number of nodes across all lists, and K is the number of linked lists. Each insertion into the priority queue takes O(log K) time.

##### Space Complexity:
- O(K) for the priority queue that stores at most K nodes at any time.
***