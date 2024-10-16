# Binary Search
[HOME.md](HOME.md)
=================
## Table of contents

<!--ts-->
|                                                                               |    |    |    |    |
|:------------------------------------------------------------------------------|:---|:---|:---|:---|
| [AlgoMap](#algomap)                                                           |    |    |    |    |
| [Binary Search](#binary-search)                                               |    |    |    |    |
| [Search Insert Position](#search-insert-position)                             |    |    |    |    |
| [First Bad Version](#first-bad-version)                                       |    |    |    |    |
| [Valid Perfect Square](#valid-perfect-square)                                 |    |    |    |    |
| [Search a 2D Matrix](#search-a-2d-matrix)                                     |    |    |    |    |
| [Find Minimum in Rotated Sorted Array](#find-minimum-in-rotated-sorted-array) |    |    |    |    |
| [Search in Rotated Sorted Array](#search-in-rotated-sorted-array)             |    |    |    |    |
| [Koko Eating Bananas](#koko-eating-bananas)                                   |    |    |    |    |

#### AlgoMap
[Back to Top](#table-of-contents)
***

#### Binary Search
##### Pattern: Binary Search
[Back to Top](#table-of-contents)

##### Description:
- Input: An array of sorted integers, a target integer.  
  Example: `arr = [1, 3, 5, 7, 9]`, `target = 7`
- Output: Index of the target if found, else -1.  
  Example: `3`
- Explanation: The array is sorted, and we use binary search to efficiently find the target by repeatedly dividing the search interval in half. If the target is less than the middle element, search the left half; otherwise, search the right half.

```java
public class BinarySearch {

    // Function to perform binary search
    public static int binarySearch(int[] arr, int target) {
        int left = 0;            // Initialize the left pointer
        int right = arr.length - 1;  // Initialize the right pointer
        
        // Loop while the search space is valid
        while (left <= right) {
            int mid = left + (right - left) / 2;  // Find the middle index
            
            // Check if the middle element is the target
            if (arr[mid] == target) {
                return mid;       // Target found, return its index
            }
            
            // If target is greater, ignore the left half
            if (arr[mid] < target) {
                left = mid + 1;   // Move the left pointer to mid + 1
            }
            // If target is smaller, ignore the right half
            else {
                right = mid - 1;  // Move the right pointer to mid - 1
            }
        }
        
        return -1;  // Target not found, return -1
    }
    
    // Example usage
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9};
        int target = 7;
        System.out.println(binarySearch(arr, target));  // Output: 3
    }
}
```

##### Time Complexity:
- **O(log n)** because the array is divided into half in each iteration.

##### Space Complexity:
- **O(1)** since the algorithm uses a constant amount of extra space.
***


#### Search Insert Position
##### Pattern: Binary Search
[Back to Top](#table-of-contents)
##### Description:
- **Input**: nums = [1, 3, 5, 6], target = 5
- **Output**: 2
- **Explanation**: The target 5 is found at index 2.

```java
// Function to find the insert position of a target in a sorted array
public class SearchInsertPosition {

    public static int searchInsert(int[] nums, int target) {
        // Initialize the pointers for binary search
        int left = 0, right = nums.length - 1;

        // Perform binary search
        while (left <= right) {
            // Find the middle index
            int mid = left + (right - left) / 2;

            // Check if the target is at the middle
            if (nums[mid] == target) {
                return mid; // Target found, return index
            }

            // If target is greater, ignore left half
            else if (nums[mid] < target) {
                left = mid + 1;
            }

            // If target is smaller, ignore right half
            else {
                right = mid - 1;
            }
        }

        // If target is not found, return the insert position
        return left;
    }

    // Main function to test the solution
    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6};
        int target = 5;
        System.out.println(searchInsert(nums, target)); // Output: 2
    }
}
```
##### Time Complexity:
- **O(log n)**: This is the time complexity for binary search since we are halving the search space in each iteration.

##### Space Complexity:
- **O(1)**: We are using constant space as no extra data structures are used.

***


#### First Bad Version

##### Pattern: Binary Search
[Back to Top](#table-of-contents)
##### Description:
- **Input**: `n = 5`, `bad = 4`
- **Output**: `4`
- **Explanation**: Versions 1, 2, 3 are good, but 4 is the first bad version. The task is to find this using the least number of checks.

```java
public class Solution {
    // Method to simulate API call for bad version.
    boolean isBadVersion(int version) {
        // Example implementation, in reality, this API is provided by the system
        return version >= 4;
    }

    public int firstBadVersion(int n) {
        // Use Binary Search to find the first bad version.
        int left = 1;
        int right = n;
        
        // Loop until left pointer crosses right pointer
        while (left < right) {
            // Find the middle version
            int mid = left + (right - left) / 2;
            
            // If mid is a bad version, we know the first bad version must be
            // at or before mid, so we adjust the right pointer.
            if (isBadVersion(mid)) {
                right = mid;
            } 
            // If mid is not a bad version, the first bad version must be
            // after mid, so we adjust the left pointer.
            else {
                left = mid + 1;
            }
        }
        
        // When the loop ends, left will be the first bad version.
        return left;
    }
}
```
##### Time Complexity:
- **O(log n)**: Binary search halves the search space with each iteration, making the time complexity logarithmic.

##### Space Complexity:
- **O(1)**: Constant space is used since only a few variables (`left`, `right`, `mid`) are required.

#### Valid Perfect Square
##### Pattern: Binary Search
[Back to Top](#table-of-contents)
##### Description:
- Input: `16`
- Output: `true`
- Explanation: `16` is a perfect square because `4 * 4 = 16`.

- Input: `14`
- Output: `false`
- Explanation: `14` is not a perfect square.

```java
public class PerfectSquareCheck {
    // Function to check if a number is a perfect square
    public static boolean isPerfectSquare(int num) {
        // If the number is less than 2, it's a perfect square (0*0 = 0, 1*1 = 1)
        if (num < 2) return true;

        // Binary search approach to check for perfect square
        long left = 2, right = num / 2;
        
        // Keep narrowing down the search range
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long guessedSquare = mid * mid;

            // If mid*mid equals the number, it's a perfect square
            if (guessedSquare == num) {
                return true;
            } 
            // If mid*mid is less than the number, move the left pointer up
            else if (guessedSquare < num) {
                left = mid + 1;
            } 
            // If mid*mid is greater than the number, move the right pointer down
            else {
                right = mid - 1;
            }
        }

        // Return false if no perfect square was found
        return false;
    }

    // Example usage
    public static void main(String[] args) {
        System.out.println(isPerfectSquare(16)); // true
        System.out.println(isPerfectSquare(14)); // false
    }
}
```

##### Time Complexity:
- **O(log n)**: We are using binary search, which divides the range in half each time.

##### Space Complexity:
- **O(1)**: Constant space is used, no extra data structures are required.


#### Search a 2D Matrix
##### Pattern: Binary Search
[Back to Top](#table-of-contents)
##### Description:
- **Input**:
    ```
    matrix = [
      [1, 3, 5, 7],
      [10, 11, 16, 20],
      [23, 30, 34, 60]
    ]
    target = 3
    ```
- **Output**: `true`
- **Explanation**: The target `3` exists in the matrix, so we return `true`.

```java
public class Search2DMatrix {

    // Function to search the target in a 2D matrix
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false; // If matrix is empty, return false
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        // Use binary search by treating the 2D matrix as a 1D array
        int left = 0, right = rows * cols - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Convert 1D mid index back to 2D matrix indexes
            int midElement = matrix[mid / cols][mid % cols];

            if (midElement == target) {
                return true; // Target found
            } else if (midElement < target) {
                left = mid + 1; // Move right if target is larger
            } else {
                right = mid - 1; // Move left if target is smaller
            }
        }

        return false; // Target not found
    }
}
```
##### Time Complexity:
- **O(log(m \* n))**: We perform binary search over the entire matrix, which has `m * n` elements.

##### Space Complexity:
- **O(1)**: Only a few extra variables are used, no additional space is required.


#### Find Minimum in Rotated Sorted Array
##### Pattern: Binary Search
[Back to Top](#table-of-contents)
##### Description:
- Input: `[4, 5, 6, 7, 0, 1, 2]`
- Output: `0`
- Explanation: The array is sorted but rotated, and the minimum element is `0`.

```java
// Java program to find the minimum in a rotated sorted array
public class FindMinInRotatedSortedArray {

    public static int findMin(int[] nums) {
        // Initialize two pointers
        int left = 0;
        int right = nums.length - 1;

        // If array is not rotated, the first element is the minimum
        if (nums[left] < nums[right]) {
            return nums[left];
        }

        // Binary search logic
        while (left < right) {
            int mid = left + (right - left) / 2; // Find the middle element

            // If middle element is greater than the rightmost element,
            // minimum must be to the right of mid
            if (nums[mid] > nums[right]) {
                left = mid + 1; // Update left pointer
            } else {
                // Minimum must be on the left side or is the middle
                right = mid; // Update right pointer
            }
        }

        // At the end of the loop, left and right converge to the minimum element
        return nums[left];
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("The minimum value is: " + findMin(nums)); // Output: 0
    }
}
```
##### Time Complexity:
- O(log n) due to binary search.
##### Space Complexity:
- O(1) as no extra space is used.
***


#### Search in Rotated Sorted Array
##### Pattern: Binary Search
[Back to Top](#table-of-contents)
##### Description:
- Input: `nums = [4, 5, 6, 7, 0, 1, 2]`, `target = 0`
- Output: `4` (The index of target `0`)
- Explanation: The array is rotated, and we need to search for the target using binary search. In the example, the target `0` is located at index `4`.

```java
// Function to search in rotated sorted array
public class RotatedArraySearch {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        // Binary search approach
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // If target is found
            if (nums[mid] == target) return mid;

            // Determine which part of the array is sorted
            if (nums[left] <= nums[mid]) {
                // Left part is sorted
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1; // Search in left sorted part
                } else {
                    left = mid + 1; // Search in right part
                }
            } else {
                // Right part is sorted
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1; // Search in right sorted part
                } else {
                    right = mid - 1; // Search in left part
                }
            }
        }

        // If target is not found
        return -1;
    }
}
```
##### Time Complexity:
- **O(log n)**: We are using binary search, which divides the search space by half at each step.

##### Space Complexity:
- **O(1)**: The algorithm uses constant space, with only a few variables (left, right, mid).
***


#### Koko Eating Bananas
##### Pattern: Binary Search
[Back to Top](#table-of-contents)
##### Description:
- Input:
  - Piles = [3, 6, 7, 11], H = 8
- Output:
  - Minimum speed = 4
- Explanation:
  - Koko can eat 4 bananas per hour, and she can finish all piles in 8 hours: (3/4=1, 6/4=2, 7/4=2, 11/4=3). Total = 8 hours.

```java
public class KokoEatingBananas {
    // Function to check if Koko can finish bananas at speed 'K' in 'H' hours
    public static boolean canFinish(int[] piles, int K, int H) {
        int hoursNeeded = 0;
        for (int pile : piles) {
            // Calculate hours for current pile, ceil(pile/K)
            hoursNeeded += (pile + K - 1) / K; 
        }
        return hoursNeeded <= H;
    }

    // Function to find the minimum eating speed
    public static int minEatingSpeed(int[] piles, int H) {
        int left = 1; // Minimum speed
        int right = 0; // Maximum speed can be the largest pile
        for (int pile : piles) {
            right = Math.max(right, pile);
        }

        // Perform binary search on the speed
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canFinish(piles, mid, H)) {
                right = mid; // Try a smaller speed
            } else {
                left = mid + 1; // Increase the speed
            }
        }
        return left; // The minimum speed to finish within H hours
    }

    public static void main(String[] args) {
        int[] piles = {3, 6, 7, 11};
        int H = 8;
        System.out.println("Minimum speed to finish bananas: " + minEatingSpeed(piles, H));
    }
}
```
##### Time Complexity:
- O(n log m), where n is the number of piles, and m is the maximum pile size. Binary search runs in O(log m), and checking takes O(n).

##### Space Complexity:
- O(1), since we are using constant extra space apart from the input array.
***