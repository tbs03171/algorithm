import java.util.*;

public class Main {

  public static StringBuilder sb = new StringBuilder();
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    ArrayDeque<Integer> deque;
    StringTokenizer st;

    int T = sc.nextInt();

    while (T-- > 0) {
      String command = sc.next();
      int n = sc.nextInt();
      st = new StringTokenizer(sc.next(), "[],");
      deque = new ArrayDeque<Integer>();

      // 덱에 배열 원소 넣기
      for (int i = 0; i < n; i++) {
        deque.add(Integer.parseInt(st.nextToken()));
      }

      AC(command, deque);
    }

    System.out.println(sb);
  }

  public static void AC(String command, ArrayDeque<Integer> deque) {
    boolean isRight = true; // 방향 상태 변수

    for (char cmd : command.toCharArray()) {
      // R인 경우
      if (cmd == 'R') {
        isRight = !isRight; // 방향 뒤집기
        continue;
      }

      // D인 경우
      if (isRight) {
        if (deque.pollFirst() == null) {
          sb.append("error\n");
          return;
        }
      } else {
        if (deque.pollLast() == null) {
          sb.append("error\n");
          return;
        }
      }
    }

    // 모든 함수들이 정상적으로 작동했다면 덱의 남은 요소들을 출력문으로 만들기
    makePrintString(deque, isRight);
  }

  public static void makePrintString(ArrayDeque<Integer> deque, boolean isRight) {
    sb.append('[');

    if (deque.size() > 0) {
      if (isRight) { // 정방향
        sb.append(deque.pollFirst());
        while (!deque.isEmpty()) {
          sb.append(',').append(deque.pollFirst());
        }
      }
      else { // 역방향
        sb.append(deque.pollLast());
        while(!deque.isEmpty()) {
          sb.append(',').append(deque.pollLast());
        }
      }
    }

    sb.append(']').append('\n');
  }
}

/* 나의 풀이
import java.util.*;

class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    sc.nextLine();
    
    while (T-- > 0) {
      StringBuilder sb = new StringBuilder();
      String op = sc.nextLine();

      int n = sc.nextInt();
      sc.nextLine();

      // arr 배열에 요소 넣기
      String str = sc.nextLine();
      str = str.substring(1, str.length() - 1);
      String[] arr = str.split(",");

      // 함수 수행
      int start = 0;
      int size = n;
      boolean dir = true; // 정방향 : true, 반대방향 : false
      for (int i = 0; i < op.length(); i++) {
        if (op.charAt(i) == 'R') { // '뒤집기'인 경우
          if (dir == true) {
            dir = false;
            start = start + size - 1;
          } else {
            dir = true;
            start = start - size + 1;
          }
        } else if (op.charAt(i) == 'D') { // '버리기' 인 경우
          if (dir == true) { // 정방향인 경우
            start++;
            size--;
          } else { // 반대방향인 경우
            start--;
            size--;
          }
        }
      }

      // 결과 출력
      if (dir == true) { // 정방향
        if (size < 0) { // error 발생
          System.out.println("error");
          continue;
        }
        sb.append("[");
        for (int i = start; i < start + size; i++) {
          sb.append(arr[i]);
          if (i != (start + size - 1)) sb.append(",");
        }
        sb.append("]");
      } else { // 반대방향
        if (size < 0) { // error 발생
          System.out.println("error");
          continue;
        }
        sb.append("[");
        for (int i = start; i > start - size; i--) {
          sb.append(arr[i]);
          if (i != (start - size + 1)) sb.append(",");
        }
        sb.append("]");
      }
      System.out.println(sb);
    }
  }
}
*/