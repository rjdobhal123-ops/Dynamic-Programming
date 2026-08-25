import java.util.Arrays;

public class PartitionArray_maxSUM {
    public int maxSumAfterPartitioning_rec(int[] arr, int k) {
        return recmaxSumAfterPartitioning(0, arr, k);
    }
    private int recmaxSumAfterPartitioning(int i, int[] arr, int k){
        if(i==arr.length)
            return 0;

        int maxsum=Integer.MIN_VALUE;
        int maxinsubarray=Integer.MIN_VALUE;

        for(int j=i; j<Math.min(i+k, arr.length); j++){
            int subarraysize=j-i+1;
            maxinsubarray=Math.max(maxinsubarray, arr[j]);
            int sum=(maxinsubarray*subarraysize) + recmaxSumAfterPartitioning(j+1, arr, k);
            maxsum=Math.max(maxsum, sum);
        }
        return maxsum;
    }


    //using memoization
    public int maxSumAfterPartitioning_memo(int[] arr, int k) {
        int[] dp=new int[arr.length];
        Arrays.fill(dp, -1);
        return memomaxSumAfterPartitioning(0, arr, k, dp);
    }
    private int memomaxSumAfterPartitioning(int i, int[] arr, int k, int[] dp){
        if(i==arr.length)
            return 0;
        if(dp[i]!=-1)
            return dp[i];

        int maxsum=Integer.MIN_VALUE;
        int maxinsubarray=Integer.MIN_VALUE;

        for(int j=i; j<Math.min(i+k, arr.length); j++){
            int subarraysize=j-i+1;
            maxinsubarray=Math.max(maxinsubarray, arr[j]);
            int sum=(maxinsubarray*subarraysize) + memomaxSumAfterPartitioning(j+1, arr, k, dp);
            maxsum=Math.max(maxsum, sum);
        }
        return dp[i]=maxsum;
    }


    //Using Tabulation
    public int maxSumAfterPartitioning_tab(int[] arr, int k) {
        int n=arr.length;
        int[] dp=new int[n+1];
        dp[n]=0;

        int maxsum=Integer.MIN_VALUE;

        for (int i = n-1; i >=0 ; i--) {
            int maxinsubarray=Integer.MIN_VALUE;

            for(int j=i; j<Math.min(i+k, arr.length); j++){
                int subarraysize=j-i+1;
                maxinsubarray=Math.max(maxinsubarray, arr[j]);
                int sum=(maxinsubarray*subarraysize) + dp[j+1];
                maxsum=Math.max(maxsum, sum);
            }
             dp[i]=maxsum;
        }
        return dp[0];
    }
}
