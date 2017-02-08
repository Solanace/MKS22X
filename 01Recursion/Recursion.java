public class Recursion {
    public static String name() {
	return "Wu,Penn";
    }

    public static double sqrt(double n) {
	if (n < 0) {
	    throw new IllegalArgumentException();
	}
	return sqrtHelper(n, n/2);
    }

    private static double sqrtHelper(double n, double guess) {
	if (isCloseEnough(n, guess * guess)) {
	    return guess;
	}
	else return sqrtHelper(n, (n / guess + guess) / 2);
    }

    private static boolean isCloseEnough(double a, double b) {
	if (a == 0 || b == 0) {
	    return Math.abs(a) < 0.000000000000001 &&
		   Math.abs(b) < 0.000000000000001;
	}
	else return Math.abs((a - b) / a) < a * 0.000000000000001;
    }
}
