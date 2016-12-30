/*
 * Nilanshu Sharma
 * https://leetcode.com/problems/range-sum-query-mutable/
 *
 * Space complexity for fenwick tree is O(n)
 * Time complexity to create fenwick tree is O(nlogn)
 * Time complexity to update value is O(logn)
 * Time complexity to get prefix sum is O(logn)
 */ 

public class RangeSumQueryMutable {

    int[] arr;
    int[] BIT; 
    public NumArray(int[] nums) {
        this.arr = nums;
        this.BIT = new int[nums.length+1];
        buildBIT(); 
    }

    void update(int i, int val) {
        int index = i+1; 
        int diff = val - arr[i]; 
        arr[i] = val; 
        while(index <= BIT.length-1){
            BIT[index] += diff;   
            index = getNext(index);
        }
    }

    public int sumRange(int i, int j) {
        int sum1 = getPrefixSum(i);
        int sum2 = getPrefixSum(j+1);
        return sum2 - sum1; 
    }
    
    private int getParent(int index){
        return (index - (index & -index));
    }
    
    private int getNext(int index){
        return (index + (index & -index)); 
    }
    
    private int getPrefixSum(int index) {
        int sum=0; 
        while(index > 0) {
            sum += BIT[index]; 
            index = getParent(index);
        }
        return sum; 
    }
    
    private void createNode(int i, int val) {
        int index = i+1; 
        while(index <= BIT.length-1) {
            BIT[index] += val;  
            index = getNext(index); 
        }    
    }
    
    private void buildBIT (){
        for(int i=0; i<=arr.length-1; i++){
            createNode(i,arr[i]);
        }
    }
}