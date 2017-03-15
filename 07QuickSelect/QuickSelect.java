public class QuickSelect {
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

    public static int part(int[] nums, int start, int end) {
	System.out.print('{');
	for (int i = 0; i < nums.length; i ++) {
	    System.out.print(nums[i] + ", ");
	}
	System.out.println('}');

	int pivotIndex = (int)(Math.random()*(end - start + 1) + start);
	int pivot = nums[pivotIndex];

	int temp = nums[start];
	nums[start] = nums[pivotIndex];
	nums[pivotIndex] = temp;
	pivotIndex = start;

	System.out.println("Pivoting around " + pivot + ", " + pivotIndex);

	int pass = 0;
	int left = start;
	int right = end;
	while (left < right) {
	    System.out.println("Pass " + pass + ", left = " + left + ", right = " + right);
	    System.out.print('{');
	    for (int i = 0; i < nums.length; i ++) {
		System.out.print(nums[i] + ", ");
	    }
	    System.out.println('}');
	    while (left < right && nums[left] < pivot) {
		left ++;
	    }
	    while (left < right && nums[right] > pivot) {
		right --;
	    }
	    if (left < right) {
		System.out.println("Swapping " + nums[left] + " at " + left + " and " + nums[right] + " at " + right);
		temp = nums[right];
		nums[right] = nums[left];
		nums[left] = temp;
		if (nums[left] == nums[right]) {
		    left ++;
		}
		else if (pivotIndex == left) {
		    System.out.println(pivotIndex + " == " + left);
		    pivotIndex = right;
		    left = start;
		    right = end;
		}
		else if (pivotIndex == right) {
		    System.out.println(pivotIndex + " == " + right);
		    pivotIndex = left;
		    left = start;
		    right = end;
		}
		else {
		    System.out.println(pivotIndex + " != " + left + " != " + right);
		    left ++;
		    right --;
		}
		pass ++;
	    }
	}
	/*temp = nums[start];
	nums[start] = nums[right];
	nums[right] = temp;
	pivotIndex = right;*/
	System.out.print('{');
	for (int i = 0; i < nums.length; i ++) {
	    System.out.print(nums[i] + ", ");
	}
	System.out.println('}');
	System.out.println("Ending with " + nums[left] + " at " + left + " and " + nums[right] + " at " + right);
	return pivotIndex;
    }
    
    public static void main(String[] args) {
	int[] blah = {3, 12, 2, 3, 7, 89, -1, 23, 67, 3, 3, 3, 1, -5, 7, 3, -2, 7, 86, 4};
	int[] unsorted = {6, 5, 3, 1, 8, 7, 2, 4};
	System.out.println(part(blah, 0, blah.length - 1));
    }
}
