package backjoon.algoQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2110 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] houses = new int[N];
        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }

        // 집의 좌표를 오름차순으로 정렬
        Arrays.sort(houses);

        // 이진 탐색을 위한 시작점과 끝점 설정
        int minDistance = 1; // 가능한 최소 거리
        int maxDistance = houses[N - 1] - houses[0]; // 가능한 최대 거리
        int result = 0;

        while (minDistance <= maxDistance) {
            int mid = (minDistance + maxDistance) / 2;
            int lastInstalled = houses[0];
            int count = 1;

            // mid 거리를 유지하며 공유기 설치 가능 여부 확인
            for (int i = 1; i < N; i++) {
                if (houses[i] - lastInstalled >= mid) {
                    count++;
                    lastInstalled = houses[i];
                }
            }

            // 공유기 설치 조건 판단
            if (count >= C) { // C개 이상 설치 가능한 경우
                minDistance = mid + 1;
                result = mid; // 최대 거리 갱신
            } else {
                maxDistance = mid - 1;
            }
        }

        System.out.println(result);
    }
}
