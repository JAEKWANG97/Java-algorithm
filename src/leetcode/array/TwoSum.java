package leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{3, 2, 4};
        int target = 6;
        int[] arr = solution.twoSum(nums, target);

        System.out.println(Arrays.toString(arr));
    }
}

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numsMap = new HashMap<>();

        //키와 값을 바꿔서 맵으로 저장
        for (int i = 0; i < nums.length; i++) {
            if (numsMap.containsKey(target - nums[i]) && i != numsMap.get(target - nums[i])) {

                return new int[]{i, numsMap.get(target - nums[i])};
            }
            numsMap.put(nums[i], i);
        }
        return null;
    }
}
