/*

Weight of a non-negative integer x = number of set bits 1 in binary form
condition - x and y to have same weight & |y-x| to be as small as possible


Brute Force 
We can start finding weights of all integers around x 
which are - x-1, x+1, x-2, x+2 ....
This is not feasible because:
if x = 8 - we attempt for 7, 9, 6, 10, 5, 11, 4(stopping here) - which is minimum 2^(3-1) attempts
For a large x = 2^(30) - the closest x with same weight is 2^(29)/2^(31) - which is one billion integers - 2^(29) - 1 tries



Follow-up 
Natural attempt is to look for least significant bit(lsb) and swap it with non set bit
This provides correct results in some cases - 2(10) -> 1(01)
but, fails in certain cases - 7(111) -> 14(1110), but 11(1011) is smaller and has same weight


First Observation 
Observe how we swaped the bits resulting in larger integer in few cases earlier
We need to swap the bits at k1 and k2, such that, |2^(k1) - 2^(k2)| is the smallest 
-> this can only happen if the bits at k1 and k2 is consecutive
1. find two consecutive bits which differ 
2. swap them

Improvement 
Iterate through the length of the binary word of integer to find two consecutive bits which differ and swap them 
Time - O(n), n is the length of the word
Space - O(1)



[Variant Question - Solve in O(1) time and space]
Second Observation 
* We iterate entire binary word to find two bits which differ -> To solve this we need to find the two consecutive bits in constant time 
* Integer can only be even or odd for any given integer 

Taking two observations into account
Two cases:
1. even number - lowest 1 bit set > lowest 0 bit set - ex: 2(10), 4(100), 6(110) ..
- observe how the consecutive lower bit next to set bit is always 0
2. odd number -  lowest 0 bit set > lowest 1 bit set - ex: 1(01), 3(011), 5(101) ..
- observe how the consecutive lower bit next to non-set bit is always 1

we need bit manipulation to
1. find the set bit and unset bit 
1. adjust them accordinly based on two cases - even/odd 


Improvement
1. Find lowest bit set to 0 & 1 for a given integer x
2. Then based on conditions
- lowest 1 bit set > lowest 0 bit set -> set the lowest 1 bit to 0 and next lowest bit to 1
- lowest 0 bit set > lowest 1 bit set -> set the lowest 0 bit to 1 and next lowest bit to 0

Assuming bit shifting takes constant time 
Based on - https://stackoverflow.com/a/38524916

Time - O(1)
Space - O(1)
*/



public class Solution{

    //Assuming 64 bit integer
    public static long swapBitsForClosestWeightInt(long x){

        //Java has signed integers - we only take 63 bits into consideration
        for(int bitIndex = 0; bitIndex < 62; bitIndex++){
            if( ((x >> bitIndex) & 1) != ((x >> (bitIndex+1)) & 1) ){
                x ^= ((1 << (bitIndex+1)) | (1 << (bitIndex)));
                return x;
            }
        }

        //This condition hits on two cases - if all bits set are 0 [or] 1
        return -1; 
    }

    public static long swapBitsForClosestWeightIntConstantTime(long x){

        //x-1 sets all the bits lower than set bit to 1 - ex: 8(1000) -> 7(0111)
        //~(x-1) - gives a mask which certifies set bit cannot occur at 0's - ex ~7 - 8(1000) - lowest set bit cannot occur at pos 0,1,2
        int lowestSetBit = (x & ~(x-1));

        //x+1 sets the lowest unset bit to 1  - ex: 8(1000) -> 9(1001)
        //~x will set the lowest unset bit to 1 - ex: 8(1000) -> 7(0111)
        int lowestUnsetBit = (~x & (x+1))

        //x is even
        if(lowestSetBit > lowestUnsetBit){
            //flip lowestSetBit to 0
            x ^= lowestSetBit;
            //set the next lower consecutive bit to 1
            x |= (lowestSetBit>>1);
        }
        //x is odd
        else if(lowestSetBit < lowestUnsetBit) {  
            //set the lowest unset bit to 1
            x |= lowestUnsetBit;
            //flip the next lower consecutive bit to 0
            x ^= (lowestUnsetBit>>1);
        }

        //Note: lowestSetBit can never be equal to lowestUnsetBit <-> other way of seeing this is x can only be even or odd

        return x;
    }


    /**
     * Dry run:
     * 
     * 1. When x is even - x = 8(1000)
     * 
     * (x-1) - 7(0111), ~(x-1) - 8(1000)
     * lowestSetBit = (1000 & 1000) - 8(1000)
     * 
     * x+1 - 9(1001), ~x - 7(0111)
     * lowestUnsetBit = (0111 & 1001) - 1(0001) 
     * 
     * lowestSetBit > lowestUnsetBit
     * x=x^lowestSetBit -> 1000^1000 - 0(0000)
     * x=x|(lowestSetBit>>1) -> 0000|(0100) - 4(0100)
     * 
     * 
     * 2. when x is odd - x = 7(0111)
     * 
     * (x-1) - 6(0110), ~(x-1) - 9(1001)
     * lowestSetBit = (0111 & 1001) - 1(0001)
     * 
     * x+1 - 8(1000), ~x - 8(1000)
     * lowestUnsetBit = (1000 & 1000) - 8(1000) 
     * 
     * lowestSetBit < lowestUnsetBit
     * x = x|lowestUnsetBit -> 0111|1000 - 15(1111)
     * x = x^(lowestUnsetBit>>1) -> 1111^(0100) - 11(1011)
     * 
     */

}

