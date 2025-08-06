## 🌵 문제 정보

- **문제명:** [다리를 지나는 트럭](hhttps://school.programmers.co.kr/learn/courses/30/lessons/42583)
- **난이도:** Lv.2
- **출처:** 프로그래머스

---

## 🌵 문제 설명

트럭 여러 대가 강을 가로지르는 일차선 다리를 정해진 순으로 건너려 합니다. 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 알아내야 합니다. 다리에는 트럭이 최대 bridge_length대 올라갈 수 있으며,
다리는 weight 이하까지의 무게를 견딜 수 있습니다. 단, 다리에 완전히 오르지 않은 트럭의 무게는 무시합니다.

예를 들어, 트럭 2대가 올라갈 수 있고 무게를 10kg까지 견디는 다리가 있습니다. 무게가 [7, 4, 5, 6]kg인 트럭이 순서대로 최단 시간 안에 다리를 건너려면 다음과 같이 건너야 합니다.

| 경과 시간 | 다리를 지난 트럭 | 다리를 건너는 트럭 | 대기 트럭     |
|-------|-----------|------------|-----------|
| 0     | []        | []         | [7,4,5,6] |
| 1~2   | []        | [7]        | [4,5,6]   |
| 3     | [7]       | [4]        | [5,6]     |
| 4     | [7]       | [4,5]      | [6]       |
| 5     | [7,4]     | [5]        | [6]       |
| 6~7   | [7,4,5]   | [6]        | []        |
| 8     | [7,4,5,6] | []         | []        |

따라서, 모든 트럭이 다리를 지나려면 최소 8초가 걸립니다.

solution 함수의 매개변수로 다리에 올라갈 수 있는 트럭 수 bridge_length, 다리가 견딜 수 있는 무게 weight, 트럭 별 무게 truck_weights가 주어집니다. 이때 모든 트럭이 다리를
건너려면 최소 몇 초가 걸리는지 return 하도록 solution 함수를 완성하세요.

**제한 사항**

* bridge_length는 1 이상 10,000 이하입니다.
* weight는 1 이상 10,000 이하입니다.
* truck_weights의 길이는 1 이상 10,000 이하입니다.
* 모든 트럭의 무게는 1 이상 weight 이하입니다.

### 🔸 입출력 예

| bridge_length | weight | truck_weights                   | return |
|---------------|--------|---------------------------------|--------|
| 2             | 10     | [7,4,5,6]                       | 8      |
| 1             | 100    | [10]                            | 101    |
| 1             | 100    | [10,10,10,10,10,10,10,10,10,10] | 110    |

---

## 🌵 알고리즘 설계

* 다리를 `Queue`로 표현하여, 각 시점에서 다리 위에 있는 트럭을 관리한다.

1. 다리 길이에 맞춰 초기 큐를 설정한다.
    * 아직 트럭이 올라오기 전에는 0으로 채운다.
2. 첫 번째 트럭을 다리에 올린다.
    * 현재 무게를 갱신한다.
3. 시간을 1로 초기화하고, 현재 트럭 인덱스를 1로 설정한다.
4. 큐가 빌 때까지 반복한다.
    * 한 칸 전진시키기 위해 큐의 맨 앞을 꺼내고, 그만큼 현재 무게를 줄인다.
    * 시간을 1 증가시킨다.
    * 모든 트럭을 다 올린 경우(`idx >= truck_weights.length`)라면, 다음 반복으로 넘어간다.
    * 다리에 다음 트럭을 올릴 수 있는지 확인한다.
      * `curWeight + 다음 트럭 무게 <= weight`이면 트럭을 올린다.
      * 현재 무게 갱신 후 인덱스 증가.
      * 그렇지 않으면 0을 올린다.

5. 큐가 빌 때까지 이 과정을 반복하면, `result`가 모든 트럭이 다리를 건너는 데 걸린 시간이다.
6. `result`를 반환한다.

---

## 🌵 코드

```java
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
```