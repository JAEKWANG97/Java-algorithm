public class BitmaskCombination {
    public static void main(String[] args) {
        int n = 4; // 전체 원소의 수
        int r = 2; // 선택할 원소의 수
        generateCombinations(n, r);
    }

    public static void generateCombinations(int n, int r) {
        int allSets = 1 << n; // 모든 원소를 포함하는 집합 생성

        for (int i = 0; i < allSets; i++) {
            if (Integer.bitCount(i) == r) { // i의 비트 중 1의 개수가 r과 같으면, 즉 r개의 원소를 포함하는 조합이면
                printCombination(i, n); // 조합 출력
            }
        }
    }

    public static void printCombination(int set, int n) {
        for (int i = 0; i < n; i++) {
            if ((set & (1 << i)) != 0) { // i번째 비트가 1이면, 해당 원소가 조합에 포함됨
                System.out.print((i + 1) + " "); // 원소 출력 (원소를 1부터 시작하도록 조정)
            }
        }
        System.out.println(); // 다음 조합을 위한 줄바꿈
    }
}
