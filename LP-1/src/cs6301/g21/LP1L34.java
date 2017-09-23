package cs6301.g21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Driver function which can process inputs from both Level 3 and 4.
 * Command line argument accepts a base, if no argument default base is used.
 * Reads input from console.
 *
 * @author Umang Shah, Shreya Vishwanath Rao, Vibha Belavadi, Umang Shah, Abhishek Jagwani
 * @version 1.0: 2017/09/19
 */
public class LP1L34 {
    private static int JUMP = 3;
    private static int POST = 2;
    private static int VAR = 1;
    private static int PRINT = 0;

    public static void main(String args[])throws FileNotFoundException{
        ArrayList<Operation> program = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        if(args.length > 0){
            Num.changeBase(Long.parseLong(args[0]));
            System.out.println("Using base from input arguments.");
        }else{
            System.out.println("Using default base: 2^30 .");
        }
//            String infile = "/home/uks/Downloads/data-lp1(1)/lp1-l4-in2.txt";
//            sc = new Scanner(new File(infile));
            ShuntingYard.populateOperators();
            while (true){
                String line = sc.nextLine();
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
                        else if(ShuntingYard.isValidOperator(tokens[i]) || (type == VAR && tokens[i].matches("\\[a-zA-Z]+"))){
                            //expression
                            Queue q;
                            String e = line.substring(line.indexOf("= ")+2,line.indexOf(";")-1);
                            q = InfixToPostfix.infixToPostfix(e);
                            op.setExpression(q);
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
                            type = JUMP;
                            op.setType(type);

                            break;
                        }
                        else if(tokens[i].contains(";")) break;
                    }
                }
                program.add(op);
            }

            /**
             * Execution of operations in program
             */
            int pc = 0;
            Num lastvar = null; //last var to be printed or assigned
            while(pc < program.size()){
                //System.out.println(pc);
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
                        ans = PostfixEvaluator.evaluate(ptr.getExpression());
                        Register.putVar(ptr.getVar(), ans);
                        pc++;
                        lastvar = ans;
                        break;
                    case 1://VAR ASSIGNMENT
                        Register.putVar(ptr.getVar(),ptr.getValue());
                        pc++;
                        break;
                    case 0://PRINT VAR
                        pc++;
                        break;
                    default:
                        System.out.println("Unexpected Error");
                        break;
                }
                if(ptr.getLevel() == 3){
                    Num outNum = Register.getVar(ptr.getVar());
                    System.out.println(outNum);
                    lastvar = outNum;
                }
            }
            if(lastvar != null)
                lastvar.printList();

    }
}

