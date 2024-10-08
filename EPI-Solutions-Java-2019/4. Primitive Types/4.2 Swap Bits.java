/*

Assuming the input is an array where we are provided i, j indices denoting position of bits to be swapped  

Brute Force 
extract bits and save them to local variables and fill in reqired bits
Time - O(n), where n is length of the word/the number of bits in the word - need to traverse the entire length to extract bits
Space - O(1)



First Observation 
we only need to swap bits if they are different - if same, swapping would result in same input

Improvement 
- get the bits at i and j and check if they are different
- create mask with i and j position set, and do an xor with input and mask => x^1 =0 if x=1, =1 if x=0
Time - O(1), independent of the size of the input  
Space - O(1)

*/



class Solution{

    public void reverseBits(int i, int j, int inputWord){

        //swap only if bits are different
        if((inputWord>>i) & 1 != (inputWord>>j) & 1){
            int bitMask = ((1<<i) | (1<<j));
            inputWord ^= bitMask;
        }
    }

}