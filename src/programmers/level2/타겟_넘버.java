package programmers.level2;

public class 타겟_넘버 {
    int cnt = 0;
    int[] numbers;
    int target;

    public int solution(int[] numbers, int target) {
        this.numbers = numbers;
        this.target = target;
        dfs(0, 0);

        return cnt;
    }

    public void dfs(int sum, int depth) {

        if (depth == numbers.length) {
            if (sum == target) {
                cnt++;
            }
            return;
        }

        dfs(sum - numbers[depth], depth + 1);
        dfs(sum + numbers[depth], depth + 1);
    }
}
