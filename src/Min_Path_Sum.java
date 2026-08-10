import java.util.Arrays;

public class Min_Path_Sum {

    //Using Recursion
    public int recminPathSum(int[][] grid) {
        return recminpath(0, 0, grid, grid.length, grid[0].length);
    }

    public int recminpath(int row, int col, int[][] grid, int n, int m){
        if(row==n-1 && col==m-1)
            return grid[n-1][m-1];

        int right=Integer.MAX_VALUE;
        int down=Integer.MAX_VALUE;

        if(col+1<m && row<n)
            right=recminpath(row, col+1, grid, n, m);
        if(row+1<n && col<m)
            down=recminpath(row+1, col, grid, n, m);

        return grid[row][col]+Math.min(right, down);
    }

    //using Memoization
    public int memominPathSum(int[][] grid) {
        int n= grid.length;
        int m= grid[0].length;
        int[][] dp=new int[n][m];
        for(int i=0; i<m; i++){
            Arrays.fill(dp[i], -1);
        }
        return memominpath(0, 0, grid,n,m, dp);
    }

    public int memominpath(int row, int col, int[][] grid, int n, int m, int[][] dp){
        if(row==n-1 && col==m-1)
            return grid[n-1][m-1];

        if (dp[row][col]!=-1)
            return dp[row][col];

        int right=Integer.MAX_VALUE;
        int down=Integer.MAX_VALUE;

        if(col+1<m && row<n)
            right=memominpath(row, col+1, grid, n, m, dp);
        if(row+1<n && col<m)
            down=memominpath(row+1, col, grid, n, m, dp);

        return dp[row][col]=grid[row][col]+Math.min(right, down);
    }


    //Using Tabulation
    public int tabminPathSum(int[][] grid) {
        int n= grid.length;
        int m= grid[0].length;
        int[][] dp=new int[n][m];
        dp[n-1][m-1]=grid[n-1][m-1];

        for(int row=n-1; row>=0; row--){
            for(int col=m-1; col>=0; col--){
                if(row==n-1 && col==m-1)
                    continue;

                int right=Integer.MAX_VALUE;
                int down=Integer.MAX_VALUE;

                if(col+1<m && row<n)
                    right=dp[row][col+1];
                if(row+1<n && col<m)
                    down=dp[row+1][col];

                dp[row][col]=grid[row][col]+Math.min(right, down);
            }
        }
        return dp[0][0];
    }


    //Space Optimization
    public int minPathSum(int[][] grid){
        int n=grid.length;
        int m=grid[0].length;
        int[] prev = new int[m];

        prev[m-1]=grid[n-1][m-1];

        for (int row = n-1; row >=0 ; row--) {
            for (int col = m-1; col >=0 ; col--) {

                if (row == n - 1 && col == m - 1) {
                    continue;
                }

                int right = Integer.MAX_VALUE;
                int down = Integer.MAX_VALUE;
                //move right
                if (col + 1 < m)
                    right = prev[col + 1];
                //move down
                if (row+1<n)
                    down=prev[col];

                prev[col]=grid[row][col]+Math.min(right, down);

            }
        }
        return prev[0];
    }
}
