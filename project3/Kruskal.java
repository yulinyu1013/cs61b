/* Kruskal.java */

import java.util.Hashtable;
import graph.*;
import list2.DList;
import list2.InvalidNodeException;
import list3.LinkedQueue;
import list3.QueueEmptyException;
import ListSorts.ListSorts;
import set.*;

/**
 * The Kruskal class contains the method minSpanTree(), which implements
 * Kruskal's algorithm for computing a minimum spanning tree of a graph.
 */

public class Kruskal {

  /**
   * minSpanTree() returns a WUGraph that represents the minimum spanning tree
   * of the WUGraph g.  The original WUGraph g is NOT changed.
 * @throws InvalidNodeException 
 * @throws QueueEmptyException 
   */
  public static WUGraph minSpanTree(WUGraph g) throws InvalidNodeException, QueueEmptyException {
	  
	  WUGraph mst = new WUGraph();
	  
	  //create a new graph that will have all the vertices in g;
	  Object[] vertices = g.getVertices();
	  
	  //collect all the edges;
	  LinkedQueue sortedEdges = new LinkedQueue();
	  
	  for(Object v : vertices) {
		 // add vertices
		  mst.addVertex(v);
		  
		  //collect neighbors
		  Neighbors n = g.getNeighbors(v);
	
		 for( int i=0; i<n.neighborList.length;i++) {
			  //store edge info
			 sortedEdges.enqueue(new myPair(v,n.neighborList[i],n.weightList[i]));
		  } 
	  }
	  
	  //sort edges based on weight
	  ListSorts.quickSort(sortedEdges);
	  
	  //find edges of T
	  DisjointSets edgesSet = new DisjointSets(g.vertexCount());//vertices that has been connected stay in the same set;
	  
	  Hashtable vertexHash = new Hashtable(g.vertexCount());
	  
	  for(int j = 0; j < g.vertexCount(); j++) {
		  vertexHash.put(vertices[j],j);
	  }
	  
	  while(!sortedEdges.isEmpty()) {
		  myPair p = (myPair) sortedEdges.dequeue();
		  
		  int v1 =  (int) vertexHash.get(p.getV1());
		  int v2 =  (int) vertexHash.get(p.getV2());
		  
		  int s1 = edgesSet.find(v1);
		  int s2 = edgesSet.find(v2);
		  
		  if(s1!=s2) {
			  mst.addEdge(p.getV1(), p.getV2(), p.getWeight());
			  edgesSet.union(s1, s2);
		  }

	  }
	   
	  
	  return mst;
  };

  
  
  
	  
  
	  
  
}
