import java.util.*;

class Main {

  public static int n, m;
  public static HashMap<String, Integer> map = new HashMap<>(); // 이름으로 검색
  public static ArrayList<String> list = new ArrayList<>(); // 번호로 검색
  
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    n = sc.nextInt();
    m = sc.nextInt();

    // 포켓몬 이름 입력
    sc.nextLine();
    for (int i = 1; i <= n; i++) {
      String name = sc.nextLine();
      map.put(name, i);
      list.add(name);
    }

    for (int i = 0; i < m; i++) {
      String s = sc.nextLine();
      if (!Character.isDigit(s.charAt(0))) { // 입력이 이름
        System.out.println(map.get(s));
      } else { // 입력이 숫자
        Integer index = Integer.parseInt(s);
        System.out.println(list.get(index - 1));
      }
    }
  }
}
