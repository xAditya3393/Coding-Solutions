/*

Brute Force 
Repeated addition
ex: 5x3 - 0+5 -> 5+5 -> 10+5
Depending on how this algorithm is implemented, time complexity can vary

- When recursive solution is poorly written 
multiply(a,b) = multiply(a, b/2) + multiply(a, b/2)
Time - 2.2.2 ... b times -> O(2^b)
Space - recursive stack space: b,b/2,b/4....1 - O(logb)

- When recursive solution is optimized
multiply(a,b) = a + multiply(a, b-1)
Time - O(b)
space - recursive stack space: b,b-1,b-2....0 - O(b)


As the problem here states to only use bitwise operators due to hardware restrictions 
-> we might not have hardware sufficient for addition operation



Observation:
We can look at axb as
a x b - 2^(k0)*b + 2^(k1)*b + 2^(k2)*b + 2^(k3)*b + .... 
Observe how the above equation can we viewed as right shiting b and adding the previous result (which includes - sum & carry from previous operation)

ex: 13(1101) * 9(1001) - 2^(0)*9 + 0*9 + 2^(2)*9 + 2^(3)*9
observe  2^(2)*9 = 9 << 2, 2^(3)*9 = 9 << 3
Therefore 13(1101) * 9(1001) - 9 + (9<<2) + (9<<3) - addition to be handled bitwise

Bitwise addition - a,b
1. bitwise sum - a^b 
if (bits are same = zero), (they differ = one) => xor truth table
2. carry - a&b
if (both bits are 1 = 1),  (both 0 = 0),  (0 and 1 = 0) => and truth table
3. result - bitwise sum + (carry << 1) 
(carry << 1) because carry goes forward 
If we result in a carry, we repeat the operation result + carry - until carry = 0

ex: 2(10) + 3(011)
1. bitwise sum - 2^3 - 001
2. carry - 010
3. result - 001 + ((010)<<1) - 5(101), carry = 0 


Improvement 
1. Keep track of the sum from previous result 
2. Instead of having a counter which keeps track of right shifts, we can right shift for each iteration instead
3. Bitwise addition  
Time - O(x*y) 
where x is the set bits of one number - the whole operation repeats based on set bits of one number 
y is the time taken for addition operation on each iteration - happens each time bit is set  
Space - O(x) - for recursive stack trace

*/

public class Solution{

    public int multiply(int a, int b){

        int sum = 0;
        
        while(a != 0){

            if((a&1) != 0){
                sum = add(sum, b);
            }

            a >>= 1;
            b <<= 1;
        }
        return sum;
    }


    private bitwiseAdd(int a, int b){
        return  (b == 0) ? a : bitwiseAdd((a^b), ((a&b)<<1)); 
    }
}