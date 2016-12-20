import java.io.*;
public class Main{
	
	public static void main(String[] args) throws IOException{
		Ticket test = new Ticket(1,1,1);
		Directory ourDirect = new Directory("Data.txt");
		ourDirect.calculate();
		System.out.println(test.get_tick());
	}
}
