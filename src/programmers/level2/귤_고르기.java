package programmers.level2;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class 귤_고르기 {
    public int solution(int k, int[] tangerine) {
        Map<Integer, Integer> tangerineMap = new HashMap<>();

        for (int i : tangerine) {
            tangerineMap.put(i, tangerineMap.getOrDefault(i, 0) + 1);
        }

        int count = 0;
        int sum = 0;
        List<Integer> sortedValue = tangerineMap.values()
                .stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        for (Integer i : sortedValue) {

            count++;
            sum += i;

            if (sum >= k) {
                break;
            }
        }

        return count;
    }
}
