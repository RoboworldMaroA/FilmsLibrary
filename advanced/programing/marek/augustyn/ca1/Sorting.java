package advanced.programing.marek.augustyn.ca1;
/*
 * CA1 Algorithms and Advanced Programming
 * Marek Augustyn
 * 17.11.2021
 * 
 */

//import java.util.Arrays; FOR TEST APP ONLY

//sorting class is using to implement Sorting and searching algorithms
public class Sorting {

	// initialize variable
	String SearchKey;

	// constructor
	public Sorting() {
		SearchKey = "";

	}

	// setters - set method, (Inputs)

	public void setSearchKey(String SearchKey) {
		if (SearchKey.isEmpty())
			// JOptionPane.showMessageDialog(null, "Field cannot be empty"); // if user
			// enters nothing

			System.out.println("Field cannot be empty");
		this.SearchKey = SearchKey;
	}

	// getters for search Title
	public String getSearchKey() {
		if (SearchKey.equals(" "))
			// JOptionPane.showMessageDialog(null, "Field cannot be empty"); // if user
			// enters nothing

			System.out.println("Field cannot be empty");

		return SearchKey;
	}

	// *****************************************************************
	// *****************************************************************
	// Quick Sort algorithm for Object
	static void quickSortObject(Film[] h, int p, int q) {
		if (q - p <= 1)
			; // skip
		else {
			Film x;
			int i, j, k;
			// let x = middle element in array
			x = h[(p + q) / 2];
			// x = f[p];
			i = p;
			j = p;
			k = q;
			while (j != k) {
				if (h[j] == x)
					j = j + 1;
				// else if(h[j] < x){ //swap f[j] with f[i]
				else if (h[j].compareTo(x) < 0) { // swap f[j] with f[i]

					Film temp;
					temp = h[j];
					h[j] = h[i];
					h[i] = temp;
					j = j + 1;
					i = i + 1;
				} else { // f[j] > x
							// swap f[j] with f[k-1]
					Film temp;
					temp = h[j];
					h[j] = h[k - 1];
					h[k - 1] = temp;
					k = k - 1;
				}
			}
			quickSortObject(h, p, i);
			quickSortObject(h, j, q);
		}
	}
	// end of quick sort for Objects

	// *****************************************************************
	// merge sort algorithm for Object
	static void mergeSortObj(Film[] f, int lowerB, int upperB) {
		if (lowerB + 1 < upperB) {
			int mid = (lowerB + upperB) / 2;
			mergeSortObj(f, lowerB, mid);
			mergeSortObj(f, mid, upperB);
			mergeObj(f, lowerB, mid, upperB);
		}
	}
	static void mergeObj(Film[] f, int lowerB, int mid, int upperB) {
		int i = lowerB;
		int j = mid;
		// use temp array to store merged sub-sequence
		Film[] temp = new Film[upperB - lowerB];
		int t = 0;
		while (i < mid && j < upperB) {
			if (f[i].compareTo(f[j]) <= 0) {//it compare element and return -1,0,1
				temp[t] = f[i];
				i++;
				t++;
			} else {
				temp[t] = f[j];
				j++;
				t++;
			}
		}
		// tag on remaining sequence
		while (i < mid) {
			temp[t] = f[i];
			i++;
			t++;
		}
		while (j < upperB) {
			temp[t] = f[j];
			j++;
			t++;
		}
		// copy temp back to f
		i = lowerB;
		t = 0;
		while (t < temp.length) {
			f[i] = temp[t];
			i++;
			t++;
		}
	}

	// End merge sort

	// **********************************************************************
	// **********************************************************************
	// Binary Search recursive
	// ************************************************************************
	// ***********************************************************************

	static int binarySearchRec(Film[] c, int first, int last, String searchKey) {
		if (last >= first) {
			int mid = (first + last) / 2;
			// if the searchKey is present at the middle itself
			if ((searchKey).compareTo(c[mid].getTitle()) == 0)
				return mid;
			// check if the searchkKey is on the left or the right
			if ((searchKey).compareTo(c[mid].getTitle()) < 0)
				return binarySearchRec(c, first, mid - 1, searchKey);
			else
				return binarySearchRec(c, mid + 1, last, searchKey);

		}
		return -1;// We reached here when element is not present in arr

	}// end of method binarySearchRec

	// **********************************************************************
	// **********************************************************************
	// This BianrySearch Algorithm iterative
	// ************************************************************************
	// ***********************************************************************

	static int binarySearch2(Film[] b, int last, String searchKey) {

		int first = 0;
		// int last = b.length - 1;
		// int last = 10;
		while (first <= last) {
			int mid = (first + last) / 2;

			// check if searchKey is present in the middle
			// array is checking only for Title fields
			if ((searchKey).compareTo(b[mid].getTitle()) == 0) {
				System.out.println(mid);
				return mid;
			}
			if ((searchKey).compareTo(b[mid].getTitle()) > 0) {
				// System.out.println("second half checking");//for testing only
				first = mid + 1;
			} else
				last = mid - 1;
		}

		return -1;

	}// end of method binarySearch

	// **********************************************************************
	// **********************************************************************
	// This is Sequential Algorithm
	// ************************************************************************
	// ***********************************************************************
	static int SequentialSearch(Film[] a, int last, String searchKey) {
		// int n = a.length;//for testing APP only
		int n = last;
		for (int i = 0; i < n; i++) {
			if ((searchKey).compareTo(a[i].getTitle()) == 0)
				return i;// this return index when find
		}
		return -1;// if not find then return -1

	}// end Sequential Sorting Algorithm

	

	
	
	
	
	// ***********************************************************************
	// ***********************************************************************
	// this main class is for testing app from inside a class Sorting only
	public static void main(String[] args) {

		/*
		 * @Test public void positiveTest() { int[] actual = { 5, 1, 6, 2, 3, 4 }; int[]
		 * expected = { 1, 2, 3, 4, 5, 6 }; MergeSort.mergeSort(actual, actual.length);
		 * assertArrayEquals(expected, actual); }
		 */
		/*
		 * //FOT TESTING APP ONLY int[] f = { 5, 1, 6, 2, 3, 4 }; // int[] expected = {
		 * 1, 2, 3, 4, 5, 6 }; // mergeSort(f, 0,f.length);
		 * 
		 * System.out.println(Arrays.toString(f));
		 * 
		 * // testing algorithms for numbers int searchedNumber = 4; // int
		 * indexSearchedNumber = binarySearchRec(f, 0, f.length-1, searchedNumber); //
		 * System.out.println("Index searched number is : " + indexSearchedNumber);
		 * 
		 * 
		 * long startTime = System.nanoTime(); //SORTING ARRAY
		 * 
		 * 
		 * quickSort(f , 0, f.length); long endTime = System.nanoTime(); long
		 * elapsedTime = (endTime - startTime);
		 * System.out.println("Cost of quickSort= "+elapsedTime);
		 * System.out.println(Arrays.toString(f));
		 */
	}

}
