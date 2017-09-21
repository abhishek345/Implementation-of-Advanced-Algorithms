package cs6301.g21;

import java.util.HashMap;

public class Register{
	static HashMap<String, Num> varReg = new HashMap<>();
	static HashMap<String, Integer> progReg = new HashMap<>();
	static Num zero_ = new Num(0);

	static void putVar(String var, Num val){
		varReg.put(var, val);
	}

	static Num getVar(String var){
		return varReg.get(var);
	}

	static void putLineNo(String lno, int pc){
		progReg.put(lno, pc);
	}

	static int getLineNo(String lno){
	    if(lno != null)
		    return progReg.get(lno);
	    return -1;
	}
}