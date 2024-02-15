package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1873 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int T; // 초기화 해야됨
    static int H, W; // 초기화 해야됨 맵의 크기
    static char[][] map; // 초기화 해야됨 맵 생성
    static int N; // 사용자 입력 개수
    static char[] userInput; // 사용자
    static int[] tankXY; // 전차좌표
    static char[] tankVector = new char[]{'<', '^', '>', 'v'};

    static char curTankVector; // 현재 전차 방향

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            initVariables();
        }
    }

    private static void initVariables() throws IOException {
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        tankXY = new int[2];
        for (int i = 0; i < H; i++) {
            String str = br.readLine();
            for (int j = 0; j < W; j++) {
                map[i][j] = str.charAt(j);
                for (int k = 0; k < 4; k++) {
                    if (map[i][j] == tankVector[k]) {
                        tankXY[0] = i;
                        tankXY[1] = j;
                    }
                }
            }
        }

        N = Integer.parseInt(br.readLine());
        String userInputStr = br.readLine();
        userInput = new char[N];
        for (int i = 0; i < N; i++) {
            userInput[i] = userInputStr.charAt(i);

        }

    }

    private static void simulateGame(int idx) {
        if (idx == N) {
            return;
        }

        char command = userInput[idx];

        if (command == 'U') {
            commandUp();
        }
        if (command == 'D') {
            commandDown();
        }
        if (command == 'L') {
            commandLeft();
        }
        if (command == 'R') {
            commandRight();
        }
        if (command == 'S') {
            commandShoot();
        }
        simulateGame(idx + 1);
    }

    static void commandUp() {
        map[tankXY[0]][tankXY[1]] = '^';
        if (isIn(tankXY[0] - 1, tankXY[1]) && map[tankXY[0] - 1][tankXY[1]] == '.') {
            map[tankXY[0]][tankXY[1]] = '.';
            map[tankXY[0] - 1][tankXY[1]] = '^';
        }
    }

    static void commandDown() {
        map[tankXY[0]][tankXY[1]] = 'v';
        if (isIn(tankXY[0] + 1, tankXY[1]) && map[tankXY[0] + 1][tankXY[1]] == '.') {
            map[tankXY[0]][tankXY[1]] = '.';
            map[tankXY[0] + 1][tankXY[1]] = 'v';
        }
    }

    static void commandLeft() {
        map[tankXY[0]][tankXY[1]] = '<';
        if (isIn(tankXY[0], tankXY[1] - 1) && map[tankXY[0]][tankXY[1] - 1] == '.') {
            map[tankXY[0]][tankXY[1]] = '.';
            map[tankXY[0]][tankXY[1] - 1] = '<';
        }
    }

    static void commandRight() {
        map[tankXY[0]][tankXY[1]] = '>';
        if (isIn(tankXY[0], tankXY[1] + 1) && map[tankXY[0]][tankXY[1] + 1] == '.') {
            map[tankXY[0]][tankXY[1]] = '.';
            map[tankXY[0]][tankXY[1] + 1] = '>';
        }
    }

    static void commandShoot() {
        if (map[tankXY[0]][tankXY[1]] == '^') {
            shoot(tankXY[0], tankXY[1], '^');
        }
        if (map[tankXY[0]][tankXY[1]] == 'V') {
            shoot(tankXY[0], tankXY[1], 'V');
        }
        if (map[tankXY[0]][tankXY[1]] == '<') {
            shoot(tankXY[0], tankXY[1], '<');
        }
        if (map[tankXY[0]][tankXY[1]] == '>') {
            shoot(tankXY[0], tankXY[1], '>');
        }

    }

    static void shoot(int x, int y, char vector) {
//      *	벽돌로 만들어진 벽
//      #	강철로 만들어진 벽
        if (vector == '^') {
            while (isIn(x, y)) {
                if (map[x][y] == '*') {
                    map[x][y] = '.';
                    return;
                }
                if (map[x][y] == '#') {
                    return;
                }
                x--;
            }
        }
        if (vector == '<') {
            while (isIn(x, y)) {
                if (map[x][y] == '*') {
                    map[x][y] = '.';
                    return;
                }
                if (map[x][y] == '#') {
                    return;
                }
                y--;
            }
        }
        if (vector == 'v') {
            while (isIn(x, y)) {
                if (map[x][y] == '*') {
                    map[x][y] = '.';
                    return;
                }
                if (map[x][y] == '#') {
                    return;
                }
                x++;
            }
        }
        if (vector == '>') {
            while (isIn(x, y)) {
                if (map[x][y] == '*') {
                    map[x][y] = '.';
                    return;
                }
                if (map[x][y] == '#') {
                    return;
                }
                y++;
            }
        }
    }

    static boolean isIn(int x, int y) {
        return (x >= 0 && x < H && y >= 0 && y < W);
    }
    /*
    U	Up : 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다.
    D	Down : 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다.
    L	Left : 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다.
    R	Right : 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동한다.
    S	Shoot : 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.
     */

}
