package backjoon.implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20057 {
    static int N, answer = 0;
    static int[][] sand;
    // 서, 남, 동, 북
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { -1, 0, 1, 0 };
    // 모래가 퍼지는 비율에 따른 위치 조정. 각 방향에 따라.
    static int[][] spreadX = { { -1, 1, -2, 2, -1, 1, -1, 1, 0, 0 }, { 0, 0, 0, 0, 1, 1, -1, -1, 2, 1 },
            { -1, 1, -2, 2, -1, 1, -1, 1, 0, 0 }, { 0, 0, 0, 0, 1, 1, -1, -1, -2, -1 } };
    static int[][] spreadY = { { 0, 0, 0, 0, -1, -1, 1, 1, -2, -1 }, { -1, 1, -2, 2, -1, 1, -1, 1, 0, 0 },
            { 0, 0, 0, 0, 1, 1, -1, -1, 2, 1 }, { -1, 1, -2, 2, -1, 1, -1, 1, 0, 0 } };
    // 모래가 퍼지는 비율.
    static int[] spreadPercent = { 1, 1, 2, 2, 7, 7, 10, 10, 5 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sand = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                sand[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        moveTornado();
        System.out.println(answer);
    }

    static void moveTornado() {
        int x = N / 2, y = N / 2; // 시작 위치: 격자의 중앙
        int direction = 0; // 초기 방향: 서쪽
        int length = 1; // 이동할 거리

        while (true) {
            if (x == 0 && y == 0)
                break; // (0, 0)에 도달하면 종료

            for (int i = 0; i < 2; i++) { // 각 거리마다 두 번 방향 전환
                for (int j = 0; j < length; j++) {
                    x += dx[direction];
                    y += dy[direction];
                    spreadSand(x, y, direction);
                    if (x == 0 && y == 0)
                        return; // (0, 0)에 도달하면 즉시 종료
                }
                direction = (direction + 1) % 4; // 방향 전환
            }

            length++; // 이동 거리 증가
        }
    }

    static void spreadSand(int x, int y, int direction) {
        int totalSpread = 0;
        int originalSand = sand[x][y];

        for (int i = 0; i < 9; i++) { // 9개 방향으로 모래 퍼트림
            int nx = x + spreadX[direction][i];
            int ny = y + spreadY[direction][i];
            int spreadAmount = (originalSand * spreadPercent[i]) / 100;

            if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                sand[nx][ny] += spreadAmount;
            } else {
                answer += spreadAmount; // 격자 밖으로 나간 모래
            }
            totalSpread += spreadAmount;
        }

        // alpha 위치 (이동하지 않은 모래의 양 계산)
        int remainSand = originalSand - totalSpread;
        // alpha 위치로 모래 이동
        int alphaX = x + dx[direction];
        int alphaY = y + dy[direction];
        if (alphaX >= 0 && alphaX < N && alphaY >= 0 && alphaY < N) {
            sand[alphaX][alphaY] += remainSand;
        } else {
            answer += remainSand; // 격자 밖으로 나간 나머지 모래
        }

        // 이동 후 현재 위치의 모래는 모두 이동했으므로 0으로 설정
        sand[x][y] = 0;
    }
}
