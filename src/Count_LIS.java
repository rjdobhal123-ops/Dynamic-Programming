import java.util.Arrays;

public class Count_LIS {
    public int findNumberOfLIS(int[] nums) {
        int n=nums.length;

        int[] dp=new int[n];
        Arrays.fill(dp, 1);

        int[] count=new int[n];
        Arrays.fill(count, 1);

        int maxlen=0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    //inherit the previous count
                    count[i] = count[j];
                }else if (nums[j]<nums[i] && dp[j]+1==dp[i]) {
                    //add the count of previous
                    count[i] += count[j];
                }
            }
            maxlen=Math.max(maxlen, dp[i]);
        }

        int cntlis=0;
        for (int i = 0; i < n; i++) {
            if (dp[i]==maxlen)
                cntlis+=count[i];
        }
        return cntlis;
    }
}
