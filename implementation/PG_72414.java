import java.util.*;

class Solution {

    public String solution(String play_time, String adv_time, String[] logs) {

        // 최대 356999까지 가능
        int[] time = new int[100 * 60 * 60];
        int play = getSecond(play_time);
        int adv = getSecond(adv_time);

        for (String log : logs) {
            int s = getSecond(log.split("-")[0]);
            int e = getSecond(log.split("-")[1]);

            for (int i = s; i < e; i++)
                time[i] += 1;
        }

        long cur = 0;
        for (int i = 0; i < adv; i++) {
            cur += time[i];
        }

        // 슬라이딩 윈도우
        long max = cur;
        int ret = 0;
        for (int i = adv; i < play; i++) {
            cur = cur + time[i] - time[i - adv];
            if (cur > max) {
                max = cur;
                ret = i - adv + 1;
            }
        }
        return String.format("%02d:%02d:%02d", ret / (60 * 60), (ret / 60) % 60, ret % 60);
    }

    public int getSecond(String s) {
        return Integer.parseInt(s.split(":")[0]) * 3600
                + Integer.parseInt(s.split(":")[1]) * 60
                + Integer.parseInt(s.split(":")[2]);
    }
}

/* 나의 풀이
class Solution {
    
    public String solution(String play_time, String adv_time, String[] logs) {
        
        // 변수 초기화
        String[] arr;
        int h, m, s;
        long max = 0L;
        int result = 0;
        
        // play_time, adv_time 추출
        arr = play_time.split(":");
        int play_time_h = Integer.parseInt(arr[0]);
        int play_time_m = Integer.parseInt(arr[1]);
        int play_time_s = Integer.parseInt(arr[2]);
        int play_time_total = play_time_h * 3600 + play_time_m * 60 + play_time_s - 1;
        arr = adv_time.split(":");
        int adv_time_h = Integer.parseInt(arr[0]);
        int adv_time_m = Integer.parseInt(arr[1]);
        int adv_time_s = Integer.parseInt(arr[2]);
        int adv_time_total = adv_time_h * 3600 + adv_time_m * 60 + adv_time_s - 1;
        
        
        // 재생 시간 배열 초기화
        int[] playLogs = new int[play_time_total + 2];
        
        // 사용자 재생 기록 배열에 담기
        for (String log : logs) {
            String[] times = log.split("-");
            
            // 시작 시간 추출해서 시작 부분 +1
            arr = times[0].split(":");
            h = Integer.parseInt(arr[0]);
            m = Integer.parseInt(arr[1]);
            s = Integer.parseInt(arr[2]);
            playLogs[h * 3600 + m * 60 + s] += 1;
            
            // 종료 시간 추출해서 종료 부분 -1
            arr = times[1].split(":");
            h = Integer.parseInt(arr[0]);
            m = Integer.parseInt(arr[1]);
            s = Integer.parseInt(arr[2]);
            playLogs[h * 3600 + m * 60 + s] -= 1;
        }
        
        // 배열 누적합
        for (int i = 1; i < playLogs.length; i++) {
            playLogs[i] = playLogs[i] + playLogs[i - 1];
        }
        
        // 누적 재생 시간 구하기
        long sum = 0L; // 초기화 (0 ~ 광고 시간 만큼)
        for (int i = 0; i < adv_time_total; i++) {
            sum = sum + playLogs[i];
        }
        max = sum;
        result = 0;
        for (int i = 1; i + adv_time_total <= play_time_total; i++) {
            sum = sum - playLogs[i - 1] + playLogs[i + adv_time_total];
            if (sum > max) { // 크면 새로운 값 갱신
                max = sum;
                result = i;
            }
        }
        
        // 결과 String으로 변환 후 리턴
        String result_s = (result % 60 < 10) ? "0" + (result % 60) : "" + (result % 60);
        String result_m = ((result / 60) % 60 < 10) ? ("0" + ((result / 60) % 60)) : ("" + ((result / 60) % 60));
        String result_h = (result / 3600 < 10) ? ("0" + (result / 3600)) : ("" + (result / 3600));
        String answer = result_h + ":" + result_m + ":" + result_s;
        return answer;
    }
} */