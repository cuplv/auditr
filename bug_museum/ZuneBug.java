import java.util.*;
import java.io.*; 
import java.util.Scanner;

public class ZuneBug {
    public static void main(String[] args) {
	String answer;
	boolean IsLeapYear;
	int days, year = 2008;
	int trials  = 0;
	
	Scanner in = new Scanner(System.in);
	
	System.out.println("Is it a Leap year? [y/n]");

	while(true) {
	    trials++;
	    if (trials > 10) {IsLeapYear = true; break;}
	    answer = in.nextLine().trim().toLowerCase();
	    if (answer.equals("y")) {
		IsLeapYear = true;
		break;
	    } else if (answer.equals("n")) {
		IsLeapYear = false;
		break;
	    } else {
		System.out.println("Sorry, I didn't catch that. Please answer y/n");
	    }
	}

	try 
	    {
		System.out.println("Enter the number of days: ");
		days = in.nextInt();
	    }
	catch(InputMismatchException exception) 
	    {
		System.out.println("Garbage In, garbage Out");
		days = 366;
	    }
	while (days > 365)
	    {
		if (IsLeapYear)
		    {
			if (days > 366)
			    {
				days -= 366;
				year += 1;
			    }
		    }
		else
		    {
			days -= 365;
			year += 1;
		    }
	    }
	
	System.out.print("Terminated..\n");
	
    }
}
