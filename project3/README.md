# Key Points

## Part 1 Create a Weighted Undirecting Graph

1. Basic Data Structures: 
  - Hashtable vertices: key is vertex, value is the edges related to the vertex that are stored as a DList(adjacency list)
  - Hashtable edges: key is edge, value is the edge DListNode in the DList(adjacency list)
  - DList vertexList: each node of the list refers to a DList(in other words, node.item = DList(adjacency list))
2. Augmented Data Structure to Get/Set Info of Edges, including:
  - partner
  - vertex 1
  - vertex 2
  - weight

## Part 2 Create Minimum Spanning Tree
1. Since VertexPair is hidden from outside, I created a new edge data structure (myPair) to store vertices and weight.
2. To collect all edges and sort them, I applied LinkedQueue and quickSort from hw8. One key point is to override the compareTo() in myPair so that the edges are sorted based on their weights.
3. To find the edges of minimum spanning trees, 
  - I created a disjointSets that has a length of number of vertices. Initially, all vertices are in separate sets. Once they are connected by edges, union the sets.
  - To map the vertices to the disjointSets, I created a Hashtable with vertices as the keys and 0~(number of vertices-1) as values.
