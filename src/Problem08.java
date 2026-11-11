import java.util.*;

public class Problem08 {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
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

        int visitedCount = 0;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            visitedCount++;

            for (int v : adj.get(u)) {
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        return visitedCount == numCourses;
    }

    public static void main(String[] args) {
        Problem08 solver = new Problem08();
        int[][] pre1 = {{1, 0}};
        System.out.println("Example 1: " + solver.canFinish(2, pre1));
        int[][] pre2 = {{1, 0}, {0, 1}};
        System.out.println("Example 2: " + solver.canFinish(2, pre2));
    }
}