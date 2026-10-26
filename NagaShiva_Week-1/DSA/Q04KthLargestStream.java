import java.util.*;

public class Q04KthLargestStream {
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        int k = obj.nextInt();
        int count = obj.nextInt();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < count; i++) {
            minHeap.add(obj.nextInt());
            if (minHeap.size() > k) minHeap.poll();
            if (minHeap.size() == k) System.out.println(minHeap.peek());
            else System.out.println("Not enough elements");
        }
    }
}
