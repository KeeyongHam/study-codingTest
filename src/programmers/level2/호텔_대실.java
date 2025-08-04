package programmers.level2;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class 호텔_대실 {
    public int solution(String[][] book_time) {
        Queue<LocalTime> endTimeQueue = new PriorityQueue<>();

        Arrays.sort(book_time, Comparator.comparing(o -> o[0]));
        for (String[] bookTime : book_time) {
            LocalTime startTime = LocalTime.parse(bookTime[0]);
            LocalTime endTime = LocalTime.parse(bookTime[1]);

            if (endTime.isBefore(LocalTime.of(23, 50))) {
                endTime = endTime.plusMinutes(10);
            } else {
                endTime = LocalTime.of(23, 59);
            }

            if (!endTimeQueue.isEmpty() && !endTimeQueue.peek().isAfter(startTime)) {
                endTimeQueue.poll();
            }

            endTimeQueue.offer(endTime);
        }

        return endTimeQueue.size();
    }
}
