# BinaryTree
[HOME.md](HOME.md)
=================
### Table of contents

<!--ts--> 
| Category                                                                                                                | Problems |    |    |    |    |    |    |    |
|:------------------------------------------------------------------------------------------------------------------------|:---------|:---|:---|:---|:---|:---|:---|:---|
| [Theory](#theory)                                                                                                       |          |    |    |    |    |    |    |    |
| [Tree DFS](#tree-dfs)                                                                                                   |          |    |    |    |    |    |    |    |
|                                                                                                                         |          |    |    |    |    |    |    |    |
| [All Traversals](#all-traversals)                                                                                       |          |    |    |    |    |    |    |    |
|                                                                                                                         |          |    |    |    |    |    |    |    |
| #####################################                                                                                   |          |    |    |    |    |    |    |    |
|                                                                                                                         |          |    |    |    |    |    |    |    |
| [Binary Search Tree](#binary-search-tree)                                                                               |          |    |    |    |    |    |    |    |
| [Validate Binary Search Tree](#validate-binary-search-tree)                                                             |          |    |    |    |    |    |    |    |
| [Lowest Common Ancestor](#lowest-common-ancestor)                                                                       |          |    |    |    |    |    |    |    |
| [Insert into a Binary Search Tree](#insert-into-a-binary-search-tree)                                                   |          |    |    |    |    |    |    |    |
| [Delete Node in a BST](#delete-node-in-a-bst)                                                                           |          |    |    |    |    |    |    |    |
| [Invert a Binary Tree](#invert-a-binary-tree)                                                                           |          |    |    |    |    |    |    |    |
|                                                                                                                         |          |    |    |    |    |    |    |    |
| #####################################                                                                                   |          |    |    |    |    |    |    |    |
|                                                                                                                         |          |    |    |    |    |    |    |    |
| [Tree Properties](#tree-properties)                                                                                     |          |    |    |    |    |    |    |    |
| [BTree Check Symmetric](#btree-check-symmetric)                                                                         |          |    |    |    |    |    |    |    |
| [Balanced Binary Tree](#balanced-binary-tree)                                                                           |          |    |    |    |    |    |    |    |
| [Diameter of Binary Tree](#diameter-of-binary-tree)                                                                     |          |    |    |    |    |    |    |    |
| [Maximum Depth of Binary](#maximum-depth-of-binary)                                                                     |          |    |    |    |    |    |    |    |
|                                                                                                                         |          |    |    |    |    |    |    |    |
| #####################################                                                                                   |          |    |    |    |    |    |    |    |
|                                                                                                                         |          |    |    |    |    |    |    |    |
| [Tree BFS Traversal Problems](#tree-bfs-traversal-problems)                                                             |          |    |    |    |    |    |    |    |
| [BTree Level Order](#btree-level-order)                                                                                 |          |    |    |    |    |    |    |    |
| [BTree Zigzag](#btree-zigzag)                                                                                           |          |    |    |    |    |    |    |    |
| [BTree Vertical Order](#btree-vertical-order)                                                                           |          |    |    |    |    |    |    |    |
| [BTree Average of Levels](#btree-average-of-levels)                                                                     |          |    |    |    |    |    |    |    |
| [BTree Right Side View](#btree-right-side-view)                                                                         |          |    |    |    |    |    |    |    |
| [BTree Cousins](#btree-cousins)                                                                                         |          |    |    |    |    |    |    |    |
| [BTree Next Right Pointers](#btree-next-right-pointers)                                                                 |          |    |    |    |    |    |    |    |
|                                                                                                                         |          |    |    |    |    |    |    |    |
| #####################################                                                                                   |          |    |    |    |    |    |    |    |
|                                                                                                                         |          |    |    |    |    |    |    |    |
| [Construct Binary Tree](#construct-binary-tree)                                                                         |          |    |    |    |    |    |    |    |
| [Convert Sorted Array to Binary Search Tree](#convert-sorted-array-to-binary-search-tree)                               |          |    |    |    |    |    |    |    |
| [Construct Binary Tree from Preorder and Inorder Traversal](#construct-binary-tree-from-preorder-and-inorder-traversal) |          |    |    |    |    |    |    |    |
|                                                                                                                         |          |    |    |    |    |    |    |    |
| #####################################                                                                                   |          |    |    |    |    |    |    |    |
|                                                                                                                         |          |    |    |    |    |    |    |    |
| [De-Construct Binary Tree](#de-construct-binary-tree)                                                                   |          |    |    |    |    |    |    |    |
| [Flatten Binary Tree to Linked List](#flatten-binary-tree-to-linked-list)                                               |          |    |    |    |    |    |    |    |
| [Serialize and Deserialize Binary Tree](#serialize-and-deserialize-binary-tree)                                         |          |    |    |    |    |    |    |    |
|                                                                                                                         |          |    |    |    |    |    |    |    |
| #####################################                                                                                   |          |    |    |    |    |    |    |    |
|                                                                                                                         |          |    |    |    |    |    |    |    |
| [Tree Path Sum](#tree-path-sum)                                                                                         |          |    |    |    |    |    |    |    |
| [Path Sum in Binary Tree](#path-sum-in-binary-tree)                                                                     |          |    |    |    |    |    |    |    |
| [Path Sum II](#path-sum-ii)                                                                                             |          |    |    |    |    |    |    |    |
| [Binary Tree Maximum Path Sum](#binary-tree-maximum-path-sum)                                                           |          |    |    |    |    |    |    |    |
|                                                                                                                         |          |    |    |    |    |    |    |    |
| #####################################                                                                                   |          |    |    |    |    |    |    |    |
| [Trees in Java](#trees-in-java)                                                                                                     |          |    |    |    |    |    |    |    |
|                                                                                                                         |          |    |    |    |    |    |    |    |
| [Additional Problems]                                                                                                   |          |    |    |    |    |    |    |    |
| [AVL Tree]                                                                                                              |          |    |    |    |    |    |    |    |
| [Red Black Tree]                                                                                                        |          |    |    |    |    |    |    |    |
| [2-3 Tree]                                                                                                              |          |    |    |    |    |    |    |    |
| [Min Value in BT] [Max Value in BT] [Max Kth value in a BT] [Nodes at distance K]                                       |          |    |    |    |    |    |    |    |


[Back to Top](#table-of-contents)

### Binary Search Tree (BST) Theory

A **Binary Search Tree (BST)** is a binary tree where each node has a key greater than all the keys in its left subtree and less than all the keys in its right subtree. BSTs allow for efficient search, insertion, and deletion operations.

#### Types of Binary Trees

##### Full Tree
- **Definition**: A binary tree is full if every node has either 0 or 2 children.
- **Properties**:
  - No nodes have a single child.
  - Perfectly suited for problems requiring complete structure.
- **Example**:
  ```
        1
       / \
      2   3
     / \
    4   5
  ```

##### Complete Tree
- **Definition**: A binary tree is complete if all levels except possibly the last are fully filled, and all nodes are as far left as possible.
- **Properties**:
  - Used in heap implementations.
  - Ensures minimal height.
- **Example**:
  ```
        1
       / \
      2   3
     / \
    4   5
  ```

##### Balanced Tree
- **Definition**: A binary tree is balanced if the height difference between the left and right subtrees of every node is at most 1.
- **Properties**:
  - Ensures logarithmic height.
  - Commonly used in AVL and Red-Black trees.

##### Perfect Tree
- **Definition**: A binary tree is perfect if all internal nodes have two children, and all leaves are at the same level.
- **Properties**:
  - Maximizes the number of nodes for a given height.
- **Example**:
  ```
        1
       / \
      2   3
     / \ / \
    4  5 6  7
  ```

#### Special Types of BSTs

##### AVL Tree
- **Definition**: A self-balancing BST where the height difference between the left and right subtrees of any node (called the balance factor) is at most 1.
- **Operations**:
  - Rotations (single and double) are used to maintain balance after insertions or deletions.
- **Time Complexity**:
  - Search, Insert, Delete: `O(log n)`
- **Example**:
  ```
        10
       /  \
      5    15
     / \
    2   7
  ```

##### Red-Black Tree
- **Definition**: A self-balancing BST where each node has an additional color property (red or black).
- **Rules**:
  - The root is always black.
  - Red nodes cannot have red children.
  - Every path from a node to its descendants contains the same number of black nodes.
- **Time Complexity**:
  - Search, Insert, Delete: `O(log n)`

##### 2-3 Tree
- **Definition**: A self-balancing search tree where each node can have 2 or 3 children and 1 or 2 keys.
- **Properties**:
  - All leaves are at the same level.
  - Operations split or merge nodes to maintain balance.
- **Time Complexity**:
  - Search, Insert, Delete: `O(log n)`

##### B-Tree
- **Definition**: A generalized BST used in databases and file systems. Each node can have multiple keys and children.
- **Properties**:
  - Ensures balanced height.
  - Minimizes disk I/O operations.
- **Time Complexity**:
  - Search, Insert, Delete: `O(log n)`

##### Splay Tree
- **Definition**: A BST that moves accessed nodes to the root using rotations.
- **Properties**:
  - Improves access time for frequently accessed elements.
- **Time Complexity**:
  - Amortized `O(log n)` for operations.

#### Summary
Different types of binary trees and specialized BSTs ensure efficient operations and adaptability to specific use cases, such as balancing, memory optimization, or frequent element access.





### Time Complexities of Operations

| **Tree Type**         | **Operation** | **Best Case** | **Average Case** | **Worst Case** |
|------------------------|---------------|---------------|------------------|----------------|
| **Binary Search Tree** | Search        | O(log n)      | O(log n)         | O(n)           |
|                        | Insert        | O(log n)      | O(log n)         | O(n)           |
|                        | Delete        | O(log n)      | O(log n)         | O(n)           |
| **AVL Tree**           | Search        | O(log n)      | O(log n)         | O(log n)       |
|                        | Insert        | O(log n)      | O(log n)         | O(log n)       |
|                        | Delete        | O(log n)      | O(log n)         | O(log n)       |
| **Red-Black Tree**     | Search        | O(log n)      | O(log n)         | O(log n)       |
|                        | Insert        | O(log n)      | O(log n)         | O(log n)       |
|                        | Delete        | O(log n)      | O(log n)         | O(log n)       |
| **2-3 Tree**           | Search        | O(log n)      | O(log n)         | O(log n)       |
|                        | Insert        | O(log n)      | O(log n)         | O(log n)       |
|                        | Delete        | O(log n)      | O(log n)         | O(log n)       |
| **B-Tree**             | Search        | O(log n)      | O(log n)         | O(log n)       |
|                        | Insert        | O(log n)      | O(log n)         | O(log n)       |
|                        | Delete        | O(log n)      | O(log n)         | O(log n)       |
| **Splay Tree**         | Search        | O(1)          | Amortized O(log n) | Amortized O(log n) |
|                        | Insert        | O(1)          | Amortized O(log n) | Amortized O(log n) |
|                        | Delete        | O(1)          | Amortized O(log n) | Amortized O(log n) |

#### Notes:
- **Binary Search Tree**: Worst case occurs when the tree is skewed.
- **AVL Tree and Red-Black Tree**: Guarantee balanced height, ensuring logarithmic time complexity.
- **Splay Tree**: Provides faster access for frequently used elements due to self-adjusting properties.
- **B-Trees and 2-3 Trees**: Ideal for systems requiring efficient disk I/O due to balanced and compact structures.

#### Summary
Different types of binary trees and specialized BSTs ensure efficient operations and adaptability to specific use cases, such as balancing, memory optimization, or frequent element access.


### All Traversals
[Back to Top](#table-of-contents)
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




### Binary Search Tree
[Back to Top](#table-of-contents)
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


#### Insert into a Binary Search Tree
##### Pattern: Binary Search / Recursion
[Back to Top](#table-of-contents)

##### Description:
- **Input:** Root of a binary search tree and a value to be inserted.  
  Example: `root = [4,2,7,1,3], val = 5`
- **Output:** A binary search tree with the new value inserted.  
  Example: `[4,2,7,1,3,5]`
- **Explanation:** Insert the value `5` while maintaining the binary search tree property.

```java
// Class definition for TreeNode
class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class InsertIntoBST {

    // Function to insert a value into a BST
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // If root is null, create a new TreeNode
        if (root == null) {
            return new TreeNode(val);
        }

        // Traverse the tree
        if (val < root.val) {
            // Value goes to the left subtree
            root.left = insertIntoBST(root.left, val);
        } else {
            // Value goes to the right subtree
            root.right = insertIntoBST(root.right, val);
        }

        // Return the root after insertion
        return root;
    }

    // Helper method to print the tree (optional, for testing purposes)
    public void inOrderTraversal(TreeNode root) {
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.print(root.val + " ");
            inOrderTraversal(root.right);
        }
    }

    // Main method to test the insertion
    public static void main(String[] args) {
        InsertIntoBST bst = new InsertIntoBST();
        
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        System.out.println("Before insertion:");
        bst.inOrderTraversal(root);

        int val = 5;
        bst.insertIntoBST(root, val);

        System.out.println("\nAfter insertion:");
        bst.inOrderTraversal(root);
    }
}
```

##### Time Complexity:
- **O(H):** Where H is the height of the tree. In a balanced tree, H = log(n); in the worst case, H = n for a skewed tree.

##### Space Complexity:
- **O(H):** Recursive calls add to the stack space, proportional to the height of the tree.

##### Algorithm:
- **Step 1:** Check if the root is null. If yes, create a new node with the given value.
- **Step 2:** Compare the value with the root's value.
  - If the value is smaller, move to the left subtree.
  - If the value is larger, move to the right subtree.
- **Step 3:** Recursively insert the value into the appropriate subtree.
- **Step 4:** Return the root node after the insertion.
***


#### Delete Node in a BST
##### Pattern: Binary Search Tree
[Back to Top](#table-of-contents)

##### Description:
- **Input:**  
  A binary search tree (BST) and a key to delete.  
  Example:
  ```
  Tree: [5, 3, 6, 2, 4, null, 7], Key to delete: 3  
  ```
- **Output:**  
  The modified BST after deleting the key.  
  Example:
  ```
  Resulting Tree: [5, 4, 6, 2, null, null, 7]  
  ```
- **Explanation:**  
  The node with key `3` is deleted, and its right child replaces it to maintain the BST property.

```java
// Java program to delete a node in a Binary Search Tree (BST).
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class DeleteNodeInBST {

    // Function to delete a node from the BST.
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null; // Base case: if the tree is empty.
        }

        if (key < root.val) {
            // Key is smaller, go left.
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            // Key is larger, go right.
            root.right = deleteNode(root.right, key);
        } else {
            // Found the node to delete.
            if (root.left == null) {
                return root.right; // Replace with right subtree if left is null.
            } else if (root.right == null) {
                return root.left; // Replace with left subtree if right is null.
            }
            // Node has two children: Find the inorder successor (smallest in right subtree).
            root.val = findMin(root.right);
            // Delete the inorder successor.
            root.right = deleteNode(root.right, root.val);
        }
        return root; // Return the updated root.
    }

    // Helper function to find the minimum value in a BST.
    private int findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left; // Go left to find the smallest value.
        }
        return node.val;
    }

    // Example usage.
    public static void main(String[] args) {
        DeleteNodeInBST bst = new DeleteNodeInBST();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);

        System.out.println("Before Deletion:");
        printTree(root);

        int key = 3;
        root = bst.deleteNode(root, key);

        System.out.println("\nAfter Deletion:");
        printTree(root);
    }

    // Helper function to print the tree (Inorder Traversal).
    private static void printTree(TreeNode root) {
        if (root != null) {
            printTree(root.left);
            System.out.print(root.val + " ");
            printTree(root.right);
        }
    }
}
```

##### Time Complexity:
- **O(h):** Traverses the height of the BST (worst case for a skewed tree: `h = n`).

##### Space Complexity:
- **O(h):** Recursion stack depth equal to the height of the tree.

##### Algorithm:
- Traverse the tree to locate the key node:
  - If `key < root.val`, move to the left subtree.
  - If `key > root.val`, move to the right subtree.
- Once the node is found:
  - **No child or one child:** Replace the node with its child.
  - **Two children:** Replace the node value with the inorder successor, then delete the successor node.
 ***


#### Invert a Binary Tree
##### Pattern: Tree Traversal
[Back to Top](#table-of-contents)

##### Description:
- **Input:** Root of a binary tree.
  ```
      4
     / \
    2   7
   / \ / \
  1  3 6  9
  ```
- **Output:** Binary tree with left and right children of all nodes swapped.
  ```
      4
     / \
    7   2
   / \ / \
  9  6 3  1
  ```
- **Explanation:** Each subtree is recursively inverted by swapping left and right children.

```java
// Java program to invert a binary tree.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class InvertBinaryTree {
    // Function to invert the binary tree
    public TreeNode invertTree(TreeNode root) {
        // Base case: if the tree is empty, return null
        if (root == null) return null;

        // Swap the left and right children
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // Recursively invert left and right subtrees
        invertTree(root.left);
        invertTree(root.right);

        // Return the root of the inverted tree
        return root;
    }

    // Helper function to print the tree (Level Order Traversal)
    public void printTree(TreeNode root) {
        if (root == null) return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            System.out.print(current.val + " ");

            if (current.left != null) queue.offer(current.left);
            if (current.right != null) queue.offer(current.right);
        }
    }

    // Main method to test the program
    public static void main(String[] args) {
        InvertBinaryTree tree = new InvertBinaryTree();

        // Create a sample binary tree
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        System.out.println("Original Tree:");
        tree.printTree(root);

        // Invert the tree
        tree.invertTree(root);

        System.out.println("\nInverted Tree:");
        tree.printTree(root);
    }
}
```

##### Time Complexity:
- **O(n):** Each node in the tree is visited once.

##### Space Complexity:
- **O(h):** Space used by the recursion stack, where `h` is the height of the tree.

##### Algorithm:
- **Step 1:** Start from the root.
- **Step 2:** Swap its left and right child nodes.
- **Step 3:** Recursively call the function for the left and right subtrees.
- **Step 4:** Continue until all nodes are visited (base case: null node).

[Back to Top](#table-of-contents)

***

### Tree Properties
[Back to Top](#table-of-contents)
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


#### Balanced Binary Tree
##### Pattern: Tree Traversal, Divide and Conquer
[Back to Top](#table-of-contents)

##### Description:
- **Input:** A binary tree.
- **Output:** `true` if the tree is balanced, `false` otherwise.
- **Explanation:** A balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differs by more than one.

```java
// Java program to check if a binary tree is balanced.
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

public class BalancedBinaryTree {
    
    // Function to determine if a tree is balanced
    public boolean isBalanced(TreeNode root) {
        // Helper function to check the height
        return checkHeight(root) != -1;
    }
    
    private int checkHeight(TreeNode node) {
        if (node == null) {
            return 0; // Base case: height of an empty tree is 0
        }
        
        // Recursively get the height of the left subtree
        int leftHeight = checkHeight(node.left);
        if (leftHeight == -1) {
            return -1; // Left subtree is not balanced
        }
        
        // Recursively get the height of the right subtree
        int rightHeight = checkHeight(node.right);
        if (rightHeight == -1) {
            return -1; // Right subtree is not balanced
        }
        
        // Check the difference in heights
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1; // Current subtree is not balanced
        }
        
        // Return the height of the current node
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        // Example: Constructing a tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        
        BalancedBinaryTree tree = new BalancedBinaryTree();
        
        // Check if the tree is balanced
        System.out.println(tree.isBalanced(root)); // Output: true
    }
}
```

##### Time Complexity:
- **O(n):** Each node is visited once in the tree.

##### Space Complexity:
- **O(h):** Where `h` is the height of the tree due to the recursion stack.

##### Algorithm
- Start at the root of the tree.
- For each node, recursively calculate the height of the left and right subtrees.
- If the difference in heights of the left and right subtrees exceeds 1, mark the tree as unbalanced by returning -1.
- Return the height of the current node as `max(leftHeight, rightHeight) + 1`.
- Traverse all nodes to check the balance condition recursively.
***

#### Diameter of Binary Tree
##### Pattern: Tree Traversal
[Back to Top](#table-of-contents)

##### Description:
- **Input**: A binary tree.
- **Output**: The length of the diameter (number of nodes on the longest path between two nodes).
- **Explanation**: The diameter of a binary tree is the longest path between any two nodes in the tree.

##### Example:
- Input:
```
        1
       / \
      2   3
     / \
    4   5
```
- Output: 3
- Explanation: The longest path is `4 → 2 → 1 → 3`.

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

public class DiameterOfBinaryTree {
    // Variable to store the diameter
    private int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        // Helper function to calculate height and update diameter
        calculateHeight(root);
        return diameter; // Return the diameter
    }

    private int calculateHeight(TreeNode node) {
        if (node == null) {
            return 0; // Base case: height of null node is 0
        }

        // Recursively calculate the height of left and right subtrees
        int leftHeight = calculateHeight(node.left);
        int rightHeight = calculateHeight(node.right);

        // Update the diameter: longest path through this node
        diameter = Math.max(diameter, leftHeight + rightHeight);

        // Return the height of the current node
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public static void main(String[] args) {
        // Example usage
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        DiameterOfBinaryTree solution = new DiameterOfBinaryTree();
        System.out.println("Diameter of the tree: " + solution.diameterOfBinaryTree(root));
    }
}
```

##### Time Complexity:
- **O(n)**: Each node is visited once to compute its height and update the diameter.

##### Space Complexity:
- **O(h)**: Recursive stack space, where `h` is the height of the tree.

##### Algorithm:
- **Initialize**: Start with `diameter = 0`.
- **Recursively compute height**:
  - Base case: Null node has height 0.
  - Recursive case: Height is `1 + max(leftHeight, rightHeight)`.
- **Update diameter**: For each node, update `diameter = max(diameter, leftHeight + rightHeight)`.
- **Pointers updated**:
  - Update `leftHeight` and `rightHeight` while traversing the tree.
  - Keep track of the maximum diameter encountered so far.
***

#### Maximum Depth of Binary
##### Same as the height of the Binary Tree
##### Pattern: Depth-First Search (DFS)
[Back to Top](#table-of-contents)

##### Description:
- **Input:** A binary tree root node, e.g., `TreeNode root = [3, 9, 20, null, null, 15, 7]`.
- **Output:** The maximum depth of the tree, e.g., `3`.
- **Explanation:** The maximum depth is the length of the longest path from the root to a leaf node.

```java
// Definition for a binary tree node
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class MaximumDepthBinaryTree {
    // Method to calculate maximum depth of a binary tree
    public int maxDepth(TreeNode root) {
        // Base case: If the node is null, depth is 0
        if (root == null) {
            return 0;
        }
        
        // Recursive call to calculate the depth of left and right subtrees
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        
        // Return the maximum of left and right depths, plus 1 for the current node
        return Math.max(leftDepth, rightDepth) + 1;
    }

    // Example Usage
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20, new TreeNode(15), new TreeNode(7));
        
        MaximumDepthBinaryTree solution = new MaximumDepthBinaryTree();
        System.out.println("Maximum Depth: " + solution.maxDepth(root)); // Output: 3
    }
}
```

##### Time Complexity:
- **O(N):** We visit every node once.

##### Space Complexity:
- **O(H):** The height of the tree, representing the recursion stack in the worst case.

##### Algorithm:
- Start at the root node.
- Recursively calculate the depth of the left and right subtrees.
- Take the maximum of these depths and add 1 (for the current node).
- Base case: If a node is `null`, return 0.
- Update pointers implicitly via recursive calls for left and right children.
***


### Tree BFS Traversal Problems
[Back to Top](#table-of-contents)
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



### Construct Binary Tree
[Back to Top](#table-of-contents)
#### Convert Sorted Array to Binary Search Tree
##### Pattern: Divide and Conquer
[Back to Top](#table-of-contents)

##### Description:
- **Input:** `nums = [-10, -3, 0, 5, 9]`
- **Output:** A height-balanced Binary Search Tree (BST).
- **Explanation:**  
  From the sorted array, pick the middle element as the root to ensure balance. Recursively apply this to left and right halves to construct the BST.

```java
// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
    }
}

public class SortedArrayToBST {

    // Main method to convert sorted array to BST
    public TreeNode sortedArrayToBST(int[] nums) {
        // Base case: return null if array is empty
        if (nums == null || nums.length == 0) {
            return null;
        }
        // Call the recursive helper function
        return constructBST(nums, 0, nums.length - 1);
    }

    // Helper function to construct BST
    private TreeNode constructBST(int[] nums, int left, int right) {
        // Base case: when left pointer exceeds right, return null
        if (left > right) {
            return null;
        }
        // Find the middle element
        int mid = left + (right - left) / 2;
        // Create the root node for this subtree
        TreeNode root = new TreeNode(nums[mid]);
        // Recursively build left and right subtrees
        root.left = constructBST(nums, left, mid - 1);
        root.right = constructBST(nums, mid + 1, right);
        return root;
    }
}
```

##### Time Complexity:
- **O(n):** We visit each node exactly once in the array.

##### Space Complexity:
- **O(log n):** Recursion stack depth is proportional to the height of the tree, which is log(n) for a balanced BST.

***

#### Construct Binary Tree from Preorder and Inorder Traversal
##### Pattern: Divide and Conquer
[Back to Top](#table-of-contents)
##### Description:
- **Input**:  
  Preorder = `[3, 9, 20, 15, 7]`  
  Inorder = `[9, 3, 15, 20, 7]`
- **Output**: A binary tree structured as follows:
```
       3
      / \
     9   20
        /  \
       15   7
```
- **Explanation**: The preorder array specifies root-first traversal, and the inorder array specifies left-root-right traversal. By combining the two, we reconstruct the tree.

```java
import java.util.HashMap;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) {
        this.val = val;
    }
}

public class ConstructBinaryTree {
    // Index to track the current root in preorder traversal
    private int preorderIndex = 0;
    // Map to store the index of each value in the inorder traversal
    private HashMap<Integer, Integer> inorderIndexMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Initialize the map with indices of inorder elements
        inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        // Start building the tree
        return construct(preorder, 0, inorder.length - 1);
    }

    private TreeNode construct(int[] preorder, int inorderStart, int inorderEnd) {
        // Base case: if the start index exceeds the end index
        if (inorderStart > inorderEnd) return null;

        // Create the root node with the current preorder element
        int rootValue = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootValue);

        // Find the index of the root value in the inorder traversal
        int rootIndexInInorder = inorderIndexMap.get(rootValue);

        // Recursively construct the left and right subtrees
        root.left = construct(preorder, inorderStart, rootIndexInInorder - 1);
        root.right = construct(preorder, rootIndexInInorder + 1, inorderEnd);

        return root;
    }
}
```
##### Time Complexity:
- **O(n)**: Each node is processed once, and the hashmap provides O(1) access for the indices.

##### Space Complexity:
- **O(n)**: For the hashmap and the recursion stack.

##### Algorithm:
- Create a hashmap to store the indices of elements in the inorder traversal for O(1) lookups.
- Use a global `preorderIndex` to track the root in the preorder array.
- Recursively:
  - Use the current preorder value as the root.
  - Divide the inorder range into left and right subtrees based on the root's index.
  - Update pointers:
    - Move `preorderIndex` forward for the next root.
    - Adjust `inorderStart` and `inorderEnd` for subtree boundaries.
***


### De-Construct Binary Tree
[Back to Top](#table-of-contents)
#### Flatten Binary Tree to Linked List
##### Pattern: Tree Traversal
[Back to Top](#table-of-contents)
##### Description:
- **Input:** A binary tree (e.g., root node of a binary tree).
- **Output:** A flattened tree that represents a singly linked list using the tree's right pointers.
- **Explanation:** The binary tree is transformed into a flattened linked list where each node's left pointer is `null`, and nodes are linked in pre-order traversal order.

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

public class FlattenBinaryTree {
    // Function to flatten the binary tree
    public void flatten(TreeNode root) {
        // Base condition: If root is null, return
        if (root == null) return;

        // Flatten the left and right subtrees
        flatten(root.left);
        flatten(root.right);

        // Store the right subtree
        TreeNode tempRight = root.right;

        // Move the left subtree to the right
        root.right = root.left;
        root.left = null;

        // Find the tail of the new right subtree and connect the original right subtree
        TreeNode current = root;
        while (current.right != null) {
            current = current.right;
        }
        current.right = tempRight;
    }
}
```

##### Time Complexity:
- **O(n):** Each node is visited once during the recursion.

##### Space Complexity:
- **O(h):** Recursive stack space where `h` is the height of the tree.

##### Algorithm
- Perform a **post-order traversal** of the tree (process left, right, root).
- Flatten the left and right subtrees recursively.
- Reassign the right pointer of the current node to its left subtree, and set the left pointer to `null`.
- Traverse the new right subtree to find its tail and attach the original right subtree.

***

#### Serialize and Deserialize Binary Tree
##### Pattern: Tree Traversal
[Back to Top](#table-of-contents)
##### Description:
- **Input**: A binary tree
- **Output**: A serialized string representation of the tree and the deserialized binary tree reconstructed from the string
- **Explanation**: Convert a binary tree to a string (serialization) and reconstruct it back to the tree (deserialization).

```java
import java.util.*;

public class Codec {

    // Serialize a binary tree to a string
    public String serialize(TreeNode root) {
        if (root == null) return "null";
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        StringBuilder serialized = new StringBuilder();
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                serialized.append("null,");
            } else {
                serialized.append(node.val).append(",");
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        return serialized.toString();
    }

    // Deserialize a string back to a binary tree
    public TreeNode deserialize(String data) {
        if (data.equals("null")) return null;
        String[] values = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        
        while (!queue.isEmpty() && i < values.length) {
            TreeNode node = queue.poll();
            if (!values[i].equals("null")) {
                node.left = new TreeNode(Integer.parseInt(values[i]));
                queue.add(node.left);
            }
            i++;
            if (i < values.length && !values[i].equals("null")) {
                node.right = new TreeNode(Integer.parseInt(values[i]));
                queue.add(node.right);
            }
            i++;
        }
        return root;
    }
}

// TreeNode class definition
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
```

##### Time Complexity:
- **Serialization**: \(O(n)\), where \(n\) is the number of nodes in the tree. We traverse each node once.
- **Deserialization**: \(O(n)\), as we process each node to reconstruct the tree.

##### Space Complexity:
- **Serialization**: \(O(n)\), for the queue and the resulting string.
- **Deserialization**: \(O(n)\), for the queue and tree construction.

##### Algorithm
- **Serialization**:
  1. Use a queue for level-order traversal.
  2. Append node values to a string, marking `null` for empty nodes.
  3. Return the serialized string.

- **Deserialization**:
  1. Split the string into an array of values.
  2. Use a queue to track nodes at the current level.
  3. Recreate the tree level by level, adding children for each node.
***





### Tree Path Sum
[Back to Top](#table-of-contents)

#### Path Sum in Binary Tree
##### Pattern: Tree Traversal
[Back to Top](#table-of-contents)

##### Description:
- **Input:** A binary tree and a target sum.
- **Output:** Boolean indicating whether the tree has a root-to-leaf path whose values sum to the target.
- **Explanation:** Check if there exists any path from the root to a leaf where the sum of node values equals the target.

```java
// Java Program to Check Path Sum in Binary Tree
public class PathSum {

    // Definition for a binary tree node
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * Method to check if the binary tree has a path with the target sum.
     * @param root - root of the binary tree
     * @param targetSum - target sum to check
     * @return true if such a path exists, otherwise false
     */
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        // Base Case: If the node is null, return false
        if (root == null) return false;

        // If leaf node is reached, check if targetSum equals node value
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }

        // Recursively check for the left and right subtrees
        // Reduce the targetSum by the current node's value
        return hasPathSum(root.left, targetSum - root.val) ||
               hasPathSum(root.right, targetSum - root.val);
    }

    // Example Usage
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);

        int targetSum = 22;
        System.out.println("Does the path sum exist? " + hasPathSum(root, targetSum));
    }
}
```

##### Time Complexity:
- **O(N):** We visit each node once in the binary tree.

##### Space Complexity:
- **O(H):** Where H is the height of the tree, for recursive call stack.

***

#### Path Sum II
##### Pattern: Backtracking
[Back to Top](#table-of-contents)
##### Description:
- Input: A binary tree root and an integer target sum.
- Output: List of all root-to-leaf paths where the sum equals the target sum.
- Explanation: Each path is a list of node values, where adding them results in the target sum.

```java
import java.util.ArrayList;
import java.util.List;

public class PathSumII {
    // Function to find all root-to-leaf paths with the target sum
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>(); // To store all paths
        List<Integer> currentPath = new ArrayList<>();  // To store the current path
        findPaths(root, targetSum, currentPath, result); // Start backtracking
        return result;
    }

    private void findPaths(TreeNode node, int remainingSum, List<Integer> currentPath, List<List<Integer>> result) {
        if (node == null) { // Base case: If node is null, return
            return;
        }

        // Add current node to the path
        currentPath.add(node.val);

        // Check if the current node is a leaf and path sum equals target sum
        if (node.left == null && node.right == null && remainingSum == node.val) {
            result.add(new ArrayList<>(currentPath)); // Add the current path to the result
        } else {
            // Recur for left and right subtrees
            findPaths(node.left, remainingSum - node.val, currentPath, result);
            findPaths(node.right, remainingSum - node.val, currentPath, result);
        }

        // Backtrack: Remove the current node from the path
        currentPath.remove(currentPath.size() - 1);
    }
}

// Definition for a binary tree node
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}
```

##### Time Complexity:
- **O(n)**: Each node is visited once.

##### Space Complexity:
- **O(h)**: Where `h` is the height of the tree, for the recursion stack and the path list.
***

#### Binary Tree Maximum Path Sum
##### Pattern: Divide and Conquer
[Back to Top](#table-of-contents)

##### Description:
- **Input**: A binary tree with integer values for each node.
- **Output**: Maximum path sum in the binary tree.
- **Explanation**: A path can start and end at any node in the tree. The output is the sum of the path with the highest value.

```java
// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class BinaryTreeMaximumPathSum {
    // Initialize a variable to store the maximum path sum.
    private static int maxSum;

    public static int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE; // Start with the lowest possible integer value.
        calculatePathSum(root);    // Call the helper function.
        return maxSum;             // Return the maximum path sum.
    }

    private static int calculatePathSum(TreeNode node) {
        if (node == null) return 0; // Base case: return 0 for null nodes.

        // Recursively calculate the max path sum for left and right subtrees.
        int leftMax = Math.max(calculatePathSum(node.left), 0);
        int rightMax = Math.max(calculatePathSum(node.right), 0);

        // Update the global maximum path sum.
        maxSum = Math.max(maxSum, leftMax + rightMax + node.val);

        // Return the maximum sum of the current path including this node.
        return Math.max(leftMax, rightMax) + node.val;
    }
}
```

##### Time Complexity:
- **O(n)**: Each node is visited once.

##### Space Complexity:
- **O(h)**: Space for the recursion stack, where `h` is the height of the tree.

***





### Trees in Java
[Back to Top](#table-of-contents)

#### Java Tree Package Hierarchy

In Java, trees can be implemented using various classes provided by the **Java Collections Framework** or through custom implementations.  
Java's built-in tree data structures primarily follow a sorted order (such as Red-Black trees) and provide various operations.  
Trees can be classified into **sorted** and **unsorted** trees, and Java provides both kinds for different use cases.

##### Sorted Trees
Sorted trees maintain a specific order of elements, typically in ascending or descending order.  
These trees are useful when you need elements in a predictable order and require efficient searching, insertion, and deletion operations.

###### 1. **TreeMap (Red-Black Tree)**
A **TreeMap** is an implementation of the **Map** interface that uses a Red-Black tree (a type of self-balancing binary search tree) to store the keys in sorted order.

- **Class:** `java.util.TreeMap`
- **Key Features:**
  - Stores elements in a sorted order based on their natural ordering or a custom comparator.
  - Allows **null values** but does not allow **null keys**.
  - Provides efficient search, insertion, and deletion in O(log n) time.

- **Common Methods:**
  - `put(K key, V value)`: Inserts a key-value pair.
  - `get(Object key)`: Returns the value associated with the specified key.
  - `remove(Object key)`: Removes the key-value pair for the specified key.
  - `containsKey(Object key)`: Checks if the key is present.
  - `firstKey()`: Returns the first (smallest) key in the map.
  - `lastKey()`: Returns the last (largest) key in the map.
  - `keySet()`: Returns a set of the keys.
  - `size()`: Returns the number of key-value pairs in the map.
  - `subMap(K fromKey, K toKey)`: Returns a view of the portion of the map between the specified keys.

###### 2. **TreeSet (Red-Black Tree)**
A **TreeSet** is a Set implementation backed by a **TreeMap**. It maintains its elements in a sorted order, with no duplicate elements allowed.

- **Class:** `java.util.TreeSet`
- **Key Features:**
  - Stores unique elements in sorted order.
  - Allows **null elements** but only **one null element**.
  - Provides efficient operations like searching, insertion, and deletion in O(log n) time.

- **Common Methods:**
  - `add(E e)`: Adds an element to the set.
  - `remove(Object o)`: Removes an element from the set.
  - `contains(Object o)`: Checks if an element exists in the set.
  - `size()`: Returns the number of elements in the set.
  - `first()`: Returns the first (smallest) element.
  - `last()`: Returns the last (largest) element.
  - `headSet(E toElement)`: Returns a view of the set with elements strictly less than the given element.
  - `tailSet(E fromElement)`: Returns a view of the set with elements greater than or equal to the given element.

---

##### Unsorted Trees
Unsorted trees do not maintain any specific order of elements. They are typically used when fast insertion and deletion are needed, and the order of elements does not matter.

###### 1. **HashMap (Hash Table)**
A **HashMap** is a part of the Java Collections Framework that implements a hash table. It stores key-value pairs but does not maintain any order.

- **Class:** `java.util.HashMap`
- **Key Features:**
  - Keys and values are stored in a hash table, which provides constant-time performance for basic operations (O(1) time complexity).
  - Allows **null values** and **one null key**.
  - **Does not maintain order** of elements; entries can be retrieved in any order.

- **Common Methods:**
  - `put(K key, V value)`: Inserts a key-value pair.
  - `get(Object key)`: Retrieves the value associated with the given key.
  - `remove(Object key)`: Removes the key-value pair for the given key.
  - `containsKey(Object key)`: Checks if the key is present.
  - `size()`: Returns the number of entries in the map.
  - `clear()`: Removes all entries from the map.
  - `keySet()`: Returns a set of all keys.
  - `values()`: Returns a collection of all values.

###### 2. **HashSet (Hash Table)**
A **HashSet** is a Set implementation backed by a **HashMap**. It stores unique elements, and it does not guarantee any specific order of elements.

- **Class:** `java.util.HashSet`
- **Key Features:**
  - Stores unique elements in a hash table.
  - **Does not maintain order**; elements can be retrieved in any order.
  - Provides O(1) time complexity for basic operations like insertion, removal, and checking existence.

- **Common Methods:**
  - `add(E e)`: Adds an element to the set.
  - `remove(Object o)`: Removes an element from the set.
  - `contains(Object o)`: Checks if an element exists in the set.
  - `size()`: Returns the number of elements in the set.
  - `clear()`: Removes all elements from the set.

---

[Back to Top](#table-of-contents)

#### Common Tree Operations and Algorithms in Java
[Back to Top](#table-of-contents)

##### 1. **Traversal Operations**

Tree traversal refers to the process of visiting all the nodes in a tree. There are three main types of traversal:

###### **In-order Traversal (LNR)**
- Visit the left subtree.
- Visit the node.
- Visit the right subtree.

```java
public void inorder(TreeNode node) {
    if (node != null) {
        inorder(node.left);
        System.out.print(node.value + " ");
        inorder(node.right);
    }
}
```

###### **Pre-order Traversal (NLR)**
- Visit the node.
- Visit the left subtree.
- Visit the right subtree.

```java
public void preorder(TreeNode node) {
    if (node != null) {
        System.out.print(node.value + " ");
        preorder(node.left);
        preorder(node.right);
    }
}
```

###### **Post-order Traversal (LRN)**
- Visit the left subtree.
- Visit the right subtree.
- Visit the node.

```java
public void postorder(TreeNode node) {
    if (node != null) {
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.value + " ");
    }
}
```
[Back to Top](#table-of-contents)

##### 2. **Insertion and Deletion Operations**
Inserting or deleting nodes in a tree requires finding the correct position for the new node or removing a node while maintaining the tree’s structure.

- **Insertion in BST:**
  - Start at the root.
  - Traverse left if the new value is smaller than the current node.
  - Traverse right if the new value is greater than the current node.
  - Insert the node at the correct position.

```java
public TreeNode insert(TreeNode root, int value) {
    if (root == null) {
        return new TreeNode(value);
    }
    if (value < root.value) {
        root.left = insert(root.left, value);
    } else {
        root.right = insert(root.right, value);
    }
    return root;
}
```

- **Deletion in BST:**
  - Find the node to delete.
  - If the node has no children, simply remove it.
  - If the node has one child, replace it with the child.
  - If the node has two children, find the in-order successor (the smallest node in the right subtree), replace the node with the successor, and delete the successor.

```java
public TreeNode delete(TreeNode root, int value) {
    if (root == null) return null;
    if (value < root.value) {
        root.left = delete(root.left, value);
    } else if (value > root.value) {
        root.right = delete(root.right, value);
    } else {
        if (root.left == null) return root.right;
        if (root.right == null) return root.left;
        TreeNode minNode = findMin(root.right);
        root.value = minNode.value;
        root.right = delete(root.right, minNode.value);
    }
    return root;
}
```
[Back to Top](#table-of-contents)

##### 3. **Searching in Trees**

Searching in a tree is typically performed by comparing the value to be searched with the node’s value and traversing the left or right subtree accordingly.

```java
public TreeNode search(TreeNode root, int value) {
    if (root == null || root.value == value) {
        return root;
    }
    if (value < root.value) {
        return search(root.left, value);
    } else {
        return search(root.right, value);
    }
}
```

---
[Back to Top](#table-of-contents)

#### Time Complexities of Tree Operations

| Operation             | AVL Tree / Red-Black Tree | Binary Search Tree | 2-3 Tree |
|-----------------------|---------------------------|--------------------|----------|
| Search                | O(log n)                  | O(n)               | O(log n) |
| Insert                | O(log n)                  | O(n)               | O(log n) |
| Delete                | O(log n)                  | O(n)               | O(log n) |
| Traversal (In-order)  | O(n)                      | O(n)               | O(n)     |
| Balanced Check        | O(n)                      | O(n)               | O(n)     |

---
[Back to Top](#table-of-contents)

#### Conclusion
Java offers powerful built-in support for managing trees through classes like **TreeMap**, **TreeSet**, **HashMap**, and **HashSet**. These classes allow developers to handle trees efficiently for various use cases, such as maintaining ordered collections, ensuring uniqueness, or performing fast lookups. By understanding the tree data structure, its various types, and common operations, developers can leverage trees to solve complex problems efficiently.
