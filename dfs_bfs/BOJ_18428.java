import java.util.*;

class Combination {
  private int n;
  private int r;
  private int[] now; // 현재 조합
  private ArrayList<ArrayList<Position>> result; // 모든 조합

  public ArrayList<ArrayList<Position>> getResult() {
    return result;
  }

  public Combination(int n, int r) {
    this.n = n;
    this.r = r;
    this.now = new int[r];
    this.result = new ArrayList<ArrayList<Position>>();
  }

  public void combination(ArrayList<Position> arr, int depth, int index, int target) {
    if (depth == r) {
      ArrayList<Position> temp = new ArrayList<>();
      for (int i = 0; i < now.length; i++) {
        temp.add(arr.get(now[i]));
      }
      result.add(temp);
      return;
    }
    if (target == n) return;
    now[index] = target;
    combination(arr, depth + 1, index + 1, target + 1); // target을 넣음
    combination(arr, depth, index, target + 1); // target을 안넣음
  }
}

class Position {
  private int x;
  private int y;

  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }
}

public class Main {

  public static int n; // 복도의 크기
  public static char[][] board = new char[6][6]; // 복도 정보 (N x N)
  public static ArrayList<Position> teachers = new ArrayList<>(); // 모든 선생님 위치 정보
  public static ArrayList<Position> spaces = new ArrayList<>(); // 모든 빈 공간 위치 정보

  // 특정 방향으로 감시를 진행 (학생 발견: true, 학생 미발견: false)
  public static boolean watch(int x, int y, int direction) {
    // 왼쪽 방향으로 감시
    if (direction == 0) {
      while (y >= 0) {
        if (board[x][y] == 'S') { // 학생이 있는 경우
          return true;
        }
        if (board[x][y] == 'O') { // 장애물이 있는 경우
          return false;
        }
        y -= 1;
      }
    }

    // 오른쪽 방향으로 감시
    if (direction == 1) {
      while (y < n) {
        if (board[x][y] == 'S') { // 학생이 있는 경우
          return true;
        }
        if (board[x][y] == 'O') { // 장애물이 있는 경우
          return false;
        }
        y += 1;
      }
    }

    // 위쪽 방향으로 감시
    if (direction == 2) {
      while (x >= 0) {
        if (board[x][y] == 'S') { // 학생이 있는 경우
          return true;
        }
        if (board[x][y] == 'O') { // 장애물이 있는 경우
          return false;
        }
        x -= 1;
      }
    }

    // 아래쪽 방향으로 감시
    if (direction == 3) {
      while (x < n) {
        if (board[x][y] == 'S') { // 학생이 있는 경우
          return true;
        }
        if (board[x][y] == 'O') { // 장애물이 있는 경우
          return false;
        }
        x += 1;
      }
    }
    return false;
  }

  // 장애물 설치 이후에, 한 명이라도 학생이 감지되는지 검사
  public static boolean process() {
    // 모든 선생의 위치를 하나씩 확인
    for (int i = 0; i < teachers.size(); i++) {
      int x = teachers.get(i).getX();
      int y = teachers.get(i).getY();
      // 4가지 방향으로 학생을 감지할 수 있는지 확인
      for (int j = 0; j < 4; j++) {
        if (watch(x, y, j)) {
          return true;
        }
      }
    }
    return false;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    n = sc.nextInt();

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        board[i][j] = sc.next().charAt(0);
        // 선생님이 존재하는 위치 저장
        if (board[i][j] == 'T') {
          teachers.add(new Position(i, j));
        }
        // 장애물을 설치할 수 있는(빈 공간) 위치 저장
        if (board[i][j] == 'X') {
          spaces.add(new Position(i, j));
        }
      }
    }

    // 빈 공간에서 3개를 뽑는 모든 조합을 확인
    Combination comb = new Combination(spaces.size(), 3);
    comb.combination(spaces, 0, 0, 0);
    ArrayList<ArrayList<Position>> spaceList = comb.getResult();

    // 학생이 한 명도 감지되지 않도록 설치할 수 있는지의 여부
    boolean found = false;
    for (int i = 0; i < spaceList.size(); i++) {
      // 장애물들을 설치해보기
      for (int j = 0; j < spaceList.get(i).size(); j++) {
        int x = spaceList.get(i).get(j).getX();
        int y = spaceList.get(i).get(j).getY();
        board[x][y] = 'O';
      }
      // 학생이 한 명도 감지되지 않는 경우
      if (!process()) {
        // 원하는 경우를 발견한 것임
        found = true;
        break;
      }
      // 설치된 장애물을 다시 없애기
      for (int j = 0; j < spaceList.get(i).size(); j++) {
        int x = spaceList.get(i).get(j).getX();
        int y = spaceList.get(i).get(j).getY();
        board[x][y] = 'X';
      }
    }

    if (found) System.out.println("YES");
    else System.out.println("NO");
  }
}


/* 나의 풀이

import java.util.*;

class Position {
  private int x, y;

  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }
}

public class Main {

  public static int n; // 복도 크기 N
  public static int[][] graph = new int[7][7]; // 복도 정보
  public static ArrayList<Position> TArr = new ArrayList<Position>(); // 선생님들
  public static ArrayList<Position> XArr = new ArrayList<Position>(); // 빈칸들
  public static boolean result = false; // 가능, 불가능 결과

  public static void print() {
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) {
        System.out.print(graph[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println();
    System.out.println();
  }

  // 장애물 설치할 DFS (깊이 우선 탐색)
  public static void dfs(int now, int index) {
    if (now == 3 || index == XArr.size()) { // 3개 다 설치했다면
      if (isAvoidable()) { // 가능하다면
        result = true;
      }

      
      return;
    }

    // 빈칸에 설치 해보기
    for (int i = index; i < XArr.size(); i++) {
      int x = XArr.get(i).getX();
      int y = XArr.get(i).getY();
      graph[x][y] = 4; // 여기에 설치
      dfs(now + 1, index + 1);
      graph[x][y] = 0;
      dfs(now, index + 1); // 여기에 설치 안함
      return;
    }
  }

  
  // 모든 선생님들의 감시를 피할 수 있는지 여부
  public static boolean isAvoidable() {
    for (int i = 0; i < TArr.size(); i++) {
      int x = TArr.get(i).getX();
      int y = TArr.get(i).getY();
      // 상 방향 탐색
      for (int j = x - 1; j >= 1; j-- ) {
        if (graph[j][y] == 4) break; // 장애물 발견
        if (graph[j][y] == 2) return false; // 학생 발견
      }

      // 하 방향 탐색
      for (int j = x + 1; j <= n; j++ ) {
        if (graph[j][y] == 4) break; // 장애물 발견
        if (graph[j][y] == 2) return false; // 학생 발견
      }

      // 좌 방향 탐색
      for (int j = y - 1; j >= 1; j-- ) {
        if (graph[x][j] == 4) break; // 장애물 발견
        if (graph[x][j] == 2) return false; // 학생 발견
      }

      // 우 방향 탐색
      for (int j = y + 1; j <= n; j++ ) {
        if (graph[x][j] == 4) break; // 장애물 발견
        if (graph[x][j] == 2) return false; // 학생 발견
      }
    }

    // 모든 선생님에 대해 통과
    return true;
  }
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 복도 크기 N 입력
    n = sc.nextInt();

    // 복도 정보 입력
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) {
        char c = sc.next().charAt(0);
        if (c == 'X') { // 빈칸
          graph[i][j] = 0;
          XArr.add(new Position(i, j));
        }
        else if (c == 'T') { // 선생님
          graph[i][j] = 1;
          TArr.add(new Position(i, j));
        }
        else if (c == 'S') { // 학생
          graph[i][j] = 2;
        }
      }
    }

    // 장애물 설치 수행
    dfs(0, 0);

    // 결과 출력
    if (result == true) {
      System.out.println("YES");
    }
    else {
      System.out.println("NO");
    }
    
  }
}

*/