/*
Brute Force 
We can do the following:
- get ith bit 
- store the result 
- left shift result and keep extracting bits until input is zero 
Time - O(n), where n is length of the word/the number of bits in the word
Space - O(1) 



First Observation 
Observe how the bits in the reverse result appear
Lets say a,b,c,d represent equal segments of input word -> the output would be rev(d), rev(c), rev(b), rev(a)

Improvement 
We can store the reverse of all possible segments in a cache and have computation reduce based on grouping size 
Time - O(n/k), n is the length of the word & k is the length of the group
Space - O(2^k) - This would be the cache required to store all possible combination of grouping size k along with their reverse 



Second Observation 
if we further divide the segments down to one bit, we can do it in place without cache 

Improvement
https://leetcode.com/problems/reverse-bits/discuss/54741/O(1)-bit-operation-C%2B%2B-solution-(8ms)
for 8 bit binary number abcdefgh, the process is as follow:
abcdefgh -> efghabcd -> ghefcdab -> hgfedcba
Time - O(1)
Space - O(1)
*/


class Solution{


    //Brute Force
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


    //Improvement 1
    public int reverseBitsWithCache(int n) {   
        
        //64bit input word

        //cache containing all possible combinations of 16 bit segments
        Map<Integer, Integer> reverseBitsLookup;
        
        int segmentSize = 16;
        int mask = 0xFFFF;

       // >>> -> logical shift - doesnt preserve signed bit
       return (reverseBitsLookup.get(n & mask) << (3*segmentSize)) |
       (reverseBitsLookup.get(n >>> (1*segmentSize) & mask) << (2*segmentSize)) |
       (reverseBitsLookup.get(n >>> (2*segmentSize) & mask) << (1*segmentSize)) |
       (reverseBitsLookup.get(n >>> (3*segmentSize) & mask)) |
       ;
    }


    //Improvement 2
    public int reverseBitsInPlaceWithoutCache(int n) {   
        //64 bit word length 
        //NOTE - java by default is signed bit integer
        n = ((n >>> 32) | (n << 32));
        n = ((n & 0xFFFF0000FFFF0000) >>> 16) | ((n & 0x0000FFFF0000FFFF) << 16);
        n = ((n & 0xFF00FF00FF00FF00) >>> 8) | ((n & 0x00FF00FF00FF00FF) << 8);
        n = ((n & 0xF0F0F0F0F0F0F0F0) >>> 4) | ((n & 0x0F0F0F0F0F0F0F0F) << 4);
        n = ((n & 0xCCCCCCCCCCCCCCCC) >>> 2) | ((n & 0x3333333333333333) << 2);
        n = ((n & 0xAAAAAAAAAAAAAAAA) >>> 1) | ((n & 0x5555555555555555) << 1);

        return n; 
    }
}