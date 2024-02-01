package backjoon;

public class test {
    static int count = 0;
    static int n = 3;

    public static void main(String[] args) {
        fprint();
    }

    static void fprint() {
        if (count == n) {

            return;
        }

        System.out.println("hi" + count);
        count += 1;

        fprint();

        count -= 1;
        System.out.println("bye" + count);
    }
}
