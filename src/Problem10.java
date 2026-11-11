import java.util.*;

public class Problem10 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>(numCourses);
        int[] inDegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] pre : prerequisites) {
            adj.get(pre[1]).add(pre[0]);
            inDegree[pre[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        int[] order = new int[numCourses];
        int idx = 0;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            order[idx++] = u;
            for (int v : adj.get(u)) {
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        return idx == numCourses ? order : new int[0];
    }
    public static void main(String[] args) {
        Problem10 solver = new Problem10();
        int[][] pre1 = {{1, 0}};
        System.out.println("Example 1: " + Arrays.toString(solver.findOrder(2, pre1)));
        int[][] pre2 = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        System.out.println("Example 2: " + Arrays.toString(solver.findOrder(4, pre2)));
        int[][] pre3 = {{1, 0}, {0, 1}};
        System.out.println("Example 3: " + Arrays.toString(solver.findOrder(2, pre3)));
    }
}