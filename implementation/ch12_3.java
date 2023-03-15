import java.util.*;

class Solution {

  public int solution(String s) {
    int length = s.length(); // 문자열 길이
    int answer = length;

    // 압축 단위를 늘려가면서
    for (int step = 1; step <= length / 2; step++) {
      String compressed = ""; // 압축 결과
      String prev = s.substring(0, step); // 시작 문자열
      int cnt = 1; // 문자열 반복 횟수
      // 문자열의 처음부터 끝까지 step만큼 증가시키면서
      for (int i = step; i < length; i += step) {
        String sub = "";
        for (int j = i; j < i + step; j++) {
          if (j < s.length())
            sub += s.charAt(j);
        }
        if (sub.equals(prev)) { // 이전 문자열과 같으면
          cnt++;
        } else { // 이전 문자열과 다르면
          compressed += (cnt >= 2) ? cnt + prev : prev;
          sub = "";
          for (int j = i; j < i + step; j++) {
            if (j < s.length())
              sub += s.charAt(j);
          }
          prev = sub; // 상태 초기화
          cnt = 1;
        }
      }

      // 마지막 문자열의 경우 처리
      compressed += (cnt >= 2) ? cnt + prev : prev;
      // 길이가 더 짧다면 업데이트
      answer = Math.min(answer, compressed.length());
    }
    return answer;
  }
}