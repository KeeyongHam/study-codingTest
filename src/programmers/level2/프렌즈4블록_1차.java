package programmers.level2;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class 프렌즈4블록_1차 {
    public int solution(int m, int n, String[] board) {

        Set<Point> matched = new HashSet<>();
        Character[][] boards = createBoards(m, n, board);

        int result = 0;
        while (true) {
            findMatchedBlock(m, n, boards, matched);

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
            char[] row = board[i].toCharArray();
            for (int j = 0; j < n; j++) {
                boards[i][j] = row[j];
            }
        }
        return boards;
    }

    private void findMatchedBlock(int m, int n, Character[][] boards, Set<Point> matched) {
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                Character cur = boards[i][j];

                if (cur == ' ') {
                    continue;
                }

                if (cur == boards[i][j + 1] && cur == boards[i + 1][j] && cur == boards[i + 1][j + 1]) {
                    matched.add(new Point(i, j));
                    matched.add(new Point(i, j + 1));
                    matched.add(new Point(i + 1, j));
                    matched.add(new Point(i + 1, j + 1));
                }
            }
        }
    }

    private void removeBlock(Set<Point> matched, Character[][] boards) {
        for (Point point : matched) {
            boards[point.getCol()][point.getRow()] = ' ';
        }
    }

    private void applyGravity(int m, int n, Character[][] boards) {
        for (int j = 0; j < n; j++) {
            for (int i = m - 1; i >= 0; i--) {
                if (boards[i][j] == ' ') {
                    for (int k = i - 1; k >= 0; k--) {
                        if (boards[k][j] != ' ') {
                            boards[i][j] = boards[k][j];
                            boards[k][j] = ' ';
                            break;
                        }
                    }
                }
            }
        }
    }

    static class Point {
        private int row;
        private int col;

        public Point(int col, int row) {
            this.col = col;
            this.row = row;
        }

        public int getCol() {
            return col;
        }

        public int getRow() {
            return row;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Point)) return false;
            Point point = (Point) o;
            return col == point.col && row == point.row;
        }


        @Override
        public int hashCode() {
            return Objects.hash(col, row);
        }
    }
}
