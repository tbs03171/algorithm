package programmers;
import java.util.*;

public class PG_131701 {
        public static void main(String[] args) {

            Solution solution = new Solution();
            int result = solution.solution(new int[]{7,9,1,1,4});
            System.out.println("result = " + result);
        }
}

class Solution {
    public int solution(int[] elements) {

        Set<Integer> sums = new HashSet<>();

        int len = elements.length;
        for (int i = 0; i < len; i++) {
            int sum = 0;
            for (int j = 0; j < len; j++) {
                sum += elements[(i + j) % len];
                sums.add(sum);
            }
        }

        return sums.size();
    }
}

/* 나의 풀이
class Solution {
    public int solution(int[] elements) {

        // 결과 합 담을 set
        Set<Integer> set = new HashSet<>();

        // 배열 연장
        int[] arr = new int[elements.length * 2];
        for (int i = 0; i < elements.length; i++) {
            arr[i] = elements[i];
        }
        for (int i = 0; i < elements.length; i++) {
            arr[i + elements.length] = elements[i];
        }

        // 누적합 구해놓기
        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i - 1] + arr[i];
        }

        // 1개짜리 순열 ~ n(배열 길이)짜리 순열
        for (int i = 1; i <= elements.length; i++) {
            set.add(arr[i - 1]);
            for (int j = i; j < elements.length * 2; j++) {
                set.add(arr[j] - arr[j - i]);
            }
        }

        return set.size();
    }
} */