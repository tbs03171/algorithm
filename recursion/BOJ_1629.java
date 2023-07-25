import java.util.*;

class Main {

  public static long C;
  
  public static void main(String[] args) {
    
    Scanner sc = new Scanner(System.in);

    long A = sc.nextLong();
    long B = sc.nextLong();
    C = sc.nextLong();

    System.out.println(pow(A, B));
    
  }

  // A^exponent
  public static long pow(long A, long exponent) {

    // 지수가 1일 경우 A^1이므로 A를 그대로 리턴
    if (exponent == 1) return A % C;

    // 지수의 절반에 해당하는 A^(exponent / 2) 를 구한다.
    long temp = pow(A, exponent / 2);

      /*
      * 현재 지수가 홀수 였다면
      * A^(exponent / 2) * A^(exponent / 2) * A 이므로
      * A를 한번 더 곱해주어야 한다.
      *
      * ex) A^9 = A^4 * A^4 * A
      */
    if (exponent % 2 == 1) return (temp * temp % C) * A % C;
    return temp * temp % C;
  }
  
}
