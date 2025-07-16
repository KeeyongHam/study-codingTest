package programmers.level2;

public class 다음_큰_숫자 {
    public int solution(int n) {
        int targetOneCount = Integer.bitCount(n);
        int nextNumber = n;

        do {
            nextNumber += 1;
        } while (targetOneCount != Integer.bitCount(nextNumber));

        return nextNumber;
    }
}
