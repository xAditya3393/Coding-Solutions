/*

For explanation refer to EPI 4.7

For this problem, there is a test case which fails while implementaion with base function signature pow(double x, int y)
x = 2.00000
n = -2147483648

This happens because while negative n conversaion where n = n*(-1) / -n => leads to overflow as max int value is 2^31 - 1 = 2147483647
conversion to long is required here to preserve the positive power conversion 

*/


class Solution {
    public double myPow(double x, int n) {
        long N = n;  //long conversion due to overflow 
        if(n < 0){
            x = 1/x;
            N = -N;
        } 
        
        return multiply(x,N);
        //return multiplyRecursion(x, N);
    }
    
    private double multiply(double x, long y){
        
        double result = 1;
        
        while(y > 0){
            
            if(y%2 != 0){
                result *= x;
            }
            
            x *= x;
            y >>= 1;
        }
        
        return result;
    }
    
    
    private double multiplyRecursion(double x, long y){
        if(y == 0) return 1;
        if(y == 1) return x;
        
        
        if(y%2 == 1){
            return x*multiplyRecursion(x, y-1); 
        }
        
        
        return multiplyRecursion(x*x, y/2);
        
     }
}