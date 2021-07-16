/* WUGraph.java */

package graph;

import java.util.Enumeration;
import java.util.Hashtable;
import list2.*; //import DList ADT from HW5;
/**
 * The WUGraph class represents a weighted, undirected graph.  Self-edges are
 * permitted.
 */

public class WUGraph {
	
	Hashtable edgeHash;
	Hashtable vertexHash;
	DList vertexList;
	
  /**
   * WUGraph() constructs a graph having no vertices or edges.
   *
   * Running time:  O(1).
   */
  public WUGraph() {
	  edgeHash = new Hashtable();
	  vertexHash = new Hashtable();
	  vertexList = new DList();
	  
  };

  /**
   * vertexCount() returns the number of vertices in the graph.
   *
   * Running time:  O(1).
   */
  public int vertexCount() {
	  return vertexHash.size();
	  
  };

  /**
   * edgeCount() returns the number of edges in the graph.
   *
   * Running time:  O(1).
   */
  public int edgeCount() {
	  return edgeHash.size();
	  
  };

  /**
   * getVertices() returns an array containing all the objects that serve
   * as vertices of the graph.  The array's length is exactly equal to the
   * number of vertices.  If the graph has no vertices, the array has length
   * zero.
   *
   * (NOTE:  Do not return any internal data structure you use to represent
   * vertices!  Return only the same objects that were provided by the
   * calling application in calls to addVertex().)
   *
   * Running time:  O(|V|).
   */
  public Object[] getVertices() throws InvalidNodeException {
	  
	  Object[] v = new Object[vertexCount()];
	  
	  if(v.length==0) {
		  return v;
	  }
	  Enumeration vs = vertexHash.keys();
	  int i = 0;
	  while(vs.hasMoreElements()) {
		  v[i]= vs.nextElement(); 
		  i++;
	  }
	  
	  return v;
	  
  };

  /**
   * addVertex() adds a vertex (with no incident edges) to the graph.  The
   * vertex's "name" is the object provided as the parameter "vertex".
   * If this object is already a vertex of the graph, the graph is unchanged.
   *
   * Running time:  O(1).
   */
  @SuppressWarnings("unchecked")
  public void addVertex(Object vertex) throws InvalidNodeException {
	  
	  if(!isVertex(vertex)) {
		  vertexList.insertFront(new DList());
		  vertexHash.put(vertex, vertexList.front()); //vertexList.front() is a DListNode;
	  }
	  
  };

  /**
   * removeVertex() removes a vertex from the graph.  All edges incident on the
   * deleted vertex are removed as well.  If the parameter "vertex" does not
   * represent a vertex of the graph, the graph is unchanged.
   *
   * Running time:  O(d), where d is the degree of "vertex".
   */
  public void removeVertex(Object vertex) throws InvalidNodeException { 
	  if(isVertex(vertex)) {
		  //find the value of the key
		  DList edgeList = (DList)((DListNode) vertexHash.get(vertex)).item();
		  int d = degree(vertex);
		  
		  if(d==0) {//no edges associated with vertex
			  ((DListNode) vertexHash.get(vertex)).remove();
			  vertexHash.remove(vertex);
			  return;
		  }
		  
		  DListNode edgeNode = (DListNode) edgeList.front(); 
		  while(edgeNode.isValidNode()) {//remove associated edges
			  removeEdge(edgeNode.getV1(), edgeNode.getV2());
			  edgeNode = (DListNode) edgeList.front();
		  }
		  
		  ((DListNode) vertexHash.get(vertex)).remove();
		  vertexHash.remove(vertex);
		  
	  }
  };

  /**
   * isVertex() returns true if the parameter "vertex" represents a vertex of
   * the graph.
   *
   * Running time:  O(1).
   */
  public boolean isVertex(Object vertex) {
	  if(vertexHash.containsKey(vertex)) {
		  return true;
	  }
	  return false;
  };

  /**
   * degree() returns the degree of a vertex.  Self-edges add only one to the
   * degree of a vertex.  If the parameter "vertex" doesn't represent a vertex
   * of the graph, zero is returned.
   *
   * Running time:  O(1).
 * @throws InvalidNodeException 
   */
  public int degree(Object vertex) throws InvalidNodeException {
	  if(!isVertex(vertex)) {
		  return 0;
	  }
	  
	  return((DList)((DListNode)vertexHash.get(vertex)).item()).length();
	  
  };

  /**
   * getNeighbors() returns a new Neighbors object referencing two arrays.  The
   * Neighbors.neighborList array contains each object that is connected to the
   * input object by an edge.  The Neighbors.weightList array contains the
   * weights of the corresponding edges.  The length of both arrays is equal to
   * the number of edges incident on the input vertex.  If the vertex has
   * degree zero, or if the parameter "vertex" does not represent a vertex of
   * the graph, null is returned (instead of a Neighbors object).
   *
   * The returned Neighbors object, and the two arrays, are both newly created.
   * No previously existing Neighbors object or array is changed.
   *
   * (NOTE:  In the neighborList array, do not return any internal data
   * structure you use to represent vertices!  Return only the same objects
   * that were provided by the calling application in calls to addVertex().)
   *
   * Running time:  O(d), where d is the degree of "vertex".
 * @throws InvalidNodeException 
   */
  public Neighbors getNeighbors(Object vertex) throws InvalidNodeException {
	  if(!isVertex(vertex)||degree(vertex)==0) {
		  return null;
	  }
	  
	  int d = degree(vertex);
	  
	  Neighbors n = new Neighbors();
	  n.neighborList = new Object[d];
	  n.weightList = new int[d];
	  DListNode edgeNodes = (DListNode) ((DList) ((DListNode) vertexHash.get(vertex)).item()).front();
	  
	  for(int i = 0; i < d; i++) {
		  n.weightList[i] = edgeNodes.getWeight();
		  if(edgeNodes.getV1()==edgeNodes.getV2()) {
			  n.neighborList[i] = vertex;
		  }else {
			  if(edgeNodes.getV1()!=vertex) {
				  n.neighborList[i]=edgeNodes.getV1();
			  }else {
				  n.neighborList[i]=edgeNodes.getV2();
			  }
		  }
		  edgeNodes = (DListNode) edgeNodes.next();
	  }
	  
	  return n;
	  
  };

  /**
   * addEdge() adds an edge (u, v) to the graph.  If either of the parameters
   * u and v does not represent a vertex of the graph, the graph is unchanged.
   * The edge is assigned a weight of "weight".  If the edge is already
   * contained in the graph, the weight is updated to reflect the new value.
   * Self-edges (where u == v) are allowed.
   *
   * Running time:  O(1).
   */
  public void addEdge(Object u, Object v, int weight) throws InvalidNodeException{
	  if(!isVertex(u)||!isVertex(v)) {
		  return;
	  }
	  
	  if(isEdge(u,v)) {
		  //update its weight
		  VertexPair existing = new VertexPair(u,v);
		  DListNode val = (DListNode)edgeHash.get(existing);
		  val.setWeight(weight);
		  val.getPartner().setWeight(weight);//don't forget the partner!

		  return;
	  }
	  
	  VertexPair newEdge = new VertexPair(u,v);
	  
	  //nodes in the vertex list
	  DListNode uNode = (DListNode) vertexHash.get(u);
	  DListNode vNode = (DListNode) vertexHash.get(v);
	  
	  //node.item is dlist
	  DList uList = (DList) uNode.item();
	  DList vList = (DList) vNode.item();
	 
	  if(u.equals(v)) {
		  uList.insertFront(newEdge);
		  uList.front().setV1(u);
		  uList.front().setV2(u);
		  uList.front().setWeight(weight);
		  uList.front().setPartner(uList.front());
	  }else {
		  uList.insertFront(newEdge);
		  uList.front().setWeight(weight);
		  uList.front().setV1(u);
		  uList.front().setV2(v);
		  
		  vList.insertFront(newEdge);
		  vList.front().setWeight(weight);
		  vList.front().setV1(u);
		  vList.front().setV2(v);
		  
		  uList.front().setPartner(vList.front());
		  vList.front().setPartner(uList.front());
	  }
	  
	  edgeHash.put(newEdge, uList.front());
  };

  /**
   * removeEdge() removes an edge (u, v) from the graph.  If either of the
   * parameters u and v does not represent a vertex of the graph, the graph
   * is unchanged.  If (u, v) is not an edge of the graph, the graph is
   * unchanged.
   *
   * Running time:  O(1).
 * @throws InvalidNodeException 
   */
  public void removeEdge(Object u, Object v) throws InvalidNodeException {
	  if(!isVertex(u)||!isVertex(v)||!isEdge(u,v)) {
		  return;
	  }
	  VertexPair e = new VertexPair(u,v);
	  DListNode toBeRemoved = (DListNode) edgeHash.get(e);
	  DListNode toBeRemovedPartner = (DListNode) toBeRemoved.getPartner();
	  toBeRemoved.remove();
	  
	  if(!toBeRemovedPartner.equals(toBeRemoved)) {//not self-edge; make sure to use .equals() rather than ==
		  toBeRemovedPartner.remove();
	  }
	  
	  edgeHash.remove(e);
	  
	  
  };

  /**
   * isEdge() returns true if (u, v) is an edge of the graph.  Returns false
   * if (u, v) is not an edge (including the case where either of the
   * parameters u and v does not represent a vertex of the graph).
   *
   * Running time:  O(1).
   */
  public boolean isEdge(Object u, Object v) {
	  if(!isVertex(u)||!isVertex(v)) {
		  return false;
	  }
	  
	  if(edgeHash.containsKey(new VertexPair(u,v))) {
		  return true;
	  }
	  
	  return false;
	  
  };

  /**
   * weight() returns the weight of (u, v).  Returns zero if (u, v) is not
   * an edge (including the case where either of the parameters u and v does
   * not represent a vertex of the graph).
   *
   * (NOTE:  A well-behaved application should try to avoid calling this
   * method for an edge that is not in the graph, and should certainly not
   * treat the result as if it actually represents an edge with weight zero.
   * However, some sort of default response is necessary for missing edges,
   * so we return zero.  An exception would be more appropriate, but
   * also more annoying.)
   *
   * Running time:  O(1).
 * @throws InvalidNodeException 
   */
  public int weight(Object u, Object v) throws InvalidNodeException {
	  if(isEdge(u,v)) {
		  return ((DListNode)edgeHash.get(new VertexPair(u,v))).getWeight();
	  }
	  
	  return 0;
  };

}
