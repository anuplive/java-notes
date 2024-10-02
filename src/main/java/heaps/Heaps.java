package heaps;

import java.util.*;

public class Heaps {

    public static void main(String args[]){

    }


    // Last stone weight
      public static int lastStoneWeight(int[] stones) {
        // Check if stones array is empty or null
        if (stones == null || stones.length == 0) {
            return 0; // No stones, return 0
        }

        // Max heap using PriorityQueue
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        // Add all stones to the max heap
        for (int stone : stones) {
            maxHeap.offer(stone);
        }

        // Simulate the smashing process
        while (maxHeap.size() > 1) {
            int firstStone = maxHeap.poll();  // Heaviest stone
            int secondStone = maxHeap.poll(); // Second heaviest stone

            // If there is a difference, put the result back into the heap
            if (firstStone != secondStone) {
                maxHeap.offer(firstStone - secondStone);
            }
        }

        // Return the last remaining stone or 0 if none are left
        return maxHeap.isEmpty() ? 0 : maxHeap.poll();
    }

    // Find the Kth Largest Element

    public int findKthLargest(int[] nums, int k) {

        if (nums == null || nums.length < k)
            return -1;

        // We will cerate a min heap and store only K elements in that heap
        // When the K+1th element is inserted we will offer then pool from the heap

        PriorityQueue<Integer> queue = new PriorityQueue<>((a,b) -> a-b); // Comparator definition

        for (int element : nums){
            queue.offer(element);

            if (queue.size() > k)
                queue.poll();
        }
        return queue.poll();
    }

    // Find the top frequency element
    public int[] topKFrequent(int[] nums, int k) {

        if (nums == null || nums.length < k)
            return new int[]{};

        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        Map<Integer, Integer> map = new HashMap<>();

        for (int element : nums){
            map.put(element, map.getOrDefault(element, 0) + 1);
        }

        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            queue.offer(entry);
        }

        int [] result = new int[k];

        for (int i = 0; i < result.length ; i ++){
            result[i] = queue.poll().getKey();
        }
        return result;
    }

    // K closest point to the origin
    public int[][] kClosest(int[][] points, int k) {

        if (points == null || points.length < k)
            return new int[][]{};

        PriorityQueue<Map.Entry<Double , int[]>> queue = new PriorityQueue<>((a,b) -> a.getKey().compareTo(b.getKey()));

        for (int [] point : points){
            Double distance  = Math.pow(point[0], 2) + Math.pow(point[1], 2);
            queue.offer(new AbstractMap.SimpleEntry<Double, int[]>(distance, point));
        }

        int[][] result = new int[k][];

        for (int i = 0; i < k ; i ++){
            result[i] = queue.poll().getValue();
        }

        return result;
    }

    // Merge K sorted List
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    public ListNode mergeKLists(ListNode[] lists) {

        if (lists == null)
            return null;

        ListNode dummyHead = new ListNode();
        ListNode nodePointer = dummyHead;

        PriorityQueue<ListNode> queue = new PriorityQueue<>((n1, n2) -> n1.value - n2.value);

        queue.addAll(lists);

        while (!queue.isEmpty()){

            ListNode currentNode = queue.poll();

            nodePointer.next  = currentNode;
            nodePointer = nodePointer.next;

            if (currentNode.next != null)
                queue.offer(currentNode.next);

        }
        return dummyHead.next;
    }

}
