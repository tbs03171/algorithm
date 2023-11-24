import java.util.*;
import java.time.*;

class Solution {

    public int[] solution(String today, String[] terms, String[] privacies) {
        int[] answer = {};
        Map<String, String> termsMap = new HashMap<>(); // key : 종류, value : 기간
        for (String term : terms) {
            String[] termSplit = term.split(" ");
            termsMap.put(termSplit[0], termSplit[1]);
        }

        Integer number = 1; // privacies의 번호
        List<Integer> result = new ArrayList<>();

        // 현재 총 날짜 수
        Integer todayTotalDate = getTotalDate(today, 0);

        for (String p : privacies) {
            String[] privateSplit = p.split(" ");
            // 개인별 날짜
            String privateDate = privateSplit[0];
            // 개인별 약관 정보
            String privateTerm = privateSplit[1];
            // 약관 개월 수
            Integer termsMonth = Integer.valueOf(termsMap.get(privateTerm));

            // 기간 경과 후 총 날짜 수
            Integer privateTotalDate = getTotalDate(privateDate, termsMonth) - 1; // 기간이므로 -1
            // 기간 경과 후 날짜가 현재 날짜보다 과거이면 폐기대상
            if (privateTotalDate < todayTotalDate) { // 현재 당일은 아직 폐기대상 아님
                result.add(number); // 유효기간 경과하여 폐기대상인 번호 추가
            }
            number++; // privacies의 번호 +1
        }

        answer = result.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }

    // (yyyy.MM.dd)을 총 날짜 수로 환산
    private Integer getTotalDate(String strDate, Integer termsMonth) {
        // 날짜 정보
        String[] dateSplit = strDate.split("\\.");
        Integer year = Integer.valueOf(dateSplit[0]);
        Integer month = Integer.valueOf(dateSplit[1]) + termsMonth;
        Integer day = Integer.valueOf(dateSplit[2]);

        // 모두 일 수로 환산, 한 달은 28일
        return (year * 12 * 28) + (month * 28) + day;
    }
}

/* LocalDate 활용
class Solution {

    public int[] solution(String today, String[] terms, String[] privacies) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate todayDate = LocalDate.parse(today, dtf);

        int[] termsAll = new int[26];
        for (String term : terms) {
            String[] ts = term.split(" ");
            termsAll[ts[0].charAt(0) - 'A'] = Integer.parseInt(ts[1]);
        }

        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < privacies.length; i++) {
            String[] privacy = privacies[i].split(" ");
            LocalDate privacyDate = LocalDate.parse(privacy[0], dtf).plusMonths(termsAll[privacy[1].charAt(0) - 'A']).minusDays(1);

            if (privacyDate.isBefore(todayDate)) {
                answer.add(i + 1);
            }
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}
*/

/* 나의 풀이
class Solution {

    // 약관 종류와 유효 기간 저장 (약관명, 유효기간)
    public Map<Character, Integer> termsMap = new HashMap<>();

    // 오늘 년, 월, 일
    public int YEAR, MONTH, DAY;

    // 결과
    public ArrayList<Integer> result = new ArrayList<>();

    public int[] solution(String today, String[] terms, String[] privacies) {


        // 오늘 날짜 세팅
        String[] arr = today.split("\\.");
        YEAR = Integer.parseInt(arr[0]);
        MONTH = Integer.parseInt(arr[1]);
        DAY = Integer.parseInt(arr[2]);

        // terms를 map에 옮기기
        for (String term : terms) {
            String[] split = term.split(" ");
            termsMap.put(split[0].charAt(0), Integer.parseInt(split[1]));
        }

        // 모든 privacy에 대해
        for (int i = 0; i < privacies.length; i++) {
            String s = privacies[i];

            String[] privacy = s.split(" ");
            int term = termsMap.get(privacy[1].charAt(0)); // 유효 기간

            // 가입 일자 + 유효 기간 계산
            String[] date = privacy[0].split("\\.");
            int year = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            int day = Integer.parseInt(date[2]);

            month += term;
            if (month > 12) {
                year = year + (month / 12);
                month = month % 12;
                if (month == 0) {
                    month = 12;
                    year -= 1;
                }
            }

            // 오늘 날짜와 비교
            if (YEAR > year) {
                result.add(i + 1);
            }
            else if (YEAR == year) {
                if (MONTH > month) {
                    result.add(i + 1);
                } else if (MONTH == month) {
                    if (DAY > day) {
                        result.add(i + 1);
                    } else if (DAY == day) {
                        result.add(i + 1);
                    }
                }
            }
        }

        Collections.sort(result);
        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
} */