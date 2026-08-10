import java.util.Arrays;

public class Unique_Paths {

    //Using Memoization
    public int uniquePathsmemo(int m, int n) {
        int[][] dp=new int[m][n];
        for(int i=0; i<m; i++){
            Arrays.fill(dp[i], -1);
        }
        return pathsmemo(0, 0, m ,n, dp);
    }

    public int pathsmemo(int row, int col, int m, int n, int[][] dp){
        if(row==m-1 && col==n-1)
            return 1;

        if(dp[row][col]!=-1)
            return dp[row][col];

        int totalpaths=0;

        if(col+1<n)
            totalpaths+=pathsmemo(row, col+1, m, n, dp);
        if(row+1<m)
            totalpaths+=pathsmemo(row+1, col, m ,n, dp);

        dp[row][col]=totalpaths;

        return dp[row][col];
    }


    //using tabulation
    public int uniquePathstab(int m, int n) {
        int[][] dp=new int[m][n];
        dp[m-1][n-1]=1;


        for (int row = m-1; row >=0; row--) {
            for (int col = n-1; col >= 0; col--) {
                if(row==m-1 && col==n-1)
                    continue;

                int totalpaths=0;
                if(col+1<n)
                    totalpaths+=dp[row][col+1];
                if(row+1<m)
                    totalpaths+=dp[row+1][col];

                dp[row][col]=totalpaths;
            }
        }
        return dp[0][0];
    }
}
