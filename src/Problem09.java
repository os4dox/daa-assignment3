import java.util.*;

public class Problem09 {

    private boolean hasCycle = false;
    private int orderIndex;
    private int[] order;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] pre : prerequisites) {
            adj.get(pre[1]).add(pre[0]);
        }

        int[] state = new int[numCourses];
        order = new int[numCourses];
        orderIndex = numCourses - 1;
        hasCycle = false;

        for (int i = 0; i < numCourses; i++) {
            if (state[i] == 0) {
                dfs(i, adj, state);
                if (hasCycle) {
                    return new int[0];
                }
            }
        }

        return order;
    }

    private void dfs(int u, List<List<Integer>> adj, int[] state) {
        state[u] = 1;

        for (int v : adj.get(u)) {
            if (state[v] == 1) {
                hasCycle = true;
                return;
            }
            if (state[v] == 0) {
                dfs(v, adj, state);
                if (hasCycle) return;
            }
        }

        state[u] = 2;
        order[orderIndex--] = u;
    }

    public static void main(String[] args) {
        Problem09 solver = new Problem09();
        int[][] pre1 = {{1, 0}};
        System.out.println("Example 1: " + Arrays.toString(solver.findOrder(2, pre1)));
        int[][] pre2 = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        System.out.println("Example 2: " + Arrays.toString(solver.findOrder(4, pre2)));
        int[][] pre3 = {{1, 0}, {0, 1}};
        System.out.println("Example 3: " + Arrays.toString(solver.findOrder(2, pre3)));
    }
}