package Dijsktra;

import java.io.*;
import java.util.*;

public class Num9370 {
    public static class Node implements Comparable<Node>{
        int start;
        int end;
        int value;
        public Node(int s, int e, int v) {
            this.start = s;
            this.end = e;
            this.value = v;
        }
        @Override
        public int compareTo(Node oj) {
            return this.value - oj.value;
        }
    }
    static int n,m,t,s,g,h,a,b,d;
    static List<Integer> endPoints;
    static List<List<Node>> map;
    static int[] vist;
    static boolean[] isPassProof;
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
            for(int i=0;i<m;i++) {
                st = new StringTokenizer(bf.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                d = Integer.parseInt(st.nextToken());
                map.get(a).add(new Node(a,b,d));
                map.get(b).add(new Node(b,a,d));
            }
            endPoints = new ArrayList<>();
            for(int i=0;i<t;i++) {
                endPoints.add(Integer.parseInt(bf.readLine()));
            }
            //find minimum
            vist = new int[n+1];
            isPassProof = new boolean[n+1];
            Arrays.fill(vist,Integer.MAX_VALUE);
            Queue<Node> que = new LinkedList<>();
            //initialize
            vist[s] =0;
            for(Node now : map.get(s)) {
                que.add(now);
            }
            //BFS
            while(!que.isEmpty()) {
                Node now = que.poll();
                if(vist[now.end] >= vist[now.start]+now.value) {
                    if(vist[now.end] == vist[now.start]+now.value) {
                        if(isPassProof[now.end]) {
                            for(Node nxt : map.get(now.end)) {
                                que.add(nxt);
                            }
                            continue;
                        }
                    }
                    vist[now.end] = vist[now.start]+now.value;
                    for(Node nxt : map.get(now.end)) {
                        que.add(nxt);
                    }
                    if(isPassProof[now.start]) {
                        isPassProof[now.end] = true;
                    }else {
                        isPassProof[now.end] = false;
                    }
                    if(now.start == g && now.end == h) {
                        isPassProof[now.end] = true;
                    }
                    if(now.start == h && now.end == g) {
                        isPassProof[now.end] = true;
                    }

                }
            }
            //findAnsewr
            Collections.sort(endPoints);
            for(int candidate : endPoints) {
                if(isPassProof[candidate]) {
                    sb.append(candidate+" ");
                }
            }
            //System.out.println(Arrays.toString(vist));
            sb.append("\n");
        }
        System.out.println(sb);
    }

}
