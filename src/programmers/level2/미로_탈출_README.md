## 🌵 문제 정보

- **문제명:** [미로 탈출](https://school.programmers.co.kr/learn/courses/30/lessons/159993)
- **난이도:** Lv.2
- **출처:** 프로그래머스

---

## 🌵 문제 설명

1 x 1 크기의 칸들로 이루어진 직사각형 격자 형태의 미로에서 탈출하려고 합니다. 각 칸은 통로 또는 벽으로 구성되어 있으며,
벽으로 된 칸은 지나갈 수 없고 통로로 된 칸으로만 이동할 수 있습니다. 통로들 중 한 칸에는 미로를 빠져나가는 문이 있는데, 이 문은 레버를 당겨서만 열 수 있습니다.
레버 또한 통로들 중 한 칸에 있습니다. 따라서, 출발 지점에서 먼저 레버가 있는 칸으로 이동하여 레버를 당긴 후 미로를 빠져나가는 문이 있는 칸으로 이동하면 됩니다.
이때 아직 레버를 당기지 않았더라도 출구가 있는 칸을 지나갈 수 있습니다. 미로에서 한 칸을 이동하는데 1초가 걸린다고 할 때,
최대한 빠르게 미로를 빠져나가는데 걸리는 시간을 구하려 합니다.

미로를 나타낸 문자열 배열 maps가 매개변수로 주어질 때, 미로를 탈출하는데 필요한 최소 시간을 return 하는 solution 함수를 완성해주세요.
만약, 탈출할 수 없다면 -1을 return 해주세요.

**제한사항**

* 5 ≤ maps의 길이 ≤ 100
* 5 ≤ maps[i]의 길이 ≤ 100
* maps[i]는 다음 5개의 문자들로만 이루어져 있습니다.
    * S : 시작 지점
    * E : 출구
    * L : 레버
    * O : 통로
    * X : 벽
* 시작 지점과 출구, 레버는 항상 다른 곳에 존재하며 한 개씩만 존재합니다.
* 출구는 레버가 당겨지지 않아도 지나갈 수 있으며, 모든 통로, 출구, 레버, 시작점은 여러 번 지나갈 수 있습니다.

### 🔸 입출력 예

| maps                                      | result |
|-------------------------------------------|--------|
| ["SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"] | 16     |
| ["LOOXS","OOOOX","OOOOO","OOOOO","EOOOO"] | -1     |

**입출력 예 설명**

입출력 예 #1

주어진 문자열은 다음과 같은 미로이며

<img src="https://user-images.githubusercontent.com/62426665/214443486-cb2b84a4-afc6-4b25-8da2-645a853859f1.png" width="300">

다음과 같이 이동하면 가장 빠른 시간에 탈출할 수 있습니다.

<img src="https://user-images.githubusercontent.com/62426665/207090680-93289071-da4f-4126-9c31-066c1d4d3802.png" width="300">

4번 이동하여 레버를 당기고 출구까지 이동하면 총 16초의 시간이 걸립니다. 따라서 16을 반환합니다.

입출력 예 #2

주어진 문자열은 다음과 같은 미로입니다.

<img src="https://user-images.githubusercontent.com/62426665/214443892-1e7734e9-b4c8-49af-ba29-aa5597039617.png" width="300">

시작 지점에서 이동할 수 있는 공간이 없어서 탈출할 수 없습니다. 따라서 -1을 반환합니다.

---

## 🌵 알고리즘 설계

* 두 번의 BFS로 문제를 풀 수 있다.

1. BFS를 구현할 `queue`와, 각 위치의 방문 여부를 기록할 `visited` 배열을 생성한다.
2. 시작 지점 `S`, 레버 `L`, 출구 `E`의 위치를 초기화한다.
3. 첫 번째 BFS를 통해 `S → L`까지의 최단 거리를 탐색한다.
    * 큐에 시작 위치 `S`를 넣고, `visited에` 표시한다.
    * 큐가 비어 있지 않은 동안 다음을 반복한다.
    * 현재 위치를 꺼낸다.
    * 꺼낸 위치가 `L`이면 거리를 반환한다.
    * 사방 탐색을 통해 인접한 위치를 확인한다.
    * 이동 가능하고, 방문하지 않았다면 큐에 추가하고 `visited`에 기록한다.
4. 두 번째 BFS를 통해 `L → E`까지의 최단 거리를 탐색한다. 방식은 위와 동일하다.
5. 두 BFS 중 하나라도 도달하지 못하면 `-1`을 반환한다.
6. 두 경로 모두 도달 가능하면 두 거리의 합을 반환한다.

## 🌵 코드

```java
 public class 미로_탈출 {
    public int solution(String[] maps) {
        Map map = new Map(maps);
        Solver solver = new Solver(map);

        int startToLeverCount = solver.getStartToLeverCount();
        int leverToEndCount = solver.getLeverToEndCount();

        if (startToLeverCount == -1 || leverToEndCount == -1) {
            return -1;
        }

        return startToLeverCount + leverToEndCount;
    }

    static class Map {

        private static final String START = "S";
        private static final String EXIT = "E";
        private static final String LEVER = "L";
        private static final String PASS = "O";

        private String[][] maps;
        private int[] startPos;
        private int[] endPos;
        private int[] leverPos;

        public Map(String[] maps) {
            this.maps = new String[maps.length][maps[0].length()];

            for (int i = 0; i < maps.length; i++) {
                char[] charArray = maps[i].toCharArray();
                for (int j = 0; j < maps[i].length(); j++) {
                    if (String.valueOf(charArray[j]).equals(START)) {
                        this.startPos = new int[]{i, j};
                    }
                    if (String.valueOf(charArray[j]).equals(EXIT)) {
                        this.endPos = new int[]{i, j};
                    }
                    if (String.valueOf(charArray[j]).equals(LEVER)) {
                        this.leverPos = new int[]{i, j};
                    }

                    this.maps[i][j] = String.valueOf(charArray[j]);
                }
            }
        }

        public int[] getStartPos() {
            return startPos;
        }

        public int[] getEndPos() {
            return endPos;
        }

        public int[] getLeverPos() {
            return leverPos;
        }

        public int getRow() {
            return maps.length;
        }

        public int getCol() {
            return maps[0].length;
        }

        public boolean isPassable(int row, int col) {
            return maps[row][col].equals(PASS) ||
                    maps[row][col].equals(START) ||
                    maps[row][col].equals(LEVER) ||
                    maps[row][col].equals(EXIT);
        }
    }

    static class Solver {

        private static final int[] dr = {1, 0, -1, 0};
        private static final int[] dc = {0, -1, 0, 1};

        private Map map;

        public Solver(Map map) {
            this.map = map;
        }

        public int getStartToLeverCount() {
            return bfs(map.getStartPos(), map.getLeverPos());
        }

        public int getLeverToEndCount() {
            return bfs(map.getLeverPos(), map.getEndPos());
        }

        private int bfs(int[] startPos, int[] endPos) {
            boolean[][] visited = new boolean[map.getRow()][map.getCol()];
            visited[startPos[0]][startPos[1]] = true;

            Queue<int[]> queue = new ArrayDeque<>();
            queue.add(new int[]{startPos[0], startPos[1], 0});

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int curRow = cur[0];
                int curCol = cur[1];
                int curCnt = cur[2];

                for (int i = 0; i < dr.length; i++) {
                    int nextRow = curRow + dr[i];
                    int nextCol = curCol + dc[i];

                    if (isOverMap(nextRow, nextCol)) {
                        continue;
                    }

                    if (!map.isPassable(nextRow, nextCol)) {
                        continue;
                    }

                    if (visited[nextRow][nextCol]) {
                        continue;
                    }

                    if (nextRow == endPos[0] && nextCol == endPos[1]) {
                        return curCnt + 1;
                    }

                    visited[nextRow][nextCol] = true;
                    queue.offer(new int[]{nextRow, nextCol, curCnt + 1});
                }
            }

            return -1;
        }

        private boolean isOverMap(int row, int col) {

            if (row < 0 || row >= map.getRow()) {
                return true;
            }

            if (col < 0 || col >= map.getCol()) {
                return true;
            }

            return false;
        }
    }
}
```