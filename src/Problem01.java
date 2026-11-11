import java.util.*;
public class Problem01{
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int provinces = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                provinces++;
                queue.offer(i);
                visited[i] = true;
                while (!queue.isEmpty()) {
                    int curr = queue.poll();
                    for (int neighbor = 0; neighbor < n; neighbor++) {
                        if (isConnected[curr][neighbor] == 1 && !visited[neighbor]) {
                            visited[neighbor] = true;
                            queue.offer(neighbor);
                        }
                    }
                }
            }
        }

        return provinces;
    }
    public static void main(String[] args) {
        Problem01 solver = new Problem01();
        int[][] test1 = {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };
        System.out.println("Example 1: " + solver.findCircleNum(test1));
        int[][] test2 = {
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        System.out.println("Example 2: " + solver.findCircleNum(test2));
    }
}
