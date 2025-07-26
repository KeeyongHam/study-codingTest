package programmers.level2;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
