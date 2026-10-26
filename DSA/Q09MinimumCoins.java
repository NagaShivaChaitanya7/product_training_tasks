import java.util.*;

public class Q09MinimumCoins {
    static int minimumCoins(int[] coins, int target) {
        int[] best = new int[target + 1];
        for (int i = 0; i < best.length; i++) best[i] = target + 1;
        best[0] = 0;
        for (int value = 1; value <= target; value++) {
            for (int coin : coins) {
                if (coin <= value && best[value - coin] + 1 < best[value]) {
                    best[value] = best[value - coin] + 1;
                }
            }
        }
        return best[target] > target ? -1 : best[target];
    }

    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        int coinCount = obj.nextInt();
        int[] coins = new int[coinCount];
        for (int i = 0; i < coinCount; i++) coins[i] = obj.nextInt();
        int target = obj.nextInt();
        System.out.println(minimumCoins(coins, target));
    }
}
