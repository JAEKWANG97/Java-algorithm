package backjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


public class P1991 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N;
    static Map<String, String[]> treeMap = new HashMap<>();

    public static void main(String[] args) throws IOException {

        // 첫줄에 전위 순회
        // 둘째줄에 중위 순회
        // 셋째 줄에 후위 순회
        N = Integer.parseInt(br.readLine());

        /*
        ★전위 순회는 뿌리->왼쪽 자식->오른쪽 자식 순
        ★중위 순회는 왼쪽자식-> 뿌리-> 오른쪽 자식
        ★후위 순회는 왼쪽자식->오른쪽 자식-> 뿌리
         */

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String parent = st.nextToken();
            String left = st.nextToken();
            String right = st.nextToken();

            treeMap.put(parent, new String[]{left, right});
        }
        String root = "A";
        preOrder(root);
        System.out.println(sb.toString());
        sb = new StringBuilder();
        inOrder(root);
        System.out.println(sb.toString());
        sb = new StringBuilder();
        postOrder(root);
        System.out.println(sb.toString());
        sb = new StringBuilder();

    }

    static void preOrder(String parent) {
        String[] node = treeMap.getOrDefault(parent, null);
        String left = node[0];
        String right = node[1];
        if (!parent.equals(".")) {
            sb.append(parent);
        }
        if (!left.equals(".")) {
            preOrder(left);
        }
        if (!right.equals(".")) {
            preOrder(right);
        }
    }

    static void inOrder(String parent) {
        String[] node = treeMap.getOrDefault(parent, null);
        String left = node[0];
        String right = node[1];
        if (!left.equals(".")) {
            inOrder(left);
        }
        if (!parent.equals(".")) {
            sb.append(parent);
        }
        if (!right.equals(".")) {
            inOrder(right);
        }
    }

    static void postOrder(String parent) {
        String[] node = treeMap.getOrDefault(parent, null);
        String left = node[0];
        String right = node[1];
        if (!left.equals(".")) {
            postOrder(left);
        }
        if (!right.equals(".")) {
            postOrder(right);
        }
        if (!parent.equals(".")) {
            sb.append(parent);
        }
    }

}
