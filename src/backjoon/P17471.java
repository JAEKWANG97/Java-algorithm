package backjoon;

import java.io.*;
import java.util.*;

class Node {

    int number;
    int value;
    List<Node> adjList;

    public Node(int number, int value) {
        this.number = number;
        this.value = value;
        adjList = new ArrayList<>();
    }

    public void addAdjNode(Node node) {
        adjList.add(node);
    }

    public List<Node> getAdjList() {
        return adjList;
    }

    @Override
    public String toString() {
        return "Node [number=" + number + ", value=" + value;
    }

}

public class P17471 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static Node[] population;
    static List<Node> a = new ArrayList<Node>();
    static List<Node> b = new ArrayList<Node>();
    static int minSum = Integer.MAX_VALUE;
    static int totalPopulation = 0;

    public static void main(String[] args) throws IOException {
        init();

        getPowerSet(new boolean[N], 0);

        System.out.println(Math.abs(minSum) == Integer.MAX_VALUE ? -1 : Math.abs(minSum));

    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        // 인덱스 0은 안씀
        population = new Node[N];

        st = new StringTokenizer(br.readLine());
        // 각 노드의 인구수 입력
        for (int i = 0; i < N; i++) {
            population[i] = new Node(i + 1, Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < N; i++) {
            totalPopulation += population[i].value;
        }
        // 각 노드의 인접한 노드 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            // 5 2 3 4 1 2
            // 각 인덱스 당 연결되어 있는 노드 기입
            int n = Integer.parseInt(st.nextToken());
            for (int j = 0; j < n; j++) {
                int nodeNum = Integer.parseInt(st.nextToken());
                population[i].addAdjNode(population[nodeNum - 1]);
                population[nodeNum - 1].addAdjNode(population[i]);
            }
        }

        minSum = Integer.MAX_VALUE;

    }

    private static void getPowerSet(boolean[] selected, int cnt) {
        if (cnt == N) {
            // 구역 맞는지 확인
            // 구역 population 계산
            if (isRight(selected, true) && isRight(selected, false)) {
                int sum1 = 0;
                int sum2 = 0;
                for (int i = 0; i < selected.length; i++) {
                    if (selected[i]) {
                        sum1 += population[i].value;
                    } else {
                        sum2 += population[i].value;
                    }
                }

                minSum = Math.min(minSum, Math.abs(sum2 - sum1));
            }
            return;
        }

        selected[cnt] = true;
        getPowerSet(selected, cnt + 1);
        selected[cnt] = false;
        getPowerSet(selected, cnt + 1);


    }

    private static boolean isRight(boolean[] selected, boolean flag) {

        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N];

        for (int i = 0; i < selected.length; i++) {
            // queue에 첫 노드 넣어주기. 해당 노드를 통해서 전체 노드를 순회 할 수 있는지 체크 할 거임
            if (selected[i] == flag) {
                queue.add(i);
                visited[i] = true;
                break;
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            // 현재 방문한 노드를 꺼내고 그 노드에서 갈 수 있는 노드들을 확인시킨다.
            // 확인 후 에 selected와 비교한다.
            for (int i = 0; i < population[cur].getAdjList().size(); i++) {
                int idx = population[cur].getAdjList().get(i).number - 1;
                // 현재 플래그와 맞으면 queue에 넣어주기
                if (selected[idx] == flag && !visited[idx]) {
                    queue.add(idx);
                    visited[idx] = true;
                }
            }

        }

        // queue를 방문 후에 visited와 selected를 비교하는데, selected가 아닌데 visit 한건 괜찮지만 selected가
        // 맞는데 visit 아니면 이어지는 노드가 아님
        for (int i = 0; i < selected.length; i++) {
            if (selected[i] == flag && !visited[i]) {
                return false;
            }
        }

        // 맞으면 값 계산;
        return true;
    }
}