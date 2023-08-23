import java.util.*;
import java.io.*;

public class Main {

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(br.readLine());
    StringTokenizer st = null;

    int[][] arr = new int[n + 2][2];
    int[] dp = new int[n + 2];
    for (int i = 1; i < n + 1; i++) {
      st = new StringTokenizer(br.readLine());

      int t = Integer.parseInt(st.nextToken());
      int p = Integer.parseInt(st.nextToken());
      arr[i][0] = t; // 기간
      arr[i][1] = p; // 금액
    }

    int max = -1;
    for (int i = 1; i <= n + 1; i++) {
      if (max < dp[i]) {
        max = dp[i];
      }

      int nxt = i + arr[i][0];
      if (nxt < n + 2) {
        dp[nxt] = Math.max(dp[nxt], max + arr[i][1]);
      }
    }

    System.out.println(dp[n + 1]);
  }
}


/* 나의 풀이
import java.util.*;

class Node implements Comparable<Node> {
  int start, end, value;

  public Node (int start, int end, int value) {
    this.start = start;
    this.end = end;
    this.value = value;
  }

  @Override
  public int compareTo(Node other) {
    return Integer.compare(this.end, other.end);
  }
}

class Main {

  public static int N;
  public static int[] dp;
  public static PriorityQueue<Node> pq = new PriorityQueue<>();
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    dp = new int[N + 2];

    // 입력
    for (int i = 1; i <= N; i++) {
      int t = sc.nextInt();
      int end = i + t;
      int value = sc.nextInt();

      if (end > N + 1) continue; // 끝나는 날이 퇴사 이후
      pq.offer(new Node(i, end, value));
    }

    // 다이나믹 프로그래밍
    while (!pq.isEmpty()) {
      Node node = pq.poll();
      int start = node.start;
      int end = node.end;
      int value = node.value;

      int max = Math.max(dp[end], dp[start] + value);
      if (pq.isEmpty()) {
        dp[end] = max;
        break;
      }
      for (int i = end; i <= pq.peek().end; i++) {
        dp[i] = max;
      }
    }

    // 최댓값 찾기
    int result = 0;
    for (int i = 0; i <= N + 1; i++) {
      result = Math.max(result, dp[i]);
      // System.out.println(i + ": " + dp[i]);
    }

    System.out.println(result);

  }
}
*/