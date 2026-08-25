import java.util.Arrays;

public class Palindrome_Partitioning_II {
    //using recursion

    public int minCut_rec(String s) {
        return rec_mincut(0, s)-1;
    }

    private int rec_mincut(int i, String s){
        if(i==s.length())
            return 0;

        int mincost=Integer.MAX_VALUE;

        for(int j=i; j<s.length(); j++){
            if(isPalindrome(i, j, s)){
                int cost=1+rec_mincut(j+1, s);
                mincost=Math.min(mincost, cost);
            }
        }
        return mincost;
    }

    private boolean isPalindrome(int s, int e, String st){
        while(s<e){
            if(st.charAt(s)==st.charAt(e)){
                s++;
                e--;
            }else{
                return false;
            }
        }
        return true;
    }


    //using Memoization
    public int minCut_memo(String s) {
        int[] dp=new int[s.length()];
        Arrays.fill(dp, -1);
        return memo_mincut(0, s, dp)-1;
    }

    private int memo_mincut(int i, String s, int[] dp){
        if(i==s.length())
            return 0;
        if (dp[i]!=-1)
            return dp[i];

        int mincost=Integer.MAX_VALUE;

        for(int j=i; j<s.length(); j++){
            if(isPalindrome(i, j, s)){
                int cost=1+memo_mincut(j+1, s, dp);
                mincost=Math.min(mincost, cost);
            }
        }
        return dp[i]=mincost;
    }


    //Using Tabulation
    public int minCut_tab(String s) {
        int n=s.length();
        int[] dp=new int[n+1];
        dp[n]=0;

        for (int i = n-1; i >=0; i--) {
            int mincost=Integer.MAX_VALUE;
            for (int j = i; j < n; j++) {
                if(isPalindrome(i, j, s)){
                    int cost=1+dp[j+1];
                    mincost=Math.min(mincost, cost);
                }
            }
            dp[i]=mincost;
        }
        return dp[0]-1;
    }
}

