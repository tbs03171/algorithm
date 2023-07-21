import java.util.*;

class Main {

  public static int N, M;
  public static int startX, startY;
  public static int result = 0;
  public static char[][] graph = new char[600][600];
  public static boolean[][] visited = new boolean[600][600];
  public static int[] dx = {-1, +1, 0, 0};
  public static int[] dy = {0, 0, -1, +1};

  public static void dfs(int x, int y) {
    visited[x][y] = true;
    if (graph[x][y] == 'P') result++; // 사람이라면 수 체크
    
    for (int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];
      if (0 <= nx && nx < N && 0 <= ny && ny < M) { // 경계선 내부이며
        if (graph[nx][ny] != 'X' && visited[nx][ny] == false) { // 벽이 아니고, 방문한 적 없다면
          dfs(nx, ny);
        }
      }
    }
  }
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    N = sc.nextInt();
    M = sc.nextInt();
    sc.nextLine();
    
    for (int i = 0; i < N; i++) {
      String s = sc.nextLine();
      for (int j = 0; j < M; j++) {
        char c = s.charAt(j);
        if (c == 'I') { // 도연이 위치 저장
          startX = i;
          startY = j;
        }
        graph[i][j] = c;
      }
    }

    dfs(startX, startY);

    // 결과 출력
    System.out.println(result == 0 ? "TT" : result);
  }
}
