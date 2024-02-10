package backjoon.bitMasking;
import java.util.Scanner;

public class P1094 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int length = sc.nextInt();
		int result=0;
		for(int i=0; i<7; i++) {
			if((length&(1<<i))>0) result++;
		}
		System.out.println(result);
	}
}