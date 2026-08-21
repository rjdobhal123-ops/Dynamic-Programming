import java.util.Arrays;

public class Buy_Sell_Stocks_II {
    //using recursion
    public int maxProfitrec(int[] prices) {
        int n=prices.length;
        return recmaxprofit(0, 1,  prices, n);
    }

    private int recmaxprofit(int index, int buy,  int[] prices, int n) {
        if (index==n)
            return 0;
        int profit=0;
        if (buy==1)
            profit=Math.max(-prices[index]+recmaxprofit(index+1, 0, prices, n), recmaxprofit(index+1, 1, prices, n));
        else
            profit=Math.max(prices[index]+recmaxprofit(index+1, 1, prices, n), recmaxprofit(index+1, 0, prices, n));

        return profit;
    }



    //using memoization
    public int maxProfitmemo(int[] prices) {
        int n=prices.length;
        int[][] dp=new int[n][2];
        for (int[] num: dp)
            Arrays.fill(num, -1);
        return memomaxprofit(0, 1,  prices, n, dp);
    }

    private int memomaxprofit(int index, int buy,  int[] prices, int n, int[][] dp) {
        if (index==n)
            return 0;
        if (dp[index][buy]!=-1)
            return dp[index][buy];

        int profit=0;
        if (buy==1)
            profit=Math.max(-prices[index]+memomaxprofit(index+1, 0, prices, n, dp), memomaxprofit(index+1, 1, prices, n, dp));
        else
            profit=Math.max(prices[index]+memomaxprofit(index+1, 1, prices, n, dp), memomaxprofit(index+1, 0, prices, n, dp));

        return dp[index][buy]=profit;
    }




    //using tabulation
    public int maxProfittab(int[] prices) {
        int n=prices.length;
        int[][] dp=new int[n+1][2];
        dp[0][0]=0;
        dp[0][1]=0;

        for (int index = n-1; index >=0; index--) {
            for (int buy = 0; buy <=1; buy++) {
                int profit=0;
                if (buy==1)
                    profit=Math.max(-prices[index]+dp[index+1][0], dp[index+1][1]);
                else
                    profit=Math.max(prices[index]+dp[index+1][1], dp[index+1][0]);

                 dp[index][buy]=profit;
            }
        }
        return dp[0][1];
    }



    //using space optimizaion
    public int maxProfit(int[] prices) {
        int n=prices.length;
        int[] next=new int[2];
        next[0]=0;

        for (int index = n-1; index >=0; index--) {
            int[] curr=new int[2];
            for (int buy = 0; buy <=1; buy++) {
                int profit=0;
                if (buy==1)
                    profit=Math.max(-prices[index]+next[0], next[1]);
                else
                    profit=Math.max(prices[index]+next[1], next[0]);

                curr[buy]=profit;
            }
            next=curr;
        }
        return next[1];
    }



    //using space optimizaion using 4 variables
    public int maxProfitspace(int[] prices) {
        int n=prices.length;
        int next_notbuy=0;
        int next_buy=0;
        int curr_notbuy;
        int curr_buy;

        for (int index = n-1; index >=0; index--) {
            curr_notbuy=Math.max(prices[index]+next_buy, next_notbuy);

            curr_buy=Math.max(-prices[index]+next_notbuy, next_buy);

            next_buy=curr_buy;
            next_notbuy=curr_notbuy;
            }

        return next_buy;
    }
}
