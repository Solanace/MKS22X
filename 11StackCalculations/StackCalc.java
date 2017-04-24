import java.util.Stack;
public class Postfix {
    public static double eval(String s) {
	String[] arr = s.split(" ");
	Stack<Double> sta = new Stack<Double>();
	for (int i = 0; i < arr.length; i ++) {
	    if (isOp(arr[i])) {
		double d = calc(arr[i], sta.pop(), sta.pop());
		sta.push(d);
	    }
	    else {
		double d = Double.parseDouble(arr[i]);
	        sta.push(d);
	    }
	}
	return sta.pop();
    }

    private static boolean isOp(String s) {
	return s.equals("+") ||
	    s.equals("-") ||
	    s.equals("*") ||
	    s.equals("/") ||
	    s.equals("%");
    }

    private static double calc(String op, double b, double a) {
	if (op.equals("+")) {
	    return a + b;
	}
	else if (op.equals("-")) {
	    return a - b;
	}
	else if (op.equals("*")) {
	    return a * b;
	}
	else if (op.equals("/")) {
	    return a / b;
	}
	else {
	    return a % b;
	}
    }

    public static void main(String[] args) {
	System.out.println(eval("10 2.0 +"));
	System.out.println(eval("11 3 - 4 + 2.5 *"));
	System.out.println(eval("8 2 + 99 9 - * 2 + 9 -"));
    }
}
