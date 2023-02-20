import java.util.*;

public class ch8_4 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 가로 길이 N 입력 받기
    int n = sc.nextInt();

    int[] d = new int[n + 1];
    d[1] = 1;
    d[2] = 3;
    for (int i = 3; i <= n; i++) {
      d[i] = (d[i - 1] + 2 * d[i - 2]) % 796796;
    }

    System.out.println(d[n]);
  }
}