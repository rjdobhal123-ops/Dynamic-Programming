import java.util.Arrays;

public class Edit_Distance {
    //Using recursion
    public int minDistancerec(String word1, String word2) {
        return recminDistance(word1.length()-1, word2.length()-1, word1, word2);
    }
    public int recminDistance(int index1, int index2, String word1, String word2) {
        if(index1<0)
            return index2+1;
        if(index2<0)
            return index1+1;

        if(word1.charAt(index1)==word2.charAt(index2))
            return recminDistance(index1-1, index2-1, word1, word2);

        int insert=1+recminDistance(index1, index2-1, word1, word2);
        int delete=1+recminDistance(index1-1, index2, word1, word2);
        int replace=1+recminDistance(index1-1, index2-1, word1, word2);

        return  Math.min(Math.min(insert, delete), replace);
    }


    //using memoization
    public int minDistancememo(String word1, String word2) {
        int[][] dp=new int[word1.length()][word2.length()];
        for (int[] num: dp)
            Arrays.fill(num, -1);
        return memominDistance(word1.length()-1, word2.length()-1, word1, word2, dp);
    }
    public int memominDistance(int index1, int index2, String word1, String word2,  int[][] dp) {
        if(index1<0)
            return index2+1;
        if(index2<0)
            return index1+1;
        if (dp[index1][index2]!=-1)
            return dp[index1][index2];

        if(word1.charAt(index1)==word2.charAt(index2))
            return dp[index1][index2]=memominDistance(index1-1, index2-1, word1, word2, dp);

        int insert=1+memominDistance(index1, index2-1, word1, word2, dp);
        int delete=1+memominDistance(index1-1, index2, word1, word2, dp);
        int replace=1+memominDistance(index1-1, index2-1, word1, word2, dp);

        return  dp[index1][index2]=Math.min(Math.min(insert, delete), replace);
    }


    //using tabulation
    public int minDistancetab(String word1, String word2) {
        int n=word1.length();
        int m=word2.length();
        int[][] dp=new int[n+1][m+1];

        for (int i=0; i<=n; i++)
            dp[i][0]=i;

        for (int i=0; i<=m; i++)
            dp[0][i]=i;

        for (int index1 = 1; index1 <=n ; index1++) {
            for (int index2 = 1; index2 <=m ; index2++) {
                if(word1.charAt(index1-1)==word2.charAt(index2-1))
                    dp[index1][index2]=dp[index1-1][index2-1];
                else{
                    int insert=1+dp[index1][index2-1];
                    int delete=1+dp[index1-1][index2];
                    int replace=1+dp[index1-1][index2-1];

                    dp[index1][index2]=Math.min(Math.min(insert, delete), replace);
                }
            }
        }
        return dp[n][m];
    }


    //using space optimization
    public int minDistance(String word1, String word2) {
        int n=word1.length();
        int m=word2.length();
        int[] prev=new int[m+1];

        for (int i=0; i<=m; i++)
            prev[i]=i;

        for (int index1 = 1; index1 <=n ; index1++) {
            int[] curr=new int[m+1];
            curr[0]=index1;
            for (int index2 = 1; index2 <=m ; index2++) {
                if(word1.charAt(index1-1)==word2.charAt(index2-1))
                    curr[index2]=prev[index2-1];
                else{
                    int insert=1+prev[index2-1];
                    int delete=1+prev[index2];
                    int replace=1+curr[index2-1];

                    curr[index2]=Math.min(Math.min(insert, delete), replace);
                }
            }
            prev=curr;
        }
        return prev[m];
    }
}
