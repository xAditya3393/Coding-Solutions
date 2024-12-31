/*


Divide and conquer algorithm
O(nlogn) - Average case running time
O(n^2) - Worst case running time 
In place & unstable (doesnt guareentee input element order while sorting) 


Algorithm
1. choose one element and find its right place in the index 
- this is taken care of partition function which places the element in the 
right index of a fully sorted input 
2. partition function returns an index of the element choosen in step 1 -> divides into 
two sub problems wherein the step 1. is applied recursively 


The runtime mainly depends on choosing the right pivot
- for an average case randomized partition function chooses a pivot with equal probability


Reference
Youtube - mycodeschool
1. Quicksort algorithm
2. Analysis of quicksort

*/


Quicksort(int[] inputArray, int startIndex, int endIndex){

    if(startIndex < endIndex){
        //pIndex = partition(inputArray, startIndex, endIndex);
        pIndex = randomizedPartition(inputArray, startIndex, endIndex);
        Quicksort(inputArray, startIndex, pIndex-1);
        Quicksort(inputArray, pIndex+1, endIndex);
    }

}

randomizedPartition(int[] inputArray, int startIndex, int endIndex){
    pivotIndex = random(startIndex, endIndex);
    swap(A[pivotIndex], A[end]);
    partition(inputArray, startIndex, endIndex);
}


partition(int[] inputArray, int startIndex, int endIndex){
    pivot = inputArray[endIndex];
    pIndex = startIndex;

    for(int currIndex = startIndex; currIndex < endIndex; currIndex++){
        if(inputArray[currIndex] <= pivot){
            swap(inputArray, currIndex, pIndex++);
        }
    }
}