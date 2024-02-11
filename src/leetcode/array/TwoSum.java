package leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 4};
        int target = 6;
        int[] arr = twoSum(nums, target);

        System.out.println(Arrays.toString(arr));
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numsMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            numsMap.put(nums[i], i);
        }

        // target에서 첫 번째 수를 뺀 결과를 키로 조회하고 현재 인덱스가 아닌경우 정답으로 리턴
        for (int i = 0; i < nums.length; i++) {
            if (numsMap.containsKey(target - nums[i]) && i != numsMap.get(target - nums[i])) {
                return new int[]{i, numsMap.get(target - nums[i])};
            }
        }

        return null;
    }
}

