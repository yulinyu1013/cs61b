/* Set.java */

import list2.*;

/**
 *  A Set is a collection of Comparable elements stored in sorted order.
 *  Duplicate elements are not permitted in a Set.
 **/
public class Set {
  /* Fill in the data fields here. */
	private List set;

  /**
   * Set ADT invariants:
   *  1)  The Set's elements must be precisely the elements of the List.
   *  2)  The List must always contain Comparable elements, and those elements 
   *      must always be sorted in ascending order.
   *  3)  No two elements in the List may be equal according to compareTo().
   **/

  /**
   *  Constructs an empty Set. 
   *
   *  Performance:  runs in O(1) time.
   **/
  public Set() { 
    // Your solution here.
	  set = new DList();
	  
	  
  }

  /**
   *  cardinality() returns the number of elements in this Set.
   *
   *  Performance:  runs in O(1) time.
   **/
  public int cardinality() {
    // Replace the following line with your solution.
    return set.length();
  }

  /**
   *  insert() inserts a Comparable element into this Set.
   *
   *  Sets are maintained in sorted order.  The ordering is specified by the
   *  compareTo() method of the java.lang.Comparable interface.
   *
   *  Performance:  runs in O(this.cardinality()) time.
   **/
  public void insert(Comparable c) {
    // Your solution here.
	  
	 if(set.isEmpty()) {
		 set.insertFront(c);
	 }else {
		 ListNode copy = set.front();
			 try {
				 while(copy.isValidNode()) {
					 if(c.compareTo(copy.item())<0) { //smaller than current one
						 copy.insertBefore(c);
						 break;
					 }else if (c.compareTo(copy.item())>0) { //greater than the current one
						 if(copy.next().isValidNode()) { // if next one is a valid node
							 copy = copy.next();
						 }else {
							 copy.insertAfter(c); 
						 }
						 	 
					 }else {
						 break;
					 }
				 }
				 
			 }catch(InvalidNodeException e) {
				 System.err.println(e);
				 
			 }
		 }
	 }

  /**
   *  union() modifies this Set so that it contains all the elements it
   *  started with, plus all the elements of s.  The Set s is NOT modified.
   *  Make sure that duplicate elements are not created.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Your implementation should NOT copy elements of s or "this", though it
   *  will copy _references_ to the elements of s.  Your implementation will
   *  create new _nodes_ for the elements of s that are added to "this", but
   *  you should reuse the nodes that are already part of "this".
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT ATTEMPT TO COPY ELEMENTS; just copy _references_ to them.
   **/
  public void union(Set s) {
    // Your solution here.
	  ListNode copy = s.set.front();
	  
	  try {
		  while(copy.isValidNode()) {
			  insert((Comparable)copy.item());
			  copy = copy.next();			  
		  }
	} catch (InvalidNodeException e) {
		System.err.println(e);
	}
	  
  }

  /**
   *  intersect() modifies this Set so that it contains the intersection of
   *  its own elements and the elements of s.  The Set s is NOT modified.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Do not construct any new ListNodes during the execution of intersect.
   *  Reuse the nodes of "this" that will be in the intersection.
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT CONSTRUCT ANY NEW NODES.
   *  DO NOT ATTEMPT TO COPY ELEMENTS.
   **/
  public void intersect(Set s) {
	  
	  if(set.isEmpty() || s.set.isEmpty()) {//if one of the two set is empty
		  set = new DList();
	  }else {
		  ListNode s1 = set.front(); 
		  ListNode s2 = s.set.front();
		  try {
			  while(s1.isValidNode()) {
				  while (s2.isValidNode()) {
					  if(((Comparable)s1.item()).compareTo(s2.item())==0) {
						  s1 = s1.next();
						  s2 = s2.next();
						  
						  if(!s1.isValidNode()) {
							  break;
						  }
						  
						  if(s1.isValidNode()&&!s2.isValidNode()) {
							  s1.remove();
						  }

						    
					  }else if(((Comparable)s1.item()).compareTo(s2.item())>0) {
						  s2 = s2.next();
						  if(!s2.isValidNode()) {
							  s1.remove();
						  }

					  }
					  else { //<0
						  s1 = s1.next();
						  s1.prev().remove();
					  } 
					  
				  }
				  

			  }		  
			  
		  }catch(InvalidNodeException e) {
			  System.err.println(e);
			  e.printStackTrace();
		  }
		  
		  
	  }
	 
	  
  }

  /**
   *  toString() returns a String representation of this Set.  The String must
   *  have the following format:
   *    {  } for an empty Set.  No spaces before "{" or after "}"; two spaces
   *            between them.
   *    {  1  2  3  } for a Set of three Integer elements.  No spaces before
   *            "{" or after "}"; two spaces before and after each element.
   *            Elements are printed with their own toString method, whatever
   *            that may be.  The elements must appear in sorted order, from
   *            lowest to highest according to the compareTo() method.
   *
   *  WARNING:  THE AUTOGRADER EXPECTS YOU TO PRINT SETS IN _EXACTLY_ THIS
   *            FORMAT, RIGHT UP TO THE TWO SPACES BETWEEN ELEMENTS.  ANY
   *            DEVIATIONS WILL LOSE POINTS.
   **/
  public String toString() {
    // Replace the following line with your solution.
	  String result = "{  ";
	  
	    ListNode current = set.front();
	    try {
			while (current.isValidNode()) {
			  result = result + current.item() + "  ";
			  current = current.next();

			}
		} catch (InvalidNodeException e) {
			System.err.println(e);
		}
	    return result + "}";
    
  }

  public static void main(String[] argv) {
	  System.out.println("Testing insert()");
	  Set s = new Set();
	  s.insert(new Integer(3));
	  s.insert(new Integer(4));
	  s.insert(new Integer(3));
	  System.out.println("Set s should be { 3 4 }: " + s);

	  Set s2 = new Set();
	  s2.insert(new Integer(4));
	  s2.insert(new Integer(5));
	  s2.insert(new Integer(5));
	  System.out.println("Set s2 should be { 4 5 }: " + s2);

	  Set s3 = new Set();
	  s3.insert(new Integer(5));
	  s3.insert(new Integer(3));
	  s3.insert(new Integer(8));
	  System.out.println("Set s3 should be { 3 5 8 }: " + s3);

	  System.out.println();
	  System.out.println("Tesing union()");
	  s.union(s2);
	  System.out.println("After s.union(s2), s should be { 3 4 5 }: " + s);
	  s2.union(s3);
	  System.out.println("After s2.union(s3), s2 should be { 3 4 5 8 }: " + s2);
	  Set s4 = new Set();
	  System.out.println("Empty set s4 = " + s4);
	  s.union(s4);
	  System.out.println("After s.union(s4), s should be { 3 4 5 }: " + s);
	  s4.union(s);
	  System.out.println("After s4.union(s), s4 should be { 3 4 5 }: " + s4);

	  System.out.println();
	  System.out.println("Tesing intersect()");
	  Set s5 = new Set();
	  Set s6 = new Set();
	  s6.insert(new Integer(1));
	  s5.intersect(s6);
	  System.out.println("{}.intersect({1}) should be { }: " + s5);
	  s6.intersect(s5);
	  System.out.println("{1}.intersect({}) should be { }: " + s6);
	  s6.insert(new Integer(1));
	  Set s7 = new Set();
	  s7.insert(new Integer(1));
	  s7.insert(new Integer(2));
	  s6.intersect(s7);
	  System.out.println("{1}.intersect({1 2}) should be { 1 }: " + s6);
	  s6.insert(new Integer(2));
	  s6.insert(new Integer(3));
	  s6.intersect(s7);
	  System.out.println("{1 2 3}.intersect({1 2}) should be { 1 2 }: " + s6);
	  s6.insert(new Integer(3));
	  s6.insert(new Integer(5));
	  s7.insert(new Integer(4));
	  s7.insert(new Integer(7));
	  s7.intersect(s6);
	  System.out.println("{1 2 4 7}.intersect({1 2 3 5}) should be { 1 2 }: " + s7);

	  System.out.println();
	  System.out.println("Tesing cardinality()");
	  System.out.println("s.cardinality() should be 3: " + s.cardinality());
	  System.out.println("s4.cardinality() should be 3: " + s4.cardinality());
	  System.out.println("s5.cardinality() should be 0: " + s5.cardinality());
	  System.out.println("s6.cardinality() should be 4: " + s6.cardinality());
	  System.out.println("s7.cardinality() should be 2: " + s7.cardinality());
	}
}
