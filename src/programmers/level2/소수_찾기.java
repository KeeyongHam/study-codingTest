package programmers.level2;

import java.util.HashSet;
import java.util.Set;

public class 소수_찾기 {
    Set<Integer> set = new HashSet<>();
    char[] charArr;
    boolean[] visited;

    public int solution(String numbers) {
        charArr = numbers.toCharArray();
        visited = new boolean[charArr.length];

        dfs("");

        return gerResult();
    }

    private void dfs(String strNum) {

        if (!strNum.isEmpty()) {
            set.add(Integer.valueOf(strNum));
        }

        for (int i = 0; i < charArr.length; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            dfs(strNum + charArr[i]);
            visited[i] = false;
        }
    }

    private int gerResult() {
        return (int)set.stream()
                .mapToInt(i -> i)
                .filter(this::isPrime)
                .count();
    }

    private boolean isPrime(Integer num) {
        if (num < 2) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
}
