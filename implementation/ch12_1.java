import java.util.*;

public class ch12_1 {

  public static String str;
  public static int summary = 0;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 점수 N 입력 받기
    String str = sc.next();

    // 왼쪽 합, 오른쪽 합 각각 구하기
    int left = 0;
    int right = 0;
    for (int i = 0; i < (str.length() / 2); i++) {
      left += (str.charAt(i) - '0');
    }
    for (int i = (str.length() / 2); i < str.length(); i++) {
      right += (str.charAt(i) - '0');
    }

    // 결과 출력
    if (left == right) {
      System.out.println("LUCKY");
    } else {
      System.out.println("READY");
    }
  }
}