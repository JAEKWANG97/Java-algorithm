package swexpert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class SWEA_14618 {

    static class UserSolution {
        static int N, M;
        static char[][] mWords;
        static Map<Character, TreeSet<String>> words;
        static boolean[] players;
        static Set<String> usedWords;

        public void init(int N, int M, char[][] mWords) {
            UserSolution.N = N;
            UserSolution.M = M;
            UserSolution.mWords = mWords;
            words = new HashMap<>();
            players = new boolean[N];
            Arrays.fill(players, true);
            usedWords = new HashSet<>();

            for (char c = 'a'; c <= 'z'; c++) {
                words.put(c, new TreeSet<>());
            }

            for (int i = 0; i < M; i++) {
                String word = new String(mWords[i]).trim();
                if (!word.isEmpty()) {
                    char firstChar = word.charAt(0);
                    words.get(firstChar).add(word);
                }
            }
        }

        public int playRound(int mID, char mCh) {
            int currentPlayer = mID - 1; // 0 기반 배열에 맞추기 위해 인덱스 조정
            String currentWord = words.get(mCh).pollFirst();
            usedWords.add(currentWord);
            Set<String> roundUsedWords = new HashSet<>(); // 이 라운드에서 사용된 단어를 추적
            roundUsedWords.add(currentWord);

            while (true) {
                currentPlayer = (currentPlayer + 1) % N;
                if (!players[currentPlayer])
                    continue; // 탈락한 플레이어 건너뛰기

                char lastChar = currentWord.charAt(currentWord.length() - 1);
                TreeSet<String> availableWords = words.get(lastChar);

                if (availableWords == null || availableWords.isEmpty()) {
                    players[currentPlayer] = false; // 플레이어 탈락
                    // 라운드 종료, 단어 반전 및 추가 로직
                    for (String usedWord : roundUsedWords) {
                        String reversedWord = new StringBuilder(usedWord).reverse().toString();
                        if (!usedWords.contains(reversedWord)) {
                            words.get(reversedWord.charAt(0)).add(reversedWord);
                        }
                    }
                    return currentPlayer + 1; // 탈락한 플레이어의 1 기반 인덱스 반환
                }

                currentWord = availableWords.pollFirst();
                usedWords.add(currentWord);
                roundUsedWords.add(currentWord);
            }
        }

    }
}

/*
 * N명의 플레이어가 M개의 단어를 사용하여 끝말잇기 게임을 함
 * 3 <= N <= 50_000
 * N <= M <= 50_000
 * 
 * 끝말잇기 게임을 시작하기 전에 먼저 플레이어 수와 사용할 수 있는 단어 리스트가 주어짐
 * 각 단어는 길이가 2 이상 10이하의 영어 알파벳 소문자로 구성되어 있음
 * 
 * '\ 0'문자로 끝나는 문자열임
 * 
 * 단어는 중복되지 않음
 * 
 * 여러개의 라운드로 구성되어 있음
 * 
 * 각 라운드에서는 플레이어 ID를 기준으로 순서대로 턴을 진행함
 * 시작하기 전에 첫번째 턴을 진행할 플레이어 ID와 첫 단어를 정하기 위한 하나의 문자가 주어짐
 * 
 * 자신의 턴이 되면 단어 리스트에 있는 단어 중 하나를 조건에 맞게 선택함
 * 
 * 선택된 단어는 선택하지 않도록 단어 리스트에서 삭제함
 * 
 * 단어를 선택할 수 있는 단어의 조건
 * 1. 이전 턴에 선택된 단어의 마지막 문자로 시작하는 단어
 * 2. 게임을 진행하는 동안 한번도 선택된 적이 없는 단어
 * 3. 1과 2에 해당하는 단어가 여러개인 경우 사전 순으로 가장 빠른 단어
 * 
 * 단어가 없는 경우 해당 턴의 플레이어는 게임에서 탈락하고= 라운드가 종료됨
 * 
 * 탈락한 플레이어는 다음 라운드 부터 턴 진행에서 제외됨
 * 
 * 다음 라운드를 시작하기 전에 이런 라운드에서 선택된 단어를 뒤집은 후 단어 리스트에 추가함
 * 
 * 뒤집은 단어가 이미 선택된 단어라면 단어 리스트에 추가하지 않음
 * 
 * 
 */