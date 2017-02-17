public class Sorts {
    public static String name() {
	return "10.Wu.Penn";
    }

    public static void selectionSort(int[] data) {
	int tempPos; // tempPos is the position of the smallest number
	int tempInt; // tempInt is the intermediary between the current index and the smallest number
	for (int index = 0; index < data.length - 1; index ++) {
	    tempPos = index;
	    for (int pos = index; pos < data.length; pos ++) {
		if (data[pos] < data[tempPos]) {
		    tempPos = pos;
		}
	    }
	    tempInt = data[index];
	    data[index] = data[tempPos];
	    data[tempPos] = tempInt;
	}
    }

    public static void insertionSort(int[] data) {
	int carryOver; // the int that gets sorted
	int shift; // the number of places left that carryOver gets shifted
	for (int index = 1; index < data.length; index ++) { // index starts at 1, since data[0] is sorted already
	    shift = 0;
	    carryOver = data[index]; // copies the value that gets sorted
	    for (int reverse = index - 1; reverse > -1 && data[reverse] > carryOver; reverse --) {
	        shift += 1;
	    }
	    for (int loop = 0; loop < shift; loop ++) {
		data[index - loop] = data[index - loop - 1];
	    }
	    data[index - shift] = carryOver;
	}
    }

    public static void bubbleSort(int[] data) {
	boolean hasSwapped = true; // whether the last pass swapped any two pairs
	int tempInt;
	for (int max = data.length - 1; hasSwapped; max --) { // integers beyond max are sorted
	    hasSwapped = false;
	    for (int forwardLoop = 0; forwardLoop < max; forwardLoop ++) {
		/*System.out.print("[");
		  for (int i = 0; i < data.length; i ++) {
		  System.out.print(data[i] + ", ");
		  }
		  System.out.println("]");*/
		if (data[forwardLoop] > data[forwardLoop + 1]) {
		    hasSwapped = true;
		    tempInt = data[forwardLoop];
		    data[forwardLoop] = data[forwardLoop + 1];
		    data[forwardLoop + 1] = tempInt;
		}
	    }
	}
    }
}
