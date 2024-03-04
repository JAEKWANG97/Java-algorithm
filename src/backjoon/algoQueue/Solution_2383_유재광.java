import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Solution {
    static class Person {
        int r, c; // 사람의 위치

        Person(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Stair {
        int r, c, length; // 계단의 위치 및 길이

        Stair(int r, int c, int length) {
            this.r = r;
            this.c = c;
            this.length = length;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt(); // 테스트 케이스의 수

        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt(); // 방의 크기
            List<Person> people = new ArrayList<>();
            List<Stair> stairs = new ArrayList<>();

            // 지도 정보 입력 및 사람, 계단 위치 파악
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int input = sc.nextInt();
                    if (input == 1) {
                        people.add(new Person(i, j)); // 사람 위치 추가
                    } else if (input > 1) {
                        stairs.add(new Stair(i, j, input)); // 계단 정보 추가
                    }
                }
            }

            int answer = Integer.MAX_VALUE;
            // 모든 사람-계단 조합 탐색
            for (int bit = 0; bit < (1 << people.size()); bit++) {
                int time = calculateTime(people, stairs, bit);
                answer = Math.min(answer, time);
            }

            System.out.println("#" + tc + " " + answer);
        }
        sc.close();
    }

    // 각 조합별 최소 시간 계산 함수
    static int calculateTime(List<Person> people, List<Stair> stairs, int bit) {
        int[] timeToDescend = {0, 0}; // 계단별 내려가는데 걸리는 시간
        int[] waiting = new int[people.size()]; // 대기 시간

        for (int i = 0; i < people.size(); i++) {
            Person p = people.get(i);
//            Stair s = stairs.get(bit & (1 << i) ? 1 : 0);
//            int distance = Math.abs(p.r - s.r) + Math.abs(p.c - s.c) + 1; // 계단 입구까지 이동 시간 + 1분 대기
//            waiting[i] = distance;
        }

        // 계단별로 내려가는 사람 관리
        int[] descending = {0, 0}; // 현재 계단에 있는 사람 수
        int time = 0;
        while (true) {
            // 계단별로 사람 내려보내기
            for (int i = 0; i < 2; i++) {
                if (descending[i] > 0) {
                    timeToDescend[i]--;
                    if (timeToDescend[i] == 0) {
                        descending[i]--;
                    }
                }
            }

            // 대기 중인 사람 계단에 배치
            for (int i = 0; i < people.size(); i++) {
                if (waiting[i] <= time && (bit & (1 << i)) > 0 && descending[1] < 3) {
                    descending[1]++;
                    timeToDescend[1] = stairs.get(1).length;
                    waiting[i] = Integer.MAX_VALUE; // 계단에 배치된 사람은 대기열에서 제거
                } else if (waiting[i] <= time && (bit & (1 << i)) == 0 && descending[0] < 3) {
                    descending[0]++;
                    timeToDescend[0] = stairs.get(0).length;
                    waiting[i] = Integer.MAX_VALUE; // 계단에 배치된 사람은 대기열에서 제거
                }
            }

            // 모든 사람이 내려갔는지 확인
            boolean allDescended = true;
            for (int i : waiting) {
                if (i != Integer.MAX_VALUE) {
                    allDescended = false;
                    break;
                }
            }
            if (allDescended && descending[0] == 0 && descending[1] == 0) {
                break;
            }

            time++;
        }
        return time;
    }
}
