import java.util.*;

class Main {

  public static int N, M;
  public static int[] arr;
  public static int[] result;
  
  public static void main(String[] args) {
    
    Scanner sc = new Scanner(System.in);

    N = sc.nextInt();
    M = sc.nextInt();
    arr = new int[N];
    result = new int[M];

    for (int i = 0; i < N; i++) {
      arr[i] = sc.nextInt();
    }
    Arrays.sort(arr);

    dfs(0, 0);
  }

  public static void dfs(int at, int depth) {
    
    if (depth == M) {
      for (int val : result) {
        System.out.print(val + " ");
      }
      System.out.println();
      return;
    }

    for (int i = at; i < N; i++) {
      if (result[depth] == arr[i]) continue;
      result[depth] = arr[i];
      dfs(i, depth + 1);
    }
    result[depth] = 0;
  }
}
