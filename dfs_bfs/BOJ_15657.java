import java.util.*;

class Main {

  public static int N, M;
  public static int[] nums;
  public static int[] arr;
  
  public static void main(String[] args) {
    
    Scanner sc = new Scanner(System.in);

    N = sc.nextInt();
    M = sc.nextInt();
    nums = new int[N];
    arr = new int[M];

    for (int i = 0; i < N; i++) {
      nums[i] = sc.nextInt();
    }
    Arrays.sort(nums);

    dfs(0, 0);
  }

  public static void dfs(int at, int depth) {

    if (depth == M) {
      for (int val : arr) {
        System.out.print(val + " ");
      }
      System.out.println();
      return;
    }

    for (int i = at; i < N; i++) {
      arr[depth] = nums[i];
      dfs(i, depth + 1);
    }
  }
}
