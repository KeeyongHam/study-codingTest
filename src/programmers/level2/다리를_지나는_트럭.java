package programmers.level2;

import java.util.ArrayDeque;
import java.util.Queue;

public class 다리를_지나는_트럭 {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < bridge_length - 1; i++) {
            queue.offer(0);
        }
        queue.offer(truck_weights[0]);

        int result = 1;
        int idx = 1;
        int curWeight = truck_weights[0];
        while (!queue.isEmpty()) {
            curWeight -= queue.poll();
            result++;

            if (idx >= truck_weights.length) {
                continue;
            }

            if (curWeight + truck_weights[idx] <= weight) {
                queue.offer(truck_weights[idx]);
                curWeight += truck_weights[idx];
                idx++;
                continue;
            }

            queue.offer(0);
        }

        return result;
    }
}
