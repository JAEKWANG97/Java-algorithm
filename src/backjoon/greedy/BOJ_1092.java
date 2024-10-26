package backjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1092 {

    static int N;
    static int M;

    static List<Integer> crain;

    static PriorityQueue<Integer> box;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        crain = new ArrayList<>();
        box = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < N; i++) {
            crain.add(Integer.parseInt(st.nextToken()));
        }

        crain.sort(Collections.reverseOrder());
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            box.add(Integer.parseInt(st.nextToken()));
        }
    }

    private static void solve() {
        int idx = 0;
        int time = 0;
        int max = crain.get(0);
        while (!box.isEmpty()) {
            int curBox = box.poll();
            if (curBox > max) {
                System.out.println(-1);
                return;
            }
            if (idx == 0) {
                time += 1;
            }
            if (curBox > crain.get(idx++)) {
                idx = N;
            }

            if (idx == N) {
                idx = 0;
            }
        }
        System.out.println(time);
    }
}