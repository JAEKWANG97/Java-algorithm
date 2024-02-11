package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P15 {
    public static void main(String[] args) {
        int[] nums = new int[]{-1,0,1,2,-1,-4};
        threeSum(nums);
    }
    // 합으로 0을 만들 수있는 3개의 엘리먼트를 출력

    static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
         Set<int[]> set = new HashSet<int[]>(Arrays.asList(nums));
        comb(new boolean[nums.length], nums, nums.length, 3, 0, answer);
        for (List<Integer> integers : answer) {
            for (Integer integer : integers) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
        return null;
    }

    static void comb(boolean[] visited, int[] nums, int n, int r, int start, List<List<Integer>> answer) {
        if (r == 0) {
            List<Integer> list = new ArrayList<>();
            int sum = 0;
            for (int i = 0; i < n; i++) {
                if (visited[i]) {
                    list.add(nums[i]);
                    sum += nums[i];
                }
            }
            if (sum == 0) {
                answer.add(list);
                return;
            }
            return;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            comb(visited, nums, n, r - 1, i + 1, answer);
            visited[i] = false;
        }
    }

}
