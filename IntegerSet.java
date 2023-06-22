package sortingProjects;

import java.util.ArrayList;
import java.util.Arrays;
public class IntegerSet {

    // The array that represents the set.
    private final int set[];

    /**
     * The constructor for IntegerSet. When an IntegerSet is created it must be
     * initialized with an integer array. The set will then pull out the duplicated
     * items and keep the unique integers.
     * 
     * @param arr
     *            The array to create the set from.
     */
    public IntegerSet(int arr[]) {
		if (arr == null) {
			throw new IllegalArgumentException("The array must not be null");
		}
		set = uniqueElements(arr);
    }

    /**
     * This is the size of the set which, in this case, is just the length of the
     * array.
     * 
     * @return The length of the set.
     */
    public int magnitude() {
		return set.length;
    }

    /**
     * This method is private and is used to help set up the set array. An integer
     * set is one in which the elements are unique (no duplicates) and are sorted.
     * 
     * @param arr
     *            The array that will be used to retrieve the unique elements from.
     * @return The new integer array that contains the unique elements from arr.
     */
    private int[] uniqueElements(int arr[]) {
    	quickSort(arr, 0, arr.length-1);
    	int temp[] = new int[arr.length];
    	temp[1] = arr[1];
    	int count = 1;
    	for(int i = 1; i < arr.length; i++) {
    	if(arr[i] != arr[i-1]) {
    	temp[count] = arr[i];
    	count++;
    	}
    	}
    	return truncateArray(temp, count);
    }

    /**
     * This method returns whether or not value is located in the set. If the value
     * is in the set then return true otherwise return false. <br />
     * Example:
     * <pre>
     * 		IntegerSet iS1 = new IntegerSet([1,2,3,4]); 
     * 		iS1.contains(3); //returns true
     * 		iS2.contains(6); //returns false
     * </pre>
     * 
     * @param value
     *            The integer to look for.
     * @return True if value is located in the set otherwise false.
     */
    public boolean contains(int value) {
    	for(int i = 0; i < set.length; i++) {
    		if(set[i] == value) {
    			return true;
    		}
    	}
		return false;
    }

    /**
     * A union of two sets is a new set that contains all elements from both sets.
     * This method takes another set and unions it with the set that calls this
     * method. A new IntegerSet is returned that contains the union of both sets.<br />
     * Example:
     * <pre>
     * 		IntegerSet is1 = new IntegerSet([1, 2, 3, 4]); 
     * 		IntegerSet is2 = new IntegerSet([3, 4, 5, 6, 7, 8]);
     * 		is1.union(is2) //returns new IntegerSet([1, 2, 3, 4, 5, 6, 7, 8]);
     * </pre>
     * 
     * @param otherSet
     *            The set to be unioned with.
     * @return A new IntegerSet that is the union of the calling set with the
     *         otherSet.
     */
    public IntegerSet union(IntegerSet otherSet) {
    	int tempArr[] = new int[otherSet.set.length+set.length];
    	int i = 0;
    	int j = 0;
    		while(i<set.length) {
    			tempArr[i] = set[i];
    			i++;
    		}
    		while(j<otherSet.set.length) {
    			tempArr[i] = otherSet.set[j];
    			i++;
    			j++;	
    		}
    	uniqueElements(tempArr);
    	IntegerSet resSet = new IntegerSet(tempArr);
		return resSet;
    }

    /**
     * The intersection of two sets is a new set that contains elements that occur
     * in both sets. This method takes another set and intersects it with the set
     * that calls this method. A new IntegerSet is returned that contains the
     * intersection of the two sets. <br />
     * Example:
     * <pre>
     * 		IntegerSet is1 = new IntegerSet([1,2,3,4]);
     * 		IntegerSet is2 = new IntegerSet([3,4,5]);
     * 		is1.intersection(is2) //returns new IntegerSet([3, 4]);
     * </pre>
     * 
     * @param otherSet
     *            The set to be intersected with.
     * @return A new IntegerSet that is the intersection of the calling set with the
     *         otherSet.
     */
    public IntegerSet intersection(IntegerSet otherSet) {
    	// this method prints a 0 for some reason in the resSet[0] element, I don't know why
    	int tempArr[] = new int[set.length];
    	int i = 0;
    	int j = 0;
    	while(i<set.length && j< otherSet.set.length) {
    		if(set[i]>otherSet.set[j]) {
    			j++;
    		}else if(set[i]<otherSet.set[j]) {
    			i++;
    		}else {
    			tempArr[i] = set[i];
    			i++;
    			j++;
    		}
    	}
    	IntegerSet resSet = new IntegerSet(tempArr);
		return resSet;
    }
    //USE THIS IF THE METHOD ISN'T WORKING IN THE OTHER CLASS, SOMETHING WITH MY IDE MAKES IT NOT RECOGNIZE IT FOR SOME REASON
 
    public static int[] truncateArray(int arr[], int count) {
		if (arr == null) {
			throw new IllegalArgumentException("Truncate Array: The integer array must not be null");
		}
		if (count > arr.length && count < 0) {
			throw new IllegalArgumentException("Count must be between 0 and the array length (inclusive).");
		}
		int result[] = new int[count];
		for (int i = 0; i < count; i++) {
			result[i] = arr[i];
		}
		return result;
    }

    /**
     * Returns a string representation of an IntegerSet type. The returned string
     * will have the following structure.
     * 
     * set{ elements in the set separated by a comma }.
     */
    public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("set{ ");
		for (int i = 0; i < set.length; i++) {
			sb.append(set[i]);
			if (i < set.length - 1) {
			sb.append(", ");
			}
		}
		sb.append(" }");
		return sb.toString();
    }
    static int getMax(int arr[]) {
    	int max = arr[0];
    	for(int i = 1; i < arr.length; i++) {
    		if(arr[i] > max) {
    			max = arr[i];
    		}
    	}
    	return max;
    }
    static void count(int arr[], int exp) {
    	int output[] = new int[arr.length];
    	int i;
    	int count[] = new int[10];
    	Arrays.fill(count, 0);
    	for(i = 0; i < arr.length; i++) {
    		count[(arr[i] / exp) % 10]++;
    	}
    	for(i=1; i <10; i++) {
    		count[i] += count[i-1];
    	}
    	for(i = arr.length-1; i>=0; i--) {
    		output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
    	}
    	for(i=0; i< arr.length; i++) {
    		arr[i] = output[i];
    	}
    }
    static void radixSort(int arr[]) {
    	int max = getMax(arr);
    	for(int exp = 1; max/exp >0; exp*=10) {
    		count(arr,exp);
    	}
    }
    static int partition(int[] arr, int low, int high) {
    	int pivot = arr[high];
    	int i = (low-1);
    	for(int j = low; j<= high-1;j++) {
    		if(arr[j] < pivot) {
    			i++;
    			swap(arr,i,j);
    		}
    	}
    	swap(arr,i+1,high);
    	return(i+1);
    }
    static void quickSort(int[] arr, int low, int high) {
    	if(low<high) {
    		int part = partition(arr,low,high);
    		quickSort(arr,low,part-1);
    		quickSort(arr,part+1,high);
    	}
    }
    static void swap(int[] arr, int i, int j) {
    	int temp = arr[i];
    	arr[i] = arr[j];
    	arr[j] = temp;
    }

}
