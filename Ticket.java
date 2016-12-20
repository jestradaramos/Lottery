public class Ticket{
	private int tick_num;
	private int index;
	private int price;

	public Ticket(int index, int tick_num, int price){
		this.tick_num = tick_num;
		this.index    = index;
		this.price    = price;
	}
	
	// Getter Functions
	public int get_tick(){
		return tick_num;
	}

	public int get_index(){
		return index;
	}

	public int get_price(){
		return price;
	}
	
	// print_String will return Ticket in String format
	public void print_String(){
		System.out.println("Ticket Index: " + this.index); 
		System.out.println("Ticket Number: " + this.tick_num); 
		System.out.println("Price: " + this.price + "\n");
	} 

	/* Update function
		Will update the ticket with new information
		Return total money made afterwards 
	*/
	
	/* This will update if no new set is entered
		new_num: Last ticket num for the night
	*/
	public int update(int new_num){
		int profit;
		profit = (this.tick_num - new_num) * this.price;
		this.tick_num = new_num;
		return profit;
	}
	/* This will update if new set is made, but retain price
		start_num: The starting ticket number of new set
		end_num:   The last ticket number
	*/
	public int update(int start_num, int end_num){
		int profit;
		profit = (this.tick_num + (start_num - end_num)) * this.price;
		this.tick_num = end_num;
		return profit;
	}
	/* This will update if new set is made and there is a price change
		start_num:	The starting ticket number of new set
		end_num:	The last ticket number
		new_price:	Price of new set
	*/
	public int update(int start_num, int end_num, int new_price){
		int total_profit;
		int old_profit;
		int new_profit;
		
		old_profit = this.tick_num * this.price;
		new_profit = (start_num - end_num) * new_price;
		total_profit = old_profit + new_profit;
		
		this.tick_num = end_num;
		this.price = new_price;

		return total_profit;
	}

	
}
