/*

Quotient -> when x divides y - y/x - assuming x and y are integers
quotient is an integer factor which signifies number of time x can be subtracted from y 
which can result in a reminder - integer factor where in (quotient*x) can [or] cannot completely negate y 



Brute Force 
Repeated subtraction
Keep subtracting until we obtain a reminder which is less than quotient 
ex: 7/3 - 7-3 = 4 -> 4-3 = 1 (stop here as 1 < 3)
quotient - 2

This can be highly inefficient for values of y much larger than x  
ex: y = 2^(32), x = 1
This leads to (2^(32))-1 subtractions 




Observation:
Based on above example - we need to find a way to reduce the number of subtration operations 
Instead of subtracting x each time - we can find largest factor of x (z) which can be deducted such that z <= y 
In binary representation, this equates to z=(2^k)*x, such that z <= y

ex: 7(111) / 3(011) -> 2^(1)*3 = 6 < 7 < 2^(2)*3 = 12
7(111) - 6(110) -> we add 2^(1) to quotient 


Improvement 
For y/x:
1. find (2^k)*x such that (2^k)*x <= y
2. negate (2^k)*x from y
3. repeat the process for y = y - (2^k)*x

Time - O(n^2), where n represents the number of set bits on y
In worst case - let y = all 32 bits are set = max integer, x = 1 
For each set bit, time = O(n) to find closest power of 2 
- for any (2^k)*x <= y, it takes k iterations of multiplying 2 to obtain (2^k)*x
Space - O(1) 



Observation
we dont have to compute 2^k factor each time -> Notice how this decreases with each iteration 
k on subsequent iterations can never be larger than previous iteration as we subtract the largest factor each time  
=> This is efficient as we can keep right shifting until we find the next eligible (2^g)*x, where (2^g)*x < (2^k)*x => g < k

Improvement 2
For y/x:
1. Compute largest 2^k and set it aside  
2. find (2^k)*x such that (2^k)*x <= y, if not right shit (2^k) until we find (2^k)*x <= y
2. negate (2^k)*x from y
3. repeat the process for y = y - (2^k)*x

Time - O(n), where n represents the number of set bits on y
The computation of (2^k) becomes contant as integer is limited to 32 bits => limiting the right shift operations to 32 at max => O(1) 
Space - O(1)


Variation - y is any integer (postive, zero or negative) & x is a nonzero integer 
If y = 0 -> quotient = 0
If y and x are any non zero integer => we keep track of the signs and compute for mod(y)/mod(x)
- if y < 0, x < 0 -> quotient = positive integer
- if only one is negative  -> quotient = negative integer

*/


public class Solution{

    //Improvement 2
    public int quotient(int y, int x){

        int power = 32;    
        int powerX = x << power;

        while(y >= x){

            //finding the highest two power of x
            while(powerX > y){
                powerX >>= 1;
                power--;
            }

            //only adding the highest power of two when multiplied to x diving y 
            //negating highest 2 power of x from y for next iteration
            result += 1 << powerX;
            y -= powerX;

        }

        return result;
    }

    //This function determines the number of negative inputs and returns result accordinly 
    public int quotientInput(int y, int x){

        int negativeInput = 0;

        if(y < 0) {
            negativeInput++;
        }
        if(x < 0) {
            negativeInput++;
        }

        int result = quotient(Math.abs(y), Math.abs(x));

        return (negativeInput%2) ? (-1)*result : result;
    }
}
