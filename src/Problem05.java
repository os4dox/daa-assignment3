import java.util.*;

public class Problem05 {
    public int countDependencyGroups(int n, int[][] dependencies) {
        if (n <= 0) return 0;
        List<List<Integer>> adj = new ArrayList<>(n);
        List<List<Integer>> revAdj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
            revAdj.add(new ArrayList<>());
        }
        for (int[] dep : dependencies) {
            int u = dep[0];
            int v = dep[1];
            adj.get(u).add(v);
            revAdj.get(v).add(u);
        }
        boolean[] visited = new boolean[n];
        Deque<Integer> order = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs1(i, adj, visited, order);
            }
        }
        Arrays.fill(visited, false);
        int sccCount = 0;
        while (!order.isEmpty()) {
            int u = order.pop();
            if (!visited[u]) {
                dfs2(u, revAdj, visited);
                sccCount++;
            }
        }
        return sccCount;
    }
    private void dfs1(int u, List<List<Integer>> adj, boolean[] visited, Deque<Integer> order) {
        visited[u] = true;
        for (int v : adj.get(u)) {
            if (!visited[v]) {
                dfs1(v, adj, visited, order);
            }
        }
        order.push(u);
    }
    private void dfs2(int u, List<List<Integer>> revAdj, boolean[] visited) {
        visited[u] = true;
        for (int v : revAdj.get(u)) {
            if (!visited[v]) {
                dfs2(v, revAdj, visited);
            }
        }
    }
    public static void main(String[] args) {
        Problem05 solver = new Problem05();
        int n1 = 5;
        int[][] dep1 = {{0, 1}, {1, 2}, {2, 0}, {2, 3}, {3, 4}};
        System.out.println("Example 1: " + solver.countDependencyGroups(n1, dep1));
        int n2 = 4;
        int[][] dep2 = {{0, 1}, {1, 0}, {2, 3}, {3, 2}};
        System.out.println("Example 2: " + solver.countDependencyGroups(n2, dep2));
        int n3 = 4;
        int[][] dep3 = {{0, 1}, {1, 2}, {2, 3}};
        System.out.println("Example 3: " + solver.countDependencyGroups(n3, dep3));
    }
}