package programmers.level2;

import java.util.Arrays;

public class 구명보트 {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        int left = 0;
        int right = people.length - 1;

        int cnt = 0;
        while (left <= right) {
            if (people[left] + people[right] <= limit) {
                left++;
                right--;
            } else {
                right--;
            }

            cnt++;
        }

        return cnt;
    }
}
