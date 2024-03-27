package programmers;

public class PG_17676 {
        public static void main(String[] args) {

            Solution solution = new Solution();
            int result = solution.solution(new String[]{"2016-09-15 20:59:57.421 0.351s",
                    "2016-09-15 20:59:58.233 1.181s",
                    "2016-09-15 20:59:58.299 0.8s",
                    "2016-09-15 20:59:58.688 1.041s",
                    "2016-09-15 20:59:59.591 1.412s",
                    "2016-09-15 21:00:00.464 1.466s",
                    "2016-09-15 21:00:00.741 1.581s",
                    "2016-09-15 21:00:00.748 2.31s",
                    "2016-09-15 21:00:00.966 0.381s",
                    "2016-09-15 21:00:02.066 2.62s"});
            System.out.println("result = " + result);
        }
}

class Solution {

    public int solution(String[] lines) {

        int startTable[] = new int[24*60*60*1000];
        int endTable[] = new int[24*60*60*1000];

        for (String time : lines) {
            int endTime = 0;
            String[] times = time.split(" ");

            // 응답완료시각 파싱
            String[] HMS = times[1].split(":");
            endTime += Integer.parseInt(HMS[0]) * 60 * 60 * 1000; // H
            endTime += Integer.parseInt(HMS[1]) * 60 * 1000; // M
            String[] ms = HMS[2].split("\\.");
            endTime += Integer.parseInt(ms[0]) * 1000; // S
            endTime += Integer.parseInt(ms[1]);

            // 처리시간 파싱
            int sec = (int)(Double.parseDouble(times[2].split("s")[0]) * 1000);

            // 시작시각, 끝 시각 저장
            int startTime = endTime - sec + 1;
            if (startTime < 0) startTime = 0;
            startTable[startTime]++;
            endTable[endTime]++;
        }

        int cnt = 0;
        int max = 0;
        // 초기화 (0~999ms 까지의 처리량 카운팅)
        for (int i = 0; i < 1000; i++) {
            cnt += startTable[i];
            max = cnt;
        }

        // 슬라이딩 윈도우 (1ms ~ 1000ms, 2ms ~ 1001ms, ...)
        for (int i = 1000; i < 24*60*60*1000; i++) {
            cnt += startTable[i];
            cnt -= endTable[i - 1000];
            max = Math.max(max, cnt);
        }

        return max;
    }
}

/* 나의 풀이
class Solution {

    // 구간 리스트 (첫 시각, 끝 시각)
    public List<BigDecimal[]> list = new ArrayList<>();

    public int solution(String[] lines) {

//         로그 데이터 분석 -> 초당 최대 처리량 계산
//         임의 시간부터 1초(1000ms)간 처리하는 요청의 최대 개수

//         lines : N개의 로그 문자열, 응답완료시간 S, 처리시간 T
//         S : 2016-09-15 hh:mm:ss.sss
//         T : 0.xxxs (처리시간은 시작시각과 끝시각 포함)

//         return lines의 초당 최대 처리량

//         **
//         N : 1 ~ 2000
//         T : 0.001 ~ 3.000
//         lines는 S를 기준으로 오름차순 정렬

//         1. 모든 시간을 Decimal로 변환하고, (시작 시각, 끝 시각) 형태로 저장
//         2. 처음부터 읽으면서.. 모든 항목에 대해
//             해당 항목의 끝 시각 + 0.999초 >= 뒷 항목의 시작 시각이면 처리량++

        // 모든 시각 Decimal로 변환
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            String[] splits = line.split(" ");
            String strS = splits[1];
            String strT = splits[2];

            // 응답완료시각 S 변환 (몇 초인지)
            splits = strS.split(":");
            BigDecimal S1 = new BigDecimal(splits[0]);
            S1 = S1.multiply(new BigDecimal("3600"));
            BigDecimal S2 = new BigDecimal(splits[1]);
            S2 = S2.multiply(new BigDecimal("60"));
            BigDecimal S3 = new BigDecimal(splits[2]);
            BigDecimal S = S1.add(S2).add(S3);

            // 처리시간 T 변환
            BigDecimal T = new BigDecimal(strT.replace("s", ""));

            // (첫 시각, 끝 시각) 형태로 리스트에 저장
            BigDecimal startTime = S.subtract(T).add(new BigDecimal("0.001"));
            list.add(new BigDecimal[]{startTime, S});
        }

        // 첫번째 항목부터 before (그 항목의 끝 시각 + 0.999s) 안에 들어오는 항목이 몇개인지 카운트
        int answer = 0; // 최대 처리량
        for (int i = 0; i < list.size(); i++) {
            BigDecimal before = list.get(i)[1].add(new BigDecimal("0.999"));
            int size = 1;
            // before 안에 들어오는 항목이 몇개인지 카운트
            for (int j = i + 1; j < list.size(); j++) {
                // 항목 안에 들어온다면 size++
                BigDecimal now = list.get(j)[0];
                if (before.compareTo(now) >= 0) size++;
            }
            answer = Math.max(size, answer);
        }

        return answer;
    }
} */