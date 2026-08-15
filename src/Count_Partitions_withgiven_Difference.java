public class Count_Partitions_withgiven_Difference {
    public int countPartitions( int diff, int[] arr) {
        int n=arr.length;
        int totalsum=0;
        for (int num: arr)
            totalsum+=num;

        if ((totalsum-diff)%2!=0)
            return 0;

        int target=(totalsum-diff)/2;

        if (target<0 || target>totalsum)
            return 0;

        int[][] dp=new int[n][target+1];

        if (arr[0] == 0)
            dp[0][0]=2;
        else{
            dp[0][0]=1;
            if (arr[0]<=target)
                dp[0][arr[0]]=1;
        }

        for (int index = 1; index <n ; index++) {
            for (int sum = 0; sum <=target; sum++) {
                int ignore=dp[index-1][sum];
                int take=0;
                if (sum>=arr[index])
                    take=dp[index-1][sum-arr[index]];
                dp[index][sum]=take+ignore;
            }
        }
        return dp[n-1][target];
    }
}
