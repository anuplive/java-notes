# BinaryTree
[HOME.md](HOME.md)
=================
### Table of contents

<!--ts-->
| Category                                                        | Problems |    |    |    |    |    |    |    |
|:----------------------------------------------------------------|:---------|:---|:---|:---|:---|:---|:---|:---|
| [Binary Tree Theory](#binary-tree-theory)                       |          |    |    |    |    |    |    |    |
| [Tree DFS](#tree-dfs)                                           |          |    |    |    |    |    |    |    |
| [All Traversals](#all-traversals)                               |          |    |    |    |    |    |    |    |
| [Pre-ORDER  ( N - left - right)](#pre-order---n---left---right) |          |    |    |    |    |    |    |    |
| [IN-ORDER TRAVERS](#in-order-traversal-left---node---right)     |          |    |    |    |    |    |    |    |
| [POST-ORDER](#post-order-left---right---node)                   |          |    |    |    |    |    |    |    |
| [Validate Binary Search Tree](#validate-binary-search-tree)     |          |    |    |    |    |    |    |    |
| [Lowest Common Ancestor](#lowest-common-ancestor)               |          |    |    |    |    |    |    |    |
| [BTree Level Order](#btree-level-order)                         |          |    |    |    |    |    |    |    |
| [BTree Zigzag](#btree-zigzag)                                   |          |    |    |    |    |    |    |    |
| [BTree Vertical Order](#btree-vertical-order)                   |          |    |    |    |    |    |    |    |
| [BTree Average of Levels](#btree-average-of-levels)             |          |    |    |    |    |    |    |    |
| [BTree Right Side View](#btree-right-side-view)                 |          |    |    |    |    |    |    |    |
| [BTree Cousins](#btree-cousins)                                 |          |    |    |    |    |    |    |    |
| [BTree Next Right Pointers](#btree-next-right-pointers)         |          |    |    |    |    |    |    |    |
| [BTree Check Symmetric](#btree-check-symmetric)                 |          |    |    |    |    |    |    |    |


#### Binary Tree Theory

##### Types of Binary Trees
- Full BT: Every node has 0 or 2 children.
- Complete BT: All levels are fully filled except possibly the last level.
- Perfect BT: All internal nodes have two children and all leaves are at the same level.
- Balanced BT: Heights of subtrees differ by no more than 1.
- Skewed BT: All nodes have only one child, creating a linear tree structure.

**Full Binary Tree**
   - **Definition:** A binary tree in which every node has either 0 or 2 children.
   - **Diagram:**
     ```
         1
        / \
       2   3
      / \
     4   5
     ```

 **Complete Binary Tree**
   - **Definition:** A binary tree in which all levels are completely filled except possibly the last level, and the last level has all keys as left as possible.
   - **Diagram:**
     ```
         1
        / \
       2   3
      / \ /
     4  5 6
     ```

 **Perfect Binary Tree**
   - **Definition:** A binary tree in which all internal nodes have two children and all leaf nodes are at the same level.
   - **Diagram:**
     ```
         1
        / \
       2   3
      / \ / \
     4  5 6  7
     ```

 **Binary Search Tree (BST)**
   - **Definition:** A binary tree in which for every node, the value of all the nodes in the left subtree is less or equal to the node's value, and the value of all the nodes in the right subtree is greater than the node's value.
   - **Diagram:**
     ```
         4
        / \
       2   6
      / \ / \
     1  3 5  7
     ```

##### Tree Measurements

 **Height of Binary Tree**
   - **Definition:** The number of edges in the longest path from the root to a leaf node. Sometimes, the number of nodes in the longest path is used.
   - **Example:** In the following tree, height = 2
     ```
         1
        / \
       2   3
      /
     4
     ```

 **Depth of Binary Tree**
   - **Definition:** The number of edges from the root node to the given node. The depth of the root node is 0.
   - **Example:** Depth of node 4 is 2
     ```
         1
        / \
       2   3
      /
     4
     ```
   - **Note:** Height and depth are related but not the same. Height is measured from the root to the farthest leaf, while depth is measured from the root to a particular node.

 **Width of Binary Tree**
   - **Definition:** The maximum number of nodes present in any level of the tree.
   - **Example:** Width = 4 (level 2 has the maximum nodes)
     ```
         1
        / \
       2   3
      / \ / \
     4  5 6  7
     ```

 **Diameter of Binary Tree**
   - **Definition:** The number of nodes on the longest path between any two nodes in the tree. This path may or may not pass through the root.
   - **Example:** Diameter = 5 (path: 4 -> 2 -> 1 -> 3 -> 7)
     ```
         1
        / \
       2   3
      / \   \
     4  5    7
     ```

##### Redundancy Note
- **Height and Depth of BT:** While height and depth are closely related, they are not the same thing. Height refers to the longest path from the root to a leaf (global measure), while depth refers to the distance from the root to a particular node (local measure). Thus, discussing both provides a comprehensive understanding of tree structure.





#### All Traversals

#### Problem: Iterative and Recursive code for  DFS
- In-ORDER, Pre-ORDER and Post-ORDER Traversals

#### Pre-ORDER  ( N - left - right)
```java
// In-order Recursive
class AllTraversals{
  // ################### DFS PREORDER RECURSIVE ################################
  
  public List<Integer> preOrderRecursive(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    dfsPreorder(root, result);
    return result;
  }
  // Helper function to recursively traverse the tree
    private void dfsPreorder(TreeNode node, List<Integer> result) {
        if (node == null) {
        return; // Base case: if the node is null, return
        }

        result.add(node.val); // Visit the root node
        dfsPreorder(node.left, result); // Traverse the left subtree
        dfsPreorder(node.right, result); // Traverse the right subtree
        }
        
  // ################### DFS PREORDER ITERATIVE ################################
  
  public List<Integer> preOredrIterative(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) {
      return result; // If the tree is empty, return an empty list
    }

    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);

    while (!stack.isEmpty()) {
      TreeNode node = stack.pop();
      result.add(node.val); // Visit the root node

      // Push right child first so that the left child is processed first
      if (node.right != null) {
        stack.push(node.right);
      }
      if (node.left != null) {
        stack.push(node.left);
      }
    }

    return result;
  }
}
```
[Back to Top](#table-of-contents)

#### IN-ORDER TRAVERSAL [left - Node - right]
- Input
  Tree:  
    1
  /   \
  2     3
 / \
4   5

- Output: [4, 2, 5, 1, 3]

```java
// In-order Recursive
class AllTraversals{
  // ################### DFS IN-ORDER  RECURSIVE ################################
  
  public List<Integer> inOrderRecursive(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    dfsPreorder(root, result);
    return result;
  }
  // Helper function to recursively traverse the tree
    private void dfsIneorder(TreeNode node, List<Integer> result) {
        if (node == null) {
        return; // Base case: if the node is null, return
        }

      dfsIneorder(node.left, result); // Traverse the left subtree
      result.add(node.val); // Visit the root node
      dfsIneorder(node.right, result); // Traverse the right subtree
        }
        
  // ################### DFS InORDER ITERATIVE ################################
  
  public List<Integer> preOredrIterative(TreeNode root) {
    if (root == null) {
      return result; // If the tree is empty, return an empty list
    }
    List<Integer> result = new ArrayList<>();
    Stack<TreeNode> stack = new Stack<>();
    TreeNode current = root;

    // Traverse the tree
    while (current != null || !stack.isEmpty()) {
      // Reach the left most Node of the current Node
      while (current != null) {
        stack.push(current);
        current = current.left;
      }
      // Current must be null at this point
      current = stack.pop();
      result.add(current.val); // Add the node value to result
      current = current.right; // Visit the right subtree
    }

    return result;

  }
}

```
[Back to Top](#table-of-contents)


#### POST-ORDER (left - right - Node)
- Input Tree:
  1
  /   \
  2     3
  / \
  4   5

- Output
  [4, 5, 2, 3, 1]
```java
// Post-Order Recursive
class AllTraversals {
  // ################### DFS POST ORDER RECURSIVE ################################

  public List<Integer> postOrderRecursive(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    dfsPostOrder(root, result);
    return result;
  }

  // Helper function to recursively traverse the tree
  private void dfsPostorder(TreeNode node, List<Integer> result) {
    if (node == null) {
      return; // Base case: if the node is null, return
    }

    dfsPostOrder(node.left, result); // Traverse the left subtree
    dfsPostOrder(node.right, result); // Traverse the right subtree
    result.add(node.val); // Visit the root node
  }

  // ################### DFS POST-ORDER ITERATIVE ################################
  
  public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) return result;

    Stack<TreeNode> stack = new Stack<>();
    TreeNode current = root;
    TreeNode lastVisited = null;

    while (!stack.isEmpty() || current != null) {
      // Reach the left most Node of the current Node
      while (current != null) {
        stack.push(current);
        current = current.left;
      }

      // Peek the node from the stack
      current = stack.peek();

      // If the right node is null or already visited, process the current node
      if (current.right == null || current.right == lastVisited) {
        result.add(current.val);
        stack.pop();
        lastVisited = current;
        current = null; // Reset the current node
      } else {
        // Otherwise, visit the right subtree
        current = current.right;
      }
    }

    return result;
  }
  
}

```

##### Time Complexity:
- O(n), where n is the number of nodes in the tree. This is because each node is visited exactly once.

##### Space Complexity:
- O(n), where n is the number of nodes in the tree.
- The space is used by the stack in the worst case (when the tree is completely unbalanced).

***
***

#### Validate Binary Search Tree
[Back to Top](#table-of-contents)
##### Problem: Given a binary tree, determine if it is a valid binary search tree (BST).

###### Sample Scenarios
- Input
   2
  / \
  1   3

    Output : true

- Input
      5
     / \
    1   4
       / \
      3   6
   Output: false
- Explanation: The root node's value is 5 but its right child's value is 4.

```java

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

//################# RECURSIVE SOLUTION #####################

public class ValidateBinarySearchTree {

    // Main function to validate BST
    public boolean isValidBST(TreeNode root) {
        return validate(root, null, null);
    }

    // Helper function for validation
    private boolean validate(TreeNode node, Integer lower, Integer upper) {
        // Base case: if node is null, return true
        if (node == null) {
            return true;
        }

        // Retrieve the current node value
        int val = node.val;

        // Check the lower bound
        if (lower != null && val <= lower) {
            return false;
        }

        // Check the upper bound
        if (upper != null && val >= upper) {
            return false;
        }

        // Recursively validate the right subtree with updated lower bound
        if (!validate(node.right, val, upper)) {
            return false;
        }

        // Recursively validate the left subtree with updated upper bound
        if (!validate(node.left, lower, val)) {
            return false;
        }

        // If all checks pass, return true
        return true;
    }
  
}
// ############################ ITERATIVE SOLUTION ########################

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class ValidateBinarySearchTree {

    // Main function to validate BST iteratively
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;

        java.util.Stack<TreeNode> stack = new java.util.Stack<>();
        TreeNode current = root;
        TreeNode previous = null;

        while (current != null || !stack.isEmpty()) {
            // Traverse to the leftmost node
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();

            // If the previous node value is not less than the current node value, it's not a valid BST
            if (previous != null && current.val <= previous.val) {
                return false;
            }

            // Update previous node
            previous = current;

            // Move to the right subtree
            current = current.right;
        }

        return true;
    }
}



```
##### Time Complexity:
- O(n), where n is the number of nodes in the tree. Each node is visited exactly once.

##### Space Complexity:
- O(h), where h is the height of the tree. This space is used by the recursion stack.
 In the worst case, the space complexity is O(n) for a skewed tree.
***

#### Lowest Common Ancestor
[Back to Top](#table-of-contents)
##### Problem:
###### Sample Scenarios
- Input : LCA of 0 and 5 ?
      6
     / \
    2   8
   / \ / \
  0  4 7  9
    / \
   3   5

- Output: 2
Explanation: The lowest common ancestor of nodes 0 and 5 is node 2.

```java
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
// ###################### RECURSIVE ############################
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        // If both nodes are less than the root, continue search in the left subtree
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        // If both nodes are greater than the root, continue search in the right subtree
        else if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        // If one node is in the left subtree and the other in the right, current root is the LCA
        else {
            return root;
        }
    }
}

//######################## ITERATIVE #########################

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    TreeNode current = root;

    while (current != null) {
        // If both p and q are less than current node, move to left subtree
        if (p.val < current.val && q.val < current.val) {
            current = current.left;
        }
        // If both p and q are greater than current node, move to right subtree
        else if (p.val > current.val && q.val > current.val) {
            current = current.right;
        }
        // If one is less and one is greater, or one is equal (found the split point)
        else {
            return current;
        }
    }

    return null;
}
}
```
##### Time Complexity:
- O(log N) in average case, where N is the number of nodes in the BST.
 This is because in each recursive call, we reduce the search space roughly in half.
##### Space Complexity:
-  O(log N) in average case due to the space used by the recursion stack,
 where N is the number of nodes in the BST.
- O(1) in case oo iterative approach.
***

#### BTree Level Order
##### Pattern: Tree Traversal
[Back to Top](#table-of-contents)

##### Description:
Example:
- Input:
  ```
      1
     / \
    2   3
   / \   \
  4   5   6
  ```
- Output: `[1, 2, 3, 4, 5, 6]`
- Explanation: Starting from the root (1), then level 2 (2, 3), and finally level 3 (4, 5, 6).

```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeBFS {
    // TreeNode definition
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int x) {
            val = x;
            left = right = null;
        }
    }

    // Method to perform BFS on binary tree
    public List<Integer> levelOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            result.add(node.val);

            // Add left child to queue if it exists
            if (node.left != null) {
                queue.add(node.left);
            }
            // Add right child to queue if it exists
            if (node.right != null) {
                queue.add(node.right);
            }
        }

        return result;
    }
    
}
```

##### Time Complexity:
- The time complexity is O(n), where n is the number of nodes in the binary tree. Each node is visited exactly once.

##### Space Complexity:
- The space complexity is O(n) as well. In the worst case, the queue will hold all the nodes at the deepest level of the binary tree.
***

#### BTree Zigzag
##### Pattern: Tree Traversal
[Back to Top](#table-of-contents)
##### Description:
Example:
- Input:
  ```
      3
     / \
    9  20
      /  \
     15   7
  ```
- Output: `[[3], [20, 9], [15, 7]]`
- Explanation: Level 1: [3], Level 2: [20, 9], Level 3: [15, 7].

```java
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeZigzagLevelOrder {
    // TreeNode definition
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int x) {
            val = x;
            left = right = null;
        }
    }
    // COLLECTION.REVERSE() :  Method to perform Zigzag Level Order Traversal on binary
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean reverse = false;

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            if (reverse) {
                Collections.reverse(level);
            }

            result.add(level);
            reverse = !reverse;
        }

        return result;
    }
    // DEQUE Implementation Method to perform Zigzag Level Order Traversal on binary tree using Deque
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        boolean reverse = false;

        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                if (reverse) {
                    TreeNode node = deque.pollLast();
                    level.add(node.val);
                    if (node.right != null) deque.offerFirst(node.right);
                    if (node.left != null) deque.offerFirst(node.left);
                } else {
                    TreeNode node = deque.pollFirst();
                    level.add(node.val);
                    if (node.left != null) deque.offerLast(node.left);
                    if (node.right != null) deque.offerLast(node.right);
                }
            }

            result.add(level);
            reverse = !reverse;
        }

        return result;
    }
    
}
```
##### Time Complexity:
- The time complexity is O(n), where n is the number of nodes in the binary tree. Each node is visited exactly once.

##### Space Complexity:
- The space complexity is O(n), where n is the number of nodes in the binary tree. In the worst case, the deque will hold all the nodes at the deepest level of the binary tree.

***


#### BTree Vertical Order
##### Pattern: Tree Traversal
[Back to Top](#table-of-contents)
##### Description:
Example:
- Input:
  ```
       3
      / \
     9  20
       /  \
      15   7
  ```
- Output: `[[9], [3, 15], [20], [7]]`
- Explanation: The vertical order traversal of the binary tree is:
  - Column -1: Nodes [9]
  - Column 0: Nodes [3, 15]
  - Column 1: Nodes [20]
  - Column 2: Nodes [7]

```java
import java.util.*;

public class BinaryTreeVerticalOrderTraversal {
      
    // TreeNode definition
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int x) {
            val = x;
            left = right = null;
        }
    }
    // ################# Solving it as is #############################
    
    public static List<List<Integer>> verticalOrder(TreeNode<Integer> root) {
            List<List<Integer>> output = new ArrayList<List<Integer>> ();
            if (root == null) {
                return output;
            }
            //  Create a map of column index and elements in the column
            Map<Integer, ArrayList<Integer>> nodeList = new HashMap<Integer, ArrayList<Integer>>() ;
            
            // Create a queue of Tuples Queue<Map.Entry<TreeNode<Integer>, Integer>> qTuple = new LinkedList<>(); 
            
            Queue<Map.Entry<TreeNode<Integer>, Integer>> queue = new LinkedList<Map.Entry<TreeNode<Integer>, Integer>>();
            int column = 0;
            
            // Create a new Tuple new AbstractMap.SimpleEntry<TreeNode<Integer>, Integer>(root, column); 
            queue.offer(new AbstractMap.SimpleEntry<TreeNode<Integer>, Integer>(root, column));
            
            // Create a min and max column to traverse the Map later in an ordered manner. 
            int minColumn = 0;
            int maxIndex = 0;
            
            // Traverse the queue
            while (!queue.isEmpty()) {
                Map.Entry<TreeNode<Integer>, Integer> p = queue.poll();
                root = p.getKey();
                column = p.getValue();
    
                if (root != null) {
                    if (!nodeList.containsKey(column)) {
                        nodeList.put(column, new ArrayList<Integer> ());
                    }
                    nodeList.get(column).add(root.data);
                    minColumn = Math.min(minColumn, column);
                    maxIndex = Math.max(maxIndex, column);
                    
                    // Decrement the column value if its left or increase the col value if right 
                    queue.offer(new AbstractMap.SimpleEntry<TreeNode<Integer>, Integer>(root.left, column - 1));
                    queue.offer(new AbstractMap.SimpleEntry<TreeNode<Integer>, Integer>(root.right, column + 1));
                }
            }
    
            for (int i = minColumn; i<maxIndex + 1; ++i) {
                output.add(nodeList.get(i));
            }
            return output;
	}
 
    //######################### SAME PROBLEM USING TREE-MAP #########################
    // Inner class to store node and its column index
    static class ColumnNode {
        TreeNode node;
        int column;

        ColumnNode(TreeNode node, int column) {
            this.node = node;
            this.column = column;
        }
    }

    // Method to perform vertical order traversal of binary tree
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        // Map to store columns and nodes
        Map<Integer, List<Integer>> columnMap = new TreeMap<>();
        Queue<ColumnNode> queue = new LinkedList<>();
        queue.offer(new ColumnNode(root, 0));

        while (!queue.isEmpty()) {
            ColumnNode colNode = queue.poll();
            TreeNode node = colNode.node;
            int column = colNode.column;

            // Add node to the corresponding column in the map
            if (!columnMap.containsKey(column)) {
                columnMap.put(column, new ArrayList<>());
            }
            columnMap.get(column).add(node.val);

            // Enqueue left and right children with their respective columns
            if (node.left != null) {
                queue.offer(new ColumnNode(node.left, column - 1));
            }
            if (node.right != null) {
                queue.offer(new ColumnNode(node.right, column + 1));
            }
        }

        // Convert the map to result list
        for (int key : columnMap.keySet()) {
            result.add(columnMap.get(key));
        }

        return result;
    }
}
```
##### Time Complexity:
- O(N) using the regular way
- O(n log n) using Tree-Map on average, where n is the number of nodes in the binary tree. This is due to the TreeMap operations for sorting the columns.
- In the worst case, it can be O(n^2) due to the worst-case scenario of inserting into a TreeMap.

##### Space Complexity:
- The space complexity is O(n) in both cases.
- where n is the number of nodes in the binary tree. This includes the space required for the queue and the TreeMap.

***


#### BTree Average of Levels
##### Pattern: Tree Traversal
[Back to Top](#table-of-contents)
##### Description:
- Input: A binary tree.
- Output: A list of average values of each level in the binary tree.
- Explanation: Compute the average value of each level in the binary tree.

Example:
- Input:
  ```
      3
     / \
    9  20
      /  \
     15   7
  ```
- Output: `[3.0, 14.5, 11.0]`
- Explanation:
  - Level 1: [3] -> Average = 3.0
  - Level 2: [9, 20] -> Average = (9 + 20) / 2 = 14.5
  - Level 3: [15, 7] -> Average = (15 + 7) / 2 = 11.0

```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeAverageLevels {
    // TreeNode definition
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int x) {
            val = x;
            left = right = null;
        }
    }

    // Method to compute average of levels in binary tree
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            double sum = 0.0;

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            double average = sum / size;
            result.add(average);
        }

        return result;
    }
}
```
##### Time Complexity:
- The time complexity is O(n), where n is the number of nodes in the binary tree. Each node is visited exactly once.

##### Space Complexity:
- The space complexity is O(m), where m is the maximum number of nodes at any level in the binary tree. In the worst case, the queue will hold all the nodes at the maximum level of the binary tree.
***

#### BTree Right Side View
##### Pattern: Tree Traversal
[Back to Top](#table-of-contents)
##### Description:
Example:
- Input:
  ```
      1
     / \
    2   3
     \   \
      5   4
  ```
- Output: `[1, 3, 4]`
- Explanation: From the right side, we see nodes 1, 3, and 4.

```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeRightSideView {
    // TreeNode definition
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int x) {
            val = x;
            left = right = null;
        }
    }

    // Method to compute right side view of binary tree
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            int rightMost = 0;

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                // Capture the rightmost node of current level
                if (i == size -1 ) {
                    rightMost = node.val; 
                }

                if (node.right != null) queue.offer(node.right);
                if (node.left != null) queue.offer(node.left);
            }
            // Add the rightMost element into the result array. 
            result.add(rightMost);
        }

        return result;
    }
}
```
##### Time Complexity:
- The time complexity is O(n), where n is the number of nodes in the binary tree. Each node is visited exactly once.

##### Space Complexity:
- The space complexity is O(m), where m is the maximum number of nodes at any level in the binary tree. In the worst case, the queue will hold all the nodes at the maximum level of the binary tree.

***


#### BTree Cousins
##### Pattern: Tree Traversal
[Back to Top](#table-of-contents)
- Input:
  ```
      1
     / \
    2   3
   / \   \
  4   5   6
  ```
  Nodes 4 and 6 are cousins.

- Output: `true`
- Explanation: Nodes 4 and 6 have the same depth (level 3) but different parents (nodes 2 and 3).

```java
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeCousins {
    // TreeNode definition
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int x) {
            val = x;
            left = right = null;
        }
    }

    // Method to check if two nodes are cousins in binary tree
    public boolean areCousins(TreeNode root, int x, int y) {
        if (root == null) return false;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean foundX = false, foundY = false;
            TreeNode parentX = null, parentY = null;

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                // Check if current node is x or y
                if (node.val == x) {
                    foundX = true;
                }
                if (node.val == y) {
                    foundY = true;
                }

                // Track parent nodes
                if (node.left != null) {
                    if (node.left.val == x) parentX = node;
                    if (node.left.val == y) parentY = node;
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    if (node.right.val == x) parentX = node;
                    if (node.right.val == y) parentY = node;
                    queue.offer(node.right);
                }
            }

            // Check if both x and y are found at the same level but different parents
            if (foundX && foundY && parentX != parentY) {
                return true;
            }
        }

        return false;
    }
}
```
##### Time Complexity:
- The time complexity is O(n), where n is the number of nodes in the binary tree. Each node is visited exactly once.

##### Space Complexity:
- The space complexity is O(n), where n is the number of nodes in the binary tree. In the worst case, the queue will hold all the nodes at the maximum level of the binary tree.
***

#### BTree Next Right Pointers
##### Pattern: Tree Traversal
[Back to Top](#table-of-contents)
##### Description:
- Input:
  ```
       1
     /   \
    2     3
   / \   / \
  4   5 6   7
  ```
- Output:
  ```
       1 -> null
     /   \
    2  ->  3 -> null
   / \   / \
  4->5->6->7 -> null
  ```

```java
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeNextRightPointers {
    // TreeNode definition
    static class TreeNode {
        int val;
        TreeNode left, right, next;

        TreeNode(int x) {
            val = x;
            left = right = next = null;
        }
    }

    // Method to set next pointers in each node of the binary tree
    public TreeNode nextRightPointer(TreeNode root) {
        if (root == null) return null;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeNode prev = null;

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                // Set the next pointer
                if (prev != null) {
                    prev.next = node;
                }
                prev = node;

                // Add children to the queue
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            // Set last node in the level's next pointer to null
            prev.next = null;
        }

        return root;
    }
}
```
##### Time Complexity:
- The time complexity is O(n), where n is the number of nodes in the binary tree. Each node is visited exactly once.

##### Space Complexity:
- The space complexity is O(m), where m is the maximum number of nodes at any level in the binary tree. In the worst case, the queue will hold all the nodes at the maximum level of the binary tree.
***



#### BTree Check Symmetric
##### Pattern: Tree Traversal
[Back to Top](#table-of-contents)
##### Description:
Example:
- Input:
  ```
       1
      / \
     2   2
    / \ / \
   3  4 4  3
  ```
- Output: `true`
```java
import java.util.LinkedList;
import java.util.Queue;
// TreeNode definition
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

// Solution to check if a tree is symmetric using a queue


public class SymmetricTree {
    // Main function to check if a tree is symmetric
    public boolean isSymmetric(TreeNode root) {
        // A tree is symmetric if its left subtree is a mirror reflection of its right subtree
        if (root == null) return true;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);
        
        while (!queue.isEmpty()) {
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();
            
            // Both nodes are null
            if (t1 == null && t2 == null) continue;
            // One of the nodes is null
            if (t1 == null || t2 == null) return false;
            // Check if the current nodes are equal
            if (t1.val != t2.val) return false;
            
            // Add children nodes to the queue in mirror order
            queue.add(t1.left);
            queue.add(t2.right);
            queue.add(t1.right);
            queue.add(t2.left);
        }
        
        return true;
    }
}
```
##### Time Complexity:
- The time complexity is O(n) where n is the number of nodes in the tree. Each node is visited once.

##### Space Complexity:
- The space complexity is O(n) where n is the number of nodes in the tree. This space is used by the queue.

***
