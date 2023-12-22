package programmers;
import java.util.*;

public class PG_77486 {
        public static void main(String[] args) {

            Solution solution = new Solution();
            int[] results = solution.solution(new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"}, new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"}, new String[]{"young", "john", "tod", "emily", "mary"}, new int[]{12, 4, 2, 5, 10});
            for (int result : results) {
                System.out.print(result + " ");
            }
        }
}

class Solution {

    public int[] solution (String[] enroll, String[] referral, String[] seller, int[] amount) {

        // 초기화
        int[] answer = new int[enroll.length];
        Map<String, String> root = new HashMap<>();
        Map<String, Integer> total = new HashMap<>();

        for (int i = 0; i < enroll.length; i++) {
            if (referral[i].equals("-"))
                root.put(enroll[i], "center");
            else
                root.put(enroll[i], referral[i]);
            total.put(enroll[i], 0);
        }

        // 수익 배분
        for (int i = 0; i < seller.length; i++) {
            String kid = seller[i];
            String parent = root.get(seller[i]);
            int money = amount[i] * 100;
            while (true) {
                total.put(kid, total.get(kid) + money - money / 10);
                money /= 10;
                if (money == 0 || parent.equals("center"))
                    break;

                kid = parent;
                parent = root.get(kid);
            }
        }

        // 결과 저장
        for (int i = 0; i < enroll.length; i++)
            answer[i] = total.get(enroll[i]);
        return answer;
    }
}

/* 나의 풀이
class Solution {

    Map<String, String> referralMap = new HashMap<>(); // 추천인 정보
    Map<String, Integer> resultMap = new HashMap<>(); // 총 순수 이익 계산 결과

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {

        // 추천인 정보 저장하면서 map 초기화
        for (int i = 0; i < enroll.length; i++) {
            String name = enroll[i]; // 내 이름
            String refName = referral[i]; // 추천인 이름
            referralMap.put(name, refName);
            resultMap.put(name, 0);
        }

        // 모든 판매 이익 분배 시작
        for (int i = 0; i < seller.length; i++) {
            giveGain(seller[i], amount[i] * 100);
        }

        // 결과 배열에 저장
        int[] answer = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            String name = enroll[i];
            answer[i] = resultMap.get(name);
        }
        return answer;
    }

    // 이익 분배
    // name : 누구에게 amount : 얼마를
    public void giveGain(String name, int amount) {
        if (name.equals("-")) return; // 꼭대기

        // 자신의 순이익 계산해서 넣기
        int give = (int)(amount * 0.1);
        int realGain = amount - give;
        resultMap.put(name, resultMap.getOrDefault(name, 0) + realGain);

        // 추천인에게 이익 분배
        if (give < 1) return; // 이익 분배하지 않음
        giveGain(referralMap.get(name), give);
    }
} */