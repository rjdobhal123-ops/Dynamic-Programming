import java.util.Arrays;

public class LIS {
    //using Recursion
    public int lisrec(int nums[]) {
        return recLIS(0, -1, nums);
    }
    public int recLIS(int index, int prev_ind, int[] nums){
        if(index==nums.length){
            return 0;
        }

        int len=recLIS(index+1, prev_ind,  nums);    //ignore
        if(prev_ind==-1 || nums[index]>nums[prev_ind])
            len=Math.max(len, 1+recLIS(index+1,index, nums));    //Take

        return len;
    }


    //using memoization
    public int lismemo(int nums[]) {
        int n=nums.length;
        int[][] dp=new int[n][n+1];
        for(int[] arr: dp)
            Arrays.fill(arr, -1);
        return memoLIS(0, -1 , nums, dp);
    }
    public int memoLIS(int index, int prev_ind, int[] nums, int[][] dp){
        if(index==nums.length){
            return 0;
        }
        if (dp[index][prev_ind+1]!=-1)
            return dp[index][prev_ind+1];

        int len=memoLIS(index+1, prev_ind,  nums, dp);    //ignore
        if(prev_ind==-1 || nums[index]>nums[prev_ind])
            len=Math.max(len, 1+memoLIS(index+1,index, nums, dp));    //Take

        return dp[index][prev_ind+1]=len;
    }


    //using Tabulation
    public int listab(int nums[]) {
        int n=nums.length;
        int[][] dp=new int[n+1][n+1];

        for (int index = n-1; index >=0; index--) {
            for (int prev_ind = index-1; prev_ind >=-1; prev_ind--) {
               int len=dp[index+1][prev_ind+1];
               if (prev_ind==-1 || nums[index]>nums[prev_ind])
                   len=Math.max(len, 1+dp[index+1][index+1]);

               dp[index][prev_ind+1]=len;
            }
        }
        return dp[0][-1+1];
    }



    //using space optimization
    public int lis(int nums[]) {
        int n=nums.length;
        int[] next=new int[n+1];

        for (int index = n-1; index >=0; index--) {
            int[] curr=new int[n+1];
            for (int prev_ind = index-1; prev_ind >=-1; prev_ind--) {
                int len=next[prev_ind+1];
                if (prev_ind==-1 || nums[index]>nums[prev_ind])
                    len=Math.max(len, 1+next[index+1]);

               curr[prev_ind+1]=len;
            }
            next=curr;
        }
        return next[-1+1];
    }
}
