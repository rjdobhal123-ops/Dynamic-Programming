public class Frog_jump {
    //After space optimization
    public int frogJump(int[] heights){
        int n=heights.length;
        if (n==0 || n==1)
            return 0;

        int prev1=0;
        int prev2=0;

        for (int i = 1; i < n; i++) {
            int onestep=prev1+Math.abs(heights[i]-heights[i-1]);
            int twostep=Integer.MAX_VALUE;
            if (i>1)
                twostep=prev2+Math.abs(heights[i]-heights[i-2]);

            int curri=Math.min(onestep, twostep);
            prev2=prev1;
            prev1=curri;
        }
        return prev1;
    }
}
