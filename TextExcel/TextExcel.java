package textExcel;

// Update this file with your own code.

import java.util.Scanner;

public class TextExcel
{
	private static final String prompt = "enter command or quit";

	public static void main(String[] args)
	{
	    Spreadsheet spreadsheet = new Spreadsheet();
	    Scanner in = new Scanner(System.in);
		System.out.println(prompt);
	    String command = in.nextLine();
	    while(!(command.equals("quit"))) {
	    	System.out.println(spreadsheet.processCommand(command));
			System.out.println(prompt);
			command = in.nextLine();
		}
	}
}
