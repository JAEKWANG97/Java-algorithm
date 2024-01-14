package leetcode.string;

public class LongestPalindromeSubString {
    static int left;
    static int maxLen;

    public static void main(String[] args) {
        String input = "cbbd";
        System.out.println("input = " + longestPalindrome(input));
    }

    private static void extendPalindrome(String s, int j, int k) {
        // 투 포인터가 유효한 범위 내에 ㅔ있고 ㅗ양쪽 끝 문자가 일치하는 팰린드롬인 경우 범위 확장
        while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
            j--;
            k++;
        }

        // 기존 최대 길이보다 큰 경우 값 교체
        if (maxLen < k - j - 1) {
            left = j + 1;
            maxLen = k - j - 1;
        }

    }

    public static String longestPalindrome(String s) {
        // 문자 길이 저장
        int len = s.length();

        // 길이가 1인 경우 예외 처리
        if (len < 2) {
            return s;
        }
        left = 0;
        maxLen = 0;
        //우측으로 한 칸 씩 이동하며 투 포인터 조사
        for (int i = 0; i < len - 1; i++) {
            extendPalindrome(s, i, i+1);
            extendPalindrome(s, i, i);

        }
        return s.substring(left, left + maxLen);
    }
}
