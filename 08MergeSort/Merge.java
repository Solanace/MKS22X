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

    public static void main(String[] args) {
	int[] P = {4, 6, 2, 3, 9, 7, 8, 1, 5, 0};
	int[] W = {-2, -4, -6, -8, 10, 10, 10, 3, -7};
	int[] u = new int[(int)(Math.random() * 1001)];
	for (int i = 0; i < u.length; i ++) {
	    u[i] = (int)(Math.random() * 1001 - 500);
	}
	mergesort(u);
	String test = display(u);
	Arrays.sort(u);
	String correct = display(u);
        System.out.println(test);
	System.out.println(correct);
	System.out.println(test.equals(correct));
    }
}
	
