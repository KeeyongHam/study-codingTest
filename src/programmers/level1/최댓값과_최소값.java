package programmers.level1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class 최댓값과_최소값 {
    public String solution(String s) {
        List<Integer> numbers = Arrays.stream(s.split(" "))
                .map(Integer::valueOf)
                .sorted()
                .collect(Collectors.toList());

        return numbers.get(0) + " " + numbers.get(numbers.size() - 1);
    }
}
