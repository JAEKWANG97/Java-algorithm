package backjoon.simulation;

import java.io.*;
import java.util.*;

/*
 N×M인 격자판
 각 칸에 포함된 적의 수는 최대 하나
 N번행의 바로 아래(N+1번 행)의 모든 칸에는 성
 궁수 3명을 배치, 성이 있는 칸에 배치, 하나의 칸에는 최대 1명
 각각의 턴마다 궁수는 적 하나를 공격
 동시에 공격
 1. 거리가 D이하인 적 중에서 가장 가까운 적
 2.  적이 여럿일 경우에는 가장 왼쪽에 있는 적
 같은 적이 여러 궁수에게 공격당할 수
 궁수의 공격이 끝나면, 적이 이동한다. 적은 아래로 한 칸 이동
 성이 있는 칸으로 이동한 경우에는 게임에서 제외
 궁수의 공격으로 제거할 수 있는 적의 최대 수를 계산
 거리는 |r1-r2| + |c1-c2|
 행의 수 N, 열의 수 M, 궁수의 공격 거리 제한 D가
 3 ≤ N, M ≤ 15
 1 ≤ D ≤ 10
 입력 예시
 5 5 1
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
1 1 1 1 1
 */
public class P17135 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int M, N, D;
    static int[][] map;
    static Archer[] archers;

    static List<int[]> archersList;
    static final int TOTAL_ARCHER = 3;
    static int maxCount;
    static int[] lineMaxCount;

    //조합으로 아처 위치들을 만는데, 이 조합 뽑을 떄마다 하냐, 조합을 다 뽑고 하냐
    static class Archer {
        int x;
        int y;

        public Archer(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Archer{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }


    public static void main(String[] args) throws IOException {
        initVariables();
        simulation();
        printMaxCount();
    }


    //변수 초기화
    private static void initVariables() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        //궁수 좌표를 통해서 죽일 수 있는 적을 계산하기 때문에 N+1;
        map = new int[N][M];
        archers = new Archer[3];
        archersList = new ArrayList<>();

        //max카운트 초기화
        maxCount = Integer.MIN_VALUE;
        lineMaxCount = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        getArcherLocationByCombiantion(0, 0, new int[3]);
    }

    private static void getArcherLocationByCombiantion(int start, int cnt, int[] outPut) {
        if (cnt == TOTAL_ARCHER) {
            archersList.add(outPut.clone()); // output 배열 클론
            return;
        }

        for (int i = start; i < M; i++) {
            outPut[cnt] = i;
            getArcherLocationByCombiantion(i + 1, cnt + 1, outPut);
        }
    }


    private static void simulation() {
        // 궁수 배치
        for (int[] arr : archersList) {
            int[][] copyMap = copyMap();
            int curCount = 0;
            int[] lineCurCount = new int[N];
            setArchers(arr);
            for (int pointer = N - 1; pointer >= 0; pointer--) {

                // 각 아처마다 죽일 수 있는 적을 선별해야돼
                List<int[]> killEnemy = new ArrayList<>();

                for (Archer archer : archers) {
                    searchCanKillEnemy(archer, killEnemy, pointer, copyMap);
                }

                // 적을 죽이는데 count를 올려야함 근데 같은 적을 죽일 때는 count를 x
                for (int[] enemy : killEnemy) {
                    int enemyX = enemy[0];
                    int enemyY = enemy[1];
                    if (copyMap[enemyX][enemyY] == 1) {
                        copyMap[enemyX][enemyY] = 0;
                        curCount++;
                    }
                }

                // 궁수 전진 시키고
                for (Archer archer : archers) {
                    archer.x--;
                }

                lineCurCount[pointer] = curCount;
                if (lineCurCount[pointer] < lineMaxCount[pointer]) {
                    break;
                }
                lineCurCount[pointer] = Math.max(lineMaxCount[pointer], lineCurCount[pointer]);
            }
            maxCount = Math.max(curCount, maxCount);
        }
    }


    private static void setArchers(int[] arr) {
        for (int i = 0; i < TOTAL_ARCHER; i++) {
            archers[i] = new Archer(N, arr[i]);
        }
    }

    private static void searchCanKillEnemy(Archer archer, List<int[]> killEnemy, int pointer, int[][] copyMap) {
        int minX = N;
        int minY = N;
        boolean flag = false;
        int minDistance = Integer.MAX_VALUE;
        for (int i = pointer; i >= 0; i--) {
            for (int j = 0; j < M; j++) {
                if (copyMap[i][j] == 1 && canKillEnemy(archer, i, j)) {
                    if (calculateDistance(archer, i, j) <= minDistance) {
                        flag = true;
                        minX = i;
                        minY = j;
                        minDistance = calculateDistance(archer, i, j);
                    } else if (calculateDistance(archer, i, j) == minDistance) {
                        if (j < minY) {
                            minX = i;
                            minY = j;
                        }
                    }
                }
            }
        }

        if (flag) {
            killEnemy.add(new int[]{minX, minY});
        }
    }

    private static boolean canKillEnemy(Archer archer, int x, int y) {
        return D >= calculateDistance(archer, x, y);
    }

    private static int calculateDistance(Archer archer, int x, int y) {
        return Math.abs(archer.x - x) + Math.abs(archer.y - y);
    }

    private static int[][] copyMap() {
        int[][] copyMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            copyMap[i] = map[i].clone();
        }
        return copyMap;
    }

    private static void printMaxCount() {
        System.out.println(maxCount);
    }
}
