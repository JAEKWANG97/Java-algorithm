package swexpert;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//1. 지도의 가로, 세로 크기는 10이다.
//2. 사용자는 총 2명이며, 사용자A는 지도의 (1, 1) 지점에서, 사용자B는 지도의 (10, 10) 지점에서 출발한다.
//3. 총 이동 시간 M은 20이상 100이하의 정수이다. (20 ≤ M ≤ 100)
//4. BC의 개수 A는 1이상 8이하의 정수이다. (1 ≤ A ≤ 8)
//5. BC의 충전 범위 C는 1이상 4이하의 정수이다. (1 ≤ C ≤ 4)
//6. BC의 성능 P는 10이상 500이하의 짝수이다. (10 ≤ P ≤ 500)
//7. 사용자의 초기 위치(0초)부터 충전을 할 수 있다.
//8. 같은 위치에 2개 이상의 BC가 설치된 경우는 없다. 그러나 사용자A, B가 동시에 같은 위치로 이동할 수는 있다. 사용자가 지도 밖으로 이동하는 경우는 없다.

public class Solution_5644_유재광 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int T, M, A;
    static int[] userA, userB;
    static BatteryCharger[] BatteryChargers;
    static int[] currentUserA = new int[]{0, 0};
    static int[] currentUserB = new int[]{9, 9};
    static int totalPowerSum;

    static class BatteryCharger {
        int x;
        int y;
        int range;
        int power;

        public BatteryCharger(int x, int y, int range, int power) {
            this.x = x;
            this.y = y;
            this.range = range;
            this.power = power;
        }


    }

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine()); // 테스트케이스 수
        for (int tc = 1; tc <= T; tc++) {
            initVariables();
            simulation(0);
            printTotalPowerSum(tc);
        }

    }

    private static void initVariables() throws IOException {
        currentUserA = new int[]{0, 0};
        currentUserB = new int[]{9, 9};
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); // 총 이동시간
        A = Integer.parseInt(st.nextToken()); // BC의 개수
        userA = new int[M];
        userB = new int[M];
        BatteryChargers = new BatteryCharger[A];
        totalPowerSum = 0;

        initUserMove(userA); // userA의 움직임
        initUserMove(userB); // userB의 움직임
        initBatteryChargerInfo(); // BC의 정보 입력

    }

    private static void initUserMove(int[] user) throws IOException {
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            user[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void initBatteryChargerInfo() throws IOException {
        for (int i = 0; i < A; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int range = Integer.parseInt(st.nextToken());
            int power = Integer.parseInt(st.nextToken());
            BatteryCharger BatteryCharger = new BatteryCharger(x - 1, y - 1, range, power);
            BatteryChargers[i] = BatteryCharger;
        }
    }

    private static void simulation(int time) {

        List<Integer> userAwithBC = new ArrayList<>();
        List<Integer> userBwithBC = new ArrayList<>();
        // userA와 userB의 좌표에서 충전할 수 있는 BC List 생성
        for (int i = 0; i < A; i++) {
            if (isInBCArea(currentUserA, i)) {

                userAwithBC.add(i);
            }
            if (isInBCArea(currentUserB, i)) {

                userBwithBC.add(i);
            }
        }

        addBestPower(userAwithBC, userBwithBC);
        if (time == M) {
            return;
        }
        moveUser(time);
        simulation(time + 1);
    }

    private static void moveUser(int time) {
        // 위
        if (userA[time] == 1) {
            currentUserA[0] -= 1;
        }
        if (userB[time] == 1) {
            currentUserB[0] -= 1;
        }
        // 오른쪽
        if (userA[time] == 2) {
            currentUserA[1] += 1;
        }
        if (userB[time] == 2) {
            currentUserB[1] += 1;
        }
        // 아래
        if (userA[time] == 3) {
            currentUserA[0] += 1;
        }
        if (userB[time] == 3) {
            currentUserB[0] += 1;
        }
        // 왼쪽
        if (userA[time] == 4) {
            currentUserA[1] -= 1;
        }
        if (userB[time] == 4) {
            currentUserB[1] -= 1;
        }
    }

    private static boolean isInBCArea(int[] user, int idx) {
        BatteryCharger bc = BatteryChargers[idx];
        return bc.range >= Math.abs(bc.x - user[0]) + Math.abs(bc.y - user[1]);
    }

    private static void addBestPower(List<Integer> userAwithBC, List<Integer> userBwithBC) {
        int maxPowerSum = 0;
        for (int i = 0; i < A; i++) {
            for (int j = i + 1; j < A; j++) {
                // 둘 다 다른 조합
                if (userAwithBC.contains(i) && userBwithBC.contains(j)) {
                    maxPowerSum = Math.max(BatteryChargers[i].power + BatteryChargers[j].power, maxPowerSum);

                }

                if (userAwithBC.contains(j) && userBwithBC.contains(i)) {
                    maxPowerSum = Math.max(BatteryChargers[i].power + BatteryChargers[j].power, maxPowerSum);

                }

            }
            // 하나만 가지고 있을 때
            if (userAwithBC.contains(i) || userBwithBC.contains(i)) {
                maxPowerSum = Math.max(BatteryChargers[i].power, maxPowerSum);
            }
        }

        totalPowerSum += maxPowerSum;
    }

    private static void printTotalPowerSum(int tc) {
        System.out.println("#" + tc + " " + totalPowerSum);
    }
}

