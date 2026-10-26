import java.util.*;

public class Q05LinkedListCycle {
    static class Node {
        int value;
        Node next;
        Node(int value) { this.value = value; }
    }

    static boolean removeCycle(Node head) {
        HashSet<Node> seen = new HashSet<>();
        Node current = head, previousNode = null;
        while (current != null) {
            if (seen.contains(current)) {
                previousNode.next = null;
                return true;
            }
            seen.add(current);
            previousNode = current;
            current = current.next;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        int size = obj.nextInt();
        Node[] nodes = new Node[size];
        for (int i = 0; i < size; i++) nodes[i] = new Node(obj.nextInt());
        for (int i = 0; i < size - 1; i++) nodes[i].next = nodes[i + 1];
        int cyclePosition = obj.nextInt();
        if (cyclePosition >= 0 && cyclePosition < size) nodes[size - 1].next = nodes[cyclePosition];
        System.out.println(removeCycle(nodes[0]) ? "Cycle removed" : "No cycle");
    }
}
