## 🌵 문제 정보

- **문제명:** [리코체 로봇](https://school.programmers.co.kr/learn/courses/30/lessons/169199)
- **난이도:** Lv.2
- **출처:** 프로그래머스

---

## 🌵 문제 설명

리코쳇 로봇이라는 보드게임이 있습니다.

이 보드게임은 격자모양 게임판 위에서 말을 움직이는 게임으로, 시작 위치에서 출발한 뒤 목표 위치에 정확하게 멈추기 위해 최소 몇 번의 이동이 필요한지 말하는 게임입니다.

이 게임에서 말의 이동은 현재 위치에서 상, 하, 좌, 우 중 한 방향으로 게임판 위의 장애물이나 게임판 가장자리까지 부딪힐 때까지 미끄러져 움직이는 것을 한 번의 이동으로 정의합니다.

다음은 보드게임판을 나타낸 예시입니다. ("."은 빈 공간을, "R"은 로봇의 처음 위치를, "D"는 장애물의 위치를, "G"는 목표지점을 나타냅니다.)

```text
...D..R
.D.G...
....D.D
D....D.
..D....
```

이때 최소 움직임은 7번이며 "R" 위치에서 아래, 왼쪽, 위, 왼쪽, 아래, 오른쪽, 위 순서로 움직이면 "G" 위치에 멈춰 설 수 있습니다.

<img src="https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/production/presigned_urls/f78b64d8-69da-454c-be09-6ad0b3acd078/%E1%84%85%E1%85%B5%E1%84%8F%E1%85%A9%E1%84%8E%E1%85%A2%E1%86%BA%E1%84%85%E1%85%A9%E1%84%87%E1%85%A9%E1%86%BA.jpg" width="300">

게임판의 상태를 나타내는 문자열 배열 board가 주어졌을 때, 말이 목표위치에 도달하는데 최소 몇 번 이동해야 하는지 return 하는 solution함수를 완성해주세요.
만약 목표위치에 도달할 수 없다면 -1을 return 해주세요.

**제한 사항**

* 3 ≤ board의 길이 ≤ 100
    * 3 ≤ board의 원소의 길이 ≤ 100
    * board의 원소의 길이는 모두 동일합니다.
    * 문자열은 ".", "D", "R", "G"로만 구성되어 있으며 각각 빈 공간, 장애물, 로봇의 처음 위치, 목표 지점을 나타냅니다.
    * "R"과 "G"는 한 번씩 등장합니다.

### 🔸 입출력 예

| board                                                   | result |
|---------------------------------------------------------|--------|
| ["...D..R", ".D.G...", "....D.D", "D....D.", "..D...."] | 7      |
| [".D.R", "....", ".G..", "...D"]                        | -1     |

---

## 🌵 알고리즘 설계

* bfs를 활용하여 문제를 풀 수 있다.

1. BFS를 구현할 `queue`와 각 위치 방문 여부를 기록할 `visited` 배열을 생성한다.
2. 시작 지점과 목표 지점의 위치를 초기화한다.
3. BFS를 통해 R → G까지의 최소 이동 횟수를 탐색한다.
4. 큐에 시작 위치 R을 넣고, `visited`에 표시한다.
5. 큐가 빌 때까지 다음을 반복한다.
    * 현재 위치와 이동 횟수를 꺼낸다.
    * 사방 탐색을 수행한다.
    * 한 번 이동하면 장애물(D) 또는 보드 경계에 부딪힐 때까지 계속 진행한다.
    * 목표 위치 G에 도착하면 현재까지의 이동 횟수 + 1을 반환한다.
    * 아직 방문하지 않은 위치면 `visited`에 기록하고 큐에 추가한다.
6. BFS를 모두 마쳤는데 목표에 도달하지 못하면 -1을 반환한다.

---

## 🌵 코드

```java
 public class 리코체_로봇 {
    public int solution(String[] board) {
        Map map = new Map(board);
        Solver solver = new Solver(map);

        return solver.getResult();
    }

    static class Solver {

        private static final int[] dr = {1, 0, -1, 0};
        private static final int[] dc = {0, -1, 0, 1};

        private final Map map;

        public Solver(Map map) {
            this.map = map;
        }

        public int getResult() {
            boolean[][] visited = new boolean[map.getRowSize()][map.getColSize()];
            visited[map.getStartPos()[0]][map.getStartPos()[1]] = true;

            Queue<int[]> queue = new ArrayDeque<>();
            queue.add(new int[]{map.startPos[0], map.getStartPos()[1], 0});
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int curRow = cur[0];
                int curCol = cur[1];
                int cnt = cur[2];

                for (int i = 0; i < 4; i++) {
                    int nr = curRow;
                    int nc = curCol;

                    while (true) {
                        int tr = nr + dr[i];
                        int tc = nc + dc[i];

                        if (tr < 0 || tr >= map.getRowSize() || tc < 0 || tc >= map.getColSize() ||
                                map.getPos(tr, tc).equals("D")) {
                            break;
                        }

                        nr = tr;
                        nc = tc;
                    }

                    if (nr == map.getGoalPos()[0] && nc == map.getGoalPos()[1]) {
                        return cnt + 1;
                    }

                    if (!visited[nr][nc]) {
                        visited[nr][nc] = true;
                        queue.add(new int[]{nr, nc, cnt + 1});
                    }
                }

            }
            return -1;
        }
    }

    static class Map {

        private String[][] map;
        private int[] startPos;
        private int[] goalPos;

        public Map(String[] board) {
            initMap(board);

        }

        private void initMap(String[] board) {
            map = new String[board.length][board[0].length()];
            for (int i = 0; i < map.length; i++) {
                char[] charArray = board[i].toCharArray();
                for (int j = 0; j < map[i].length; j++) {
                    if (charArray[j] == 'R') {
                        startPos = new int[]{i, j};
                    }

                    if (charArray[j] == 'G') {
                        goalPos = new int[]{i, j};
                    }
                    map[i][j] = String.valueOf(charArray[j]);
                }
            }
        }

        public int getRowSize() {
            return map.length;
        }

        public int getColSize() {
            return map[0].length;
        }

        public int[] getStartPos() {
            return startPos;
        }

        public int[] getGoalPos() {
            return goalPos;
        }

        public String getPos(int row, int col) {
            return map[row][col];
        }
    }
}
```