/* 플로이드 와샬 */
import java.io.*;
import java.util.*;

public class Main {

  static final int INF = 987654321;

  public static void main(String[] args) throws NumberFormatException, IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    // 그래프 초기화
    int[][] arr = new int[N + 1][N + 1];
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        if (i != j) {
          arr[i][j] = INF;
        }
      }
    }

    // 그래프 입력
    while (true) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());

      if (x == -1 && y == -1) {
        break;
      }

      arr[x][y] = arr[y][x] = 1; // 친구 관계는 양방향
    }

    // 플로이드 와샬
    for (int k = 1; k <= N; k++) {
      for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
          if (arr[i][j] > arr[i][k] + arr[k][j]) arr[i][j] = arr[i][k] + arr[k][j];
        }
      }
    }

    // 각 친구 점수 구하기
    int readerScore = INF;
    int[] scoreArr = new int[N + 1]; // 친구 점수 목록
    for (int i = 1; i <= N; i++) {
      int score = 0;
      for (int j = 1; j <= N; j++) {
        if (arr[i][j] != INF) {
          score = Math.max(score, arr[i][j]);
        }
      }

      scoreArr[i] = score;
      readerScore = Math.min(readerScore, score);
    }

    // 결과 출력
    StringBuilder title = new StringBuilder(); // 리더 점수
    title.append(readerScore + " ");

    int readerNum = 0;
    StringBuilder body = new StringBuilder(); // 리더 후보 목록
    for (int i = 1; i <= N; i++) {
      if (readerScore == scoreArr[i]) {
        readerNum++;
        body.append(i + " ");
      }
    }

    title.append(readerNum + "\n"); // 리더 후보 수

    bw.write(title.toString());
    bw.write(body.toString() + "\n");
    bw.flush();
    bw.close();
    br.close();
  }
}

/* BFS (나의 풀이)
import java.util.*;
import java.io.*;

class Main {

  public static int N;
  public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
  public static boolean[] visited;
  public static int[] distance;
  public static ArrayList<Integer> resultList = new ArrayList<>(); // 점수가 min인 후보들 리스트
  public static int min = 51; // 최저 점수
  
  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    // 회원 수 입력
    N = Integer.parseInt(br.readLine());

    // 회원 수만큼 그래프 초기화
    for (int i = 0; i <= N; i++) {
      graph.add(new ArrayList<Integer>());
    }

    // 그래프 정보 입력
    while (true) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      if (a == -1 && b == -1) break;

      graph.get(a).add(b);
      graph.get(b).add(a);
    }

    // BFS 탐색
    for (int i = 1; i <= N; i++) {
      visited = new boolean[N + 1];
      distance = new int[N + 1];
      int result = bfs(i);
      if (result < min) {
        resultList = new ArrayList<>();
        resultList.add(i);
        min = result;
      }
      else if (result == min) {
        resultList.add(i);
      }
    }

    // 결과 정렬 후 출력
    Collections.sort(resultList);
    System.out.println(min + " " + resultList.size());
    for (Integer val : resultList) {
      System.out.print(val + " ");
    }

  }

  public static int bfs(int start) {
    Queue<Integer> q = new LinkedList<>();
    visited[start] = true;
    distance[start] = 0;
    q.offer(start);
    int max = 0;

    while (!q.isEmpty()) {
      int now = q.poll();
      for (Integer val : graph.get(now)) {
        if (!visited[val]) {
          q.offer(val);
          visited[val] = true;
          distance[val] = distance[now] + 1;
          max = Math.max(max, distance[val]);
        }
      }
    }

    // 끝까지 닿지 못하면..?

    return max;
  } 
}
*/