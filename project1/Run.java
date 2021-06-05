
public class Run {

	short red;
	short blue;
	short green;
	public int runlength;
	Run prev;
	Run next;
	 
	public Run() { //sentinel
		red=blue=green=0;
		runlength=0;
		prev = null;
		next = null;
	}
	
	public Run(short red, short blue, short green, int runlength) {
		this.red=red;
		this.green=green;
		this.blue=blue;
		this.runlength = runlength;
		prev = null;
		next = null;
	}
	
	public String toString() {
		return " | " + " ( " + red + " , " + green + " , " + blue + " ), " + runlength + " | " + next;
	}
	
}
