import java.util.*;

public class Problem07 {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] pre : prerequisites) {
            adj.get(pre[1]).add(pre[0]);
        }

        int[] state = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (state[i] == 0) {
                if (hasCycle(i, adj, state)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean hasCycle(int u, List<List<Integer>> adj, int[] state) {
        state[u] = 1;

        for (int v : adj.get(u)) {
            if (state[v] == 1) {
                return true;
            }
            if (state[v] == 0 && hasCycle(v, adj, state)) {
                return true;
            }
        }

        state[u] = 2;
        return false;
    }

    public static void main(String[] args) {
        Problem07 solver = new Problem07();
        int[][] pre1 = {{1, 0}};
        System.out.println("Example 1: " + solver.canFinish(2, pre1));
        int[][] pre2 = {{1, 0}, {0, 1}};
        System.out.println("Example 2: " + solver.canFinish(2, pre2));
    }
}