/* Refer: EPI-Solutions-Java-2019 - 4.1 Computing the parity of a word.java */


class Solution {
    public int hammingWeight(int n) {
    
        int result = 0;
        while(n > 0){
            result++;
            n &= (n-1);
        }
        return result;
        
    }
}