package backjoon.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P6987 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int[][] arr;
    static boolean possible;

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 4; i++) {
            arr = new int[6][3]; // 각 팀의 승, 무, 패 횟수를 저장할 배열
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 3; k++) {
                    arr[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            possible = false;
            dfs(0, 1); // 첫 번째 팀부터 시작하여 모든 경기 결과 탐색
            sb.append(possible ? 1 : 0).append(' ');
        }
        System.out.println(sb.toString());
    }

    private static void dfs(int team1, int team2) {
        if (team2 == 6) {
            dfs(team1 + 1, team1 +2);
            return;
        }

        if (team1 >= 5) {
            // 탐색
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    if(arr[i][j] != 0){
                        possible = false;
                        return;
                    }
                }
            }

            possible = true;
            return;
        }

        // 이기는 경우
        if (arr[team1][0] > 0 && arr[team2][2] > 0) {
            arr[team1][0]--;
            arr[team2][2]--;
            dfs(team1, team2 + 1);
            arr[team1][0]++;
            arr[team2][2]++;
        }
        // 무승부
        if (arr[team1][1] > 0 && arr[team2][1] > 0) {
            arr[team1][1]--;
            arr[team2][1]--;
            dfs(team1, team2 + 1);
            arr[team1][1]++;
            arr[team2][1]++;
        }
        // 지는 경우
        if (arr[team1][2] > 0 && arr[team2][0] > 0) {
            arr[team1][2]--;
            arr[team2][0]--;
            dfs(team1, team2 + 1);
            arr[team1][2]++;
            arr[team2][0]++;
        }
    }
}
