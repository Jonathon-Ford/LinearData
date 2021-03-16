/* Jonathon Ford
 * Linear Data Structures
 * Dr. Gerald R. Heuring
 * 
 * This code takes in mail addresses from a file and sorts the information by zip code while
 *  implementing a given interface.
 */

//import the doobally do's
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class mailAdressSorter 
{
	
	//Set up the main to read and sort the files
	public static void main(String[]args) throws FileNotFoundException
	{
		//Ask for a filename
		System.out.println("What is the name of your input file?");
		Scanner input = new Scanner(System.in);
		String inputFileName = input.nextLine();
		
		//Create a new file that the addresses are pulled from
		File mail = new File (inputFileName);
		
		//Create a scanner to read the file
		Scanner scanMail = new Scanner(mail);
		
		
		//Read 6 lines of the file at a time and create an instance of the Mail Address, then save it to the list of addresses
		List<MailAddress> addressList = new ArrayList <MailAddress>();
		
		while (scanMail.hasNextLine())
		{
			try
			{
				addressList.add(new MailAddress(
						scanMail.nextLine(),
						scanMail.nextLine(),
						scanMail.nextLine(),
						scanMail.nextLine(),
						scanMail.nextLine(),
						Integer.parseInt(scanMail.nextLine())));
			}
			catch (NoSuchElementException notEnoughLines)
			{
				System.out.println("Either one or more addresses were less than 6 lines long.");
				notEnoughLines.printStackTrace();
				break;
			}
			catch (NumberFormatException zipNotFound)
			{
				System.out.println("When looking for a zip we did not find a number.");
				zipNotFound.printStackTrace();
				break;
			}
		}
		
		//Close the scanner once all the info is read
		scanMail.close();
		
		//Turn the array list into an array for the helper I guess and send it to the helper class
		MailAddress[] helperList = new MailAddress [addressList.size()];
		helperList = arrayListToArray(addressList, helperList);
		Project0Helper HelpMe = new Project0Helper();
		HelpMe.checkStartingOrder(helperList);
		
		//Sort the mail
		addressSort(addressList);
		
		//Check the sorted Mail
		helperList = arrayListToArray(addressList, helperList);
		HelpMe.checkFinalOrder(helperList);
		
		//Put the sorted information into a new file
		System.out.println("What would you Like to name the output file?");
		String outputFileName = input.nextLine();
		
		newFile(outputFileName, addressList);
		
		input.close();
		
	}
	
	
	
	
	/*
	 * Method for sorting the mail
	 * This method will create a 2d array list addressSortingBins of digits 0,1,2,3,4,5,6,7,8,9 wide, and unbounded long (because we do not know how many addresses we will have)
	 * It will go through the addressList and starting with the LSD of the zip, put all elements in the addressList into bins according to their number
	 * It will then pull out the elements back to addressList starting with 0 first and going to 9
	 * It will then repeat for all digits 
	 */
	public static void addressSort(List<MailAddress> toBeSorted)
	{
		//Create the 2d array
		List<List<MailAddress>> addressSortingBins = new ArrayList<List<MailAddress>>();
		
		//Create the 10 bins and add them to the 2d arrayList
		List<MailAddress> bin0 = new ArrayList<MailAddress>();
		List<MailAddress> bin1 = new ArrayList<MailAddress>();
		List<MailAddress> bin2 = new ArrayList<MailAddress>();
		List<MailAddress> bin3 = new ArrayList<MailAddress>();
		List<MailAddress> bin4 = new ArrayList<MailAddress>();
		List<MailAddress> bin5 = new ArrayList<MailAddress>();
		List<MailAddress> bin6 = new ArrayList<MailAddress>();
		List<MailAddress> bin7 = new ArrayList<MailAddress>();
		List<MailAddress> bin8 = new ArrayList<MailAddress>();
		List<MailAddress> bin9 = new ArrayList<MailAddress>();
		
		addressSortingBins.add(bin0);
		addressSortingBins.add(bin1);
		addressSortingBins.add(bin2);
		addressSortingBins.add(bin3);
		addressSortingBins.add(bin4);
		addressSortingBins.add(bin5);
		addressSortingBins.add(bin6);
		addressSortingBins.add(bin7);
		addressSortingBins.add(bin8);
		addressSortingBins.add(bin9);
		
		//This loop will go through the sent array and get a digit of the zip and put the address into a bin accordingly
		for(int i = 1; i <= 5; i++) //It will start with the 1st digit (LSD) and work its way to the 5th
		{
			for (int j = 0; j < toBeSorted.size(); j++) //it will loop through the entirety of the array
			{	
				addressSortingBins.get(toBeSorted.get(j).getZipCodeDigit(i)).add(toBeSorted.get(j));
			}

			
			//Reorder toBeSorted starting with bin0
			//Clear the array
			toBeSorted.clear();
			
			for(int k = 0; k < 10; k++)//Loop once for the 10 bins
			{
				for (int l = 0; l < addressSortingBins.get(k).size(); l++)//Loop until each bin is empty
				{
					toBeSorted.add(addressSortingBins.get(k).get(l));
				}
				
				//Clear the bins
				addressSortingBins.get(k).clear();
			}
		}
	}
	
	
	/*
	 * Method for creating a mail file and populating it with the sorted mail
	 * It will create a new file if one does not already exist
	 * It will then fill the file with the sorted addresses
	 */
	public static File newFile(String name, List<MailAddress> sortedMail)
	{
		//First create the file for the mail to be put into
		//Create a new file with the specified name
		File sortedFile = new File(name);
		
		try
		{	
			if (sortedFile.createNewFile())//If a new file was created let them know it was successful
			{
				System.out.println("File successfully created.");
			}
			else//If not let them know nothing happened
			{
				System.out.println("A file with that name already exists.");
			}
		}
		catch (IOException error)
		{
			System.out.println("Something went wrong.");
			error.printStackTrace();
		}
		
		//Fill the file with mail
		try
		{
			//Create the FileWriter
			FileWriter fill = new FileWriter(name);
			
			//loop the writer to write all the info in the array
			for(int i = 0; i < sortedMail.size(); i++)
			{
				fill.write(sortedMail.get(i).getName() + "\n");
				fill.write(sortedMail.get(i).getAddressLine1() + "\n");
				fill.write(sortedMail.get(i).getAddressLine2() + "\n");
				fill.write(sortedMail.get(i).getCity() + "\n");
				fill.write(sortedMail.get(i).getState() + "\n");
				fill.write(String.valueOf(sortedMail.get(i).getZipCode()));
				fill.write("\n");
			}
			//close the FileWriter once done with it
			fill.close();
		}
		catch (IOException error)
		{
			System.out.println("Something went wrong.");
			error.printStackTrace();
		}
		
		//Return the File
		return sortedFile;
	}

	/*
	 * Method for turning an ArrayList into an array for the project helper
	 */
	public static MailAddress[] arrayListToArray (List<MailAddress> toBeTurned, MailAddress[] helperList)
	{	
		//Set elements in the same order to the new array
		for(int i = 0; i < toBeTurned.size(); i++)
		{
			helperList[i] = toBeTurned.get(i);
		}
		
		return helperList;
	}
}
	