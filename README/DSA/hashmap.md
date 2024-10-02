# HashMap and HashSets
[HOME.md](HOME.md)
=================
## Table of contents

<!--ts-->
|                                                               |    |    |    |    |
|:--------------------------------------------------------------|:---|:---|:---|:---|
| [Jewels and Stones](#jewels-and-stones)                       |    |    |    |    |
| [Contains Duplicate](#contains-duplicate)                     |    |    |    |    |
| [Solve Ransom Note](#solve-ransom-note)                       |    |    |    |    |
| [Valid Anagram](#valid-anagram)                               |    |    |    |    |
| [Maximum Number of Balloons](#maximum-number-of-balloons)     |    |    |    |    |
| [Two Sum](#two-sum)                                           |    |    |    |    |
| [Valid Sudoku](#valid-sudoku)                                 |    |    |    |    |
| [Group Anagram](#group-anagrams)                              |    |    |    |    |
| [Group Anagram No Sorting](#group-anagrams-no-sorting)        |    |    |    |    |
| [Majority Element](#majority-element)                         |    |    |    |    |
| [Longest Consecutive Sequence](#longest-consecutive-sequence) |    |    |    |    |


***
#### Jewels and Stones
##### Pattern: Hashing
[Back to Top](#table-of-contents)

##### Description:
- **Input:**  
  Jewels: "aA", Stones: "aAAbbbb"
- **Output:**  
  `3`
- **Explanation:**  
  There are 3 jewels ('a', 'A', 'A') found in the stone collection. We need to count how many stones are jewels by comparing the characters.

```java
import java.util.HashSet;

public class JewelsAndStones {

    // Function to count the number of jewels in stones
    public static int numJewelsInStones(String jewels, String stones) {
        // Step 1: Store jewels in a HashSet for O(1) lookups
        HashSet<Character> jewelSet = new HashSet<>();
        
        // Adding all characters of jewels into the set
        for (char jewel : jewels.toCharArray()) {
            jewelSet.add(jewel);
        }
        
        // Step 2: Count the stones that are also jewels
        int count = 0;
        for (char stone : stones.toCharArray()) {
            if (jewelSet.contains(stone)) { // If stone is a jewel, increment count
                count++;
            }
        }
        
        return count; // Return the total number of jewels found in stones
    }

    public static void main(String[] args) {
        // Example input
        String jewels = "aA";
        String stones = "aAAbbbb";
        
        // Output the result
        System.out.println(numJewelsInStones(jewels, stones)); // Expected output: 3
    }
}
```

##### Time Complexity:
- **O(m + n)**:
  - `m` is the length of jewels.
  - `n` is the length of stones.
  - The time complexity is linear due to looping over both strings.

##### Space Complexity:
- **O(m)**:
  - The space used by the HashSet to store the unique jewels is proportional to the number of characters in the `jewels` string.

***

#### Contains Duplicate
##### Pattern: Hashing
[Back to Top](#table-of-contents)

##### Description:
- **Input:** `[1, 2, 3, 1]`
- **Output:** `true`
- **Explanation:** The array contains a duplicate value (1).

```java
import java.util.HashSet;

public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        // Initialize a HashSet to store unique numbers
        HashSet<Integer> seen = new HashSet<>();
        
        // Traverse each number in the input array
        for (int num : nums) {
            // If the number already exists in the set, we found a duplicate
            if (seen.contains(num)) {
                return true;
            }
            // Otherwise, add the number to the set
            seen.add(num);
        }
        
        // If no duplicates were found, return false
        return false;
    }
}
```

##### Time Complexity:
- **O(n)**: We iterate through the array once, and checking/inserting into a HashSet is O(1) on average.

##### Space Complexity:
- **O(n)**: In the worst case, all elements are unique, so the HashSet will contain all `n` elements.
***


#### Solve Ransom Note
##### Pattern: Hashing
[Back to Top](#table-of-contents)

##### Description:
- Input: `ransomNote = "a", magazine = "b"`
- Output: `false`
- Explanation: The letter "a" is not present in the magazine "b", so the ransom note cannot be formed.

- Input: `ransomNote = "aa", magazine = "aab"`
- Output: `true`
- Explanation: The magazine "aab" contains enough letters to form the ransom note "aa".

```java
import java.util.HashMap;

public class RansomNote {
    public boolean canConstruct(String ransomNote, String magazine) {
        // Create a hashmap to store character counts from the magazine
        HashMap<Character, Integer> charCount = new HashMap<>();
        
        // Count frequency of each character in the magazine
        for (char c : magazine.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        // Check if ransomNote can be constructed
        for (char c : ransomNote.toCharArray()) {
            // If character is not present or used up, return false
            if (!charCount.containsKey(c) || charCount.get(c) == 0) {
                return false;
            }
            // Decrease the count of the character
            charCount.put(c, charCount.get(c) - 1);
        }

        // If we can construct the ransom note, return true
        return true;
    }

    public static void main(String[] args) {
        RansomNote ransomNote = new RansomNote();
        System.out.println(ransomNote.canConstruct("a", "b")); // false
        System.out.println(ransomNote.canConstruct("aa", "aab")); // true
    }
}
```

##### Time Complexity:
- **O(n + m)**, where `n` is the length of the ransom note and `m` is the length of the magazine. We traverse both strings once.

##### Space Complexity:
- **O(m)**, as we use a HashMap to store the character counts from the magazine.
***




#### Valid Anagram
##### Pattern: Hashing
[Back to Top](#table-of-contents)
##### Description:
- **Input**: `s = "anagram"`, `t = "nagaram"`
- **Output**: `true`
- **Explanation**: The string `t` is a valid rearrangement (anagram) of string `s`, as both contain the same characters with the same frequency.

```java
import java.util.HashMap;

public class ValidAnagram {

    // Function to check if two strings are anagrams
    public static boolean isAnagram(String s, String t) {
        // If lengths are not the same, they can't be anagrams
        if (s.length() != t.length()) {
            return false;
        }

        // Create a HashMap to count character frequencies
        HashMap<Character, Integer> charCount = new HashMap<>();

        // Iterate over the first string and populate the HashMap with character counts
        for (char c : s.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        // Iterate over the second string and reduce the character count
        for (char c : t.toCharArray()) {
            if (!charCount.containsKey(c) || charCount.get(c) == 0) {
                return false; // If character is missing or count mismatches
            }
            charCount.put(c, charCount.get(c) - 1);
        }

        // If all character counts match, return true
        return true;
    }

    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        System.out.println(isAnagram(s, t)); // true
    }
}
```

##### Time Complexity:
- **O(n)** where `n` is the length of the string. We iterate through both strings once.
##### Space Complexity:
- **O(1)** because the HashMap stores at most 26 characters, which is constant space.

#### Maximum Number of Balloons
##### Pattern: Hashing
[Back to Top](#table-of-contents)
##### Description:
- Input: `"loonbalxballpoon"`
- Output: `2`
- Explanation: We can form two instances of the word "balloon" from the input string.

```java
import java.util.HashMap;

public class MaximumBalloons {
    
    public static int maxNumberOfBalloons(String text) {
        // Required character frequencies for the word "balloon"
        HashMap<Character, Integer> required = new HashMap<>();
        required.put('b', 1);
        required.put('a', 1);
        required.put('l', 2);
        required.put('o', 2);
        required.put('n', 1);
        
        // Frequency count of characters in the input text
        HashMap<Character, Integer> freq = new HashMap<>();
        for (char c : text.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        // Find the maximum number of "balloon" words we can form
        int min = Integer.MAX_VALUE;
        for (char c : required.keySet()) {
            // Calculate how many times we can use the character
            min = Math.min(min, freq.getOrDefault(c, 0) / required.get(c));
        }

        return min; // Return the number of "balloon" words we can form
    }

    public static void main(String[] args) {
        // Test the function with a sample input
        String input = "loonbalxballpoon";
        System.out.println("Maximum number of 'balloon' words: " + maxNumberOfBalloons(input)); 
        // Output: 2
    }
}
```
##### Time Complexity:
- **O(n)**: We iterate through the string once to calculate frequencies and check the required characters.

##### Space Complexity:
- **O(1)**: The size of the `HashMap` is constant because we are only storing frequencies for 26 letters.
***



#### Two Sum
##### Pattern: Hashing
[Back to Top](#table-of-contents)
##### Description:
- Input: `nums = [2, 7, 11, 15], target = 9`
- Output: `[0, 1]`
- Explanation: The numbers at indices `0` and `1` add up to `9` (i.e., `2 + 7 = 9`).

```java
// Java solution for the Two Sum problem

import java.util.HashMap;

public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        // HashMap to store the number and its index
        HashMap<Integer, Integer> map = new HashMap<>();
        
        // Traverse the array
        for (int i = 0; i < nums.length; i++) {
            // Calculate the complement
            int complement = target - nums[i];
            
            // Check if the complement exists in the map
            if (map.containsKey(complement)) {
                // Return indices of the two numbers
                return new int[] { map.get(complement), i };
            }
            
            // Add the current number to the map with its index
            map.put(nums[i], i);
        }
        
        // Return an empty array if no solution is found
        return new int[] {};
    }
}
```
##### Time Complexity:
- O(n), where `n` is the number of elements in the array. We traverse the array only once, and HashMap operations (put and get) take O(1) time.

##### Space Complexity:
- O(n), where `n` is the number of elements in the array. We use extra space to store the numbers and their indices in the HashMap.
***


#### Valid Sudoku
##### Pattern: Hashing
[Back to Top](#table-of-contents)

##### Description:
- **Input:** A 9x9 Sudoku board filled with digits (1-9) or '.' (for empty cells).
- **Output:** `true` if the board is a valid Sudoku configuration, otherwise `false`.
- **Explanation:** A valid Sudoku board must satisfy three conditions:
  1. Each row must contain the digits 1-9 without repetition.
  2. Each column must contain the digits 1-9 without repetition.
  3. Each of the nine 3x3 sub-boxes must contain the digits 1-9 without repetition.

```java
public class ValidSudoku {

    public boolean isValidSudoku(char[][] board) {
        // Initialize hash sets to keep track of seen numbers
        Set<String> seen = new HashSet<>();
        
        // Traverse each cell of the board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char current_val = board[i][j];
                
                // If the cell is not empty ('.'), validate the constraints
                if (current_val != '.') {
                    // Check for the same number in the current row
                    if (!seen.add(current_val + " found in row " + i)) {
                        return false;
                    }
                    
                    // Check for the same number in the current column
                    if (!seen.add(current_val + " found in column " + j)) {
                        return false;
                    }
                    
                    // Check for the same number in the current 3x3 sub-box
                    if (!seen.add(current_val + " found in box " + i/3 + "-" + j/3)) {
                        return false;
                    }
                }
            }
        }
        return true; // If all checks are passed, the board is valid
    }
}
```

##### Time Complexity:
- **O(1)** since the board is fixed at 9x9. Every cell is checked exactly once.
##### Space Complexity:
- **O(1)** for the same reason: the number of cells (81) remains constant, and the size of the hash set grows with this fixed limit.
***


#### Group Anagrams
##### Pattern: Hashing/Sorting
[Back to Top](#table-of-contents)
##### Description:
- **Input**: `["eat", "tea", "tan", "ate", "nat", "bat"]`
- **Output**: `[["eat", "tea", "ate"], ["tan", "nat"], ["bat"]]`
- **Explanation**: Words are grouped based on anagram similarity. Anagrams have the same sorted characters.

```java
import java.util.*;

public class GroupAnagrams {
    public static List<List<String>> groupAnagrams(String[] strs) {
        // Create a map to store grouped anagrams
        Map<String, List<String>> anagramMap = new HashMap<>();
        
        // Loop through each string in the input array
        for (String str : strs) {
            // Convert the string to a character array, sort it, and convert it back to a string
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String sortedStr = new String(charArray);
            
            // If the sorted string is not already a key in the map, create a new list for it
            anagramMap.putIfAbsent(sortedStr, new ArrayList<>());
            
            // Add the original string to the corresponding anagram list
            anagramMap.get(sortedStr).add(str);
        }
        
        // Return the list of anagram groups
        return new ArrayList<>(anagramMap.values());
    }
}
```

##### Time Complexity:
- Sorting each string takes `O(N * K log K)`, where `N` is the number of strings and `K` is the average string length.
- Total: `O(N * K log K)`.

##### Space Complexity:
- The space complexity is `O(N * K)` due to storing strings in the map.

***




#### Group Anagrams (No Sorting)
##### Pattern: Hashing
[Back to Top](#table-of-contents)
##### Description:
- **Input**: `["eat", "tea", "tan", "ate", "nat", "bat"]`
- **Output**: `[["eat", "tea", "ate"], ["tan", "nat"], ["bat"]]`
- **Explanation**: Words are grouped by counting the frequency of characters instead of sorting.

```java
import java.util.*;

public class GroupAnagramsWithoutSorting {
    public static List<List<String>> groupAnagrams(String[] strs) {
        // Map to group anagrams using character frequency as the key
        Map<String, List<String>> anagramMap = new HashMap<>();

        for (String str : strs) {
            // Create a frequency array for 26 lowercase English letters
            int[] charCount = new int[26];
            
            // Count the frequency of each character in the string
            for (char c : str.toCharArray()) {
                charCount[c - 'a']++;
            }
            
            // Convert frequency array to a string to use as the map key
            StringBuilder keyBuilder = new StringBuilder();
            for (int count : charCount) {
                keyBuilder.append("#").append(count);
            }
            String key = keyBuilder.toString();
            
            // If key doesn't exist, create a new list in the map
            anagramMap.putIfAbsent(key, new ArrayList<>());
            
            // Add the original string to the list associated with this key
            anagramMap.get(key).add(str);
        }
        
        // Return the grouped anagrams
        return new ArrayList<>(anagramMap.values());
    }
}
```

##### Time Complexity:
- We iterate through each string and compute the frequency array in `O(N * K)` where `N` is the number of strings and `K` is the average string length.
- Total: `O(N * K)`.

##### Space Complexity:
- `O(N * K)` due to storing the strings and frequency arrays.

***

#### Majority Element
##### Pattern: Boyer-Moore Voting Algorithm
[Back to Top](#table-of-contents)

##### Description:
- Input: `[2, 2, 1, 1, 1, 2, 2]`
- Output: `2`
- Explanation: The number `2` appears more than half of the time in the input array, making it the majority element.

```java
public class MajorityElement {
    public static int majorityElement(int[] nums) {
        int candidate = nums[0]; // Step 1: Initialize candidate
        int count = 1; // Step 2: Initialize count
        
        // Step 3: Find potential majority element
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == candidate) {
                count++; // Increment count if current element matches candidate
            } else {
                count--; // Decrement count if current element differs
                if (count == 0) {
                    candidate = nums[i]; // Update candidate when count is zero
                    count = 1; // Reset count for the new candidate
                }
            }
        }
        
        return candidate; // Step 4: Return the candidate
    }
}
```

##### Time Complexity:
- O(n): The algorithm traverses the array once.

##### Space Complexity:
- O(1): Only a constant amount of space is used for the candidate and count variables.
***



#### Longest Consecutive Sequence
##### Pattern: Sorting / Hashing
[Back to Top](#table-of-contents)
##### Description:
- Input: `[100, 4, 200, 1, 3, 2]`
- Output: `4`
- Explanation: The longest consecutive sequence is `[1, 2, 3, 4]`, which has a length of 4.

```java
import java.util.HashSet;

public class LongestConsecutiveSequence {

    // Function to find the length of the longest consecutive sequence
    public static int longestConsecutive(int[] nums) {
        // Edge case: if the input array is empty
        if (nums.length == 0) return 0;

        // Use a HashSet to store all the numbers for quick lookup
        HashSet<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);  // Add each number to the set
        }

        int longestStreak = 0;

        // Loop through each number in the array
        for (int num : nums) {
            // Check if the current number is the start of a sequence
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                // Continue to find the consecutive sequence
                while (numSet.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                // Update the longest streak if the current one is longer
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }

    // Example usage
    public static void main(String[] args) {
        int[] nums = {100, 4, 200, 1, 3, 2};
        System.out.println("Longest consecutive sequence length: " + longestConsecutive(nums));
    }
}
```
##### Time Complexity:
- The time complexity is **O(n)** where n is the number of elements in the array. This is because we process each element once in the hash set lookup, which takes constant time.

##### Space Complexity:
- The space complexity is **O(n)**, as we are storing all the elements in a hash set.
***