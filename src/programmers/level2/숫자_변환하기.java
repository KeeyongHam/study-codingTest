package programmers.level2;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class 숫자_변환하기 {
    public int solution(int x, int y, int n) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new ArrayDeque<>();

        queue.offer(x);
        visited.add(x);

        int cnt = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int cur = queue.poll();

                if (cur == y) {
                    return cnt;
                }

                for (int next : new int[]{cur + n, cur * 2, cur * 3}) {
                    if (next <= y && visited.add(next)) {
                        queue.offer(next);
                    }
                }
            }

            cnt++;
        }

        return -1;
    }
}
