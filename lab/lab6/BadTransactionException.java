
public class BadTransactionException extends Exception{
	
	public int amt;
	
	public BadTransactionException(int badAmt) {
		super("Invalid deposite amount: " + badAmt);
		amt = badAmt;
	}
	

}
