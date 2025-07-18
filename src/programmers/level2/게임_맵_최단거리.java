package programmers.level2;

import java.util.ArrayDeque;
import java.util.Queue;

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
