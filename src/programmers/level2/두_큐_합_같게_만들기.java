package programmers.level2;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class 두_큐_합_같게_만들기 {
    public int solution(int[] queue1, int[] queue2) {

        long queueOneSum = 0;
        long queueTwoSum = 0;

        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new LinkedList<>();
        for (int i = 0; i < queue1.length; i++) {
            q1.offer(queue1[i]);
            q2.offer(queue2[i]);

            queueOneSum += queue1[i];
            queueTwoSum += queue2[i];
        }

        if ((queueOneSum + queueTwoSum) % 2 == 1) {
            return -1;
        }

        int cnt = 0;
        while (queueOneSum != queueTwoSum) {

            if (cnt == queue1.length * 3) {
                return -1;
            }

            if (queueOneSum > queueTwoSum) {
                Integer num = q1.poll();
                q2.offer(num);

                queueOneSum -= num;
                queueTwoSum += num;
            } else {
                Integer num = q2.poll();
                q1.offer(num);

                queueTwoSum -= num;
                queueOneSum += num;
            }

            cnt++;
        }

        return cnt;
    }
}
