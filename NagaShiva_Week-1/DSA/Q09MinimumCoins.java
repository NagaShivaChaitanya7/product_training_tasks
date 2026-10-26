import java.util.*;

public class MinimumCoins {
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -2);
        return solve(coins, amount, dp);
    }
    private static int solve(int[] coins, int amount, int[] dp) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        if (dp[amount] != -2) {
            return dp[amount];
        }
        int minCoins = Integer.MAX_VALUE;
        for (int coin : coins) {
            if (coin > 0) {
                int result = solve(coins, amount - coin, dp);
                if (result != -1) {
                    minCoins = Math.min(minCoins, result + 1);
                }
            }
        }
        dp[amount] = (minCoins == Integer.MAX_VALUE) ? -1 : minCoins;
        return dp[amount];
    }

    public static void main(String[] args) {
        System.out.println(coinChange(
            new int[]{1, 2, 5}, 11)); 
        System.out.println(coinChange(
            new int[]{2}, 3)); 
        System.out.println(coinChange(
            new int[]{1}, 0)); 

        System.out.println(coinChange(
            new int[]{1, 3, 4}, 6)); 

        System.out.println(coinChange(
            new int[]{2, 5, 10}, 27));
    }
}
