import java.util.Stack;
public class Postfix {
    public static double eval(String s) {
	String[] arr = s.split(" ");
	Stack<Double> sta = new Stack<Double>();
	for (int i = 0; i < arr.length; i ++) {
	    if (isOp(arr[i])) {
		double d = calculate(arr[i], sta.pop(), sta.pop());
		sta.push(d);
	    }
	    else {
		double d = Double.parseDouble(arr[i]);
	        sta.push(d);
	    }
	}
	return 1;
    }

    private static boolean isOp(String s) {
	return s.equals("+") ||
	    s.equals("-") ||
	    s.equals("*") ||
	    s.equals("/") ||
	    s.equals("%");
    }
    
}
