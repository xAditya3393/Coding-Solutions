/*

Important Question:
Clarify if 
- order of the elements need to be maintained 
- even/odd elements in the resulting array need to be sorted 

Brute Force
We can easily solve this problem using extra space
we iterate over all the elements and add the even first 
do another iteration and place odd elements later (or do it in a single iteration)

Time - O(n), Space - O(n)


Observation
We can make use of working with arrays to our advantage
-> we can operate efficiently on both ends

Improvement:
We can divide the array into parts where on holds even elements, one has unprocessed elements
while the third part contains odd elements
-> This can be implemented by keeping track of indeces which denote each partition respectively 

While implementing, notice how for this problem we really dont need a third placeholder variable
-> instead we can directly make use of the fact that the first sequence of elements need to be even

Time - O(n), Space - O(1)

*/


public static void evenOdd(List<Integers> A){

    int nextEven = 0, nextOdd = A.size()-1;

    while(nextEven < nextOdd){
        if(A.get(nextEven)%2 == 0){
            nextEven++;
        }
        else Collections.swap(A, nextEven, nextOdd--);
    }
}