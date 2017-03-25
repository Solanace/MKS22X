import java.util.Arrays;
public class Merge {
    public static void mergesort(int[] ary) {
	if (ary.length < 2) {
	    return;
	}
	
	int[] left = new int[ary.length / 2];
	int[] right = new int[ary.length - left.length];
	
	int count = 0;
	for (int i = 0; i < left.length; i ++) {
	    left[i] = ary[i];
	    count ++;
	}
	for (int i = 0; i < right.length; i ++) {
	    right[i] = ary[count + i];
	}
	
	mergesort(left);
	mergesort(right);
	
	int l = 0, r = 0, i = 0;
	while (l < left.length && r < right.length) {
	    if (left[l] < right[r]) {
		ary[i] = left[l];
		i ++;
		l ++;
	    }
	    else {
		ary[i] = right[r];
		i ++;
		r ++;
	    }
	}
	while (l < left.length) {
	    ary[i] = left[l];
	    i ++;
	    l ++;
	}
	while (r < right.length) {
	    ary[i] = right[r];
	    i ++;
	    r ++;
	}
    }

    public static String display(int[] args) {
	String s = "{";
	for (int i = 0; i < args.length - 1; i ++) {
	    s = s + args[i] + ", ";
	}
	s = s + args[args.length - 1] + "}";
	return s;
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
	// mergesort
	mergesort(a);
        String test = Arrays.toString(a);
        Arrays.sort(a);
	String correct = Arrays.toString(a);
	if (test.equals(correct)) {
	    System.out.println("Mergesort.....works!");
	}
	else {
	    System.out.println("Mergesort.....failed!");
	}
	System.out.println();

	a = randomArray(2000000, 0, 10);
	System.out.println("Random array with limited range");
	System.out.println("--------------------");
        // mergesort
	mergesort(a);
        test = Arrays.toString(a);
        Arrays.sort(a);
	correct = Arrays.toString(a);
	if (test.equals(correct)) {
	    System.out.println("Mergesort.....works!");
	}
	else {
	    System.out.println("Mergesort.....failed!");
	}
	System.out.println();

	a = randomArray(2000000, 50, 50);
	System.out.println("Array with all of 1 number");
	System.out.println("--------------------");
        // mergesort
	mergesort(a);
        test = Arrays.toString(a);
        Arrays.sort(a);
	correct = Arrays.toString(a);
	if (test.equals(correct)) {
	    System.out.println("Mergesort.....works!");
	}
	else {
	    System.out.println("Mergesort.....failed!");
	}
	System.out.println();

	a = randomArray(2000000);
	Arrays.sort(a);
	System.out.println("Sorted array");
	System.out.println("--------------------");
        // mergesort
	mergesort(a);
        test = Arrays.toString(a);
        Arrays.sort(a);
	correct = Arrays.toString(a);
	if (test.equals(correct)) {
	    System.out.println("Mergesort.....works!");
	}
	else {
	    System.out.println("Mergesort.....failed!");
	}
	System.out.println();

	int[] b = randomArray(2000000);
	Arrays.sort(b);
	for (int i = b.length - 1; i > -1; i --) {
	    a[b.length - 1 - i] = b[i];
	}
	System.out.println("Reverse sorted array");
	System.out.println("--------------------");
        // mergesort
	mergesort(a);
        test = Arrays.toString(a);
        Arrays.sort(a);
	correct = Arrays.toString(a);
	if (test.equals(correct)) {
	    System.out.println("Mergesort.....works!");
	}
	else {
	    System.out.println("Mergesort.....failed!");
	}
	System.out.println();
    }
}
	
