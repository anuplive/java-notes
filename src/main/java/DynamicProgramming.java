import java.util.TreeMap;
public class DynamicProgramming {
    public static void main(String args[]){
    }


    public static boolean targetSum(int[] nums, int targetSum){

        boolean[][] memo = new boolean[nums.length +1 ][targetSum];

        return targetSumHelper(nums, nums.length, targetSum, memo);

    }

    public static boolean targetSumHelper(int[] nums, int N, int targetSum, boolean[][] memo) {

        if (targetSum == 0)
            return true;
        if (N == 0)
            return false;

        if (nums[N - 1] <= targetSum) {
            memo[N][targetSum] = targetSumHelper(nums, N - 1, targetSum - nums[N - 1], memo);
            return memo[N][targetSum];

        } else {
            return memo[N][targetSum] = targetSumHelper(nums, N - 1, targetSum, memo);
        }
    }


    // KnapSack problem 0/1
    // Limited number of items we can select it only once
    // Capacity we cannot exceed it
    // Boundary conditions : When we run out of elements or when the capacity is reached.



    public static int knapSack (int[] weights, int [] values, int capacity){

        int N = weights.length;
        return knapSackHelper(weights, values, capacity , N);
    }

    public static int knapSackHelper(int[] weights, int[] values, int capacity, int N){

        if (N == 0 || capacity == 0)
            return 0; // Max value in case of no elements


        if (weights[N-1] > capacity){
            return knapSackHelper(weights, values, capacity, N-1);
        }

        return Math.max(values[N -1 ] + knapSackHelper(weights, values, capacity -weights[N-1], N-1), knapSackHelper(weights, values, capacity, N-1));
    }


}
// TC O(2^N)
// SC O(N)

