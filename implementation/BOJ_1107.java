import java.util.*;

class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int target = sc.nextInt();
    int m = sc.nextInt();

    boolean[] broken = new boolean[10];
    for (int i = 0; i < m; i++) {
      int n = sc.nextInt();
      broken[n] = true;
    }

    int result = Math.abs(target - 100); // 초기값 설정
    for (int i = 0; i <= 999999; i++) {
      String str = String.valueOf(i);
      int len = str.length();

      boolean isBreak = false;
      for (int j = 0; j < len; j++) {
        if (broken[str.charAt(j) - '0']) { // 고장난 버튼을 눌러야 하면
          isBreak = true;
          break; // 더 이상 탐색하지 않고 멈추기
        }
      }
      if (!isBreak) {
        int min = Math.abs(target - i) + len; // i를 누른 후 target까지 이동
        result = Math.min(min, result);
      }
    }
    System.out.println(result);
  }
}
