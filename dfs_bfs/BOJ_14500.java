import java.util.*;

class Main {
  public static int n, m;
  public static int[][] board = new int[500][500];
  public static boolean[][] visited = new boolean[500][500];
  public static int[] dx = {-1, +1, 0, 0};
  public static int[] dy = {0, 0, -1, +1};
  public static int max = 0;

  public static void dfs(int x, int y, int sum, int count) {
    // 테트로미노 완성
    if (count == 4) {
      max = Math.max(sum, max);
      return;
    }

    // 상하좌우 탐색
    for (int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];
      if (0 <= nx && nx < n && 0 <= ny && ny < m) { // 경계선 내부이고
        if (visited[nx][ny] == false) { // 방문하지 않았다면
          // T자 모양 테트로미노 위해 2번째 칸에서 탐색 한번 더
          if (count == 2) {
            visited[nx][ny] = true;
            dfs(x, y, sum + board[nx][ny], count + 1);
            visited[nx][ny] = false;
          }
          visited[nx][ny] = true;
          dfs(nx, ny, sum + board[nx][ny], count + 1);
          visited[nx][ny] = false;
        }
      }
    }
  }
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    n = sc.nextInt();
    m = sc.nextInt();

    // 그래프 정보 입력
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        board[i][j] = sc.nextInt();
      }
    }

    // 재귀 탐색
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        visited[i][j] = true;
        dfs(i, j, board[i][j], 1);
        visited[i][j] = false;
      }
    }

    // 결과 출력
    System.out.println(max);
  }

}
