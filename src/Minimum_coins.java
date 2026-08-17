import java.util.Arrays;

public class Minimum_coins {
    //Using recursion
    public int coinChangerec(int[] coins, int amount) {
        int n = coins.length;
        int ans = mincoinsrec(n - 1, amount, coins);
        return ans >= (int) 1e9 ? -1 : ans;
    }

    private int mincoinsrec(int index, int amount, int[] coins) {
        if (amount == 0) return 0;
        if (index == 0) {
            if (amount % coins[0] == 0)
                return amount / coins[0];
            return (int) 1e9;
        }

        int nottake = mincoinsrec(index - 1, amount, coins);
        int take = (int) 1e9;
        if (coins[index] <= amount)
            take = 1 + mincoinsrec(index, amount - coins[index], coins);

        return Math.min(nottake, take);
    }


    //Using Memoization
    public int coinChangememo(int[] coins, int amount){
        int n=coins.length;
        int[][] dp=new int[n][amount+1];
        for (int[] num: dp)
         Arrays.fill(num, -1);
        int ans = mincoinsmemo(n - 1, amount, coins, dp);
        return ans >= (int) 1e9 ? -1 : ans;
    }

    private int mincoinsmemo(int index, int amount, int[] coins, int[][] dp) {
        if (amount == 0) return 0;
        if (index == 0) {
            if (amount % coins[0] == 0)
                return amount / coins[0];
            return (int) 1e9;
        }

        if(dp[index][amount]!=-1)
            return dp[index][amount];


        int nottake = mincoinsmemo(index - 1, amount, coins, dp);
        int take = (int) 1e9;
        if (coins[index] <= amount)
            take = 1 + mincoinsmemo(index, amount - coins[index], coins, dp);

        return dp[index][amount]=Math.min(nottake, take);
    }



    //using tabulation
    public int coinChangetab(int[] coins, int amount){
        int n=coins.length;
        int[][] dp=new int[n][amount+1];

        for (int i = 0; i < n; i++) {
            dp[i][0]=0;
        }
        for (int amt = 1; amt <=amount ; amt++) {
            if (amt % coins[0] == 0){
                dp[0][amt]=amt/coins[0];
            }else{
                dp[0][amt]=(int)1e9;
            }
        }

        for (int ind = 1; ind < n; ind++) {
            for (int amt = 1; amt <= amount; amt++) {
                int nottake=dp[ind-1][amt];
                int take=(int)1e9;
                if (coins[ind]<=amt)
                    take=1+dp[ind][amt-coins[ind]];
                dp[ind][amt]=Math.min(nottake, take);
            }
        }
        return dp[n-1][amount]>= (int) 1e9 ? -1 : dp[n-1][amount];
    }


    //using space optimization
    public int coinChange(int[] coins, int amount){
        int n=coins.length;
        int[] prev=new int[amount+1];

        prev[0]=0;
        for (int amt = 1; amt <=amount ; amt++) {
            if (amt % coins[0] == 0){
                prev[amt]=amt/coins[0];
            }else{
                prev[amt]=(int)1e9;
            }
        }


        for (int ind = 1; ind < n; ind++) {
            int[] curr=new int[amount+1];
            for (int amt = 1; amt <= amount; amt++) {
                int nottake=prev[amt];
                int take=(int)1e9;
                if (coins[ind]<=amt)
                    take=1+curr[amt-coins[ind]];
                curr[amt]=Math.min(nottake, take);
            }
            prev=curr;
        }
        return prev[amount]>= (int) 1e9 ? -1 : prev[amount];
    }
}
