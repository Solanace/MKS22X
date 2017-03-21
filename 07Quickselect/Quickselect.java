import java.util.Arrays;
public class Quickselect {
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
    
    public static void main(String[] args) {
	int[] u = new int[50];
	for (int i = 0; i < u.length; i ++) {
	    u[i] = (int)(Math.random() * 201 - 100);
	}
	int r = (int)(Math.random() * u.length);
	System.out.println(quickselect(u, r));
	/*quicksort(u);
	System.out.print("{");
	for (int i = 0; i < u.length; i ++) {
	    System.out.print(u[i] + ", ");
	}
	System.out.println("}");*/
        Arrays.sort(u);
	System.out.println(u[r]);
	/*System.out.print("{");
	for (int i = 0; i < u.length; i ++) {
	    System.out.print(u[i] + ", ");
	}
	System.out.println("}");*/
    }
}
