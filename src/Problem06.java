import java.util.*;

public class Problem06 {

    private int timer;
    private int sccCount;

    public int countDependencyGroups(int n, int[][] dependencies) {
        if (n <= 0) return 0;

        List<List<Integer>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] dep : dependencies) {
            adj.get(dep[0]).add(dep[1]);
        }

        int[] tin = new int[n];
        int[] low = new int[n];
        boolean[] onStack = new boolean[n];
        Deque<Integer> stack = new ArrayDeque<>();

        Arrays.fill(tin, -1);
        Arrays.fill(low, -1);
        timer = 0;
        sccCount = 0;

        for (int i = 0; i < n; i++) {
            if (tin[i] == -1) {
                dfs(i, adj, tin, low, onStack, stack);
            }
        }

        return sccCount;
    }

    private void dfs(int u, List<List<Integer>> adj, int[] tin, int[] low, boolean[] onStack, Deque<Integer> stack) {
        tin[u] = low[u] = timer++;
        stack.push(u);
        onStack[u] = true;

        for (int v : adj.get(u)) {
            if (tin[v] == -1) {
                dfs(v, adj, tin, low, onStack, stack);
                low[u] = Math.min(low[u], low[v]);
            } else if (onStack[v]) {
                low[u] = Math.min(low[u], tin[v]);
            }
        }

        if (low[u] == tin[u]) {
            while (true) {
                int node = stack.pop();
                onStack[node] = false;
                if (node == u) break;
            }
            sccCount++;
        }
    }

    public static void main(String[] args) {
        Problem06 solver = new Problem06();
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