import java.util.*;

public class Q10RangeMaximumQuery {
    static int[] values;

    static void update(int index, int value) {
        values[index] = value;
    }

    static int maximum(int start, int end) {
        int largest = Integer.MIN_VALUE;
        for (int i = start; i <= end; i++) {
            if (values[i] > largest) largest = values[i];
        }
        return largest;
    }

    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        int size = obj.nextInt();
        values = new int[size];
        for (int i = 0; i < size; i++) values[i] = obj.nextInt();

        int operations = obj.nextInt();
        for (int i = 0; i < operations; i++) {
            int operation = obj.nextInt();
            if (operation == 1) {
                int index = obj.nextInt();
                int value = obj.nextInt();
                update(index, value);
            } else {
                int start = obj.nextInt();
                int end = obj.nextInt();
                System.out.println(maximum(start, end));
            }
        }
    }
}
