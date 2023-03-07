import java.util.*;

public class ch11_2 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 숫자들 입력 받기
    String str = sc.next();

    // 한 글자씩 연산
    int result = str.charAt(0) - '0'; // 맨 처음 숫자 미리 받기
    for (int i = 1; i < str.length(); i++) {
      int b = str.charAt(i) - '0';
      if (result <= 1 || b <= 1) { // 피연산자가 0 이나 1 -> 더하기
        result = result + b;
      } else { // 곱하기
        result = result * b;
      }
    }

    System.out.println(result);
  }
}