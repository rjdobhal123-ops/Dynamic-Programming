public class Boolean_Expression_toTRUE {
    public int booleanexpression(String exp){
        return rec_booleanexpression(0, exp.length()-1,1, exp);
    }

    private int rec_booleanexpression(int i, int j, int isTrue, String exp){
        if (i==j){
            if (isTrue==1)
                return exp.charAt(i)=='T'?1:0;
            else
                return exp.charAt(i)=='F'?1:0;
        }

        int ways=0;
        for (int k = i+1; k <=j; k+=2) {
            int leftTrue=rec_booleanexpression(i, k-1, 1, exp);
            int leftFalse=rec_booleanexpression(i, k-1, 0, exp);

            int rightTrue=rec_booleanexpression(k+1, j, 1, exp);
            int rightFalse=rec_booleanexpression(k+1, j, 0, exp);

            char op=exp.charAt(k);
            if (op=='&'){
                if (isTrue==1){
                    ways+=leftTrue*rightTrue;
                }else{
                    ways+=(leftTrue*rightFalse)+(leftFalse*rightTrue)*(leftFalse*rightFalse);
                }
            }else if(op=='|'){
                if (isTrue==1){
                    ways+=(leftTrue*rightFalse)+(leftFalse*rightTrue)+(leftTrue*rightTrue);
                }else{
                    ways+=leftFalse*rightFalse;
                }
            }else if(op=='^'){
                if (isTrue==1){
                    ways+=(leftTrue*rightFalse)+(leftFalse*rightTrue);
                }else{
                    ways+=(leftTrue*rightTrue)+(leftFalse*rightFalse);
                }
            }
        }
        return ways;
    }
}
