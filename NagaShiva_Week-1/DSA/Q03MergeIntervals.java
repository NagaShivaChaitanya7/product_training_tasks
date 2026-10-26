import java.util.*;

public class Q03MergeIntervals {
    static int[][] merge(int[][] meetings) {
        for (int i = 0; i < meetings.length - 1; i++) {
            for (int j = i + 1; j < meetings.length; j++) {
                if (meetings[i][0] > meetings[j][0]) {
                    int[] temp = meetings[i];
                    meetings[i] = meetings[j];
                    meetings[j] = temp;
                }
            }
        }
        ArrayList<int[]> result = new ArrayList<>();
        int start = meetings[0][0], end = meetings[0][1];

        for (int i = 1; i < meetings.length; i++) {
            if (meetings[i][0] <= end) {
                if (meetings[i][1] > end) end = meetings[i][1];
            } else {
                result.add(new int[]{start, end});
                start = meetings[i][0];
                end = meetings[i][1];
            }
        }
        result.add(new int[]{start, end});
        return result.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        int count = obj.nextInt();
        int[][] meetings = new int[count][2];
        for (int i = 0; i < count; i++) {
            meetings[i][0] = obj.nextInt();
            meetings[i][1] = obj.nextInt();
        }
        for (int[] meeting : merge(meetings)) {
            System.out.println(meeting[0] + " " + meeting[1]);
        }
    }
}
