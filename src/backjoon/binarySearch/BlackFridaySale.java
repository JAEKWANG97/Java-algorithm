import java.util.*;

public class BlackFridaySale {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int C = scanner.nextInt();
        int[] weights = new int[N];
        for (int i = 0; i < N; i++) {
            weights[i] = scanner.nextInt();
        }

        if (canPurchase(weights, C)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

        scanner.close();
    }

    private static boolean canPurchase(int[] weights, int target) {
        List<Integer> combinations = new ArrayList<>();
        int n = weights.length;

        // 모든 가능한 무게 조합을 생성
        for (int i = 0; i < n; i++) {
            combinations.add(weights[i]);
            for (int j = i + 1; j < n; j++) {
                combinations.add(weights[i] + weights[j]);
                for (int k = j + 1; k < n; k++) {
                    combinations.add(weights[i] + weights[j] + weights[k]);
                }
            }
        }

        // 조합 리스트 정렬
        Collections.sort(combinations);

        // 이분 탐색으로 각 조합을 검사
        for (int weight : combinations) {
            if (Collections.binarySearch(combinations, target - weight) >= 0) {
                return true;
            }
        }

        return false;
    }
}
