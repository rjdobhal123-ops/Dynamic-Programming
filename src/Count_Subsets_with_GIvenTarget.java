import java.util.Arrays;

public class Count_Subsets_with_GIvenTarget {

    //using Recursion
    public int perfectSumrec(int[] arr, int K) {
        int n=arr.length;
        return countsubsetsrec(n-1, arr, K, n);
    }
    public int countsubsetsrec(int index, int[] arr, int K, int n){

        if(K==0)
            return 1;

        if(index==0){
            if(arr[index]==K)
                return 1;

            return 0;
        }

//        int count=0;
        //Ignore
        int count=countsubsetsrec(index-1, arr, K, n);
        //Take
        if(arr[index]<=K)
            count+=countsubsetsrec(index-1, arr, K-arr[index], n);

        return count;
    }


    //using memoization
    public int perfectSummemo(int[] arr, int K) {
        int n=arr.length;
        int[][] dp=new int[n][K+1];
        for(int[] nums: dp)
            Arrays.fill(nums, -1);

        return countsubsetsmemo(n-1, arr, K, n, dp);
    }
    public int countsubsetsmemo(int index, int[] arr, int K, int n, int[][] dp){

        if(K==0)
            return 1;

        if(index==0){
            if(arr[index]==K)
                return 1;

            return 0;
        }

        if(dp[index][K]!=-1)
            return dp[index][K];

        int count=0;
        //Ignore
        count+= countsubsetsmemo(index-1, arr, K, n, dp);
        //Take
        if(arr[index]<=K)
            count+= countsubsetsmemo(index-1, arr, K-arr[index], n, dp);

        return  dp[index][K]=count;
    }


    //Using Tabulation
    public int perfectSumtab(int[] arr, int K) {
        int n=arr.length;
        int[][] dp=new int[n][K+1];

        for (int i = 0; i <n ; i++) {
            dp[i][0]=1;
        }

        if (arr[0]<=K)
            dp[0][arr[0]]=1;

        for (int index = 1; index < n; index++) {
            for (int target = 1; target <=K ; target++) {
                //ignore
                int count=dp[index-1][target];

                if(arr[index]<=target)
                    count+=dp[index-1][ target-arr[index]];
                dp[index][target]=count;
            }
        }
        return dp[n-1][K];
    }

    //Using Space Optimization
    public int perfectSum(int[] arr, int K) {
        int n = arr.length;
        int[] prev=new int[K+1];
        prev[0]=1;

        if (arr[0]<=K)
            prev[arr[0]]=1;

        for (int index = 1; index < n; index++) {
            int[] curr=new int[K+1];
            curr[0]=1;
            for (int target = 1; target <=K ; target++) {
                //ignore
                int count=prev[target];

                if(arr[index]<=target)
                    count+=prev[ target-arr[index]];
                curr[target]=count;
            }
            prev=curr;
        }
        return prev[K];
    }
}
