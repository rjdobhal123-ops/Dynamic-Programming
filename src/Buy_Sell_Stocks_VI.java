public class Buy_Sell_Stocks_VI {
    //using Recursion
    public int maxProfitrec(int[] prices, int fee) {
        int n=prices.length;
        return recmaxprofit(0, 1, fee, prices, n);
    }

    private int recmaxprofit(int index, int buy, int fee, int[] prices, int n) {
        if (index==n)
            return 0;
        int profit=0;
        if (buy==1)
            profit=Math.max(-prices[index]+recmaxprofit(index+1, 0, fee, prices, n), recmaxprofit(index+1, 1, fee, prices, n));
        else
            profit=Math.max(prices[index]+recmaxprofit(index+1, 1, fee, prices, n)-fee, recmaxprofit(index+1, 0, fee, prices, n));

        return profit;
    }


    //using space optimization
    public int maxProfit(int[] prices, int fee){
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
                    profit=Math.max(prices[index]+next[1]-fee, next[0]);

                curr[buy]=profit;
            }
            next=curr;
        }
        return next[1];
    }

}
