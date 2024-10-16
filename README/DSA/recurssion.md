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

