
import java.io.*;

public class Nuke2 {

	public static void main (String[] args) throws Exception{
		
		//read
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		System.out.flush();
		String inputLine = keyboard.readLine();
		
		//
		String modified = inputLine.substring(0,1) + inputLine.substring(2);
		System.out.println(modified);
	}
}
