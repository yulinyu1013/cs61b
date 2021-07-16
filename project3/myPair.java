
public class myPair implements Comparable{
	
	protected Object v1;
	protected Object v2;
	protected int weight;
	
	
	public myPair(Object v1, Object v2, int weight) {
		this.v1=v1;
		this.v2=v2;
		this.weight=weight;
	}
	
	public Object getV1() {
		return this.v1;
		
	}
	
	public Object getV2() {
		return this.v2;
		
	}

	public int getWeight() {
		return this.weight;
	}
	
	
	public int compareTo(Object o) {//***override compareTo()
		  myPair p = (myPair) o;
		 
		  if(this.getWeight()==p.getWeight()) {
			  return 0;
		  }else if(this.getWeight()>p.getWeight()) {
			  return 1;
		  }else {
			  return -1;
		  }
		  
	  }

}
