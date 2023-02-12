import java.util.*;

public class ch6_3 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 학생 수 N 입력 받기
    int n = sc.nextInt();

    // 점수별 학생들 정보 입력 받을 배열 만들기
    ArrayList<String>[] scoreArr = new ArrayList[100];
    for (int i = 0; i < 100; i++) {
      scoreArr[i] = new ArrayList<String>();
    }

    // 학생 정보 입력 받기
    for (int i = 0; i < n; i++) {
      String name = sc.next();
      int score = sc.nextInt();
      scoreArr[score].add(name);
    }

    // 출력 하기
    for (int i = 0; i < 100; i++) {
      Iterator iter = scoreArr[i].iterator();
      while (iter.hasNext()) {
        System.out.print(iter.next() + " ");
      }
    }
  }
}