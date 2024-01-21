package programmers;
import java.util.*;

public class PG_258707 {
        public static void main(String[] args) {

            Solution solution = new Solution();
            int result = solution.solution(4, new int[]{3, 6, 7, 2, 1, 10, 5, 9, 8, 12, 11, 4});
            System.out.println(result);
        }
}

class Solution {

    public Queue<Integer> cardsQ = new LinkedList<>(); // 모든 카드
    public boolean[] inCards = new boolean[1001]; // 들어온 카드
    public boolean[] myCards = new boolean[1001]; // 내 카드

    public int solution(int coin, int[] cards) {

        int answer = 0;
        int target = cards.length + 1;

        // 카드 초기화
        for (int i = 0; i < cards.length / 3; i++) {
            myCards[cards[i]] = true;
        }
        for (int i = cards.length / 3; i < cards.length; i++) {
            cardsQ.add(cards[i]);
        }

        // 카드 게임 시작
        while (true) {
            // 라운드 증가
            answer++;

            // 카드 뭉치에 카드가 없다면 종료
            if (cardsQ.isEmpty()) break;

            // 카드 두개 뽑기
            inCards[cardsQ.poll()] = true;
            inCards[cardsQ.poll()] = true;

            boolean check = false;

            // 1. 지금 있는 카드로 다음 라운드 이동
            for (int i = 1; i <= target / 2; i++) {
                if (myCards[i] == true && myCards[target - i] == true) {
                    myCards[i] = false;
                    myCards[target - i] = false;
                    check = true;
                    break;
                }
            }
            if (check == true) continue;

            // 2. 지금 있는 카드 + 들어온 카드로 다음 라운드 이동
            for (int i = 1; i <= target / 2; i++) {
                if (myCards[i] == true && inCards[target - i] == true) {
                    myCards[i] = false;
                    inCards[target - i] = false;
                    coin--;
                    check = true;
                    break;
                }
                if (inCards[i] == true && myCards[target - i] == true) {
                    inCards[i] = false;
                    myCards[target - i] = false;
                    coin--;
                    check = true;
                    break;
                }
            }
            if (check == true && coin >= 0) continue;

            // 3. 들어온 카드로 다음 라운드 이동
            for (int i = 1; i <= target / 2; i++) {
                if (inCards[i] == true && inCards[target - i] == true) {
                    inCards[i] = false;
                    inCards[target - i] = false;
                    coin -= 2;
                    check = true;
                    break;
                }
            }
            if (check == true && coin >= 0) continue;
            else break; // 카드를 낼 수 없다면 종료
        }

        return answer;
    }
}