package cs6301.g21;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class InfixToPostfix {

    /**
     * Checks if postfix conversion needed for the expression
     * @param t String token to be evaluated
     * @return Boolean true if postFix conversion needed, false otherwise
     */
    public static boolean needPostfix(String t){

        String [] tokens = t.split(" ");

        if(!ShuntingYard.isValidOperator(tokens[1])){

            return false;
        }

        return true;
    }

    /**
     * Does infix to postfix conversion
     * @param t String token to be evaluated
     * @return Queue of postfix expression
     */
    public static Queue<String> infixToPostfix(String t){

        Queue<String> output = new LinkedList<String>();
        Stack<String> opstack = new Stack<>();

        String [] tokens = t.split(" ");
        boolean need = needPostfix(t);

        //if already in postfix, then just populate the queue and return it
        if(need == false){
            for(int i=0; i<tokens.length; i++){
                output.add(tokens[i]);
            }

            return output;
        }



        for(int i=0; i<tokens.length; i++){

            //if handles operators
            //else handles alphanumeric character
            if(ShuntingYard.isValidOperator(tokens[i])){

                if(tokens[i].equals("(")){
                    //push to stack if (
                    opstack.push(tokens[i]);

                }else if(tokens[i].equals(")")){
                    //remove all elements of stack till ( is encountered
                    while (!opstack.empty() && !opstack.peek().equals("(")){
                        output.add(opstack.pop());
                    }

                    opstack.pop();

                }else{

                    //pop all elements of the stack having lower precedence than tokens[i] except ")"
                    // assuming opstack will only contain valid operators
                    while (!opstack.empty()){
                        if(ShuntingYard.precedence(opstack.peek()) <= ShuntingYard.precedence(tokens[i]) && !opstack.peek().equals("(")){
                            output.add(opstack.pop());
                        }
                        else
                            break;
                    }

                    opstack.push(tokens[i]);
                }

            }else if(ShuntingYard.isAlphaNumeric(tokens[i])) {
                output.add(tokens[i]);
            }else
                throw new IllegalArgumentException("Not a valid expression");


        }

        //pop the remaining elements of the opstack
        while (!opstack.empty() && !opstack.peek().equals("(")){
            output.add(opstack.pop());
        }

        return output;
    }

//    public static void main(String[] args) {
//
//
//        ShuntingYard.populateOperators();
//
//        //works for this case ( a + b ) * ( c + d ) / b ^ c {a b + c d + * b c ^ /}
//        //a * b ^ c + answer: ( d * e ) {a b c ^ * d e * +}
//        // a * b ^ c answer: {a b c ^ *}
//        // a ^ b * c answer: {a b ^ c *}
//        Queue<String> q = infixToPostfix("a b ^ c *");
//
//        //Prints the answer
//        while (!q.isEmpty())
//            System.out.println(q.remove());
//
//    }
}
