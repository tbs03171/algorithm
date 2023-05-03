import java.util.*;

public class Main {

  static int n;
  static int[][] dp = new int[500][500]; // 다이나믹 프로그래밍을 위한 DP 테이블 초기화

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < i + 1; j++) {
        dp[i][j] = sc.nextInt();
      }
    }

    // 다이나믹 프로그래밍으로 2번째 줄부터 내려가면서 확인
    for (int i = 1; i < n; i++) {
      for (int j = 0; j <= i; j++) {
        int upLeft, up;
        // 왼쪽 위에서 내려오는 경우
        if (j == 0) upLeft = 0;
        else upLeft = dp[i - 1][j - 1];
        // 바로 위에서 내려오는 경우
        if (j == i) up = 0;
        else up = dp[i - 1][j];
        // 최대 합을 저장
        dp[i][j] = dp[i][j] + Math.max(upLeft, up);
      }
    }
    int result = 0;
    for (int i = 0; i < n; i++) {
      result = Math.max(result, dp[n - 1][i]);
    }
    System.out.println(result);
  }
}




/* 나의 코드

import java.util.*;

class Main {

  public static int n;
  public static int[] d = new int[500];

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 삼각형의 크기 n 입력
    n = sc.nextInt();

    // 정수 삼각형 입력
    ArrayList<ArrayList<Integer>> arrayList = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      ArrayList<Integer> temp = new ArrayList<>();
      for (int j = 0; j < i + 1; j++) {
        int num = sc.nextInt();
        temp.add(num);
      }
      arrayList.add(temp);
    }

    // 맨 밑에층 부터 시작 
    for (int i = n - 2; i >= 0; i--) {
      for (int j = 0; j < arrayList.get(i).size(); j++) {
        int max = Math.max(arrayList.get(i + 1).get(j), arrayList.get(i + 1).get(j + 1)) + arrayList.get(i).get(j);    
        arrayList.get(i).set(j, max);
      }
    }

    // 결과 출력
    System.out.println(arrayList.get(0).get(0));
  }
} 

*/