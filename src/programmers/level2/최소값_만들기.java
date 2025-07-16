package programmers.level2;

import java.util.Arrays;

public class 최소값_만들기 {
    public int solution(int[] A, int[] B) {

        int result = 0;

        Arrays.sort(A);
        Arrays.sort(B);

        for (int i = 0; i < A.length; i++) {
            result += A[i] * B[(B.length - 1) - i];
        }

        return result;
    }
}
