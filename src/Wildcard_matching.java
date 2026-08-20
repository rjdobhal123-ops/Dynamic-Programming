public class Wildcard_matching {
    //using recursion
    public boolean isMatchrec(String s, String p) {
        int n = s.length();
        int m = p.length();

        return recisMatch(n - 1, m - 1, s, p);
    }

    private boolean recisMatch(int index1, int index2, String s, String p) {

        // Both s and p are exhausted
        if (index1 < 0 && index2 < 0)
            return true;

        // Pattern remains, but string is exhausted
        if (index1 < 0 && index2 >= 0) {
            for (int i = 0; i <= index2; i++) {
                if (p.charAt(i) != '*')
                    return false;
            }
            return true;
        }

        // String remains, but pattern is exhausted
        if (index2 < 0 && index1 >= 0)
            return false;

        // Characters match OR pattern has '?'
        if (s.charAt(index1) == p.charAt(index2)
                || p.charAt(index2) == '?') {

            return recisMatch(index1 - 1, index2 - 1, s, p);
        }

        // Pattern has '*'
        if (p.charAt(index2) == '*') {

            // '*' matches one/more characters OR '*' matches empty
            return recisMatch(index1 - 1, index2, s, p)
                    || recisMatch(index1, index2 - 1, s, p);
        }

        return false;
    }


    //using tabulation
    class Solution {
        public boolean isMatch(String s, String p) {
            int n = s.length();
            int m = p.length();

            boolean[][] dp = new boolean[n + 1][m + 1];

            // Both string and pattern are empty
            dp[0][0] = true;

            // Empty string vs pattern
            // Only '*' can match an empty string
            for (int j = 1; j <= m; j++) {
                if (p.charAt(j - 1) == '*')
                    dp[0][j] = dp[0][j - 1];
            }

            // Fill the DP table
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {

                    // Characters match or pattern has '?'
                    if (s.charAt(i - 1) == p.charAt(j - 1)
                            || p.charAt(j - 1) == '?') {

                        dp[i][j] = dp[i - 1][j - 1];
                    }

                    // Pattern has '*'
                    else if (p.charAt(j - 1) == '*') {

                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                    }

                    // No match
                    else {
                        dp[i][j] = false;
                    }
                }
            }

            return dp[n][m];
        }
    }
}
