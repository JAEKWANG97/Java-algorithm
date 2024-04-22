package backjoon.temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_16500 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        int n = Integer.parseInt(br.readLine());

        String[] targets = new String[n];
        for (int i = 0; i < n; i++) {
            targets[i] = br.readLine();
        }
        for (String target : targets) {
            if (!simulate(target, input)) {
                System.out.println(0);
                return;
            }
        }
        System.out.println(1);

    }

    private static boolean simulate(String target, String input) {

        if (target.length() > input.length()) {
            return false;
        }
        char startChar = target.charAt(0);
        for (int i = 0; i <= input.length() - target.length(); i++) {
            if (startChar == input.charAt(i)) {
                boolean flag = true;
                for (int j = i; j < i + target.length(); j++) {
                    if (input.charAt(j) != target.charAt(j - i)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }
}
