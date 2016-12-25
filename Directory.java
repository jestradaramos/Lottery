import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

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
		int earning = 0;
		System.out.println("Give input for each ticket as follows:");
		System.out.println("Enter ONLY final ticket number if no new blocks were set");
		System.out.println("Enter ONLY starting and ending ticket number if there was a new block w/ equal price");
		System.out.println("Enter only starting,ending, and new price if there was a new block w/ new price");
		for (int i = 0; i < this.directory.size(); i++){
			System.out.print("Input for Ticket " + (i + 1) + ": ");
			String input = sc.nextLine();
			String[] info = input.split("\\s+");

			if (info.length == 3){
				earning = directory.get(i).update(Integer.parseInt(info[0]), Integer.parseInt(info[1]), Integer.parseInt(info[2]));
				if (earning != -1){
					total += earning;
					System.out.println("Earnings: $" + total);
					toHistory(directory.get(i));
				}
				else {
					System.out.println("Invalid Entry. Try Again.");
					i--;
				}
			}
			else if (info.length == 2){
				earning = directory.get(i).update(Integer.parseInt(info[0]), Integer.parseInt(info[1]));
				if (earning != -1){
					total += earning;
					System.out.println("Earnings: $" + total);
					toHistory(directory.get(i));
				}
				else {
					System.out.println("Invalid Entry. Try Again.");
					i--;
				}
			}
			else if (info.length == 1){
				earning = directory.get(i).update(Integer.parseInt(info[0]));
				if (earning != -1) {
					total += earning;
					System.out.println("Earnings: $" + total);
					toHistory(directory.get(i));
				}	
				else {
					System.out.println("Invalid Entry. Try Again.");
					i--;
				}
			}
			else{
				System.out.println("Invalid Entry. Try Again.");
				i--;
			}
			
		}
	}	

	// Writing new file for history
	
	private void toHistory(Ticket aTicket){
		// History files will be given the date as a name{
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		File history = new File("History/" + timeStamp + ".txt");
		String report = aTicket.get_index() + " " + aTicket.get_tick() + " " + aTicket.get_price() + "\n";
		
		BufferedWriter bw = null;
		FileWriter fw = null;
		
		try{
			if  (!history.exists())
				history.createNewFile();

			fw = new FileWriter(history.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			
			bw.write(report);
			System.out.println("Recorded");
		}
		catch (IOException e){
			e.printStackTrace();
		}
		finally {
			try {
				
				if (bw != null)
					bw.close();
				
				if (fw != null)
					fw.close();
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	
	
	}
	
}
