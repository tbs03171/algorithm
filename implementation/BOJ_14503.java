import java.io.*;
import java.util.*;

public class Main {

  static int y, x, count;
  static int map[][];
  static int dy[] = {-1, 0, 1, 0}; // 북동남서
  static int dx[] = {0, 1, 0, -1};

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer stk = new StringTokenizer(br.readLine(), " ");

    y = Integer.parseInt(stk.nextToken());
    x = Integer.parseInt(stk.nextToken());
    map = new int[y][x];

    stk = new StringTokenizer(br.readLine(), " ");
    int r = Integer.parseInt(stk.nextToken());
    int c = Integer.parseInt(stk.nextToken());
    int d = Integer.parseInt(stk.nextToken()); // 방향 0북, 1동, 2남, 3서
    count = 1;

    for (int i = 0; i < y; i++) {
      stk = new StringTokenizer(br.readLine(), " ");
      for (int j = 0; j < x; j++) {
        map[i][j] = Integer.parseInt(stk.nextToken());
      }
    }

    dfs(r, c, d);
    System.out.println(count);
  }

  public static void dfs(int r, int c, int direction) {

    // 현재 위치를 청소한다.
    map[r][c] = -1;

    // 현재 위치에서 현재 방향을 기준으로 왼쪽방향부터 차례대로 탐색을 진행한다.
    for (int i = 0; i < 4; i++) {
      direction = (direction + 3) % 4; // 왼쪽 방향으로 변환 (북 -> 서 -> 남 -> 동)
      int ny = r + dy[direction];
      int nx = c + dx[direction];

      // 청소가 안된 곳이 있으면 count++, dfs
      if (ny >= 0 && ny < y && nx >= 0 && nx < x && map[ny][nx] == 0) {
        count++;
        dfs(ny, nx, direction);

        // 여기서 return을 안하면 복귀하는 도중 뒤로가서 다른 곳을 청소할 수가 있음
        return;
      }
    }

    // 네 방향 모두 청소가 이미 되어있거나 벽인 경우에는
    // 뒤쪽 칸이 벽이 아니라는 전제 하에, 바라보는 방향을 유지한 채로 한 칸 후진
    int back = (direction + 2) % 4;
    int by = r + dy[back];
    int bx = c + dx[back];

    if (by >= 0 && by < y && bx >= 0 && bx < x && map[by][bx] != 1) {
      dfs(by, bx, direction);
    }
  }
}

/* 나의 풀이
import java.util.*;

class Main {

  public static int N, M;
  public static int cnt = 0;
  public static int result = 0;
  public static boolean[][] visited;
  public static int[][] graph;
  public static int[] dx = {-1, 0, +1, 0};
  public static int[] dy = {0, +1, 0, -1};
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    M = sc.nextInt();
    visited = new boolean[N][M];
    graph = new int[N][M];

    int x = sc.nextInt();
    int y = sc.nextInt();
    int d = sc.nextInt();

    // 그래프 입력
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        graph[i][j] = sc.nextInt();
      }
    }

    dfs(x, y, d);

    System.out.println(result);
  }

  public static void dfs(int x, int y, int d) {
    if (graph[x][y] == 0 && visited[x][y] == false) { // 청소되지 않은 빈 칸인 경우 청소
      visited[x][y] = true;
      cnt++;
    }

    // 반시계 방향으로 회전하며 청소되지 않은 빈 칸 확인
    boolean check = false;
    for (int i = 3; i >= 0; i--) {
      int nd = (d + i) % 4;
      int nx = x + dx[nd];
      int ny = y + dy[nd];
      if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
      if (graph[nx][ny] == 0 && visited[nx][ny] == false) {
        check = true;
        dfs(nx, ny, nd);
      }
    }

    // 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
    if (check == false) {
      int nd = (d + 2) % 4; // 후진 방향
      int nx = x + dx[nd]; // 후진 위치
      int ny = y + dy[nd];

      if (0 <= nx && nx < N && 0 <= ny && ny < M && graph[nx][ny] == 0) { // 후진 가능
        dfs(nx, ny, d);
      } else {
        if (result == 0)    result = cnt;
        return; // 후진 불가능 (작동 멈춤)
      }
    }
  }
}
*/