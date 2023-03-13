import java.util.*;

public class ch11_5 {

  public static int n, m;
  // 1부터 10까지의 무게를 담을 수 있는 배열
  public static int[] arr = new int[11];

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 볼링공의 개수 N, 공의 최대 무게 M
    n = sc.nextInt();
    m = sc.nextInt();

    // 볼링공 무게 K 입력
    for (int i = 0; i < n; i++) {
      int k = sc.nextInt();
      arr[k] += 1;
    }

    // 경우의 수 계산
    int result = 0;
    for (int i = 1; i <= m; i++) {
      n -= arr[i];
      result += arr[i] * n;
    }

    // 결과 출력
    System.out.println(result);
  }
}