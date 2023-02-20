import java.util.*;

public class ch8_2 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 정수 X 입력 받기
    int x = sc.nextInt();

    int[] d = new int[x + 1];
    d[1] = 0;
    for (int i = 2; i <= x; i++) {
      // X에서 1을 빼는 경우
      d[i] = d[i - 1] + 1;
      // X가 5로 나누어 떨어지면
      if (i % 5 == 0)
        d[i] = Math.min(d[i], d[i / 5] + 1);
      // X가 3으로 나누어 떨어지면
      if (i % 3 == 0)
        d[i] = Math.min(d[i], d[i / 3] + 1);
      // X가 2로 나누어 떨어지면
      if (i % 2 == 0)
        d[i] = Math.min(d[i], d[i / 2] + 1);
    }

    System.out.println(d[x]);
  }
}