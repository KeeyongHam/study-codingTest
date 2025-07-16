package programmers.level2;

public class 카펫 {
    public int[] solution(int brown, int yellow) {
        int area = brown + yellow;

        for (int h = 3; h < area; h++) {
            if (area % h != 0) {
                continue;
            }

            int w = area / h;

            if (h > w) {
                continue;
            }

            if ((w - 2) * (h - 2) == yellow) {
                return new int[]{w, h};
            }
        }

        return null;
    }
}
