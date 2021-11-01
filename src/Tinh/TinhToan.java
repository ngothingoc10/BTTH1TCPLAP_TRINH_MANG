package Tinh;
import java.util.Stack;

public class TinhToan {
	private static int Priority(char c) {
		if (c == '+' || c == '-') return 1;
		if (c == '*' || c == '/') return 2;
		if (c == '^' || c == '#') return 3;
		return 0;
				
	}
	
	private static boolean IsNumber(char c) {
		return (c >= '0' && c <= '9') || (c == '.');
	}
	private static String convert(String st) {
		st = st.replaceAll("sqrt", "#");
		System.out.println(st);
		String result = "";
		Stack<Character> stack = new Stack<Character>();
		try {
			for (int i = 0; i < st.length(); i++) {
				System.out.println("result " + result);
				//neu la phep toan
				if (st.charAt(i) == '+' || st.charAt(i) == '-'
				|| st.charAt(i) == '/' || st.charAt(i) == '*'
				|| st.charAt(i) == '^' || st.charAt(i) == '#') {
					if (stack.size() > 0) {
						try {
							while (Priority(stack.peek()) >= Priority(st.charAt(i))) {
								result += stack.pop(); 
							}
						} catch (Exception e) {}}
					stack.push(st.charAt(i)); 
					}
				if (st.charAt(i) >= '0' && st.charAt(i) <= '9'){
					int pos = i;
					//liem tra cac vi tri tiep theo co phai so hay khong
					while (IsNumber(st.charAt(i))) {
						i++;
						if (i == st.length())
						break;
					}
					result += st.substring(pos, i) + " ";
					i--;//vi lat nua quay tro lai vong lap for chinh i se tang len	
				}
				if (st.charAt(i) == '(' )
					stack.push( '(' );
				if (st.charAt(i) == ')') {
					char tmp = stack.pop();
					while (tmp != '(') {
						result += tmp;
						tmp = stack.pop(); 
					}
				}
			}
			while (!stack.empty())
				result += stack.pop();
		}
		catch (Exception e) {
			return "Error"; }
		
		System.out.println("resultt o day la:"+result);
		return result;
	}
	
	public static String calculate(String st) {
		int S = 0;
		String bl = convert(st).trim();
		if (bl.compareTo("Error") == 0) return "Error";
		bl += " ";
		Stack<Float> stack = new Stack<Float>();
		float a, b;
		try {
			for (int i = 0; i < bl.length(); i++) {
				System.out.println("kt: " + bl + " " + i);
				switch (bl.charAt(i)) {
				case '*':
					a = stack.pop();
					b = stack.pop();
					stack.push(a * b); break;
				case '/':
					a = stack.pop();
					b = stack.pop();
					if (a == 0)
					return "Error";
					stack.push(b / a);break;
				case '-':
					a = stack.pop();
					b = stack.pop();
					stack.push(b - a);break;
					case '+':
					a = stack.pop();
					b = stack.pop();
					stack.push(a + b); break;
				default:{
					if (IsNumber(bl.charAt(i))) {
						int pos = i;
						i = bl.indexOf(" ", pos);
						stack.push(Float.parseFloat(bl.substring(pos, i)));
					}
					else if (bl.charAt(i) != ' ') {
						return "Error";
					}
				}
				}
			}
		} catch(Exception e) {
			return "Error";
		}
		if (stack.size() > 1)
			return "Error";
			return stack.pop() + "";
	}
	

}



