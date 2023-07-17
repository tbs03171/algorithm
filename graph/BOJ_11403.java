import java.util.*;

class Main {

  public static int n;
  public static int[][] graph = new int[100][100];

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    n = sc.nextInt();

    // 그래프 정보 입력
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        graph[i][j] = sc.nextInt();
      }
    }

    // 플로이드 워셜
    for (int k = 0; k < n; k++) {
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          if (graph[i][k] == 1 && graph[k][j] == 1) graph[i][j] = 1;
        }
      }
    }

    // 결과 출력
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        System.out.print(graph[i][j] + " ");
      }
      System.out.println();
    }
    
  }
}
