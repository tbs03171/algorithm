import java.io.*;
import java.util.*;

public class Main {

  static int N, M;
  static int[][] A, B;

  public static void main(String[] args) throws Exception {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    String[] sarr = br.readLine().split(" ");
    N = Integer.parseInt(sarr[0]);
    M = Integer.parseInt(sarr[1]);

    A = new int[N][M];
    B = new int[N][M];

    for (int i = 0; i < N; i++) {
      String str = br.readLine();
      for (int j = 0; j < M; j++) {
        A[i][j] = str.charAt(j) - '0';
      }
    }

    for (int i = 0; i < N; i++) {
      String str = br.readLine();
      for (int j = 0; j < M; j++) {
        B[i][j] = str.charAt(j) - '0';
      }
    }

    int cnt = 0;

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (A[i][j] != B[i][j] && i + 2 < N && j + 2 < M) {
          for (int r = i; r < i + 3; r++) {
            for (int c = j; c < j + 3; c++) {
              A[r][c] = A[r][c] == 0 ? 1 : 0;
            }
          }
          cnt++;
        }
      }
    }

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (A[i][j] != B[i][j]) {
          bw.write(-1 + "\n");
          bw.flush();
          return;
        }
      }
    }

    bw.write(cnt + "\n");
    bw.flush();
  }
}

/* 나의 풀이
import java.util.*;

class Main {

  public static int N, M;
  public static boolean[][] arr1;
  public static boolean[][] arr2;
  public static int result = 0;
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    M = sc.nextInt();
    arr1 = new boolean[N][M];
    arr2 = new boolean[N][M];

    // 행렬 입력
    sc.nextLine();
    for (int i = 0; i < N; i++) {
      String str = sc.nextLine();
      for (int j = 0; j < M; j++) {
        if (str.charAt(j) == '0') arr1[i][j] = false;
        else arr1[i][j] = true;
      }
    }
    for (int i = 0; i < N; i++) {
      String str = sc.nextLine();
      for (int j = 0; j < M; j++) {
        if (str.charAt(j) == '0') arr2[i][j] = false;
        else arr2[i][j] = true;
      }
    }

    // 연산
    for (int i = 0; i <= N - 3; i++) {
      for (int j = 0; j <= M - 3; j++) {
        if (arr1[i][j] != arr2[i][j]) {
          result++;
          operation(i, j);
        }
      }
    }

    // 일치하는지 확인
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (arr1[i][j] != arr2[i][j]) result = -1;
      }
    }
    System.out.println(result);
  }

  // 3 X 3 크기의 부분 행렬에 있는 모든 원소 뒤집는 연산
  public static void operation(int x, int y) {

    for (int i = x; i < x + 3; i++) {
      for (int j = y; j < y + 3; j++) {
        arr1[i][j] = !arr1[i][j];
      }
    }

  }
}
*/