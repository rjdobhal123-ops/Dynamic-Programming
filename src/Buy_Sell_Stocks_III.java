import java.util.Arrays;

public class Buy_Sell_Stocks_III {
    //Using Recursion
    public int maxProfitrec(int[] prices) {
        int n=prices.length;
        return recmaxProfit(0, 1, 2, prices, n);
    }
    public int recmaxProfit(int index, int buy, int countbuy, int[] prices, int n){
        if(countbuy==0)
            return 0;
        if(index==n)
            return 0;

        int profit=0;
        if(buy==1)
            profit=Math.max(-prices[index]+recmaxProfit(index+1, 0, countbuy, prices, n), recmaxProfit(index+1, 1, countbuy, prices, n));
        else
            profit=Math.max(prices[index]+recmaxProfit(index+1, 1, countbuy-1, prices, n), recmaxProfit(index+1, 0, countbuy, prices, n));

        return profit;
    }



    //using memoization
    public int maxProfitmemo(int[] prices) {
        int n=prices.length;
        int[][][] dp=new int[n][2][3];
        for (int[][] num: dp){
            for (int[] nums: num)
                Arrays.fill(nums, -1);
        }
        return memomaxProfit(0, 1, 2, prices, n, dp);
    }
    public int memomaxProfit(int index, int buy, int countbuy, int[] prices, int n, int[][][] dp){
        if(countbuy==0)
            return 0;
        if(index==n)
            return 0;
        if (dp[index][buy][countbuy]!=-1)
            return dp[index][buy][countbuy];

        int profit=0;
        if(buy==1)
            profit=Math.max(-prices[index]+memomaxProfit(index+1, 0, countbuy, prices, n, dp),
                    memomaxProfit(index+1, 1, countbuy, prices, n, dp));
        else
            profit=Math.max(prices[index]+memomaxProfit(index+1, 1, countbuy-1, prices, n, dp),
                    memomaxProfit(index+1, 0, countbuy, prices, n, dp));

        return dp[index][buy][countbuy]=profit;
    }


    //using tabulation
    public int maxProfittab(int[] prices) {
        int n=prices.length;
        int[][][] dp=new int[n+1][2][3];

        for (int index = n-1; index >=0 ; index--) {
            for (int buy = 1; buy >=0 ; buy--) {
                for (int countbuy = 2; countbuy >0 ; countbuy--) {
                    if(buy==1)
                        dp[index][buy][countbuy]=Math.max(-prices[index]+dp[index+1][0][countbuy],
                                dp[index+1][1][countbuy]);
                    else
                        dp[index][buy][countbuy]=Math.max(prices[index]+dp[index+1][1][countbuy-1],
                                dp[index+1][0][countbuy]);

                }
            }
        }
        return dp[0][1][2];
    }




    //using space optimization
    public int maxProfit(int[] prices) {
        int n=prices.length;
        int[][] next=new int[2][3];

        for (int index = n-1; index >=0 ; index--) {
            int[][] curr=new int[2][3];
            for (int buy = 1; buy >=0 ; buy--) {
                for (int countbuy = 2; countbuy >0 ; countbuy--) {
                    if(buy==1)
                       curr[buy][countbuy]=Math.max(-prices[index]+next[0][countbuy],
                                next[1][countbuy]);
                    else
                       curr[buy][countbuy]=Math.max(prices[index]+next[1][countbuy-1],
                                next[0][countbuy]);

                }
            }
            next=curr;
        }
        return next[1][2];
    }
}
