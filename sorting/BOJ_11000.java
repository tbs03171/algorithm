import java.util.*;

class Lesson implements Comparable<Lesson> {
  int start, end;

  Lesson(int start, int end) {
    this.start = start;
    this.end = end;
  }

  @Override
  public int compareTo(Lesson other) {
    return Integer.compare(this.start, other.start);
  }
}

class Main {

  public static int N;
  public static ArrayList<Lesson> list = new ArrayList<>();
  public static PriorityQueue<Integer> pq = new PriorityQueue<>();
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    
    for (int i = 0; i < N; i++) {
      int start = sc.nextInt();
      int end = sc.nextInt();
      list.add(new Lesson(start, end));
    }

    Collections.sort(list);

    for (Lesson l : list) {
      if (pq.isEmpty()) {
        pq.offer(l.end); // 큐가 비어있다면
        continue;
      }
      if (pq.peek() > l.start) { // 나의 시작보다 빠른 게 없다면
        pq.offer(l.end);
      } else { // 있다면
        pq.poll();
        pq.offer(l.end);
      }
    }

    System.out.println(pq.size());
  }
}
