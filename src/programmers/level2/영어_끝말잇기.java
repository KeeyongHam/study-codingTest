package programmers.level2;

import java.util.HashSet;

public class 영어_끝말잇기 {
    public int[] solution(int n, String[] words) {

        int[] result = new int[]{0, 0};

        HashSet<String> set = new HashSet<>();
        set.add(words[0]);

        for (int i = 1; i < words.length; i++) {

            String beforeWord = words[i - 1];
            String currentWord = words[i];

            if (beforeWord.charAt(beforeWord.length() - 1) != currentWord.charAt(0) || !set.add(words[i])) {
                result[0] = (i % n) + 1;
                result[1] = (i / n) + 1;
                break;
            }
        }

        return result;
    }
}
