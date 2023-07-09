import java.util.*;

class Node {
  private int x, y;

  public Node(int x, int y) {
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

  // 지도의 크기 N
  public static int n;

  // 그래프
  public static int[][] graph = new int[25][25];

  public static boolean[][] visited = new boolean[25][25];

  // 결과 저장 (단지 번호, 단지에 속하는 집의 수)
  public static ArrayList<Integer> result = new ArrayList<>();

  public static int[] dx = {-1, +1, 0, 0};
  public static int[] dy = {0, 0, -1, +1};

  public static void bfs(int x, int y) {
    Queue<Node> q = new LinkedList<Node>();
    q.offer(new Node(x, y));
    visited[x][y] = true;

    int sum = 1;
    while(!q.isEmpty()) {
      Node node = q.poll();

      // 상하좌우 연결 노드 큐에 넣기
      for (int i = 0; i < 4; i++) {
        int nx = node.getX() + dx[i];
        int ny = node.getY() + dy[i];
        if (0 <= nx && nx < n && 0 <= ny && ny < n) { // 경계선 내부이며
          if (visited[nx][ny] == false && graph[nx][ny] == 1) { // 방문하지 않았고, 집이 있다면
            visited[nx][ny] = true;
            sum++;
            q.offer(new Node(nx, ny));
          }
        }
      }
    }

    // 단지 번호, 단지에 속하는 집의 수
    result.add(sum);
  }
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 지도의 크기 N 입력
    n = sc.nextInt();

    // 그래프 생성
    sc.nextLine();
    for (int i = 0; i < n; i++) {
      String s = sc.nextLine();
      for (int j = 0; j < n; j++) {
        graph[i][j] = s.charAt(j) - '0';
      }
    }

    // 탐색
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        // 방문한 적 없고, 집이 있다면 탐색
        if (graph[i][j] == 1 && visited[i][j] == false) {
          bfs(i, j);
        }
      }
    }

    // 정렬 후 결과 출력
    Collections.sort(result);
    System.out.println(result.size());
    for (int i = 0; i < result.size(); i++) {
      System.out.println(result.get(i));
    }
  }
}
