package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P17281 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N; // 이닝 수
    static int[][] performance; // 각 이닝에서 각 선수의 성능
    static boolean[] visited; // 순열 생성을 위한 방문 체크
    static int[] order; // 타자 순서
    static int maxScore = 0; // 최대 점수

    public static void main(String[] args) throws IOException {
        initData();
        visited = new boolean[9];
        order = new int[9];
        visited[0] = true; // 4번 타자 고정
        order[3] = 0; // 4번 타자는 항상 1번 선수
        createPermutation(0);
        System.out.println(maxScore);
    }

    static void initData() throws IOException {
        N = Integer.parseInt(br.readLine());
        performance = new int[N][9];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                performance[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void createPermutation(int depth) throws IOException {
        if (depth == 9) {
            simulate();
            return;
        }

        if (depth == 3) { // 4번 타자 위치 건너뛰기
            createPermutation(depth + 1);
            return;
        }

        for (int i = 1; i < 9; i++) {
            if (!visited[i]) {
                visited[i] = true;
                order[depth] = i; //  output 배열에는 순서가 저장되어 있음
                createPermutation(depth + 1);
                visited[i] = false;
            }
        }
    }

    private static void simulate() {
        int idx = 0;
        int score = 0;

        for (int inning = 0; inning < N; inning++) {
            int outCount = 0;
            int[] gameInfo = new int[9];
            // 한 이닝 시작
            while (outCount < 3) {
                int result = performance[inning][order[idx]];
                if (result == 0) {
                    outCount += 1;
                } else {
                    gameInfo[order[idx]] += result;
                    for (int i = 0; i < 9; i++) {
                        if (gameInfo[i] > 0 && i != order[idx]) {
                            gameInfo[i] += result;
                        }
                        if (gameInfo[i] >= 4) {
                            score += 1;
                            gameInfo[i] = 0;
                        }
                    }
                }
                idx = (idx + 1) % 9;
            }
        }
        maxScore = Math.max(score, maxScore);
    }

}
