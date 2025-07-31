## 🌵 문제 정보

- **문제명:** [전령망 둘로 나누기](https://school.programmers.co.kr/learn/courses/30/lessons/86971)
- **난이도:** Lv.2
- **출처:** 프로그래머스

---

## 🌵 문제 설명

n개의 송전탑이 전선을 통해 하나의 트리 형태로 연결되어 있습니다.
당신은 이 전선들 중 하나를 끊어서 현재의 전력망 네트워크를 2개로 분할하려고 합니다. 이때, 두 전력망이 갖게 되는 송전탑의 개수를 최대한 비슷하게 맞추고자 합니다.

송전탑의 개수 n, 그리고 전선 정보 wires가 매개변수로 주어집니다. 전선들 중 하나를 끊어서 송전탑 개수가 가능한 비슷하도록 두 전력망으로 나누었을 때,
두 전력망이 가지고 있는 송전탑 개수의 차이(절대값)를 return 하도록 solution 함수를 완성해주세요.

제한사항

* n은 2 이상 100 이하인 자연수입니다.
* wires는 길이가 n-1인 정수형 2차원 배열입니다.
    * wires의 각 원소는 [v1, v2] 2개의 자연수로 이루어져 있으며, 이는 전력망의 v1번 송전탑과 v2번 송전탑이 전선으로 연결되어 있다는 것을 의미합니다.
    * 1 ≤ v1 < v2 ≤ n 입니다.
    * 전력망 네트워크가 하나의 트리 형태가 아닌 경우는 입력으로 주어지지 않습니다.

### 🔸 입출력 예

| n | wires                                             | result |
|---|---------------------------------------------------|--------|
| 9 | [[1,3],[2,3],[3,4],[4,5],[4,6],[4,7],[7,8],[7,9]] | 3      |
| 4 | [[1,2],[2,3],[3,4]]                               | 0      |
| 7 | [[1,2],[2,7],[3,7],[3,4],[4,5],[6,7]]             | 1      |

**입출력 예 #1**

* 다음 그림은 주어진 입력을 해결하는 방법 중 하나를 나타낸 것입니다.
* <img src="https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/5b8a0dcd-cba0-47ca-b5e3-d3bafc81f9d6/ex1.png" width="300">
* 4번과 7번을 연결하는 전선을 끊으면 두 전력망은 각 6개와 3개의 송전탑을 가지며, 이보다 더 비슷한 개수로 전력망을 나눌 수 없습니다.
* 또 다른 방법으로는 3번과 4번을 연결하는 전선을 끊어도 최선의 정답을 도출할 수 있습니다.

**입출력 예 #2**

* 다음 그림은 주어진 입력을 해결하는 방법을 나타낸 것입니다.
* <img src="https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/b28865e1-a18e-429d-ae7a-14e77e801539/ex2.png" width="300">
* 2번과 3번을 연결하는 전선을 끊으면 두 전력망이 모두 2개의 송전탑을 가지게 되며, 이 방법이 최선입니다.

**입출력 예 #3**

* 다음 그림은 주어진 입력을 해결하는 방법을 나타낸 것입니다.
* <img src="https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/0a7f21af-1e07-4015-8ad3-c06155c613b3/ex3.png" width="300">
* 3번과 7번을 연결하는 전선을 끊으면 두 전력망이 각각 4개와 3개의 송전탑을 가지게 되며, 이 방법이 최선입니다.
---

## 🌵 알고리즘 설계

* 모든 전선을 하나씩 끊어보며 두 전력망으로 분리한 뒤, 각각의 송전탑 개수를 DFS를 이용해 탐색한다.

1. 양쪽 노드 간 연결을 끊는다.
2. 임의의 노드(1번 노드)부터 DFS를 수행하여 연결된 송전탑 수를 센다.
3. 전체 노드 수 n에서 DFS로 구한 수를 빼면 나머지 전력망의 송전탑 수가 된다.
4. 두 전력망의 송전탑 수 차이의 절댓값을 구해 최소값을 갱신한다. 
5. 끊었던 전선을 다시 복구하여 다음 경우를 테스트한다. 
6. 이 과정을 모든 전선에 대해 반복하며 최소 차이를 계산한다.

---

## 🌵 코드

```java
public class 전력망_둘로_나누기 {

    int result = Integer.MAX_VALUE;
    private List<Integer>[] graph;

    public int solution(int n, int[][] wires) {

        initGraph(n, wires);

        for (int[] wire : wires) {
            int v1 = wire[0];
            int v2 = wire[1];

            graph[v1].remove(Integer.valueOf(v2));
            graph[v2].remove(Integer.valueOf(v1));

            boolean[] visited = new boolean[n + 1];
            int cnt = dfs(1, visited);

            int diff = Math.abs((n - cnt) - cnt);
            result = Math.min(result, diff);

            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        return result;
    }

    private void initGraph(int n, int[][] wires) {
        graph = new List[n + 1];
        for (int i = 1; i < graph.length; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int[] wire : wires) {
            int v1 = wire[0];
            int v2 = wire[1];

            graph[v1].add(v2);
            graph[v2].add(v1);
        }
    }

    private int dfs(int v, boolean[] visited) {

        visited[v] = true;

        int cnt = 1;
        for (Integer node : graph[v]) {
            if (visited[node]) {
                continue;
            }

            cnt += dfs(node, visited);
        }

        return cnt;
    }
}
```