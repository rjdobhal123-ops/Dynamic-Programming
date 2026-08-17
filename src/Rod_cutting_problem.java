import java.util.Arrays;

public class Rod_cutting_problem {
    //using recursion
    public int RodCuttingrec(int price[], int N) {
        int n=price.length;
        return cutrodrec(n-1, N, price);
    }

    private int cutrodrec(int index, int N, int[] price) {
        if (N==0)
            return 0;
        if (index==0)
            return price[0];

        int ignore=cutrodrec(index-1, N, price);
        int take=Integer.MIN_VALUE;
        if (N>=index+1)
            take=price[index]+cutrodrec(index, N-(index+1), price);

        return Math.max(take, ignore);
    }


    //using memoization
    public int RodCuttingmemo(int price[], int N) {
        int n=price.length;
        int[][] dp=new int[n][N+1];
        for (int[] num:dp)
            Arrays.fill(num, -1);

        return cutrodmemo(n-1, N, price, dp);
    }

    private int cutrodmemo(int index, int N, int[] price, int[][] dp) {
        if (N==0)
            return 0;
        if (index==0)
            return price[0];
        if (dp[index][N]!=-1)
            return dp[index][N];

        int ignore=cutrodmemo(index-1, N, price, dp);
        int take=Integer.MIN_VALUE;
        if (N>=index+1)
            take=price[index]+cutrodmemo(index, N-(index+1), price, dp);

        return dp[index][N]= Math.max(take, ignore);
    }


    //Using Tabulation
    public int RodCuttingtab(int price[], int N){
        int n=price.length;
        int[][] dp=new int[n][N+1];

        for (int ind = 0; ind < n; ind++) {
            dp[ind][0]=0;
        }

        dp[0][N]=price[0];

        for (int ind = 1; ind < n; ind++) {
            for (int rem = 1; rem <=N; rem++) {
                int ignore=dp[ind-1][rem];
                int take=Integer.MIN_VALUE;
                if (rem>=ind+1)
                    take=price[ind]+dp[ind] [rem-(ind+1)];

                dp[ind][rem]= Math.max(take, ignore);
            }
        }
        return dp[n-1][N];
    }

    //Using Space Optimization
    public int RodCutting(int price[], int N){
        int n=price.length;
        int[] prev=new int[N+1];

        prev[0]=0;

        for (int ind = 1; ind < n; ind++) {
            int[] curr=new int[N+1];
            curr[0]=0;
            for (int rem = 1; rem <=N; rem++) {
                int ignore=prev[rem];
                int take=Integer.MIN_VALUE;
                if (rem>=ind+1)
                    take=price[ind]+curr[rem-(ind+1)];

                curr[rem]= Math.max(take, ignore);
            }
            prev=curr;
        }
        return prev[N];
    }
}
