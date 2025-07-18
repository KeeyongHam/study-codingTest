package programmers.level2;

public class 피로도 {
    int result = 0;
    int[][] dungeons;
    boolean[] visited;

    public int solution(int k, int[][] dungeons) {
        this.dungeons = dungeons;
        visited = new boolean[dungeons.length];

        dfs(k, 0);

        return result;
    }

    private void dfs(int fatigue, int cnt) {

        for (int i = 0; i < dungeons.length; i++) {

            if (visited[i]) {
                continue;
            }

            if (fatigue < dungeons[i][0]) {
                continue;
            }

            result = Math.max(result, cnt + 1);

            visited[i] = true;
            dfs(fatigue - dungeons[i][1], cnt + 1);
            visited[i] = false;
        }
    }
}
