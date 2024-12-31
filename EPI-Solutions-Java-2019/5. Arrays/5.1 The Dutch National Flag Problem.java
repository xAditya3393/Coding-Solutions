/*


Colors - Red(0)- start, White(1) - middle, Blue(2) - end

Brute Force 
Have extra space - arrays each with less, equal and greater elements  
iterate and assemble


Improvement - Two Pass
Taking partition function into account for quick sort - Sorting-Algorithms - S1
Since we know the elements, we can choose pivot 1 here 
first iteration - bring all the elements less than pivot to begining
Second iteration - bring all the elements greater than pivot to end 
The resulting array will have all 1's in the middle

Notice how this can be applied for any general pivot - with athe following additional steps
For any random pivotElement and pivotIndex:
- choose a random index as pivotIndex and element here is pivotElement
- make initial swap to move pivotElement to end 

Time - 2n, n for each pass -> O(n)
Space - O(1)



Improvement - One Pass
Instead of having two pass, we can combine in one pass by having two pointers 
with added implementation complexity

Time - n , only one pass -> O(n)
Space - O(1)



*/