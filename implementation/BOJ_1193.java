import java.util.*;

public class Main {

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    int X = sc.nextInt();

    int cross_count = 1, prev_count_sum = 0;

    while (true) {

      // 직전 대각선 누적합 + 해당 대각선 개수 이용한 범위 판별
      if (X <= prev_count_sum + cross_count) {

        if (cross_count % 2 == 1) { // 대각선상의 블럭 개수가 홀수라면
          // 분자가 큰 수부터 시작
          // 분자는 대각선상 내의 블럭 개수 - (X 번째 - 직전 대각선까지의 블럭 개수 - 1)
          // 분모는 X 번째 - 직전 대각선까지의 블럭 개수
          System.out.print((cross_count - (X - prev_count_sum - 1)) + "/" + (X - prev_count_sum));
          break;
        }
          
        else { // 대각선상의 블럭 개수가 짝수라면
          // 홀수일 때의 출력을 반대로
          System.out.print((X - prev_count_sum) + "/" + (cross_count - (X - prev_count_sum - 1)));
          break;
        }
          
      } else {
        prev_count_sum += cross_count;
        cross_count++;
      }
    }
  }
}

/* 나의 풀이
import java.util.*;

class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int X = sc.nextInt();

    // 몇 번째 줄인지
    int line = 0;
    int sum = 0;
    while (sum < X) {
      line++;
      sum += line;
    }

    // 홀수이면 위로, 짝수이면 아래로
    int remain = X - (sum - line); // 나머지
    if (line % 2 == 0) { // 짝수이면
      System.out.println(remain + "/" + (line + 1 - remain));
    } else { // 홀수이면
      System.out.println((line + 1 - remain) + "/" + remain);
    }
  }
}
*/
