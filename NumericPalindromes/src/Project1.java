/*
 * Jonathon Ford
 * Linear Data Structures
 * Dr.Gerald Heuring
 * Project 1
 * 
 * This program will allow a user to designate a range of numbers and will output numbers that are palindromes in both base 10 and 2
 */

//IMPORTant things
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Project1 {

	public static void main(String[] args) {
		//Create a scanner to read inputs for the range, get the desired range from the user, loop if not allowed
		long lowerBound;
		long upperBound;
		Scanner input = new Scanner(System.in);
		while(true){
		    System.out.println("Enter a lower bound:");
		    lowerBound = input.nextLong();
		    System.out.println("Enter an upper bound:");
		    upperBound = input.nextLong();
		    if(lowerBound < upperBound && lowerBound > 0){
		        break;
		    }
		    else if(lowerBound > upperBound){
		       System.out.println("Error: Lower Bound is greater or equal to Upper Bound, try again."); 
		    }
		    else if(lowerBound <= 0){
		    	System.out.println("Error: All values must be greater than 0, try again.");
		    }
		}
		input.close();
		
		
		
		
		//Create an ArrayList to save palindromes in base ten into
		List<Long> palindromesBase10 = new ArrayList<Long>();
		
		//Loop through the bounds and split the numbers into digits, save those numbers to an array and a stack, test
		//for palindromes and add them to the base10 palindrome list
		for(long i = lowerBound; i <= upperBound; i++) {
			
			//If the number is only 1 digit auto add it to the list of palindromes
			if(i >= 10) {
				
				//List and Stack of digits for binary
				List<Integer> digitList = new ArrayList<Integer>();
				Stack digitStack = new Stack();
				
				
				//Fill the List and Stack
				setListAndStackDecimal(i,digitList,digitStack);
				
				//Test the numbers for palindromes
				palindromeTest(i,digitList,digitStack,palindromesBase10);
				
			}else
				palindromesBase10.add(i);
		}
		

		
		//Create a final array with only palindromes for both decimal and binary
		List<Long> palindromesFinal = new ArrayList<Long>();
				
		for(int i = 0; i < palindromesBase10.size(); i++)
		{
			//Create a variable for the number being tested
			Long number = palindromesBase10.get(i);
					
			//Make the array and stack
			List<Integer> binaryDigitArray = new ArrayList<Integer>();
			Stack binaryDigitStack = new Stack();
			
			//Set the List and Stack
			setListAndStackBinary(number,binaryDigitArray,binaryDigitStack);
			
			//Test the binary digits and add them to the final array if it is indeed a palindrome
			palindromeTest(number,binaryDigitArray,binaryDigitStack,palindromesFinal);
		}
				
				
				
		//Print out the results
		System.out.println("Palindromes:");
		System.out.printf("%-30.30s  %-30.30s%n", "Decimal", "Binary");
		for(int i = 0; i < palindromesFinal.size(); i++)
		{
			System.out.printf("%-30.30s  %-30.30s%n", palindromesFinal.get(i) , Long.toString(palindromesFinal.get(i), 2));
		}
	}
	
	//This method innitalizes the stack and List for decimal
	public static void setListAndStackDecimal(Long number, List<Integer> digitList, Stack digitStack){
		
		while(number > 0) {
			int digit = (int) (number % 10);
			digitList.add(digit);
			digitStack.push(digit);
			number /= 10;
		}
	}
	
	//This method innitalizes the stack and List for binary
	public static void setListAndStackBinary(Long number, List<Integer> binaryDigitList, Stack binaryDigitStack) {
		
		
		//Convert the decimal number to a binary string with a handy method
		String binary = Long.toString(number, 2);
				
		for(int i = 0; i < binary.length(); i++)
		{
			binaryDigitList.add((int)binary.charAt(i));
			binaryDigitStack.push((int)binary.charAt(i));
		}
	}
	
	//This method tests for palindromes
	public static void palindromeTest(Long number, List<Integer> digitArray, Stack digitStack, List<Long> palindromes)
	{
		boolean isAPalindrome = true;
		
		//Loop through the digits of the number
		for(int i = 0; i < digitArray.size(); i++)
		{
			if(digitArray.get(i) != digitStack.pop())//Stay in the loop so long as the digits at the beginning of the array match the digits at the top of the stack
			{
				isAPalindrome = false;
				break;
			}
		}
		if(isAPalindrome) {
			palindromes.add(number);
		}
	}
}
		
