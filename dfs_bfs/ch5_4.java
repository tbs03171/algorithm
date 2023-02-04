import java.util.*;

class Pair {
  private int x;
  private int y;
  
  public Pair(int x, int y) {
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

public class Main {
  
  public static int n, m;
  public static Integer[][] graph;

  // 상, 하, 좌, 우
  public static int[] dx = {-1, +1, 0, 0};
  public static int[] dy = {0, 0, -1, +1};

  public static void bfs(int x, int y) {
    Queue<Pair> q = new LinkedList<>();
    q.offer(new Pair(x, y)); // 시작 위치
    
    while(true) {
      Pair curPoint = q.poll();
      x = curPoint.getX();
      y = curPoint.getY();
      if (x == n-1 && y == m-1) return; // 현재 도착 위치면 바로 return
      // 상, 하, 좌, 우 탐색
      for (int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];
        if (nx > -1 && nx < n && ny > -1 && ny < m) {
          if (graph[nx][ny] == 1) { // 방문 한 적 없으며, 이동 가능 
            graph[nx][ny] = graph[x][y] + 1; // 방문 처리
            q.offer(new Pair(nx, ny));
          }
        }
      }
    }
  }
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // N, M 입력 받기
    n = sc.nextInt();
    m = sc.nextInt();
    sc.nextLine(); // 버퍼 비우기

    // 미로 정보 입력 받기
    graph = new Integer[n][m];
    for (int i = 0; i < n; i++) {
      String str = sc.nextLine();
      for (int j = 0; j < m; j++) {
        graph[i][j] = str.charAt(j) - '0';
      }
    }

    // bfs 탐색
    bfs(0,0);
    
    // 결과 출력
    System.out.println(graph[n-1][m-1]);
  }
}