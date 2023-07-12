import java.util.*;

class Main {

  public static int n, m;
  public static int[] arr = new int[100001];
  public static int[] dp = new int[100001];
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    n = sc.nextInt();
    m = sc.nextInt();

    // n개의 수 입력
    for (int i = 1; i <= n; i++) {
      arr[i] = sc.nextInt();
    }

    // 합 미리 계산해서 저장
    int sum = 0;
    for (int i = 1; i <= n; i++) {
      sum = sum + arr[i];
      dp[i] = sum;
    }

    // 구간 입력
    for (int i = 0; i < m; i++) {
      int a = sc.nextInt();
      int b = sc.nextInt();
      System.out.println(dp[b] - dp[a - 1]);
    }
  }
}
