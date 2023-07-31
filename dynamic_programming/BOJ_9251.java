import java.util.*;

public class Main {

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    char[] str1 = sc.nextLine().toCharArray();
    char[] str2 = sc.nextLine().toCharArray();

    int length_1 = str1.length;
    int length_2 = str2.length;

    // 공집합 표현을 위해 인덱스가 한 줄씩 추가됨
    int[][] dp = new int[length_1 + 1][length_2 + 1];

    // 1부터 시작 (index 0은 공집합이므로 0의 값을 갖고 있음)
    for (int i = 1; i <= length_1; i++) {
      for (int j = 1; j <= length_2; j++) {

        // (i - 1)과 (j - 1) 번째 문자가 서로 같다면
        if (str1[i - 1] == str2[j - 1]) {
          // 대각선 위 (i - 1, j - 1)의 dp에 +1 한 값으로 갱신
          dp[i][j] = dp[i - 1][j - 1] + 1;
        }

        // 같지 않다면 이전 열(i - 1)과 이전 행(j - 1)의 값 중 큰 것으로 갱신
        else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }

    // Top-Down 때와는 다르게 dp 인덱스가 한 줄씩 추가되었으므로 -1을 하지 않음
    System.out.println(dp[length_1][length_2]);
    
  }
}

/* Top-Down 방식
import java.util.*;

class Main {

  static char[] str1;
  static char[] str2;

  static Integer[][] dp;
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    // toCharArray()는 문자열을 char[] 배열로 반환해주는 메소드
    str1 = sc.nextLine().toCharArray();
    str2 = sc.nextLine().toCharArray();

    dp = new Integer[str1.length][str2.length];

    System.out.println(LCS(str1.length - 1, str2.length - 1));
    
  }

  static int LCS(int x, int y) {

    // 인덱스 밖(공집합)일 경우 0 반환
    if (x == -1 || y == -1) {
      return 0;
    }

    // 만약 탐색하지 않은 인덱스라면?
    if (dp[x][y] == null) {
      dp[x][y] = 0;

      // str1의 x번째 문자와 str2의 y번째 문자가 같은지 검사
      if (str1[x] == str2[y]) {
        dp[x][y] = LCS(x - 1, y - 1) + 1;
      }

      // 같지 않다면 LCS(dp) [x - 1][y]와 LCS(dp) [x, y - 1] 중 큰 값으로 초기화
      else {
        dp[x][y] = Math.max(LCS(x - 1, y), LCS(x, y - 1));
      }
    }

    return dp[x][y];
  }
}
*/