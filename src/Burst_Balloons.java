import java.util.*;

public class Burst_Balloons {

    //using recursion
    public int maxCoins_rec(int[] nums) {
        int n=nums.length;
        List<Integer> list=new ArrayList<>();
        for (int x : nums) {
            list.add(x);
        }
        list.addFirst(1);
        list.addLast(1);
        return rec_maxcoins(1, n, list);
    }
    private int rec_maxcoins(int i, int j,  List<Integer> list){
        if (i>j)
            return 0;

        int maxi=Integer.MIN_VALUE;
        for (int k = i; k <= j; k++) {
            int coins=(list.get(i-1)*list.get(k)*list.get(j+1))+ rec_maxcoins(i, k-1, list)+ rec_maxcoins(k+1, j, list);
            maxi=Math.max(maxi, coins);
        }
        return maxi;
    }



    //using memoization
    public int maxCoins_memo(int[] nums) {
        int n=nums.length;

        List<Integer> list=new ArrayList<>();
        for (int x : nums) {
            list.add(x);
        }
        list.addFirst(1);
        list.addLast(1);

        int[][] dp=new int[list.size()][list.size()];
        for (int[] arr: dp)
            Arrays.fill(arr, -1);

        return memo_maxcoins(1, n, list, dp);
    }
    private int memo_maxcoins(int i, int j,  List<Integer> list,  int[][] dp){
        if (i>j)
            return 0;
        if  (dp[i][j]!=-1)
            return dp[i][j];

        int maxi=Integer.MIN_VALUE;
        for (int k = i; k <= j; k++) {
            int coins=(list.get(i-1)*list.get(k)*list.get(j+1))+ memo_maxcoins(i, k-1, list, dp)+ memo_maxcoins(k+1, j, list, dp);
            maxi=Math.max(maxi, coins);
        }
        return dp[i][j]=maxi;
    }



    //using tabulation
    public int maxCoins(int[] nums) {
        int n=nums.length;

        List<Integer> list=new ArrayList<>();
        for (int x : nums) {
            list.add(x);
        }
        list.addFirst(1);
        list.addLast(1);

        int[][] dp=new int[list.size()][list.size()];

        for (int i = list.size()-2; i >=1 ; i--) {
            for (int j = i; j <= list.size()-2; j++) {
                int maxi=Integer.MIN_VALUE;
                for (int k = i; k <= j; k++) {
                    int coins=(list.get(i-1)*list.get(k)*list.get(j+1))+ dp[i][k-1] + dp[k+1][j];
                    maxi=Math.max(maxi, coins);
                }
                dp[i][j]=maxi;
            }
        }
        return dp[1][list.size()-2];
    }
}
