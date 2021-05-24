// cameron campbell
// advanced java
// occc spring 2021
// puzzle reader program

import java.util.Scanner;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.Math;

/*
 *  /Users/Cameron/Desktop/Semester Materials/Java Homework/Unit 2/Homework 3 - Square Puzzle Reader/SquarePuzzle.txt
 */

public class PuzzleReader {
	
	
	/*
	 * the main method handles the user's input and passes it to the FileReader method.
	 * if the program is invoked through the command prompt with an accompanying argument,
	 * that is accepted and passed to the FileReader method.
	 */
	public static void main(String args[]) 
	{
		Scanner sc = new Scanner(System.in);
		String fileName;
		
		if(args.length == 1) 
		{
			fileName = args[0];
			FileReader(fileName);
		}
		else
		{
			System.out.println("Welcome to the Puzzle Reader Program!");
			System.out.println("Please give the file name of a puzzle you'd like to read: ");
			fileName = sc.nextLine();
			FileReader(fileName);
		}
	}
	
	
	/*
	 * the FileReader method reads the puzzle file passed to it, creates a character vector to
	 * store the puzzle values, then passes that vector to the puzzleValues method to be solved.
	 * if the file name passed is invalid or the IsValidValue method returns false when checking
	 * any character added to the vector, then an error will occur and the program will exit.
	 */
	static void FileReader(String fileName)
	{
		BufferedReader reader;
	    try
	    {
	    	reader = new BufferedReader(new FileReader(fileName));
	    	Vector<Character> puzzleValues = new Vector<Character>(0);
    		String line = reader.readLine();
	    	while(line != null) 
	    	{
	    		String strings[] = line.trim().split("\\s+");
	    		for(int i = 0; i < strings.length; i++)
		    	{
		    		char c = strings[i].charAt(0);
		    		if (IsValidValue(c)) 
		    		{
		    			puzzleValues.add(c);
		    		}
		    		else 
		    		{
		    			System.out.println("Your file contains an illegal value not supported by the Puzzle Reader.");
		    			throw new Exception();
		    		}
		    	}
	    		line = reader.readLine();
	    	} // writing of file to vectors has ended
	    	
	    	SolvePuzzle(puzzleValues);
	    }
	    catch(Exception e)
	    {
	    	System.out.println(e.toString());
	    	System.exit(0);
	    }
	  }
	
	
	/*
	 * the SolvePuzzle method first checks if the passed vector is a perfect square. if it is not,
	 * the program will notify the user, reject the vector, and exit. otherwise, it will begin the 
	 * solving process, creating a two-dimensional array with sizes equal to the perfect square root
	 * of the passed vector. the following for loop will then read each value in the vector. if the
	 * first (and for the purpose of the loop, current) element of the vector is a blank square, it is
	 * not added into the array, and in its place a formatted print statement will take its place so that
	 * the column will remain symmetrical. if the first element of the vector is not a blank square, it will be
	 * added to the current element of the column before being displayed via a formatted print statement.
	 * either way, the first element of the vector is removed so the next can take its place, and the loop
	 * continues until the table is fully mapped.
	 */
	static void SolvePuzzle(Vector<Character> passedPuzzle) 
	{
		if (IsPerfectSquare(passedPuzzle)) 
		{
			int puzzleRoot = (int) Math.sqrt(passedPuzzle.size());
			int [] [] puzzleTable = new int[puzzleRoot][puzzleRoot];
			
			for(int r = 0; r < puzzleTable.length; r++)
			{
				for(int c = 0; c < puzzleTable[r].length; c++)
				{
					if (passedPuzzle.get(0) == '-' || passedPuzzle.get(0) == '*')
					{
						System.out.printf("%4s", " ");
						passedPuzzle.remove(0);
					}
					else 
					{
						int convertedValue = Integer.valueOf(String.valueOf(passedPuzzle.get(0)), 36);
						puzzleTable[r][c] = convertedValue;
						passedPuzzle.remove(0);
						System.out.printf("%4d", puzzleTable[r][c]);
					}
				}
				System.out.println();
			}
		}
		else 
		{
			System.out.println("Your given puzzle is not a perfect square, and thus cannot be solved.");
	    	System.exit(0);
		}
	}
	
	
	/*
	 * the isPerfectSquare method determines if the passed vector's
	 * size is a perfect square by first finding the square root
	 * of the passed vector's size, then subtracting from it the integer
	 * closest to that square root. if the end result is 0, the method
	 * returns true. otherwise, it returns false.
	 */
	static boolean IsPerfectSquare(Vector<Character> passedPuzzle) 
	{
		double puzzleSquare = Math.sqrt(passedPuzzle.size());
		if((puzzleSquare - Math.floor(puzzleSquare)) == 0)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	/*
	 * the isValidValue method checks is the passed character falls under
	 * 'a-z', 'A-Z', '0-9', '-', or '*'. if it does, the method returns
	 * true. otherwise, the character is considered an illegal value in
	 * the context of the puzzle format, and it is returned false.
	 */
	static boolean IsValidValue(char passedChar) 
	{
		if ((passedChar >= 'a' && passedChar <= 'z') ||
		           (passedChar >= 'A' && passedChar <= 'Z') ||
		           (passedChar >= '0' && passedChar <= '9') ||
		           (passedChar == '-' || passedChar == '*'))
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
}
