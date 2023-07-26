import java.util.*;

class Main {

  public static int T;
  public static int[][] arr;
  public static int[][] dp;
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    T = sc.nextInt();

    while (T-- > 0) {
      int n = sc.nextInt();
      arr = new int[2][n + 1];
      dp = new int[2][n + 1];

      // 스티커 점수 정보 입력
      for (int i = 0; i < 2; i++) {
        for (int j = 1; j <= n; j++) {
          arr[i][j] = sc.nextInt();
        }
      }

      // 다이나믹 프로그래밍
      dp[0][1] = arr[0][1];
      dp[1][1] = arr[1][1];

      for (int i = 2; i <= n; i++) {
        dp[0][i] = Math.max(dp[1][i - 2], dp[1][i - 1]) + arr[0][i];
        dp[1][i] = Math.max(dp[0][i - 2], dp[0][i - 1]) + arr[1][i];
      }

      // 결과 출력
      System.out.println(Math.max(dp[0][n], dp[1][n]));
    }
    
  }
}
