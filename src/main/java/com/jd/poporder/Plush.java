package com.jd.poporder;

public class Plush {
    public int[] twoSum(int[] nums, int target) throws Exception {
        int lenght = nums.length;
        for(int i = 0;i<lenght;i++){
            for(int j=i+1;j<lenght;j++){
                if(nums[i] + nums[j] == target){
                    return new int[]{i,j};
                }
            }
        }
        throw new Exception("没有找到这样的数据");
    }
    public static void main(String[] args) throws Exception {
        int[] nums = {2,7,11,15};
        int target = 13;
        Plush plush = new Plush();
        int[] result = plush.twoSum(nums, target);
        System.out.println(result[0]+":"+result[1]);
    }
}
