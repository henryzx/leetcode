package leetcode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by zhengxiao on 15/11/2016.
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] solution = new TwoSum().twoSum(new int[]{2, 7, 11, 15}, 9);
        System.out.print(Arrays.toString(solution));
    }

    public int[] twoSum(int[] nums, int target) {
        if(false){
            // 不排序的方案
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++){
                    // 遍历所有对
                    if (nums[i] + nums[j] == target){
                        return new int[]{i,j};
                    }
                }
            }
            return null;
        }

        HashMap<Integer, Integer> num2Index = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++){
            // 遍历一遍 nums
            // 元素放入hash
            // 检查 target - num 是否存在，如果存在返回。否则继续

            Integer compl = target - nums[i];
            if (num2Index.containsKey(compl)){
                // 找到，返回
                return new int[]{i, num2Index.get(compl)};
            }
            num2Index.put(nums[i], i);
        }

        return null;
    }
}
