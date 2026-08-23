import java.util.Arrays;

public class Longest_Bitonic_Subsequence {
    public int LongestBitonicSequence(int[] nums) {
        int n=nums.length;

        //LIS from index 0->n-1
        int[] dp1=new int[n];
        Arrays.fill(dp1, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && dp1[j] + 1 > dp1[i]) {
                    dp1[i] = dp1[j] + 1;
                }
            }
        }

        //LIS from index n-1->0
        int maxlen=0;
        int[] dp2=new int[n];
        Arrays.fill(dp2, 1);

        for (int i = n-1; i >=0; i--) {
            for (int j = n-1; j >= i; j--) {
                if (nums[j] < nums[i] && dp2[j] + 1 > dp2[i]) {
                    dp2[i] = dp2[j] + 1;
                }
            }
            maxlen=Math.max(maxlen, dp1[i]+dp2[i]-1);
        }
        return maxlen;
    }
}
