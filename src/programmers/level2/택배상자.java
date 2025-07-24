package programmers.level2;

import java.util.ArrayDeque;
import java.util.Queue;

public class 택배상자 {
    public int solution(int[] order) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        Queue<Integer> queue = createMainContainer(order);

        int cnt = 0;
        for (int i = 1; i <= order.length; i++) {
            if (queue.peek().equals(i)) {
                cnt++;
                queue.poll();
                continue;
            }

            cnt += unloadStackIfMatch(stack, queue);
            stack.push(i);
        }

        cnt += unloadStackIfMatch(stack, queue);

        return cnt;
    }

    private Queue<Integer> createMainContainer(int[] order) {
        Queue<Integer> queue = new ArrayDeque<>();
        for (int o : order) {
            queue.add(o);
        }
        return queue;
    }

    private Integer unloadStackIfMatch(ArrayDeque<Integer> stack, Queue<Integer> queue) {
        int count = 0;
        while (!stack.isEmpty()) {
            if (stack.peek().equals(queue.peek())) {
                stack.pop();
                queue.poll();
                count++;
                continue;
            }

            break;
        }
        return count;
    }
}
