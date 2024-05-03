package swexpert.codeBattle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1230 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb;

    public static void main(String[] args) throws NumberFormatException, IOException {
        for (int t = 1; t <= 10; t++) {
            sb = new StringBuilder();
            int n = Integer.parseInt(br.readLine());
            List<Integer> list = new LinkedList<>();
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < n; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }

            n = Integer.parseInt(br.readLine());

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 3; j++) {
                    String command = st.nextToken();
                    if (command == "I") {
                        List<Integer> tempList = new LinkedList<>();

                        int index = Integer.parseInt(st.nextToken());
                        n = Integer.parseInt(st.nextToken());
                        for (int k = 0; k < n; k++) {
                            tempList.add(Integer.parseInt(st.nextToken()));
                        }

                        list.addAll(index, tempList);
                    } else if (command == "D") {
                        int index = Integer.parseInt(st.nextToken());
                        int num = Integer.parseInt(st.nextToken());
                        List<Integer> tempList = list.subList(0, index);
                        tempList.addAll(list.subList(index + num, list.size()));
                    } else if (command == "A") {
                        List<Integer> tempList = new LinkedList<>();
                        n = Integer.parseInt(st.nextToken());
                        for (int k = 0; k < n; k++) {
                            tempList.add(Integer.parseInt(st.nextToken()));
                        }
                        tempList.addAll(list);
                        list = tempList;
                    }
                }
            }

            sb.append("#").append(t).append(" ").append(list);
        }
        System.out.println(sb);
    }
}
