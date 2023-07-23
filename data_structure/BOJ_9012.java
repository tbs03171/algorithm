import java.util.*;

class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    sc.nextLine();

    while (T-- > 0) {
      String s = sc.nextLine();

      if(isVPS(s)) System.out.println("YES");
      else System.out.println("NO");
    }
  }

  public static boolean isVPS(String s) {
    Stack<Character> stack = new Stack<>();
    
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '(') {
        stack.push('(');
      }
      else {
        if (stack.isEmpty()) return false;
        else stack.pop();
      }
    }

    if (stack.isEmpty()) return true;
    else return false;
  }
}
