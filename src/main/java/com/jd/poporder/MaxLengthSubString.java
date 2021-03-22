package com.jd.poporder;

import java.util.HashSet;
import java.util.Set;

/**
 * 寻找无重复字符的最大长度的子串
 */
public class MaxLengthSubString {
    public static void main(String[] args) {
        String str = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(str));
    }
    public static int lengthOfLongestSubstring(String s) {
        int maxLength = 0;// 定义最大长度变量，记录下来
        int length = s.length();// 设置一变量存储字符串长度
        for(int i = 0;i < length;i++){
            // 用来判断字符重复出现
            Set<Character> charKey = new HashSet<Character>();
            int right_index = -1;
            if(i != 0){
                charKey.remove(s.charAt(i -1));
            }
            while(right_index+1<length && !charKey.contains(s.charAt(right_index+1))){
                charKey.add(s.charAt(right_index+1));
                right_index++;
            }
            maxLength = Math.max(maxLength, right_index - i + 1);
        }
        return maxLength;
    }
}
