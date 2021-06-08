package list;

public class LockDList extends DList{
	
	
	public LockDList() {
		  head = new LockDListNode(Integer.MIN_VALUE, null, null);
		  head.next = head;
		  head.prev = head;
		  size = 0;
	}
	
	//override the method so we get LockDListNode instead of DListNode from insertion
	 public LockDListNode newNode(Object item, DListNode prev, DListNode next) {
		    return new LockDListNode(item, prev, next);
		  }
	 
	
	
	public void lockNode(DListNode node) {
		LockDListNode copy = (LockDListNode) head.next; //cast static type to sub class to access "lock" field
		int ct = size;
		  while(ct>0){
			  if(copy.item.equals(node.item)&&copy.next.equals(node.next)&&copy.prev.equals(node.prev)) {
				 copy.lock = true;
				 break;
			  }else {
				  ct--;
				  copy = (LockDListNode) copy.next;
			  }
		  }
	}
	

	 public void remove(DListNode node) {
		 LockDListNode copy = (LockDListNode) head.next; 
		 int ct = size;
		 while(ct>0){
			  if(copy.item.equals(node.item)&&copy.next.equals(node.next)&&copy.prev.equals(node.prev)) {
				  if(copy.lock==false) {
					  copy.prev.next = copy.next;
						 copy.next.prev = copy.prev;
						 copy.prev = null;
						 copy.next = null;
						 size--;
						 break;
				  }else {
					  break;
				  }
				 
			  }else { 
				  ct--;
				  copy = (LockDListNode) copy.next;
			  }
		  }
	 }
	 


}
