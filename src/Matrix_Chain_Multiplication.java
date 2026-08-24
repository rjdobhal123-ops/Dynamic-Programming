import java.util.Arrays;

public class Matrix_Chain_Multiplication {
    public int MCM_rec(int[] nums){
        int n=nums.length;
        return rec_MCM(1, n-1, nums);
    }
    private int rec_MCM(int i, int j, int[] nums){
        if (i==j)
            return 0;

        int mini=Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int steps=(nums[i-1]*nums[k]*nums[j])+ rec_MCM(i, k, nums)+ rec_MCM(k+1, j, nums);
            mini=Math.min(mini, steps);
        }
        return mini;
    }


    //using memoization
    public int MCM_memo(int[] nums){
        int n=nums.length;
        int[][] dp=new int[n][n];
        for (int[] num: dp)
            Arrays.fill(num, -1);
        return memo_MCM(1, n-1, nums, dp);
    }
    private int memo_MCM(int i, int j, int[] nums, int[][] dp){
        if (i==j)
            return 0;

        if (dp[i][j]!=-1)
            return dp[i][j];

        int mini=Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int steps=(nums[i-1]*nums[k]*nums[j])+ memo_MCM(i, k, nums, dp)+ memo_MCM(k+1, j, nums, dp);
            mini=Math.min(mini, steps);
        }
        return dp[i][j]=mini;
    }



    //using tabulation
    public int MCM_tab(int[] nums){
        int n=nums.length;
        int[][] dp=new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i]=0;
        }

        for (int i = n-1; i >=1 ; i--) {
            for (int j = i+1; j <n ; j++) {
                int mini=Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int steps=(nums[i-1]*nums[k]*nums[j])+ dp[i][k]+ dp[k+1][j];
                    mini=Math.min(mini, steps);
                }
                dp[i][j]=mini;
            }
        }
        return dp[1][n-1];
    }
}
