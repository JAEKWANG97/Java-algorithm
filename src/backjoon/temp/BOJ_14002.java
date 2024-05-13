package backjoon.temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_14002 {

    // LIS를 계산하는 함수
    public static List<Integer> lis(int[] arr) {
        int n = arr.length;
        int[] lisLength = new int[n];// 각 요소까지의 LIS 길이를 저장하기 위한 배열
        Arrays.fill(lisLength, 1); // 모든 요소의 길이를 1로 초기화 (최소한의 길이는 1)
        int[] previousIndex = new int[n]; // 이전 요소의 인덱스를 저장하기 위한 배열
        Arrays.fill(previousIndex, -1); // 이전 인덱스를 -1로 초기화 (초기값)

        // 모든 요소에 대해
        for (int i = 1; i < n; i++) {
            // 현재 요소 이전의 요소들을 검사
            for (int j = 0; j < i; j++) {
                // 현재 요소가 이전 요소보다 크고, LIS 길이를 늘릴 수 있다면
                if (arr[i] > arr[j] && lisLength[i] < lisLength[j] + 1) {
                    lisLength[i] = lisLength[j] + 1; // LIS 길이 갱신
                    previousIndex[i] = j; // 현재 요소의 이전 인덱스 업데이트
                }
            }
        }

        // 가장 긴 LIS의 길이와 끝나는 요소의 인덱스 찾기
        int maxIndex = 0;
        int maxLength = lisLength[0];
        for (int i = 1; i < n; i++) {
            if (lisLength[i] > maxLength) {
                maxLength = lisLength[i];
                maxIndex = i;
            }
        }

        // LIS 배열 추적
        List<Integer> lisSequence = new ArrayList<>();
        while (maxIndex != -1) {
            lisSequence.add(arr[maxIndex]);
            maxIndex = previousIndex[maxIndex];
        }
        Collections.reverse(lisSequence); // 역순으로 저장된 배열을 뒤집어 정상적인 순서로 만듦

        return lisSequence;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        List<Integer> result = lis(arr);
        System.out.println("가장 긴 증가하는 부분 수열: " + result);
    }

}
