import java.util.*;
import java.io.*;

public class Main {
  static int n, m;
  static int[][] arr;
  static boolean[][] visit;
  static int[] dx;
  static int[] dy;
  static Queue<Pair> qu;

  public static void main(String args[]) throws IOException {

    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(bf.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());

    arr = new int[n][m];
    visit = new boolean[n][m];
    qu = new LinkedList<>();

    dx = new int[]{1, 0, -1, 0};
    dy = new int[]{0, 1, 0, -1};

    // 배열값 세팅
    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(bf.readLine());
      for (int j = 0; j < m; j++) {
        arr[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    // 시작
    int count = 0;
    int area = 0;
    int max = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        // 0이거나 방문한 적이 있으면 생략
        if (arr[i][j] == 0 || visit[i][j]) continue;
        count++; // 1이고, 방문을 하지 않았으므로 시작점이 되니까 +1
        qu.offer(new Pair(i, j)); // 큐에 좌표를 넣어준다.
        visit[i][j] = true; // 방문처리
        area = 0; // 0이면 그림이 끊겼으므로
        while(!qu.isEmpty()) {
          Pair p = qu.poll();
          area++; // 넓이 +1
          for (int k = 0; k < 4; k++) {
            int n_x = p.x + dx[k];
            int n_y = p.y + dy[k];
            if (n_x < 0 || n_x >= n || n_y < 0 || n_y >= m) continue;
            if (arr[n_x][n_y] == 1 && !visit[n_x][n_y]) {
              qu.offer(new Pair(n_x, n_y));
              visit[n_x][n_y] = true;
            }
          }
        }
        if (area > max) max = area;
      }
    }

    System.out.println(count);
    System.out.println(max);
  }

  public static class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}

/* 나의 풀이(DFS)
import java.util.*;

class Main {

  public static int n, m;
  public static int[][] graph;
  public static boolean[][] visited;
  public static int resultMax = 0;
  public static int resultCnt = 0;
  public static int count;
  public static int[] dx = {-1, +1, 0, 0};
  public static int[] dy = {0, 0, -1, +1};
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    m = sc.nextInt();
    graph = new int[n][m];
    visited = new boolean[n][m];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        graph[i][j] = sc.nextInt();
      }
    }

    // dfs 탐색
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (graph[i][j] == 1 && visited[i][j] == false) {
          resultCnt++;
          count = 0;
          dfs(i, j);
          resultMax = Math.max(resultMax, count);
        }
      }
    }

    System.out.println(resultCnt);
    System.out.println(resultMax);
  }


  public static void dfs(int x, int y) {
    visited[x][y] = true;
    count++;

    for (int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];
      if (0 > nx || nx >= n || ny < 0 || ny >= m) continue;
      if (visited[nx][ny] == false && graph[nx][ny] == 1) dfs(nx, ny);
    }
  }
  
}
*/