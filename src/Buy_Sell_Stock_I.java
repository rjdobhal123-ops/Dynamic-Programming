public class Buy_Sell_Stock_I {
    public int maxProfit(int[] prices) {
        int n=prices.length;
        int min=prices[0];
        int maxProfit=0;
        for(int i=1;i<n;i++){
            int cost=prices[i]-min;
            maxProfit=Math.max(cost,maxProfit);
            min=Math.min(min,prices[i]);
        }
        return maxProfit;
    }
}
