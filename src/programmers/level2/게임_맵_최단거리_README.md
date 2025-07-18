## 🌵 문제 정보

- **문제명:** [게임 맵 최단거리](https://school.programmers.co.kr/learn/courses/30/lessons/1844)
- **난이도:** Lv.2
- **출처:** 프로그래머스

---

## 🌵 문제 설명

ROR 게임은 두 팀으로 나누어서 진행하며, 상대 팀 진영을 먼저 파괴하면 이기는 게임입니다. 따라서, 각 팀은 상대 팀 진영에 최대한 빨리 도착하는 것이 유리합니다.

지금부터 당신은 한 팀의 팀원이 되어 게임을 진행하려고 합니다. 다음은 5 x 5 크기의 맵에, 당신의 캐릭터가 (행: 1, 열: 1) 위치에 있고,
상대 팀 진영은 (행: 5, 열: 5) 위치에 있는 경우의 예시입니다.

<img src="https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/dc3a1b49-13d3-4047-b6f8-6cc40b2702a7/%E1%84%8E%E1%85%AC%E1%84%83%E1%85%A1%E1%86%AB%E1%84%80%E1%85%A5%E1%84%85%E1%85%B51_sxuruo.png" width="300">

위 그림에서 검은색 부분은 벽으로 막혀있어 갈 수 없는 길이며, 흰색 부분은 갈 수 있는 길입니다. 캐릭터가 움직일 때는 동, 서, 남, 북 방향으로 한 칸씩 이동하며, 게임 맵을 벗어난 길은 갈 수 없습니다.
아래 예시는 캐릭터가 상대 팀 진영으로 가는 두 가지 방법을 나타내고 있습니다.

* 첫 번째 방법은 11개의 칸을 지나서 상대 팀 진영에 도착했습니다.

<img src="https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/9d909e5a-ca95-4088-9df9-d84cb804b2b0/%E1%84%8E%E1%85%AC%E1%84%83%E1%85%A1%E1%86%AB%E1%84%80%E1%85%A5%E1%84%85%E1%85%B52_hnjd3b.png" width="300">

* 두 번째 방법은 15개의 칸을 지나서 상대팀 진영에 도착했습니다.

<img src="https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/4b7cd629-a3c2-4e02-b748-a707211131de/%E1%84%8E%E1%85%AC%E1%84%83%E1%85%A1%E1%86%AB%E1%84%80%E1%85%A5%E1%84%85%E1%85%B53_ntxygd.png" width="300">

위 예시에서는 첫 번째 방법보다 더 빠르게 상대팀 진영에 도착하는 방법은 없으므로, 이 방법이 상대 팀 진영으로 가는 가장 빠른 방법입니다.

만약, 상대 팀이 자신의 팀 진영 주위에 벽을 세워두었다면 상대 팀 진영에 도착하지 못할 수도 있습니다. 예를 들어, 다음과 같은 경우에 당신의 캐릭터는 상대 팀 진영에 도착할 수 없습니다.

<img src="https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/d963b4bd-12e5-45da-9ca7-549e453d58a9/%E1%84%8E%E1%85%AC%E1%84%83%E1%85%A1%E1%86%AB%E1%84%80%E1%85%A5%E1%84%85%E1%85%B54_of9xfg.png" width="300">

게임 맵의 상태 maps가 매개변수로 주어질 때, 캐릭터가 상대 팀 진영에 도착하기 위해서 지나가야 하는 칸의 개수의 최솟값을 return 하도록 solution 함수를 완성해주세요. 단, 상대 팀 진영에 도착할 수
없을 때는 -1을 return 해주세요.

**제한사항**

* maps는 n x m 크기의 게임 맵의 상태가 들어있는 2차원 배열로, n과 m은 각각 1 이상 100 이하의 자연수입니다.
* n과 m은 서로 같을 수도, 다를 수도 있지만, n과 m이 모두 1인 경우는 입력으로 주어지지 않습니다.
* maps는 0과 1로만 이루어져 있으며, 0은 벽이 있는 자리, 1은 벽이 없는 자리를 나타냅니다.
* 처음에 캐릭터는 게임 맵의 좌측 상단인 (1, 1) 위치에 있으며, 상대방 진영은 게임 맵의 우측 하단인 (n, m) 위치에 있습니다.

### 🔸 입출력 예

| maps                                                          | answer |
|---------------------------------------------------------------|--------|
| [[1,0,1,1,1],[1,0,1,0,1],[1,0,1,1,1],[1,1,1,0,1],[0,0,0,0,1]] | 11     |
| [[1,0,1,1,1],[1,0,1,0,1],[1,0,1,1,1],[1,1,1,0,0],[0,0,0,0,1]] | -1     |

---

## 🌵 알고리즘 설계

* `Queue`를 사용하여 bfs를 구현한다.

1. `maps`의 시작 위치 `(0, 0)`에서 탐색을 시작하며, 현재 위치와 이동 횟수를 함께 `Queue`에 저장한다.
2. `visited[][]` 배열을 사용하여 이미 방문한 칸인지 여부를 체크한다.
3. Queue가 빌 때까지 다음을 반복한다
    * 현재 위치 `(y, x)`를 꺼내고, 도착지`(maps.length - 1, maps[0].length - 1)`에 도달했는지 확인한다.
    * 도달했다면 현재까지의 거리를 반환한다.
4. `4방향(상하좌우)`을 탐색하기 위해 `dy`, `dx` 배열을 사용한다.
5. 다음 위치 `(ny, nx)`가 다음 조건을 만족하면 이동 가능하다고 판단한다
    * 맵의 범위 내에 있고
    * 방문하지 않았고
    * 해당 칸이 벽이 아닌 경우 `(maps[ny][nx] == 1)`
6. 이동 가능하다면 `visited[ny][nx] = true`로 표시하고 `(ny, nx, cnt + 1)`을 큐에 넣는다.
7. 도착지에 도달하지 못하고 큐가 모두 비었다면, -1을 반환한다.


---

## 🌵 코드

```java
public class 게임_맵_최단거리 {
    int[][] maps;
    boolean[][] visited;

    int[] dy = {0, 1, 0, -1};
    int[] dx = {1, 0, -1, 0};

    public int solution(int[][] maps) {
        this.maps = maps;
        visited = new boolean[maps.length][maps[0].length];
        return bfs();
    }

    public int bfs() {
        visited[0][0] = true;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0, 1});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cy = cur[0];
            int cx = cur[1];
            int cnt = cur[2];

            if (cy == maps.length - 1 && cx == maps[0].length - 1) {
                return cnt;
            }

            for (int i = 0; i < dy.length; i++) {
                int ny = cy + dy[i];
                int nx = cx + dx[i];

                if (ny < 0 || ny >= maps.length) {
                    continue;
                }

                if (nx < 0 || nx >= maps[0].length) {
                    continue;
                }

                if (visited[ny][nx]) {
                    continue;
                }

                if (maps[ny][nx] == 0) {
                    continue;
                }

                visited[ny][nx] = true;
                queue.offer(new int[]{ny, nx, cnt + 1});
            }
        }

        return -1;
    }
}
```