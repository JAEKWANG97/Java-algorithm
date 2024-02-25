package backjoon;

import java.io.*;
import java.util.*;

public class P15961 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] arr;
    static int N, D, K, C;
    static Map<Integer, Integer> map = new HashMap<>();
    static int maxCount = 0;

    public static void main(String[] args) throws IOException {
        init(); // 입력 받기 및 초기 윈도우 설정
        System.out.println(maxCount);
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            if (i == K - 1) {
                initWindow();
            } else if (i >= K) {
                searchWindow(i - K);
            }
        }
    }

    private static void initWindow() {
        // 초기 윈도우 설정
        for (int i = 0; i < K; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        updateMaxCount(); // 초기 윈도우에 대한 최대 종류 수 갱신
    }

    private static void searchWindow(int i) {

        // 슬라이딩 윈도우 시작

        int removeIndex = i;
        int addIndex = (i + K) % N;

        // 이전 요소 제거
        if (map.getOrDefault(arr[removeIndex], 0) == 1) {
            map.remove(arr[removeIndex]);
        } else {
            map.put(arr[removeIndex], map.get(arr[removeIndex]) - 1);
        }
        // 새 요소 추가
        map.put(arr[addIndex], map.getOrDefault(arr[addIndex], 0) + 1);

        // 최대 종류 수 갱신
        updateMaxCount();
    }


    private static void updateMaxCount() {
        int currentCount = map.size();
        if (!map.containsKey(C)) {
            currentCount++; // 쿠폰 초밥이 윈도우 내에 없으면 종류 하나 추가
        }
        maxCount = Math.max(maxCount, currentCount);
    }
}