/* Refer: EPI-Solutions-Java-2019 - 4.3 Reverse Bits.java */

public class Solution {
    
    public int reverseBits(int n) {
         
        
        int result = 0;
        int mask = 1;
        
        for(int pos=0; pos<32; pos++){
            result |= (n&mask);
            n>>=1;
            if(pos != 31) result <<= 1;
        }
        
        return result;
    }
}


public class Solution {
    //NOTE - java by default is signed bit integer
    public int reverseBits(int n) {
         
        n = (n >>> 16) | (n << 16);
        n = ((n & 0xFF00FF00) >>> 8) | ((n & 0x00FF00FF) << 8);
        n = ((n & 0xF0F0F0F0) >>> 4) | ((n & 0x0F0F0F0F) << 4);
        n = ((n & 0xCCCCCCCC) >>> 2) | ((n & 0x33333333) << 2);
        n = ((n & 0xAAAAAAAA) >>> 1) | ((n & 0x55555555) << 1);
        
        return n;
    }
}