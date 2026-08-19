public class Min_Insertions_palindrome {
    public int minInsertions(String s) {
        return s.length()-longestPalindromeSubseq(s);
    }
    public int longestPalindromeSubseq(String s) {
        StringBuilder rev= new StringBuilder(s);
        rev.reverse();

        String s2=rev.toString();
        return lcstab(s, s2);
    }
    public int lcstab( String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int[][] dp = new int[n+1][m+1];

        for (int i = 0; i <= n; i++)  dp[i][0]=0;
        for (int i = 0; i <= m; i++)  dp[0][i]=0;

        for (int index1 = 1; index1 <= n ; index1++) {
            for (int index2 = 1; index2 <= m; index2++) {
                if (str1.charAt(index1-1)==str2.charAt(index2-1))
                    dp[index1][index2]=1+dp[index1-1][index2-1];
                else
                    dp[index1][index2]=Math.max(dp[index1-1][index2], dp[index1][index2-1]);
            }
        }
        return dp[n][m];
    }
}
