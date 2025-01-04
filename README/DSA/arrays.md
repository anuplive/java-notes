# Arrays
[HOME.md](HOME.md)
=================
## Table of contents

<!--ts-->
| [Two Pointer](#two-pointer)                            |    |    |    |    |
|:--------------------------------------------------------|:---|:---|:---|:---|
| [Squares of a Sorted Array](#squares-of-a-sorted-array) |    |    |    |    |
| [Reverse String](#reverse-string)                       |    |    |    |    |
| [Valid Palindrome](#valid-palindrome)                   |    |    |    |    |
| [Two Sum](#two-sum)                                     |    |    |    |    |
| [Three Sum Problem](#three-sum-problem)                 |    |    |    |    |
| [Container with most Water](#container-with-most-water) |    |    |    |    |
| [Product except itself](#product-except-itself)         |    |    |    |    |
| [Triplet Sum](#triplet-sum)                             |    |    |    |    |
|                                                         |    |    |    |    |


<!--ts-->
| [Arrays and Strings](#arrays-and-string)                            |    |    |    |    |
|:--------------------------------------------------------------------|:---|:---|:---|:---|
| [Find Closest Number to Zero](#find-closest-number-to-zero)         |    |    |    |    |
| [Merge Strings Alternately](#merge-strings-alternately)             |    |    |    |    |
| [Roman to Numeral](#roman-to-integer)                               |    |    |    |    |
| [Is Subsequence](#is-subsequence)                                   |    |    |    |    |
| [Best Time to Buy and Sell Stock](#best-time-to-buy-and-sell-stock) |    |    |    |    |
| [Longest Common Prefix](#longest-common-prefix)                     |    |    |    |    |
| [Range Summary](#range-summary)                                     |    |    |    |    |
| [Merge Intervals](#merge-intervals)                                 |    |    |    |    |
| [Spiral Matrix](#spiral-matrix)                                     |    |    |    |    |
| [Rotate Image](#rotate-image)                                       |    |    |    |    |
| [Set Matrix Zeros](#set-matrix-zeros)                               |    |    |    |    |
|                                                                     |    |    |    |    |


| [Sliding Window](#sliding-window)                                                             |    |    |    |    |
|:----------------------------------------------------------------------------------------------|:---|:---|:---|:---|
| [MaxSum SubArray Size K](#maxsum-subarray-size-k)                                             |    |    |    |    |
| [Longest Substring with K Distinct Characters](#longest-substring-with-k-distinct-characters) |    |    |    |    |
| [Sliding Window Maximum](#sliding-window-maximum)                                             |    |    |    |    |
| [Sub arrays Product Less than Target](#sub-arrays-product-less-than-target)                   |    |    |    |    |
| [Longest Substring No Repeats](#longest-substring-no-repeats)                                 |    |    |    |    |
| [Minimum Window Subsequence](#minimum-window-subsequence)                                     |    |    |    |    |
| [Longest Repeating Character Replacement](#longest-repeating-character-replacement)           |    |    |    |    |


| [Merging Intervals](#merging-intervals)                     |    |    |    |    |
|:------------------------------------------------------------|:---|:---|:---|:---|
| [Merge Intervals](#merge-intervals)                         |    |    |    |    |
| [Insert Intervals](#insert-intervals)                       |    |    |    |    |
| [Interval List Intersections](#interval-list-intersections) |    |    |    |    |
| [Employee Free Time](#employee-free-time)                   |    |    |    |    |
| [Task Scheduler](#task-scheduler)                           |    |    |    |    |
| [Meeting Rooms II](#meeting-rooms-ii)                       |    |    |    |    |

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

***

#### Squares of a Sorted Array
##### Pattern: Two Pointer
[Back to Top](#table-of-contents)
##### Description:
- Input: `[-4, -1, 0, 3, 10]`
- Output: `[0, 1, 9, 16, 100]`
- Explanation: The square of each element in the array is calculated and then sorted in non-decreasing order.

```java
// Class to solve the problem of sorting the squares of a sorted array
public class SortedSquares {

   // Method to return the squares of a sorted array
   public static int[] sortedSquares(int[] nums) {
       // Initialize a result array to store the squares
       int[] result = new int[nums.length];
       // Two pointers: one starting from the beginning and one from the end
       int left = 0, right = nums.length - 1;
       // Position to fill in the result array, starting from the end
       int index = nums.length - 1;

       // Traverse until the two pointers meet
       while (left <= right) {
           int leftSquare = nums[left] * nums[left];   // Square of the left pointer element
           int rightSquare = nums[right] * nums[right]; // Square of the right pointer element

           // Compare squares and place the larger square at the current index
           if (leftSquare > rightSquare) {
               result[index] = leftSquare;
               left++; // Move left pointer to the right
           } else {
               result[index] = rightSquare;
               right--; // Move right pointer to the left
           }
           index--; // Move index left for next largest square
       }

       return result;
   }

   // Main method to test the function with an example
   public static void main(String[] args) {
       int[] nums = {-4, -1, 0, 3, 10};
       int[] result = sortedSquares(nums);

       // Print the result
       for (int num : result) {
           System.out.print(num + " ");
       }
   }
}
```
##### Time Complexity:
- O(n): We traverse the array once using two pointers, so the time complexity is linear.

##### Space Complexity:
- O(n): The space complexity is linear because we are using an additional array of the same size as the input array.
***


[Back to Top](#table-of-contents)


#### Reverse String
##### Pattern: Two Pointers
[Back to Top](#table-of-contents)
##### Description:
- Input: `"hello"`
- Output: `"olleh"`
- Explanation: The characters in the string are reversed. The first and last characters are swapped, and this process continues until the entire string is reversed.

```java
public class ReverseString {
   // Function to reverse a given string
   public static String reverse(String s) {
       // Convert the string to a character array
       char[] chars = s.toCharArray();
      
       // Initialize two pointers, one at the start and the other at the end of the array
       int left = 0;
       int right = chars.length - 1;
      
       // Swap characters while left pointer is less than right pointer
       while (left < right) {
           // Swap the characters at left and right pointers
           char temp = chars[left];
           chars[left] = chars[right];
           chars[right] = temp;
          
           // Move the pointers towards the center
           left++;
           right--;
       }
      
       // Convert the character array back to a string and return it
       return new String(chars);
   }

   public static void main(String[] args) {
       // Example usage
       String input = "hello";
       String reversed = reverse(input);
       System.out.println("Reversed string: " + reversed);  // Output: olleh
   }
}
```
##### Time Complexity:
- **O(n)**: We iterate over half of the string with two pointers, making a single pass through the string.

##### Space Complexity:
- **O(n)**: The space is proportional to the input string size due to the character array creation.
***


#### Valid Palindrome
##### Pattern: Two Pointer
[Back to Top](#table-of-contents)
##### Description:
- Input: `"A man, a plan, a canal, Panama"`
- Output: `true`
- Explanation: The string reads the same forward and backward when ignoring non-alphanumeric characters and case.

```java
public class ValidPalindrome {
  
   // Function to check if a given string is a valid palindrome
   public static boolean isPalindrome(String s) {
       int left = 0;                 // Initialize left pointer
       int right = s.length() - 1;   // Initialize right pointer
      
       // Traverse the string from both ends
       while (left < right) {
           // Skip non-alphanumeric characters from the left
           while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
               left++;
           }
           // Skip non-alphanumeric characters from the right
           while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
               right--;
           }
          
           // Compare characters from both ends
           if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
               return false;  // Return false if characters do not match
           }
          
           left++;   // Move left pointer to the right
           right--;  // Move right pointer to the left
       }
      
       return true;   // Return true if all characters match
   }

   public static void main(String[] args) {
       // Test examples
       System.out.println(isPalindrome("A man, a plan, a canal, Panama"));  // Output: true
       System.out.println(isPalindrome("race a car"));                       // Output: false
   }
}
```
##### Time Complexity:
- O(n), where n is the length of the string. Each character is checked once.

##### Space Complexity:
- O(1), no extra space is used other than the input string.

***

#### Two Sum
##### Pattern: Two Pointer
[Back to Top](#table-of-contents)
##### Description:
- **Input:** A sorted array of integers `nums[]` and an integer `target`.
- **Output:** Indices of the two numbers such that they add up to `target`.
- **Explanation:** Given `nums = [2, 7, 11, 15]` and `target = 9`, the output would be `[0, 1]` because `nums[0] + nums[1] = 2 + 7 = 9`.

```java
public class TwoSumTwoPointer {
   // Function to find indices of two numbers that add up to the target
   public static int[] twoSum(int[] nums, int target) {
       // Two pointers: one starting from the beginning, the other from the end
       int left = 0;
       int right = nums.length - 1;

       // Loop until the two pointers meet
       while (left < right) {
           int sum = nums[left] + nums[right];

           // Check if we found the target sum
           if (sum == target) {
               return new int[] { left, right };
           }
           // If sum is greater than target, move the right pointer to the left
           else if (sum > target) {
               right--;
           }
           // If sum is less than target, move the left pointer to the right
           else {
               left++;
           }
       }

       // Return an empty array if no solution is found (assuming there is always a solution)
       return new int[] {};
   }

   // Example usage
   public static void main(String[] args) {
       int[] nums = {2, 7, 11, 15};
       int target = 9;
       int[] result = twoSum(nums, target);
      
       // Output: Indices [0, 1]
       System.out.println("Indices: [" + result[0] + ", " + result[1] + "]");
   }
}
```
##### Time Complexity:
- The time complexity is **O(n)** because we are traversing the array once with two pointers, where `n` is the length of the array.

##### Space Complexity:
- The space complexity is **O(1)** as we only use a few extra variables (`left`, `right`, `sum`).
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
           // Outer Loop Skip duplicate elements
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
[Back to Top](#table-of-contents)

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

##### Algorithm Explanation:
- **Initialize two pointers**: `left` at the start, `right` at the end of the array.
- **Calculate area** between `left` and `right`: `min(height[left], height[right]) * (right - left)`.
- **Update maxArea**: Store the larger of the current area or the previously stored maxArea.
- **Move the pointer with the smaller height**:
 - If `height[left] <= height[right]`: Increment `left`.
 - Else: Decrement `right`.
- **Repeat** until the pointers meet.
- **Return maxArea** found.

This ensures the widest containers are checked first, narrowing inwards.

***

[Back to Top](#table-of-contents)


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



## Arrays and String
#### Find Closest Number to Zero
##### Pattern: Array Manipulation
[Back to Top](#table-of-contents)
##### Description:
- **Input**: An array of integers, e.g., `[-4, 2, 1, -3]`
- **Output**: The closest number to zero from the array, e.g., `1`
- **Explanation**: The function needs to find the number from the array that is closest to zero. If two numbers are equally close, return the positive number.

```java
public class ClosestToZero {

   // Function to find the closest number to zero
   public int findClosestNumber(int[] nums) {

   // Edge case: If the array is empty, return -1 (or based on requirement)
   if (nums == null || nums.length == 0) {
       return -1;
   }

   // Variable to store the minimum distance (absolute value)
   int minDistance = Integer.MAX_VALUE;
  
   // Variable to store the number closest to zero
   int closestNumber = Integer.MAX_VALUE;

   for (int i = 0; i < nums.length; i++) {
       int current = nums[i];
      
       // Calculate the absolute value of the current number
       int absCurrent = Math.abs(current);

       // If we find a smaller distance, or if the distance is the same and the number is positive
       if (absCurrent < minDistance || (absCurrent == minDistance && current > closestNumber)) {
           minDistance = absCurrent;  // Update the minimum distance
           closestNumber = current;   // Update the number closest to zero
       }
   }

   return closestNumber;  // Return the number closest to zero
}
   public static void main(String[] args) {
       // Example 1
       int[] nums1 = {-4, 2, 1, -3};
       System.out.println("Closest to zero: " + findClosestToZero(nums1)); // Output: 1

       // Example 2
       int[] nums2 = {-7, -5, 5, 7};
       System.out.println("Closest to zero: " + findClosestToZero(nums2)); // Output: 5

       // Example 3 (Edge case)
       int[] nums3 = {};
       System.out.println("Closest to zero: " + findClosestToZero(nums3)); // Output: 0
   }
}
```

##### Time Complexity:
- The time complexity is **O(n)**, where `n` is the length of the array. This is because we iterate through the array once.

##### Space Complexity:
- The space complexity is **O(1)** since we are using a constant amount of extra space regardless of the input size.
***





#### Merge Strings Alternately
##### Pattern: Two Pointers
[Back to Top](#table-of-contents)
##### Description:
- Input:
 - `word1 = "abc"`, `word2 = "pqr"`
- Output:
 - `"apbqcr"`
- Explanation:
 - The characters from both strings are merged alternately. After merging characters up to the length of the shorter string, any remaining characters from the longer string are appended.

```java
public class MergeStringsAlternately {

   public String mergeAlternately(String word1, String word2) {
      
       // Check if any of the strings is null, return null if true
       if (word1 == null || word2 == null)
           return null;

       // Convert the strings to char arrays for efficient access
       char[] ch1 = word1.toCharArray();
       char[] ch2 = word2.toCharArray();
      
       // Initialize a StringBuilder to store the merged result
       StringBuilder sb = new StringBuilder();
      
       // Iterate over both arrays up to the length of the shorter string
       for (int i = 0; i < ch1.length && i < ch2.length; i++) {
           // Append characters alternately from both char arrays
           sb.append(ch1[i]);
           sb.append(ch2[i]);
       }
      
       // If word1 is longer, append the remaining characters
       if (word1.length() > word2.length()) {
           sb.append(word1.substring(word2.length(), word1.length()));
       }
      
       // If word2 is longer, append the remaining characters
       if (word2.length() > word1.length()) {
           sb.append(word2.substring(word1.length(), word2.length()));
       }

       // Return the merged string
       return sb.toString();  
   }

   public static void main(String[] args) {
       MergeStringsAlternately mergeObj = new MergeStringsAlternately();
      
       // Test case 1
       String word1 = "abc", word2 = "pqr";
       System.out.println(mergeObj.mergeAlternately(word1, word2));  // Output: "apbqcr"
      
       // Test case 2
       String word1_2 = "ab", word2_2 = "pqrs";
       System.out.println(mergeObj.mergeAlternately(word1_2, word2_2));  // Output: "apbqrs"
   }
}
```

##### Time Complexity:
- **O(n)**: The method processes both strings character by character, where `n` is the length of the longer string.

##### Space Complexity:
- **O(n)**: Space is used for the result stored in the `StringBuilder`, where `n` is the combined length of both strings.
***



#### Roman to Integer
##### Pattern: No specific pattern

[Back to Top](#table-of-contents)

##### Description:
- **Input:** "IX"
- **Output:** 9
- **Explanation:** The Roman numeral "IX" translates to 9 because I (1) is placed before X (10), which means subtract I from X.

```java
public class RomanToInteger {
  
   // Function to convert a Roman numeral to an integer
   public static int romanToInt(String s) {
       // Map to store Roman numerals and their corresponding integer values
       Map<Character, Integer> romanMap = new HashMap<>();
       romanMap.put('I', 1);
       romanMap.put('V', 5);
       romanMap.put('X', 10);
       romanMap.put('L', 50);
       romanMap.put('C', 100);
       romanMap.put('D', 500);
       romanMap.put('M', 1000);

       int total = 0; // To store the final integer value
       int prev = 0;  // To store the previous Roman numeral's value

       // Loop through each character in the Roman numeral string
       for (int i = s.length() - 1; i >= 0; i--) {
           // Get the integer value of the current Roman numeral
           int current = romanMap.get(s.charAt(i));

           // If the current numeral is less than the previous one, subtract it
           // Otherwise, add it to the total
           if (current < prev) {
               total -= current;
           } else {
               total += current;
           }

           // Update the previous numeral to the current one
           prev = current;
       }

       return total; // Return the final integer value
   }

   public static void main(String[] args) {
       // Test cases
       System.out.println(romanToInt("III"));  // Output: 3
       System.out.println(romanToInt("IV"));   // Output: 4
       System.out.println(romanToInt("IX"));   // Output: 9
       System.out.println(romanToInt("LVIII"));// Output: 58
       System.out.println(romanToInt("MCMXCIV"));// Output: 1994
   }
}
```

##### Time Complexity:
- O(n), where n is the length of the input string, because we iterate through the Roman numeral once.

##### Space Complexity:
- O(1), since the space used by the map and variables is constant and does not grow with the input size.

##### Algorithm Explanation:
- Initialize a map to store the values of Roman numerals.
- Loop through the Roman numeral string in reverse:
 - If the current value is less than the previous value, subtract it from the total.
 - Otherwise, add it to the total.
- Return the total as the final integer value.
***


#### Is Subsequence
##### Pattern: Two Pointer
[Back to Top](#table-of-contents)
##### Description:
- Input: `s = "abc", t = "ahbgdc"`
- Output: `true`
- Explanation: `"abc"` is a subsequence of `"ahbgdc"` because all characters of `"abc"` appear in order in `"ahbgdc"`.

```java
public class Solution {
   // Method to check if s is a subsequence of t
   public boolean isSubsequence(String s, String t) {
       // Two pointers: one for each string
       int i = 0, j = 0;
      
       // Traverse the second string t
       while (i < s.length() && j < t.length()) {
           // If characters match, move the pointer for s
           if (s.charAt(i) == t.charAt(j)) {
               i++;
           }
           // Always move the pointer for t
           j++;
       }
      
       // If i reaches the end of s, it means all characters of s are found in t in order
       return i == s.length();
   }
}
```

##### Time Complexity:
- **O(n)**, where `n` is the length of string `t`. We traverse `t` while checking each character with `s`.

##### Space Complexity:
- **O(1)**, as no extra space is used except for pointers.

##### Algorithm Explanation:
- Initialize two pointers `i` (for `s`) and `j` (for `t`).
- Traverse through `t`:
 - If `s[i] == t[j]`, increment `i`.
 - Always increment `j`.
- Return true if all characters of `s` are found (`i == s.length()`), else false.
***

#### Best Time to Buy and Sell Stock
##### Pattern: Dynamic Programming
[Back to Top](#table-of-contents)
##### Description:
- **Input**: `[7, 1, 5, 3, 6, 4]`
- **Output**: `5`
- **Explanation**: The maximum profit can be achieved by buying on day 2 (price = 1) and selling on day 5 (price = 6), for a profit of 6 - 1 = 5.

```java
// Function to find the maximum profit from buying and selling a stock
public class StockProfit {
   public int maxProfit(int[] prices) {
       // Edge case: If prices array is empty or has only one element, return 0
       if (prices == null || prices.length < 2) {
           return 0;
       }
      
       // Initialize variables
       int minPrice = prices[0]; // Tracks the lowest price encountered so far
       int maxProfit = 0; // Tracks the maximum profit
      
       // Loop through the prices array
       for (int i = 1; i < prices.length; i++) {
           // If the current price is lower than the minPrice, update minPrice
           if (prices[i] < minPrice) {
               minPrice = prices[i];
           } else {
               // If selling at current price gives better profit, update maxProfit
               maxProfit = Math.max(maxProfit, prices[i] - minPrice);
           }
       }
       return maxProfit; // Return the maximum profit
   }
}
```
##### Time Complexity:
- **O(n)**: We loop through the prices array once, where `n` is the number of days.

##### Space Complexity:
- **O(1)**: We only use a few extra variables (`minPrice` and `maxProfit`).
***

### Longest Common Prefix




#### Longest Common Prefix
##### Pattern: String Processing
[Back to Top](#Table-of-contents)
##### Description:
- Input: `["flower", "flow", "flight"]`
- Output: `"fl"`
- Explanation: The longest common prefix among the strings is `"fl"`. All strings start with this sequence of characters.

```java
// Java program to find the longest common prefix

public class LongestCommonPrefix {

   // Method to find the longest common prefix
   public static String longestCommonPrefix(String[] strs) {
       // Base case: if the array is empty, return an empty string
       if (strs == null || strs.length == 0) return "";

       // Initialize the prefix as the first string in the array
       String prefix = strs[0];

       // Iterate over the remaining strings in the array
       for (int i = 1; i < strs.length; i++) {
           // Reduce the prefix until it matches the beginning of each string
           while (strs[i].indexOf(prefix) != 0) {
               // Remove the last character from the prefix
               prefix = prefix.substring(0, prefix.length() - 1);
               // If the prefix becomes empty, return an empty string
               if (prefix.isEmpty()) return "";
           }
       }
       // Return the common prefix
       return prefix;
   }

   public static void main(String[] args) {
       String[] words = {"flower", "flow", "flight"};
       System.out.println("Longest Common Prefix: " + longestCommonPrefix(words));
   }
}
```
##### Time Complexity:
- **O(S)** where `S` is the sum of all characters in all strings. The program iterates over each character once, shortening the prefix as needed.

##### Space Complexity:
- **O(1)** (excluding input storage), since no extra space proportional to the input size is used apart from storing the result.
***




#### Range Summary
##### Pattern: Merging Intervals
[Back to Top](#table-of-contents)
##### Description:
- Input: [0, 1, 2, 4, 5, 7]
- Output: ["0->2", "4->5", "7"]
- Explanation: The input represents numbers that can be grouped into continuous ranges: 0->2, 4->5, and 7 by itself.

```java
import java.util.*;

public class RangeSummary {
   public List<String> summaryRanges(int[] nums) {
       // Handle the case when input is empty
       if (nums == null || nums.length == 0) {
           return new ArrayList<>(); // Return empty list
       }

       // Handle the case when input has only one element
       if (nums.length == 1) {
           return Arrays.asList(String.valueOf(nums[0])); // Return list with one element
       }

       // Initialize result list to store range summaries
       List<String> result = new ArrayList<>();

       // Set start to track the beginning of the range
       int start = nums[0];
      
       // Start iterating from the second element
       for (int i = 1; i < nums.length; i++) {
           // If current number is not consecutive
           if (nums[i - 1] + 1 != nums[i]) {
               // Check if start and previous number are the same
               if (start == nums[i - 1]) {
                   result.add(String.valueOf(start)); // Add a single number
               } else {
                   result.add(start + "->" + nums[i - 1]); // Add the range
               }
               // Move the start to the current number
               start = nums[i];
           }
       }

       // Add the last range or number after loop ends
       if (start == nums[nums.length - 1]) {
           result.add(String.valueOf(start)); // Add single number
       } else {
           result.add(start + "->" + nums[nums.length - 1]); // Add the final range
       }

       return result; // Return the result list
   }

   public static void main(String[] args) {
       int[] nums = {0, 1, 2, 4, 5, 7};
       System.out.println(summaryRanges(nums)); // Output: [0->2, 4->5, 7]
   }
}
```

##### Time Complexity:
- **O(n)**: We loop through the array once.

##### Space Complexity:
- **O(1)**: No extra space is used except for the output list.

***

#### Merge Intervals
##### Pattern: Merging Intervals
[Back to Top](#table-of-contents)

##### Description:
- Input: `[[1,3],[2,6],[8,10],[15,18]]`
- Output: `[[1,6],[8,10],[15,18]]`
- Explanation: Intervals `[1,3]` and `[2,6]` overlap, so we merge them into `[1,6]`. Other intervals remain unchanged.

```java
import java.util.*;

public class MergeIntervals {

   public static int[][] merge(int[][] intervals) {
       // If there are no intervals, return an empty array
       if (intervals.length == 0) {
           return new int[0][];
       }

       // Sort intervals based on the starting point
       Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

       // Resultant list of merged intervals
       List<int[]> merged = new ArrayList<>();

       // Start with the first interval
       int[] currentInterval = intervals[0];
       merged.add(currentInterval);

       // Iterate through each interval
       for (int[] interval : intervals) {
           // Check if the current interval overlaps with the last added interval
           if (interval[0] <= currentInterval[1]) {
               // Merge intervals by updating the end time
               currentInterval[1] = Math.max(currentInterval[1], interval[1]);
           } else {
               // No overlap, add the new interval
               currentInterval = interval;
               merged.add(currentInterval);
           }
       }

       // Convert the merged list to an array and return
       return merged.toArray(new int[merged.size()][]);
   }

   public static void main(String[] args) {
       // Test case 1
       int[][] intervals1 = {{1,3},{2,6},{8,10},{15,18}};
       System.out.println(Arrays.deepToString(merge(intervals1)));
       // Output: [[1,6],[8,10],[15,18]]

       // Test case 2
       int[][] intervals2 = {{1,4},{4,5}};
       System.out.println(Arrays.deepToString(merge(intervals2)));
       // Output: [[1,5]]
   }
}
```

##### Time Complexity:
- Sorting the intervals takes `O(n log n)`, where `n` is the number of intervals. Merging takes `O(n)`.

##### Space Complexity:
- The space complexity is `O(n)` due to the output list of merged intervals.

***

#### Spiral Matrix
##### Pattern: Matrix Traversal
[Back to Top](#table-of-contents)
##### Description:
- **Input**: A 2D matrix.
 ```
 matrix = [
   [1, 2, 3],
   [4, 5, 6],
   [7, 8, 9]
 ]
 ```
- **Output**: List of elements in spiral order.
 ```
 [1, 2, 3, 6, 9, 8, 7, 4, 5]
 ```
- **Explanation**: Start from the top-left and move in a spiral pattern: right → down → left → up.

```java
import java.util.*;

public class SpiralMatrix {
   // Function to return spiral order of a matrix
   public static List<Integer> spiralOrder(int[][] matrix) {
       List<Integer> result = new ArrayList<>();
      
       if (matrix == null || matrix.length == 0) {
           return result;  // Edge case: empty matrix
       }
      
       // Initialize boundaries
       int top = 0, bottom = matrix.length - 1;
       int left = 0, right = matrix[0].length - 1;
      
       // Traverse the matrix while the boundaries are valid
       while (top <= bottom && left <= right) {
           // Traverse from left to right along the top boundary
           for (int i = left; i <= right; i++) {
               result.add(matrix[top][i]);
           }
           top++;  // Move top boundary down
          
           // Traverse from top to bottom along the right boundary
           for (int i = top; i <= bottom; i++) {
               result.add(matrix[i][right]);
           }
           right--;  // Move right boundary left
          
           if (top <= bottom) {
               // Traverse from right to left along the bottom boundary
               for (int i = right; i >= left; i--) {
                   result.add(matrix[bottom][i]);
               }
               bottom--;  // Move bottom boundary up
           }
          
           if (left <= right) {
               // Traverse from bottom to top along the left boundary
               for (int i = bottom; i >= top; i--) {
                   result.add(matrix[i][left]);
               }
               left++;  // Move left boundary right
           }
       }
      
       return result;
   }

   // Main method for testing the solution
   public static void main(String[] args) {
       int[][] matrix = {
           {1, 2, 3},
           {4, 5, 6},
           {7, 8, 9}
       };

       List<Integer> result = spiralOrder(matrix);
       System.out.println(result);  // Expected output: [1, 2, 3, 6, 9, 8, 7, 4, 5]
   }
}
```

##### Time Complexity:
- **O(m * n)**, where `m` is the number of rows and `n` is the number of columns. Each element is visited exactly once.

##### Space Complexity:
- **O(1)** for the traversal itself. The result list uses **O(m * n)** space to store the output.
***



#### Rotate Image
##### Pattern: Matrix Manipulation
[Back to Top](#table-of-contents)

##### Description:
- Input: A 2D array representing an image, e.g., `[[1,2,3],[4,5,6],[7,8,9]]`
- Output: The image rotated 90 degrees clockwise, e.g., `[[7,4,1],[8,5,2],[9,6,3]]`
- Explanation: The input matrix is transformed by rotating its elements in a clockwise direction.

```java
public class RotateImage {
   public static void rotate(int[][] matrix) {
       int n = matrix.length;
      
       // Step 1: Transpose the matrix
       for (int i = 0; i < n; i++) {
           for (int j = i; j < n; j++) {
               // Swap elements to transpose the matrix
               int temp = matrix[i][j];
               matrix[i][j] = matrix[j][i];
               matrix[j][i] = temp;
           }
       }

       // Step 2: Reverse each row to achieve rotation
       for (int i = 0; i < n; i++) {
           for (int j = 0; j < n / 2; j++) {
               // Swap elements to reverse the row
               int temp = matrix[i][j];
               matrix[i][j] = matrix[i][n - j - 1];
               matrix[i][n - j - 1] = temp;
           }
       }
   }

   // Method to print the matrix
   public static void printMatrix(int[][] matrix) {
       for (int[] row : matrix) {
           for (int val : row) {
               System.out.print(val + " ");
           }
           System.out.println();
       }
   }

   public static void main(String[] args) {
       int[][] image = {
           {1, 2, 3},
           {4, 5, 6},
           {7, 8, 9}
       };
       rotate(image);
       printMatrix(image); // Output rotated matrix
   }
}
```

##### Time Complexity:
- O(n²), where n is the number of rows (or columns) in the matrix, as we traverse the entire matrix twice.

##### Space Complexity:
- O(1), since we are performing the rotation in place without using extra space.
***



#### Set Matrix Zeros
##### Pattern: Matrix Manipulation
[Back to Top](#table-of-contents)
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



#### Sub arrays Product Less than Target

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



#### Longest Substring No Repeats
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

#### Task Scheduler
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

#### Meeting Rooms II
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


