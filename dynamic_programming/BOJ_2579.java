import java.util.*;

class Main {
  // 계단의 개수 n
  public static int n;

  // 계단들 정보
  public static int[] arr = new int[301];
  
  // 결과
  public static int[] dp = new int[301];
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    n = sc.nextInt();

    // 계단 정보 입력
    for (int i = 1; i <= n; i++) {
      arr[i] = sc.nextInt();
    }

    dp[1] = arr[1];
    dp[2] = arr[1] + arr[2];
    for (int i = 3; i <= n; i++) {
      // 두 칸 이동해서 온 경우 or 두 칸 + 한 칸 이동해서 온 경우
      dp[i] = Math.max(dp[i - 2], dp[i - 3] + arr[i - 1]) + arr[i];
    }

    // 결과 출력
    System.out.println(dp[n]);
  }
}
