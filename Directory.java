import java.util.*;
import java.io.*;

public class Directory{
	LinkedList<Ticket> directory = new LinkedList<Ticket>();

	/* This will full up our directory with tickets, 
		ticket information will be pulled from a text file
	*/
	public Directory(String aFile) throws IOException{
		File file = new File(aFile);
		
		// Elements that make an individual ticket
		int index;
		int tick_num;
		int price;
		
		// Element used for reading the *.txt file
		String line;
		String[] ticket;
		try{
			
			Scanner sc = new Scanner(new FileInputStream(file));
			while (sc.hasNextLine()){
				line = sc.nextLine();		
				ticket = line.split("\\s+"); // Parsing each line with any white space

				index = Integer.parseInt(ticket[0]);
				tick_num = Integer.parseInt(ticket[1]);
				price = Integer.parseInt(ticket[2]);
				// Place ticket into a linked list
				Ticket aTicket = new Ticket(index, tick_num, price);
				directory.add(aTicket);
			}
			sc.close();
		}
		catch(FileNotFoundException fnf){
			fnf.printStackTrace();
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("\nProgram terminated safely...");
		}
	}

	// Printing Elements within the LinkedList
	public void print_Ticket(int index){
		Ticket select =  this.directory.get(index);
		select.print_String();
		
	}

	public void print_all(){
		for (int i = 0; i < this.directory.size(); i++){
			print_Ticket(i);
		}		
	}

	// Now to handle all User interactions with Directory
	
	public void calculate(){
		Scanner sc = new Scanner(System.in);
		int total = 0;
		System.out.println("Give input for each ticket as follows:");
		System.out.println("Enter ONLY final ticket number if no new blocks were set");
		System.out.println("Enter ONLY starting and ending ticket number if there was a new block w/ equal price");
		System.out.println("Enter only starting,ending, and new price if there was a new block w/ new price");
		for (int i = 0; i < this.directory.size(); i++){
			System.out.print("Input for Ticket " + (i + 1) + ": ");
			String input = sc.nextLine();
			String[] info = input.split("\\s+");

			if (info.length == 3){
				total += directory.get(i).update(Integer.parseInt(info[0]), Integer.parseInt(info[1]), Integer.parseInt(info[2]));
				System.out.println("Earnings: $" + total);
			}
			else if (info.length == 2){
				total += directory.get(i).update(Integer.parseInt(info[0]), Integer.parseInt(info[1]));
				System.out.println("Earnings: $" + total);
			}
			else if (info.length == 1){
				total += directory.get(i).update(Integer.parseInt(info[0]));
				System.out.println("Earnings: $" + total);
			}
			else{
				System.out.println("Invalid Entry. Try Again.");
				i--;
			}
			
		}
	}	

}
