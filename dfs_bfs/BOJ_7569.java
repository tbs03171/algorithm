import java.util.*;

class Position {
  private int x, y, z;

  public Position(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public int getZ() {
    return this.z;
  }
}

class Main {

  public static int m, n, h;
  public static int[][][] graph = new int[100][100][100];
  public static boolean[][][] visited = new boolean[100][100][100];
  public static Queue<Position> q = new LinkedList<Position>();
  public static int[] dz = {0, 0, -1, +1, 0, 0};
  public static int[] dy = {0, 0, 0, 0, -1, +1};
  public static int[] dx = {+1, -1, 0, 0, 0, 0};
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // m, n, h 입력
    m = sc.nextInt();
    n = sc.nextInt();
    h = sc.nextInt();

    // 그래프 정보 입력
    for (int i = 0; i < h; i++) {
      for (int j = 0; j < n; j++) {
        for (int k = 0; k < m; k++) {
          int x = sc.nextInt();
          if (x == 1) { // 익은 토마토는 큐에 넣기
            q.offer(new Position(i, j, k));
            graph[i][j][k] = 1;
            visited[i][j][k] = true;
          } else {
            graph[i][j][k] = x;
          }
        }
      }
    }

    // bfs 탐색
    while (!q.isEmpty()) {
      Position p = q.poll();
      
      for (int i = 0; i < 6; i++) {
        int nz = p.getZ() + dz[i];
        int ny = p.getY() + dy[i];
        int nx = p.getX() + dx[i];
        if (0 <= nx && nx < h && 0 <= ny && ny < n && 0 <= nz && nz < m) { // 경계 내부이며,
          if (visited[nx][ny][nz] == false && graph[nx][ny][nz] == 0) { // 방문하지 않았고, 익지 않은 토마토라면
            q.offer(new Position(nx, ny, nz));
            visited[nx][ny][nz] = true;
            graph[nx][ny][nz] = graph[p.getX()][p.getY()][p.getZ()] + 1;
          }
        }
      }
    }

    // 그래프 모두 탐색
    boolean all = true; // 모든 토마토 익음?
    int max = 0;
    for (int i = 0; i < h; i++) {
      for (int j = 0; j < n; j++) {
        for (int k = 0; k < m; k++) {
          max = Math.max(graph[i][j][k], max);
          if (visited[i][j][k] == false && graph[i][j][k] == 0) all = false;
        }
      }
    }

    // 결과 출력
    if (max == 1) { // 이미 다 익어있음
      System.out.println(0);
    } else if (all == false) { // 모두 익지 못함
      System.out.println(-1);
    } else {
      System.out.println(max - 1);
    }
  }
}
