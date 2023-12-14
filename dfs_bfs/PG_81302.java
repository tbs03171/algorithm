package programmers;
import java.util.*;

public class PG_81302 {
        public static void main(String[] args) {

            int[] result = Solution.solution(new String[][]{{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}
                    , {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}
                    , {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}
                    , {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}
                    , {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}});
            for (int i : result) {
                System.out.println("i = " + i);
            }
        }
}
class Solution {

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static Queue<int[]> q = new LinkedList<>();
    static boolean flag; // 거리두기 안한 사람 발견 -> flag = true
    static boolean[][] visited = new boolean[5][5];

    public static int[] solution(String[][] places) {
        int[] answer = new int[5];

        // 모든 대기실에 대해
        for (int i = 0; i < 5; i++) {
            flag = false;

            // 초기화
            for (int x = 0; x < 5; x++) {
                for (int y = 0; y < 5; y++) {
                    visited[x][y] = false;
                }
            }

            // 모든 자리에 대해 'P'이고 방문하지 않았다면 BFS 탐색
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (places[i][j].charAt(k) == 'P' && !visited[j][k]) bfs(j, k, places[i]);
                }
            }

            // 결과 저장
            if (!flag) answer[i] = 1;
            else answer[i] = 0;
        }
        return answer;
    }

    public static void bfs(int x, int y, String[] p) {
        q.add(new int[] {x, y});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] arr = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = arr[0] + dx[i];
                int ny = arr[1] + dy[i];

                // 경계선 내부이고 아직 방문하지 않았다면
                if (nx >= 0 && nx <= 4 && ny >= 0 && ny <= 4 && !visited[nx][ny]) {
                    int distance = Math.abs(nx - x) + Math.abs(ny - y);

                    // P 이고 거리가 2 이하 -> 바로 리턴
                    if (p[nx].charAt(ny) == 'P' && distance <= 2) {
                        flag = true;
                        return;
                    }
                    // O 이고 거리가 1 -> 큐에 담기
                    else if (p[nx].charAt(ny) == 'O' && distance == 1) {
                        q.add(new int[] {nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
        }
    }
}

/* 나의 풀이
class Solution {

    char[][] place;
    List<Integer[]> personList;
    int[] dx = {-1, +1, 0, 0};
    int[] dy = {0, 0, -1, +1};

    public int[] solution(String[][] places) {

        int[] answer = new int[5];

        // 모든 대기실에 대해
        for (int p = 0; p < 5; p++) {
            // 초기화
            String[] placeStr = places[p];
            place = new char[5][5];
            personList = new ArrayList<>();

            // 대기실 정보 배열에 담기
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    char info = placeStr[i].charAt(j);
                    place[i][j] = info;
                    if (info == 'P') personList.add(new Integer[] {i, j});
                }
            }

            // 모든 사람들이 거리두기 지키고 있는지 체크
            answer[p] = 1;
            for (Integer[] person : personList) {
                if (!check(person[0], person[1])) {
                    answer[p] = 0;
                    break;
                }
            }
        }
        return answer;
    }

    public boolean check(int x, int y) {
        Queue<Integer[]> q = new LinkedList<>();

        // 맨해튼 거리 1로 도달 가능한 곳 확인
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;
            if (place[nx][ny] == 'P') return false;
            if (place[nx][ny] == 'O') q.offer(new Integer[]{nx, ny});
        }

        // 맨해튼 거리 2로 도달 가능한 곳 확인
        while (!q.isEmpty()) {
            Integer[] node = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = node[0] + dx[i];
                int ny = node[1] + dy[i];
                if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;
                if (nx == x && ny == y) continue;
                if (place[nx][ny] == 'P') return false;
            }
        }
        return true;
    }
} */