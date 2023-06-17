import java.util.*;

class Main {

  // 집의 수 N
  public static int n;

  // 비용 저장 배열
  public static int[][] cost = new int[10001][3];
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 집의 수 N 입력
    n = sc.nextInt();

    // N개의 집 RGB 비용 정보 입력
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < 3; j++) {
        cost[i][j] = sc.nextInt();
      }
    }

    // 탐색 시작
    for (int i = 0; i < n - 1; i++) {
      cost[i + 1][0] = Math.min(cost[i][1], cost[i][2]) + cost[i + 1][0];
      cost[i + 1][1] = Math.min(cost[i][0], cost[i][2]) + cost[i + 1][1];
      cost[i + 1][2] = Math.min(cost[i][0], cost[i][1]) + cost[i + 1][2];
    }

    // 결과 출력
    System.out.println(Math.min(cost[n - 1][0], Math.min(cost[n - 1][1], cost[n - 1][2])));
  }
}
