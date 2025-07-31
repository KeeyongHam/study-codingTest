package programmers.level2;

import java.util.LinkedList;
import java.util.List;

public class 전력망_둘로_나누기 {

    private List<Integer>[] graph;
    int result = Integer.MAX_VALUE;

    public int solution(int n, int[][] wires) {

        initGraph(n, wires);

        for (int[] wire : wires) {
            int v1 = wire[0];
            int v2 = wire[1];

            graph[v1].remove(Integer.valueOf(v2));
            graph[v2].remove(Integer.valueOf(v1));

            boolean[] visited = new boolean[n + 1];
            int cnt = dfs(1, visited);

            int diff = Math.abs((n - cnt) - cnt);
            result = Math.min(result, diff);

            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        return result;
    }

    private void initGraph(int n, int[][] wires) {
        graph = new List[n + 1];
        for (int i = 1; i < graph.length; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int[] wire : wires) {
            int v1 = wire[0];
            int v2 = wire[1];

            graph[v1].add(v2);
            graph[v2].add(v1);
        }
    }

    private int dfs(int v, boolean[] visited) {

        visited[v] = true;

        int cnt = 1;
        for (Integer node : graph[v]) {
            if (visited[node]) {
                continue;
            }

            cnt += dfs(node, visited);
        }

        return cnt;
    }
}