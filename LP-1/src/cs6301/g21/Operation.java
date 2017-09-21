package cs6301.g21;

import java.util.Queue;

public class Operation {
    private static int JUMP = 3;
    private static int POST = 2;
    private static int VAR = 1;
    private static int PRINT = 0;
    private int type;
    private int jumps;
    private Num value;
    private Queue expression;
	private String varName;

    public Operation(){
        type = -1;
        jumps = 0;
        value = null;
    }
    public void setType(int type){
        this.type = type;
    }
    
	public int getType(){
		return this.type;
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
		Num val_ = Register.varReg.get(this.varName);
		if(val_.compareTo(zero_) == 1){
			return jumps[0];
		}
		return jumps[1];
	}
}
