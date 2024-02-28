package swexpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_1767_유재광 {
    static int minDistance = Integer.MAX_VALUE; // 최소 전선 길이 저장
    static int maxCore = 0; // 최대 연결 코어 수 저장
    static final int[] dx = {-1, 1, 0, 0}; // 상, 하 방향 이동을 위한 배열
    static final int[] dy = {0, 0, -1, 1}; // 좌, 우 방향 이동을 위한 배열

    static class Core {
        int x, y; // 코어의 위치 (x, y)
        boolean isConnected = false; // 현재 코어가 연결되었는지 여부

        Core(int x, int y) { // 코어 생성자
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim()); // 테스트 케이스 수

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine().trim()); // 보드의 크기
            int[][] map = new int[N][N]; // 보드를 나타내는 2차원 배열
            ArrayList<Core> cores = new ArrayList<>(); // 코어 리스트

            // 보드 입력 받기
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    // 코어이고, 가장자리가 아닌 경우 리스트에 추가
                    if (map[i][j] == 1 && !(i == 0 || i == N - 1 || j == 0 || j == N - 1)) {
                        cores.add(new Core(i, j));
                    }
                }
            }

            // 백트래킹 시작
            backtrack(0, cores, map, 0, 0, N);
            // 결과 출력
            System.out.println("#" + tc + " " + minDistance);
            // 전역 변수 초기화
            minDistance = Integer.MAX_VALUE;
            maxCore = 0;
        }
    }

    // 백트래킹 메소드
    static void backtrack(int index, ArrayList<Core> cores, int[][] map, int connectedCores, int totalLength, int N) {
        // 모든 코어를 확인했을 때
        if (index == cores.size()) {
            // 연결된 코어 수가 더 많거나, 같으면서 전선 길이가 더 짧은 경우 결과 업데이트
            if (connectedCores > maxCore || (connectedCores == maxCore && totalLength < minDistance)) {
                maxCore = connectedCores;
                minDistance = totalLength;
            }
            return;
        }

        Core core = cores.get(index); // 현재 코어
        int x = core.x;
        int y = core.y;

        // 상, 하, 좌, 우 방향으로 연결 시도
        for (int direction = 0; direction < 4; direction++) {
            int newX = x + dx[direction];
            int newY = y + dy[direction];
            int length = 0; // 연결된 전선 길이

            // 전선을 놓을 수 있는지 확인
            while (newX >= 0 && newX < N && newY >= 0 && newY < N && map[newX][newY] == 0) {
                map[newX][newY] = 2; // 전선 놓기
                newX += dx[direction];
                newY += dy[direction];
                length++;
            }

            // 가장자리에 도달한 경우만 유효
            if (newX < 0 || newX >= N || newY < 0 || newY >= N) {
                backtrack(index + 1, cores, map, connectedCores + 1, totalLength + length, N);
            }

            // 놓았던 전선 제거 (백트래킹)
            while (length-- > 0) {
                newX -= dx[direction];
                newY -= dy[direction];
                map[newX][newY] = 0;
            }
        }

        // 현재 코어를 연결하지 않고 다음 코어로 넘어가기
        backtrack(index + 1, cores, map, connectedCores, totalLength, N);
    }
}
