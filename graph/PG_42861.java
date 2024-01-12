package programmers;
import java.util.*;

public class PG_42861 {
        public static void main(String[] args) {

            Solution solution = new Solution();
            int result = solution.solution(4, new int[][]{{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}});
            System.out.println("result = " + result);
        }
}


class Solution {

    public class Point implements Comparable<Point> {
        int node, cost;

        public Point(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Point o) {
            return this.cost - o.cost;
        }
    }

    public List<List<Point>> map = new ArrayList<>();

    public int solution(int n, int[][] costs) {

        int answer = 0;

        // 그래프 초기화
        for (int i = 0; i < n; i++) {
            map.add(new ArrayList<>());
        }

        // 그래프 구성
        for (int i = 0; i < costs.length; i++) {
            int from = costs[i][0];
            int to = costs[i][1];
            int val = costs[i][2];
            map.get(from).add(new Point(to, val));
            map.get(to).add(new Point(from, val));
        }

        // Prim
        boolean[] visited = new boolean[n];
        PriorityQueue<Point> queue = new PriorityQueue<>();
        queue.add(new Point(0, 0));
        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            if (visited[cur.node]) continue;
            visited[cur.node] = true;
            answer += cur.cost;

            // 새로운 정점들 추가
            for (int i = 0; i < map.get(cur.node).size(); i++) {
                int next = map.get(cur.node).get(i).node;
                int cost = map.get(cur.node).get(i).cost;
                if (visited[next]) continue; // 이미 방문했다면 제외
                queue.add(new Point(next, cost));
            }
        }

        return answer;
    }
}

/* 크루스칼 알고리즘
class Solution {

    private int[] parent;

    public int find(int a) {
        if (parent[a] == a) return a;
        else return parent[a] = find(parent[a]);
    }

    public void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b)
            parent[b] = a;
    }

    public int solution(int n, int[][] costs) {
        int answer = 0;

        // parent 초기화
        parent = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i;

        // 비용 오름차순 정렬
        Arrays.sort(costs, (o1, o2) -> o1[2] - o2[2]);

        // Kruskal
        for (int i = 0; i < costs.length; i++) {
            if (find(costs[i][0]) != find(costs[i][1])) {
                union(costs[i][0], costs[i][1]);
                answer += costs[i][2];
            }
        }
        return answer;
    }
} */

/* 나의 풀이 (Kruskal)
class Solution {

    public int find(int x) {
        if (parent[x] != x) {
            return find(parent[x]);
        }
        else
            return x;
    }

    public void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x < y)
            parent[y] = x;
        else
            parent[x] = y;
    }

    public int[] parent;

    public int solution(int n, int[][] costs) {

        // costs 정렬
        Arrays.sort(costs, (a, b) -> a[2] - b[2]);

        // 부모 저장할 배열 초기화
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // 다리 연결 n - 1번 반복
        int count = 0;
        int cost = 0;
        for (int i = 0; i < costs.length; i++) {
            // 둘 다 같은 집합에 연결되어 있음 -> 싸이클 생기므로 지나가기
            if (find(costs[i][0]) == find(costs[i][1]))
                continue;

            // 다리 연결
            union(costs[i][0], costs[i][1]);

            // 비용 추가, 카운팅
            cost += costs[i][2];
            count++;

            if (count == n - 1) break;
        }

        return cost;
    }
} */