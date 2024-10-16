# Dynamic Programming
[HOME.md](HOME.md)
=================
## Table of contents

<!--ts-->
| [Beginner Level](#beginner-level)                       |    |    |    |    |
|:--------------------------------------------------------|:---|:---|:---|:---|
| [Squares of a Sorted Array](#squares-of-a-sorted-array) |    |    |    |    |
| [Reverse String](#reverse-string)                       |    |    |    |    |



### Beginner Level
Climbing Stairs - Link
House Robber - Link
Min Cost Climbing Stairs - Link
Fibonacci Number - Link
Best Time to Buy and Sell Stock - Link
Maximum Subarray - Link
Coin Change - Link

### Intermediate
 LevelLongest Increasing Subsequence - Link
Unique Paths - Link
Word Break - Link
Jump Game - Link
Partition Equal Subset Sum - Link
Longest Palindromic Substring - Link
Decode Ways - Link

### Advanced Level
Edit Distance - Link
Maximum Product Subarray - Link
Burst Balloons - Link
Palindrome Partitioning II - Link
Wildcard Matching - Link
Regular Expression Matching - Link
Russian Doll Envelopes - Link

### Comprehensive Challenges
Longest Common Subsequence - Link
Interleaving String - Link
Maximal Square - Link
Word Search II - Link
Distinct Subsequences - Link
Dungeon Game - Link
Scramble String - Link

#### Climbing Stairs (Recursive)
##### Pattern: Dynamic Programming (Recursion with Memoization)
[Back to Top](#table-of-contents)

##### Description:
- **Input:** `n = 4` (total steps)
- **Output:** `5` (ways to climb)
- **Explanation:** You can climb 1+1+1+1, 1+1+2, 1+2+1, 2+1+1, or 2+2.

```java
// Problem: Climbing Stairs (Recursive Solution with Memoization)

// This approach uses recursion to solve the problem and memoization to store previously calculated results
// to avoid redundant calculations.

import java.util.HashMap;

public class ClimbingStairs {

    // Memoization map to store already computed results for each step
    private HashMap<Integer, Integer> memo = new HashMap<>();

    // Recursive function to calculate the number of ways to climb 'n' stairs
    public int climbStairs(int n) {
        // Base case: If there are 0 or 1 stairs, there's only 1 way to climb
        if (n == 0 || n == 1) {
            return 1;
        }

        // Check if the result is already calculated and present in the memo map
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        // Recursively calculate the number of ways to climb 'n' stairs
        // Store the result in the memo map for future use
        int result = climbStairs(n - 1) + climbStairs(n - 2);
        memo.put(n, result);

        // Return the result for the current number of stairs
        return result;
    }

    public static void main(String[] args) {
        ClimbingStairs cs = new ClimbingStairs();
        int n = 4; // Example input
        System.out.println("Number of ways to climb " + n + " stairs: " + cs.climbStairs(n)); // Expected output: 5
    }
}
```

##### Time Complexity:
- **O(n):** Each step is only calculated once, and stored in the memo, reducing redundant calculations.

##### Space Complexity:
- **O(n):** The recursion stack and the memoization map both use space proportional to `n`.
***

#### Climbing Stairs (Tabulation)
##### Pattern: Dynamic Programming
[Back to Top](#table-of-contents)

##### Description:
- Input: `n = 4` (number of stairs)
- Output: `5` (number of distinct ways to reach the top)
- Explanation: There are 5 distinct ways to climb 4 stairs: [1,1,1,1], [1,1,2], [1,2,1], [2,1,1], [2,2].

```java
// Java Program to Solve Climbing Stairs problem
public class ClimbingStairs {

    // Method to calculate the number of distinct ways to climb 'n' stairs
    public static int climbStairs(int n) {
        // Base case: if there's 1  stairs, only 1 way to climb
        if (n == 1) return 1;
        // Base case: if there's 2  stairs, only 2 way to climb [1, 1] and [2]
        if (n == 2 ) return 2;  
   
        // Create an array to store results for each step
        int[] dp = new int[n]; // dp[i] means number of ways to reach step i
        
        // Base cases initialization
        dp[0] = 1;  // 1 way to climb 1 stair
        dp[1] = 2;  // 2  way to climb 2 stair

        // Calculate ways for each subsequent step
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];  // Sum of ways to reach the previous two steps
        }

        // Return the result for the top step
        return dp[n-1];
    }

    // Example usage
    public static void main(String[] args) {
        int n = 4;
        System.out.println("Ways to climb " + n + " stairs: " + climbStairs(n));  // Output: 5
    }
}
```

##### Time Complexity:
- **O(n)**: We iterate through the array `dp` from 2 to `n`.

##### Space Complexity:
- **O(n)**: We use an array of size `n+1` to store the results for each step.

***


### KnapSack


#### Subset Sum Problem
##### Pattern: Backtracking
[Back to Top](#table-of-contents)
##### Description:
- **Input:** Set of integers `arr[]`, integer `sum`
- **Output:** `true` if there is a subset of `arr[]` that adds up to `sum`; `false` otherwise
- **Explanation:** We need to check if any subset of the array adds up to a given sum.

```java
public class SubsetSum {

    // Recursive function to check if there exists a subset with the given sum
    public static boolean isSubsetSum(int[] arr, int n, int sum) {
        // Base Case 1: If sum is 0, we've found a subset (empty subset)
        if (sum == 0) {
            return true;
        }
        // Base Case 2: If no elements are left and sum is not 0, no subset is possible
        if (n == 0) {
            return false;
        }
        
        // If last element is greater than sum, skip it
        if (arr[n-1] > sum) {
            return isSubsetSum(arr, n-1, sum);
        }

        // Recursive case:
        // 1. Include the last element in the subset
        // 2. Exclude the last element from the subset
        return isSubsetSum(arr, n-1, sum) || 
               isSubsetSum(arr, n-1, sum - arr[n-1]);
    }

    public static void main(String[] args) {
        int[] arr = {3, 34, 4, 12, 5, 2};
        int sum = 9;
        int n = arr.length;
        
        // Example Input/Output
        if (isSubsetSum(arr, n, sum)) {
            System.out.println("Subset with the given sum exists.");
        } else {
            System.out.println("Subset with the given sum does not exist.");
        }
    }
}
```
##### Time Complexity:
- **O(2^n)** because we are solving two subproblems for every element.

##### Space Complexity:
- **O(n)** due to the recursive call stack.

***
#### Subset Sum Problem with Memoization
##### Pattern: Dynamic Programming
[Back to Top](#table-of-contents)
##### Description:
- **Input:** Set of integers `arr[]`, integer `sum`
- **Output:** `true` if there is a subset of `arr[]` that adds up to `sum`; `false` otherwise
- **Explanation:** We need to check if any subset of the array adds up to a given sum using memoization to optimize the solution.

```java
import java.util.Arrays;

public class SubsetSumMemo {

    // Function to check if there exists a subset with the given sum using memoization
    public static boolean isSubsetSum(int[] arr, int n, int sum, Boolean[][] memo) {
        // Base Case 1: If sum is 0, a valid subset (empty subset) is found
        if (sum == 0) {
            return true;
        }
        // Base Case 2: If no elements are left and sum is not 0, no subset is possible
        if (n == 0) {
            return false;
        }
        
        // Check if the result is already computed in memoization table
        if (memo[n][sum] != null) {
            return memo[n][sum];
        }
        
        // If the last element is greater than sum, skip it
        if (arr[n - 1] > sum) {
            memo[n][sum] = isSubsetSum(arr, n - 1, sum, memo);
            return memo[n][sum];
        }

        // Recursive case:
        // 1. Include the last element in the subset
        // 2. Exclude the last element from the subset
        memo[n][sum] = isSubsetSum(arr, n - 1, sum, memo) || 
                       isSubsetSum(arr, n - 1, sum - arr[n - 1], memo);
        return memo[n][sum];
    }

    public static void main(String[] args) {
        int[] arr = {3, 34, 4, 12, 5, 2};
        int sum = 9;
        int n = arr.length;
        
        // Memoization table (initially filled with nulls)
        Boolean[][] memo = new Boolean[n + 1][sum + 1];
        for (Boolean[] row : memo) {
            Arrays.fill(row, null);
        }

        // Example Input/Output
        if (isSubsetSum(arr, n, sum, memo)) {
            System.out.println("Subset with the given sum exists.");
        } else {
            System.out.println("Subset with the given sum does not exist.");
        }
    }
}
```
##### Time Complexity:
- **O(n * sum)** because we store and reuse results for each subproblem, avoiding recomputation.

##### Space Complexity:
- **O(n * sum)** for the memoization table.

***
#### Equal Sum Partition
##### Pattern: Dynamic Programming (Subset Sum Problem)
[Back to Top](#table-of-contents)
##### Description:
- **Input:** `[1, 5, 11, 5]`
- **Output:** `true`
- **Explanation:** The array can be partitioned into two subsets with equal sums: `[1, 5, 5]` and `[11]`.

```java
public class EqualSumPartition {

    // Recursive function to check if a subset with the given sum exists
    public static boolean canPartition(int[] nums) {
        // Step 1: Calculate total sum of array
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // Step 2: If sum is odd, it's impossible to partition into equal subsets
        if (sum % 2 != 0) {
            return false;
        }

        // Step 3: Recursive function call to check if a subset with sum/2 exists
        return canPartitionRecursive(nums, nums.length - 1, sum / 2);
    }

    // Recursive helper function
    private static boolean canPartitionRecursive(int[] nums, int n, int sum) {
        // Base Case 1: If sum becomes 0, we have found a valid subset
        if (sum == 0) {
            return true;
        }

        // Base Case 2: If no elements are left or sum becomes negative
        if (n < 0 || sum < 0) {
            return false;
        }

        // Choice Diagram: 
        // 1. Include the current element in the subset or
        // 2. Exclude the current element from the subset

        // Recursive Case 1: Include the current element nums[n] in the subset and reduce the sum
        boolean include = canPartitionRecursive(nums, n - 1, sum - nums[n]);

        // Recursive Case 2: Exclude the current element and keep the sum unchanged
        boolean exclude = canPartitionRecursive(nums, n - 1, sum);

        // Return true if any of the cases return true
        return include || exclude;
    }

    public static void main(String[] args) {
        // Test example
        int[] nums = {1, 5, 11, 5};
        System.out.println(canPartition(nums));  // Output: true
    }
}
```

##### Algorithm Explanation:
- Calculate total array sum.
- If the sum is odd, return `false`.
- Use recursion to check if a subset with `sum/2` exists.
- **Base Cases:**
  - If the sum becomes 0, return `true`.
  - If no elements are left or the sum is negative, return `false`.

##### Time Complexity:
- **O(2^n)** where `n` is the size of the array, due to the recursive exploration of subsets.

##### Space Complexity:
- **O(n)** for the recursive call stack.
***
#### Count of Subset Sum Using Recursion
##### Pattern: Dynamic Programming (Subset Sum Problem)
[Back to Top](#table-of-contents)
##### Description:
- Input: `arr = [1, 2, 3], sum = 4`
- Output: `2`
- Explanation: Subsets that add up to 4 are `[1, 3]` and `[2, 2]`. So, the count is 2.

```java
public class SubsetSumCount {
    
    // Recursive method to count the subsets that sum up to the given value
    public static int countSubsetSum(int[] arr, int n, int sum) {
        // Base Case 1: If sum is 0, there's one subset (empty set) that sums to 0
        if (sum == 0) {
            return 1; // Found a valid subset
        }
        // Base Case 2: If no elements left and sum is not 0, no valid subset exists
        if (n == 0) {
            return 0; // No subset found
        }
        
        // If the current element is greater than the sum, skip it
        if (arr[n-1] > sum) {
            return countSubsetSum(arr, n-1, sum); // Exclude the current element
        }
        
        // Count subsets including and excluding the current element
        return countSubsetSum(arr, n-1, sum) // Exclude current element
                + countSubsetSum(arr, n-1, sum - arr[n-1]); // Include current element
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        int sum = 4;
        int n = arr.length;
        System.out.println("Count of subsets with sum " + sum + ": " + countSubsetSum(arr, n, sum));
    }
}
```
##### Time Complexity:
- `O(2^n)` where `n` is the size of the array. Each element has two choices: include or exclude.

##### Space Complexity:
- `O(n)` due to the recursion stack.

##### Explanation:
- We use recursion to find all subsets that sum up to a target value.
- **Base Case 1:** If `sum == 0`, we found a valid subset.
- **Base Case 2:** If `n == 0` and `sum != 0`, no subset can be formed.
- For each element, we explore two options: include it in the subset or exclude it.

***

