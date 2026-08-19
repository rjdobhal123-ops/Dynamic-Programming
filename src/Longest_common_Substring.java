public class Longest_common_Substring {
    public int lcstab( String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int[][] dp = new int[n+1][m+1];

        for (int i = 0; i <= n; i++)  dp[i][0]=0;
        for (int i = 0; i <= m; i++)  dp[0][i]=0;

        int ans=0;

        for (int index1 = 1; index1 <= n ; index1++) {
            for (int index2 = 1; index2 <= m; index2++) {
                if (str1.charAt(index1-1)==str2.charAt(index2-1)) {
                    dp[index1][index2] = 1 + dp[index1 - 1][index2 - 1];
                    ans=Math.max(ans, dp[index1][index2]);
                }
                else
                    dp[index1][index2]=0;
            }
        }
        return ans;
    }

    //Using Space optimization
    public int lcs( String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int[] prev = new int[m+1];

        for (int i = 0; i <= m; i++)  prev[i]=0;
        int ans=0;

        for (int index1 = 1; index1 <= n ; index1++) {
            int[] curr = new int[m+1];
            for (int index2 = 1; index2 <= m; index2++) {
                if (str1.charAt(index1-1)==str2.charAt(index2-1)){
                    curr[index2]=1+prev[index2-1];
                    ans=Math.max(ans, curr[index2]);
                }
                else
                    curr[index2]=0;
            }
            prev=curr;
        }
        return ans;
    }
}
