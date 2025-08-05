package programmers.level3;

import java.util.ArrayList;
import java.util.List;

public class 네트워크 {
    public int solution(int n, int[][] computers) {
        NetworkCounter networkCounter = new NetworkCounter(n, new Graph(computers));
        return networkCounter.getNetworkCount();
    }

    static class NetworkCounter {

        private boolean[] visited;
        private Graph graph;

        public NetworkCounter(int n, Graph graph) {
            visited = new boolean[n + 1];
            this.graph = graph;
        }

        public int getNetworkCount() {
            int count = 0;
            for (int i = 1; i < graph.getSize(); i++) {

                if (visited[i]) {
                    continue;
                }

                count++;
                dfs(i);
            }

            return count;
        }

        private void dfs(int idx) {

            visited[idx] = true;

            for (int next : graph.getNode(idx)) {
                if (visited[next]) {
                    continue;
                }

                dfs(next);
            }
        }
    }

    static class Graph {

        private List<Integer>[] graph;

        public Graph(int[][] computers) {
            initGraph(computers);
            addEdges(computers);
        }

        private void initGraph(int[][] computers) {

            graph = new List[computers.length + 1];

            for (int i = 0; i < graph.length; i++) {
                graph[i] = new ArrayList<>();
            }
        }

        private void addEdges(int[][] computers) {
            for (int i = 0; i < computers.length; i++) {
                for (int j = 0; j < computers.length; j++) {

                    if (i == j) {
                        continue;
                    }

                    if (computers[i][j] == 1) {
                        graph[i + 1].add(j + 1);
                    }
                }
            }
        }

        public int getSize() {
            return graph.length;
        }

        public List<Integer> getNode(int idx) {
            return graph[idx];
        }
    }
}