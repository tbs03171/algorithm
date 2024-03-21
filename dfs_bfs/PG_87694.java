package programmers;
import java.util.*;

public class PG_87694 {
        public static void main(String[] args) {

            Solution solution = new Solution();
            int result = solution.solution(new int[][]{{1, 1, 7, 4}, {3, 2, 5, 5}, {4, 3, 6, 9}, {2, 6, 8, 8}}, 1, 3, 7, 8);
            System.out.println(result);
        }
}

/* 제일 직관적인 풀이 */
class Solution {
    
    int[][] map;
    int ans;
    boolean[][] visited;
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};
    
    public int solution (int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        
        int answer = 0;
        map = new int[102][102];
        
        // 도형 채우기
        for (int[] r : rectangle) {
            for (int x = r[0] * 2; x <= r[2] * 2; x++) {
                for (int y = r[1] * 2; y <= r[3] * 2; y++) {
                    map[x][y] = 1;
                }
            }
        }
        
        // 내부 비우기
        for (int[] r : rectangle) {
            for (int x = r[0] * 2 + 1; x <= r[2] * 2 - 1; x++) {
                for (int y = r[1] * 2 + 1; y <= r[3] * 2 - 1; y++) {
                    map[x][y] = 0;
                }
            }
        }
        
        // BFS
        bfs(2 * characterX, 2 * characterY, 2 * itemX, 2 * itemY);
        
        return ans;
    }
    
    public void bfs(int cX, int cY, int iX, int iY) {
        visited = new boolean[102][102];
        Queue<Point> q = new LinkedList<>();
        visited[cX][cY] = true;
        q.add(new Point(cX, cY, 0));
        
        while (!q.isEmpty()) {
            Point p = q.poll();
            
            // 아이템 위치에 도착
            if (p.x == iX && p.y == iY) {
                ans = p.distance / 2;
                return;
            }
            
            // 동서남북 탐색
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + p.x;
                int ny = dy[i] + p.y;
                
                // 테두리이면서 방문하지 않은 곳
                if (!visited[nx][ny] && map[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    q.add(new Point(nx, ny, p.distance + 1));
                }
            }
        }
    }
    
    public class Point {
        int x, y, distance;
        
        public Point(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
}

/* 박스 Boolean 사용한 풀이
class Solution {
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        
        // 초기화 (모든 좌표를 2배로)
        int answer = Integer.MAX_VALUE;
        Boolean[][] map = new Boolean[102][102];
        int[] dx = new int[] {1, 0, -1, 0};
        int[] dy = new int[] {0, 1, 0, -1};
        characterX *= 2;
        characterY *= 2;
        itemX *= 2;
        itemY *= 2;
        
        // 도형 테두리 T, 내부 F, 그 외 N
        for (int i = 0; i < rectangle.length; i++) {
            int[] tmp = rectangle[i];
            for (int j = 0; j < tmp.length; j++) tmp[j] *= 2;
            
            for (int x = tmp[0]; x <= tmp[2]; x++) {
                for (int y = tmp[1]; y <= tmp[3]; y++) {
                    if (map[x][y] != Boolean.FALSE) {
                        map[x][y] = (x == tmp[0] || x == tmp[2] || y == tmp[1] || y == tmp[3]);
                    }
                }
            }
        }
        
        // BFS
        Queue<int[]> queue = new LinkedList<>();
        map[characterX][characterY] = Boolean.FALSE;
        queue.add(new int[] {characterX, characterY, 0});
        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();
            
            // 아이템 위치 도착
            if (tmp[0] == itemX && tmp[1] == itemY)
                answer = Math.min(answer, tmp[2]/2);
            
            // 동서남북 탐색
            for (int i = 0; i < 4; i++) {
                int nx = tmp[0] + dx[i];
                int ny = tmp[1] + dy[i];
                // 방문 가능한 곳 -> 방문 표시하고 큐에 넣기
                if (isRange(map, nx, ny)) {
                    map[nx][ny] = Boolean.FALSE;
                    queue.add(new int[] {nx, ny, tmp[2] + 1});
                }
            }
        }
        
        return answer;
    }
    
    public boolean isRange(Boolean[][] map, int x, int y) {
        return x >= 2 && x <= 100 && y >= 2 && y <= 100 && map[x][y] == Boolean.TRUE;
    }
} */

/* 나의 풀이
class Solution {
    
    public int[][] graphA = new int[52][52]; // ㅡ, 방향 표시
    public int[][] graphB = new int[52][52]; // ㅣ, 방향 표시
    public boolean[][] visited = new boolean[52][52]; // 방문 여부
    public int min = Integer.MAX_VALUE;
    
    public class Node {
        int x;
        int y;
        int distance;
        int dir;
        
        public Node(int x, int y, int distance, int dir) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.dir = dir;
        }
    }
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        
        // 테두리 그리기
        for (int[] rec : rectangle) {
            for (int i = rec[0]; i < rec[2]; i++) {
                graphA[rec[1]][i] = 1;
                graphA[rec[3]][i] = 1;
            }
            graphA[rec[1]][rec[2]] = -1;
            graphA[rec[3]][rec[2]] = -1;
            
            for (int i = rec[1]; i < rec[3]; i++) {
                graphB[i][rec[0]] = 1;
                graphB[i][rec[2]] = 1;
            }
            graphB[rec[3]][rec[0]] = -1;
            graphB[rec[3]][rec[2]] = -1;
        }
        
        // 내부 비우기
        for (int[] rec : rectangle) {
            for (int i = rec[0] + 1; i < rec[2]; i++) {
                for (int j = rec[1] + 1; j < rec[3]; j++) {
                    graphA[j][i] = 0;
                    graphB[j][i] = 0;
                }
            }
        }
        
        visited[characterY][characterX] = true;
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(characterX, characterY, 0, -1)); // 가로 방향
        q.offer(new Node(characterX, characterY, 0, 1)); // 세로 방향
        
        while (!q.isEmpty()) {
            Node node = q.poll();
            
            if (node.x == itemX && node.y == itemY) {
                min = Math.min(min, node.distance);
                continue;
            }
            
            // 코너라면 방향 바꾸기
            int dir = node.dir;
            if (isCorner(node.x, node.y))
                dir = -dir;
                
            // 가로 이동이라면
            if (dir == -1) {
                int nx = node.x + 1;
                int ny = node.y;
                if (0 <= nx && nx <= 51 && visited[ny][nx] == false && (graphA[ny][node.x] == 1 || graphA[ny][nx] == -1)) {
                    visited[ny][nx] = true;
                    q.offer(new Node(nx, ny, node.distance + 1, dir));
                }
                    
                nx = node.x - 1;
                ny = node.y;
                if (0 <= nx && nx <= 51 && visited[ny][nx] == false && (graphA[ny][node.x] == -1 || graphA[ny][nx] == 1)) {
                    visited[ny][nx] = true;
                    q.offer(new Node(nx, ny, node.distance + 1, dir));
                }
            }
            // 세로 이동이라면
            else {
                int nx = node.x;
                int ny = node.y + 1;
                if (0 <= ny && ny <= 51 && visited[ny][nx] == false && (graphB[node.y][nx] == 1 || graphB[ny][nx] == -1)) {
                    visited[ny][nx] = true;
                    q.offer(new Node(nx, ny, node.distance + 1, dir));
                }
                    
                nx = node.x;
                ny = node.y - 1;
                if (0 <= ny && ny <= 51 && visited[ny][nx] == false && (graphB[node.y][nx] == -1 || graphB[ny][nx] == 1)) {
                    visited[ny][nx] = true;
                    q.offer(new Node(nx, ny, node.distance + 1, dir));
                }
            }
        }
        
        int answer = min;
        return answer;
    }
    
    public boolean isCorner(int x, int y) {
        // 오른 하 모서리
        if (graphA[y][x] == -1 && graphB[y][x] == 1) return true;
        // 왼 하 모서리
        if (graphA[y][x] == 1 && graphB[y][x] == 1) return true;
        // 오른 상 모서리
        if (graphA[y][x] == -1 && graphB[y][x] == -1) return true;
        // 왼 상 모서리
        if (graphA[y][x] == 1 && graphB[y][x] == -1) return true;
        return false;
    }
} */
