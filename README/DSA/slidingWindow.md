# Sliding Window
[HOME.md](HOME.md)
=================
## Table of contents

<!--ts-->
|                                                                                                   |    |    |    |    |
|:--------------------------------------------------------------------------------------------------|:---|:---|:---|:---|
| [AlgoMap](#algomap)                                                                               |    |    |    |    |
| [Maximum Average Subarray I](#maximum-average-subarray-i)                                         |    |    |    |    |
| [Max Consecutive Ones III](#max-consecutive-ones-iii)                                             |    |    |    |    |
| [Longest Substring Without Repeating Characters](#longest-substring-without-repeating-characters) |    |    |    |    |
| [Longest Repeating Character Replacement](#longest-repeating-character-replacement)               |    |    |    |    |
| [Minimum Size Subarray Sum](#minimum-size-subarray-sum)                                           |    |    |    |    |
| [Permutation in String](#permutation-in-string)                                                                       |    |    |    |    |


#### AlgoMap
[Back to Top](#table-of-contents)
***

#### Maximum Average Subarray I
##### Pattern: Sliding Window
[Back to Top](#table-of-contents)
##### Description:
- Input: `nums = [1, 12, -5, -6, 50, 3]`, `k = 4`
- Output: `12.75`
- Explanation: The subarray `[12, -5, -6, 50]` has the maximum average of 12.75.

```java
public class MaximumAverageSubarray {
    public double findMaxAverage(int[] nums, int k) {
        // Initialize sum for the first 'k' elements
        double sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        // Set maxSum as the initial sum
        double maxSum = sum;

        // Sliding window: Slide the window by one element at a time
        for (int i = k; i < nums.length; i++) {
            // Update the sum by subtracting the first element of the window and adding the new element
            sum = sum - nums[i - k] + nums[i];
            // Update maxSum if current sum is larger
            maxSum = Math.max(maxSum, sum);
        }

        // Return the maximum average
        return maxSum / k;
    }
}
```
##### Time Complexity:
- O(n) — We iterate through the array once.

##### Space Complexity:
- O(1) — Constant space is used regardless of input size.
***

#### Max Consecutive Ones III
##### Pattern: Sliding Window
[Back to Top](#table-of-contents)
##### Description:
- Input: `nums = [1,1,0,0,1,1,0,1,1,1], k = 2`
- Output: `6`
- Explanation: We are allowed to flip at most `k = 2` zeros to ones. The longest subarray with consecutive 1's after flipping is `[1,1,1,1,1,1]` of length `6`.

```java
public class MaxConsecutiveOnesIII {
    public int longestOnes(int[] nums, int k) {
        // Two pointers for sliding window
        int left = 0, right = 0;
        // To count the zeros in the window
        int zeroCount = 0;
        // Maximum length of consecutive ones
        int maxLength = 0;

        // Iterate through the array
        while (right < nums.length) {
            // If we encounter a 0, increase the zeroCount
            if (nums[right] == 0) {
                zeroCount++;
            }

            // If zeroCount exceeds k, shrink the window from the left
            while (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;  // Move the left pointer
            }

            // Update the maximum length
            maxLength = Math.max(maxLength, right - left + 1);

            // Move the right pointer
            right++;
        }

        return maxLength;
    }
}
```

##### Time Complexity:
- The time complexity is `O(n)` where `n` is the length of the input array, as each element is visited at most twice.

##### Space Complexity:
- The space complexity is `O(1)` since we are using a constant amount of extra space.
***


#### Longest Substring Without Repeating Characters
##### Pattern: Sliding Window
[Back to Top](#table-of-contents)
##### Description:
- Input: `"abcabcbb"`
- Output: `3` (The longest substring is `"abc"`)
- Explanation: The function finds the length of the longest substring that contains all unique characters. In this case, the substring `"abc"` has a length of 3.

```java
import java.util.HashMap;

public class LongestSubstringWithoutRepeating {
    public static int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> charIndexMap = new HashMap<>(); // To store the last index of each character
        int maxLength = 0; // Variable to keep track of the maximum length of substring
        int start = 0; // Left pointer for the sliding window

        for (int end = 0; end < s.length(); end++) {
            // If the character is already in the map, move the start pointer
            if (charIndexMap.containsKey(s.charAt(end))) {
                // Update the start pointer to the index after the last occurrence
                start = Math.max(start, charIndexMap.get(s.charAt(end)) + 1);
            }
            // Update the last index of the character
            charIndexMap.put(s.charAt(end), end);
            // Calculate the length of the current substring and update maxLength
            maxLength = Math.max(maxLength, end - start + 1);
        }
        return maxLength; // Return the maximum length found
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb")); // Output: 3
        System.out.println(lengthOfLongestSubstring("bbbbb"));    // Output: 1
        System.out.println(lengthOfLongestSubstring("pwwkew"));   // Output: 3
    }
}
```
##### Time Complexity:
- O(n): The algorithm traverses the string once, where n is the length of the string.

##### Space Complexity:
- O(min(n, m)): The space complexity is determined by the size of the HashMap, where n is the size of the string and m is the size of the character set.
***


#### Longest Repeating Character Replacement
##### Pattern: Sliding Window
[Back to Top](#table-of-contents)

##### Description:
- Input: `"AABABBA"`, `k = 1`
- Output: `4`
- Explanation: By replacing one 'B' with 'A', the substring "AAAA" (length 4) can be formed.

```java
import java.util.HashMap;

public class LongestRepeatingCharacterReplacement {
    public static int characterReplacement(String s, int k) {
        // Create a HashMap to count frequency of characters in the current window
        HashMap<Character, Integer> charCount = new HashMap<>();
        int maxLength = 0; // To keep track of the maximum length of substring found
        int maxCount = 0;  // To keep track of the count of the most frequently occurring character
        int left = 0;      // Left pointer of the sliding window

        for (int right = 0; right < s.length(); right++) {
            // Count the frequency of the current character
            charCount.put(s.charAt(right), charCount.getOrDefault(s.charAt(right), 0) + 1);
            // Update the count of the most frequent character in the current window
            maxCount = Math.max(maxCount, charCount.get(s.charAt(right)));

            // Check if we need to shrink the window
            // Condition: Total characters in the window - count of the most frequent character > k
            if (right - left + 1 - maxCount > k) {
                // Decrease the count of the leftmost character as we move the left pointer
                charCount.put(s.charAt(left), charCount.get(s.charAt(left)) - 1);
                left++; // Move the left pointer to the right
            }

            // Update the maximum length found so far
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength; // Return the maximum length of the substring found
    }

    public static void main(String[] args) {
        String s = "AABABBA";
        int k = 1;
        System.out.println("The length of the longest substring is: " + characterReplacement(s, k)); // Output: 4
    }
}
```

##### Time Complexity:
- The time complexity is O(n), where n is the length of the string. Each character is processed at most twice (once by the right pointer and once by the left pointer).

##### Space Complexity:
- The space complexity is O(1) since the size of the hashmap is bounded by the number of distinct characters (in this case, at most 26 for uppercase letters).

***

#### Minimum Size Subarray Sum
##### Pattern: Sliding Window
[Back to Top](#table-of-contents)
##### Description:
- Input: `target = 7`, `nums = [2,3,1,2,4,3]`
- Output: `2`
- Explanation: The smallest subarray with a sum of at least `7` is `[4,3]`, which has a length of `2`.

```java
import java.util.Arrays;

public class MinimumSizeSubarraySum {
    public static int minSubArrayLen(int target, int[] nums) {
        int left = 0; // Left pointer for the sliding window
        int sum = 0; // Current sum of the window
        int minLength = Integer.MAX_VALUE; // Initialize minimum length to max value

        // Iterate through the array with the right pointer
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right]; // Add current number to the sum

            // While current sum is greater than or equal to the target
            while (sum >= target) {
                // Update minimum length if current window is smaller
                minLength = Math.min(minLength, right - left + 1);
                sum -= nums[left]; // Shrink the window from the left
                left++; // Move the left pointer to the right
            }
        }

        // Return 0 if no valid subarray found, otherwise return minimum length
        return (minLength == Integer.MAX_VALUE) ? 0 : minLength;
    }

    public static void main(String[] args) {
        int target = 7;
        int[] nums = {2, 3, 1, 2, 4, 3};
        System.out.println("Minimum size subarray length: " + minSubArrayLen(target, nums));
    }
}
```
##### Time Complexity:
- The time complexity is O(n), where n is the number of elements in the `nums` array, as each element is processed at most twice (once by the right pointer and once by the left pointer).

##### Space Complexity:
- The space complexity is O(1), as we are using a fixed amount of extra space regardless of the input size.
***

#### Permutation in String
##### Pattern: Sliding Window
[Back to Top](#table-of-contents)
##### Description:
- Input: `s1 = "ab"` and `s2 = "eidbaooo"`
- Output: `true`
- Explanation: The string `s2` contains a permutation of `s1` as a substring (`"ba"`).

```java
import java.util.HashMap;

public class PermutationInString {
    public static boolean checkInclusion(String s1, String s2) {
        // Create a frequency map for s1
        HashMap<Character, Integer> s1Count = new HashMap<>();
        for (char c : s1.toCharArray()) {
            s1Count.put(c, s1Count.getOrDefault(c, 0) + 1);
        }

        // Sliding window variables
        int left = 0, right = 0, required = s1Count.size(), formed = 0;
        HashMap<Character, Integer> windowCount = new HashMap<>();

        // Expand the window with the right pointer
        while (right < s2.length()) {
            char c = s2.charAt(right);
            windowCount.put(c, windowCount.getOrDefault(c, 0) + 1);
            
            // Check if the current character frequency matches that in s1
            if (s1Count.containsKey(c) && windowCount.get(c).intValue() == s1Count.get(c).intValue()) {
                formed++;
            }

            // Contract the window from the left
            while (formed == required) {
                // If the window size matches s1's length, return true
                if (right - left + 1 == s1.length()) {
                    return true;
                }
                char leftChar = s2.charAt(left);
                windowCount.put(leftChar, windowCount.get(leftChar) - 1);
                
                // Update the formed count if necessary
                if (s1Count.containsKey(leftChar) && windowCount.get(leftChar).intValue() < s1Count.get(leftChar).intValue()) {
                    formed--;
                }
                left++; // Move the left pointer forward
            }
            right++; // Move the right pointer forward
        }
        return false; // No permutation found
    }

    public static void main(String[] args) {
        System.out.println(checkInclusion("ab", "eidbaooo")); // true
        System.out.println(checkInclusion("ab", "eidboaoo")); // false
    }
}
```
##### Time Complexity:
- O(n), where n is the length of `s2`, since we traverse `s2` once with the sliding window.
##### Space Complexity:
- O(m), where m is the size of the character set (e.g., 26 for lowercase English letters) used to store character frequencies.
***

