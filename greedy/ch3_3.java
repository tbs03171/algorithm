import java.util.*;

class ch3_3 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // N, M 입력받기
    int N = sc.nextInt();
    int M = sc.nextInt();

    int max = 0; // 각 줄에서 제일 작은 수 중에 제일 큰 수
    int currentNum; // 현재 숫자
    for (int i = 0; i < N; i++) {
      // 각 줄에서 제일 작은 수 찾기
      int min = 10001; // 각 줄에서 제일 작은 수
      for (int j = 0; j < M; j++) {
        currentNum = sc.nextInt();
        if (min > currentNum)
          min = currentNum;
      }
      if (max < min)
        max = min;
    }

    // 출력하기
    System.out.println(max);
  }
}