package cs6301.g21;

import java.util.ArrayList;

public class ShuntingYard {

    private static ArrayList<String> supportedOp = new ArrayList<String>();

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

    //Checks if it is a valid operator
    public static boolean isValidOperator(String token){



        if(supportedOp.contains(token))
            return true;

        return false;
    }

    //

    /**
     * Checks if is a token is alphanumeric value
     * @param token String token to be evaluated
     * @return Boolean true if
     */
    public static boolean isAlphaNumeric(String token){

        if(token.matches("^[a-zA-Z0-9]*$"))
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

    public static void main(String[] args) {

        //ShuntingYard.populateOperators();

        System.out.println(ShuntingYard.isValidOperator("+"));

    }

}