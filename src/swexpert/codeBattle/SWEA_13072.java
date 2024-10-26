package swexpert.codeBattle;

import java.util.*;

class SWEA_13072 {

    private Map<Integer, Soldier> soldierMap;
    private Set<Integer>[][] teamMapByScore;

    static class Soldier {
        int id;
        int team;
        int score;  // 기본 점수
        boolean isFired;

        public Soldier(int id, int team, int score) {
            this.id = id;
            this.team = team;
            this.score = score;
        }

        public void fire() {
            isFired = true;
        }
    }

    public void init() {
        soldierMap = new HashMap<>();
        teamMapByScore = new HashSet[6][6];
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                teamMapByScore[i][j] = new HashSet<>();
            }
        }
    }

    public void hire(int mID, int mTeam, int mScore) {
        Soldier soldier = new Soldier(mID, mTeam, mScore);
        soldierMap.put(mID, soldier);
        teamMapByScore[mTeam][mScore].add(mID);
    }

    public void fire(int mID) {
        teamMapByScore[soldierMap.get(mID).team][soldierMap.get(mID).score].remove(mID);
        soldierMap.remove(mID);
    }

    public void updateSoldier(int mID, int mScore) {
        Soldier soldier = soldierMap.get(mID);
        teamMapByScore[soldier.team][soldier.score].remove(mID); // 이동 전에 제거
        soldier.score = mScore;
        teamMapByScore[soldier.team][mScore].add(mID);
    }

    public void updateTeam(int mTeam, int mChangeScore) {
        if (mChangeScore > 0) {
            for (int i = 5; i > 0; i--) {
                if (i + mChangeScore > 5) {
                    continue;
                }
                if (teamMapByScore[mTeam][i] != null) {
                    teamMapByScore[mTeam][i + mChangeScore].addAll(teamMapByScore[mTeam][i]);
                    teamMapByScore[mTeam][i].clear();
                }
            }
        } else {
            for (int i = 1; i <= 5; i++) {
                if (i + mChangeScore < 1) {
                    continue;
                }
                if (teamMapByScore[mTeam][i] != null) {
                    teamMapByScore[mTeam][i + mChangeScore].addAll(teamMapByScore[mTeam][i]);
                    teamMapByScore[mTeam][i].clear();
                }
            }
        }
    }

    public int bestSoldier(int mTeam) {
        int bestSoldierID = -1;
        for (int i = 5; i > 0; i--) {
            if (teamMapByScore[mTeam][i] != null && !teamMapByScore[mTeam][i].isEmpty()) {
                bestSoldierID = Collections.max(teamMapByScore[mTeam][i]);
                break;
            }
        }
        return bestSoldierID;
    }
}
