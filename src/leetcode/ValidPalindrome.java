package leetcode;

public class ValidPalindrome {
    public static void main(String[] args) {
        String input = "Do geese see God?";
        input = input.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
        char[] charArray_input = input.toCharArray();
        isPalindrome(charArray_input);
    }

    private static void isPalindrome(char[] array){

        int i = 0;
        int j = array.length - 1;

        if(j == 0 ){
            System.out.println("true");
            return;
        }

        while(i < j){
            if(array[i] != array[j]){
                System.out.println("false");
                return;
            }
            i++;
            j--;
        }
        System.out.println("true");
        return;
    }
}
