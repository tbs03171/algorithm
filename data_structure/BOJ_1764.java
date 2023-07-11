import java.util.*;

class Main {

  public static int n, m;
  public static Set<String> setA = new HashSet<>();
  public static Set<String> setB = new HashSet<>();
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    n = sc.nextInt();
    m = sc.nextInt();

    sc.nextLine();

    // 듣도 못한 사람
    for (int i = 0; i < n; i++) {
      setA.add(sc.nextLine());
    }

    // 보도 못한 사람
    for (int i = 0; i < m; i++) {
      setB.add(sc.nextLine());
    }

    // 교집합 구하기
    setA.retainAll(setB);

    // 리스트로 변경하고 정렬
    ArrayList<String> list = new ArrayList<>(setA);
    Collections.sort(list);

    // 듣보잡의 수, 명단 출력
    System.out.println(list.size());
    for (int i = 0; i < list.size(); i++) {
      System.out.println(list.get(i));
    }
  }
}
