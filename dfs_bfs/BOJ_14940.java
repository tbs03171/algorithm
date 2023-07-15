import java.util.*;
import java.io.*;

class Position {
  private int x, y;

  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }
}

class Main {
  
  public static int n, m;
  public static int[][] graph = new int[999][999];
  public static boolean[][] visited = new boolean[999][999];
  public static int startX, startY;
  public static int[] dx = {-1, +1, 0, 0};
  public static int[] dy = {0, 0, -1, +1};

  public static void bfs(int x, int y) {
    Queue<Position> q = new LinkedList<Position>();
    q.offer(new Position(x, y));
    visited[x][y] = true;
    graph[x][y] = 0;

    while(!q.isEmpty()) {
      Position position = q.poll();

      for (int i = 0; i < 4; i++) {
        int nx = position.getX() + dx[i];
        int ny = position.getY() + dy[i];
        if (0 <= nx && nx < n && 0 <= ny && ny < m) { // 경계 내부이고,
          if (graph[nx][ny] == 1 && visited[nx][ny] == false) { // 갈 수 있는 땅이며, 방문한 적 없는 경우
            q.offer(new Position(nx, ny));
            visited[nx][ny] = true;
            graph[nx][ny] = graph[position.getX()][position.getY()] + 1;
          }
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
        int x = sc.nextInt();
        if (x == 2)  {
          startX = i;
          startY = j;
        }
        graph[i][j] = x;
      }
    }

    // bfs 탐색
    bfs(startX, startY);

    // 결과 출력
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        // 원래 갈 수 있는 땅인 부분 중에서 도달할 수 없는 위치
        if (graph[i][j] == 1 && visited[i][j] == false) {
          sb.append(-1).append(" ");
        } else {
          sb.append(graph[i][j]).append(" ");
        }
      }
      sb.append("\n");
    }
    System.out.println(sb);
  }
}
