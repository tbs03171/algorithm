import java.util.*;

class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();
    int[] seq = new int[N];
    int[] dp = new int[N];

    for (int i = 0; i < N; i++) {
      seq[i] = sc.nextInt();
    }

    // dp
    for (int i = 0; i < N; i++) {
      dp[i] = 1;

      // 0 ~ i 이번 원소들 탐색
      for (int j = 0; j < i; j++) {
        // j번째 원소가 i번째 원소보다 작으면서 i번째 dp가 j번째 dp+1 값보다 작은 경우
        if (seq[j] < seq[i] && dp[i] < dp[j] + 1) {
          dp[i] = dp[j] + 1; // j번째 원소의 +1 값이 i번째 dp가 됨
        }
      }
    }

    // 최댓값(최대 길이) 탐색
    int max = -1;
    for (int i = 0; i < N; i++) {
      max = Math.max(max, dp[i]);
    }
    System.out.println(max);
  }
}
