package programmers.level2;

public class 이진_변환_반복하기 {
    public int[] solution(String s) {
        int[] answer = new int[2];

        while (!s.equals("1")) {
            answer[0] += 1;

            int countOne = 0;
            for (char c : s.toCharArray()) {
                if (c == '1') {
                    countOne += 1;
                    continue;
                }

                answer[1] += 1;
            }
            s = Integer.toBinaryString(countOne);
        }

        return answer;
    }
}
