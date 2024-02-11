package leetcode.array;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;

public class TrappingRainWater {
    public static void main(String[] args) {
        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};

        System.out.println(trap(height));
    }

    //투 포인터를 이용한 풀이
//    static int trap(int[] height) {
//        int maxHeightValue = -1;
//        int maxHeightIndex = -1;
//
//        for (int i = 0; i < height.length; i++) {
//            if (maxHeightValue < height[i]) {
//                maxHeightIndex = i;
//                maxHeightValue = height[i];
//            }
//        }
//        int left = 0;
//        int curMax = 0;
//        int sum = 0;
//        while (left <= maxHeightIndex) {
//            if (curMax < height[left]) {
//                curMax = height[left];
//            } else {
//                sum += curMax - height[left];
//            }
//            left++;
//        }
//
//        int right = height.length - 1;
//        curMax = 0;
//        while (right >= maxHeightIndex) {
//            if (curMax < height[right]) {
//                curMax = height[right];
//            } else {
//                sum += curMax - height[right];
//            }
//
//            right--;
//        }
//        return sum;
//    }

    //stack 를 이용한 풀이
    static int trap(int[] height) {
        Deque<Integer> stack = new ArrayDeque<>();
        int volume = 0;

        for (int i = 0; i < height.length; i++) {
            //변곡점을 만나는 경우
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                //스택에서 꺼낸다.
                Integer top = stack.pop();

                if (stack.isEmpty()) {
                    break;
                }
                // 스택의 마지막 위치까지를 거리로 계산
                int distance = i - stack.peek() - 1;
                //현재 높이 또는 스택의 마지막 위치 높이 중 낮은 값에 방금 꺼낸 높이의 차이를 물 높이로 저장
                int waters = Math.min(height[i], height[stack.peek()] = height[top]);

                //물이 쌓이는 양은 거리와 물 높이의 곱
                volume += distance * waters;
            }

            //진행하면서 현재 위치를 스택에 삽입
            stack.push(i);

        }
        return 0;
    }
}