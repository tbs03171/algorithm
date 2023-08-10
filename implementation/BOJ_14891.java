import java.io.*;
import java.util.*;

public class Main {
  static int gear[][];
  static int d[]; // 기어의 회전 정보를 담고 있음
  static int n, m;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    gear = new int[4][8];

    for (int i = 0; i < 4; i++) {
      String s = br.readLine();
      for (int j = 0; j < 8; j++) {
        gear[i][j] = s.charAt(j) - '0';
      }
    }

    int k = Integer.parseInt(br.readLine());

    while(k-- > 0) {
      st = new StringTokenizer(br.readLine());
      // 배열에 담긴 gear는 1부터 시작이 아닌 0부터라 -1을 해줌
      int gearN = Integer.parseInt(st.nextToken()) - 1;
      int turn = Integer.parseInt(st.nextToken());

      // 톱니의 회전방향 정보를 담음
      d = new int[4];

      d[gearN] = turn;
      checkDir(gearN);
      gearTurn();
    }

    int ans = 0;
    if(gear[0][0] == 1) ans += 1;
    if(gear[1][0] == 1) ans += 2;
    if(gear[2][0] == 1) ans += 4;
    if(gear[3][0] == 1) ans += 8;
    System.out.println(ans);
  }

  static void checkDir(int gearN) {
    // 좌측 톱니 회전 방향 검사
    for (int i = gearN - 1; i >= 0; i--) {
      if (gear[i][2] != gear[i + 1][6]) {
        d[i] = -d[i + 1];
      } else break; // 회전을 하지 않으면 다음 톱니도 회전을 하지 않게 된다.
    }

    // 우측 톱니 회전 방향 검사
    for (int i = gearN + 1; i < 4; i++) {
      if (gear[i][6] != gear[i - 1][2]) {
        d[i] = -d[i - 1];
      } else break;
    }
  }

  static void gearTurn() {
    int temp = 0;

    for (int i = 0; i < 4; i++) { // 모든 톱니에 대해서
      // 시계방향 회전
      if (d[i] == 1) {
        temp = gear[i][7];
        for (int j = 7; j > 0; j--) {
          gear[i][j] = gear[i][j - 1];
        }
        gear[i][0] = temp;
      }

      // 반시계방향 회전
      if (d[i] == -1) {
        temp = gear[i][0];
        for (int j = 0; j < 7; j++) {
          gear[i][j] = gear[i][j + 1];
        }
        gear[i][7] = temp;
      }
    }
  }
}

/* 나의 풀이
import java.util.*;

class Main {

  public static StringBuilder[] wheel = new StringBuilder[4];
  public static int[] move = new int[4];
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    for (int i = 0; i < 4; i++) {
      wheel[i] = new StringBuilder(sc.nextLine().trim());
    }

    int K = sc.nextInt();
    while(K-- > 0) {
      int num = sc.nextInt() - 1;
      int dir = sc.nextInt();
      move[num] = dir;
      
      // num의 회전이 왼쪽으로 영향 (지금꺼, 왼쪽꺼 극 비교)
      for (int i = num; i > 0; i--) {
        if (wheel[i].charAt(6) != wheel[i - 1].charAt(2)) { // 같이 움직임
          move[i - 1] = -move[i];
        } else { // 같이 움직이지 않음
          move[i - 1] = 0;
        }
      }

      // num의 회전이 오른쪽으로 영향
      for (int i = num; i < 3; i++) {
        if (wheel[i].charAt(2) != wheel[i + 1].charAt(6)) {
          move[i + 1] = -move[i];
        } else {
          move[i + 1] = 0;
        }
      }

      rotate();
    }

    // 점수 계산
    int score = 0;
    if (wheel[0].charAt(0) == '1') score += 1;
    if (wheel[1].charAt(0) == '1') score += 2;
    if (wheel[2].charAt(0) == '1') score += 4;
    if (wheel[3].charAt(0) == '1') score += 8;
    System.out.println(score);
  }

  public static void rotate() {
    for (int i = 0; i < 4; i++) { // 모든 톱니바퀴에 대해
      if (move[i] == -1) { // 반시계 방향
        char c = wheel[i].charAt(0);
        wheel[i].deleteCharAt(0);
        wheel[i].append(c);
      } else if (move[i] == 1) { // 시계 방향
        char c = wheel[i].charAt(7);
        wheel[i].deleteCharAt(7);
        wheel[i].insert(0, c);
      }
    }
  }
}
*/