package swexpert.solvingClub.third;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChangYoungTown {
    static List<List<Integer>> adjList;  // 인접 리스트
    static boolean[] visited;  // 방문 여부 확인 배열

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();

        for (int i = 1; i <= T; i++) {
            int N = scanner.nextInt();
            int M = scanner.nextInt();

            adjList = new ArrayList<>();
            visited = new boolean[N + 1];
            // 인접 리스트 초기화
            for (int j = 0; j <= N; j++) {
                adjList.add(new ArrayList<>());
            }
            for (int j = 0; j < M; j++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                adjList.get(a).add(b);
                adjList.get(b).add(a);  // 양방향 관계
            }

            int count = 0;  // 무리의 개수
            for (int j = 1; j <= N; j++) {
                if (!visited[j]) {
                    dfs(j);
                    count++;  // 새로운 무리 발견
                }
            }

            System.out.println("#" + i + " " + count);

        }
        scanner.close();


    }

    static void dfs(int node) {
        visited[node] = true;
        for (int next : adjList.get(node)) {
            if (!visited[next]) {
                dfs(next);
            }
        }
    }
}
