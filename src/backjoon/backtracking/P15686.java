package backjoon.backtracking;

import java.io.*;
import java.util.*;

class Chicken {
    int x;
    int y;

    public Chicken(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Chicken{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

class Home {
    int x;
    int y;

    public Home(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int calDistance(int x, int y) {
        return Math.abs(this.x - x) + Math.abs(this.y - y);
    }
}

public class P15686 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[][] map;

    static List<Chicken> chickenList;
    static List<Home> homeList;
    static int minTotalDistance = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        initVariables();
        combination(0, 0, new Chicken[M]);
        sb.append(minTotalDistance);
        System.out.println(sb.toString());
    }

    private static void initVariables() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        chickenList = new ArrayList<>();
        homeList = new ArrayList<>();

        // 맵 초기화
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int item = Integer.parseInt(st.nextToken());
                map[i][j] = item;
                if (item == 1) {
                    homeList.add(new Home(i, j));
                } else if (item == 2) {
                    chickenList.add(new Chicken(i, j));
                }
            }
        }
    }

    // 1. 조합 구하기
    private static void combination(int start, int cnt, Chicken[] chickens) {
        if (cnt == M) {
            // 2. 구해진 조합으로 각 집마다 최소 거리인 지점 찾기


            int curTotalDistance = 0;
            for (Home home : homeList) {
                int minDistance = Integer.MAX_VALUE;
                for (Chicken chicken : chickens) {

                    minDistance = Math.min(home.calDistance(chicken.x, chicken.y), minDistance);
                }

                curTotalDistance += minDistance;
            }

            minTotalDistance = Math.min(minTotalDistance, curTotalDistance);
            return;
        }

        for (int i = start; i < chickenList.size(); i++) {
            chickens[cnt] = chickenList.get(i);
            combination(i + 1, cnt + 1, chickens);
        }
    }


}
