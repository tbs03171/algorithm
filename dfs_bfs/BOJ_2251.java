import java.util.*;
import java.io.*;

public class Main {

  static int maxA, maxB, maxC;
  static ArrayList<Integer> answer;

  public static void main(String[] args) throws IOException {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(in.readLine(), " ");

    // 물병의 최대 용량 입력 받기
    maxA = Integer.parseInt(st.nextToken());
    maxB = Integer.parseInt(st.nextToken());
    maxC = Integer.parseInt(st.nextToken());

    answer = new ArrayList<>(); // 가능한 C의 용량 담는 list

    bfs();

    Collections.sort(answer);
    for (int ans : answer) {
      System.out.print(ans + " ");
    }
  }

  static class Bottle {
    int a, b, c;

    public Bottle(int a, int b, int c) {
      super();
      this.a = a;
      this.b = b;
      this.c = c;
    }
  }

  static void bfs() {
    Queue<Bottle> q = new LinkedList<>();
    boolean[][][] visited = new boolean[maxA + 1][maxB + 1][maxC + 1];

    q.add(new Bottle(0, 0, maxC)); // 초기 상태
    while (!q.isEmpty()) {
      Bottle curr = q.poll();
      if (visited[curr.a][curr.b][curr.c]) continue;

      if (curr.a == 0) {
        answer.add(curr.c);
      }

      visited[curr.a][curr.b][curr.c] = true;

      // case 1: B -> A
      if (curr.a + curr.b <= maxA) {
        q.add(new Bottle(curr.a + curr.b, 0, curr.c));
      } else {
        q.add(new Bottle(maxA, curr.a + curr.b - maxA, curr.c));
      }

      // case 2: C -> A
      if (curr.a + curr.c <= maxA) {
        q.add(new Bottle(curr.a + curr.c, curr.b, 0));
      } else {
        q.add(new Bottle(maxA, curr.b, curr.a + curr.c - maxA));
      }

      // case 3: A -> B
      if (curr.a + curr.b <= maxB) {
        q.add(new Bottle(0, curr.a + curr.b, curr.c));
      } else {
        q.add(new Bottle(curr.a + curr.b - maxB, maxB, curr.c));
      }

      // case 4: C -> B
      if (curr.c + curr.b <= maxB) {
        q.add(new Bottle(curr.a, curr.c + curr.b, 0));
      } else {
        q.add(new Bottle(curr.a, maxB, curr.b + curr.c - maxB));
      }

      // case 5: A -> C
      if (curr.a + curr.c <= maxC) {
        q.add(new Bottle(0, curr.b, curr.a + curr.c));
      } else {
        q.add(new Bottle(curr.a + curr.c - maxC, curr.b, maxC));
      }

      // case 6: B -> C
      if (curr.b + curr.c <= maxC) {
        q.add(new Bottle(curr.a, 0, curr.b + curr.c));
      } else {
        q.add(new Bottle(curr.a, curr.b + curr.c - maxC, maxC));
      }
    }
  }
}

/* 나의 풀이
import java.util.*;

class Main {

  public static boolean[][][] visited = new boolean[201][201][201];
  public static int[] capacity = new int[3]; // 물통 용량
  public static int[] now = new int[3]; // 물통에 현재 담겨있는 물 양
  public static HashSet<Integer> set = new HashSet<>();
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    capacity[0] = sc.nextInt();
    capacity[1] = sc.nextInt();
    capacity[2] = sc.nextInt();

    now[2] = capacity[2];
    visited[0][0][capacity[2]] = true;
    dfs(2, 0); // C -> A
    dfs(2, 1); // C -> B

    ArrayList<Integer> list = new ArrayList<>(set);
    Collections.sort(list);
    for (Integer val : list) {
      System.out.print(val + " ");
    }
  }

  public static void dfs(int from, int to) {
    if (now[from] == 0 || now[to] == capacity[to]) return; // from에 물이 없거나, to에 가득 차있는 경우 리턴
    // System.out.println("dfs(" + from + "," + to + ")");
    // System.out.println("dfs(" + from + "," + to + ")" + ":" + now[0] + " " + now[1] + " " + now[2]);
    
    // 물 옮기기
    int w1 = capacity[to] - now[to]; // 받을 수 있는 양
    int w2 = now[from]; // 줄 수 있는 양
    int beforeFrom = now[from]; // 주기 전 from 상태
    int beforeTo = now[to]; // 주기 전 to 상태

    if (w1 < now[from]) { // 받는 물통이 가득 참
      now[to] = capacity[to];
      now[from] = now[from] - w1;
    } else { // 주는 물통이 빔
      now[from] = 0;
      now[to] = now[to] + w2;
    }

    // A 비어 있다면, 결과 저장
    if (now[0] == 0) {
      set.add(now[2]);
    }

    // 재귀 탐색
    if (!visited[now[0]][now[1]][now[2]]) { // 탐색하지 않은 용량 상태
      visited[now[0]][now[1]][now[2]] = true;
      for (int i = 0; i < 3; i++) { // from
        for (int j = 0; j < 3; j++) { // to
          if (i == j) continue;
          if (i == from && j == to) continue;
          if (j == from && i == to) continue;
          dfs(i, j);
        }
      }
    }
    
    now[from] = beforeFrom;
    now[to] = beforeTo;
    
  }
}
*/