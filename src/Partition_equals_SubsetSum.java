import java.util.Arrays;

public class Partition_equals_SubsetSum {
    //Using Recursion
    public boolean canPartitionrec(int[] nums) {
        int n=nums.length;
        int sum=0;
        for(int i:nums)
            sum+=i;

        if(sum%2!=0)
            return false;

        int target=sum/2;
        return partitionrec(n-1, nums, target, n );
    }

    public boolean partitionrec(int index, int[] nums, int target, int n){
        if(target==0)
            return true;

        if(index==0)
            return (nums[0]==target);

        boolean ignore= partitionrec(index-1, nums, target, n);
        boolean take=false;
        if(target>=nums[index])
            take= partitionrec(index-1, nums, target-nums[index], n);

        return ignore||take;
    }



    //Using memoization
    public boolean canPartitionmemo(int[] nums) {
        int n=nums.length;
        int sum=0;
        for(int i:nums)
            sum+=i;

        if(sum%2!=0)
            return false;
        int target=sum/2;

        int[][] dp=new int[n][target+1];
        for (int[] arr: dp)
            Arrays.fill(arr, -1);
        return partitionmemo(n-1, nums, target, n , dp);
    }

    public boolean partitionmemo(int index, int[] nums, int target, int n, int[][] dp){
        if(target==0)
            return true;

        if(index==0)
            return (nums[0]==target);

        if (dp[index][target]!=-1)
            return dp[index][target]==1;

        boolean ignore= partitionmemo(index-1, nums, target, n, dp);
        boolean take=false;
        if(target>=nums[index])
            take= partitionmemo(index-1, nums, target-nums[index], n, dp);

        dp[index][target]=ignore||take?1:0;
        return ignore||take;
    }


    //Using Tabulation
    public boolean canPartitiontab(int[] nums){
        int n=nums.length;
        int sum=0;
        for(int i:nums)
            sum+=i;

        if(sum%2!=0)
            return false;
        int target=sum/2;
        boolean[][] dp=new boolean[n][target+1];
        for (int i = 0; i < n; i++) {
            dp[i][0]=true;
        }

        if (nums[0] <= target)
            dp[0][nums[0]]=true;

        for (int index = 1; index <n ; index++) {
            for (int k = 1; k <=target; k++) {
                boolean ignore=dp[index-1][k];
                boolean take=false;
                if (k>=nums[index])
                    take=dp[index-1][k-nums[index]];
                dp[index][k]=take||ignore;
            }
        }
        return dp[n-1][target];
    }


    //Space optimization
    public boolean canPartition(int[] nums){
        int n=nums.length;
        int sum=0;
        for(int i:nums)
            sum+=i;

        if(sum%2!=0)
            return false;
        int k=sum/2;

        boolean[] prev=new boolean[k+1];
        prev[0]=true;

        if (nums[0]<=k)
            prev[nums[0]]=true;

        for (int index = 1; index < n; index++) {
            boolean[] curr=new boolean[k+1];
            curr[0]=true;
            for (int target = 1; target <=k ; target++) {
                boolean ignore=prev[target];
                boolean take=false;
                if (nums[index]<=target)
                    take=prev[target-nums[index]];

                curr[target]=ignore||take;
            }
            prev=curr;
        }
        return prev[k];
    }
}
