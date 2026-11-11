import java.util.*;

public class Problem03 {
    static class Edge implements Comparable<Edge> {
        int u;
        int v;
        int weight;
        Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }
    static class UnionFind {
        private final int[] parent;
        private final int[] rank;
        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return false;
            }
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
            return true;
        }
    }
    public int minCostConnectPoints(int[][] points) {
        if (points == null || points.length <= 1) {
            return 0;
        }
        int n = points.length;
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int dist = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                edges.add(new Edge(i, j, dist));
            }
        }
        Collections.sort(edges);
        UnionFind uf = new UnionFind(n);
        int totalCost = 0;
        int edgesCount = 0;
        for (Edge edge : edges) {
            if (uf.union(edge.u, edge.v)) {
                totalCost += edge.weight;
                edgesCount++;
                if (edgesCount == n - 1) {
                    break;
                }
            }
        }
        return totalCost;
    }
    public static void main(String[] args) {
        Problem03 solver = new Problem03();

        int[][] test1 = {{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}};
        System.out.println("Test 1: " + solver.minCostConnectPoints(test1));

        int[][] test2 = {{3, 12}, {-2, 5}, {-4, 1}};
        System.out.println("Test 2: " + solver.minCostConnectPoints(test2));
    }
}