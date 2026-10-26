import java.util.*;

public class Q01LongestSubarraySumK {
    static int longestSubarray(int[] numbers, int target) {
        HashMap<Integer, Integer> firstSeen = new HashMap<>();
        int sum = 0, maxLength = 0;
        firstSeen.put(0, -1);

        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
            if (!firstSeen.containsKey(sum)) firstSeen.put(sum, i);
            if (firstSeen.containsKey(sum - target)) {
                int length = i - firstSeen.get(sum - target);
                if (length > maxLength) maxLength = length;
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        int size = obj.nextInt();
        int[] numbers = new int[size];
        for (int i = 0; i < size; i++) numbers[i] = obj.nextInt();
        int target = obj.nextInt();
        System.out.println(longestSubarray(numbers, target));
    }
}
