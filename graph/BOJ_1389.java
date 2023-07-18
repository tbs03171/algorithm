import java.util.*;

class Main {
  public static int n, m;
  public static int[][] graph = new int[101][101];
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    n = sc.nextInt(); // 유저의 수
    m = sc.nextInt(); // 친구 관계의 수

    // 그래프 초기화
    for (int i = 0; i <= n; i++) {
      Arrays.fill(graph[i], 100);
      graph[i][i] = 0;
    }

    // 그래프 정보 입력
    while (m-- > 0) {
      int a = sc.nextInt();
      int b = sc.nextInt();
      graph[a][b] = 1;
      graph[b][a] = 1;
    }

    // 플로이드 워셜
    for (int k = 1; k <= n; k++) {
      for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
          graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
        }
      }
    }

    // 케빈 베이컨의 수 계산
    int minValue = Integer.MAX_VALUE; // 케빈 베이컨의 수가 가장 작은 사람의 값
    int minIdx = 0; // 케빈 베이컨의 수가 가장 작은 사람
    for (int i = 1; i <= n; i++) {
      int sum = 0;
      for (int j = 1; j <= n; j++) {
        sum = sum + graph[i][j];
      }
      if (minValue > sum) {
        minValue = sum;
        minIdx = i;
      }
    }

    // 결과 출력
    System.out.println(minIdx);
  }
}
