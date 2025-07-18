package programmers.level2;

public class 예상_대진표 {
    public int solution(int n, int a, int b) {

        int result = 0;
        while (a != b) {
            a = (int) Math.ceil((double) a / 2);
            b = (int) Math.ceil((double) b / 2);
            result++;
        }

        return result;
    }
}
