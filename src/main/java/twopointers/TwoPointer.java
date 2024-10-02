package twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoPointer {

    public static void main(String args[]) {

        // Test Sorted Squares
     //   Arrays.stream(sortedSquares(new int[]{-88, 20, 50})).forEach(x -> System.out.print(x + ", "));
        Arrays.stream(productArray(new int []{1,2,3,4,5})).forEach(a -> System.out.print(a + ","));
    }

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

    // Reverse a String
    public void reverseString(char[] s) {
        if (s == null || s.length < 1)
            return;
        int left = 0;
        int right = s.length - 1;
        while (left <= right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    // Palindromic String
    public boolean isPalindrome(String s) {
        if (s == null || s.length() < 1)
            return false;


        int left = 0, right = s.length() - 1;

        while (left <= right) {
            while (!Character.isLetterOrDigit(s.charAt(left)))
                left++;
            while (!Character.isLetterOrDigit(s.charAt(right)))
                right--;
            if (s.charAt(left) != s.charAt(right))
                return false;
            left++;
            right--;
        }

        return true;

    }

    // 3sum
    public static  List<List<Integer>> threeSum(int[] nums) {
            // Sort the array to facilitate two-pointer approach
            Arrays.sort(nums);
            List<List<Integer>> result = new ArrayList<>();

            // Traverse each element in the array
            for (int i = 0; i < nums.length - 2; i++) {
                // Skip duplicates for the first number
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                // Initialize two pointers
                int left = i + 1, right = nums.length - 1;
                int target = -nums[i]; // We need two numbers that sum up to -nums[i]

                // Explore the array with the two pointers
                while (left < right) {
                    int sum = nums[left] + nums[right];

                    if (sum == target) {
                        // If the sum matches the target, add the triplet to the result
                        result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                        // Move pointers and skip duplicates
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;

                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;  // Move left pointer to increase sum
                    } else {
                        right--; // Move right pointer to decrease sum
                    }
                }
            }
            return result;
        }

    // Container with most water
    public int maxArea(int[] height) {

        if (height == null || height.length < 2)
            return 0 ;
        int maxArea = Integer.MIN_VALUE;

        int left = 0, right = height.length -1 ;

        while (right > left ){

            int leftHeight = height[left];
            int rightHeight = height[right];
            int minHeight = Math.min(leftHeight, rightHeight);
            int breadth = right - left;
            int currentArea = minHeight * breadth;
            maxArea = Math.max(maxArea, minHeight * currentArea);

            if (leftHeight <= rightHeight)
                left--;
            else
                right++;
        }
        return maxArea;
    }

    // Product except itself

    public static int[] productArray(int[] nums){

        if (nums == null || nums.length == 0)
            return new int[]{} ;

        int[] result = new int[nums.length];
        int leftAcc = 1;
        int rightAcc = 1;

        // Left scan
        for (int i = 0 ; i < nums.length ; i ++){

            result[i] = leftAcc;
            leftAcc *= nums[i];
        }

        // Right Scan
        for (int i = nums.length - 1 ; i >=  0 ; i --){
            result[i] *= rightAcc;
            rightAcc *= nums[i];
        }


        return result;
    }


}