import java.util.*;

class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    StringBuilder sb = new StringBuilder();

    int N = sc.nextInt();

    // list 계열 중 하나를 쓰면 된다.
    ArrayList<Integer> list = new ArrayList<>();

    for (int i = 0; i < N; i++) {
      list.add(sc.nextInt());
    }

    Collections.sort(list);

    for (int value : list) {
      sb.append(value).append('\n');
    }
    System.out.println(sb);
  }
}
