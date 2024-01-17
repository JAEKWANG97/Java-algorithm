package backjoon.dequeue.p1021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        LinkedList<Integer> queue = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        int[] targets = new int[M];

        for (int i = 0; i < M; i++) {
            targets[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            queue.add(i);
        }

        int count = 0;

        for (int i = 0; i < M; i++) {
            int target = targets[i];
            int index = queue.indexOf(target);

            if (index <= queue.size() / 2) {
                while (target != queue.getFirst()) {
                    queue.addLast(queue.pollFirst());
                    count++;
                }
            } else {
                while (target != queue.getFirst()) {
                    queue.addFirst(queue.pollLast());
                    count++;
                }
            }

            queue.poll();
        }

        System.out.println(count);
    }
}
