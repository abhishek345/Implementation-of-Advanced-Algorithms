package cs6301.g21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class LP1L4 {

    public static void main(String args[])throws FileNotFoundException{
        ArrayList<Operation> program = new ArrayList<>();
        

        if(args.length > 0){
            Scanner in = new Scanner(new File(args[0]));
            while (in.hasNextLine()){
                String line = in.nextLine();
                if(line.equals(";"))
                    break;
                Operation op = new Operation();
                int type=-1;
                if(line.contains("=")) {
                    type = 1;
                    op.setType(1);
                    if (line.contains("+...")) {
                        op.setType(2);
                        type = 2;
                    }
                }else if(line.contains("?")){
                    op.setType(3);
                    type = 3;
                }
                String [] tokens = line.split(" ");
                for(int i=0;i < tokens.length;i++){
                    if(i==0) {
                        if (tokens[i].matches("\\d+")) {
                            String lno = tokens[0];
                            Register.progReg.put(lno, program.size());

                            if (type == 2) {
                                //op.setExpr(getPostfix(tokens));
                            }

                        }else{
                            //var name
                            if(type == 2){
                                //expression
                            }
                            else{
                                //var assmt

                            }
                        }
                    }
                    else {
                        if (type != 2) {
                            if (type == 3) {
                                //its a jump
                                //[lineNo] var ? jump1 [: jump2]
                                if (tokens[i].matches("\\d+")){

                                }
                            } else {
                                //var assignment
                                //op.setType
                                //op.setValue(new Num(tokes[i]);
                                varReg.put(tokens[i], op);
                            }
                        }
                    }
                }
                program.add(op);
                System.out.println(line);
            }
            for(int i=0;i < program.size(); i++){
                Operation ptr = program.get(i);
                int type = ptr.getType();
                switch(type){
                    case 3://JUMP
                        String lno = ptr.resolve(varReg.get(ptr.getVar()));
                        i =
                        break;
                    case 2://POSTFIX
                        Num ans;
//                        ans = evaluate(ptr.getVar());
//                        varRegister.put(ptr.getVar());
                        break;
                    case 1://VAR ASSIGNMENT
                        break;
                    case 0://PRINT VAR
                        System.out.print("");
                        //printList
                    default:

                }
            }


        }
    }
}

