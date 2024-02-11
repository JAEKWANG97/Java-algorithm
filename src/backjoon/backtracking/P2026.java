package backjoon.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P2026 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int K, N, F;
    static ArrayList<Integer>[] friends;
    static boolean found = false;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken()); // K명의 친구를 찾아야 함
        N = Integer.parseInt(st.nextToken()); // 전체 학생 수
        F = Integer.parseInt(st.nextToken()); // 친구 관계의 수

        friends = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            friends[i] = new ArrayList<>();
        }

        for (int i = 0; i < F; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            friends[a].add(b);
            friends[b].add(a);
        }

        for (int i = 1; i <= N; i++) {
            ArrayList<Integer> group = new ArrayList<>();
            group.add(i);
            dfs(i, group);
            if (found) break;
        }

        if (!found) {
            System.out.println(-1);
        }
    }

    static void dfs(int current, ArrayList<Integer> group) {
        if (group.size() == K) {
            found = true;
            for (int member : group) {
                System.out.println(member);
            }
            return;
        }

        for (int next : friends[current]) {
            boolean canAdd = true;
            for (int member : group) {
                if (!friends[member].contains(next)) {
                    canAdd = false;
                    break;
                }
            }
            if (canAdd) {
                group.add(next);
                dfs(next, group);
                if (found) return;
                group.remove(group.size() - 1);
            }
        }
    }
}
