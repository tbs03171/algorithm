import java.util.*;

class Main {
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();

    while (T-- > 0) {
      int N = sc.nextInt();
      sc.nextLine();

      // N명의 MBTI 입력
      ArrayList<String> list = new ArrayList<String>();
      for (int i = 0; i < N; i++) {
        list.add(sc.next());
      }

      if (N > 32) {
        System.out.println(0);
        continue;
      }
      
      int min = Integer.MAX_VALUE;
outerLoop : for (int i = 0; i < N; i++) {
        for (int j = i + 1; j < N; j++) {
          for (int k = j + 1; k < N; k++) {
            min = Math.min(min, calcDistance(list.get(i), list.get(j), list.get(k)));
            if (min == 0) {
              break outerLoop;
            } 
          }
        }
      }

      System.out.println(min);
    }
  }

  public static int calcDistance(String p1, String p2, String p3) {
    int distance = 0;
    for (int i = 0; i < 4; i++) {
      distance += (p1.charAt(i) != p2.charAt(i) ? 1 : 0);
      distance += (p2.charAt(i) != p3.charAt(i) ? 1 : 0);
      distance += (p3.charAt(i) != p1.charAt(i) ? 1 : 0);
    }
    return distance;
  }

}
