package programmers.level2;

import java.util.ArrayDeque;
import java.util.Queue;

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
