
/*

    Refer to EPI 5.2

*/


/*

Two Pass solution 
First pass brings all zero's to the begining of the array
Second pass brings all the two's to the end of the array

Time - O(n)
Space - O(1)

*/
class Solution {
    public void sortColors(int[] nums) {
        
        int zeroIndex = 0;
        
        for(int currentIndex = 0; currentIndex < nums.length; currentIndex++){
            if(nums[currentIndex] == 0){
                swap(nums, currentIndex, zeroIndex++);
            }
        }
        
        
        int twoIndex = nums.length-1;
        
        for(int currentIndex = nums.length-1; currentIndex >= 0 ; currentIndex--){
            if(nums[currentIndex] == 2){
                swap(nums, currentIndex, twoIndex--);
            }
        }
        
    }
    
    private void swap(int[] nums, int currentIndex, int swapIndex){
        int currentNum = nums[currentIndex];
        nums[currentIndex] = nums[swapIndex];
        nums[swapIndex] = currentNum;
    }
}


/*

One Pass solution 

We maintain following three pointers:
zeroIndex - before this index all the elements are zero, also marks the last index where the element is zero
oneIndex - marks the index where every element between zeroIndex and this index are one, also used as iterator for processing the current element 
twoIndex - marks all elements equal to two between oneIndex and twoIndex

Time - O(n)
Space - O(1)

*/

class Solution {
    public void sortColors(int[] nums) {
        
        int zeroIndex = -1;
        int oneIndex = 0;
        int twoIndex = nums.length;
        
        while(oneIndex < twoIndex){
            
            if(nums[oneIndex] == 0){
                swap(nums, oneIndex++, ++zeroIndex);
            }
            else if(nums[oneIndex] == 1){
                oneIndex++;
            }
            else if(nums[oneIndex] == 2){
                swap(nums, oneIndex, --twoIndex);
            }
            
        }
        
    }
    
    private void swap(int[] nums, int currentIndex, int swapIndex){
        int currentNum = nums[currentIndex];
        nums[currentIndex] = nums[swapIndex];
        nums[swapIndex] = currentNum;
    }
}