import java.util.Arrays;
public class Quickselect {
    /*public static int part(int[] data, int start, int end) {
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
	}*/

    public static int quickselect(int[] nums, int k) {
	int start = 0;
	int end = nums.length - 1;
	int pivotIndex = part(nums, start, end);
	while (pivotIndex != k) {
	    if (k < pivotIndex) {
		pivotIndex = part(nums, start, pivotIndex - 1);
	    }
	    else if (k > pivotIndex) {
		pivotIndex = part(nums, pivotIndex + 1, end);
	    }
	}
	return nums[k];
    }

    public static int part(int[] nums, int start, int end) {
	/*System.out.println("Original array");
	System.out.print('{');
	for (int i = 0; i < nums.length; i ++) {
	    System.out.print(nums[i] + ", ");
	}
	System.out.println('}');
	System.out.println("----------");*/
	
	int pivotIndex = (int)(Math.random() * (end - start + 1) + start);
	int pivot = nums[pivotIndex];
	swap(nums, start, pivotIndex);
	pivotIndex = start;

	/*System.out.println("Swapping pivot with beginning");
	System.out.print('{');
	for (int i = 0; i < nums.length; i ++) {
	    System.out.print(nums[i] + ", ");
	}
	System.out.println('}');
	System.out.println("----------");*/

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
	    /*System.out.print('{');
	    for (int j = 0; j < nums.length; j ++) {
	    System.out.print(nums[j] + ", ");
	    }
	    System.out.println('}');*/
	}

	pivotIndex = left - 1;
	swap (nums, start, pivotIndex);
	
	/*System.out.println("Final array");
	System.out.println("Left: " + left);
	System.out.println("Middle: " + middle);
	System.out.println("Right: " + right);
	System.out.print('{');
	for (int i = 0; i < nums.length; i ++) {
	    System.out.print(nums[i] + ", ");
	}
	System.out.println('}');*/
	return pivotIndex;
    }

    private static void swap(int[] data, int a, int b) {
	int temp = data[b];
	data[b] = data[a];
	data[a] = temp;
    }
    
    public static void main(String[] args) {
	int[] blah = {3, 12, 2, 3, 7, 89, -1, 23, 67, 3, 3, 3, 1, -5, 7, 3, -2, 7, 86, 4};
	int[] unsorted = {6, 5, 3, 1, 8, 7, 2, 4};
	int[] a = {999,999,999,4,1,0,3,2,999,999,999,5,3,3,3,3};
	int[] b = {9, 4, 3, 6, 5, 1, 0, 8, 7, 2};
	int i = Integer.parseInt(args[0]);
	System.out.println(quickselect(a, i));
        Arrays.sort(a);
	System.out.println(a[i]);
    }
}
