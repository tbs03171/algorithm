import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        char[][] type = {{'R', 'T'}, {'C', 'F'}, {'J', 'M'}, {'A', 'N'}};
        int[] score = {0, 3, 2, 1, 0, 1, 2, 3};
        HashMap<Character, Integer> point = new HashMap<Character, Integer>();

        // 점수 기록할 배열 초기화
        for (char[] t : type) {
            point.put(t[0], 0);
            point.put(t[1], 0);
        }

        // 점수 기록
        for (int idx = 0; idx < choices.length; idx++) {
            if (choices[idx] > 4) { // 동의
                point.put(survey[idx].charAt(1), point.get(survey[idx].charAt(1)) + score[choices[idx]]);
            } else { // 비동의
                point.put(survey[idx].charAt(0), point.get(survey[idx].charAt(0)) + score[choices[idx]]);
            }
        }

        // 지표 별 점수 비교 후 유형 기입
        for (char[] t : type) {
            answer += (point.get(t[1]) <= point.get(t[0])) ? t[0] : t[1];
        }

        return answer;
    }
}

/*
class Solution {
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('R', 0); map.put('T', 0);
        map.put('C', 0); map.put('F', 0);
        map.put('J', 0); map.put('M', 0);
        map.put('A', 0); map.put('N', 0);

        for (int i = 0; i < survey.length; i++) {
            if (choices[i] > 4) map.put(survey[i].charAt(1), map.get(survey[i].charAt(1)) + choices[i] - 4);
            else if (choices[i] < 4) map.put(survey[i].charAt(0), map.get(survey[i].charAt(0)) + 4 - choices[i]);
        }

        if (map.get('R') >= map.get('T')) answer = "R";
        else answer = "T";
        if (map.get('C') >= map.get('F')) answer += "C";
        else answer += "F";
        if (map.get('J') >= map.get('M')) answer += "J";
        else answer += "M";
        if (map.get('A') >= map.get('N')) answer += "A";
        else answer += "N";

        return answer;
    }
}
*/

/* 나의 풀이
class Solution {
    public Map<Character, Integer> scores = new HashMap<>();

    public String solution(String[] survey, int[] choices) {

        // scores 초기화
        scores.put('R', 0);
        scores.put('T', 0);
        scores.put('C', 0);
        scores.put('F', 0);
        scores.put('J', 0);
        scores.put('M', 0);
        scores.put('A', 0);
        scores.put('N', 0);

        // 문항 점수 계산
        for (int i = 0; i < survey.length; i++) { // 모든 문항에 대하여
            String type = survey[i]; // 문항 정보
            int choice = choices[i];
            if (choice < 4) { // 비동의쪽에 점수
                char c = type.charAt(0);
                int score = 4 - choice;
                scores.put(c, scores.get(c) + score);
            } else { // 동의쪽에 점수
                char c = type.charAt(1);
                int score = choice - 4;
                scores.put(c, scores.get(c) + score);
            }
        }

        // 최종 유형 판단
        String answer = "";

        // 1번 지표
        if (scores.get('R') < scores.get('T')) answer += "T";
        else answer += "R";

        // 2번 지표
        if (scores.get('C') < scores.get('F')) answer += "F";
        else answer += "C";

        // 3번 지표
        if (scores.get('J') < scores.get('M')) answer += "M";
        else answer += "J";

        // 4번 지표
        if (scores.get('A') < scores.get('N')) answer += "N";
        else answer += "A";


        return answer;
    }
}
*/