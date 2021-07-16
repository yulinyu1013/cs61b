/* ListNode.java */

package list2;

/**
 *  A ListNode is a mutable node in a list.  No implementation is provided.
 *
 *  DO NOT CHANGE THIS FILE.
 **/

public abstract class ListNode {

  /**
   *  item references the item stored in the current node.
   *  myList references the List that contains this node.
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  protected Object item;
  protected List myList;
//for project 3:
  protected int weight;
  protected ListNode partner;
  protected Object v1;
  protected Object v2;

  /**
   *  isValidNode returns true if this node is valid; false otherwise.
   *  By default, an invalid node is one that doesn't belong to a list (myList
   *  is null), but subclasses can override this definition.
   *
   *  @return true if this node is valid; false otherwise.
   *
   *  Performance:  runs in O(1) time.
   */
  public boolean isValidNode() {
    return myList != null;
  }

  /**
   *  item() returns this node's item.  If this node is invalid,
   *  throws an exception.
   *
   *  @return the item stored in this node.
   *
   *  Performance:  runs in O(1) time.
   */
  public Object item() throws InvalidNodeException {
    if (!isValidNode()) {
      throw new InvalidNodeException();
    }
    return item;
  }

  /**
   *  setItem() sets this node's item to "item".  If this node is invalid,
   *  throws an exception.
   *
   *  Performance:  runs in O(1) time.
   */
  public void setItem(Object item) throws InvalidNodeException {
    if (!isValidNode()) {
      throw new InvalidNodeException();
    }
    this.item = item;
  }

  /**
   *  next() returns the node following this node.  If this node is invalid,
   *  throws an exception.
   *
   *  @return the node following this node.
   *  @exception InvalidNodeException if this node is not valid.
   */
  public abstract ListNode next() throws InvalidNodeException;

  /**
   *  prev() returns the node preceding this node.  If this node is invalid,
   *  throws an exception.
   *
   *  @param node the node whose predecessor is sought.
   *  @return the node preceding this node.
   *  @exception InvalidNodeException if this node is not valid.
   */
  public abstract ListNode prev() throws InvalidNodeException;

  /**
   *  insertAfter() inserts an item immediately following this node.  If this
   *  node is invalid, throws an exception.
   *
   *  @param item the item to be inserted.
   *  @exception InvalidNodeException if this node is not valid.
   */
  public abstract void insertAfter(Object item) throws InvalidNodeException;

  /**
   *  insertBefore() inserts an item immediately preceding this node.  If this
   *  node is invalid, throws an exception.
   *
   *  @param item the item to be inserted.
   *  @exception InvalidNodeException if this node is not valid.
   */
  public abstract void insertBefore(Object item) throws InvalidNodeException;

  /**
   *  remove() removes this node from its List.  If this node is invalid,
   *  throws an exception.
   *
   *  @exception InvalidNodeException if this node is not valid.
   */
  public abstract void remove() throws InvalidNodeException;
  
  
  //for project 3:
  
  /**
   *  setWeight() sets weight of this node.  If this node is invalid,
   *  throws an exception.
   *
   *  @exception InvalidNodeException if this node is not valid.
   */
  public void setWeight(int weight) throws InvalidNodeException{
	  this.weight=weight;
  };
  
  
  /**
   *  getWeight() gets weight of this node.  If this node is invalid,
   *  throws an exception.
   *
   *  @exception InvalidNodeException if this node is not valid.
   */
  public int getWeight() throws InvalidNodeException{
	  return this.weight;
  };
  
  /**
   *  getPartner() finds the node with same item.  If this node is invalid,
   *  throws an exception.
   *
   *  @exception InvalidNodeException if this node is not valid.
   */
  public ListNode getPartner() throws InvalidNodeException{
	  return this.partner;
  };
  
  /**
   *  setPartner() set the partner of the node.  If this node is invalid,
   *  throws an exception.
   *
   *  @exception InvalidNodeException if this node is not valid.
   */
  public void setPartner(ListNode partner) throws InvalidNodeException{
	  this.partner = partner;
  };
  
  public Object getV1() throws InvalidNodeException{
	  return this.v1;
  };
  
  public Object getV2() throws InvalidNodeException{
	  return this.v2;
  };
  
  public void setV1(Object v1) throws InvalidNodeException{
	  this.v1 = v1;
  };
  
  public void setV2(Object v2) throws InvalidNodeException{
	  this.v2 = v2;
  };

}







