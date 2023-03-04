import java.util.*;

public class ch9_2 {

  public static final int INF = (int) 1e9; // 무한
  public static int n, m; // 전체 회사의 개수 N, 경로의 개수
  public static int[][] graph = new int[101][101];

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    n = sc.nextInt();
    m = sc.nextInt();

    // 최단 거리 테이블을 모두 무한으로 초기화
    for (int i = 0; i < 101; i++) {
      Arrays.fill(graph[i], INF);
    }

    // 자기 자신에서 자기 자신으로 가는 비용은 0으로 초기화
    for (int a = 1; a <= n; a++) {
      for (int b = 1; b <= n; b++) {
        if (a == b)
          graph[a][b] = 0;
      }
    }

    // 회사 연결 정보 입력 받아 초기화
    for (int i = 0; i < m; i++) {
      int a = sc.nextInt();
      int b = sc.nextInt();
      graph[a][b] = 1;
      graph[b][a] = 1;
    }

    // 플로이드 워셜 알고리즘 수행
    for (int k = 1; k <= n; k++) {
      for (int a = 1; a <= n; a++) {
        for (int b = 1; b <= n; b++) {
          graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
        }
      }
    }

    // 최종 도착 회사 X, 소개팅 상대 회사 K 입력 받기
    int x = sc.nextInt();
    int k = sc.nextInt();

    // 수행 결과 출력
    if (graph[1][k] == INF || graph[k][x] == INF) {
      System.out.println(-1);
    } else {
      System.out.println(graph[1][k] + graph[k][x]);
    }
  }
}