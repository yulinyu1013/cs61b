/* SibTreeNode.java */

//package tree;

/**
 *  A SibTreeNode object is a node in a SibTree (sibling-based general tree).
 *  @author Jonathan Shewchuk
 */

class SibTreeNode extends TreeNode {

  /**
   *  (inherited)  item references the item stored in this node.
   *  (inherited)  valid is true if and only if this is a valid node in some
   *               Tree.
   *  myTree references the Tree that contains this node.
   *  parent references this node's parent node.
   *  firstChild references this node's first (leftmost) child.
   *  nextSibling references this node's next sibling.
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  /**
   *  ADT implementation invariants:
   *  1) if valid == true, myTree != null.
   *  2) if valid == true, then this is a descendent of myTree.root.
   *  3) if valid == true, myTree satisfies all the invariants of a
   *     SibTree (listed in SibTree.java).
   */

  protected SibTree myTree;
  protected SibTreeNode parent;
  protected SibTreeNode firstChild;
  protected SibTreeNode nextSibling;

  /**
   * Construct a valid SibTreeNode referring to a given item.
   */
  SibTreeNode(SibTree tree, Object item) {
    this.item = item;
    valid = true;
    myTree = tree;
    parent = null;
    firstChild = null;
    nextSibling = null;
  }

  /**
   * Construct an invalid SibTreeNode.
   */
  SibTreeNode() {
    valid = false;
  }

  /**
   *  children() returns the number of children of the node at this position.
   *  WARNING:  Does not run in constant time.  Actually counts the kids.
   */
  public int children() {
    if (isValidNode()) {
      int count = 0;
      SibTreeNode countNode = firstChild;
      while (countNode != null) {
        count++;
        countNode = countNode.nextSibling;
      }
      return count;
    } else {
      return 0;
    }
  }

  /**
   *  parent() returns the parent TreeNode of this TreeNode.  Throws an
   *  exception if `this' is not a valid node.  Returns an invalid TreeNode if
   *  this node is the root.
   */
  public TreeNode parent() throws InvalidNodeException2 {
    // REPLACE THE FOLLOWING LINE WITH YOUR SOLUTION TO PART I.
	  if(this.isValidNode()) {
		  if(this.parent!=null) {
			  return this.parent;
		  }else {
			  return new SibTreeNode();
		  }
		  
	  }else {
		  throw new InvalidNodeException2();
		  
	  }
    
  }

  /**
   *  child() returns the cth child of this TreeNode.  Throws an exception if
   *  `this' is not a valid node.  Returns an invalid TreeNode if there is no
   *  cth child.
   */
  public TreeNode child(int c) throws InvalidNodeException2 {
    if (isValidNode()) {
      if (c < 1) {
        return new SibTreeNode();
      }
      SibTreeNode kid = firstChild;
      while ((kid != null) && (c > 1)) {
        kid = kid.nextSibling;
        c--;
      }
      if (kid == null) {
        return new SibTreeNode();
      } else {
        return kid;
      }
    } else {
      throw new InvalidNodeException2();
    }
  }

  /**
   *  nextSibling() returns the next sibling TreeNode to the right from this
   *  TreeNode.  Throws an exception if `this' is not a valid node.  Returns
   *  an invalid TreeNode if there is no sibling to the right of this node.
   */
  public TreeNode nextSibling() throws InvalidNodeException2 {
    if (isValidNode()) {
      if (nextSibling == null) {
        return new SibTreeNode();
      } else {
        return nextSibling;
      }
    } else {
      throw new InvalidNodeException2();
    }
  }

  /**
   *  insertChild() inserts an item as the cth child of this node.  Existing
   *  children numbered c or higher are shifted one place to the right
   *  to accommodate.  If the current node has fewer than c children,
   *  the new item is inserted as the last child.  If c < 1, act as if c is 1.
   */
  public void insertChild(Object item, int c) throws InvalidNodeException2 {
    // FILL IN YOUR SOLUTION TO PART II HERE.
	  if(isValidNode()) {
		  if(children()==0) {//c<1
			  this.firstChild = new SibTreeNode(myTree,item);
			  this.firstChild.parent = this;
			  
		  }else if(children()<c) {		  
			  SibTreeNode copy = firstChild;
			  while(copy.nextSibling!=null) {
				  copy = copy.nextSibling;
			  }
			  SibTreeNode n = new SibTreeNode(myTree,item);
			  copy.nextSibling = n;
			  n.parent=this;
			  
		  }else{ // children()>=c
			  
			  if(c==1) {
				  SibTreeNode temp = firstChild;
				  firstChild = new SibTreeNode(myTree,item);
				  firstChild.parent = this;
				  firstChild.nextSibling = temp;
				  
			  }else {
				  SibTreeNode copy = firstChild;
				  while(c>2) {
					  copy = copy.nextSibling;
					  c--;
				  }
				  SibTreeNode n = new SibTreeNode(myTree,item);
				  n.nextSibling = copy.nextSibling;
				  n.parent = this;
				  copy.nextSibling = n;
				  
			  }

		  }
		  myTree.size++; //update tree size
		  
	  } else {
	      throw new InvalidNodeException2();
	    }
	  
  }

  /**
   *  removeLeaf() removes the node at the current position from the tree if
   *  it is a leaf.  Does nothing if `this' has one or more children.  Throws
   *  an exception if `this' is not a valid node.  If 'this' has siblings to
   *  its right, those siblings are all shifted left by one.
   */
  public void removeLeaf() throws InvalidNodeException2 {
    // FILL IN YOUR SOLUTION TO PART III HERE.
	  if(isValidNode()) {
		  if(children()==0) { //a leaf
			  valid = false;
			  
			  if(parent == null) {//a root
				  myTree.root=null;

				  
			  }else if(parent.firstChild==this) {
				  parent.firstChild = nextSibling;  
				  this.nextSibling = null;

			  }else {
				  SibTreeNode copy = parent.firstChild;
				  while(copy.nextSibling!=this) {
					  copy = copy.nextSibling;
				  }
				  copy.nextSibling = copy.nextSibling.nextSibling;

			  }
			  myTree.size--;
		  }
		  
		  
	  }else {
	      throw new InvalidNodeException2();
	  }
	  
  }

}
