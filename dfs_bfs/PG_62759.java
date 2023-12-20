package programmers;
import java.util.*;

public class PG_62759 {
        public static void main(String[] args) {

            Solution solution = new Solution();
            int result = solution.solution(new int[][]{{0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0},{0,0,0,0,1,0,0,0},{0,0,0,1,0,0,0,1},{0,0,1,0,0,0,1,0},{0,1,0,0,0,1,0,0},{1,0,0,0,0,0,0,0}});
            System.out.println("result = " + result);
        }
}

class Solution {

    int[] dr = {1, -1, 0, 0};
    int[] dc = {0, 0, 1, -1};

    public int solution(int[][] board) {

        // 초기화
        int answer = Integer.MAX_VALUE;
        int n = board.length;
        int[][][] visited = new int[n][n][4];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {0, 0, -500, -1}); // 시작점에서 아래, 오른쪽 두 방향 모두 코너로 간주하기 위해 비용 -500으로 설정

        while (!q.isEmpty()) {

            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int cost = cur[2];
            int prevD = cur[3]; // 직전에 탐색한 방향

            // 도착지라면 최솟값 갱신
            if (r == n - 1 && c == n - 1) {
                answer = Integer.min(answer, cost);
                continue;
            }

            // 네 방향 탐색
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nc < 0 || nr >= n || nc >= n || board[nr][nc] == 1) // 경계 벗어남
                    continue;

                // 직전 탐색한 방향과 같은 방향으로 진행
                // 직선 도로를 놓았을 때 visited보다 적은 비용일 때만 진행
                if (d == prevD && visited[nr][nc][d] > cost + 100) {
                    visited[nr][nc][d] = cost + 100;
                    q.offer(new int[] {nr, nc, cost + 100, d});
                }

                // 코너
                // 직선 도로 + 코너를 놓았을 때 visited보다 적은 비용일 때만 진행
                else if (d != prevD && visited[nr][nc][d] > cost + 600) {
                    visited[nr][nc][d] = cost + 600;
                    q.offer(new int[] {nr, nc, cost + 600, d});
                }
            }
        }
        return answer;
    }
}

/* 나의 풀이
class Node {
    int x;
    int y;
    int dir;
    int cost;

    public Node (int x, int y, int dir, int cost) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.cost = cost;
    }
}

class Solution {

    int[] dx = {-1, +1, 0, 0};
    int[] dy = {0, 0, -1, +1};
    int[][] costVer; // 세로 상태의 cost
    int[][] costHor; // 가로 상태의 cost
    int MAX_VALUE = 987654321;

    public int solution(int[][] board) {

        // 초기화
        costVer = new int[board.length][board.length];
        costHor = new int[board.length][board.length];
        for (int i = 0; i < board.length; i++) {
            Arrays.fill(costVer[i], MAX_VALUE);
            Arrays.fill(costHor[i], MAX_VALUE);
        }

        // BFS 탐색
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0, 1, 0));
        q.offer(new Node(0, 0, -1, 0));
        costHor[0][0] = 0;
        costVer[0][0] = 0;

        while (!q.isEmpty()) {
            Node node = q.poll();

            // 세로 탐색
            for (int i = 0; i < 2; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (nx < 0 || nx >= board.length || ny < 0 || ny >= board.length) continue; // 경계 벗어남
                if (board[nx][ny] == 1) continue; // 벽

                // 현재 방향에 따라 cost 계산
                int cost;
                if (node.dir == 1) { // 현재 가로 방향일 경우
                    cost = node.cost + 600;
                }
                else { // 현재 세로 방향일 경우
                    cost = node.cost + 100;
                }

                // 현재 계산한 비용이 더 작다면 큐에 넣기
                if (cost < costVer[nx][ny]) {
                    q.offer(new Node(nx, ny, -1, cost));
                    costVer[nx][ny] = cost;
                }
            }

            // 가로 탐색
            for (int i = 2; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (nx < 0 || nx >= board.length || ny < 0 || ny >= board.length) continue; // 경계 벗어남
                if (board[nx][ny] == 1) continue; // 벽

                // 현재 방향에 따라 cost 계산
                int cost;
                if (node.dir == -1) { // 현재 세로 방향일 경우
                    cost = node.cost + 600;
                }
                else { // 현재 가로 방향일 경우
                    cost = node.cost + 100;
                }

                // 현재 계산한 비용이 더 작다면 큐에 넣기
                if (cost < costHor[nx][ny]) {
                    q.offer(new Node(nx, ny, 1, cost));
                    costHor[nx][ny] = cost;
                }
            }
        }

        return Math.min(costVer[board.length - 1][board.length - 1], costHor[board.length - 1][board.length - 1]);
    }
} */