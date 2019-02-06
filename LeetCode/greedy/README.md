## Greedy Algorithm

### Minimum Spanning Tree
#### DEFINITION
- Spanning Tree
  - Given a connected and undirected graph, a spanning tree of the graph is a subgraph that is atree and connects all the vertices together.
  - A single graph can have many spanning trees.
- Minimum Spanning Tree (MST)
  - For a weighted, connected and undirected graph, a spanning tree with weight less than or equal to the weight of every other spanning tree is MST.
  - The weight of spanning tree is the sum of weights given to each edge of the spanning tree.
  - How many edges does a MST has?
    - (V-1) edges where V is the number of vertices in the given graph
#### IMPLEMENTATION
##### Kruskal
1. Sort all the edges in non-decreasing order of their weight.
2. (**Union-Find**)Pick the smallest edge. Check if it forms a cycle with the spanning tree formed so far. If cycle is not formed, include this edge. Else, discard it.
3. Repeat step#2 until there are (V-1) edges in the spanning tree.
##### Prim
#### APPLICATIONS
MST problem: Given connected graph G with positive edge weights, find a min weight set of edges that connects all of the vertices.
MST is fundamental problem with diverse applications:
- **Network Design**
  - The standard application is to a problem like phone network design. You have a business with several offices;
  You want to lease phonne lines to connect them up with each otehr, and the phone company charges different amount of money to connect different pairs of cities.
  You want a set of lines that connects all your offices with a minimum total cost. It should be a spanning tree, since if a network isn't a tree you can always remove some edges and save money.
  
- **Approximation algorithms for NP-hard problem** - Travling Slaesperson Problem
  - A less obvious application is that the minimum spanning tree can be used to approximately solve the traveling salesman problem.
  A convenient formal way of deining the problem is to find the shortest path that visits each point at least once.
  - Note that if you have a path visiting all points exactly once, it's a special kind of tree. For instance, 12 of 16 spanning trees are actually paths. 
  If you have a path visiting some vertices more than once, you can always drop some edges to get a tree. So in general the MST weight is less the the TSP weight,
  because it's a minimization over a strictly larger set.
  - On the other hand, if you draw a path tracing around the MST, you trace each edge twice and visit all points, so the TSP weight is less than 
  twice the MST weight. Therefore, this tour is within a factor of two of optimal.
- **Indirect Applications**
  - Max bottleneck paths
  - LDPC codes for error correction
  - Image registration with Renyi entropy
  - Reducing data storage in sequencing amino acids in a protein
  - autoconfig protocol for Ethernet bridging to avoid cycles in a network.
- **Cluster Analysis**
  - K clustering problem can be viewed as finding an MST and deleting the k-1 most expensive edges.
