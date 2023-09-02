import java.io.*;
import java.util.*;

public class Main {

  static int N, M, K, ans, temp;
  static int[] dx = {-1, 1, 0, 0};
  static int[] dy = {0, 0, -1, 1};
  static boolean[][] map;
  static boolean[][] visited;

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    map = new boolean[N][M];
    visited = new boolean[N][M];

    for (int i = 0; i < K; i++) {
      st = new StringTokenizer(br.readLine());
      int r = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      map[r - 1][c - 1] = true;
    }

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (!visited[i][j] && map[i][j]) {
          temp = 0;
          dfs(i, j);
          ans = Math.max(ans, temp);
        }
      }
    }

    System.out.println(ans);
  }

  private static void dfs(int x, int y) {
    temp++;
    visited[x][y] = true;
    for (int k = 0; k < 4; k++) {
      int xx = x + dx[k];
      int yy = y + dy[k];

      if (xx < 0 || yy < 0 || xx >= N || yy >= M) continue;

      if (!visited[xx][yy] && map[xx][yy]) {
        dfs(xx, yy);
      }
    }
  }
}

/* 나의 풀이 (BFS)
import java.util.*;

class Position {

  int x, y;

  Position(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
}

class Main {

  public static int N, M, K;
  public static boolean[][] graph;
  public static boolean[][] visited;
  public static int[] dx = {-1, +1, 0, 0};
  public static int[] dy = {0, 0, -1, +1};
  public static int max = 0;
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    M = sc.nextInt();
    K = sc.nextInt();
    graph = new boolean[N][M];
    visited = new boolean[N][M];

    // 그래프 입력
    for (int i = 0; i < K; i++) {
      int r = sc.nextInt() - 1;
      int c = sc.nextInt() - 1;
      graph[r][c] = true;
    }

    // 그래프 탐색
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (!visited[i][j] && graph[i][j]) bfs(i, j);
      }
    }

    // 결과 출력
    System.out.println(max);
  }

  public static void bfs(int x, int y) {
    Queue<Position> q = new LinkedList<Position>();
    q.offer(new Position(x, y));
    visited[x][y] = true;
    int cnt = 0;

    while(!q.isEmpty()) {
      Position p = q.poll();
      for (int i = 0; i < 4; i++) {
        int nx = p.x + dx[i];
        int ny = p.y + dy[i];

        if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

        if (!visited[nx][ny] && graph[nx][ny]) {
          q.offer(new Position(nx, ny));
          visited[nx][ny] = true;
        }
      }
      cnt++;
    }

    max = Math.max(max, cnt);
  }
}
*/