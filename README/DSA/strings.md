# Strings

[HOME.md](HOME.md)
==================

## Table of contents

<!--ts-->

| Category                                    | Problems                              |                                         |                                                         |                                                 |
|:--------------------------------------------|:--------------------------------------|:----------------------------------------|:--------------------------------------------------------|:------------------------------------------------|
| [Sliding Window](#sliding-window)           | [Valid Palindrome](#valid-palindrome) | [Three Sum Problem](#three-sum-problem) | [Container with most Water](#container-with-most-water) | [Product except itself](#product-except-itself) |
| [Palindromic](#palindromic)                 |                                       |                                         |                                                         |                                                 |
| [Anagrams](#anagrams)                       |                                       |                                         |                                                         |                                                 |
| [Parentheses Stack](#parentheses-stack)     |                                       |                                         |                                                         |                                                 |
| [String Manipulation](#string-manipulation) |                                       |                                         |                                                         |                                                 |
|                                             |                                       |                                         |                                                         |                                                 |


***

### Sliding Window

#### Longest Substring Without Repeating Characters

##### Pattern: Sliding Window

[Back to Top](#Table-of-contents)

##### Description:

- Input: `"abcabcbb"`
- Output: `3`
- Explanation: The answer is `"abc"` with the length of `3`.

```java
import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeating {
    public int lengthOfLongestSubstring(String s) {
        // HashSet to store the characters in the current window
        Set<Character> set = new HashSet<>();
        int maxLength = 0;
        int i = 0, j = 0;
        
        // Using sliding window approach
        while (i < s.length() && j < s.length()) {
            // If character is not in set, add it and update maxLength
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                maxLength = Math.max(maxLength, j - i);
            } else {
                // If character is in set, remove the character from the left
                set.remove(s.charAt(i++));
            }
        }
        
        return maxLength;
    }

}
```

##### Time Complexity:

- The time complexity is `O(n)`, where `n` is the length of the string.
  Each character is processed at most twice, once by the right pointer
  and once by the left pointer.

##### Space Complexity:

- The space complexity is `O(min(n, m))`, where `n` is the length of the
  string and `m` is the size of the character set. This is due to the
  extra space used by the HashSet to store the characters of the
  substring.


***

### Palindromic

#### Longest Palindromic Substring

##### Pattern: Dynamic Programming

[Back to Top](#Table-of-contents)

##### Description:

- Input: "babad"
- Output: "bab" or "aba"
- Explanation: The longest palindromic substrings in "babad" are "bab"
  and "aba", both have the same length.

```java
public class LongestPalindromicSubstring {

    // Function to find the longest palindromic substring
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        
        // Start and end pointers for the longest palindromic substring
        int start = 0, end = 0;
        
        // Loop through each character in the string
        for (int i = 0; i < s.length(); i++) {
            // Check for odd-length palindromes centered at i
            int len1 = expandAroundCenter(s, i, i);
            // Check for even-length palindromes centered between i and i+1
            int len2 = expandAroundCenter(s, i, i + 1);
            // Take the maximum length of the two palindromes
            int len = Math.max(len1, len2);
            // Update start and end if we found a longer palindrome
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        // Return the longest palindromic substring
        return s.substring(start, end + 1);
    }

    // Helper function to expand around the center and check for palindromes
    private static int expandAroundCenter(String s, int left, int right) {
        // Expand while the characters at left and right are equal
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // Return the length of the palindrome
        return right - left - 1;
    }
}
```

##### Time Complexity:

- The time complexity is O(n^2), where n is the length of the input
  string. This is because for each character, we might expand around it
  to check for palindromes, which takes linear time.

##### Space Complexity:

- The space complexity is O(1) as we are using only a few additional
  variables and not any extra space that scales with the input size.

***

#### Longest Common Subsequence (Top-Down Approach Without Memoization)

##### Pattern: Dynamic Programming

[Back to Top](#Table-of-contents)

##### Description:

- **Input:** Two strings, e.g., `s1 = "abcde"` and `s2 = "ace"`
- **Output:** Length of the longest common subsequence, e.g., `3`
- **Explanation:** The longest common subsequence of `"abcde"` and
  `"ace"` is `"ace"`, which has a length of `3`.

```java
public class LongestCommonSubsequence {
    // Method to find the length of the longest common subsequence
    public static int lcs(String s1, String s2, int m, int n) {
        // Base case: if either string is empty
        if (m == 0 || n == 0) {
            return 0;
        }

        // If the last characters of both substrings match
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            return 1 + lcs(s1, s2, m - 1, n - 1);
        } else {
            // If the last characters don't match, consider two cases:
            // 1. Exclude the last character of the first string
            // 2. Exclude the last character of the second string
            return Math.max(lcs(s1, s2, m - 1, n), lcs(s1, s2, m, n - 1));
        }
    }

    public static void main(String[] args) {
        String s1 = "abcde";
        String s2 = "ace";
        int result = lcs(s1, s2, s1.length(), s2.length());
        System.out.println("Length of Longest Common Subsequence: " + result);
    }
}
```

##### Time Complexity:

- The time complexity is `O(2^n)` because there are two recursive calls
  for each character of the strings, resulting in an exponential number
  of recursive calls.

##### Space Complexity:

- The space complexity is `O(n + m)` due to the recursive stack space,
  where `n` and `m` are the lengths of the two strings.

***

#### Longest Common Subsequence (Top-Down Approach + Memoization)

##### Pattern: Dynamic Programming

[Back to Top](#Table-of-contents)

##### Description:

- Input: `text1 = "abcde"`, `text2 = "ace"`
- Output: `3`
- Explanation: The longest common subsequence is `"ace"`, which has
  length 3.

```java
public class LongestCommonSubsequence {
    
    public int longestCommonSubsequence(String text1, String text2) {
        // Create a memoization table
        Integer[][] memo = new Integer[text1.length()][text2.length()];
        
        // Call the helper function with initial parameters
        return lcsHelper(text1, text2, 0, 0, memo);
    }

    private int lcsHelper(String text1, String text2, int index1, int index2, Integer[][] memo) {
        // Base case: if either string is exhausted
        if (index1 == text1.length() || index2 == text2.length()) {
            return 0;
        }

        // If the result is already computed, return it from the memoization table
        if (memo[index1][index2] != null) {
            return memo[index1][index2];
        }

        // If characters match, increment the count and move to the next characters in both strings
        if (text1.charAt(index1) == text2.charAt(index2)) {
            memo[index1][index2] = 1 + lcsHelper(text1, text2, index1 + 1, index2 + 1, memo);
        } else {
            // Otherwise, try moving to the next character in either of the strings
            memo[index1][index2] = Math.max(
                lcsHelper(text1, text2, index1 + 1, index2, memo),
                lcsHelper(text1, text2, index1, index2 + 1, memo)
            );
        }

        return memo[index1][index2];
    }
}
```

##### Time Complexity:

- `O(m * n)`, where `m` and `n` are the lengths of `text1` and `text2`,
  respectively. Each state is computed once.

##### Space Complexity:

- `O(m * n)`, due to the memoization table that stores results for each
  pair of indices.

***


### Anagrams

#### Valid Anagram

##### Pattern: Hashing

[Back to Top](#Table-of-contents)

##### Description:

- Input: `s = "anagram", t = "nagaram"`
- Output: `true`
- Explanation: The characters in `t` can be rearranged to form `s`.

- Input: `s = "rat", t = "car"`
- Output: `false`
- Explanation: The characters in `t` cannot be rearranged to form `s`.

```java
public class ValidAnagram {

    // Function to check if two strings are anagrams
    public static boolean isAnagram(String s, String t) {
        // If the lengths of the strings are not equal, they cannot be anagrams
        if (s.length() != t.length()) {
            return false;
        }

        // Create an array to count the frequency of each character
        int[] charCount = new int[26];

        // Iterate over the first string and increase the count of each character
        for (int i = 0; i < s.length(); i++) {
            charCount[s.charAt(i) - 'a']++;
        }

        // Iterate over the second string and decrease the count of each character
        for (int i = 0; i < t.length(); i++) {
            charCount[t.charAt(i) - 'a']--;
        }

        // Check if all counts are zero
        for (int count : charCount) {
            if (count != 0) {
                return false; // If any count is not zero, the strings are not anagrams
            }
        }

        // If all counts are zero, the strings are anagrams
        return true;
    }

    // Main method to test the function
    public static void main(String[] args) {
        // Example 1
        String s1 = "anagram";
        String t1 = "nagaram";
        System.out.println("Example 1: " + isAnagram(s1, t1)); // Output: true

        // Example 2
        String s2 = "rat";
        String t2 = "car";
        System.out.println("Example 2: " + isAnagram(s2, t2)); // Output: false
    }
}
```

##### Time Complexity:

- The time complexity is O(n), where n is the length of the strings. We
  iterate over the strings a constant number of times.

##### Space Complexity:

- The space complexity is O(1) because the array used to count
  characters is of fixed size (26 characters).

***

#### Group Anagrams

##### Pattern: Hashing

[Back to Top](#Table-of-contents)

##### Description:

- Input: `["eat", "tea", "tan", "ate", "nat", "bat"]`
- Output: `[["eat", "tea", "ate"], ["tan", "nat"], ["bat"]]`
- Explanation: Words like "eat", "tea", and "ate" are anagrams and hence
  grouped together. Similarly, "tan" and "nat" are grouped, and "bat"
  stands alone.

```java
import java.util.*;

public class GroupAnagrams {

    public static List<List<String>> groupAnagrams(String[] strs) {
        // Using a HashMap to store the anagrams
        Map<String, List<String>> map = new HashMap<>();
        
        for (String str : strs) {
            // Convert the string to a char array, sort it, and convert it back to a string
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String sortedStr = new String(charArray);
            
            // If the sorted string is not yet in the map, add it with a new list
            if (!map.containsKey(sortedStr)) {
                map.put(sortedStr, new ArrayList<>());
            }
            
            // Add the original string to the corresponding list
            map.get(sortedStr).add(str);
        }
        
        // Return all the values in the map as a list of lists
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(input));
    }
}
```

##### Time Complexity:

- The time complexity is `O(N * K log K)`, where `N` is the number of
  strings and `K` is the maximum length of a string. Sorting each string
  takes `O(K log K)` and we do this for each string.

##### Space Complexity:

- The space complexity is `O(N * K)`, where `N` is the number of strings
  and `K` is the maximum length of a string. We store all strings in the
  hash map.

***


***

### Parentheses Stack

#### Valid Parentheses

##### Pattern: Stack

[Back to Top](#Table-of-contents)

##### Description:

- Input: `"()[]{}"`, `"(]"`, `"{[()]}"`, `"{[(])}"`
- Output: `true`, `false`, `true`, `false`
- Explanation:
  - `"()[]{}"` is valid because all brackets are closed properly.
  - `"(]"` is invalid because the parentheses and square brackets are
    mismatched.
  - `"{[()]}"` is valid because all types of brackets are properly
    nested and closed.
  - `"{[(])}"` is invalid because the brackets are not properly closed
    in the correct order.

```java
import java.util.Stack;

public class ValidParentheses {
    public static boolean isValid(String s) {
        // Create a stack to keep track of opening brackets
        Stack<Character> stack = new Stack<>();
        
        // Traverse the input string
        for (char c : s.toCharArray()) {
            // If the character is an opening bracket, push it onto the stack
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } 
            // If the character is a closing bracket
            else {
                // Check if the stack is empty (invalid case)
                if (stack.isEmpty()) {
                    return false;
                }
                // Pop the top element from the stack
                char top = stack.pop();
                // Check if the popped element matches the current closing bracket
                if ((c == ')' && top != '(') || 
                    (c == '}' && top != '{') || 
                    (c == ']' && top != '[')) {
                    return false;
                }
            }
        }
        
        // If the stack is empty at the end, all brackets were matched; otherwise, invalid
        return stack.isEmpty();
    }
    
    public static void main(String[] args) {
        System.out.println(isValid("()[]{}")); // true
        System.out.println(isValid("(]"));     // false
        System.out.println(isValid("{[()]}")); // true
        System.out.println(isValid("{[(])}")); // false
    }
}
```

##### Time Complexity:

- O(n) - where `n` is the length of the input string. Each character is
  processed once.

##### Space Complexity:

- O(n) - in the worst case, all opening brackets are pushed onto the
  stack.

***

### String Manipulation

#### String to Integer (atoi)

##### Pattern: String Manipulation

[Back to Top](#Table-of-contents)

##### Description:

- Input: `" 4193 with words"`
- Output: `4193`
- Explanation: Leading whitespace is ignored, and conversion stops at
  the first non-digit character.

```java
public class StringToInteger {
    public int myAtoi(String s) {
        // Edge case: Empty string
        if (s == null || s.length() == 0) return 0;

        int index = 0, sign = 1, total = 0;
        int length = s.length();

        // Remove leading whitespaces
        while (index < length && s.charAt(index) == ' ') {
            index++;
        }

        // Check for sign
        if (index < length && (s.charAt(index) == '+' || s.charAt(index) == '-')) {
            sign = s.charAt(index) == '-' ? -1 : 1;
            index++;
        }

        // Convert characters to integer
        while (index < length && Character.isDigit(s.charAt(index))) {
            int digit = s.charAt(index) - '0';

            // Check for overflow
            if (total > (Integer.MAX_VALUE - digit) / 10) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            total = total * 10 + digit;
            index++;
        }

        return total * sign;
    }
}
```

##### Time Complexity:

- **O(n)**, where `n` is the length of the string. We iterate through
  the string a few times.

##### Space Complexity:

- **O(1)**, as we use a constant amount of extra space.

***

#### Implement strStr()

##### Pattern: Substring Search

[Back to Top](#Table-of-contents)

##### Description:

- Input:
  - `haystack = "hello", needle = "ll"`
  - `haystack = "aaaaa", needle = "bba"`
  - `haystack = "", needle = ""`
- Output:
  - `2`
  - `-1`
  - `0`
- Explanation:
  - In the first example, "ll" is found at index 2 in "hello".
  - In the second example, "bba" is not found in "aaaaa".
  - In the third example, both strings are empty, so the result is 0.

```java
public class Solution {
    /**
     * Implement strStr() function.
     * 
     * @param haystack The main string to search within.
     * @param needle The substring to search for.
     * @return The index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
     */
    public int strStr(String haystack, String needle) {
        // If needle is an empty string, return 0
        if (needle.isEmpty()) {
            return 0;
        }
        
        // Get lengths of haystack and needle
        int haystackLength = haystack.length();
        int needleLength = needle.length();
        
        // Iterate through haystack to find the needle
        for (int i = 0; i <= haystackLength - needleLength; i++) {
            // Check if the substring from current index matches the needle
            if (haystack.substring(i, i + needleLength).equals(needle)) {
                return i; // Return the index if needle is found
            }
        }
        
        return -1; // Return -1 if needle is not found
    }
}
```

##### Time Complexity:

- The time complexity is O(n * m), where n is the length of the haystack
  and m is the length of the needle. This is because for each character
  in the haystack, we may compare up to m characters of the needle.

##### Space Complexity:

- The space complexity is O(1), as we only use a fixed amount of extra
  space (variables for lengths and loop indices).

***

#### Longest Common Prefix

##### Pattern: String Manipulation

[Back to Top](#Table-of-contents)

##### Description:

- Input: `["flower","flow","flight"]`
- Output: `"fl"`
- Explanation: The longest common prefix for the input array is `"fl"`
  as it is the longest string that is a prefix of all the input strings.

```java
public class LongestCommonPrefix {
    // Method to find the longest common prefix
    public static String longestCommonPrefix(String[] strs) {
        // If the array is empty, return an empty string
        if (strs == null || strs.length == 0) return "";
        
        // Start with the first string in the array as the prefix
        String prefix = strs[0];
        
        // Loop through the rest of the strings
        for (int i = 1; i < strs.length; i++) {
            // Check the prefix against each string and shorten the prefix if needed
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                // If the prefix is reduced to an empty string, return an empty string
                if (prefix.isEmpty()) return "";
            }
        }
        
        // Return the longest common prefix
        return prefix;
    }

}
```

##### Time Complexity:

- O(S) where S is the sum of all characters in all strings. In the worst
  case, we compare every character of every string.

##### Space Complexity:

- O(1) since we use only a constant amount of extra space.

***


#### Roman to Integer

##### Pattern: String Manipulation

[Back to Top](#Table-of-contents)

##### Description:

- Input: "III", "IV", "IX", "LVIII", "MCMXCIV"
- Output: 3, 4, 9, 58, 1994
- Explanation: Converts a Roman numeral to an integer. Roman numerals
  are represented by seven different symbols: I, V, X, L, C, D, and M.

```java
public class RomanToInteger {

    // Method to convert Roman numeral to Integer
    public static int romanToInt(String s) {
        // Mapping of Roman numerals to their integer values
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        int total = 0;
        int prevValue = 0;

        // Traverse the string from the end to the beginning
        for (int i = s.length() - 1; i >= 0; i--) {
            int currentValue = romanMap.get(s.charAt(i));

            // If the current value is less than the previous value, it means we need to subtract
            if (currentValue < prevValue) {
                total -= currentValue;
            } else {
                // Otherwise, we add the current value
                total += currentValue;
            }

            // Update previous value
            prevValue = currentValue;
        }

        return total;
    }

    // Main method to test the romanToInt method
    public static void main(String[] args) {
        // Test cases
        System.out.println(romanToInt("III")); // Output: 3
        System.out.println(romanToInt("IV")); // Output: 4
        System.out.println(romanToInt("IX")); // Output: 9
        System.out.println(romanToInt("LVIII")); // Output: 58
        System.out.println(romanToInt("MCMXCIV")); // Output: 1994
    }
}
```

##### Time Complexity:

- O(n), where n is the length of the string. We traverse the string
  once.

##### Space Complexity:

- O(1), constant space is used for the hashmap and a few integer
  variables.

***

***

#### Integer to Roman

##### Pattern: Conversion

[Back to Top](#Table-of-contents)

##### Description:

- Input: 58
- Output: "LVIII"
- Explanation: 58 is represented as "LVIII" in Roman numerals (L = 50, V
  = 5, III = 3).

```java
public class IntegerToRoman {

    public static String intToRoman(int num) {
        // Array of Roman numerals and their corresponding integer values
        String[] romanNumerals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        
        // StringBuilder to build the Roman numeral string
        StringBuilder roman = new StringBuilder();
        
        // Iterate through the values and construct the Roman numeral
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                // Append the Roman numeral to the result
                roman.append(romanNumerals[i]);
                // Subtract the value from the number
                num -= values[i];
            }
        }
        return roman.toString();
    }

    // Example usage
    public static void main(String[] args) {
        int number = 58;
        System.out.println("The Roman numeral for " + number + " is " + intToRoman(number));
    }
}
```

##### Time Complexity:

- The time complexity is O(1) because the algorithm always processes a
  fixed number of Roman numeral symbols, regardless of the input number.

##### Space Complexity:

- The space complexity is O(1) because the space used by the output
  string is proportional to the number of symbols, which is fixed and
  does not depend on the input size.

***

#### Integer to Binary

##### Pattern: Mathematical Calculation

[Back to Top](#Table-of-contents)

##### Description:

- Input: 10
- Output: 1010
- Explanation: The binary representation of the integer 10 is 1010.

```java
public class IntegerToBinary {
    // Method to convert an integer to its binary representation
    public static String toBinary(int number) {
        // Using StringBuilder to construct the binary representation
        StringBuilder binary = new StringBuilder();

        // Special case for zero
        if (number == 0) {
            return "0";
        }

        // Loop until the number is greater than zero
        while (number > 0) {
            // Get the remainder when divided by 2 (either 0 or 1)
            int remainder = number % 2;
            // Append the remainder to the binary string
            binary.append(remainder);
            // Divide the number by 2
            number = number / 2;
        }

        // Reverse the binary string to get the correct representation
        return binary.reverse().toString();
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test cases
        System.out.println(toBinary(10));  // Output: 1010
        System.out.println(toBinary(0));   // Output: 0
        System.out.println(toBinary(7));   // Output: 111
        System.out.println(toBinary(15));  // Output: 1111
    }
}
```

##### Time Complexity:

- The time complexity is O(log N) where N is the input number. This is
  because we divide the number by 2 in each iteration, which reduces the
  number of iterations logarithmically.

##### Space Complexity:

- The space complexity is O(log N) due to the space required to store
  the binary representation, which is proportional to the number of
  digits in the binary form of the number.

***

#### Integer to Binary (Using Queue)

##### Pattern: Queue

[Back to Top](#Table-of-contents)

##### Description:

- Input: 10
- Output: 1010
- Explanation: The binary representation of the integer 10 is 1010.

```java
import java.util.LinkedList;
import java.util.Queue;

public class IntegerToBinaryQueue {
    // Method to convert an integer to its binary representation using a queue
    public static String toBinary(int number) {
        // Special case for zero
        if (number == 0) {
            return "0";
        }

        // Create a queue to store binary digits
        Queue<Character> queue = new LinkedList<>();

        // Process the number until it becomes zero
        while (number > 0) {
            // Get the remainder (0 or 1) and add to the queue
            queue.add((char) ('0' + (number % 2)));
            // Divide the number by 2
            number = number / 2;
        }

        // Use StringBuilder to construct the binary string
        StringBuilder binary = new StringBuilder();

        // Dequeue elements and append to StringBuilder
        while (!queue.isEmpty()) {
            binary.append(queue.poll());
        }

        // Reverse the string to get the correct binary representation
        return binary.reverse().toString();
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test cases
        System.out.println(toBinary(10));  // Output: 1010
        System.out.println(toBinary(0));   // Output: 0
        System.out.println(toBinary(7));   // Output: 111
        System.out.println(toBinary(15));  // Output: 1111
    }
}
```

##### Time Complexity:

- The time complexity is O(log N) where N is the input number. The
  number of operations is proportional to the number of bits in the
  binary representation.

##### Space Complexity:

- The space complexity is O(log N) due to the space required for the
  queue and the binary string, which is proportional to the number of
  bits in the binary representation.

***

