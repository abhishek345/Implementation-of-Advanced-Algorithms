package cs6301.g21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class LP1L4 {

    public static void main(String args[])throws FileNotFoundException{
        LinkedList<Operation> program = new LinkedList<>();
        HashMap<String, Operation> access = new HashMap<>();

        if(args.length > 0){
            Scanner in = new Scanner(new File(args[0]));
            while (in.hasNextLine()){
                String line = in.nextLine();
                if(line.equals(";"))
                    break;
                Operation op = new Operation();
                int type=-1;
                if(line.contains("+...")) {
                    op.setType(2);
                    type = 2;
                }else if(line.contains("?")){
                    op.setType(3);
                    type = 3;
                }
                String [] tokens = line.split(" ");
                for(int i=0;i < tokens.length;i++){
                    if(tokens[i].matches("\\d+")){
                        if(i==0) {
                            String lno = tokens[0];
                            access.put(lno, op);
                        }
                        else{
                            if(type == 2){
                                //op.setExpr(getPostfix(tokens));
                            }
                        }
                    }
                    else if(type != 2){
                        if(type == 3){
                            //its a jump
                        }
                        else{
                            //var assignment
                            //op.setType
                            //op.setValue(new Num(tokes[i]);
                            access.put(tokens[i], op);
                        }
                    }
                }
                program.add(op);
                System.out.println(line);
            }

        }
    }
}
