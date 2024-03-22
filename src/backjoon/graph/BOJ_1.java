package backjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

import java.util.StringTokenizer;
class BOJ_13549 {
    static class Person implements Comparable<Person>{
        int time;
        int location;
        public Person(int location , int time){
            this.time = time;
            this.location = location;
        }
        @Override
        public int compareTo(Person o) {
            // TODO Auto-generated method stub
            return this.time - o.time;
        }
        
    }

    static int minTime = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        bfs(N, K);
        System.out.println(minTime);
    }

    private static void bfs(int start, int end){
        PriorityQueue<Person> queue = new PriorityQueue<>();
        queue.offer(new Person(start, 0));
        boolean[] visited = new boolean[100001];
        visited[start] = true;

        while(!queue.isEmpty()){
            Person cur = queue.poll();
            visited[cur.location] = true;
            
            if(cur.location == end){
                minTime = Math.min(cur.time, minTime);
                continue;
            }


            if(cur.location * 2 < 100001 && !visited[cur.location * 2]){
                queue.offer(new Person(cur.location * 2, cur.time));
            }
            if(cur.location + 1 < 100001 && !visited[cur.location + 1]){
                queue.offer(new Person(cur.location + 1, cur.time + 1));
            }
            if(cur.location - 1 >= 0 && !visited[cur.location - 1]){
                queue.offer(new Person(cur.location - 1, cur.time + 1));
            }
        }
    }
}
