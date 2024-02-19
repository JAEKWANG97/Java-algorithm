package backjoon.backtracking;

import java.io.*;
import java.util.*;

class Team {
    int win;
    int lose;
    int draw;

    public Team(int win, int draw, int lose) {
        this.win = win;
        this.draw = draw;
        this.lose = lose;
    }

    @Override
    public String toString() {
        return "Team{" +
                "win=" + win +
                ", lose=" + lose +
                ", draw=" + draw +
                '}';
    }

    public boolean isDoneMatch() {
        return win == 0 && lose == 0 && draw == 0;
    }
}

public class P6987_2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static Team[] teams;
    static boolean isRightMatch;

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 4; i++) {
            initVariables();

            dfs(0, 1);
            if (isRightMatch) {
                sb.append(1).append(" ");
            } else {
                sb.append(0).append(" ");
            }
        }
        System.out.println(sb.toString());

    }


    private static void initVariables() throws IOException {
        st = new StringTokenizer(br.readLine());
        teams = new Team[6];
        isRightMatch = false;
        // 팀 스코어 입력
        for (int i = 0; i < teams.length; i++) {
            int win = Integer.parseInt(st.nextToken());
            int draw = Integer.parseInt(st.nextToken());
            int lose = Integer.parseInt(st.nextToken());
            teams[i] = new Team(win, draw, lose);
        }
    }

    private static void dfs(int homeIdx, int awayIdx) {
        if (awayIdx == teams.length) {
            homeIdx++;
            awayIdx = homeIdx + 1;
        }

        if (homeIdx == 5) {
            for (Team team : teams) {
                isRightMatch = team.isDoneMatch();
                if(!isRightMatch){
                    return;
                }
            }
            return;
        }

        // 이긴 경우
        if (teams[homeIdx].win > 0 && teams[awayIdx].lose > 0) {
            teams[homeIdx].win--;
            teams[awayIdx].lose--;
            dfs(homeIdx, awayIdx + 1);
            teams[homeIdx].win++;
            teams[awayIdx].lose++;
        }
        // 비긴 경우
        if (teams[homeIdx].draw > 0 && teams[awayIdx].draw > 0) {
            teams[homeIdx].draw--;
            teams[awayIdx].draw--;
            dfs(homeIdx, awayIdx + 1);
            teams[homeIdx].draw++;
            teams[awayIdx].draw++;

        }

        // 진 경우
        if (teams[homeIdx].lose > 0 && teams[awayIdx].win > 0) {
            teams[homeIdx].lose--;
            teams[awayIdx].win--;
            dfs(homeIdx, awayIdx + 1);
            teams[homeIdx].lose++;
            teams[awayIdx].win++;
        }
    }

}
