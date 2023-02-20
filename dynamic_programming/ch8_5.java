import java.util.*;

public class ch8_5 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 화폐 종류 수 N 입력
    int n = sc.nextInt();

    // 화폐 가치 합 M 입력
    int m = sc.nextInt();

    // 각 화폐의 가치 입력
    int[] coins = new int[n];
    for (int i = 0; i < n; i++) {
      coins[i] = sc.nextInt();
    }

    // 다이나믹 프로그래밍
    int[] d = new int[m + 1];
    Arrays.fill(d, 10001);
    d[0] = 0;
    for (int i = 0; i < n; i++) {
      for (int j = coins[i]; j <= m; j++) {
        if (d[j - coins[i]] != 10001) {
          d[j] = Math.min(d[j], d[j - coins[i]] + 1);
        }
      }
    }

    // 결과 출력
    if (d[m] == 10001) {
      System.out.println(-1);
    } else {
      System.out.println(d[m]);
    }
  }
}