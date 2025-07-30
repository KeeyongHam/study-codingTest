package programmers.level2;

public class 마법의_엘리베이터 {
    public int solution(int storey) {

        int cnt = 0;
        while (storey != 0) {
            int num = storey % 10;
            int nextNum = (storey / 10) % 10;

            if (num < 5) {
                cnt += num;
            }

            if (num > 5) {
                cnt += 10 - num;
                storey += 10 - num;
            }

            if (num == 5) {
                if (nextNum < 5) {
                    cnt += num;
                } else {
                    cnt += 10 - num;
                    storey += 10 - num;
                }
            }

            storey /= 10;
        }

        return cnt;
    }
}
