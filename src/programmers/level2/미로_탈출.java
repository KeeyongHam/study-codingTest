package programmers.level2;

import java.util.ArrayDeque;
import java.util.Queue;

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
