import java.util.Arrays;

public class Minimum_Coins_II {
    //using Recursion
    public int changerec(int amount, int[] coins) {
        int n=coins.length;
        return mincoinsrec(n-1, amount, coins);
    }
    private int mincoinsrec(int index, int amount, int[] coins){
        if(amount==0)
            return 1;

        if(index==0){
            if(amount%coins[index]==0)
                return 1;
            return 0;
        }

        int nottake=mincoinsrec(index-1, amount, coins);
        int take=0;
        if(coins[index]<=amount)
            take=mincoinsrec(index, amount-coins[index], coins);

        return nottake+take;
    }

    //using memoization
    public int changememo(int amount, int[] coins) {
        int n=coins.length;
        int[][] dp=new int[n][amount+1];
        for (int[] num: dp)
            Arrays.fill(num, -1);
        return mincoinsmemo(n-1, amount, coins, dp);
    }
    private int mincoinsmemo(int index, int amount, int[] coins, int[][] dp){
        if(amount==0)
            return 1;

        if(index==0){
            if(amount%coins[index]==0)
                return 1;
            return 0;
        }

        if (dp[index][amount]!=-1)
            return dp[index][amount];

        int nottake=mincoinsmemo(index-1, amount, coins, dp);
        int take=0;
        if(coins[index]<=amount)
            take=mincoinsmemo(index, amount-coins[index], coins, dp);

        return dp[index][amount]=nottake+take;
    }


    //Using Tabulation
    public int changetab(int amount, int[] coins){
        int n=coins.length;
        int[][] dp=new int[n][amount+1];

        for (int i = 0; i < n; i++) {
            dp[i][0]=1;
        }
        for (int amt = 1; amt <=amount ; amt++) {
            if (amt % coins[0] == 0){
                dp[0][amt]=1;
            }else{
                dp[0][amt]=0;
            }
        }

        for (int ind = 1; ind < n; ind++) {
            for (int amt = 1; amt <= amount; amt++) {
                int nottake=dp[ind-1][amt];
                int take=0;
                if (coins[ind]<=amt)
                    take=dp[ind][amt-coins[ind]];
                dp[ind][amt]=nottake+take;
            }
        }
        return dp[n-1][amount];
    }


    // Space opimization
    public int coinChange(int[] coins, int amount){
        int n=coins.length;
        int[] prev=new int[amount+1];

        prev[0]=1;

        for (int amt = 1; amt <=amount ; amt++) {
            if (amt % coins[0] == 0){
                prev[amt]=1;
            }
        }

        for (int ind = 1; ind < n; ind++) {
            int[] curr=new int[amount+1];
            curr[0] = 1;
            for (int amt = 1; amt <= amount; amt++) {
                int nottake=prev[amt];
                int take=0;
                if (coins[ind]<=amt)
                    take=curr[amt-coins[ind]];
                curr[amt]=nottake + take;
            }
            prev=curr;
        }
        return prev[amount];
    }
}
