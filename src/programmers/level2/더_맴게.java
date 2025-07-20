package programmers.level2;

import java.util.PriorityQueue;
import java.util.Queue;

public class 더_맴게 {
    public int solution(int[] scoville, int K) {
        Queue<Integer> queue = new PriorityQueue<>();

        for (int i : scoville) {
            queue.add(i);
        }

        int cnt = 0;
        while (queue.peek() < K && queue.size() >= 2) {
            Integer first = queue.poll();
            Integer second = queue.poll();

            int newFood = (second * 2) + first;
            queue.offer(newFood);

            cnt++;
        }

        if (queue.peek() < K) {
            return -1;
        }

        return cnt;
    }
}
