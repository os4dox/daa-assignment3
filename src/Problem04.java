import java.util.Arrays;

public class Problem04 {

    public int minCostConnectPoints(int[][] points) {
        if (points == null || points.length <= 1) {
            return 0;
        }

        int n = points.length;
        int[] minDist = new int[n];
        boolean[] inMST = new boolean[n];

        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[0] = 0;

        int totalCost = 0;

        for (int step = 0; step < n; step++) {
            int u = -1;

            for (int i = 0; i < n; i++) {
                if (!inMST[i] && (u == -1 || minDist[i] < minDist[u])) {
                    u = i;
                }
            }

            inMST[u] = true;
            totalCost += minDist[u];

            for (int v = 0; v < n; v++) {
                if (!inMST[v]) {
                    int dist = Math.abs(points[u][0] - points[v][0]) + Math.abs(points[u][1] - points[v][1]);
                    if (dist < minDist[v]) {
                        minDist[v] = dist;
                    }
                }
            }
        }

        return totalCost;
    }

    public static void main(String[] args) {
        Problem04 solver = new Problem04();

        int[][] test1 = {{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}};
        System.out.println("Test 1: " + solver.minCostConnectPoints(test1));

        int[][] test2 = {{3, 12}, {-2, 5}, {-4, 1}};
        System.out.println("Test 2: " + solver.minCostConnectPoints(test2));
    }
}