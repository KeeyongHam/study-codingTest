package programmers.level2;

public class 피보나치_수 {
    public int solution(int n) {

        int[] fiboNumbers = getFiboNumbers(n);

        return fiboNumbers[n];
    }

    private int[] getFiboNumbers(int n) {
        int[] fiboNumbers = new int[n + 1];
        fiboNumbers[0] = 0;
        fiboNumbers[1] = 1;
        for (int i = 2; i < fiboNumbers.length; i++) {
            fiboNumbers[i] = (fiboNumbers[i - 1] + fiboNumbers[i - 2]) % 1234567;
        }

        return fiboNumbers;
    }
}
