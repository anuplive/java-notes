# BinaryTree
[HOME.md](HOME.md)
=================
### Table of contents

<!--ts-->
| Category                                                                                              | Problems |    |    |    |    |    |    |    |
|:------------------------------------------------------------------------------------------------------|:---------|:---|:---|:---|:---|:---|:---|:---|
| [Binary Tree Theory](#binary-tree-theory)                                                             |          |    |    |    |    |    |    |    |
| [Tree DFS](#tree-dfs)                                                                                 |          |    |    |    |    |    |    |    |
| [All Traversals](#all-traversals)                                                                     |          |    |    |    |    |    |    |    |
| [Pre-ORDER  ( N - left - right)](#pre-order---n---left---right)                                       |          |    |    |    |    |    |    |    |
| [IN-ORDER TRAVERS](#in-order-traversal-left---node---right)                                           |          |    |    |    |    |    |    |    |
| [POST-ORDER](#post-order-left---right---node)                                                         |          |    |    |    |    |    |    |    |
| [Validate Binary Search Tree](#validate-binary-search-tree)                                           |          |    |    |    |    |    |    |    |
| [Lowest Common Ancestor](#lowest-common-ancestor)                                                     |          |    |    |    |    |    |    |    |
| [BTree Level Order](#btree-level-order)                                                               |          |    |    |    |    |    |    |    |
| [BTree Zigzag](#btree-zigzag)                                                                         |          |    |    |    |    |    |    |    |
| [BTree Vertical Order](#btree-vertical-order)                                                         |          |    |    |    |    |    |    |    |
| [BTree Average of Levels](#btree-average-of-levels)                                                   |          |    |    |    |    |    |    |    |
| [BTree Right Side View](#btree-right-side-view)                                                       |          |    |    |    |    |    |    |    |
| [BTree Cousins](#btree-cousins)                                                                       |          |    |    |    |    |    |    |    |
| [BTree Next Right Pointers](#btree-next-right-pointers)                                               |          |    |    |    |    |    |    |    |
| [BTree Check Symmetric](#btree-check-symmetric)                                                       |          |    |    |    |    |    |    |    |
| [AlgoMap](#algomap)                                                                                   |          |    |    |    |    |    |    |    |
| [Invert Binary Tree (Iterative Solution)](#invert-binary-tree-iterative-solution)                     |          |    |    |    |    |    |    |    |
| [Maximum Depth of Binary Tree (Iterative Solution)](#maximum-depth-of-binary-tree-iterative-solution) |          |    |    |    |    |    |    |    |
| [Maximum Depth of Binary Tree (Recursive)](#maximum-depth-of-binary-tree-recursive)                   |          |    |    |    |    |    |    |    |
| [Diameter of Binary Tree](#diameter-of-binary-tree)                                                   |          |    |    |    |    |    |    |    |
| [Diameter of Binary Tree (Iterative Approach](#diameter-of-binary-tree-iterative-approach)            |          |    |    |    |    |    |    |    |
|                                                                                                       |          |    |    |    |    |    |    |    |


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
            Map<Integer, ArrayList<Integer>> nodeMap = new HashMap<Integer, ArrayList<Integer>>() ;
            
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
                    if (!nodeMap.containsKey(column)) {
                        nodeMap.put(column, new ArrayList<Integer> ());
                    }
                    nodeMap.get(column).add(root.data);
                    minColumn = Math.min(minColumn, column);
                    maxIndex = Math.max(maxIndex, column);
                    
                    // Decrement the column value if its left or increase the col value if right 
                    queue.offer(new AbstractMap.SimpleEntry<TreeNode<Integer>, Integer>(root.left, column - 1));
                    queue.offer(new AbstractMap.SimpleEntry<TreeNode<Integer>, Integer>(root.right, column + 1));
                }
            }
    
            for (int i = minColumn; i<maxIndex + 1; ++i) {
                output.add(nodeMap.get(i));
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

                
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
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


#### Algomap

#### Invert Binary Tree (Iterative Solution)
##### Pattern: Tree Traversal
[Back to Top](#table-of-contents)
##### Description:
- Input: A binary tree (e.g., root of a tree).
- Output: A new tree where the left and right children of all nodes are swapped.
- Explanation: The function will invert the binary tree using an iterative approach (level-order traversal with a queue).

Example:
- Input:
```
     4
   /   \
  2     7
 / \   / \
1   3 6   9
```
- Output:
```
     4
   /   \
  7     2
 / \   / \
9   6 3   1
```

```java
// Definition for a binary tree node
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { 
        this.val = val; 
    }
}

public class InvertBinaryTree {

    // Iterative function to invert the binary tree
    public TreeNode invertTree(TreeNode root) {
        // If the tree is empty, return null
        if (root == null) return null;

        // Initialize a queue to store the nodes for level-order traversal
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        // Process nodes level by level
        while (!queue.isEmpty()) {
            // Remove the current node from the queue
            TreeNode current = queue.poll();

            // Swap its left and right children
            TreeNode temp = current.left;
            current.left = current.right;
            current.right = temp;

            // Add the left and right children to the queue if they exist
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
        // Return the root of the inverted tree
        return root;
    }
}
```
##### Algorithm Explanation:
- Use a queue to perform level-order traversal.
- For each node, swap its left and right children.
- Continue processing all nodes until the queue is empty.

##### Time Complexity:
- O(n) where **n** is the number of nodes in the tree. Each node is processed once.

##### Space Complexity:
- O(n), where **n** is the number of nodes. The queue can hold all nodes in the worst case (e.g., for a complete binary tree).

***

#### Maximum Depth of Binary Tree (Iterative Solution)
##### Pattern: Tree Traversal (Level Order)
[Back to Top](#table-of-contents)

##### Description:
- **Input**: A binary tree.
  ```
      3
     / \
    9  20
      /  \
     15   7
  ```
- **Output**: 3
- **Explanation**: The maximum depth of the tree is 3 because the longest path from the root to a leaf is 3 levels deep.

```java
// Definition for a binary tree node.
import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int x) {
        val = x;
        left = null;
        right = null;
    }
}

public class Solution {
    // Function to calculate the maximum depth of a binary tree using an iterative approach
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        // Initialize a queue for level order traversal
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 0;
        
        // Loop until the queue is empty
        while (!queue.isEmpty()) {
            int levelSize = queue.size(); // Get the number of nodes at the current level
            depth++; // Increment depth for each level
            
            // Process all nodes at the current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll(); // Get the next node in the queue
                
                // Add the left and right children to the queue, if they exist
                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }
        }
        
        return depth; // Return the final depth
    }
}
```

##### Time Complexity:
- **O(n)**: Every node in the tree is processed once.

##### Space Complexity:
- **O(n)**: In the worst case, the queue will hold all the nodes at the deepest level, which can be up to n/2 nodes.

***


#### Maximum Depth of Binary Tree (Recursive)
##### Pattern: Tree Traversal
[Back to Top](#table-of-contents)

##### Description:
- **Input**: A binary tree.
  ```
      3
     / \
    9  20
      /  \
     15   7
  ```
- **Output**: 3
- **Explanation**: The maximum depth of the tree is the longest path from the root to a leaf, which in this case is 3 (root -> 20 -> 7).

```java
// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int x) {
        val = x;
        left = null;
        right = null;
    }
}

public class Solution {
    // Function to calculate the maximum depth of a binary tree
    public int maxDepth(TreeNode root) {
        // Base case: if the node is null, return depth 0
        if (root == null) {
            return 0;
        }
        
        // Recursively calculate the depth of left and right subtrees
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        
        // The depth of the current node is max of left and right subtree depths plus 1 (for the current node)
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
```

##### Time Complexity:
- **O(n)**: We visit every node in the binary tree exactly once.

##### Space Complexity:
- **O(h)**: The space complexity is determined by the height of the tree, which is the maximum depth of recursion calls (h is the height of the tree).

***

#### Diameter of Binary Tree
##### Pattern: Tree Traversal
[Back to Top](#table-of-contents)
##### Description:
- Input: A binary tree with nodes having integer values.
- Output: An integer representing the diameter of the tree (the number of nodes on the longest path between two leaves).
- Explanation: The diameter of the tree is the length of the longest path between any two nodes, which may or may not pass through the root. For example, for a tree with 5 nodes, the diameter may be 4.

```java
// Java program to find the diameter of a binary tree

// Definition for a binary tree node
class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int x) {
        val = x;
        left = right = null;
    }
}

public class BinaryTree {
    
    // Variable to store the diameter
    int diameter = 0;

    // Function to calculate the height of the tree and update diameter
    public int height(TreeNode node) {
        // Base case: if the node is null, the height is 0
        if (node == null) return 0;

        // Recursive call for left and right subtrees
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        // Update the diameter. The diameter is the max of the current diameter
        // and the sum of heights of left and right subtrees
        diameter = Math.max(diameter, leftHeight + rightHeight);

        // Return the height of the tree from the current node
        return 1 + Math.max(leftHeight, rightHeight);
    }

    // Function to calculate the diameter of the tree
    public int diameterOfBinaryTree(TreeNode root) {
        height(root); // Update the diameter during height calculation
        return diameter; // Return the final diameter
    }

    public static void main(String[] args) {
        // Create a binary tree
        BinaryTree tree = new BinaryTree();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        // Calculate and print the diameter of the binary tree
        System.out.println("Diameter of the tree: " + tree.diameterOfBinaryTree(root));
    }
}
```
##### Time Complexity:
- O(N): Where N is the number of nodes in the tree. We visit each node once.

##### Space Complexity:
- O(H): Where H is the height of the tree, due to recursion stack space.


***

***

#### Diameter of Binary Tree (Iterative Approach)
##### Pattern: Tree Traversal (Using Stack/DFS)
[Back to Top](#table-of-contents)
##### Description:
- Input: A binary tree with nodes having integer values.
- Output: An integer representing the diameter of the tree (the number of nodes on the longest path between two leaves).
- Explanation: The diameter of the tree is the longest path between two nodes in the tree, which may or may not pass through the root. The iterative approach uses a stack for depth-first traversal.

```java
import java.util.HashMap;
import java.util.Stack;

// Definition for a binary tree node
class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int x) {
        val = x;
        left = right = null;
    }
}

public class BinaryTreeIterative {

    // Function to calculate the diameter of the tree
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;

        // Stack for DFS traversal
        Stack<TreeNode> stack = new Stack<>();
        HashMap<TreeNode, Integer> heights = new HashMap<>();

        stack.push(root);
        int diameter = 0;

        // Depth-first traversal to calculate heights of all nodes
        while (!stack.isEmpty()) {
            TreeNode current = stack.peek();

            if (current.left != null && !heights.containsKey(current.left)) {
                stack.push(current.left); // Explore left subtree
            } else if (current.right != null && !heights.containsKey(current.right)) {
                stack.push(current.right); // Explore right subtree
            } else {
                // Both children are visited, or current node is a leaf
                current = stack.pop();

                // Get heights of left and right children (0 if null)
                int leftHeight = heights.getOrDefault(current.left, 0);
                int rightHeight = heights.getOrDefault(current.right, 0);

                // Calculate height of the current node
                int currentHeight = 1 + Math.max(leftHeight, rightHeight);
                heights.put(current, currentHeight);

                // Update diameter with the longest path at the current node
                diameter = Math.max(diameter, leftHeight + rightHeight);
            }
        }

        return diameter;
    }

    public static void main(String[] args) {
        // Create a binary tree
        BinaryTreeIterative tree = new BinaryTreeIterative();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        // Calculate and print the diameter of the binary tree
        System.out.println("Diameter of the tree: " + tree.diameterOfBinaryTree(root));
    }
}
```
##### Time Complexity:
- O(N): We process each node once, where N is the number of nodes in the tree.

##### Space Complexity:
- O(N): Due to the stack used in the iterative DFS, and the HashMap storing the heights.
***




#### Same Binary Tree (Iterative Solution)
##### Pattern: Breadth First Search (BFS)
[Back to Top](#table-of-contents)
##### Description:
- **Input**: Two binary trees `p` and `q`
- **Output**: `true` if both trees are structurally identical and the nodes have the same values; otherwise, `false`
- **Explanation**: Compare each node in the first tree to the corresponding node in the second tree using an iterative approach with BFS.

Example:
- Input: `p = [1,2,3]`, `q = [1,2,3]`
- Output: `true`
- Input: `p = [1,2]`, `q = [1,null,2]`
- Output: `false`

```java
import java.util.LinkedList;
import java.util.Queue;

public class SameTreeIterative {

    // Iterative function to check if two binary trees are the same
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // Use a queue to perform BFS for both trees
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(p);
        queue.add(q);

        while (!queue.isEmpty()) {
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();

            // If both nodes are null, continue
            if (node1 == null && node2 == null) {
                continue;
            }

            // If one is null and the other isn't, or the values differ, return false
            if (node1 == null || node2 == null || node1.val != node2.val) {
                return false;
            }

            // Add the left and right children of both nodes to the queue for comparison
            queue.add(node1.left);
            queue.add(node2.left);
            queue.add(node1.right);
            queue.add(node2.right);
        }

        // If we finish without returning false, the trees are the same
        return true;
    }

    // Example usage
    public static void main(String[] args) {
        SameTreeIterative sameTree = new SameTreeIterative();
        
        // Creating tree 1
        TreeNode p = new TreeNode(1);
        p.left = new TreeNode(2);
        p.right = new TreeNode(3);

        // Creating tree 2
        TreeNode q = new TreeNode(1);
        q.left = new TreeNode(2);
        q.right = new TreeNode(3);

        // Check if both trees are the same
        System.out.println(sameTree.isSameTree(p, q)); // Output: true
    }
}
```

##### Time Complexity:
- O(n), where n is the number of nodes in the trees. We visit every node once.

##### Space Complexity:
- O(n), due to the space used by the queue to store nodes during traversal.

***

#### Same Binary Tree
##### Pattern: Depth First Search
[Back to Top](#table-of-contents)
##### Description:
- **Input**: Two binary trees `p` and `q`
- **Output**: `true` if both trees are structurally identical and the nodes have the same values; otherwise, `false`
- **Explanation**: Compare each node in the first tree to the corresponding node in the second tree.

Example:
- Input: `p = [1,2,3]`, `q = [1,2,3]`
- Output: `true`
- Input: `p = [1,2]`, `q = [1,null,2]`
- Output: `false`

```java
// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int val) {
        this.val = val;
        left = null;
        right = null;
    }
}

public class SameTree {

    // Recursive function to check if two binary trees are the same.
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // If both trees are empty, they are the same
        if (p == null && q == null) {
            return true;
        }

        // If one of the trees is empty, they are not the same
        if (p == null || q == null) {
            return false;
        }

        // If the current node values differ, the trees are not the same
        if (p.val != q.val) {
            return false;
        }

        // Recursively check the left and right subtrees
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    // Example usage
    public static void main(String[] args) {
        SameTree sameTree = new SameTree();
        
        // Creating tree 1
        TreeNode p = new TreeNode(1);
        p.left = new TreeNode(2);
        p.right = new TreeNode(3);

        // Creating tree 2
        TreeNode q = new TreeNode(1);
        q.left = new TreeNode(2);
        q.right = new TreeNode(3);

        // Check if both trees are the same
        System.out.println(sameTree.isSameTree(p, q)); // Output: true
    }
}
```

##### Time Complexity:
- O(n), where n is the number of nodes in the trees. We visit every node once.

##### Space Complexity:
- O(h), where h is the height of the tree, due to the recursive call stack.

***


#### Symmetric Tree (Iterative Approach)
##### Pattern: Tree Traversal
[Back to Top](#table-of-contents)
##### Description:
- **Input:** A binary tree:
  ```
      1
     / \
    2   2
   / \ / \
  3  4 4  3
  ```
- **Output:** `true`
- **Explanation:** The binary tree is symmetric around its center.

```java
// Java program to check if a tree is symmetric using an iterative approach

import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int x) {
        val = x;
        left = null;
        right = null;
    }
}

public class SymmetricTreeIterative {
    public boolean isSymmetric(TreeNode root) {
        // If the tree is empty, it's symmetric
        if (root == null) return true;

        // Use a queue to store pairs of nodes to compare
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);

        // Process nodes in pairs
        while (!queue.isEmpty()) {
            // Remove two nodes to compare
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();

            // Both are null, symmetric at this level, continue
            if (t1 == null && t2 == null) continue;

            // One is null or values are different, not symmetric
            if (t1 == null || t2 == null || t1.val != t2.val) return false;

            // Enqueue children in mirror order:
            // Compare t1's left with t2's right and t1's right with t2's left
            queue.add(t1.left);
            queue.add(t2.right);
            queue.add(t1.right);
            queue.add(t2.left);
        }

        // If all pairs matched, the tree is symmetric
        return true;
    }

    public static void main(String[] args) {
        // Example input
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);

        SymmetricTreeIterative obj = new SymmetricTreeIterative();
        System.out.println(obj.isSymmetric(root)); // Output: true
    }
}
```
##### Time Complexity:
- **O(n):** We process each node once.

##### Space Complexity:
- **O(n):** Space used by the queue, where `n` is the number of nodes. In the worst case, all nodes at a level are stored in the queue.
***


#### Symmetric Tree
##### Pattern: Tree Traversal
[Back to Top](#table-of-contents)
##### Description:
- **Input:** A binary tree:
  ```
      1
     / \
    2   2
   / \ / \
  3  4 4  3
  ```
- **Output:** `true`
- **Explanation:** The binary tree is symmetric around its center.

```java
// Java program to check if a tree is symmetric

// Definition for a binary tree node
class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int x) {
        val = x;
        left = null;
        right = null;
    }
}

public class SymmetricTree {
    // Main function to check if the tree is symmetric
    public boolean isSymmetric(TreeNode root) {
        // If the tree is empty, it's symmetric
        if (root == null) return true;
        // Use helper function to compare the left and right subtrees
        return isMirror(root.left, root.right);
    }

    // Helper function to compare two subtrees
    private boolean isMirror(TreeNode t1, TreeNode t2) {
        // Both are null, meaning symmetric
        if (t1 == null && t2 == null) return true;
        // One is null and the other is not, meaning not symmetric
        if (t1 == null || t2 == null) return false;
        // Both nodes must have the same value, and their respective subtrees must mirror each other
        return (t1.val == t2.val)
            && isMirror(t1.left, t2.right)  // Compare left subtree of t1 with right subtree of t2
            && isMirror(t1.right, t2.left); // Compare right subtree of t1 with left subtree of t2
    }

    public static void main(String[] args) {
        // Example input
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);

        SymmetricTree obj = new SymmetricTree();
        System.out.println(obj.isSymmetric(root)); // Output: true
    }
}
```
##### Time Complexity:
- **O(n):** We traverse each node of the tree once.

##### Space Complexity:
- **O(h):** Recursive stack space, where `h` is the height of the tree.

***

#### Path Sum
##### Pattern: Tree Traversal
[Back to Top](#table-of-contents)
##### Description:
- Input: A binary tree and an integer `targetSum`.
- Output: Return `true` if there is a root-to-leaf path such that adding up all the values along the path equals `targetSum`, otherwise return `false`.
- Explanation: Check if there exists a path from root to leaf where the sum of node values matches `targetSum`.

```java
// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class PathSum {
    // Function to check if a path with the target sum exists
    public boolean hasPathSum(TreeNode root, int targetSum) {
        // If the current node is null, return false as we can't find a path here
        if (root == null) {
            return false;
        }
        
        // If we've reached a leaf node, check if the current sum equals targetSum
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }
        
        // Update the targetSum by subtracting the current node's value
        int remainingSum = targetSum - root.val;
        
        // Recursively check the left and right subtrees for a valid path
        return hasPathSum(root.left, remainingSum) || hasPathSum(root.right, remainingSum);
    }
}
```

##### Time Complexity:
- The algorithm visits every node once, so the time complexity is **O(N)**, where `N` is the number of nodes in the binary tree.

##### Space Complexity:
- In the worst case (for a completely unbalanced tree), the recursion stack can go as deep as the height of the tree. Hence, the space complexity is **O(H)**, where `H` is the height of the tree.

***

#### Problem: Path Sum (Iterative Approach)

##### Pattern: Tree Traversal (DFS using Stack)
[Back to Top](#table-of-contents)
##### Description:
- Input: A binary tree and an integer `targetSum`.
- Output: Return `true` if there is a root-to-leaf path such that adding up all the values along the path equals `targetSum`, otherwise return `false`.
- Explanation: Use an iterative DFS approach to check if there exists a path from root to leaf where the sum of node values matches `targetSum`.

```java
import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class PathSumIterative {
    // Function to check if a path with the target sum exists (Iterative)
    public boolean hasPathSum(TreeNode root, int targetSum) {
        // If the root is null, no path exists
        if (root == null) {
            return false;
        }

        // Stack to hold pairs of the current node and the remaining sum needed
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> sumStack = new Stack<>();
        
        // Initialize the stacks with the root node and initial targetSum
        nodeStack.push(root);
        sumStack.push(targetSum - root.val);
        
        // Iterate until the stack is empty
        while (!nodeStack.isEmpty()) {
            TreeNode currentNode = nodeStack.pop();
            int currentSum = sumStack.pop();
            
            // Check if it's a leaf node and the path sum matches
            if (currentNode.left == null && currentNode.right == null && currentSum == 0) {
                return true;
            }
            
            // Push the left and right children into the stack if they exist
            if (currentNode.left != null) {
                nodeStack.push(currentNode.left);
                sumStack.push(currentSum - currentNode.left.val);
            }
            
            if (currentNode.right != null) {
                nodeStack.push(currentNode.right);
                sumStack.push(currentSum - currentNode.right.val);
            }
        }
        
        return false;  // If no path matches the targetSum
    }
}
```

##### Time Complexity:
- The algorithm still visits every node once, so the time complexity is **O(N)**, where `N` is the number of nodes in the binary tree.

##### Space Complexity:
- The space complexity is **O(H)**, where `H` is the height of the tree. In the worst case (completely unbalanced tree), the stack could grow up to `H`, and in the best case (completely balanced tree), it would be `O(log N)`.

***



#### Subtree of Another Tree
##### Pattern: Tree Traversal
[Back to Top](#table-of-contents)
##### Description:
- **Input:**
  - Two binary trees `root` and `subRoot`.
  - `root = [3,4,5,1,2]`, `subRoot = [4,1,2]`
- **Output:**
  - `true`
- **Explanation:**
  - Check if `subRoot` is a subtree of `root`. A subtree means all nodes of `subRoot` match with some subtree of `root`.

```java
// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { 
        this.val = val; 
    }
}

public class SubtreeOfAnotherTree {

    // Function to check if two trees are identical
    private boolean isSameTree(TreeNode s, TreeNode t) {
        // Base case: both are null, they are identical
        if (s == null && t == null) return true;
        // If one is null and the other is not, they aren't identical
        if (s == null || t == null) return false;
        // Check current node and recursively check left and right subtrees
        return (s.val == t.val) && isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }

    // Main function to check if subRoot is a subtree of root
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        // Base case: If root is null, it can't contain any subtree
        if (root == null) return false;
        // If the trees match, return true
        if (isSameTree(root, subRoot)) return true;
        // Otherwise, check subtrees of left and right children
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    public static void main(String[] args) {
        // Example tree creation for root and subRoot
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(2);

        TreeNode subRoot = new TreeNode(4);
        subRoot.left = new TreeNode(1);
        subRoot.right = new TreeNode(2);

        SubtreeOfAnotherTree solution = new SubtreeOfAnotherTree();
        System.out.println(solution.isSubtree(root, subRoot)); // Expected output: true
    }
}
```
##### Time Complexity:
- **O(m * n)**: For each node of `root`, we are calling the `isSameTree` function, which can take up to `O(n)` time (where `m` and `n` are the sizes of `root` and `subRoot`).

##### Space Complexity:
- **O(h)**: The space complexity is proportional to the height of the tree due to recursive calls, where `h` is the height of the tree.
***

#### Subtree of Another Tree (Iterative Approach)
##### Pattern: Tree Traversal
[Back to Top](#table-of-contents)
##### Description:
- **Input:**
  - Two binary trees `root` and `subRoot`.
  - `root = [3,4,5,1,2]`, `subRoot = [4,1,2]`
- **Output:**
  - `true`
- **Explanation:**
  - Check if `subRoot` is a subtree of `root` using an iterative approach. A subtree means all nodes of `subRoot` match with some subtree of `root`.

```java
// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { 
        this.val = val; 
    }
}

public class SubtreeOfAnotherTreeIterative {

    // Helper method to check if two trees are identical
    private boolean isSameTree(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        return s.val == t.val && isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }

    // Main function to check if subRoot is a subtree of root using iteration
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) return false;

        // Use a stack to iteratively traverse the tree
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        // Iterate through each node in the tree
        while (!stack.isEmpty()) {
            TreeNode currentNode = stack.pop();

            // If the current node matches the root of subRoot, check for subtree
            if (currentNode.val == subRoot.val && isSameTree(currentNode, subRoot)) {
                return true;
            }

            // Add the left and right children to the stack for further exploration
            if (currentNode.right != null) stack.push(currentNode.right);
            if (currentNode.left != null) stack.push(currentNode.left);
        }

        return false; // Return false if no matching subtree is found
    }

    public static void main(String[] args) {
        // Example tree creation for root and subRoot
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(2);

        TreeNode subRoot = new TreeNode(4);
        subRoot.left = new TreeNode(1);
        subRoot.right = new TreeNode(2);

        SubtreeOfAnotherTreeIterative solution = new SubtreeOfAnotherTreeIterative();
        System.out.println(solution.isSubtree(root, subRoot)); // Expected output: true
    }
}
```
##### Time Complexity:
- **O(m * n)**: For each node in `root`, we are performing a subtree comparison which takes `O(n)` time, where `m` is the number of nodes in `root` and `n` is the number of nodes in `subRoot`.

##### Space Complexity:
- **O(h)**: The space complexity is proportional to the height of the tree `h` due to the stack used for the iterative traversal.
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


#### Kth Smallest Element in a BST
##### Pattern: Inorder Traversal, Binary Search Tree
[Back to Top](#table-of-contents)

##### Description:
- **Input**: A Binary Search Tree (BST) and an integer `k`.
- **Output**: The `k`th smallest element in the BST.
- **Explanation**: Given a BST, the elements are ordered in a specific way, and the `k`th smallest can be found using an inorder traversal, which processes nodes in sorted order for a BST.

##### Example:
- **Input**:
  - `root = [3, 1, 4, null, 2]`, `k = 1`
- **Output**:
  - `1`
- **Explanation**: Inorder traversal gives [1, 2, 3, 4]. The 1st smallest element is 1.

```java
// Java program to find the kth smallest element in a BST
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class KthSmallestElementInBST {
    
    // Helper function to perform inorder traversal
    private int inorderTraversal(TreeNode root, int[] k) {
        if (root == null) {
            return -1;  // Base case: return an invalid value
        }

        // Traverse the left subtree
        int left = inorderTraversal(root.left, k);
        if (left != -1) return left; // If found, return the result

        // Decrease k after visiting the current node
        k[0]--; 
        if (k[0] == 0) {
            return root.val; // Found the kth smallest element
        }

        // Traverse the right subtree
        return inorderTraversal(root.right, k);
    }

    public int kthSmallest(TreeNode root, int k) {
        // Wrap k into an array to simulate pass by reference
        return inorderTraversal(root, new int[]{k});
    }
}
```

##### Time Complexity:
- **O(n)**: We perform an inorder traversal that visits each node exactly once.

##### Space Complexity:
- **O(h)**: The space complexity is determined by the depth of the recursive stack, which is the height of the tree. In the worst case (unbalanced tree), the height can be `O(n)`, but in a balanced tree, it is `O(log n)`.

***

