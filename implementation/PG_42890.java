package programmers;
import java.util.*;

public class PG_42890 {
        public static void main(String[] args) {

            Solution solution = new Solution();
            int result = solution.solution(new String[][]{{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}});
            System.out.println("result = " + result);
        }
}

class Solution {

    public List<List<boolean[]>> combList = new ArrayList<>();
    public boolean[] visited;

    public int solution(String[][] relation) {

        // 초기화
        for (int i = 0; i <= relation[0].length; i++) {
            combList.add(new LinkedList<boolean[]>());
        }
        visited = new boolean[relation[0].length];
        int answer = 0;

        // 1개 ~ n개(속성 개수)짜리 모든 조합 생성
        combination(relation[0].length, 0, 0);

        // 각 조합들이 유일성 만족하는지 확인
        // 만족한다면 카운팅하고, 최소성을 만족하기 위해 그 조합을 포함하는 조합을 제거
        for (int i = 1; i <= relation[0].length; i++) {
            for (int j = 0; j < combList.get(i).size(); j++) {

                // 조합 boolean[] -> List로
                List<Integer> list = new ArrayList<>();
                for (int k = 0; k < relation[0].length; k++) {
                    if (combList.get(i).get(j)[k]) list.add(k);
                }

                // 유일성 만족하는지 확인
                // 만족 -> 카운팅, 포함 조합 제거
                if (isUniqueKey(relation, list)) {
                    answer++;
                    removeKeys(list);
                }
            }
        }

        return answer;
    }

    // n개 중에 1 ~ n개를 뽑는 조합
    public void combination(int n, int at, int cnt) {

        // 조합 저장
        boolean[] temp = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (visited[i]) temp[i] = true;
        }
        combList.get(cnt).add(temp);

        // 재귀
        for (int i = at; i < n; i++) {
            visited[i] = true;
            combination(n, i + 1, cnt + 1);
            visited[i] = false;
        }
    }

    public boolean isUniqueKey(String[][] relation, List<Integer> list) {

        // 모든 튜플에 대해 조합된 값 set에 저장
        Set<String> set = new HashSet<>();
        for (int i = 0; i < relation.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (Integer j : list) {
                sb.append(relation[i][j]);
            }
            set.add(sb.toString());
        }

        // 현재 속성 조합이 유일성 만족하는지 확인
        if (set.size() == relation.length) {
            return true;
        }
        return false;
    }

    public void removeKeys(List<Integer> list) {
        for (int j = list.size() + 1; j < combList.size(); j++) {
            List<boolean[]> comb = combList.get(j);
            for (int i = 0; i < comb.size(); i++) {
                boolean[] arr = comb.get(i); // 현재 조합
                boolean removed = true; // 지워야 할 조합에 해당하는지 여부
                for (Integer n : list) {
                    if (arr[n] == false) {
                        removed = false;
                        break;
                    }
                }

                if (removed) {
                    comb.remove(i);
                    i--;
                }
            }
        }
    }
}