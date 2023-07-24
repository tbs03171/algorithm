import java.util.*;

public class Main {

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();

    int[][] arr = new int[N][2];

    for (int i = 0; i < N; i++) {
      arr[i][0] = sc.nextInt();
      arr[i][1] = sc.nextInt();
    }

    Arrays.sort(arr, (e1, e2) -> {
      if(e1[0] == e2[0]) {
        return e1[1] - e2[1];
      } else {
        return e1[0] - e2[0];
      }
    });

    for (int i = 0; i < N; i++) {
      System.out.println(arr[i][0] + " " + arr[i][1]);
    }
  }
}

/* 나의 풀이
import java.util.*;

class Point implements Comparable<Point> {
  private int x, y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  @Override
  public int compareTo(Point other) {
    if (this.x == other.x) return Integer.compare(this.y, other.y);
    else return Integer.compare(this.x, other.x);
  }
}

class Main {
  
  public static void main(String[] args) {
    
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();

    // N개의 좌표 입력
    ArrayList<Point> list = new ArrayList<>();
    while (N-- > 0) {
      list.add(new Point(sc.nextInt(), sc.nextInt()));
    }

    // 정렬
    Collections.sort(list);

    // 출력
    for (Point p : list) {
      System.out.println(p.getX() + " " + p.getY());
    }
  }
}
*/