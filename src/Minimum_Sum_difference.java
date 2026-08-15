public class Minimum_Sum_difference {
    public int minimumDifference(int[] nums) {
        int n = nums.length;
        int totalsum = 0;
        for (int i : nums) totalsum += i;

        int offset = 0;
        for (int i : nums) offset += Math.abs(i); // max possible negative deviation

        int size = 2 * offset + 1; // range: [-offset, +offset] shifted to [0, 2*offset]
        boolean[][] dp = new boolean[n][size];

        dp[0][nums[0] + offset] = true;
        dp[0][0 + offset] = true; // empty subset sum = 0

        for (int index = 1; index < n; index++) {
            for (int k = 0; k < size; k++) {
                boolean ignore = dp[index-1][k];
                boolean take = false;
                int prevK = k - nums[index]; // shifting handles negative nums[index] too
                if (prevK >= 0 && prevK < size) {
                    take = dp[index-1][prevK];
                }
                dp[index][k] = take || ignore;
            }
        }

        int mini = Integer.MAX_VALUE;
        for (int s = -offset; s <= offset; s++) {
            if (dp[n-1][s + offset]) {
                // s = sum of one subset, other subset = totalsum - s
                mini = Math.min(mini, Math.abs((totalsum - s) - s));
            }
        }
        return mini;
    }
}
