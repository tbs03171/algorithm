import java.util.*;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int top = -1; // 마지막 원소의 위치를 가리키는 변수

    int K = sc.nextInt();
    int[] arr = new int[K];

    for (int i = 0; i < K; i++) {

      int number = sc.nextInt(); // 정수 입력

      if (number == 0) { // 0 이라면
        top--; // top이 가리키는 위치 1 감소
      }
      else {
        top++; // top이 가리키는 위치 1 증가
        arr[top] = number; // 입력받은 정수로 초기화
      }
    }

    int sum = 0;
    for (int i = 0; i <= top; i++) {
      sum += arr[i];
    }
    System.out.println(sum);
    
  }
}

/*
import java.util.*;

class Main {
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    int K = sc.nextInt();

    // K개의 값 입력
    Stack<Integer> stack = new Stack<>();
    while (K-- > 0) {
      int x = sc.nextInt();
      if (x == 0) {
        stack.pop();
      } else {
        stack.push(x);
      }
    }

    // 합 구해서 출력
    int sum = 0;
    for (int o : stack) {
      sum += o;
    }
    System.out.println(sum);
  }
}
*/