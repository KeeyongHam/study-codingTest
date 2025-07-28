package programmers.level2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class 가장_큰_수 {
    public String solution(int[] numbers) {

        List<String> strNumbers = Arrays.stream(numbers)
                .mapToObj(String::valueOf)
                .sorted((o1, o2) -> ((o1 + o2).compareTo(o2 + o1)) * -1)
                .collect(Collectors.toList());

        if (strNumbers.get(0).equals("0")) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();

        strNumbers.forEach(sb::append);

        return sb.toString();
    }
}
