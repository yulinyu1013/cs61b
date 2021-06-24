/* HashTableChained.java */

package dict;
import linkedList.*;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/
	int n; //actual number of entries we want to store
	public linkedList[] buckets; //buckets that stores linkedlist


  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
	  n = sizeEstimate;
	  
	  int bucketSize = (int) (n/0.75); //N: should be slightly >= n;
	  buckets = new linkedList[bucketSize];
	  
	  for(int i = 0; i< buckets.length; i++) { //create an empty list for each bucket
		  buckets[i] = new linkedList();
	  }
	  
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
	  buckets = new linkedList[101];
	  for(int i = 0; i< buckets.length; i++) {
		  buckets[i] = new linkedList();
	  }
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {	
//		int cf = ((2*code+3)%16908799) % buckets.length;
	int cf = code % buckets.length;
	if(cf < 0) {
	cf += buckets.length;
	}
	
    return cf;
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
	  int size = 0;
	  for(int i = 0; i< buckets.length; i++) {
			  size += buckets[i].size;
	  }
    return size;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
	  if(size()==0) {
		  return true;
	  }
	  return false;
    
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
	  int cf = compFunction(key.hashCode()); 
	  linkedListNode copy = buckets[cf].head; //copy a reference
	  if(buckets[cf].head==null) {
		  buckets[cf].head = new linkedListNode(key, value); 
		  buckets[cf].size++; 
		  return buckets[cf].head;
	  }else {
		  while(copy.next!=null) {
			  copy = copy.next;//walk to the last node;
		  }
		  copy.next = new linkedListNode(key, value); 
		  copy = copy.next;
	  }
	  buckets[cf].size++;
	  return copy; //valid because linkedListNode extends Entry; copy is an Entry
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
	  int cf = compFunction(key.hashCode()); 
	  linkedListNode copy = buckets[cf].head; //copy a reference
	  while(copy!=null) {
		  if(copy.key == key) {
			  return copy;
		  }
	  }
    return null;
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
	  int cf = compFunction(key.hashCode()); 
	  linkedListNode dummy = new linkedListNode(); //create a dummy head;
	  dummy.next = buckets[cf].head;
	  linkedListNode curr = dummy;
	  while(curr.next!=null) {
		  if(curr.next.key == key) {
			  linkedListNode removed = curr.next;
			  curr.next = curr.next.next;
			  dummy.next = null; //release the dummy head;
			  return removed;
		  }
	  }
	  
    return null;
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
	  for(int i=0;i<buckets.length;i++){
		 buckets[i].head=null;
		 buckets[i].size=0;
	  }
  }
  
  /**
   *  Count numbers of collisions in the table.
   */
  public int countCollision() {
	  int co = 0;
	  for(int i = 0; i< buckets.length; i++) {
		  if(buckets[i].size>1) {
			  co+=buckets[i].size-1;
		  }
	  }
    return co;
  }
  
  
  public String toString(){
	  String str = "";
	  for(int i=0;i<buckets.length;i++){ //for each bucket
		  str += "[";
		  str += buckets[i].size;
//		  print entries in each bucket:
//		  linkedListNode copy = buckets[i].head;
//		  while(copy!=null) {
//			  System.out.println("(" + copy.key + ", " + copy.value  + ") ");
//			  str += "(" + copy.key + ", " + copy.value  + ") ";
//			  copy = copy.next;
//		  }
		str += " ] ";
		
		if(i%10==9) {
			str+= "\n";
		}
	  }
	  return str;
}


}

