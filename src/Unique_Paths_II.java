import java.util.Arrays;

public class Unique_Paths_II {
    //using simple recursion
    public int recuniquePathsWithObstacles(int[][] obstacleGrid) {
        return recpaths(0,0, obstacleGrid);
    }

    public int recpaths(int row, int col, int[][] obstacleGrid){
        if(obstacleGrid[row][col]==1)
            return 0;

        if(row==obstacleGrid.length-1 && col==obstacleGrid[0].length-1)
            return 1;

        int right = 0;
        int down = 0;

        //move right
        if(col+1<obstacleGrid[0].length)
            right=recpaths(row, col+1, obstacleGrid);
        //move down
        if(row+1<obstacleGrid.length)
            down=recpaths(row+1, col, obstacleGrid);

        return right+down;
    }


    //using Memoization
    public int memouniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] dp=new int[obstacleGrid.length][obstacleGrid[0].length];
        for(int i=0; i<obstacleGrid.length; i++){
            Arrays.fill(dp[i], -1);
        }
        return memopaths(0,0, obstacleGrid, dp);
    }

    public int memopaths(int row, int col, int[][] obstacleGrid, int[][] dp){
        if(obstacleGrid[row][col]==1)
            return 0;

        if(row==obstacleGrid.length-1 && col==obstacleGrid[0].length-1)
            return 1;

        if (dp[row][col]!=-1)
            return dp[row][col];

        int right = 0;
        int down = 0;

        //move right
        if(col+1<obstacleGrid[0].length)
            right= memopaths(row, col+1, obstacleGrid, dp);
        //move down
        if(row+1<obstacleGrid.length)
            down= memopaths(row+1, col, obstacleGrid, dp);


        return dp[row][col]=right+down;
    }


    //using Tabulation method
    public int tabuniquePathsWithObstacles(int[][] obstacleGrid){
        if(obstacleGrid[0][0]==1)
            return 0;

        int n=obstacleGrid.length;
        int m=obstacleGrid[0].length;
        int[][] dp=new int[n][m];
        dp[n-1][m-1]=1;

        for (int row = n-1; row >=0 ; row--) {
            for (int col = m-1; col >=0 ; col--) {
                if (obstacleGrid[row][col]==1){
                    dp[row][col]=0;
                    continue;
                }

                if (row == n - 1 && col == m - 1)
                    continue;

                int right = 0;
                int down = 0;
                //move right
                if(col+1<obstacleGrid[0].length)
                    right= dp[row][col+1];
                //move down
                if(row+1<obstacleGrid.length)
                    down=  dp[row+1][col];

                dp[row][col]=right+down;

            }
        }
        return dp[0][0];
    }


    //Space optimization
    public int uniquePathsWithObstacles(int[][] obstacleGrid){
        if(obstacleGrid[0][0]==1)
            return 0;

        int n=obstacleGrid.length;
        int m=obstacleGrid[0].length;
        int[] prev = new int[m];

        prev[m-1]=1;

        for (int row = n-1; row >=0 ; row--) {
            for (int col = m-1; col >=0 ; col--) {

                if (obstacleGrid[row][col] == 1) {
                    prev[col] = 0;
                    continue;
                }

                if (row == n - 1 && col == m - 1) {
                    continue;
                }

                int right = 0;
                int down = 0;
                //move right
                if (col + 1 < m)
                    prev[col] = prev[col] + prev[col + 1];

            }
        }
        return prev[0];
    }
}
