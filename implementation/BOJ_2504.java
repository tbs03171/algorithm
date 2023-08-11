import java.util.*;
import java.io.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    Stack<Character> stack = new Stack<>();
    char arr[] = in.readLine().toCharArray();
    int ans = 0;
    int tmp = 1;

    for (int i = 0; i < arr.length; i++) {
      char c = arr[i];
      if (c == '(' || c == '[') { // (, [ 일 경우
        stack.push(c);
        tmp *= (c == '(')? 2 : 3;
      }
      else { // ), ] 일 경우
        if (stack.isEmpty()) {
          ans = 0;
          break;
        } else {
          char cc = stack.pop();
          if (c == ')') {
            if (cc != '(') {
              ans = 0;
              break;
            } else {
              if (arr[i - 1] == '(') ans += tmp;
              tmp /= 2;
            }
          } else {
            if (cc != '[') {
              ans = 0;
              break;
            } else {
              if (cc != '[') {
                ans = 0;
                break;
              } else {
                if (arr[i - 1] == '[') ans += tmp;
                tmp /= 3;
              }
            }
          }
        }
        
      }
    }

    if (!stack.isEmpty()) ans = 0;
    System.out.println(ans);
  }
}

/*
import java.util.*;

class Main {

  public static String s;
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    s = sc.nextLine();

    // 올바른 괄호열인지 확인
    if (isRight(s)) {
      System.out.println(recursion(0, s.length() - 1));
    }
    else {
      System.out.println(0);
    }
  }

  public static int recursion(int start, int end) {

    if (start > end) return 1; // (), [] 인 경우
    
    Stack<Character> stack = new Stack<>();
    int result = 0;
    for (int i = start; i <= end; i++) {
      char c = s.charAt(i);
      if (c == '(' || c == '[') { // 여는 괄호
        stack.push(c);
      } else { // 닫는 괄호
        stack.pop();
      }

      if (stack.isEmpty()) {
        if (c == ')') result = result + 2 * recursion(start + 1, i - 1);
        else result = result + 3 * recursion(start + 1, i - 1);
        start = i + 1;
      }
    }

    return result;
  }

  // 올바른 괄호열인지 확인
  public static boolean isRight(String s) {
    Stack<Character> stack = new Stack<>();

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c == '(' || c == '[') stack.push(c); // (, [
      else if (c == ')') { // )
        if (stack.isEmpty() || stack.peek() == '[') return false;
        stack.pop();
      }
      else { // ]
        if (stack.isEmpty() || stack.peek() == '(') return false;
        stack.pop();
      }
    }

    if (!stack.isEmpty()) return false;
    return true;
  }
}
*/
