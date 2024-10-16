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

        if (memo[N][targetSum] != null) {
            return memo[N][targetSum];
        }

        if (nums[N - 1] <= targetSum) {
            memo[N][targetSum] = targetSumHelper(nums, N - 1, targetSum - nums[N - 1], memo);
            return memo[N][targetSum];

        } else {
            return memo[N][targetSum] = targetSumHelper(nums, N - 1, targetSum, memo);
        }
    }
  }


