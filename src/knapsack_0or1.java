public class knapsack_0or1 {
    public int knapsackrec(int W, int val[], int wt[]) {
        int n = val.length;
        return findmaxprofit(n - 1, W, val, wt);
    }

    public int findmaxprofit(int index, int W, int[] val, int[] wt) {
        if (index == 0) {
            if (wt[0] <= W) return W - wt[0];
            return 0;
        }

        int ignore = 0 + findmaxprofit(index - 1, W, val, wt);
        int take = Integer.MIN_VALUE;
        if (wt[index] <= W)
            take = val[index] + findmaxprofit(index - 1, W - wt[index], val, wt);

        return Math.max(take, ignore);
    }

    // Memoization, Tabulation, Space optimization is similar to all DP problems of subsequences

    //Using space optimization(One step ahead of traditional space optimization
    public int knapsack(int W, int val[], int wt[]) {
        int n = val.length;
        int[] prev=new int[W+1];
        for (int i = wt[0]; i <=W ; i++) {
            prev[i]=val[0];
        }

        for (int index = 1; index <n ; index++) {
            for (int weight = W; weight >=0; weight--) {
                int ignore= prev[weight];
                int take=Integer.MIN_VALUE;
                if (wt[index]<=weight)
                    take=val[index]+prev[weight-wt[index]];
                prev[weight]=Math.max(take ,ignore);
            }
        }
        return prev[W];
    }

}
