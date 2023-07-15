import java.util.*;

class Main {

  public static int n;
  public static char[][] graph = new char[100][100];
  public static boolean[][] visited = new boolean[100][100];
  public static int[] dx = {-1, +1, 0, 0};
  public static int[] dy = {0, 0, -1, +1};

  public static void dfs(int x, int y) {
    visited[x][y] = true;
    char color = graph[x][y];

    for (int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];
      if (0 <= nx && nx < n && 0 <= ny && ny < n) { // 경계 내부이고,
        if (visited[nx][ny] == false && graph[nx][ny] == color) { // 방문하지 않았으며, 같은 색깔이면
          dfs(nx, ny);
        }
      }
    }
  }
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    n = sc.nextInt();
    sc.nextLine();

    // 그래프 정보 입력
    for (int i = 0; i < n; i++) {
      String s = sc.nextLine();
      for (int j = 0; j < n; j++) {
        graph[i][j] = s.charAt(j);
      }
    }

    // 색약 아닌 사람 탐색
    int result = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (visited[i][j] == false) { // 방문 안했다면
          dfs(i, j);
          result++;
        }
      }
    }
    System.out.print(result + " ");

    // 색약인 사람의 그래프로 수정
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (graph[i][j] == 'G') {
          graph[i][j] = 'R';
        }
        visited[i][j] = false;
      }
    }

    // 색약인 사람 탐색
    result = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (visited[i][j] == false) { // 방문 안했다면
          dfs(i, j);
          result++;
        }
      }
    }
    System.out.println(result);
  }
}
