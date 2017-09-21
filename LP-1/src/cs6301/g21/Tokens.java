package cs6301.g21;

import java.util.ArrayList;

//Contains functions used by Postfix and infix evaluation, shunting yard
//and infix to postfix conversion. Make changes if necessary
public class Tokens {
	private static ArrayList<String> supportedOp;

    public Tokens(){
    	supportedOp=new ArrayList<String>();
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
        supportedOp.add("|");
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
    
	public static boolean isVariable(String token){

        if(Register.varReg.containsKey(token))
            return true;

        return false;
    }

}
