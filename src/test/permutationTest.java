package test;

public class permutationTest {
    public static void main(String[] args) {
        char[] arr = new char[]{'A', 'B', 'C', 'D'};
        permutation(0, new boolean[4], 4, 4, arr, new char[4]);

    }

    private static void permutation(int depth, boolean[] visited, int r, int n, char[] arr, char[] order) {
        if (depth == r) {
            for (int i = 0; i < n; i++) {
                System.out.print(order[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                order[depth] = arr[i];
                permutation(depth + 1, visited, r, n, arr, order);
                visited[i] = false;
            }
        }
    }


}
