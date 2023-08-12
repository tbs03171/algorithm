import java.io.*;
import java.util.*;

class Point {
  int x, y;

  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
}

public class Main {

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int T = Integer.parseInt(br.readLine());

    ArrayList<Point> a; // 집, 편의점, 페스티벌의 위치를 저장하는 배열
    boolean[][] isSearch;

    StringBuilder sb = new StringBuilder();
    while (T-- > 0) {
      int N = Integer.parseInt(br.readLine());

      a = new ArrayList<>();

      // 집, 편의점, 페스티벌의 위치를 저장
      for (int i = 0; i < N + 2; i++) {
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        a.add(new Point(x, y));
      }

      isSearch = new boolean[N + 2][N + 2];

      // 맨해튼 거리 1000m 이하를 만족하는 두 정점을 찾음
      // 그 두 거리는 서로 연결되어 있다고 판단하고,
      // 경로 배열에 true 처리를 함.
      for (int i = 0; i < N + 2; i++) {
        for (int j = i + 1; j < N + 2; j++) {
          if (Manhattan(a.get(i), a.get(j)) <= 1000) {
            isSearch[i][j] = isSearch[j][i] = true;
          }
        }
      }

      fload(isSearch, N);

      sb.append((isSearch[0][N + 1] ? "happy" : "sad") + '\n');
    }

    bw.write(sb.toString());
    bw.flush();
    bw.close();
    br.close();
  }

  // 맨해튼 거리
  public static int Manhattan(Point p1, Point p2) {
    return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
  }

  // 플로이드 와샬 알고리즘
  public static void fload(boolean[][] isSearch, int N) {
    for (int k = 0; k < N + 2; k++) {
      for (int i = 0; i < N + 2; i++) {
        for (int j = 0; j < N + 2; j++) {
          if (isSearch[i][k] && isSearch[k][j]) {
            isSearch[i][j] = true;
          }
        }
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

  public static int t;
  public static int n;
  public static ArrayList<Position> list; // 각 위치의 좌표 가짐
  public static ArrayList<ArrayList<Integer>> graph;
  public static boolean[] visited;
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    t = sc.nextInt();

    while(t-- > 0) {
      n = sc.nextInt();
      list = new ArrayList<>();
      graph = new ArrayList<>();
      visited = new boolean[n + 2];

      // 각 위치 정보 저장 (상근 집 : 0번, 편의점 : 1~n번, 페스티벌 : n + 1번)
      // 그래프 초기화
      for (int i = 0; i <= n + 1; i++) {
        int x = sc.nextInt();
        int y = sc.nextInt();
        list.add(new Position(x, y));
        graph.add(new ArrayList<Integer>());
      }

      // 그래프 정보 입력 (각 위치에 대해 이동 가능한 위치 탐색)
      for (int i = 0; i <= n + 1; i++) {
        for (int j = i + 1; j <= n + 1; j++) {
          int x1 = list.get(i).x;
          int y1 = list.get(i).y;
          int x2 = list.get(j).x;
          int y2 = list.get(j).y;
          if (Math.abs(x1 - x2) + Math.abs(y1 - y2) <= 1000) { // 이동 가능한 장소
            graph.get(i).add(j);
            graph.get(j).add(i);
          }
        }
      }

      // bfs 탐색
      boolean check = false;
      Queue<Integer> q = new LinkedList<>();
      q.offer(0);
      visited[0] = true;

      while(!q.isEmpty()) {
        int now = q.poll();
        if (now == n + 1) {
          check = true;
          break;
        }

        for (int next : graph.get(now)) {
          if (visited[next] == false) {
            visited[next] = true;
            q.offer(next);
          }
        }
      }

      // 결과 출력
      if (check == true) System.out.println("happy");
      else System.out.println("sad");
    }
  }
}
*/