import java.util.*;

class Main {

  public static int N;
  public static int[][] graph;
  public static boolean[][] visited;
  public static int[] dx = {-1, +1, 0, 0};
  public static int[] dy = {0, 0, -1, +1};
  public static int max = 1;
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    graph = new int[N][N];

    // 지역 높이 정보 입력
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        graph[i][j] = sc.nextInt();
      }
    }

    // 모든 높이에 대해 안전 영역 개수 구하기 (1 <= 높이 <= 100)
    for (int height = 1; height <= 100; height++) {
      int cnt = 0;
      visited = new boolean[N][N];

      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (visited[i][j] == false && graph[i][j] > height) { // 방문하지 않았고, 물에 잠기지 않는다면
            dfs(i, j, height);
            cnt++;
          }
        }
      }

      max = Math.max(max, cnt);
    }

    // 결과 출력
    System.out.println(max);
  }

  
  public static void dfs(int x, int y, int height) {
    visited[x][y] = true;
    // 상하좌우 탐색
    for (int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];
      if (0 <= nx && nx < N && 0 <= ny && ny < N) { // 경계값 내부에 있고
        if (visited[nx][ny] == false && graph[nx][ny] > height) { // 방문하지 않았고, 물에 잠기지 않는다면
          dfs(nx, ny, height);
        }
      }
    }
  }
  
}
