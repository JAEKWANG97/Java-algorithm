package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1916 {
    static class BusInfo {
        int start;
        int end;
        int price;

        public BusInfo(int start, int end, int price) {
            this.start = start;
            this.end = end;
            this.price = price;
        }
    }

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N, M;
    private static int BASE, TARGET;

    private static List<List<BusInfo>> busInfos;

    public static void main(String[] args) throws IOException {
        init();
        solution();
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        busInfos = new LinkedList<>();

        for (int i = 0; i <= N; i++) {
            busInfos.add(new LinkedList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            BusInfo busInfo = new BusInfo(start, end, price);

            busInfos.get(start).add(busInfo);
        }

        st = new StringTokenizer(br.readLine());
        BASE = Integer.parseInt(st.nextToken());
        TARGET = Integer.parseInt(st.nextToken());
    }

    private static void solution() {
        PriorityQueue<BusInfo> pq = new PriorityQueue<>((o1, o2) -> o1.price - o2.price);
        boolean[] visited = new boolean[N + 1];
        pq.addAll(busInfos.get(BASE));

        while (!pq.isEmpty()) {
            BusInfo busInfo = pq.poll();
            if (busInfo.end == TARGET) {
                System.out.println(busInfo.price);
                return;
            }

            if (visited[busInfo.end]) {
                continue;
            }
            
            visited[busInfo.end] = true;
            for (BusInfo nextBusInfo : busInfos.get(busInfo.end)) {
                if (visited[nextBusInfo.end]) {
                    continue;
                }
                pq.add(new BusInfo(nextBusInfo.start, nextBusInfo.end, busInfo.price + nextBusInfo.price));
            }
        }
    }
}
