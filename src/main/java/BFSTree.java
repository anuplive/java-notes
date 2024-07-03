import java.util.*;

class BFSTree {
    // Define a TreeNode
    public static class TreeNode{

        Integer data;
        TreeNode left;
        TreeNode right;

        public TreeNode(Integer input){
            this.data = input;
        }
        public TreeNode(){
        }
    }
    public List<Integer> levelOrderTraversal(TreeNode root){

        if (root == null )
            return new ArrayList<Integer>();

        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();

        q.offer(root);

        while (! q.isEmpty()){

            TreeNode current = q.poll();
            result.add(current.data);
            if (current.left != null)
                q.offer(current.left);
            if (current.right != null)
                q.offer(current.right);
        }

        return result;
    }

    public Object zigzagOrderTraversal(TreeNode root){

        if (root == null )
            return new ArrayList<Integer>();

        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> q = new LinkedList<>();
        boolean direction = true;
        q.offer(root);

        while (! q.isEmpty()){
            int qSize = q.size();
            for (int i = 0 ; i < qSize; i ++){
                TreeNode current = (direction) ? q.getFirst() : q.getLast();
                result.add(current.data);
                if (current.left != null)
                    q.offer(current.left);
                if (current.right != null)
                    q.offer(current.right);
            }
            direction = !direction;
        }
        Collections.reverse(result);
        return  result;
    }
}

