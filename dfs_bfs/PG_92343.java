import java.util.*;

/* 더 효율적인 풀이 (비트마스킹으로 메모이제이션) */
class Solution {
    int answer = 0;
    boolean[] checked;

    public int solution(int[] info, int[][] edges) {
        checked = new boolean[(int) Math.pow(2, info.length)];
        dfs(0, new boolean[info.length], 0, 0, info, edges, 1);
        return answer;
    }

    private void dfs(int idx, boolean[] visited, int sheep, int wolf, int[] info, int[][] edges, int state) {
        visited[idx] = true;
        checked[state] = true;

        if (info[idx] == 0) {
            sheep++;
            answer = Math.max(sheep, answer);
        } else {
            wolf++;
        }

        if (sheep <= wolf) return;

        // 1 << edge[1] -> 현재 간선의 목적지 노드(edge[1])의 비트를 1로 설정 (00000100000 과 같은 형태)
        // 000000001000을 현재 상태와 |(or) 연산
        // state | (1 << edge[1]) -> 현재 간선의 목적지 노드를 방문한 상태를 나타내는 비트마스크
        // checked[] -> 특정 상태가 이미 탐색되었는지
        for (int[] edge : edges) {
            if (visited[edge[0]] && !visited[edge[1]] && !checked[state | (1 << edge[1])]) {
                boolean[] newVisited = visited.clone();
                dfs(edge[1], newVisited, sheep, wolf, info, edges, state | (1 << edge[1]));
            }
        }
    }
}

/* 단순 재귀 + 백트래킹
class Solution {

    int answer = 0;

    public int solution(int[] info, int[][] edges) {
        dfs(0, new boolean[info.length], 0, 0, info, edges);
        return answer;
    }

    private void dfs(int idx, boolean[] visited, int sheep, int wolf, int[] info, int[][] edges) {
        visited[idx] = true;

        if (info[idx] == 0) {
            sheep++;
            answer = Math.max(sheep, answer);
        } else {
            wolf++;
        }

        if (sheep <= wolf) return;

        for (int[] edge : edges) {
            if (visited[edge[0]] && !visited[edge[1]]) {
                boolean[] newVisited = visited.clone();
                dfs(edge[1], newVisited, sheep, wolf, info, edges);
            }
        }
    }
} */

/* 나의 풀이
class Solution {

    int[][] graph;
    int[] in;
    boolean[] visited;
    int result = 0;

    public int solution(int[] info, int[][] edges) {
        // 그래프 초기화
        graph = new int[info.length][2];
        for (int[] arr : graph) {
            Arrays.fill(arr, -1);
        }
        in = new int[info.length];
        Arrays.fill(in, 1);
        in[0] = 0;
        visited = new boolean[info.length];

        // 그래프 구성
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            if (graph[a][0] == -1) graph[a][0] = b;
            else graph[a][1] = b;
        }

        dfs(0, 1, 0, info);

        return result;
    }

    public void dfs(int index, int sheepCnt, int wolfCnt, int[] info) {
        visited[index] = true;
        result = Math.max(result, sheepCnt);

        // 현재 노드에서 나가는 간선 제거 (in 갱신)
        for (int child : graph[index]) {
            if (child != -1) {
                in[child] = 0;
            }
        }

        // 현재 방문 가능한 곳 다 방문
        for (int i = 0; i < in.length; i++) {
            if (in[i] == 0 && visited[i] == false) {
               if (info[i] == 0) { // 양인 경우
                   dfs(i, sheepCnt + 1, wolfCnt, info);
               } else { // 늑대인 경우
                   if (wolfCnt + 1 < sheepCnt) {
                       dfs(i, sheepCnt, wolfCnt + 1, info);
                   }
               }
            }
        }

        // 원상복구
        for (int child : graph[index]) {
            if (child != -1) {
                in[child] = 1;
            }
        }

        visited[index] = false;
    }
} */