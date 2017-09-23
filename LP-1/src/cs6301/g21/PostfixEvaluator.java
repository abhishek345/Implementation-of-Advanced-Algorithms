package cs6301.g21;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
* Evaluation of postfix expressions
*
* @author Shreya Vishwanath Rao, Vibha Belavadi, Umang Shah, Abhishek Jagwani
* @version 1.0: 2017/09/19
*/
public class PostfixEvaluator {
	
	/**
	* Evaluates the given postfix expression
	*
	* @param q : queue containing the post fix expression
	* @return evaluated result(Num object) of the postfix expression
	*/
	public static Num evaluate(Queue<String> q){

		Stack<Num> stack = new Stack<Num>();
		ShuntingYard t= new ShuntingYard();
		
		for(String token : q ){
			
			if(ShuntingYard.isNumeric(token)){
				Num n= new Num(token);
				stack.push(n);
			}
			else if(ShuntingYard.isVariable(token)){
				stack.push(Register.varReg.get(token));
				
			}
			else if(ShuntingYard.isValidOperator(token)){ //Shunting Yard function
				Num right;
               			Num left;

				if(token.equals("+")){
				    right = stack.pop();
				    left = stack.pop();
				    stack.push(Num.add(left, right));

				}else if(token.equals("-")){
				    right = stack.pop();
				    left = stack.pop();
				    stack.push(Num.subtract(left, right));

				}else if(token.equals("*")){
				    right = stack.pop();
				    left = stack.pop();
				    stack.push(Num.product(left, right));

				}else if(token.equals("/")){
				    right = stack.pop();
				    left = stack.pop();
				    stack.push(Num.divide(left, right));

				}else if(token.equals("%")){
				    right = stack.pop();
				    left = stack.pop();
				    stack.push(Num.mod(left, right));

				}else if(token.equals("^")){
				    right = stack.pop();
				    left = stack.pop();
				    stack.push(Num.power(left, right));

				}else if(token.equals("|")){
				    right = stack.pop();
				    stack.push(Num.squareRoot(right));
				}

				else
				    throw new IllegalArgumentException("Invalid token, neither a number nor a token: " + token);
			}
		}
		return stack.pop();
		
	}
	
// 	public static void main(String[] args){
// 		Queue<String> queue= new LinkedList<String>();
		
// 		//infix = 5 + 6 - 7  * (3+7);
// 		//postfix = 56+737+*-
		
// 		Register.varReg = new HashMap<String,Num>();
// 		Register.varReg.put("x", new Num(5));
// 		Register.varReg.put("y", new Num(6));
// 		Register.varReg.put("z", new Num(7));
// 		Register.varReg.put("a", new Num(3));

// 		queue.add("x");
// 		queue.add("y");
// 		queue.add("+");
// 		queue.add("z");
// 		queue.add("a");
// 		queue.add("z");
// 		queue.add("+");
// 		queue.add("*");
// 		queue.add("-");
		
		
// 		Num result = evaluate(queue);
// 		//printing the sign since to string doesn't display negative sign
// 		System.out.println(result.getSign() + " " + result.toString());
// 		result.printList();
		
// 	}
	

}
