import java.util.*;

class Main {

  public static int n;
  public static ArrayList<Integer> list = new ArrayList<>();
  public static ArrayList<Integer> sortedList = new ArrayList<>();
  public static HashMap<Integer, Integer> map = new HashMap<>();

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    n = sc.nextInt();

    // n개의 좌표 정보 입력
    for (int i = 0; i < n; i++) {
      int x = sc.nextInt();
      list.add(x);
      sortedList.add(x);
    }

    // 정렬
    Collections.sort(sortedList);

    // 등수 정보 map에 저장
    int rank = 0;
    for (Integer val : sortedList) {
      if (!map.containsKey(val)) {
        map.put(val, rank);
        rank++;
      }
    }

    // 결과 출력
    StringBuilder sb = new StringBuilder();
    for (Integer val : list) {
      sb.append(map.get(val)).append(' ');
    }

    System.out.println(sb);
  }
}
