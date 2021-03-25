package com.jd.poporder;

import java.util.Arrays;

public class MidNum {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 1.总长度
        int totalLength = nums1.length + nums2.length;
        // 2.中位数下标
        int mid_index = 0;
        if (totalLength == 0){
            return 0;
         }else if (totalLength == 1) {
            return nums1.length == 0 ? nums2[0] : nums1[0];
        }else {
            mid_index = (nums1.length + nums2.length)/2 -1;
        }

        // 3.偶数标识
        boolean oddNum = false;
        if(totalLength%2 == 0){
            oddNum = true;
        }

        // 排序后的索引记录，比较直到mid_index结束
        int totalIndex = 0;
        // 中位数直
        int mid_value = 0;
        // nums1数组游标
        int nums1Index = 0;
        // nums2数组游标
        int nums2Index = 0;

        while(true){
            // 设置临界条件，防止死循环
            if(nums1Index >= nums1.length && nums2Index >= nums2.length) break;

            if (totalIndex != 0 && totalIndex == mid_index){
                if (oddNum){
                    return (mid_value + Math.max(nums1[nums1Index], nums2[nums2Index])) / 2.0;
                }else {
                    return mid_value;
                }
            }
            // 数值比较并移动游标
            if(nums1[nums1Index] <= nums2[nums2Index]){
                mid_value = nums2[nums2Index];
                nums2Index++;
            }else {
                mid_value = nums1[nums1Index];
                nums1Index++;
            }
            totalIndex++;
        }
        return mid_value;
    }

    public static void main(String[] args) {
        int[] nums1 = {1,3,6,8,4};
        int[] nums2 = {2};
//        System.out.println(findMedianSortedArrays(nums1, nums2));

        System.out.println(Arrays.copyOf(nums1,2));

    }
}
