package swexpert.solvingClub.first;

import java.util.Scanner;

public class RockScissorsPaper {
    final static int ROCK = 1;
    final static int SCISSORS = 2;
    final static int PAPER = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("가위바위보 게임에 오신 것을 환영합니다!");
        System.out.println("'exit'을 입력하면 게임을 종료합니다.");

        System.out.print("플레이어 A의 선택 (1: 바위, 2: 가위, 3: 보): ");
        String inputA = scanner.next();

        System.out.print("플레이어 B의 선택 (1: 바위, 2: 가위, 3: 보): ");
        String inputB = scanner.next();

        int choiceA = Integer.parseInt(inputA);
        int choiceB = Integer.parseInt(inputB);

        String result = determineWinner(choiceA, choiceB);
        System.out.println(result);
    }


    static String determineWinner(int choiceA, int choiceB) {
        if (choiceA == choiceB) {
            return "비겼습니다!";
        }
        if ((choiceA == ROCK && choiceB == SCISSORS) || (choiceA == SCISSORS && choiceB == PAPER) || (choiceA == PAPER
                && choiceB == ROCK)) {
            return "승자는 플레이어 A입니다.";
        }
        return "승자는 플레이어 B입니다.";
    }
}
