import java.io.*;

public class Main {

  public static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    
    getResult(0, n);
    
    System.out.println(sb);
    
  }

  public static void getResult(int output, int n) {
    if (n == 0) {
      if (isPrime(output)) sb.append(output).append("\n");
      return;
    }
    for (int i = 0; i < 10; i++) {
      int next = output * 10 + i;
      if (isPrime(next)) getResult(next, n - 1);
    }
  }

  public static boolean isPrime(int num) {
    if (num < 2) return false;

    for (int i = 2; i <= Math.sqrt(num); i++) {
      if (num % i == 0) return false;
    }
    return true;
  }
}

/* 나의 풀이
import java.util.*;

class Main {

  public static int N;

  public static ArrayList<Integer> list = new ArrayList<>(); // 이전 단계의 신기한 소수
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    list.add(2);
    list.add(3);
    list.add(5);
    list.add(7);

    for (int i = 2; i <= N; i++) {
      ArrayList<Integer> temp = new ArrayList<>();
      // 신기한 소수 후보들에 대해 소수라고 판정되면 넣기
      for (Integer val : list) {
        for (int j = 0; j <= 9; j++) {
          if (isPrime(val * 10 + j)) temp.add(val * 10 + j); 
        }
      }

      list = temp;
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < list.size(); i++) {
      sb.append(list.get(i));
      sb.append("\n");
    }

    System.out.print(sb);
  }

  public static boolean isPrime(int n) {
    double sqrt = Math.sqrt(n);
    for (int i = 2; i <= sqrt; i++) {
      if (n % i == 0) return false;
    }
    return true;
  }
}
*/