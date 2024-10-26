package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Collections;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1202 {
    static class Item implements Comparable<Item> {
        int weight, value;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Item other) {
            return this.weight - other.weight;  // 무게에 대해 오름차순 정렬
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, K;
    static ArrayList<Item> items = new ArrayList<>();
    static ArrayList<Integer> bags = new ArrayList<>();
    static long totalValue = 0;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            items.add(new Item(weight, value));
        }

        for (int i = 0; i < K; i++) {
            bags.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(items);
        Collections.sort(bags);

        solve();
        System.out.println(totalValue);
    }

    private static void solve() {
        PriorityQueue<Integer> valueMaxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int j = 0;

        for (int capacity : bags) {
            while (j < N && items.get(j).weight <= capacity) {
                valueMaxHeap.add(items.get(j).value);
                j++;
            }
            if (!valueMaxHeap.isEmpty()) {
                totalValue += valueMaxHeap.poll();
            }
        }
    }
}
