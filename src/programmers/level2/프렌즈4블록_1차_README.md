## 🌵 문제 정보

- **문제명:** [1차 프렌즈4블로](https://school.programmers.co.kr/learn/courses/30/lessons/17679)
- **난이도:** Lv.2
- **출처:** 프로그래머스

---

## 🌵 문제 설명

블라인드 공채를 통과한 신입 사원 라이언은 신규 게임 개발 업무를 맡게 되었다. 이번에 출시할 게임 제목은 "프렌즈4블록".
같은 모양의 카카오프렌즈 블록이 2×2 형태로 4개가 붙어있을 경우 사라지면서 점수를 얻는 게임이다.

<img src="http://t1.kakaocdn.net/welcome2018/pang1.png" width="300">

만약 판이 위와 같이 주어질 경우, 라이언이 2×2로 배치된 7개 블록과 콘이 2×2로 배치된 4개 블록이 지워진다.
같은 블록은 여러 2×2에 포함될 수 있으며, 지워지는 조건에 만족하는 2×2 모양이 여러 개 있다면 한꺼번에 지워진다.

<img src="http://t1.kakaocdn.net/welcome2018/pang2.png" width="300">

블록이 지워진 후에 위에 있는 블록이 아래로 떨어져 빈 공간을 채우게 된다.


<img src="http://t1.kakaocdn.net/welcome2018/pang3.png" width="300">

만약 빈 공간을 채운 후에 다시 2×2 형태로 같은 모양의 블록이 모이면 다시 지워지고 떨어지고를 반복하게 된다.

<img src="http://t1.kakaocdn.net/welcome2018/pang4.png" width="300">

위 초기 배치를 문자로 표시하면 아래와 같다.

```text
TTTANT
RRFACC
RRRFCC
TRRRAA
TTMMMF
TMMTTJ
```

각 문자는 라이언(R), 무지(M), 어피치(A), 프로도(F), 네오(N), 튜브(T), 제이지(J), 콘(C)을 의미한다

입력으로 블록의 첫 배치가 주어졌을 때, 지워지는 블록은 모두 몇 개인지 판단하는 프로그램을 제작하라.

**입력 형식**

* 입력으로 판의 높이 m, 폭 n과 판의 배치 정보 board가 들어온다.
* 2 ≦ n, m ≦ 30
* board는 길이 n인 문자열 m개의 배열로 주어진다. 블록을 나타내는 문자는 대문자 A에서 Z가 사용된다.

**출력 형식**

* 입력으로 주어진 판 정보를 가지고 몇 개의 블록이 지워질지 출력하라.

### 🔸 입출력 예

| m | n | board                                                        | answer |
|---|---|--------------------------------------------------------------|--------|
| 4 | 5 | ["CCBDE", "AAADE", "AAABF", "CCBBF"]                         | 14     |
| 6 | 6 | ["TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"] | 15     |

---

## 🌵 알고리즘 설계

* 2x2 블록 탐색 → 제거 → 중력 적용의 과정을 반복한다.

1. 보드를 2차원 배열로 변환
    * 문자열 배열로 주어진 `board`를 `Character[][]` 타입의 2차원 배열로 변환한다.
2. 매 반복마다 2x2 블록을 탐색
    * 중복 제거를 위해 `Set<Point>`을 사용하여 제거할 블록 위치를 저장한다.
    * 좌측 상단 기준으로 2x2 범위를 검사하며, 같은 문자가 연속된 경우 4개 좌표를 모두 `Set`에 저장한다.
    * 탐색은 `findMatchedBlock(cur, boards, row, col, matched)` 메서드를 통해 수행한다.
3. 제거할 블록이 없으면 반복 종료
    * `matched`가 비어있으면 `while문` 종료.

4. 블록 제거 및 개수 누적
    * ` removeBlock()` 메서드를 통해 `boards`에서 해당 위치를 `' '(공백)`으로 처리.
    * 제거된 블록 개수를 누적하여 result에 더한다.

5. 중력 적용
    * `applyGravity()` 메서드를 통해 위의 블록이 아래로 내려오도록 정렬.
    * 각 열마다 아래에서부터 비어있는 칸을 찾아, 위의 블록을 끌어내린다.

---

## 🌵 코드

```java
 public class 프렌즈4블록_1차 {
    public int solution(int m, int n, String[] board) {
        Character[][] boards = createBoards(m, n, board);
        Set<Point> matched = new HashSet<>();

        int result = 0;
        while (true) {
            for (int row = 0; row < m - 1; row++) {
                for (int col = 0; col < n - 1; col++) {
                    Character cur = boards[row][col];
                    findMatchedBlock(cur, boards, row, col, matched);
                }
            }

            if (matched.isEmpty()) {
                break;
            }

            result += matched.size();
            removeBlock(matched, boards);
            matched.clear();

            applyGravity(m, n, boards);
        }

        return result;
    }

    private Character[][] createBoards(int m, int n, String[] board) {
        Character[][] boards = new Character[m][n];

        for (int i = 0; i < m; i++) {
            char[] parsedBlock = board[i].toCharArray();
            for (int j = 0; j < n; j++) {
                boards[i][j] = parsedBlock[j];
            }
        }

        return boards;
    }

    private void findMatchedBlock(Character cur, Character[][] boards, int row, int col, Set<Point> matched) {
        if (cur == ' ') {
            return;
        }

        if (cur == boards[row][col + 1] && cur == boards[row + 1][col] && cur == boards[row + 1][col + 1]) {
            matched.add(new Point(row, col));
            matched.add(new Point(row, col + 1));
            matched.add(new Point(row + 1, col));
            matched.add(new Point(row + 1, col + 1));
        }
    }

    private void removeBlock(Set<Point> matched, Character[][] boards) {
        for (Point point : matched) {
            boards[point.getRow()][point.getCol()] = ' ';
        }
    }

    private void applyGravity(int m, int n, Character[][] boards) {
        for (int col = 0; col < n; col++) {
            applyGravityToCol(m, boards, col);
        }
    }

    private void applyGravityToCol(int m, Character[][] boards, int col) {
        for (int emptyRow = m - 1; emptyRow >= 0; emptyRow--) {
            if (boards[emptyRow][col] != ' ') {
                continue;
            }

            for (int fillRow = emptyRow - 1; fillRow >= 0; fillRow--) {
                if (boards[fillRow][col] != ' ') {
                    boards[emptyRow][col] = boards[fillRow][col];
                    boards[fillRow][col] = ' ';
                    break;
                }
            }
        }
    }

    static class Point {


        private int row;
        private int col;

        public Point(int row, int col) {
            this.col = col;
            this.row = row;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Point point = (Point) o;
            return col == point.col && row == point.row;
        }

        @Override
        public int hashCode() {
            return Objects.hash(col, row);
        }
    }
}
```