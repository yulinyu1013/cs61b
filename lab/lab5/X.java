package cs61b_lab_and_hw;

public class X {
	//field
	protected int x;
	public static final int N = 3;
	
	//constructor
	public X(int x) {
		this.x = x;
	}

	public void add(int i) {
		x = x + i;
	}
	
	public String toString() {
		return "" + this.x;
	}
}
