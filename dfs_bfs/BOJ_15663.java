import java.util.*;

class Main {

  public static int N, M;
  public static int[] arr;
  public static boolean[] visited;
  public static int[] result;
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    N = sc.nextInt();
    M = sc.nextInt();
    arr = new int[N];
    visited = new boolean[N];
    result = new int[M];

    for (int i = 0; i < N; i++) {
      arr[i] = sc.nextInt();
    }
    
    Arrays.sort(arr);

    dfs(0);
  }

  public static void dfs(int depth) {

    if (depth == M) {
      for (int val : result) {
        System.out.print(val + " ");
      }
      System.out.println();
      return;
    }

    for (int i = 0; i < N; i++) {
      if (result[depth] == arr[i]) continue; // 한 번 넣어 봤던 값은 패스
      if (visited[i] == false) {
        visited[i] = true;
        result[depth] = arr[i];
        dfs(depth + 1);
        visited[i] = false;
      }
    }
    result[depth] = 0; // 해당 칸에 남아 있는 값 없도록 초기화
    
  }
}
