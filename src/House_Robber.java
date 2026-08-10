import java.util.Arrays;

public class House_Robber {
    // using Recursion
    public int rob_rec(int[] nums){
        return recrob(nums.length-1, nums);
    }
    public int recrob(int index, int[] nums){
        if (index==0)
            return nums[index];
        if (index<0)
            return 0;

        int pick=nums[index]+recrob(index-2, nums);
        int notpick=0+recrob(index-1, nums);

        return Math.max(pick, notpick);
    }


    //Using Memoization
    public int rob_memo(int[] nums){
        int n=nums.length;
        int[] dp=new int[n];
        Arrays.fill(dp, -1);

        return robmemo(n-1, dp, nums);
    }

    public int robmemo(int index, int[] dp, int[] nums){
        if (index==0)
            return nums[index];
        if (index<0)
            return 0;
        if (dp[index]!=-1)
            return dp[index];

        int pick=nums[index]+robmemo(index-2, dp, nums);
        int notpick=0+robmemo(index-1, dp, nums);

        return dp[index]=Math.max(pick, notpick);
    }

    //using tabulation
    public int rob_tab(int[] nums){
        int n=nums.length;
        if (n == 1) return nums[0];
        int[] dp=new int[n];
        Arrays.fill(dp, -1);

        dp[0]=nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i <n ; i++) {
            int pick=nums[i];
            if (i>1)
                pick+=dp[i-2];
            int notpick=0+dp[i-1];

            dp[i]=Math.max(pick ,notpick);
        }
        return dp[n-1];
    }

    //Performing Space Optimization
    public int rob_SpaceO(int[] nums){
        int n=nums.length;

        if (n==0)
            return 0;
        if (n==1)
            return nums[0];

        int prev1=nums[0];
        int prev2=0;

        for (int i = 1; i < n; i++) {
            int pick=nums[i];
            if (i>1)
                pick+=prev2;

            int notpick=prev1;

            int curri=Math.max(pick ,notpick);
            prev2=prev1;
            prev1=curri;
        }
        return prev1;
    }
}
