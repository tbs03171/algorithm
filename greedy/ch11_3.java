import java.util.*;

public class ch11_3 {

  public static String str;
  public static int count0 = 0; // 전부 0으로 바꾸는 경우
  public static int count1 = 0; // 전부 1로 바꾸는 경우

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 문자열 입력 받기
    String str = sc.next();

    // 첫 번째 원소에 대해서 처리
    if (str.charAt(0) == '1') {
      count0++;
    } else {
      count1++;
    }

    // 두 번째 원소부터 모든 원소를 확인하며
    for (int i = 0; i < str.length() - 1; i++) {
      if (str.charAt(i) != str.charAt(i + 1)) {
        // 다음 수에서 1로 바뀌는 경우
        if (str.charAt(i + 1) == '1')
          count0++;
        // 다음 수에서 0으로 바뀌는 경우
        else
          count1++;
      }
    }

    // 결과 출력
    System.out.println(Math.min(count0, count1));
  }
}