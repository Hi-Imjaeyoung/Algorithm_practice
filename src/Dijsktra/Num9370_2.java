package Dijsktra;

import java.io.*;
import java.util.*;
public class Num9370_2 {
    static int n,m,t,s,g,h,a,b,d;
    static List<Integer> endPoints;
    static List<List<Node>> map;
    static int[] vist, vist2, vist3;
    public static class Node implements Comparable<Num9370.Node>{
        int start;
        int end;
        int value;
        public Node(int s, int e, int v) {
            this.start = s;
            this.end = e;
            this.value = v;
        }
        @Override
        public int compareTo(Num9370.Node oj) {
            return this.value - oj.value;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        while(T>0) {
            T--;
            //input
            StringTokenizer st = new StringTokenizer(bf.readLine());
            n = Integer.parseInt(st.nextToken());
            map = new LinkedList<>();
            for(int i=0;i<=n;i++) {
                map.add(new LinkedList<>());
            }
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(bf.readLine());
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            //memo g>h value
            int targetRoadValue = 0;
            for(int i=0;i<m;i++) {
                st = new StringTokenizer(bf.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                d = Integer.parseInt(st.nextToken());
                if(a==g && b==h) {
                    targetRoadValue = d;
                }
                if(a==h && b==g) {
                    targetRoadValue = d;
                }
                map.get(a).add(new Node(a,b,d));
                map.get(b).add(new Node(b,a,d));
            }
            endPoints = new ArrayList<>();
            for(int i=0;i<t;i++) {
                endPoints.add(Integer.parseInt(bf.readLine()));
            }
            //find minimum
            vist = new int[n+1];
            vist2 = new int[n+1];
            vist3 = new int[n+1];
            Arrays.fill(vist,1000000000);
            Arrays.fill(vist2,1000000000);
            Arrays.fill(vist3,1000000000);
            PriorityQueue<Node> que = new PriorityQueue<>();
            //initialize
            vist[s] =0;
            for(Node now : map.get(s)) {
                que.add(now);
            }
            //BFS(startPoint)
            while(!que.isEmpty()) {
                Node now = que.poll();
                if(vist[now.end] > vist[now.start]+now.value) {
                    vist[now.end] = vist[now.start]+now.value;
                    for(Node nxt : map.get(now.end)) {
                        que.add(nxt);
                    }
                }
            }

            //BFS2
            for(Node now : map.get(g)) {
                que.add(now);
            }
            vist2[g] = 0;
            while(!que.isEmpty()) {
                Node now = que.poll();
                if(vist2[now.end] > vist2[now.start]+now.value) {
                    vist2[now.end] = vist2[now.start]+now.value;
                    for(Node nxt : map.get(now.end)) {
                        que.add(nxt);
                    }
                }
            }
            //BFS3
            for(Node now : map.get(h)) {
                que.add(now);
            }
            vist3[h] = 0;
            while(!que.isEmpty()) {
                Node now = que.poll();
                if(vist3[now.end] > vist3[now.start]+now.value) {
                    vist3[now.end] = vist3[now.start]+now.value;
                    for(Node nxt : map.get(now.end)) {
                        que.add(nxt);
                    }
                }
            }
            //findAnsewr
            Collections.sort(endPoints);
            for(int candidate : endPoints) {
                if(vist[candidate] == vist2[candidate]+vist[h]+targetRoadValue) {
                    sb.append(candidate +" ");
                }else if(vist[candidate] == vist3[candidate]+vist[g]+targetRoadValue){
                    sb.append(candidate +" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

}
