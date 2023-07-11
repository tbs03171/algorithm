import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int sum = Integer.MAX_VALUE; // 초기 상태 여부 확인을 위한 값으로 설정
    String[] subtraction = sc.nextLine().split("-");

    for (int i = 0; i < subtraction.length; i++) {
      int temp = 0;

      // 뺄셈으로 나뉜 토큰을 덧셈으로 분리하여 해당 토큰들을 더한다.
      String[] addiction = subtraction[i].split("\\+");

      // 덧셈으로 나뉜 토큰들을 모두 더한다.
      for (int j = 0; j < addiction.length; j++) {
        temp += Integer.parseInt(addiction[j]);
      }

      // 첫 번째 토큰인 경우 temp 값이 첫 번째 수가 됨
      if (sum == Integer.MAX_VALUE) {
        sum = temp;
      } else {
        sum -= temp;
      }
    }
    
    System.out.println(sum);
  }
}

/* 나의 코드
import java.util.*;

class Main {
  public static ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 식 입력
    String s = sc.nextLine();

    int before = 0;
    int index = 0;
    list.add(new ArrayList<Integer>());
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '-' || s.charAt(i) == '+') { // 연산자라면
        list.get(index).add(Integer.parseInt(s.substring(before, i)));
        if (s.charAt(i) == '-') { // 마이너스 연산자라면
          list.add(new ArrayList<Integer>());
          index++;
        }
        before = i + 1;
      }
    }
    list.get(index).add(Integer.parseInt(s.substring(before, s.length()))); // 마지막 숫자

    int result = 0;
    for (int i = 0; i < list.size(); i++) {
      int sum = 0;
      for (int j = 0; j < list.get(i).size(); j++) {
        sum = sum + list.get(i).get(j);
      }
      if (i == 0) {
        result = result + sum;
      } else {
        result = result - sum;
      }
    }

    System.out.println(result);
  }
}
*/