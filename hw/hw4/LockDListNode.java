package list;
public class LockDListNode extends DListNode{
	
	//inherit from DListNode
//	  public Object item;
//	  protected DListNode prev;
//	  protected DListNode next;
	
	public boolean lock;
	LockDListNode(Object i, DListNode p, DListNode n) {
		super(i, p, n);
		lock = false;
	}
	
	


	

}
