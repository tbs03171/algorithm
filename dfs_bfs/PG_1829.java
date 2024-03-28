package programmers;
import java.util.*;

public class PG_1829 {
        public static void main(String[] args) {

            Solution solution = new Solution();
            int[] result = solution.solution(6, 4,
                    new int[][]{{1, 1, 1, 0}, {1, 1, 1, 0}, {0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}});
            System.out.println(result[0] + ", " + result[1]);
        }
}

class Solution {

    public int[] dx = {0, 0, -1, 1};
    public int[] dy = {-1, +1, 0, 0};
    public boolean[][] visited;

    public int[] solution(int m, int n, int[][] picture) {

        int[] answer = {0, 0};

        // 초기화
        int[][] copy = picture.clone();
        visited = new boolean[m][n];

        // BFS
        for (int i = 0; i < copy.length; i++) {
            for (int j = 0; j < copy[i].length; j++) {
                // 색을 가지고, 방문하지 않은 경우
                if (copy[i][j] > 0 && !visited[i][j]) {
                    answer[0]++;
                    answer[1] = Math.max(answer[1], bfs(m, n, i, j, copy));
                }
            }
        }

        return answer;
    }

    public int bfs(int m, int n, int x, int y, int[][] picture) {
        int size = 1;
        Queue<int[]> q = new LinkedList<>();
        visited[x][y] = true;
        q.offer(new int[]{x, y});

        while(!q.isEmpty()) {
            int[] position = q.poll();

            // 동서남북
            for (int i = 0; i < 4; i++) {
                int nx = position[0] + dx[i];
                int ny = position[1] + dy[i];

                // 영역 내부, 동일한 색, 방문 안한 경우
                if (nx > -1 && nx < m && ny > -1 && ny < n && picture[nx][ny] == picture[x][y] && !visited[nx][ny]) {
                    size++;
                    q.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }

        return size;
    }
}


/* 나의 풀이
class Solution {

    public boolean[][] visited = new boolean[100][100];
    public int[] dx = {0, 0, -1, +1};
    public int[] dy = {-1, +1, 0, 0};

    public int[] solution(int m, int n, int[][] picture) {

//         그림 난이도를 영역의 수로 정의

//         m, n : 그림 크기
//         picture : 그림
//         return 몇 개의 영역, 가장 큰 영역 크기

//         **
//         m, n : 1 ~ 100
//         picture의 원소 : 0 ~ 2^31 - 1 (0이면 색칠 안함)

//         1. 모든 칸에 대해 0이 아니고, 방문 안했다면 BFS
//         2. BFS 끝날 때마다 영역 크기 업뎃, 영역 수 ++

        // 초기화
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        // 모든 칸에 대해 0이 아니고, 방문 안했다면 BFS
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] != 0 && visited[i][j] == false) {
                    Queue<int[]> q = new LinkedList<>();
                    q.offer(new int[]{i, j});
                    visited[i][j] = true;
                    int size = 1;

                    while (!q.isEmpty()) {
                        int[] node = q.poll();
                        // 상하좌우
                        for (int k = 0; k < 4; k++) {
                            int nx = node[0] + dx[k];
                            int ny = node[1] + dy[k];
                            if (0 <= nx && nx < m && 0 <= ny && ny < n) {
                                if (visited[nx][ny] == false && picture[nx][ny] == picture[node[0]][node[1]]) {
                                    q.offer(new int[]{nx, ny});
                                    visited[nx][ny] = true;
                                    size++;
                                }
                            }
                        }
                    }

                    // 업데이트
                    numberOfArea++;
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, size);
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
}*/