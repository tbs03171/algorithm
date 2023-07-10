import java.util.*;

class Node implements Comparable<Node> {
  private int start, end;

  public Node(int start, int end) {
    this.start = start;
    this.end = end;
  }

  public int getStart() {
    return this.start;
  }

  public int getEnd() {
    return this.end;
  }

  @Override
  public int compareTo(Node other) {
    if (this.end == other.end) {
      if (this.start > other.start) {
        return 1;
      } else {
        return -1;
      }
    } else if (this.end < other.end) {
      return -1;
    } else {
      return 1;
    }
  }
}

class Main {

  public static int n;
  public static ArrayList<Node> arrayList = new ArrayList<Node>();
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    n = sc.nextInt();
    
    // 회의들 입력 받고 정렬
    for (int i = 0; i < n; i++) {
      int start = sc.nextInt();
      int end = sc.nextInt();
      arrayList.add(new Node(start, end));
    }
    Collections.sort(arrayList);

    int endTime = arrayList.get(0).getEnd();
    int cnt = 1;
    for (int i = 1; i < arrayList.size(); i++) {
      if (arrayList.get(i).getStart() >= endTime) {
        cnt++;
        endTime = arrayList.get(i).getEnd();
      }
    }

    System.out.println(cnt);
  }
}
