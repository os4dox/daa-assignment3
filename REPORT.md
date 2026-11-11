### DAA Assignment3 - Report and Defense (50 points)

##### Problem 1 and 2 - Number of Provinces (10 points)
* **Problem 1 (BFS)**: Traverses each unvisited component using a Queue (BFS), incrementing the counter for each traversal.
  * *Complexity*: Time $\mathcal{O}(n^2)$ | Space $\mathcal{O}(n)$.
* **Problem 2 (DSU)**: Uses Disjoint Set Union with Path Compression and Union by Rank. Iterates through the upper triangle ($j > i$) and unions connected cities.
  * *Complexity*: Time $\mathcal{O}(n^2 \cdot \alpha(n))$ | Space $\mathcal{O}(n)$.

##### Problem 3 and 4 - Min Cost Spanning Tree (10 points)
* **Problem 3 (Kruskal)**: Explicitly generates and sorts all $\mathcal{O}(V^2)$ edges, then adds them greedily using DSU to avoid cycles.
  * *Complexity*: Time $\mathcal{O}(V^2 \log V)$ | Space $\mathcal{O}(V^2)$ (edge list).
* **Problem 4 (Prim)**: Uses dense array-based Prim's algorithm with a `minDist` array without generating an edge list.
  * *Complexity*: Time $\mathcal{O}(V^2)$ | Space $\mathcal{O}(V)$.

##### Problem 5 and 6 - Software Dependency Cycles (10 points)
* **Problem 5 (Kosaraju)**: Two-pass algorithm: 1st DFS finds finishing times on the original graph; 2nd DFS traverses the transposed graph $G^T$.
  * *Complexity*: Time $\mathcal{O}(V + E)$ | Space $\mathcal{O}(V + E)$ (stores transposed graph).
* **Problem 6 (Tarjan)**: Single-pass DFS tracking discovery times `tin[u]`, lowest reachable times `low[u]`, and an explicit component stack.
  * *Complexity*: Time $\mathcal{O}(V + E)$ | Space $\mathcal{O}(V)$.

##### Problem 7 and 8 - Course Schedule (10 points)
* **Problem 7 (DFS)**: 3-state coloring (`0 = unvisited`, `1 = visiting / in stack`, `2 = visited`). Encountering state `1` indicates a back-edge (cycle).
  * *Complexity*: Time $\mathcal{O}(V + E)$ | Space $\mathcal{O}(V + E)$.
* **Problem 8 (Kahn)**: BFS topological sort using an in-degree array. Courses with in-degree 0 are queued. A cycle exists if processed count $< V$.
  * *Complexity*: Time $\mathcal{O}(V + E)$ | Space $\mathcal{O}(V + E)$.

##### Problem 9 and 10 - Course Schedule II (10 points)
* **Problem 9 (DFS)**: Reverse post-order traversal with cycle detection. Once a node finishes (`state = 2`), it is inserted into the result array from back to front (`order[idx--] = u`).
  * *Complexity*: Time $\mathcal{O}(V + E)$ | Space $\mathcal{O}(V + E)$.
* **Problem 10 (Kahn)**: Standard BFS topological sort. Dequeued nodes (in-degree 0) a
