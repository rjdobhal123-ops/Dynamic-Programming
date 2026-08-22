public class Buy_Sell_Stocks_IV {
    //Everything is similar to part III of stocks problem just assign the countbuy variable with value of k
    public int maxProfit(int k, int[] prices) {
        int n=prices.length;
        int[][] next=new int[2][k+1];

        for (int index = n-1; index >=0 ; index--) {
            int[][] curr=new int[2][k+1];
            for (int buy = 1; buy >=0 ; buy--) {
                for (int countbuy = k; countbuy >0 ; countbuy--) {
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
        return next[1][k];
    }
}
