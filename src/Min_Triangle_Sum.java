import java.util.Arrays;
import java.util.List;

public class Min_Triangle_Sum {

    //Using recursion
        public int minimumTotalrec(List<List<Integer>> triangle) {
            int n=triangle.size();
            return trianglesumrec(0, 0, n, triangle);
        }

        public int trianglesumrec(int row, int col, int n, List<List<Integer>> triangle ){
            if(row==n-1)
                return triangle.get(row).get(col);

            int left=0;
            int right=0;

            if(row+1<n)
                left=triangle.get(row).get(col)+trianglesumrec(row+1, col, n, triangle);
            if(row+1<n && col+1<triangle.get(row+1).size())
                right=triangle.get(row).get(col)+trianglesumrec(row+1, col+1, n, triangle);

            return Math.min(left, right);
        }


        //using Memoization
        public int minimumTotalmemo(List<List<Integer>> triangle) {
            int n=triangle.size();
            int[][] dp=new int[n][n];
            for(int[] row: dp){
                Arrays.fill(row, Integer.MIN_VALUE);
            }
            return trianglesummemo(0, 0, n, triangle, dp);
        }

    public int trianglesummemo(int row, int col, int n, List<List<Integer>> triangle , int[][] dp){
        if(row==n-1)
            return triangle.get(row).get(col);

        if(dp[row][col]!=Integer.MIN_VALUE)
            return dp[row][col];

        int left=trianglesummemo(row+1, col, n, triangle, dp);
        int right=trianglesummemo(row+1, col+1, n, triangle, dp);


        return dp[row][col]=triangle.get(row).get(col)+Math.min(left, right);
    }


    //Using Tabulation
    public int minimumTotaltab(List<List<Integer>> triangle) {
        int n=triangle.size();
        int[][] dp=new int[n][n];
        for (int i = 0; i < triangle.get(n-1).size(); i++) {
            dp[n-1][i]=triangle.get(n-1).get(i);
        }
        for (int row = n-2; row >= 0; row--) {
            for (int col = triangle.get(row).size()-1; col >=0; col--) {
                int left=dp[row+1][col];
                int right=dp[row+1][col+1];
                dp[row][col]=triangle.get(row).get(col)+Math.min(left, right);
            }
        }
        return dp[0][0];
        }

        //Space optimization
        public int minimumTotal(List<List<Integer>> triangle){
            int n=triangle.size();
            //Array to store next row
            int[] front=new int[n];
            for (int i = 0; i < n; i++) {
                front[i] = triangle.get(n - 1).get(i);
            }

            for (int row = n-2; row >= 0; row--) {
                //Array to store current row
                int[] curr=new int[n];
                for (int col = row; col >=0; col--) {
                    int left=front[col];
                    int right=front[col+1];
                    curr[col]=triangle.get(row).get(col)+Math.min(left, right);
                }
                front=curr;
            }
            return front[0];
        }
    }
