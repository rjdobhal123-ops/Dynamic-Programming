import java.util.Arrays;

public class Distinct_Subsequence {
    //using recursion
    public int numDistinctrec(String s, String t) {
        int n=s.length();
        int m=t.length();
        return recnumdistinct(n-1, m-1, s, t);
    }

    private int recnumdistinct(int index1, int index2, String s, String t) {
        if (index2<0)
            return 1;
        if (index1<0)
            return 0;

        if (s.charAt(index1)==t.charAt(index2))
            return recnumdistinct(index1-1, index2-1, s, t)+recnumdistinct(index1-1, index2, s , t);
        else
            return recnumdistinct(index1-1, index2, s, t);
    }


    //using memoization
    public int numDistinctmemo(String s, String t) {
        int n=s.length();
        int m=t.length();
        int[][] dp=new int[n][m];
        for(int[] num: dp)
            Arrays.fill(num, -1);

        return memonumdistinct(n-1, m-1, s, t, dp);
    }

    private int memonumdistinct(int index1, int index2, String s, String t, int[][] dp) {
        if (index2<0)
            return 1;
        if (index1<0)
            return 0;
        if (dp[index1][index2]!=-1)
            return dp[index1][index2];

        if (s.charAt(index1)==t.charAt(index2))
            return dp[index1][index2]= memonumdistinct(index1-1, index2-1, s, t, dp)+memonumdistinct(index1-1, index2, s , t, dp);
        else
            return dp[index1][index2]= memonumdistinct(index1-1, index2, s, t, dp);
    }


    //using tabulation
    public int numDistincttab(String s, String t) {
        int n=s.length();
        int m=t.length();
        int[][] dp=new int[n+1][m+1];

        for(int i=0; i<=n; i++){
            dp[i][0]=1;
        }
        for(int i=1; i<=m; i++){
            dp[0][i]=0;
        }

        for (int index1 = 1; index1 <=n ; index1++) {
            for (int index2 = 1; index2 <=m; index2++) {
                if (s.charAt(index1-1)==t.charAt(index2-1))
                    dp[index1][index2]=dp[index1-1][index2-1]+dp[index1-1][index2];
                else
                    dp[index1][index2]=dp[index1-1][index2];
            }
        }
        return dp[n][m];
    }
}
