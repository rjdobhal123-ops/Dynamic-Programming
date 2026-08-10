import java.util.ArrayList;
import java.util.List;

public class House_Robber_II {
    class Solution {
        public int rob(int[] nums) {
            int n=nums.length;
            if(n==1)
                return nums[0];

            List<Integer> temp1=new ArrayList<>();
            List<Integer> temp2=new ArrayList<>();

            for(int i=0; i<n; i++){
                if(i!=0)
                    temp1.add(nums[i]);

                if(i!=n-1)
                    temp2.add(nums[i]);
            }

            return Math.max(rob_SpaceO(temp1), rob_SpaceO(temp2));
        }

        public int rob_SpaceO(List<Integer> nums){
            int n=nums.size();

            if (n==0)
                return 0;
            if (n==1)
                return nums.get(0);

            int prev1= nums.get(0);
            int prev2=0;

            for (int i = 1; i < n; i++) {
                int pick=nums.get(i);
                if (i>1)
                    pick+=prev2;

                int notpick=prev1;

                int curri=Math.max(pick ,notpick);
                prev2=prev1;
                prev1=curri;
            }
            return prev1;
        }
    }
}
