package programmers;

import java.util.HashSet;
import java.util.Set;

public class PG_42895 {
        public static void main(String[] args) {

            Solution solution = new Solution();
            int result = solution.solution(5, 12);
            System.out.println(result);
        }
}


class Solution {

    Set<Integer>[] set = new HashSet[9];

    public int solution(int N, int number) {

        int answer = 0;

        // 사칙연산이 아닌 수 넣기 N, NN, NNN, ...
        int num = 0;
        for (int i = 1; i <= 8; i++) {
            num = num * 10 + N;
            set[i] = new HashSet<>();
            set[i].add(num);
        }

        if (N == number) return 1;

        // 2자릿수부터 8자릿수까지
        for (int i = 2; i <= 8; i++) {
            // j자리수와 (i - j)자리수의 조합으로 사칙연산
            for (int j = 1; j < i; j++) {
                for (Integer a : set[j]) {
                    for (Integer b : set[i - j]) {
                        set[i].add(a + b);
                        set[i].add(a - b);
                        set[i].add(a * b);
                        if (b != 0) set[i].add(a / b);
                    }
                }
            }

            // number 생성됨
            if (set[i].contains(number)) {
                answer = i;
                return answer;
            }
        }

        answer = -1;
        return answer;
    }
}