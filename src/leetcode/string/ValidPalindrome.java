package leetcode.string;

public class ValidPalindrome {
    public static void main(String[] args) {
        String input = "Do geese see God?";
        input = input.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
        char[] charArray_input = input.toCharArray();
        boolean answer =  isPalindrome(charArray_input);
    }

    private static boolean isPalindrome(char[] array){

        int i = 0;
        int j = array.length - 1;

        if(j == 0 ){
            return true;
        }

        while(i < j){
            if(array[i] != array[j]){

                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
