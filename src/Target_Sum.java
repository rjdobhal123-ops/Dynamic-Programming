public class Target_Sum {
    public int countPartitions( int target, int[] arr) {
        int n=arr.length;
        int totalsum=0;
        for (int num: arr)
            totalsum+=num;

        if ((totalsum+target)%2!=0)
            return 0;

        int newtarget=(totalsum+target)/2;

        if (newtarget<0 || newtarget>totalsum)
            return 0;

        int[][] dp=new int[n][newtarget+1];

        if (arr[0] == 0)
            dp[0][0]=2;
        else{
            dp[0][0]=1;
            if (arr[0]<=newtarget)
                dp[0][arr[0]]=1;
        }

        for (int index = 1; index <n ; index++) {
            for (int sum = 0; sum <=newtarget; sum++) {
                int ignore=dp[index-1][sum];
                int take=0;
                if (sum>=arr[index])
                    take=dp[index-1][sum-arr[index]];
                dp[index][sum]=take+ignore;
            }
        }
        return dp[n-1][newtarget];
    }
}
