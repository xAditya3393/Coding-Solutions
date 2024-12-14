/*
    Refer to EPI 4.8 for explanation

*/


class Solution {
    public int reverse(int x) {
        
        long result = 0;
        int num = Math.abs(x);

        while(num != 0){

            int reminder = num%10;
            num = num/10;
            result = result*10 + reminder;

        }

        result = (x < 0) ? -1*result : result;
        if(result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) return 0;
        
        return (int)result;
    }
}