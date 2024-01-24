package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class CoordinateCombinations {
    static int n;
    static int m;
    static int[][] arr;
    public static int[][] copyMap;
    static List<Node> virusList;
    public static Queue<Node> queue = new LinkedList<>();
    public static int maxSafetyRoom = Integer.MIN_VALUE;

    static int[] directionX = {1, -1, 0, 0};
    static int[] directionY = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int virus = 0;
        virusList = new ArrayList<>();
        arr = new int[n][m];
        //arr 입력 받기
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int number = Integer.parseInt(st.nextToken());
                arr[i][j] = number;
                if (number == 2) {
                    virus += 1;
                    virusList.add(new Node(i, j));
                }
            }
        }
        dfs(0);
        System.out.println(maxSafetyRoom);
    }

    public static void dfs(int wallCnt) {
        if (wallCnt == 3) {
            bfs();
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 0) {
                    arr[i][j] = 1;
                    System.out.println(i + " " + j);
                    dfs(wallCnt + 1);
                    arr[i][j] = 0;
                }
            }
        }
    }


    private static void bfs() {
        for (Node node : virusList) {
            queue.offer(node);
        }

        copyMap = new int[n][m];
        for (int i = 0; i < n; i++) {
            copyMap[i] = arr[i].clone();
        }

        while (!queue.isEmpty()) {
            Node v = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = v.x + directionX[i];
                int ny = v.y + directionY[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    if (copyMap[nx][ny] == 0) {
                        copyMap[nx][ny] = 2;
                        queue.add(new Node(nx, ny));
                    }
                }
            }

        }
        calculateSafezoneArea(copyMap);
    }

    static void calculateSafezoneArea(int[][] arr) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(arr[i][j] == 0){
                    count += 1;
                }
            }
        }
        maxSafetyRoom = Math.max(maxSafetyRoom,count);
    }


    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
