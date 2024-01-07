package leetcode;

//문자 단위로 추출
public class ValidPalindrome_ver2 {
    public static void main(String[] args) {
        String s = "Dog geese God?";


    }

    private boolean isPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;

        //서로 중앙으로 이동해 나가다가 겹치는 지점에 도달하면 종료
        while (start < end) {
            //영숫자인지 판별하고 유효하지 않으면 한 칸씩 이동
            if (!Character.isLetterOrDigit(s.charAt(start))) {
                start++;
            } else if (!Character.isLetterOrDigit(s.charAt(end))) {
                end--;
            } else {
                //유효한 문자라면 앞 글자와 뒷 글자를 모두 소문자로 변경해 비교
                if (Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))) {
                    System.out.println();
                    return false;
                }

                start++;
                end--;
            }
        }
        return true;
    }

}
