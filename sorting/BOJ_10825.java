import java.util.*;

class Student implements Comparable<Student> {
  private String name;
  private int kor, eng, m;

  public Student(String name, int kor, int eng, int m) {
    this.name = name;
    this.kor = kor;
    this.eng = eng;
    this.m = m;
  }

  public String getName() {
    return this.name;
  }

  @Override
  public int compareTo(Student other) {
    if (this.kor == other.kor && this.eng == other.eng && this.m == other.m) {
      return this.name.compareTo(other.name);
    }
    if (this.kor == other.kor && this.eng == other.eng) {
      return Integer.compare(other.m, this.m);
    }
    if (this.kor == other.kor) {
      return Integer.compare(this.eng, other.eng);
    }
    return Integer.compare(other.kor, this.kor);
  }
}

class Main {
  public static int n; // 학생 수 N
  public static ArrayList<Student> students = new ArrayList<>();

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    n = sc.nextInt();

    // 학생들 정보 입력
    for (int i = 0; i < n; i++) {
      String name = sc.next();
      int kor = sc.nextInt();
      int eng = sc.nextInt();
      int m = sc.nextInt();
      students.add(new Student(name, kor, eng, m));
    }

    // 정렬
    Collections.sort(students);

    // 결과 출력
    for (int i = 0; i < students.size(); i++) {
      System.out.println(students.get(i).getName());
    }
  }
}
