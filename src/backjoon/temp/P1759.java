package backjoon.temp;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1759 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static StringBuilder sb = new StringBuilder();
    static int L, C;

    static char[] arr; // 주어진 문자를 저장할 배열
    static char[] Vowel = new char[]{'a', 'e', 'i', 'o', 'u'}; // 모음 배열

    public static void main(String[] args) throws IOException {
        init();
        Arrays.sort(arr); // 사전식 배열을 위한 정렬
        dfs(0, 0, new char[L], 0, 0);
        System.out.println(sb);
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[C];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            arr[i] = st.nextToken().charAt(0);
        }
    }

    // DFS를 이용한 조합 생성 및 조건 검사
    private static void dfs(int start, int depth, char[] password, int vowelCount, int consonantCount) {
        if (depth == L) { // 조건을 만족하는 경우
            if (vowelCount >= 1 && consonantCount >= 2) {
                sb.append(new String(password)).append('\n');
            }
            return;
        }

        for (int i = start; i < C; i++) {
            char nextChar = arr[i];
            // 다음 문자가 모음인지 자음인지 판단
            if (isVowel(nextChar)) {
                password[depth] = nextChar;
                dfs(i + 1, depth + 1, password, vowelCount + 1, consonantCount);
            } else {
                password[depth] = nextChar;
                dfs(i + 1, depth + 1, password, vowelCount, consonantCount + 1);
            }
        }
    }

    // 모음 판단 함수
    private static boolean isVowel(char c) {
        for (char vowel : Vowel) {
            if (c == vowel) {
                return true;
            }
        }
        return false;
    }
}
