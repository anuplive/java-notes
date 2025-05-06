# Longest Common Subsequence

## Pattern:
Dynamic Programming - String Matching

## Description:
Find the length of the longest subsequence common to two strings. A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.

### Input:
* Two strings, text1 and text2

### Output:
* An integer representing the length of the longest common subsequence

### Examples:
* Input: text1 = "abcde", text2 = "ace"  
  Output: 3  
  Explanation: The longest common subsequence is "ace" with length 3.

* Input: text1 = "abc", text2 = "abc"  
  Output: 3  
  Explanation: The longest common subsequence is "abc" with length 3.

* Input: text1 = "abc", text2 = "def"  
  Output: 0  
  Explanation: There is no common subsequence between the two strings.

```java
/**
 * Java solution for finding the Longest Common Subsequence of two strings using recursion.
 */
public class LongestCommonSubsequence {
    
    /**
     * Find the length of the longest common subsequence using recursion.
     * 
     * @param text1 First string
     * @param text2 Second string
     * @return Length of longest common subsequence
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        // Call the recursive helper function with the full length of both strings
        return lcsRecursive(text1, text2, text1.length(), text2.length());
    }
    
    /**
     * Recursive function to find the length of the longest common subsequence.
     * 
     * @param text1 First string
     * @param text2 Second string
     * @param m Length of text1 being considered
     * @param n Length of text2 being considered
     * @return Length of LCS of text1[0...m-1] and text2[0...n-1]
     */
    private static int lcsRecursive(String text1, String text2, int m, int n) {
        // Base case: If either string has no characters left, return 0
        if (m == 0 || n == 0) {
            return 0;
        }
        
        // If the last characters of both strings match
        if (text1.charAt(m - 1) == text2.charAt(n - 1)) {
            // Include this character in LCS and recurse for the remaining strings
            return 1 + lcsRecursive(text1, text2, m - 1, n - 1);
        } else {
            // Last characters don't match, consider two cases:
            // 1. Exclude last character of text1 and recurse
            // 2. Exclude last character of text2 and recurse
            // Take the maximum of these two cases
            return Math.max(
                lcsRecursive(text1, text2, m - 1, n),
                lcsRecursive(text1, text2, m, n - 1)
            );
        }
    }
    
    /**
     * Main method for testing the LCS function.
     */
    public static void main(String[] args) {
        // Test case 1
        String text1 = "abcde";
        String text2 = "ace";
        int lcsLength = longestCommonSubsequence(text1, text2);
        System.out.println("Text1: " + text1);
        System.out.println("Text2: " + text2);
        System.out.println("LCS Length: " + lcsLength);  // Expected: 3
        
        // Test case 2
        text1 = "abc";
        text2 = "abc";
        lcsLength = longestCommonSubsequence(text1, text2);
        System.out.println("\nText1: " + text1);
        System.out.println("Text2: " + text2);
        System.out.println("LCS Length: " + lcsLength);  // Expected: 3
        
        // Test case 3
        text1 = "abc";
        text2 = "def";
        lcsLength = longestCommonSubsequence(text1, text2);
        System.out.println("\nText1: " + text1);
        System.out.println("Text2: " + text2);
        System.out.println("LCS Length: " + lcsLength);  // Expected: 0
    }
}
```

## Time Complexity:
* O(2^(m+n)) - Where m and n are the lengths of the two input strings. This is because there are two recursive calls in the worst case at each step, leading to exponential complexity.

## Space Complexity:
* O(m+n) - The space complexity is determined by the maximum depth of the recursion stack, which is at most m+n.

## Algorithm:
* Base case: If either string is empty, return 0
* If the last characters of both strings match:
  * Include this character in LCS and recursively find LCS of the remaining strings
* If the last characters don't match:
  * Recursively find LCS by either:
    * Excluding the last character of the first string, or
    * Excluding the last character of the second string
  * Take the maximum of these two results
* Return the final LCS length

***

# Longest Palindromic Substring

## Pattern:
Dynamic Programming - String Manipulation

## Description:
Find the longest substring in a string that is a palindrome. A palindrome is a string that reads the same backward as forward.

### Input:
* A string s

### Output:
* The longest palindromic substring of s

### Examples:
* Input: s = "babad"  
  Output: "bab" or "aba"  
  Explanation: Both "bab" and "aba" are valid palindromic substrings of maximum length 3.

* Input: s = "cbbd"  
  Output: "bb"  
  Explanation: "bb" is the longest palindromic substring with length 2.

* Input: s = "a"  
  Output: "a"  
  Explanation: A single character is always a palindrome.

```java
/**
 * Java solution to find the longest palindromic substring using recursion.
 */
public class LongestPalindromicSubstring {
    
    // Variables to track the longest palindrome found
    private static int startIndex = 0;
    private static int maxLength = 0;
    
    /**
     * Find the longest palindromic substring in a given string.
     * 
     * @param s Input string
     * @return Longest palindromic substring
     */
    public static String longestPalindrome(String s) {
        // Handle edge cases
        if (s == null || s.length() < 1) {
            return "";
        }
        
        // Reset the global variables
        startIndex = 0;
        maxLength = 0;
        
        // Try every character as a potential center of palindrome
        for (int i = 0; i < s.length(); i++) {
            // For odd length palindromes (like "aba")
            findPalindromeFromCenter(s, i, i);
            
            // For even length palindromes (like "abba")
            findPalindromeFromCenter(s, i, i + 1);
        }
        
        // Return the longest palindromic substring found
        return s.substring(startIndex, startIndex + maxLength);
    }
    
    /**
     * Recursive function to expand around a center to find palindromes.
     * 
     * @param s Input string
     * @param left Left pointer (moving left from center)
     * @param right Right pointer (moving right from center)
     */
    private static void findPalindromeFromCenter(String s, int left, int right) {
        // Base case: if we've reached the boundaries or characters don't match
        if (left < 0 || right >= s.length() || s.charAt(left) != s.charAt(right)) {
            return;
        }
        
        // Calculate current palindrome length
        int currentLength = right - left + 1;
        
        // Update global variables if we found a longer palindrome
        if (currentLength > maxLength) {
            maxLength = currentLength;
            startIndex = left;
        }
        
        // Recursively expand outward to check for a larger palindrome
        findPalindromeFromCenter(s, left - 1, right + 1);
    }
    
    /**
     * Main method for testing the longestPalindrome function.
     */
    public static void main(String[] args) {
        // Test case 1
        String s1 = "babad";
        System.out.println("Input: " + s1);
        System.out.println("Output: " + longestPalindrome(s1));  // Expected: "bab" or "aba"
        
        // Test case 2
        String s2 = "cbbd";
        System.out.println("\nInput: " + s2);
        System.out.println("Output: " + longestPalindrome(s2));  // Expected: "bb"
        
        // Test case 3
        String s3 = "a";
        System.out.println("\nInput: " + s3);
        System.out.println("Output: " + longestPalindrome(s3));  // Expected: "a"
        
        // Test case 4
        String s4 = "racecar";
        System.out.println("\nInput: " + s4);
        System.out.println("Output: " + longestPalindrome(s4));  // Expected: "racecar"
    }
}
```

## Time Complexity:
* O(n²) - Where n is the length of the input string. For each of the n characters, we expand outwards potentially up to the entire string length.

## Space Complexity:
* O(n) - The space complexity is determined by the recursion stack depth, which in the worst case can be n/2 (for a string that is a palindrome).

## Algorithm:
* For each character in the string:
  * Consider it as the center of an odd-length palindrome
  * Consider it and its next character as the center of an even-length palindrome
* For each center position, expand outwards recursively as long as characters match
* Update the longest palindrome found whenever we find a longer one
* Return the substring corresponding to the longest palindrome found
***


# Longest Palindromic Subsequence

## Pattern:
Dynamic Programming - String Manipulation

## Description:
Find the length of the longest subsequence in a string that is a palindrome. A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.

### Input:
* A string s

### Output:
* The length of the longest palindromic subsequence in s

### Examples:
* Input: s = "bbbab"  
  Output: 4  
  Explanation: The longest palindromic subsequence is "bbbb" with length 4.

* Input: s = "cbbd"  
  Output: 2  
  Explanation: The longest palindromic subsequence is "bb" with length 2.

* Input: s = "abcdef"  
  Output: 1  
  Explanation: If no longer palindromic subsequence exists, a single character is always a palindrome.

```java
/**
 * Java solution to find the length of the longest palindromic subsequence using recursion.
 */
public class LongestPalindromicSubsequence {
    
    /**
     * Find the length of the longest palindromic subsequence in a string.
     * 
     * @param s Input string
     * @return Length of the longest palindromic subsequence
     */
    public static int longestPalindromeSubseq(String s) {
        // Call the recursive helper function with the full range of the string
        return lpsRecursive(s, 0, s.length() - 1);
    }
    
    /**
     * Recursive function to find the length of the longest palindromic subsequence.
     * 
     * @param s Input string
     * @param start Start index of the substring
     * @param end End index of the substring
     * @return Length of LPS in s[start...end]
     */
    private static int lpsRecursive(String s, int start, int end) {
        // Base case 1: If only one character remains, it's a palindrome of length 1
        if (start == end) {
            return 1;
        }
        
        // Base case 2: If start passes end, we've considered all characters
        if (start > end) {
            return 0;
        }
        
        // If the characters at both ends match
        if (s.charAt(start) == s.charAt(end)) {
            // Include both characters in the palindrome and check the inner substring
            return 2 + lpsRecursive(s, start + 1, end - 1);
        } else {
            // Characters don't match, consider two cases:
            // 1. Exclude the character at start and find LPS in the remaining string
            // 2. Exclude the character at end and find LPS in the remaining string
            // Take the maximum of these two cases
            return Math.max(
                lpsRecursive(s, start + 1, end),
                lpsRecursive(s, start, end - 1)
            );
        }
    }
    
    /**
     * Main method for testing the longestPalindromeSubseq function.
     */
    public static void main(String[] args) {
        // Test case 1
        String s1 = "bbbab";
        System.out.println("Input: " + s1);
        System.out.println("Output: " + longestPalindromeSubseq(s1));  // Expected: 4
        
        // Test case 2
        String s2 = "cbbd";
        System.out.println("\nInput: " + s2);
        System.out.println("Output: " + longestPalindromeSubseq(s2));  // Expected: 2
        
        // Test case 3
        String s3 = "abcdef";
        System.out.println("\nInput: " + s3);
        System.out.println("Output: " + longestPalindromeSubseq(s3));  // Expected: 1
        
        // Test case 4
        String s4 = "racecar";
        System.out.println("\nInput: " + s4);
        System.out.println("Output: " + longestPalindromeSubseq(s4));  // Expected: 7
    }
}
```

## Time Complexity:
* O(2^n) - Where n is the length of the input string. This is because there are two recursive calls in the worst case at each step, leading to exponential complexity.

## Space Complexity:
* O(n) - The space complexity is determined by the maximum depth of the recursion stack, which is at most n.

## Algorithm:
* Base cases:
  * If start equals end (single character), return 1
  * If start is greater than end (empty string), return 0
* If characters at start and end match:
  * Include both characters and recursively find LPS in the inner substring
* If characters don't match:
  * Recursively find LPS by either:
    * Excluding the character at start, or
    * Excluding the character at end
  * Take the maximum of these two results
* Return the final LPS length
***


# Edit Distance (Levenshtein Distance)

## Pattern:
Dynamic Programming - String Manipulation

## Description:
Find the minimum number of operations (insertions, deletions, substitutions) required to convert one string into another.

### Input:
* Two strings, word1 and word2

### Output:
* An integer representing the minimum number of operations required to convert word1 to word2

### Examples:
* Input: word1 = "horse", word2 = "ros"  
  Output: 3  
  Explanation:  
  horse -> rorse (replace 'h' with 'r')  
  rorse -> rose (remove 'r')  
  rose -> ros (remove 'e')

* Input: word1 = "intention", word2 = "execution"  
  Output: 5  
  Explanation:  
  intention -> inention (remove 't')  
  inention -> enention (replace 'i' with 'e')  
  enention -> exention (replace 'n' with 'x')  
  exention -> exection (replace 'n' with 'c')  
  exection -> execution (insert 'u')

```java
/**
 * Java solution to find the minimum edit distance (Levenshtein distance) using recursion.
 */
public class EditDistance {
    
    /**
     * Calculate the minimum edit distance between two strings.
     * 
     * @param word1 First string
     * @param word2 Second string
     * @return Minimum number of operations to convert word1 to word2
     */
    public static int minDistance(String word1, String word2) {
        // Call the recursive helper function with the full length of both strings
        return editDistanceRecursive(word1, word2, word1.length(), word2.length());
    }
    
    /**
     * Recursive function to calculate the minimum edit distance.
     * 
     * @param word1 First string
     * @param word2 Second string
     * @param m Length of word1 being considered
     * @param n Length of word2 being considered
     * @return Minimum edit distance between word1[0...m-1] and word2[0...n-1]
     */
    private static int editDistanceRecursive(String word1, String word2, int m, int n) {
        // Base case 1: If first string is empty, insert all characters of second string
        if (m == 0) {
            return n;
        }
        
        // Base case 2: If second string is empty, remove all characters of first string
        if (n == 0) {
            return m;
        }
        
        // If last characters match, no operation needed for them
        if (word1.charAt(m - 1) == word2.charAt(n - 1)) {
            return editDistanceRecursive(word1, word2, m - 1, n - 1);
        }
        
        // If last characters don't match, consider all three operations:
        // 1. Insert: Insert character from word2 into word1
        // 2. Remove: Remove character from word1
        // 3. Replace: Replace character in word1 with character from word2
        // Take the minimum of these three operations + 1
        return 1 + Math.min(
            // Insert operation
            editDistanceRecursive(word1, word2, m, n - 1),
            Math.min(
                // Remove operation
                editDistanceRecursive(word1, word2, m - 1, n),
                // Replace operation
                editDistanceRecursive(word1, word2, m - 1, n - 1)
            )
        );
    }
    
    /**
     * Main method for testing the minDistance function.
     */
    public static void main(String[] args) {
        // Test case 1
        String word1 = "horse";
        String word2 = "ros";
        System.out.println("Word1: " + word1);
        System.out.println("Word2: " + word2);
        System.out.println("Minimum Edit Distance: " + minDistance(word1, word2));  // Expected: 3
        
        // Test case 2
        word1 = "intention";
        word2 = "execution";
        System.out.println("\nWord1: " + word1);
        System.out.println("Word2: " + word2);
        System.out.println("Minimum Edit Distance: " + minDistance(word1, word2));  // Expected: 5
        
        // Test case 3
        word1 = "saturday";
        word2 = "sunday";
        System.out.println("\nWord1: " + word1);
        System.out.println("Word2: " + word2);
        System.out.println("Minimum Edit Distance: " + minDistance(word1, word2));  // Expected: 3
    }
}
```

## Time Complexity:
* O(3^(m+n)) - Where m and n are the lengths of the two input strings. This is because there are three recursive calls in the worst case at each step, leading to exponential complexity.

## Space Complexity:
* O(m+n) - The space complexity is determined by the maximum depth of the recursion stack, which is at most m+n.

## Algorithm:
* Base cases:
  * If first string is empty, return length of second string (need to insert all characters)
  * If second string is empty, return length of first string (need to delete all characters)
* If the last characters of both strings match:
  * No operation needed for these characters, recursively find edit distance for the remaining strings
* If the last characters don't match:
  * Consider all three operations:
    * Insert: Recursively find edit distance after inserting a character
    * Delete: Recursively find edit distance after deleting a character
    * Replace: Recursively find edit distance after replacing a character
  * Take the minimum of these three cases and add 1 (for the current operation)
* Return the final edit distance
***


# Wildcard Matching

## Pattern:
Dynamic Programming - String Matching with Wildcards

## Description:
Implement wildcard pattern matching where:
- '?' matches any single character
- '*' matches any sequence of characters (including empty sequence)

### Input:
* A string `s` and a pattern `p` containing wildcards

### Output:
* Boolean indicating if pattern matches entire string

### Examples:
* Input: s = "adceb", p = "*a*b"  
  Output: true  
  Explanation: The first '*' matches empty, 'a' matches 'a', '*' matches 'dce', 'b' matches 'b'

* Input: s = "acdcb", p = "a*c?b"  
  Output: false  
  Explanation: '?' cannot match 'd' and 'c' simultaneously

* Input: s = "", p = "*"  
  Output: true  
  Explanation: '*' matches empty sequence

```java
public class WildcardMatching {
    private static Boolean[][] memo;

    public static boolean isMatch(String s, String p) {
        memo = new Boolean[s.length()+1][p.length()+1];
        return recursiveMatch(s, p, 0, 0);
    }

    private static boolean recursiveMatch(String s, String p, int sIndex, int pIndex) {
        // Check memoization
        if (memo[sIndex][pIndex] != null) {
            return memo[sIndex][pIndex];
        }

        // Base case 1: Both strings exhausted
        if (sIndex == s.length() && pIndex == p.length()) {
            return true;
        }

        // Base case 2: Pattern exhausted but string remains
        if (pIndex == p.length()) {
            return false;
        }

        // Case 1: Current pattern is '*'
        if (p.charAt(pIndex) == '*') {
            // Option 1: Match empty sequence
            boolean matchEmpty = recursiveMatch(s, p, sIndex, pIndex+1);
            
            // Option 2: Match one or more characters
            boolean matchChar = (sIndex < s.length()) && 
                               recursiveMatch(s, p, sIndex+1, pIndex);
            
            return memo[sIndex][pIndex] = matchEmpty || matchChar;
        }

        // Case 2: Current pattern is '?' or matching character
        boolean currentMatch = (sIndex < s.length()) && 
                              (p.charAt(pIndex) == '?' || 
                               p.charAt(pIndex) == s.charAt(sIndex));

        // Proceed if current characters match
        if (currentMatch) {
            return memo[sIndex][pIndex] = recursiveMatch(s, p, sIndex+1, pIndex+1);
        }

        // No match found
        return memo[sIndex][pIndex] = false;
    }

    public static void main(String[] args) {
        String[][] testCases = {
            {"adceb", "*a*b"},  // true
            {"acdcb", "a*c?b"}, // false
            {"", "*"},          // true
            {"aa", "a"},        // false
            {"cb", "?a"}        // false
        };

        for (String[] test : testCases) {
            memo = new Boolean[test[0].length()+1][test[1].length()+1];
            System.out.printf("Input: s=\"%s\", p=\"%s\" → %b%n",
                test[0], test[1], isMatch(test[0], test[1]));
        }
    }
}
```

## Time Complexity:
* Without memoization: O(2^(m+n)) [Exponential]
* With memoization: O(m×n) [Quadratic]

## Space Complexity:
* O(m×n) for memoization table

## Algorithm:
1. **Base Cases**:
   - Both strings empty → true
   - Pattern empty but string remains → false

2. **Star ('*') Handling**:
   - Match empty sequence (skip star)
   - OR match current character and keep star

3. **Question Mark/Character Match**:
   - '?' matches any single character
   - Literal characters must match exactly

4. **Memoization**:
   - Stores results of (sIndex, pIndex) pairs
   - Avoids recomputing same subproblems

## Edge Cases Handled:
1. Multiple consecutive stars ("**" equivalent to "*")
2. Star at beginning/end of pattern
3. Empty string with various patterns
4. Partial matches vs complete matches

## Optimization Notes:
1. The solution can be further optimized to O(n) space using DP table compression
2. Early termination possible when remaining pattern is all stars
3. Greedy approach exists for special cases


# Wildcard Matching

## Pattern:
Dynamic Programming - String Matching

## Description:
Determine if a string matches a pattern with wildcard characters. The pattern can include:
* '?' - Matches any single character
* '*' - Matches any sequence of characters (including empty sequence)

### Input:
* A string s
* A pattern p containing wildcard characters

### Output:
* Boolean value - true if s matches p, false otherwise

### Examples:
* Input: s = "aa", p = "a"  
  Output: false  
  Explanation: "a" does not match the entire string "aa".

* Input: s = "aa", p = "*"  
  Output: true  
  Explanation: '*' matches any sequence, including "aa".

* Input: s = "cb", p = "?a"  
  Output: false  
  Explanation: '?' matches 'c', but 'a' does not match 'b'.

* Input: s = "adceb", p = "*a*b"  
  Output: true  
  Explanation: The '*' matches the empty sequence in the beginning, then 'a' matches actual 'a', second '*' matches "dce", and 'b' matches 'b'.

```java
/**
 * Java solution for wildcard pattern matching using recursion.
 */
public class WildcardMatching {
    
    /**
     * Determines if a string matches a wildcard pattern.
     * 
     * @param s Input string
     * @param p Pattern with wildcard characters ('?' and '*')
     * @return true if the string matches the pattern, false otherwise
     */
    public static boolean isMatch(String s, String p) {
        // Call the recursive helper function with the starting indices
        return isMatchRecursive(s, p, 0, 0);
    }
    
    /**
     * Recursive function to determine if the string matches the pattern.
     * 
     * @param s Input string
     * @param p Pattern with wildcard characters
     * @param i Current index in string s
     * @param j Current index in pattern p
     * @return true if s[i...] matches p[j...], false otherwise
     */
    private static boolean isMatchRecursive(String s, String p, int i, int j) {
        // Base case: If we've reached the end of both the string and pattern
        if (i == s.length() && j == p.length()) {
            return true;
        }
        
        // If we've reached the end of the pattern but not the string
        if (j == p.length()) {
            return false;
        }
        
        // If we've reached the end of the string
        if (i == s.length()) {
            // Remaining pattern must be all '*' characters to match
            for (int k = j; k < p.length(); k++) {
                if (p.charAt(k) != '*') {
                    return false;
                }
            }
            return true;
        }
        
        // Current characters match or pattern has '?'
        if (j < p.length() && (i < s.length() && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i)))) {
            return isMatchRecursive(s, p, i + 1, j + 1);
        }
        
        // Pattern has '*'
        if (j < p.length() && p.charAt(j) == '*') {
            // Two options with '*':
            // 1. '*' matches empty sequence (skip '*')
            // 2. '*' matches at least one character (use '*' again for next character)
            return isMatchRecursive(s, p, i, j + 1) || // Skip '*'
                   isMatchRecursive(s, p, i + 1, j);   // Use '*' for current character and keep it
        }
        
        // No match
        return false;
    }
    
    /**
     * Main method for testing the isMatch function.
     */
    public static void main(String[] args) {
        // Test case 1
        String s1 = "aa";
        String p1 = "a";
        System.out.println("String: " + s1);
        System.out.println("Pattern: " + p1);
        System.out.println("Match: " + isMatch(s1, p1));  // Expected: false
        
        // Test case 2
        String s2 = "aa";
        String p2 = "*";
        System.out.println("\nString: " + s2);
        System.out.println("Pattern: " + p2);
        System.out.println("Match: " + isMatch(s2, p2));  // Expected: true
        
        // Test case 3
        String s3 = "cb";
        String p3 = "?a";
        System.out.println("\nString: " + s3);
        System.out.println("Pattern: " + p3);
        System.out.println("Match: " + isMatch(s3, p3));  // Expected: false
        
        // Test case 4
        String s4 = "adceb";
        String p4 = "*a*b";
        System.out.println("\nString: " + s4);
        System.out.println("Pattern: " + p4);
        System.out.println("Match: " + isMatch(s4, p4));  // Expected: true
    }
}
```

## Time Complexity:
* O(2^(m+n)) - Where m is the length of the string and n is the length of the pattern. In the worst case, each '*' in the pattern could represent either empty or non-empty sequences, leading to exponential complexity.

## Space Complexity:
* O(m+n) - The space complexity is determined by the maximum depth of the recursion stack, which is at most m+n.

## Algorithm:
* Base cases:
  * If both string and pattern are exhausted, return true
  * If pattern is exhausted but string isn't, return false
  * If string is exhausted, check if remaining pattern consists only of '*' characters
* If current characters match or pattern has '?':
  * Move to the next character in both string and pattern
* If pattern has '*':
  * Consider two cases:
    * '*' matches empty sequence - skip '*' and continue with the pattern
    * '*' matches the current character - use '*' again for the next character
* If none of the above conditions are met, return false
* Return the final result
***




# Minimum Insertions to Make a String Palindrome

## Pattern:
Dynamic Programming - Palindrome Variation

## Description:
Find the minimum number of insertions required to make a string a palindrome. Insertions can be done at any position in the string.

### Input:
* A single string s

### Output:
* An integer representing the minimum number of insertions needed

### Examples:
* Input: s = "zzazz"  
  Output: 0  
  Explanation: The string is already a palindrome.

* Input: s = "mbadm"  
  Output: 2  
  Explanation: String can be "mbdadbm" or "mdbabdm" with 2 insertions.

* Input: s = "leetcode"  
  Output: 5  
  Explanation: Inserting 5 characters the string becomes "leetcodocteel".

```java
/**
 * Java solution for finding minimum insertions to make a string palindrome using recursion.
 */
public class MinInsertionsForPalindrome {

    /**
     * Main method to find minimum insertions to make string palindrome.
     * 
     * @param s Input string
     * @return Minimum number of insertions needed
     */
    public static int minInsertions(String s) {
        // The problem is equivalent to finding the length of the string minus
        // the length of its longest palindromic subsequence (LPS)
        int n = s.length();
        int lps = longestPalindromicSubsequence(s, 0, n - 1);
        return n - lps;
    }

    /**
     * Recursive function to find length of longest palindromic subsequence.
     * 
     * @param s Input string
     * @param left Left index of current substring
     * @param right Right index of current substring
     * @return Length of LPS for s[left...right]
     */
    private static int longestPalindromicSubsequence(String s, int left, int right) {
        // Base case 1: Single character
        if (left == right) {
            return 1;
        }
        
        // Base case 2: Only two characters
        if (s.charAt(left) == s.charAt(right) && left + 1 == right) {
            return 2;
        }
        
        // Case 1: First and last characters match
        if (s.charAt(left) == s.charAt(right)) {
            return 2 + longestPalindromicSubsequence(s, left + 1, right - 1);
        }
        
        // Case 2: First and last characters don't match
        return Math.max(
            longestPalindromicSubsequence(s, left + 1, right),
            longestPalindromicSubsequence(s, left, right - 1)
        );
    }

    public static void main(String[] args) {
        // Test case 1
        String s1 = "zzazz";
        System.out.println("Input: " + s1);
        System.out.println("Insertions needed: " + minInsertions(s1));  // Expected: 0
        
        // Test case 2
        String s2 = "mbadm";
        System.out.println("\nInput: " + s2);
        System.out.println("Insertions needed: " + minInsertions(s2));  // Expected: 2
        
        // Test case 3
        String s3 = "leetcode";
        System.out.println("\nInput: " + s3);
        System.out.println("Insertions needed: " + minInsertions(s3));  // Expected: 5
    }
}
```

## Time Complexity:
* O(2^n) - Where n is the length of the string. This is because in the worst case we make two recursive calls at each step.

## Space Complexity:
* O(n) - The space used by the recursion stack, which can go up to n levels deep.

## Algorithm:
1. The minimum insertions needed equals the string length minus the length of its longest palindromic subsequence (LPS)
2. To find LPS recursively:
   - Base case 1: Single character is a palindrome of length 1
   - Base case 2: Two same characters form a palindrome of length 2
   - If first and last characters match:
     * Add 2 to result and recurse on inner substring
   - If first and last characters don't match:
     * Take maximum of LPS from excluding first or last character
3. Return string length minus LPS length

***


# Count Palindromic Subsequences

## Pattern:
Dynamic Programming - Palindrome Counting

## Description:
Count the number of palindromic subsequences in a string. A palindromic subsequence is a sequence that reads the same backward as forward, but does not need to be contiguous.

### Input:
* A single string s

### Output:
* An integer representing the count of palindromic subsequences

### Examples:
* Input: s = "aab"  
  Output: 4  
  Explanation: The palindromic subsequences are "a", "a", "b", "aa"

* Input: s = "abcd"  
  Output: 4  
  Explanation: Each single character is a palindromic subsequence

* Input: s = "aaa"  
  Output: 7  
  Explanation: The palindromic subsequences are "a", "a", "a", "aa", "aa", "aa", "aaa"

```java
/**
 * Java solution for counting palindromic subsequences using recursion.
 */
public class CountPalindromicSubsequences {

    /**
     * Main method to count palindromic subsequences.
     * 
     * @param s Input string
     * @return Count of palindromic subsequences
     */
    public static int countPalindromicSubsequences(String s) {
        return countPS(s, 0, s.length() - 1);
    }

    /**
     * Recursive helper function to count palindromic subsequences.
     * 
     * @param s Input string
     * @param i Starting index
     * @param j Ending index
     * @return Count of palindromic subsequences in s[i..j]
     */
    private static int countPS(String s, int i, int j) {
        // Base case 1: Single character
        if (i == j) {
            return 1;
        }

        // Base case 2: Empty string
        if (i > j) {
            return 0;
        }

        // Case 1: First and last characters are same
        if (s.charAt(i) == s.charAt(j)) {
            return 1 + countPS(s, i + 1, j) + countPS(s, i, j - 1);
        }
        // Case 2: First and last characters are different
        else {
            return countPS(s, i + 1, j) + countPS(s, i, j - 1) - countPS(s, i + 1, j - 1);
        }
    }

    public static void main(String[] args) {
        // Test case 1
        String s1 = "aab";
        System.out.println("Input: " + s1);
        System.out.println("Count: " + countPalindromicSubsequences(s1));  // Expected: 4

        // Test case 2
        String s2 = "abcd";
        System.out.println("\nInput: " + s2);
        System.out.println("Count: " + countPalindromicSubsequences(s2));  // Expected: 4

        // Test case 3
        String s3 = "aaa";
        System.out.println("\nInput: " + s3);
        System.out.println("Count: " + countPalindromicSubsequences(s3));  // Expected: 7
    }
}
```

## Time Complexity:
* O(3^n) - In the worst case, each recursive call makes up to 3 additional calls

## Space Complexity:
* O(n) - The recursion depth is at most n levels

## Algorithm:
1. Base cases:
   - Single character counts as 1 palindromic subsequence
   - Empty string counts as 0
2. If first and last characters match:
   - Add 1 (for the new palindrome formed by these two characters)
   - Add count from substring excluding first character
   - Add count from substring excluding last character
3. If first and last characters don't match:
   - Add counts from both substrings excluding first or last character
   - Subtract the overlap (count from substring excluding both first and last characters)
***


# Longest Repeating Subsequence

## Pattern:
Dynamic Programming - String Matching with Constraints

## Description:
Find the length of the longest subsequence that appears at least twice in a string. The two subsequences should not have the same character at the same position in the original string.

### Input:
* A single string s

### Output:
* An integer representing the length of the longest repeating subsequence

### Examples:
* Input: s = "aabebcdd"  
  Output: 3  
  Explanation: The longest repeating subsequence is "abd" which appears twice at positions (0,2,6) and (1,4,7)

* Input: s = "abc"  
  Output: 0  
  Explanation: No repeating subsequence exists

* Input: s = "aabb"  
  Output: 2  
  Explanation: The longest repeating subsequence is "ab" which appears twice

```java
/**
 * Java solution for finding Longest Repeating Subsequence using recursion.
 */
public class LongestRepeatingSubsequence {

    /**
     * Main method to find longest repeating subsequence.
     * 
     * @param s Input string
     * @return Length of longest repeating subsequence
     */
    public static int longestRepeatingSubsequence(String s) {
        return lrs(s, s.length(), s.length());
    }

    /**
     * Recursive helper function to find LRS.
     * 
     * @param s Input string
     * @param m Length of first substring
     * @param n Length of second substring
     * @return Length of LRS
     */
    private static int lrs(String s, int m, int n) {
        // Base case: if either string is empty
        if (m == 0 || n == 0) {
            return 0;
        }

        // If characters match and have different indices
        if (s.charAt(m-1) == s.charAt(n-1) && m != n) {
            return 1 + lrs(s, m-1, n-1);
        }
        // If characters don't match
        else {
            return Math.max(lrs(s, m-1, n), lrs(s, m, n-1));
        }
    }

    public static void main(String[] args) {
        // Test case 1
        String s1 = "aabebcdd";
        System.out.println("Input: " + s1);
        System.out.println("LRS Length: " + longestRepeatingSubsequence(s1));  // Expected: 3

        // Test case 2
        String s2 = "abc";
        System.out.println("\nInput: " + s2);
        System.out.println("LRS Length: " + longestRepeatingSubsequence(s2));  // Expected: 0

        // Test case 3
        String s3 = "aabb";
        System.out.println("\nInput: " + s3);
        System.out.println("LRS Length: " + longestRepeatingSubsequence(s3));  // Expected: 2
    }
}
```

## Time Complexity:
* O(2^n) - In the worst case, each recursive call makes 2 additional calls

## Space Complexity:
* O(n) - The recursion depth is at most n levels

## Algorithm:
1. Compare the string with itself character by character
2. If characters match AND their indices are different:
   - Increment count and recurse for remaining string
3. If characters don't match:
   - Take maximum of two possibilities:
     a) Recurse by excluding current character from first string
     b) Recurse by excluding current character from second string
4. Base case: return 0 when either string is empty

***


# Shortest Common Supersequence

## Pattern:
Dynamic Programming - String Merging

## Description:
Find the length of the shortest string that has both input strings as subsequences. A supersequence maintains the relative order of characters from both strings but may skip some.

### Input:
* Two strings, str1 and str2

### Output:
* An integer representing the length of the shortest common supersequence

### Examples:
* Input: str1 = "abac", str2 = "cab"  
  Output: 5  
  Explanation: The shortest supersequence is "cabac"

* Input: str1 = "geek", str2 = "eke"  
  Output: 5  
  Explanation: The shortest supersequence is "geeke"

* Input: str1 = "abc", str2 = "def"  
  Output: 6  
  Explanation: The shortest supersequence is "abcdef"

```java
/**
 * Java solution for finding Shortest Common Supersequence using recursion.
 */
public class ShortestCommonSupersequence {

    /**
     * Main method to find length of shortest common supersequence.
     * 
     * @param str1 First input string
     * @param str2 Second input string
     * @return Length of shortest common supersequence
     */
    public static int shortestCommonSupersequence(String str1, String str2) {
        return scsRecursive(str1, str2, str1.length(), str2.length());
    }

    /**
     * Recursive helper function to find SCS length.
     * 
     * @param str1 First string
     * @param str2 Second string
     * @param m Length of str1 being considered
     * @param n Length of str2 being considered
     * @return Length of SCS for str1[0..m-1] and str2[0..n-1]
     */
    private static int scsRecursive(String str1, String str2, int m, int n) {
        // Base case: if one string is empty, return length of other
        if (m == 0) return n;
        if (n == 0) return m;

        // If last characters match
        if (str1.charAt(m-1) == str2.charAt(n-1)) {
            return 1 + scsRecursive(str1, str2, m-1, n-1);
        }
        // If last characters don't match
        else {
            return 1 + Math.min(scsRecursive(str1, str2, m-1, n),
                             scsRecursive(str1, str2, m, n-1));
        }
    }

    public static void main(String[] args) {
        // Test case 1
        String str1 = "abac";
        String str2 = "cab";
        System.out.println("Input: \"" + str1 + "\", \"" + str2 + "\"");
        System.out.println("SCS Length: " + shortestCommonSupersequence(str1, str2));  // Expected: 5

        // Test case 2
        str1 = "geek";
        str2 = "eke";
        System.out.println("\nInput: \"" + str1 + "\", \"" + str2 + "\"");
        System.out.println("SCS Length: " + shortestCommonSupersequence(str1, str2));  // Expected: 5

        // Test case 3
        str1 = "abc";
        str2 = "def";
        System.out.println("\nInput: \"" + str1 + "\", \"" + str2 + "\"");
        System.out.println("SCS Length: " + shortestCommonSupersequence(str1, str2));  // Expected: 6
    }
}
```

## Time Complexity:
* O(2^(m+n)) - Where m and n are lengths of input strings
* Worst case occurs when all characters are different

## Space Complexity:
* O(m+n) - Maximum recursion depth

## Algorithm:
1. Base cases:
   - If one string is empty, return length of other
2. If last characters match:
   - Count 1 for the matching character
   - Recurse for remaining strings
3. If last characters don't match:
   - Count 1 for either character
   - Take minimum of two possibilities:
     a) Include last character of str1
     b) Include last character of str2


# Number of Ways to Decode a String (Complete Solution)

## Pattern:
Dynamic Programming - Recursion with Memoization

## Description:
Count the number of ways to decode a digit string where:
- 'A' → "1", 'B' → "2", ..., 'Z' → "26"
- Leading zeros are invalid ("06" can't be decoded as "F")
- Single digits (1-9) and valid double digits (10-26) are allowed

### Input:
* A string s containing only digits (0-9)

### Output:
* Integer representing number of valid decodings

### Examples:
* Input: "12" → Output: 2 ("AB" or "L")
* Input: "226" → Output: 3 ("BZ", "VF", or "BBF")
* Input: "0" → Output: 0 (invalid)
* Input: "2101" → Output: 1 ("BA A" only)
* Input: "100" → Output: 0 (no valid decoding)

```java
import java.util.HashMap;
import java.util.Map;

public class DecodeWays {
    private static Map<Integer, Integer> memo = new HashMap<>();

    public static int numDecodings(String s) {
        return recursiveWithMemo(s, 0);
    }

    private static int recursiveWithMemo(String s, int index) {
        // Check memoization cache
        if (memo.containsKey(index)) {
            return memo.get(index);
        }

        // Base case: valid path
        if (index == s.length()) {
            return 1;
        }

        // Invalid: leading zero
        if (s.charAt(index) == '0') {
            return 0;
        }

        // Single digit case
        int ways = recursiveWithMemo(s, index + 1);

        // Check two-digit possibility
        if (index < s.length() - 1) {
            int twoDigit = Integer.parseInt(s.substring(index, index + 2));
            if (twoDigit >= 10 && twoDigit <= 26) {
                ways += recursiveWithMemo(s, index + 2);
            }
        }

        // Store result in memo before returning
        memo.put(index, ways);
        return ways;
    }

    public static void main(String[] args) {
        String[] testCases = {
            "12",    // 2
            "226",   // 3
            "0",     // 0
            "06",    // 0
            "2101",  // 1
            "100",   // 0
            "11106"  // 2
        };

        for (String test : testCases) {
            memo.clear(); // Reset memo for each test case
            System.out.printf("Input: \"%s\" → Output: %d%n", 
                test, numDecodings(test));
        }
    }
}
```

## Time Complexity:
* Without memoization: O(2^n) [Exponential]
* With memoization: O(n) [Linear]

## Space Complexity:
* O(n) for recursion stack and memoization storage

## Key Optimizations:
1. **Memoization Cache** (Top-down DP):
   - Stores computed results for indexes to avoid redundant calculations
   - Dramatically reduces time complexity from exponential to linear

2. **Early Termination**:
   - Immediately returns 0 for invalid cases (leading zeros)
   - Prevents unnecessary recursive calls

3. **Two-digit Validation**:
   - Only attempts two-digit interpretation for values 10-26
   - Automatically handles edge cases like "27"-"99"

## Edge Cases Handled:
1. Strings with leading zeros ("0", "06")
2. Strings ending with zero ("10" valid, "20" valid, but "30" invalid)
3. Consecutive zeros ("100")
4. Strings with all valid digits ("1234")
5. Maximum length strings (performance test)

## Alternative Solutions:
1. **Bottom-up DP** (O(n) time, O(1) space):
```java
public static int numDecodingsDP(String s) {
    if (s.charAt(0) == '0') return 0;
    
    int n = s.length();
    int prev1 = 1, prev2 = 1;
    
    for (int i = 1; i < n; i++) {
        int current = 0;
        if (s.charAt(i) != '0') {
            current = prev1;
        }
        
        int twoDigit = Integer.parseInt(s.substring(i-1, i+1));
        if (twoDigit >= 10 && twoDigit <= 26) {
            current += prev2;
        }
        
        prev2 = prev1;
        prev1 = current;
    }
    
    return prev1;
}
```

2. **Iterative with Array** (More readable DP):
```java
public static int numDecodingsArray(String s) {
    int[] dp = new int[s.length() + 1];
    dp[0] = 1;
    dp[1] = s.charAt(0) == '0' ? 0 : 1;
    
    for (int i = 2; i <= s.length(); i++) {
        int oneDigit = Integer.parseInt(s.substring(i-1, i));
        int twoDigits = Integer.parseInt(s.substring(i-2, i));
        
        if (oneDigit >= 1) {
            dp[i] += dp[i-1];
        }
        if (twoDigits >= 10 && twoDigits <= 26) {
            dp[i] += dp[i-2];
        }
    }
    
    return dp[s.length()];
}
```

Would you like me to:
1. Add more test cases?
2. Explain the DP solutions in more detail?
3. Provide solutions for any other problems from your original list?

# Regular Expression Matching

## Pattern:
Dynamic Programming - String Matching with Regex

## Description:
Implement regular expression matching where:
- '.' matches any single character
- '*' matches zero or more of the preceding element

### Input:
* A string `s` and a pattern `p` containing regex special characters

### Output:
* Boolean indicating if pattern matches entire string

### Examples:
* Input: s = "aa", p = "a"  
  Output: false  
  Explanation: "a" doesn't match entire string "aa"

* Input: s = "aa", p = "a*"  
  Output: true  
  Explanation: '*' matches zero or more 'a's

* Input: s = "ab", p = ".*"  
  Output: true  
  Explanation: ".*" means "zero or more (*) of any character (.)"

```java
public class RegexMatching {
    private static Boolean[][] memo;

    public static boolean isMatch(String s, String p) {
        memo = new Boolean[s.length()+1][p.length()+1];
        return matchHelper(s, p, 0, 0);
    }

    private static boolean matchHelper(String s, String p, int sIdx, int pIdx) {
        // Check memoization
        if (memo[sIdx][pIdx] != null) {
            return memo[sIdx][pIdx];
        }

        // Base case: both pattern and string exhausted
        if (pIdx == p.length()) {
            return sIdx == s.length();
        }

        // Check current character match
        boolean firstMatch = (sIdx < s.length()) && 
                           (p.charAt(pIdx) == '.' || p.charAt(pIdx) == s.charAt(sIdx));

        // Handle Kleene star case
        if (pIdx + 1 < p.length() && p.charAt(pIdx + 1) == '*') {
            // Case 1: don't use the star (match zero characters)
            boolean zeroMatch = matchHelper(s, p, sIdx, pIdx + 2);
            
            // Case 2: use the star if first character matches
            boolean starMatch = firstMatch && matchHelper(s, p, sIdx + 1, pIdx);
            
            return memo[sIdx][pIdx] = zeroMatch || starMatch;
        }

        // Simple match case
        return memo[sIdx][pIdx] = firstMatch && matchHelper(s, p, sIdx + 1, pIdx + 1);
    }

    public static void main(String[] args) {
        String[][] testCases = {
            {"aa", "a"},    // false
            {"aa", "a*"},   // true
            {"ab", ".*"},   // true
            {"aab", "c*a*b"}, // true
            {"mississippi", "mis*is*p*."} // false
        };

        for (String[] test : testCases) {
            memo = new Boolean[test[0].length()+1][test[1].length()+1];
            System.out.printf("Input: s=\"%s\", p=\"%s\" → %b%n",
                test[0], test[1], isMatch(test[0], test[1]));
        }
    }
}
```

## Time Complexity:
* Without memoization: O(2^(m+n)) [Exponential]
* With memoization: O(m×n) [Quadratic]

## Space Complexity:
* O(m×n) for memoization table

## Key Differences from Wildcard Matching:
1. Star ('*') behavior:
   - In regex: matches zero or more of preceding element
   - In wildcard: matches any sequence

2. Dot ('.') vs question mark ('?'):
   - '.' matches exactly one character
   - '?' matches zero or one character

## Algorithm Breakdown:
1. **Base Case**:
   - Pattern exhausted → check if string also exhausted

2. **Star Handling**:
   - Option 1: Skip star and its preceding character (match zero)
   - Option 2: Consume current character if it matches star's preceding element

3. **Simple Match**:
   - Current characters must match (either exact or via '.')
   - Proceed to next characters in both strings

## Optimization Notes:
1. The DP table can be compressed to O(n) space
2. Patterns can be preprocessed to merge consecutive stars
3. Early termination possible when remaining pattern cannot possibly match remaining string
***


# All Problems

All Problems

DP :
Strings:
Longest Common Subsequence: Find the length of the longest subsequence common to two strings.  
Longest Palindromic Substring: Find the longest substring in a string that is a palindrome.  
Longest Palindromic Subsequence: Find the length of the longest subsequence in a string that is a palindrome.  
Edit Distance (Levenshtein Distance): Find the minimum number of operations (insertions, deletions, substitutions) required to convert one string into another.  
Wildcard Matching: Determine if a string matches a pattern with * (wildcard for any sequence) and ? (wildcard for a single character).  
Regular Expression Matching: Determine if a string matches a pattern with regex-like operators such as * and ..  
Distinct Subsequences: Count the number of distinct subsequences of a string that match another string.  
Interleaving String: Check if a string is formed by interleaving two other strings while maintaining their order.  
Word Break: Determine if a string can be segmented into a space-separated sequence of dictionary words.  
Minimum Insertions to Make a String Palindrome: Find the minimum number of insertions required to make a string a palindrome.  
Count Palindromic Subsequences: Count the number of palindromic subsequences in a string.  
Longest Repeating Subsequence: Find the length of the longest subsequence that appears at least twice in a string.  
Shortest Common Supersequence: Find the shortest string that has two given strings as subsequences.  
Number of Ways to Decode a String: Count the number of ways to decode a string encoded with a mapping of numbers to letters (e.g., A=1, B=2, ...).




Recursion:
Problem1: Fibonacci Sequence - Find the nth Fibonacci number using recursion.
Problem2: Factorial of a Number - Calculate the factorial of a given number using recursion.
Problem3: Tower of Hanoi - Solve the Tower of Hanoi problem for n disks.
Problem4: Subset Sum Problem - Determine if there is a subset of a given set with a sum equal to a target.
Problem5: Permutations and Combinations - Generate all permutations or combinations of a given array or string.
Problem6: N-Queens Problem - Place n queens on an n x n chessboard such that no two queens attack each other.
Problem7: Word Search - Check if a word exists in a 2D grid of characters using backtracking.
Problem8: Jump Game - Determine if you can reach the last index of an array starting from the first index.
Problem9: House Robber - Find the maximum sum of non-adjacent houses to rob.
Problem10: Climbing Stairs - Count the number of ways to climb n stairs, taking 1 or 2 steps at a time.
Problem11: Generate Parentheses - Generate all valid combinations of n pairs of parentheses.
Problem12: Palindrome Partitioning - Partition a string into all possible palindromic substrings.
Problem13: Combination Sum - Find all unique combinations of numbers that sum up to a target.
Problem14: Sudoku Solver - Solve a Sudoku puzzle using recursion and backtracking.
Problem15: Word Break II - Generate all possible sentences by breaking a string into dictionary words.
Problem16: Longest Increasing Subsequence - Find the length of the longest increasing subsequence using recursion.
Problem17: Subset Generation - Generate all subsets (power set) of a given set.
Problem18: Binary Tree Traversals - Perform recursive traversals (inorder, preorder, postorder) of a binary tree.
Problem19: Kth Symbol in Grammar - Find the Kth symbol in the nth row of a grammar sequence.
Problem20: Partition Equal Subset Sum - Determine if a given array can be partitioned into two subsets with equal sums.


Dynamic Programming:
Problem1: Longest Common Subsequence - Find the length of the longest subsequence common to two strings.
Problem2: Longest Palindromic Substring - Find the longest substring in a string that is a palindrome.
Problem3: Longest Palindromic Subsequence - Find the length of the longest subsequence in a string that is a palindrome.
Problem4: Edit Distance (Levenshtein Distance) - Find the minimum number of operations (insertions, deletions, substitutions) required to convert one string into another.
Problem5: Wildcard Matching - Determine if a string matches a pattern with * (wildcard for any sequence) and ? (wildcard for a single character).
Problem6: Regular Expression Matching - Determine if a string matches a pattern with regex-like operators such as * and ..
Problem7: Distinct Subsequences - Count the number of distinct subsequences of a string that match another string.
Problem8: Interleaving String - Check if a string is formed by interleaving two other strings while maintaining their order.
 Problem9: Word Break - Determine if a string can be segmented into a space-separated sequence of dictionary words.
 Problem10: Minimum Insertions to Make a String Palindrome - Find the minimum number of insertions required to make a string a palindrome.
 Problem11: Count Palindromic Subsequences - Count the number of palindromic subsequences in a string.
 Problem12: Longest Repeating Subsequence - Find the length of the longest subsequence that appears at least twice in a string.
 Problem13: Shortest Common Supersequence - Find the shortest string that has two given strings as subsequences.
 Problem14: Number of Ways to Decode a String - Count the number of ways to decode a string encoded with a mapping of numbers to letters (e.g., A=1, B=2, ...).

Graphs:
Problem1: Depth First Search (DFS) - Traverse a graph using depth-first search.
Problem2: Breadth First Search (BFS) - Traverse a graph using breadth-first search.
Problem3: Dijkstra's Algorithm - Find the shortest path from a source to all vertices in a weighted graph.
Problem4: Bellman-Ford Algorithm - Find the shortest path from a source to all vertices, handling negative weights.
Problem5: Floyd-Warshall Algorithm - Find shortest paths between all pairs of vertices in a graph.
Problem6: Prim's Algorithm - Find the Minimum Spanning Tree (MST) of a graph.
Problem7: Kruskal's Algorithm - Find the MST of a graph using edge sorting.
Problem8: Topological Sort - Perform a topological ordering of vertices in a Directed Acyclic Graph (DAG).
Problem9: Detect Cycle in a Graph - Check if a graph contains a cycle (directed or undirected).
Problem10: Connected Components - Find all connected components in an undirected graph.
Problem11: Bipartite Graph Check - Determine if a graph is bipartite.
Problem12: Strongly Connected Components (SCC) - Find SCCs in a directed graph using Kosaraju's or Tarjan's algorithm.
Problem13: Shortest Path in Unweighted Graph - Find the shortest path in an unweighted graph using BFS.
Problem14: Traveling Salesman Problem (TSP) - Find the shortest possible route visiting all vertices exactly once.
Problem15: Graph Coloring - Assign colors to vertices such that no two adjacent vertices share the same color.
Problem16: Minimum Cut - Find the minimum cut in a flow network.
Problem17: Maximum Flow - Calculate the maximum flow in a flow network using Ford-Fulkerson or Edmonds-Karp algorithm.
Problem18: Eulerian Path and Circuit - Determine if a graph has an Eulerian path or circuit.
Problem19: Hamiltonian Path and Circuit - Determine if a graph has a Hamiltonian path or circuit.
Problem20: Alien Dictionary - Determine the order of characters in an alien language using a DAG.


Trees:
Problem1: Binary Tree Traversals - Perform inorder, preorder, and postorder traversals of a binary tree.
Problem2: Maximum Depth of Binary Tree - Find the maximum depth of a binary tree.
Problem3: Lowest Common Ancestor - Find the lowest common ancestor of two nodes in a binary tree.
Problem4: Validate Binary Search Tree - Check if a binary tree is a valid binary search tree.
Problem5: Symmetric Tree - Determine if a binary tree is symmetric.
Problem6: Path Sum - Check if there is a root-to-leaf path with a given sum.
Problem7: Diameter of Binary Tree - Find the diameter (longest path) of a binary tree.
Problem8: Serialize and Deserialize Binary Tree - Convert a binary tree to a string and back.
Problem9: Construct Binary Tree from Traversals - Build a binary tree from inorder and preorder/postorder traversals.
 Problem10: Flatten Binary Tree to Linked List - Flatten a binary tree into a linked list in-place.
 Problem11: Binary Tree Level Order Traversal - Perform level order traversal of a binary tree.
 Problem12: Zigzag Level Order Traversal - Perform zigzag (spiral) level order traversal of a binary tree.
 Problem13: Binary Search Tree Iterator - Implement an iterator for a binary search tree.
 Problem14: Kth Smallest Element in BST - Find the kth smallest element in a binary search tree.
 Problem15: Convert Sorted Array to BST - Convert a sorted array into a height-balanced binary search tree.
 Problem16: Invert Binary Tree - Invert a binary tree (mirror it).
 Problem17: Count Nodes in Complete Binary Tree - Count the number of nodes in a complete binary tree.
 Problem18: Recover Binary Search Tree - Recover a binary search tree where two nodes are swapped.
 Problem19: Maximum Path Sum - Find the maximum path sum in a binary tree.
 Problem20: Binary Tree Right Side View - Get the right side view of a binary tree.

 Back tracking
 Backtracking:
 Problem1: N-Queens Problem - Place n queens on an n x n chessboard such that no two queens attack each other.
 Problem2: Sudoku Solver - Solve a Sudoku puzzle using backtracking.
 Problem3: Word Search - Check if a word exists in a 2D grid of characters using backtracking.
 Problem4: Generate Parentheses - Generate all valid combinations of n pairs of parentheses.
 Problem5: Subset Sum Problem - Determine if there is a subset of a given set with a sum equal to a target.
 Problem6: Combination Sum - Find all unique combinations of numbers that sum up to a target.
 Problem7: Palindrome Partitioning - Partition a string into all possible palindromic substrings.
 Problem8: Permutations - Generate all permutations of a given array or string.
 Problem9: Rat in a Maze - Find a path for a rat to move from the start to the destination in a maze.
 Problem10: Knight's Tour - Find a sequence of moves for a knight to visit every square on a chessboard exactly once.
 Problem11: Hamiltonian Path - Determine if a Hamiltonian path exists in a graph.
 Problem12: M-Coloring Problem - Assign colors to vertices of a graph such that no two adjacent vertices share the same color.
 Problem13: Subset Generation - Generate all subsets (power set) of a given set.
 Problem14: Tug of War - Divide an array into two subsets with minimum difference in their sums.
 Problem15: Crossword Puzzle - Fill a crossword puzzle with given words using backtracking.
 Problem16: Kth
Permutation Sequence - Find the kth permutation of a sequence of numbers.
 Problem17: Binary Watch - Generate all possible times a binary watch can display with a given number of LEDs.
 Problem18: Restore IP Addresses - Generate all possible valid IP addresses from a given string.
 Problem19: Letter Combinations of a Phone Number - Generate all possible letter combinations for a phone number.
 Problem20: Solve Cryptarithmetic Puzzle - Solve puzzles like SEND + MORE = MONEY using backtracking.

