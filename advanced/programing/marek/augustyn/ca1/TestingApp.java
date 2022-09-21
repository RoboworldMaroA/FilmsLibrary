package advanced.programing.marek.augustyn.ca1;

/*
 * CA1 Algorithms and Advanced Programming
 * Marek Augustyn
 * 17.11.2021
 * 
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

//class TestingApp responsible for execute and Test film Library, 
//RUN THIS CLASS FOR TEST APP
public class TestingApp {

	// declare variable that you will be using in the methods
	static int filmID = 0;// variable to store film id
	static String title = ""; //variable to store film title
	static String description = "";//variable to store description
	static int releaseYear = 0;//variable to store release year
	static double rentalRate = 0.0;//variable to store rental Rate
	static int length = 0;//variable to store length
	static double replacementCost = 0.0;// variable to store replacement cost
	static String specialFeatures = "";//variable to store speacial features
	static String inputNewFilm = "";// variable to store input
	static Scanner sc = new Scanner(System.in);// initial Scanner object used later in the validation input from user
	static Scanner sc2 = new Scanner(System.in);//initial Scanner object used later in the validation input from user

	// method saveRecord responsible for save another record in the csv file
	public static void saveRecord(int filmID, String title, String description, int releaseYear, double rentalRate,
			int length, double replacementCost, String specialFeatures, String filepath) {
		try {
			FileWriter fw = new FileWriter(filepath, true);//write data from filepath
			BufferedWriter bw = new BufferedWriter(fw);// buffer them
			PrintWriter pw = new PrintWriter(bw);//write data to the file
			pw.println(filmID + "," + title + "," + description + "," + releaseYear + "," + rentalRate + "," + length
					+ "," + replacementCost + "," + specialFeatures);
			pw.flush();//method Print Writer to clear the stream 
			pw.close();
			System.out.println(
					"Record saved to the file called: \\\"film_data.csv\\\" located in the main folder app CA1");
		} catch (Exception E) {
			System.out.println("Record not saved");
		}
		
		
		
	}// end method saveRecord

	// printing method to print all elements from array Film
	private static void printResutsArray(Film[] list) {
		for (int i = 0; i < list.length; i++) {
			System.out.println(list[i].toString());
		} // end for loop
	}// end printing

	
	
	//For testing Application only
//	// printing method to print 10 elements from array Film
//	private static void print10ResutsArray(Film[] list) {
//		for (int i = 0; i < 10; i++) {
//			System.out.println(list[i].toString());
//		}
//	}// end printing 10 elements

	// ******************************************************************************************************************************
	// ******************************************************************************************************************************
	// ******************************************************************************************************************************
	// ******************************************************************************************************************************
	// ********************************************************************************************************************************

	// MAIN METHOD
	public static void main(String[] args) throws IOException {

		// parsing and reading the CSV file data into the film (object) array provide
		// the path here...
		/*
		 * For testing APP only Scanner sc = new Scanner(new
		 * File("C:\\Users\\Maro\\Desktop\\Software Development course NCI\\Algoritms and Advanced Programing\\CA1/film_data.csv"
		 * ));
		 */
		
		
		Scanner sc = new Scanner(new File("C:\\Users\\Maro\\AlgoritmsAndAdvancedPrograming\\CA1\\originalFilmData/film_data.csv"));

		
		// You can not use the same location if you want to store more data then initial
		// array, because original
		// array has 1000 element and if you want to add 1002 element then will be error
		// Scanner sc = new Scanner(new File("film_data.csv"));

		Film[] films = new Film[1000];// we have 1000 records
		// first element is Title
		// this will just print the header in CSV file
		sc.nextLine();

		int i = 0;
		String st = "";
		while (sc.hasNextLine()) // while scanner returns a boolean value true then do the task in loop else exit
		{
			st = sc.nextLine();
			String[] data = st.split(",");// split values by ,
			// create film object
			// element on index 1 is a Title
			films[i] = new Film(Integer.parseInt(data[0]), data[1], data[2], Integer.parseInt(data[3]),
					Double.parseDouble(data[4]), Integer.parseInt(data[5]), Double.parseDouble(data[6]), data[7]);
			i++;// increment element

		}

		//System.out.println(sc.hasNextLine());
		// I did not close
		// sc.close(); // closes the scanner
        
		//Print list of films before sorting
		printResutsArray(films);
		
	
		
		// QUCKSORT algorithm
		System.out.println("******************************************************************************************");
		System.out.println("Part 1: Task One. Quick sort");
		System.out.println("Write a sorting algoritm (algoritm A) that sorts the film data using the \"title\" field");
		System.out.println("******************************************************************************************");

		// use method quickSort located in the class Sorting
		Sorting.quickSortObject(films, 0, films.length - 1);
		

		System.out.println("This is list of the films using Quick Sort Algoritm. It is 1000 film records sorted in ascending order by title: ");
		System.out.println("******************************************************************************************");
		// method used to print elements in array 
		printResutsArray(films);
		// print 1000 elements sorted array
		System.out.println("Finished printing using Qick Sort above this line"); /// end test Quick Sort
		// ***********************************************************************************************************

		System.out.println("******************************************************************************************");
		System.out.println("I did Merge Sort algoritm to compare with Quck sort");
		Sorting.mergeSortObj(films, 0, films.length - 1);
		System.out.println("******************************************************************************************");
		System.out.println("This is list of the films using Merge Sort Algoritm. It is 1000 film records sorted in ascending order by title: ");
		System.out.println("******************************************************************************************");

		//print results for Merge Sort
		//printResutsArray(films); for test only
		
		
		System.out.println("******************************************************************************************");
		System.out.println("Part 1: Task two");
		System.out.println("Experimentally analyse the time complexity of your sorting algorithm you wrote for task 1 above.");
		System.out.println("Show your results by taking the elapsed time(in nanosecond) for 10, 100. 500, 1000 record");
		System.out.println("**********************************************");

		System.out.println("Mergesort analysis");
		
		// print and calculate cost of Merge Sort for 10 records
		long startTimeTenRecords = System.nanoTime();
		Sorting.mergeSortObj(films, 0, 9);// sorting 10 elements
		long endTimeTenRecords = System.nanoTime();
		long elapsedTimeForTenRecords = (endTimeTenRecords - startTimeTenRecords);
		System.out.println("Cost of mergeSort for 10 record= " + elapsedTimeForTenRecords+" nanoseconds.");
		//print10ResutsArray(films); for test only
		
		// 100 records elapsed time
		long startTime100Records = System.nanoTime();
		Sorting.mergeSortObj(films, 0, 99);// sorting 100 elements
		long endTime100Records = System.nanoTime();
		long elapsedTimeFor100Records = (endTime100Records - startTime100Records);
		System.out.println("Cost of mergeSort for 100 record= " + elapsedTimeFor100Records+" nanoseconds.");

		// 500 records elapsed time
		long startTime500Recors = System.nanoTime();

		Sorting.mergeSortObj(films, 0, 499);
		long endTime500Recors = System.nanoTime();
		long elapsedTimeFor500Records = (endTime500Recors - startTime500Recors);
		System.out.println("Cost of mergeSort for 500 record= " + elapsedTimeFor500Records+" nanoseconds.");

		// 1000 records elapsed time
		long startTime1000Records = System.nanoTime();

		Sorting.mergeSortObj(films, 0, films.length - 1);
		long endTime1000Records = System.nanoTime();
		long elapsedTimeFor1000Records = (endTime1000Records - startTime1000Records);
		System.out.println("Cost of mergeSort for 1000 record= " + elapsedTimeFor1000Records+" nanoseconds.");

			
		//QUCK SORT ANALYSIS
		System.out.println("Qucksort analysis");
		// print and calculate cost of Quick Sort for 10 records
		long startTimeTenRecordsQS = System.nanoTime();
		Sorting.quickSortObject(films, 0, 9);// sorting 10 elements
		long endTimeTenRecordsQS = System.nanoTime();
		long elapsedTimeForTenRecordsQS = (endTimeTenRecordsQS - startTimeTenRecordsQS);
		System.out.println("Cost of QuickSort for 10 records= " + elapsedTimeForTenRecordsQS+" nanoseconds.");
			
		// 100 records elapsed time
		long startTime100RecordsQS = System.nanoTime();
		Sorting.quickSortObject(films, 0, 99);// sorting 100 elements
		long endTime100RecordsQS = System.nanoTime();
		long elapsedTimeFor100RecordsQS = (endTime100RecordsQS - startTime100RecordsQS);
		System.out.println("Cost of QuickSort for 100 records= " + elapsedTimeFor100RecordsQS+" nanoseconds.");

		// 500 records elapsed time
		long startTime500RecorsQS = System.nanoTime();

		Sorting.quickSortObject(films, 0, 499);
		long endTime500RecorsQS = System.nanoTime();
		long elapsedTimeFor500RecordsQS = (endTime500RecorsQS - startTime500RecorsQS);
		System.out.println("Cost of QuickSort for 500 records= " + elapsedTimeFor500RecordsQS+" nanoseconds.");

				
		// 1000 records elapsed time for Quick Sort
		long startTime1000RecordsQuick = System.nanoTime();
		Sorting.quickSortObject(films, 0, films.length - 1);//use quick sort to sort data
		long endTime1000RecordsQuick = System.nanoTime();
		long elapsedTimeFor1000RecordsQuick = (endTime1000RecordsQuick - startTime1000RecordsQuick);
		System.out.println("Cost of QuickSort for 1000 records= " + elapsedTimeFor1000RecordsQuick+" nanoseconds.");

		System.out.println("**********************************************");
		System.out.println("Part 1: Task tree.");
		System.out.println("Write an efficient searching algorith that accept the title of the film and searches the film record from dataset.");
		System.out.println("If no film is found, display appropiate message eg., \"Film not found!\".");
		System.out.println("**********************************************");

		String SearchKey; // initialize variable to hold input from user, looking for title of the movie

		// Input - garb data from user and
		System.out.println("This algorithm is using method Binary Search implemented in the class Sorting.");
		System.out.println("Enter Title of the movie that you want to find in the library, please:");
		
		//variable searchKey used to grab data from user and then pass to the Sorting class using Search method implemented in this class
		boolean validSearchKey = false;
		do {
			System.out.print("Please enter a valid searched film name: ");
			SearchKey = sc2.nextLine();
			if (SearchKey.isEmpty()) {
				System.out.println("Input can not be empty string");
				validSearchKey = false;
			} else {
				validSearchKey = true;
			}
		} while (!validSearchKey);
		System.out.println("Thank you...");
		System.out.println("I am checking database for a film: " + SearchKey);


		// **************************************************************************************************************************************
		// Search for element using BIANRY SEARCH Recursively for 1000 elements
		int binRec = Sorting.binarySearchRec(films, 0, films.length - 1, SearchKey);// variable returned from Search
		// algorithm using Binary Search
		System.out.println("Index for searched Title of the movie  is: " + binRec);
        //if result from searching algoritzm is -1 then pront that film was not found if is found print a film details and location
		if (binRec == -1) {
			System.out.println("Binary Search Recursively");
			System.out.println("Film not found!");
		} else {
			System.out.println("Binary Search Recursively");
			System.out.println("Film has found on index: " + binRec);
			System.out.println(films[binRec]);
		}

		System.out.println("************************************************************************************************************************");
		System.out.println("Part 1: Task four.");
		System.out
				.println("Analyse and determinate the time complexity of the searching algorithm you write in task 3.");
		System.out
				.println("Show your result by taking the elapset time (in nanoseconds) for 10, 100, 500,1000 records");
		System.out.println("************************************************************************************************************************");

		
		
		// grab searchKey variable from the user using Scanner and validate the input
		boolean validSearchKeyAgain = false;
		do {
			System.out.print("Please enter a valid searched film name for task 4: ");
			SearchKey = sc2.nextLine();
			if (SearchKey.isEmpty()) {
				System.out.println("Input can not be empty string");
				validSearchKeyAgain = false;
			} else {
				validSearchKeyAgain = true;
			}
		} while (!validSearchKeyAgain);
		System.out.println("Thank you...");
		System.out.println("I am checking database for a film: " + SearchKey);
	
	
		// Search 10 elements in sorted list of the films using Binary Search (recursively)
		// start counting
		long startTimeSearchTenRecords = System.nanoTime();
		int numbersOf10Records = 10;
		int binRecTenRecords = Sorting.binarySearchRec(films, 0, numbersOf10Records - 1, SearchKey);//
		//System.out.println("Index for searched Title of the movie  is: " + binRecTenRecords);//for test only
		if (binRecTenRecords == -1) {
			System.out.println("Binary Search Recursively");
			System.out.println("Film not found!");
		} else {
			System.out.println("Binary Search Recursively");
			System.out.println("Film has found on index: " + binRecTenRecords);
			System.out.println(films[binRecTenRecords]);//print index from the main library what match to searched film
		}
		// end counting
		long endTimeSearchTenRecords = System.nanoTime();
        //calculate elapsed Time
		long elapsedTimeForSearchTenRecords = (endTimeSearchTenRecords - startTimeSearchTenRecords);
		System.out.println("Cost of BinarySearchSort for 10 records= " + elapsedTimeForSearchTenRecords+" nanoseconds.");

		
		// Search 100 elements in sorted list of the films using Binary Search (recursively)
		long startTimeSearch100Recors = System.nanoTime();
		// number of record you want to sort in the film.csv
		int numbersOfRecords100 = 100;
		// return values from binary search algorithm
		int binRec100Records = Sorting.binarySearchRec(films, 0, numbersOfRecords100 - 1, SearchKey);
		//System.out.println("Index for searched Title of the movie  is: " + binRec100Records);//for test app only
		if (binRec100Records == -1) {
			System.out.println("Binary Search Recursively");
			System.out.println("Film not found!");
		} else {
			System.out.println("Binary Search Recursively");
			System.out.println("Film has found on index: " + binRec100Records);
			System.out.println(films[binRec100Records]);
		}
		// stop counting
		long endTimeSearch100Recors = System.nanoTime();
		//calculate elapsed time
		long elapsedTimeForSearch100Recors = (endTimeSearch100Recors - startTimeSearch100Recors);
		System.out.println("Cost of BinarySearchSort for 100 records= " + elapsedTimeForSearch100Recors+" nanoseconds.");

		
		// Search 500 elements in sorted list of the films using Binary Search (recursively)
		long startTimeSearch500Recors = System.nanoTime();
		// input number of record you want to sort in the film.csv
		int numbersOfRecords500 = 500;
		//return value searched film from 500 records
		int binRec500Records = Sorting.binarySearchRec(films, 0, numbersOfRecords500 - 1, SearchKey);
		//System.out.println("Index for searched Title of the movie  is: " + binRec500Records);//for test only
		if (binRec500Records == -1) {
			System.out.println("Binary Search Recursively");
			System.out.println("Film not found!");
		} else {
			System.out.println("Binary Search Recursively");
			System.out.println("Film has found on index: " + binRec500Records);
			System.out.println(films[binRec500Records]);
		}
		// end counting
		long endTimeSearch500Recors = System.nanoTime();
		//calculating time for search element in 500 films
		long elapsedTimeForSearch500Recors = (endTimeSearch500Recors - startTimeSearch500Recors);
		System.out.println("Cost of BinarySearchSort for 500 records = " + elapsedTimeForSearch500Recors+" nanoseconds."); 
		
		
		// Search 1000 elements in sorted list of the films using Binary Search (recursively)
		long startTimeSearch1000Recors = System.nanoTime();
		// number of record you want to sort in the film.csv
		int numbersOfRecords1000 = 1000;
		//variable returned from search algorithm 
		int binRec1000Records = Sorting.binarySearchRec(films, 0, numbersOfRecords1000 - 1, SearchKey);
		//System.out.println("Index for searched Title of the movie  is: " + binRec1000Records);For test app only
		if (binRec1000Records == -1) {
			System.out.println("Binary Search Recursively");
			System.out.println("Film not found!");
		} else {
			System.out.println("Binary Search Recursively");
			System.out.println("Film has found on index: " + binRec1000Records);
			System.out.println(films[binRec1000Records]);
		}
		// end counting
		long endTimeSearch1000Recors = System.nanoTime();
		long elapsedTimeForSearch1000Recors = (endTimeSearch1000Recors - startTimeSearch1000Recors);
		System.out.println("Cost of BinarySearchSort for 1000 records= " + elapsedTimeForSearch1000Recors+" nanoseconds.");

		
		
		//BIANRY SEARCH ALGORITHMS - method in class Sorting binarySearch2()
		System.out.println("*************");	
		System.out.println("BINARY SEARCH");
		System.out.println("*************");	
		// Search 10 elements in sorted list of the films using Binary Search 
		// start counting
		long startTimeSearchTenRecords2 = System.nanoTime();
		
		int binRecTenRecords2 = Sorting.binarySearch2(films,10, SearchKey);//
		System.out.println("Index for searched Title of the movie  is: " + binRecTenRecords);//for test only
		if (binRecTenRecords2 == -1) {
			System.out.println("Binary Search ");
			System.out.println("Film not found!");
		} else {
			System.out.println("Binary Search");
			System.out.println("Film has found on index: " + binRecTenRecords2);
			System.out.println(films[binRecTenRecords2]);
		}
		// end counting
		long endTimeSearchTenRecords2 = System.nanoTime();
        //calculate elapsed Time
		long elapsedTimeForSearchTenRecords2 = (endTimeSearchTenRecords2 - startTimeSearchTenRecords2);
		System.out.println("Cost of BinarySearchSort for 10 records= " + elapsedTimeForSearchTenRecords2+" nanoseconds.");
       
		
		// Search 100 elements in sorted list of the films using Binary Search
		long startTimeSearch100Recors2 = System.nanoTime();
		// number of record you want to sort in the film.csv
		int numbersOfRecords1002 = 100;
		// return values from binary search algorithm
		int binRec100Records2 = Sorting.binarySearch2(films, numbersOfRecords1002, SearchKey);
		//System.out.println("Index for searched Title of the movie  is: " + binRec100Records);//for test app only
		if (binRec100Records2 == -1) {
			System.out.println("Binary Search");
			System.out.println("Film not found!");
		} else {
			System.out.println("Binary Search");
			System.out.println("Film has found on index: " + binRec100Records2);
			System.out.println(films[binRec100Records2]);
		}
		// stop counting
		long endTimeSearch100Recors2 = System.nanoTime();
		//calculate elapsed time
		long elapsedTimeForSearch100Recors2 = (endTimeSearch100Recors2 - startTimeSearch100Recors2);
		System.out.println("Cost of BinarySearchSort for 100 records= " + elapsedTimeForSearch100Recors2+" nanoseconds.");

		
		// Search 500 elements in sorted list of the films using Binary Search
		long startTimeSearch500Recors2 = System.nanoTime();
		// input number of record you want to sort in the film.csv
		int numbersOfRecords5002 = 500;
		//return value searched film from 500 records
		int binRec500Records2 = Sorting.binarySearch2(films, numbersOfRecords5002, SearchKey);
		//System.out.println("Index for searched Title of the movie  is: " + binRec500Records2);//for test only
		if (binRec500Records2 == -1) {
			System.out.println("Binary Search");
			System.out.println("Film not found!");
		} else {
			System.out.println("Binary Search");
			System.out.println("Film has found on index: " + binRec500Records2);
			System.out.println(films[binRec500Records2]);
		}
		// end counting
		long endTimeSearch500Recors2 = System.nanoTime();
		//calculating time for search element in 500 films
		long elapsedTimeForSearch500Recors2 = (endTimeSearch500Recors2 - startTimeSearch500Recors2);
		System.out.println("Cost of BinarySearchSort for 500 records = " + elapsedTimeForSearch500Recors2+" nanoseconds."); 
		
		
		// Search 1000 elements in sorted list of the films using Binary Search
		long startTimeSearch1000Recors2 = System.nanoTime();
		// number of record you want to sort in the film.csv
		int numbersOfRecords10002 = films.length-1;
		//variable returned from search algorithm 
		int binRec1000Records2 = Sorting.binarySearch2(films,numbersOfRecords10002, SearchKey);
		//System.out.println("Index for searched Title of the movie  is: " + binRec1000Records2);For test app only
		if (binRec1000Records2 == -1) {
			System.out.println("Binary Search");
			System.out.println("Film not found!");
		} else {
			System.out.println("Binary Search");
			System.out.println("Film has found on index: " + binRec1000Records2);
			System.out.println(films[binRec1000Records2]);
		}
		// end counting
		long endTimeSearch1000Recors2 = System.nanoTime();
		long elapsedTimeForSearch1000Recors2 = (endTimeSearch1000Recors2 - startTimeSearch1000Recors2);
		System.out.println("Cost of BinarySearchSort for 1000 records= " + elapsedTimeForSearch1000Recors2+" nanoseconds.");

		
		
		//*********************************************************************************************************
		
		//SEQUENTIAL SEARCH
		System.out.println("*************");
		System.out.println("SEQUENTIAL SEARCH");
		System.out.println("*************");
		
		// Search 10 elements in sorted list of the films using Sequential Search 
		// start counting
		long startTimeSearchTenRecords3 = System.nanoTime();
		int binRecTenRecords3 = Sorting.SequentialSearch(films,10, SearchKey);//
		//System.out.println("Index for searched Title of the movie  is: " + binRecTenRecords3);//for test only
		if (binRecTenRecords3 == -1) {
			System.out.println("Sequential Search ");
			System.out.println("Film not found!");
		} else {
			System.out.println("Sequential Search");
			System.out.println("Film has found on index: " + binRecTenRecords3);
			System.out.println(films[binRecTenRecords3]);
		}
		// end counting
		long endTimeSearchTenRecords3 = System.nanoTime();
        //calculate elapsed Time
		long elapsedTimeForSearchTenRecords3 = (endTimeSearchTenRecords3 - startTimeSearchTenRecords3);
		System.out.println("Cost of Sequential Search for 10 records= " + elapsedTimeForSearchTenRecords3+" nanoseconds.");
		       
		
		
		// Search 100 elements in sorted list of the films using Sequential Search
		long startTimeSearch100Recors3 = System.nanoTime();
		// number of record you want to sort in the film.csv
		int numbersOfRecords1003 = 100;
		// return values from binary search algorithm
		int binRec100Records3 = Sorting.SequentialSearch(films, numbersOfRecords1003, SearchKey);
		//System.out.println("Index for searched Title of the movie  is: " + binRec100Records);//for test app only
		if (binRec100Records3 == -1) {
			System.out.println("Sequential Search");
			System.out.println("Film not found!");
		} else {
			System.out.println("Sequential Search");
			System.out.println("Film has found on index: " + binRec100Records3);
			System.out.println(films[binRec100Records3]);
		}
		// stop counting
		long endTimeSearch100Recors3 = System.nanoTime();
		//calculate elapsed time
		long elapsedTimeForSearch100Recors3 = (endTimeSearch100Recors3 - startTimeSearch100Recors3);
		System.out.println("Cost of Sequential Search for 100 records= " + elapsedTimeForSearch100Recors3+" nanoseconds.");

		
		// Search 500 elements in sorted list of the films using Sequential Search
		long startTimeSearch500Recors3 = System.nanoTime();
		// input number of record you want to sort in the film.csv
		int numbersOfRecords5003 = 500;
		//return value searched film from 500 records
		int binRec500Records3 = Sorting.SequentialSearch(films, numbersOfRecords5003, SearchKey);
		//System.out.println("Index for searched Title of the movie  is: " + binRec500Records3);//for test only
		if (binRec500Records3 == -1) {
			System.out.println("Sequential Search");
			System.out.println("Film not found!");
		} else {
			System.out.println("Sequential Search");
			System.out.println("Film has found on index: " + binRec500Records3);
			System.out.println(films[binRec500Records3]);
		}
		// end counting
		long endTimeSearch500Recors3 = System.nanoTime();
		//calculating time for search element in 500 films
		long elapsedTimeForSearch500Recors3 = (endTimeSearch500Recors3 - startTimeSearch500Recors3);
		System.out.println("Cost of Sequential Search for 500 records = " + elapsedTimeForSearch500Recors3+" nanoseconds."); 
		
		
		// Search 1000 elements in sorted list of the films using Sequential Search
		long startTimeSearch1000Recors3 = System.nanoTime();
		// number of record you want to sort in the film.csv
		int numbersOfRecords10003 = films.length-1;
		//variable returned from search algorithm 
		int binRec1000Records3 = Sorting.SequentialSearch(films,numbersOfRecords10003, SearchKey);
		//System.out.println("Index for searched Title of the movie  is: " + binRec1000Records2);For test app only
		if (binRec1000Records3 == -1) {
			System.out.println("Sequential Search");
			System.out.println("Film not found!");
		} else {
			System.out.println("Sequential Search");
			System.out.println("Film has found on index: " + binRec1000Records3);
			System.out.println(films[binRec1000Records3]);
		}
		// end counting
		long endTimeSearch1000Recors3 = System.nanoTime();
		long elapsedTimeForSearch1000Recors3 = (endTimeSearch1000Recors3 - startTimeSearch1000Recors3);
		System.out.println("Cost of Sequential Search for 1000 records= " + elapsedTimeForSearch1000Recors3+" nanoseconds.");
		
		
		
		
		
		
		
		//*************************************************************************************************************
		//*************************************************************************************************************
		System.out.println("***********************************************************************************");
		System.out.println("Part 2: Input Handling and Exceptions");
		System.out.println("Task 1.");
		System.out.println("Write Java program that accept new film record(with all the eight fields) and ");
		System.out.println("add it at the end of the record with a new consecutive film_id(after the last film).");
		System.out.println("Make sure you check the input for each field is valid before accepting new record from the user.");
		System.out.println("Hint. Use setters to implement input constrains");
		System.out.println("Example input:");
		System.out.println("filmID(integer), title(String), description (String), release_year (integer), rental_rate(double),");
		System.out.println("length(integer), replacement_cost(double), special_features(String))");
		System.out.println("*******************************************************************************************");

		
		
		String filepath = "film_data.csv";
        //create object AddFilmToLibrary which allowed to use below variable
		Film AddFilmToLibrary = new Film(filmID, title, description, releaseYear, rentalRate, length, replacementCost, specialFeatures);
		
	    //COLLECT DATA FROM USER
		System.out.println("Enter filmId of the new movie that you want add to the library, please: ");
		System.out.println("Film ID must be 4 digit with no dot and any special characters");
        
		// get an ID last element and add 1 that we add next element to the list and set this value to the library
		AddFilmToLibrary.setFilmID(films.length + 1);
		// get an ID last element

		System.out.println("film ID is: " + AddFilmToLibrary.getFilmID());
		
		
		// add and validate title of the movie
		boolean valid = false;
		do {
			System.out.print("Please enter a valid Title: ");
			AddFilmToLibrary.setTitle(title = sc2.nextLine());
			if (title.isEmpty()) {
				System.out.println("Input cant be empty string");
				valid = false;
			} else {
				valid = true;
			}
		} while (!valid);
		System.out.println("Thank you...");
		System.out.println("Your title is: " + AddFilmToLibrary.getTitle());
		
		
		// get a description
		boolean validDescription = false;
		do {
			System.out.print("Please enter a valid description: ");
			AddFilmToLibrary.setDescription(description=sc2.nextLine());
			if (AddFilmToLibrary.getDescription().isEmpty()) {
				System.out.println("Description can not be empty string!");
				validDescription = false;
			} else {
				validDescription = true;
			}
		} while (!validDescription);
		System.out.println("Thank you...");
		System.out.println("Your description is: " + AddFilmToLibrary.getDescription());
		System.out.println("Enter release year of the new movie that you want add to the library, please: ");

		
		// get a release year from client
		// I initiate to value what is for sure wrong so it will go to the loop
		int releaseYear = -1;
		while (AddFilmToLibrary.getReleaseYear() < 0 || (AddFilmToLibrary.getReleaseYear() > 2021 || AddFilmToLibrary.getReleaseYear() < 1985)) {
			System.out.println("Please enter production year must be between 1985-2021: ");
			String inputYear = sc2.nextLine();// we are going to grab a string instead of number
			try {
				// releaseYear will be a value what will be assign to the input
				AddFilmToLibrary.setReleaseYear(Integer.valueOf(inputYear));// convert string value to integer if find exception then throw
															// this exception and we can catch
			} catch (NumberFormatException e) {
				System.out.println("Please provide numbers not a characters.");
				AddFilmToLibrary.setReleaseYear(-1);
			}
		}
		System.out.println("release year is: " + AddFilmToLibrary.getReleaseYear());

		
		// rental rate add and validate
		System.out.println("Enter rental rate of the new movie that you want add to the library, please: ");
		// I initiate to value what is for sure wrong so it will go to the loop
		//double rentalRate = -1.0;
		while (AddFilmToLibrary.getRentalRate() < 0.0 || (AddFilmToLibrary.getRentalRate() > 10.0 || AddFilmToLibrary.getRentalRate() < 3.0)) {
			System.out.println("Please enter a rate between 3 and 10 euros : ");
			String inputRate = sc2.nextLine();// we are going to grab a string instead of number
			try {
				// releaseYear will be a value what will be assign to the input
				AddFilmToLibrary.setRentalRate(Double.valueOf(inputRate));

			} catch (Exception e) {
				System.out.println("Please provide numbers not a characters.");
				rentalRate = -1.0;
			}
		}
		System.out.println("rental rate is: " + AddFilmToLibrary.getRentalRate() + " euros.");
		System.out.println("Enter film length of the new movie that you want add to the library, please: ");

		// get a length from client
		// I initiate to value what is for sure wrong so it will go to the loop
		int length = -1;
		while (AddFilmToLibrary.getLength() <= 0 || (AddFilmToLibrary.getLength() > 400)) {
			System.out.println("Please enter film length in muntes no longer than 400 minut: ");
			String inputLength = sc2.nextLine();// we are going to grab a string instead of number
            System.out.println("this is for test inputLength"+inputLength);
			try {
				// releaseYear will be a value what will be assign to the input
				AddFilmToLibrary.setLength(Integer.valueOf(inputLength));// convert string value to integer if find exception then throw
			// this exception and we can catch
			} catch (NumberFormatException e) {
				System.out.println("Please provide numbers not a characters.");
				length = -1;
			}
		}//end while loop for getting length

		System.out.println("Film length: " + AddFilmToLibrary.getLength() + " minutes.");
		System.out.println("Enter replacement cost for the new movie that you want add to the library, please: ");

		// get a replacement cost
		//double replacementCost = -1.0;
		while (AddFilmToLibrary.getReplacementCost() < 0.0 || (AddFilmToLibrary.getReplacementCost() > 300.0 || AddFilmToLibrary.getReplacementCost() < 80.0)) {
			System.out.println("Please enter a replacement cost between 80 ato 300 euros : ");
			String inputCost = sc2.nextLine();// we are going to grab a string instead of number
			try {
				// releaseYear will be a value what will be assign to the input
				AddFilmToLibrary.setReplacementCost(Double.valueOf(inputCost)) ;

			} catch (Exception e) {
				System.out.println("Please provide numbers not a characters.");
				AddFilmToLibrary.setReplacementCost(-1.0);
			}
		}
		System.out.println("rental rate is: " + AddFilmToLibrary.getReplacementCost() + " euros.");

		
		// get a special features
		boolean validSpecialFeature = false;
		do {
			System.out.print("Please enter a valid special features: ");
			AddFilmToLibrary.setSpecialFeatures(sc2.nextLine()); 
			if (AddFilmToLibrary.getSpecialFeatures().isEmpty()) {
				System.out.println("Input cant be empty string");
				validSpecialFeature = false;
			} else {
				validSpecialFeature = true;
			}
		} while (!validSpecialFeature);
		System.out.println("Thank you...");
		System.out.println("Your speacial featutes is: " + AddFilmToLibrary.getSpecialFeatures());


		// Print all variables collected from user
		System.out.println("Do you want to input this data? :");
		System.out.println("Film ID is: " + AddFilmToLibrary.getFilmID());
		System.out.println("Title is: " + AddFilmToLibrary.getTitle());
		System.out.println("Description is: " + AddFilmToLibrary.getDescription());
		System.out.println("Year is: " + AddFilmToLibrary.getReleaseYear());
		System.out.println("Rate is: " + AddFilmToLibrary.getRentalRate());
		System.out.println("Length is: " + AddFilmToLibrary.getLength());
		System.out.println("Replacement is: " + AddFilmToLibrary.getReplacementCost());
		System.out.println("Special features: " + AddFilmToLibrary.getSpecialFeatures());
        
		
		//method save record responsible for add all collected inputs from the user to the csv file
		saveRecord(AddFilmToLibrary.getFilmID(), AddFilmToLibrary.getTitle(), AddFilmToLibrary.getDescription(), AddFilmToLibrary.getReleaseYear(), AddFilmToLibrary.getRentalRate(),
				AddFilmToLibrary.getLength(), AddFilmToLibrary.getReplacementCost(), AddFilmToLibrary.getSpecialFeatures(),
				filepath);
        //print 
		printResutsArray(films);
		System.out.println((AddFilmToLibrary.toString()));	
		
		
	}

}
