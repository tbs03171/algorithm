import java.util.*;

class Main {
  public static int[] d = new int[5001];

  // 봉지 키로수 N
  public static int n;
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // N 입력
    n = sc.nextInt();

    Arrays.fill(d, -1);
    d[3] = 1;
    d[5] = 1;

    // dp 시작
    for (int i = 3; i <= n; i++) {
      for (int j = 1; j < i; j++) {
        if (d[j] != -1 && d[i - j] != -1) {
          if (d[i] == -1) {
            d[i] = d[j] + d[i - j];
          } else {
            d[i] = Math.min(d[i], d[j] + d[i - j]);  
          }
        }
      }
    }

    System.out.println(d[n]);
  }
}
