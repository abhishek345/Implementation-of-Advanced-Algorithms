package cs6301.g21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class LP1L4 {
    private static int JUMP = 3;
    private static int POST = 2;
    private static int VAR = 1;
    private static int PRINT = 0;

    public static void main(String args[])throws FileNotFoundException{
        ArrayList<Operation> program = new ArrayList<>();
        

        if(true){//argslength > 0 and file from cmd line
            Scanner in = new Scanner(new File("/home/uks/ImplofAlgos/Implementation-of-Advanced-Algorithms/LP-1/src/cs6301/g21/input/lp1-l4-in2.txt"));
            Operation.initChecker();
            while (in.hasNextLine()){
                String line = in.nextLine();
                if(line.equals(";"))
                    break;
                Operation op = new Operation();
                String [] tokens = line.split(" ");
                int type = -1;
                op.setLevel(3);
                for(int i=0;i < tokens.length;i++){
                    if(i==0) {
                        if (tokens[i].matches("\\d+")) {
                            String lno = tokens[0];
                            Register.putLineNo(lno, program.size());
                            op.setLevel(4);
                        }else{
                            //var name
                            type = PRINT;
                            op.setType(type);
                            op.setVar(tokens[0]);
                        }
                    }
                    else if(i == 1 && type < 0 && !tokens[i].matches("\\d+")){

                        type = PRINT;
                        op.setVar(tokens[1]);
                        op.setType(type);
                    }
                    else {
                        if(tokens[i].contains("=")){
                            //either asmt or expression
                            type = VAR;
                            op.setType(type);
                        }
                        else if(tokens[i].matches("\\d+")){
                            //either asmt or start of an expression
                            if(type == VAR)
                                op.setValue(tokens[i]);
                        }
                        else if(Operation.isValidOperator(tokens[i]) || (type == VAR && tokens[i].matches("\\[a-zA-Z]+"))){
                            //expression
                            Queue q = new ArrayDeque();
                            String e = line.substring(line.indexOf("= ")+2);
                            //q = convert(e);
                            //op.setExpression(q);
                            type = POST;
                            op.setType(type);
                            break;
                        }
                        else if(tokens[i].contains("?")){
                            //JUMP
                            String token = line.substring(line.indexOf("?")+1, line.indexOf(";"));
                            token = token.replace(" ", "");
                            if(token.contains(":")){
                                String[] j = token.split(":");
                                op.setJumps(j[0], j[1]);
                            }else{
                                op.setJumps(token, null);
                            }
                            type = 3;
                            op.setType(JUMP);

                            break;
                        }
                    }
                }
                program.add(op);
            }

            for(int i=0;i < program.size();i++){
                System.out.println(i + " > "+program.get(i));
            }
            int pc = 100;
            while(pc < program.size()){
                Operation ptr = program.get(pc);
                int type = ptr.getType();
                switch(type){
                    case 3://JUMP
                        String lno = ptr.resolve();
                        if(lno == null)
                            pc++;
                        else
                            pc = Register.getLineNo(lno);
                        break;
                    case 2://POSTFIX
                        Num ans;
//                        ans = evaluate(ptr.getExpression());
//                        varRegister.put(ptr.getVar());
                        pc++;
                        break;
                    case 1://VAR ASSIGNMENT
                        Register.putVar(ptr.getVar(),ptr.getValue());
                        pc++;
                        break;
                    case 0://PRINT VAR
//                        Num outNum = Register.getVar(ptr.getVar());
//                        System.out.print(outNum);
//                        outNum.printList();
                        pc++;
                        break;
                    default:
                        System.out.println("Unexpected Error");
                        break;
                }
                if(ptr.getLevel() == 3){
                    Num outNum = Register.getVar(ptr.getVar());
                    System.out.println(outNum);
                    outNum.printList();
                }
            }


        }
    }
}

