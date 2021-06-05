package cs61b_lab_and_hw;

public class Y extends X implements Z{
	protected int y;
	
	public Y(int x) {
		super(x);
		this.y = 0;
	}
	
	
	public void add(int i) { //+2
		super.add(i);
		x = x + i;
	}

	
	public static void main(String[] args) {
		//PART I
	
		//(a)At compile-time, can we assign xa to ya, and vice versa?  When is a cast required?
		X[] xa = new X[] {new X(0),new X(1),new X(2)};
		Y[] ya = new Y[] {new Y(4),new Y(5),new Y(6)};
		xa = ya; //yes, y is an x
		ya = (Y[]) xa; //case required, because x is not always y
		
		//(b)  At run-time, if ya references an array of Y's, 
		X[] xa2 = new X[] {new X(0),new X(1),new X(2)};
		Y[] ya2 = new Y[] {new Y(4),new Y(5),new Y(6)};
		xa2 = ya2; //can we assign it to xa? yes, y is x
		ya2 = (Y[])xa2; //Can we then assign it back from xa to ya? yes with casting
		
		//(c) If xa references an array of X's (that are not Y's),   
		X[] xa3 = new X[] {new X(0),new X(1),new X(2)};
		Y[] ya3 = new Y[] {new Y(4),new Y(5),new Y(6)};
		//ya3 = (Y[]) xa3; //can we assign it to ya? no, runtime error
		//xa3 = ya3; // Can we then assign it back from ya to xa? yes
		
		X[] xa4 = new Y[] {new Y(4),new Y(5),new Y(6)};
		Y[] ya4 = new Y[] {new Y(4),new Y(5),new Y(6)}; //even if array of type X[] references objects that are all of class Y
		ya4 = (Y[]) xa4; //yes casting based on the static type
		xa4 = ya4; 
		
		//PART II
		
		//(a)  Will Java compile the result? YES
		Y y = new Y(1);
		System.out.println(y);
		y.add(1);
		System.out.println(y);
		System.out.println();
		
		//(b) What if the method declaration in the interface has a different return type? 
		//NO,The return type is incompatible with Z.add(int)
		
		
		//(c)  What if the method declaration in the interface has the same return type,
	    //but a signature with a different parameter type?
		//NO, Y needs to override that method to compile
		
		//(d)What if the method declaration in the interface has the same return type,
	    //and the same number of parameters and parameter types, but those
	    //parameters have different names?
		//YES
		
		
		//PART III
		
		//(a&b)  Will Java compile the result? NO, ambiguous
		//Does it make any difference whether the constant in the superclass 
		//and the constant in the interface have the same value? NO.
		//System.out.println(Y.N);
		
		//(c) 
//		System.out.println(X.N);
//		System.out.println(Z.N);
	
		//PART IV
		
		//(a) yes
		Y sub = new Y(1);
		System.out.println(sub);
		((X)sub).add(1);
		System.out.println(sub); //called subclass method
		System.out.println();
		
		//(b) NOT CALL ANY METHOD; supe not instance of y, cannot be cast. super class cannot know subclass method.
		X supe = new X(1);
		System.out.println(sub);
	//	((Y)supe).add(1);
		System.out.println(sub);
		System.out.println();
		
		//(c)
		//change the parameter type/number so that make the sub method a different one than super method.
		
		
		
		
		
		
	}
}


