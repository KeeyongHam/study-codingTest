## 🌵 문제 정보

- **문제명:** [숫자 변환하기](https://school.programmers.co.kr/learn/courses/30/lessons/154538)
- **난이도:** Lv.2
- **출처:** 프로그래머스

---

## 🌵 문제 설명

자연수 x를 y로 변환하려고 합니다. 사용할 수 있는 연산은 다음과 같습니다.

* x에 n을 더합니다
* x에 2를 곱합니다.
* x에 3을 곱합니다.

자연수 x, y, n이 매개변수로 주어질 때, x를 y로 변환하기 위해 필요한 최소 연산 횟수를 return하도록 solution 함수를 완성해주세요.
이때 x를 y로 만들 수 없다면 -1을 return 해주세요.

**제한사항**
1 ≤ x ≤ y ≤ 1,000,000
1 ≤ n < y

**제한사항**
1 ≤ x ≤ y ≤ 1,000,000
1 ≤ n < y

---

## 🌵 알고리즘 설계

* `queue`를 활용하여 bfs 구현한다.

1. BFS를 구현할 큐와, 특정 숫자를 이미 만든 적이 있는지를 확인할 `visited`을 생성한다.
2. 시작 숫자인 x를 큐에 넣고, `visited`에 추가한다.
3. 큐가 비어 있지 않은 동안 다음을 반복한다.
    * 현재 큐의 크기만큼 반복하며, 같은 깊이(BFS 레벨)에 있는 숫자들을 처리한다.
    * 큐에서 하나의 숫자를 꺼낸다.
    * 꺼낸 숫자가 목표 숫자 y와 같다면, 현재 카운트를 정답으로 반환한다.
    * 아니라면, +n, *2, *3 연산을 통해 만들 수 있는 숫자들을 생성한다.
    * 생성된 숫자가 y 이하이고, 아직 방문하지 않았다면 큐에 추가하고 visited에 기록한다.
    * 현재 레벨 처리가 끝나면 카운터를 1 증가시킨다.
    * 큐가 모두 비었음에도 y에 도달하지 못했다면, -1을 반환한다.

---

## 🌵 코드

```java
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
```