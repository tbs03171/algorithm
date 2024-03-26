package programmers;
import java.util.*;

public class PG_250136 {
        public static void main(String[] args) {

            Solution solution = new Solution();


            int result = solution.solution(new int[][]{{1, 0, 1, 0, 1, 1}, {1, 0, 1, 0, 0, 0}, {1, 0, 1, 0, 0, 1}, {1, 0, 0, 1, 0, 0}, {1, 0, 0, 1, 0, 1}, {1, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1}});
            System.out.println("result = " + result);
        }
}
class Solution {

    // 가로, 세로 길이
    static int n, m;
    // 시추관 위치별 석유량
    static int[] oil;

    public int solution(int[][] land) {

        // 초기화
        int answer = 0;
        n = land.length;
        m = land[0].length;
        oil = new int[m];
        boolean[][] visited = new boolean[n][m];

        // BFS
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 1 && visited[i][j] == false)
                    bfs(land, visited, i, j);
            }
        }

        // 제일 큰 값 찾아 반환
        answer = Arrays.stream(oil).max().getAsInt();
        return answer;
    }

    public void bfs(int[][] land, boolean[][] visited, int x, int y) {

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;

        // 상, 하, 좌, 우 이동
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // 석유 덩어리 개수
        int count = 1;
        // 석유 덩어리의 열 위치 저장
        Set<Integer> set = new HashSet<>();

        // 석유가 속한 열 Set에 저장 + 석유 덩어리 크기 계산
        while (!q.isEmpty()) {
            int[] now = q.poll();
            set.add(now[1]);

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                // 땅 범위를 넘는 경우 생략
                if (!checkRange(nx, ny)) continue;

                // 석유 있고, 방문한 적 없는 경우
                if (land[nx][ny] == 1 && visited[nx][ny] == false) {
                    q.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    count++;
                }
            }
        }

        // 해당 열에 석유 추가
        for (int index : set)
            oil[index] += count;
    }

    public boolean checkRange(int x, int y) {

        if (x < 0 || x >= n || y < 0 || y >= m) return false;
        return true;
    }
}

/* 나의 풀이
class Solution {

    public boolean[][] visited = new boolean[500][500];
    public int[][] map = new int[500][500];
    public int[] dx = {0, 0, -1, +1};
    public int[] dy = {-1, +1, 0, 0};
    public Map<Integer, Integer> sizeMap = new HashMap<>();

    public int solution(int[][] land) {

//         세로 n 가로 m 격자
//         가장 많은 석유를 뽑을 수 있는 시추관의 위치

//         land : 석유가 묻힌 땅, 석유 덩어리 2차원 정수 배열
//         return 시추관 하나로 뽑을 수 있는 가장 많은 석유량

//         **
//         땅의 세로길이 n 1 ~ 500
//         땅의 가로길이 m 1 ~ 500
//         land[i][j] 0이면 빈 땅, 1이면 석유 있는 땅

//         1. 쭈욱 읽으면서 BFS (덩어리 번호 표시, <석유 덩어리 번호, 크기> Map 에 저장) - 25만
//         2. 세로로 쭉쭉 읽으면서 더하기 (Set에 이미 더한 덩어리 표시하면서) - 25만

        // 초기화
        int n = land.length;
        int m = land[0].length;
        int answer = 0;

        // 쭈욱 읽으면서 BFS (덩어리 번호 표시, <석유 덩어리 번호, 크기> Map 에 저장)
        int number = 1; // 덩어리 번호
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 방문하지 않은 석유라면 BFS 시작
                if (visited[i][j] == false && land[i][j] == 1) {
                    int size = 1;
                    Queue<int[]> q = new LinkedList<>();
                    q.offer(new int[]{i, j});
                    visited[i][j] = true;
                    map[i][j] = number;

                    while(!q.isEmpty()) {
                        int[] node = q.poll();
                        // 상하좌우 확인
                        for (int k = 0; k < 4; k++) {
                            int nx = node[1] + dx[k];
                            int ny = node[0] + dy[k];
                            if (0 <= nx && nx < m && 0 <= ny && ny < n) {
                                if (visited[ny][nx] == false && land[ny][nx] == 1) {
                                    q.offer(new int[]{ny, nx});
                                    visited[ny][nx] = true;
                                    map[ny][nx] = number;
                                    size++;
                                }
                            }
                        }
                    }

                    sizeMap.put(number, size);
                    number++;
                }
            }
        }

        // 시추관 위치 옮기면서 수직으로 확인
        for (int i = 0; i < m; i++) {
            int sum = 0;
            Set<Integer> set = new HashSet<>();
            for (int j = 0; j < n; j++) {
                if (map[j][i] != 0 && (set.contains(map[j][i]) == false)) {
                    sum = sum + sizeMap.get(map[j][i]);
                    set.add(map[j][i]);
                }
            }
            answer = Math.max(answer, sum);
        }

        return answer;
    }
} */