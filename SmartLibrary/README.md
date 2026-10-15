# SmartLibrary – Intelligent Digital Library Platform

A Java 17+ terminal application combining core library management with demonstrations of string matching, dynamic programming, max flow, optimization/approximation, randomized algorithms, parallel algorithms, and performance comparison. No frontend, web server, or REST API is used.

## Requirements
- JDK 17 or newer (tested source compatibility: Java 17; Java 21 also works)
- Maven 3.8+

## Run
From the `SmartLibrary` project root:
```bash
mvn clean test
mvn exec:java
```
Data files live in `data/`. The app seeds sample books when `books.csv` is empty/missing. The demo user is `U001`.

## Menu
1. Dashboard/statistics
2. Add/list/search/update/delete books
3. Search book text using KMP, Z-function, Rabin–Karp, suffix array/LCP, or suffix automaton
4. Reading-time budget planner (0/1 knapsack DP)
5. Borrow, return, reserve, history, and max-flow allocation simulation
6. P/NP concepts, exact knapsack, greedy 2-approximation vertex cover, and reduction explanation
7. Randomized QuickSort and Miller–Rabin
8. Sequential/parallel prefix sum and reductions
9. Compare KMP, Z, and Rabin–Karp on identical text
10. Save data; 0 exits after saving

## Algorithms and complexity
- **KMP:** O(n+m) time, O(m) prefix-table space. Supports overlapping matches and empty pattern (all boundaries).
- **Z-function:** O(n+m) time and O(n+m) memory for the concatenated pattern/separator/text.
- **Rabin–Karp:** expected O(n+m), worst-case O(nm); verifies hash candidates to handle collisions.
- **Suffix array/LCP:** pedagogical suffix sorting uses substring comparisons, O(n² log n) worst-case; LCP is O(n²) in this direct implementation. For production, use a linearithmic suffix-array algorithm.
- **Suffix automaton:** O(n) expected construction and O(m) query for typical hash-map transitions.
- **Book knapsack DP:** O(nB) time, O(B) DP value storage; reconstruction bookkeeping O(nB). Budget `B` is minutes and planner caps at 25 books.
- **Edmonds–Karp:** O(VE²) time; integer capacities.
- **Exact brute-force knapsack:** O(2ⁿ n), capped at 30 items.
- **Greedy vertex cover:** 2-approximation for unweighted vertex cover with the implemented edge-picking method.
- **Randomized QuickSort:** expected O(n log n), worst O(n²); deterministic seed makes demos reproducible.
- **Miller–Rabin:** probabilistic primality test; for independent uniform rounds, composite false-positive probability ≤ 4⁻ʳ. This demo uses seeded pseudo-random bases.
- **Parallel prefix sum:** chunked local scans plus offsets; parallel overhead means small arrays may run slower.

## Persistence and consistency
Books, borrowing history, and reservations are saved in CSV files. Borrowing rejects unavailable inventory and invalid users; the seeded demo user is `U001`. A reservation is marked fulfilled when a copy is returned. Avoid editing CSV files while the program is running.

## Benchmark methodology and limitations
The comparison runs each string matcher once using `System.nanoTime()` and checks whether result positions agree. This is an educational microbenchmark, not a statistically rigorous benchmark; JVM warm-up, GC, and machine load affect measurements. For serious performance claims, add warm-up, multiple trials, medians, and JMH. The current project includes a working 3-SAT-to-Vertex-Cover reduction explanation but not a full interactive graph-construction visualizer; DP-on-trees, interval DP, subset topic-cover DP, Dinic, and a full Ford–Fulkerson comparison are extensions rather than implemented menu modules. Unicode is processed as Java UTF-16 code units, not grapheme clusters.

## Project structure
`src/main/java/com/smartlibrary` contains model, service, repository, algorithm packages, benchmark, and `Main`. `src/test/java` contains JUnit 5 algorithm regression tests. `data/` contains sample CSV data.
