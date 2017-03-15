public class QuickSort {
    public static int part(int[] data, int start, int end) {
	System.out.print('{');
	for (int i = 0; i < data.length; i ++) {
	    System.out.print(data[i] + ", ");
	}
	System.out.println('}');
        int[] parted = new int[data.length];
	int count = 0;
	int pivotIndex = (int)(Math.random()*(end - start + 1) + start);
	int pivot = data[pivotIndex];
	System.out.println("Partitioning around " + data[pivotIndex]);
	for (int i = 0; i < data.length; i ++) {
	    if (data[i] < pivot) {
		parted[count] = data[i];
		count ++;
	    }
	}
	System.out.print('{');
	for (int i = 0; i < data.length; i ++) {
	    System.out.print(parted[i] + ", ");
	}
	System.out.println('}');
	System.out.println(count);
	parted[count] = pivot;
	pivotIndex = count;
	count ++;
	boolean ignoreOnce = true;
	for (int i = start; i <= end; i ++) {
	    System.out.print('{');
	    for (int j = 0; j < data.length; j ++) {
		System.out.print(parted[j] + ", ");
	    }
	    System.out.println('}');
	    if (data[i] == pivot && ignoreOnce) {
		ignoreOnce = false;
	    }
	    else if (data[i] >= pivot) {
		parted[count] = data[i];
		count ++;
	    }
	}
	System.out.print('{');
	for (int i = start; i <= end; i ++) {
	    System.out.print(parted[i] + ", ");
	}
	System.out.println('}');
	return pivotIndex;
    }
    
    public static void main(String[] args) {
	int[] blah = {4, 2, 3, 7, 89, -1, 23, 67, 3, 3, 3, 1, -5, 7, 3, -2, 7, 86, 12};
	int[] unsorted = {6, 5, 3, 1, 8, 7, 2, 4};
	System.out.println(part(blah, 0, blah.length - 1));
    }
}
