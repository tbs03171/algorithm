import java.util.*;

public class ch8_3 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 식량창고 개수 N 입력 받기
    int n = sc.nextInt();

    // 식량 창고 정보 K 입력 받기
    int[] d = new int[n];
    for (int i = 0; i < n; i++) {
      d[i] = sc.nextInt();
    }

    d[2] = d[0] + d[2];
    for (int i = 3; i < n; i++) {
      d[i] = Math.max(d[i - 2], d[i - 3]) + d[i];
    }

    System.out.println(Math.max(d[n - 1], d[n - 2]));
  }
}