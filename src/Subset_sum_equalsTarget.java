public class Subset_sum_equalsTarget {
    public boolean isSubsetSum(int[] arr, int k){
        int n=arr.length;
        boolean[] prev=new boolean[k+1];
        prev[0]=true;

        if (arr[0]<=k)
            prev[arr[0]]=true;

        for (int index = 1; index < n; index++) {
            boolean[] curr=new boolean[k+1];
            curr[0]=true;
            for (int target = 1; target <=k ; target++) {
                boolean ignore=prev[target];
                boolean take=false;
                if (arr[index]<=target)
                    take=prev[target-arr[index]];

                curr[target]=ignore||take;
            }
            prev=curr;
        }
        return prev[k];
    }

}
