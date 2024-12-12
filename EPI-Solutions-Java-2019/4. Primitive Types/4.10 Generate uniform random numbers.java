/*

Observation:

How would you mimic a three outcome result with a two outcome uniform random generator
Intuitively we flip the coin three time, why ?
In binary - for a given two outcome uniform random generator (0 & 1), there are three possibilities 00,01,11


To mimic any numbered outcome -> we need to find the closest power of two


Our question dictates an interval here, namely [a, b] (a & b are inclusive)
This gives total number of outcomes = b-a+1
Since we need to match the output of two outcome generator, There are three possibilities here 
1. b-a+1 < 2^(i)-1 - Here we encounter more cases to match than the required number of outcomes, we simply retry if the result + a doesnt fall within the range required 
1. b-a+1 = 2^(i)-1 - we simply return -> result + a 
1. b-a+1 > 2^(i)-1 - we go for the next power of 2   


Implementation
1. calculate the number of outcomes in the interval [a,b]
2. keep right shifting result obtained with the uniform random generator result until we get a result <= number of outcomes calculated in step 1
3. return result + a  

*/

//zeroOneGenerator() is the uniform two output generator

public int uniformRandomGenerator(int lowerBound, int upperBound){

    int numberOfOutcomes = upperBound-lowerBound+1;
    int result;


    do{
        result = 0;
        int offset = 0;
        while(offset <= numberOfOutcomes){
            offset = (offset << 1) | zeroOneGenerator();
        }

        result += offset;
    }while(result > upperBound)

    return result;
}


/*
    instead of performing offset calculation, we can keep a check on the number of called made to random generator and check if it exceeds number of outcomes required
*/


public int uniformRandomGenerator(int lowerBound, int upperBound){

    int numberOfOutcomes = upperBound-lowerBound+1;
    int offset;


    do{
        offset = 0;
        
        for(int calls = 0; (1<<calls) <= numberOfOutcomes; calls++){
            offset = (offset << 1) | zeroOneGenerator();
        }

    }while(offset >= numberOfOutcomes)  

    return offset + lowerBound;
}