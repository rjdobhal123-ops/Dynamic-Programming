public class Buy_Sell_Stocks_V {
    //using recursion
    public int maxProfitrec(int[] prices) {
        int n=prices.length;
        return recmaxprofit(0, 1,  prices, n);
    }

    private int recmaxprofit(int index, int buy,  int[] prices, int n) {
        if (index>=n)
            return 0;
        int profit=0;
        if (buy==1)
            profit=Math.max(-prices[index]+recmaxprofit(index+1, 0, prices, n), recmaxprofit(index+1, 1, prices, n));
        else
            profit=Math.max(prices[index]+recmaxprofit(index+2, 1, prices, n), recmaxprofit(index+1, 0, prices, n));

        return profit;
    }


    //uisng tabulation
    public int maxProfit(int[] prices) {
        int n=prices.length;
        int[][] dp=new int[n+2][2];

        for (int index = n-1; index >=0; index--) {
            for (int buy = 0; buy <=1; buy++) {
                int profit=0;
                if (buy==1)
                    profit=Math.max(-prices[index]+dp[index+1][0], dp[index+1][1]);
                else
                    profit=Math.max(prices[index]+dp[index+2][1], dp[index+1][0]);

                dp[index][buy]=profit;
            }
        }
        return dp[0][1];
    }
}
