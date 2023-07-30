import java.util.*;

class Main {

  public static int N;
  public static int[][] arr;
  public static int[][] dp;
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    arr = new int[N][3];
    dp = new int[N][3];

    // 점수 정보 입력
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < 3; j++) {
        int x = sc.nextInt();
        arr[i][j] = x;
      }
    }

    // 최대 점수 계산
    dp[0][0] = arr[0][0];
    dp[0][1] = arr[0][1];
    dp[0][2] = arr[0][2];
    for (int i = 1; i < N; i++) {
      dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]) + arr[i][0];
      dp[i][1] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1], dp[i - 1][2])) + arr[i][1];
      dp[i][2] = Math.max(dp[i - 1][1], dp[i - 1][2]) + arr[i][2];
    }
    System.out.print(Math.max(dp[N - 1][0], Math.max(dp[N - 1][1], dp[N - 1][2])) + " ");

    // 최소 점수 계산
    for (int i = 1; i < N; i++) {
      dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]) + arr[i][0];
      dp[i][1] = Math.min(dp[i - 1][0], Math.min(dp[i - 1][1], dp[i - 1][2])) + arr[i][1];
      dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][2]) + arr[i][2];
    }
    System.out.print(Math.min(dp[N - 1][0], Math.min(dp[N - 1][1], dp[N - 1][2])));
  }
}
