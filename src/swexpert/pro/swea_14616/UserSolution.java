package swexpert.pro.swea_14616;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class UserSolution {

    static class Player implements Comparable<Player> {
        int id;
        int ablity;
        int league;

        public Player(int id, int ablity, int league) {
            this.id = id;
            this.ablity = ablity;
            this.league = league;
        }

        @Override // 능력치 내림차순
        public int compareTo(Player o) {
            int value = o.ablity - this.ablity;
            if (value == 0) {
                value = this.id - o.id;
            }
            return value;
        }
    }

    static List<List<Player>> playerList = new ArrayList<>();

    void init(int N, int L, int mAbility[]) {
        for (int i = 0; i < L; i++) {
            playerList.add(new ArrayList<>());
        }
        for (int i = 0, j = 0; i < N; i++) { // 리그 0 이 제일 좋은 리그임
            playerList.get(j).add(new Player(i, mAbility[i], j));
            if (i > 0 && i % 3 == 0) {
                j += 1;
            }
        }


    }

    int move() {
        for (int i = 0; i < playerList.size(); i++) {
            Collections.sort(playerList.get(i));
        }

        for (int i = 0; i < playerList.size() - 1; i++) {
            Player temp = playerList.get(i).get(2);
            Player player = new Player(temp.id, temp.ablity, temp.league);
            playerList.get(i).get(2) = playerList.get(i + 1).get(0);
        }

        return 0;
    }

    int trade() {
        return 0;
    }

}