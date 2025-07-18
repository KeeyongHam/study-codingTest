package programmers.level2;

import java.util.ArrayList;
import java.util.HashSet;

public class 연속_부분_수열_합의_개수 {
    public int solution(int[] elements) {

        HashSet<Integer> set = new HashSet<>();
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            for (int element : elements) {
                list.add(element);
            }
        }

        for (int i = 0; i < elements.length; i++) {
            for (int j = 0; j < elements.length; j++) {
                set.add(list.subList(i, i + j).stream()
                        .mapToInt(num -> num)
                        .sum());
            }
        }

        return set.size();
    }
}
