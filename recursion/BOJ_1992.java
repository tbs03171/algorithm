import java.util.*;

class Main {

  public static int N;
  public static int[][] graph;
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    graph = new int[N][N];

    sc.nextLine();
    for (int i = 0; i < N; i++) {
      String s = sc.nextLine();
      for (int j = 0; j < N; j++) {
        graph[i][j] = s.charAt(j) - '0';
      }
    }

    recursion(0, 0, N);
    
  }

  public static void recursion(int x, int y, int size) {

    // 해당 영역이 모두 0이거나 모두 1인지 확인
    int key = graph[x][y]; // 0인지 or 1인지
    boolean check = true;
    for (int i = x; i < x + size; i++) {
      for (int j = y; j < y + size; j++) {
        if (graph[i][j] != key) check = false;
      }
    }

    // 모두 같음
    if (check == true) {
      System.out.print(key);
      return;
    }

    // 다른거 존재
    System.out.print("(");
    recursion(x, y, size / 2);
    recursion(x, y + size / 2, size / 2);
    recursion(x + size / 2, y, size / 2);
    recursion(x + size / 2, y + size / 2, size / 2);
    System.out.print(")");
  }
}
