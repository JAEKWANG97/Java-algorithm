package swexpert.week6.day1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node {
    String str;
    Node link;
    public Node(String str, Node link) {
        super();
        this.str = str;
        this.link = link;
    }
    public Node() {
    }
    public Node(String data) {
        this.str = data;
    }

}

class LinkedList {
    Node head = null;
    Node tail = null;

    int size = 0;

    public LinkedList() {
        head = new Node();
        tail = head;
    }

    void add(String str) {
        tail.link = new Node(str);
        tail = tail.link;
        size++;
    }

    boolean isEmpty() {
        return head == null;
    }

    Node get(int index) {
        Node temp = head;
        int idx = 0;

        while (temp.link != null) {
            if (index == 0) {
                return temp;
            }
            if (idx == index) {
                return temp;
            }
            idx++;
            temp = temp.link;
        }
        return null;
    }

    void insert(int index, LinkedList list) {

        if (index + 1 < size) {
            list.tail.link = get(index).link;
        } else {
            tail.link = list.tail;
        }
        get(index).link = list.head.link;
        size += list.size;
    }

}

public class Solution_1228_유재광 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        // TODO Auto-generated method stub
        for (int k = 0; k < 10; k++) {
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            LinkedList list = new LinkedList();
            LinkedList insertlist;

            for (int i = 0; i < n; i++) {
                list.add(st.nextToken());
            }

            int m = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < m; i++) {
                st.nextToken();
                insertlist = new LinkedList();
                int index = Integer.parseInt(st.nextToken());
                int size = Integer.parseInt(st.nextToken());
                for (int j = 0; j < size; j++) {
                    insertlist.add(st.nextToken());
                }
                list.insert(index, insertlist);
            }
            Node item = list.head.link;
            System.out.print("#" + k + " ");
            for (int i = 0; i < 10; i++) {
                System.out.print(item.str + " ");
                item = item.link;
            }
            System.out.println();
        }

    }

}