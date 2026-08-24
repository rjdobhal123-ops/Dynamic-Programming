import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Min_Cost_toCut_stick {
    //using recursion
    public int minCost_rec(int n, int[] cuts) {
        Arrays.sort(cuts);
        List<Integer> cut=new ArrayList<>();
        for (int x : cuts) {
            cut.add(x);
        }
        cut.addFirst(0);
        cut.addLast(n);

        return rec_minCost(1, cut.size()-2, cut);
    }
    private int rec_minCost(int i, int j,  List<Integer> cut){
        if (i>j) return 0;

        int mini=Integer.MAX_VALUE;
        for (int index = i; index <=j ; index++) {
            int cost=(cut.get(j+1)-cut.get(i-1))+rec_minCost(i, index-1, cut)+ rec_minCost(index+1, j, cut);
            mini=Math.min(cost, mini);
        }
        return mini;
    }


    //using memoization
    public int minCost_memo(int n, int[] cuts) {
        Arrays.sort(cuts);
        List<Integer> cut=new ArrayList<>();
        for (int x : cuts) {
            cut.add(x);
        }
        cut.addFirst(0);
        cut.addLast(n);

        int[][] dp=new int[cut.size()+1][cut.size()+1];
        for(int[] num: dp)
            Arrays.fill(num,-1);

        return memo_minCost(1, cut.size()-2, cut, dp);
    }
    private int memo_minCost(int i, int j,  List<Integer> cut, int[][] dp){
        if (i>j) return 0;
        if (dp[i][j]!=-1)
            return dp[i][j];

        int mini=Integer.MAX_VALUE;
        for (int index = i; index <=j ; index++) {
            int cost=(cut.get(j+1)-cut.get(i-1))+memo_minCost(i, index-1, cut, dp)+ memo_minCost(index+1, j, cut, dp);
            mini=Math.min(cost, mini);
        }
        return dp[i][j]=mini;
    }


    //using tabulation
    public int minCost_tab(int n, int[] cuts) {
        Arrays.sort(cuts);
        List<Integer> cut=new ArrayList<>();
        for (int x : cuts) {
            cut.add(x);
        }
        cut.addFirst(0);
        cut.addLast(n);

        int[][] dp=new int[cut.size()][cut.size()];
        for (int i = cut.size()-2; i >=1 ; i--) {
            for (int j = i; j <=cut.size()-2 ; j++) {
                if (i>j) continue;
                int mini=Integer.MAX_VALUE;
                for (int index = i; index <=j ; index++) {
                    int cost=(cut.get(j+1)-cut.get(i-1))+dp[i][index-1]+ dp[index+1][j];
                    mini=Math.min(cost, mini);
                }
                dp[i][j]=mini;
            }
        }
        return dp[1][cut.size()-2];
    }
}
