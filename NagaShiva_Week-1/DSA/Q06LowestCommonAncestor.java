import java.util.*;

public class Q06LowestCommonAncestor {
    static class Node {
        int value;
        Node left, right;
        Node(int value) { this.value = value; }
    }

    static Node lca(Node root, int firstValue, int secondValue) {
        if (root == null || root.value == firstValue || root.value == secondValue) return root;
        Node left = lca(root.left, firstValue, secondValue);
        Node right = lca(root.right, firstValue, secondValue);
        if (left != null && right != null) return root;
        return left != null ? left : right;
    }

    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        int size = obj.nextInt();
        Node[] nodes = new Node[size];
        for (int i = 0; i < size; i++) nodes[i] = new Node(obj.nextInt());
        for (int i = 0; i < size; i++) {
            int leftIndex = obj.nextInt(), rightIndex = obj.nextInt();
            if (leftIndex != -1) nodes[i].left = nodes[leftIndex];
            if (rightIndex != -1) nodes[i].right = nodes[rightIndex];
        }
        int firstValue = obj.nextInt(), secondValue = obj.nextInt();
        Node answer = lca(nodes[0], firstValue, secondValue);
        System.out.println(answer == null ? "Not found" : answer.value);
    }
}
