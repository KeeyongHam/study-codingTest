## 🌵 문제 정보

- **문제명:** [네트워크](https://school.programmers.co.kr/learn/courses/30/lessons/43162)
- **난이도:** Lv.3
- **출처:** 프로그래머스

---

## 🌵 문제 설명

네트워크란 컴퓨터 상호 간에 정보를 교환할 수 있도록 연결된 형태를 의미합니다.
예를 들어, 컴퓨터 A와 컴퓨터 B가 직접적으로 연결되어있고, 컴퓨터 B와 컴퓨터 C가 직접적으로 연결되어 있을 때 컴퓨터 A와 컴퓨터 C도 간접적으로 연결되어
정보를 교환할 수 있습니다. 따라서 컴퓨터 A, B, C는 모두 같은 네트워크 상에 있다고 할 수 있습니다.

컴퓨터의 개수 n, 연결에 대한 정보가 담긴 2차원 배열 computers가 매개변수로 주어질 때, 네트워크의 개수를 return 하도록 solution 함수를 작성하시오.

**제한사항**

* 컴퓨터의 개수 n은 1 이상 200 이하인 자연수입니다.
* 각 컴퓨터는 0부터 n-1인 정수로 표현합니다.
* i번 컴퓨터와 j번 컴퓨터가 연결되어 있으면 computers[i][j]를 1로 표현합니다.
* computer[i][i]는 항상 1입니다.

### 🔸 입출력 예

| n | computers                         | return |
|---|-----------------------------------|--------|
| 3 | [[1, 1, 0], [1, 1, 0], [0, 0, 1]] | 2      |
| 3 | [[1, 1, 0], [1, 1, 1], [0, 1, 1]] | 1      |

---

## 🌵 알고리즘 설계

* DFS를 활용하여 문제를 해결할 수 있다.

1. 그래프 생성
    * computers 2차원 배열을 순회하며, 간선을 인접 리스트 형태로 변환한다.
2. 자기 자신(`i == j`)은 건너뛴다.
    * 연결되어 있으면 해당 노드의 리스트에 상대 노드 번호를 추가한다.
3. 방문 배열 초기화
    * `visited[n+1]` 배열을 만들어, 각 컴퓨터의 방문 여부를 저장한다.
    * 아직 방문하지 않은 노드부터 DFS를 시작한다.
4. 네트워크 탐색
    * 방문하지 않은 노드를 만나면, 네트워크 개수를 1 증가시킨다.
    * 해당 노드에서 DFS를 실행해, 연결된 모든 노드를 방문 처리한다.
5. DFS 동작
   * 현재 노드를 방문 처리(`visited[idx] = true`)
   * 현재 노드와 연결된 모든 인접 노드를 순회한다.
   * 아직 방문하지 않은 인접 노드에 대해 재귀적으로 DFS 실행한다..
6. 반복
   * 모든 노드를 순회하며, 방문하지 않은 노드가 발견될 때마다 3~4 과정을 반복한다.
7. 결과 반환
   * 누적된 네트워크 개수를 반환한다.

---

## 🌵 코드

```java
 public class 네트워크 {
    public int solution(int n, int[][] computers) {
        NetworkCounter networkCounter = new NetworkCounter(n, new Graph(computers));
        return networkCounter.getNetworkCount();
    }

    static class NetworkCounter {

        private boolean[] visited;
        private Graph graph;

        public NetworkCounter(int n, Graph graph) {
            visited = new boolean[n + 1];
            this.graph = graph;
        }

        public int getNetworkCount() {
            int count = 0;
            for (int i = 1; i < graph.getSize(); i++) {

                if (visited[i]) {
                    continue;
                }

                count++;
                dfs(i);
            }

            return count;
        }

        private void dfs(int idx) {

            visited[idx] = true;

            for (int next : graph.getNode(idx)) {
                if (visited[next]) {
                    continue;
                }

                dfs(next);
            }
        }
    }

    static class Graph {

        private List<Integer>[] graph;

        public Graph(int[][] computers) {
            initGraph(computers);
            addEdges(computers);
        }

        private void initGraph(int[][] computers) {

            graph = new List[computers.length + 1];

            for (int i = 0; i < graph.length; i++) {
                graph[i] = new ArrayList<>();
            }
        }

        private void addEdges(int[][] computers) {
            for (int i = 0; i < computers.length; i++) {
                for (int j = 0; j < computers.length; j++) {

                    if (i == j) {
                        continue;
                    }

                    if (computers[i][j] == 1) {
                        graph[i + 1].add(j + 1);
                    }
                }
            }
        }

        public int getSize() {
            return graph.length;
        }

        public List<Integer> getNode(int idx) {
            return graph[idx];
        }
    }
}

```