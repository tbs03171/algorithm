import java.io.*;
import java.util.*;

public class Main {
  static int n, k;
  static int[] arr;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    n = Integer.parseInt(st.nextToken());
    k = Integer.parseInt(st.nextToken());
    arr = new int[n + 1];

    for (int i = 1; i <= n; i++) {
      st = new StringTokenizer(br.readLine());
      arr[i] = Integer.parseInt(st.nextToken());
    }

    int[] dp = new int[k + 1];

    for (int i = 1; i <= k; i++) {
      dp[i] = Integer.MAX_VALUE - 1;
    }
    for (int i = 1; i <= n; i++) {
      for (int j = arr[i]; j <= k; j++) {
        dp[j] = Math.min(dp[j], dp[j - arr[i]] + 1);
      }
    }

    if (dp[k] == Integer.MAX_VALUE - 1) System.out.println(-1);
    else System.out.println(dp[k]);
  }
}

/* 나의 풀이
import java.util.*;

class Main {

  public static int n, k;
  public static Integer[] coin;
  public static int[] dp;
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    k = sc.nextInt();
    dp = new int[k + 1];
    
    Set<Integer> set = new HashSet<>();
    for (int i = 0; i < n; i++) {
      set.add(sc.nextInt());
    }
    coin = set.toArray(new Integer[0]);

    Arrays.fill(dp, -1);
    dp[0] = 0;
    for (int i = 0; i < coin.length; i++) {
      for (int j = coin[i]; j <= k; j++) {
        if (dp[j - coin[i]] == -1) continue;
        if (dp[j] == -1) { // 처음 채우는 값
          dp[j] = dp[j - coin[i]] + 1;
        } else { // 이전 값 존재
          dp[j] = Math.min(dp[j], dp[j - coin[i]] + 1);
        }
      }
    }

    System.out.println(dp[k]);
    
  }
}
*/