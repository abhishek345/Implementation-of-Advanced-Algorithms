package cs6301.g21;

import java.util.ArrayList;

/**
 * The Shunting Yard Algorithm and helper functions.
 *
 * @author Shreya Vishwanath Rao, Vibha Belavadi, Umang Shah, Abhishek Jagwani
 * @version 2.0: 2017/09/23
 */
public class ShuntingYard {

    private static ArrayList<String> supportedOp = new ArrayList<String>();

    /**
     * Populate list with supported operators
     */
    public static void populateOperators(){
        supportedOp.add("+");
        supportedOp.add("-");
        supportedOp.add("*");
        supportedOp.add("/");
        supportedOp.add("(");
        supportedOp.add(")");
        supportedOp.add("^");
        supportedOp.add("|");
        supportedOp.add("%");
    }

    /**
     * Checks if token is one of the supported operations
     * @param token String token to be evaluated
     * @return Boolean true if valid; else false
     */
    public static boolean isValidOperator(String token){

        if(supportedOp.contains(token))
            return true;

        return false;
    }

    /**
     * Checks if is a token is alphanumeric value
     * @param token String token to be evaluated
     * @return Boolean true for alphanumeric token; else false
     */
    public static boolean isAlphaNumeric(String token){

        if(token.matches("^[a-zA-Z0-9]*$"))
            return true;

        return false;
    }
    
    /**
    * Checks if is the token is a numeric value
    *
    * @param token :String token to be evaluated
    * @return true if token is numeric; false otherwise
    */
    public static boolean isNumeric(String token){

        if(token.matches("\\d+"))
            return true;

        return false;
    }
    
    /**
    * Checks if the token is a variable
    *
    * @param token: String token to be evaluated
    * @return true if token is a variable; false otherwise
    */
	public static boolean isVariable(String token){

        if(Register.varReg.containsKey(token))
            return true;

        return false;
    }


    /**
     * Calculates the precedence of the operators
     * @param op String operator whose precedence needs to be calculated
     * @return Integer the precedence value of the operation
     */
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
