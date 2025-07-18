package programmers.level2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class 기능개발 {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> daysQueue = createDaysQueue(progresses, speeds);
        return getResult(daysQueue);
    }

    private Queue<Integer> createDaysQueue(int[] progresses, int[] speeds) {
        Queue<Integer> daysQueue = new ArrayDeque<>();
        for (int i = 0; i < progresses.length; i++) {
            int remaining = 100 - progresses[i];
            int days = (int) Math.ceil((double) remaining / speeds[i]);
            daysQueue.add(days);
        }
        return daysQueue;
    }

    private int[] getResult(Queue<Integer> daysQueue) {
        List<Integer> result = new ArrayList<>();
        int deployCount = 1;
        int current = daysQueue.poll();

        while (!daysQueue.isEmpty()) {
            Integer nextDay = daysQueue.poll();

            if (current >= nextDay) {
                deployCount++;
            } else {
                current = nextDay;
                result.add(deployCount);
                deployCount = 1;
            }
        }
        result.add(deployCount);

        return result.stream()
                .mapToInt(i -> i)
                .toArray();
    }
}
