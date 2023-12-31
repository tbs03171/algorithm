package programmers;
import java.util.*;

public class PG_64064 {
        public static void main(String[] args) {

            Solution solution = new Solution();
            int result = solution.solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"fr*d*", "abc1**"});
            System.out.println("result = " + result);
        }
}

class Solution {

    Set<Integer> resultSet = new HashSet<>();

    public int solution(String[] user_id, String[] banned_id) {

        int answer = 0;
        int banLen = banned_id.length, userLen = user_id.length; // user_id, banned_id 길이
        boolean[][] map = new boolean[banLen][userLen];

        // banned_id에 해당하는 user_id 체크
        for (int i = 0; i < banLen; i++) {
            for (int j = 0; j < userLen; j++) {
                if (match(banned_id[i], user_id[j])) map[i][j] = true;
            }
        }

        // dfs
        dfs(banLen, userLen, map, new boolean[userLen], 0);
        return resultSet.size();
    }

    // b가 a(금지된 id)에 매치되는지
    public boolean match(String a, String b) {
        if (a.length() != b.length()) return false;
        int len = a.length();
        for (int i = 0; i < len; i++) {
            if (a.charAt(i) != '*' && a.charAt(i) != b.charAt(i)) return false;
        }
        return true;
    }

    public void dfs(int banLen, int userLen, boolean[][] map, boolean[] visited, int index) {
        // 조합 완성
        if (index >= banLen) {
            int result = 0;
            for (int i = 0; i < userLen; i++)
                if (visited[i]) result += 1 << i;
            resultSet.add(result);
            return;
        }

        int count = 0;
        for (int j = 0; j < userLen; j++) {
            if (map[index][j] && !visited[j]) {
                visited[j] = true;
                dfs(banLen, userLen, map, visited, index + 1);
                visited[j] = false;
            }
        }
    }
}

/* 나의 풀이
class Solution {

    List<List<Integer>> list = new ArrayList<>(); // <bannedId, userId List>
    boolean[] checked; // 카운팅한 조합인지 저장
    int count = 0;

    public int solution(String[] user_id, String[] banned_id) {

        // 초기화
        checked = new boolean[1000];

        // 모든 bannedId 에 대해 해당되는 userId 목록 생성
        for (int i = 0; i < banned_id.length; i++) {
            // 모든 userId 에 대해 검사 수행
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < user_id.length; j++) {
                if (isBanned(user_id[j], banned_id[i])) temp.add(j);
            }
            list.add(temp);
        }

        // 조합 카운팅
        combination(0, banned_id.length, 0);

        return count;
    }

    // userId가 bannedId와 일치하는지 리턴
    public boolean isBanned(String userId, String bannedId) {
        // 길이가 다르다면 일치하지 않음
        if (userId.length() != bannedId.length()) return false;

        // 일치하는지 확인
        for (int i = 0; i < userId.length(); i++) {
            if (bannedId.charAt(i) == '*' || bannedId.charAt(i) == userId.charAt(i)) continue;
            else return false;
        }

        return true;
    }

    public void combination(int at, int n, int bitmask) {
        // 끝까지 다 뽑았으면 카운팅
        if (at == n) {
            // 이 조합을 카운팅 했었는지 체크
            if (!checked[bitmask]) {
                checked[bitmask] = true;
                count++;
            }
            return;
        }

        // 계속 조합 생성
        for (int i = 0; i < list.get(at).size(); i++) {
            int id = list.get(at).get(i); // 현재 아이디
            if ((bitmask & (1 << id)) != 0) continue; // 이미 선택함
            combination(at + 1, n, (bitmask | 1 << id)); // 선택 안함
        }
    }
} */