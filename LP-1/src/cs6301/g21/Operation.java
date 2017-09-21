package cs6301.g21;

import java.util.ArrayList;
import java.util.Queue;

public class Operation {
    private static ArrayList<String> supportedOp;

    private static int JUMP = 3;
    private static int POST = 2;
    private static int VAR = 1;
    private static int PRINT = 0;
    private int type;
    private String jumps[];
    private Num value;
    private Queue expression;
	private String varName;
	private int level;
//	private
    public Operation(){

        type = -1;
        jumps = new String[2];
        value = null;
    }

    public static void initChecker(){
        supportedOp = new ArrayList<>();
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

    public void setType(int type){
        this.type = type;
    }
    
	public int getType(){
		return this.type;
	}
	
	public void setValue(String s){
		this.value = new Num(s);
	}

	public Num getValue(){
		return this.value;
	}

	public void setVar(String var){
		this.varName = var;
	}
	
	public String getVar(){
		return this.varName;
	}
	
	public void setExpression(Queue expr){
		this.expression = expr;
	}
	
	public Queue getExpression(){
		return this.expression;
	}
	
	public void setJumps(String j1, String j2){
		jumps[0] = j1;
		jumps[1] = j2;
	}
	
	public String resolve(){
		Num val_ = Register.getVar(this.varName);
		if(val_.compareTo(Register.zero_) == 1){
			return jumps[0];
		}
		return jumps[1];
	}

	public void setLevel(int level){
	    this.level = level;
	}

	public int getLevel(){
	    return this.level;
    }

	public String toString(){
	    StringBuilder s = new StringBuilder();
	    s.append(" lev: "+level);
	    s.append(" type: "+type);
        s.append(" var: "+varName);
	    if(type == JUMP) {
            s.append(" ? " + jumps[0] + " : " + jumps[1] + " = ");
            s.append(Register.getLineNo(jumps[0]) + " : " + Register.getLineNo(jumps[1]) + " ;");
        }
	    else if(type == POST)
	        s.append("EXP: " + expression+" ;");
        else if(type == VAR)
            s.append((" Val = " + value+" ;"));
        else
            s.append(" print ;");
	    return s.toString();
    }
}
