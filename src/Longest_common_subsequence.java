import java.util.Arrays;

public class Longest_common_subsequence {
    //Using Recursion
    public int lcsrec( String str1, String str2) {
        int n=str1.length();
        int m=str2.length();

        return reclcs(n-1, m-1, str1 , str2);
    }

    private int reclcs(int index1, int index2, String str1, String str2) {
        if (index1<0 || index2<0)
            return 0;

        if (str1.charAt(index1)==str2.charAt(index2))
            return 1+reclcs(index1-1, index2-1, str1, str2);

        return Math.max(reclcs(index1-1, index2, str1, str2), reclcs(index1, index2-1, str1, str2));
    }


    //using memoization
    public int lcsmemo( String str1, String str2) {
        int n=str1.length();
        int m=str2.length();
        int[][] dp=new int[n][m];
        for (int[] num: dp)
            Arrays.fill(num, -1);

        return memolcs(n-1, m-1, str1 , str2, dp);
    }

    private int memolcs(int index1, int index2, String str1, String str2, int[][] dp) {
        if (index1<0 || index2<0)
            return 0;

        if (dp[index1][index2]!=-1)
            return dp[index1][index2];

        if (str1.charAt(index1)==str2.charAt(index2))
            return dp[index1][index2]=1+memolcs(index1-1, index2-1, str1, str2, dp);

        return dp[index1][index2]=Math.max(memolcs(index1-1, index2, str1, str2, dp), memolcs(index1, index2-1, str1, str2, dp));
    }




    //using tabulation--> in order to perform tabulation code we have to assume indices to be 1-based indexing
    // so that we can define the negative base case for the dp array

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




    // Using space optimization
    public int lcs( String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int[] prev = new int[m+1];

        for (int i = 0; i <= m; i++)  prev[i]=0;

        for (int index1 = 1; index1 <= n ; index1++) {
            int[] curr = new int[m+1];
            for (int index2 = 1; index2 <= m; index2++) {
                if (str1.charAt(index1-1)==str2.charAt(index2-1))
                    curr[index2]=1+prev[index2-1];
                else
                    curr[index2]=Math.max(prev[index2], curr[index2-1]);
            }
            prev=curr;
        }
        return prev[m];
    }
}
