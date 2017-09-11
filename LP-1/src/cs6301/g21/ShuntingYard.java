package cs6301.g21;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class ShuntingYard {

    private static ArrayList<String> supportedOp;

    public ShuntingYard(){
        populateSupportedOps();
    }

    //Add valid operators to the supported operators class
    public static void populateSupportedOps(){

        supportedOp.add("+");
        supportedOp.add("-");
        supportedOp.add("*");
        supportedOp.add("/");
        supportedOp.add("(");
        supportedOp.add(")");
        supportedOp.add("^");
        supportedOp.add("!");
        supportedOp.add("%");
    }

    //Checks if it is a valid operator
    public static boolean isValidOperator(String token){

        if(supportedOp.contains(token))
            return true;

        return false;
    }

    //Checks if is a numeric value
    public static boolean isNumeric(String token){

        if(token.matches("\\d+"))
            return true;

        return false;
    }

    //Calculate the value given the polish notation
    public Num calculate(Queue<String> q, Stack<Num> stack){

        //Stack<Num> stack = new Stack<>();

        for(String item: q){

            if(isNumeric(item)) {
                stack.push(new Num(item));

            }else if(isValidOperator(item)){

                Num right;
                Num left;

                if(item.equals("+")){
                    right = stack.pop();
                    left = stack.pop();
                    stack.push(Num.add(left, right));

                }else if(item.equals("-")){
                    right = stack.pop();
                    left = stack.pop();
                    //stack.push(Num.sub(left, right))

                }else if(item.equals("*")){
                    right = stack.pop();
                    left = stack.pop();
                    stack.push(Num.simpleMul(left, right));

                }else if(item.equals("/")){
                    right = stack.pop();
                    left = stack.pop();
                    stack.push(Num.divide(left, right));

                }else if(item.equals("%")){
                    right = stack.pop();
                    left = stack.pop();
                    //stack.push(Num.mod(left, right));

                }else if(item.equals("^")){
                    right = stack.pop();
                    left = stack.pop();
                    stack.push(Num.pow(left, right));

                }else if(item.equals("|")){
                    right = stack.pop();
                    stack.push(Num.squareRoot(right));
                }

            }//to do, handle the case of ?, = or ;
            else if(item.equals("=") || item.equals("?") || item.equals(";")){

            }
            else
                throw new IllegalArgumentException("Invalid token, neither a number nor a token: " + item);


        }

        return stack.pop();
    }

    //Calculate the precedence of the operators
    public static int precedence(String op){

        if(op.equals("(") || op.equals(")"))
            return 1;

        else if(op.equals("|"))
            return 2;

        else if(op.equals("^"))
            return 3;

        else if(op.equals("*") || op.equals("/") || op.equals("%"))
            return 4;

        else if(op.equals("+") || op.equals("-"))
            return 5;

        return -1;
    }

}
