# Lottery
Program used to calculate daily Profit of lottery ticket sales.


Everyday my parents have to keep track of how many lottery tickets they sell everyday and how much money they earn everyday. 
This  program is to facilitate that process. The program holds a text file which hold all the information of what lottery
tickets are in stock. Each line contains info of each ticket. In each line the first number represents the ticket number in the
store. The second number represents the individual ticket number. The final number is the price of the ticket.


Each time the program is ran, the program reads this text file and creates a LinkedList of Objects, called tickets, that hold 
all of the information in text file. Then the program will ask you for some new info for each ticket. So, if you did  not sell
out of the tickets that are up, you just put in the new ticket number. It will update it in the text file and calculate how much
was sold of that ticket. If you did run out, you put in the starting number of the new set of tickets, and the final ticket 
number. If there was no difference in price between the new and old set then you are done. If there is a difference you just add
the new price. 


After giving all the data is will calculate total profit for the day and keep a separate text file containing the info of the 
changes in a History folder. 


I plan on turning this into a GUI so that it would be easier to use. Currently this is just ran from the terminal and can 
possibly cause problems for my parents who are not familiar with the terminal.
