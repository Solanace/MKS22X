import java.util.Arrays;
public class Quick {
    public static void quicksort(int[] nums) {
	quicksortH(nums, 0, nums.length - 1);
    }
    
    private static void quicksortH(int[] nums, int start, int end) {
	if (end - start < 2) {
	    return;
	}
	else {
	    int pivotIndex = (int)(Math.random() * (end - start + 1) + start);
	    int pivot = nums[pivotIndex];
	    swap(nums, start, pivotIndex);
	    int left = start + 1;
	    int middle = start + 1;
	    int right = end;
	    while (middle <= right) {
		if (nums[middle] < pivot) {
		    if (left != middle) {
			swap(nums, left, middle);
		    }
		    left ++;
		    middle ++;
		}
		else if (nums[middle] == pivot) {
		    middle ++;
		}
		else if (nums[middle] > pivot) {
		    swap(nums, middle, right);
		    right --;
		}
	    }
	    pivotIndex = left - 1;
	    swap(nums, start, pivotIndex);
	    quicksortH(nums, start, left - 1);
	    quicksortH(nums, middle - 1, end);
	}
    }

    public static int quickselect(int[] nums, int k) {
	return quickselectH(nums, k, 0, nums.length - 1);
    }
    
    public static int quickselectH(int[] nums, int k, int start, int end) {
	if (k <= end && k >= start) {
	    return nums[k];
	}
	else {
	    int pivotIndex = (int)(Math.random() * (end - start + 1) + start);
	    int pivot = nums[pivotIndex];
	    swap(nums, start, pivotIndex);
	
	    pivotIndex = start;
	    int left = start + 1;
	    int middle = start + 1;
	    int right = end;
	    while (middle <= right) {
		if (nums[middle] < pivot) {
		    if (left != middle) {
			swap(nums, left, middle);
		    }
		    left ++;
		    middle ++;
		}
		else if (nums[middle] == pivot) {
		    middle ++;
		}
		else if (nums[middle] > pivot) {
		    swap(nums, middle, right);
		    right --;
		}
	    }

	    pivotIndex = left - 1;
	    swap(nums, start, pivotIndex);
	    if (k > right) {
		return quickselectH(nums, k, right, end);
	    }
	    else return quickselectH(nums, k, start, left);
	}
    }

    public static int part(int[] nums, int start, int end) {
	int pivotIndex = (int)(Math.random() * (end - start + 1) + start);
	int pivot = nums[pivotIndex];
	swap(nums, start, pivotIndex);
	
	pivotIndex = start;
	int left = start + 1;
	int middle = start + 1;
	int right = end;
        while (middle <= right) {
	    if (nums[middle] < pivot) {
		if (left != middle) {
		    swap(nums, left, middle);
		}
		left ++;
		middle ++;
	    }
	    else if (nums[middle] == pivot) {
		middle ++;
	    }
	    else if (nums[middle] > pivot) {
		swap(nums, middle, right);
		right --;
	    }
	}

	pivotIndex = left - 1;
	swap(nums, start, pivotIndex);
	return pivotIndex;
    }

    private static void swap(int[] data, int a, int b) {
	int temp = data[b];
	data[b] = data[a];
	data[a] = temp;
    }

    public static int[] randomArray(int size) {
	int[] ary = new int[size];
	for (int i = 0; i < ary.length; i ++) {
	    ary[i] = (int)(Math.random() * ary.length - ary.length / 2);
	}
	return ary;
    }

    public static int[] randomArray(int size, int min, int max) {
	int[] ary = new int[size];
	for (int i = 0; i < ary.length; i ++) {
	    ary[i] = (int)(Math.random() * (max - min + 1) + min);
	}
	return ary;
    }
    
    public static void main(String[] args) {
	int[] a = randomArray(2000000);
	System.out.println("Completely random array");
	System.out.println("--------------------");
	// quicksort
	quicksort(a);
        String test = Arrays.toString(a);
        Arrays.sort(a);
	String correct = Arrays.toString(a);
	if (test.equals(correct)) {
	    System.out.println("Quicksort.....works!");
	}
	else {
	    System.out.println("Quicksort.....failed!");
	}
	// quickselect
	int r = (int)(Math.random() * a.length);
        int select = quickselect(a, r);
	Arrays.sort(a);
        if (select == a[r]) {
	    System.out.println("Quickselect...works!");
	}
	else {
	    System.out.println("Quickselect...failed!");
	}
	System.out.println();

	a = randomArray(2000000, 0, 10);
	System.out.println("Random array with limited range");
	System.out.println("--------------------");
	// quicksort
	quicksort(a);
        test = Arrays.toString(a);
        Arrays.sort(a);
	correct = Arrays.toString(a);
	if (test.equals(correct)) {
	    System.out.println("Quicksort.....works!");
	}
	else {
	    System.out.println("Quicksort.....failed!");
	}
	// quickselect
	r = (int)(Math.random() * a.length);
        select = quickselect(a, r);
	Arrays.sort(a);
        if (select == a[r]) {
	    System.out.println("Quickselect...works!");
	}
	else {
	    System.out.println("Quickselect...failed!");
	}
	System.out.println();

	a = randomArray(2000000, 50, 50);
	System.out.println("Array with all of 1 number");
	System.out.println("--------------------");
	// quicksort
	quicksort(a);
        test = Arrays.toString(a);
        Arrays.sort(a);
	correct = Arrays.toString(a);
	if (test.equals(correct)) {
	    System.out.println("Quicksort.....works!");
	}
	else {
	    System.out.println("Quicksort.....failed!");
	}
	// quickselect
	r = (int)(Math.random() * a.length);
        select = quickselect(a, r);
	Arrays.sort(a);
        if (select == a[r]) {
	    System.out.println("Quickselect...works!");
	}
	else {
	    System.out.println("Quickselect...failed!");
	}
	System.out.println();

	a = randomArray(2000000);
	Arrays.sort(a);
	System.out.println("Sorted array");
	System.out.println("--------------------");
	// quicksort
	quicksort(a);
        test = Arrays.toString(a);
        Arrays.sort(a);
	correct = Arrays.toString(a);
	if (test.equals(correct)) {
	    System.out.println("Quicksort.....works!");
	}
	else {
	    System.out.println("Quicksort.....failed!");
	}
	// quickselect
	r = (int)(Math.random() * a.length);
        select = quickselect(a, r);
	Arrays.sort(a);
        if (select == a[r]) {
	    System.out.println("Quickselect...works!");
	}
	else {
	    System.out.println("Quickselect...failed!");
	}
	System.out.println();

	int[] b = randomArray(2000000);
	Arrays.sort(b);
	for (int i = b.length - 1; i > -1; i --) {
	    a[b.length - 1 - i] = b[i];
	}
	System.out.println("Reverse sorted array");
	System.out.println("--------------------");
	// quicksort
	quicksort(a);
        test = Arrays.toString(a);
        Arrays.sort(a);
	correct = Arrays.toString(a);
	if (test.equals(correct)) {
	    System.out.println("Quicksort.....works!");
	}
	else {
	    System.out.println("Quicksort.....failed!");
	}
	// quickselect
	r = (int)(Math.random() * a.length);
        select = quickselect(a, r);
	Arrays.sort(a);
        if (select == a[r]) {
	    System.out.println("Quickselect...works!");
	}
	else {
	    System.out.println("Quickselect...failed!");
	}
	System.out.println();
    }
}
