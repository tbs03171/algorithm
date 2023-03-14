import java.util.*;

public class ch12_2 {

  public static String str;
  public static ArrayList<Character> result = new ArrayList<Character>();
  public static int sum = 0; // 숫자 합 저장

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 입력
    str = sc.next();

    // 문자를 하나씩 확인하며
    for (int i = 0; i < str.length(); i++) {
      char tmp = str.charAt(i);
      if (Character.isDigit(tmp) == true) { // 숫자면
        sum += (tmp - '0');
      } else { // 문자면
        result.add(tmp);
      }
    }

    // 알파벳 오름차순 정렬
    Collections.sort(result);

    // 출력
    for (int i = 0; i < result.size(); i++) {
      System.out.print(result.get(i));
    }

    // 숫자가 하나라도 존재하는 경우 가장 뒤에 출력
    if (sum != 0)
      System.out.print(sum);
  }
}