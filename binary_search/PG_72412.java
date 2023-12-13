package programmers;
import java.util.*;

public class PG_72412 {
        public static void main(String[] args) {
            int n = 3;
            int m = 4;
            int x = 2;
            int y = 3;
            int r = 3;
            int c = 1;
            int k = 5;

            Solution s = new Solution();
            int[] result = s.solution(new String[]{"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"}
                    , new String[]{"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"});
            for (int i : result) {
                System.out.println("i = " + i);
            }
        }
}

class Solution {

    Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();

    public int[] solution(String[] info, String[] query) {

        // 지원자 정보 추출해서 해당하는 조건에 코테 점수 넣기
        for (String i : info) {
            String[] iSplit = i.split(" ");
            comb(iSplit, 0);
        }

        // 각 조건에 해당하는 점수들 정렬
        for (String key : map.keySet()) Collections.sort(map.get(key));

        // 모든 쿼리에 대해 결과 조회
        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {

            // and 제거, - 제거 -> query key 뽑아냄
            String q = query[i];
            q = q.replace(" and", "");
            String[] qSplit = q.split(" ");
            String key = "";
            for (int j = 0; j < 4; j++) key += qSplit[j].equals("-") ? "" : qSplit[j];

            // 이진 탐색
            int score = Integer.parseInt(qSplit[4]);
            List<Integer> scoreList = map.getOrDefault(key, new ArrayList<Integer>());
            int s = 0;
            int l = scoreList.size();
            while (s < l) {
                int mid = (s + l) / 2;
                if (scoreList.get(mid) < score)
                    s = mid + 1;
                else l = mid;
            }
            answer[i] = scoreList.size() - s;
        }
        return answer;
    }

    // 해당 idx 포함 여부 (1010 -> language + "" + work + "")
    boolean selidx[] = new boolean[4];

    // 해당하는 조건 모두에 점수 넣기
    void comb(String[] iSplit, int idx) {
        if (idx == 4) {
            String s = "";
            for (int i = 0; i < 4; i++)
                s += selidx[i] ? iSplit[i] : "";
            map.put(s, map.getOrDefault(s, new ArrayList<Integer>()));
            map.get(s).add(Integer.parseInt(iSplit[4]));
            return;
        }

        selidx[idx] = true;
        comb(iSplit, idx + 1);
        selidx[idx] = false;
        comb(iSplit, idx + 1);
    }
}

/* 나의 풀이
class Solution {

    String[] typeLang = {"cpp", "java", "python", "-"};
    String[] typeWork = {"backend", "frontend", "-"};
    String[] typeCareer = {"junior", "senior", "-"};
    String[] typeFood = {"chicken", "pizza", "-"};
    Map<String, Set<Integer>> map = new HashMap<>();
    Map<String, List<Integer>> queryMap = new HashMap<>(); // <query 조건, 일치하는 사람 리스트>

    public int[] solution(String[] info, String[] query) {

        // query에 대한 결과 저장
        int[] answer = new int[query.length];

        // 지원자 id에 해당하는 코테 점수 저장
        int[] points = new int[info.length];

        // map 초기화
        map.put("cpp", new HashSet<Integer>());
        map.put("java", new HashSet<Integer>());
        map.put("python", new HashSet<Integer>());
        map.put("backend", new HashSet<Integer>());
        map.put("frontend", new HashSet<Integer>());
        map.put("junior", new HashSet<Integer>());
        map.put("senior", new HashSet<Integer>());
        map.put("chicken", new HashSet<Integer>());
        map.put("pizza", new HashSet<Integer>());
        map.put("-", new HashSet<Integer>());


        // 모든 지원자에 대해 해당 조건 Set에 넣기
        for (int i = 0; i < info.length; i++) {
            // 해당 지원자 조건 파싱
            String s = info[i];
            String[] infoArr = s.split(" ");
            String language = infoArr[0]; // 언어
            String work = infoArr[1]; // 직무
            String career = infoArr[2]; // 경력
            String food = infoArr[3]; // 푸드
            int point = Integer.parseInt(infoArr[4]); // 코테 점수

            // 코테 점수 저장
            points[i] = point;

            // 해당하는 조건 set에 id 저장
            map.get(language).add(i);
            map.get(work).add(i);
            map.get(career).add(i);
            map.get(food).add(i);
            map.get("-").add(i);
        }

        // 가능한 모든 쿼리에 대한 지원자 리스트 생성
        for (String lang : typeLang) {
            for (String work : typeWork) {
                for (String career : typeCareer) {
                    for (String food : typeFood) {

                        // 쿼리 생성 후 교집합 생성
                        StringBuilder sb = new StringBuilder();
                        sb.append(lang).append(work).append(career).append(food);
                        Set<Integer> intersaction = new HashSet<>(map.get(lang));
                        intersaction.retainAll(map.get(work));
                        intersaction.retainAll(map.get(career));
                        intersaction.retainAll(map.get(food));

                        // Set<지원자 id> -> List<코테 점수> 한 후 정렬
                        List<Integer> list = new ArrayList<Integer>(intersaction);
                        for (int i = 0; i < list.size(); i++) {
                            list.set(i, points[list.get(i)]);
                        }
                        Collections.sort(list);
                        queryMap.put(sb.toString(), list);
                    }
                }
            }
        }

        // 모든 쿼리에 대해
        for (int i = 0; i < query.length; i++) {
            // 쿼리 파싱 후 결과 조회
            String[] q = query[i].split(" ");
            int point = Integer.parseInt(q[7]);
            StringBuilder sb = new StringBuilder();
            sb.append(q[0]).append(q[2]).append(q[4]).append(q[6]);
            List<Integer> result = queryMap.get(sb.toString());

            // 이진 탐색
            int index = binarySearch(result, point);
            if (index == -1) continue; // point 이상인 경우 없음
            answer[i] = result.size() - index;

            // Collections.binarySearch() 사용
            // int index = Collections.binarySearch(result, point);
            // if (index < 0) index = -(index + 1);
            // // 중복 요소 처리
            // while (index > 0 && result.get(index - 1) == point) {
            //     index--;
            // }
            // answer[i] = result.size() - index;
        }

        return answer;
    }

    // target 이상인 값들 중 제일 빠른 인덱스
    public int binarySearch(List<Integer> list, int target) {
        int low = 0;
        int high = list.size() - 1;
        int result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int midValue = list.get(mid);

            if (midValue >= target) {
                result = mid; // target 이상인 값 -> 일단 결과 저장
                high = mid - 1; // 왼쪽 부분에 대해 계속 이진 탐색
            } else {
                low = mid + 1;
            }
        }

        return result;
    }
} */