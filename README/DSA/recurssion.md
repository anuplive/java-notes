# Heaps
[HOME.md](HOME.md)
=================
## Table of contents

<!--ts-->
|                                                                                         |    |    |    |    |
|:----------------------------------------------------------------------------------------|:---|:---|:---|:---|
| [Height of a Binary Tree](#height-of-a-binary-tree)                                     |    |    |    |    |
| [Reverse a String using Recursion](#reverse-a-string-using-recursion)                   |    |    |    |    |
| [Sum of Digits of a Number using Recursion](#sum-of-digits-of-a-number-using-recursion) |    |    |    |    |

#### Height of a Binary Tree
##### Pattern: Tree Traversal
[Back to Top](#table-of-contents)
##### Description:
- Input: A binary tree
- Output: The height of the tree
- Explanation: Given a binary tree, return the height of the tree. The height of a tree is the number of edges on the longest path from the root to a leaf node.

##### Example:
- Input:
  ```
      1
     / \
    2   3
   / \
  4   5
  ```
- Output: `3`
- Explanation: The longest path from root to leaf is 1 -> 2 -> 4 (3 edges), so the height is 3.

```java
// Java program to calculate the height of a binary tree using recursion.

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
        left = right = null;
    }
}

public class BinaryTree {
    
    // Function to calculate the height of the binary tree
    public int height(TreeNode root) {
        // Base case: if the tree is empty, return 0
        if (root == null) {
            return 0;
        }
        
        // Recursively find the height of the left subtree
        int leftHeight = height(root.left);
        
        // Recursively find the height of the right subtree
        int rightHeight = height(root.right);
        
        // The height of the tree is the maximum of the left and right subtree heights plus 1 for the root
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    public static void main(String[] args) {
        // Creating the binary tree for the example
        BinaryTree tree = new BinaryTree();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        // Calculating the height of the tree
        System.out.println("Height of the tree is: " + tree.height(root));  // Output: 3
    }
}
```

##### Time Complexity:
- The time complexity is **O(n)** where `n` is the number of nodes in the tree because we traverse each node exactly once.

##### Space Complexity:
- The space complexity is **O(h)** where `h` is the height of the tree. This is due to the recursive stack usage.
***

#### Reverse a String using Recursion
##### Pattern: Recursion
[Back to Top](#table-of-contents)
##### Description:
- Input: `"hello"`
- Output: `"olleh"`
- Explanation: The input string is reversed character by character through recursive calls.

```java
public class ReverseString {
    // Function to reverse a string using recursion
    public static String reverse(String str) {
        // Base case: if the string is empty or has one character
        if (str.isEmpty()) {
            return str; // Return the empty string
        }
        // Recursive case: return the last character + reverse of the remaining string
        return str.charAt(str.length() - 1) + reverse(str.substring(0, str.length() - 1));
    }

    public static void main(String[] args) {
        String input = "hello"; // Input string
        String reversed = reverse(input); // Call the recursive function
        System.out.println("Reversed String: " + reversed); // Output the reversed string
    }
}
```
##### Time Complexity:
- O(n), where n is the length of the string, as we go through each character once.

##### Space Complexity:
- O(n), due to the recursive call stack and the creation of substrings.


***

#### Sum of Digits of a Number using Recursion
##### Pattern: Recursion
[Back to Top](#table-of-contents)
##### Description:
- Input: `1234`
- Output: `10`
- Explanation: The sum of digits \(1 + 2 + 3 + 4 = 10\).

```java
public class SumOfDigits {
    // Method to calculate the sum of digits using recursion
    public static int sumOfDigits(int n) {
        // Base case: if the number is 0, return 0
        if (n == 0) {
            return 0;
        }
        // Recursive case: add the last digit to the sum of the remaining digits
        return (n % 10) + sumOfDigits(n / 10);
    }

    public static void main(String[] args) {
        int number = 1234; // Sample input
        int result = sumOfDigits(number); // Calculate the sum of digits
        System.out.println("The sum of digits of " + number + " is: " + result);
    }
}
```
##### Time Complexity:
- \(O(d)\), where \(d\) is the number of digits in the number. Each digit is processed once.

##### Space Complexity:
- \(O(d)\) for the recursive call stack, where \(d\) is the depth of recursion equal to the number of digits.

***

#### Check if a String is a Palindrome
##### Pattern: Recursion
[Back to Top](#table-of-contents)
##### Description:
- Input: `"racecar"`
- Output: `true`
- Explanation: The input string reads the same backward as forward, so it is a palindrome.

- Input: `"hello"`
- Output: `false`
- Explanation: The input string does not read the same backward as forward, so it is not a palindrome.

```java
public class PalindromeChecker {
    
    // Public method to check if a string is a palindrome
    public boolean isPalindrome(String str) {
        // Normalize the string to handle case insensitivity
        str = str.toLowerCase();
        // Call the recursive helper method
        return checkPalindrome(str, 0, str.length() - 1);
    }

    // Recursive helper method to check characters from both ends
    private boolean checkPalindrome(String str, int left, int right) {
        // Base case: if left index crosses right index, it's a palindrome
        if (left >= right) {
            return true;
        }
        // Check if characters at the current indices are the same
        if (str.charAt(left) != str.charAt(right)) {
            return false; // Not a palindrome
        }
        // Update pointers and continue checking
        return checkPalindrome(str, left + 1, right - 1);
    }
}
```
##### Time Complexity:
- O(n), where n is the length of the string, as each character is checked once.

##### Space Complexity:
- O(n), due to the recursion stack in the worst case when all characters are the same.
***


***



#### Print All Subsequences of a String using Recursion
##### Pattern: Backtracking
[Back to Top](#table-of-contents)
##### Description:
- Input: `abc`
- Output: `a`, `b`, `c`, `ab`, `ac`, `bc`, `abc`, ``
- Explanation: The function generates all possible subsequences by including or excluding each character of the string.

```java
import java.util.ArrayList;
import java.util.List;

public class Subsequences {

    // Function to generate all subsequences
    public static List<String> getAllSubsequences(String str) {
        List<String> result = new ArrayList<>();
        generateSubsequences(str, "", 0, result);
        return result;
    }

    // Helper function to use recursion
    private static void generateSubsequences(String str, String current, int index, List<String> result) {
        // Base case: if index reaches the end of the string
        if (index == str.length()) {
            result.add(current); // Add the current subsequence to the result
            return;
        }
        
        // Include the character at the current index
        generateSubsequences(str, current + str.charAt(index), index + 1, result);
        
        // Exclude the character at the current index
        generateSubsequences(str, current, index + 1, result);
    }

    public static void main(String[] args) {
        String input = "abc";
        List<String> subsequences = getAllSubsequences(input);
        
        // Print all subsequences
        System.out.println("All Subsequences: " + subsequences);
    }
}
```
##### Time Complexity:
- The time complexity is \(O(2^n)\) since each character can either be included or excluded from the subsequence.

##### Space Complexity:
- The space complexity is \(O(n)\) due to the recursive call stack, where \(n\) is the length of the input string.
***

#### Count the Number of Subsets with a Given Sum
##### Pattern: Backtracking
[Back to Top](#table-of-contents)
##### Description:
- Input: `arr = {1, 2, 3, 3}, targetSum = 6`
- Output: `4`
- Explanation: The subsets that add up to 6 are `{3, 3}`, `{1, 2, 3}`, `{2, 3}`, and `{1, 2, 3}` (considering the second 3).

```java
import java.util.Arrays;

public class SubsetSumCounter {
    
    // Function to count subsets with a given sum using recursion
    public static int countSubsets(int[] arr, int targetSum) {
        return countSubsetsRecursive(arr, arr.length, targetSum);
    }

    // Recursive helper function
    private static int countSubsetsRecursive(int[] arr, int n, int targetSum) {
        // Base case: If target sum is 0, there is one subset (the empty subset)
        if (targetSum == 0) return 1;
        // Base case: If no elements left or targetSum becomes negative
        if (n == 0 || targetSum < 0) return 0;

        // Include the last element and recurse
        int include = countSubsetsRecursive(arr, n - 1, targetSum - arr[n - 1]);
        // Exclude the last element and recurse
        int exclude = countSubsetsRecursive(arr, n - 1, targetSum);

        // Return the total count of subsets
        return include + exclude;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3};
        int targetSum = 6;
        int count = countSubsets(arr, targetSum);
        System.out.println("Count of subsets with sum " + targetSum + ": " + count);
    }
}
```
##### Time Complexity:
- The time complexity is \(O(2^n)\) due to the nature of recursion, where each element can either be included or excluded.

##### Space Complexity:
- The space complexity is \(O(n)\) due to the recursion stack space used in the worst case.
***


Sure! Here’s the updated code with a comment added before the `pHelper` method call.

***

#### Permutation with Spaces
##### Pattern: Backtracking
[Back to Top](#table-of-contents)
##### Description:
- Input: `"abc"`
- Output: `["abc", "a bc", "ab c", "a b c"]`
- Explanation: The function generates all possible permutations of the input string by inserting spaces between the characters.

```java
import java.util.ArrayList;

public class PermutationWithSpaces {
    
    // Function to generate permutations with spaces
    public ArrayList<String> permutation(String s) {
        ArrayList<String> result = new ArrayList<>();
        
        // Start the recursive helper function with the first character
        pHelper(s, 1, s.substring(0, 1), result);
        return result; 
    }
    
    // Helper function to perform backtracking
    void pHelper(String s, int index, String current, ArrayList<String> result) {
        // If we've reached the end of the string, add the current permutation to results
        if (index == s.length()) {
            result.add(current);
            return; 
        }

        // Recursive call with a space before the next character
        pHelper(s, index + 1, current + " " + s.charAt(index), result);
        
        // Recursive call without a space before the next character
        pHelper(s, index + 1, current + s.charAt(index), result);
    }

    public static void main(String[] args) {
        PermutationWithSpaces solution = new PermutationWithSpaces();
        String input = "abc";
        ArrayList<String> permutations = solution.permutation(input);
        System.out.println(permutations);
    }
}
```
##### Time Complexity:
- The time complexity is \(O(2^n)\) because for each character, we can either include a space or not.

##### Space Complexity:
- The space complexity is \(O(n)\) due to the recursion stack used for the backtracking approach.
***

***

#### Permutations of String with Case Change
##### Pattern: Backtracking
[Back to Top](#table-of-contents)
##### Description:
- Input: `abc`
- Output: `["abc", "abC", "aBc", "aBC", "Abc", "AbC", "ABc", "ABC"]`
- Explanation: The input string can have its letters in lower or upper case, generating all possible combinations.

```java
import java.util.ArrayList;
import java.util.List;

public class CaseChangePermutations {
    public static void main(String[] args) {
        String input = "abc"; // Input string
        List<String> results = new ArrayList<>(); // List to store the results
        generatePermutations(input, 0, "", results); // Start generating permutations
        System.out.println(results); // Print the final list of permutations
    }

    // Recursive function to generate permutations
    private static void generatePermutations(String input, int index, String current, List<String> results) {
        // Base case: if the current index reaches the length of input, add the current permutation to results
        if (index == input.length()) {
            results.add(current);
            return;
        }

        // Recursive case: process the current character in lower case
        generatePermutations(input, index + 1, current + Character.toLowerCase(input.charAt(index)), results);

        // Recursive case: process the current character in upper case
        generatePermutations(input, index + 1, current + Character.toUpperCase(input.charAt(index)), results);
    }
}
```
##### Time Complexity:
- The time complexity is \(O(2^n)\) because each character can be either lowercase or uppercase, leading to \(2^n\) possible combinations.

##### Space Complexity:
- The space complexity is \(O(n)\) for the recursion stack, where \(n\) is the length of the input string.
***



#### Letter Case Permutation
##### Pattern: Backtracking
[Back to Top](#table-of-contents)
##### Description:
- Input: `"a1b2"`
- Output: `["a1b2", "a1B2", "A1b2", "A1B2"]`
- Explanation: The output contains all possible permutations of the string where each letter can be either lowercase or uppercase, while digits remain unchanged.

```java
import java.util.ArrayList;
import java.util.List;

public class LetterCasePermutation {
    public List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>();
        backtrack(result, s, 0, new StringBuilder());
        return result;
    }

    private void backtrack(List<String> result, String s, int index, StringBuilder current) {
        // Base case: if we've processed all characters, add the current combination to the result
        if (index == s.length()) {
            result.add(current.toString());
            return;
        }
        
        // Get the current character
        char ch = s.charAt(index);
        
        // If it's a digit, keep it as is and move to the next character
        if (Character.isDigit(ch)) {
            current.append(ch); // Append digit
            backtrack(result, s, index + 1, current); // Move to the next index
            current.deleteCharAt(current.length() - 1); // Backtrack
        } else {
            // If it's a letter, we have two choices: lowercase and uppercase
            // Append lowercase version and recurse
            current.append(Character.toLowerCase(ch));
            backtrack(result, s, index + 1, current);
            current.deleteCharAt(current.length() - 1); // Backtrack
            
            // Append uppercase version and recurse
            current.append(Character.toUpperCase(ch));
            backtrack(result, s, index + 1, current);
            current.deleteCharAt(current.length() - 1); // Backtrack
        }
    }

    public static void main(String[] args) {
        LetterCasePermutation lcp = new LetterCasePermutation();
        System.out.println(lcp.letterCasePermutation("a1b2")); // Example usage
    }
}
```
##### Time Complexity:
- The time complexity is \(O(2^n)\), where \(n\) is the length of the input string, as each letter can be in two states (lowercase and uppercase).

##### Space Complexity:
- The space complexity is \(O(n)\) for the recursive call stack and the result list.
***

#### Generate All Balanced Parentheses
##### Pattern: Backtracking
[Back to Top](#table-of-contents)

##### Description:
- **Input:** `n = 3` (number of pairs of parentheses)
- **Output:** `["((()))", "(()())", "(())()", "()(())", "()()()"]`
- **Explanation:** The output contains all possible valid combinations of balanced parentheses for `n` pairs.

```java
// Java program to generate all combinations of balanced parentheses using recursion
import java.util.ArrayList;
import java.util.List;

public class BalancedParentheses {

    // Function to generate all valid combinations
    public static List<String> generateParentheses(int n) {
        List<String> result = new ArrayList<>();
        generateCombinations(result, "", 0, 0, n); // Start the recursive backtracking
        return result;
    }

    // Recursive function to generate parentheses
    private static void generateCombinations(List<String> result, String current, int open, int close, int max) {
        // Base condition: If the current string has max pairs of parentheses
        if (current.length() == max * 2) {
            result.add(current); // Add valid combination to result
            return;
        }

        // If we can add an open parenthesis (i.e., open count is less than max)
        if (open < max) {
            generateCombinations(result, current + "(", open + 1, close, max); // Add '(' and increase open count
        }

        // If we can add a close parenthesis (i.e., close count is less than open)
        if (close < open) {
            generateCombinations(result, current + ")", open, close + 1, max); // Add ')' and increase close count
        }
    }

    public static void main(String[] args) {
        int n = 3; // Example input
        System.out.println(generateParentheses(n)); // Expected output: ["((()))", "(()())", "(())()", "()(())", "()()()"]
    }
}
```

##### Time Complexity:
- **O(2^n):** We make two recursive calls (one for open and one for close), generating `2^n` combinations for `n` pairs.

##### Space Complexity:
- **O(n):** The space used by the recursion stack is proportional to `n`, the depth of recursion.

##### Algorithm Explanation:
- Start with an empty string and backtrack.
- Add `(` if open brackets are available.
- Add `)` if it can form a valid pair with an open one.
- Stop when a valid combination is formed with `n` pairs.
***


#### Print N-bit binary numbers having more 1's than 0's for any given prefix
##### Pattern: Backtracking
[Back to Top](#table-of-contents)
##### Description:
- Input: N = 3
- Output: "111", "110", "101"
- Explanation: All 3-bit binary numbers where at every prefix, the number of 1's is greater than or equal to the number of 0's.

```java
public class BinaryNumberGenerator {
    // Main function to generate N-bit binary numbers
    public static void generateBinaryNumbers(int n) {
        generateHelper(n, 0, 0, "");
    }

    // Recursive helper function
    // countOnes: number of 1's in the current prefix
    // countZeros: number of 0's in the current prefix
    // prefix: the current binary number
    private static void generateHelper(int n, int countOnes, int countZeros, String prefix) {
        // Base case: If the length of the prefix is equal to n, print it
        if (prefix.length() == n) {
            System.out.println(prefix);
            return;
        }

        // Recursive case 1: Add '1' to the prefix
        // Always allowed to add '1', so we call the helper recursively
        generateHelper(n, countOnes + 1, countZeros, prefix + "1");

        // Recursive case 2: Add '0' to the prefix only if countOnes > countZeros
        // To maintain the condition that there are more 1's than 0's at every prefix
        if (countOnes > countZeros) {
            generateHelper(n, countOnes, countZeros + 1, prefix + "0");
        }
    }

    // Main function to test the solution
    public static void main(String[] args) {
        int n = 3;
        generateBinaryNumbers(n);
    }
}
```
##### Time Complexity:
- O(2^N), since for each bit, there are 2 choices, but due to pruning when adding 0's, the complexity is lower than generating all 2^N combinations.

##### Space Complexity:
- O(N), as the recursion depth is N and each recursive call adds one character to the prefix.

##### Algorithm:
- Start with an empty string as the prefix.
- Recursively add '1' and '0', ensuring that the number of 1's is always greater than or equal to the number of 0's in any prefix.
- Terminate when the length of the prefix is N.
***

#### Josephus Problem
##### Pattern: Recursion / Dynamic Programming
[Back to Top](#table-of-contents)
##### Description:
- Input: `n = 7`, `k = 3`
- Output: `4`
- Explanation: In a group of 7 people, every 3rd person is eliminated until only one remains. The last person standing is at position 4.

```java
public class Josephus {
    // Function to find the position of the last person standing
    public static int josephus(int n, int k) {
        // Base case: when there's only one person, return 0 (the position of the last person)
        if (n == 1) {
            return 0;
        } else {
            // Recursive case: calculate the position of the last person
            // Use (josephus(n - 1, k) + k) % n to find the new position
            return (josephus(n - 1, k) + k) % n;
        }
    }

    public static void main(String[] args) {
        int n = 7; // Number of people
        int k = 3; // Step count
        int lastPosition = josephus(n, k) + 1; // Adjusting for 1-based index
        System.out.println("The position of the last person standing is: " + lastPosition);
    }
}
```
##### Time Complexity:
- O(n): The recursion occurs for `n` levels, where each call does a constant amount of work.

##### Space Complexity:
- O(n): The recursion stack can go as deep as `n`, leading to linear space complexity.
***
