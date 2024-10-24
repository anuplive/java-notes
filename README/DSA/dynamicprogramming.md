# Dynamic Programming
[HOME.md](HOME.md)
=================
## Table of contents

<!--ts-->
| [Beginner Level]                                                                                              |    |    |    |    |
|:--------------------------------------------------------------------------------------------------------------|:---|:---|:---|:---|
| [Knapsack Problem (Recursive)](#knapsack-problem-recursive)                                                   |    |    |    |    |
| [Knapsack Problem (Recursive with Memoization)](#knapsack-problem-recursive-with-memoization)                 |    |    |    |    |
| [Knapsack Problem (Top-Down Approach)](#knapsack-problem-top-down-approach)                                   |    |    |    |    |
|                                                                                                               |    |    |    |    |
| [Subset Sum Problem](#subset-sum-problem)                                                                     |    |    |    |    |
| [Subset Sum Problem Memoization](#subset-sum-problem-memoization)                                             |    |    |    |    |
| [Subset Sum Problem Top-Down](#subset-sum-problem-top-down)                                                   |    |    |    |    |
|                                                                                                               |    |    |    |    |
| [Equal Sum Partition Problem](#equal-sum-partition-problem)                                                   |    |    |    |    |
| [Count of Subset with a Given Sum Problem](#count-of-subset-with-a-given-sum-problem)                         |    |    |    |    |
| [Minimum Subset Sum Difference Problem](#minimum-subset-sum-difference-problem)                               |    |    |    |    |
| [Count the Number of Subsets with a Given Difference](#count-the-number-of-subsets-with-a-given-difference)   |    |    |    |    |
|                                                                                                               |    |    |    |    |
| [Additional Problems Hello Interview](#additional-problems-hello-interview)                                   |    |    |    |    |
| [Counting Bits](#counting-bits)                                                                               |    |    |    |    |
| [Decode Ways](#decode-ways)                                                                                   |    |    |    |    |
| [Unique Paths](#unique-paths-recursive-only)                                                                  |    |    |    |    |
| [Longest Increasing Subsequence](#longest-increasing-subsequence-lis)                                         |    |    |    |    |
|                                                                                                               |    |    |    |    |
| [Beginner Level](#beginner-level)                                                                             |    |    |    |    |
| [Climb Stairs (Recursive Only)](#climb-stairs-recursive-only)                                                 |    |    |    |    |
| [House Robber](#house-robber)                                                                                 |    |    |    |    |
| [Min Cost Climbing Stairs](#min-cost-climbing-stairs)                                                         |    |    |    |    |
| [Fibonacci Number](#fibonacci-number)                                                                         |    |    |    |    |
| [Best Time to Buy and Sell Stock](#best-time-to-buy-and-sell-stock)                                           |    |    |    |    |
| [Maximum Subarray](#maximum-subarray)                                                                         |    |    |    |    |
| [Coin Change Minimum Number of Coins](#coin-change-minimum-number-of-coins)                                   |    |    |    |    |
| [Coin Change II - Total Number of Ways to Get a Value](#coin-change-ii---total-number-of-ways-to-get-a-value) |    |    |    |    |
|                                                                                                               |    |    |    |    |
|                                                                                                               |    |    |    |    |
|                                                                                                               |    |    |    |    |
|                                                                                                               |    |    |    |    |


### Problem Equal Sum partition problem  recursive , state each condition explicitly


Memozation , keep the conditions as expicit as possible , add as many comments


non recursive Top down Approach keep the initialization and actual logic in separate sections , be as explicit as possible
***


### KnapSack

#### Knapsack Problem (Recursive)
##### Pattern: Dynamic Programming
[Back to Top](#table-of-contents)
##### Description:
- Input: Weights = [1, 2, 3], Values = [10, 15, 40], Capacity = 6
- Output: 55
- Explanation: The maximum value that can be obtained with a knapsack capacity of 6 is 55 by including items with weights 2 and 3.

```java
public class Knapsack {

    // Recursive function to solve the knapsack problem
    public static int knapsack(int capacity, int[] weights, int[] values, int n) {
        // Base case: no items left or no capacity
        if (n == 0 || capacity == 0) {
            return 0;
        }

        // If weight of the nth item is more than the capacity, skip this item
        if (weights[n - 1] > capacity) {
            return knapsack(capacity, weights, values, n - 1);
        } else {
            // Return the maximum of two cases:
            // 1. nth item included
            // 2. nth item not included
            return Math.max(values[n - 1] + knapsack(capacity - weights[n - 1], weights, values, n - 1),
                            knapsack(capacity, weights, values, n - 1));
        }
    }

    public static void main(String[] args) {
        int[] weights = {1, 2, 3};
        int[] values = {10, 15, 40};
        int capacity = 6;
        int n = values.length;

        int maxValue = knapsack(capacity, weights, values, n);
        System.out.println("Maximum value in knapsack: " + maxValue);
    }
}
```
##### Time Complexity:
- O(2^n) due to the branching factor of 2 for each item.

##### Space Complexity:
- O(n) for the recursive call stack.
***



#### Knapsack Problem (Recursive with Memoization)
##### Pattern: Dynamic Programming
[Back to Top](#table-of-contents)
##### Description:
- Input: Weights = [1, 2, 3], Values = [10, 15, 40], Capacity = 6
- Output: 55
- Explanation: The maximum value that can be obtained with a knapsack capacity of 6 is 55 by including items with weights 2 and 3.

```java
import java.util.Arrays;

public class Knapsack {

    // Recursive function to solve the knapsack problem with memoization
    public static int knapsack(int capacity, int[] weights, int[] values, int n, Integer[][] memo) {
        // Base case: no items left or no capacity
        if (n == 0 || capacity == 0) {
            return 0; // Return 0 as no items can be taken
        }

        // Check if the result is already computed and stored in memo
        if (memo[n][capacity] != null) {
            return memo[n][capacity]; // Return the cached result
        }

        // If weight of the nth item is more than the capacity, skip this item
        if (weights[n - 1] > capacity) {
            // Store the result in memo and recurse without including the nth item
            memo[n][capacity] = knapsack(capacity, weights, values, n - 1, memo);
            return memo[n][capacity];
        } else {
            // Calculate the maximum of two cases:
            // 1. nth item included
            // 2. nth item not included
            int included = values[n - 1] + knapsack(capacity - weights[n - 1], weights, values, n - 1, memo);
            int excluded = knapsack(capacity, weights, values, n - 1, memo);
            memo[n][capacity] = Math.max(included, excluded); // Store the result in memo
            return memo[n][capacity];
        }
    }

    public static void main(String[] args) {
        int[] weights = {1, 2, 3};
        int[] values = {10, 15, 40};
        int capacity = 6;
        int n = values.length;

        // Create a memoization table initialized to null
        Integer[][] memo = new Integer[n + 1][capacity + 1];
        int maxValue = knapsack(capacity, weights, values, n, memo);
        System.out.println("Maximum value in knapsack: " + maxValue);
    }
}
```
##### Time Complexity:
- O(n * capacity) due to filling the memoization table.

##### Space Complexity:
- O(n * capacity) for the memoization table, plus O(n) for the recursive call stack.
***

#### Knapsack Problem (Top-Down Approach)
##### Pattern: Dynamic Programming
[Back to Top](#table-of-contents)
##### Description:
- Input: Weights = [1, 2, 3], Values = [10, 15, 40], Capacity = 6
- Output: 55
- Explanation: The maximum value that can be obtained with a knapsack capacity of 6 is 55 by including items with weights 2 and 3.

```java
public class Knapsack {

    // Function to solve the knapsack problem using a top-down approach
    public static int knapsack(int capacity, int[] weights, int[] values) {
        int n = values.length;

        // Step 1: Initialize the DP table
        int[][] dp = new int[n + 1][capacity + 1];

        // Step 2: Fill the DP table
        for (int i = 1; i <= n; i++) { // Iterate over each item
            for (int w = 1; w <= capacity; w++) { // Iterate over each capacity
                // Condition 1: If the item's weight is more than the current capacity
                if (weights[i - 1] > w) {
                    dp[i][w] = dp[i - 1][w]; // Item not included
                } else {
                    // Condition 2: If including the item gives a better value
                    dp[i][w] = Math.max(dp[i - 1][w], 
                                        values[i - 1] + dp[i - 1][w - weights[i - 1]]);
                }
            }
        }
        return dp[n][capacity]; // Maximum value in the knapsack
    }

    public static void main(String[] args) {
        int[] weights = {1, 2, 3};
        int[] values = {10, 15, 40};
        int capacity = 6;

        int maxValue = knapsack(capacity, weights, values);
        System.out.println("Maximum value in knapsack: " + maxValue);
    }
}
```
##### Time Complexity:
- O(n * capacity), where n is the number of items and capacity is the maximum weight.

##### Space Complexity:
- O(n * capacity) for the DP table.
***





#### Subset Sum Problem
##### Pattern: Backtracking
[Back to Top](#table-of-contents)

##### Description:
- Input: `arr = [3, 34, 4, 12, 5, 2]`, `sum = 9`
- Output: `true`
- Explanation: A subset with a sum of 9 exists: `{4, 5}`.

```java
import java.util.Arrays;

public class SubsetSum {
    // Main function to find if there's a subset with the given sum
    public static boolean isSubsetSum(int[] arr, int n, int sum) {
        // Base case: if sum is 0, there is a subset (empty set)
        if (sum == 0) return true;
        // Base case: if no elements left and sum is not achieved
        if (n == 0) return false;

        // If last element is greater than the sum, ignore it
        if (arr[n - 1] > sum) {
            return isSubsetSum(arr, n - 1, sum);
        }

        // Check two cases: including or excluding the last element
        return isSubsetSum(arr, n - 1, sum) || 
               isSubsetSum(arr, n - 1, sum - arr[n - 1]);
    }

    public static void main(String[] args) {
        int[] arr = {3, 34, 4, 12, 5, 2};
        int sum = 9;
        int n = arr.length;
        if (isSubsetSum(arr, n, sum)) {
            System.out.println("Found a subset with the given sum");
        } else {
            System.out.println("No subset with the given sum");
        }
    }
}
```

##### Time Complexity:
- The time complexity is \(O(2^n)\) due to the recursive nature of the problem, where each element can either be included or excluded.

##### Space Complexity:
- The space complexity is \(O(n)\) for the recursion stack in the worst case.

##### Explanation of the Algorithm:
- Use recursion to explore subsets.
- Check if:
  - The current `sum` is `0` (subset found).
  - No elements left (`n == 0`) with a non-zero `sum` (subset not found).
- If the last element is greater than the `sum`, ignore it.
- Recur for:
  - Excluding the last element.
  - Including the last element (reduce `sum` by that element's value).
***

#### Subset Sum Problem Memoization
##### Pattern: Dynamic Programming (Memoization)
[Back to Top](#table-of-contents)

##### Description:
- Input: `arr = [3, 34, 4, 12, 5, 2]`, `sum = 9`
- Output: `true`
- Explanation: A subset with a sum of 9 exists: `{4, 5}`.

```java
import java.util.Arrays;

public class SubsetSumMemoization {
    // Create a memoization table to store results of subproblems
    static Boolean[][] memo;

    // Main function to find if there's a subset with the given sum
    public static boolean isSubsetSum(int[] arr, int n, int sum) {
        // Initialize memoization table with null values
        memo = new Boolean[n + 1][sum + 1];
        
        // Call the recursive helper function
        return isSubsetSumUtil(arr, n, sum);
    }

    // Recursive utility function with memoization
    private static boolean isSubsetSumUtil(int[] arr, int n, int sum) {
        // Base case: if sum is 0, there is a subset (empty set)
        if (sum == 0) {
            return true; // Subset found
        }
        
        // Base case: if no elements left and sum is not achieved
        if (n == 0) {
            return false; // No subset found
        }

        // Check if the value is already computed
        if (memo[n][sum] != null) {
            return memo[n][sum]; // Return cached result
        }

        // If last element is greater than the sum, ignore it
        if (arr[n - 1] > sum) {
            // Store the result in the memoization table
            memo[n][sum] = isSubsetSumUtil(arr, n - 1, sum);
            return memo[n][sum]; // Return the result
        }

        // Recursively check two cases:
        // 1. Exclude the last element
        // 2. Include the last element (reduce sum by the last element's value)
        boolean result = isSubsetSumUtil(arr, n - 1, sum) || 
                         isSubsetSumUtil(arr, n - 1, sum - arr[n - 1]);

        // Store the computed result in the memoization table
        memo[n][sum] = result;

        return result; // Return the final result
    }

    public static void main(String[] args) {
        int[] arr = {3, 34, 4, 12, 5, 2}; // Input array
        int sum = 9; // Target sum
        int n = arr.length; // Length of the input array

        // Check if a subset with the given sum exists
        if (isSubsetSum(arr, n, sum)) {
            System.out.println("Found a subset with the given sum");
        } else {
            System.out.println("No subset with the given sum");
        }
    }
}
```

##### Time Complexity:
- The time complexity is \(O(n \times \text{sum})\) because we fill a 2D memoization table of size \(n \times \text{sum}\).

##### Space Complexity:
- The space complexity is \(O(n \times \text{sum})\) for the memoization table.

##### Explanation of the Algorithm:
- Use a memoization table to store results of subproblems.
- Check conditions:
  - If `sum` is `0`, return `true` (empty subset).
  - If `n` is `0` and `sum` is non-zero, return `false` (no subset).
  - If the memoization table already has a computed result, return it.
- If the last element is greater than `sum`, ignore it and check the rest.
- Recur for:
  - Excluding the last element.
  - Including the last element (reduce `sum` by the last element's value).
- Store results in the memoization table to avoid redundant calculations.
***




#### Subset Sum Problem Top-Down
##### Pattern: Dynamic Programming (Top-Down)
[Back to Top](#table-of-contents)

##### Description:
- Input: `arr = [3, 34, 4, 12, 5, 2]`, `sum = 9`
- Output: `true`
- Explanation: A subset with a sum of 9 exists: `{4, 5}`.

```java
import java.util.Arrays;

public class SubsetSumTopDown {
    // Function to find if there's a subset with the given sum
    public static boolean isSubsetSum(int[] arr, int sum) {
        int n = arr.length; // Get the number of elements in the array

        // Step 1: Initialize a 2D DP table
        boolean[][] dp = new boolean[n + 1][sum + 1];

        // Step 2: Set base cases
        // If sum is 0, there is always a subset (empty subset)
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true; // Initialize first column as true
        }

        // Step 3: Fill the DP table
        for (int i = 1; i <= n; i++) { // Iterate through each element
            for (int j = 1; j <= sum; j++) { // Iterate through each possible sum
                // If the current element is greater than the current sum
                if (arr[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j]; // Exclude the current element
                } else {
                    // Include the current element or exclude it
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i - 1]];
                }
            }
        }

        // The value in the last cell represents if a subset with the given sum exists
        return dp[n][sum];
    }

    public static void main(String[] args) {
        int[] arr = {3, 34, 4, 12, 5, 2}; // Input array
        int sum = 9; // Target sum

        // Check if a subset with the given sum exists
        if (isSubsetSum(arr, sum)) {
            System.out.println("Found a subset with the given sum");
        } else {
            System.out.println("No subset with the given sum");
        }
    }
}
```

##### Time Complexity:
- The time complexity is \(O(n \times \text{sum})\) due to the nested loops filling the DP table.

##### Space Complexity:
- The space complexity is \(O(n \times \text{sum})\) for the DP table.

##### Explanation of the Algorithm:
- Use a 2D DP table to store results of subproblems.
- Step 1: Initialize a 2D array `dp[n+1][sum+1]`.
- Step 2: Set the first column to `true` (sum `0` can be achieved with an empty set).
- Step 3: Fill the table:
  - If the current element is greater than the target sum, carry forward the value from the previous row.
  - Otherwise, check if the sum can be achieved either by including or excluding the current element.
- The final result is found in `dp[n][sum]`, indicating if a subset with the target sum exists.




#### Equal Sum Partition Problem
##### Pattern: Subset Sum Problem
[Back to Top](#table-of-contents)

##### Description:
- Input: An array of integers, e.g., `[1, 5, 11, 5]`
- Output: `true` (The array can be partitioned into two subsets with equal sum)
- Explanation: The array can be divided into two subsets, `{1, 5}` and `{5, 11}`, both summing to `6`.

```java
public class EqualSumPartition {
    // Function to check if there's a subset with the given sum
    public static boolean canPartition(int[] nums) {
        int totalSum = 0;
        // Calculate the total sum of the array
        for (int num : nums) {
            totalSum += num;
        }
        // If the total sum is odd, it's not possible to split it equally
        if (totalSum % 2 != 0) {
            return false;
        }
        // We need to find a subset with sum equal to half of the total sum
        int targetSum = totalSum / 2;
        // Call the recursive function to check for the subset
        return subsetSum(nums, nums.length, targetSum);
    }

    // Recursive function to check if there's a subset with the given sum
    private static boolean subsetSum(int[] nums, int n, int sum) {
        // Base case: If sum is 0, we found a subset
        if (sum == 0) {
            return true;
        }
        // Base case: If no numbers are left and sum is not 0
        if (n == 0) {
            return false;
        }
        // If the last number is greater than the current sum, skip it
        if (nums[n - 1] > sum) {
            return subsetSum(nums, n - 1, sum);
        } else {
            // Check by including or excluding the last number
            return subsetSum(nums, n - 1, sum) || subsetSum(nums, n - 1, sum - nums[n - 1]);
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};
        System.out.println(canPartition(nums)); // Output: true
    }
}
```

##### Time Complexity:
- The time complexity is \(O(2^n)\), where \(n\) is the number of elements in the array, due to the exponential growth of recursive calls.

##### Space Complexity:
- The space complexity is \(O(n)\), used for the recursion stack in the worst case.

##### Explanation of the Algorithm:
- Calculate the total sum of the array.
- Check if the total sum is odd; if so, return false.
- Set the target sum as half of the total sum.
- Use a recursive function to determine if a subset exists with the target sum:
  - **If sum is 0**: A valid subset is found (return true).
  - **If no numbers left**: No valid subset (return false).
  - **Check last element**:
    - If it's greater than the current sum, exclude it and recurse.
    - Otherwise, check both including and excluding the last element.
***



#### Equal Sum Partition Problem (Memoization)
##### Pattern: Subset Sum Problem
[Back to Top](#table-of-contents)

##### Description:
- Input: An array of integers, e.g., `[1, 5, 11, 5]`
- Output: `true` (The array can be partitioned into two subsets with equal sum)
- Explanation: The array can be divided into two subsets, `{1, 5}` and `{5, 11}`, both summing to `6`.

```java
import java.util.HashMap;

public class EqualSumPartitionMemoization {
    // Main function to check if the array can be partitioned into two equal subsets
    public static boolean canPartition(int[] nums) {
        int totalSum = 0;
        // Calculate the total sum of the array
        for (int num : nums) {
            totalSum += num;
        }
        // If the total sum is odd, we cannot partition it equally
        if (totalSum % 2 != 0) {
            return false;
        }
        // Set the target sum as half of the total sum
        int targetSum = totalSum / 2;
        // Create a memoization map to store results of subproblems
        HashMap<String, Boolean> memo = new HashMap<>();
        // Start the recursive function with the full array length and target sum
        return subsetSum(nums, nums.length, targetSum, memo);
    }

    // Recursive function to check if a subset with the given sum exists
    private static boolean subsetSum(int[] nums, int n, int sum, HashMap<String, Boolean> memo) {
        // Base case: If the target sum is 0, a valid subset is found
        if (sum == 0) {
            return true;
        }
        // Base case: If no elements are left and the sum is not 0, no valid subset exists
        if (n == 0) {
            return false;
        }

        // Create a unique key for memoization based on the current index and remaining sum
        String key = n + "-" + sum;

        // Check if the result for this state has already been computed
        if (memo.containsKey(key)) {
            return memo.get(key); // Return the cached result
        }

        // Check if the last element is greater than the current sum
        if (nums[n - 1] > sum) {
            // If greater, skip this element and move to the next
            boolean result = subsetSum(nums, n - 1, sum, memo);
            memo.put(key, result); // Store the result in memoization map
            return result;
        } else {
            // Check both possibilities: include or exclude the last element
            boolean includeLast = subsetSum(nums, n - 1, sum - nums[n - 1], memo); // Include last element
            boolean excludeLast = subsetSum(nums, n - 1, sum, memo); // Exclude last element
            
            // Store the result in memoization map
            boolean result = includeLast || excludeLast; // If either inclusion or exclusion gives true
            memo.put(key, result);
            return result;
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};
        System.out.println(canPartition(nums)); // Output: true
    }
}
```

##### Time Complexity:
- The time complexity is \(O(n \times \text{targetSum})\) due to the memoization of subproblems.

##### Space Complexity:
- The space complexity is \(O(n + \text{targetSum})\) for the memoization map and recursion stack.

##### Explanation of the Algorithm:
- Calculate the total sum of the array.
- Check if the total sum is odd; if so, return false (no equal partition possible).
- Set the target sum as half of the total sum.
- Use a recursive function with memoization to determine if a subset exists with the target sum:
  - **If sum is 0**: A valid subset is found (return true).
  - **If no numbers left**: No valid subset (return false).
  - **Create a unique key** for the current index and remaining sum to store results in memo.
  - **Check last element**:
    - If it's greater than the current sum, skip it and recurse.
    - Otherwise, check both including and excluding the last element:
      - **Include last**: Recurse with reduced sum.
      - **Exclude last**: Recurse without changing sum.
    - Return true if either inclusion or exclusion yields a valid subset.
***


#### Equal Sum Partition Problem (Top-Down Non-Recursive Approach)
##### Pattern: Subset Sum Problem
[Back to Top](#table-of-contents)

##### Description:
- Input: An array of integers, e.g., `[1, 5, 11, 5]`
- Output: `true` (The array can be partitioned into two subsets with equal sum)
- Explanation: The array can be divided into two subsets, `{1, 5}` and `{5, 11}`, both summing to `6`.

```java
import java.util.HashMap;

public class EqualSumPartitionTopDown {
    // Main function to check if the array can be partitioned into two equal subsets
    public static boolean canPartition(int[] nums) {
        int totalSum = 0;
        // Calculate the total sum of the array
        for (int num : nums) {
            totalSum += num;
        }
        // If the total sum is odd, we cannot partition it equally
        if (totalSum % 2 != 0) {
            return false;
        }
        // Set the target sum as half of the total sum
        int targetSum = totalSum / 2;

        // Call the helper function to perform the top-down approach
        return canPartitionUtil(nums, targetSum);
    }

    // Helper function for the top-down approach
    private static boolean canPartitionUtil(int[] nums, int targetSum) {
        int n = nums.length;
        // Create a 2D array to store results of subproblems
        boolean[][] dp = new boolean[n + 1][targetSum + 1];

        // Initialize the first column, as a sum of 0 can always be achieved
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true; // Base case: empty subset sums to 0
        }

        // Initialize the first row: If no elements are considered, no positive sum can be achieved
        for (int j = 1; j <= targetSum; j++) {
            dp[0][j] = false; // Base case: no elements cannot achieve any positive sum
        }

        // Fill the DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= targetSum; j++) {
                // Check if the current number can be included or excluded
                if (nums[i - 1] > j) {
                    // Current number is greater than the target sum
                    dp[i][j] = dp[i - 1][j]; // Exclude the number
                } else {
                    // Include the current number or exclude it
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]]; // Check both cases
                }
            }
        }

        // The last cell contains the answer: can we partition the array?
        return dp[n][targetSum];
    }

    // Main method for testing
    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};
        System.out.println(canPartition(nums)); // Output: true
    }
}
```

##### Time Complexity:
- The time complexity is \(O(n \times \text{targetSum})\) due to filling the DP table.

##### Space Complexity:
- The space complexity is \(O(n \times \text{targetSum})\) for the DP table.

##### Explanation of the Algorithm:
- Calculate the total sum of the array.
- Check if the total sum is odd; if so, return false (no equal partition possible).
- Set the target sum as half of the total sum.
- Use a 2D DP table where `dp[i][j]` represents whether a subset of the first `i` elements can achieve the sum `j`:
  - **Initialization**:
    - Set the first column to true (sum of 0 can always be achieved).
    - Set the first row to false (no elements can't achieve any positive sum).
  - **Filling the DP Table**:
    - Iterate through each element and each possible sum.
    - If the current number is greater than the current sum, exclude it and take the result from the previous row.
    - Otherwise, check both including and excluding the current number:
      - **Include**: Check if the remaining sum can be achieved without the current number.
      - **Exclude**: Check if the current number can be skipped to achieve the current sum.
- Return the value in the last cell, indicating if the partition is possible.
***




#### Count of Subset with a Given Sum Problem
##### Pattern: Dynamic Programming (Subset Sum)
[Back to Top](#table-of-contents)
##### Description:
- Input: An array of integers `arr = [1, 2, 3, 3]` and a target sum `sum = 6`
- Output: The count of subsets that sum to the target, which is `3`
- Explanation: The valid subsets that sum to 6 are `[3, 3]`, `[2, 1, 3]`, and `[1, 2, 3]`.

```java
public class SubsetSum {
    // Function to count subsets with a given sum
    public static int countSubsets(int[] arr, int sum) {
        return countSubsetsRecursive(arr, arr.length, sum);
    }

    // Recursive function to count subsets
    private static int countSubsetsRecursive(int[] arr, int n, int sum) {
        // Base case: if sum is 0, there's one subset (empty subset)
        if (sum == 0) {
            return 1;
        }
        // Base case: if no elements are left and sum is not 0
        if (n == 0) {
            return 0;
        }
        
        // If last element is greater than sum, ignore it
        if (arr[n - 1] > sum) {
            return countSubsetsRecursive(arr, n - 1, sum);
        } else {
            // Include the last element or exclude it
            return countSubsetsRecursive(arr, n - 1, sum) // exclude
                 + countSubsetsRecursive(arr, n - 1, sum - arr[n - 1]); // include
        }
    }

    // Main function to test the implementation
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3};
        int sum = 6;
        int result = countSubsets(arr, sum);
        System.out.println("Count of subsets with sum " + sum + ": " + result);
    }
}
```
##### Time Complexity:
- The time complexity is \(O(2^n)\) due to the recursive exploration of including or excluding each element.

##### Space Complexity:
- The space complexity is \(O(n)\) due to the recursion stack.

##### Algorithm
- If the `sum` is 0, return 1 (one subset exists: the empty subset).
- If there are no elements left and `sum` is not 0, return 0 (no valid subsets).
- If the last element is greater than `sum`, ignore it and call the function with remaining elements.
- Otherwise, consider two cases:
  - Exclude the last element and call the function with remaining elements.
  - Include the last element, reduce the `sum` by the last element's value, and call the function again with remaining elements.
***


#### Count of Subset with a Given Sum Problem (Memoization)
##### Pattern: Dynamic Programming (Subset Sum)
[Back to Top](#table-of-contents)
##### Description:
- Input: An array of integers `arr = [1, 2, 3, 3]` and a target sum `sum = 6`
- Output: The count of subsets that sum to the target, which is `3`
- Explanation: The valid subsets that sum to 6 are `[3, 3]`, `[2, 1, 3]`, and `[1, 2, 3]`.

```java
import java.util.HashMap;

public class SubsetSumMemoization {
    // Function to count subsets with a given sum using memoization
    public static int countSubsets(int[] arr, int sum) {
        // HashMap to store results of subproblems
        HashMap<String, Integer> memo = new HashMap<>();
        return countSubsetsRecursive(arr, arr.length, sum, memo);
    }

    // Recursive function to count subsets with memoization
    private static int countSubsetsRecursive(int[] arr, int n, int sum, HashMap<String, Integer> memo) {
        // Base case: if sum is 0, there is one subset (the empty subset)
        if (sum == 0) {
            return 1;
        }
        // Base case: if no elements are left and sum is not 0
        if (n == 0) {
            return 0;
        }

        // Create a unique key for the current state (n and sum)
        String key = n + "-" + sum;

        // Check if the result for this state is already computed
        if (memo.containsKey(key)) {
            return memo.get(key); // Return the cached result
        }

        // Condition: if the last element is greater than the sum, ignore it
        if (arr[n - 1] > sum) {
            // Recursive call excluding the last element
            memo.put(key, countSubsetsRecursive(arr, n - 1, sum, memo));
        } else {
            // Recursive calls for both including and excluding the last element
            int exclude = countSubsetsRecursive(arr, n - 1, sum, memo); // Exclude the last element
            int include = countSubsetsRecursive(arr, n - 1, sum - arr[n - 1], memo); // Include the last element

            // Store the result in memoization table
            memo.put(key, exclude + include);
        }
        
        return memo.get(key); // Return the computed result
    }

    // Main function to test the implementation
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3};
        int sum = 6;
        int result = countSubsets(arr, sum);
        System.out.println("Count of subsets with sum " + sum + ": " + result);
    }
}
```
##### Time Complexity:
- The time complexity is \(O(n \times \text{sum})\) due to the memoization storing results for each combination of `n` and `sum`.

##### Space Complexity:
- The space complexity is \(O(n + \text{sum})\) for the memoization table and the recursion stack.

##### Algorithm
- If `sum` is 0, return 1 (one subset exists: the empty subset).
- If `n` is 0 (no elements left) and `sum` is not 0, return 0 (no valid subsets).
- Create a unique key from `n` and `sum` to store in the memoization table.
- Check if the result for this key is already computed; if yes, return it.
- If the last element is greater than `sum`, ignore it and make a recursive call with `n-1`.
- Otherwise, consider two cases:
  - Exclude the last element: call the function with `n-1` and `sum`.
  - Include the last element: call the function with `n-1` and `sum - arr[n-1]`.
- Store the result for the current state in the memoization table and return it.
***

#### Count of Subset with a Given Sum Problem (Top-Down Approach)
##### Pattern: Dynamic Programming (Subset Sum)
[Back to Top](#table-of-contents)
##### Description:
- Input: An array of integers `arr = [1, 2, 3, 3]` and a target sum `sum = 6`
- Output: The count of subsets that sum to the target, which is `3`
- Explanation: The valid subsets that sum to 6 are `[3, 3]`, `[2, 1, 3]`, and `[1, 2, 3]`.

```java
public class SubsetSumTopDown {
    // Main function to count subsets with a given sum
    public static int countSubsets(int[] arr, int sum) {
        // Create a 2D array to store the count of subsets for different sums and array lengths
        int[][] dp = new int[arr.length + 1][sum + 1];

        // Initialize the DP table
        initializeDP(dp);

        // Fill the DP table using the actual logic
        return countSubsetsRecursive(arr, arr.length, sum, dp);
    }

    // Function to initialize the DP table
    private static void initializeDP(int[][] dp) {
        // Base case: There is one subset with sum 0 (the empty subset)
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 1; // One way to get sum 0: by choosing no elements
        }
        // Base case: No subsets exist for positive sums with zero elements
        for (int j = 1; j < dp[0].length; j++) {
            dp[0][j] = 0; // Zero ways to get positive sums with no elements
        }
    }

    // Function to fill the DP table based on the actual logic
    private static int countSubsetsRecursive(int[] arr, int n, int sum, int[][] dp) {
        // Iterate through each element in the array
        for (int i = 1; i <= n; i++) {
            // Iterate through each possible sum
            for (int j = 1; j <= sum; j++) {
                // If the current element is greater than the current sum, ignore it
                if (arr[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j]; // Copy the value from the previous row
                } else {
                    // Include the current element or exclude it
                    dp[i][j] = dp[i - 1][j] // Exclude the element
                             + dp[i - 1][j - arr[i - 1]]; // Include the element
                }
            }
        }
        // The answer is in the last cell of the table
        return dp[n][sum];
    }

    // Main function to test the implementation
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3};
        int sum = 6;
        int result = countSubsets(arr, sum);
        System.out.println("Count of subsets with sum " + sum + ": " + result);
    }
}
```
##### Time Complexity:
- The time complexity is \(O(n \times \text{sum})\) due to the nested loops filling the DP table.

##### Space Complexity:
- The space complexity is \(O(n \times \text{sum})\) for the DP table.

##### Algorithm
- Initialize a DP table with dimensions \((n+1) \times (\text{sum}+1)\).
- Fill the first column with `1` (base case: one way to get sum 0).
- Fill the first row with `0` (base case: zero ways to get positive sums with no elements).
- Iterate through the array:
  - For each element, iterate through possible sums.
  - If the element is greater than the sum, inherit the value from the previous row.
  - Otherwise, add the results from including and excluding the current element.
- The final count of subsets for the target sum is stored in the bottom-right cell of the DP table.
***





#### Minimum Subset Sum Difference Problem
##### Pattern: Dynamic Programming
[Back to Top](#table-of-contents)
##### Description:
- Input: An array of integers, e.g., `{1, 6, 11, 5}`
- Output: Minimum difference between the sums of two subsets, e.g., `1`
- Explanation: The array can be split into two subsets: `{1, 5}` and `{6, 11}`. The sums of these subsets are `6` and `17`, resulting in a minimum difference of `1`.

```java
public class MinimumSubsetSumDifference {
    
    // Function to calculate the minimum subset sum difference
    public static int findMinSubsetSumDiff(int[] nums) {
        int totalSum = 0;
        // Calculate the total sum of elements
        for (int num : nums) {
            totalSum += num;
        }
        // Call the recursive function to find the minimum difference
        return findMinDiff(nums, nums.length, totalSum, 0);
    }

    // Recursive function to find the minimum subset sum difference
    private static int findMinDiff(int[] nums, int n, int totalSum, int currentSum) {
        // Base case: if we reach the end of the array
        if (n == 0) {
            // Calculate and return the difference between two subsets
            return Math.abs((totalSum - currentSum) - currentSum);
        }
        
        // Include the current element in the first subset
        int includeCurrent = findMinDiff(nums, n - 1, totalSum, currentSum + nums[n - 1]);
        // Exclude the current element from the first subset
        int excludeCurrent = findMinDiff(nums, n - 1, totalSum, currentSum);
        
        // Return the minimum of both cases
        return Math.min(includeCurrent, excludeCurrent);
    }

    public static void main(String[] args) {
        int[] nums = {1, 6, 11, 5};
        System.out.println("Minimum Subset Sum Difference: " + findMinSubsetSumDiff(nums));
    }
}
```
##### Time Complexity:
- The time complexity is \(O(2^n)\) due to the two recursive calls made at each step, leading to an exponential growth in the number of subsets.

##### Space Complexity:
- The space complexity is \(O(n)\) due to the recursion stack depth in the worst case, where \(n\) is the number of elements in the array.

##### Algorithm
- Calculate the total sum of the array.
- Use a recursive function that explores both possibilities for each element (including or excluding it).
- When the recursion reaches the base case (end of the array), calculate the difference between the two subsets formed.
- Return the minimum difference found among all possible subsets.
***

#### Minimum Subset Sum Difference Problem with Memoization
##### Pattern: Dynamic Programming
[Back to Top](#table-of-contents)
##### Description:
- Input: An array of integers, e.g., `{1, 6, 11, 5}`
- Output: Minimum difference between the sums of two subsets, e.g., `1`
- Explanation: The array can be split into two subsets: `{1, 5}` and `{6, 11}`. The sums of these subsets are `6` and `17`, resulting in a minimum difference of `1`.

```java
import java.util.Arrays;

public class MinimumSubsetSumDifference {

    // Function to calculate the minimum subset sum difference
    public static int findMinSubsetSumDiff(int[] nums) {
        int totalSum = 0;
        // Calculate the total sum of elements in the array
        for (int num : nums) {
            totalSum += num;
        }
        // Create a memoization table to store results of subproblems
        int[][] memo = new int[nums.length + 1][totalSum + 1];
        // Initialize memoization table with -1 (indicating uncalculated states)
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        // Call the recursive function with memoization to find the minimum difference
        return findMinDiff(nums, nums.length, totalSum, 0, memo);
    }

    // Recursive function to find the minimum subset sum difference with memoization
    private static int findMinDiff(int[] nums, int n, int totalSum, int currentSum, int[][] memo) {
        // Base case: if we have considered all elements
        if (n == 0) {
            // Calculate and return the difference between two subsets
            return Math.abs((totalSum - currentSum) - currentSum);
        }

        // Check if the result for this state is already computed
        if (memo[n][currentSum] != -1) {
            // Return the stored result to avoid redundant calculations
            return memo[n][currentSum];
        }

        // Case 1: Include the current element in the first subset
        int includeCurrent = findMinDiff(nums, n - 1, totalSum, currentSum + nums[n - 1], memo);

        // Case 2: Exclude the current element from the first subset
        int excludeCurrent = findMinDiff(nums, n - 1, totalSum, currentSum, memo);

        // Calculate the minimum difference from both cases
        int minDiff = Math.min(includeCurrent, excludeCurrent);
        
        // Store the result in the memoization table
        memo[n][currentSum] = minDiff;

        // Return the minimum difference found
        return minDiff;
    }

    public static void main(String[] args) {
        int[] nums = {1, 6, 11, 5};
        System.out.println("Minimum Subset Sum Difference: " + findMinSubsetSumDiff(nums));
    }
}
```
##### Time Complexity:
- The time complexity is \(O(n \times \text{totalSum})\) due to the memoization table that stores results for each combination of `n` (number of elements) and `currentSum`.

##### Space Complexity:
- The space complexity is \(O(n \times \text{totalSum})\) for the memoization table plus \(O(n)\) for the recursion stack, leading to a total of \(O(n \times \text{totalSum})\).

##### Algorithm
- Calculate the total sum of the array.
- Initialize a memoization table to store results of subproblems.
- Use a recursive function that checks:
  - If the result is already computed (memoization).
  - Include the current element in the first subset and recursively call for the remaining elements.
  - Exclude the current element and call recursively for the remaining elements.
- When the recursion reaches the base case (end of the array), calculate the difference between the two subsets formed.
- Store and return the minimum difference found among all possible subsets.
***

#### Minimum Subset Sum Difference Problem with Top-Down Approach (Non-Recursive)
##### Pattern: Dynamic Programming
[Back to Top](#table-of-contents)
##### Description:
- Input: An array of integers, e.g., `{1, 6, 11, 5}`
- Output: Minimum difference between the sums of two subsets, e.g., `1`
- Explanation: The array can be split into two subsets: `{1, 5}` and `{6, 11}`. The sums of these subsets are `6` and `17`, resulting in a minimum difference of `1`.

```java
import java.util.Arrays;

public class MinimumSubsetSumDifference {

    // Function to calculate the minimum subset sum difference using a top-down approach
    public static int findMinSubsetSumDiff(int[] nums) {
        int totalSum = 0;

        // Calculate the total sum of all elements in the array
        for (int num : nums) {
            totalSum += num;
        }

        // Initialize the DP table
        int[][] dp = initializeDPTable(nums.length, totalSum);

        // Calculate the minimum difference using the DP table
        return calculateMinDiff(nums, dp, nums.length, totalSum);
    }

    // Initialize the DP table for subset sum problem
    private static int[][] initializeDPTable(int n, int totalSum) {
        // Create a DP table with dimensions (n+1) x (totalSum+1)
        int[][] dp = new int[n + 1][totalSum + 1];

        // Fill the first column with 0 (0 sum can always be achieved with any number of elements)
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1; // 1 indicates that the sum can be achieved
        }

        // Fill the first row with 0 (non-zero sum cannot be achieved with 0 elements)
        for (int j = 1; j <= totalSum; j++) {
            dp[0][j] = 0; // 0 indicates that the sum cannot be achieved
        }

        return dp; // Return the initialized DP table
    }

    // Calculate the minimum subset sum difference using the DP table
    private static int calculateMinDiff(int[] nums, int[][] dp, int n, int totalSum) {
        // Fill the DP table using the bottom-up approach
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= totalSum; j++) {
                // Check if the current element can be included
                if (nums[i - 1] <= j) {
                    // Include the element or exclude it
                    dp[i][j] = dp[i - 1][j - nums[i - 1]] | dp[i - 1][j]; // Logical OR
                } else {
                    // Exclude the current element
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // Find the largest j such that dp[n][j] is true (indicating subset sum can be achieved)
        int minDiff = Integer.MAX_VALUE;
        for (int j = 0; j <= totalSum / 2; j++) {
            if (dp[n][j] == 1) { // If sum j can be achieved
                // Calculate the difference between the two subsets
                int diff = totalSum - 2 * j; // difference = totalSum - 2 * sum1
                minDiff = Math.min(minDiff, diff); // Keep track of the minimum difference
            }
        }

        return minDiff; // Return the minimum difference found
    }

    public static void main(String[] args) {
        int[] nums = {1, 6, 11, 5}; // Example input
        // Output the result of minimum subset sum difference
        System.out.println("Minimum Subset Sum Difference: " + findMinSubsetSumDiff(nums));
    }
}
```
##### Time Complexity:
- The time complexity is \(O(n \times \text{totalSum})\) due to filling the DP table, where `n` is the number of elements and `totalSum` is the sum of all elements.

##### Space Complexity:
- The space complexity is \(O(n \times \text{totalSum})\) for the DP table used to store intermediate results.

##### Algorithm
- Calculate the total sum of the array elements.
- Initialize a DP table to store results for achievable subset sums.
  - Fill the first column to indicate that a sum of `0` can be achieved with any number of elements.
  - Fill the first row to indicate that non-zero sums cannot be achieved with `0` elements.
- Use nested loops to fill the DP table:
  - For each element, check if it can be included in the current subset sum.
  - Update the DP table based on whether to include or exclude the current element.
- After filling the DP table, find the largest subset sum that can be achieved from the first half of the total sum.
- Calculate the minimum difference between the total sum and twice the achieved subset sum.
- Return the minimum difference found.
***



#### Count the Number of Subsets with a Given Difference
##### Pattern: Dynamic Programming (Subset Sum)
[Back to Top](#table-of-contents)
##### Description:
- Input:
  - `arr[] = {1, 1, 2, 3}`
  - `difference = 1`
- Output:
  - `3`
- Explanation:
  - There are three subsets with a difference of `1`: `{1, 2}`, `{1, 3}`, and `{1, 1, 2, 3}`.

```java
public class SubsetDifference {

    // Function to count subsets with a given difference
    public static int countSubsetsWithDifference(int[] arr, int n, int diff) {
        // Calculate the total sum of the array
        int totalSum = 0;
        for (int num : arr) {
            totalSum += num;
        }

        // Check if the required subset sum is valid
        int requiredSum = (totalSum + diff) / 2;
        if ((totalSum + diff) % 2 != 0 || requiredSum < 0) {
            return 0; // No valid subsets
        }

        // Call the recursive helper function
        return countSubsets(arr, n, requiredSum);
    }

    // Recursive function to count subsets with a given sum
    private static int countSubsets(int[] arr, int n, int sum) {
        // Base cases
        if (sum == 0) {
            return 1; // Found a valid subset
        }
        if (n == 0) {
            return 0; // No elements left to form a subset
        }

        // Exclude the last element
        int exclude = countSubsets(arr, n - 1, sum);
        
        // Include the last element, if it doesn't exceed the sum
        int include = (arr[n - 1] <= sum) ? countSubsets(arr, n - 1, sum - arr[n - 1]) : 0;

        // Return the total count of subsets
        return exclude + include;
    }

    // Main method to test the function
    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 3};
        int difference = 1;
        int result = countSubsetsWithDifference(arr, arr.length, difference);
        System.out.println("Number of subsets with given difference: " + result);
    }
}
```
##### Time Complexity:
- The time complexity is \(O(2^n)\), where \(n\) is the number of elements in the array, due to the recursive nature of the function.

##### Space Complexity:
- The space complexity is \(O(n)\) due to the recursion stack.

##### Algorithm
- Calculate the total sum of the array elements.
- Determine the required subset sum using the formula: \((\text{totalSum} + \text{difference}) / 2\).
- Check if the required sum is valid (i.e., non-negative and integer).
- Use recursion to count subsets that sum to the required sum:
  - If `sum` is `0`, return `1` (valid subset found).
  - If `n` is `0`, return `0` (no elements left).
  - Recur by excluding the last element.
  - Recur by including the last element (if it doesn't exceed the sum).
- Return the total count of valid subsets.
***

#### Count the Number of Subsets with a Given Difference (Memoization)
##### Pattern: Dynamic Programming (Subset Sum)
[Back to Top](#table-of-contents)
##### Description:
- Input:
  - `arr[] = {1, 1, 2, 3}`
  - `difference = 1`
- Output:
  - `3`
- Explanation:
  - There are three subsets with a difference of `1`: `{1, 2}`, `{1, 3}`, and `{1, 1, 2, 3}`.

```java
import java.util.HashMap;

public class SubsetDifferenceMemoization {

    // Memoization map to store computed results
    private static HashMap<String, Integer> memo = new HashMap<>();

    // Function to count subsets with a given difference using memoization
    public static int countSubsetsWithDifference(int[] arr, int n, int diff) {
        // Calculate the total sum of the array
        int totalSum = 0;
        for (int num : arr) {
            totalSum += num; // Summing up the elements
        }

        // Calculate the required subset sum based on the difference
        int requiredSum = (totalSum + diff) / 2;

        // Check if the required subset sum is valid
        // Condition 1: totalSum + diff must be even
        // Condition 2: requiredSum must be non-negative
        if ((totalSum + diff) % 2 != 0 || requiredSum < 0) {
            return 0; // Invalid subset sum, return 0
        }

        // Call the recursive helper function to count subsets
        return countSubsets(arr, n, requiredSum);
    }

    // Recursive function to count subsets with a given sum using memoization
    private static int countSubsets(int[] arr, int n, int sum) {
        // Base Case 1: If sum is 0, we found a valid subset
        if (sum == 0) {
            return 1; // Valid subset found
        }

        // Base Case 2: If no elements are left and sum is not 0
        if (n == 0) {
            return 0; // No valid subsets can be formed
        }

        // Create a unique key for memoization
        String key = n + "|" + sum;

        // Check if the result is already computed and stored in memo
        if (memo.containsKey(key)) {
            return memo.get(key); // Return the cached result
        }

        // Exclude the last element and count subsets without it
        int exclude = countSubsets(arr, n - 1, sum);

        // Include the last element only if it does not exceed the remaining sum
        int include = (arr[n - 1] <= sum) ? countSubsets(arr, n - 1, sum - arr[n - 1]) : 0;

        // Store the result in memo before returning
        memo.put(key, exclude + include); // Cache the result
        return memo.get(key); // Return the total count of subsets
    }

    // Main method to test the function
    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 3};
        int difference = 1;
        int result = countSubsetsWithDifference(arr, arr.length, difference);
        System.out.println("Number of subsets with given difference: " + result);
    }
}
```
##### Time Complexity:
- The time complexity is \(O(n \times \text{sum})\), where \(n\) is the number of elements in the array and \(\text{sum}\) is the required subset sum due to the memoization technique.

##### Space Complexity:
- The space complexity is \(O(n \times \text{sum})\) for storing the memoization results.

##### Algorithm
- Calculate the total sum of the array elements.
- Determine the required subset sum using the formula: \((\text{totalSum} + \text{difference}) / 2\).
- Check if the required sum is valid:
  - **Condition 1**: Check if \((\text{totalSum} + \text{difference})\) is even.
  - **Condition 2**: Check if the required sum is non-negative.
- Use recursion with memoization to count subsets that sum to the required sum:
  - **Base Case 1**: If `sum` is `0`, return `1` (valid subset found).
  - **Base Case 2**: If `n` is `0` and `sum` is not `0`, return `0` (no valid subsets).
  - Create a unique key using `n` and `sum` for memoization.
  - Check if the result is already computed; if yes, return the cached result.
  - Recur by excluding the last element (count subsets without it).
  - Recur by including the last element (if it doesn't exceed the sum).
  - Store the computed result in the memoization map before returning it.
***

#### Count the Number of Subsets with a Given Difference (Top-Down Non-Recursive Approach)
##### Pattern: Dynamic Programming (Subset Sum)
[Back to Top](#table-of-contents)
##### Description:
- Input:
  - `arr[] = {1, 1, 2, 3}`
  - `difference = 1`
- Output:
  - `3`
- Explanation:
  - There are three subsets with a difference of `1`: `{1, 2}`, `{1, 3}`, and `{1, 1, 2, 3}`.

```java
import java.util.Arrays;

public class SubsetDifferenceTopDown {

    // Function to count subsets with a given difference using a top-down approach
    public static int countSubsetsWithDifference(int[] arr, int diff) {
        // Calculate the total sum of the array
        int totalSum = 0;
        for (int num : arr) {
            totalSum += num; // Summing up the elements
        }

        // Calculate the required subset sum based on the difference
        int requiredSum = (totalSum + diff) / 2;

        // Check if the required subset sum is valid
        // Condition 1: totalSum + diff must be even
        // Condition 2: requiredSum must be non-negative
        if ((totalSum + diff) % 2 != 0 || requiredSum < 0) {
            return 0; // Invalid subset sum, return 0
        }

        // Initialize the DP table
        return countSubsets(arr, requiredSum);
    }

    // Function to count subsets with a given sum using dynamic programming (top-down)
    private static int countSubsets(int[] arr, int sum) {
        int n = arr.length; // Get the number of elements in the array

        // Create a DP table to store the results for subproblems
        int[][] dp = new int[n + 1][sum + 1];

        // Initialize base case: If sum is 0, there is 1 way to form this sum (empty subset)
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1; // One way to make sum 0
        }

        // Fill the DP table iteratively
        for (int i = 1; i <= n; i++) { // Loop through elements
            for (int j = 1; j <= sum; j++) { // Loop through all possible sums
                // Exclude the last element
                dp[i][j] = dp[i - 1][j];

                // Include the last element if it does not exceed the current sum
                if (arr[i - 1] <= j) {
                    dp[i][j] += dp[i - 1][j - arr[i - 1]];
                }
            }
        }

        // The answer is the number of subsets that sum up to the required sum
        return dp[n][sum];
    }

    // Main method to test the function
    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 3};
        int difference = 1;
        int result = countSubsetsWithDifference(arr, difference);
        System.out.println("Number of subsets with given difference: " + result);
    }
}
```
##### Time Complexity:
- The time complexity is \(O(n \times \text{sum})\), where \(n\) is the number of elements in the array and \(\text{sum}\) is the required subset sum.

##### Space Complexity:
- The space complexity is \(O(n \times \text{sum})\) for the DP table.

##### Algorithm
- Calculate the total sum of the array elements.
- Determine the required subset sum using the formula: \((\text{totalSum} + \text{difference}) / 2\).
- Check if the required sum is valid:
  - **Condition 1**: Check if \((\text{totalSum} + \text{difference})\) is even.
  - **Condition 2**: Check if the required sum is non-negative.
- Initialize a DP table where `dp[i][j]` represents the number of subsets using the first `i` elements that sum up to `j`:
  - Set `dp[i][0] = 1` for all `i` (one way to make sum 0).
- Fill the DP table:
  - For each element, decide to exclude or include it based on whether it exceeds the current sum.
- Return the value in `dp[n][\text{sum}]`, which contains the total count of subsets that sum to the required value.
***


#### Target Sum Problem
##### Pattern: Dynamic Programming (Recursion)

[Back to Top](#table-of-contents)

##### Description:
- **Input**: An array of integers `nums` and an integer `target`.
  - Example: `nums = [1, 1, 1, 1, 1], target = 3`
- **Output**: The number of ways to assign `+` and `-` to the numbers such that their sum equals the target.
  - Expected Output: `5`
- **Explanation**:
  By assigning signs to each number, there are 5 valid combinations that reach the sum of 3.

```java
public class TargetSum {
    // Recursive function to calculate ways to reach target sum
    public int findTargetSumWays(int[] nums, int target) {
        // Start recursion from index 0 with current sum as 0
        return calculateWays(nums, 0, 0, target);
    }

    // Recursive function definition
    private int calculateWays(int[] nums, int i, int currentSum, int target) {
        // Base case: When we have gone through all numbers
        if (i == nums.length) {
            // Check if the current sum equals the target
            if (currentSum == target) {
                return 1; // One valid combination found
            } else {
                return 0; // No valid combination
            }
        }

        // Recursive cases:
        // 1. Include the number with a positive sign
        int add = calculateWays(nums, i + 1, currentSum + nums[i], target);

        // 2. Include the number with a negative sign
        int subtract = calculateWays(nums, i + 1, currentSum - nums[i], target);

        // Return the total number of ways (sum of both possibilities)
        return add + subtract;
    }

    public static void main(String[] args) {
        TargetSum ts = new TargetSum();
        int[] nums = {1, 1, 1, 1, 1};
        int target = 3;
        System.out.println("Number of ways: " + ts.findTargetSumWays(nums, target));
    }
}
```

##### Time Complexity:
- The time complexity is **O(2^n)**, where `n` is the length of the input array `nums`, because for each element, we explore two possibilities (either add or subtract).

##### Space Complexity:
- The space complexity is **O(n)**, which is the recursion depth (equal to the number of elements in the array).

##### Algorithm
- Start at the first index of the array.
- At each step, recursively consider adding or subtracting the current element from the sum.
- If you reach the end of the array, check if the current sum equals the target.
- Return the total number of ways the target sum can be achieved.
- No pointers are updated here; instead, recursion explores every possibility (adding/subtracting).
***
#### Target Sum Problem(Memoization)
##### Pattern: Dynamic Programming (Memoization)

[Back to Top](#table-of-contents)

##### Description:
- **Input**: An array of integers `nums` and an integer `target`.
  - Example: `nums = [1, 1, 1, 1, 1], target = 3`
- **Output**: The number of ways to assign `+` and `-` to the numbers such that their sum equals the target.
  - Expected Output: `5`
- **Explanation**:
  By assigning signs to each number, there are 5 valid combinations that reach the sum of 3.

```java
import java.util.HashMap;

public class TargetSum {
    // HashMap to store the results of subproblems (memoization)
    private HashMap<String, Integer> memo;

    // Main function to find the number of ways to reach the target sum
    public int findTargetSumWays(int[] nums, int target) {
        // Initialize the memoization map
        memo = new HashMap<>();
        // Start recursion from index 0 with current sum as 0
        return calculateWays(nums, 0, 0, target);
    }

    // Recursive function to calculate ways to reach target sum
    private int calculateWays(int[] nums, int i, int currentSum, int target) {
        // Base case: When we have gone through all numbers
        if (i == nums.length) {
            // Check if the current sum equals the target
            if (currentSum == target) {
                return 1; // One valid combination found
            } else {
                return 0; // No valid combination
            }
        }

        // Create a unique key for the current state (index and currentSum)
        String key = i + ":" + currentSum;

        // Check if the result for this state is already computed
        if (memo.containsKey(key)) {
            return memo.get(key); // Return the cached result
        }

        // Recursive cases:
        // 1. Include the number with a positive sign
        int add = calculateWays(nums, i + 1, currentSum + nums[i], target);

        // 2. Include the number with a negative sign
        int subtract = calculateWays(nums, i + 1, currentSum - nums[i], target);

        // Total ways is the sum of both possibilities
        int totalWays = add + subtract;

        // Store the result in memoization map to avoid recomputation
        memo.put(key, totalWays);

        // Return the total number of ways
        return totalWays;
    }

    public static void main(String[] args) {
        TargetSum ts = new TargetSum();
        int[] nums = {1, 1, 1, 1, 1};
        int target = 3;
        System.out.println("Number of ways: " + ts.findTargetSumWays(nums, target));
    }
}
```

##### Time Complexity:
- The time complexity is **O(n * s)**, where `n` is the length of the input array `nums` and `s` is the range of possible sums. Each unique state (index and currentSum) is computed once and cached.

##### Space Complexity:
- The space complexity is **O(n * s)** for storing the memoization states. The space required for the recursion stack is **O(n)**, but the memoization dominates.

##### Algorithm
- Initialize a HashMap for memoization to cache results of previously computed states.
- Start at the first index of the array with a current sum of `0`.
- If the index equals the length of the array, check if the current sum equals the target:
  - If yes, return `1` (a valid combination).
  - If no, return `0` (no valid combination).
- Check if the current state (index and current sum) is already computed:
  - If yes, return the cached result.
- Recursively compute:
  - **Add**: Include the current number with a positive sign.
  - **Subtract**: Include the current number with a negative sign.
- Cache the total number of ways for the current state and return it.
- The algorithm uses recursion instead of pointers to explore combinations of signs.
***

#### Target Sum Problem(Top - Down)
##### Pattern: Dynamic Programming (Top-Down)

[Back to Top](#table-of-contents)

##### Description:
- **Input**: An array of integers `nums` and an integer `target`.
  - Example: `nums = [1, 1, 1, 1, 1], target = 3`
- **Output**: The number of ways to assign `+` and `-` to the numbers such that their sum equals the target.
  - Expected Output: `5`
- **Explanation**:
  By assigning signs to each number, there are 5 valid combinations that reach the sum of 3.

```java
import java.util.HashMap;

public class TargetSum {
    // HashMap to store results of subproblems (memoization)
    private HashMap<String, Integer> memo;

    // Main function to find the number of ways to reach the target sum
    public int findTargetSumWays(int[] nums, int target) {
        // Initialize the memoization map
        memo = new HashMap<>();
        
        // Start the recursive computation
        return calculateWays(nums, 0, 0, target);
    }

    // Actual logic for calculating ways to reach target sum
    private int calculateWays(int[] nums, int i, int currentSum, int target) {
        // Base case: When we have processed all numbers
        if (i == nums.length) {
            // Check if the current sum equals the target
            if (currentSum == target) {
                return 1; // One valid combination found
            } else {
                return 0; // No valid combination
            }
        }

        // Create a unique key for the current state (index and currentSum)
        String key = i + ":" + currentSum;

        // Check if the result for this state is already computed
        if (memo.containsKey(key)) {
            return memo.get(key); // Return the cached result
        }

        // Recursive cases:
        // 1. Include the current number with a positive sign
        int add = calculateWays(nums, i + 1, currentSum + nums[i], target);

        // 2. Include the current number with a negative sign
        int subtract = calculateWays(nums, i + 1, currentSum - nums[i], target);

        // Total ways is the sum of both possibilities
        int totalWays = add + subtract;

        // Store the result in the memoization map to avoid recomputation
        memo.put(key, totalWays);

        // Return the total number of ways
        return totalWays;
    }

    public static void main(String[] args) {
        TargetSum ts = new TargetSum();
        int[] nums = {1, 1, 1, 1, 1};
        int target = 3;
        System.out.println("Number of ways: " + ts.findTargetSumWays(nums, target));
    }
}
```

##### Time Complexity:
- The time complexity is **O(n * s)**, where `n` is the length of the input array `nums` and `s` is the range of possible sums. Each unique state (index and currentSum) is computed once and cached.

##### Space Complexity:
- The space complexity is **O(n * s)** for storing the memoization states. The space required for the recursion stack is **O(n)**, but the memoization dominates.

##### Algorithm
- **Initialization**:
  - Create a HashMap to store previously computed results.
  - Start the recursive process with the first index and a current sum of `0`.

- **Logic**:
  - If all elements are processed (base case), check if the current sum equals the target.
  - If the current state is already computed, return the cached result.
  - For each number:
    - Calculate ways by adding the current number with a `+` sign.
    - Calculate ways by adding the current number with a `-` sign.
  - Sum the results of both cases and store it in the memoization map.
  - Return the total number of ways.
***







## Additional Problems Hello Interview

#### Counting Bits
##### Pattern: Dynamic Programming
[Back to Top](#table-of-contents)

##### Description:
- **Input**: An integer `n`.
- **Output**: An array `result` where `result[i]` is the number of `1`s in the binary representation of `i` for `0 <= i <= n`.
- **Explanation**: For each number from 0 to `n`, return the count of `1`s in its binary representation. The solution should use recursion, with each condition explicitly defined.

```java
public class CountingBits {
    // Function to count bits using recursion
    public static int[] countBits(int n) {
        int[] result = new int[n + 1]; // Array to store result
        countBitsRecursive(result, n); // Fill array using recursion
        return result; // Return the result array
    }

    // Recursive function to calculate the number of 1s
    private static int countBitsRecursive(int[] result, int num) {
        // Base case: 0 has no 1s in its binary representation
        if (num == 0) {
            result[0] = 0;
            return 0;
        }
        // If the value is already computed, return it
        if (result[num] != 0) {
            return result[num];
        }
        // Recursive relation: result[i] = result[i / 2] + i % 2
        result[num] = countBitsRecursive(result, num / 2) + num % 2;
        return result[num];
    }

    // Main method to test the function
    public static void main(String[] args) {
        int n = 5; // Example input
        int[] result = countBits(n); // Get the result
        // Print the result
        for (int i = 0; i <= n; i++) {
            System.out.println("Number of 1s in " + i + " (" + Integer.toBinaryString(i) + "): " + result[i]);
        }
    }
}
```

##### Time Complexity:
- **O(n)**: The recursion visits each number from `0` to `n` once, and each bit-count operation is constant.

##### Space Complexity:
- **O(n)**: We use an array of size `n+1` to store the result, and the recursion stack depth is proportional to `n`.

##### Algorithm:
- Initialize an array `result` to store the count of 1s for each number.
- Use a recursive function to compute the number of bits:
  - Base case: If the number is `0`, return `0`.
  - If the result is already computed for the number, return it (memoization).
  - Otherwise, calculate the result using `countBitsRecursive(num / 2) + num % 2`:
    - This breaks down the number recursively by dividing by `2`.
    - Add `1` if the number is odd (`num % 2`).
- Repeat this for every number from `0` to `n`.
***

#### Decode Ways
##### Pattern: Dynamic Programming (Recursion)
[Back to Top](#table-of-contents)
##### Description:
- Input: `"226"`
- Output: `3`
- Explanation: Possible decodings are "BZ" (2 26), "VF" (22 6), "BBF" (2 2 6).

```java
public class DecodeWays {

   public int numDecodings(String s) {
    // Edge case: if the string is empty or starts with '0', no valid decoding
    if (s == null || s.length() == 0 || s.charAt(0) == '0') {
        return 0;
    }
    
    return decodeHelper(s, 0); 
}

int decodeHelper(String s, int index) {
    // Base case: if we reach the end of the string, there's one valid way to decode
    if (index == s.length()) {
        return 1; 
    }

    // If the current character is '0', it's an invalid path
    if (s.charAt(index) == '0') {
        return 0;
    }

    // Option 1: Decode one character
    int selectOne = decodeHelper(s, index + 1);

    // Option 2: Decode two characters (check if it's valid)
    int selectTwo = 0;
    if (index + 1 < s.length()) {
        int twoDigit = Integer.parseInt(s.substring(index, index + 2));
        if (twoDigit >= 10 && twoDigit <= 26) {
            selectTwo = decodeHelper(s, index + 2);
        }
    }

    // Return the total count from both options
    return selectOne + selectTwo;  
 }
}
```

##### Time Complexity:
- The time complexity is **O(2^n)** due to the two recursive calls at each step.

##### Space Complexity:
- The space complexity is **O(n)**, due to the recursion stack in the worst case.

##### Algorithm:
- Base condition: If string is empty or starts with '0', return 0.
- If current character is '0', it's an invalid decoding path.
- Recursively check for valid single and two-digit combinations.
- Update the recursive calls by advancing either by 1 or 2 based on the valid decodings.
***



#### Unique Paths (Recursive Only)
##### Pattern: Dynamic Programming (Recursion)
[Back to Top](#table-of-contents)

##### Description:
- **Input**:
  - `m = 3`, `n = 2` (Grid of 3 rows and 2 columns)
- **Output**:
  - `3` (There are 3 unique paths from top-left to bottom-right)
- **Explanation**:
  - We need to find all unique paths from the top-left corner to the bottom-right corner, moving only right or down.

```java
public class UniquePaths {

    // Recursive function to calculate unique paths
    public static int uniquePaths(int down, int right) {
        // Base case: If we reach the bottom-right corner
        if (down == 1 && right == 1) {
            return 1;
        }
        
        // Out of bounds: If m or n is less than 1, there's no valid path
        if (down < 1 || right < 1) {
            return 0;
        }
        
        // Recursive case:
        // Move down
        int moveDown = uniquePaths(down - 1, right); 
        // Move right
        int moveRight = uniquePaths(down, right - 1);
        
        // Sum of paths from moving down (m-1, n) and moving right (m, n-1)
        return  moveDown + moveRight;
    }

    // Example to test the function
    public static void main(String[] args) {
        int m = 3, n = 2;
        System.out.println("Unique paths for a grid of " + m + "x" + n + " is: " + uniquePaths(m, n));
    }
}
```

##### Time Complexity:
- **O(2^(m+n))**: The time complexity is exponential since for each recursive call, we have two possible paths (down and right). This leads to 2 recursive calls per step.

##### Space Complexity:
- **O(m+n)**: The space complexity is due to the recursion stack, where the maximum depth will be `m + n` (sum of rows and columns).

##### Algorithm:
- Base case: If `m == 1` and `n == 1`, we have reached the target.
- Check out-of-bounds conditions for invalid moves (`m < 1` or `n < 1`).
- Recursively move down by decreasing `m` (moving to the row below).
- Recursively move right by decreasing `n` (moving to the next column).
- Sum the result of both moves to get the total unique paths.
***


#### Longest Increasing Subsequence (LIS)
##### Pattern: Recursion
[Back to Top](#table-of-contents)
##### Description:
- **Input:** `[10, 9, 2, 5, 3, 7, 101, 18]`
- **Output:** `4`
- **Explanation:** The longest increasing subsequence is `[2, 3, 7, 101]` and its length is 4.

```java
// Class to solve the Longest Increasing Subsequence using Recursion
public class LISRecursion {

    // Helper function to find the longest increasing subsequence
    public static int lisRecursive(int[] nums, int currentIndex, int prevIndex) {
        // Base case: if we reach the end of the array
        if (currentIndex == nums.length) {
            return 0;
        }

        // Option 1: Exclude the current element and move to the next element
        int exclude = lisRecursive(nums, currentIndex + 1, prevIndex);

        // Option 2: Include the current element if it's greater than the previous included element
        int include = 0;
        if (prevIndex == -1 || nums[currentIndex] > nums[prevIndex]) {
            include = 1 + lisRecursive(nums, currentIndex + 1, currentIndex);
        }

        // Return the maximum of include and exclude
        return Math.max(include, exclude);
    }

    // Wrapper function to call the recursive solution
    public static int longestIncreasingSubsequence(int[] nums) {
        // Start with index 0 and previous index -1 (no element considered yet)
        return lisRecursive(nums, 0, -1);
    }

    // Driver code to test the function
    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("Length of Longest Increasing Subsequence: " + longestIncreasingSubsequence(nums));
    }
}
```

##### Time Complexity:
- The time complexity of this solution is **O(2^n)**, where `n` is the length of the input array. This is because we have two choices at each step (include or exclude), leading to an exponential number of recursive calls.

##### Space Complexity:
- The space complexity is **O(n)**, due to the recursive call stack depth, where `n` is the length of the array.

##### Algorithm
- Start from index 0 with no previously selected element (prevIndex = -1).
- For each element, we decide whether to include it in the subsequence or not.
- If we include it, the current element must be greater than the previously included one.
- If we don't include it, move to the next element.
- Recursively evaluate both options and return the maximum length.
***





## All Problems

### Advanced Level
Edit Distance - Link
Maximum Product Subarray - Link
Burst Balloons - Link
Palindrome Partitioning II - Link
Wildcard Matching - Link
Regular Expression Matching - Link
Russian Doll Envelopes - Link
Comprehensive Challenges

### Comprehensive Challenges
Longest Common Subsequence - Link
Interleaving String - Link
Maximal Square - Link
Word Search II - Link
Distinct Subsequences - Link
Dungeon Game - Link
Scramble String - Link

### Beginner Level
Climbing Stairs - Link
House Robber - Link
Min Cost Climbing Stairs - Link
Fibonacci Number - Link
Best Time to Buy and Sell Stock - Link
Maximum Subarray - Link
Coin Change - Link



#### Climb Stairs (Recursive Only)
##### Pattern: Dynamic Programming
[Back to Top](#table-of-contents)
##### Description:
- Input: `n = 3` (Number of steps)
- Output: `3`
- Explanation: There are three ways to climb 3 stairs: `(1+1+1)`, `(1+2)`, and `(2+1)`.

```java
// Recursive solution for Climb Stairs problem
public class ClimbStairs {
    
    // Recursive function to find the number of ways to climb 'n' stairs
    public static int climbStairs(int n) {
        // Base case 1: If there are no stairs, there is only one way (do nothing)
        if (n == 0) {
            return 1;
        }
        // Base case 2: If there is only one stair, there is only one way to climb it
        if (n == 1) {
            return 1;
        }
        // Recursive case: Sum of the ways to climb (n-1) stairs and (n-2) stairs
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    public static void main(String[] args) {
        int n = 3; // Example input
        System.out.println(climbStairs(n)); // Expected output: 3
    }
}
```
##### Time Complexity:
- `O(2^n)`: Each call branches into two recursive calls, leading to an exponential number of calls.

##### Space Complexity:
- `O(n)`: The depth of the recursion tree is `n`, so the space complexity is linear due to the function call stack.

##### Algorithm:
- Start from the top and recursively calculate the number of ways to climb the last step.
- Base cases:
  - If `n == 0`, return 1 (1 way to stay on the ground).
  - If `n == 1`, return 1 (1 way to climb one step).
- For other cases, add the results of `climbStairs(n-1)` and `climbStairs(n-2)` (ways to climb from the last two steps).
***
***

#### House Robber
##### Pattern: Dynamic Programming (Recursion)
[Back to Top](#table-of-contents)
##### Description:
- **Input:** `[1, 2, 3, 1]` (List of non-negative integers representing the amount of money at each house)
- **Output:** `4` (Maximum amount of money that can be robbed without robbing two adjacent houses)
- **Explanation:** Rob houses 1 and 3 (money = 1 + 3 = 4).

```java
public class HouseRobber {

    // Main function to calculate the maximum amount that can be robbed
    public int rob(int[] nums) {
        int houseCount = nums.length;  // Total number of houses
        return robHelper(nums, houseCount);  // Start the recursive process
    }

    // Helper recursive function to calculate maximum money that can be robbed
    public int robHelper(int[] nums, int houseCount) {
        // Base case 1: If there are no houses left, return 0
        if (houseCount == 0) return 0;

        // Base case 2: If there's only one house, return its value
        if (houseCount == 1) return nums[houseCount - 1];

        // Recursive relation:
        // Option 1: Rob the current house (nums[houseCount - 1]) and skip one house (houseCount - 2)
        int robHouse = nums[houseCount - 1] + robHelper(nums, houseCount - 2);

        // Option 2: Skip the current house and move to the next (houseCount - 1)
        int dontRobHouse = robHelper(nums, houseCount - 1);

        // Return the maximum value of the two options
        return Math.max(robHouse, dontRobHouse);
    }

    // Example to test the function
    public static void main(String[] args) {
        HouseRobber houseRobber = new HouseRobber();
        int[] nums = {1, 2, 3, 1};
        System.out.println(houseRobber.rob(nums));  // Output: 4
    }
}
```

##### Time Complexity:
- **O(2^n)**: Each house generates two recursive calls, leading to exponential time complexity.

##### Space Complexity:
- **O(n)**: The depth of recursion can go up to `n` (the total number of houses), requiring linear space.

##### Algorithm:
- **Step 1:** If no houses are left (`houseCount == 0`), return `0`.
- **Step 2:** If only one house is left (`houseCount == 1`), return its value (`nums[houseCount - 1]`).
- **Step 3:** Calculate the two choices:
  - **Choice 1:** Rob the current house (`nums[houseCount - 1]`) and skip the previous house.
  - **Choice 2:** Skip the current house and check the next.
- **Step 4:** Return the maximum of these two choices.
***


#### Min Cost Climbing Stairs
##### Pattern: Dynamic Programming (Recursion)
[Back to Top](#table-of-contents)

##### Description:
- **Input**: Array of integers `cost[]` where `cost[i]` is the cost of step `i`.
- **Output**: Minimum cost to reach the top of the staircase.
- **Explanation**: You can start from step 0 or step 1. At each step, you can take 1 or 2 steps forward. The goal is to minimize the total cost to reach the top.

Example:
```
Input: cost = [10, 15, 20]
Output: 15
Explanation: The minimum cost path is to start at step 1 (cost 15), then directly jump to the top.
```

```java
public class MinCostClimbingStairs {
    // Main function to calculate the minimum cost to climb the stairs
    public int minCostClimbingStairs(int[] cost) {
        int totalSteps = cost.length; // Total number of steps
        // Start by calling the helper function with totalSteps (not totalSteps - 1)
        return minCostHelper(cost, totalSteps);
    }

    // Helper function to recursively calculate the minimum cost for the current step
    public int minCostHelper(int[] cost, int totalSteps) {
        // Base condition 1: If there are no steps, no cost
        if (totalSteps == 0) return 0;
        
        // Base condition 2: If there's only 1 step, no cost to start
        if (totalSteps == 1) return 0;
        
        // Base condition 3: When we're on step 2, return the minimum cost of starting from step 0 or 1
        if (totalSteps == 2) return Math.min(cost[0], cost[1]);

        // Recursive case: Calculate the cost for taking one step or two steps
        // Take one step from totalSteps-1 and add the cost
        int takeOneStep = cost[totalSteps - 1] + minCostHelper(cost, totalSteps - 1);
        
        // Take two steps from totalSteps-2 and add the cost
        int takeTwoStep = cost[totalSteps - 2] + minCostHelper(cost, totalSteps - 2);

        // Return the minimum of the two choices
        return Math.min(takeOneStep, takeTwoStep);
    }

    // Test the function
    public static void main(String[] args) {
        MinCostClimbingStairs obj = new MinCostClimbingStairs();
        int[] cost = {10, 15, 20};
        System.out.println(obj.minCostClimbingStairs(cost)); // Output: 15
    }
}
```

##### Time Complexity:
- **O(2^n)**: Each recursive call spawns two more calls, resulting in exponential time complexity.

##### Space Complexity:
- **O(n)**: The recursive call stack depth is proportional to the number of steps, i.e., `n`.

##### Algorithm:
- **Base Case**:
  - If there are 0 steps, no cost is incurred.
  - If there is only 1 step, no cost is incurred to start.
  - If there are 2 steps, the minimum cost is either starting at step 0 or step 1.

- **Recursive Case**:
  - Calculate the cost if you take 1 step from `totalSteps-1`.
  - Calculate the cost if you take 2 steps from `totalSteps-2`.

- **Return**:
  - Return the minimum of the two calculated costs.
***



#### Fibonacci Number
##### Pattern: Recursion
[Back to Top](#table-of-contents)
##### Description:
- **Input**: `n = 5`
- **Output**: `5`
- **Explanation**: The Fibonacci sequence is: 0, 1, 1, 2, 3, 5. The 5th Fibonacci number is 5.

```java
// Recursive solution to find the nth Fibonacci number
public class Fibonacci {

    // Function to calculate nth Fibonacci number
    public static int fib(int n) {
        // Base case 1: If n is 0, return 0
        if (n == 0) {
            return 0;
        }
        // Base case 2: If n is 1, return 1
        if (n == 1) {
            return 1;
        }
        // Recursive case: Fib(n) = Fib(n-1) + Fib(n-2)
        return fib(n - 1) + fib(n - 2);
    }

    // Main function to test the Fibonacci function
    public static void main(String[] args) {
        int n = 5; // Example input
        System.out.println("Fibonacci number at position " + n + " is: " + fib(n));
    }
}
```

##### Time Complexity:
- The time complexity is **O(2^n)** because each recursive call splits into two more calls.

##### Space Complexity:
- The space complexity is **O(n)** due to the recursion stack depth.

##### Algorithm:
- Start with base cases:
  - If `n == 0`, return 0.
  - If `n == 1`, return 1.
- Recursively compute Fibonacci for `(n-1)` and `(n-2)`.
- Add the results of `(n-1)` and `(n-2)` to get Fibonacci of `n`.
***

#### Best Time to Buy and Sell Stock
##### Pattern: Greedy
[Back to Top](#table-of-contents)
##### Description:
- **Input**: `[7, 1, 5, 3, 6, 4]`
- **Output**: `5`
- **Explanation**: Buy at price `1` (on day 2) and sell at price `6` (on day 5), profit = `6 - 1 = 5`.

```java
public class BestTimeToBuyAndSellStock {

    public int maxProfit(int[] prices) {

        // If there are less than 2 prices, no transactions can be made
        if (prices == null || prices.length < 2)
            return 0;

        // Initialize lowest price to the first element and max profit to 0
        int lowestPrice = prices[0];
        int maxProfit = 0;

        // Loop through the prices starting from the second day (index 1)
        for (int i = 1; i < prices.length; i++) {

            // If the current price is lower than the lowest recorded price, update lowestPrice
            if (prices[i] < lowestPrice) {
                lowestPrice = prices[i]; 
            }

            // Calculate the profit if sold today and update maxProfit if it's higher than previous
            maxProfit = Math.max(maxProfit, prices[i] - lowestPrice);
        }

        // Return the maximum profit that can be achieved
        return maxProfit;
    }

    public static void main(String[] args) {
        // Example usage
        BestTimeToBuyAndSellStock solution = new BestTimeToBuyAndSellStock();
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println("Maximum Profit: " + solution.maxProfit(prices));  // Output: 5
    }
}
```

##### Time Complexity:
- **O(n)**, where `n` is the number of prices. We traverse the array once, so the time complexity is linear.

##### Space Complexity:
- **O(1)**, since we are using only a few extra variables (`lowestPrice` and `maxProfit`) regardless of the input size.

##### Algorithm:
- **Step 1**: Start by initializing `lowestPrice` to the first day's price and `maxProfit` to 0.
- **Step 2**: For each subsequent day:
  - If the current price is less than the `lowestPrice`, update `lowestPrice`.
  - Calculate profit by subtracting the `lowestPrice` from the current price and update `maxProfit` if this profit is higher.
- **Step 3**: Continue the process for all days and return the maximum profit found.

This method efficiently solves the problem by using a **greedy approach**, always keeping track of the lowest price seen so far and calculating the maximum profit at each step.

***

#### Maximum Subarray
##### Pattern: Dynamic Programming
[Back to Top](#table-of-contents)
##### Description:
- Input: `[−2,1,−3,4,−1,2,1,−5,4]`
- Output: `6`
- Explanation: The contiguous subarray `[4,−1,2,1]` has the largest sum, which is `6`.

```java
public class MaximumSubarray {
    public static int maxSubArray(int[] nums) {
        // Initialize current max and global max with the first element
        int currentMax = nums[0];
        int globalMax = nums[0];

        // Iterate through the array starting from the second element
        for (int i = 1; i < nums.length; i++) {
            // Update current max to be the maximum of current element or 
            // current element + current max
            currentMax = Math.max(nums[i], currentMax + nums[i]);
            // Update global max if current max is greater
            globalMax = Math.max(globalMax, currentMax);
        }

        return globalMax; // Return the largest sum found
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Maximum Subarray Sum: " + maxSubArray(nums)); // Output: 6
    }
}
```
##### Time Complexity:
- O(n), where n is the number of elements in the array; we traverse the array once.

##### Space Complexity:
- O(1), since we use a constant amount of space for storing variables.

##### Algorithm
- Initialize `currentMax` and `globalMax` with the first element of the array.
- Iterate through the array from the second element:
  - Update `currentMax` to be the maximum of the current element or the sum of the current element and `currentMax`.
  - Update `globalMax` if `currentMax` is greater than `globalMax`.
- Return `globalMax` as the result.
***



Here’s a detailed breakdown of the issues in your current solution and how to fix them:

#### Coin Change Minimum Number of Coins
##### Pattern: Dynamic Programming (Recursion)
[Back to Top](#table-of-contents)

##### Description:
- **Input**: An integer array `coins[]` representing available coin denominations and an integer `amount` representing the target value.
  - Example: `coins = [1, 2, 5]`, `amount = 11`
- **Output**: Minimum number of coins required to make up the amount.
  - Example: `Output = 3` (5 + 5 + 1)
- **Explanation**: Use recursive calls to explore all combinations of coins to form the target amount.

```java
class Solution {
    public int coinChange(int[] coins, int amount) {
        // Edge case: If the amount is 0, no coins are needed
        if (amount == 0) return 0;

        // Call the recursive helper function
        int result = coinHelper(coins, amount, 0);

        // If the result is too large, return -1 (indicating no valid solution)
        return result >= Integer.MAX_VALUE ? -1 : result;
    }

    int coinHelper(int[] coins, int amount, int index) {
        // Base case: If amount becomes 0, no more coins are needed
        if (amount == 0) return 0;

        // If we run out of coins (index exceeds coins array) or the amount is negative, return a large value
        if (index == coins.length || amount < 0) {
            return Integer.MAX_VALUE;  // Large value indicates no solution
        }

        // Recursive Case 1: Take the current coin
        int takeCoin = coinHelper(coins, amount - coins[index], index);  // Stay at the same coin
        if (takeCoin != Integer.MAX_VALUE) {
            takeCoin += 1;  // Add 1 to the result for taking this coin
        }

        // Recursive Case 2: Don't take the current coin, move to the next coin
        int dontTakeCoin = coinHelper(coins, amount, index + 1);

        // Return the minimum of both choices
        return Math.min(takeCoin, dontTakeCoin);
    }
}
```

##### Time Complexity:
- **O(2^n)**: Each recursive call branches into multiple subproblems, leading to exponential growth.

##### Space Complexity:
- **O(amount)**: The depth of the recursive call stack is proportional to the amount.

##### Algorithm:
- If `amount == 0`, return 0 as no more coins are needed.
- If `amount < 0`, return -1 because it's impossible to achieve the negative amount.
- For each coin:
  - Subtract the coin's value from `amount` and make a recursive call with the new amount.
  - If the recursive call returns a valid number, update the minimum number of coins required.
- Return the minimum number of coins found, or -1 if no solution is possible.

***



#### Coin Change II - Total Number of Ways to Get a Value
##### Pattern: Dynamic Programming (Recursive Approach)
[Back to Top](#table-of-contents)
##### Description:
- Input: `amount = 5`, `coins = [1, 2, 5]`
- Output: `4`
- Explanation: There are four ways to make the amount 5 using the given coins:
    1. 1+1+1+1+1
    2. 1+1+1+2
    3. 1+2+2
    4. 5

```java
class Solution {
    
    // Main function that initiates the recursive helper
    public int change(int amount, int[] coins) {
        int index = 0; // Start from the first coin
        return changeCountHelper(amount, index, coins); // Call helper function
    }

    // Recursive helper function
    public int changeCountHelper(int amount, int index, int[] coins) {
        
        // Base Case 1: If amount becomes 0, there's exactly one way to form it (no coins)
        if (amount == 0)
            return 1;

        // Base Case 2: If all coins are used or amount is negative, no valid solution
        if (index == coins.length || amount < 0)
            return 0;

        // Option 1: Include the current coin (coins[index]) and reduce the amount
        int takeCoin = changeCountHelper(amount - coins[index], index, coins);

        // Option 2: Exclude the current coin and move to the next coin
        int dontTakeCoin = changeCountHelper(amount, index + 1, coins);

        // Total ways is sum of both options
        return takeCoin + dontTakeCoin;
    }
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] coins = {1, 2, 5};
        int amount = 5;
        System.out.println("Number of ways to get the amount: " + solution.change(amount, coins));  // Output: 4
    }
}
```

##### Time Complexity:
- **O(2^n)**: The time complexity is exponential since each recursive call branches into two possibilities (taking or not taking the coin), leading to a total of `2^n` recursive calls.

##### Space Complexity:
- **O(amount)**: The space complexity depends on the recursion depth, which is proportional to the amount being reduced. Each recursive call reduces the amount.

##### Algorithm:
- Start from the first coin (`index = 0`) and the full amount.
- **Base Case 1**: If `amount == 0`, return 1 (this means a valid combination is found).
- **Base Case 2**: If `amount < 0` or `index == coins.length`, return 0 (no valid combination).
- **Recursive Case**:
  - **Take the current coin**: Reduce the amount by the coin value and make another recursive call without changing the index (same coin can be used again).
  - **Skip the current coin**: Move to the next coin by incrementing the index.
- **Pointers**: `index` is updated to move to the next coin, and `amount` is reduced when a coin is taken.
***


### Intermediate
LevelLongest Increasing Subsequence - Link
Unique Paths - Link
Word Break - Link
Jump Game - Link
Partition Equal Subset Sum - Link
Longest Palindromic Substring - Link
Decode Ways - Link
Advanced Level